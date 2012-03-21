// _EORepartFormationSouhaitee.java
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

// DO NOT EDIT.  Make changes to EORepartFormationSouhaitee.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFormationSouhaitee extends A_DescriptionFormation  {
	public static final String ENTITY_NAME = "RepartFormationSouhaitee";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FORMATION_SOUHAITEE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String RFS_FORMATION_CHAMP_LIBRE_KEY = "rfsFormationChampLibre";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String RFS_FORMATION_CHAMP_LIBRE_COLKEY = "RFS_FORMATION_CHAMP_LIBRE";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_FORMATION_PERSONNEL_KEY = "toFormationPersonnel";

	// Utilities methods
  public EORepartFormationSouhaitee localInstanceIn(EOEditingContext editingContext) {
    EORepartFormationSouhaitee localInstance = (EORepartFormationSouhaitee)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String rfsFormationChampLibre() {
    return (String) storedValueForKey("rfsFormationChampLibre");
  }

  public void setRfsFormationChampLibre(String value) {
    takeStoredValueForKey(value, "rfsFormationChampLibre");
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
  
  public org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel toFormationPersonnel() {
    return (org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel)storedValueForKey("toFormationPersonnel");
  }

  public void setToFormationPersonnelRelationship(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel oldValue = toFormationPersonnel();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFormationPersonnel");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFormationPersonnel");
    }
  }
  

  public static EORepartFormationSouhaitee createRepartFormationSouhaitee(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EORepartFormationSouhaitee eo = (EORepartFormationSouhaitee) EOUtilities.createAndInsertInstance(editingContext, _EORepartFormationSouhaitee.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllRepartFormationSouhaitees(EOEditingContext editingContext) {
    return _EORepartFormationSouhaitee.fetchAllRepartFormationSouhaitees(editingContext, null);
  }

  public static NSArray fetchAllRepartFormationSouhaitees(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFormationSouhaitee.fetchRepartFormationSouhaitees(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFormationSouhaitees(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFormationSouhaitee.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFormationSouhaitee fetchRepartFormationSouhaitee(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFormationSouhaitee.fetchRepartFormationSouhaitee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFormationSouhaitee fetchRepartFormationSouhaitee(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFormationSouhaitee.fetchRepartFormationSouhaitees(editingContext, qualifier, null);
    EORepartFormationSouhaitee eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFormationSouhaitee)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFormationSouhaitee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFormationSouhaitee fetchRequiredRepartFormationSouhaitee(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFormationSouhaitee.fetchRequiredRepartFormationSouhaitee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFormationSouhaitee fetchRequiredRepartFormationSouhaitee(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFormationSouhaitee eoObject = _EORepartFormationSouhaitee.fetchRepartFormationSouhaitee(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFormationSouhaitee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFormationSouhaitee localInstanceIn(EOEditingContext editingContext, EORepartFormationSouhaitee eo) {
    EORepartFormationSouhaitee localInstance = (eo == null) ? null : (EORepartFormationSouhaitee)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
