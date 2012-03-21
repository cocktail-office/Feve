// _EOTypeUniteTemps.java
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

// DO NOT EDIT.  Make changes to EOTypeUniteTemps.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTypeUniteTemps extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TypeUniteTemps";
	public static final String ENTITY_TABLE_NAME = "GRHUM.TYPE_UNITE_TEMPS";

	// Attributes
	public static final String TUT_CODE_KEY = "tutCode";
	public static final String TUT_LIBELLE_KEY = "tutLibelle";

	public static final String TUT_CODE_COLKEY = "TUT_CODE";
	public static final String TUT_LIBELLE_COLKEY = "TUT_LIBELLE";

	// Relationships

	// Utilities methods
  public EOTypeUniteTemps localInstanceIn(EOEditingContext editingContext) {
    EOTypeUniteTemps localInstance = (EOTypeUniteTemps)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String tutCode() {
    return (String) storedValueForKey("tutCode");
  }

  public void setTutCode(String value) {
    takeStoredValueForKey(value, "tutCode");
  }

  public String tutLibelle() {
    return (String) storedValueForKey("tutLibelle");
  }

  public void setTutLibelle(String value) {
    takeStoredValueForKey(value, "tutLibelle");
  }


  public static EOTypeUniteTemps createTypeUniteTemps(EOEditingContext editingContext) {
    EOTypeUniteTemps eo = (EOTypeUniteTemps) EOUtilities.createAndInsertInstance(editingContext, _EOTypeUniteTemps.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllTypeUniteTempses(EOEditingContext editingContext) {
    return _EOTypeUniteTemps.fetchAllTypeUniteTempses(editingContext, null);
  }

  public static NSArray fetchAllTypeUniteTempses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTypeUniteTemps.fetchTypeUniteTempses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTypeUniteTempses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTypeUniteTemps.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTypeUniteTemps fetchTypeUniteTemps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeUniteTemps.fetchTypeUniteTemps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeUniteTemps fetchTypeUniteTemps(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTypeUniteTemps.fetchTypeUniteTempses(editingContext, qualifier, null);
    EOTypeUniteTemps eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTypeUniteTemps)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TypeUniteTemps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeUniteTemps fetchRequiredTypeUniteTemps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeUniteTemps.fetchRequiredTypeUniteTemps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeUniteTemps fetchRequiredTypeUniteTemps(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTypeUniteTemps eoObject = _EOTypeUniteTemps.fetchTypeUniteTemps(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TypeUniteTemps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeUniteTemps localInstanceIn(EOEditingContext editingContext, EOTypeUniteTemps eo) {
    EOTypeUniteTemps localInstance = (eo == null) ? null : (EOTypeUniteTemps)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
