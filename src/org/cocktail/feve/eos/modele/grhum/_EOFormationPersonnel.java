// _EOFormationPersonnel.java
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

// DO NOT EDIT.  Make changes to EOFormationPersonnel.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOFormationPersonnel extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "FormationPersonnel";
	public static final String ENTITY_TABLE_NAME = "GRHUM.FORMATION_PERSONNEL";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String FOR_COMMENTAIRE_KEY = "forCommentaire";
	public static final String FOR_D_FERMETURE_KEY = "forDFermeture";
	public static final String FOR_D_OUVERTURE_KEY = "forDOuverture";
	public static final String FOR_ID_KEY = "forId";
	public static final String FOR_ID_PERE_KEY = "forIdPere";
	public static final String FOR_LIBELLE_KEY = "forLibelle";
	public static final String FOR_LIBELLE_DESCENDANCE_KEY = "forLibelleDescendance";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String FOR_COMMENTAIRE_COLKEY = "FOR_COMMENTAIRE";
	public static final String FOR_D_FERMETURE_COLKEY = "FOR_D_FERMETURE";
	public static final String FOR_D_OUVERTURE_COLKEY = "FOR_D_OUVERTURE";
	public static final String FOR_ID_COLKEY = "FOR_ID";
	public static final String FOR_ID_PERE_COLKEY = "FOR_ID_PERE";
	public static final String FOR_LIBELLE_COLKEY = "FOR_LIBELLE";
	public static final String FOR_LIBELLE_DESCENDANCE_COLKEY = "forLibelleDescendance";

	// Relationships
	public static final String TO_FORMATION_PERE_KEY = "toFormationPere";
	public static final String TOS_FORMATION_FILLE_KEY = "tosFormationFille";
	public static final String TOS_REPART_FORMATION_SOUHAITEE_KEY = "tosRepartFormationSouhaitee";

	// Utilities methods
  public EOFormationPersonnel localInstanceIn(EOEditingContext editingContext) {
    EOFormationPersonnel localInstance = (EOFormationPersonnel)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public String forCommentaire() {
    return (String) storedValueForKey("forCommentaire");
  }

  public void setForCommentaire(String value) {
    takeStoredValueForKey(value, "forCommentaire");
  }

  public NSTimestamp forDFermeture() {
    return (NSTimestamp) storedValueForKey("forDFermeture");
  }

  public void setForDFermeture(NSTimestamp value) {
    takeStoredValueForKey(value, "forDFermeture");
  }

  public NSTimestamp forDOuverture() {
    return (NSTimestamp) storedValueForKey("forDOuverture");
  }

  public void setForDOuverture(NSTimestamp value) {
    takeStoredValueForKey(value, "forDOuverture");
  }

  public Integer forId() {
    return (Integer) storedValueForKey("forId");
  }

  public void setForId(Integer value) {
    takeStoredValueForKey(value, "forId");
  }

  public Integer forIdPere() {
    return (Integer) storedValueForKey("forIdPere");
  }

  public void setForIdPere(Integer value) {
    takeStoredValueForKey(value, "forIdPere");
  }

  public String forLibelle() {
    return (String) storedValueForKey("forLibelle");
  }

  public void setForLibelle(String value) {
    takeStoredValueForKey(value, "forLibelle");
  }

  public String forLibelleDescendance() {
    return (String) storedValueForKey("forLibelleDescendance");
  }

  public void setForLibelleDescendance(String value) {
    takeStoredValueForKey(value, "forLibelleDescendance");
  }

  public org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel toFormationPere() {
    return (org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel)storedValueForKey("toFormationPere");
  }

  public void setToFormationPereRelationship(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel oldValue = toFormationPere();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toFormationPere");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toFormationPere");
    }
  }
  
  public NSArray tosFormationFille() {
    return (NSArray)storedValueForKey("tosFormationFille");
  }

  public NSArray tosFormationFille(EOQualifier qualifier) {
    return tosFormationFille(qualifier, null, false);
  }

  public NSArray tosFormationFille(EOQualifier qualifier, boolean fetch) {
    return tosFormationFille(qualifier, null, fetch);
  }

  public NSArray tosFormationFille(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel.TO_FORMATION_PERE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel.fetchFormationPersonnels(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosFormationFille();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosFormationFilleRelationship(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosFormationFille");
  }

  public void removeFromTosFormationFilleRelationship(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFormationFille");
  }

  public org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel createTosFormationFilleRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("FormationPersonnel");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosFormationFille");
    return (org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel) eo;
  }

  public void deleteTosFormationFilleRelationship(org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosFormationFille");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosFormationFilleRelationships() {
    Enumeration objects = tosFormationFille().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosFormationFilleRelationship((org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel)objects.nextElement());
    }
  }

  public NSArray tosRepartFormationSouhaitee() {
    return (NSArray)storedValueForKey("tosRepartFormationSouhaitee");
  }

  public NSArray tosRepartFormationSouhaitee(EOQualifier qualifier) {
    return tosRepartFormationSouhaitee(qualifier, null, false);
  }

  public NSArray tosRepartFormationSouhaitee(EOQualifier qualifier, boolean fetch) {
    return tosRepartFormationSouhaitee(qualifier, null, fetch);
  }

  public NSArray tosRepartFormationSouhaitee(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee.TO_FORMATION_PERSONNEL_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee.fetchRepartFormationSouhaitees(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartFormationSouhaitee();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartFormationSouhaiteeRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartFormationSouhaitee");
  }

  public void removeFromTosRepartFormationSouhaiteeRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFormationSouhaitee");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee createTosRepartFormationSouhaiteeRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartFormationSouhaitee");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartFormationSouhaitee");
    return (org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee) eo;
  }

  public void deleteTosRepartFormationSouhaiteeRelationship(org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartFormationSouhaitee");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartFormationSouhaiteeRelationships() {
    Enumeration objects = tosRepartFormationSouhaitee().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartFormationSouhaiteeRelationship((org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee)objects.nextElement());
    }
  }


  public static EOFormationPersonnel createFormationPersonnel(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer forId
, Integer forIdPere
, String forLibelle
, org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel toFormationPere) {
    EOFormationPersonnel eo = (EOFormationPersonnel) EOUtilities.createAndInsertInstance(editingContext, _EOFormationPersonnel.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setForId(forId);
		eo.setForIdPere(forIdPere);
		eo.setForLibelle(forLibelle);
    eo.setToFormationPereRelationship(toFormationPere);
    return eo;
  }

  public static NSArray fetchAllFormationPersonnels(EOEditingContext editingContext) {
    return _EOFormationPersonnel.fetchAllFormationPersonnels(editingContext, null);
  }

  public static NSArray fetchAllFormationPersonnels(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOFormationPersonnel.fetchFormationPersonnels(editingContext, null, sortOrderings);
  }

  public static NSArray fetchFormationPersonnels(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOFormationPersonnel.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOFormationPersonnel fetchFormationPersonnel(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFormationPersonnel.fetchFormationPersonnel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFormationPersonnel fetchFormationPersonnel(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOFormationPersonnel.fetchFormationPersonnels(editingContext, qualifier, null);
    EOFormationPersonnel eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOFormationPersonnel)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one FormationPersonnel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFormationPersonnel fetchRequiredFormationPersonnel(EOEditingContext editingContext, String keyName, Object value) {
    return _EOFormationPersonnel.fetchRequiredFormationPersonnel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOFormationPersonnel fetchRequiredFormationPersonnel(EOEditingContext editingContext, EOQualifier qualifier) {
    EOFormationPersonnel eoObject = _EOFormationPersonnel.fetchFormationPersonnel(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no FormationPersonnel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOFormationPersonnel localInstanceIn(EOEditingContext editingContext, EOFormationPersonnel eo) {
    EOFormationPersonnel localInstance = (eo == null) ? null : (EOFormationPersonnel)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
