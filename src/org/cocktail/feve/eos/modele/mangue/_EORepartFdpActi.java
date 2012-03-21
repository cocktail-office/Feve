// _EORepartFdpActi.java
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

// DO NOT EDIT.  Make changes to EORepartFdpActi.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFdpActi extends A_CanBeDeleted  {
	public static final String ENTITY_NAME = "RepartFdpActi";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FDP_ACTI";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String RFA_POSITION_KEY = "rfaPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String RFA_POSITION_COLKEY = "RFA_POSITION";

	// Relationships
	public static final String TO_ACTIVITE_KEY = "toActivite";
	public static final String TO_FICHE_DE_POSTE_KEY = "toFicheDePoste";
	public static final String TO_REFERENS_ACTIVITES_KEY = "toReferensActivites";

	// Utilities methods
  public EORepartFdpActi localInstanceIn(EOEditingContext editingContext) {
    EORepartFdpActi localInstance = (EORepartFdpActi)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer rfaPosition() {
    return (Integer) storedValueForKey("rfaPosition");
  }

  public void setRfaPosition(Integer value) {
    takeStoredValueForKey(value, "rfaPosition");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOActivite toActivite() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOActivite)storedValueForKey("toActivite");
  }

  public void setToActiviteRelationship(org.cocktail.feve.eos.modele.grhum.old.EOActivite value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOActivite oldValue = toActivite();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toActivite");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toActivite");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheDePoste)storedValueForKey("toFicheDePoste");
  }

  public void setToFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheDePoste oldValue = toFicheDePoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheDePoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheDePoste");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensActivites toReferensActivites() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensActivites)storedValueForKey("toReferensActivites");
  }

  public void setToReferensActivitesRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensActivites value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensActivites oldValue = toReferensActivites();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensActivites");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensActivites");
    }
  }
  

  public static EORepartFdpActi createRepartFdpActi(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste) {
    EORepartFdpActi eo = (EORepartFdpActi) EOUtilities.createAndInsertInstance(editingContext, _EORepartFdpActi.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToFicheDePosteRelationship(toFicheDePoste);
    return eo;
  }

  public static NSArray fetchAllRepartFdpActis(EOEditingContext editingContext) {
    return _EORepartFdpActi.fetchAllRepartFdpActis(editingContext, null);
  }

  public static NSArray fetchAllRepartFdpActis(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFdpActi.fetchRepartFdpActis(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFdpActis(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFdpActi.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFdpActi fetchRepartFdpActi(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpActi.fetchRepartFdpActi(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpActi fetchRepartFdpActi(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFdpActi.fetchRepartFdpActis(editingContext, qualifier, null);
    EORepartFdpActi eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFdpActi)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFdpActi that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpActi fetchRequiredRepartFdpActi(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFdpActi.fetchRequiredRepartFdpActi(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFdpActi fetchRequiredRepartFdpActi(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFdpActi eoObject = _EORepartFdpActi.fetchRepartFdpActi(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFdpActi that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFdpActi localInstanceIn(EOEditingContext editingContext, EORepartFdpActi eo) {
    EORepartFdpActi localInstance = (eo == null) ? null : (EORepartFdpActi)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
