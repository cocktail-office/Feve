// _EOTplFiche.java
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

// DO NOT EDIT.  Make changes to EOTplFiche.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplFiche extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplFiche";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_FICHE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TFI_CODE_KEY = "tfiCode";
	public static final String TFI_COMMENTAIRE_KEY = "tfiCommentaire";
	public static final String TFI_LIBELLE_KEY = "tfiLibelle";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TFI_CODE_COLKEY = "TFI_CODE";
	public static final String TFI_COMMENTAIRE_COLKEY = "TFI_COMMENTAIRE";
	public static final String TFI_LIBELLE_COLKEY = "TFI_LIBELLE";

	// Relationships
	public static final String TOS_TPL_ONGLET_KEY = "tosTplOnglet";

	// Utilities methods
  public EOTplFiche localInstanceIn(EOEditingContext editingContext) {
    EOTplFiche localInstance = (EOTplFiche)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String tfiCode() {
    return (String) storedValueForKey("tfiCode");
  }

  public void setTfiCode(String value) {
    takeStoredValueForKey(value, "tfiCode");
  }

  public String tfiCommentaire() {
    return (String) storedValueForKey("tfiCommentaire");
  }

  public void setTfiCommentaire(String value) {
    takeStoredValueForKey(value, "tfiCommentaire");
  }

  public String tfiLibelle() {
    return (String) storedValueForKey("tfiLibelle");
  }

  public void setTfiLibelle(String value) {
    takeStoredValueForKey(value, "tfiLibelle");
  }

  public NSArray tosTplOnglet() {
    return (NSArray)storedValueForKey("tosTplOnglet");
  }

  public NSArray tosTplOnglet(EOQualifier qualifier) {
    return tosTplOnglet(qualifier, null, false);
  }

  public NSArray tosTplOnglet(EOQualifier qualifier, boolean fetch) {
    return tosTplOnglet(qualifier, null, fetch);
  }

  public NSArray tosTplOnglet(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOTplOnglet.TO_TPL_FICHE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOTplOnglet.fetchTplOnglets(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosTplOnglet();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosTplOngletRelationship(org.cocktail.feve.eos.modele.mangue.EOTplOnglet object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosTplOnglet");
  }

  public void removeFromTosTplOngletRelationship(org.cocktail.feve.eos.modele.mangue.EOTplOnglet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplOnglet");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplOnglet createTosTplOngletRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("TplOnglet");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosTplOnglet");
    return (org.cocktail.feve.eos.modele.mangue.EOTplOnglet) eo;
  }

  public void deleteTosTplOngletRelationship(org.cocktail.feve.eos.modele.mangue.EOTplOnglet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplOnglet");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosTplOngletRelationships() {
    Enumeration objects = tosTplOnglet().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosTplOngletRelationship((org.cocktail.feve.eos.modele.mangue.EOTplOnglet)objects.nextElement());
    }
  }


  public static EOTplFiche createTplFiche(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String tfiCode
, String tfiLibelle
) {
    EOTplFiche eo = (EOTplFiche) EOUtilities.createAndInsertInstance(editingContext, _EOTplFiche.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTfiCode(tfiCode);
		eo.setTfiLibelle(tfiLibelle);
    return eo;
  }

  public static NSArray fetchAllTplFiches(EOEditingContext editingContext) {
    return _EOTplFiche.fetchAllTplFiches(editingContext, null);
  }

  public static NSArray fetchAllTplFiches(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplFiche.fetchTplFiches(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplFiches(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplFiche.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplFiche fetchTplFiche(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplFiche.fetchTplFiche(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplFiche fetchTplFiche(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplFiche.fetchTplFiches(editingContext, qualifier, null);
    EOTplFiche eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplFiche)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplFiche that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplFiche fetchRequiredTplFiche(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplFiche.fetchRequiredTplFiche(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplFiche fetchRequiredTplFiche(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplFiche eoObject = _EOTplFiche.fetchTplFiche(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplFiche that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplFiche localInstanceIn(EOEditingContext editingContext, EOTplFiche eo) {
    EOTplFiche localInstance = (eo == null) ? null : (EOTplFiche)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
