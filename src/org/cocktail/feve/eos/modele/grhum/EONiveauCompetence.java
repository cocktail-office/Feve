// EONiveauCompetence.java
// Created on Wed Apr 27 12:31:05  2005 by Apple EOModeler Version 5.2

package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;


public class EONiveauCompetence extends _EONiveauCompetence {

  public EONiveauCompetence() {
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

	
	
	// ajouts
	
	/**
	 * Donne la liste des {@link EONiveauCompetence} potentiels 
	 * utilisable pour la periode passée en paramètre. On fait
	 * un croisement avec les dates de validités.
	 */
	public static NSArray getNiveauCompetenceForPeriode(EOEvaluationPeriode periode) {
		NSArray result = null;
		
		// 
		EOQualifier qual = CktlDataBus.newCondition(
				"(" + D_DEB_VAL_KEY + "<=%@ and ("+D_FIN_VAL_KEY+">=%@ or "+D_FIN_VAL_KEY+"=nil )) or " +
				"(" + D_DEB_VAL_KEY + "<=%@ and ("+D_FIN_VAL_KEY+">=%@ or "+D_FIN_VAL_KEY+"=nil )) or " +
				"(" + D_DEB_VAL_KEY + ">=%@ and "+D_FIN_VAL_KEY+"<=%@ )",
				new NSArray(new NSTimestamp[] {
						periode.epeDDebut(), periode.epeDDebut(),
						periode.epeDFin(), periode.epeDFin(),
						periode.epeDDebut(), periode.epeDFin()}));

		result = fetchNiveauCompetences(
				periode.editingContext(), qual, CktlSort.newSort(NCP_POSITION_KEY));
		
		return result;
	}
	
}
