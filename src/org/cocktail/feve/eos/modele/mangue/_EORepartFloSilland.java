// _EORepartFloSilland.java
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

// DO NOT EDIT.  Make changes to EORepartFloSilland.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFloSilland extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartFloSilland";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FLO_SILLAND";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String RFS_QUOTITE_KEY = "rfsQuotite";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String RFS_QUOTITE_COLKEY = "RFS_QUOTITE";

	// Relationships
	public static final String TO_FCT_SILLAND_KEY = "toFctSilland";
	public static final String TO_FICHE_LOLF_KEY = "toFicheLolf";
	public static final String TOS_REPART_FLO_LOLF_NOMEN_KEY = "tosRepartFloLolfNomen";

	// Utilities methods
  public EORepartFloSilland localInstanceIn(EOEditingContext editingContext) {
    EORepartFloSilland localInstance = (EORepartFloSilland)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Double rfsQuotite() {
    return (Double) storedValueForKey("rfsQuotite");
  }

  public void setRfsQuotite(Double value) {
    takeStoredValueForKey(value, "rfsQuotite");
  }

  public org.cocktail.feve.eos.modele.grhum.EOFctSilland toFctSilland() {
    return (org.cocktail.feve.eos.modele.grhum.EOFctSilland)storedValueForKey("toFctSilland");
  }

  public void setToFctSillandRelationship(org.cocktail.feve.eos.modele.grhum.EOFctSilland value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOFctSilland oldValue = toFctSilland();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFctSilland");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFctSilland");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheLolf toFicheLolf() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheLolf)storedValueForKey("toFicheLolf");
  }

  public void setToFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheLolf oldValue = toFicheLolf();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheLolf");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheLolf");
    }
  }
  
  public NSArray tosRepartFloLolfNomen() {
    return (NSArray)storedValueForKey("tosRepartFloLolfNomen");
  }

  public NSArray tosRepartFloLolfNomen(EOQualifier qualifier) {
    return tosRepartFloLolfNomen(qualifier, null, false);
  }

  public NSArray tosRepartFloLolfNomen(EOQualifier qualifier, boolean fetch) {
    return tosRepartFloLolfNomen(qualifier, null, fetch);
  }

  public NSArray tosRepartFloLolfNomen(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen.TO_REPART_FLO_SILLAND_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen.fetchRepartFloLolfNomens(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFloLolfNomen();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFloLolfNomenRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFloLolfNomen");
  }

  public void removeFromTosRepartFloLolfNomenRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloLolfNomen");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen createTosRepartFloLolfNomenRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFloLolfNomen");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFloLolfNomen");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen) eo;
  }

  public void deleteTosRepartFloLolfNomenRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloLolfNomen");
  }

  public void deleteAllTosRepartFloLolfNomenRelationships() {
    Enumeration objects = tosRepartFloLolfNomen().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFloLolfNomenRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen)objects.nextElement());
    }
  }


  public static EORepartFloSilland createRepartFloSilland(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Double rfsQuotite
, org.cocktail.feve.eos.modele.grhum.EOFctSilland toFctSilland, org.cocktail.feve.eos.modele.mangue.EOFicheLolf toFicheLolf) {
    EORepartFloSilland eo = (EORepartFloSilland) EOUtilities.createAndInsertInstance(editingContext, _EORepartFloSilland.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setRfsQuotite(rfsQuotite);
    eo.setToFctSillandRelationship(toFctSilland);
    eo.setToFicheLolfRelationship(toFicheLolf);
    return eo;
  }

  public static NSArray fetchAllRepartFloSillands(EOEditingContext editingContext) {
    return _EORepartFloSilland.fetchAllRepartFloSillands(editingContext, null);
  }

  public static NSArray fetchAllRepartFloSillands(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFloSilland.fetchRepartFloSillands(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFloSillands(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFloSilland.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFloSilland fetchRepartFloSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFloSilland.fetchRepartFloSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFloSilland fetchRepartFloSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFloSilland.fetchRepartFloSillands(editingContext, qualifier, null);
    EORepartFloSilland eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFloSilland)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFloSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFloSilland fetchRequiredRepartFloSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFloSilland.fetchRequiredRepartFloSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFloSilland fetchRequiredRepartFloSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFloSilland eoObject = _EORepartFloSilland.fetchRepartFloSilland(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFloSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFloSilland localInstanceIn(EOEditingContext editingContext, EORepartFloSilland eo) {
    EORepartFloSilland localInstance = (eo == null) ? null : (EORepartFloSilland)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
