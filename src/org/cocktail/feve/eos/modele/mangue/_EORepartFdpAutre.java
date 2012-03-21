// _EORepartFdpAutre.java
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

// DO NOT EDIT.  Make changes to EORepartFdpAutre.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFdpAutre extends A_CanBeDeleted  {
	public static final String ENTITY_NAME = "RepartFdpAutre";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FDP_AUTRE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String FAU_CHAMP_LIBRE_KEY = "fauChampLibre";
	public static final String FAU_POSITION_KEY = "fauPosition";
	public static final String FAU_TYPE_KEY = "fauType";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String FAU_CHAMP_LIBRE_COLKEY = "FAU_ACTIVITE_AUTRE";
	public static final String FAU_POSITION_COLKEY = "FAU_POSITION";
	public static final String FAU_TYPE_COLKEY = "FAU_TYPE";

	// Relationships
	public static final String TO_FICHE_DE_POSTE_KEY = "toFicheDePoste";
	public static final String TOS_REPART_NIVEAU_COMP_KEY = "tosRepartNiveauComp";

	// Utilities methods
  public EORepartFdpAutre localInstanceIn(EOEditingContext editingContext) {
    EORepartFdpAutre localInstance = (EORepartFdpAutre)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String fauChampLibre() {
    return (String) storedValueForKey("fauChampLibre");
  }

  public void setFauChampLibre(String value) {
    takeStoredValueForKey(value, "fauChampLibre");
  }

  public Integer fauPosition() {
    return (Integer) storedValueForKey("fauPosition");
  }

  public void setFauPosition(Integer value) {
    takeStoredValueForKey(value, "fauPosition");
  }

  public String fauType() {
    return (String) storedValueForKey("fauType");
  }

  public void setFauType(String value) {
    takeStoredValueForKey(value, "fauType");
  }

  public org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheDePoste)storedValueForKey("toFicheDePoste");
  }

  public void setToFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheDePoste oldValue = toFicheDePoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheDePoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheDePoste");
    }
  }
  
  public NSArray tosRepartNiveauComp() {
    return (NSArray)storedValueForKey("tosRepartNiveauComp");
  }

  public NSArray tosRepartNiveauComp(EOQualifier qualifier) {
    return tosRepartNiveauComp(qualifier, null, false);
  }

  public NSArray tosRepartNiveauComp(EOQualifier qualifier, boolean fetch) {
    return tosRepartNiveauComp(qualifier, null, fetch);
  }

  public NSArray tosRepartNiveauComp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp.TO_REPART_FDP_AUTRE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp.fetchRepartNiveauComps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartNiveauComp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartNiveauCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartNiveauComp");
  }

  public void removeFromTosRepartNiveauCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartNiveauComp");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp createTosRepartNiveauCompRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartNiveauComp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartNiveauComp");
    return (org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp) eo;
  }

  public void deleteTosRepartNiveauCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartNiveauComp");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartNiveauCompRelationships() {
    Enumeration objects = tosRepartNiveauComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartNiveauCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp)objects.nextElement());
    }
  }


  public static EORepartFdpAutre createRepartFdpAutre(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String fauChampLibre
, String fauType
, org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste) {
    EORepartFdpAutre eo = (EORepartFdpAutre) EOUtilities.createAndInsertInstance(editingContext, _EORepartFdpAutre.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setFauChampLibre(fauChampLibre);
		eo.setFauType(fauType);
    eo.setToFicheDePosteRelationship(toFicheDePoste);
    return eo;
  }

  public static NSArray fetchAllRepartFdpAutres(EOEditingContext editingContext) {
    return _EORepartFdpAutre.fetchAllRepartFdpAutres(editingContext, null);
  }

  public static NSArray fetchAllRepartFdpAutres(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFdpAutre.fetchRepartFdpAutres(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFdpAutres(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFdpAutre.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFdpAutre fetchRepartFdpAutre(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpAutre.fetchRepartFdpAutre(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpAutre fetchRepartFdpAutre(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFdpAutre.fetchRepartFdpAutres(editingContext, qualifier, null);
    EORepartFdpAutre eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFdpAutre)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFdpAutre that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpAutre fetchRequiredRepartFdpAutre(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpAutre.fetchRequiredRepartFdpAutre(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpAutre fetchRequiredRepartFdpAutre(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFdpAutre eoObject = _EORepartFdpAutre.fetchRepartFdpAutre(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFdpAutre that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpAutre localInstanceIn(EOEditingContext editingContext, EORepartFdpAutre eo) {
    EORepartFdpAutre localInstance = (eo == null) ? null : (EORepartFdpAutre)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
