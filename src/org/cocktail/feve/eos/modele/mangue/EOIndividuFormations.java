package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOIndividuFormations extends _EOIndividuFormations {

    public EOIndividuFormations() {
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
    	// validation de la classe A_DescriptionFormation
    	super.validateObjectMetier();
    }
    
    /**
     * A appeler par les validateforsave, forinsert, forupdate.
     *
     */
    private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

    }

    
    // ajouts
    
    public final static boolean IS_GESTION_PERIODE	= true;
    public final static boolean IS_GESTION_DUREE 		= true;
    
		/**
		 * Retrouver les formations d'un individu.
		 * Les objets retournes sont issus de l'entite <code>IndividuFormations</code>.
		 * Le classement est inversement chronologique a la date de debut.
		 * @param ec
		 * @param recIndividu 
		 */
		public static NSArray findRecordsInContext(
				EOEditingContext ec, EOIndividu recIndividu) {
			return EOIndividuFormations.fetchIndividuFormationses(
					ec, 
					CktlDataBus.newCondition(
							EOIndividuFormations.TO_INDIVIDU_KEY + " = %@ ",
							new NSArray(recIndividu)),
							CktlSort.newSort(EOIndividuFormations.D_DEB_FORMATION_KEY, CktlSort.Descending));
		}

		@Override
		public String champLibre() {
			return llFormation();
		}

		@Override
		public void setChampLibre(String champLibre) {
			setLlFormation(champLibre);
		}
		
		@Override
		public boolean isGestionPeriode() {
			return IS_GESTION_PERIODE;
		}
		
		@Override
		public NSTimestamp dDebut() {
			return dDebFormation();
		}
		
		@Override
		public void setDDebut(NSTimestamp dDebut) {
			setDDebFormation(dDebut);
		}
		
		@Override
		public NSTimestamp dFin() {
			return dFinFormation();
		}
		
		@Override
		public void setDFin(NSTimestamp dFin) {
			setDFinFormation(dFin);
		}
		
		@Override
		public boolean isGestionDuree() {
			return IS_GESTION_DUREE;
		}
		
}
