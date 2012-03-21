// _EOTplItem.java
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

// DO NOT EDIT.  Make changes to EOTplItem.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplItem extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplItem";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_ITEM";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TIT_CODE_KEY = "titCode";
	public static final String TIT_COMMENTAIRE_KEY = "titCommentaire";
	public static final String TIT_LIBELLE_KEY = "titLibelle";
	public static final String TIT_POSITION_KEY = "titPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TIT_CODE_COLKEY = "TIT_CODE";
	public static final String TIT_COMMENTAIRE_COLKEY = "TIT_COMMENTAIRE";
	public static final String TIT_LIBELLE_COLKEY = "TIT_LIBELLE";
	public static final String TIT_POSITION_COLKEY = "TIT_POSITION";

	// Relationships
	public static final String TOS_REPART_FICHE_ITEM_KEY = "tosRepartFicheItem";
	public static final String TOS_TPL_REPART_ITEM_ITEM_VALEUR_KEY = "tosTplRepartItemItemValeur";
	public static final String TO_TPL_BLOC_KEY = "toTplBloc";
	public static final String TO_TPL_ITEM_NATURE_KEY = "toTplItemNature";

	// Utilities methods
  public EOTplItem localInstanceIn(EOEditingContext editingContext) {
    EOTplItem localInstance = (EOTplItem)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String titCode() {
    return (String) storedValueForKey("titCode");
  }

  public void setTitCode(String value) {
    takeStoredValueForKey(value, "titCode");
  }

  public String titCommentaire() {
    return (String) storedValueForKey("titCommentaire");
  }

  public void setTitCommentaire(String value) {
    takeStoredValueForKey(value, "titCommentaire");
  }

  public String titLibelle() {
    return (String) storedValueForKey("titLibelle");
  }

  public void setTitLibelle(String value) {
    takeStoredValueForKey(value, "titLibelle");
  }

  public Integer titPosition() {
    return (Integer) storedValueForKey("titPosition");
  }

  public void setTitPosition(Integer value) {
    takeStoredValueForKey(value, "titPosition");
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
  
  public org.cocktail.feve.eos.modele.mangue.EOTplItemNature toTplItemNature() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplItemNature)storedValueForKey("toTplItemNature");
  }

  public void setToTplItemNatureRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItemNature value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplItemNature oldValue = toTplItemNature();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplItemNature");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplItemNature");
    }
  }
  
  public NSArray tosRepartFicheItem() {
    return (NSArray)storedValueForKey("tosRepartFicheItem");
  }

  public NSArray tosRepartFicheItem(EOQualifier qualifier) {
    return tosRepartFicheItem(qualifier, null, false);
  }

  public NSArray tosRepartFicheItem(EOQualifier qualifier, boolean fetch) {
    return tosRepartFicheItem(qualifier, null, fetch);
  }

  public NSArray tosRepartFicheItem(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFicheItem.TO_TPL_ITEM_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFicheItem.fetchRepartFicheItems(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFicheItem();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFicheItemRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheItem object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFicheItem");
  }

  public void removeFromTosRepartFicheItemRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheItem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFicheItem");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFicheItem createTosRepartFicheItemRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFicheItem");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFicheItem");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFicheItem) eo;
  }

  public void deleteTosRepartFicheItemRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheItem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFicheItem");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartFicheItemRelationships() {
    Enumeration objects = tosRepartFicheItem().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFicheItemRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFicheItem)objects.nextElement());
    }
  }

  public NSArray tosTplRepartItemItemValeur() {
    return (NSArray)storedValueForKey("tosTplRepartItemItemValeur");
  }

  public NSArray tosTplRepartItemItemValeur(EOQualifier qualifier) {
    return tosTplRepartItemItemValeur(qualifier, null, false);
  }

  public NSArray tosTplRepartItemItemValeur(EOQualifier qualifier, boolean fetch) {
    return tosTplRepartItemItemValeur(qualifier, null, fetch);
  }

  public NSArray tosTplRepartItemItemValeur(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur.TO_TPL_ITEM_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur.fetchTplRepartItemItemValeurs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosTplRepartItemItemValeur();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosTplRepartItemItemValeurRelationship(org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosTplRepartItemItemValeur");
  }

  public void removeFromTosTplRepartItemItemValeurRelationship(org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplRepartItemItemValeur");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur createTosTplRepartItemItemValeurRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("TplRepartItemItemValeur");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosTplRepartItemItemValeur");
    return (org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur) eo;
  }

  public void deleteTosTplRepartItemItemValeurRelationship(org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplRepartItemItemValeur");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosTplRepartItemItemValeurRelationships() {
    Enumeration objects = tosTplRepartItemItemValeur().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosTplRepartItemItemValeurRelationship((org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur)objects.nextElement());
    }
  }


  public static EOTplItem createTplItem(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String titLibelle
, Integer titPosition
, org.cocktail.feve.eos.modele.mangue.EOTplBloc toTplBloc, org.cocktail.feve.eos.modele.mangue.EOTplItemNature toTplItemNature) {
    EOTplItem eo = (EOTplItem) EOUtilities.createAndInsertInstance(editingContext, _EOTplItem.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTitLibelle(titLibelle);
		eo.setTitPosition(titPosition);
    eo.setToTplBlocRelationship(toTplBloc);
    eo.setToTplItemNatureRelationship(toTplItemNature);
    return eo;
  }

  public static NSArray fetchAllTplItems(EOEditingContext editingContext) {
    return _EOTplItem.fetchAllTplItems(editingContext, null);
  }

  public static NSArray fetchAllTplItems(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplItem.fetchTplItems(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplItems(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplItem.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplItem fetchTplItem(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplItem.fetchTplItem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplItem fetchTplItem(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplItem.fetchTplItems(editingContext, qualifier, null);
    EOTplItem eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplItem)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplItem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplItem fetchRequiredTplItem(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplItem.fetchRequiredTplItem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplItem fetchRequiredTplItem(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplItem eoObject = _EOTplItem.fetchTplItem(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplItem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplItem localInstanceIn(EOEditingContext editingContext, EOTplItem eo) {
    EOTplItem localInstance = (eo == null) ? null : (EOTplItem)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
