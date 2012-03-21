package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOFctSilland extends _EOFctSilland {

    public EOFctSilland() {
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

    // methodes rajoutees
    
    public String display() {
      return silLibelle();
    }

    /**
     * Les repartition associées à un exercice comptable
     * @param exercice
     * @return
     */
    public NSArray tosRepartSilLolf(EOExercice exercice) {
    	return tosRepartSilLolf(
    			CktlDataBus.newCondition(
    					EORepartSillandLolf.TO_EXERCICE_KEY + "=%@",
    					//EORepartSillandLolf.TO_TYPE_ACTION_KEY + "." + EOTypeAction.EXERCICE_KEY + "=%@",
    					new NSArray(exercice)));
    }
    
    /**
     * Les destination associ�es a un exercice comptable
     * @param exercice
     * @return
     */
    public NSArray tosTypeAction(EOExercice exercice) {
    	//return (NSArray) tosRepartSilLolf(exercice).valueForKey(EORepartSillandLolf.TO_TYPE_ACTION_KEY);
    	return (NSArray) tosRepartSilLolf(exercice).valueForKey(EORepartSillandLolf.TO_LOLF_NOMENCLATURE_DEPENSE_KEY);
    }
    
    /**
     * Indique si la fonction est affectee dans l'exercice.
     * @param exercice
     * @return
     */
    public boolean isDeclaree(EOExercice exercice) {
    	return tosRepartSilLolf(
    			CktlDataBus.newCondition(
    					//EORepartSillandLolf.TO_TYPE_ACTION_KEY + "." + EOTypeAction.EXERCICE_KEY + "=%@",
    					EORepartSillandLolf.TO_EXERCICE_KEY + "=%@",
    					new NSArray(exercice))).count() > 0;
    }
    
    public boolean isEnseignant() {
    	return silEnseignant().equals(OUI);
    }
    
    public boolean isNonEnseignant() {
    	return silNonEnseignant().equals(OUI);
    }
    
    public void setIsEnseignant(boolean value) {
    	if (value) {
    		setSilEnseignant(OUI);
    	} else {
    		setSilEnseignant(NON);
    	}
    }
    
    public void setIsNonEnseignant(boolean value) {
    	if (value) {
    		setSilNonEnseignant(OUI);
    	} else {
    		setSilNonEnseignant(NON);
    	}
    }

		/**
		 * liste de toutes les fonction silland declarees pour un exercice et pour un type de poste
		 * @param ec
		 * @return
		 */
		public static NSArray findAllFctSillandExerciceInContext(EOEditingContext ec, EOExercice exercice, EOPoste poste) {
			//String strQual = EORepartSillandLolf.TO_TYPE_ACTION_KEY + "." + EOTypeAction.EXERCICE_KEY +  "=%@";
			String strQual = EORepartSillandLolf.TO_EXERCICE_KEY +  "=%@";
			if (poste.isEnseignant()) {
				strQual += " and " + EORepartSillandLolf.TO_FCT_SILLAND_KEY + "." + EOFctSilland.SIL_ENSEIGNANT_KEY + "='" + OUI + "'";
			} else {
				strQual += " and " + EORepartSillandLolf.TO_FCT_SILLAND_KEY + "." + EOFctSilland.SIL_NON_ENSEIGNANT_KEY + "='" + OUI + "'";
			}
			NSArray recsRepartSillandLolf = EORepartSillandLolf.fetchRepartLolfSillands(
					ec, 
					CktlDataBus.newCondition(strQual, new NSArray(exercice)), 
					CktlSort.newSort(
							EORepartSillandLolf.TO_FCT_SILLAND_KEY + "." + EOFctSilland.SIL_LIBELLE_KEY));
			return NSArrayCtrl.removeDuplicate((NSArray) recsRepartSillandLolf.valueForKey(EORepartSillandLolf.TO_FCT_SILLAND_KEY));
		}

		/**
		 * liste de toutes les fonction silland
		 * @param ec
		 * @return
		 */
		public static NSArray findAllFctSillandInContext(EOEditingContext ec) {
			return UtilDb.fetchArray(ec, EOFctSilland.ENTITY_NAME, null, CktlSort.newSort(EOFctSilland.SIL_LIBELLE_KEY));
		}
}
