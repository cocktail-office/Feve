package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.components.common.A_FeveSubMenuPage;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

/**
 * Page rassemblant l'ensemble des outils de suivi des postes, fiches de postes,
 * evaluations et demandes de formation
 * 
 * @author ctarade
 */
public class PageSuivi
		extends A_FeveSubMenuPage {

	public final static String ITEM_MENU_SUIVI_POSTE = "Postes";
	public final static String ITEM_MENU_SUIVI_FICHE_DE_POSTE = "Fiches de poste";
	public final static String ITEM_MENU_SUIVI_FICHE_LOLF = "Fiches LOLF";
	public final static String ITEM_MENU_SUIVI_EVALUATION = "Entretiens professionnels";
	public final static String ITEM_MENU_SUIVI_DEM_FORMATION = "Demandes de formation";
	public final static String ITEM_MENU_SUIVI_FORMATION_SUIVI = "Formations suivies";
	public final static String ITEM_MENU_SUIVI_NOTICE_PROMOTION_SUIVI = "Promotions";
	public final static String ITEM_MENU_SUIVI_EVOLUTION_SUIVI = "Evolutions";
	private final static NSArray<String> SUIVI_MENU_ITEMS = new NSArray<String>(new String[] {
			ITEM_MENU_SUIVI_POSTE, ITEM_MENU_SUIVI_FICHE_DE_POSTE, ITEM_MENU_SUIVI_FICHE_LOLF,
			ITEM_MENU_SUIVI_EVALUATION, ITEM_MENU_SUIVI_EVOLUTION_SUIVI, ITEM_MENU_SUIVI_NOTICE_PROMOTION_SUIVI,
			ITEM_MENU_SUIVI_DEM_FORMATION, ITEM_MENU_SUIVI_FORMATION_SUIVI });

	public PageSuivi(WOContext context) {
		super(context);
	}

	public NSArray<String> getMenuItems() {
		return SUIVI_MENU_ITEMS;
	}

	public boolean isPoste() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_POSTE);
	}

	public boolean isFicheDePoste() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_FICHE_DE_POSTE);
	}

	public boolean isFicheLolf() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_FICHE_LOLF);
	}

	public boolean isEvaluation() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_EVALUATION);
	}

	public boolean isEvolution() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_EVOLUTION_SUIVI);
	}

	public boolean isPromotion() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_NOTICE_PROMOTION_SUIVI);
	}

	public boolean isDemFormation() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_DEM_FORMATION);
	}

	public boolean isFormation() {
		return getSelectedItemMenu().equals(ITEM_MENU_SUIVI_FORMATION_SUIVI);
	}

}