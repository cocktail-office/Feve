// _EOEmploiType.java
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

// DO NOT EDIT.  Make changes to EOEmploiType.java instead.
package org.cocktail.feve.eos.modele.grhum.old;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOEmploiType extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "EmploiType";
	public static final String ENTITY_TABLE_NAME = "GRHUM.EMPLOI_TYPE_OLD";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String ETY_CODE_KEY = "etyCode";
	public static final String ETY_CODE_MEN_KEY = "etyCodeMen";
	public static final String ETY_DEFINITION_KEY = "etyDefinition";
	public static final String ETY_LIBELLE_KEY = "etyLibelle";
	public static final String ETY_SIGLE_CORPS_KEY = "etySigleCorps";
	public static final String FPR_KEY_KEY = "fprKey";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String ETY_CODE_COLKEY = "ETY_CODE";
	public static final String ETY_CODE_MEN_COLKEY = "ETY_CODE_MEN";
	public static final String ETY_DEFINITION_COLKEY = "ETY_DEFINITION";
	public static final String ETY_LIBELLE_COLKEY = "ETY_LIBELLE";
	public static final String ETY_SIGLE_CORPS_COLKEY = "ETY_SIGLE_CORPS";
	public static final String FPR_KEY_COLKEY = "FPR_KEY";

	// Relationships
	public static final String TO_FAMILLE_PROFESSIONNELLE_KEY = "toFamilleProfessionnelle";
	public static final String TOS_REPART_EMPLOI_ACTIVITE_KEY = "tosRepartEmploiActivite";
	public static final String TOS_REPART_EMPLOI_COMPETENCE_KEY = "tosRepartEmploiCompetence";

	// Utilities methods
  public EOEmploiType localInstanceIn(EOEditingContext editingContext) {
    EOEmploiType localInstance = (EOEmploiType)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String etyCode() {
    return (String) storedValueForKey("etyCode");
  }

  public void setEtyCode(String value) {
    takeStoredValueForKey(value, "etyCode");
  }

  public String etyCodeMen() {
    return (String) storedValueForKey("etyCodeMen");
  }

  public void setEtyCodeMen(String value) {
    takeStoredValueForKey(value, "etyCodeMen");
  }

  public String etyDefinition() {
    return (String) storedValueForKey("etyDefinition");
  }

  public void setEtyDefinition(String value) {
    takeStoredValueForKey(value, "etyDefinition");
  }

  public String etyLibelle() {
    return (String) storedValueForKey("etyLibelle");
  }

  public void setEtyLibelle(String value) {
    takeStoredValueForKey(value, "etyLibelle");
  }

  public String etySigleCorps() {
    return (String) storedValueForKey("etySigleCorps");
  }

  public void setEtySigleCorps(String value) {
    takeStoredValueForKey(value, "etySigleCorps");
  }

  public Integer fprKey() {
    return (Integer) storedValueForKey("fprKey");
  }

  public void setFprKey(Integer value) {
    takeStoredValueForKey(value, "fprKey");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle toFamilleProfessionnelle() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle)storedValueForKey("toFamilleProfessionnelle");
  }

  public void setToFamilleProfessionnelleRelationship(org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOFamilleProfessionnelle oldValue = toFamilleProfessionnelle();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFamilleProfessionnelle");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFamilleProfessionnelle");
    }
  }
  
  public NSArray tosRepartEmploiActivite() {
    return (NSArray)storedValueForKey("tosRepartEmploiActivite");
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier) {
    return tosRepartEmploiActivite(qualifier, null, false);
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier, boolean fetch) {
    return tosRepartEmploiActivite(qualifier, null, fetch);
  }

  public NSArray tosRepartEmploiActivite(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite.TO_EMPLOI_TYPE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite.fetchRepartEmploiActivites(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEmploiActivite();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
  }

  public void removeFromTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite createTosRepartEmploiActiviteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEmploiActivite");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEmploiActivite");
    return (org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite) eo;
  }

  public void deleteTosRepartEmploiActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEmploiActivite");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartEmploiActiviteRelationships() {
    Enumeration objects = tosRepartEmploiActivite().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEmploiActiviteRelationship((org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiActivite)objects.nextElement());
    }
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence.TO_EMPLOI_TYPE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartEmploiCompetenceRelationships() {
    Enumeration objects = tosRepartEmploiCompetence().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEmploiCompetenceRelationship((org.cocktail.feve.eos.modele.grhum.old.EORepartEmploiCompetence)objects.nextElement());
    }
  }


  public static EOEmploiType createEmploiType(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String etyCode
) {
    EOEmploiType eo = (EOEmploiType) EOUtilities.createAndInsertInstance(editingContext, _EOEmploiType.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setEtyCode(etyCode);
    return eo;
  }

  public static NSArray fetchAllEmploiTypes(EOEditingContext editingContext) {
    return _EOEmploiType.fetchAllEmploiTypes(editingContext, null);
  }

  public static NSArray fetchAllEmploiTypes(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOEmploiType.fetchEmploiTypes(editingContext, null, sortOrderings);
  }

  public static NSArray fetchEmploiTypes(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEmploiType.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEmploiType fetchEmploiType(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEmploiType.fetchEmploiType(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEmploiType fetchEmploiType(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOEmploiType.fetchEmploiTypes(editingContext, qualifier, null);
    EOEmploiType eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEmploiType)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EmploiType that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEmploiType fetchRequiredEmploiType(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEmploiType.fetchRequiredEmploiType(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEmploiType fetchRequiredEmploiType(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEmploiType eoObject = _EOEmploiType.fetchEmploiType(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EmploiType that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEmploiType localInstanceIn(EOEditingContext editingContext, EOEmploiType eo) {
    EOEmploiType localInstance = (eo == null) ? null : (EOEmploiType)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
