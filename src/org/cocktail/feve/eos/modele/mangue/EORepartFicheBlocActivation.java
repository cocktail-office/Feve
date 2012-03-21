package org.cocktail.feve.eos.modele.mangue;

import java.util.NoSuchElementException;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EORepartFicheBlocActivation extends _EORepartFicheBlocActivation {

    public EORepartFicheBlocActivation() {
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
		 * TODO faire generique recFiche : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
		 * Determiner si un bloc a ete active ou pas.
		 * @param ec
		 * @param strTplBloc : le libelle du bloc facultatif
		 * @param recEvaluation : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
		 * @return <em>null</em> si non trouv�.
		 */
    public static boolean isActif(
				EOEditingContext ec, String strTplBloc, EOEvaluation recEvaluation) {
			try {
				EORepartFicheBlocActivation.fetchRequiredRepartFicheBlocActivation(
						ec, CktlDataBus.newCondition(
									EORepartFicheBlocActivation.TO_TPL_BLOC_KEY+"."+EOTplBloc.TBL_CODE_KEY + " = %@ and " + EORepartFicheBlocActivation.TO_EVALUATION_KEY + " = %@",
								new NSArray(new Object[]{strTplBloc, recEvaluation})));
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}

}
