package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;

/**
 * Gestion des droits associés aux nouveaux entrants pour qui il faut autoriser
 * la saisie d'objectifs précédents
 * 
 * @author ctarade
 * 
 */
public class GestionDroitsNouveauxEntrants
		extends A_ComponentControled {

	public GestionDroitsNouveauxEntrants(WOContext context) {
		super(context);
	}

	/**
	 * Controleur associé
	 * 
	 * @return
	 */
	public GestionDroitsNouveauxEntrantsCtrl ctrl() {
		return (GestionDroitsNouveauxEntrantsCtrl) ctrl;
	}
}