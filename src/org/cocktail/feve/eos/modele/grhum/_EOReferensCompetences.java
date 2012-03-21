// _EOReferensCompetences.java
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

// DO NOT EDIT.  Make changes to EOReferensCompetences.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensCompetences extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensCompetences";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_COMPETENCES";

	// Attributes
	public static final String IDTYPEGROUPEMENT_KEY = "idtypegroupement";
	public static final String INTITULCOMP_KEY = "intitulcomp";
	public static final String INTITULCOMP_CLEAN_KEY = "intitulcompClean";

	public static final String IDTYPEGROUPEMENT_COLKEY = "IDTYPEGROUPEMENT";
	public static final String INTITULCOMP_COLKEY = "INTITULCOMP";
	public static final String INTITULCOMP_CLEAN_COLKEY = "INTITULCOMP_CLEAN";

	// Relationships
	public static final String TO_REFERENS_EMPLOIS_KEY = "toReferensEmplois";

	// Utilities methods
  public EOReferensCompetences localInstanceIn(EOEditingContext editingContext) {
    EOReferensCompetences localInstance = (EOReferensCompetences)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String intitulcomp() {
    return (String) storedValueForKey("intitulcomp");
  }

  public void setIntitulcomp(String value) {
    takeStoredValueForKey(value, "intitulcomp");
  }

  public String intitulcompClean() {
    return (String) storedValueForKey("intitulcompClean");
  }

  public void setIntitulcompClean(String value) {
    takeStoredValueForKey(value, "intitulcompClean");
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
  

  public static EOReferensCompetences createReferensCompetences(EOEditingContext editingContext, org.cocktail.feve.eos.modele.grhum.EOReferensEmplois toReferensEmplois) {
    EOReferensCompetences eo = (EOReferensCompetences) EOUtilities.createAndInsertInstance(editingContext, _EOReferensCompetences.ENTITY_NAME);    
    eo.setToReferensEmploisRelationship(toReferensEmplois);
    return eo;
  }

  public static NSArray fetchAllReferensCompetenceses(EOEditingContext editingContext) {
    return _EOReferensCompetences.fetchAllReferensCompetenceses(editingContext, null);
  }

  public static NSArray fetchAllReferensCompetenceses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensCompetences.fetchReferensCompetenceses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensCompetenceses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensCompetences.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensCompetences fetchReferensCompetences(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensCompetences.fetchReferensCompetences(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensCompetences fetchReferensCompetences(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensCompetences.fetchReferensCompetenceses(editingContext, qualifier, null);
    EOReferensCompetences eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensCompetences)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensCompetences that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensCompetences fetchRequiredReferensCompetences(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensCompetences.fetchRequiredReferensCompetences(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensCompetences fetchRequiredReferensCompetences(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensCompetences eoObject = _EOReferensCompetences.fetchReferensCompetences(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensCompetences that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensCompetences localInstanceIn(EOEditingContext editingContext, EOReferensCompetences eo) {
    EOReferensCompetences localInstance = (eo == null) ? null : (EOReferensCompetences)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
