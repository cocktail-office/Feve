package org.cocktail.feve.app;

/*
 * Copyright Universit� de La Rochelle 2007
 *
 * ctarade@univ-lr.fr
 *
 * Ce logiciel est un programme informatique servant � g�rer les comptes
 * informatiques des utilisateurs. 
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".

 * En contrepartie de l'accessibilit� au code source et des droits de copie,
 * de modification et de redistribution accord�s par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
 * seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les conc�dants successifs.

 * A cet �gard  l'attention de l'utilisateur est attir�e sur les risques
 * associ�s au chargement,  � l'utilisation,  � la modification et/ou au
 * d�veloppement et � la reproduction du logiciel par l'utilisateur �tant 
 * donn� sa sp�cificit� de logiciel libre, qui peut le rendre complexe � 
 * manipuler et qui le r�serve donc � des d�veloppeurs et des professionnels
 * avertis poss�dant  des  connaissances  informatiques approfondies.  Les
 * utilisateurs sont donc invit�s � charger  et  tester  l'ad�quation  du
 * logiciel � leurs besoins dans des conditions permettant d'assurer la
 * s�curit� de leurs syst�mes et ou de leurs donn�es et, plus g�n�ralement, 
 * � l'utiliser et l'exploiter dans les m�mes conditions de s�curit�. 

 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accept� les
 * termes.
 */

//TODO pb de refresh lors de modif d'occupation, les fiches de poste de l'evaluation ne sont pas mises a jour immediatement

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpActi;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpComp;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.version.A_CktlVersion;
import org.cocktail.ycrifwk.utils.UtilDb;
import org.cocktail.ycrifwk.utils.UtilException;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXMessageEncoding;
import er.extensions.eof.ERXDatabaseContext;
import er.extensions.eof.ERXDatabaseContextDelegate;

public class Application extends NextCRIWebApplication {

	public static void main(String argv[]) {
		er.extensions.appserver.ERXApplication.main(argv, Application.class);
		// com.webobjects.appserver.WOApplication.main(argv, Application.class);
	}

	public Application() {
		super();

		// pour ne pas planter sur les to-one n'ayant pas d'objet dans la base de
		// données
		ERXDatabaseContextDelegate.setDefaultDelegate(new FeveDatabaseContextDelegate());
		ERXDatabaseContext.setDefaultDelegate(new FeveDatabaseContextDelegate());

		setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
		WOMessage.setDefaultEncoding("UTF-8");
		WOMessage.setDefaultURLEncoding("UTF-8");
		ERXMessageEncoding.setDefaultEncoding("UTF8");
		ERXMessageEncoding.setDefaultEncodingForAllLanguages("UTF8");

	}

	/**
	 * Execute les operations au demarrage de l'application, juste apres
	 * l'initialisation standard de l'application.
	 */
	@Override
	public void startRunning() {

		// settage du timezone
		/*
		 * String tzs = config().stringForKey("DEFAULT_TIME_ZONE"); if(tzs == null)
		 * { tzs = "CEST"; } java.util.TimeZone tz =
		 * java.util.TimeZone.getTimeZone(tzs); java.util.TimeZone.setDefault(tz);
		 * NSTimeZone.setDefault(tz);
		 */

		java.util.TimeZone tz = java.util.TimeZone.getTimeZone(config().stringForKey("APP_TIME_ZONE"));
		java.util.TimeZone.setDefault(tz);
		NSTimeZone.setDefault(tz);

		rawLogAppInfos();
		rawLogVersionInfos();
		rawLogModelInfos();

		if (checkCustomParams() == false) {
			System.exit(-1);
		}

		if (checkModel() == false) {
			CktlLog.log("ATTENTION, tous les modèles ne pointent pas sur la même base de données !!");
		}

		//
		if (config().booleanForKey(KEY_APP_FIX_POSITION)) {
			fixFicheDePostePosition();
		}
	}

	@Override
	public String mainModelName() {
		return "Grhum";
	}

	public String configTableName() {
		return "FwkCktlWebApp_GrhumParametres";
	}

	@Override
	public String configFileName() {
		return "Feve.config";
	}

	// controle de version

	private A_CktlVersion _appCktlVersion;

	@Override
	public A_CktlVersion appCktlVersion() {
		if (_appCktlVersion == null) {
			_appCktlVersion = new Version();
		}
		return _appCktlVersion;
	}

	private A_CktlVersion _appCktlVersionDb;

