// _EOIndividuFormations.java
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

// DO NOT EDIT.  Make changes to EOIndividuFormations.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOIndividuFormations extends A_DescriptionFormation  {
	public static final String ENTITY_NAME = "IndividuFormations";
	public static final String ENTITY_TABLE_NAME = "MANGUE.INDIVIDU_FORMATIONS";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_FORMATION_KEY = "dDebFormation";
	public static final String D_FIN_FORMATION_KEY = "dFinFormation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DUREE_KEY = "duree";
	public static final String LL_FORMATION_KEY = "llFormation";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_FORMATION_COLKEY = "D_DEB_FORMATION";
	public static final String D_FIN_FORMATION_COLKEY = "D_FIN_FORMATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DUREE_COLKEY = "DUREE";
	public static final String LL_FORMATION_COLKEY = "LL_FORMATION";

	// Relationships
	public static final String TO_FORMATION_PERSONNEL_KEY = "toFormationPersonnel";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TO_TYPE_UNITE_TEMPS_KEY = "toTypeUniteTemps";

	// Utilities methods
  public EOIndividuFormations localInstanceIn(EOEditingContext editingContext) {
    EOIndividuFormations localInstance = (EOIndividuFormations)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dDebFormation() {
    return (NSTimestamp) storedValueForKey("dDebFormation");
  }

  public void setDDebFormation(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebFormation");
  }

  public NSTimestamp dFinFormation() {
    return (NSTimestamp) storedValueForKey("dFinFormation");
  }

  public void setDFinFormation(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinFormation");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public String duree() {
    return (String) storedValueForKey("duree");
  }

  public void setDuree(String value) {
    takeStoredValueForKey(value, "duree");
  }

  public String llFormation() {
    return (String) storedValueForKey("llFormation");
  }

  public void setLlFormation(String value) {
    takeStoredValueForKey(value, "llFormation");
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
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividu");
  }

  public void setToIndividuRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividu();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividu");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividu");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps toTypeUniteTemps() {
    return (org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps)storedValueForKey("toTypeUniteTemps");
  }

  public void setToTypeUniteTempsRelationship(org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps oldValue = toTypeUniteTemps();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTypeUniteTemps");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTypeUniteTemps");
    }
  }
  

  public static EOIndividuFormations createIndividuFormations(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dDebFormation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOIndividuFormations eo = (EOIndividuFormations) EOUtilities.createAndInsertInstance(editingContext, _EOIndividuFormations.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDDebFormation(dDebFormation);
		eo.setDModification(dModification);
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllIndividuFormationses(EOEditingContext editingContext) {
    return _EOIndividuFormations.fetchAllIndividuFormationses(editingContext, null);
  }

  public static NSArray fetchAllIndividuFormationses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOIndividuFormations.fetchIndividuFormationses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchIndividuFormationses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOIndividuFormations.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOIndividuFormations fetchIndividuFormations(EOEditingContext editingContext, String keyName, Object value) {
    return _EOIndividuFormations.fetchIndividuFormations(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOIndividuFormations fetchIndividuFormations(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOIndividuFormations.fetchIndividuFormationses(editingContext, qualifier, null);
    EOIndividuFormations eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOIndividuFormations)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one IndividuFormations that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOIndividuFormations fetchRequiredIndividuFormations(EOEditingContext editingContext, String keyName, Object value) {
    return _EOIndividuFormations.fetchRequiredIndividuFormations(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOIndividuFormations fetchRequiredIndividuFormations(EOEditingContext editingContext, EOQualifier qualifier) {
    EOIndividuFormations eoObject = _EOIndividuFormations.fetchIndividuFormations(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no IndividuFormations that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOIndividuFormations localInstanceIn(EOEditingContext editingContext, EOIndividuFormations eo) {
    EOIndividuFormations localInstance = (eo == null) ? null : (EOIndividuFormations)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
