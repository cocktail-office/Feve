// _EOTplOnglet.java
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

// DO NOT EDIT.  Make changes to EOTplOnglet.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplOnglet extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplOnglet";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_ONGLET";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TEM_CONTRACTUEL_KEY = "temContractuel";
	public static final String TEM_TITULAIRE_KEY = "temTitulaire";
	public static final String TON_CODE_KEY = "tonCode";
	public static final String TON_COMMENTAIRE_KEY = "tonCommentaire";
	public static final String TON_LIBELLE_KEY = "tonLibelle";
	public static final String TON_POSITION_KEY = "tonPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TEM_CONTRACTUEL_COLKEY = "TEM_CONTRACTUEL";
	public static final String TEM_TITULAIRE_COLKEY = "TEM_TITULAIRE";
	public static final String TON_CODE_COLKEY = "TON_CODE";
	public static final String TON_COMMENTAIRE_COLKEY = "TON_COMMENTAIRE";
	public static final String TON_LIBELLE_COLKEY = "TON_LIBELLE";
	public static final String TON_POSITION_COLKEY = "TON_POSITION";

	// Relationships
	public static final String TOS_TPL_BLOC_KEY = "tosTplBloc";
	public static final String TO_TPL_FICHE_KEY = "toTplFiche";

	// Utilities methods
  public EOTplOnglet localInstanceIn(EOEditingContext editingContext) {
    EOTplOnglet localInstance = (EOTplOnglet)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String temContractuel() {
    return (String) storedValueForKey("temContractuel");
  }

  public void setTemContractuel(String value) {
    takeStoredValueForKey(value, "temContractuel");
  }

  public String temTitulaire() {
    return (String) storedValueForKey("temTitulaire");
  }

  public void setTemTitulaire(String value) {
    takeStoredValueForKey(value, "temTitulaire");
  }

  public String tonCode() {
    return (String) storedValueForKey("tonCode");
  }

  public void setTonCode(String value) {
    takeStoredValueForKey(value, "tonCode");
  }

  public String tonCommentaire() {
    return (String) storedValueForKey("tonCommentaire");
  }

  public void setTonCommentaire(String value) {
    takeStoredValueForKey(value, "tonCommentaire");
  }

  public String tonLibelle() {
    return (String) storedValueForKey("tonLibelle");
  }

  public void setTonLibelle(String value) {
    takeStoredValueForKey(value, "tonLibelle");
  }

  public Integer tonPosition() {
    return (Integer) storedValueForKey("tonPosition");
  }

  public void setTonPosition(Integer value) {
    takeStoredValueForKey(value, "tonPosition");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplFiche toTplFiche() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplFiche)storedValueForKey("toTplFiche");
  }

  public void setToTplFicheRelationship(org.cocktail.feve.eos.modele.mangue.EOTplFiche value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplFiche oldValue = toTplFiche();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplFiche");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplFiche");
    }
  }
  
  public NSArray tosTplBloc() {
    return (NSArray)storedValueForKey("tosTplBloc");
  }

  public NSArray tosTplBloc(EOQualifier qualifier) {
    return tosTplBloc(qualifier, null, false);
  }

  public NSArray tosTplBloc(EOQualifier qualifier, boolean fetch) {
    return tosTplBloc(qualifier, null, fetch);
  }

  public NSArray tosTplBloc(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOTplBloc.TO_TPL_ONGLET_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOTplBloc.fetchTplBlocs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosTplBloc();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosTplBlocRelationship(org.cocktail.feve.eos.modele.mangue.EOTplBloc object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosTplBloc");
  }

  public void removeFromTosTplBlocRelationship(org.cocktail.feve.eos.modele.mangue.EOTplBloc object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplBloc");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplBloc createTosTplBlocRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("TplBloc");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosTplBloc");
    return (org.cocktail.feve.eos.modele.mangue.EOTplBloc) eo;
  }

  public void deleteTosTplBlocRelationship(org.cocktail.feve.eos.modele.mangue.EOTplBloc object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosTplBloc");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosTplBlocRelationships() {
    Enumeration objects = tosTplBloc().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosTplBlocRelationship((org.cocktail.feve.eos.modele.mangue.EOTplBloc)objects.nextElement());
    }
  }


  public static EOTplOnglet createTplOnglet(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String temContractuel
, String temTitulaire
, String tonCode
, String tonLibelle
, Integer tonPosition
) {
    EOTplOnglet eo = (EOTplOnglet) EOUtilities.createAndInsertInstance(editingContext, _EOTplOnglet.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTemContractuel(temContractuel);
		eo.setTemTitulaire(temTitulaire);
		eo.setTonCode(tonCode);
		eo.setTonLibelle(tonLibelle);
		eo.setTonPosition(tonPosition);
    return eo;
  }

  public static NSArray fetchAllTplOnglets(EOEditingContext editingContext) {
    return _EOTplOnglet.fetchAllTplOnglets(editingContext, null);
  }

  public static NSArray fetchAllTplOnglets(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplOnglet.fetchTplOnglets(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplOnglets(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplOnglet.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplOnglet fetchTplOnglet(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplOnglet.fetchTplOnglet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplOnglet fetchTplOnglet(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplOnglet.fetchTplOnglets(editingContext, qualifier, null);
    EOTplOnglet eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplOnglet)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplOnglet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplOnglet fetchRequiredTplOnglet(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplOnglet.fetchRequiredTplOnglet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplOnglet fetchRequiredTplOnglet(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplOnglet eoObject = _EOTplOnglet.fetchTplOnglet(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplOnglet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplOnglet localInstanceIn(EOEditingContext editingContext, EOTplOnglet eo) {
    EOTplOnglet localInstance = (eo == null) ? null : (EOTplOnglet)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
