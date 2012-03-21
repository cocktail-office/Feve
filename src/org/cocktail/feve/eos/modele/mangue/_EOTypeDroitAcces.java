// _EOTypeDroitAcces.java
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

// DO NOT EDIT.  Make changes to EOTypeDroitAcces.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTypeDroitAcces extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TypeDroitAcces";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FEV_TYPE_DROIT_ACCES";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DTA_CODE_KEY = "dtaCode";
	public static final String DTA_LIBELLE_KEY = "dtaLibelle";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DTA_CODE_COLKEY = "DTA_CODE";
	public static final String DTA_LIBELLE_COLKEY = "DTA_LIBELLE";

	// Relationships

	// Utilities methods
  public EOTypeDroitAcces localInstanceIn(EOEditingContext editingContext) {
    EOTypeDroitAcces localInstance = (EOTypeDroitAcces)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String dtaCode() {
    return (String) storedValueForKey("dtaCode");
  }

  public void setDtaCode(String value) {
    takeStoredValueForKey(value, "dtaCode");
  }

  public String dtaLibelle() {
    return (String) storedValueForKey("dtaLibelle");
  }

  public void setDtaLibelle(String value) {
    takeStoredValueForKey(value, "dtaLibelle");
  }


  public static EOTypeDroitAcces createTypeDroitAcces(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String dtaCode
, String dtaLibelle
) {
    EOTypeDroitAcces eo = (EOTypeDroitAcces) EOUtilities.createAndInsertInstance(editingContext, _EOTypeDroitAcces.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setDtaCode(dtaCode);
		eo.setDtaLibelle(dtaLibelle);
    return eo;
  }

  public static NSArray fetchAllTypeDroitAcceses(EOEditingContext editingContext) {
    return _EOTypeDroitAcces.fetchAllTypeDroitAcceses(editingContext, null);
  }

  public static NSArray fetchAllTypeDroitAcceses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTypeDroitAcces.fetchTypeDroitAcceses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTypeDroitAcceses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTypeDroitAcces.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTypeDroitAcces fetchTypeDroitAcces(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeDroitAcces.fetchTypeDroitAcces(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeDroitAcces fetchTypeDroitAcces(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTypeDroitAcces.fetchTypeDroitAcceses(editingContext, qualifier, null);
    EOTypeDroitAcces eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTypeDroitAcces)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TypeDroitAcces that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeDroitAcces fetchRequiredTypeDroitAcces(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeDroitAcces.fetchRequiredTypeDroitAcces(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeDroitAcces fetchRequiredTypeDroitAcces(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTypeDroitAcces eoObject = _EOTypeDroitAcces.fetchTypeDroitAcces(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TypeDroitAcces that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeDroitAcces localInstanceIn(EOEditingContext editingContext, EOTypeDroitAcces eo) {
    EOTypeDroitAcces localInstance = (eo == null) ? null : (EOTypeDroitAcces)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
