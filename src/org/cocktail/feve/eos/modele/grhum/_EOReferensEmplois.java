// _EOReferensEmplois.java
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

// DO NOT EDIT.  Make changes to EOReferensEmplois.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensEmplois extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensEmplois";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_EMPLOIS";

	// Attributes
	public static final String CODEMEN_KEY = "codemen";
	public static final String DEFINITION_KEY = "definition";
	public static final String INTITULEMPLOI_KEY = "intitulemploi";
	public static final String LETTREBAP_KEY = "lettrebap";
	public static final String NUMBAP_KEY = "numbap";
	public static final String NUMDCP_KEY = "numdcp";
	public static final String NUMEMPLOI_KEY = "numemploi";
	public static final String NUMFP_KEY = "numfp";
	public static final String OUVERTCONCOURS_KEY = "ouvertconcours";
	public static final String SIGLECORPS_KEY = "siglecorps";

	public static final String CODEMEN_COLKEY = "CODEMEN";
	public static final String DEFINITION_COLKEY = "DEFINTION_CLEAN";
	public static final String INTITULEMPLOI_COLKEY = "INTITULEMPLOI";
	public static final String LETTREBAP_COLKEY = "LETTREBAP";
	public static final String NUMBAP_COLKEY = "NUMBAP";
	public static final String NUMDCP_COLKEY = "NUMDCP";
	public static final String NUMEMPLOI_COLKEY = "NUMEMPLOI";
	public static final String NUMFP_COLKEY = "NUMFP";
	public static final String OUVERTCONCOURS_COLKEY = "OUVERTCONCOURS";
	public static final String SIGLECORPS_COLKEY = "SIGLECORPS";

	// Relationships
	public static final String TO_REFERENS_CORPS_KEY = "toReferensCorps";
	public static final String TO_REFERENS_FP_KEY = "toReferensFp";
	public static final String TOS_REFERENS_ACTIVITES_KEY = "tosReferensActivites";
	public static final String TOS_REFERENS_COMPETENCES_KEY = "tosReferensCompetences";

	// Utilities methods
  public EOReferensEmplois localInstanceIn(EOEditingContext editingContext) {
    EOReferensEmplois localInstance = (EOReferensEmplois)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String codemen() {
    return (String) storedValueForKey("codemen");
  }

  public void setCodemen(String value) {
    takeStoredValueForKey(value, "codemen");
  }

  public String definition() {
    return (String) storedValueForKey("definition");
  }

  public void setDefinition(String value) {
    takeStoredValueForKey(value, "definition");
  }

  public String intitulemploi() {
    return (String) storedValueForKey("intitulemploi");
  }

  public void setIntitulemploi(String value) {
    takeStoredValueForKey(value, "intitulemploi");
  }

  public String lettrebap() {
    return (String) storedValueForKey("lettrebap");
  }

  public void setLettrebap(String value) {
    takeStoredValueForKey(value, "lettrebap");
  }

  public String numbap() {
    return (String) storedValueForKey("numbap");
  }

  public void setNumbap(String value) {
    takeStoredValueForKey(value, "numbap");
  }

  public String numdcp() {
    return (String) storedValueForKey("numdcp");
  }

  public void setNumdcp(String value) {
    takeStoredValueForKey(value, "numdcp");
  }

  public String numemploi() {
    return (String) storedValueForKey("numemploi");
  }

  public void setNumemploi(String value) {
    takeStoredValueForKey(value, "numemploi");
  }

  public String numfp() {
    return (String) storedValueForKey("numfp");
  }

  public void setNumfp(String value) {
    takeStoredValueForKey(value, "numfp");
  }

  public String ouvertconcours() {
    return (String) storedValueForKey("ouvertconcours");
  }

  public void setOuvertconcours(String value) {
    takeStoredValueForKey(value, "ouvertconcours");
  }

  public String siglecorps() {
    return (String) storedValueForKey("siglecorps");
  }

  public void setSiglecorps(String value) {
    takeStoredValueForKey(value, "siglecorps");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensCorps toReferensCorps() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensCorps)storedValueForKey("toReferensCorps");
  }

  public void setToReferensCorpsRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCorps value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensCorps oldValue = toReferensCorps();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensCorps");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensCorps");
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
  
  public NSArray tosReferensActivites() {
    return (NSArray)storedValueForKey("tosReferensActivites");
  }

  public NSArray tosReferensActivites(EOQualifier qualifier) {
    return tosReferensActivites(qualifier, null, false);
  }

  public NSArray tosReferensActivites(EOQualifier qualifier, boolean fetch) {
    return tosReferensActivites(qualifier, null, fetch);
  }

  public NSArray tosReferensActivites(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOReferensActivites.TO_REFERENS_EMPLOIS_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOReferensActivites.fetchReferensActiviteses(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosReferensActivites();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosReferensActivitesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensActivites object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReferensActivites");
  }

  public void removeFromTosReferensActivitesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensActivites object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensActivites");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensActivites createTosReferensActivitesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReferensActivites");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReferensActivites");
    return (org.cocktail.feve.eos.modele.grhum.EOReferensActivites) eo;
  }

  public void deleteTosReferensActivitesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensActivites object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensActivites");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReferensActivitesRelationships() {
    Enumeration objects = tosReferensActivites().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReferensActivitesRelationship((org.cocktail.feve.eos.modele.grhum.EOReferensActivites)objects.nextElement());
    }
  }

  public NSArray tosReferensCompetences() {
    return (NSArray)storedValueForKey("tosReferensCompetences");
  }

  public NSArray tosReferensCompetences(EOQualifier qualifier) {
    return tosReferensCompetences(qualifier, null, false);
  }

  public NSArray tosReferensCompetences(EOQualifier qualifier, boolean fetch) {
    return tosReferensCompetences(qualifier, null, fetch);
  }

  public NSArray tosReferensCompetences(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences.TO_REFERENS_EMPLOIS_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOReferensCompetences.fetchReferensCompetenceses(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosReferensCompetences();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosReferensCompetencesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReferensCompetences");
  }

  public void removeFromTosReferensCompetencesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensCompetences");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensCompetences createTosReferensCompetencesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReferensCompetences");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReferensCompetences");
    return (org.cocktail.feve.eos.modele.grhum.EOReferensCompetences) eo;
  }

  public void deleteTosReferensCompetencesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensCompetences object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensCompetences");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReferensCompetencesRelationships() {
    Enumeration objects = tosReferensCompetences().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReferensCompetencesRelationship((org.cocktail.feve.eos.modele.grhum.EOReferensCompetences)objects.nextElement());
    }
  }


  public static EOReferensEmplois createReferensEmplois(EOEditingContext editingContext) {
    EOReferensEmplois eo = (EOReferensEmplois) EOUtilities.createAndInsertInstance(editingContext, _EOReferensEmplois.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllReferensEmploises(EOEditingContext editingContext) {
    return _EOReferensEmplois.fetchAllReferensEmploises(editingContext, null);
  }

  public static NSArray fetchAllReferensEmploises(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensEmplois.fetchReferensEmploises(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensEmploises(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensEmplois.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensEmplois fetchReferensEmplois(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensEmplois.fetchReferensEmplois(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensEmplois fetchReferensEmplois(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensEmplois.fetchReferensEmploises(editingContext, qualifier, null);
    EOReferensEmplois eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensEmplois)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensEmplois that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensEmplois fetchRequiredReferensEmplois(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensEmplois.fetchRequiredReferensEmplois(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensEmplois fetchRequiredReferensEmplois(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensEmplois eoObject = _EOReferensEmplois.fetchReferensEmplois(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensEmplois that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensEmplois localInstanceIn(EOEditingContext editingContext, EOReferensEmplois eo) {
    EOReferensEmplois localInstance = (eo == null) ? null : (EOReferensEmplois)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchReferensEmploi(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchReferensEmploi", "ReferensEmplois");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
