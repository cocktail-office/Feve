// _EOPoste.java
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

// DO NOT EDIT.  Make changes to EOPoste.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOPoste extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Poste";
	public static final String ENTITY_TABLE_NAME = "MANGUE.POSTE";

	// Attributes
	public static final String C_STRUCTURE_KEY = "cStructure";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String POS_CODE_KEY = "posCode";
	public static final String POS_D_DEBUT_KEY = "posDDebut";
	public static final String POS_D_FIN_KEY = "posDFin";
	public static final String POS_LIBELLE_KEY = "posLibelle";
	public static final String TEM_VALIDE_KEY = "temValide";

	public static final String C_STRUCTURE_COLKEY = "C_STRUCTURE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String POS_CODE_COLKEY = "POS_CODE";
	public static final String POS_D_DEBUT_COLKEY = "POS_D_DEBUT";
	public static final String POS_D_FIN_COLKEY = "POS_D_FIN";
	public static final String POS_LIBELLE_COLKEY = "POS_LIBELLE";
	public static final String TEM_VALIDE_COLKEY = "TEM_VALIDE";

	// Relationships
	public static final String TOS_AFFECTATION_DETAIL_KEY = "tosAffectationDetail";
	public static final String TOS_FICHE_DE_POSTE_KEY = "tosFicheDePoste";
	public static final String TOS_FICHE_LOLF_KEY = "tosFicheLolf";
	public static final String TO_STRUCTURE_KEY = "toStructure";

	// Utilities methods
  public EOPoste localInstanceIn(EOEditingContext editingContext) {
    EOPoste localInstance = (EOPoste)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cStructure() {
    return (String) storedValueForKey("cStructure");
  }

  public void setCStructure(String value) {
    takeStoredValueForKey(value, "cStructure");
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

  public String posCode() {
    return (String) storedValueForKey("posCode");
  }

  public void setPosCode(String value) {
    takeStoredValueForKey(value, "posCode");
  }

  public NSTimestamp posDDebut() {
    return (NSTimestamp) storedValueForKey("posDDebut");
  }

  public void setPosDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "posDDebut");
  }

  public NSTimestamp posDFin() {
    return (NSTimestamp) storedValueForKey("posDFin");
  }

  public void setPosDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "posDFin");
  }

  public String posLibelle() {
    return (String) storedValueForKey("posLibelle");
  }

  public void setPosLibelle(String value) {
    takeStoredValueForKey(value, "posLibelle");
  }

  public String temValide() {
    return (String) storedValueForKey("temValide");
  }

  public void setTemValide(String value) {
    takeStoredValueForKey(value, "temValide");
  }

  public org.cocktail.feve.eos.modele.grhum.EOStructure toStructure() {
    return (org.cocktail.feve.eos.modele.grhum.EOStructure)storedValueForKey("toStructure");
  }

  public void setToStructureRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOStructure oldValue = toStructure();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toStructure");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toStructure");
    }
  }
  
  public NSArray tosAffectationDetail() {
    return (NSArray)storedValueForKey("tosAffectationDetail");
  }

  public NSArray tosAffectationDetail(EOQualifier qualifier) {
    return tosAffectationDetail(qualifier, null, false);
  }

  public NSArray tosAffectationDetail(EOQualifier qualifier, boolean fetch) {
    return tosAffectationDetail(qualifier, null, fetch);
  }

  public NSArray tosAffectationDetail(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail.TO_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOAffectationDetail.fetchAffectationDetails(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosAffectationDetail();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
  }

  public void removeFromTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
  }

  public org.cocktail.feve.eos.modele.mangue.EOAffectationDetail createTosAffectationDetailRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AffectationDetail");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosAffectationDetail");
    return (org.cocktail.feve.eos.modele.mangue.EOAffectationDetail) eo;
  }

  public void deleteTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosAffectationDetailRelationships() {
    Enumeration objects = tosAffectationDetail().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosAffectationDetailRelationship((org.cocktail.feve.eos.modele.mangue.EOAffectationDetail)objects.nextElement());
    }
  }

  public NSArray tosFicheDePoste() {
    return (NSArray)storedValueForKey("tosFicheDePoste");
  }

  public NSArray tosFicheDePoste(EOQualifier qualifier) {
    return tosFicheDePoste(qualifier, null, false);
  }

  public NSArray tosFicheDePoste(EOQualifier qualifier, boolean fetch) {
    return tosFicheDePoste(qualifier, null, fetch);
  }

  public NSArray tosFicheDePoste(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste.TO_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOFicheDePoste.fetchFicheDePostes(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosFicheDePoste();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosFicheDePoste");
  }

  public void removeFromTosFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFicheDePoste");
  }

  public org.cocktail.feve.eos.modele.mangue.EOFicheDePoste createTosFicheDePosteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("FicheDePoste");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosFicheDePoste");
    return (org.cocktail.feve.eos.modele.mangue.EOFicheDePoste) eo;
  }

  public void deleteTosFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFicheDePoste");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosFicheDePosteRelationships() {
    Enumeration objects = tosFicheDePoste().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosFicheDePosteRelationship((org.cocktail.feve.eos.modele.mangue.EOFicheDePoste)objects.nextElement());
    }
  }

  public NSArray tosFicheLolf() {
    return (NSArray)storedValueForKey("tosFicheLolf");
  }

  public NSArray tosFicheLolf(EOQualifier qualifier) {
    return tosFicheLolf(qualifier, null, false);
  }

  public NSArray tosFicheLolf(EOQualifier qualifier, boolean fetch) {
    return tosFicheLolf(qualifier, null, fetch);
  }

  public NSArray tosFicheLolf(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOFicheLolf.TO_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOFicheLolf.fetchFicheLolfs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosFicheLolf();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosFicheLolf");
  }

  public void removeFromTosFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFicheLolf");
  }

  public org.cocktail.feve.eos.modele.mangue.EOFicheLolf createTosFicheLolfRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("FicheLolf");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosFicheLolf");
    return (org.cocktail.feve.eos.modele.mangue.EOFicheLolf) eo;
  }

  public void deleteTosFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFicheLolf");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosFicheLolfRelationships() {
    Enumeration objects = tosFicheLolf().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosFicheLolfRelationship((org.cocktail.feve.eos.modele.mangue.EOFicheLolf)objects.nextElement());
    }
  }


  public static EOPoste createPoste(EOEditingContext editingContext, String cStructure
, NSTimestamp dCreation
, NSTimestamp dModification
, String posCode
, NSTimestamp posDDebut
, String posLibelle
, org.cocktail.feve.eos.modele.grhum.EOStructure toStructure) {
    EOPoste eo = (EOPoste) EOUtilities.createAndInsertInstance(editingContext, _EOPoste.ENTITY_NAME);    
		eo.setCStructure(cStructure);
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setPosCode(posCode);
		eo.setPosDDebut(posDDebut);
		eo.setPosLibelle(posLibelle);
    eo.setToStructureRelationship(toStructure);
    return eo;
  }

  public static NSArray fetchAllPostes(EOEditingContext editingContext) {
    return _EOPoste.fetchAllPostes(editingContext, null);
  }

  public static NSArray fetchAllPostes(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOPoste.fetchPostes(editingContext, null, sortOrderings);
  }

  public static NSArray fetchPostes(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOPoste.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOPoste fetchPoste(EOEditingContext editingContext, String keyName, Object value) {
    return _EOPoste.fetchPoste(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOPoste fetchPoste(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOPoste.fetchPostes(editingContext, qualifier, null);
    EOPoste eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOPoste)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Poste that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOPoste fetchRequiredPoste(EOEditingContext editingContext, String keyName, Object value) {
    return _EOPoste.fetchRequiredPoste(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOPoste fetchRequiredPoste(EOEditingContext editingContext, EOQualifier qualifier) {
    EOPoste eoObject = _EOPoste.fetchPoste(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Poste that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOPoste localInstanceIn(EOEditingContext editingContext, EOPoste eo) {
    EOPoste localInstance = (eo == null) ? null : (EOPoste)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchPoste(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchPoste", "Poste");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}
public static NSArray fetchFetchSuivi(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchSuivi", "Poste");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
