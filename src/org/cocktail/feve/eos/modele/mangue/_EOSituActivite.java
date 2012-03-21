// _EOSituActivite.java
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

// DO NOT EDIT.  Make changes to EOSituActivite.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOSituActivite extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "SituActivite";
	public static final String ENTITY_TABLE_NAME = "MANGUE.SITU_ACTIVITE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String SAC_SITUATION_KEY = "sacSituation";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String SAC_SITUATION_COLKEY = "SAC_SITUATION";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";

	// Utilities methods
  public EOSituActivite localInstanceIn(EOEditingContext editingContext) {
    EOSituActivite localInstance = (EOSituActivite)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public String sacSituation() {
    return (String) storedValueForKey("sacSituation");
  }

  public void setSacSituation(String value) {
    takeStoredValueForKey(value, "sacSituation");
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
  

  public static EOSituActivite createSituActivite(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation) {
    EOSituActivite eo = (EOSituActivite) EOUtilities.createAndInsertInstance(editingContext, _EOSituActivite.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToEvaluationRelationship(toEvaluation);
    return eo;
  }

  public static NSArray fetchAllSituActivites(EOEditingContext editingContext) {
    return _EOSituActivite.fetchAllSituActivites(editingContext, null);
  }

  public static NSArray fetchAllSituActivites(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOSituActivite.fetchSituActivites(editingContext, null, sortOrderings);
  }

  public static NSArray fetchSituActivites(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOSituActivite.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOSituActivite fetchSituActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EOSituActivite.fetchSituActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOSituActivite fetchSituActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOSituActivite.fetchSituActivites(editingContext, qualifier, null);
    EOSituActivite eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOSituActivite)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one SituActivite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOSituActivite fetchRequiredSituActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EOSituActivite.fetchRequiredSituActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOSituActivite fetchRequiredSituActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    EOSituActivite eoObject = _EOSituActivite.fetchSituActivite(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no SituActivite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOSituActivite localInstanceIn(EOEditingContext editingContext, EOSituActivite eo) {
    EOSituActivite localInstance = (eo == null) ? null : (EOSituActivite)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
