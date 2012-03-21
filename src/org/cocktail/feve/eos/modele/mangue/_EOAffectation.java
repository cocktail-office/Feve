// _EOAffectation.java
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

// DO NOT EDIT.  Make changes to EOAffectation.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOAffectation extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Affectation";
	public static final String ENTITY_TABLE_NAME = "MANGUE.AFFECTATION";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_AFFECTATION_KEY = "dDebAffectation";
	public static final String DEN_QUOT_AFFECTATION_KEY = "denQuotAffectation";
	public static final String D_FIN_AFFECTATION_KEY = "dFinAffectation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String NO_OCCUPATION_KEY = "noOccupation";
	public static final String NUM_QUOT_AFFECTATION_KEY = "numQuotAffectation";
	public static final String TEM_VALIDE_KEY = "temValide";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_AFFECTATION_COLKEY = "D_DEB_AFFECTATION";
	public static final String DEN_QUOT_AFFECTATION_COLKEY = "DEN_QUOT_AFFECTATION";
	public static final String D_FIN_AFFECTATION_COLKEY = "D_FIN_AFFECTATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String NO_OCCUPATION_COLKEY = "NO_OCCUPATION";
	public static final String NUM_QUOT_AFFECTATION_COLKEY = "NUM_QUOT_AFFECTATION";
	public static final String TEM_VALIDE_COLKEY = "TEM_VALIDE";

	// Relationships
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TOS_AFFECTATION_DETAIL_KEY = "tosAffectationDetail";
	public static final String TO_STRUCTURE_KEY = "toStructure";

	// Utilities methods
  public EOAffectation localInstanceIn(EOEditingContext editingContext) {
    EOAffectation localInstance = (EOAffectation)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dDebAffectation() {
    return (NSTimestamp) storedValueForKey("dDebAffectation");
  }

  public void setDDebAffectation(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebAffectation");
  }

  public Integer denQuotAffectation() {
    return (Integer) storedValueForKey("denQuotAffectation");
  }

  public void setDenQuotAffectation(Integer value) {
    takeStoredValueForKey(value, "denQuotAffectation");
  }

  public NSTimestamp dFinAffectation() {
    return (NSTimestamp) storedValueForKey("dFinAffectation");
  }

  public void setDFinAffectation(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinAffectation");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public Integer noOccupation() {
    return (Integer) storedValueForKey("noOccupation");
  }

  public void setNoOccupation(Integer value) {
    takeStoredValueForKey(value, "noOccupation");
  }

  public Integer numQuotAffectation() {
    return (Integer) storedValueForKey("numQuotAffectation");
  }

  public void setNumQuotAffectation(Integer value) {
    takeStoredValueForKey(value, "numQuotAffectation");
  }

  public String temValide() {
    return (String) storedValueForKey("temValide");
  }

  public void setTemValide(String value) {
    takeStoredValueForKey(value, "temValide");
  }

  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividu");
  }

  public void setToIndividuRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividu();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividu");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividu");
    }
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail.TO_AFFECTATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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


  public static EOAffectation createAffectation(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dDebAffectation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu, org.cocktail.feve.eos.modele.grhum.EOStructure toStructure) {
    EOAffectation eo = (EOAffectation) EOUtilities.createAndInsertInstance(editingContext, _EOAffectation.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDDebAffectation(dDebAffectation);
		eo.setDModification(dModification);
    eo.setToIndividuRelationship(toIndividu);
    eo.setToStructureRelationship(toStructure);
    return eo;
  }

  public static NSArray fetchAllAffectations(EOEditingContext editingContext) {
    return _EOAffectation.fetchAllAffectations(editingContext, null);
  }

  public static NSArray fetchAllAffectations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOAffectation.fetchAffectations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchAffectations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOAffectation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOAffectation fetchAffectation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOAffectation.fetchAffectation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOAffectation fetchAffectation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOAffectation.fetchAffectations(editingContext, qualifier, null);
    EOAffectation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOAffectation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Affectation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOAffectation fetchRequiredAffectation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOAffectation.fetchRequiredAffectation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOAffectation fetchRequiredAffectation(EOEditingContext editingContext, EOQualifier qualifier) {
    EOAffectation eoObject = _EOAffectation.fetchAffectation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Affectation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOAffectation localInstanceIn(EOEditingContext editingContext, EOAffectation eo) {
    EOAffectation localInstance = (eo == null) ? null : (EOAffectation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
