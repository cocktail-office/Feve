// _EOTplItemValeur.java
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

// DO NOT EDIT.  Make changes to EOTplItemValeur.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTplItemValeur extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TplItemValeur";
	public static final String ENTITY_TABLE_NAME = "MANGUE.TPL_ITEM_VALEUR";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String TIV_COMMENTAIRE_KEY = "tivCommentaire";
	public static final String TIV_LIBELLE_KEY = "tivLibelle";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String TIV_COMMENTAIRE_COLKEY = "TIV_COMMENTAIRE";
	public static final String TIV_LIBELLE_COLKEY = "TIV_LIBELLE";

	// Relationships

	// Utilities methods
  public EOTplItemValeur localInstanceIn(EOEditingContext editingContext) {
    EOTplItemValeur localInstance = (EOTplItemValeur)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String tivCommentaire() {
    return (String) storedValueForKey("tivCommentaire");
  }

  public void setTivCommentaire(String value) {
    takeStoredValueForKey(value, "tivCommentaire");
  }

  public String tivLibelle() {
    return (String) storedValueForKey("tivLibelle");
  }

  public void setTivLibelle(String value) {
    takeStoredValueForKey(value, "tivLibelle");
  }


  public static EOTplItemValeur createTplItemValeur(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String tivLibelle
) {
    EOTplItemValeur eo = (EOTplItemValeur) EOUtilities.createAndInsertInstance(editingContext, _EOTplItemValeur.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTivLibelle(tivLibelle);
    return eo;
  }

  public static NSArray fetchAllTplItemValeurs(EOEditingContext editingContext) {
    return _EOTplItemValeur.fetchAllTplItemValeurs(editingContext, null);
  }

  public static NSArray fetchAllTplItemValeurs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTplItemValeur.fetchTplItemValeurs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTplItemValeurs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTplItemValeur.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTplItemValeur fetchTplItemValeur(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplItemValeur.fetchTplItemValeur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplItemValeur fetchTplItemValeur(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTplItemValeur.fetchTplItemValeurs(editingContext, qualifier, null);
    EOTplItemValeur eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTplItemValeur)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TplItemValeur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplItemValeur fetchRequiredTplItemValeur(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTplItemValeur.fetchRequiredTplItemValeur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTplItemValeur fetchRequiredTplItemValeur(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTplItemValeur eoObject = _EOTplItemValeur.fetchTplItemValeur(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TplItemValeur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTplItemValeur localInstanceIn(EOEditingContext editingContext, EOTplItemValeur eo) {
    EOTplItemValeur localInstance = (eo == null) ? null : (EOTplItemValeur)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
