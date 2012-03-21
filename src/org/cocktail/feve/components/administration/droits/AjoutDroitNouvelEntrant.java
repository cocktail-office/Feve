package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;

/**
 * Ajout d'un enregistrement de droit pour nouvel entrant (objectifs precedents)
 * 
 * @author ctarade
 * 
 */
public class AjoutDroitNouvelEntrant
		extends A_ComponentControled {

	public AjoutDroitNouvelEntrant(WOContext context) {
		super(context);
	}

	/**
	 * Controleur associé
	 * 
	 * @return
	 */
	public AjoutDroitNouvelEntrantCtrl ctrl() {
		return (AjoutDroitNouvelEntrantCtrl) ctrl;
	}
}