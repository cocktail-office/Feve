// _EORepartEmploiCompetence.java
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

// DO NOT EDIT.  Make changes to EORepartEmploiCompetence.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartEmploiCompetence extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartEmploiCompetence";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REPART_EMPLOI_TYPE_COMP_OLD";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_COMPETENCE_KEY = "toCompetence";
	public static final String TO_EMPLOI_TYPE_KEY = "toEmploiType";

	// Utilities methods
  public EORepartEmploiCompetence localInstanceIn(EOEditingContext editingContext) {
    EORepartEmploiCompetence localInstance = (EORepartEmploiCompetence)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public org.cocktail.feve.eos.modele.grhum.old.EOCompetence toCompetence() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOCompetence)storedValueForKey("toCompetence");
  }

  public void setToCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EOCompetence value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOCompetence oldValue = toCompetence();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toCompetence");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toCompetence");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.old.EOEmploiType toEmploiType() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOEmploiType)storedValueForKey("toEmploiType");
  }

  public void setToEmploiTypeRelationship(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOEmploiType oldValue = toEmploiType();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEmploiType");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEmploiType");
    }
  }
  

  public static EORepartEmploiCompetence createRepartEmploiCompetence(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.grhum.old.EOCompetence toCompetence, org.cocktail.feve.eos.modele.grhum.old.EOEmploiType toEmploiType) {
    EORepartEmploiCompetence eo = (EORepartEmploiCompetence) EOUtilities.createAndInsertInstance(editingContext, _EORepartEmploiCompetence.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToCompetenceRelationship(toCompetence);
    eo.setToEmploiTypeRelationship(toEmploiType);
    return eo;
  }

  public static NSArray fetchAllRepartEmploiCompetences(EOEditingContext editingContext) {
    return _EORepartEmploiCompetence.fetchAllRepartEmploiCompetences(editingContext, null);
  }

  public static NSArray fetchAllRepartEmploiCompetences(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartEmploiCompetence.fetchRepartEmploiCompetences(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartEmploiCompetences(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartEmploiCompetence.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartEmploiCompetence fetchRepartEmploiCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEmploiCompetence.fetchRepartEmploiCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEmploiCompetence fetchRepartEmploiCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartEmploiCompetence.fetchRepartEmploiCompetences(editingContext, qualifier, null);
    EORepartEmploiCompetence eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartEmploiCompetence)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartEmploiCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEmploiCompetence fetchRequiredRepartEmploiCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEmploiCompetence.fetchRequiredRepartEmploiCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEmploiCompetence fetchRequiredRepartEmploiCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartEmploiCompetence eoObject = _EORepartEmploiCompetence.fetchRepartEmploiCompetence(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartEmploiCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEmploiCompetence localInstanceIn(EOEditingContext editingContext, EORepartEmploiCompetence eo) {
    EORepartEmploiCompetence localInstance = (eo == null) ? null : (EORepartEmploiCompetence)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
