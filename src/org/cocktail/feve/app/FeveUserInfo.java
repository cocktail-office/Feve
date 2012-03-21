package org.cocktail.feve.app;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.A_EOEvaluationKeyValueCoding;
import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EODroit;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.feve.eos.modele.mangue.I_Droit;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.database.CktlUserInfoDB;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.eof.ERXQ;

/*
 * Copyright Universit� de La Rochelle 2007
 *
 * TODO Ce logiciel est un programme informatique servant � g�rer 
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

/**
 * La classe descriptive d'un utilisateur connecte a l'application
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class FeveUserInfo extends CktlUserInfoDB {

	/** droits accordes */
	private Boolean isAdmin;

	/** le persId de la personne connectee */
	private Number persId;

	/** */
	private EOEditingContext editingContext;
	private Application app;
	private EOIndividu recIndividu;

	/**
	 * la liste des droits de l'utilisateur connecte
	 */
	private NSArray<EODroit> droitList;
	private NSArray<EODroit> droitDeductedList;
	private NSArray<EODroit> allDroitList;

	/**
	 * Binding pour l'interface listant les evaluations : elle contient tous les
	 * evaluations creees et non creees dont la personne connecte a les droits de
	 * lecture ou d'ecriture
	 */
	private NSArray evaluationList;

	/**
	 * Tous les services surlequel la personne connectee a un droit (directement,
	 * ou sur un poste ou une fiche)
	 */
	private NSArray<EOStructure> servicePosteList;

	/**
	 * Determiner si l'utilisateur n'a que des droits sur lui meme, a savoir il
	 * voit que ses propres trucs : evaluation et fiches
	 */
	private Boolean isLimitedUser = null;

	/**
	 * 
	 * @param aCriApp
	 * @param bus
	 * @param anEc
	 * @param aPersId
	 */
	public FeveUserInfo(Application aCriApp, CktlDataBus bus, EOEditingContext anEc, Number aPersId) {
		super(bus);
		app = aCriApp;
		persId = aPersId;
		editingContext = anEc;
		initUserInfo();
	}

	/**
	 * 
	 */
	private void initUserInfo() {
		individuForPersId(persId, true);
		recIndividu = EOIndividu.findIndividuForPersIdInContext(editingContext, persId);

		isAdmin = new Boolean(recIndividu.belongStructureRepartStructure(app.cStructureAdmin()));

		//
		droitList = EODroit.fetchDroitsForIndividu(recIndividu());
		NSDictionary dicoDeducted = EODroit.getDeductedDroitForIndividu(app, recIndividu());
		droitDeductedList = (NSArray) dicoDeducted.valueForKey("droitList");
		boolean hasLimitedHeritage = ((Boolean) dicoDeducted.valueForKey("hasLimitedHeritage")).booleanValue();
		if (!isAdmin() && (droitList.count() == 0 && hasLimitedHeritage)) {
			isLimitedUser = Boolean.TRUE;
		} else {
			isLimitedUser = Boolean.FALSE;
		}
		allDroitList = droitList.arrayByAddingObjectsFromArray(droitDeductedList);

		//
		reloadEvaluationList();
		loadServicePosteList();
	}

	/**
	 * Permet de retrouver la liste de toutes les evaluations associees a la
	 * personne connectee (en modif et en visu). Cette methode est sortie de
	 * {@link #initUserInfo()} car elle peut etre appelee via la page
	 * d'administration des periodes.
	 */
	public void reloadEvaluationList() {
		if (isAdmin()) {
			// l'administrateur voit toutes les evaluations
			evaluationList = EOVCandidatEvaluation.fetchAllVCandidatEvaluations(editingContext);
		} else {

			evaluationList = new NSArray();

			NSArray droitGlobalEvaluationList = EOQualifier.filteredArrayWithQualifier(
					droitList,
					CktlDataBus.newCondition(
							EODroit.TO_TYPE_DROIT_CIBLE_KEY + "=%@ and " + EODroit.IS_DROIT_GLOBAL_KEY + "=%@",
							new NSArray(new Object[] { EOTypeDroitCible.eoTypeDroitCibleEvaluation(editingContext), Boolean.TRUE })));

			// s'il a le droit global en evaluation, alors c'est toutes les
			// evaluations
			if (droitGlobalEvaluationList.count() > 0) {
				// on regarde si c'est pas periode ou pas
				NSArray allEvaluationList = EOVCandidatEvaluation.fetchAllVCandidatEvaluations(editingContext);
				for (int i = 0; i < droitGlobalEvaluationList.count(); i++) {
					EODroit droit = (EODroit) droitGlobalEvaluationList.objectAtIndex(i);
					if (droit.toDroitEvaluationPeriode() == null) {
						evaluationList = evaluationList.arrayByAddingObjectsFromArray(allEvaluationList);
					} else {
						evaluationList = evaluationList.arrayByAddingObjectsFromArray(
								EOQualifier.filteredArrayWithQualifier(
										allEvaluationList,
										CktlDataBus.newCondition(EOVCandidatEvaluation.TO_EVALUATION_PERIODE_KEY + "=%@",
												new NSArray(droit.toDroitEvaluationPeriode()))));
					}
				}
			} else {
				//
				// sinon, c'est selon les droits de type evaluation
				//
				NSArray allDroitEvaluationList = EOQualifier.filteredArrayWithQualifier(
						allDroitList,
						CktlDataBus.newCondition(
								EODroit.TO_TYPE_DROIT_CIBLE_KEY + "=%@",
								new NSArray(EOTypeDroitCible.eoTypeDroitCibleEvaluation(editingContext))));

				// droits sur les evaluations (on rajoute or
				// " + EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY + "<> nil" car
				// lors de la creation des EODroit bidons issus de la hierarchie, seule
				// la to one toDroitVCandidatEvaluation
				// est initialisee - si on remploi aussi individu et periode, alors
				// erreur WebObjects ...)
				evaluationList = (NSArray) EOQualifier.filteredArrayWithQualifier(
						allDroitEvaluationList,
						CktlDataBus.newCondition(
								"(" + EODroit.TO_DROIT_INDIVIDU_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "<> nil) or " + EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY + "<> nil")).
						valueForKey(EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY);

				// droits sur les individus toute periode
				evaluationList = evaluationList.arrayByAddingObjectsFromArray(
						(NSArray) EOQualifier.filteredArrayWithQualifier(
								allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_INDIVIDU_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "= nil")).
								valueForKeyPath(EODroit.TO_DROIT_INDIVIDU_KEY + "." + EOIndividu.TOS_V_CANDIDAT_EVALUATION_KEY));

				// droits sur les services toute periode
				evaluationList = evaluationList.arrayByAddingObjectsFromArray(
						(NSArray) EOQualifier.filteredArrayWithQualifier(
								allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_STRUCTURE_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "= nil")).
								valueForKeyPath(EODroit.TO_DROIT_STRUCTURE_KEY + "." + EOStructure.TOS_INDIVIDU_AFFECTE_KEY + "." + EOIndividu.TOS_V_CANDIDAT_EVALUATION_KEY));

				// droits sur les composantes toute periode
				evaluationList = evaluationList.arrayByAddingObjectsFromArray(
						(NSArray) EOQualifier.filteredArrayWithQualifier(
								allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_COMPOSANTE_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "= nil")).
								valueForKeyPath(EODroit.TOS_SERVICE_FLATTEN_KEY + "." + EOStructure.TOS_INDIVIDU_AFFECTE_KEY + "." + EOIndividu.TOS_V_CANDIDAT_EVALUATION_KEY));

				//
				// -- rechercher les evaluations ou les droits concernent une periode
				// fixe
				//

				// droits sur les individus periode fixee
				NSArray droitEvaluationIndividuPeriodeFixeList =
						EOQualifier.filteredArrayWithQualifier(allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_INDIVIDU_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "<> nil"));
				for (int i = 0; i < droitEvaluationIndividuPeriodeFixeList.count(); i++) {
					I_Droit droit = (I_Droit) droitEvaluationIndividuPeriodeFixeList.objectAtIndex(i);
					evaluationList = evaluationList.arrayByAddingObject(droit.toDroitVCandidatEvaluation());
				}

				// droits sur les services periode fixee
				NSArray droitEvaluationServicePeriodeFixeList =
						EOQualifier.filteredArrayWithQualifier(allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_STRUCTURE_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "<> nil"));
				for (int i = 0; i < droitEvaluationServicePeriodeFixeList.count(); i++) {
					I_Droit droit = (I_Droit) droitEvaluationServicePeriodeFixeList.objectAtIndex(i);
					// liste des individus concernes
					NSArray individus = droit.toDroitStructure().tosIndividuAffecte();
					for (int j = 0; j < individus.count(); j++) {
						EOIndividu individu = (EOIndividu) individus.objectAtIndex(j);
						EOVCandidatEvaluation evaluation = individu.toVCandidatEvaluationForPeriode(droit.toDroitEvaluationPeriode());
						if (evaluation != null) {
							evaluationList = evaluationList.arrayByAddingObject(evaluation);
						}
					}
				}

				// droits sur les composantes periode fixee
				NSArray droitEvaluationComposantePeriodeFixeList =
						EOQualifier.filteredArrayWithQualifier(allDroitEvaluationList,
								CktlDataBus.newCondition(EODroit.TO_DROIT_COMPOSANTE_KEY + "<> nil and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "<> nil"));
				for (int i = 0; i < droitEvaluationComposantePeriodeFixeList.count(); i++) {
					I_Droit droit = (I_Droit) droitEvaluationComposantePeriodeFixeList.objectAtIndex(i);
					// liste des individus concernes
					NSArray services = droit.tosServiceFlatten();
					NSArray individus = new NSArray();
					for (int j = 0; j < services.count(); j++) {
						EOStructure service = (EOStructure) services.objectAtIndex(j);
						individus = individus.arrayByAddingObjectsFromArray(service.tosIndividuAffecte());
					}
					for (int j = 0; j < individus.count(); j++) {
						EOIndividu individu = (EOIndividu) individus.objectAtIndex(j);
						EOVCandidatEvaluation evaluation = individu.toVCandidatEvaluationForPeriode(droit.toDroitEvaluationPeriode());
						if (evaluation != null) {
							evaluationList = evaluationList.arrayByAddingObject(evaluation);
						}
					}
				}

				// les evaluations d'objectifs précédents des nouveaux entrants
				NSArray<A_EOEvaluationKeyValueCoding> pouet = A_EOEvaluationKeyValueCoding.getEoEvaluationKeyValueCodingNouvelEntrantForResponsable(recIndividu());
				for (int i = 0; i < pouet.count(); i++) {
					evaluationList = evaluationList.arrayByAddingObject(pouet.objectAtIndex(i));
				}

				//
				// --
				//

				// applatir
				evaluationList = NSArrayCtrl.flattenArray(evaluationList);
				// suppression des doublons
				evaluationList = NSArrayCtrl.removeDuplicate(evaluationList);
				// enlever les valeurs Null
				NSMutableArray evaluationListMutable = new NSMutableArray(evaluationList);
				evaluationListMutable.removeIdenticalObject(NSKeyValueCoding.NullValue);
				// enlever les evaluations des agents n'ayant pas encore de contrats
				// la to-one toEvaluationPeriode() est vide pour ces cas précis
				evaluationList = evaluationListMutable.immutableClone();

				// enlever ceux qui ne sont plus dans la vue et petent une erreur
				// d'intégrité
				evaluationListMutable = new NSMutableArray();
				for (int i = 0; i < evaluationList.count(); i++) {
					A_EOEvaluationKeyValueCoding evaluation = (A_EOEvaluationKeyValueCoding) evaluationList.objectAtIndex(i);
					try {
						if (evaluation.toEvaluationPeriode() != null) {
							evaluationListMutable.addObject(evaluation);
						}
					} catch (Exception e) {

					}
				}
				evaluationList = evaluationListMutable.immutableClone();
				//
				/*
				 * for (int i=0;i<evaluationList.count(); i++) {
				 * A_EOEvaluationKeyValueCoding evaluation =
				 * (A_EOEvaluationKeyValueCoding) evaluationList.objectAtIndex(i);
				 * org.cocktail.fwkcktlwebapp.common.CktlLog.log(i+" "+evaluation);
				 * org.cocktail
				 * .fwkcktlwebapp.common.CktlLog.log(i+" "+evaluation.toEvaluationPeriode
				 * ()); }
				 */

			}
		}
		// retrouver les periodes d'evaluation => celles deduits des evaluations
		// visibles
		periodeList = (NSArray) evaluationList.valueForKey(EOVCandidatEvaluation.TO_EVALUATION_PERIODE_KEY);
		periodeList = NSArrayCtrl.removeDuplicate(periodeList);
		periodeList = CktlSort.sortedArray(periodeList, EOEvaluationPeriode.EPE_D_DEBUT_KEY);
	}

	/**
	 * Obtenir la liste des services a afficher dans l'interface gestion des
	 * postes
	 */
	private void loadServicePosteList() {
		if (isAdmin()) {
			// l'administrateur voit tous les services
			servicePosteList = EOStructure.findAllServicesInContext(editingContext);
			;
		} else {
			// sinon, c'est selon les droits
			NSMutableArray servicePosteListMutable = new NSMutableArray(
					NSArrayCtrl.flattenArray((NSArray) allDroitList.valueForKey(EODroit.TOS_SERVICE_FLATTEN_KEY)));
			// enlever les services vides
			servicePosteListMutable.removeIdenticalObject(NSKeyValueCoding.NullValue);
			// virer les doublons
			servicePosteListMutable = new NSMutableArray(NSArrayCtrl.removeDuplicate(servicePosteListMutable.immutableClone()));

			/*
			 * //TODO // enlever les services pour lesquels il ne possede que des
			 * droits sur ses // propres fiches de poste et fiches LOLF (car il les
			 * voit dans sa partie personnelle) NSArray serviceRemoveList = new
			 * NSArray(); // moulinette qui se fait sur des to-many ... filtrage en
			 * memoire obligatoire ! for (int i=0; i<servicePosteListMutable.count();
			 * i++) { EOStructure service = (EOStructure)
			 * servicePosteListMutable.objectAtIndex(i); boolean shouldRemoveService =
			 * true; boolean shouldContinue = true; // la liste des droits li�s a ce
			 * service int prout = 0; for (int j=0; j<allDroitList.count() &&
			 * shouldContinue; j++) { EODroit droit = (EODroit)
			 * allDroitList.objectAtIndex(j); // les services associes a ce droit (au
			 * cas ou c'est une composante) NSArray droitServiceList =
			 * droit.tosServiceFlatten(); if (droitServiceList.count()> 0)
			 * CktlLog.log(">> "+droitServiceList.valueForKey("cStructure") + " dans "
			 * + servicePosteListMutable.valueForKey("cStructure")); // si concern� if
			 * (droitServiceList.count() == 1 && (((EOStructure)
			 * droitServiceList.objectAtIndex
			 * (0)).cStructure().equals(service.cStructure()))) {
			 * CktlLog.log(">>>>>>>>>>>>> "+((EOStructure)
			 * droitServiceList.objectAtIndex(0)).cStructure()); // on vire tout ce
			 * qui ne concerne par les fiches lolf / poste // ou bien si les fiches
			 * lolf / poste ne sont pas les siennes if ((!droit.isDroitFicheLolf() &&
			 * !droit.isDroitFicheDePoste()) || (droit.isDroitFicheLolf() &&
			 * droit.toDroitFicheLolf() != null &&
			 * !recIndividu().tosFicheLolf().containsObject(droit.toDroitFicheLolf()))
			 * || (droit.isDroitFicheDePoste() && droit.toDroitFicheDePoste() != null
			 * &&
			 * !recIndividu().tosFicheDePoste().containsObject(droit.toDroitFicheDePoste
			 * ()))) { shouldRemoveService = false; shouldContinue = false; } }
			 * prout++; } // on marque comme a degager if (shouldRemoveService) {
			 * serviceRemoveList = serviceRemoveList.arrayByAddingObject(service); } }
			 * // virer les services qu'il faut
			 * servicePosteListMutable.removeObjectsInArray(serviceRemoveList);
			 */
			// classement alphabetique
			servicePosteList = CktlSort.sortedArray(servicePosteListMutable.immutableClone(), EOStructure.LL_STRUCTURE_KEY);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return isAdmin.booleanValue();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNotAdmin() {
		return !isAdmin();
	}

	/**
	 * 
	 * @return
	 */
	public EOIndividu recIndividu() {
		return recIndividu;
	}

	/**
	 * 
	 * @return
	 */
	public NSArray getEvaluationList() {
		return evaluationList;
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOStructure> getServicePosteList() {
		return servicePosteList;
	}

	/**
	 * 
	 * @param isAfficherArchive
	 * @return
	 */
	public NSArray<EOStructure> getServicePosteList(boolean isAfficherArchive) {
		NSArray<EOStructure> serviceList = getServicePosteList();
		if (!isAfficherArchive) {
			serviceList = EOQualifier.filteredArrayWithQualifier(
					serviceList,
					ERXQ.equals(
							EOStructure.IS_ARCHIVE_KEY, Boolean.FALSE));
		}
		return serviceList;
	}

	/**
	 * Indique si l'utilisateur connecte a le droit d'effectuer l'action sur la
	 * cible.
	 * 
	 * @param periode
	 * @param codeTypeDroitAcces
	 * @param codeTypeDroitCible
	 * @param cible
	 * @param isComposante
	 *          : cas particulier si la cible est une structure, il faut specifier
	 *          s'il s'agit d'une composante ou d'un service
	 * 
	 *          L'administateur a par defaut le droit de tout faire.
	 * 
	 * @return
	 */
	public boolean hasDroitOnCible(
			EOEvaluationPeriode periode, String codeTypeDroitAcces, String codeTypeDroitCible, Object cible, boolean isComposante) {
		boolean result = false;

		/*
		 * org.cocktail.fwkcktlwebapp.common.CktlLog.log("hasDroitOnCible()" +
		 * " periode="+(periode != null ? periode.strAnneeDebutAnneeFin() : "null")
		 * + " codeTypeDroitAcces="+codeTypeDroitAcces+
		 * " codeTypeDroitCible="+codeTypeDroitCible+ " cible="+cible+
		 * " isComposante="+isComposante);
		 */
		if (isAdmin()) {
			// l'administrateur peut faire toutes les actions
			result = true;

		} else {

			// le qualifier concernant les droits allou�s a la personne connectee
			String strQual =
					EODroit.TO_INDIVIDU_KEY + "=%@ and " +
							EODroit.TO_TYPE_DROIT_ACCES_KEY + "." + EOTypeDroitAcces.DTA_CODE_KEY + "=%@ and " +
							EODroit.TO_TYPE_DROIT_CIBLE_KEY + "." + EOTypeDroitCible.DTC_CODE_KEY + "=%@";
			NSArray args = new NSArray(new Object[] {
					recIndividu(), codeTypeDroitAcces, codeTypeDroitCible });

			// le qualifier et l'argument concernant la periode
			if (periode != null) {
				strQual += " and " + EODroit.TO_DROIT_EVALUATION_PERIODE_KEY + "=%@";
				args = args.arrayByAddingObject(periode);
			}

			if (cible != null) {
				args = args.arrayByAddingObject(cible);
			}

			// connaitre la nature de la cible
			boolean isCibleGlobal = (cible == null);
			boolean isCibleFicheDePoste = (!isCibleGlobal && cible instanceof EOFicheDePoste);
			boolean isCibleFicheLolf = (!isCibleGlobal && !isCibleFicheDePoste && cible instanceof EOFicheLolf);
			boolean isCiblePoste = (!isCibleGlobal && !isCibleFicheDePoste && !isCibleFicheLolf && cible instanceof EOPoste);
			boolean isCibleEvaluation = (!isCibleGlobal && !isCibleFicheDePoste && !isCibleFicheLolf && !isCiblePoste && cible instanceof EOVCandidatEvaluation);
			boolean isCibleIndividu = (!isCibleGlobal && !isCibleFicheDePoste && !isCibleFicheLolf && !isCiblePoste && !isCibleEvaluation && cible instanceof EOIndividu);
			boolean isCibleService = (!isCibleGlobal && !isCibleFicheDePoste && !isCibleFicheLolf && !isCiblePoste && !isCibleEvaluation && !isCibleIndividu && cible instanceof EOStructure && !isComposante);
			boolean isCibleComposante = (!isCibleGlobal && !isCibleFicheDePoste && !isCibleFicheLolf && !isCiblePoste && !isCibleEvaluation && !isCibleIndividu && !isCibleService && cible instanceof EOStructure && isComposante);

			// dans le cas d'une demande de modification de fiche de poste,
			// on verifie si la periode de saisie est ouverte
			if (isCibleFicheLolf && codeTypeDroitAcces.equals(EOTypeDroitAcces.MODIFICATION)) {

				// on verifie que l'on est en periode de saisie
				if (!app.isPeriodeLolfOuverte()) {
					return false;
				} else {
					// seules les fiches actuelles sont modifiables pendant la periode
					// ouverte
					NSTimestamp now = DateCtrl.now();
					EOFicheLolf ficheLolf = (EOFicheLolf) cible;
					if (DateCtrl.isBefore(ficheLolf.floDFin(), now) ||
							DateCtrl.isAfter(ficheLolf.floDDebut(), now)) {
						// org.cocktail.fwkcktlwebapp.common.CktlLog.log(">> hors periode LOLF "
						// + result);
						return false;
					}
				}
			}

			// dans le cas d'une demande de modification d'evaluation,
			// on verifie si la periode de saisie est ouverte
			if (isCibleEvaluation && (
					codeTypeDroitAcces.equals(EOTypeDroitAcces.MODIFICATION) || codeTypeDroitAcces.equals(EOTypeDroitAcces.CREATION))) {

				// on verifie que l'on est en periode de saisie
				if (!app.isPeriodeEvaluationOuverte()) {
					return false;
				} else {
					// seules les fiches actuelles sont modifiables pendant la periode
					// ouverte
					NSTimestamp now = DateCtrl.now();
					EOVCandidatEvaluation evaluation = (EOVCandidatEvaluation) cible;
					if (evaluation.toEvaluationPeriode() != null && (
								DateCtrl.isBefore(evaluation.toEvaluationPeriode().epeDFin(), now) ||
								DateCtrl.isAfter(evaluation.toEvaluationPeriode().epeDDebut(), now))) {
						// org.cocktail.fwkcktlwebapp.common.CktlLog.log(">> hors periode evaluation "
						// + result);
						return false;
					}
				}

			}

			// construire le qualifier vers un droit direct
			if (isCibleGlobal) {
				// cas particulier du droit global, on a un qualifier different
				// si la periode est fixee ou non
				if (periode == null) {
					strQual += " and " + EODroit.STR_QUAL_TOUTE_CIBLE_TOUTE_PERIODE;
				} else {
					strQual += " and " + EODroit.STR_QUAL_TOUTE_CIBLE_IGNORE_PERIODE;
				}
			}
			if (isCibleComposante) {
				strQual += " and " + EODroit.TO_DROIT_COMPOSANTE_KEY + "=%@";
			} else if (isCibleService) {
				strQual += " and " + EODroit.TO_DROIT_STRUCTURE_KEY + "=%@";
			} else if (isCibleIndividu) {
				strQual += " and " + EODroit.TO_DROIT_INDIVIDU_KEY + "=%@";
			} else if (isCibleEvaluation) {
				strQual += " and " + EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY + "=%@";
			} else if (isCiblePoste) {
				strQual += " and " + EODroit.TO_DROIT_POSTE_KEY + "=%@";
			} else if (isCibleFicheLolf) {
				strQual += " and " + EODroit.TO_DROIT_FICHE_LOLF_KEY + "=%@";
			} else if (isCibleFicheDePoste) {
				strQual += " and " + EODroit.TO_DROIT_FICHE_DE_POSTE_KEY + "=%@";
			}

			// --
			// filtrer sur la liste preconstruire des droits de l'utilisateur
			// <code>droitList</code>
			// --
			result = EOQualifier.filteredArrayWithQualifier(allDroitList,
					CktlDataBus.newCondition(strQual, args)).count() > 0;

			// si pas trouv�, on va remonter vers le niveau superieur
			if (!result) {
				if (isCibleComposante) {
					result = hasDroitOnCible(periode, codeTypeDroitAcces, codeTypeDroitCible, null, false);
				} else if (isCibleService) {
					result = hasDroitOnCible(periode, codeTypeDroitAcces, codeTypeDroitCible, ((EOStructure) cible).toComposante(), true);
				} else if (isCibleIndividu) {
					// remonter a tous les services potentiels de l'individu
					NSArray services = ((EOIndividu) cible).tosPosteStructure();
					for (int i = 0; !result && i < services.count(); i++) {
						EOStructure service = (EOStructure) services.objectAtIndex(i);
						result = hasDroitOnCible(periode, codeTypeDroitAcces, codeTypeDroitCible, service, false);
					}
				} else if (isCibleEvaluation) {
					// normalement, le point d'entree pour l'evaluation passe toujours par
					// cette methode en premier
					// on fait donc un "ou" vers l'individu avec la periode et sans la
					// periode
					EOVCandidatEvaluation vCandidatEvaluation = (EOVCandidatEvaluation) cible;
					result =
							hasDroitOnCible(vCandidatEvaluation.toEvaluationPeriode(), codeTypeDroitAcces, codeTypeDroitCible, vCandidatEvaluation.toIndividu(), false) ||
									hasDroitOnCible(null, codeTypeDroitAcces, codeTypeDroitCible, vCandidatEvaluation.toIndividu(), false);
				} else if (isCiblePoste) {
					result = hasDroitOnCible(codeTypeDroitAcces, codeTypeDroitCible, ((EOPoste) cible).toStructure(), false);
				} else if (isCibleFicheLolf || isCibleFicheDePoste) {
					result = hasDroitOnCible(codeTypeDroitAcces, codeTypeDroitCible, ((A_Fiche) cible).toPoste(), false);
				}
			}

		}

		/* org.cocktail.fwkcktlwebapp.common.CktlLog.log(">> " + result); */

		return result;
	}

	/**
	 * 
	 * @param codeTypeDroitAcces
	 * @param codeTypeDroitCible
	 * @param cible
	 * @param isComposante
	 * @return
	 */
	public boolean hasDroitOnCible(
			String codeTypeDroitAcces, String codeTypeDroitCible, Object cible, boolean isComposante) {
		return hasDroitOnCible(null, codeTypeDroitAcces, codeTypeDroitCible, cible, isComposante);
	}

	/**
	 * Indique si l'utilisateur possede au moins un des 4 droits d'acces sur la
	 * cible indiquee
	 * 
	 * @param codeTypeDroitCible
	 * @param cible
	 * @param isComposante
	 * @return
	 */
	public boolean hasAnyDroitAccesOnCible(String codeTypeDroitCible, Object cible, boolean isComposante) {
		boolean result = false;
		for (int i = 0; !result && i < EOTypeDroitAcces.CODE_TYPE_DROIT_ACCES_LIST.length; i++) {
			result = hasDroitOnCible(
					EOTypeDroitAcces.CODE_TYPE_DROIT_ACCES_LIST[i], codeTypeDroitCible, cible, isComposante);
		}
		return result;
	}

	/**
	 * Determiner si l'utilisateur n'a que des droits sur lui meme, a savoir il
	 * voit que ses propres trucs : evaluation et fiches
	 */
	public boolean getIsLimitedUser() {
		return isLimitedUser.booleanValue();
	}

	// la liste des periodes d'evaluation
	private NSArray periodeList;

	/**
	 * la liste des periodes d'evaluation
	 */
	public NSArray getPeriodeList() {
		return periodeList;
	}

	/**
	 * La liste des droits deduits.
	 * 
	 * @return
	 */
	public NSArray getDroitDeductedList() {
		return droitDeductedList;
	}

	// parametres d'acces aux zones activites / competences autres
	private Boolean _isFicheDePosteSaisieActiviteAutre;
	private Boolean _isFicheDePosteSaisieCompetenceAutre;

	/**
	 * Autorise-t-on la saisie d'activités autres dans la fiche de poste
	 * 
	 * @return
	 */
	public boolean isFicheDePosteSaisieActiviteAutre() {
		if (_isFicheDePosteSaisieActiviteAutre == null) {
			_isFicheDePosteSaisieActiviteAutre =
					app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES);
		}
		return _isFicheDePosteSaisieActiviteAutre.booleanValue();
	}

	/**
	 * Autorise-t-on la saisie de compétences autres dans la fiche de poste ainsi
	 * que leur évaluation dans l'entretien professionnel
	 * 
	 * @return
	 */
	public boolean isFicheDePosteSaisieCompetenceAutre() {
		if (_isFicheDePosteSaisieCompetenceAutre == null) {
			_isFicheDePosteSaisieCompetenceAutre =
					app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES);
		}
		return _isFicheDePosteSaisieCompetenceAutre.booleanValue();
	}

	/**
	 * Forcer la relecture des parametres dans la base de données
	 */
	public void clearParamCache() {
		_isFicheDePosteSaisieActiviteAutre = null;
		_isFicheDePosteSaisieCompetenceAutre = null;
	}

	@Override
	public Integer persId() {
		return new Integer(super.persId().intValue());
	}
}
