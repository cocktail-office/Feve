// _EORepartFdpComp.java
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

// DO NOT EDIT.  Make changes to EORepartFdpComp.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFdpComp extends A_CanBeDeleted  {
	public static final String ENTITY_NAME = "RepartFdpComp";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FDP_COMP";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String RFC_POSITION_KEY = "rfcPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String RFC_POSITION_COLKEY = "RFC_POSITION";

	// Relationships
	public static final String TO_COMPETENCE_KEY = "toCompetence";
	public static final String TO_FICHE_DE_POSTE_KEY = "toFicheDePoste";
	public static final String TO_REFERENS_COMPETENCES_KEY = "toReferensCompetences";
	public static final String TOS_REPART_NIVEAU_COMP_KEY = "tosRepartNiveauComp";

	// Utilities methods
  public EORepartFdpComp localInstanceIn(EOEditingContext editingContext) {
    EORepartFdpComp localInstance = (EORepartFdpComp)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer rfcPosition() {
    return (Integer) storedValueForKey("rfcPosition");
  }

  public void setRfcPosition(Integer value) {
    takeStoredValueForKey(value, "rfcPosition");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOCompetence toCompetence() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOCompetence)storedValueForKey("toCompetence");
  }

  public void setToCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EOCompetence value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOCompetence oldValue = toCompetence();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toCompetence");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toCompetence");
    }
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp.TO_REPART_FDP_COMP_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
  }

  public void deleteAllTosRepartNiveauCompRelationships() {
    Enumeration objects = tosRepartNiveauComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartNiveauCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp)objects.nextElement());
    }
  }


  public static EORepartFdpComp createRepartFdpComp(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste) {
    EORepartFdpComp eo = (EORepartFdpComp) EOUtilities.createAndInsertInstance(editingContext, _EORepartFdpComp.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToFicheDePosteRelationship(toFicheDePoste);
    return eo;
  }

  public static NSArray fetchAllRepartFdpComps(EOEditingContext editingContext) {
    return _EORepartFdpComp.fetchAllRepartFdpComps(editingContext, null);
  }

  public static NSArray fetchAllRepartFdpComps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFdpComp.fetchRepartFdpComps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFdpComps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFdpComp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFdpComp fetchRepartFdpComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpComp.fetchRepartFdpComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpComp fetchRepartFdpComp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFdpComp.fetchRepartFdpComps(editingContext, qualifier, null);
    EORepartFdpComp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFdpComp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFdpComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpComp fetchRequiredRepartFdpComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpComp.fetchRequiredRepartFdpComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpComp fetchRequiredRepartFdpComp(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFdpComp eoObject = _EORepartFdpComp.fetchRepartFdpComp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFdpComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpComp localInstanceIn(EOEditingContext editingContext, EORepartFdpComp eo) {
    EORepartFdpComp localInstance = (eo == null) ? null : (EORepartFdpComp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
