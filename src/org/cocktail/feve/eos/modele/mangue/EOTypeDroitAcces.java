/*
 * Copyright Cocktail, 2001-2008 
 * 
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use, 
 * modify and/or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and, more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSValidation;

public class EOTypeDroitAcces extends _EOTypeDroitAcces {

 public EOTypeDroitAcces() {
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

  
  // ajout
  
	// les valeur du code pour chaque acces
	public final static String CREATION 				= "CRE";
	public final static String MODIFICATION 		= "MAJ";
	public final static String SUPPRESSION 			= "SUP";
	public final static String VISUALISATION 		= "VIS";
	
	public final static String[] CODE_TYPE_DROIT_ACCES_LIST = new String[] {
		CREATION, MODIFICATION, SUPPRESSION, VISUALISATION};
	
	public static EOTypeDroitAcces typeDroitAccesCreation(EOEditingContext ec) {
		return fetchRequiredTypeDroitAcces(ec, DTA_CODE_KEY, CREATION);
	}

	public static EOTypeDroitAcces typeDroitAccesModification(EOEditingContext ec) {
		return fetchRequiredTypeDroitAcces(ec, DTA_CODE_KEY, MODIFICATION);
	}

	public static EOTypeDroitAcces typeDroitAccesSuppression(EOEditingContext ec) {
		return fetchRequiredTypeDroitAcces(ec, DTA_CODE_KEY, SUPPRESSION);
	}

	public static EOTypeDroitAcces typeDroitAccesVisualisation(EOEditingContext ec) {
		return fetchRequiredTypeDroitAcces(ec, DTA_CODE_KEY, VISUALISATION);
	}
	
	public static boolean isTypeDroitAccesCreation(EOTypeDroitAcces value) {
		return value.dtaCode().equals(CREATION);
	}
	
	public static boolean isTypeDroitAccesModification(EOTypeDroitAcces value) {
		return value.dtaCode().equals(MODIFICATION);
	}
	
	public static boolean isTypeDroitAccesSuppression(EOTypeDroitAcces value) {
		return value.dtaCode().equals(SUPPRESSION);
	}
	
	public static boolean isTypeDroitAccesVisualisation(EOTypeDroitAcces value) {
		return value.dtaCode().equals(VISUALISATION);
	}
}
