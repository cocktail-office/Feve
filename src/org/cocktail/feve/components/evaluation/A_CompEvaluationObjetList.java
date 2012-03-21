package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;

/**
 * Traitement commun pour une liste d'objet en lien avec une évaluation
 * 
 * @author ctarade
 */
public abstract class A_CompEvaluationObjetList
		extends FeveWebComponent {

	/** binding d'entree : liste des evaluation */
	public NSArray<EOVCandidatEvaluation> evaluationList;

	/** binding d'entree : le message a afficher si pas de poste a afficher */
	public String messageEmptyList;

	// liste des evaluateurs
	private NSArray<EOIndividu> evaluateurArray;
	public EOIndividu evaluateurItem;
	private EOIndividu evaluateurSelected;

	public A_CompEvaluationObjetList(WOContext context) {
		super(context);
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOIndividu> getEvaluateurArray() {
		if (evaluateurArray == null) {
			evaluateurArray = NSArrayCtrl.removeDuplicate(
					(NSArray) evaluationList.valueForKey(EOVCandidatEvaluation.TO_EVALUATEUR_KEY));
			// suppression du NSKeyValueCoding.NullValue representant le N+1 du "roi"
			NSMutableArray evaluateurListMutable = new NSMutableArray(evaluateurArray);
			evaluateurListMutable.removeIdenticalObject(NSKeyValueCoding.NullValue);
			evaluateurArray = evaluateurListMutable.immutableClone();
			// classement par le nom
			evaluateurArray = CktlSort.sortedArray(evaluateurArray, EOIndividu.NOM_PRENOM_KEY);
		}
		return evaluateurArray;
	}

	public final NSArray<EOVCandidatEvaluation> getEvaluationList() {
		return evaluationList;
	}

	public final void setEvaluationList(
			NSArray<EOVCandidatEvaluation> evaluationList) {
		this.evaluationList = evaluationList;
		// forcer le rechargement de la liste des responsables
		evaluateurArray = null;
		// forcer le rechargement de la liste des evaluations affichées
		resetListeAffichee();
	}

	public final EOIndividu getEvaluateurSelected() {
		return evaluateurSelected;
	}

	public final void setEvaluateurSelected(EOIndividu evaluateurSelected) {
		this.evaluateurSelected = evaluateurSelected;
		// forcer le rechargement de la liste des evaluations affichées
		resetListeAffichee();
	}

	/**
	 * Les actions à faire pour remettre à 0 la liste affichée
	 */
	public abstract void resetListeAffichee();

	// classement
	private String sortStringSelected;
	private int sortOrder;

	/**
	 * 
	 * @param sort
	 */
	protected void faireClassement(String sort) {
		// si c'est le même classement alors on fait une inversion
		if (sortStringSelected != null && sort.equals(sortStringSelected)) {
			if (sortOrder == CktlSort.Ascending) {
				sortOrder = CktlSort.Descending;
			} else {
				sortOrder = CktlSort.Ascending;
			}
		} else {
			sortOrder = CktlSort.Ascending;
		}
		//
		sortStringSelected = sort;
		// forcer le rafraichissement de la liste
		resetListeAffichee();
	}

	public final int getSortOrder() {
		return sortOrder;
	}

	public final String getSortStringSelected() {
		return sortStringSelected;
	}
}
