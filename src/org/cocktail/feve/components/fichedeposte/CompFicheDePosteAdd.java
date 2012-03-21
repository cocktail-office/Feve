package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;

/**
 * Composant de gestion de la creation de fiche de poste
 * @author ctarade
 *
 */
public class CompFicheDePosteAdd 
	extends A_ComponentControled {

	public CompFicheDePosteAdd(WOContext context) {
		super(context);
	}
		
	/**
	 * cast du {@link A_ComponentControler} en {@link CompFicheDePosteAddCtrl}
	 * @return
	 */
	public CompFicheDePosteAddCtrl ctrl() {
		return (CompFicheDePosteAddCtrl) ctrl;
	}

}