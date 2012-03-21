// _EOBapCorps.java
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

// DO NOT EDIT.  Make changes to EOBapCorps.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOBapCorps extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "BapCorps";
	public static final String ENTITY_TABLE_NAME = "GRHUM.BAP_CORPS";

	// Attributes
	public static final String C_BAP_KEY = "cBap";
	public static final String C_BAP_FAMILLE_KEY = "cBapFamille";
	public static final String C_CORPS_KEY = "cCorps";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String LC_BAP_CORPS_KEY = "lcBapCorps";
	public static final String LL_BAP_CORPS_KEY = "llBapCorps";

	public static final String C_BAP_COLKEY = "C_BAP";
	public static final String C_BAP_FAMILLE_COLKEY = "C_BAP_FAMILLE";
	public static final String C_CORPS_COLKEY = "C_CORPS";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String LC_BAP_CORPS_COLKEY = "LC_BAP_CORPS";
	public static final String LL_BAP_CORPS_COLKEY = "LL_BAP_CORPS";

	// Relationships
	public static final String TO_BAP_KEY = "toBap";
	public static final String TO_CORPS_KEY = "toCorps";
	public static final String TOS_EMPLOI_TYPE_KEY = "tosEmploiType";
	public static final String TOS_REFERENS_EMPLOIS_KEY = "tosReferensEmplois";

	// Utilities methods
  public EOBapCorps localInstanceIn(EOEditingContext editingContext) {
    EOBapCorps localInstance = (EOBapCorps)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cBap() {
    return (String) storedValueForKey("cBap");
  }

  public void setCBap(String value) {
    takeStoredValueForKey(value, "cBap");
  }

  public String cBapFamille() {
    return (String) storedValueForKey("cBapFamille");
  }

  public void setCBapFamille(String value) {
    takeStoredValueForKey(value, "cBapFamille");
  }

  public String cCorps() {
    return (String) storedValueForKey("cCorps");
  }

  public void setCCorps(String value) {
    takeStoredValueForKey(value, "cCorps");
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

  public String lcBapCorps() {
    return (String) storedValueForKey("lcBapCorps");
  }

  public void setLcBapCorps(String value) {
    takeStoredValueForKey(value, "lcBapCorps");
  }

  public String llBapCorps() {
    return (String) storedValueForKey("llBapCorps");
  }

  public void setLlBapCorps(String value) {
    takeStoredValueForKey(value, "llBapCorps");
  }

  public org.cocktail.feve.eos.modele.grhum.EOBap toBap() {
    return (org.cocktail.feve.eos.modele.grhum.EOBap)storedValueForKey("toBap");
  }

  public void setToBapRelationship(org.cocktail.feve.eos.modele.grhum.EOBap value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOBap oldValue = toBap();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toBap");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toBap");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOCorps toCorps() {
    return (org.cocktail.feve.eos.modele.grhum.EOCorps)storedValueForKey("toCorps");
  }

  public void setToCorpsRelationship(org.cocktail.feve.eos.modele.grhum.EOCorps value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOCorps oldValue = toCorps();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toCorps");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toCorps");
    }
  }
  
  public NSArray tosEmploiType() {
    return (NSArray)storedValueForKey("tosEmploiType");
  }

  public NSArray tosEmploiType(EOQualifier qualifier) {
    return tosEmploiType(qualifier, null);
  }

  public NSArray tosEmploiType(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosEmploiType();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosEmploiTypeRelationship(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosEmploiType");
  }

  public void removeFromTosEmploiTypeRelationship(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEmploiType");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOEmploiType createTosEmploiTypeRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EmploiType");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosEmploiType");
    return (org.cocktail.feve.eos.modele.grhum.old.EOEmploiType) eo;
  }

  public void deleteTosEmploiTypeRelationship(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEmploiType");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosEmploiTypeRelationships() {
    Enumeration objects = tosEmploiType().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosEmploiTypeRelationship((org.cocktail.feve.eos.modele.grhum.old.EOEmploiType)objects.nextElement());
    }
  }

  public NSArray tosReferensEmplois() {
    return (NSArray)storedValueForKey("tosReferensEmplois");
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier) {
    return tosReferensEmplois(qualifier, null);
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosReferensEmplois();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
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


  public static EOBapCorps createBapCorps(EOEditingContext editingContext) {
    EOBapCorps eo = (EOBapCorps) EOUtilities.createAndInsertInstance(editingContext, _EOBapCorps.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllBapCorpses(EOEditingContext editingContext) {
    return _EOBapCorps.fetchAllBapCorpses(editingContext, null);
  }

  public static NSArray fetchAllBapCorpses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOBapCorps.fetchBapCorpses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchBapCorpses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOBapCorps.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOBapCorps fetchBapCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOBapCorps.fetchBapCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOBapCorps fetchBapCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOBapCorps.fetchBapCorpses(editingContext, qualifier, null);
    EOBapCorps eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOBapCorps)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one BapCorps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOBapCorps fetchRequiredBapCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOBapCorps.fetchRequiredBapCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOBapCorps fetchRequiredBapCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    EOBapCorps eoObject = _EOBapCorps.fetchBapCorps(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no BapCorps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOBapCorps localInstanceIn(EOEditingContext editingContext, EOBapCorps eo) {
    EOBapCorps localInstance = (eo == null) ? null : (EOBapCorps)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
