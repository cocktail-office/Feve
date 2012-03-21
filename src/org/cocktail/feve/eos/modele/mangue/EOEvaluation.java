// EOEvaluation.java
// Created on Fri Apr 01 13:59:56  2005 by Apple EOModeler Version 5.2
/**
 * @deprecated
 * @author ctarade 1 avr. 2005
 *
 */
package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.feve.app.finder.FinderFeve;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

/**
 * @author ctarade 1 avr. 2005
 * 
 */
public class EOEvaluation extends _EOEvaluation {

	public void validateForInsert() throws NSValidation.ValidationException {
		setDCreation(DateCtrl.now());
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
	private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

	}

	// methodes rajoutees

	// methodes rajoutees

	/**
   * 
   */
	public boolean isViseParResponsableRh() {
		boolean isViseParResponsableRh = false;

		if (dVisaResponsableRh() != null) {
			isViseParResponsableRh = true;
		}

		return isViseParResponsableRh;
	}

	/**
   * 
   */
	public void setIsViseParResponsableRh(boolean value) {
		if (value == true) {
			setDVisaResponsableRh(DateCtrl.now());
		} else {
			setDVisaResponsableRh(null);
		}
	}

	/**
	 * @deprecated
	 * @see #tosLastRepartFdpComp() les repart associees aux competences des
	 *      fiches de poste utilises pour l'evaluation des competences
	 */
	public NSArray tosRepartFdpComp() {
		return NSArrayCtrl.flattenArray((NSArray) tosFicheDePoste().valueForKey("tosRepartFdpComp"));
	}

	/**
	 * les repart associees aux competences des fiches de poste utilises pour
	 * l'evaluation des competences
	 */
	public NSArray tosLastRepartFdpComp() {
		return NSArrayCtrl.flattenArray((NSArray) tosLastFicheDePoste().valueForKey(EOFicheDePoste.TOS_REPART_FDP_COMP_KEY));
	}

	public String display() {
		return toIndividu().display() + " du " + DateCtrl.dateToString(evaDDebut()) + " au " + DateCtrl.dateToString(evaDFin());
	}

	/**
	 * @deprecated
	 * @see #tosLastFicheDePoste() les fiches de poste sur lesquelles portent
	 *      cette evaluation -> filtrage par rapport aux dates de l'evaluation
	 */
	public NSArray tosFicheDePoste() {
		return toIndividu().tosFicheDePostePourDate(evaDDebut(), evaDFin());
	}

	public final static NSArray ARRAY_SORT_INDIVIDU = new NSArray(
			new EOSortOrdering[] {
					EOSortOrdering.sortOrderingWithKey("toIndividu.nomUsuel", EOSortOrdering.CompareAscending),
					EOSortOrdering.sortOrderingWithKey("toIndividu.prenom", EOSortOrdering.CompareAscending)
			}
			);

	// deplacement de la date vers la periode

	/**
	 * @deprecated utiliser la periode
	 * @see #toEvaluationPeriode()
	 */
	public NSTimestamp evaDDebut() {
		// return (NSTimestamp) storedValueForKey("evaDDebut");
		return toEvaluationPeriode().epeDDebut();
	}

	/**
	 * @deprecated utiliser la periode
	 * @see #toEvaluationPeriode()
	 */
	public NSTimestamp evaDFin() {
		// return (NSTimestamp) storedValueForKey("evaDFin");
		return toEvaluationPeriode().epeDFin();
	}

	/**
	 * Cas particulier d'un enregistrement "tout frais", cette valeur est a null
	 * ... on prend donc la valeur sur la to-one toIndividu
	 * 
	 * @return
	 */
	public Integer noIndividuVisible() {
		Integer noIndividuVisible = super.noIndividuVisible();
		if (noIndividuVisible == null) {
			noIndividuVisible = new Integer(toIndividu().noIndividu().intValue());
		}
		return noIndividuVisible;
	}

