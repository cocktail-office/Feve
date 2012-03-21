// _EOTypeDroitCible.java
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

// DO NOT EDIT.  Make changes to EOTypeDroitCible.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTypeDroitCible extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TypeDroitCible";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FEV_TYPE_DROIT_CIBLE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DTC_CODE_KEY = "dtcCode";
	public static final String DTC_LIBELLE_KEY = "dtcLibelle";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DTC_CODE_COLKEY = "DTC_CODE";
	public static final String DTC_LIBELLE_COLKEY = "DTC_LIBELLE";

	// Relationships

	// Utilities methods
  public EOTypeDroitCible localInstanceIn(EOEditingContext editingContext) {
    EOTypeDroitCible localInstance = (EOTypeDroitCible)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String dtcCode() {
    return (String) storedValueForKey("dtcCode");
  }

  public void setDtcCode(String value) {
    takeStoredValueForKey(value, "dtcCode");
  }

  public String dtcLibelle() {
    return (String) storedValueForKey("dtcLibelle");
  }

  public void setDtcLibelle(String value) {
    takeStoredValueForKey(value, "dtcLibelle");
  }


  public static EOTypeDroitCible createTypeDroitCible(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String dtcCode
, String dtcLibelle
) {
    EOTypeDroitCible eo = (EOTypeDroitCible) EOUtilities.createAndInsertInstance(editingContext, _EOTypeDroitCible.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setDtcCode(dtcCode);
		eo.setDtcLibelle(dtcLibelle);
    return eo;
  }

  public static NSArray fetchAllTypeDroitCibles(EOEditingContext editingContext) {
    return _EOTypeDroitCible.fetchAllTypeDroitCibles(editingContext, null);
  }

  public static NSArray fetchAllTypeDroitCibles(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTypeDroitCible.fetchTypeDroitCibles(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTypeDroitCibles(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTypeDroitCible.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTypeDroitCible fetchTypeDroitCible(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeDroitCible.fetchTypeDroitCible(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeDroitCible fetchTypeDroitCible(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTypeDroitCible.fetchTypeDroitCibles(editingContext, qualifier, null);
    EOTypeDroitCible eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTypeDroitCible)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TypeDroitCible that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeDroitCible fetchRequiredTypeDroitCible(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeDroitCible.fetchRequiredTypeDroitCible(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeDroitCible fetchRequiredTypeDroitCible(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTypeDroitCible eoObject = _EOTypeDroitCible.fetchTypeDroitCible(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TypeDroitCible that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeDroitCible localInstanceIn(EOEditingContext editingContext, EOTypeDroitCible eo) {
    EOTypeDroitCible localInstance = (eo == null) ? null : (EOTypeDroitCible)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
