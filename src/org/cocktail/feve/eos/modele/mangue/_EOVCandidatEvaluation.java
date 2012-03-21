// _EOVCandidatEvaluation.java
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

// DO NOT EDIT.  Make changes to EOVCandidatEvaluation.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOVCandidatEvaluation extends A_EOEvaluationKeyValueCoding  {
	public static final String ENTITY_NAME = "VCandidatEvaluation";
	public static final String ENTITY_TABLE_NAME = "MANGUE.V_CANDIDAT_EVALUATION";

	// Attributes
	public static final String EPE_KEY_VISIBLE_KEY = "epeKeyVisible";
	public static final String EVA_KEY_VISIBLE_KEY = "evaKeyVisible";
	public static final String NO_INDIVIDU_VISIBLE_KEY = "noIndividuVisible";
	public static final String NOM_USUEL_KEY = "nomUsuel";
	public static final String PRENOM_KEY = "prenom";

	public static final String EPE_KEY_VISIBLE_COLKEY = "epeKey";
	public static final String EVA_KEY_VISIBLE_COLKEY = "evaKey";
	public static final String NO_INDIVIDU_VISIBLE_COLKEY = "noIndividu";
	public static final String NOM_USUEL_COLKEY = "NOM_USUEL";
	public static final String PRENOM_COLKEY = "PRENOM";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_EVALUATION_PERIODE_KEY = "toEvaluationPeriode";
	public static final String TO_INDIVIDU_KEY = "toIndividu";

	// Utilities methods
  public EOVCandidatEvaluation localInstanceIn(EOEditingContext editingContext) {
    EOVCandidatEvaluation localInstance = (EOVCandidatEvaluation)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public Integer epeKeyVisible() {
    return (Integer) storedValueForKey("epeKeyVisible");
  }

  public void setEpeKeyVisible(Integer value) {
    takeStoredValueForKey(value, "epeKeyVisible");
  }

  public Integer evaKeyVisible() {
    return (Integer) storedValueForKey("evaKeyVisible");
  }

  public void setEvaKeyVisible(Integer value) {
    takeStoredValueForKey(value, "evaKeyVisible");
  }

  public Integer noIndividuVisible() {
    return (Integer) storedValueForKey("noIndividuVisible");
  }

  public void setNoIndividuVisible(Integer value) {
    takeStoredValueForKey(value, "noIndividuVisible");
  }

  public String nomUsuel() {
    return (String) storedValueForKey("nomUsuel");
  }

  public void setNomUsuel(String value) {
    takeStoredValueForKey(value, "nomUsuel");
  }

  public String prenom() {
    return (String) storedValueForKey("prenom");
  }

  public void setPrenom(String value) {
    takeStoredValueForKey(value, "prenom");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluation)storedValueForKey("toEvaluation");
  }

  public void setToEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluation oldValue = toEvaluation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluation");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode)storedValueForKey("toEvaluationPeriode");
  }

  public void setToEvaluationPeriodeRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode oldValue = toEvaluationPeriode();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluationPeriode");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluationPeriode");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividu");
  }

  public void setToIndividuRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividu();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividu");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividu");
    }
  }
  

  public static EOVCandidatEvaluation createVCandidatEvaluation(EOEditingContext editingContext, Integer epeKeyVisible
, Integer evaKeyVisible
, String nomUsuel
, String prenom
, org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOVCandidatEvaluation eo = (EOVCandidatEvaluation) EOUtilities.createAndInsertInstance(editingContext, _EOVCandidatEvaluation.ENTITY_NAME);    
		eo.setEpeKeyVisible(epeKeyVisible);
		eo.setEvaKeyVisible(evaKeyVisible);
		eo.setNomUsuel(nomUsuel);
		eo.setPrenom(prenom);
    eo.setToEvaluationPeriodeRelationship(toEvaluationPeriode);
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllVCandidatEvaluations(EOEditingContext editingContext) {
    return _EOVCandidatEvaluation.fetchAllVCandidatEvaluations(editingContext, null);
  }

  public static NSArray fetchAllVCandidatEvaluations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOVCandidatEvaluation.fetchVCandidatEvaluations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchVCandidatEvaluations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOVCandidatEvaluation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOVCandidatEvaluation fetchVCandidatEvaluation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVCandidatEvaluation.fetchVCandidatEvaluation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVCandidatEvaluation fetchVCandidatEvaluation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOVCandidatEvaluation.fetchVCandidatEvaluations(editingContext, qualifier, null);
    EOVCandidatEvaluation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOVCandidatEvaluation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VCandidatEvaluation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVCandidatEvaluation fetchRequiredVCandidatEvaluation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVCandidatEvaluation.fetchRequiredVCandidatEvaluation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVCandidatEvaluation fetchRequiredVCandidatEvaluation(EOEditingContext editingContext, EOQualifier qualifier) {
    EOVCandidatEvaluation eoObject = _EOVCandidatEvaluation.fetchVCandidatEvaluation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VCandidatEvaluation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVCandidatEvaluation localInstanceIn(EOEditingContext editingContext, EOVCandidatEvaluation eo) {
    EOVCandidatEvaluation localInstance = (eo == null) ? null : (EOVCandidatEvaluation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchSuivi(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchSuivi", "VCandidatEvaluation");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
