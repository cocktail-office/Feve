// _EORepartSillandLolf.java
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

// DO NOT EDIT.  Make changes to EORepartSillandLolf.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartSillandLolf extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartLolfSilland";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REPART_SILLAND_LOLF";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_EXERCICE_KEY = "toExercice";
	public static final String TO_FCT_SILLAND_KEY = "toFctSilland";
	public static final String TO_LOLF_NOMENCLATURE_DEPENSE_KEY = "toLolfNomenclatureDepense";

	// Utilities methods
  public EORepartSillandLolf localInstanceIn(EOEditingContext editingContext) {
    EORepartSillandLolf localInstance = (EORepartSillandLolf)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public org.cocktail.fwkcktljefyadmin.common.metier.EOExercice toExercice() {
    return (org.cocktail.fwkcktljefyadmin.common.metier.EOExercice)storedValueForKey("toExercice");
  }

  public void setToExerciceRelationship(org.cocktail.fwkcktljefyadmin.common.metier.EOExercice value) {
    if (value == null) {
    	org.cocktail.fwkcktljefyadmin.common.metier.EOExercice oldValue = toExercice();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toExercice");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toExercice");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOFctSilland toFctSilland() {
    return (org.cocktail.feve.eos.modele.grhum.EOFctSilland)storedValueForKey("toFctSilland");
  }

  public void setToFctSillandRelationship(org.cocktail.feve.eos.modele.grhum.EOFctSilland value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOFctSilland oldValue = toFctSilland();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFctSilland");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFctSilland");
    }
  }
  
  public org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense toLolfNomenclatureDepense() {
    return (org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense)storedValueForKey("toLolfNomenclatureDepense");
  }

  public void setToLolfNomenclatureDepenseRelationship(org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense value) {
    if (value == null) {
    	org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense oldValue = toLolfNomenclatureDepense();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toLolfNomenclatureDepense");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toLolfNomenclatureDepense");
    }
  }
  

  public static EORepartSillandLolf createRepartLolfSilland(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.fwkcktljefyadmin.common.metier.EOExercice toExercice, org.cocktail.feve.eos.modele.grhum.EOFctSilland toFctSilland, org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense toLolfNomenclatureDepense) {
    EORepartSillandLolf eo = (EORepartSillandLolf) EOUtilities.createAndInsertInstance(editingContext, _EORepartSillandLolf.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToExerciceRelationship(toExercice);
    eo.setToFctSillandRelationship(toFctSilland);
    eo.setToLolfNomenclatureDepenseRelationship(toLolfNomenclatureDepense);
    return eo;
  }

  public static NSArray fetchAllRepartLolfSillands(EOEditingContext editingContext) {
    return _EORepartSillandLolf.fetchAllRepartLolfSillands(editingContext, null);
  }

  public static NSArray fetchAllRepartLolfSillands(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartSillandLolf.fetchRepartLolfSillands(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartLolfSillands(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartSillandLolf.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartSillandLolf fetchRepartLolfSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartSillandLolf.fetchRepartLolfSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartSillandLolf fetchRepartLolfSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartSillandLolf.fetchRepartLolfSillands(editingContext, qualifier, null);
    EORepartSillandLolf eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartSillandLolf)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartLolfSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartSillandLolf fetchRequiredRepartLolfSilland(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartSillandLolf.fetchRequiredRepartLolfSilland(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartSillandLolf fetchRequiredRepartLolfSilland(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartSillandLolf eoObject = _EORepartSillandLolf.fetchRepartLolfSilland(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartLolfSilland that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartSillandLolf localInstanceIn(EOEditingContext editingContext, EORepartSillandLolf eo) {
    EORepartSillandLolf localInstance = (eo == null) ? null : (EORepartSillandLolf)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
