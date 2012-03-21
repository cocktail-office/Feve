// _EOReferensFp.java
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

// DO NOT EDIT.  Make changes to EOReferensFp.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensFp extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensFp";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_FP";

	// Attributes
	public static final String INTITULFP_KEY = "intitulfp";

	public static final String INTITULFP_COLKEY = "INTITULFP";

	// Relationships
	public static final String TO_REFERENS_DCP_KEY = "toReferensDcp";
	public static final String TOS_REFERENS_EMPLOIS_KEY = "tosReferensEmplois";

	// Utilities methods
  public EOReferensFp localInstanceIn(EOEditingContext editingContext) {
    EOReferensFp localInstance = (EOReferensFp)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String intitulfp() {
    return (String) storedValueForKey("intitulfp");
  }

  public void setIntitulfp(String value) {
    takeStoredValueForKey(value, "intitulfp");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensDcp toReferensDcp() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensDcp)storedValueForKey("toReferensDcp");
  }

  public void setToReferensDcpRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensDcp value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensDcp oldValue = toReferensDcp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensDcp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensDcp");
    }
  }
  
  public NSArray tosReferensEmplois() {
    return (NSArray)storedValueForKey("tosReferensEmplois");
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier) {
    return tosReferensEmplois(qualifier, null, false);
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier, boolean fetch) {
    return tosReferensEmplois(qualifier, null, fetch);
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois.TO_REFERENS_FP_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOReferensEmplois.fetchReferensEmploises(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosReferensEmplois();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
  }

  public void removeFromTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensEmplois createTosReferensEmploisRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReferensEmplois");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReferensEmplois");
    return (org.cocktail.feve.eos.modele.grhum.EOReferensEmplois) eo;
  }

  public void deleteTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReferensEmploisRelationships() {
    Enumeration objects = tosReferensEmplois().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReferensEmploisRelationship((org.cocktail.feve.eos.modele.grhum.EOReferensEmplois)objects.nextElement());
    }
  }


  public static EOReferensFp createReferensFp(EOEditingContext editingContext, org.cocktail.feve.eos.modele.grhum.EOReferensDcp toReferensDcp) {
    EOReferensFp eo = (EOReferensFp) EOUtilities.createAndInsertInstance(editingContext, _EOReferensFp.ENTITY_NAME);    
    eo.setToReferensDcpRelationship(toReferensDcp);
    return eo;
  }

  public static NSArray fetchAllReferensFps(EOEditingContext editingContext) {
    return _EOReferensFp.fetchAllReferensFps(editingContext, null);
  }

  public static NSArray fetchAllReferensFps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensFp.fetchReferensFps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensFps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensFp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensFp fetchReferensFp(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensFp.fetchReferensFp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensFp fetchReferensFp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensFp.fetchReferensFps(editingContext, qualifier, null);
    EOReferensFp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensFp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensFp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensFp fetchRequiredReferensFp(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensFp.fetchRequiredReferensFp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensFp fetchRequiredReferensFp(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensFp eoObject = _EOReferensFp.fetchReferensFp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensFp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensFp localInstanceIn(EOEditingContext editingContext, EOReferensFp eo) {
    EOReferensFp localInstance = (eo == null) ? null : (EOReferensFp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
