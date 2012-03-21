// _EOEnqueteFormation.java
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

// DO NOT EDIT.  Make changes to EOEnqueteFormation.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOEnqueteFormation extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "EnqueteFormation";
	public static final String ENTITY_TABLE_NAME = "MANGUE.ENQUETE_FORMATION";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String EFO_ANNEE_KEY = "efoAnnee";
	public static final String EFO_CHAMP_LIBRE_KEY = "efoChampLibre";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String EFO_ANNEE_COLKEY = "EFO_ANNEE";
	public static final String EFO_CHAMP_LIBRE_COLKEY = "EFO_CHAMP_LIBRE";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TOS_REPART_ENQ_COMP_KEY = "tosRepartEnqComp";

	// Utilities methods
  public EOEnqueteFormation localInstanceIn(EOEditingContext editingContext) {
    EOEnqueteFormation localInstance = (EOEnqueteFormation)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer efoAnnee() {
    return (Integer) storedValueForKey("efoAnnee");
  }

  public void setEfoAnnee(Integer value) {
    takeStoredValueForKey(value, "efoAnnee");
  }

  public String efoChampLibre() {
    return (String) storedValueForKey("efoChampLibre");
  }

  public void setEfoChampLibre(String value) {
    takeStoredValueForKey(value, "efoChampLibre");
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
  
  public NSArray tosRepartEnqComp() {
    return (NSArray)storedValueForKey("tosRepartEnqComp");
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier) {
    return tosRepartEnqComp(qualifier, null, false);
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier, boolean fetch) {
    return tosRepartEnqComp(qualifier, null, fetch);
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp.TO_ENQUETE_FORMATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartEnqComp.fetchRepartEnqComps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEnqComp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public void removeFromTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartEnqComp createTosRepartEnqCompRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEnqComp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEnqComp");
    return (org.cocktail.feve.eos.modele.mangue.EORepartEnqComp) eo;
  }

  public void deleteTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public void deleteAllTosRepartEnqCompRelationships() {
    Enumeration objects = tosRepartEnqComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEnqCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartEnqComp)objects.nextElement());
    }
  }


  public static EOEnqueteFormation createEnqueteFormation(EOEditingContext editingContext) {
    EOEnqueteFormation eo = (EOEnqueteFormation) EOUtilities.createAndInsertInstance(editingContext, _EOEnqueteFormation.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllEnqueteFormations(EOEditingContext editingContext) {
    return _EOEnqueteFormation.fetchAllEnqueteFormations(editingContext, null);
  }

  public static NSArray fetchAllEnqueteFormations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOEnqueteFormation.fetchEnqueteFormations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchEnqueteFormations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEnqueteFormation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEnqueteFormation fetchEnqueteFormation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEnqueteFormation.fetchEnqueteFormation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEnqueteFormation fetchEnqueteFormation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOEnqueteFormation.fetchEnqueteFormations(editingContext, qualifier, null);
    EOEnqueteFormation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEnqueteFormation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EnqueteFormation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEnqueteFormation fetchRequiredEnqueteFormation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEnqueteFormation.fetchRequiredEnqueteFormation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEnqueteFormation fetchRequiredEnqueteFormation(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEnqueteFormation eoObject = _EOEnqueteFormation.fetchEnqueteFormation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EnqueteFormation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEnqueteFormation localInstanceIn(EOEditingContext editingContext, EOEnqueteFormation eo) {
    EOEnqueteFormation localInstance = (eo == null) ? null : (EOEnqueteFormation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
