// _EOStage.java
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

// DO NOT EDIT.  Make changes to EOStage.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOStage extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Stage";
	public static final String ENTITY_TABLE_NAME = "MANGUE.STAGE";

	// Attributes
	public static final String C_CORPS_KEY = "cCorps";
	public static final String DATE_TITULARISATION_KEY = "dateTitularisation";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_STAGE_KEY = "dDebStage";
	public static final String D_FIN_STAGE_KEY = "dFinStage";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String NO_DOSSIER_PERS_KEY = "noDossierPers";
	public static final String NO_SEQ_CARRIERE_KEY = "noSeqCarriere";
	public static final String TEM_RENOUVELLEMENT_KEY = "temRenouvellement";
	public static final String TEM_VALIDE_KEY = "temValide";

	public static final String C_CORPS_COLKEY = "C_CORPS";
	public static final String DATE_TITULARISATION_COLKEY = "DATE_TITULARISATION";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_STAGE_COLKEY = "D_DEB_STAGE";
	public static final String D_FIN_STAGE_COLKEY = "D_FIN_STAGE";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String NO_DOSSIER_PERS_COLKEY = "NO_DOSSIER_PERS";
	public static final String NO_SEQ_CARRIERE_COLKEY = "NO_SEQ_CARRIERE";
	public static final String TEM_RENOUVELLEMENT_COLKEY = "TEM_RENOUVELLEMENT";
	public static final String TEM_VALIDE_COLKEY = "TEM_VALIDE";

	// Relationships
	public static final String TO_CORPS_KEY = "toCorps";
	public static final String TO_INDIVIDU_KEY = "toIndividu";

	// Utilities methods
  public EOStage localInstanceIn(EOEditingContext editingContext) {
    EOStage localInstance = (EOStage)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dateTitularisation() {
    return (NSTimestamp) storedValueForKey("dateTitularisation");
  }

  public void setDateTitularisation(NSTimestamp value) {
    takeStoredValueForKey(value, "dateTitularisation");
  }

  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dDebStage() {
    return (NSTimestamp) storedValueForKey("dDebStage");
  }

  public void setDDebStage(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebStage");
  }

  public NSTimestamp dFinStage() {
    return (NSTimestamp) storedValueForKey("dFinStage");
  }

  public void setDFinStage(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinStage");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public Integer noDossierPers() {
    return (Integer) storedValueForKey("noDossierPers");
  }

  public void setNoDossierPers(Integer value) {
    takeStoredValueForKey(value, "noDossierPers");
  }

  public Integer noSeqCarriere() {
    return (Integer) storedValueForKey("noSeqCarriere");
  }

  public void setNoSeqCarriere(Integer value) {
    takeStoredValueForKey(value, "noSeqCarriere");
  }

  public String temRenouvellement() {
    return (String) storedValueForKey("temRenouvellement");
  }

  public void setTemRenouvellement(String value) {
    takeStoredValueForKey(value, "temRenouvellement");
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
  

  public static EOStage createStage(EOEditingContext editingContext, String cCorps
, NSTimestamp dateTitularisation
, NSTimestamp dCreation
, NSTimestamp dDebStage
, NSTimestamp dModification
, Integer noDossierPers
, Integer noSeqCarriere
, String temRenouvellement
, org.cocktail.feve.eos.modele.grhum.EOCorps toCorps, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu) {
    EOStage eo = (EOStage) EOUtilities.createAndInsertInstance(editingContext, _EOStage.ENTITY_NAME);    
		eo.setCCorps(cCorps);
		eo.setDateTitularisation(dateTitularisation);
		eo.setDCreation(dCreation);
		eo.setDDebStage(dDebStage);
		eo.setDModification(dModification);
		eo.setNoDossierPers(noDossierPers);
		eo.setNoSeqCarriere(noSeqCarriere);
		eo.setTemRenouvellement(temRenouvellement);
    eo.setToCorpsRelationship(toCorps);
    eo.setToIndividuRelationship(toIndividu);
    return eo;
  }

  public static NSArray fetchAllStages(EOEditingContext editingContext) {
    return _EOStage.fetchAllStages(editingContext, null);
  }

  public static NSArray fetchAllStages(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOStage.fetchStages(editingContext, null, sortOrderings);
  }

  public static NSArray fetchStages(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOStage.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOStage fetchStage(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStage.fetchStage(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStage fetchStage(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOStage.fetchStages(editingContext, qualifier, null);
    EOStage eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOStage)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Stage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStage fetchRequiredStage(EOEditingContext editingContext, String keyName, Object value) {
    return _EOStage.fetchRequiredStage(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOStage fetchRequiredStage(EOEditingContext editingContext, EOQualifier qualifier) {
    EOStage eoObject = _EOStage.fetchStage(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Stage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOStage localInstanceIn(EOEditingContext editingContext, EOStage eo) {
    EOStage localInstance = (eo == null) ? null : (EOStage)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
