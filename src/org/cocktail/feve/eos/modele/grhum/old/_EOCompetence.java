// _EOCompetence.java
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

// DO NOT EDIT.  Make changes to EOCompetence.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOCompetence extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Competence";
	public static final String ENTITY_TABLE_NAME = "GRHUM.COMPETENCE_PROFESSIONNELLE_OLD";

	// Attributes
	public static final String COM_LIBELLE_KEY = "comLibelle";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String COM_LIBELLE_COLKEY = "COM_LIBELLE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TOS_REPART_EMPLOI_COMPETENCE_KEY = "tosRepartEmploiCompetence";
	public static final String TOS_REPART_ENQ_COMP_KEY = "tosRepartEnqComp";

	// Utilities methods
  public EOCompetence localInstanceIn(EOEditingContext editingContext) {
    EOCompetence localInstance = (EOCompetence)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String comLibelle() {
    return (String) storedValueForKey("comLibelle");
  }

  public void setComLibelle(String value) {
    takeStoredValueForKey(value, "comLibelle");
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

  public NSArray tosRepartEmploiCompetence() {
    return (NSArray)storedValueForKey("tosRepartEmploiCompetence");
  }

  public NSArray tosRepartEmploiCompetence(EOQualifier qualifier) {
    return tosRepartEmploiCompetence(qualifier, null, false);
  }

  public NSArray tosRepartEmploiCompetence(EOQualifier qualifier, boolean fetch) {
    return tosRepartEmploiCompetence(qualifier, null, fetch);
  }

  public NSArray tosRepartEmploiCompetence(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence.TO_COMPETENCE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence.fetchRepartEmploiCompetences(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEmploiCompetence();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEmploiCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEmploiCompetence");
  }

  public void removeFromTosRepartEmploiCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiCompetence");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence createTosRepartEmploiCompetenceRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEmploiCompetence");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEmploiCompetence");
    return (org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence) eo;
  }

  public void deleteTosRepartEmploiCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiCompetence");
  }

  public void deleteAllTosRepartEmploiCompetenceRelationships() {
    Enumeration objects = tosRepartEmploiCompetence().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEmploiCompetenceRelationship((org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence)objects.nextElement());
    }
  }

  public NSArray tosRepartEnqComp() {
    return (NSArray)storedValueForKey("tosRepartEnqComp");
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier) {
    return tosRepartEnqComp(qualifier, null, false);
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier, boolean fetch) {
    return tosRepartEnqComp(qualifier, null, fetch);
  }

  public NSArray tosRepartEnqComp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp.TO_COMPETENCE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartEnqComp.fetchRepartEnqComps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEnqComp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public void removeFromTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartEnqComp createTosRepartEnqCompRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEnqComp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEnqComp");
    return (org.cocktail.feve.eos.modele.mangue.EORepartEnqComp) eo;
  }

  public void deleteTosRepartEnqCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEnqComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEnqComp");
  }

  public void deleteAllTosRepartEnqCompRelationships() {
    Enumeration objects = tosRepartEnqComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEnqCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartEnqComp)objects.nextElement());
    }
  }


  public static EOCompetence createCompetence(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EOCompetence eo = (EOCompetence) EOUtilities.createAndInsertInstance(editingContext, _EOCompetence.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllCompetences(EOEditingContext editingContext) {
    return _EOCompetence.fetchAllCompetences(editingContext, null);
  }

  public static NSArray fetchAllCompetences(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOCompetence.fetchCompetences(editingContext, null, sortOrderings);
  }

  public static NSArray fetchCompetences(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOCompetence.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOCompetence fetchCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EOCompetence.fetchCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOCompetence fetchCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOCompetence.fetchCompetences(editingContext, qualifier, null);
    EOCompetence eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOCompetence)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Competence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOCompetence fetchRequiredCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EOCompetence.fetchRequiredCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOCompetence fetchRequiredCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    EOCompetence eoObject = _EOCompetence.fetchCompetence(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Competence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOCompetence localInstanceIn(EOEditingContext editingContext, EOCompetence eo) {
    EOCompetence localInstance = (eo == null) ? null : (EOCompetence)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
