// _EORepartFicheBlocActivation.java
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

// DO NOT EDIT.  Make changes to EORepartFicheBlocActivation.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFicheBlocActivation extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartFicheBlocActivation";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FICHE_BLOC_ACTI";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_FICHE_DE_POSTE_KEY = "toFicheDePoste";
	public static final String TO_FICHE_LOLF_KEY = "toFicheLolf";
	public static final String TO_TPL_BLOC_KEY = "toTplBloc";

	// Utilities methods
  public EORepartFicheBlocActivation localInstanceIn(EOEditingContext editingContext) {
    EORepartFicheBlocActivation localInstance = (EORepartFicheBlocActivation)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluation)storedValueForKey("toEvaluation");
  }

  public void setToEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluation oldValue = toEvaluation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluation");
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
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheLolf toFicheLolf() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheLolf)storedValueForKey("toFicheLolf");
  }

  public void setToFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheLolf oldValue = toFicheLolf();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheLolf");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheLolf");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOTplBloc toTplBloc() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplBloc)storedValueForKey("toTplBloc");
  }

  public void setToTplBlocRelationship(org.cocktail.feve.eos.modele.mangue.EOTplBloc value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplBloc oldValue = toTplBloc();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplBloc");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplBloc");
    }
  }
  

  public static EORepartFicheBlocActivation createRepartFicheBlocActivation(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOTplBloc toTplBloc) {
    EORepartFicheBlocActivation eo = (EORepartFicheBlocActivation) EOUtilities.createAndInsertInstance(editingContext, _EORepartFicheBlocActivation.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToTplBlocRelationship(toTplBloc);
    return eo;
  }

  public static NSArray fetchAllRepartFicheBlocActivations(EOEditingContext editingContext) {
    return _EORepartFicheBlocActivation.fetchAllRepartFicheBlocActivations(editingContext, null);
  }

  public static NSArray fetchAllRepartFicheBlocActivations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFicheBlocActivation.fetchRepartFicheBlocActivations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFicheBlocActivations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFicheBlocActivation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFicheBlocActivation fetchRepartFicheBlocActivation(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFicheBlocActivation.fetchRepartFicheBlocActivation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFicheBlocActivation fetchRepartFicheBlocActivation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFicheBlocActivation.fetchRepartFicheBlocActivations(editingContext, qualifier, null);
    EORepartFicheBlocActivation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFicheBlocActivation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFicheBlocActivation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFicheBlocActivation fetchRequiredRepartFicheBlocActivation(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFicheBlocActivation.fetchRequiredRepartFicheBlocActivation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFicheBlocActivation fetchRequiredRepartFicheBlocActivation(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFicheBlocActivation eoObject = _EORepartFicheBlocActivation.fetchRepartFicheBlocActivation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFicheBlocActivation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFicheBlocActivation localInstanceIn(EOEditingContext editingContext, EORepartFicheBlocActivation eo) {
    EORepartFicheBlocActivation localInstance = (eo == null) ? null : (EORepartFicheBlocActivation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
