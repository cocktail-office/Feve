package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOContratAvenant extends _EOContratAvenant {

	public EOContratAvenant() {
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
	public static NSArray<EOContratAvenant> findSortedContratForIndividuAndDateInContext(EOEditingContext ec, EOIndividu individu, NSTimestamp dateDebut, NSTimestamp dateFin) {
		String strQual = null;
		NSArray args = null;
		if (dateFin != null) {
			strQual =
					"toContrat.toIndividu = %@ AND (" +
							"(dDebContratTravAv <= %@ AND dFinContratTravAv = nil) OR" +
							"(dDebContratTravAv >= %@ AND dFinContratTravAv <= %@) OR" +
							"(dDebContratTravAv <= %@ AND dFinContratTravAv >= %@) OR" +
							"(dDebContratTravAv <= %@ AND dFinContratTravAv >= %@ AND dFinContratTravAv <= %@) OR" +
							"(dDebContratTravAv >= %@ AND dDebContratTravAv <= %@ AND dFinContratTravAv >= %@) OR" +
							"(dDebContratTravAv >= %@ AND dDebContratTravAv <= %@ AND dFinContratTravAv = nil)" +
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
						"toContrat.toIndividu = %@ AND (" +
								"(dFinContratTravAv = nil) OR" +
								"(dDebContratTravAv <= %@ AND dFinContratTravAv >= %@) OR" +
								"(dDebContratTravAv >= %@ AND dFinContratTravAv >= %@)" +
								")";
			args = new NSArray(new Object[] { individu, dateDebut, dateDebut, dateDebut, dateDebut });
		}
		NSArray sortOrderings = new NSArray(EOSortOrdering.sortOrderingWithKey("dFinContratTravAv", EOSortOrdering.CompareAscending));
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);
		return UtilDb.fetchArray(ec, EOContratAvenant.ENTITY_NAME, qual, sortOrderings);
	}

}
