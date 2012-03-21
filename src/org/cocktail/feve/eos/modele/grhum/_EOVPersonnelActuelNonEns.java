// _EOVPersonnelActuelNonEns.java
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

// DO NOT EDIT.  Make changes to EOVPersonnelActuelNonEns.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOVPersonnelActuelNonEns extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "VPersonnelActuelNonEns";
	public static final String ENTITY_TABLE_NAME = "GRHUM.V_PERSONNEL_ACTUEL_NON_ENS";

	// Attributes


	// Relationships
	public static final String TO_INDIVIDU_KEY = "toIndividu";

	// Utilities methods
  public EOVPersonnelActuelNonEns localInstanceIn(EOEditingContext editingContext) {
    EOVPersonnelActuelNonEns localInstance = (EOVPersonnelActuelNonEns)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
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
  

  public static EOVPersonnelActuelNonEns createVPersonnelActuelNonEns(EOEditingContext editingContext) {
    EOVPersonnelActuelNonEns eo = (EOVPersonnelActuelNonEns) EOUtilities.createAndInsertInstance(editingContext, _EOVPersonnelActuelNonEns.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllVPersonnelActuelNonEnses(EOEditingContext editingContext) {
    return _EOVPersonnelActuelNonEns.fetchAllVPersonnelActuelNonEnses(editingContext, null);
  }

  public static NSArray fetchAllVPersonnelActuelNonEnses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOVPersonnelActuelNonEns.fetchVPersonnelActuelNonEnses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchVPersonnelActuelNonEnses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOVPersonnelActuelNonEns.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOVPersonnelActuelNonEns fetchVPersonnelActuelNonEns(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVPersonnelActuelNonEns.fetchVPersonnelActuelNonEns(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVPersonnelActuelNonEns fetchVPersonnelActuelNonEns(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOVPersonnelActuelNonEns.fetchVPersonnelActuelNonEnses(editingContext, qualifier, null);
    EOVPersonnelActuelNonEns eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOVPersonnelActuelNonEns)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VPersonnelActuelNonEns that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVPersonnelActuelNonEns fetchRequiredVPersonnelActuelNonEns(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVPersonnelActuelNonEns.fetchRequiredVPersonnelActuelNonEns(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVPersonnelActuelNonEns fetchRequiredVPersonnelActuelNonEns(EOEditingContext editingContext, EOQualifier qualifier) {
    EOVPersonnelActuelNonEns eoObject = _EOVPersonnelActuelNonEns.fetchVPersonnelActuelNonEns(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VPersonnelActuelNonEns that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVPersonnelActuelNonEns localInstanceIn(EOEditingContext editingContext, EOVPersonnelActuelNonEns eo) {
    EOVPersonnelActuelNonEns localInstance = (eo == null) ? null : (EOVPersonnelActuelNonEns)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