	@Override
	public A_CktlVersion appCktlVersionDb() {
		if (_appCktlVersionDb == null) {
			_appCktlVersionDb = new VersionMangueDbUser();
		}
		return _appCktlVersionDb;
	}

	@Override
	public boolean appShouldSendCollecte() {
		return true;
	}

	@Override
	public String[] configMandatoryKeys() {
		return new String[] {
				"C_STRUCTURE_ADMIN",
				"APP_USE_CAS",
				APP_ADMIN_MAIL };
	}

	private final static String CONFIG_KEY_APP_CLOSED_SYNC_REFERENS = "APP_CLOSED_SYNC_REFERENS";
	private final static String CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION = "ADDITIONNAL_URL_DOCUMENTS_EVALUATION";
	private final static String CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION_FONCTIONNAIRE = "ADDITIONNAL_URL_DOCUMENTS_EVALUATION_FONCTIONNAIRE";
	@Deprecated
	private final static String CONFIG_URL_GUIDE_ENTRETIEN_AGENT_KEY = "URL_GUIDE_ENTRETIEN_AGENT";
	@Deprecated
	private final static String CONFIG_URL_GUIDE_ENTRETIEN_RESPONSABLE_KEY = "URL_GUIDE_ENTRETIEN_RESPONSABLE";
	private final static String CONFIG_KEY_SIX_TTL = "APP_SIX_TTL";

	@Override
	public String[] configOptionalKeys() {
		return new String[] {
				"GRHUM_PHOTO",
				"MAIN_LOGO_URL",
				"MAIN_WEB_SITE_URL",
				"SIX_SERVICE_HOST",
				"SIX_SERVICE_PORT",
				CONFIG_KEY_APP_CLOSED_SYNC_REFERENS,
				CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION,
				CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION_FONCTIONNAIRE,
				APP_ERROR_MAIL,
				CONFIG_KEY_SIX_TTL };
	}

	// getters des variables de l'application

	private String cStructureAdmin, mainLogoUrl, mainWebSiteUrl, appAdminMail, appErrorMail;
	private Boolean grhumPhoto, sixUseSix, appUseCas, appClosedSyncReferens;
	private NSArray<String> additionnalUrlDocumentsEvaluation, additionnalUrlDocumentsEvaluationFonctionnaire;
	private Integer sixTtl;

	public String cStructureAdmin() {
		if (cStructureAdmin == null) {
			cStructureAdmin = config().stringForKey("C_STRUCTURE_ADMIN");
		}
		return cStructureAdmin;
	}

	public boolean isAppClosedSyncReferens() {
		if (appClosedSyncReferens == null) {
			appClosedSyncReferens = new Boolean(config().booleanForKey(CONFIG_KEY_APP_CLOSED_SYNC_REFERENS));
		}
		return appClosedSyncReferens.booleanValue();
	}

	public NSArray<String> getAdditionnalUrlDocumentsEvaluation() {
		if (additionnalUrlDocumentsEvaluation == null) {
			String strAdditionnalUrlDocumentsEvaluation = config().stringForKey(CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION);
			if (!StringCtrl.isEmpty(strAdditionnalUrlDocumentsEvaluation)) {
				additionnalUrlDocumentsEvaluation = NSArray.componentsSeparatedByString(strAdditionnalUrlDocumentsEvaluation, ",");
			} else {
				additionnalUrlDocumentsEvaluation = new NSArray<String>();
			}
		}
		return additionnalUrlDocumentsEvaluation;
	}

	public NSArray<String> getAdditionnalUrlDocumentsEvaluationFonctionnaire() {
		if (additionnalUrlDocumentsEvaluationFonctionnaire == null) {
			String strAdditionnalUrlDocumentsEvaluationFonctionnaire = config().stringForKey(CONFIG_KEY_ADDITIONNAL_URL_DOCUMENTS_EVALUATION_FONCTIONNAIRE);
			if (!StringCtrl.isEmpty(strAdditionnalUrlDocumentsEvaluationFonctionnaire)) {
				additionnalUrlDocumentsEvaluationFonctionnaire = NSArray.componentsSeparatedByString(strAdditionnalUrlDocumentsEvaluationFonctionnaire, ",");
			} else {
				additionnalUrlDocumentsEvaluationFonctionnaire = new NSArray<String>();
			}
		}
		return additionnalUrlDocumentsEvaluationFonctionnaire;
	}

