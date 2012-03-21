package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.I_PkVisible;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSValidation;

public class EORepartEvaNouvelleComp extends _EORepartEvaNouvelleComp implements I_PkVisible {

    public EORepartEvaNouvelleComp() {
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


    /**
     * Le compose de la cle primaire
     */
    public String id() {
    	NSDictionary dico = EOUtilities.primaryKeyForObject(editingContext(), this);
    	return ENTITY_NAME + "-" + dico.valueForKey("rncKey");
    }

		@Override
		@Deprecated
		public NSArray othersRecords() {
			return toEvaluation().tosRepartEvaNouvelleComp();
		}

		@Override
		@Deprecated
		public String positionKey() {
			return "";
		}

		private static EORepartEvaNouvelleComp newDefaultRecordInContext(EOEditingContext ec) {
		  EORepartEvaNouvelleComp record = new EORepartEvaNouvelleComp();
		  ec.insertObject(record);
		  return record;
		}

		public static EORepartEvaNouvelleComp newRecordInContext(
		    EOEditingContext ec, 
		    EOEvaluation evaluation,
		    EOReferensCompetences competence
		) {
		  EORepartEvaNouvelleComp newRecord = EORepartEvaNouvelleComp.newDefaultRecordInContext(ec);
		  
		  newRecord.addObjectToBothSidesOfRelationshipWithKey(evaluation,       EORepartEvaNouvelleComp.TO_EVALUATION_KEY);
		  newRecord.addObjectToBothSidesOfRelationshipWithKey(competence,       EORepartEvaNouvelleComp.TO_REFERENS_COMPETENCES_KEY);
		    
		  return newRecord;
		}
}
