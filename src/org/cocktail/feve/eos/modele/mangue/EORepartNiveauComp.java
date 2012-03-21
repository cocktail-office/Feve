package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EORepartNiveauComp extends _EORepartNiveauComp {

    public EORepartNiveauComp() {
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

    
    // ajouts
    
    /**
     * Constructeur simplifié
     */
    public static EORepartNiveauComp create(
    		EOEditingContext editingContext, EOEvaluation toEvaluation, EORepartFdpAutre toRepartFdpAutre, EORepartFdpComp toRepartFdpComp) {
    	NSTimestamp now = DateCtrl.now();
    	EORepartNiveauComp repartNiveauComp = createRepartNiveauComp(
    			editingContext, now, now, toEvaluation);
    	repartNiveauComp.setToRepartFdpAutreRelationship(toRepartFdpAutre);
    	repartNiveauComp.setToRepartFdpCompRelationship(toRepartFdpComp);
    	return repartNiveauComp;
    }
}
