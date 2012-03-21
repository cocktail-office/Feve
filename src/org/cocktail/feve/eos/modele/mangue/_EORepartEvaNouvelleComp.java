// _EORepartEvaNouvelleComp.java
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

// DO NOT EDIT.  Make changes to EORepartEvaNouvelleComp.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartEvaNouvelleComp extends A_CanBeDeleted  {
	public static final String ENTITY_NAME = "RepartEvaNouvelleComp";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_NOUVELLE_COMP";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_COMPETENCE_KEY = "toCompetence";
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_REFERENS_COMPETENCES_KEY = "toReferensCompetences";

	// Utilities methods
  public EORepartEvaNouvelleComp localInstanceIn(EOEditingContext editingContext) {
    EORepartEvaNouvelleComp localInstance = (EORepartEvaNouvelleComp)EOUtilities.localInstanceOfObject(editingContext, this);
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
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensCompetences toReferensCompetences() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensCompetences)storedValueForKey("toReferensCompetences");
  }

  public void setToReferensCompetencesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensCompetences oldValue = toReferensCompetences();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensCompetences");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensCompetences");
    }
  }
  

  public static EORepartEvaNouvelleComp createRepartEvaNouvelleComp(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation) {
    EORepartEvaNouvelleComp eo = (EORepartEvaNouvelleComp) EOUtilities.createAndInsertInstance(editingContext, _EORepartEvaNouvelleComp.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToEvaluationRelationship(toEvaluation);
    return eo;
  }

  public static NSArray fetchAllRepartEvaNouvelleComps(EOEditingContext editingContext) {
    return _EORepartEvaNouvelleComp.fetchAllRepartEvaNouvelleComps(editingContext, null);
  }

  public static NSArray fetchAllRepartEvaNouvelleComps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartEvaNouvelleComps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartEvaNouvelleComp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartEvaNouvelleComp fetchRepartEvaNouvelleComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEvaNouvelleComp fetchRepartEvaNouvelleComp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComps(editingContext, qualifier, null);
    EORepartEvaNouvelleComp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartEvaNouvelleComp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartEvaNouvelleComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEvaNouvelleComp fetchRequiredRepartEvaNouvelleComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEvaNouvelleComp.fetchRequiredRepartEvaNouvelleComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEvaNouvelleComp fetchRequiredRepartEvaNouvelleComp(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartEvaNouvelleComp eoObject = _EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartEvaNouvelleComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEvaNouvelleComp localInstanceIn(EOEditingContext editingContext, EORepartEvaNouvelleComp eo) {
    EORepartEvaNouvelleComp localInstance = (eo == null) ? null : (EORepartEvaNouvelleComp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