	/**
	 * les fiches de poste (les dernieres) sur lesquelles portent cette evaluation
	 * -> filtrage par rapport aux dates de l'evaluation
	 */
	public NSArray tosLastFicheDePoste() {
		NSArray records = toIndividu().tosFicheDePostePourDate(evaDDebut(), evaDFin());
		// classement chronologique
		records = CktlSort.sortedArray(records, EOFicheDePoste.FDP_D_DEBUT_KEY);
		NSArray recsPoste = NSArrayCtrl.removeDuplicate((NSArray) records.valueForKey(EOFicheDePoste.TO_POSTE_KEY));

		NSArray recsFicheDePoste = new NSArray();
		// ne conserver que les dernieres fiches en date
		for (int i = 0; i < recsPoste.count(); i++) {
			EOPoste poste = (EOPoste) recsPoste.objectAtIndex(i);
			NSArray recs = EOQualifier.filteredArrayWithQualifier(
					records, CktlDataBus.newCondition(EOFicheDePoste.TO_POSTE_KEY + "=%@", new NSArray(poste)));
			// on ne conserve que le dernier
			if (recs.count() > 0) {
				recsFicheDePoste = recsFicheDePoste.arrayByAddingObject(recs.lastObject());
			}
		}

		// ne conserver que celles dont l'occupation du poste est la deniere
		NSArray result = new NSArray();

		NSArray recsAffDet = NSArrayCtrl.flattenArray((NSArray) recsFicheDePoste.valueForKeyPath(EOFicheDePoste.TO_POSTE_KEY + "." + EOPoste.TOS_AFFECTATION_DETAIL_KEY));

		// d'abord celle non fermees sur la periode
		NSArray recsAffDetNonFermees = new NSArray();
		for (int i = 0; i < recsAffDet.count(); i++) {
			EOAffectationDetail recAffDet = (EOAffectationDetail) recsAffDet.objectAtIndex(i);
			if (recAffDet.toAffectation().toIndividu().noIndividu().intValue() == noIndividuVisible().intValue() && (
					recAffDet.dFin() == null || DateCtrl.isAfterEq(recAffDet.dFin(), evaDFin()))) {
				recsAffDetNonFermees = recsAffDetNonFermees.arrayByAddingObject(recAffDet);
			}
		}

		// qsdqds

		// ce eoqualifier marche pas ???
		/*
		 * EOQualifier.filteredArrayWithQualifier( recsAffDet,
		 * CktlDataBus.newCondition( EOAffectationDetail.D_FIN_AFFECTATION_DETAIL +
		 * "=%@ OR " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + ">=%@", new
		 * NSArray(new Object[]{NSKeyValueCoding.NullValue, evaDFin()})));
		 */
		if (recsAffDetNonFermees.count() > 0) {
			// on prend les fiches ayant ces occupation
			for (int i = 0; i < recsFicheDePoste.count(); i++) {
				EOFicheDePoste recFicheDePoste = (EOFicheDePoste) recsFicheDePoste.objectAtIndex(i);
				for (int j = 0; j < recsAffDetNonFermees.count(); j++) {
					EOAffectationDetail recAffDet = (EOAffectationDetail) recsAffDetNonFermees.objectAtIndex(j);
					if (recFicheDePoste.tosAffectationDetail().containsObject(recAffDet)) {
						result = result.arrayByAddingObject(recFicheDePoste);
						break;
					}
				}
			}
		} else {
			// on prend alors les dernieres en date
			result = recsFicheDePoste;
		}

		return result;
	}

	/**
	 * La liste des services de rattachement de cette evaluation
	 * 
	 * @return
	 */
	public NSArray<EOStructure> tosStructure() {
		return (NSArray<EOStructure>) tosPoste().valueForKey(EOPoste.TO_STRUCTURE_KEY);
	}

