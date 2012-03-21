// _EOEvaluation.java
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

// DO NOT EDIT.  Make changes to EOEvaluation.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOEvaluation extends A_EOEvaluationKeyValueCoding  {
	public static final String ENTITY_NAME = "Evaluation";
	public static final String ENTITY_TABLE_NAME = "MANGUE.EVALUATION";

	// Attributes
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String D_TENUE_ENTRETIEN_KEY = "dTenueEntretien";
	public static final String D_VISA_RESPONSABLE_RH_KEY = "dVisaResponsableRh";
	public static final String EPE_KEY_VISIBLE_KEY = "epeKeyVisible";
	public static final String EVA_CHAMP_LIBRE_KEY = "evaChampLibre";
	public static final String EVA_EVOLUTION_ENVIS_KEY = "evaEvolutionEnvis";
	public static final String EVA_EVOLUTION_PROPO_KEY = "evaEvolutionPropo";
	public static final String EVA_KEY_VISIBLE_KEY = "evaKeyVisible";
	public static final String NO_INDIVIDU_RESP_KEY = "noIndividuResp";
	public static final String NO_INDIVIDU_VISIBLE_KEY = "noIndividuVisible";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String D_TENUE_ENTRETIEN_COLKEY = "D_TENUE_ENTRETIEN";
	public static final String D_VISA_RESPONSABLE_RH_COLKEY = "D_VISA_RESPONSABLE_RH";
	public static final String EPE_KEY_VISIBLE_COLKEY = "epeKeyVisible";
	public static final String EVA_CHAMP_LIBRE_COLKEY = "EVA_CHAMP_LIBRE";
	public static final String EVA_EVOLUTION_ENVIS_COLKEY = "EVA_EVOLUTION_ENVIS";
	public static final String EVA_EVOLUTION_PROPO_COLKEY = "EVA_EVOLUTION_PROPO";
	public static final String EVA_KEY_VISIBLE_COLKEY = "evaKeyVisible";
	public static final String NO_INDIVIDU_RESP_COLKEY = "NO_INDIVIDU_RESP";
	public static final String NO_INDIVIDU_VISIBLE_COLKEY = "noIndividuVisible";

	// Relationships
	public static final String TO_EVALUATION_PERIODE_KEY = "toEvaluationPeriode";
	public static final String TO_INDIVIDU_KEY = "toIndividu";
	public static final String TO_INDIVIDU_RESP_KEY = "toIndividuResp";
	public static final String TOS_EVALUATION_NOTICE_PROMOTION_KEY = "tosEvaluationNoticePromotion";
	public static final String TOS_OBJECTIF_KEY = "tosObjectif";
	public static final String TOS_REPART_EVA_NOUVELLE_COMP_KEY = "tosRepartEvaNouvelleComp";
	public static final String TOS_REPART_FORMATION_SOUHAITEE_KEY = "tosRepartFormationSouhaitee";
	public static final String TOS_SITU_ACTIVITE_KEY = "tosSituActivite";
	public static final String TO_V_CANDIDAT_EVALUATION_KEY = "toVCandidatEvaluation";

	// Utilities methods
  public EOEvaluation localInstanceIn(EOEditingContext editingContext) {
    EOEvaluation localInstance = (EOEvaluation)EOUtilities.localInstanceOfObject(editingContext, this);
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

  public NSTimestamp dTenueEntretien() {
    return (NSTimestamp) storedValueForKey("dTenueEntretien");
  }

  public void setDTenueEntretien(NSTimestamp value) {
    takeStoredValueForKey(value, "dTenueEntretien");
  }

  public NSTimestamp dVisaResponsableRh() {
    return (NSTimestamp) storedValueForKey("dVisaResponsableRh");
  }

  public void setDVisaResponsableRh(NSTimestamp value) {
    takeStoredValueForKey(value, "dVisaResponsableRh");
  }

  public Integer epeKeyVisible() {
    return (Integer) storedValueForKey("epeKeyVisible");
  }

  public void setEpeKeyVisible(Integer value) {
    takeStoredValueForKey(value, "epeKeyVisible");
  }

  public String evaChampLibre() {
    return (String) storedValueForKey("evaChampLibre");
  }

  public void setEvaChampLibre(String value) {
    takeStoredValueForKey(value, "evaChampLibre");
  }

  public String evaEvolutionEnvis() {
    return (String) storedValueForKey("evaEvolutionEnvis");
  }

  public void setEvaEvolutionEnvis(String value) {
    takeStoredValueForKey(value, "evaEvolutionEnvis");
  }

  public String evaEvolutionPropo() {
    return (String) storedValueForKey("evaEvolutionPropo");
  }

  public void setEvaEvolutionPropo(String value) {
    takeStoredValueForKey(value, "evaEvolutionPropo");
  }

  public Integer evaKeyVisible() {
    return (Integer) storedValueForKey("evaKeyVisible");
  }

  public void setEvaKeyVisible(Integer value) {
    takeStoredValueForKey(value, "evaKeyVisible");
  }

  public Integer noIndividuResp() {
    return (Integer) storedValueForKey("noIndividuResp");
  }

  public void setNoIndividuResp(Integer value) {
    takeStoredValueForKey(value, "noIndividuResp");
  }

  public Integer noIndividuVisible() {
    return (Integer) storedValueForKey("noIndividuVisible");
  }

  public void setNoIndividuVisible(Integer value) {
    takeStoredValueForKey(value, "noIndividuVisible");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode toEvaluationPeriode() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode)storedValueForKey("toEvaluationPeriode");
  }

  public void setToEvaluationPeriodeRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode oldValue = toEvaluationPeriode();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluationPeriode");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluationPeriode");
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
  
  public org.cocktail.feve.eos.modele.grhum.EOIndividu toIndividuResp() {
    return (org.cocktail.feve.eos.modele.grhum.EOIndividu)storedValueForKey("toIndividuResp");
  }

  public void setToIndividuRespRelationship(org.cocktail.feve.eos.modele.grhum.EOIndividu value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.grhum.EOIndividu oldValue = toIndividuResp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toIndividuResp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toIndividuResp");
    }
  }
  
  public org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation toVCandidatEvaluation() {
    return (org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation)storedValueForKey("toVCandidatEvaluation");
  }

  public void setToVCandidatEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation oldValue = toVCandidatEvaluation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toVCandidatEvaluation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toVCandidatEvaluation");
    }
  }
  
  public NSArray tosEvaluationNoticePromotion() {
    return (NSArray)storedValueForKey("tosEvaluationNoticePromotion");
  }

  public NSArray tosEvaluationNoticePromotion(EOQualifier qualifier) {
    return tosEvaluationNoticePromotion(qualifier, null, false);
  }

  public NSArray tosEvaluationNoticePromotion(EOQualifier qualifier, boolean fetch) {
    return tosEvaluationNoticePromotion(qualifier, null, fetch);
  }

  public NSArray tosEvaluationNoticePromotion(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion.TO_EVALUATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion.fetchEvaluationNoticePromotions(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosEvaluationNoticePromotion();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosEvaluationNoticePromotionRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosEvaluationNoticePromotion");
  }

  public void removeFromTosEvaluationNoticePromotionRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluationNoticePromotion");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion createTosEvaluationNoticePromotionRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EvaluationNoticePromotion");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosEvaluationNoticePromotion");
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion) eo;
  }

  public void deleteTosEvaluationNoticePromotionRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosEvaluationNoticePromotion");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosEvaluationNoticePromotionRelationships() {
    Enumeration objects = tosEvaluationNoticePromotion().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosEvaluationNoticePromotionRelationship((org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion)objects.nextElement());
    }
  }

  public NSArray tosObjectif() {
    return (NSArray)storedValueForKey("tosObjectif");
  }

  public NSArray tosObjectif(EOQualifier qualifier) {
    return tosObjectif(qualifier, null, false);
  }

  public NSArray tosObjectif(EOQualifier qualifier, boolean fetch) {
    return tosObjectif(qualifier, null, fetch);
  }

  public NSArray tosObjectif(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOObjectif.TO_EVALUATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOObjectif.fetchObjectifs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosObjectif();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosObjectifRelationship(org.cocktail.feve.eos.modele.mangue.EOObjectif object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosObjectif");
  }

  public void removeFromTosObjectifRelationship(org.cocktail.feve.eos.modele.mangue.EOObjectif object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosObjectif");
  }

  public org.cocktail.feve.eos.modele.mangue.EOObjectif createTosObjectifRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Objectif");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosObjectif");
    return (org.cocktail.feve.eos.modele.mangue.EOObjectif) eo;
  }

  public void deleteTosObjectifRelationship(org.cocktail.feve.eos.modele.mangue.EOObjectif object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosObjectif");
  }

  public void deleteAllTosObjectifRelationships() {
    Enumeration objects = tosObjectif().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosObjectifRelationship((org.cocktail.feve.eos.modele.mangue.EOObjectif)objects.nextElement());
    }
  }

  public NSArray tosRepartEvaNouvelleComp() {
    return (NSArray)storedValueForKey("tosRepartEvaNouvelleComp");
  }

  public NSArray tosRepartEvaNouvelleComp(EOQualifier qualifier) {
    return tosRepartEvaNouvelleComp(qualifier, null, false);
  }

  public NSArray tosRepartEvaNouvelleComp(EOQualifier qualifier, boolean fetch) {
    return tosRepartEvaNouvelleComp(qualifier, null, fetch);
  }

  public NSArray tosRepartEvaNouvelleComp(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp.TO_EVALUATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComps(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosRepartEvaNouvelleComp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosRepartEvaNouvelleCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosRepartEvaNouvelleComp");
  }

  public void removeFromTosRepartEvaNouvelleCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEvaNouvelleComp");
  }

  public org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp createTosRepartEvaNouvelleCompRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("RepartEvaNouvelleComp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosRepartEvaNouvelleComp");
    return (org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp) eo;
  }

  public void deleteTosRepartEvaNouvelleCompRelationship(org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosRepartEvaNouvelleComp");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosRepartEvaNouvelleCompRelationships() {
    Enumeration objects = tosRepartEvaNouvelleComp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosRepartEvaNouvelleCompRelationship((org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp)objects.nextElement());
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
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee.TO_EVALUATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
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

  public NSArray tosSituActivite() {
    return (NSArray)storedValueForKey("tosSituActivite");
  }

  public NSArray tosSituActivite(EOQualifier qualifier) {
    return tosSituActivite(qualifier, null, false);
  }

  public NSArray tosSituActivite(EOQualifier qualifier, boolean fetch) {
    return tosSituActivite(qualifier, null, fetch);
  }

  public NSArray tosSituActivite(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.feve.eos.modele.mangue.EOSituActivite.TO_EVALUATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.feve.eos.modele.mangue.EOSituActivite.fetchSituActivites(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosSituActivite();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosSituActiviteRelationship(org.cocktail.feve.eos.modele.mangue.EOSituActivite object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosSituActivite");
  }

  public void removeFromTosSituActiviteRelationship(org.cocktail.feve.eos.modele.mangue.EOSituActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosSituActivite");
  }

  public org.cocktail.feve.eos.modele.mangue.EOSituActivite createTosSituActiviteRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("SituActivite");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosSituActivite");
    return (org.cocktail.feve.eos.modele.mangue.EOSituActivite) eo;
  }

  public void deleteTosSituActiviteRelationship(org.cocktail.feve.eos.modele.mangue.EOSituActivite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosSituActivite");
  }

  public void deleteAllTosSituActiviteRelationships() {
    Enumeration objects = tosSituActivite().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosSituActiviteRelationship((org.cocktail.feve.eos.modele.mangue.EOSituActivite)objects.nextElement());
    }
  }


  public static EOEvaluation createEvaluation(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer noIndividuVisible
) {
    EOEvaluation eo = (EOEvaluation) EOUtilities.createAndInsertInstance(editingContext, _EOEvaluation.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setNoIndividuVisible(noIndividuVisible);
    return eo;
  }

  public static NSArray fetchAllEvaluations(EOEditingContext editingContext) {
    return _EOEvaluation.fetchAllEvaluations(editingContext, null);
  }

  public static NSArray fetchAllEvaluations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOEvaluation.fetchEvaluations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchEvaluations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEvaluation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEvaluation fetchEvaluation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluation.fetchEvaluation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluation fetchEvaluation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOEvaluation.fetchEvaluations(editingContext, qualifier, null);
    EOEvaluation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEvaluation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Evaluation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluation fetchRequiredEvaluation(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluation.fetchRequiredEvaluation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluation fetchRequiredEvaluation(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEvaluation eoObject = _EOEvaluation.fetchEvaluation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Evaluation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluation localInstanceIn(EOEditingContext editingContext, EOEvaluation eo) {
    EOEvaluation localInstance = (eo == null) ? null : (EOEvaluation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