	public boolean grhumPhoto() {
		if (grhumPhoto == null) {
			grhumPhoto = new Boolean(config().booleanForKey("GRHUM_PHOTO"));
		}
		return grhumPhoto.booleanValue();
	}

	public String mainLogoUrl() {
		if (mainLogoUrl == null) {
			mainLogoUrl = config().stringForKey("MAIN_LOGO_URL");
		}
		return mainLogoUrl;
	}

	public String mainWebSiteUrl() {
		if (mainWebSiteUrl == null) {
			mainWebSiteUrl = config().stringForKey("MAIN_WEB_SITE_URL");
		}
		return mainWebSiteUrl;
	}

	public Boolean sixUseSix() {
		if (sixUseSix == null) {
			sixUseSix = new Boolean(
					!StringCtrl.isEmpty(config().stringForKey("SIX_SERVICE_HOST")) &&
							!StringCtrl.isEmpty(config().stringForKey("SIX_SERVICE_PORT")));
		}
		return sixUseSix;
	}

	public Boolean appUseCas() {
		if (appUseCas == null) {
			appUseCas = new Boolean(config().booleanForKey("APP_USE_CAS"));
		}
		return appUseCas;
	}

	// l'adresse email pour l'envoi des erreurs java si different de
	// APP_ADMIN_MAIL
	private final static String APP_ADMIN_MAIL = "APP_ADMIN_MAIL";

	private String appAdminMail() {
		if (appAdminMail == null) {
			appAdminMail = config().stringForKey(APP_ADMIN_MAIL);
		}
		return appAdminMail;
	}

	// l'adresse email pour l'envoi des erreurs java si different de
	// APP_ADMIN_MAIL
	private final static String APP_ERROR_MAIL = "APP_ERROR_MAIL";

	private String appErrorMail() {
		if (appErrorMail == null) {
			appErrorMail = config().stringForKey(APP_ERROR_MAIL);
		}
		return appErrorMail;
	}

	/**
	 * Temps maximum autorisée pour une conversion SIX. Au dela, on estime que
	 * l'édition a échoué
	 * 
	 * @return
	 */
	public int sixTtl() {
		if (sixTtl == null) {
			int iSixTtl = config().intForKey(CONFIG_KEY_SIX_TTL);
			if (iSixTtl == -1) {
				// valeur par defaut : 30 sec
				sixTtl = new Integer(5);
			} else {
				sixTtl = new Integer(iSixTtl);
			}
		}
		return sixTtl.intValue();
	}

	/**
	 * TODO provient de YCRIFwk pour avoir CktlAlertPage -> pb depuis passage dans
	 * les package gestion des exceptions : redirection vers une page d'erreur
	 * puis envoi du mail a l'adresse {@link #appErrorMail()} si defini, sinon a
	 * adm
	 */
	@Override
	public WOResponse handleException(Exception anException, WOContext aContext) {
		CktlLog.log("- Exception");
		anException.printStackTrace();
		if (anException.getCause() != null) {
			CktlLog.log("- Exception.getCause()");
			anException.getCause().printStackTrace();
		}
		CktlLog.log("-----------");
		String contenuPage =
				"Une erreur est survenue dans l'application ...<br>" +
						"Un mail contenant le message d'erreur a &eacute;t&eacute; envoy&eacute; &agrave; l'administrateur de l'application.<br>" +
						"L'erreur sera corrig&eacute;e au plus vite.<br><br>" +
						"Contenu de l'erreur :<br><br>" +
						"<textarea cols='75' rows='15'>" +
						UtilException.stackTraceToString(anException, false) +
						(anException.getCause() != null ?
								"\nCaused by : " + UtilException.stackTraceToString(anException.getCause(), false) : "") +
						"</textarea><br><br>" +
						"<center><a href='" + getApplicationInstanceURL(aContext) + "'>Fermer la session</a></center>";

		CktlAlertPage page = (CktlAlertPage) pageWithName(CktlAlertPage.class.getName(), aContext);
		page.showMessage(null, "Une erreur est survenue ...", contenuPage, null, null, null, CktlAlertPage.ERROR, null);
		String contenuMail = "Une exception est survenue ...\n";
		contenuMail += "Elle provient de la machine qui a l'ip *" + getRequestIPAddress(aContext.request()) + "*\n";
		contenuMail += "La personne qui a provoqué l'erreur est : ";
		Session feveSession = ((Session) aContext.session());
		FeveUserInfo uiConnected = feveSession.feveUserInfo();
		if (uiConnected != null) {
			contenuMail += "*" + uiConnected.nomEtPrenom() + "*\n\n";
			contenuMail += uiConnected.toString();
		} else {
			contenuMail += "????????]";
		}
		contenuMail += "\nsessionId = " + feveSession.sessionID();
		contenuMail += "\n\n";
		//
		String to = appErrorMail();
		if (StringCtrl.isEmpty(to)) {
			to = appAdminMail();
		}
		String from = to;
		if (uiConnected != null && !StringCtrl.isEmpty(uiConnected.email())) {
			from = uiConnected.email();
		}
		UtilException.sendExceptionTrace(mailBus(), name(), from, to, contenuMail, anException);
		WOResponse response = page.generateResponse();
		return response;
	}

