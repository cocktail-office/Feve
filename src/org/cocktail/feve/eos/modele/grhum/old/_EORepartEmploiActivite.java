// _EORepartEmploiActivite.java
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

// DO NOT EDIT.  Make changes to EORepartEmploiActivite.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartEmploiActivite extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartEmploiActivite";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REPART_EMPLOI_TYPE_ACTI_OLD";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_ACTIVITE_KEY = "toActivite";
	public static final String TO_EMPLOI_TYPE_KEY = "toEmploiType";

	// Utilities methods
  public EORepartEmploiActivite localInstanceIn(EOEditingContext editingContext) {
    EORepartEmploiActivite localInstance = (EORepartEmploiActivite)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public org.cocktail.feve.eos.modele.grhum.old.EOActivite toActivite() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOActivite)storedValueForKey("toActivite");
  }

  public void setToActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EOActivite value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOActivite oldValue = toActivite();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toActivite");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toActivite");
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
  

  public static EORepartEmploiActivite createRepartEmploiActivite(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.grhum.old.EOActivite toActivite, org.cocktail.feve.eos.modele.grhum.old.EOEmploiType toEmploiType) {
    EORepartEmploiActivite eo = (EORepartEmploiActivite) EOUtilities.createAndInsertInstance(editingContext, _EORepartEmploiActivite.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToActiviteRelationship(toActivite);
    eo.setToEmploiTypeRelationship(toEmploiType);
    return eo;
  }

  public static NSArray fetchAllRepartEmploiActivites(EOEditingContext editingContext) {
    return _EORepartEmploiActivite.fetchAllRepartEmploiActivites(editingContext, null);
  }

  public static NSArray fetchAllRepartEmploiActivites(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartEmploiActivite.fetchRepartEmploiActivites(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartEmploiActivites(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartEmploiActivite.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartEmploiActivite fetchRepartEmploiActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEmploiActivite.fetchRepartEmploiActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEmploiActivite fetchRepartEmploiActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartEmploiActivite.fetchRepartEmploiActivites(editingContext, qualifier, null);
    EORepartEmploiActivite eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartEmploiActivite)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartEmploiActivite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEmploiActivite fetchRequiredRepartEmploiActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEmploiActivite.fetchRequiredRepartEmploiActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEmploiActivite fetchRequiredRepartEmploiActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartEmploiActivite eoObject = _EORepartEmploiActivite.fetchRepartEmploiActivite(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartEmploiActivite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEmploiActivite localInstanceIn(EOEditingContext editingContext, EORepartEmploiActivite eo) {
    EORepartEmploiActivite localInstance = (eo == null) ? null : (EORepartEmploiActivite)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
