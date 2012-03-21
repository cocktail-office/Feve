package org.cocktail.feve.app;

/*
 * Copyright Universit� de La Rochelle 2004
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

import org.cocktail.feve.app.print.FevePdfBoxCtrl;
import org.cocktail.feve.components.PageBibliotheque;
import org.cocktail.feve.components.PageChangeIdentite;
import org.cocktail.feve.components.PageLogin;
import org.cocktail.feve.components.PageLogs;
import org.cocktail.feve.components.PagePersonnel;
import org.cocktail.feve.components.PageToutesLesFiches;
import org.cocktail.feve.components.administration.PageAdministration;
import org.cocktail.feve.components.common.PageTemplate;
import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.mangue.EOTplFiche;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;
import org.cocktail.fwkcktlajaxwebext.serveur.CocktailAjaxSession;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.components.CktlMenuItemSet;
import org.cocktail.fwkcktlwebapp.server.components.CktlMenuListener;

import com.webobjects.appserver.WOComponent;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;

public class Session extends CocktailAjaxSession {

	// niveaux de connexion a l'application

	public static int NIVEAU_CONNEXION_ENQUETE = 0; // juste les enquetes de
																									// formations
	public static int NIVEAU_CONNEXION_COMPLETE = 1; // tout le reste
	public static int NIVEAU_CONNEXION_LOLF = 2; // juste le formulaire lolf et
																								// les stats
	public static int NIVEAU_DA_FICHE_DE_POSTE = 3; // on consulte une fiche de
																									// poste uniquement
	private int niveauConnexion;

	private EOIndividu individuConnecte;

	public Session() {
		super();
		// niveau par defaut
		setNiveauConnexion(NIVEAU_CONNEXION_COMPLETE);
		log("open session");
		// getAllFormationPersonnelArray();
	}

	public EOEditingContext ec() {
		return defaultEditingContext();
	}

	private FeveUserInfo feveUserInfo;

	/**
	 * Ensemble d'infos sur la personne connectee
	 */
	public FeveUserInfo feveUserInfo() {
		return feveUserInfo;
	}

	/**
	 * gestion de la connection et du settage des droits d'un individu
	 * 
	 * @param individu
	 * @return
	 */
	public WOComponent loginRedirect(Number persId) {

		// on est plus sur la page de login
		isPageLogin = false;

		EOIndividu individu = null;
		if (persId != null) {
			feveUserInfo = new FeveUserInfo((Application) cktlApp, dataBus(), ec(), persId);
			setConnectedUserInfo(feveUserInfo);
			CktlLog.log("login : " + feveUserInfo.login() + " - OK");
			individu = feveUserInfo.recIndividu();
		}

		// si l'application est fermee pour cause de mise a jour de REFERENS
		// on ne laisse passer que les administrateurs
		if (((Application) cktlApp).isAppClosedSyncReferens() && !feveUserInfo().isAdmin()) {
			return CktlAlertPage.newAlertPageWithMessage(
					getSavedPageWithName(PageLogin.class.getName()),
					"Application en cours de mise &agrave; jour",
					"La base de donn&eacute;es de l'application est actuellement en cours de migration.<br/>" +
							"Merci de bien vouloir r&eacute;essayer de vous connecter plus tard ...",
					CktlAlertPage.INFO);
		}

		// raz menu
		_feveMenuItemSet = null;
		_feveMenuLister = null;

		setIndividuConnecte(individu);

		if (niveauConnexion() == NIVEAU_CONNEXION_COMPLETE
				|| niveauConnexion() == NIVEAU_CONNEXION_LOLF) {
			// setDroits();
		}

		WOComponent nextPage = null;

		if (niveauConnexion() == NIVEAU_CONNEXION_COMPLETE) {
			// nextPage = getSavedPageWithName(PageAccueil.class.getName());
			// on selectionne les fiches perso par defaut
			nextPage = selectFichesPersonnelles();
		} else if (niveauConnexion() == NIVEAU_CONNEXION_ENQUETE) {
			// enquete
			// retrouver l'evaluation
			// EOEvaluation evaluation =
			// FinderEvaluation.findEvaluationForIndividuAndAnneeInContext(
			// ec(),
			// individuConnecte(),
			// anneeEnCours()
			// );

			// nextPage = getSavedPageWithName(PageEnqueteFormation.class.getName());
			// ((PageEnqueteFormation)nextPage).externeReset(individu, evaluation);

		} else if (niveauConnexion() == NIVEAU_CONNEXION_LOLF) {
			// nextPage = getSavedPageWithName(.class.getName());
		}
		return nextPage;
	}

	// TEMOINS

	/**
	 * savoir si la personne surlaquelle on travaille est la personne connectee
	 * 
	 * @return
	 */
	public boolean isIndividuConnecte(EOIndividu unIndividu) {
		return unIndividu == individuConnecte();
	}

	/**
	 * Indique s'il faut afficher le menu. Il est tout le temps disponible sauf
	 * sur la page de login.
	 */
	public boolean isPageLogin = true;

	/**
	 * admin de l'appli (param C_STRUCTURE_ADMIN)
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return feveUserInfo().isAdmin();
	}

	// SETTERS
	public void setIndividuConnecte(EOIndividu value) {
		individuConnecte = value;
	}

	public void setNiveauConnexion(int value) {
		niveauConnexion = value;
	}

	// GETTERS
	public EOIndividu individuConnecte() {
		return individuConnecte;
	}

	public int niveauConnexion() {
		return niveauConnexion;
	}

	/**
	 * Le binding de CktlDefaultPage pour indiquer l'ancre a atteindre au prochain
	 * rechargement de la page
	 */
	public String targetPosition = StringCtrl.emptyString();

	/**
	 * Surcharge de cette methode le temps de tout passer en FwkCktlWebApp
	 */
	public void setOnLoad(String value) {
		if (!StringCtrl.isEmpty(value)) {
			// lors de cet appel, on a qqchose qui commence par
			// <document.location='#> et qui finit par <';>
			// on vire donc ces trucs plus utiles
			targetPosition = value.substring(value.indexOf("#") + 1, value.length() - 2);
		} else {
			targetPosition = StringCtrl.emptyString();
		}
	}

	// gestion du menu : 1 seule instance

	private CktlMenuListener _feveMenuLister;

	private CktlMenuItemSet _feveMenuItemSet;

	public CktlMenuListener getFeveMenuLister() {
		return _feveMenuLister;
	}

	public void setFeveMenuLister(CktlMenuListener value) {
		_feveMenuLister = value;
	}

	public CktlMenuItemSet getFeveMenuItemSet() {
		return _feveMenuItemSet;
	}

	public void setFeveMenuItemSet(CktlMenuItemSet value) {
		_feveMenuItemSet = value;
	}

	// la selection des items dans le menu principal se fait ici

	public WOComponent selectAdministration() {
		pageEnCoursTitre = "Administration";
		return getSavedPageWithName(PageAdministration.class.getName());
	}

	public WOComponent selectToutesLesFiches() {
		pageEnCoursTitre = "Toutes les fiches";
		// si cet item est deja selectionne, on va utiliser ce bouton
		// comme un bouton retour
		PageToutesLesFiches pageToutesLesFiches = (PageToutesLesFiches) getSavedPageWithName(PageToutesLesFiches.class.getName());
		if (prevMenuId == PageTemplate.ID_MENU_REC) {
			// on re-selectionne l'item selectionne du sous menu
			// pour forcer les remonteees
			String prevSubMenuLabel = pageToutesLesFiches.getSelectedItemMenu();
			pageToutesLesFiches.unItemMenu = prevSubMenuLabel;
			pageToutesLesFiches.selectMenu();
		}
		return pageToutesLesFiches;
	}

	public WOComponent selectBibliotheque() {
		pageEnCoursTitre = "Biblioth&egrave;que";
		return getSavedPageWithName(PageBibliotheque.class.getName());
	}

	public WOComponent selectChangerIdentite() {
		pageEnCoursTitre = "Changement d'identit&eacute;";
		return getSavedPageWithName(PageChangeIdentite.class.getName());
	}

	public WOComponent selectLogs() {
		pageEnCoursTitre = "Logs de l'application";
		return getSavedPageWithName(PageLogs.class.getName());
	}

	public WOComponent selectFichesPersonnelles() {
		PagePersonnel reponse = (PagePersonnel) getSavedPageWithName(PagePersonnel.class.getName());
		// on va afficher la liste uniquement si on affiche deja la
		// page personnel. sert de bouton "retour a la liste"
		if (prevMenuId == PageTemplate.ID_MENU_PER) {
			((PagePersonnel) reponse).redisplayGlobalComponent();
		}
		pageEnCoursTitre = "Fiches personnelles";
		return reponse;
	}

	public WOComponent selectQuitter() {
		return logout();
	}

	/**
	 * Memoriser la precedent selection du menu principal pour determiner certains
	 * comportement du menu
	 */
	private int prevMenuId;

	public void setPrevMenuId(int value) {
		prevMenuId = value;
	}

	public String pageEnCoursTitre = null;

	private FeveDataBus _dataBus;

	/**
	 * Le bus de donnees pour acces aux methodes supplementaires
	 */
	public FeveDataBus feveDataBus() {
		if (_dataBus == null)
			_dataBus = new FeveDataBus(ec());
		return _dataBus;
	}

	// -----------
	// IMPRESSIONS
	// -----------

	private NSData lastPdfData;
	private FevePdfBoxCtrl lastPdfCtrl;

	public NSData getLastPdfData() {
		return lastPdfData;
	}

	public void setCurrentPdfData(NSData value) {
		lastPdfData = value;
	}

	public FevePdfBoxCtrl getLastPdfCtrl() {
		return lastPdfCtrl;
	}

	public void setCurrentPdfCtrl(FevePdfBoxCtrl value) {
		lastPdfCtrl = value;
	}

	// ---------------------------
	// gestion du browser REFERENS
	// ---------------------------

	/**
	 * cache de tous les nodes <code>VReferens</code> qui ont ete cree
	 */
	private NSArray listVReferensNodes = new NSArray();

	public NSArray getListVReferensNodes() {
		return listVReferensNodes;
	}

	/**
	 * Pour reinstancier, on a besoin d'effacer le cache des nodes. Ces derniers
	 * sont dans la session
	 */
	public void clearBrowserReferensCache() {
		listVReferensNodes = new NSArray();
	}

	/**
	 * Memoriser la derniere selection d'emploi type dans le browser pour
	 * permettre a l'utilisateur de rester sur sa derniere selection
	 */
	private EOReferensEmplois lastReferensEmplois;

	public final EOReferensEmplois getLastReferensEmplois() {
		return lastReferensEmplois;
	}

	public final void setLastReferensEmplois(
			EOReferensEmplois lastReferensEmplois) {
		this.lastReferensEmplois = lastReferensEmplois;
	}

	// ---------------------
	// RECUP YCRISESSION
	// ---------------------

	public void terminate() {
		String userLogin = null;
		if (connectedUserInfo() != null)
			userLogin = connectedUserInfo().login();
		StringBuffer buffer = new StringBuffer("close session");
		if (userLogin != null)
			buffer.append(", user : ").append(userLogin);
		log(buffer.toString());
		super.terminate();
	}

	private String logPrefix() {
		return "<" + sessionID() + " - ";
	}

	private void log(String value) {
		CktlLog.log(logPrefix() + value + ">");
	}

	// cache enregistrements

	private EOTplFiche eoTplFicheEvaluation;

	public final EOTplFiche getEoTplFicheEvaluation() {
		if (eoTplFicheEvaluation == null) {
			eoTplFicheEvaluation = EOTplFiche.fetchRequiredTplFiche(
					ec(), EOTplFiche.TFI_CODE_KEY, EOTplFiche.TPL_FICHE_EVALUATION_CODE);
		}
		return eoTplFicheEvaluation;
	}

	private EOTplOnglet eoTplOngletSituationActivite;

	public EOTplOnglet getEoTplOngletSituationActivite() {
		if (eoTplOngletSituationActivite == null) {
			eoTplOngletSituationActivite = EOTplOnglet.fetchRequiredTplOnglet(
					ec(), EOTplOnglet.TON_CODE_KEY, EOTplOnglet.SITUATION_ACTIVITE_CODE);
		}
		return eoTplOngletSituationActivite;
	}

	private NSArray<EOFormationPersonnel> allFormationPersonnelArray;

	/**
	 * 
	 * @return
	 */
	public NSArray<EOFormationPersonnel> getAllFormationPersonnelArray() {
		if (allFormationPersonnelArray == null) {
			long start = System.currentTimeMillis();
			/*
			 * allFormationPersonnelArray =
			 * EOFormationPersonnel.fetchAllFormationPersonnels(ec()); // ne conserver
			 * que les feuilles allFormationPersonnelArray =
			 * EOQualifier.filteredArrayWithQualifier( allFormationPersonnelArray,
			 * ERXQ.equals(EOFormationPersonnel.IS_FEUILLE_KEY, Boolean.TRUE));
			 */
			allFormationPersonnelArray = EOFormationPersonnel.fetchAllFormationPersonnelsRawRows(ec());
			CktlLog.log("chargement de la liste des formations : " + (System.currentTimeMillis() - start) + "ms");
		}
		return allFormationPersonnelArray;
	}

}