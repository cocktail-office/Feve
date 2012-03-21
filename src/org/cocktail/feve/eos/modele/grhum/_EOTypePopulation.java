// _EOTypePopulation.java
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

// DO NOT EDIT.  Make changes to EOTypePopulation.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTypePopulation extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TypePopulation";
	public static final String ENTITY_TABLE_NAME = "GRHUM.TYPE_POPULATION";

	// Attributes
	public static final String TEM_ATOS_KEY = "temAtos";
	public static final String TEM_BIBLIO_KEY = "temBiblio";
	public static final String TEM_ITARF_KEY = "temItarf";

	public static final String TEM_ATOS_COLKEY = "TEM_ATOS";
	public static final String TEM_BIBLIO_COLKEY = "TEM_BIBLIO";
	public static final String TEM_ITARF_COLKEY = "TEM_ITARF";

	// Relationships

	// Utilities methods
  public EOTypePopulation localInstanceIn(EOEditingContext editingContext) {
    EOTypePopulation localInstance = (EOTypePopulation)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String temAtos() {
    return (String) storedValueForKey("temAtos");
  }

  public void setTemAtos(String value) {
    takeStoredValueForKey(value, "temAtos");
  }

  public String temBiblio() {
    return (String) storedValueForKey("temBiblio");
  }

  public void setTemBiblio(String value) {
    takeStoredValueForKey(value, "temBiblio");
  }

  public String temItarf() {
    return (String) storedValueForKey("temItarf");
  }

  public void setTemItarf(String value) {
    takeStoredValueForKey(value, "temItarf");
  }


  public static EOTypePopulation createTypePopulation(EOEditingContext editingContext, String temAtos
, String temBiblio
, String temItarf
) {
    EOTypePopulation eo = (EOTypePopulation) EOUtilities.createAndInsertInstance(editingContext, _EOTypePopulation.ENTITY_NAME);    
		eo.setTemAtos(temAtos);
		eo.setTemBiblio(temBiblio);
		eo.setTemItarf(temItarf);
    return eo;
  }

  public static NSArray fetchAllTypePopulations(EOEditingContext editingContext) {
    return _EOTypePopulation.fetchAllTypePopulations(editingContext, null);
  }

  public static NSArray fetchAllTypePopulations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTypePopulation.fetchTypePopulations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTypePopulations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTypePopulation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTypePopulation fetchTypePopulation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypePopulation.fetchTypePopulation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypePopulation fetchTypePopulation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTypePopulation.fetchTypePopulations(editingContext, qualifier, null);
    EOTypePopulation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTypePopulation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TypePopulation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypePopulation fetchRequiredTypePopulation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypePopulation.fetchRequiredTypePopulation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypePopulation fetchRequiredTypePopulation(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTypePopulation eoObject = _EOTypePopulation.fetchTypePopulation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TypePopulation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypePopulation localInstanceIn(EOEditingContext editingContext, EOTypePopulation eo) {
    EOTypePopulation localInstance = (eo == null) ? null : (EOTypePopulation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