	/**
	 * La liste des postes de rattachement de cette evaluation
	 * 
	 * @return
	 */
	public NSArray<EOPoste> tosPoste() {
		return (NSArray<EOPoste>) valueForKeyPath(TOS_LAST_FICHE_DE_POSTE_KEY + "." + EOFicheDePoste.TO_POSTE_KEY);
	}

	/**
	 * Retourne l'objet <code>EOVCandidatEvaluation</code> associe a cet
	 * enregistrement.
	 */
	public EOVCandidatEvaluation toVCandidatEvaluationPeriode() {
		return EOVCandidatEvaluation.fetchVCandidatEvaluation(
				editingContext(), EOVCandidatEvaluation.TO_EVALUATION_KEY, this);
	}

	/**
	 * La liste des objectifs classés par ordre de la position
	 */
	public NSArray<EOObjectif> tosObjectif() {
		NSArray<EOObjectif> tosObjectif = super.tosObjectif();
		return CktlSort.sortedArray(tosObjectif, EOObjectif.OBJ_POSITION_KEY);
	}

	private final static int DEFAULT_OBJECTIF_COUNT = 4;

	/**
	 * Creation d'un enregistrement avec un certain nombre d'objectifs vierges
	 * 
	 * @return
	 */
	public static EOEvaluation createWithObjectif(
			EOEditingContext ec, EOIndividu individu, EOEvaluationPeriode evaluationPeriode) {
		NSTimestamp now = DateCtrl.now();
		EOEvaluation evaluation = createEvaluation(
				ec,
				now,
				now,
				(Integer) individu.noIndividu());
		evaluation.setToEvaluationPeriodeRelationship(evaluationPeriode);
		evaluation.setToIndividuRelationship(individu);

		// les objectifs
		for (int i = 0; i < DEFAULT_OBJECTIF_COUNT; i++) {
			EOObjectif unObjectif = EOObjectif.createObjectif(ec, now, now, i, evaluation);
			evaluation.addToTosObjectifRelationship(unObjectif);
		}

		return evaluation;
	}

	/**
	 * Indique si l'entretien a déjà été tenu
	 * 
	 * @return
	 */
	public boolean isEntretienTenu() {
		boolean isEntretienTenu = false;

		if (dTenueEntretien() != null) {
			isEntretienTenu = true;
		}

		return isEntretienTenu;
	}

	/**
	 * Gestion de la selection des évaluations dans parmi une liste : cette
	 * méthode va créer les enregistrements nécéssaires si besoin.
	 * 
	 * Attention, cette méthode fait la sauvegarde, car il faut rafraichir
	 * certiains objets via les globalIds ... dispos uniquement apres sauvegarde
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static EOEvaluation doSelectEvaluation(
			EOEditingContext editingContext,
			EOVCandidatEvaluation eoVCandidatEvaluation,
			EOEvaluationPeriode eoEvaluationPeriode,
			EOIndividu eoIndividuSelectionneur) throws Throwable {

		EOEvaluation eoEvaluation = null;

		// faut-il créer l'enregistrement N ?
		if (eoVCandidatEvaluation.toEvaluation() != null) {
			eoEvaluation = eoVCandidatEvaluation.toEvaluation();
		} else {
			if (eoEvaluationPeriode != null) {
				eoEvaluation = EOEvaluation.createWithObjectif(
						editingContext, eoVCandidatEvaluation.toIndividu(), eoEvaluationPeriode);
			}
		}

		if (UtilDb.save(editingContext, "")) {
			// forcer le rafraichissement de objets
			NSMutableArray<EOGlobalID> eoGlobalIDArray = new NSMutableArray<EOGlobalID>();
			eoGlobalIDArray.add(editingContext.globalIDForObject(eoVCandidatEvaluation));
			eoGlobalIDArray.add(editingContext.globalIDForObject(eoEvaluation));
			editingContext.invalidateObjectsWithGlobalIDs(eoGlobalIDArray);
		}

		// faut-il créer l'enregistrement N-1 ?
		EOEvaluation prevEoEvaluation = null;
		if (eoVCandidatEvaluation.isModificationObjPrecAutorisee(eoIndividuSelectionneur) &&
				eoEvaluationPeriode.toPrevPeriode() != null) {
			prevEoEvaluation = eoVCandidatEvaluation.toEvaluation().toEvaluationPrecedente();
			if (prevEoEvaluation == null) {
				prevEoEvaluation = EOEvaluation.createWithObjectif(
						editingContext, eoVCandidatEvaluation.toIndividu(), eoEvaluationPeriode.toPrevPeriode());

				if (UtilDb.save(editingContext, "")) {
					// forcer le rafraichissement de objets
					NSMutableArray<EOGlobalID> eoGlobalIDArray = new NSMutableArray<EOGlobalID>();
					eoGlobalIDArray.add(editingContext.globalIDForObject(prevEoEvaluation));
					editingContext.invalidateObjectsWithGlobalIDs(eoGlobalIDArray);
				}
			}

		}

		return eoEvaluation;

	}

	/**
	 * Indique si le dernier statut connu de l'agent sur la période de l'entretien
	 * est AENES
	 * 
	 * @return
	 */
	public boolean isAenes() {
		boolean isAenes = false;

		isAenes = toIndividu().isAenes(toEvaluationPeriode().epeDDebut(), toEvaluationPeriode().epeDFin());

		return isAenes;
	}

