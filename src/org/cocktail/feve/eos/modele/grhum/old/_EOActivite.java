// _EOActivite.java
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

// DO NOT EDIT.  Make changes to EOActivite.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOActivite extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Activite";
	public static final String ENTITY_TABLE_NAME = "GRHUM.ACTIVITE_PROFESSIONNELLE_OLD";

	// Attributes
	public static final String ACT_LIBELLE_KEY = "actLibelle";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String ACT_LIBELLE_COLKEY = "ACT_LIBELLE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TOS_REPART_EMPLOI_ACTIVITE_KEY = "tosRepartEmploiActivite";

	// Utilities methods
  public EOActivite localInstanceIn(EOEditingContext editingContext) {
    EOActivite localInstance = (EOActivite)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String actLibelle() {
    return (String) storedValueForKey("actLibelle");
  }

  public void setActLibelle(String value) {
    takeStoredValueForKey(value, "actLibelle");
  }

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

  public NSArray tosRepartEmploiActivite() {
    return (NSArray)storedValueForKey("tosRepartEmploiActivite");
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier) {
    return tosRepartEmploiActivite(qualifier, null, false);
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier, boolean fetch) {
    return tosRepartEmploiActivite(qualifier, null, fetch);
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite.TO_ACTIVITE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite.fetchRepartEmploiActivites(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEmploiActivite();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
  }

  public void removeFromTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite createTosRepartEmploiActiviteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEmploiActivite");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEmploiActivite");
    return (org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite) eo;
  }

  public void deleteTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
  }

  public void deleteAllTosRepartEmploiActiviteRelationships() {
    Enumeration objects = tosRepartEmploiActivite().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEmploiActiviteRelationship((org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite)objects.nextElement());
    }
  }


  public static EOActivite createActivite(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EOActivite eo = (EOActivite) EOUtilities.createAndInsertInstance(editingContext, _EOActivite.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllActivites(EOEditingContext editingContext) {
    return _EOActivite.fetchAllActivites(editingContext, null);
  }

  public static NSArray fetchAllActivites(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOActivite.fetchActivites(editingContext, null, sortOrderings);
  }

  public static NSArray fetchActivites(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOActivite.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOActivite fetchActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EOActivite.fetchActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOActivite fetchActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOActivite.fetchActivites(editingContext, qualifier, null);
    EOActivite eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOActivite)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Activite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOActivite fetchRequiredActivite(EOEditingContext editingContext, String keyName, Object value) {
    return _EOActivite.fetchRequiredActivite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOActivite fetchRequiredActivite(EOEditingContext editingContext, EOQualifier qualifier) {
    EOActivite eoObject = _EOActivite.fetchActivite(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Activite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOActivite localInstanceIn(EOEditingContext editingContext, EOActivite eo) {
    EOActivite localInstance = (eo == null) ? null : (EOActivite)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
