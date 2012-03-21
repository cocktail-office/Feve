// _EOStructureInfo.java
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

// DO NOT EDIT.  Make changes to EOStructureInfo.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOStructureInfo extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "StructureInfo";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FDP_STRUCTURE_INFO";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String SIN_D_DEBUT_KEY = "sinDDebut";
	public static final String SIN_D_FIN_KEY = "sinDFin";
	public static final String SIN_LIBELLE_KEY = "sinLibelle";
	public static final String SIN_TYPE_KEY = "sinType";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String SIN_D_DEBUT_COLKEY = "SIN_D_DEBUT";
	public static final String SIN_D_FIN_COLKEY = "SIN_D_FIN";
	public static final String SIN_LIBELLE_COLKEY = "SIN_LIBELLE";
	public static final String SIN_TYPE_COLKEY = "SIN_TYPE";

	// Relationships
	public static final String TO_STRUCTURE_KEY = "toStructure";

	// Utilities methods
  public EOStructureInfo localInstanceIn(EOEditingContext editingContext) {
    EOStructureInfo localInstance = (EOStructureInfo)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp sinDDebut() {
    return (NSTimestamp) storedValueForKey("sinDDebut");
  }

  public void setSinDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "sinDDebut");
  }

  public NSTimestamp sinDFin() {
    return (NSTimestamp) storedValueForKey("sinDFin");
  }

  public void setSinDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "sinDFin");
  }

  public String sinLibelle() {
    return (String) storedValueForKey("sinLibelle");
  }

  public void setSinLibelle(String value) {
    takeStoredValueForKey(value, "sinLibelle");
  }

  public Integer sinType() {
    return (Integer) storedValueForKey("sinType");
  }

  public void setSinType(Integer value) {
    takeStoredValueForKey(value, "sinType");
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
  

  public static EOStructureInfo createStructureInfo(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, NSTimestamp sinDDebut
, Integer sinType
, org.cocktail.feve.eos.modele.grhum.EOStructure toStructure) {
    EOStructureInfo eo = (EOStructureInfo) EOUtilities.createAndInsertInstance(editingContext, _EOStructureInfo.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setSinDDebut(sinDDebut);
		eo.setSinType(sinType);
    eo.setToStructureRelationship(toStructure);
    return eo;
  }

  public static NSArray fetchAllStructureInfos(EOEditingContext editingContext) {
    return _EOStructureInfo.fetchAllStructureInfos(editingContext, null);
  }

  public static NSArray fetchAllStructureInfos(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOStructureInfo.fetchStructureInfos(editingContext, null, sortOrderings);
  }

  public static NSArray fetchStructureInfos(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOStructureInfo.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOStructureInfo fetchStructureInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStructureInfo.fetchStructureInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStructureInfo fetchStructureInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOStructureInfo.fetchStructureInfos(editingContext, qualifier, null);
    EOStructureInfo eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOStructureInfo)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one StructureInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStructureInfo fetchRequiredStructureInfo(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStructureInfo.fetchRequiredStructureInfo(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStructureInfo fetchRequiredStructureInfo(EOEditingContext editingContext, EOQualifier qualifier) {
    EOStructureInfo eoObject = _EOStructureInfo.fetchStructureInfo(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no StructureInfo that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStructureInfo localInstanceIn(EOEditingContext editingContext, EOStructureInfo eo) {
    EOStructureInfo localInstance = (eo == null) ? null : (EOStructureInfo)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
