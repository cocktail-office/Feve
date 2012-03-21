package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.feve.eos.modele.FinderTypeActionFeve;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOFicheLolf extends _EOFicheLolf {

    public EOFicheLolf() {
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
    
    private Boolean voirDetail = new Boolean(false);
    
    public boolean voirDetail()  {
      return voirDetail.booleanValue();
    }
    
    public void setVoirDetail(boolean value) {
      voirDetail = new Boolean(value);
    }
 
    /**
     *
     */
    public boolean hasWarning() {
    	boolean hasWarning = false;
    	
    	// calcul des sommes totales des fonctions silland
	    Double totalSilland = new Double(((Number) tosRepartFloSilland().valueForKey("@sum."+EORepartFloSilland.RFS_QUOTITE_KEY)).doubleValue());
	    if (totalSilland.compareTo(new Double(100)) != 0) {
	    	hasWarning = true;
	    }
    	
	    if (!hasWarning) {
	    	for (int i = 0; i < tosRepartFloSilland().count(); i++) {
		      EORepartFloSilland uneRepart = (EORepartFloSilland) tosRepartFloSilland().objectAtIndex(i);
		      Double totalLolfNomenclature = new Double(((Number)  uneRepart.tosRepartFloLolfNomen().valueForKey("@sum."+EORepartFloLolfNomen.RRF_QUOTITE_KEY)).doubleValue());      
		      if (totalLolfNomenclature.compareTo(new Double(100)) != 0) {
		      	hasWarning = true;
		      }
	    	}
	    }
	    
    	return hasWarning;
    }
    
    /**
     * 
     */
    public String htmlWarnMessage() {
    	
    	String message = "";
    	
    	// calcul des sommes totales des fonctions silland
	    Double totalSilland = new Double(((Number) tosRepartFloSilland().valueForKey("@sum."+EORepartFloSilland.RFS_QUOTITE_KEY)).doubleValue());
	    if (totalSilland.compareTo(new Double(100)) != 0) {
	    	message += "La somme des pourcentages de toutes les fonctions SILLAND n'est pas &eacute;gale &agrave; 100% (ici : <b><font color='red'>" + totalSilland + "</font></b>)<BR><BR>";
	    }
	    
	    // calcul des sous sommes par rapport aux destinations jefycos
	    for (int i = 0; i < tosRepartFloSilland().count(); i++) {
	      EORepartFloSilland uneRepart = (EORepartFloSilland) tosRepartFloSilland().objectAtIndex(i);
	      Double totalLolfNomenclature = new Double(((Number)  uneRepart.tosRepartFloLolfNomen().valueForKey("@sum."+EORepartFloLolfNomen.RRF_QUOTITE_KEY)).doubleValue());      
	      if (totalLolfNomenclature.compareTo(new Double(100)) != 0) {
	      	message += 
	          "Fonction SILLAND : '" + uneRepart.toFctSilland().display() + "' :<br>" +
	          "La somme des pourcentage de toutes les destinations LOLF n'est pas &eacute;gale &agrave; 100% (ici : <font color='red'>" + totalLolfNomenclature + "</font>)" +
	          "<br><br>";   
	      }
	    }
    
    	return message;
    
    }

		/**
		 * Creer une fiche de poste a partir d'une fiche existante
		 * @param fiche : la fiche a dupliquer
		 * @param dDebut
		 * @param dFin
		 * @return
		 * @throws Exception 
		 */
		public static EOFicheLolf newRecordInContextFrom(
				EOFicheLolf fiche, EOExercice exercice) throws Exception {
			EOEditingContext ec = fiche.editingContext();
			
		  // creer le record FicheDePoste
			EOFicheLolf newRecord = newDefaultRecordInContext(ec);
		  newRecord.addObjectToBothSidesOfRelationshipWithKey(fiche.toPoste(), "toPoste");
		
		  // erreur si l'exercice est le meme 
		  if (exercice == fiche.toExercice()) {
		  	throw new Exception("Impossible de créer 2 fiches pour un meme poste sur un meme exercice !");
		  }
		  
		  NSTimestamp dDebutExercice = DateCtrl.stringToDate("01/01/" + exercice.exeExercice().intValue());
			NSTimestamp dFinExercice = DateCtrl.stringToDate("31/12/" + exercice.exeExercice().intValue());
		  newRecord.setFloDDebut(dDebutExercice);
		  newRecord.setFloDFin(dFinExercice);
		
		  newRecord.setToExerciceRelationship(exercice);
			
		  // 
		  NSArray recsRepartFloSilland = fiche.tosRepartFloSilland();
		   for (int i=0; i<recsRepartFloSilland.count(); i++) {
		  	
		  	EORepartFloSilland recRepartFloSilland = (EORepartFloSilland) recsRepartFloSilland.objectAtIndex(i);
		
		  	// est-ce que la fonction silland est associ�e dans le nouvel exercice
		  	if (recRepartFloSilland.toFctSilland().isDeclaree(exercice)) {
		  		
		  		// affectation a la fiche dans le nouvel exercice
		  		EORepartFloSilland newRecRepart = EORepartFloSilland.createRepartFloSilland(
		  				ec, 
		    			DateCtrl.now(), 
		    			DateCtrl.now(), 
		    			recRepartFloSilland.rfsQuotite(), 
		    			recRepartFloSilland.toFctSilland(), 
		    			newRecord);
		    	
		    	
		    	NSArray recsRepartFloLolfNomen = recRepartFloSilland.tosRepartFloLolfNomen();
		
		    	int totalLolfNomenclature = 0;
		    	
		    	for (int j=0; j<recsRepartFloLolfNomen.count() ; j++) {
		    		EORepartFloLolfNomen recRepartFloLolfNomen = (EORepartFloLolfNomen) recsRepartFloLolfNomen.objectAtIndex(j);
		
		      	// la destination est affectee a cette fonction silland dans le nouvel exercice ?
		    		//if (FinderTypeActionFeve.isDeclareeInExercice(ec, recRepartFloLolfNomen.toTypeAction(), exercice, recRepartFloSilland.toFctSilland())) {
		    		if (FinderTypeActionFeve.isDeclareeInExercice(ec, recRepartFloLolfNomen.toLolfNomenclatureDepense(), exercice, recRepartFloSilland.toFctSilland())) {
		      				
		    			EOLolfNomenclatureDepense typeActionInExercice = FinderTypeActionFeve.findTypeActionForExercice(
		    					//ec, recRepartFloLolfNomen.toTypeAction(), exercice);
		    					ec, recRepartFloLolfNomen.toLolfNomenclatureDepense(), exercice);
		  				
		    			/*EORepartFloLolfNomen.createRepartFloLolfNomen(
		    					ec, 
		    					DateCtrl.now(),  
		    					DateCtrl.now(), 
		    					recRepartFloLolfNomen.rrfQuotite(), 
		    					newRecRepart, 
		    					typeActionInExercice);*/
		    			EORepartFloLolfNomen.createRepartFloLolfNomen(
		    					ec, DateCtrl.now(), DateCtrl.now(), recRepartFloLolfNomen.rrfQuotite(), exercice, typeActionInExercice, newRecRepart);
				      		
		    			totalLolfNomenclature++;
		    		}
		    	}
		    	
		    	// on vire l'enregistrement s'il n'y a pas de destination
		    	if (totalLolfNomenclature == 0) {
		    		ec.deleteObject(newRecRepart);
		    	}
		  	}
		  	
		  }
		  return newRecord;
		}

		public static EOFicheLolf newRecordInContext(
		    EOEditingContext ec, 
		    EOPoste poste,
		    EOExercice exercice
		) {
		  EOFicheLolf newRecord = newDefaultRecordInContext(ec);
		  
		  newRecord.setToPosteRelationship(poste);
		  newRecord.setToExerciceRelationship(exercice);
		
		  NSTimestamp dDebutExercice = DateCtrl.stringToDate("01/01/" + exercice.exeExercice().intValue());
			NSTimestamp dFinExercice = DateCtrl.stringToDate("31/12/" + exercice.exeExercice().intValue());
		  newRecord.setFloDDebut(dDebutExercice);
		  newRecord.setFloDFin(dFinExercice);
		
		  return newRecord;
		}

		private static EOFicheLolf newDefaultRecordInContext(EOEditingContext ec) {
		  EOFicheLolf record = new EOFicheLolf();
		  ec.insertObject(record);
		  return record;
		}
    
	  
	  /**
	   * Indique si l'utilisateur possede au moins un des 4 droits d'acces
	   * sur la fiche lolf
	   * @param feveUserInfo : l'utilisateur concerné
	   * @return
	   */
	  public boolean hasAnyDroitAccesOnFicheLolf(FeveUserInfo feveUserInfo) {
	  	boolean result = false;
	  	
	  	result = feveUserInfo.hasAnyDroitAccesOnCible(EOTypeDroitCible.FICHE_LOLF, this, false);
	  	
	  	return result;
	  }
    
}
