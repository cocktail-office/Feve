// _EODroitNouvelEntrant.java
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

// DO NOT EDIT.  Make changes to EODroitNouvelEntrant.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EODroitNouvelEntrant extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "DroitNouvelEntrant";
	public static final String ENTITY_TABLE_NAME = "FEV_DRO_NOUV_ENTRANT";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DNE_D_DEBUT_KEY = "dneDDebut";
	public static final String DNE_D_FIN_KEY = "dneDFin";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DNE_D_DEBUT_COLKEY = "DNE_D_DEBUT";
	public static final String DNE_D_FIN_COLKEY = "DNE_D_FIN";

	// Relationships
	public static final String TO_EVALUATION_PERIODE_KEY = "toEvaluationPeriode";
	public static final String TO_INDIVIDU_ENTRANT_KEY = "toIndividuEntrant";
	public static final String TO_INDIVIDU_RESP_KEY = "toIndividuResp";

	// Utilities methods
  public EODroitNouvelEntrant localInstanceIn(EOEditingContext editingContext) {
    EODroitNouvelEntrant localInstance = (EODroitNouvelEntrant)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dneDDebut() {
    return (NSTimestamp) storedValueForKey("dneDDebut");
  }

  public void setDneDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "dneDDebut");
  }

  public NSTimestamp dneDFin() {
    return (NSTimestamp) storedValueForKey("dneDFin");
  }

  public void setDneDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "dneDFin");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode)storedValueForKey("toEvaluationPeriode");
  }

  public void setToEvaluationPeriodeRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode oldValue = toEvaluationPeriode();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluationPeriode");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluationPeriode");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuEntrant() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividuEntrant");
  }

  public void setToIndividuEntrantRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividuEntrant();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividuEntrant");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividuEntrant");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuResp() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividuResp");
  }

  public void setToIndividuRespRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividuResp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividuResp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividuResp");
    }
  }
  

  public static EODroitNouvelEntrant createDroitNouvelEntrant(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, NSTimestamp dneDDebut
, NSTimestamp dneDFin
, org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuEntrant, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuResp) {
    EODroitNouvelEntrant eo = (EODroitNouvelEntrant) EOUtilities.createAndInsertInstance(editingContext, _EODroitNouvelEntrant.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setDneDDebut(dneDDebut);
		eo.setDneDFin(dneDFin);
    eo.setToEvaluationPeriodeRelationship(toEvaluationPeriode);
    eo.setToIndividuEntrantRelationship(toIndividuEntrant);
    eo.setToIndividuRespRelationship(toIndividuResp);
    return eo;
  }

  public static NSArray fetchAllDroitNouvelEntrants(EOEditingContext editingContext) {
    return _EODroitNouvelEntrant.fetchAllDroitNouvelEntrants(editingContext, null);
  }

  public static NSArray fetchAllDroitNouvelEntrants(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EODroitNouvelEntrant.fetchDroitNouvelEntrants(editingContext, null, sortOrderings);
  }

  public static NSArray fetchDroitNouvelEntrants(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EODroitNouvelEntrant.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EODroitNouvelEntrant fetchDroitNouvelEntrant(EOEditingContext editingContext, String keyName, Object value) {
    return _EODroitNouvelEntrant.fetchDroitNouvelEntrant(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODroitNouvelEntrant fetchDroitNouvelEntrant(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EODroitNouvelEntrant.fetchDroitNouvelEntrants(editingContext, qualifier, null);
    EODroitNouvelEntrant eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EODroitNouvelEntrant)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one DroitNouvelEntrant that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODroitNouvelEntrant fetchRequiredDroitNouvelEntrant(EOEditingContext editingContext, String keyName, Object value) {
    return _EODroitNouvelEntrant.fetchRequiredDroitNouvelEntrant(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODroitNouvelEntrant fetchRequiredDroitNouvelEntrant(EOEditingContext editingContext, EOQualifier qualifier) {
    EODroitNouvelEntrant eoObject = _EODroitNouvelEntrant.fetchDroitNouvelEntrant(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no DroitNouvelEntrant that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODroitNouvelEntrant localInstanceIn(EOEditingContext editingContext, EODroitNouvelEntrant eo) {
    EODroitNouvelEntrant localInstance = (eo == null) ? null : (EODroitNouvelEntrant)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
