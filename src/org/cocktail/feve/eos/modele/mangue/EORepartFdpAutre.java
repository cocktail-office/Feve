package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.I_PkVisible;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSValidation;


public class EORepartFdpAutre 
	extends _EORepartFdpAutre 
		implements I_PkVisible, I_RepartCompetence {

	public final static String TYPE_ACTIVITE = "A";
	public final static String TYPE_COMPETENCE = "C";
	
    public EORepartFdpAutre() {
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
      NSArray lesAutresRepart = toFicheDePoste().tosRepartFdpAutre();
      if (lesAutresRepart.count() > 0) {
        Number position = (Number) lesAutresRepart.valueForKey("@max."+FAU_POSITION_KEY);
        if (position != null) {
          setFauPosition(new Integer(position.intValue() + 1));
        }
      }
      if (fauPosition() == null) {
        setFauPosition(new Integer(1));
      }
    }
    
    /**
     * Le composé de la clé primaire
     */
    public String id() {
    	NSDictionary dico = EOUtilities.primaryKeyForObject(editingContext(), this);
    	return ENTITY_NAME + "-" + dico.valueForKey("fauKey");
    }

		@Override
		public NSArray othersRecords() {
			NSArray othersRecords = null;
			
			if (fauType().equals(TYPE_ACTIVITE)) {
				othersRecords = toFicheDePoste().tosRepartFdpActivitesAutres();
			} else if (fauType().equals(TYPE_COMPETENCE)) {
				othersRecords = toFicheDePoste().tosRepartFdpCompetencesAutres();
			}
			
			return othersRecords;
		}

		@Override
		public String positionKey() {
			return FAU_POSITION_KEY;
		}

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

		public String competenceDisplay() {
			return fauChampLibre(); 
		}

		private static EORepartFdpAutre newDefaultRecordInContext(EOEditingContext ec) {
		  EORepartFdpAutre record = new EORepartFdpAutre();
		  ec.insertObject(record);
		  return record;
		}

		public static EORepartFdpAutre newRecordInContext(
		    EOEditingContext ec, 
		    EOFicheDePoste ficheDePoste,
		    String texteChampLibre,
		    String type
		) {
		  EORepartFdpAutre newRecord = EORepartFdpAutre.newDefaultRecordInContext(ec);
		  
		  newRecord.setFauChampLibre(texteChampLibre);
		  newRecord.setFauType(type);
		  /*ficheDePoste.addObjectToBothSidesOfRelationshipWithKey(
		  		newRecord, EOFicheDePoste.TOS_REPART_FDP_AUTRE_KEY);
		    */
		  
		  ficheDePoste.addToTosRepartFdpAutreRelationship(newRecord);
		  
		  return newRecord;
		}
}
