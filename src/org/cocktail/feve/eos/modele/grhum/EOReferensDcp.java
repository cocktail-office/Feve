package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOReferensDcp extends _EOReferensDcp {

    public EOReferensDcp() {
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
     * Apparemment cette methode n'est pas appel̩e.
     * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
     */    
    public void validateForSave() throws NSValidation.ValidationException {
        validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForSave();
        
    }

    /**
     * Peut etre appele �� partir des factories.
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

    /**
     * Avec les changement de bap, les lettres ne correspondent plus ...
     */
    private String deductedLettreBap() {
    	if (lettrebap().equals(numdcp())) {
    		return lettrebap();
    	} else {
    		if (numdcp().length() == 1) {
    			return lettrebap();
    		} else {
    			return numdcp().substring(0, 1);
    		}
    	}
    }
    
    public String display() {
    	String display = deductedLettreBap() + " - " + StringCtrl.capitalizeWords(intituldcp());
    	if (isArchive()) {
    		display = "___NE PLUS UTILISER__" + display;
    	}
    	return display;
    }
 
    // liste des cle primaire des bap desactivees
    public final static NSArray LIST_NUMDCP_ARCHIVE = new NSArray(new String[] {
    		"A","B","C","D","E","F","G","H","I"});
    
    /**
     * Indique si la DCP est une DCP archivee ou non.
     * @return
     */
    public boolean isArchive() {
    	return LIST_NUMDCP_ARCHIVE.containsObject(numdcp());
    }
    
    /**
     * 
     * @return
     */
    public String lettrebap() {
    	if (numdcp() == null) {
    		return "PRRRRRRRRRRRRRRRRRRRROUT";
    	}
    	if (numdcp() != null) {
      	if (numdcp().equals("BB")) return "B";
      	if (numdcp().equals("CC")) return "C";
    	}
    	return super.lettrebap();
    }
}
