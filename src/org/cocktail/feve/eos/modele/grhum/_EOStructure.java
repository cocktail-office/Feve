// _EOStructure.java
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

// DO NOT EDIT.  Make changes to EOStructure.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOStructure extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Structure";
	public static final String ENTITY_TABLE_NAME = "GRHUM.STRUCTURE_ULR";

	// Attributes
	public static final String C_TYPE_ETABLISSEMEN_KEY = "cTypeEtablissemen";
	public static final String C_TYPE_STRUCTURE_KEY = "cTypeStructure";
	public static final String IS_STR_ARCHIVE_KEY = "isStrArchive";
	public static final String IS_STR_COMPOSANTE_KEY = "isStrComposante";
	public static final String IS_STR_SERVICE_KEY = "isStrService";
	public static final String LC_STRUCTURE_KEY = "lcStructure";
	public static final String LL_STRUCTURE_KEY = "llStructure";
	public static final String PERS_ID_KEY = "persId";

	public static final String C_TYPE_ETABLISSEMEN_COLKEY = "C_TYPE_ETABLISSEMEN";
	public static final String C_TYPE_STRUCTURE_COLKEY = "C_TYPE_STRUCTURE";
	public static final String IS_STR_ARCHIVE_COLKEY = "isStrArchive";
	public static final String IS_STR_COMPOSANTE_COLKEY = "isStrComposante";
	public static final String IS_STR_SERVICE_COLKEY = "isStrService";
	public static final String LC_STRUCTURE_COLKEY = "LC_STRUCTURE";
	public static final String LL_STRUCTURE_COLKEY = "LL_STRUCTURE";
	public static final String PERS_ID_COLKEY = "PERS_ID";

	// Relationships
	public static final String TO_COMPOSANTE_KEY = "toComposante";
	public static final String TO_RESPONSABLE_KEY = "toResponsable";
	public static final String TOS_AFFECTATION_KEY = "tosAffectation";
	public static final String TOS_INDIVIDU_AFFECTE_KEY = "tosIndividuAffecte";
	public static final String TOS_POSTE_KEY = "tosPoste";
	public static final String TOS_REPART_TYPE_GROUPE_KEY = "tosRepartTypeGroupe";
	public static final String TOS_STRUCTURES_FILLES_KEY = "tosStructuresFilles";
	public static final String TO_STRUCTURE_PERE_KEY = "toStructurePere";
	public static final String TO_V_SERVICE_KEY = "toVService";

	// Utilities methods
  public EOStructure localInstanceIn(EOEditingContext editingContext) {
    EOStructure localInstance = (EOStructure)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cTypeEtablissemen() {
    return (String) storedValueForKey("cTypeEtablissemen");
  }

  public void setCTypeEtablissemen(String value) {
    takeStoredValueForKey(value, "cTypeEtablissemen");
  }

  public String cTypeStructure() {
    return (String) storedValueForKey("cTypeStructure");
  }

  public void setCTypeStructure(String value) {
    takeStoredValueForKey(value, "cTypeStructure");
  }

  public String isStrArchive() {
    return (String) storedValueForKey("isStrArchive");
  }

  public void setIsStrArchive(String value) {
    takeStoredValueForKey(value, "isStrArchive");
  }

  public String isStrComposante() {
    return (String) storedValueForKey("isStrComposante");
  }

  public void setIsStrComposante(String value) {
    takeStoredValueForKey(value, "isStrComposante");
  }

  public String isStrService() {
    return (String) storedValueForKey("isStrService");
  }

  public void setIsStrService(String value) {
    takeStoredValueForKey(value, "isStrService");
  }

  public String lcStructure() {
    return (String) storedValueForKey("lcStructure");
  }

  public void setLcStructure(String value) {
    takeStoredValueForKey(value, "lcStructure");
  }

  public String llStructure() {
    return (String) storedValueForKey("llStructure");
  }

  public void setLlStructure(String value) {
    takeStoredValueForKey(value, "llStructure");
  }

  public Integer persId() {
    return (Integer) storedValueForKey("persId");
  }

  public void setPersId(Integer value) {
    takeStoredValueForKey(value, "persId");
  }

  public org.cocktail.feve.eos.modele.grhum.EOStructure toComposante() {
    return (org.cocktail.feve.eos.modele.grhum.EOStructure)storedValueForKey("toComposante");
  }

  public void setToComposanteRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOStructure oldValue = toComposante();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toComposante");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toComposante");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toResponsable() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toResponsable");
  }

  public void setToResponsableRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toResponsable();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toResponsable");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toResponsable");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOStructure toStructurePere() {
    return (org.cocktail.feve.eos.modele.grhum.EOStructure)storedValueForKey("toStructurePere");
  }

  public void setToStructurePereRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOStructure oldValue = toStructurePere();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toStructurePere");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toStructurePere");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOVService toVService() {
    return (org.cocktail.feve.eos.modele.grhum.EOVService)storedValueForKey("toVService");
  }

  public void setToVServiceRelationship(org.cocktail.feve.eos.modele.grhum.EOVService value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOVService oldValue = toVService();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVService");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVService");
    }
  }
  
  public NSArray tosAffectation() {
    return (NSArray)storedValueForKey("tosAffectation");
  }

  public NSArray tosAffectation(EOQualifier qualifier) {
    return tosAffectation(qualifier, null, false);
  }

  public NSArray tosAffectation(EOQualifier qualifier, boolean fetch) {
    return tosAffectation(qualifier, null, fetch);
  }

  public NSArray tosAffectation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOAffectation.TO_STRUCTURE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOAffectation.fetchAffectations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosAffectation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosAffectation");
  }

  public void removeFromTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOAffectation createTosAffectationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Affectation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosAffectation");
    return (org.cocktail.feve.eos.modele.mangue.EOAffectation) eo;
  }

  public void deleteTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectation");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosAffectationRelationships() {
    Enumeration objects = tosAffectation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosAffectationRelationship((org.cocktail.feve.eos.modele.mangue.EOAffectation)objects.nextElement());
    }
  }

  public NSArray tosIndividuAffecte() {
    return (NSArray)storedValueForKey("tosIndividuAffecte");
  }

  public NSArray tosIndividuAffecte(EOQualifier qualifier) {
    return tosIndividuAffecte(qualifier, null);
  }

  public NSArray tosIndividuAffecte(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosIndividuAffecte();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosIndividuAffecteRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosIndividuAffecte");
  }

  public void removeFromTosIndividuAffecteRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosIndividuAffecte");
  }

  public org.cocktail.feve.eos.modele.grhum.EOIndividu createTosIndividuAffecteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Individu");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosIndividuAffecte");
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu) eo;
  }

  public void deleteTosIndividuAffecteRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosIndividuAffecte");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosIndividuAffecteRelationships() {
    Enumeration objects = tosIndividuAffecte().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosIndividuAffecteRelationship((org.cocktail.feve.eos.modele.grhum.EOIndividu)objects.nextElement());
    }
  }

  public NSArray tosPoste() {
    return (NSArray)storedValueForKey("tosPoste");
  }

  public NSArray tosPoste(EOQualifier qualifier) {
    return tosPoste(qualifier, null, false);
  }

  public NSArray tosPoste(EOQualifier qualifier, boolean fetch) {
    return tosPoste(qualifier, null, fetch);
  }

  public NSArray tosPoste(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOPoste.TO_STRUCTURE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOPoste.fetchPostes(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosPoste();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosPoste");
  }

  public void removeFromTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosPoste");
  }

  public org.cocktail.feve.eos.modele.mangue.EOPoste createTosPosteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Poste");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosPoste");
    return (org.cocktail.feve.eos.modele.mangue.EOPoste) eo;
  }

  public void deleteTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosPoste");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosPosteRelationships() {
    Enumeration objects = tosPoste().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosPosteRelationship((org.cocktail.feve.eos.modele.mangue.EOPoste)objects.nextElement());
    }
  }

  public NSArray tosRepartTypeGroupe() {
    return (NSArray)storedValueForKey("tosRepartTypeGroupe");
  }

  public NSArray tosRepartTypeGroupe(EOQualifier qualifier) {
    return tosRepartTypeGroupe(qualifier, null);
  }

  public NSArray tosRepartTypeGroupe(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosRepartTypeGroupe();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosRepartTypeGroupeRelationship(org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartTypeGroupe");
  }

  public void removeFromTosRepartTypeGroupeRelationship(org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartTypeGroupe");
  }

  public org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe createTosRepartTypeGroupeRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartTypeGroupe");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartTypeGroupe");
    return (org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe) eo;
  }

  public void deleteTosRepartTypeGroupeRelationship(org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartTypeGroupe");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartTypeGroupeRelationships() {
    Enumeration objects = tosRepartTypeGroupe().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartTypeGroupeRelationship((org.cocktail.feve.eos.modele.grhum.EORepartTypeGroupe)objects.nextElement());
    }
  }

  public NSArray tosStructuresFilles() {
    return (NSArray)storedValueForKey("tosStructuresFilles");
  }

  public NSArray tosStructuresFilles(EOQualifier qualifier) {
    return tosStructuresFilles(qualifier, null, false);
  }

  public NSArray tosStructuresFilles(EOQualifier qualifier, boolean fetch) {
    return tosStructuresFilles(qualifier, null, fetch);
  }

  public NSArray tosStructuresFilles(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOStructure.TO_STRUCTURE_PERE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOStructure.fetchStructures(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosStructuresFilles();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosStructuresFillesRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosStructuresFilles");
  }

  public void removeFromTosStructuresFillesRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosStructuresFilles");
  }

  public org.cocktail.feve.eos.modele.grhum.EOStructure createTosStructuresFillesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Structure");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosStructuresFilles");
    return (org.cocktail.feve.eos.modele.grhum.EOStructure) eo;
  }

  public void deleteTosStructuresFillesRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosStructuresFilles");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosStructuresFillesRelationships() {
    Enumeration objects = tosStructuresFilles().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosStructuresFillesRelationship((org.cocktail.feve.eos.modele.grhum.EOStructure)objects.nextElement());
    }
  }


  public static EOStructure createStructure(EOEditingContext editingContext, String cTypeStructure
, String isStrArchive
, String isStrComposante
, String isStrService
, Integer persId
, org.cocktail.feve.eos.modele.grhum.EOVService toVService) {
    EOStructure eo = (EOStructure) EOUtilities.createAndInsertInstance(editingContext, _EOStructure.ENTITY_NAME);    
		eo.setCTypeStructure(cTypeStructure);
		eo.setIsStrArchive(isStrArchive);
		eo.setIsStrComposante(isStrComposante);
		eo.setIsStrService(isStrService);
		eo.setPersId(persId);
    eo.setToVServiceRelationship(toVService);
    return eo;
  }

  public static NSArray fetchAllStructures(EOEditingContext editingContext) {
    return _EOStructure.fetchAllStructures(editingContext, null);
  }

  public static NSArray fetchAllStructures(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOStructure.fetchStructures(editingContext, null, sortOrderings);
  }

  public static NSArray fetchStructures(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOStructure.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOStructure fetchStructure(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStructure.fetchStructure(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStructure fetchStructure(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOStructure.fetchStructures(editingContext, qualifier, null);
    EOStructure eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOStructure)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Structure that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStructure fetchRequiredStructure(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStructure.fetchRequiredStructure(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStructure fetchRequiredStructure(EOEditingContext editingContext, EOQualifier qualifier) {
    EOStructure eoObject = _EOStructure.fetchStructure(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Structure that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStructure localInstanceIn(EOEditingContext editingContext, EOStructure eo) {
    EOStructure localInstance = (eo == null) ? null : (EOStructure)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchComposante(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchComposante", "Structure");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