	/**
	 * Indique si le dernier statut connu de l'agent sur la période de l'entretien
	 * est ITRF
	 * 
	 * @return
	 */
	public boolean isItrf() {
		boolean isItrf = false;

		isItrf = toIndividu().isItrf(toEvaluationPeriode().epeDDebut(), toEvaluationPeriode().epeDFin());

		return isItrf;
	}

	/**
	 * Indique si le dernier statut connu de l'agent sur la période de l'entretien
	 * est AENES
	 * 
	 * @return
	 */
	public boolean isBu() {
		boolean isBu = false;

		isBu = toIndividu().isBu(toEvaluationPeriode().epeDDebut(), toEvaluationPeriode().epeDFin());

		return isBu;
	}

	private final static String LIBELLE_POPULATION_AENES = "Administration de l'Education Nationale et de l'Enseignement (AENES)";
	private final static String LIBELLE_POPULATION_ITRF = "Ingénieurs Techniques, de Recherche et de Formation (ITRF)";
	private final static String LIBELLE_POPULATION_BU = "Bibliothèque";

	/**
	 * Le libelle de la population selon le statut de l'agent
	 * 
	 * @return
	 */
	public String getLibellePopulation() {
		String libelle = "";

		if (isAenes()) {
			libelle = LIBELLE_POPULATION_AENES;
		} else if (isItrf()) {
			libelle = LIBELLE_POPULATION_ITRF;
		} else if (isBu()) {
			libelle = LIBELLE_POPULATION_BU;
		}

		return libelle;
	}

	// gestion des droits

	/**
	 * Indique si l'agent passé en paramètre a le droit de modifier l'évaluation
	 * en cours
	 */
	public boolean isModifiable(FeveUserInfo ui) {
		boolean isModifiable = false;

		isModifiable = ui.hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.EVALUATION, toVCandidatEvaluation(), false);

		return isModifiable;
	}

	// dico des données

	private NSMutableDictionary<String, Object> dicoAgent;

	public final NSDictionary<String, Object> getDicoAgent() {
		if (dicoAgent == null) {
			dicoAgent = new NSMutableDictionary<String, Object>();
			dicoAgent.addEntriesFromDictionary(EOIndividu.findDicoAgentGepetoInContext(editingContext(), this));
			dicoAgent.addEntriesFromDictionary(FinderFeve.findDicoEvaluationInContext(editingContext(), this));
		}
		return dicoAgent;
	}
}
