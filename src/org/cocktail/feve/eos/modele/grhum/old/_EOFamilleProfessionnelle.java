// _EOFamilleProfessionnelle.java
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

// DO NOT EDIT.  Make changes to EOFamilleProfessionnelle.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOFamilleProfessionnelle extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "FamilleProfessionnelle";
	public static final String ENTITY_TABLE_NAME = "GRHUM.FAMILLE_PROFESSIONNELLE_OLD";

	// Attributes
	public static final String DCO_KEY_KEY = "dcoKey";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String FPR_LIBELLE_KEY = "fprLibelle";

	public static final String DCO_KEY_COLKEY = "DCO_KEY";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String FPR_LIBELLE_COLKEY = "FPR_LIBELLE";

	// Relationships
	public static final String TO_DOMAINE_COMPETENCE_KEY = "toDomaineCompetence";
	public static final String TOS_EMPLOI_TYPE_KEY = "tosEmploiType";

	// Utilities methods
  public EOFamilleProfessionnelle localInstanceIn(EOEditingContext editingContext) {
    EOFamilleProfessionnelle localInstance = (EOFamilleProfessionnelle)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public Integer dcoKey() {
    return (Integer) storedValueForKey("dcoKey");
  }

  public void setDcoKey(Integer value) {
    takeStoredValueForKey(value, "dcoKey");
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

  public String fprLibelle() {
    return (String) storedValueForKey("fprLibelle");
  }

  public void setFprLibelle(String value) {
    takeStoredValueForKey(value, "fprLibelle");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EODomaineCompetence toDomaineCompetence() {
    return (org.cocktail.feve.eos.modele.grhum.old.EODomaineCompetence)storedValueForKey("toDomaineCompetence");
  }

  public void setToDomaineCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EODomaineCompetence value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EODomaineCompetence oldValue = toDomaineCompetence();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDomaineCompetence");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDomaineCompetence");
    }
  }
  
  public NSArray tosEmploiType() {
    return (NSArray)storedValueForKey("tosEmploiType");
  }

  public NSArray tosEmploiType(EOQualifier qualifier) {
    return tosEmploiType(qualifier, null, false);
  }

  public NSArray tosEmploiType(EOQualifier qualifier, boolean fetch) {
    return tosEmploiType(qualifier, null, fetch);
  }

  public NSArray tosEmploiType(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType.TO_FAMILLE_PROFESSIONNELLE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.old.EOEmploiType.fetchEmploiTypes(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosEmploiType();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
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


  public static EOFamilleProfessionnelle createFamilleProfessionnelle(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EOFamilleProfessionnelle eo = (EOFamilleProfessionnelle) EOUtilities.createAndInsertInstance(editingContext, _EOFamilleProfessionnelle.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllFamilleProfessionnelles(EOEditingContext editingContext) {
    return _EOFamilleProfessionnelle.fetchAllFamilleProfessionnelles(editingContext, null);
  }

  public static NSArray fetchAllFamilleProfessionnelles(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOFamilleProfessionnelle.fetchFamilleProfessionnelles(editingContext, null, sortOrderings);
  }

  public static NSArray fetchFamilleProfessionnelles(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOFamilleProfessionnelle.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOFamilleProfessionnelle fetchFamilleProfessionnelle(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFamilleProfessionnelle.fetchFamilleProfessionnelle(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFamilleProfessionnelle fetchFamilleProfessionnelle(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOFamilleProfessionnelle.fetchFamilleProfessionnelles(editingContext, qualifier, null);
    EOFamilleProfessionnelle eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOFamilleProfessionnelle)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FamilleProfessionnelle that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFamilleProfessionnelle fetchRequiredFamilleProfessionnelle(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFamilleProfessionnelle.fetchRequiredFamilleProfessionnelle(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFamilleProfessionnelle fetchRequiredFamilleProfessionnelle(EOEditingContext editingContext, EOQualifier qualifier) {
    EOFamilleProfessionnelle eoObject = _EOFamilleProfessionnelle.fetchFamilleProfessionnelle(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FamilleProfessionnelle that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFamilleProfessionnelle localInstanceIn(EOEditingContext editingContext, EOFamilleProfessionnelle eo) {
    EOFamilleProfessionnelle localInstance = (eo == null) ? null : (EOFamilleProfessionnelle)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
