// _EOIndividu.java
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

// DO NOT EDIT.  Make changes to EOIndividu.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOIndividu extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "Individu";
	public static final String ENTITY_TABLE_NAME = "GRHUM.INDIVIDU_ULR";

	// Attributes
	public static final String C_CIVILITE_KEY = "cCivilite";
	public static final String D_NAISSANCE_KEY = "dNaissance";
	public static final String IND_PHOTO_KEY = "indPhoto";
	public static final String IND_QUALITE_KEY = "indQualite";
	public static final String IS_ENSEIGNANT_KEY = "isEnseignant";
	public static final String IS_NON_ENSEIGNANT_KEY = "isNonEnseignant";
	public static final String NOM_PATRONYMIQUE_KEY = "nomPatronymique";
	public static final String NOM_USUEL_KEY = "nomUsuel";
	public static final String PERS_ID_KEY = "persId";
	public static final String PRENOM_KEY = "prenom";
	public static final String PRENOM2_KEY = "prenom2";
	public static final String TEM_VALIDE_KEY = "temValide";

	public static final String C_CIVILITE_COLKEY = "C_CIVILITE";
	public static final String D_NAISSANCE_COLKEY = "D_NAISSANCE";
	public static final String IND_PHOTO_COLKEY = "IND_PHOTO";
	public static final String IND_QUALITE_COLKEY = "IND_QUALITE";
	public static final String IS_ENSEIGNANT_COLKEY = "isEnseignant";
	public static final String IS_NON_ENSEIGNANT_COLKEY = "isNonEnseignant";
	public static final String NOM_PATRONYMIQUE_COLKEY = "NOM_PATRONYMIQUE";
	public static final String NOM_USUEL_COLKEY = "NOM_USUEL";
	public static final String PERS_ID_COLKEY = "PERS_ID";
	public static final String PRENOM_COLKEY = "PRENOM";
	public static final String PRENOM2_COLKEY = "PRENOM2";
	public static final String TEM_VALIDE_COLKEY = "TEM_VALIDE";

	// Relationships
	public static final String TO_PHOTOS_EMPLOYES_GRHUM_KEY = "toPhotosEmployesGrhum";
	public static final String TOS_AFFECTATION_KEY = "tosAffectation";
	public static final String TOS_AFFECTATION_DETAIL_KEY = "tosAffectationDetail";
	public static final String TOS_ENQUETE_FORMATION_KEY = "tosEnqueteFormation";
	public static final String TOS_EVALUATION_KEY = "tosEvaluation";
	public static final String TOS_HIERARCHIE_NM1_KEY = "tosHierarchieNm1";
	public static final String TOS_HIERARCHIE_NP1_KEY = "tosHierarchieNp1";
	public static final String TOS_INDIVIDU_FORMATIONS_KEY = "tosIndividuFormations";
	public static final String TOS_POSTE_KEY = "tosPoste";
	public static final String TOS_V_CANDIDAT_EVALUATION_KEY = "tosVCandidatEvaluation";
	public static final String TOS_V_PERSONNEL_NON_ENS_KEY = "tosVPersonnelNonEns";
	public static final String TO_V_PERSONNEL_ACTUEL_KEY = "toVPersonnelActuel";
	public static final String TO_V_PERSONNEL_ACTUEL_ENS_KEY = "toVPersonnelActuelEns";
	public static final String TO_V_PERSONNEL_ACTUEL_NON_ENS_KEY = "toVPersonnelActuelNonEns";

	// Utilities methods
  public EOIndividu localInstanceIn(EOEditingContext editingContext) {
    EOIndividu localInstance = (EOIndividu)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cCivilite() {
    return (String) storedValueForKey("cCivilite");
  }

  public void setCCivilite(String value) {
    takeStoredValueForKey(value, "cCivilite");
  }

  public NSTimestamp dNaissance() {
    return (NSTimestamp) storedValueForKey("dNaissance");
  }

  public void setDNaissance(NSTimestamp value) {
    takeStoredValueForKey(value, "dNaissance");
  }

  public String indPhoto() {
    return (String) storedValueForKey("indPhoto");
  }

  public void setIndPhoto(String value) {
    takeStoredValueForKey(value, "indPhoto");
  }

  public String indQualite() {
    return (String) storedValueForKey("indQualite");
  }

  public void setIndQualite(String value) {
    takeStoredValueForKey(value, "indQualite");
  }

  public String isEnseignant() {
    return (String) storedValueForKey("isEnseignant");
  }

  public void setIsEnseignant(String value) {
    takeStoredValueForKey(value, "isEnseignant");
  }

  public String isNonEnseignant() {
    return (String) storedValueForKey("isNonEnseignant");
  }

  public void setIsNonEnseignant(String value) {
    takeStoredValueForKey(value, "isNonEnseignant");
  }

  public String nomPatronymique() {
    return (String) storedValueForKey("nomPatronymique");
  }

  public void setNomPatronymique(String value) {
    takeStoredValueForKey(value, "nomPatronymique");
  }

  public String nomUsuel() {
    return (String) storedValueForKey("nomUsuel");
  }

  public void setNomUsuel(String value) {
    takeStoredValueForKey(value, "nomUsuel");
  }

  public Integer persId() {
    return (Integer) storedValueForKey("persId");
  }

  public void setPersId(Integer value) {
    takeStoredValueForKey(value, "persId");
  }

  public String prenom() {
    return (String) storedValueForKey("prenom");
  }

  public void setPrenom(String value) {
    takeStoredValueForKey(value, "prenom");
  }

  public String prenom2() {
    return (String) storedValueForKey("prenom2");
  }

  public void setPrenom2(String value) {
    takeStoredValueForKey(value, "prenom2");
  }

  public String temValide() {
    return (String) storedValueForKey("temValide");
  }

  public void setTemValide(String value) {
    takeStoredValueForKey(value, "temValide");
  }

  public org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum toPhotosEmployesGrhum() {
    return (org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum)storedValueForKey("toPhotosEmployesGrhum");
  }

  public void setToPhotosEmployesGrhumRelationship(org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum oldValue = toPhotosEmployesGrhum();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toPhotosEmployesGrhum");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toPhotosEmployesGrhum");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuel toVPersonnelActuel() {
    return (org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuel)storedValueForKey("toVPersonnelActuel");
  }

  public void setToVPersonnelActuelRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuel value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuel oldValue = toVPersonnelActuel();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVPersonnelActuel");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVPersonnelActuel");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelEns toVPersonnelActuelEns() {
    return (org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelEns)storedValueForKey("toVPersonnelActuelEns");
  }

  public void setToVPersonnelActuelEnsRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelEns value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelEns oldValue = toVPersonnelActuelEns();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVPersonnelActuelEns");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVPersonnelActuelEns");
    }
  }
  
  public org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelNonEns toVPersonnelActuelNonEns() {
    return (org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelNonEns)storedValueForKey("toVPersonnelActuelNonEns");
  }

  public void setToVPersonnelActuelNonEnsRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelNonEns value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelNonEns oldValue = toVPersonnelActuelNonEns();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVPersonnelActuelNonEns");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVPersonnelActuelNonEns");
    }
  }
  
  public NSArray tosAffectation() {
    return (NSArray)storedValueForKey("tosAffectation");
  }

  public NSArray tosAffectation(EOQualifier qualifier) {
    return tosAffectation(qualifier, null, false);
  }

  public NSArray tosAffectation(EOQualifier qualifier, boolean fetch) {
    return tosAffectation(qualifier, null, fetch);
  }

  public NSArray tosAffectation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOAffectation.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOAffectation.fetchAffectations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosAffectation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosAffectation");
  }

  public void removeFromTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOAffectation createTosAffectationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Affectation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosAffectation");
    return (org.cocktail.feve.eos.modele.mangue.EOAffectation) eo;
  }

  public void deleteTosAffectationRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectation");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosAffectationRelationships() {
    Enumeration objects = tosAffectation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosAffectationRelationship((org.cocktail.feve.eos.modele.mangue.EOAffectation)objects.nextElement());
    }
  }

  public NSArray tosAffectationDetail() {
    return (NSArray)storedValueForKey("tosAffectationDetail");
  }

  public NSArray tosAffectationDetail(EOQualifier qualifier) {
    return tosAffectationDetail(qualifier, null);
  }

  public NSArray tosAffectationDetail(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosAffectationDetail();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
  }

  public void removeFromTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
  }

  public org.cocktail.feve.eos.modele.mangue.EOAffectationDetail createTosAffectationDetailRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("AffectationDetail");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosAffectationDetail");
    return (org.cocktail.feve.eos.modele.mangue.EOAffectationDetail) eo;
  }

  public void deleteTosAffectationDetailRelationship(org.cocktail.feve.eos.modele.mangue.EOAffectationDetail object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosAffectationDetail");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosAffectationDetailRelationships() {
    Enumeration objects = tosAffectationDetail().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosAffectationDetailRelationship((org.cocktail.feve.eos.modele.mangue.EOAffectationDetail)objects.nextElement());
    }
  }

  public NSArray tosEnqueteFormation() {
    return (NSArray)storedValueForKey("tosEnqueteFormation");
  }

  public NSArray tosEnqueteFormation(EOQualifier qualifier) {
    return tosEnqueteFormation(qualifier, null, false);
  }

  public NSArray tosEnqueteFormation(EOQualifier qualifier, boolean fetch) {
    return tosEnqueteFormation(qualifier, null, fetch);
  }

  public NSArray tosEnqueteFormation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation.fetchEnqueteFormations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosEnqueteFormation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosEnqueteFormationRelationship(org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosEnqueteFormation");
  }

  public void removeFromTosEnqueteFormationRelationship(org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEnqueteFormation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation createTosEnqueteFormationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EnqueteFormation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosEnqueteFormation");
    return (org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation) eo;
  }

  public void deleteTosEnqueteFormationRelationship(org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEnqueteFormation");
  }

  public void deleteAllTosEnqueteFormationRelationships() {
    Enumeration objects = tosEnqueteFormation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosEnqueteFormationRelationship((org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation)objects.nextElement());
    }
  }

  public NSArray tosEvaluation() {
    return (NSArray)storedValueForKey("tosEvaluation");
  }

  public NSArray tosEvaluation(EOQualifier qualifier) {
    return tosEvaluation(qualifier, null, false);
  }

  public NSArray tosEvaluation(EOQualifier qualifier, boolean fetch) {
    return tosEvaluation(qualifier, null, fetch);
  }

  public NSArray tosEvaluation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOEvaluation.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOEvaluation.fetchEvaluations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosEvaluation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosEvaluation");
  }

  public void removeFromTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluation createTosEvaluationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Evaluation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosEvaluation");
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluation) eo;
  }

  public void deleteTosEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluation");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosEvaluationRelationships() {
    Enumeration objects = tosEvaluation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosEvaluationRelationship((org.cocktail.feve.eos.modele.mangue.EOEvaluation)objects.nextElement());
    }
  }

  public NSArray tosHierarchieNm1() {
    return (NSArray)storedValueForKey("tosHierarchieNm1");
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier) {
    return tosHierarchieNm1(qualifier, null, false);
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier, boolean fetch) {
    return tosHierarchieNm1(qualifier, null, fetch);
  }

  public NSArray tosHierarchieNm1(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOHierarchie.TO_INDIVIDU_RESP_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOHierarchie.fetchHierarchies(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosHierarchieNm1();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
  }

  public void removeFromTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
  }

  public org.cocktail.feve.eos.modele.mangue.EOHierarchie createTosHierarchieNm1Relationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Hierarchie");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosHierarchieNm1");
    return (org.cocktail.feve.eos.modele.mangue.EOHierarchie) eo;
  }

  public void deleteTosHierarchieNm1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNm1");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosHierarchieNm1Relationships() {
    Enumeration objects = tosHierarchieNm1().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosHierarchieNm1Relationship((org.cocktail.feve.eos.modele.mangue.EOHierarchie)objects.nextElement());
    }
  }

  public NSArray tosHierarchieNp1() {
    return (NSArray)storedValueForKey("tosHierarchieNp1");
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier) {
    return tosHierarchieNp1(qualifier, null, false);
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier, boolean fetch) {
    return tosHierarchieNp1(qualifier, null, fetch);
  }

  public NSArray tosHierarchieNp1(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOHierarchie.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOHierarchie.fetchHierarchies(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosHierarchieNp1();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
  }

  public void removeFromTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
  }

  public org.cocktail.feve.eos.modele.mangue.EOHierarchie createTosHierarchieNp1Relationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Hierarchie");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosHierarchieNp1");
    return (org.cocktail.feve.eos.modele.mangue.EOHierarchie) eo;
  }

  public void deleteTosHierarchieNp1Relationship(org.cocktail.feve.eos.modele.mangue.EOHierarchie object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosHierarchieNp1");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosHierarchieNp1Relationships() {
    Enumeration objects = tosHierarchieNp1().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosHierarchieNp1Relationship((org.cocktail.feve.eos.modele.mangue.EOHierarchie)objects.nextElement());
    }
  }

  public NSArray tosIndividuFormations() {
    return (NSArray)storedValueForKey("tosIndividuFormations");
  }

  public NSArray tosIndividuFormations(EOQualifier qualifier) {
    return tosIndividuFormations(qualifier, null, false);
  }

  public NSArray tosIndividuFormations(EOQualifier qualifier, boolean fetch) {
    return tosIndividuFormations(qualifier, null, fetch);
  }

  public NSArray tosIndividuFormations(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOIndividuFormations.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOIndividuFormations.fetchIndividuFormationses(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosIndividuFormations();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosIndividuFormationsRelationship(org.cocktail.feve.eos.modele.mangue.EOIndividuFormations object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosIndividuFormations");
  }

  public void removeFromTosIndividuFormationsRelationship(org.cocktail.feve.eos.modele.mangue.EOIndividuFormations object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosIndividuFormations");
  }

  public org.cocktail.feve.eos.modele.mangue.EOIndividuFormations createTosIndividuFormationsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("IndividuFormations");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosIndividuFormations");
    return (org.cocktail.feve.eos.modele.mangue.EOIndividuFormations) eo;
  }

  public void deleteTosIndividuFormationsRelationship(org.cocktail.feve.eos.modele.mangue.EOIndividuFormations object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosIndividuFormations");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosIndividuFormationsRelationships() {
    Enumeration objects = tosIndividuFormations().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosIndividuFormationsRelationship((org.cocktail.feve.eos.modele.mangue.EOIndividuFormations)objects.nextElement());
    }
  }

  public NSArray tosPoste() {
    return (NSArray)storedValueForKey("tosPoste");
  }

  public NSArray tosPoste(EOQualifier qualifier) {
    return tosPoste(qualifier, null);
  }

  public NSArray tosPoste(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosPoste();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosPoste");
  }

  public void removeFromTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosPoste");
  }

  public org.cocktail.feve.eos.modele.mangue.EOPoste createTosPosteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Poste");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosPoste");
    return (org.cocktail.feve.eos.modele.mangue.EOPoste) eo;
  }

  public void deleteTosPosteRelationship(org.cocktail.feve.eos.modele.mangue.EOPoste object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosPoste");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosPosteRelationships() {
    Enumeration objects = tosPoste().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosPosteRelationship((org.cocktail.feve.eos.modele.mangue.EOPoste)objects.nextElement());
    }
  }

  public NSArray tosVCandidatEvaluation() {
    return (NSArray)storedValueForKey("tosVCandidatEvaluation");
  }

  public NSArray tosVCandidatEvaluation(EOQualifier qualifier) {
    return tosVCandidatEvaluation(qualifier, null, false);
  }

  public NSArray tosVCandidatEvaluation(EOQualifier qualifier, boolean fetch) {
    return tosVCandidatEvaluation(qualifier, null, fetch);
  }

  public NSArray tosVCandidatEvaluation(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation.fetchVCandidatEvaluations(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosVCandidatEvaluation();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosVCandidatEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosVCandidatEvaluation");
  }

  public void removeFromTosVCandidatEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVCandidatEvaluation");
  }

  public org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation createTosVCandidatEvaluationRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("VCandidatEvaluation");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosVCandidatEvaluation");
    return (org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation) eo;
  }

  public void deleteTosVCandidatEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVCandidatEvaluation");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosVCandidatEvaluationRelationships() {
    Enumeration objects = tosVCandidatEvaluation().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosVCandidatEvaluationRelationship((org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation)objects.nextElement());
    }
  }

  public NSArray tosVPersonnelNonEns() {
    return (NSArray)storedValueForKey("tosVPersonnelNonEns");
  }

  public NSArray tosVPersonnelNonEns(EOQualifier qualifier) {
    return tosVPersonnelNonEns(qualifier, null, false);
  }

  public NSArray tosVPersonnelNonEns(EOQualifier qualifier, boolean fetch) {
    return tosVPersonnelNonEns(qualifier, null, fetch);
  }

  public NSArray tosVPersonnelNonEns(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns.TO_INDIVIDU_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns.fetchVPersonnelNonEnses(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosVPersonnelNonEns();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosVPersonnelNonEnsRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosVPersonnelNonEns");
  }

  public void removeFromTosVPersonnelNonEnsRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVPersonnelNonEns");
  }

  public org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns createTosVPersonnelNonEnsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("VPersonnelNonEns");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosVPersonnelNonEns");
    return (org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns) eo;
  }

  public void deleteTosVPersonnelNonEnsRelationship(org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosVPersonnelNonEns");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosVPersonnelNonEnsRelationships() {
    Enumeration objects = tosVPersonnelNonEns().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosVPersonnelNonEnsRelationship((org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns)objects.nextElement());
    }
  }


  public static EOIndividu createIndividu(EOEditingContext editingContext, String cCivilite
, String isEnseignant
, String isNonEnseignant
, Integer persId
, org.cocktail.feve.eos.modele.grhum.EOPhotosEmployesGrhum toPhotosEmployesGrhum, org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuel toVPersonnelActuel, org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelEns toVPersonnelActuelEns, org.cocktail.feve.eos.modele.grhum.EOVPersonnelActuelNonEns toVPersonnelActuelNonEns) {
    EOIndividu eo = (EOIndividu) EOUtilities.createAndInsertInstance(editingContext, _EOIndividu.ENTITY_NAME);    
		eo.setCCivilite(cCivilite);
		eo.setIsEnseignant(isEnseignant);
		eo.setIsNonEnseignant(isNonEnseignant);
		eo.setPersId(persId);
    eo.setToPhotosEmployesGrhumRelationship(toPhotosEmployesGrhum);
    eo.setToVPersonnelActuelRelationship(toVPersonnelActuel);
    eo.setToVPersonnelActuelEnsRelationship(toVPersonnelActuelEns);
    eo.setToVPersonnelActuelNonEnsRelationship(toVPersonnelActuelNonEns);
    return eo;
  }

  public static NSArray fetchAllIndividus(EOEditingContext editingContext) {
    return _EOIndividu.fetchAllIndividus(editingContext, null);
  }

  public static NSArray fetchAllIndividus(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOIndividu.fetchIndividus(editingContext, null, sortOrderings);
  }

  public static NSArray fetchIndividus(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOIndividu.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOIndividu fetchIndividu(EOEditingContext editingContext, String keyName, Object value) {
    return _EOIndividu.fetchIndividu(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOIndividu fetchIndividu(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOIndividu.fetchIndividus(editingContext, qualifier, null);
    EOIndividu eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOIndividu)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Individu that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOIndividu fetchRequiredIndividu(EOEditingContext editingContext, String keyName, Object value) {
    return _EOIndividu.fetchRequiredIndividu(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOIndividu fetchRequiredIndividu(EOEditingContext editingContext, EOQualifier qualifier) {
    EOIndividu eoObject = _EOIndividu.fetchIndividu(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Individu that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOIndividu localInstanceIn(EOEditingContext editingContext, EOIndividu eo) {
    EOIndividu localInstance = (eo == null) ? null : (EOIndividu)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
