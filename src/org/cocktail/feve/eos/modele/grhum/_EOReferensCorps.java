// _EOReferensCorps.java
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

// DO NOT EDIT.  Make changes to EOReferensCorps.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensCorps extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensCorps";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_CORPS";

	// Attributes
	public static final String CATEGORIE_KEY = "categorie";
	public static final String CODECORPS_KEY = "codecorps";
	public static final String CODGRADEECORPS_KEY = "codgradeecorps";
	public static final String FILIERE_KEY = "filiere";
	public static final String INTITULCORPS_KEY = "intitulcorps";
	public static final String SIGLECORPS_KEY = "siglecorps";
	public static final String STATUT_KEY = "statut";

	public static final String CATEGORIE_COLKEY = "CATEGORIE";
	public static final String CODECORPS_COLKEY = "CODECORPS";
	public static final String CODGRADEECORPS_COLKEY = "CODGRADEECORPS";
	public static final String FILIERE_COLKEY = "FILIERE";
	public static final String INTITULCORPS_COLKEY = "INTITULCORPS";
	public static final String SIGLECORPS_COLKEY = "SIGLECORPS";
	public static final String STATUT_COLKEY = "STATUT";

	// Relationships
	public static final String TOS_REFERENS_EMPLOIS_KEY = "tosReferensEmplois";

	// Utilities methods
  public EOReferensCorps localInstanceIn(EOEditingContext editingContext) {
    EOReferensCorps localInstance = (EOReferensCorps)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String categorie() {
    return (String) storedValueForKey("categorie");
  }

  public void setCategorie(String value) {
    takeStoredValueForKey(value, "categorie");
  }

  public String codecorps() {
    return (String) storedValueForKey("codecorps");
  }

  public void setCodecorps(String value) {
    takeStoredValueForKey(value, "codecorps");
  }

  public String codgradeecorps() {
    return (String) storedValueForKey("codgradeecorps");
  }

  public void setCodgradeecorps(String value) {
    takeStoredValueForKey(value, "codgradeecorps");
  }

  public String filiere() {
    return (String) storedValueForKey("filiere");
  }

  public void setFiliere(String value) {
    takeStoredValueForKey(value, "filiere");
  }

  public String intitulcorps() {
    return (String) storedValueForKey("intitulcorps");
  }

  public void setIntitulcorps(String value) {
    takeStoredValueForKey(value, "intitulcorps");
  }

  public String siglecorps() {
    return (String) storedValueForKey("siglecorps");
  }

  public void setSiglecorps(String value) {
    takeStoredValueForKey(value, "siglecorps");
  }

  public String statut() {
    return (String) storedValueForKey("statut");
  }

  public void setStatut(String value) {
    takeStoredValueForKey(value, "statut");
  }

  public NSArray tosReferensEmplois() {
    return (NSArray)storedValueForKey("tosReferensEmplois");
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier) {
    return tosReferensEmplois(qualifier, null, false);
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier, boolean fetch) {
    return tosReferensEmplois(qualifier, null, fetch);
  }

  public NSArray tosReferensEmplois(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois.TO_REFERENS_CORPS_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOReferensEmplois.fetchReferensEmploises(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosReferensEmplois();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
  }

  public void removeFromTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensEmplois createTosReferensEmploisRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReferensEmplois");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReferensEmplois");
    return (org.cocktail.feve.eos.modele.grhum.EOReferensEmplois) eo;
  }

  public void deleteTosReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensEmplois");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReferensEmploisRelationships() {
    Enumeration objects = tosReferensEmplois().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReferensEmploisRelationship((org.cocktail.feve.eos.modele.grhum.EOReferensEmplois)objects.nextElement());
    }
  }


  public static EOReferensCorps createReferensCorps(EOEditingContext editingContext, String siglecorps
) {
    EOReferensCorps eo = (EOReferensCorps) EOUtilities.createAndInsertInstance(editingContext, _EOReferensCorps.ENTITY_NAME);    
		eo.setSiglecorps(siglecorps);
    return eo;
  }

  public static NSArray fetchAllReferensCorpses(EOEditingContext editingContext) {
    return _EOReferensCorps.fetchAllReferensCorpses(editingContext, null);
  }

  public static NSArray fetchAllReferensCorpses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensCorps.fetchReferensCorpses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensCorpses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensCorps.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensCorps fetchReferensCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensCorps.fetchReferensCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensCorps fetchReferensCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensCorps.fetchReferensCorpses(editingContext, qualifier, null);
    EOReferensCorps eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensCorps)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensCorps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensCorps fetchRequiredReferensCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensCorps.fetchRequiredReferensCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensCorps fetchRequiredReferensCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensCorps eoObject = _EOReferensCorps.fetchReferensCorps(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensCorps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensCorps localInstanceIn(EOEditingContext editingContext, EOReferensCorps eo) {
    EOReferensCorps localInstance = (eo == null) ? null : (EOReferensCorps)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
