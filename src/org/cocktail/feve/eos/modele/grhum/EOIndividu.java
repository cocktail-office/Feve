// EOIndividu.java
// Created on Fri Oct 29 08:40:43  2004 by Apple EOModeler Version 5.2

package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOContratAvenant;
import org.cocktail.feve.eos.modele.mangue.EOElementCarriere;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOHierarchie;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOStage;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.database.CktlUserInfoDB;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EONotQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EOIndividu extends _EOIndividu {

	public EOIndividu() {
		super();
	}

	public void validateForInsert() throws NSValidation.ValidationException {
		this.validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForInsert();
	}

	public void validateForUpdate() throws NSValidation.ValidationException {
		this.validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForUpdate();
	}

	public void validateForDelete() throws NSValidation.ValidationException {
		super.validateForDelete();
	}

	/**
	 * Apparemment cette methode n'est pas appelee.
	 * 
	 * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
	 */
	public void validateForSave() throws NSValidation.ValidationException {
		validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForSave();

	}

	/**
	 * Peut etre appele a partir des factories.
	 * 
	 * @throws NSValidation.ValidationException
	 */
	public void validateObjectMetier() throws NSValidation.ValidationException {

	}

	/**
	 * A appeler par les validateforsave, forinsert, forupdate.
	 * 
	 */
	private final void validateBeforeTransactionSave()
			throws NSValidation.ValidationException {

	}

	// METHODES RAJOUTEES

	public final static NSArray<EOSortOrdering> INDIVIDU_SORT = new NSArray<EOSortOrdering>(
			new EOSortOrdering[] {
					EOSortOrdering.sortOrderingWithKey(NOM_USUEL_KEY, EOSortOrdering.CompareAscending),
					EOSortOrdering.sortOrderingWithKey(PRENOM_KEY, EOSortOrdering.CompareAscending)
			}
			);

	public final static String NOM_PRENOM_KEY = "nomPrenom";

	public String display() {
		return nomPrenom();
	}

	private static CktlDataBus _criDataBusForDisplayLogin = new CktlDataBus(new EOEditingContext());

	/**
	 * Affichage du noIndividu et du login
	 * 
	 * @return
	 */
	public String noIndividuLogin() {
		String display = Integer.toString(noIndividu().intValue());
		CktlUserInfoDB ui = new CktlUserInfoDB(_criDataBusForDisplayLogin);
		ui.compteForPersId(persId(), false);
		if (ui != null) {
			display += " / " + ui.login();
		}
		return display;
	}

	public String nomPrenomQualite() {
		return nomUsuel() + " " + prenom() +
				(!StringCtrl.isEmpty(indQualite()) ? " (" + indQualite() + ")" : "");
	}

	public String nomPrenom() {
		return nomUsuel() + " " + prenom();
	}

	/*
	 * public String identifiant() { return (indNoInsee() == null ? "" :
	 * indNoInsee()) + "-" + (indCleInsee() == null ? "" :
	 * indCleInsee().toString()); }
	 */

	/**
	 * retourne vrai si l'individu appartient a la structure via RepartStructure
	 */
	public boolean belongStructureRepartStructure(String cStructure) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("persId = %@ AND cStructure = %@",
				new NSArray(new Object[] { persId(), cStructure }));
		NSArray records = UtilDb.fetchArray(editingContext(), "GrhumRepartStructure", qual, null);
		return (records.count() > 0);
	}

	/**
	 * liste des fiches de poste actuellement occupees par cet individu
	 * 
	 * @return
	 */
	public NSArray tosFicheDePosteActuelle() {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"fdpDDebut <= %@ AND (fdpDFin >= %@ OR fdpDFin = nil)",
				new NSArray(new NSTimestamp[] { DateCtrl.now(), DateCtrl.now() }));
		return EOQualifier.filteredArrayWithQualifier(tosFicheDePoste(), qual);
	}

	/**
	 * liste des fiches LOLF actuellement occupees par cet individu
	 * 
	 * @return
	 */
	public NSArray tosFicheLolfActuelle() {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"fdpDDebut <= %@ AND (fdpDFin >= %@ OR fdpDFin = nil)",
				new NSArray(new NSTimestamp[] { DateCtrl.now(), DateCtrl.now() }));
		return EOQualifier.filteredArrayWithQualifier(tosFicheLolf(), qual);
	}

	/**
	 * liste des poste actuellement occupes par cet individu
	 * 
	 * @return
	 */
	public NSArray tosPosteActuel() {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"posDDebut <= %@ AND (posDFin >= %@ OR posDFin = nil)",
				new NSArray(new NSTimestamp[] { DateCtrl.now(), DateCtrl.now() }));
		return EOQualifier.filteredArrayWithQualifier(tosPoste(), qual);
	}

	/**
	 * tous les fiches occupees pendant une periode
	 * 
	 * @param debut
	 * @param fin
	 * @return
	 */
	public NSArray tosFicheDePostePourDate(NSTimestamp debut, NSTimestamp fin) {
		String strQual =
				"(" +
						"(" + EOFicheDePoste.FDP_D_DEBUT_KEY + " <= %@ AND (" + EOFicheDePoste.FDP_D_FIN_KEY + " >= %@ OR " + EOFicheDePoste.FDP_D_FIN_KEY + " = nil)) OR " +
						"(" + EOFicheDePoste.FDP_D_DEBUT_KEY + " >= %@ AND " + EOFicheDePoste.FDP_D_DEBUT_KEY + " <= %@ AND " + EOFicheDePoste.FDP_D_FIN_KEY + " => %@ AND " + EOFicheDePoste.FDP_D_FIN_KEY + " <= %@) OR " +
						"(" + EOFicheDePoste.FDP_D_DEBUT_KEY + " <= %@ and " + EOFicheDePoste.FDP_D_FIN_KEY + " <= %@ AND " + EOFicheDePoste.FDP_D_FIN_KEY + " >= %@) OR " +
						"(" + EOFicheDePoste.FDP_D_DEBUT_KEY + " >= %@ AND " + EOFicheDePoste.FDP_D_DEBUT_KEY + " <= %@ AND (" + EOFicheDePoste.FDP_D_FIN_KEY + " >= %@ OR " + EOFicheDePoste.FDP_D_FIN_KEY + " = nil)))";

		NSArray args = new NSArray(
				new NSTimestamp[] {
						debut, fin,
						debut, fin, debut, fin,
						debut, fin, debut,
						debut, fin, fin,
						debut, fin,
						debut, fin, debut, fin,
						debut, fin, debut,
						debut, fin, fin });
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);

		// on a les fiches en dates de validite
		NSArray recsFiche = EOQualifier.filteredArrayWithQualifier(tosFicheDePoste(), qual);

		// on ne conserve maintenant que celle dont les AffectationDetail
		// correspondent aussi a ces dates
		strQual =
				"((" + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " <= %@ AND (" + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " >= %@ OR " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " = nil)) OR " +
						"(" + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " >= %@ AND " + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " <= %@ AND " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " => %@ AND " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " <= %@) OR " +
						"(" + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " <= %@ and " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " <= %@ AND " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " >= %@) OR " +
						"(" + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " >= %@ AND " + EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL + " <= %@ AND (" + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " >= %@ OR " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + " = nil)))";
		qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);

		// on a les occupations en dates de validite
		NSArray recsAffDet = EOQualifier.filteredArrayWithQualifier(tosAffectationDetail(), qual);

		NSArray recsFicheForDate = new NSArray();

		for (int i = 0; i < recsFiche.count(); i++) {
			EOFicheDePoste ficheDePoste = (EOFicheDePoste) recsFiche.objectAtIndex(i);
			for (int j = 0; j < recsAffDet.count(); j++) {
				EOAffectationDetail affDet = (EOAffectationDetail) recsAffDet.objectAtIndex(j);
				NSArray recsAffDetFicheDePoste = NSArrayCtrl.flattenArray((NSArray) affDet.valueForKeyPath(EOAffectationDetail.TO_POSTE_KEY + "." + EOPoste.TOS_FICHE_DE_POSTE_KEY));
				if (recsAffDetFicheDePoste.containsObject(ficheDePoste)) {
					recsFicheForDate = recsFicheForDate.arrayByAddingObject(ficheDePoste);
					break;
				}
			}
		}

		return recsFicheForDate;
	}

	/**
	 * l'evaluation en cours de l'individu
	 * 
	 * @return
	 */
	public EOEvaluation toEvaluationActuelle() {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"evaDDebut <= %@ AND (evaDFin >= %@ OR evaDFin = nil)",
				new NSArray(new NSTimestamp[] { DateCtrl.now(), DateCtrl.now() }));
		NSArray records = EOQualifier.filteredArrayWithQualifier(tosEvaluation(), qual);
		EOEvaluation record = null;
		if (records.count() > 0) {
			record = (EOEvaluation) records.lastObject();
		}
		return record;
	}

	/**
	 * La liste des fiches de postes "occupees" par l'agent. On ne conserve que
	 * les fiches dont les dates coincident avec l'affectation du poste et de
	 * l'agent.
	 */
	private NSArray tosFiche(String entity) {
		NSArray result = new NSArray();
		boolean isFicheDePoste = entity.equals(EOFicheDePoste.ENTITY_NAME);
		// construction du nom des attributes a fetcher
		String attrDeb = isFicheDePoste ? EOFicheDePoste.FDP_D_DEBUT_KEY : EOFicheLolf.FLO_D_DEBUT_KEY;
		String attrFin = isFicheDePoste ? EOFicheDePoste.FDP_D_FIN_KEY : EOFicheLolf.FLO_D_FIN_KEY;
		//
		NSArray occupations = tosAffectationDetail();
		for (int i = 0; i < occupations.count(); i++) {
			EOAffectationDetail occ = (EOAffectationDetail) occupations.objectAtIndex(i);
			// lors de la suppression d'un poste les toPoste de AffectationDetail
			// ne pas mis a jour ...
			if (occ.toPoste() != null) {
				NSArray fiches = isFicheDePoste ? occ.toPoste().tosFicheDePoste() : occ.toPoste().tosFicheLolf();
				EOQualifier qual = null;
				if (occ.dFin() == null) {
					qual = EOQualifier.qualifierWithQualifierFormat(
							attrFin + " >= %@ OR dFin = nil", new NSArray(occ.dDebut()));
				} else {
					qual = new EONotQualifier(EOQualifier.qualifierWithQualifierFormat(
							attrDeb + " > %@ OR " + attrFin + " < %@",
							new NSArray(new NSTimestamp[] { occ.dFin(), occ.dDebut() })));
				}
				result = result.arrayByAddingObjectsFromArray(
						EOQualifier.filteredArrayWithQualifier(fiches, qual));
			}
		}
		result = NSArrayCtrl.removeDuplicate(result);
		// classement par date de debut d'occupation
		result = CktlSort.sortedArray(result, A_Fiche.D_DEBUT);
		//
		return result;
	}

	/**
	 * La liste des fiches de postes "occupees" par l'agent. On ne conserve que
	 * les fiches dont les dates coincident avec l'affectation du poste et de
	 * l'agent.
	 */
	public NSArray tosFicheDePoste() {
		return tosFiche(EOFicheDePoste.ENTITY_NAME);
	}

	/**
	 * La liste des fiches de LOLF "occupees" par l'agent. On ne conserve que les
	 * fiches dont les dates coincident avec l'affectation du poste et de l'agent.
	 */
	public NSArray tosFicheLolf() {
		return tosFiche(EOFicheLolf.ENTITY_NAME);
	}

	public Number noIndividu() {
		return (Number) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("noIndividu");
	}

	/**
	 * La liste des postes sans doublons
	 */
	public NSArray tosPoste() {
		NSArray tosPoste = super.tosPoste();
		return NSArrayCtrl.removeDuplicate(tosPoste);
	}

	/**
	 * La liste des structures des postes sans doublons
	 */
	public NSArray<EOStructure> tosPosteStructure() {
		NSArray<EOPoste> tosPoste = this.tosPoste();
		return NSArrayCtrl.removeDuplicate((NSArray<EOStructure>) tosPoste.valueForKey(EOPoste.TO_STRUCTURE_KEY));
	}

	// recherche

	public final static String NOM_USUEL_BASIC_KEY = NOM_USUEL_KEY + "Basic";
	public final static String PRENOM_BASIC_KEY = PRENOM_KEY + "Basic";

	public String nomUsuelBasic() {
		return StringCtrl.toBasicString(StringCtrl.chaineSansAccents(nomUsuel()));
	}

	public String prenomBasic() {
		return StringCtrl.toBasicString(StringCtrl.chaineSansAccents(prenom()));
	}

	/**
	 * Pour un service donne, detailler le niveau de responsabilite de la personne
	 * via GRP_RESPONSABLE. Les droits de lecture sont herites par les
	 * responsables de toutes structures peres => c'est ce qu'on affiche.
	 * 
	 * @param fromStructure
	 * @return
	 */
	public String tipHeritageAnnuaire(EOStructure fromStructure) {
		StringBuffer sb = new StringBuffer();

		boolean shouldContinue = true;
		EOStructure structure = fromStructure;
		while (shouldContinue) {
			if (structure == structure.toStructurePere()) {
				shouldContinue = false;
			}
			if (structure.toResponsable() == this) {
				if (sb.length() == 0) {
					sb.append("Responsable de ");
				} else {
					sb.append(" / ");
				}
				sb.append(structure.displayUltraCourt());
			}
			structure = structure.toStructurePere();
		}

		return sb.toString();
	}

	/**
	 * Retourne l'evaluation de l'individu pour la periode donnee
	 * 
	 * @param periode
	 * @return
	 */
	public EOVCandidatEvaluation toVCandidatEvaluationForPeriode(EOEvaluationPeriode periode) {
		EOVCandidatEvaluation result = null;
		NSArray<EOVCandidatEvaluation> records = tosVCandidatEvaluation(
				ERXQ.equals(EOVCandidatEvaluation.TO_EVALUATION_PERIODE_KEY, periode));
		if (records.count() > 0) {
			result = records.objectAtIndex(0);
		}
		return result;
	}

	/**
	 * Indique si l'individu est fonctionnaire sur une période de référence
	 * 
	 * @param dateReference
	 * @return
	 */
	public boolean isFonctionnaire(NSTimestamp dateDebut, NSTimestamp dateFin) {
		NSArray<EOElementCarriere> lesElementsCarriere = EOElementCarriere.findSortedElementCarriereForIndividuAndDateInContext(
				editingContext(), this, dateDebut, dateFin);
		return lesElementsCarriere.count() > 0;
	}

	/**
	 * Indique si l'individu est fonctionnaire stagiaire sur une période de
	 * référence
	 * 
	 * @param dateReference
	 * @return
	 */
	public boolean isStagiaire(NSTimestamp dateReference) {
		NSArray<EOStage> lesStages = EOStage.findStageForIndividuAndDateInContext(
				editingContext(), this, dateReference);
		return lesStages.count() > 0;
	}

	/**
	 * Indique si l'individu est contractuel sur une période de référence
	 * 
	 * @param dateReference
	 * @return
	 */
	public boolean isContractuel(NSTimestamp dateDebut, NSTimestamp dateFin) {
		NSArray<EOContratAvenant> lesContrats = EOContratAvenant.findSortedContratForIndividuAndDateInContext(
				editingContext(), this, dateDebut, dateFin);
		return lesContrats.count() > 0;
	}

	/**
	 * Le corps d'appartenance d'un titulaire sur une période données
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	private EOCorps getTitulaireCorpsForPeriode(NSTimestamp dateDebut, NSTimestamp dateFin) {

		NSArray<EOElementCarriere> lesElementsCarriere = EOElementCarriere.findSortedElementCarriereForIndividuAndDateInContext(
				editingContext(), this, dateDebut, dateFin);

		// corps d'appartenance
		EOCorps corps = null;
		if (lesElementsCarriere.count() > 0) {
			corps = (((EOElementCarriere) lesElementsCarriere.lastObject())).toCorps();
		}

		return corps;
	}

	/**
	 * Indique si l'individu fait partie de l'ANES sur une période de référence
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public boolean isAenes(NSTimestamp dateDebut, NSTimestamp dateFin) {
		boolean isAenes = false;

		EOCorps eoCorps = getTitulaireCorpsForPeriode(dateDebut, dateFin);
		if (eoCorps != null &&
				eoCorps.toTypePopulation() != null &&
				eoCorps.toTypePopulation().isAenes()) {
			isAenes = true;
		}

		return isAenes;
	}

	/**
	 * Indique si l'individu fait partie de l'ITRF sur une période de référence
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public boolean isItrf(NSTimestamp dateDebut, NSTimestamp dateFin) {
		boolean isItrf = false;

		EOCorps eoCorps = getTitulaireCorpsForPeriode(dateDebut, dateFin);
		if (eoCorps != null &&
				eoCorps.toTypePopulation() != null &&
				eoCorps.toTypePopulation().isItrf()) {
			isItrf = true;
		}

		return isItrf;
	}

	/**
	 * Indique si l'individu fait partie de la BU sur une période de référence
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public boolean isBu(NSTimestamp dateDebut, NSTimestamp dateFin) {
		boolean isBu = false;

		EOCorps eoCorps = getTitulaireCorpsForPeriode(dateDebut, dateFin);
		if (eoCorps != null &&
				eoCorps.toTypePopulation() != null &&
				eoCorps.toTypePopulation().isBu()) {
			isBu = true;
		}

		return isBu;
	}

	/**
	 * liste des individus qui sont dans la hierarchie (NON ENS)
	 * 
	 * @param ec
	 * @return
	 */
	public static NSArray<EOIndividu> findIndividuActuelNonEnsInHierarchieInContext(
			EOEditingContext ec, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"toIndividu.toVPersonnelActuelNonEns.toIndividu.nomUsuel <> nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
				new NSArray(periode));
		return (NSArray) UtilDb.fetchArray(ec, "Hierarchie", qual, null).valueForKey("toIndividu");
	}

	/**
	 * liste des individus qui sont dans la hierarchie (ENS)
	 * 
	 * @param ec
	 * @return
	 */
	public static NSArray<EOIndividu> findIndividuActuelEnsInHierarchieInContext(
			EOEditingContext ec, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"toIndividu.toVPersonnelActuelEns.toIndividu.nomUsuel <> nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
				new NSArray(periode));
		return (NSArray) UtilDb.fetchArray(ec, "Hierarchie", qual, null).valueForKey("toIndividu");
	}

	/**
	 * liste des individus qui ne sont pas dans la hierarchie (NON ENS)
	 */
	public static NSArray<EOIndividu> findIndividuActuelNonEnsNotInHierarchieInContext(
			EOEditingContext ec, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"toIndividu.toVPersonnelActuelNonEns.toIndividu.nomUsuel <> nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
				new NSArray(periode));
		NSArray records = EOIndividu.findIndividuActuelNonEnsInHierarchieInContext(ec, periode).arrayByAddingObjectsFromArray(
				(NSArray) UtilDb.fetchArray(ec, "Hierarchie", qual, null).valueForKey("toIndividu"));
		NSMutableArray recordsMutable = new NSMutableArray(findAllIndividuActuelNonEnsInContext(ec));
		recordsMutable.removeObjectsInArray(records);
		return recordsMutable.immutableClone();
	}

	/**
	 * liste des individus qui ne sont pas dans la hierarchie (ENS)
	 */
	public static NSArray<EOIndividu> findIndividuActuelEnsNotInHierarchieInContext(
			EOEditingContext ec, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
				"toIndividu.toVPersonnelActuelEns.toIndividu.nomUsuel <> nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
				new NSArray(periode));
		NSArray records = EOIndividu.findIndividuActuelEnsInHierarchieInContext(ec, periode).arrayByAddingObjectsFromArray(
				(NSArray) UtilDb.fetchArray(ec, "Hierarchie", qual, null).valueForKey("toIndividu"));
		NSMutableArray recordsMutable = new NSMutableArray(findAllIndividuActuelEnsInContext(ec));
		recordsMutable.removeObjectsInArray(records);
		return recordsMutable.immutableClone();
	}

	/**
	 * liste des individus responsable administratif de la structure
	 * 
	 * @param ec
	 * @param individu
	 * @return
	 */
	public static NSArray<EOIndividu> findResponsableAdministratifForStructure(EOEditingContext ec, EOStructure eoStructure) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("toStructure = %@", new NSArray<EOStructure>(eoStructure));
		return (NSArray<EOIndividu>) UtilDb.fetchArray(ec, "RepartStructureResp", qual, null).valueForKey("toIndividu");
	}

	/**
	 * tourver un individu par son persId
	 * 
	 * @param ec
	 * @param persId
	 * @return
	 */
	public static EOIndividu findIndividuForPersIdInContext(EOEditingContext ec, Number persId) {
		EOIndividu individu = null;
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("persId = %@", new NSArray<Number>(persId));
		NSArray<EOIndividu> records = UtilDb.fetchArray(ec, "Individu", qual, null);
		if (records.count() > 0) {
			individu = (EOIndividu) records.lastObject();
		}
		return individu;
	}

	/**
	 * trouve un individu d'apres son numero
	 * 
	 * @param ec
	 * @param noIndividu
	 * @return
	 */
	public static EOIndividu findIndividuForNoIndividuInContext(EOEditingContext ec, Number noIndividu) {
		EOIndividu individu = null;
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("noIndividu = %@", new NSArray<Number>(noIndividu));
		NSArray<EOIndividu> records = UtilDb.fetchArray(ec, "Individu", qual, null);
		if (records.count() > 0) {
			individu = (EOIndividu) records.lastObject();
		}
		return individu;
	}

	/**
	 * trouver un individu selon un nom et/ou un prenom
	 * 
	 * @param ec
	 * @param nomOuPrenom
	 * @param onlyInPersonnelActuel
	 *          : <code>true</code> : restreindre la recherche aux personnels,
	 *          <code>false</code>
	 * @return
	 */
	public static NSArray<EOIndividu> findIndividuForNomOrPrenom(
			EOEditingContext ec, String nomOuPrenom, boolean onlyInPersonnelActuel) {
		// mise en caps
		nomOuPrenom = nomOuPrenom.toUpperCase();
		NSArray<EOIndividu> findIndividuForNomOrPrenom = new NSArray<EOIndividu>();
		NSArray<String> mots = NSArray.componentsSeparatedByString(nomOuPrenom, " ");
		String premierMot, deuxiemeMot;
		premierMot = deuxiemeMot = "";
		if (mots.count() > 0) {
			premierMot = mots.objectAtIndex(0);
			if (mots.count() > 1) {
				deuxiemeMot = mots.objectAtIndex(1);
			}
			NSArray<String> args = new NSArray<String>(new String[] {
					"*" + nomOuPrenom + "*",
					"*" + nomOuPrenom + "*",
					"*" + premierMot + "*",
					"*" + deuxiemeMot + "*",
					"*" + premierMot + "*",
					"*" + deuxiemeMot + "*" });

			String strQual =
					"(" + NOM_USUEL_KEY + "  like %@ OR " +
							PRENOM_KEY + "  like %@ OR " +
							"(" + NOM_USUEL_KEY + "  like %@ AND " + PRENOM_KEY + "  like %@) OR " +
							"(" + PRENOM_KEY + "  like %@ AND " + NOM_USUEL_KEY + "  like %@)" +
							")";
			if (onlyInPersonnelActuel) {

				strQual += " and " + TO_V_PERSONNEL_ACTUEL_KEY + "." +
						EOVPersonnelActuel.TO_INDIVIDU_KEY + "." +
						EOIndividu.PERS_ID_KEY + " <> nil";
			}
			EOQualifier qual = CktlDataBus.newCondition(strQual, args);
			findIndividuForNomOrPrenom = fetchIndividus(ec, qual, INDIVIDU_SORT);
			findIndividuForNomOrPrenom = NSArrayCtrl.removeDuplicate(findIndividuForNomOrPrenom);
		}
		return findIndividuForNomOrPrenom;
	}

	/**
	 * Construire un qualifier qui permet une recherche d'un individu d'apres un
	 * nom et/ou un prenom. Le lien vers l'individu doit etre nommee
	 * <code>toIndividu</code> sur l'entite que l'on va fetcher avec ce qualifier
	 * 
	 * @param nomOuPrenom
	 * @return
	 */
	public static EOQualifier getQualifierForNomOrPrenom(String nomOuPrenom) {
		NSArray<String> mots = NSArray.componentsSeparatedByString(nomOuPrenom, " ");
		String premierMot, deuxiemeMot;
		premierMot = deuxiemeMot = "";
		if (mots.count() > 0) {
			premierMot = (String) mots.objectAtIndex(0);
			if (mots.count() > 1) {
				deuxiemeMot = (String) mots.objectAtIndex(1);
			}
			NSArray<String> args = new NSArray<String>(new String[] {
					"*" + nomOuPrenom + "*",
					"*" + nomOuPrenom + "*",
					"*" + premierMot + "*",
					"*" + deuxiemeMot + "*",
					"*" + premierMot + "*",
					"*" + deuxiemeMot + "*" });
			String strQual = "toIndividu.nomUsuel like %@ OR " +
					"toIndividu.prenom like %@ OR " +
					"(toIndividu.nomUsuel like %@ AND toIndividu.prenom like %@) OR " +
					"(toIndividu.prenom like %@ AND toIndividu.nomUsuel like %@)";
			return EOQualifier.qualifierWithQualifierFormat(strQual, args);
		}
		return null;
	}

	public static NSArray<EOIndividu> findAllIndividuActuelNonEnsInContext(EOEditingContext ec) {
		NSArray<EOIndividu> records = (NSArray<EOIndividu>) UtilDb.fetchArray(ec, "VPersonnelActuelNonEns", null, null).valueForKey("toIndividu");
		// records = EOSortOrdering.sortedArrayUsingKeyOrderArray(records,
		// Individu.arraySort);
		return records;
	}

	public static NSArray<EOIndividu> findAllIndividuActuelEnsInContext(EOEditingContext ec) {
		NSArray<EOIndividu> records = (NSArray<EOIndividu>) UtilDb.fetchArray(ec, "VPersonnelActuelEns", null, null).valueForKey("toIndividu");
		// records = EOSortOrdering.sortedArrayUsingKeyOrderArray(records,
		// Individu.arraySort);
		return records;
	}

	/**
	 * La liste des evaluations classées par dates
	 * 
	 * @return
	 */
	public NSArray<EOEvaluation> tosEvaluationSorted() {
		NSArray<EOEvaluation> tosEvaluation = tosEvaluation();

		tosEvaluation = CktlSort.sortedArray(
				tosEvaluation, EOEvaluation.TO_EVALUATION_PERIODE_KEY + "." + EOEvaluationPeriode.EPE_D_DEBUT_KEY);

		// TODO faire du filtrage plus en amont ou bien pouvoir gérer sans plantage
		NSMutableArray<EOEvaluation> array = new NSMutableArray<EOEvaluation>();

		// ze mégabidouille de sagouin ...
		for (int i = 0; i < tosEvaluation.count(); i++) {
			EOEvaluation eoEvaluation = tosEvaluation.objectAtIndex(i);
			try {
				EOVCandidatEvaluation eoVCandidiatEvaluation = eoEvaluation.toVCandidatEvaluation();
				EOEvaluation eoEvaluation2 = eoVCandidiatEvaluation.toEvaluation();
				array.addObject(eoEvaluation);
			} catch (Exception e) {

			}
		}

		tosEvaluation = array.immutableClone();

		return tosEvaluation;
	}

	/**
	 * Grade de l'agent sur la période
	 * 
	 * @param debut
	 * @param fin
	 * @return
	 */
	public EOGrade getGradeForPeriode(NSTimestamp debut, NSTimestamp fin) {
		EOGrade eoGrade = null;

		NSArray<EOElementCarriere> eoElementCarriereArray = EOElementCarriere.findSortedElementCarriereForIndividuAndDateInContext(
				editingContext(), this, debut, fin);

		if (eoElementCarriereArray.count() > 0) {
			EOElementCarriere eoElementCarriere = eoElementCarriereArray.lastObject();
			eoGrade = eoElementCarriere.toGrade();
		} else {
			// rechercher parmi les contrats
			NSArray<EOContratAvenant> eoContratAvenantArray = EOContratAvenant.findSortedContratForIndividuAndDateInContext(
					editingContext(), this, debut, fin);
			if (eoContratAvenantArray.count() > 0) {
				EOContratAvenant eoContratAvenant = eoContratAvenantArray.lastObject();
				eoGrade = eoContratAvenant.toGrade();
			}
		}

		return eoGrade;
	}

	/**
	 * Corps de l'agent sur la période
	 * 
	 * @param debut
	 * @param fin
	 * @return
	 */
	public EOCorps getCorpsForPeriode(NSTimestamp debut, NSTimestamp fin) {
		EOCorps eoCorps = null;

		NSArray<EOElementCarriere> eoElementCarriereArray = EOElementCarriere.findSortedElementCarriereForIndividuAndDateInContext(
				editingContext(), this, debut, fin);

		if (eoElementCarriereArray.count() > 0) {
			EOElementCarriere eoElementCarriere = eoElementCarriereArray.lastObject();
			eoCorps = eoElementCarriere.toCorps();
		}

		return eoCorps;
	}

	/**
	 * retourne un dico contenant les informations GEPETO d'un agent
	 * 
	 * @param ec
	 * @param record
	 * @return
	 */
	public static NSDictionary<String, Object> findDicoAgentGepetoInContext(EOEditingContext ec, EOGenericRecord record) {
		NSMutableDictionary<String, Object> dico = new NSMutableDictionary<String, Object>();

		if (record != null) {

			EOIndividu individu = null;
			NSTimestamp debut = null;
			NSTimestamp fin = null;
			if (record instanceof EOAffectationDetail) {
				individu = ((EOAffectationDetail) record).toAffectation().toIndividu();
				debut = ((EOAffectationDetail) record).dDebut();
				fin = ((EOAffectationDetail) record).dFin();
				if (fin == null) {
					fin = DateCtrl.now();
				}
			} else if (record instanceof EOEvaluation) {
				individu = ((EOEvaluation) record).toIndividu();
				debut = ((EOEvaluation) record).toEvaluationPeriode().epeDDebut();
				fin = ((EOEvaluation) record).toEvaluationPeriode().epeDFin();
			}

			// contractuel ou (fonctionnaire ou fonctionnaire stagiaire)
			String statut = "";
			if (individu.isFonctionnaire(debut, fin)) {
				statut = "Fonctionnaire";
				if (individu.isStagiaire(fin)) {
					statut += " Stagiaire";
				}
			} else {
				statut = "Contractuel";
			}

			// corps d'appartenance
			EOCorps eoCorps = individu.getCorpsForPeriode(debut, fin);

			// grade de l'individu
			EOGrade eoGrade = individu.getGradeForPeriode(debut, fin);

			dico.setObjectForKey(Integer.toString(individu.noIndividu().intValue()), "identifiant");
			dico.setObjectForKey(individu.nomUsuel(), "nomUsuel");
			dico.setObjectForKey(individu.prenom(), "prenom");
			dico.setObjectForKey(DateCtrl.dateToString(individu.dNaissance()), "dNaissance");

			dico.setObjectForKey(statut, "statut");

			dico.setObjectForKey("", "corps");
			if (eoCorps != null) {
				dico.setObjectForKey(eoCorps.llCorps(), "corps");
			}

			dico.setObjectForKey("", "grade");
			if (eoGrade != null) {
				dico.setObjectForKey(eoGrade.llGrade(), "grade");
			}

		}

		return dico.immutableClone();
	}

	/**
	 * La photo de l'agent, en tenant compte du droit de publication ainsi que
	 * l'existance ou non d'un flux binaire
	 * 
	 * @return
	 */
	public NSData toPhoto() {
		NSData datasPhoto = null;

		// pour eviter les No
		// org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum found with
		// globalID: <PhotosEmployesGrhum: [noIndividu: '1159'] >
		try {
			if (OUI.equals(indPhoto()) &&
					toPhotosEmployesGrhum() != null &&
					toPhotosEmployesGrhum().datasPhoto() != null) {
				datasPhoto = toPhotosEmployesGrhum().datasPhoto();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return datasPhoto;
	}
}
