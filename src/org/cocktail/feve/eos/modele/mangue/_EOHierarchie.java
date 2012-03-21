// _EOHierarchie.java
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

// DO NOT EDIT.  Make changes to EOHierarchie.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOHierarchie extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Hierarchie";
	public static final String ENTITY_TABLE_NAME = "MANGUE.HIERARCHIE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String HIE_KEY_KEY = "hieKey";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String HIE_KEY_COLKEY = "HIE_KEY";

	// Relationships
	public static final String TO_EVALUATION_PERIODE_KEY = "toEvaluationPeriode";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TO_INDIVIDU_RESP_KEY = "toIndividuResp";
	public static final String TOS_HIERARCHIE_NM1_KEY = "tosHierarchieNm1";
	public static final String TOS_HIERARCHIE_NP1_KEY = "tosHierarchieNp1";

	// Utilities methods
  public EOHierarchie localInstanceIn(EOEditingContext editingContext) {
    EOHierarchie localInstance = (EOHierarchie)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer hieKey() {
    return (Integer) storedValueForKey("hieKey");
  }

  public void setHieKey(Integer value) {
    takeStoredValueForKey(value, "hieKey");
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
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuResp() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividuResp");
  }

  public void setToIndividuRespRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividuResp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividuResp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividuResp");
    }
  }
  
  public NSArray tosHierarchieNm1() {
    return (NSArray)storedValueForKey("tosHierarchieNm1");
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier) {
    return tosHierarchieNm1(qualifier, null, false);
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier, boolean fetch) {
    return tosHierarchieNm1(qualifier, null, fetch);
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOHierarchie.TOS_HIERARCHIE_NP1_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOHierarchie.fetchHierarchies(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosHierarchieNm1();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
  }

  public void removeFromTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
  }

  public org.cocktail.feve.eos.modele.mangue.EOHierarchie createTosHierarchieNm1Relationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Hierarchie");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosHierarchieNm1");
    return (org.cocktail.feve.eos.modele.mangue.EOHierarchie) eo;
  }

  public void deleteTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosHierarchieNm1Relationships() {
    Enumeration objects = tosHierarchieNm1().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosHierarchieNm1Relationship((org.cocktail.feve.eos.modele.mangue.EOHierarchie)objects.nextElement());
    }
  }

  public NSArray tosHierarchieNp1() {
    return (NSArray)storedValueForKey("tosHierarchieNp1");
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier) {
    return tosHierarchieNp1(qualifier, null, false);
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier, boolean fetch) {
    return tosHierarchieNp1(qualifier, null, fetch);
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOHierarchie.TOS_HIERARCHIE_NM1_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOHierarchie.fetchHierarchies(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosHierarchieNp1();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
  }

  public void removeFromTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
  }

  public org.cocktail.feve.eos.modele.mangue.EOHierarchie createTosHierarchieNp1Relationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Hierarchie");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosHierarchieNp1");
    return (org.cocktail.feve.eos.modele.mangue.EOHierarchie) eo;
  }

  public void deleteTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosHierarchieNp1Relationships() {
    Enumeration objects = tosHierarchieNp1().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosHierarchieNp1Relationship((org.cocktail.feve.eos.modele.mangue.EOHierarchie)objects.nextElement());
    }
  }


  public static EOHierarchie createHierarchie(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer hieKey
, org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOHierarchie eo = (EOHierarchie) EOUtilities.createAndInsertInstance(editingContext, _EOHierarchie.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setHieKey(hieKey);
    eo.setToEvaluationPeriodeRelationship(toEvaluationPeriode);
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllHierarchies(EOEditingContext editingContext) {
    return _EOHierarchie.fetchAllHierarchies(editingContext, null);
  }

  public static NSArray fetchAllHierarchies(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOHierarchie.fetchHierarchies(editingContext, null, sortOrderings);
  }

  public static NSArray fetchHierarchies(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOHierarchie.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOHierarchie fetchHierarchie(EOEditingContext editingContext, String keyName, Object value) {
    return _EOHierarchie.fetchHierarchie(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOHierarchie fetchHierarchie(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOHierarchie.fetchHierarchies(editingContext, qualifier, null);
    EOHierarchie eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOHierarchie)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Hierarchie that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOHierarchie fetchRequiredHierarchie(EOEditingContext editingContext, String keyName, Object value) {
    return _EOHierarchie.fetchRequiredHierarchie(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOHierarchie fetchRequiredHierarchie(EOEditingContext editingContext, EOQualifier qualifier) {
    EOHierarchie eoObject = _EOHierarchie.fetchHierarchie(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Hierarchie that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOHierarchie localInstanceIn(EOEditingContext editingContext, EOHierarchie eo) {
    EOHierarchie localInstance = (eo == null) ? null : (EOHierarchie)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchEvaluateur(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchEvaluateur", "Hierarchie");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
