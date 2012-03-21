// _EOCorps.java
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

// DO NOT EDIT.  Make changes to EOCorps.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOCorps extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Corps";
	public static final String ENTITY_TABLE_NAME = "GRHUM.CORPS";

	// Attributes
	public static final String C_BUREAU_GESTION_KEY = "cBureauGestion";
	public static final String C_CATEGORIE_KEY = "cCategorie";
	public static final String C_FILIERE_KEY = "cFiliere";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_FERMETURE_CORPS_KEY = "dFermetureCorps";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String D_OUVERTURE_CORPS_KEY = "dOuvertureCorps";
	public static final String LC_CORPS_KEY = "lcCorps";
	public static final String LL_CORPS_KEY = "llCorps";
	public static final String MASSE_INDICIAIRE_KEY = "masseIndiciaire";
	public static final String TEM_CFP_KEY = "temCfp";
	public static final String TEM_CRCT_KEY = "temCrct";
	public static final String TEM_DELEGATION_KEY = "temDelegation";
	public static final String TEM_MISDEP_KEY = "temMisdep";
	public static final String TEM_SURNOMBRE_KEY = "temSurnombre";

	public static final String C_BUREAU_GESTION_COLKEY = "C_BUREAU_GESTION";
	public static final String C_CATEGORIE_COLKEY = "C_CATEGORIE";
	public static final String C_FILIERE_COLKEY = "C_FILIERE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_FERMETURE_CORPS_COLKEY = "D_FERMETURE_CORPS";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String D_OUVERTURE_CORPS_COLKEY = "D_OUVERTURE_CORPS";
	public static final String LC_CORPS_COLKEY = "LC_CORPS";
	public static final String LL_CORPS_COLKEY = "LL_CORPS";
	public static final String MASSE_INDICIAIRE_COLKEY = "MASSE_INDICIAIRE";
	public static final String TEM_CFP_COLKEY = "TEM_CFP";
	public static final String TEM_CRCT_COLKEY = "TEM_CRCT";
	public static final String TEM_DELEGATION_COLKEY = "TEM_DELEGATION";
	public static final String TEM_MISDEP_COLKEY = "TEM_MISDEP";
	public static final String TEM_SURNOMBRE_COLKEY = "TEM_SURNOMBRE";

	// Relationships
	public static final String TO_TYPE_POPULATION_KEY = "toTypePopulation";

	// Utilities methods
  public EOCorps localInstanceIn(EOEditingContext editingContext) {
    EOCorps localInstance = (EOCorps)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cBureauGestion() {
    return (String) storedValueForKey("cBureauGestion");
  }

  public void setCBureauGestion(String value) {
    takeStoredValueForKey(value, "cBureauGestion");
  }

  public String cCategorie() {
    return (String) storedValueForKey("cCategorie");
  }

  public void setCCategorie(String value) {
    takeStoredValueForKey(value, "cCategorie");
  }

  public String cFiliere() {
    return (String) storedValueForKey("cFiliere");
  }

  public void setCFiliere(String value) {
    takeStoredValueForKey(value, "cFiliere");
  }

  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dFermetureCorps() {
    return (NSTimestamp) storedValueForKey("dFermetureCorps");
  }

  public void setDFermetureCorps(NSTimestamp value) {
    takeStoredValueForKey(value, "dFermetureCorps");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public NSTimestamp dOuvertureCorps() {
    return (NSTimestamp) storedValueForKey("dOuvertureCorps");
  }

  public void setDOuvertureCorps(NSTimestamp value) {
    takeStoredValueForKey(value, "dOuvertureCorps");
  }

  public String lcCorps() {
    return (String) storedValueForKey("lcCorps");
  }

  public void setLcCorps(String value) {
    takeStoredValueForKey(value, "lcCorps");
  }

  public String llCorps() {
    return (String) storedValueForKey("llCorps");
  }

  public void setLlCorps(String value) {
    takeStoredValueForKey(value, "llCorps");
  }

  public Integer masseIndiciaire() {
    return (Integer) storedValueForKey("masseIndiciaire");
  }

  public void setMasseIndiciaire(Integer value) {
    takeStoredValueForKey(value, "masseIndiciaire");
  }

  public String temCfp() {
    return (String) storedValueForKey("temCfp");
  }

  public void setTemCfp(String value) {
    takeStoredValueForKey(value, "temCfp");
  }

  public String temCrct() {
    return (String) storedValueForKey("temCrct");
  }

  public void setTemCrct(String value) {
    takeStoredValueForKey(value, "temCrct");
  }

  public String temDelegation() {
    return (String) storedValueForKey("temDelegation");
  }

  public void setTemDelegation(String value) {
    takeStoredValueForKey(value, "temDelegation");
  }

  public String temMisdep() {
    return (String) storedValueForKey("temMisdep");
  }

  public void setTemMisdep(String value) {
    takeStoredValueForKey(value, "temMisdep");
  }

  public String temSurnombre() {
    return (String) storedValueForKey("temSurnombre");
  }

  public void setTemSurnombre(String value) {
    takeStoredValueForKey(value, "temSurnombre");
  }

  public org.cocktail.feve.eos.modele.grhum.EOTypePopulation toTypePopulation() {
    return (org.cocktail.feve.eos.modele.grhum.EOTypePopulation)storedValueForKey("toTypePopulation");
  }

  public void setToTypePopulationRelationship(org.cocktail.feve.eos.modele.grhum.EOTypePopulation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOTypePopulation oldValue = toTypePopulation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTypePopulation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTypePopulation");
    }
  }
  

  public static EOCorps createCorps(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
) {
    EOCorps eo = (EOCorps) EOUtilities.createAndInsertInstance(editingContext, _EOCorps.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
    return eo;
  }

  public static NSArray fetchAllCorpses(EOEditingContext editingContext) {
    return _EOCorps.fetchAllCorpses(editingContext, null);
  }

  public static NSArray fetchAllCorpses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOCorps.fetchCorpses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchCorpses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOCorps.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOCorps fetchCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOCorps.fetchCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOCorps fetchCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOCorps.fetchCorpses(editingContext, qualifier, null);
    EOCorps eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOCorps)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Corps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOCorps fetchRequiredCorps(EOEditingContext editingContext, String keyName, Object value) {
    return _EOCorps.fetchRequiredCorps(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOCorps fetchRequiredCorps(EOEditingContext editingContext, EOQualifier qualifier) {
    EOCorps eoObject = _EOCorps.fetchCorps(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Corps that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOCorps localInstanceIn(EOEditingContext editingContext, EOCorps eo) {
    EOCorps localInstance = (eo == null) ? null : (EOCorps)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
