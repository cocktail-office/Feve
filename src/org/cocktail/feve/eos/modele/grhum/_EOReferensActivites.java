// _EOReferensActivites.java
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

// DO NOT EDIT.  Make changes to EOReferensActivites.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensActivites extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensActivites";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_ACTIVITES";

	// Attributes
	public static final String IDTYPEGROUPEMENT_KEY = "idtypegroupement";
	public static final String INTITULACTIVITE_KEY = "intitulactivite";
	public static final String INTITULACTIVITE_CLEAN_KEY = "intitulactiviteClean";

	public static final String IDTYPEGROUPEMENT_COLKEY = "IDTYPEGROUPEMENT";
	public static final String INTITULACTIVITE_COLKEY = "INTITULACTIVITE";
	public static final String INTITULACTIVITE_CLEAN_COLKEY = "INTITULACTIVITE_CLEAN";

	// Relationships
	public static final String TO_REFERENS_EMPLOIS_KEY = "toReferensEmplois";

	// Utilities methods
  public EOReferensActivites localInstanceIn(EOEditingContext editingContext) {
    EOReferensActivites localInstance = (EOReferensActivites)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String idtypegroupement() {
    return (String) storedValueForKey("idtypegroupement");
  }

  public void setIdtypegroupement(String value) {
    takeStoredValueForKey(value, "idtypegroupement");
  }

  public String intitulactivite() {
    return (String) storedValueForKey("intitulactivite");
  }

  public void setIntitulactivite(String value) {
    takeStoredValueForKey(value, "intitulactivite");
  }

  public String intitulactiviteClean() {
    return (String) storedValueForKey("intitulactiviteClean");
  }

  public void setIntitulactiviteClean(String value) {
    takeStoredValueForKey(value, "intitulactiviteClean");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensEmplois toReferensEmplois() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensEmplois)storedValueForKey("toReferensEmplois");
  }

  public void setToReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensEmplois oldValue = toReferensEmplois();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensEmplois");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensEmplois");
    }
  }
  

  public static EOReferensActivites createReferensActivites(EOEditingContext editingContext, org.cocktail.feve.eos.modele.grhum.EOReferensEmplois toReferensEmplois) {
    EOReferensActivites eo = (EOReferensActivites) EOUtilities.createAndInsertInstance(editingContext, _EOReferensActivites.ENTITY_NAME);    
    eo.setToReferensEmploisRelationship(toReferensEmplois);
    return eo;
  }

  public static NSArray fetchAllReferensActiviteses(EOEditingContext editingContext) {
    return _EOReferensActivites.fetchAllReferensActiviteses(editingContext, null);
  }

  public static NSArray fetchAllReferensActiviteses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensActivites.fetchReferensActiviteses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensActiviteses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensActivites.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensActivites fetchReferensActivites(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensActivites.fetchReferensActivites(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensActivites fetchReferensActivites(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensActivites.fetchReferensActiviteses(editingContext, qualifier, null);
    EOReferensActivites eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensActivites)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensActivites that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensActivites fetchRequiredReferensActivites(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensActivites.fetchRequiredReferensActivites(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensActivites fetchRequiredReferensActivites(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensActivites eoObject = _EOReferensActivites.fetchReferensActivites(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensActivites that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensActivites localInstanceIn(EOEditingContext editingContext, EOReferensActivites eo) {
    EOReferensActivites localInstance = (eo == null) ? null : (EOReferensActivites)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
