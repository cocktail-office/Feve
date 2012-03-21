// _EORepartNiveauComp.java
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

// DO NOT EDIT.  Make changes to EORepartNiveauComp.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartNiveauComp extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartNiveauComp";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_NIVEAU_COMP_PRO";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_NIVEAU_COMPETENCE_KEY = "toNiveauCompetence";
	public static final String TO_REPART_FDP_AUTRE_KEY = "toRepartFdpAutre";
	public static final String TO_REPART_FDP_COMP_KEY = "toRepartFdpComp";

	// Utilities methods
  public EORepartNiveauComp localInstanceIn(EOEditingContext editingContext) {
    EORepartNiveauComp localInstance = (EORepartNiveauComp)EOUtilities.localInstanceOfObject(editingContext, this);
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
  
  public org.cocktail.feve.eos.modele.grhum.EONiveauCompetence toNiveauCompetence() {
    return (org.cocktail.feve.eos.modele.grhum.EONiveauCompetence)storedValueForKey("toNiveauCompetence");
  }

  public void setToNiveauCompetenceRelationship(org.cocktail.feve.eos.modele.grhum.EONiveauCompetence value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EONiveauCompetence oldValue = toNiveauCompetence();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toNiveauCompetence");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toNiveauCompetence");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre toRepartFdpAutre() {
    return (org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre)storedValueForKey("toRepartFdpAutre");
  }

  public void setToRepartFdpAutreRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre oldValue = toRepartFdpAutre();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toRepartFdpAutre");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toRepartFdpAutre");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EORepartFdpComp toRepartFdpComp() {
    return (org.cocktail.feve.eos.modele.mangue.EORepartFdpComp)storedValueForKey("toRepartFdpComp");
  }

  public void setToRepartFdpCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpComp value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EORepartFdpComp oldValue = toRepartFdpComp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toRepartFdpComp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toRepartFdpComp");
    }
  }
  

  public static EORepartNiveauComp createRepartNiveauComp(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation) {
    EORepartNiveauComp eo = (EORepartNiveauComp) EOUtilities.createAndInsertInstance(editingContext, _EORepartNiveauComp.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToEvaluationRelationship(toEvaluation);
    return eo;
  }

  public static NSArray fetchAllRepartNiveauComps(EOEditingContext editingContext) {
    return _EORepartNiveauComp.fetchAllRepartNiveauComps(editingContext, null);
  }

  public static NSArray fetchAllRepartNiveauComps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartNiveauComp.fetchRepartNiveauComps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartNiveauComps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartNiveauComp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartNiveauComp fetchRepartNiveauComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartNiveauComp.fetchRepartNiveauComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartNiveauComp fetchRepartNiveauComp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartNiveauComp.fetchRepartNiveauComps(editingContext, qualifier, null);
    EORepartNiveauComp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartNiveauComp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartNiveauComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartNiveauComp fetchRequiredRepartNiveauComp(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartNiveauComp.fetchRequiredRepartNiveauComp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartNiveauComp fetchRequiredRepartNiveauComp(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartNiveauComp eoObject = _EORepartNiveauComp.fetchRepartNiveauComp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartNiveauComp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartNiveauComp localInstanceIn(EOEditingContext editingContext, EORepartNiveauComp eo) {
    EORepartNiveauComp localInstance = (eo == null) ? null : (EORepartNiveauComp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
