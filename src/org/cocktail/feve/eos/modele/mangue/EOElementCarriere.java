package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOElementCarriere extends _EOElementCarriere {

	public EOElementCarriere() {
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
	private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

	}

	/**
	 * liste des elements de carriere d'un individu pour une periode donnee
	 * 
	 * @param ec
	 * @param individu
	 * @param date
	 * @return
	 */
	public static NSArray<EOElementCarriere> findSortedElementCarriereForIndividuAndDateInContext(EOEditingContext ec, EOIndividu individu, NSTimestamp dateDebut, NSTimestamp dateFin) {
		// String strQual =
		// "toIndividu = %@ AND ((dFinElement = nil) or ((dEffetElement < %@) AND (dFinElement > %@)) ";
		// strQual +=
		// "or ((dFinElement >= %@) AND (dFinElement <= %@)) or ((dEffetElement >= %@) and (dEffetElement <= %@))))";
		// NSArray args = new NSArray(new Object[]{individu, dateDebut, dateFin,
		// dateDebut, dateFin, dateDebut, dateFin});

		String strQual = null;
		NSArray args = null;
		if (dateFin != null) {
			strQual =
					"toIndividu = %@ AND (" +
							"(dEffetElement <= %@ AND dFinElement = nil) OR" +
							"(dEffetElement >= %@ AND dFinElement <= %@) OR" +
							"(dEffetElement <= %@ AND dFinElement >= %@) OR" +
							"(dEffetElement <= %@ AND dFinElement >= %@ AND dFinElement <= %@) OR" +
							"(dEffetElement >= %@ AND dEffetElement <= %@ AND dFinElement >= %@) OR" +
							"(dEffetElement >= %@ AND dEffetElement <= %@ AND dFinElement = nil)" +
							")";
			args = new NSArray(new Object[] {
						individu,
						dateDebut,
						dateDebut, dateFin,
						dateDebut, dateFin,
						dateDebut, dateDebut, dateFin,
						dateDebut, dateFin, dateFin,
						dateDebut, dateFin });
		} else {
			strQual =
						"toIndividu = %@ AND (" +
								"(dFinElement = nil) OR" +
								"(dEffetElement <= %@ AND dFinElement >= %@) OR" +
								"(dEffetElement >= %@ AND dFinElement >= %@)" +
								")";
			args = new NSArray(new Object[] { individu, dateDebut, dateDebut, dateDebut, dateDebut });
		}

		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);
		NSArray sortOrderings = new NSArray(EOSortOrdering.sortOrderingWithKey("dFinElement", EOSortOrdering.CompareAscending));
		return UtilDb.fetchArray(ec, EOElementCarriere.ENTITY_NAME, qual, sortOrderings);
	}

}
