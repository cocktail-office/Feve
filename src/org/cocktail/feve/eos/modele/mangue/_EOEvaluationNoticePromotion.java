// _EOEvaluationNoticePromotion.java
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

// DO NOT EDIT.  Make changes to EOEvaluationNoticePromotion.java instead.
package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOEvaluationNoticePromotion extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "EvaluationNoticePromotion";
	public static final String ENTITY_TABLE_NAME = "MANGUE.EVALUATION_NOTICE_PROMO";

	// Attributes
	public static final String ENP_APPRECIATION_GENERALE_KEY = "enpAppreciationGenerale";
	public static final String ENP_PROMOTION_CORPS_KEY = "enpPromotionCorps";
	public static final String ENP_PROMOTION_CORPS_REFUS_MOTIF_KEY = "enpPromotionCorpsRefusMotif";
	public static final String ENP_PROMOTION_GRADE_KEY = "enpPromotionGrade";
	public static final String ENP_PROMOTION_GRADE_REFUS_MOTIF_KEY = "enpPromotionGradeRefusMotif";
	public static final String ENP_REDUCTION_ECHELON_KEY = "enpReductionEchelon";
	public static final String ENP_REDUCTION_ECHELON_REFUS_MOTIF_KEY = "enpReductionEchelonRefusMotif";

	public static final String ENP_APPRECIATION_GENERALE_COLKEY = "ENP_APPRECIATION_GENERALE";
	public static final String ENP_PROMOTION_CORPS_COLKEY = "ENP_PROMO_CORPS";
	public static final String ENP_PROMOTION_CORPS_REFUS_MOTIF_COLKEY = "ENP_PROMO_CORPS_REFUS_MOTIF";
	public static final String ENP_PROMOTION_GRADE_COLKEY = "ENP_PROMO_GRADE";
	public static final String ENP_PROMOTION_GRADE_REFUS_MOTIF_COLKEY = "ENP_PROMO_GRADE_REFUS_MOTIF";
	public static final String ENP_REDUCTION_ECHELON_COLKEY = "ENP_REDUC_ECHELON";
	public static final String ENP_REDUCTION_ECHELON_REFUS_MOTIF_COLKEY = "ENP_REDUC_REFUS_MOTIF";

	// Relationships
	public static final String TO_EVALUATION_KEY = "toEvaluation";

	// Utilities methods
  public EOEvaluationNoticePromotion localInstanceIn(EOEditingContext editingContext) {
    EOEvaluationNoticePromotion localInstance = (EOEvaluationNoticePromotion)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String enpAppreciationGenerale() {
    return (String) storedValueForKey("enpAppreciationGenerale");
  }

  public void setEnpAppreciationGenerale(String value) {
    takeStoredValueForKey(value, "enpAppreciationGenerale");
  }

  public Integer enpPromotionCorps() {
    return (Integer) storedValueForKey("enpPromotionCorps");
  }

  public void setEnpPromotionCorps(Integer value) {
    takeStoredValueForKey(value, "enpPromotionCorps");
  }

  public String enpPromotionCorpsRefusMotif() {
    return (String) storedValueForKey("enpPromotionCorpsRefusMotif");
  }

  public void setEnpPromotionCorpsRefusMotif(String value) {
    takeStoredValueForKey(value, "enpPromotionCorpsRefusMotif");
  }

  public Integer enpPromotionGrade() {
    return (Integer) storedValueForKey("enpPromotionGrade");
  }

  public void setEnpPromotionGrade(Integer value) {
    takeStoredValueForKey(value, "enpPromotionGrade");
  }

  public String enpPromotionGradeRefusMotif() {
    return (String) storedValueForKey("enpPromotionGradeRefusMotif");
  }

  public void setEnpPromotionGradeRefusMotif(String value) {
    takeStoredValueForKey(value, "enpPromotionGradeRefusMotif");
  }

  public Integer enpReductionEchelon() {
    return (Integer) storedValueForKey("enpReductionEchelon");
  }

  public void setEnpReductionEchelon(Integer value) {
    takeStoredValueForKey(value, "enpReductionEchelon");
  }

  public String enpReductionEchelonRefusMotif() {
    return (String) storedValueForKey("enpReductionEchelonRefusMotif");
  }

  public void setEnpReductionEchelonRefusMotif(String value) {
    takeStoredValueForKey(value, "enpReductionEchelonRefusMotif");
  }

  public org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation() {
    return (org.cocktail.feve.eos.modele.mangue.EOEvaluation)storedValueForKey("toEvaluation");
  }

  public void setToEvaluationRelationship(org.cocktail.feve.eos.modele.mangue.EOEvaluation value) {
    if (value == null) {
    	org.cocktail.feve.eos.modele.mangue.EOEvaluation oldValue = toEvaluation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toEvaluation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toEvaluation");
    }
  }
  

  public static EOEvaluationNoticePromotion createEvaluationNoticePromotion(EOEditingContext editingContext, org.cocktail.feve.eos.modele.mangue.EOEvaluation toEvaluation) {
    EOEvaluationNoticePromotion eo = (EOEvaluationNoticePromotion) EOUtilities.createAndInsertInstance(editingContext, _EOEvaluationNoticePromotion.ENTITY_NAME);    
    eo.setToEvaluationRelationship(toEvaluation);
    return eo;
  }

  public static NSArray fetchAllEvaluationNoticePromotions(EOEditingContext editingContext) {
    return _EOEvaluationNoticePromotion.fetchAllEvaluationNoticePromotions(editingContext, null);
  }

  public static NSArray fetchAllEvaluationNoticePromotions(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOEvaluationNoticePromotion.fetchEvaluationNoticePromotions(editingContext, null, sortOrderings);
  }

  public static NSArray fetchEvaluationNoticePromotions(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEvaluationNoticePromotion.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEvaluationNoticePromotion fetchEvaluationNoticePromotion(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluationNoticePromotion.fetchEvaluationNoticePromotion(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluationNoticePromotion fetchEvaluationNoticePromotion(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOEvaluationNoticePromotion.fetchEvaluationNoticePromotions(editingContext, qualifier, null);
    EOEvaluationNoticePromotion eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEvaluationNoticePromotion)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EvaluationNoticePromotion that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluationNoticePromotion fetchRequiredEvaluationNoticePromotion(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEvaluationNoticePromotion.fetchRequiredEvaluationNoticePromotion(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEvaluationNoticePromotion fetchRequiredEvaluationNoticePromotion(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEvaluationNoticePromotion eoObject = _EOEvaluationNoticePromotion.fetchEvaluationNoticePromotion(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EvaluationNoticePromotion that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEvaluationNoticePromotion localInstanceIn(EOEditingContext editingContext, EOEvaluationNoticePromotion eo) {
    EOEvaluationNoticePromotion localInstance = (eo == null) ? null : (EOEvaluationNoticePromotion)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
