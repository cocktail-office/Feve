// _EOReferensDcp.java
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

// DO NOT EDIT.  Make changes to EOReferensDcp.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOReferensDcp extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ReferensDcp";
	public static final String ENTITY_TABLE_NAME = "GRHUM.REFERENS_DCP";

	// Attributes
	public static final String INFOSDCP_KEY = "infosdcp";
	public static final String INTITULDCP_KEY = "intituldcp";
	public static final String LETTREBAP_KEY = "lettrebap";
	public static final String NUMDCP_KEY = "numdcp";
	public static final String SYGLEDCP_KEY = "sygledcp";

	public static final String INFOSDCP_COLKEY = "INFOSDCP";
	public static final String INTITULDCP_COLKEY = "INTITULDCP";
	public static final String LETTREBAP_COLKEY = "LETTREBAP";
	public static final String NUMDCP_COLKEY = "NUMDCP";
	public static final String SYGLEDCP_COLKEY = "SYGLEDCP";

	// Relationships
	public static final String TOS_REFERENS_FP_KEY = "tosReferensFp";

	// Utilities methods
  public EOReferensDcp localInstanceIn(EOEditingContext editingContext) {
    EOReferensDcp localInstance = (EOReferensDcp)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String infosdcp() {
    return (String) storedValueForKey("infosdcp");
  }

  public void setInfosdcp(String value) {
    takeStoredValueForKey(value, "infosdcp");
  }

  public String intituldcp() {
    return (String) storedValueForKey("intituldcp");
  }

  public void setIntituldcp(String value) {
    takeStoredValueForKey(value, "intituldcp");
  }

  public String lettrebap() {
    return (String) storedValueForKey("lettrebap");
  }

  public void setLettrebap(String value) {
    takeStoredValueForKey(value, "lettrebap");
  }

  public String numdcp() {
    return (String) storedValueForKey("numdcp");
  }

  public void setNumdcp(String value) {
    takeStoredValueForKey(value, "numdcp");
  }

  public String sygledcp() {
    return (String) storedValueForKey("sygledcp");
  }

  public void setSygledcp(String value) {
    takeStoredValueForKey(value, "sygledcp");
  }

  public NSArray tosReferensFp() {
    return (NSArray)storedValueForKey("tosReferensFp");
  }

  public NSArray tosReferensFp(EOQualifier qualifier) {
    return tosReferensFp(qualifier, null, false);
  }

  public NSArray tosReferensFp(EOQualifier qualifier, boolean fetch) {
    return tosReferensFp(qualifier, null, fetch);
  }

  public NSArray tosReferensFp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOReferensFp.TO_REFERENS_DCP_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOReferensFp.fetchReferensFps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosReferensFp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosReferensFpRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensFp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReferensFp");
  }

  public void removeFromTosReferensFpRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensFp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensFp");
  }

  public org.cocktail.feve.eos.modele.grhum.EOReferensFp createTosReferensFpRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReferensFp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReferensFp");
    return (org.cocktail.feve.eos.modele.grhum.EOReferensFp) eo;
  }

  public void deleteTosReferensFpRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensFp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReferensFp");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReferensFpRelationships() {
    Enumeration objects = tosReferensFp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReferensFpRelationship((org.cocktail.feve.eos.modele.grhum.EOReferensFp)objects.nextElement());
    }
  }


  public static EOReferensDcp createReferensDcp(EOEditingContext editingContext, String numdcp
) {
    EOReferensDcp eo = (EOReferensDcp) EOUtilities.createAndInsertInstance(editingContext, _EOReferensDcp.ENTITY_NAME);    
		eo.setNumdcp(numdcp);
    return eo;
  }

  public static NSArray fetchAllReferensDcps(EOEditingContext editingContext) {
    return _EOReferensDcp.fetchAllReferensDcps(editingContext, null);
  }

  public static NSArray fetchAllReferensDcps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOReferensDcp.fetchReferensDcps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReferensDcps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOReferensDcp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOReferensDcp fetchReferensDcp(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensDcp.fetchReferensDcp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensDcp fetchReferensDcp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOReferensDcp.fetchReferensDcps(editingContext, qualifier, null);
    EOReferensDcp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOReferensDcp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ReferensDcp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensDcp fetchRequiredReferensDcp(EOEditingContext editingContext, String keyName, Object value) {
    return _EOReferensDcp.fetchRequiredReferensDcp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOReferensDcp fetchRequiredReferensDcp(EOEditingContext editingContext, EOQualifier qualifier) {
    EOReferensDcp eoObject = _EOReferensDcp.fetchReferensDcp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ReferensDcp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOReferensDcp localInstanceIn(EOEditingContext editingContext, EOReferensDcp eo) {
    EOReferensDcp localInstance = (eo == null) ? null : (EOReferensDcp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchReferensDcp(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchReferensDcp", "ReferensDcp");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
