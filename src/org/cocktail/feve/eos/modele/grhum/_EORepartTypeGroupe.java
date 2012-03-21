// _EORepartTypeGroupe.java
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

// DO NOT EDIT.  Make changes to EORepartTypeGroupe.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartTypeGroupe extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartTypeGroupe";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REPART_TYPE_GROUPE";

	// Attributes
	public static final String TGRP_CODE_KEY = "tgrpCode";

	public static final String TGRP_CODE_COLKEY = "TGRP_CODE";

	// Relationships

	// Utilities methods
  public EORepartTypeGroupe localInstanceIn(EOEditingContext editingContext) {
    EORepartTypeGroupe localInstance = (EORepartTypeGroupe)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String tgrpCode() {
    return (String) storedValueForKey("tgrpCode");
  }

  public void setTgrpCode(String value) {
    takeStoredValueForKey(value, "tgrpCode");
  }


  public static EORepartTypeGroupe createRepartTypeGroupe(EOEditingContext editingContext, String tgrpCode
) {
    EORepartTypeGroupe eo = (EORepartTypeGroupe) EOUtilities.createAndInsertInstance(editingContext, _EORepartTypeGroupe.ENTITY_NAME);    
		eo.setTgrpCode(tgrpCode);
    return eo;
  }

  public static NSArray fetchAllRepartTypeGroupes(EOEditingContext editingContext) {
    return _EORepartTypeGroupe.fetchAllRepartTypeGroupes(editingContext, null);
  }

  public static NSArray fetchAllRepartTypeGroupes(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartTypeGroupe.fetchRepartTypeGroupes(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartTypeGroupes(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartTypeGroupe.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartTypeGroupe fetchRepartTypeGroupe(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartTypeGroupe.fetchRepartTypeGroupe(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartTypeGroupe fetchRepartTypeGroupe(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartTypeGroupe.fetchRepartTypeGroupes(editingContext, qualifier, null);
    EORepartTypeGroupe eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartTypeGroupe)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartTypeGroupe that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartTypeGroupe fetchRequiredRepartTypeGroupe(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartTypeGroupe.fetchRequiredRepartTypeGroupe(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartTypeGroupe fetchRequiredRepartTypeGroupe(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartTypeGroupe eoObject = _EORepartTypeGroupe.fetchRepartTypeGroupe(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartTypeGroupe that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartTypeGroupe localInstanceIn(EOEditingContext editingContext, EORepartTypeGroupe eo) {
    EORepartTypeGroupe localInstance = (eo == null) ? null : (EORepartTypeGroupe)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