	// numero de version

	private String _appVersionDateInstanceInfo;

	/**
	 * Les informations sur la base de donnees : - no version - date version -
	 * connection bdd
	 */
	public String appVersionDateInstanceInfo() {
		if (_appVersionDateInstanceInfo == null) {
			_appVersionDateInstanceInfo = appCktlVersion().txtVersion() + " - ";
			// information base de donnees
			EOModelGroup vModelGroup = EOModelGroup.defaultGroup();
			EOModel vModel = vModelGroup.modelNamed(mainModelName());
			_appVersionDateInstanceInfo += org.cocktail.fwkcktlwebapp.server.util.EOModelCtrl.bdConnexionServerId(vModel);
		}
		return _appVersionDateInstanceInfo;
	}

	//

	@Override
	public String[] listeVariablesCustomParametre() {
		return new String[] {
				EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_DEBUT,
				EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_FIN,
				EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_DEBUT,
				EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_FIN,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_CREATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF,
				EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF,
				EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION,
				EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION,
				EOMangueParametres.KEY_FEV_SELF_CONSULTATION_FICHE_DE_POSTE,
				EOMangueParametres.KEY_FEV_SELF_CONSULTATION_FICHE_LOLF,
				EOMangueParametres.KEY_FEV_SELF_CONSULTATION_EVALUATION,
				EOMangueParametres.KEY_FEV_SELF_CONSULTATION_POSTE,
				EOMangueParametres.KEY_FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES,
				EOMangueParametres.KEY_FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES,
				EOMangueParametres.KEY_FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL,
				EOMangueParametres.KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT,
				EOMangueParametres.KEY_FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION };
	}

	@Override
	public String customConfigEntityName() {
		return EOMangueParametres.ENTITY_NAME;
	}

	@Override
	public String customConfigTableName() {
		return EOMangueParametres.ENTITY_TABLE_NAME;
	}

	@Override
	public String customConfigTableAttributeKeyName() {
		return EOMangueParametres.PARAM_KEY_KEY;
	}

	@Override
	public String customConfigTableAttributeValueName() {
		return EOMangueParametres.PARAM_VALUE_KEY;
	}

	private EOEditingContext paramEditingContext;

	/**
	 * EditingContext particulier qui ne doit pas etre qu'en lecture seule
	 * (donnees modifiee dans l'interface d'administration)
	 * 
	 * @return
	 */
	@Override
	public EOEditingContext paramEditingContext() {
		if (paramEditingContext == null) {
			paramEditingContext = new EOEditingContext();
		}
		return paramEditingContext;
	}

	// gestion des variables globales a l'application et de leur cache

	public NSTimestamp getEvaluationSaisieDDebut() {
		return DateCtrl.stringToDate(getParamValueForKey(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_DEBUT));
	}

	public NSTimestamp getEvaluationSaisieDFin() {
		return DateCtrl.stringToDate(getParamValueForKey(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_FIN));
	}

