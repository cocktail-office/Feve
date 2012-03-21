package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.foundation.NSValidation;

public class EOReferensFp extends _EOReferensFp {

	public EOReferensFp() {
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

	public String display() {
		return StringCtrl.capitalizeWords(intitulfp());
	}

	/**
	 * Pour eviter les exceptions
	 * er.extensions.eof.ERXDatabaseContextDelegate$ObjectNotAvailableException:
	 * No org.cocktail.feve.eos.modele.grhum.EOReferensFp found with globalID:
	 * <ReferensFp: [numdcp: 'L', numfp: '01'] >
	 */
	@Override
	public EOReferensDcp toReferensDcp() {
		EOReferensDcp toReferensDcp = null;

		try {
			toReferensDcp = super.toReferensDcp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReferensDcp;
	}

	/**
	 * Pour eviter les exceptions
	 * er.extensions.eof.ERXDatabaseContextDelegate$ObjectNotAvailableException:
	 * No org.cocktail.feve.eos.modele.grhum.EOReferensFp found with globalID:
	 * <ReferensFp: [numdcp: 'L', numfp: '01'] >
	 */
	@Override
	public String intitulfp() {
		String intitulfp = null;

		try {
			intitulfp = super.intitulfp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return intitulfp;
	}
}
