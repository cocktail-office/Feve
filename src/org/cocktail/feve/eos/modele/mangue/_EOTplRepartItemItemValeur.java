// _EOTplRepartItemItemValeur.java
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

// DO NOT EDIT.  Make changes to EOTplRepartItemItemValeur.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplRepartItemItemValeur extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplRepartItemItemValeur";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_REPART_ITEM_ITEM_VALEUR";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TIV_POSITION_KEY = "tivPosition";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TIV_POSITION_COLKEY = "TIV_POSITION";

	// Relationships
	public static final String TO_TPL_ITEM_KEY = "toTplItem";
	public static final String TO_TPL_ITEM_VALEUR_KEY = "toTplItemValeur";

	// Utilities methods
  public EOTplRepartItemItemValeur localInstanceIn(EOEditingContext editingContext) {
    EOTplRepartItemItemValeur localInstance = (EOTplRepartItemItemValeur)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer tivPosition() {
    return (Integer) storedValueForKey("tivPosition");
  }

  public void setTivPosition(Integer value) {
    takeStoredValueForKey(value, "tivPosition");
  }

  public org.cocktail.feve.eos.modele.mangue.EOTplItem toTplItem() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplItem)storedValueForKey("toTplItem");
  }

  public void setToTplItemRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItem value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplItem oldValue = toTplItem();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplItem");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplItem");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOTplItemValeur toTplItemValeur() {
    return (org.cocktail.feve.eos.modele.mangue.EOTplItemValeur)storedValueForKey("toTplItemValeur");
  }

  public void setToTplItemValeurRelationship(org.cocktail.feve.eos.modele.mangue.EOTplItemValeur value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTplItemValeur oldValue = toTplItemValeur();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTplItemValeur");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTplItemValeur");
    }
  }
  

  public static EOTplRepartItemItemValeur createTplRepartItemItemValeur(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOTplItem toTplItem, org.cocktail.feve.eos.modele.mangue.EOTplItemValeur toTplItemValeur) {
    EOTplRepartItemItemValeur eo = (EOTplRepartItemItemValeur) EOUtilities.createAndInsertInstance(editingContext, _EOTplRepartItemItemValeur.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToTplItemRelationship(toTplItem);
    eo.setToTplItemValeurRelationship(toTplItemValeur);
    return eo;
  }

  public static NSArray fetchAllTplRepartItemItemValeurs(EOEditingContext editingContext) {
    return _EOTplRepartItemItemValeur.fetchAllTplRepartItemItemValeurs(editingContext, null);
  }

  public static NSArray fetchAllTplRepartItemItemValeurs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplRepartItemItemValeur.fetchTplRepartItemItemValeurs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplRepartItemItemValeurs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplRepartItemItemValeur.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplRepartItemItemValeur fetchTplRepartItemItemValeur(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplRepartItemItemValeur.fetchTplRepartItemItemValeur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplRepartItemItemValeur fetchTplRepartItemItemValeur(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplRepartItemItemValeur.fetchTplRepartItemItemValeurs(editingContext, qualifier, null);
    EOTplRepartItemItemValeur eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplRepartItemItemValeur)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplRepartItemItemValeur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplRepartItemItemValeur fetchRequiredTplRepartItemItemValeur(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplRepartItemItemValeur.fetchRequiredTplRepartItemItemValeur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplRepartItemItemValeur fetchRequiredTplRepartItemItemValeur(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplRepartItemItemValeur eoObject = _EOTplRepartItemItemValeur.fetchTplRepartItemItemValeur(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplRepartItemItemValeur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplRepartItemItemValeur localInstanceIn(EOEditingContext editingContext, EOTplRepartItemItemValeur eo) {
    EOTplRepartItemItemValeur localInstance = (eo == null) ? null : (EOTplRepartItemItemValeur)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
