package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EORepartFicheItem 
	extends _EORepartFicheItem 
		implements I_RepartFormation, I_ToEvaluation {

    public EORepartFicheItem() {
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
    
		public String libelleFormation() {
			return rfiValeurLibre();
		}

		public boolean isNomenclature() {
			return false;
		}

		/**
		 * TODO faire generique recFiche : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
		 * Retrouver l'enregistrement associe a une fiche pour un item
		 * @param ec
		 * @param itemCode : le code du type de l'item
		 * @param recEvaluation : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
		 * @return <em>null</em> si non trouvÈ.
		 */
		public static EORepartFicheItem findRecordForItemCodeInContext(
				EOEditingContext ec, String itemCode, EOEvaluation recEvaluation) {
			EORepartFicheItem result = null;

			result = EORepartFicheItem.fetchRepartFicheItem(
						ec, CktlDataBus.newCondition(
								EORepartFicheItem.TO_TPL_ITEM_KEY+"."+EOTplItem.TIT_CODE_KEY+" = %@ AND "+ EORepartFicheItem.TO_EVALUATION_KEY + " = %@",
								new NSArray<Object>(
										new Object[]{
												itemCode, recEvaluation})));
		
			return result;
		}
}
