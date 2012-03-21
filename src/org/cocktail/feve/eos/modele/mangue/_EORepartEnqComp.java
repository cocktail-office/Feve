// _EORepartEnqComp.java
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

// DO NOT EDIT.  Make changes to EORepartEnqComp.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartEnqComp extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartEnqComp";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FEV_REPART_ENQ_COMP";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String REC_COMMENTAIRE_KEY = "recCommentaire";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String REC_COMMENTAIRE_COLKEY = "REC_COMMENTAIRE";

	// Relationships
	public static final String TO_COMPETENCE_KEY = "toCompetence";
	public static final String TO_ENQUETE_FORMATION_KEY = "toEnqueteFormation";

	// Utilities methods
  public EORepartEnqComp localInstanceIn(EOEditingContext editingContext) {
    EORepartEnqComp localInstance = (EORepartEnqComp)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String recCommentaire() {
    return (String) storedValueForKey("recCommentaire");
  }

  public void setRecCommentaire(String value) {
    takeStoredValueForKey(value, "recCommentaire");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOCompetence toCompetence() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOCompetence)storedValueForKey("toCompetence");
  }

  public void setToCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.old.EOCompetence value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOCompetence oldValue = toCompetence();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toCompetence");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toCompetence");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation toEnqueteFormation() {
    return (org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation)storedValueForKey("toEnqueteFormation");
  }

  public void setToEnqueteFormationRelationship(org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation oldValue = toEnqueteFormation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEnqueteFormation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEnqueteFormation");
    }
  }
  

  public static EORepartEnqComp createRepartEnqComp(EOEditingContext editingContext, org.cocktail.feve.eos.modele.grhum.old.EOCompetence toCompetence, org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation toEnqueteFormation) {
    EORepartEnqComp eo = (EORepartEnqComp) EOUtilities.createAndInsertInstance(editingContext, _EORepartEnqComp.ENTITY_NAME);    
    eo.setToCompetenceRelationship(toCompetence);
    eo.setToEnqueteFormationRelationship(toEnqueteFormation);
    return eo;
  }

  public static NSArray fetchAllRepartEnqComps(EOEditingContext editingContext) {
    return _EORepartEnqComp.fetchAllRepartEnqComps(editingContext, null);
  }

  public static NSArray fetchAllRepartEnqComps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartEnqComp.fetchRepartEnqComps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartEnqComps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartEnqComp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartEnqComp fetchRepartEnqComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEnqComp.fetchRepartEnqComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEnqComp fetchRepartEnqComp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartEnqComp.fetchRepartEnqComps(editingContext, qualifier, null);
    EORepartEnqComp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartEnqComp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartEnqComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEnqComp fetchRequiredRepartEnqComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartEnqComp.fetchRequiredRepartEnqComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartEnqComp fetchRequiredRepartEnqComp(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartEnqComp eoObject = _EORepartEnqComp.fetchRepartEnqComp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartEnqComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartEnqComp localInstanceIn(EOEditingContext editingContext, EORepartEnqComp eo) {
    EORepartEnqComp localInstance = (eo == null) ? null : (EORepartEnqComp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
