// _EOTplBloc.java
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

// DO NOT EDIT.  Make changes to EOTplBloc.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplBloc extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplBloc";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_BLOC";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TBL_CODE_KEY = "tblCode";
	public static final String TBL_COMMENTAIRE_KEY = "tblCommentaire";
	public static final String TBL_FACULTATIF_KEY = "tblFacultatif";
	public static final String TBL_LIBELLE_KEY = "tblLibelle";
	public static final String TBL_POSITION_KEY = "tblPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TBL_CODE_COLKEY = "TBL_CODE";
	public static final String TBL_COMMENTAIRE_COLKEY = "TBL_COMMENTAIRE";
	public static final String TBL_FACULTATIF_COLKEY = "TBL_FACULTATIF";
	public static final String TBL_LIBELLE_COLKEY = "TBL_LIBELLE";
	public static final String TBL_POSITION_COLKEY = "TBL_POSITION";

	// Relationships
	public static final String TOS_REPART_FICHE_BLOC_ACTIVATION_KEY = "tosRepartFicheBlocActivation";
	public static final String TOS_TPL_ITEM_KEY = "tosTplItem";
	public static final String TO_TPL_BLOC_NATURE_KEY = "toTplBlocNature";
	public static final String TO_TPL_ONGLET_KEY = "toTplOnglet";

	// Utilities methods
  public EOTplBloc localInstanceIn(EOEditingContext editingContext) {
    EOTplBloc localInstance = (EOTplBloc)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dDebVal() {
    return (NSTimestamp) storedValueForKey("dDebVal");
  }

  public void setDDebVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebVal");
  }

  public NSTimestamp dFinVal() {
    return (NSTimestamp) storedValueForKey("dFinVal");
  }

  public void setDFinVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinVal");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public String tblCode() {
    return (String) storedValueForKey("tblCode");
  }

  public void setTblCode(String value) {
    takeStoredValueForKey(value, "tblCode");
  }

  public String tblCommentaire() {
    return (String) storedValueForKey("tblCommentaire");
  }

  public void setTblCommentaire(String value) {
    takeStoredValueForKey(value, "tblCommentaire");
  }

  public String tblFacultatif() {
    return (String) storedValueForKey("tblFacultatif");
  }

  public void setTblFacultatif(String value) {
    takeStoredValueForKey(value, "tblFacultatif");
  }

  public String tblLibelle() {
    return (String) storedValueForKey("tblLibelle");
  }

  public void setTblLibelle(String value) {
    takeStoredValueForKey(value, "tblLibelle");
  }

  public Integer tblPosition() {
    return (Integer) storedValueForKey("tblPosition");
  }

  public void setTblPosition(Integer value) {
    takeStoredValueForKey(value, "tblPosition");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplBlocNature toTplBlocNature() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplBlocNature)storedValueForKey("toTplBlocNature");
  }

  public void setToTplBlocNatureRelationship(org.cocktail.feve.eos.modele.mangue.EOTplBlocNature value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplBlocNature oldValue = toTplBlocNature();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplBlocNature");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplBlocNature");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOTplOnglet toTplOnglet() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplOnglet)storedValueForKey("toTplOnglet");
  }

  public void setToTplOngletRelationship(org.cocktail.feve.eos.modele.mangue.EOTplOnglet value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplOnglet oldValue = toTplOnglet();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplOnglet");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplOnglet");
    }
  }
  
  public NSArray tosRepartFicheBlocActivation() {
    return (NSArray)storedValueForKey("tosRepartFicheBlocActivation");
  }

  public NSArray tosRepartFicheBlocActivation(EOQualifier qualifier) {
    return tosRepartFicheBlocActivation(qualifier, null, false);
  }

  public NSArray tosRepartFicheBlocActivation(EOQualifier qualifier, boolean fetch) {
    return tosRepartFicheBlocActivation(qualifier, null, fetch);
  }

  public NSArray tosRepartFicheBlocActivation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation.TO_TPL_BLOC_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation.fetchRepartFicheBlocActivations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFicheBlocActivation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFicheBlocActivationRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFicheBlocActivation");
  }

  public void removeFromTosRepartFicheBlocActivationRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFicheBlocActivation");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation createTosRepartFicheBlocActivationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFicheBlocActivation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFicheBlocActivation");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation) eo;
  }

  public void deleteTosRepartFicheBlocActivationRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFicheBlocActivation");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartFicheBlocActivationRelationships() {
    Enumeration objects = tosRepartFicheBlocActivation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFicheBlocActivationRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation)objects.nextElement());
    }
  }

  public NSArray tosTplItem() {
    return (NSArray)storedValueForKey("tosTplItem");
  }

  public NSArray tosTplItem(EOQualifier qualifier) {
    return tosTplItem(qualifier, null, false);
  }

  public NSArray tosTplItem(EOQualifier qualifier, boolean fetch) {
    return tosTplItem(qualifier, null, fetch);
  }

  public NSArray tosTplItem(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOTplItem.TO_TPL_BLOC_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOTplItem.fetchTplItems(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosTplItem();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosTplItemRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItem object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosTplItem");
  }

  public void removeFromTosTplItemRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplItem");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplItem createTosTplItemRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("TplItem");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosTplItem");
    return (org.cocktail.feve.eos.modele.mangue.EOTplItem) eo;
  }

  public void deleteTosTplItemRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplItem");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosTplItemRelationships() {
    Enumeration objects = tosTplItem().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosTplItemRelationship((org.cocktail.feve.eos.modele.mangue.EOTplItem)objects.nextElement());
    }
  }


  public static EOTplBloc createTplBloc(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String tblCode
, String tblFacultatif
, String tblLibelle
, Integer tblPosition
, org.cocktail.feve.eos.modele.mangue.EOTplBlocNature toTplBlocNature, org.cocktail.feve.eos.modele.mangue.EOTplOnglet toTplOnglet) {
    EOTplBloc eo = (EOTplBloc) EOUtilities.createAndInsertInstance(editingContext, _EOTplBloc.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTblCode(tblCode);
		eo.setTblFacultatif(tblFacultatif);
		eo.setTblLibelle(tblLibelle);
		eo.setTblPosition(tblPosition);
    eo.setToTplBlocNatureRelationship(toTplBlocNature);
    eo.setToTplOngletRelationship(toTplOnglet);
    return eo;
  }

  public static NSArray fetchAllTplBlocs(EOEditingContext editingContext) {
    return _EOTplBloc.fetchAllTplBlocs(editingContext, null);
  }

  public static NSArray fetchAllTplBlocs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplBloc.fetchTplBlocs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplBlocs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplBloc.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplBloc fetchTplBloc(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplBloc.fetchTplBloc(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplBloc fetchTplBloc(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplBloc.fetchTplBlocs(editingContext, qualifier, null);
    EOTplBloc eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplBloc)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplBloc that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplBloc fetchRequiredTplBloc(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplBloc.fetchRequiredTplBloc(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplBloc fetchRequiredTplBloc(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplBloc eoObject = _EOTplBloc.fetchTplBloc(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplBloc that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplBloc localInstanceIn(EOEditingContext editingContext, EOTplBloc eo) {
    EOTplBloc localInstance = (eo == null) ? null : (EOTplBloc)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
