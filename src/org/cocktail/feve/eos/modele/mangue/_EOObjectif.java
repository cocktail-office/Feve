// _EOObjectif.java
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

// DO NOT EDIT.  Make changes to EOObjectif.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOObjectif extends A_CanChangePosition  {
	public static final String ENTITY_NAME = "Objectif";
	public static final String ENTITY_TABLE_NAME = "MANGUE.OBJECTIF";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String OBJ_MESURE_KEY = "objMesure";
	public static final String OBJ_MOYEN_KEY = "objMoyen";
	public static final String OBJ_OBJECTIF_KEY = "objObjectif";
	public static final String OBJ_OBSERVATION_KEY = "objObservation";
	public static final String OBJ_POSITION_KEY = "objPosition";
	public static final String OBJ_RESULTAT_KEY = "objResultat";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String OBJ_MESURE_COLKEY = "OBJ_MESURE";
	public static final String OBJ_MOYEN_COLKEY = "OBJ_MOYEN";
	public static final String OBJ_OBJECTIF_COLKEY = "OBJ_OBJECTIF";
	public static final String OBJ_OBSERVATION_COLKEY = "OBJ_OBSERVATION";
	public static final String OBJ_POSITION_COLKEY = "OBJ_POSITION";
	public static final String OBJ_RESULTAT_COLKEY = "OBJ_RESULTAT";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";

	// Utilities methods
  public EOObjectif localInstanceIn(EOEditingContext editingContext) {
    EOObjectif localInstance = (EOObjectif)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String objMesure() {
    return (String) storedValueForKey("objMesure");
  }

  public void setObjMesure(String value) {
    takeStoredValueForKey(value, "objMesure");
  }

  public String objMoyen() {
    return (String) storedValueForKey("objMoyen");
  }

  public void setObjMoyen(String value) {
    takeStoredValueForKey(value, "objMoyen");
  }

  public String objObjectif() {
    return (String) storedValueForKey("objObjectif");
  }

  public void setObjObjectif(String value) {
    takeStoredValueForKey(value, "objObjectif");
  }

  public String objObservation() {
    return (String) storedValueForKey("objObservation");
  }

  public void setObjObservation(String value) {
    takeStoredValueForKey(value, "objObservation");
  }

  public Integer objPosition() {
    return (Integer) storedValueForKey("objPosition");
  }

  public void setObjPosition(Integer value) {
    takeStoredValueForKey(value, "objPosition");
  }

  public String objResultat() {
    return (String) storedValueForKey("objResultat");
  }

  public void setObjResultat(String value) {
    takeStoredValueForKey(value, "objResultat");
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
  

  public static EOObjectif createObjectif(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer objPosition
, org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation) {
    EOObjectif eo = (EOObjectif) EOUtilities.createAndInsertInstance(editingContext, _EOObjectif.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setObjPosition(objPosition);
    eo.setToEvaluationRelationship(toEvaluation);
    return eo;
  }

  public static NSArray fetchAllObjectifs(EOEditingContext editingContext) {
    return _EOObjectif.fetchAllObjectifs(editingContext, null);
  }

  public static NSArray fetchAllObjectifs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOObjectif.fetchObjectifs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchObjectifs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOObjectif.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOObjectif fetchObjectif(EOEditingContext editingContext, String keyName, Object value) {
    return _EOObjectif.fetchObjectif(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOObjectif fetchObjectif(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOObjectif.fetchObjectifs(editingContext, qualifier, null);
    EOObjectif eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOObjectif)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Objectif that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOObjectif fetchRequiredObjectif(EOEditingContext editingContext, String keyName, Object value) {
    return _EOObjectif.fetchRequiredObjectif(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOObjectif fetchRequiredObjectif(EOEditingContext editingContext, EOQualifier qualifier) {
    EOObjectif eoObject = _EOObjectif.fetchObjectif(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Objectif that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOObjectif localInstanceIn(EOEditingContext editingContext, EOObjectif eo) {
    EOObjectif localInstance = (eo == null) ? null : (EOObjectif)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
