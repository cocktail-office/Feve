// _EOVPersonnel.java
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

// DO NOT EDIT.  Make changes to EOVPersonnel.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOVPersonnel extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "VPersonnel";
	public static final String ENTITY_TABLE_NAME = "GRHUM.V_PERSONNEL";

	// Attributes


	// Relationships
	public static final String TO_INDIVIDU_KEY = "toIndividu";

	// Utilities methods
  public EOVPersonnel localInstanceIn(EOEditingContext editingContext) {
    EOVPersonnel localInstance = (EOVPersonnel)EOUtilities.localInstanceOfObject(editingContext, this);
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
  

  public static EOVPersonnel createVPersonnel(EOEditingContext editingContext, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOVPersonnel eo = (EOVPersonnel) EOUtilities.createAndInsertInstance(editingContext, _EOVPersonnel.ENTITY_NAME);    
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllVPersonnels(EOEditingContext editingContext) {
    return _EOVPersonnel.fetchAllVPersonnels(editingContext, null);
  }

  public static NSArray fetchAllVPersonnels(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOVPersonnel.fetchVPersonnels(editingContext, null, sortOrderings);
  }

  public static NSArray fetchVPersonnels(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOVPersonnel.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOVPersonnel fetchVPersonnel(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVPersonnel.fetchVPersonnel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVPersonnel fetchVPersonnel(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOVPersonnel.fetchVPersonnels(editingContext, qualifier, null);
    EOVPersonnel eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOVPersonnel)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VPersonnel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVPersonnel fetchRequiredVPersonnel(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVPersonnel.fetchRequiredVPersonnel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVPersonnel fetchRequiredVPersonnel(EOEditingContext editingContext, EOQualifier qualifier) {
    EOVPersonnel eoObject = _EOVPersonnel.fetchVPersonnel(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VPersonnel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVPersonnel localInstanceIn(EOEditingContext editingContext, EOVPersonnel eo) {
    EOVPersonnel localInstance = (eo == null) ? null : (EOVPersonnel)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
