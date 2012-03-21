// _EODroit.java
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

// DO NOT EDIT.  Make changes to EODroit.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EODroit extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Droit";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FEV_DROIT";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DRO_C_STRUCTURE_KEY = "droCStructure";
	public static final String DRO_C_STRUCTURE_COMPOSANTE_KEY = "droCStructureComposante";
	public static final String DRO_EPE_KEY_KEY = "droEpeKey";
	public static final String DRO_FDP_KEY_KEY = "droFdpKey";
	public static final String DRO_FLO_KEY_KEY = "droFloKey";
	public static final String DRO_NO_INDIVIDU_KEY = "droNoIndividu";
	public static final String DRO_POS_KEY_KEY = "droPosKey";
	public static final String NO_INDIVIDU_KEY = "noIndividu";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DRO_C_STRUCTURE_COLKEY = "DRO_C_STRUCTURE";
	public static final String DRO_C_STRUCTURE_COMPOSANTE_COLKEY = "DRO_C_STRUCTURE_COMPOSANTE";
	public static final String DRO_EPE_KEY_COLKEY = "DRO_EPE_KEY";
	public static final String DRO_FDP_KEY_COLKEY = "DRO_FDP_KEY";
	public static final String DRO_FLO_KEY_COLKEY = "DRO_FLO_KEY";
	public static final String DRO_NO_INDIVIDU_COLKEY = "DRO_NO_INDIVIDU";
	public static final String DRO_POS_KEY_COLKEY = "DRO_POS_KEY";
	public static final String NO_INDIVIDU_COLKEY = "NO_INDIVIDU";

	// Relationships
	public static final String TO_DROIT_COMPOSANTE_KEY = "toDroitComposante";
	public static final String TO_DROIT_EVALUATION_PERIODE_KEY = "toDroitEvaluationPeriode";
	public static final String TO_DROIT_FICHE_DE_POSTE_KEY = "toDroitFicheDePoste";
	public static final String TO_DROIT_FICHE_LOLF_KEY = "toDroitFicheLolf";
	public static final String TO_DROIT_INDIVIDU_KEY = "toDroitIndividu";
	public static final String TO_DROIT_POSTE_KEY = "toDroitPoste";
	public static final String TO_DROIT_STRUCTURE_KEY = "toDroitStructure";
	public static final String TO_DROIT_V_CANDIDAT_EVALUATION_KEY = "toDroitVCandidatEvaluation";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TO_TYPE_DROIT_ACCES_KEY = "toTypeDroitAcces";
	public static final String TO_TYPE_DROIT_CIBLE_KEY = "toTypeDroitCible";

	// Utilities methods
  public EODroit localInstanceIn(EOEditingContext editingContext) {
    EODroit localInstance = (EODroit)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String droCStructure() {
    return (String) storedValueForKey("droCStructure");
  }

  public void setDroCStructure(String value) {
    takeStoredValueForKey(value, "droCStructure");
  }

  public String droCStructureComposante() {
    return (String) storedValueForKey("droCStructureComposante");
  }

  public void setDroCStructureComposante(String value) {
    takeStoredValueForKey(value, "droCStructureComposante");
  }

  public Integer droEpeKey() {
    return (Integer) storedValueForKey("droEpeKey");
  }

  public void setDroEpeKey(Integer value) {
    takeStoredValueForKey(value, "droEpeKey");
  }

  public Integer droFdpKey() {
    return (Integer) storedValueForKey("droFdpKey");
  }

  public void setDroFdpKey(Integer value) {
    takeStoredValueForKey(value, "droFdpKey");
  }

  public Integer droFloKey() {
    return (Integer) storedValueForKey("droFloKey");
  }

  public void setDroFloKey(Integer value) {
    takeStoredValueForKey(value, "droFloKey");
  }

  public Integer droNoIndividu() {
    return (Integer) storedValueForKey("droNoIndividu");
  }

  public void setDroNoIndividu(Integer value) {
    takeStoredValueForKey(value, "droNoIndividu");
  }

  public Integer droPosKey() {
    return (Integer) storedValueForKey("droPosKey");
  }

  public void setDroPosKey(Integer value) {
    takeStoredValueForKey(value, "droPosKey");
  }

  public Integer noIndividu() {
    return (Integer) storedValueForKey("noIndividu");
  }

  public void setNoIndividu(Integer value) {
    takeStoredValueForKey(value, "noIndividu");
  }

  public org.cocktail.feve.eos.modele.grhum.EOStructure toDroitComposante() {
    return (org.cocktail.feve.eos.modele.grhum.EOStructure)storedValueForKey("toDroitComposante");
  }

  public void setToDroitComposanteRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOStructure oldValue = toDroitComposante();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitComposante");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitComposante");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toDroitEvaluationPeriode() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode)storedValueForKey("toDroitEvaluationPeriode");
  }

  public void setToDroitEvaluationPeriodeRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode oldValue = toDroitEvaluationPeriode();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitEvaluationPeriode");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitEvaluationPeriode");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheDePoste toDroitFicheDePoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheDePoste)storedValueForKey("toDroitFicheDePoste");
  }

  public void setToDroitFicheDePosteRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheDePoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheDePoste oldValue = toDroitFicheDePoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitFicheDePoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitFicheDePoste");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOFicheLolf toDroitFicheLolf() {
    return (org.cocktail.feve.eos.modele.mangue.EOFicheLolf)storedValueForKey("toDroitFicheLolf");
  }

  public void setToDroitFicheLolfRelationship(org.cocktail.feve.eos.modele.mangue.EOFicheLolf value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOFicheLolf oldValue = toDroitFicheLolf();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitFicheLolf");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitFicheLolf");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toDroitIndividu() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toDroitIndividu");
  }

  public void setToDroitIndividuRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toDroitIndividu();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitIndividu");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitIndividu");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOPoste toDroitPoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOPoste)storedValueForKey("toDroitPoste");
  }

  public void setToDroitPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOPoste oldValue = toDroitPoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitPoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitPoste");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOStructure toDroitStructure() {
    return (org.cocktail.feve.eos.modele.grhum.EOStructure)storedValueForKey("toDroitStructure");
  }

  public void setToDroitStructureRelationship(org.cocktail.feve.eos.modele.grhum.EOStructure value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOStructure oldValue = toDroitStructure();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitStructure");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitStructure");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation toDroitVCandidatEvaluation() {
    return (org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation)storedValueForKey("toDroitVCandidatEvaluation");
  }

  public void setToDroitVCandidatEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation oldValue = toDroitVCandidatEvaluation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toDroitVCandidatEvaluation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toDroitVCandidatEvaluation");
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
  
  public org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces toTypeDroitAcces() {
    return (org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces)storedValueForKey("toTypeDroitAcces");
  }

  public void setToTypeDroitAccesRelationship(org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces oldValue = toTypeDroitAcces();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTypeDroitAcces");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTypeDroitAcces");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible toTypeDroitCible() {
    return (org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible)storedValueForKey("toTypeDroitCible");
  }

  public void setToTypeDroitCibleRelationship(org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible oldValue = toTypeDroitCible();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toTypeDroitCible");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toTypeDroitCible");
    }
  }
  

  public static EODroit createDroit(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer noIndividu
, org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividu, org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces toTypeDroitAcces, org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible toTypeDroitCible) {
    EODroit eo = (EODroit) EOUtilities.createAndInsertInstance(editingContext, _EODroit.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setNoIndividu(noIndividu);
    eo.setToIndividuRelationship(toIndividu);
    eo.setToTypeDroitAccesRelationship(toTypeDroitAcces);
    eo.setToTypeDroitCibleRelationship(toTypeDroitCible);
    return eo;
  }

  public static NSArray fetchAllDroits(EOEditingContext editingContext) {
    return _EODroit.fetchAllDroits(editingContext, null);
  }

  public static NSArray fetchAllDroits(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EODroit.fetchDroits(editingContext, null, sortOrderings);
  }

  public static NSArray fetchDroits(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EODroit.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EODroit fetchDroit(EOEditingContext editingContext, String keyName, Object value) {
    return _EODroit.fetchDroit(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODroit fetchDroit(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EODroit.fetchDroits(editingContext, qualifier, null);
    EODroit eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EODroit)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Droit that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODroit fetchRequiredDroit(EOEditingContext editingContext, String keyName, Object value) {
    return _EODroit.fetchRequiredDroit(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EODroit fetchRequiredDroit(EOEditingContext editingContext, EOQualifier qualifier) {
    EODroit eoObject = _EODroit.fetchDroit(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Droit that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EODroit localInstanceIn(EOEditingContext editingContext, EODroit eo) {
    EODroit localInstance = (eo == null) ? null : (EODroit)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
