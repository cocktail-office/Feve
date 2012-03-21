// _EOContratAvenant.java
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

// DO NOT EDIT.  Make changes to EOContratAvenant.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOContratAvenant extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "ContratAvenant";
	public static final String ENTITY_TABLE_NAME = "MANGUE.CONTRAT_AVENANT";

	// Attributes
	public static final String D_DEB_CONTRAT_TRAV_AV_KEY = "dDebContratTravAv";
	public static final String D_FIN_CONTRAT_TRAV_AV_KEY = "dFinContratTravAv";
	public static final String NO_SEQ_CONTRAT_KEY = "noSeqContrat";

	public static final String D_DEB_CONTRAT_TRAV_AV_COLKEY = "D_DEB_CONTRAT_AV";
	public static final String D_FIN_CONTRAT_TRAV_AV_COLKEY = "D_FIN_CONTRAT_AV";
	public static final String NO_SEQ_CONTRAT_COLKEY = "NO_SEQ_CONTRAT";

	// Relationships
	public static final String TO_BAP_KEY = "toBap";
	public static final String TO_CONTRAT_KEY = "toContrat";
	public static final String TO_GRADE_KEY = "toGrade";
	public static final String TOS_REFERENS_EMPLOI_KEY = "tosReferensEmploi";

	// Utilities methods
  public EOContratAvenant localInstanceIn(EOEditingContext editingContext) {
    EOContratAvenant localInstance = (EOContratAvenant)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public NSTimestamp dDebContratTravAv() {
    return (NSTimestamp) storedValueForKey("dDebContratTravAv");
  }

  public void setDDebContratTravAv(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebContratTravAv");
  }

  public NSTimestamp dFinContratTravAv() {
    return (NSTimestamp) storedValueForKey("dFinContratTravAv");
  }

  public void setDFinContratTravAv(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinContratTravAv");
  }

  public Integer noSeqContrat() {
    return (Integer) storedValueForKey("noSeqContrat");
  }

  public void setNoSeqContrat(Integer value) {
    takeStoredValueForKey(value, "noSeqContrat");
  }

  public org.cocktail.feve.eos.modele.grhum.EOBap toBap() {
    return (org.cocktail.feve.eos.modele.grhum.EOBap)storedValueForKey("toBap");
  }

  public void setToBapRelationship(org.cocktail.feve.eos.modele.grhum.EOBap value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOBap oldValue = toBap();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toBap");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toBap");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOContrat toContrat() {
    return (org.cocktail.feve.eos.modele.mangue.EOContrat)storedValueForKey("toContrat");
  }

  public void setToContratRelationship(org.cocktail.feve.eos.modele.mangue.EOContrat value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOContrat oldValue = toContrat();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toContrat");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toContrat");
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
  

  public static EOContratAvenant createContratAvenant(EOEditingContext editingContext, NSTimestamp dDebContratTravAv
, Integer noSeqContrat
, org.cocktail.feve.eos.modele.mangue.EOContrat toContrat) {
    EOContratAvenant eo = (EOContratAvenant) EOUtilities.createAndInsertInstance(editingContext, _EOContratAvenant.ENTITY_NAME);    
		eo.setDDebContratTravAv(dDebContratTravAv);
		eo.setNoSeqContrat(noSeqContrat);
    eo.setToContratRelationship(toContrat);
    return eo;
  }

  public static NSArray fetchAllContratAvenants(EOEditingContext editingContext) {
    return _EOContratAvenant.fetchAllContratAvenants(editingContext, null);
  }

  public static NSArray fetchAllContratAvenants(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOContratAvenant.fetchContratAvenants(editingContext, null, sortOrderings);
  }

  public static NSArray fetchContratAvenants(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOContratAvenant.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOContratAvenant fetchContratAvenant(EOEditingContext editingContext, String keyName, Object value) {
    return _EOContratAvenant.fetchContratAvenant(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOContratAvenant fetchContratAvenant(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOContratAvenant.fetchContratAvenants(editingContext, qualifier, null);
    EOContratAvenant eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOContratAvenant)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ContratAvenant that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOContratAvenant fetchRequiredContratAvenant(EOEditingContext editingContext, String keyName, Object value) {
    return _EOContratAvenant.fetchRequiredContratAvenant(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOContratAvenant fetchRequiredContratAvenant(EOEditingContext editingContext, EOQualifier qualifier) {
    EOContratAvenant eoObject = _EOContratAvenant.fetchContratAvenant(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ContratAvenant that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOContratAvenant localInstanceIn(EOEditingContext editingContext, EOContratAvenant eo) {
    EOContratAvenant localInstance = (eo == null) ? null : (EOContratAvenant)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
