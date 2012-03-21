// _EOVReferens.java
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

// DO NOT EDIT.  Make changes to EOVReferens.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOVReferens extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "VReferens";
	public static final String ENTITY_TABLE_NAME = "GRHUM.V_REFERENS";

	// Attributes
	public static final String KEY_KEY = "key";
	public static final String KEY_PERE_KEY = "keyPere";
	public static final String LIBELLE_KEY = "libelle";
	public static final String LIBELLE_SEUL_KEY = "libelleSeul";
	public static final String LIBELLE_SEUL_CHAINE_CLAIRE_KEY = "libelleSeulChaineClaire";
	public static final String NIVEAU_KEY = "niveau";

	public static final String KEY_COLKEY = "KEY";
	public static final String KEY_PERE_COLKEY = "KEY_PERE";
	public static final String LIBELLE_COLKEY = "LIBELLE";
	public static final String LIBELLE_SEUL_COLKEY = "LIBELLE_SEUL";
	public static final String LIBELLE_SEUL_CHAINE_CLAIRE_COLKEY = "libelleSeulChaineClaire";
	public static final String NIVEAU_COLKEY = "NIVEAU";

	// Relationships
	public static final String TO_REFERENS_ACTIVITES_KEY = "toReferensActivites";
	public static final String TO_REFERENS_COMPETENCES_KEY = "toReferensCompetences";
	public static final String TO_REFERENS_DCP_KEY = "toReferensDcp";
	public static final String TO_REFERENS_EMPLOIS_KEY = "toReferensEmplois";
	public static final String TO_REFERENS_FP_KEY = "toReferensFp";
	public static final String TOS_V_REFERENS_FILS_KEY = "tosVReferensFils";
	public static final String TO_V_REFERENS_PERE_KEY = "toVReferensPere";

	// Utilities methods
  public EOVReferens localInstanceIn(EOEditingContext editingContext) {
    EOVReferens localInstance = (EOVReferens)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String key() {
    return (String) storedValueForKey("key");
  }

  public void setKey(String value) {
    takeStoredValueForKey(value, "key");
  }

  public String keyPere() {
    return (String) storedValueForKey("keyPere");
  }

  public void setKeyPere(String value) {
    takeStoredValueForKey(value, "keyPere");
  }

  public String libelle() {
    return (String) storedValueForKey("libelle");
  }

  public void setLibelle(String value) {
    takeStoredValueForKey(value, "libelle");
  }

  public String libelleSeul() {
    return (String) storedValueForKey("libelleSeul");
  }

  public void setLibelleSeul(String value) {
    takeStoredValueForKey(value, "libelleSeul");
  }

  public String libelleSeulChaineClaire() {
    return (String) storedValueForKey("libelleSeulChaineClaire");
  }

  public void setLibelleSeulChaineClaire(String value) {
    takeStoredValueForKey(value, "libelleSeulChaineClaire");
  }

  public Integer niveau() {
    return (Integer) storedValueForKey("niveau");
  }

  public void setNiveau(Integer value) {
    takeStoredValueForKey(value, "niveau");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensActivites toReferensActivites() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensActivites)storedValueForKey("toReferensActivites");
  }

  public void setToReferensActivitesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensActivites value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensActivites oldValue = toReferensActivites();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensActivites");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensActivites");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensCompetences toReferensCompetences() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensCompetences)storedValueForKey("toReferensCompetences");
  }

  public void setToReferensCompetencesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensCompetences oldValue = toReferensCompetences();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensCompetences");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensCompetences");
    }
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
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensFp toReferensFp() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensFp)storedValueForKey("toReferensFp");
  }

  public void setToReferensFpRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensFp value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensFp oldValue = toReferensFp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensFp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensFp");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOVReferens toVReferensPere() {
    return (org.cocktail.feve.eos.modele.grhum.EOVReferens)storedValueForKey("toVReferensPere");
  }

  public void setToVReferensPereRelationship(org.cocktail.feve.eos.modele.grhum.EOVReferens value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOVReferens oldValue = toVReferensPere();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVReferensPere");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVReferensPere");
    }
  }
  
  public NSArray tosVReferensFils() {
    return (NSArray)storedValueForKey("tosVReferensFils");
  }

  public NSArray tosVReferensFils(EOQualifier qualifier) {
    return tosVReferensFils(qualifier, null, false);
  }

  public NSArray tosVReferensFils(EOQualifier qualifier, boolean fetch) {
    return tosVReferensFils(qualifier, null, fetch);
  }

  public NSArray tosVReferensFils(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOVReferens.TO_V_REFERENS_PERE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOVReferens.fetchVReferenses(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosVReferensFils();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosVReferensFilsRelationship(org.cocktail.feve.eos.modele.grhum.EOVReferens object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosVReferensFils");
  }

  public void removeFromTosVReferensFilsRelationship(org.cocktail.feve.eos.modele.grhum.EOVReferens object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVReferensFils");
  }

  public org.cocktail.feve.eos.modele.grhum.EOVReferens createTosVReferensFilsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("VReferens");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosVReferensFils");
    return (org.cocktail.feve.eos.modele.grhum.EOVReferens) eo;
  }

  public void deleteTosVReferensFilsRelationship(org.cocktail.feve.eos.modele.grhum.EOVReferens object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVReferensFils");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosVReferensFilsRelationships() {
    Enumeration objects = tosVReferensFils().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosVReferensFilsRelationship((org.cocktail.feve.eos.modele.grhum.EOVReferens)objects.nextElement());
    }
  }


  public static EOVReferens createVReferens(EOEditingContext editingContext, String key
, String keyPere
, Integer niveau
, org.cocktail.feve.eos.modele.grhum.EOVReferens toVReferensPere) {
    EOVReferens eo = (EOVReferens) EOUtilities.createAndInsertInstance(editingContext, _EOVReferens.ENTITY_NAME);    
		eo.setKey(key);
		eo.setKeyPere(keyPere);
		eo.setNiveau(niveau);
    eo.setToVReferensPereRelationship(toVReferensPere);
    return eo;
  }

  public static NSArray fetchAllVReferenses(EOEditingContext editingContext) {
    return _EOVReferens.fetchAllVReferenses(editingContext, null);
  }

  public static NSArray fetchAllVReferenses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOVReferens.fetchVReferenses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchVReferenses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOVReferens.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOVReferens fetchVReferens(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVReferens.fetchVReferens(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVReferens fetchVReferens(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOVReferens.fetchVReferenses(editingContext, qualifier, null);
    EOVReferens eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOVReferens)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VReferens that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVReferens fetchRequiredVReferens(EOEditingContext editingContext, String keyName, Object value) {
    return _EOVReferens.fetchRequiredVReferens(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOVReferens fetchRequiredVReferens(EOEditingContext editingContext, EOQualifier qualifier) {
    EOVReferens eoObject = _EOVReferens.fetchVReferens(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VReferens that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOVReferens localInstanceIn(EOEditingContext editingContext, EOVReferens eo) {
    EOVReferens localInstance = (eo == null) ? null : (EOVReferens)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
