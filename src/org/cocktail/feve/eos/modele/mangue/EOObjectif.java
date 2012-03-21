package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOMessage;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOObjectif extends _EOObjectif {

    public EOObjectif() {
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

		@Override
		public NSArray othersRecords() {
			return toEvaluation().tosObjectif();
		}

		@Override
		public String positionKey() {
			return OBJ_POSITION_KEY;
		}
		
		// affichage des sauts de ligne en HTML
		
		private static String getHtmlValue(String value) {
			return StringCtrl.replace(WOMessage.stringByEscapingHTMLAttributeValue(value), "&#10;", "<br>");
		}
		
		public String objMesureHtml() {
			return getHtmlValue(super.objMesure());
		}
		
		public String objMoyenHtml() {
			return getHtmlValue(super.objMoyen());
		}
		
		public String objObjectifHtml() {
			return getHtmlValue(super.objObjectif());
		}
		
		public String objObservationHtml() {
			return getHtmlValue(super.objObservation());
		}
		
		public String objResultatHtml() {
			return getHtmlValue(super.objResultat());
		}

		public static EOObjectif newRecordInContext(
				EOEditingContext ec, EOEvaluation evaluation, String objectif, String moyen, String mesure, String resultat, String observation) {
			EOObjectif newRecord = newDefaultRecordInContext(ec);
			newRecord.addObjectToBothSidesOfRelationshipWithKey(evaluation, "toEvaluation");
			newRecord.setObjObjectif(objectif);
			newRecord.setObjMoyen(moyen);
			newRecord.setObjMesure(mesure);
			newRecord.setObjResultat(resultat);
			newRecord.setObjObservation(observation);
			Number maxPos = (Number) evaluation.tosObjectif().valueForKey("@max." + EOObjectif.OBJ_POSITION_KEY);
			if (maxPos == null) {
				maxPos = new Integer(0);
			}
			newRecord.setObjPosition(new Integer(maxPos.intValue() + 1));
			return newRecord;
		}

		private static EOObjectif newDefaultRecordInContext(EOEditingContext ec) {
		    EOObjectif record = new EOObjectif();
		    ec.insertObject(record);
		    return record;
		}
		
		private Number objKey;
		
		/**
		 * La clé primaire pour avoir un identifiant unique (pour faire
		 * des ancres html par exemple ...)
		 * @return
		 */
		private Number objKey() {
			if (objKey == null) {
				objKey = (Number) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("objKey");
			}
			return objKey;
		}
		
		/**
		 * Ancre HTML associée à cet objectif
		 * @return
		 */
		public String ancre() {
			return ENTITY_NAME + "_" + objKey().intValue();
		}
}
