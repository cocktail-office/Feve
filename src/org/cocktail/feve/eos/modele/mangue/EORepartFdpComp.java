package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;


public class EORepartFdpComp 
	extends _EORepartFdpComp
		implements I_RepartCompetence {

    public EORepartFdpComp() {
        super();
    }

    public void validateForInsert() throws NSValidation.ValidationException {
    	setDCreation(DateCtrl.now());
        this.validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForInsert();
        checkRfcPosition();
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
    
    private void checkRfcPosition() {
    	// on met la position max par defaut
      NSArray lesAutresRepart = toFicheDePoste().tosRepartFdpComp();
      if (lesAutresRepart.count() > 0) {
        Number position = (Number) lesAutresRepart.valueForKey("@max."+RFC_POSITION_KEY);
        if (position != null) {
          setRfcPosition(new Integer(position.intValue() + 1));
        }
      } 
      if (rfcPosition() == null) {
      	setRfcPosition(new Integer(1));
      }
    }

		@Override
		public NSArray othersRecords() {
			return toFicheDePoste().tosRepartFdpComp();
		}

		@Override
		public String positionKey() {
			return RFC_POSITION_KEY;
		}

		public String competenceDisplay() {
			return toReferensCompetences().displayLong();
		}

		private static EORepartFdpComp newDefaultRecordInContext(EOEditingContext ec) {
		  EORepartFdpComp record = new EORepartFdpComp();
		  ec.insertObject(record);
		  return record;
		}

		public static EORepartFdpComp newRecordInContext(
		    EOEditingContext ec, 
		    EOFicheDePoste ficheDePoste,
		    EOReferensCompetences competence
		) {
			EORepartFdpComp newRecord = EORepartFdpComp.newDefaultRecordInContext(ec);
		  
			newRecord.setToReferensCompetencesRelationship(competence);
			ficheDePoste.addToTosRepartFdpCompRelationship(newRecord);
			
		  //newRecord.addObjectToBothSidesOfRelationshipWithKey(competence,         EORepartFdpComp.TO_REFERENS_COMPETENCES_KEY);
		  //ficheDePoste.addObjectToBothSidesOfRelationshipWithKey(newRecord,       EOFicheDePoste.TOS_REPART_FDP_COMP_KEY);
		
		  return newRecord;
		}
    
    
}
