// _EOElementCarriere.java
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

// DO NOT EDIT.  Make changes to EOElementCarriere.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOElementCarriere extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ElementCarriere";
	public static final String ENTITY_TABLE_NAME = "MANGUE.ELEMENT_CARRIERE";

	// Attributes
	public static final String C_CORPS_KEY = "cCorps";
	public static final String C_ECHELON_KEY = "cEchelon";
	public static final String C_GRADE_KEY = "cGrade";
	public static final String C_TYPE_ACCES_KEY = "cTypeAcces";
	public static final String D_EFFET_ELEMENT_KEY = "dEffetElement";
	public static final String D_FIN_ELEMENT_KEY = "dFinElement";
	public static final String QUOTITE_ELEMENT_KEY = "quotiteElement";
	public static final String TEM_PROVISOIRE_KEY = "temProvisoire";
	public static final String TEM_VALIDE_KEY = "temValide";

	public static final String C_CORPS_COLKEY = "C_CORPS";
	public static final String C_ECHELON_COLKEY = "C_ECHELON";
	public static final String C_GRADE_COLKEY = "C_GRADE";
	public static final String C_TYPE_ACCES_COLKEY = "C_TYPE_ACCES";
	public static final String D_EFFET_ELEMENT_COLKEY = "D_EFFET_ELEMENT";
	public static final String D_FIN_ELEMENT_COLKEY = "D_FIN_ELEMENT";
	public static final String QUOTITE_ELEMENT_COLKEY = "QUOTITE_ELEMENT";
	public static final String TEM_PROVISOIRE_COLKEY = "TEM_PROVISOIRE";
	public static final String TEM_VALIDE_COLKEY = "TEM_VALIDE";

	// Relationships
	public static final String TO_CORPS_KEY = "toCorps";
	public static final String TO_GRADE_KEY = "toGrade";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TOS_REFERENS_EMPLOI_KEY = "tosReferensEmploi";

	// Utilities methods
  public EOElementCarriere localInstanceIn(EOEditingContext editingContext) {
    EOElementCarriere localInstance = (EOElementCarriere)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cCorps() {
    return (String) storedValueForKey("cCorps");
  }

  public void setCCorps(String value) {
    takeStoredValueForKey(value, "cCorps");
  }

  public String cEchelon() {
    return (String) storedValueForKey("cEchelon");
  }

  public void setCEchelon(String value) {
    takeStoredValueForKey(value, "cEchelon");
  }

  public String cGrade() {
    return (String) storedValueForKey("cGrade");
  }

  public void setCGrade(String value) {
    takeStoredValueForKey(value, "cGrade");
  }

  public String cTypeAcces() {
    return (String) storedValueForKey("cTypeAcces");
  }

  public void setCTypeAcces(String value) {
    takeStoredValueForKey(value, "cTypeAcces");
  }

  public NSTimestamp dEffetElement() {
    return (NSTimestamp) storedValueForKey("dEffetElement");
  }

  public void setDEffetElement(NSTimestamp value) {
    takeStoredValueForKey(value, "dEffetElement");
  }

  public NSTimestamp dFinElement() {
    return (NSTimestamp) storedValueForKey("dFinElement");
  }

  public void setDFinElement(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinElement");
  }

  public Integer quotiteElement() {
    return (Integer) storedValueForKey("quotiteElement");
  }

  public void setQuotiteElement(Integer value) {
    takeStoredValueForKey(value, "quotiteElement");
  }

  public String temProvisoire() {
    return (String) storedValueForKey("temProvisoire");
  }

  public void setTemProvisoire(String value) {
    takeStoredValueForKey(value, "temProvisoire");
  }

  public String temValide() {
    return (String) storedValueForKey("temValide");
  }

  public void setTemValide(String value) {
    takeStoredValueForKey(value, "temValide");
  }

  public org.cocktail.feve.eos.modele.grhum.EOCorps toCorps() {
    return (org.cocktail.feve.eos.modele.grhum.EOCorps)storedValueForKey("toCorps");
  }

  public void setToCorpsRelationship(org.cocktail.feve.eos.modele.grhum.EOCorps value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOCorps oldValue = toCorps();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toCorps");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toCorps");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOGrade toGrade() {
    return (org.cocktail.feve.eos.modele.grhum.EOGrade)storedValueForKey("toGrade");
  }

  public void setToGradeRelationship(org.cocktail.feve.eos.modele.grhum.EOGrade value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOGrade oldValue = toGrade();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toGrade");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toGrade");
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
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensEmplois tosReferensEmploi() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensEmplois)storedValueForKey("tosReferensEmploi");
  }

  public void setTosReferensEmploiRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensEmplois oldValue = tosReferensEmploi();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "tosReferensEmploi");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "tosReferensEmploi");
    }
  }
  

  public static EOElementCarriere createElementCarriere(EOEditingContext editingContext, String cGrade
, NSTimestamp dEffetElement
, Integer quotiteElement
, String temProvisoire
, org.cocktail.feve.eos.modele.grhum.EOGrade toGrade, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOElementCarriere eo = (EOElementCarriere) EOUtilities.createAndInsertInstance(editingContext, _EOElementCarriere.ENTITY_NAME);    
		eo.setCGrade(cGrade);
		eo.setDEffetElement(dEffetElement);
		eo.setQuotiteElement(quotiteElement);
		eo.setTemProvisoire(temProvisoire);
    eo.setToGradeRelationship(toGrade);
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllElementCarrieres(EOEditingContext editingContext) {
    return _EOElementCarriere.fetchAllElementCarrieres(editingContext, null);
  }

  public static NSArray fetchAllElementCarrieres(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOElementCarriere.fetchElementCarrieres(editingContext, null, sortOrderings);
  }

  public static NSArray fetchElementCarrieres(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOElementCarriere.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOElementCarriere fetchElementCarriere(EOEditingContext editingContext, String keyName, Object value) {
    return _EOElementCarriere.fetchElementCarriere(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOElementCarriere fetchElementCarriere(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOElementCarriere.fetchElementCarrieres(editingContext, qualifier, null);
    EOElementCarriere eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOElementCarriere)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ElementCarriere that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOElementCarriere fetchRequiredElementCarriere(EOEditingContext editingContext, String keyName, Object value) {
    return _EOElementCarriere.fetchRequiredElementCarriere(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOElementCarriere fetchRequiredElementCarriere(EOEditingContext editingContext, EOQualifier qualifier) {
    EOElementCarriere eoObject = _EOElementCarriere.fetchElementCarriere(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ElementCarriere that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOElementCarriere localInstanceIn(EOEditingContext editingContext, EOElementCarriere eo) {
    EOElementCarriere localInstance = (eo == null) ? null : (EOElementCarriere)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
