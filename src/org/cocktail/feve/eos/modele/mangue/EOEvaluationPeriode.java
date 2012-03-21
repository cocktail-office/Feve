package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EONiveauCompetence;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EOEvaluationPeriode extends _EOEvaluationPeriode {

	public EOEvaluationPeriode() {
		super();
	}

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

	/**
	 * Affichage dd1/mm1/yyyy1-dd2/mm2/yyyy2
	 */
	public String strDateDebutDateFin() {
		return DateCtrl.dateToString(epeDDebut()) + "-" + DateCtrl.dateToString(epeDFin());
	}

	private final static String DATE_FORMAT_ANNEE = "%Y";

	/**
	 * Affichage yyyy1/yyyy2
	 */
	public String strAnneeDebutAnneeFin() {
		return DateCtrl.dateToString(epeDDebut(), DATE_FORMAT_ANNEE) + "/" + DateCtrl.dateToString(epeDFin(), DATE_FORMAT_ANNEE);
	}

	public Number epeKey() {
		return (Number) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("epeKey");
	}

	/**
	 * Obtenir l'enregistrement <code>EOEvaluationPeriode</code> actuel.
	 * 
	 * @return
	 */
	public static EOEvaluationPeriode getCurrentPeriode(EOEditingContext ec) {
		EOQualifier qualCurrentPeriode = CktlDataBus.newCondition(
					EPE_D_DEBUT_KEY + "<=%@ and " + EPE_D_FIN_KEY + ">=%@", new NSArray(new NSTimestamp[] {
							DateCtrl.now(), DateCtrl.now() }));
		NSArray records = fetchEvaluationPeriodes(ec, qualCurrentPeriode, null);
		EOEvaluationPeriode result = null;
		if (records.count() > 0) {
			result = (EOEvaluationPeriode) records.lastObject();
		} else {
			// le dernier en date
			records = fetchEvaluationPeriodes(ec, null, CktlSort.newSort(EPE_D_DEBUT_KEY));
			if (records.count() > 0) {
				result = (EOEvaluationPeriode) records.lastObject();
			}
		}
		return result;
	}

	/**
	 * Obtenir l'enregistrement <code>EOEvaluationPeriode</code> dernier en date
	 * 
	 * @return
	 */
	public static EOEvaluationPeriode getLastPeriode(EOEditingContext ec) {
		NSArray records = fetchEvaluationPeriodes(ec, null, CktlSort.newSort(EPE_D_DEBUT_KEY));
		EOEvaluationPeriode result = null;
		if (records.count() > 0) {
			result = (EOEvaluationPeriode) records.lastObject();
		}
		return result;
	}

	/**
	 * L'enregistrement <code>EOEvaluationPeriode</code> qui suit <em>this</em>
	 * 
	 * @return
	 */
	public EOEvaluationPeriode toNextPeriode() {
		NSArray records = fetchEvaluationPeriodes(editingContext(), null, CktlSort.newSort(EPE_D_DEBUT_KEY));
		EOEvaluationPeriode result = null;
		boolean shouldBeNext = false;
		for (int i = 0; i < records.count(); i++) {
			EOEvaluationPeriode periode = (EOEvaluationPeriode) records.objectAtIndex(i);
			if (shouldBeNext) {
				result = periode;
				break;
			}
			if (periode.epeKey().intValue() == epeKey().intValue()) {
				shouldBeNext = true;
			}
		}
		return result;
	}

	/**
	 * L'enregistrement <code>EOEvaluationPeriode</code> qui precede <em>this</em>
	 * 
	 * @return
	 */
	public EOEvaluationPeriode toPrevPeriode() {
		NSArray records = fetchEvaluationPeriodes(editingContext(), null, CktlSort.newSort(EPE_D_DEBUT_KEY, CktlSort.Descending));
		EOEvaluationPeriode result = null;
		boolean shouldBeNext = false;
		for (int i = 0; i < records.count(); i++) {
			EOEvaluationPeriode periode = (EOEvaluationPeriode) records.objectAtIndex(i);
			if (shouldBeNext) {
				result = periode;
				break;
			}
			if (periode == this) {
				shouldBeNext = true;
			}
		}
		return result;
	}

	/**
	 * Retrouver une periode d'apres sa clé primaire
	 * 
	 * @param editingContext
	 * @param epeKey
	 * @return
	 */
	public static EOEvaluationPeriode findPeriodeForEpeKeyInContext(
				EOEditingContext editingContext, Number epeKey) {
		NSArray allPeriode = fetchAllEvaluationPeriodes(editingContext);
		EOEvaluationPeriode result = null;
		int i = 0;
		while (result == null && i < allPeriode.count()) {
			EOEvaluationPeriode periode = (EOEvaluationPeriode) allPeriode.objectAtIndex(i);
			Number periodeEpeKey = (Number) com.webobjects.eoaccess.EOUtilities.primaryKeyForObject(editingContext, periode).valueForKey("epeKey");
			if (periodeEpeKey != null && periodeEpeKey.intValue() == epeKey.intValue()) {
				result = periode;
			}
			i++;
		}
		return result;
	}

	// la liste des niveaux de competences associés

	private NSArray<EONiveauCompetence> niveauCompetenceList;

	/**
	 * 
	 * @return
	 */
	public NSArray<EONiveauCompetence> niveauCompetenceList() {
		if (niveauCompetenceList == null) {
			niveauCompetenceList = EONiveauCompetence.getNiveauCompetenceForPeriode(this);
		}
		return niveauCompetenceList;
	}

	/**
	 * Creation d'un enregistrement avec une date de debut et une date de fin
	 * definie
	 * 
	 * @param ec
	 * @param dDebut
	 * @param dFin
	 * @return
	 */
	public static EOEvaluationPeriode createEvaluationPeriode(EOEditingContext ec, NSTimestamp dDebut, NSTimestamp dFin) {
		EOEvaluationPeriode newRecord = EOEvaluationPeriode.createEvaluationPeriode(
					ec, DateCtrl.now(), DateCtrl.now(), dDebut, dFin);
		return newRecord;
	}

	/**
	 * Liste des individus nécéssitant la saisie d'objectifs précédents (nouveaux
	 * entrants)
	 * 
	 * @return
	 */
	public NSArray<EOVCandidatEvaluation> getEoVCandidatEvaluationNouveauxEntrantsArray() {
		NSArray<EOVCandidatEvaluation> array = new NSArray<EOVCandidatEvaluation>();

		NSArray<EOVCandidatEvaluation> eoVCandidatEvaluationArray = EOVCandidatEvaluation.fetchVCandidatEvaluations(
				editingContext(),
				ERXQ.equals(EOVCandidatEvaluation.TO_EVALUATION_PERIODE_KEY, this),
				null);

		for (int i = 0; i < eoVCandidatEvaluationArray.count(); i++) {
			EOVCandidatEvaluation eoVCandidatEvaluation = eoVCandidatEvaluationArray.objectAtIndex(i);
			if (eoVCandidatEvaluation.toEvaluationPrecedente() == null &&
					eoVCandidatEvaluation.toEvaluationPeriode().toPrevPeriode() != null) {
				array = array.arrayByAddingObject(eoVCandidatEvaluation);
			}
		}

		// classement
		array = CktlSort.sortedArray(array, EOVCandidatEvaluation.TO_INDIVIDU_KEY + "." + EOIndividu.NOM_PRENOM_KEY);

		return array;
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EODroitNouvelEntrant> getEoDroitNouvelEntrantArray() {
		NSArray<EODroitNouvelEntrant> array = null;

		EOQualifier qual = ERXQ.equals(
				EODroitNouvelEntrant.TO_EVALUATION_PERIODE_KEY, this);

		array = EODroitNouvelEntrant.fetchDroitNouvelEntrants(
				editingContext(), qual, null);

		return array;
	}
}
