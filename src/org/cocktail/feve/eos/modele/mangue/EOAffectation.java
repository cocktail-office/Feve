package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.foundation.NSValidation;


public class EOAffectation extends _EOAffectation {

    public EOAffectation() {
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



    
    // methodes rajoutee
    
    public String display() {
      String display = toIndividu().display() + " ";
      if (dFinAffectation() != null) {
        display += "du " + DateCtrl.dateToString(dDebAffectation()) + " au " + DateCtrl.dateToString(dFinAffectation());
      } else {
        display += "a partir du " + DateCtrl.dateToString(dDebAffectation());        
      }
      display += " (" + numQuotAffectation() + "%)";
      return display;
    }
}
