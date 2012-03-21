// _EORepartFicheItem.java
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

// DO NOT EDIT.  Make changes to EORepartFicheItem.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EORepartFicheItem extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "RepartFicheItem";
	public static final String ENTITY_TABLE_NAME = "MANGUE.REPART_FICHE_ITEM";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String FIC_KEY_KEY = "ficKey";
	public static final String RFI_VALEUR_LIBRE_KEY = "rfiValeurLibre";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String FIC_KEY_COLKEY = "FIC_KEY";
	public static final String RFI_VALEUR_LIBRE_COLKEY = "RFI_VALEUR_LIBRE";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";
	public static final String TO_FICHE_DE_POSTE_KEY = "toFicheDePoste";
	public static final String TO_FICHE_LOLF_KEY = "toFicheLolf";
	public static final String TO_TPL_ITEM_KEY = "toTplItem";
	public static final String TO_TPL_ITEM_VALEUR_KEY = "toTplItemValeur";

	// Utilities methods
  public EORepartFicheItem localInstanceIn(EOEditingContext editingContext) {
    EORepartFicheItem localInstance = (EORepartFicheItem)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public Integer ficKey() {
    return (Integer) storedValueForKey("ficKey");
  }

  public void setFicKey(Integer value) {
    takeStoredValueForKey(value, "ficKey");
  }

  public String rfiValeurLibre() {
    return (String) storedValueForKey("rfiValeurLibre");
  }

  public void setRfiValeurLibre(String value) {
    takeStoredValueForKey(value, "rfiValeurLibre");
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
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toFicheDePoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheDePoste)storedValueForKey("toFicheDePoste");
  }

  public void setToFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheDePoste oldValue = toFicheDePoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheDePoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheDePoste");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheLolf toFicheLolf() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheLolf)storedValueForKey("toFicheLolf");
  }

  public void setToFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheLolf oldValue = toFicheLolf();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFicheLolf");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFicheLolf");
    }
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
  

  public static EORepartFicheItem createRepartFicheItem(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, org.cocktail.feve.eos.modele.mangue.EOTplItem toTplItem) {
    EORepartFicheItem eo = (EORepartFicheItem) EOUtilities.createAndInsertInstance(editingContext, _EORepartFicheItem.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    eo.setToTplItemRelationship(toTplItem);
    return eo;
  }

  public static NSArray fetchAllRepartFicheItems(EOEditingContext editingContext) {
    return _EORepartFicheItem.fetchAllRepartFicheItems(editingContext, null);
  }

  public static NSArray fetchAllRepartFicheItems(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EORepartFicheItem.fetchRepartFicheItems(editingContext, null, sortOrderings);
  }

  public static NSArray fetchRepartFicheItems(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EORepartFicheItem.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EORepartFicheItem fetchRepartFicheItem(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFicheItem.fetchRepartFicheItem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFicheItem fetchRepartFicheItem(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EORepartFicheItem.fetchRepartFicheItems(editingContext, qualifier, null);
    EORepartFicheItem eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EORepartFicheItem)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RepartFicheItem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFicheItem fetchRequiredRepartFicheItem(EOEditingContext editingContext, String keyName, Object value) {
    return _EORepartFicheItem.fetchRequiredRepartFicheItem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EORepartFicheItem fetchRequiredRepartFicheItem(EOEditingContext editingContext, EOQualifier qualifier) {
    EORepartFicheItem eoObject = _EORepartFicheItem.fetchRepartFicheItem(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RepartFicheItem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EORepartFicheItem localInstanceIn(EOEditingContext editingContext, EORepartFicheItem eo) {
    EORepartFicheItem localInstance = (eo == null) ? null : (EORepartFicheItem)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
