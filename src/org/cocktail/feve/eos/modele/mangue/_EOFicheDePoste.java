// _EOFicheDePoste.java
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

// DO NOT EDIT.  Make changes to EOFicheDePoste.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOFicheDePoste extends A_Fiche  {
	public static final String ENTITY_NAME = "FicheDePoste";
	public static final String ENTITY_TABLE_NAME = "MANGUE.FICHE_DE_POSTE";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String ETY_CODE_KEY = "etyCode";
	public static final String FDP_CONTEXTE_TRAVAIL_KEY = "fdpContexteTravail";
	public static final String FDP_D_DEBUT_KEY = "fdpDDebut";
	public static final String FDP_D_FIN_KEY = "fdpDFin";
	public static final String FDP_MISSION_POSTE_KEY = "fdpMissionPoste";
	public static final String FDP_VISA_AGENT_KEY = "fdpVisaAgent";
	public static final String FDP_VISA_DIREC_KEY = "fdpVisaDirec";
	public static final String FDP_VISA_RESP_KEY = "fdpVisaResp";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String ETY_CODE_COLKEY = "ETY_CODE";
	public static final String FDP_CONTEXTE_TRAVAIL_COLKEY = "FDP_CONTEXTE_TRAVAIL";
	public static final String FDP_D_DEBUT_COLKEY = "FDP_D_DEBUT";
	public static final String FDP_D_FIN_COLKEY = "FDP_D_FIN";
	public static final String FDP_MISSION_POSTE_COLKEY = "FDP_MISSION_POSTE";
	public static final String FDP_VISA_AGENT_COLKEY = "FDP_VISA_AGENT";
	public static final String FDP_VISA_DIREC_COLKEY = "FDP_VISA_DIREC";
	public static final String FDP_VISA_RESP_COLKEY = "FDP_VISA_RESP";

	// Relationships
	public static final String TO_EMPLOI_TYPE_KEY = "toEmploiType";
	public static final String TO_POSTE_KEY = "toPoste";
	public static final String TO_REFERENS_EMPLOIS_KEY = "toReferensEmplois";
	public static final String TOS_REPART_FDP_ACTI_KEY = "tosRepartFdpActi";
	public static final String TOS_REPART_FDP_AUTRE_KEY = "tosRepartFdpAutre";
	public static final String TOS_REPART_FDP_COMP_KEY = "tosRepartFdpComp";

	// Utilities methods
  public EOFicheDePoste localInstanceIn(EOEditingContext editingContext) {
    EOFicheDePoste localInstance = (EOFicheDePoste)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String etyCode() {
    return (String) storedValueForKey("etyCode");
  }

  public void setEtyCode(String value) {
    takeStoredValueForKey(value, "etyCode");
  }

  public String fdpContexteTravail() {
    return (String) storedValueForKey("fdpContexteTravail");
  }

  public void setFdpContexteTravail(String value) {
    takeStoredValueForKey(value, "fdpContexteTravail");
  }

  public NSTimestamp fdpDDebut() {
    return (NSTimestamp) storedValueForKey("fdpDDebut");
  }

  public void setFdpDDebut(NSTimestamp value) {
    takeStoredValueForKey(value, "fdpDDebut");
  }

  public NSTimestamp fdpDFin() {
    return (NSTimestamp) storedValueForKey("fdpDFin");
  }

  public void setFdpDFin(NSTimestamp value) {
    takeStoredValueForKey(value, "fdpDFin");
  }

  public String fdpMissionPoste() {
    return (String) storedValueForKey("fdpMissionPoste");
  }

  public void setFdpMissionPoste(String value) {
    takeStoredValueForKey(value, "fdpMissionPoste");
  }

  public String fdpVisaAgent() {
    return (String) storedValueForKey("fdpVisaAgent");
  }

  public void setFdpVisaAgent(String value) {
    takeStoredValueForKey(value, "fdpVisaAgent");
  }

  public String fdpVisaDirec() {
    return (String) storedValueForKey("fdpVisaDirec");
  }

  public void setFdpVisaDirec(String value) {
    takeStoredValueForKey(value, "fdpVisaDirec");
  }

  public String fdpVisaResp() {
    return (String) storedValueForKey("fdpVisaResp");
  }

  public void setFdpVisaResp(String value) {
    takeStoredValueForKey(value, "fdpVisaResp");
  }

  public org.cocktail.feve.eos.modele.grhum.old.EOEmploiType toEmploiType() {
    return (org.cocktail.feve.eos.modele.grhum.old.EOEmploiType)storedValueForKey("toEmploiType");
  }

  public void setToEmploiTypeRelationship(org.cocktail.feve.eos.modele.grhum.old.EOEmploiType value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.old.EOEmploiType oldValue = toEmploiType();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEmploiType");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEmploiType");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOPoste toPoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOPoste)storedValueForKey("toPoste");
  }

  public void setToPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOPoste oldValue = toPoste();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toPoste");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toPoste");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOReferensEmplois toReferensEmplois() {
    return (org.cocktail.feve.eos.modele.grhum.EOReferensEmplois)storedValueForKey("toReferensEmplois");
  }

  public void setToReferensEmploisRelationship(org.cocktail.feve.eos.modele.grhum.EOReferensEmplois value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOReferensEmplois oldValue = toReferensEmplois();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toReferensEmplois");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toReferensEmplois");
    }
  }
  
  public NSArray tosRepartFdpActi() {
    return (NSArray)storedValueForKey("tosRepartFdpActi");
  }

  public NSArray tosRepartFdpActi(EOQualifier qualifier) {
    return tosRepartFdpActi(qualifier, null, false);
  }

  public NSArray tosRepartFdpActi(EOQualifier qualifier, boolean fetch) {
    return tosRepartFdpActi(qualifier, null, fetch);
  }

  public NSArray tosRepartFdpActi(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFdpActi.TO_FICHE_DE_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFdpActi.fetchRepartFdpActis(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFdpActi();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFdpActiRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpActi object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFdpActi");
  }

  public void removeFromTosRepartFdpActiRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpActi object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpActi");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFdpActi createTosRepartFdpActiRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFdpActi");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFdpActi");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFdpActi) eo;
  }

  public void deleteTosRepartFdpActiRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpActi object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpActi");
  }

  public void deleteAllTosRepartFdpActiRelationships() {
    Enumeration objects = tosRepartFdpActi().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFdpActiRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFdpActi)objects.nextElement());
    }
  }

  public NSArray tosRepartFdpAutre() {
    return (NSArray)storedValueForKey("tosRepartFdpAutre");
  }

  public NSArray tosRepartFdpAutre(EOQualifier qualifier) {
    return tosRepartFdpAutre(qualifier, null, false);
  }

  public NSArray tosRepartFdpAutre(EOQualifier qualifier, boolean fetch) {
    return tosRepartFdpAutre(qualifier, null, fetch);
  }

  public NSArray tosRepartFdpAutre(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre.TO_FICHE_DE_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre.fetchRepartFdpAutres(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFdpAutre();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFdpAutreRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFdpAutre");
  }

  public void removeFromTosRepartFdpAutreRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpAutre");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre createTosRepartFdpAutreRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFdpAutre");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFdpAutre");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre) eo;
  }

  public void deleteTosRepartFdpAutreRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpAutre");
  }

  public void deleteAllTosRepartFdpAutreRelationships() {
    Enumeration objects = tosRepartFdpAutre().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFdpAutreRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre)objects.nextElement());
    }
  }

  public NSArray tosRepartFdpComp() {
    return (NSArray)storedValueForKey("tosRepartFdpComp");
  }

  public NSArray tosRepartFdpComp(EOQualifier qualifier) {
    return tosRepartFdpComp(qualifier, null, false);
  }

  public NSArray tosRepartFdpComp(EOQualifier qualifier, boolean fetch) {
    return tosRepartFdpComp(qualifier, null, fetch);
  }

  public NSArray tosRepartFdpComp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFdpComp.TO_FICHE_DE_POSTE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFdpComp.fetchRepartFdpComps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFdpComp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFdpCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpComp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFdpComp");
  }

  public void removeFromTosRepartFdpCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpComp");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFdpComp createTosRepartFdpCompRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFdpComp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFdpComp");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFdpComp) eo;
  }

  public void deleteTosRepartFdpCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFdpComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFdpComp");
  }

  public void deleteAllTosRepartFdpCompRelationships() {
    Enumeration objects = tosRepartFdpComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFdpCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFdpComp)objects.nextElement());
    }
  }


  public static EOFicheDePoste createFicheDePoste(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, NSTimestamp fdpDDebut
, String fdpVisaAgent
, String fdpVisaDirec
, String fdpVisaResp
, org.cocktail.feve.eos.modele.mangue.EOPoste toPoste) {
    EOFicheDePoste eo = (EOFicheDePoste) EOUtilities.createAndInsertInstance(editingContext, _EOFicheDePoste.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setFdpDDebut(fdpDDebut);
		eo.setFdpVisaAgent(fdpVisaAgent);
		eo.setFdpVisaDirec(fdpVisaDirec);
		eo.setFdpVisaResp(fdpVisaResp);
    eo.setToPosteRelationship(toPoste);
    return eo;
  }

  public static NSArray fetchAllFicheDePostes(EOEditingContext editingContext) {
    return _EOFicheDePoste.fetchAllFicheDePostes(editingContext, null);
  }

  public static NSArray fetchAllFicheDePostes(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOFicheDePoste.fetchFicheDePostes(editingContext, null, sortOrderings);
  }

  public static NSArray fetchFicheDePostes(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOFicheDePoste.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOFicheDePoste fetchFicheDePoste(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFicheDePoste.fetchFicheDePoste(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFicheDePoste fetchFicheDePoste(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOFicheDePoste.fetchFicheDePostes(editingContext, qualifier, null);
    EOFicheDePoste eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOFicheDePoste)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FicheDePoste that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFicheDePoste fetchRequiredFicheDePoste(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFicheDePoste.fetchRequiredFicheDePoste(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFicheDePoste fetchRequiredFicheDePoste(EOEditingContext editingContext, EOQualifier qualifier) {
    EOFicheDePoste eoObject = _EOFicheDePoste.fetchFicheDePoste(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FicheDePoste that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFicheDePoste localInstanceIn(EOEditingContext editingContext, EOFicheDePoste eo) {
    EOFicheDePoste localInstance = (eo == null) ? null : (EOFicheDePoste)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...

public static NSArray fetchFetchSuivi(EOEditingContext editingContext, NSDictionary bindings) {
	EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("FetchSuivi", "FicheDePoste");
	fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
	return (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
}

}
