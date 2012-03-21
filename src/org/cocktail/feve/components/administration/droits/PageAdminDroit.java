package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.administration.PageAdminAdministrateurCtrl;
import org.cocktail.feve.components.common.A_FeveSubMenuPage;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

/**
 * Page rassemblant l'ensemble des outils de gestion des droits
 * 
 * @author ctarade
 */
public class PageAdminDroit
		extends A_FeveSubMenuPage {

	public final static String ITEM_MENU_DROIT_GESTION = "Gestion";
	public final static String ITEM_MENU_DROIT_GESTION_NOUVEAUX_ENTRANTS = "Nouveaux entrants";
	public final static String ITEM_MENU_DROIT_ETAT = "Etat";
	public final static String ITEM_MENU_DROIT_DEDUIT = "D&eacute;duits";
	public final static String ITEM_MENU_DROIT_ADMIN = "Administrateurs";
	private final static NSArray<String> SUIVI_MENU_ITEMS = new NSArray<String>(new String[] {
			ITEM_MENU_DROIT_GESTION, ITEM_MENU_DROIT_GESTION_NOUVEAUX_ENTRANTS, ITEM_MENU_DROIT_ETAT, ITEM_MENU_DROIT_DEDUIT, ITEM_MENU_DROIT_ADMIN });

	/**
	 * Binding obligatoire pour le composant GestionDroits
	 */
	public GestionDroitsCtrl gestionDroitsCtrl = new GestionDroitsCtrl(session);

	/**
	 * Binding obligatoire pour le composant GestionDroitsNouveauxEntrants
	 */
	public GestionDroitsNouveauxEntrantsCtrl gestionDroitsNouveauxEntrantsCtrl = new GestionDroitsNouveauxEntrantsCtrl(session);

	/**
	 * Binding obligatoire pour le composant PageAdminEtatDroits
	 */
	public PageAdminEtatDroitsCtrl pageAdminEtatDroitsCtrl = new PageAdminEtatDroitsCtrl(session);

	/**
	 * Binding obligatoire pour le composant PageAdminEtatDroitsCtrl
	 */
	public PageAdminDroitsDeduitsCtrl pageAdminDroitsDeduitsCtrl = new PageAdminDroitsDeduitsCtrl(session);

	/**
	 * Binding obligatoire pour le composant PageAdminEtatDroitsCtrl
	 */
	public PageAdminAdministrateurCtrl pageAdminAdministrateurCtrl = new PageAdminAdministrateurCtrl(session);

	public PageAdminDroit(WOContext context) {
		super(context);
	}

	public NSArray<String> getMenuItems() {
		return SUIVI_MENU_ITEMS;
	}

	public boolean isDroitGestion() {
		return getSelectedItemMenu().equals(ITEM_MENU_DROIT_GESTION);
	}

	public boolean isDroitGestionNouveauxEntrants() {
		return getSelectedItemMenu().equals(ITEM_MENU_DROIT_GESTION_NOUVEAUX_ENTRANTS);
	}

	public boolean isDroitEtat() {
		return getSelectedItemMenu().equals(ITEM_MENU_DROIT_ETAT);
	}

	public boolean isDroitDeduit() {
		return getSelectedItemMenu().equals(ITEM_MENU_DROIT_DEDUIT);
	}

	public boolean isDroitAdmin() {
		return getSelectedItemMenu().equals(ITEM_MENU_DROIT_ADMIN);
	}

}