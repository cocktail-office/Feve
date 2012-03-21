// _EOEvaluationPeriode.java
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

// DO NOT EDIT.  Make changes to EOEvaluationPeriode.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOEvaluationPeriode extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "EvaluationPeriode";
	public static final String ENTITY_TABLE_NAME = "MANGUE.EVALUATION_PERIODE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String EPE_D_DEBUT_KEY = "epeDDebut";
	public static final String EPE_D_FIN_KEY = "epeDFin";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String EPE_D_DEBUT_COLKEY = "EPE_D_DEBUT";
	public static final String EPE_D_FIN_COLKEY = "EPE_D_FIN";

	// Relationships
	public static final String TOS_DROIT_KEY = "tosDroit";
	public static final String TOS_EVALUATION_KEY = "tosEvaluation";
	public static final String TOS_HIERARCHIE_KEY = "tosHierarchie";

	// Utilities methods
  public EOEvaluationPeriode localInstanceIn(EOEditingContext editingContext) {
    EOEvaluationPeriode localInstance = (EOEvaluationPeriode)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp epeDDebut() {
    return (NSTimestamp) storedValueForKey("epeDDebut");
  }

  public void setEpeDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "epeDDebut");
  }

  public NSTimestamp epeDFin() {
    return (NSTimestamp) storedValueForKey("epeDFin");
  }

  public void setEpeDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "epeDFin");
  }

  public NSArray tosDroit() {
    return (NSArray)storedValueForKey("tosDroit");
  }

  public NSArray tosDroit(EOQualifier qualifier) {
    return tosDroit(qualifier, null, false);
  }

  public NSArray tosDroit(EOQualifier qualifier, boolean fetch) {
    return tosDroit(qualifier, null, fetch);
  }

  public NSArray tosDroit(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EODroit.TO_DROIT_EVALUATION_PERIODE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EODroit.fetchDroits(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosDroit();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosDroitRelationship(org.cocktail.feve.eos.modele.mangue.EODroit object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosDroit");
  }

  public void removeFromTosDroitRelationship(org.cocktail.feve.eos.modele.mangue.EODroit object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosDroit");
  }

  public org.cocktail.feve.eos.modele.mangue.EODroit createTosDroitRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Droit");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosDroit");
    return (org.cocktail.feve.eos.modele.mangue.EODroit) eo;
  }

  public void deleteTosDroitRelationship(org.cocktail.feve.eos.modele.mangue.EODroit object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosDroit");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosDroitRelationships() {
    Enumeration objects = tosDroit().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosDroitRelationship((org.cocktail.feve.eos.modele.mangue.EODroit)objects.nextElement());
    }
  }

  public NSArray tosEvaluation() {
    return (NSArray)storedValueForKey("tosEvaluation");
  }

  public NSArray tosEvaluation(EOQualifier qualifier) {
    return tosEvaluation(qualifier, null, false);
  }

  public NSArray tosEvaluation(EOQualifier qualifier, boolean fetch) {
    return tosEvaluation(qualifier, null, fetch);
  }

  public NSArray tosEvaluation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOEvaluation.TO_EVALUATION_PERIODE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOEvaluation.fetchEvaluations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosEvaluation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosEvaluation");
  }

  public void removeFromTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluation createTosEvaluationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Evaluation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosEvaluation");
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluation) eo;
  }

  public void deleteTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluation");
  }

  public void deleteAllTosEvaluationRelationships() {
    Enumeration objects = tosEvaluation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosEvaluationRelationship((org.cocktail.feve.eos.modele.mangue.EOEvaluation)objects.nextElement());
    }
  }

  public NSArray tosHierarchie() {
    return (NSArray)storedValueForKey("tosHierarchie");
  }

  public NSArray tosHierarchie(EOQualifier qualifier) {
    return tosHierarchie(qualifier, null, false);
  }

  public NSArray tosHierarchie(EOQualifier qualifier, boolean fetch) {
    return tosHierarchie(qualifier, null, fetch);
  }

  public NSArray tosHierarchie(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOHierarchie.TO_EVALUATION_PERIODE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOHierarchie.fetchHierarchies(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosHierarchie();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosHierarchieRelationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosHierarchie");
  }

  public void removeFromTosHierarchieRelationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchie");
  }

  public org.cocktail.feve.eos.modele.mangue.EOHierarchie createTosHierarchieRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Hierarchie");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosHierarchie");
    return (org.cocktail.feve.eos.modele.mangue.EOHierarchie) eo;
  }

  public void deleteTosHierarchieRelationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchie");
  }

  public void deleteAllTosHierarchieRelationships() {
    Enumeration objects = tosHierarchie().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosHierarchieRelationship((org.cocktail.feve.eos.modele.mangue.EOHierarchie)objects.nextElement());
    }
  }


  public static EOEvaluationPeriode createEvaluationPeriode(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, NSTimestamp epeDDebut
, NSTimestamp epeDFin
) {
    EOEvaluationPeriode eo = (EOEvaluationPeriode) EOUtilities.createAndInsertInstance(editingContext, _EOEvaluationPeriode.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setEpeDDebut(epeDDebut);
		eo.setEpeDFin(epeDFin);
    return eo;
  }

  public static NSArray fetchAllEvaluationPeriodes(EOEditingContext editingContext) {
    return _EOEvaluationPeriode.fetchAllEvaluationPeriodes(editingContext, null);
  }

  public static NSArray fetchAllEvaluationPeriodes(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOEvaluationPeriode.fetchEvaluationPeriodes(editingContext, null, sortOrderings);
  }

  public static NSArray fetchEvaluationPeriodes(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEvaluationPeriode.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEvaluationPeriode fetchEvaluationPeriode(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluationPeriode.fetchEvaluationPeriode(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluationPeriode fetchEvaluationPeriode(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOEvaluationPeriode.fetchEvaluationPeriodes(editingContext, qualifier, null);
    EOEvaluationPeriode eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEvaluationPeriode)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EvaluationPeriode that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluationPeriode fetchRequiredEvaluationPeriode(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluationPeriode.fetchRequiredEvaluationPeriode(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluationPeriode fetchRequiredEvaluationPeriode(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEvaluationPeriode eoObject = _EOEvaluationPeriode.fetchEvaluationPeriode(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EvaluationPeriode that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluationPeriode localInstanceIn(EOEditingContext editingContext, EOEvaluationPeriode eo) {
    EOEvaluationPeriode localInstance = (eo == null) ? null : (EOEvaluationPeriode)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
