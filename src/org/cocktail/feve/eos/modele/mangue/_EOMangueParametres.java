// _EOMangueParametres.java
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

// DO NOT EDIT.  Make changes to EOMangueParametres.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOMangueParametres extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "MangueParametres";
	public static final String ENTITY_TABLE_NAME = "MANGUE.MANGUE_PARAMETRES";

	// Attributes
	public static final String PARAM_COMMENTAIRES_KEY = "paramCommentaires";
	public static final String PARAM_KEY_KEY = "paramKey";
	public static final String PARAM_VALUE_KEY = "paramValue";

	public static final String PARAM_COMMENTAIRES_COLKEY = "PARAM_COMMENTAIRES";
	public static final String PARAM_KEY_COLKEY = "PARAM_KEY";
	public static final String PARAM_VALUE_COLKEY = "PARAM_VALUE";

	// Relationships

	// Utilities methods
  public EOMangueParametres localInstanceIn(EOEditingContext editingContext) {
    EOMangueParametres localInstance = (EOMangueParametres)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String paramCommentaires() {
    return (String) storedValueForKey("paramCommentaires");
  }

  public void setParamCommentaires(String value) {
    takeStoredValueForKey(value, "paramCommentaires");
  }

  public String paramKey() {
    return (String) storedValueForKey("paramKey");
  }

  public void setParamKey(String value) {
    takeStoredValueForKey(value, "paramKey");
  }

  public String paramValue() {
    return (String) storedValueForKey("paramValue");
  }

  public void setParamValue(String value) {
    takeStoredValueForKey(value, "paramValue");
  }


  public static EOMangueParametres createMangueParametres(EOEditingContext editingContext, String paramKey
) {
    EOMangueParametres eo = (EOMangueParametres) EOUtilities.createAndInsertInstance(editingContext, _EOMangueParametres.ENTITY_NAME);    
		eo.setParamKey(paramKey);
    return eo;
  }

  public static NSArray fetchAllMangueParametreses(EOEditingContext editingContext) {
    return _EOMangueParametres.fetchAllMangueParametreses(editingContext, null);
  }

  public static NSArray fetchAllMangueParametreses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOMangueParametres.fetchMangueParametreses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchMangueParametreses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOMangueParametres.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOMangueParametres fetchMangueParametres(EOEditingContext editingContext, String keyName, Object value) {
    return _EOMangueParametres.fetchMangueParametres(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOMangueParametres fetchMangueParametres(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOMangueParametres.fetchMangueParametreses(editingContext, qualifier, null);
    EOMangueParametres eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOMangueParametres)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one MangueParametres that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOMangueParametres fetchRequiredMangueParametres(EOEditingContext editingContext, String keyName, Object value) {
    return _EOMangueParametres.fetchRequiredMangueParametres(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOMangueParametres fetchRequiredMangueParametres(EOEditingContext editingContext, EOQualifier qualifier) {
    EOMangueParametres eoObject = _EOMangueParametres.fetchMangueParametres(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no MangueParametres that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOMangueParametres localInstanceIn(EOEditingContext editingContext, EOMangueParametres eo) {
    EOMangueParametres localInstance = (eo == null) ? null : (EOMangueParametres)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
