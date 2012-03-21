// _EOAffectationDetail.java
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

// DO NOT EDIT.  Make changes to EOAffectationDetail.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOAffectationDetail extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "AffectationDetail";
	public static final String ENTITY_TABLE_NAME = "MANGUE.AFFECTATION_DETAIL";

	// Attributes
	public static final String ADE_DATE_DIFF_AFFECTATION_KEY = "adeDateDiffAffectation";
	public static final String ADE_D_DEBUT_KEY = "adeDDebut";
	public static final String ADE_D_FIN_KEY = "adeDFin";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String ADE_DATE_DIFF_AFFECTATION_COLKEY = "ADE_DATE_DIFF_AFFECTATION";
	public static final String ADE_D_DEBUT_COLKEY = "ADE_D_DEBUT";
	public static final String ADE_D_FIN_COLKEY = "ADE_D_FIN";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_AFFECTATION_KEY = "toAffectation";
	public static final String TO_POSTE_KEY = "toPoste";

	// Utilities methods
  public EOAffectationDetail localInstanceIn(EOEditingContext editingContext) {
    EOAffectationDetail localInstance = (EOAffectationDetail)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public Integer adeDateDiffAffectation() {
    return (Integer) storedValueForKey("adeDateDiffAffectation");
  }

  public void setAdeDateDiffAffectation(Integer value) {
    takeStoredValueForKey(value, "adeDateDiffAffectation");
  }

  public NSTimestamp adeDDebut() {
    return (NSTimestamp) storedValueForKey("adeDDebut");
  }

  public void setAdeDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "adeDDebut");
  }

  public NSTimestamp adeDFin() {
    return (NSTimestamp) storedValueForKey("adeDFin");
  }

  public void setAdeDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "adeDFin");
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

  public org.cocktail.feve.eos.modele.mangue.EOAffectation toAffectation() {
    return (org.cocktail.feve.eos.modele.mangue.EOAffectation)storedValueForKey("toAffectation");
  }

  public void setToAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOAffectation oldValue = toAffectation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toAffectation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toAffectation");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOPoste toPoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOPoste)storedValueForKey("toPoste");
  }

  public void setToPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOPoste oldValue = toPoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toPoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toPoste");
    }
  }
  

  public static EOAffectationDetail createAffectationDetail(EOEditingContext editingContext, Integer adeDateDiffAffectation
, NSTimestamp adeDDebut
, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOAffectation toAffectation, org.cocktail.feve.eos.modele.mangue.EOPoste toPoste) {
    EOAffectationDetail eo = (EOAffectationDetail) EOUtilities.createAndInsertInstance(editingContext, _EOAffectationDetail.ENTITY_NAME);    
		eo.setAdeDateDiffAffectation(adeDateDiffAffectation);
		eo.setAdeDDebut(adeDDebut);
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToAffectationRelationship(toAffectation);
    eo.setToPosteRelationship(toPoste);
    return eo;
  }

  public static NSArray fetchAllAffectationDetails(EOEditingContext editingContext) {
    return _EOAffectationDetail.fetchAllAffectationDetails(editingContext, null);
  }

  public static NSArray fetchAllAffectationDetails(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOAffectationDetail.fetchAffectationDetails(editingContext, null, sortOrderings);
  }

  public static NSArray fetchAffectationDetails(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOAffectationDetail.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOAffectationDetail fetchAffectationDetail(EOEditingContext editingContext, String keyName, Object value) {
    return _EOAffectationDetail.fetchAffectationDetail(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOAffectationDetail fetchAffectationDetail(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOAffectationDetail.fetchAffectationDetails(editingContext, qualifier, null);
    EOAffectationDetail eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOAffectationDetail)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one AffectationDetail that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOAffectationDetail fetchRequiredAffectationDetail(EOEditingContext editingContext, String keyName, Object value) {
    return _EOAffectationDetail.fetchRequiredAffectationDetail(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOAffectationDetail fetchRequiredAffectationDetail(EOEditingContext editingContext, EOQualifier qualifier) {
    EOAffectationDetail eoObject = _EOAffectationDetail.fetchAffectationDetail(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no AffectationDetail that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOAffectationDetail localInstanceIn(EOEditingContext editingContext, EOAffectationDetail eo) {
    EOAffectationDetail localInstance = (eo == null) ? null : (EOAffectationDetail)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
