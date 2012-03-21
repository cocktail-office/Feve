package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSValidation;


public class EOReferensActivites extends _EOReferensActivites implements I_PkVisible {

    public EOReferensActivites() {
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

    
    // METHODES RAJOUTEES

    private final static int LIBELLE_TAILLE_MAX = 120;

    public String display() {
        return displayCourt();
    }
    
    /**
     * affichage des LIBELLE_TAILLE_MAX premiers caracteres
     */
    public String displayCourt() {
    	String display = intitulactivite();
    	if (libelleTailleMaxDepassee()) {
    		display = display.substring(0,LIBELLE_TAILLE_MAX-6) + " (...)";
    	}
       return display;
    }
    
    public String displayLong() {
        return intitulactivite();
    }
    
    public boolean libelleTailleMaxDepassee() {
        return intitulactivite().length() > LIBELLE_TAILLE_MAX;
    }
    
    public String popup() {
      return "poplink('" + StringCtrl.replace(displayLong(), "'", " ") + "');";
    }

    public String popupWs() {
      return "Tip('" + StringCtrl.replace(displayLong(), "'", " ") + "');";
    }
    
    /**
     * Le compos� de la cl� primaire
     */
    public String id() {
    	NSDictionary dico = EOUtilities.primaryKeyForObject(editingContext(), this);
    	return ENTITY_NAME + "-" + dico.valueForKey("codeemploi") + "-_-" + dico.valueForKey("ordre");
    }
    
}
