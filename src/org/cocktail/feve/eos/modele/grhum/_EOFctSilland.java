// _EOFctSilland.java
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

// DO NOT EDIT.  Make changes to EOFctSilland.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOFctSilland extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "FctSilland";
	public static final String ENTITY_TABLE_NAME = "GRHUM.SILLAND";

	// Attributes
	public static final String SIL_ENSEIGNANT_KEY = "silEnseignant";
	public static final String SIL_INDICATEUR_KEY = "silIndicateur";
	public static final String SIL_LIBELLE_KEY = "silLibelle";
	public static final String SIL_NON_ENSEIGNANT_KEY = "silNonEnseignant";

	public static final String SIL_ENSEIGNANT_COLKEY = "SIL_ENSEIGNANT";
	public static final String SIL_INDICATEUR_COLKEY = "SIL_INDICATEUR";
	public static final String SIL_LIBELLE_COLKEY = "SIL_LIBELLE";
	public static final String SIL_NON_ENSEIGNANT_COLKEY = "SIL_NON_ENSEIGNANT";

	// Relationships
	public static final String TOS_REPART_FLO_SILLANDS_KEY = "tosRepartFloSillands";
	public static final String TOS_REPART_SIL_LOLF_KEY = "tosRepartSilLolf";

	// Utilities methods
  public EOFctSilland localInstanceIn(EOEditingContext editingContext) {
    EOFctSilland localInstance = (EOFctSilland)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String silEnseignant() {
    return (String) storedValueForKey("silEnseignant");
  }

  public void setSilEnseignant(String value) {
    takeStoredValueForKey(value, "silEnseignant");
  }

  public String silIndicateur() {
    return (String) storedValueForKey("silIndicateur");
  }

  public void setSilIndicateur(String value) {
    takeStoredValueForKey(value, "silIndicateur");
  }

  public String silLibelle() {
    return (String) storedValueForKey("silLibelle");
  }

  public void setSilLibelle(String value) {
    takeStoredValueForKey(value, "silLibelle");
  }

  public String silNonEnseignant() {
    return (String) storedValueForKey("silNonEnseignant");
  }

  public void setSilNonEnseignant(String value) {
    takeStoredValueForKey(value, "silNonEnseignant");
  }

  public NSArray tosRepartFloSillands() {
    return (NSArray)storedValueForKey("tosRepartFloSillands");
  }

  public NSArray tosRepartFloSillands(EOQualifier qualifier) {
    return tosRepartFloSillands(qualifier, null, false);
  }

  public NSArray tosRepartFloSillands(EOQualifier qualifier, boolean fetch) {
    return tosRepartFloSillands(qualifier, null, fetch);
  }

  public NSArray tosRepartFloSillands(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland.TO_FCT_SILLAND_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFloSilland.fetchRepartFloSillands(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFloSillands();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFloSillandsRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFloSillands");
  }

  public void removeFromTosRepartFloSillandsRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloSillands");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFloSilland createTosRepartFloSillandsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFloSilland");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFloSillands");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFloSilland) eo;
  }

  public void deleteTosRepartFloSillandsRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloSillands");
  }

  public void deleteAllTosRepartFloSillandsRelationships() {
    Enumeration objects = tosRepartFloSillands().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFloSillandsRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFloSilland)objects.nextElement());
    }
  }

  public NSArray tosRepartSilLolf() {
    return (NSArray)storedValueForKey("tosRepartSilLolf");
  }

  public NSArray tosRepartSilLolf(EOQualifier qualifier) {
    return tosRepartSilLolf(qualifier, null, false);
  }

  public NSArray tosRepartSilLolf(EOQualifier qualifier, boolean fetch) {
    return tosRepartSilLolf(qualifier, null, fetch);
  }

  public NSArray tosRepartSilLolf(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf.TO_FCT_SILLAND_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf.fetchRepartLolfSillands(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartSilLolf();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartSilLolfRelationship(org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartSilLolf");
  }

  public void removeFromTosRepartSilLolfRelationship(org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartSilLolf");
  }

  public org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf createTosRepartSilLolfRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartLolfSilland");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartSilLolf");
    return (org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf) eo;
  }

  public void deleteTosRepartSilLolfRelationship(org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartSilLolf");
  }

  public void deleteAllTosRepartSilLolfRelationships() {
    Enumeration objects = tosRepartSilLolf().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartSilLolfRelationship((org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf)objects.nextElement());
    }
  }


  public static EOFctSilland createFctSilland(EOEditingContext editingContext, String silEnseignant
, String silLibelle
, String silNonEnseignant
) {
    EOFctSilland eo = (EOFctSilland) EOUtilities.createAndInsertInstance(editingContext, _EOFctSilland.ENTITY_NAME);    
		eo.setSilEnseignant(silEnseignant);
		eo.setSilLibelle(silLibelle);
		eo.setSilNonEnseignant(silNonEnseignant);
    return eo;
  }

  public static NSArray fetchAllFctSillands(EOEditingContext editingContext) {
    return _EOFctSilland.fetchAllFctSillands(editingContext, null);
  }

  public static NSArray fetchAllFctSillands(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOFctSilland.fetchFctSillands(editingContext, null, sortOrderings);
  }

  public static NSArray fetchFctSillands(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOFctSilland.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOFctSilland fetchFctSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFctSilland.fetchFctSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFctSilland fetchFctSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOFctSilland.fetchFctSillands(editingContext, qualifier, null);
    EOFctSilland eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOFctSilland)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FctSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFctSilland fetchRequiredFctSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFctSilland.fetchRequiredFctSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFctSilland fetchRequiredFctSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    EOFctSilland eoObject = _EOFctSilland.fetchFctSilland(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FctSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFctSilland localInstanceIn(EOEditingContext editingContext, EOFctSilland eo) {
    EOFctSilland localInstance = (eo == null) ? null : (EOFctSilland)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
