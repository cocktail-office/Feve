// _EODomaineCompetence.java
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

// DO NOT EDIT.  Make changes to EODomaineCompetence.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EODomaineCompetence extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "DomaineCompetence";
	public static final String ENTITY_TABLE_NAME = "GRHUM.DOMAINE_COMPETENCE_PRO_OLD";

	// Attributes
	public static final String DCO_LETTRE_KEY = "dcoLettre";
	public static final String DCO_LIBELLE_KEY = "dcoLibelle";
	public static final String DCO_SIGLE_KEY = "dcoSigle";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String DCO_LETTRE_COLKEY = "DCO_LETTRE";
	public static final String DCO_LIBELLE_COLKEY = "DCO_LIBELLE";
	public static final String DCO_SIGLE_COLKEY = "DCO_SIGLE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TOS_FAMILLE_PROFESSIONNELLE_KEY = "tosFamilleProfessionnelle";

	// Utilities methods
  public EODomaineCompetence localInstanceIn(EOEditingContext editingContext) {
    EODomaineCompetence localInstance = (EODomaineCompetence)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String dcoLettre() {
    return (String) storedValueForKey("dcoLettre");
  }

  public void setDcoLettre(String value) {
    takeStoredValueForKey(value, "dcoLettre");
  }

  public String dcoLibelle() {
    return (String) storedValueForKey("dcoLibelle");
  }

  public void setDcoLibelle(String value) {
    takeStoredValueForKey(value, "dcoLibelle");
  }

  public String dcoSigle() {
    return (String) storedValueForKey("dcoSigle");
  }

  public void setDcoSigle(String value) {
    takeStoredValueForKey(value, "dcoSigle");
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

  public NSArray tosFamilleProfessionnelle() {
    return (NSArray)storedValueForKey("tosFamilleProfessionnelle");
  }

  public NSArray tosFamilleProfessionnelle(EOQualifier qualifier) {
    return tosFamilleProfessionnelle(qualifier, null, false);
  }

  public NSArray tosFamilleProfessionnelle(EOQualifier qualifier, boolean fetch) {
    return tosFamilleProfessionnelle(qualifier, null, fetch);
  }

  public NSArray tosFamilleProfessionnelle(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle.TO_DOMAINE_COMPETENCE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle.fetchFamilleProfessionnelles(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosFamilleProfessionnelle();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosFamilleProfessionnelleRelationship(org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosFamilleProfessionnelle");
  }

  public void removeFromTosFamilleProfessionnelleRelationship(org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFamilleProfessionnelle");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle createTosFamilleProfessionnelleRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("FamilleProfessionnelle");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosFamilleProfessionnelle");
    return (org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle) eo;
  }

  public void deleteTosFamilleProfessionnelleRelationship(org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFamilleProfessionnelle");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosFamilleProfessionnelleRelationships() {
    Enumeration objects = tosFamilleProfessionnelle().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosFamilleProfessionnelleRelationship((org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle)objects.nextElement());
    }
  }


  public static EODomaineCompetence createDomaineCompetence(EOEditingContext editingContext, String dcoLettre
, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EODomaineCompetence eo = (EODomaineCompetence) EOUtilities.createAndInsertInstance(editingContext, _EODomaineCompetence.ENTITY_NAME);    
		eo.setDcoLettre(dcoLettre);
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllDomaineCompetences(EOEditingContext editingContext) {
    return _EODomaineCompetence.fetchAllDomaineCompetences(editingContext, null);
  }

  public static NSArray fetchAllDomaineCompetences(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EODomaineCompetence.fetchDomaineCompetences(editingContext, null, sortOrderings);
  }

  public static NSArray fetchDomaineCompetences(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EODomaineCompetence.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EODomaineCompetence fetchDomaineCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EODomaineCompetence.fetchDomaineCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODomaineCompetence fetchDomaineCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EODomaineCompetence.fetchDomaineCompetences(editingContext, qualifier, null);
    EODomaineCompetence eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EODomaineCompetence)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one DomaineCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODomaineCompetence fetchRequiredDomaineCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EODomaineCompetence.fetchRequiredDomaineCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODomaineCompetence fetchRequiredDomaineCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    EODomaineCompetence eoObject = _EODomaineCompetence.fetchDomaineCompetence(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no DomaineCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODomaineCompetence localInstanceIn(EOEditingContext editingContext, EODomaineCompetence eo) {
    EODomaineCompetence localInstance = (eo == null) ? null : (EODomaineCompetence)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
