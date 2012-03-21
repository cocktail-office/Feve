package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXQ;

public class CompPromotionList
		extends A_CompEvaluationObjetList {

	// promotions affichées
	private NSArray<EOEvaluationNoticePromotion> promotionArrayFiltree;
	public EOEvaluationNoticePromotion promotionItem;
	public EOEvaluationNoticePromotion promotionSelected;

	public CompPromotionList(WOContext context) {
		super(context);
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOEvaluationNoticePromotion> getPromotionArrayFiltree() {
		if (promotionArrayFiltree == null) {
			NSArray<EOVCandidatEvaluation> array = getEvaluationList();
			if (getEvaluateurSelected() != null) {
				array = EOQualifier.filteredArrayWithQualifier(
						getEvaluationList(),
						ERXQ.equals(EOVCandidatEvaluation.TO_EVALUATEUR_KEY, getEvaluateurSelected()));
			} else {
				array = getEvaluationList();
			}
			promotionArrayFiltree = (NSArray<EOEvaluationNoticePromotion>) array.valueForKey(EOEvaluation.TOS_EVALUATION_NOTICE_PROMOTION_KEY);
			// classement
			promotionArrayFiltree = CktlSort.sortedArray(promotionArrayFiltree, getSortStringSelected(), getSortOrder());
		}
		return promotionArrayFiltree;
	}

	@Override
	public void resetListeAffichee() {
		promotionArrayFiltree = null;
	}

	public void sortEvaluateur() {
		faireClassement(EOEvaluationNoticePromotion.TO_EVALUATION_KEY + "." + EOVCandidatEvaluation.SORT_EVALUATEUR);
	}

	public void sortAgent() {
		faireClassement(EOEvaluationNoticePromotion.TO_EVALUATION_KEY + "." + EOVCandidatEvaluation.SORT_AGENT);
	}
}