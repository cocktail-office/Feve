package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOStage extends _EOStage {

	public EOStage() {
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
	 * liste des stage d'un individu pour une periode donnee
	 * 
	 * @param ec
	 * @param individu
	 * @param date
	 * @return
	 */
	public static NSArray<EOStage> findStageForIndividuAndDateInContext(EOEditingContext ec, EOIndividu individu, NSTimestamp dateRef) {
		String strQual = null;
		NSArray args = null;/*
												 * if (dateFin != null) strQual =
												 * "toIndividu = %@ AND (" +
												 * "(dDebStage <= %@ AND dFinStage = nil) OR" +
												 * "(dDebStage >= %@ AND dFinStage <= %@) OR" +
												 * "(dDebStage <= %@ AND dFinStage >= %@) OR" +
												 * "(dDebStage <= %@ AND dFinStage >= %@ AND dFinStage <= %@) OR"
												 * +
												 * "(dDebStage >= %@ AND dDebStage <= %@ AND dFinStage >= %@) OR"
												 * +
												 * "(dDebStage >= %@ AND dDebStage <= %@ AND dFinStage = nil)"
												 * + ")"; args = new NSArray(new Object[]{ individu,
												 * dateDebut, dateDebut, dateFin, dateDebut, dateFin,
												 * dateDebut, dateDebut, dateFin, dateDebut, dateFin,
												 * dateFin, dateDebut, dateFin}); } else { strQual =
												 * "toIndividu = %@ AND (" + "(dFinStage = nil) OR" +
												 * "(dDebStage <= %@ AND dFinStage >= %@) OR" +
												 * "(dDebStage >= %@ AND dFinStage >= %@)" + ")"; args =
												 * new NSArray(new Object[]{ individu, dateDebut,
												 * dateDebut, dateDebut, dateDebut}); }
												 */
		strQual = "toIndividu = %@ AND dDebStage <= %@ AND (dFinStage >= %@ OR dFinStage = nil)";
		args = new NSArray(new Object[] { individu, dateRef, dateRef });
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);
		return UtilDb.fetchArray(ec, "Stage", qual, null);
	}

}
