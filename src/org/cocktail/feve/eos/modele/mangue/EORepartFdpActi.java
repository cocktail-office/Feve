package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;



public class EORepartFdpActi extends _EORepartFdpActi {

    public EORepartFdpActi() {
        super();
    }

    public void validateForInsert() throws NSValidation.ValidationException {
    	setDCreation(DateCtrl.now());
    	this.validateObjectMetier();
    	validateBeforeTransactionSave();
    	super.validateForInsert();
    	checkRfaPosition();
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

    
    // Methodes rajoutees
    
    private void checkRfaPosition() {
    	// on met la position max par defaut
    	NSArray lesAutresRepart = toFicheDePoste().tosRepartFdpActi();
    	if (lesAutresRepart.count() > 0) {
    		Number position = (Number) lesAutresRepart.valueForKey("@max."+RFA_POSITION_KEY);
    		if (position != null) {
    			setRfaPosition(new Integer(position.intValue() + 1));
    		}
    	}
    	if (rfaPosition() == null) {
    		setRfaPosition(new Integer(1));
    	}
    }

		@Override
		public NSArray othersRecords() {
			return toFicheDePoste().tosRepartFdpActi();
		}

		@Override
		public String positionKey() {
			return RFA_POSITION_KEY;
		}

		private static EORepartFdpActi newDefaultRecordInContext(EOEditingContext ec) {
		  EORepartFdpActi record = new EORepartFdpActi();
		  ec.insertObject(record);
		  record.setDCreation(DateCtrl.now());
		  record.setDModification(DateCtrl.now());
		  return record;
		}

		/**
		 * Associer une activite a une fiche de poste
		 * @param ec
		 * @param ficheDePoste
		 * @param activite
		 * @return
		 */
		public static EORepartFdpActi newRecordInContext(
		    EOEditingContext ec, 
		    EOFicheDePoste ficheDePoste,
		    EOReferensActivites activite
		) {
		  EORepartFdpActi newRecord = EORepartFdpActi.newDefaultRecordInContext(ec);
		  
		  newRecord.setToReferensActivitesRelationship(activite);
		  ficheDePoste.addToTosRepartFdpActiRelationship(newRecord);
		  
		 // newRecord.addObjectToBothSidesOfRelationshipWithKey(activite,           EORepartFdpActi.TO_REFERENS_ACTIVITES_KEY);
		//  ficheDePoste.addObjectToBothSidesOfRelationshipWithKey(newRecord,       EOFicheDePoste.TOS_REPART_FDP_ACTI_KEY);
		
		  return newRecord;
		}
    
}
