// _EOTplBlocNature.java
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

// DO NOT EDIT.  Make changes to EOTplBlocNature.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplBlocNature extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplBlocNature";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_BLOC_NATURE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TBN_LIBELLE_KEY = "tbnLibelle";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TBN_LIBELLE_COLKEY = "TBN_LIBELLE";

	// Relationships

	// Utilities methods
  public EOTplBlocNature localInstanceIn(EOEditingContext editingContext) {
    EOTplBlocNature localInstance = (EOTplBlocNature)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String tbnLibelle() {
    return (String) storedValueForKey("tbnLibelle");
  }

  public void setTbnLibelle(String value) {
    takeStoredValueForKey(value, "tbnLibelle");
  }


  public static EOTplBlocNature createTplBlocNature(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String tbnLibelle
) {
    EOTplBlocNature eo = (EOTplBlocNature) EOUtilities.createAndInsertInstance(editingContext, _EOTplBlocNature.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTbnLibelle(tbnLibelle);
    return eo;
  }

  public static NSArray fetchAllTplBlocNatures(EOEditingContext editingContext) {
    return _EOTplBlocNature.fetchAllTplBlocNatures(editingContext, null);
  }

  public static NSArray fetchAllTplBlocNatures(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplBlocNature.fetchTplBlocNatures(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplBlocNatures(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplBlocNature.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplBlocNature fetchTplBlocNature(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplBlocNature.fetchTplBlocNature(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplBlocNature fetchTplBlocNature(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplBlocNature.fetchTplBlocNatures(editingContext, qualifier, null);
    EOTplBlocNature eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplBlocNature)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplBlocNature that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplBlocNature fetchRequiredTplBlocNature(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplBlocNature.fetchRequiredTplBlocNature(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplBlocNature fetchRequiredTplBlocNature(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplBlocNature eoObject = _EOTplBlocNature.fetchTplBlocNature(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplBlocNature that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplBlocNature localInstanceIn(EOEditingContext editingContext, EOTplBlocNature eo) {
    EOTplBlocNature localInstance = (eo == null) ? null : (EOTplBlocNature)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
