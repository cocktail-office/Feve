package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXQ;

/**
 * Affichage d'une liste de fiche de poste sous forme d'un tableau
 * 
 * @author ctarade
 */
public class CompEvaluationList
		extends A_CompEvaluationObjetList {

	//
	public boolean isAfficherDateEntretien;
	public boolean isAfficherDateVisaRh;
	public boolean isAfficherEvolutionCarriere;
	public boolean isAfficherEvolutionPoste;

	// evaluations affichées
	private NSArray<EOVCandidatEvaluation> evaluationArrayFiltree;
	public EOVCandidatEvaluation evaluationItem;
	public EOVCandidatEvaluation evaluationSelected;

	public CompEvaluationList(WOContext context) {
		super(context);
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOVCandidatEvaluation> getEvaluationArrayFiltree() {
		if (evaluationArrayFiltree == null) {
			if (getEvaluateurSelected() != null) {
				evaluationArrayFiltree = EOQualifier.filteredArrayWithQualifier(
						getEvaluationList(),
						ERXQ.equals(EOVCandidatEvaluation.TO_EVALUATEUR_KEY, getEvaluateurSelected()));
			} else {
				evaluationArrayFiltree = getEvaluationList();
			}
			// classement
			evaluationArrayFiltree = CktlSort.sortedArray(evaluationArrayFiltree, getSortStringSelected(), getSortOrder());
		}
		return evaluationArrayFiltree;
	}

	@Override
	public void resetListeAffichee() {
		evaluationArrayFiltree = null;
	}

	public void sortEvaluateur() {
		faireClassement(EOVCandidatEvaluation.SORT_EVALUATEUR);
	}

	public void sortAgent() {
		faireClassement(EOVCandidatEvaluation.SORT_AGENT);
	}

	public void sortDateEntretien() {
		faireClassement(EOVCandidatEvaluation.SORT_DATE_ENTRETIEN);
	}

	public void sortDateVisaRh() {
		faireClassement(EOVCandidatEvaluation.SORT_DATE_VISA_RH);
	}
}