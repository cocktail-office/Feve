// _EOFicheLolf.java
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

// DO NOT EDIT.  Make changes to EOFicheLolf.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOFicheLolf extends A_Fiche  {
	public static final String ENTITY_NAME = "FicheLolf";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FICHE_LOLF";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String FLO_D_DEBUT_KEY = "floDDebut";
	public static final String FLO_D_FIN_KEY = "floDFin";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String FLO_D_DEBUT_COLKEY = "FLO_D_DEBUT";
	public static final String FLO_D_FIN_COLKEY = "FLO_D_FIN";

	// Relationships
	public static final String TO_EXERCICE_KEY = "toExercice";
	public static final String TO_POSTE_KEY = "toPoste";
	public static final String TOS_REPART_FLO_SILLAND_KEY = "tosRepartFloSilland";

	// Utilities methods
  public EOFicheLolf localInstanceIn(EOEditingContext editingContext) {
    EOFicheLolf localInstance = (EOFicheLolf)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp floDDebut() {
    return (NSTimestamp) storedValueForKey("floDDebut");
  }

  public void setFloDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "floDDebut");
  }

  public NSTimestamp floDFin() {
    return (NSTimestamp) storedValueForKey("floDFin");
  }

  public void setFloDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "floDFin");
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
  
  public org.cocktail.feve.eos.modele.mangue.EOPoste toPoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOPoste)storedValueForKey("toPoste");
  }

  public void setToPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOPoste oldValue = toPoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toPoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toPoste");
    }
  }
  
  public NSArray tosRepartFloSilland() {
    return (NSArray)storedValueForKey("tosRepartFloSilland");
  }

  public NSArray tosRepartFloSilland(EOQualifier qualifier) {
    return tosRepartFloSilland(qualifier, null, false);
  }

  public NSArray tosRepartFloSilland(EOQualifier qualifier, boolean fetch) {
    return tosRepartFloSilland(qualifier, null, fetch);
  }

  public NSArray tosRepartFloSilland(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland.TO_FICHE_LOLF_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFloSilland.fetchRepartFloSillands(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFloSilland();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFloSillandRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFloSilland");
  }

  public void removeFromTosRepartFloSillandRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloSilland");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFloSilland createTosRepartFloSillandRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFloSilland");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFloSilland");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFloSilland) eo;
  }

  public void deleteTosRepartFloSillandRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFloSilland object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFloSilland");
  }

  public void deleteAllTosRepartFloSillandRelationships() {
    Enumeration objects = tosRepartFloSilland().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFloSillandRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFloSilland)objects.nextElement());
    }
  }


  public static EOFicheLolf createFicheLolf(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, NSTimestamp floDDebut
, org.cocktail.fwkcktljefyadmin.common.metier.EOExercice toExercice, org.cocktail.feve.eos.modele.mangue.EOPoste toPoste) {
    EOFicheLolf eo = (EOFicheLolf) EOUtilities.createAndInsertInstance(editingContext, _EOFicheLolf.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setFloDDebut(floDDebut);
    eo.setToExerciceRelationship(toExercice);
    eo.setToPosteRelationship(toPoste);
    return eo;
  }

  public static NSArray fetchAllFicheLolfs(EOEditingContext editingContext) {
    return _EOFicheLolf.fetchAllFicheLolfs(editingContext, null);
  }

  public static NSArray fetchAllFicheLolfs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOFicheLolf.fetchFicheLolfs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchFicheLolfs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOFicheLolf.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOFicheLolf fetchFicheLolf(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFicheLolf.fetchFicheLolf(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFicheLolf fetchFicheLolf(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOFicheLolf.fetchFicheLolfs(editingContext, qualifier, null);
    EOFicheLolf eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOFicheLolf)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FicheLolf that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFicheLolf fetchRequiredFicheLolf(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFicheLolf.fetchRequiredFicheLolf(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFicheLolf fetchRequiredFicheLolf(EOEditingContext editingContext, EOQualifier qualifier) {
    EOFicheLolf eoObject = _EOFicheLolf.fetchFicheLolf(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FicheLolf that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFicheLolf localInstanceIn(EOEditingContext editingContext, EOFicheLolf eo) {
    EOFicheLolf localInstance = (eo == null) ? null : (EOFicheLolf)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchSuivi(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchSuivi", "FicheLolf");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