	public NSTimestamp getFicheLolfSaisieDDebut() {
		return DateCtrl.stringToDate(getParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_DEBUT));
	}

	public NSTimestamp getFicheLolfSaisieDFin() {
		return DateCtrl.stringToDate(getParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_FIN));
	}

	/**
	 * Indique si l'application autorise la saisie des fiches LOLF
	 * 
	 * @return
	 */
	public boolean isPeriodeLolfOuverte() {
		return (DateCtrl.isAfterEq(DateCtrl.now(), getFicheLolfSaisieDDebut()) && DateCtrl.isBeforeEq(DateCtrl.now(), getFicheLolfSaisieDFin().timestampByAddingGregorianUnits(0, 0, 0, 23, 59, 59)));
	}

	/**
	 * Indique si l'application autorise la saisie des fiches LOLF
	 * 
	 * @return
	 */
	public boolean isPeriodeEvaluationOuverte() {
		return (DateCtrl.isAfterEq(DateCtrl.now(), getEvaluationSaisieDDebut()) && DateCtrl.isBeforeEq(DateCtrl.now(), getEvaluationSaisieDFin().timestampByAddingGregorianUnits(0, 0, 0, 23, 59, 59)));
	}

	private NSMutableDictionary dicoVariables = new NSMutableDictionary();

	/**
	 * Retrouver une variable depuis le dictionnaire des variables. Si pas
	 * disponible, alors on cherche dans la table de parametres et on stocke la
	 * valeur dans le dictionnaire.
	 * 
	 * @param paramKey
	 * @return
	 */
	public String getParamValueForKey(String paramKey) {
		String value = (String) dicoVariables.valueForKey(paramKey);
		if (value == null) {
			value = customConfigStringForKey(paramKey);
			// si null, on met la chaine vide
			if (value == null) {
				value = StringCtrl.emptyString();
			}
			dicoVariables.setObjectForKey(value, paramKey);
		}
		return value;
	}

	/**
	 * Obtenir la valeur booleene d'un parametre
	 * 
	 * @param paramKey
	 * @return
	 */
	public Boolean getBooleanParamValueForKey(String paramKey) {
		return new Boolean(getParamValueForKey(paramKey).equals(A_FeveCktlRecord.OUI));
	}

	/**
	 * Obtenir la valeur date d'un parametre
	 * 
	 * @param paramKey
	 * @return
	 */
	public NSTimestamp getDateParamValueForKey(String paramKey) {
		return DateCtrl.stringToDate(getParamValueForKey(paramKey));
	}

	/**
	 * Obtenir la valeur Integer d'un parametre
	 * 
	 * @param paramKey
	 * @return
	 */
	public Integer getIntegerParamValueForKey(String paramKey) {
		return new Integer(Integer.parseInt(getParamValueForKey(paramKey)));
	}

	/**
	 * RAZ du cache d'une variable ciblee
	 */
	public void clearCache(String paramKey) {
		dicoVariables.removeObjectForKey(paramKey);
	}

	private final static String KEY_APP_FIX_POSITION = "APP_FIX_POSITION";

	/**
	 * Corrige un bug qui a mis toutes les positions des association d'activite et
	 * competences a 1.
	 */
	private void fixFicheDePostePosition() {
		EOEditingContext ec = new EOEditingContext();
		NSArray ficheList = EOFicheDePoste.fetchAllFicheDePostes(ec);
		for (int i = 0; i < ficheList.count(); i++) {
			EOFicheDePoste fiche = (EOFicheDePoste) ficheList.objectAtIndex(i);
			// les activites
			NSArray reparList = fiche.tosRepartFdpActi();
			for (int j = 0; j < reparList.count(); j++) {
				EORepartFdpActi repart = (EORepartFdpActi) reparList.objectAtIndex(j);
				if (repart.rfaPosition() == null || repart.rfaPosition().intValue() != (j + 1)) {
					repart.setRfaPosition(new Integer(j + 1));
				}
			}
			// les activites autres
			reparList = fiche.tosRepartFdpActivitesAutres();
			for (int j = 0; j < reparList.count(); j++) {
				EORepartFdpAutre repart = (EORepartFdpAutre) reparList.objectAtIndex(j);
				if (repart.fauPosition() == null || repart.fauPosition().intValue() != (j + 1)) {
					repart.setFauPosition(new Integer(j + 1));
				}
			}
			// les competences
			reparList = fiche.tosRepartFdpComp();
			for (int j = 0; j < reparList.count(); j++) {
				EORepartFdpComp repart = (EORepartFdpComp) reparList.objectAtIndex(j);
				if (repart.rfcPosition() == null || repart.rfcPosition().intValue() != (j + 1)) {
					repart.setRfcPosition(new Integer(j + 1));
				}
			}
			// les competences autres
			reparList = fiche.tosRepartFdpCompetencesAutres();
			for (int j = 0; j < reparList.count(); j++) {
				EORepartFdpAutre repart = (EORepartFdpAutre) reparList.objectAtIndex(j);
				if (repart.fauPosition() == null || repart.fauPosition().intValue() != (j + 1)) {
					repart.setFauPosition(new Integer(j + 1));
				}
			}
		}
		try {
			UtilDb.save(ec, "");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}