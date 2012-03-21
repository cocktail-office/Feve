package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOReferensEmplois extends _EOReferensEmplois {

	public EOReferensEmplois() {
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
	 * Apparemment cette methode n'est pas appel̩e.
	 * 
	 * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
	 */
	public void validateForSave() throws NSValidation.ValidationException {
		validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForSave();

	}

	/**
	 * Peut etre appele �� partir des factories.
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

	// METHODES RAJOUTEES

	public final static String DISPLAY_KEY = "display";

	public boolean isArchive() {
		boolean isArchive = false;

		if (toReferensFp() == null ||
				toReferensFp().toReferensDcp() == null ||
				toReferensFp().toReferensDcp().isArchive()) {
			isArchive = true;
		}

		return isArchive;
	}

	public String display() {
		String display = StringCtrl.capitalizeWords(intitulemploi());
		if (codemen() != null) {
			display += " (" + codemen() + ")";
		}
		if (isArchive()) {
			display = "___" + display + " (ancienne nomenclature)";
		}

		return display;
	}

	public String displayLong() {
		return display();
	}

	@Override
	public NSArray tosReferensActivites() {
		// classement alphabetique
		NSArray tosReferensActivites = super.tosReferensActivites();
		return CktlSort.sortedArray(tosReferensActivites, EOReferensActivites.INTITULACTIVITE_KEY);
	}

	@Override
	public NSArray tosReferensCompetences() {
		// classement alphabetique
		NSArray tosReferensCompetences = super.tosReferensCompetences();
		return CktlSort.sortedArray(tosReferensCompetences, EOReferensCompetences.INTITULCOMP_KEY);
	}
}
