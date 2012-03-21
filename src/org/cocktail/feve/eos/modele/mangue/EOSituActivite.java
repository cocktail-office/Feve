package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSValidation;

public class EOSituActivite extends _EOSituActivite {

    public EOSituActivite() {
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
     * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
     */    
    public void validateForSave() throws NSValidation.ValidationException {
        validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForSave();
        
    }

    /**
     * Peut etre appele a partir des factories.
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
    
    // formulaire de saisie des objectifs : un seul modifiable a la fois
    
    private Boolean isEnCoursDeModification;
    
    public boolean isEnCoursDeModification() {
    	if (isEnCoursDeModification == null) {
    		isEnCoursDeModification = new Boolean(false);
    	}
    	return isEnCoursDeModification.booleanValue();
    }
    
    public void setIsEnCoursDeModification(boolean value) {
    	isEnCoursDeModification = new Boolean(value);
    }

		private static EOSituActivite newDefaultRecordInContext(EOEditingContext ec) {
		    EOSituActivite record = new EOSituActivite();
		    ec.insertObject(record);
		    return record;
		}

		public static EOSituActivite newRecordInContext(EOEditingContext ec, EOEvaluation evaluation, String situation) {
		    EOSituActivite newRecord = EOSituActivite.newDefaultRecordInContext(ec);
		    newRecord.addObjectToBothSidesOfRelationshipWithKey(evaluation, "toEvaluation");
		    newRecord.setSacSituation(situation);
		    return newRecord;
		}
}
