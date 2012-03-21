package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;


/**
 * Composant de gestion de mise a jour des meta données de la fiche de poste
 * @author ctarade
 *
 */
public class CompFicheDePosteUpdate 
	extends A_ComponentControled {

	public CompFicheDePosteUpdate(WOContext context) {
		super(context);
	}
		
	/**
	 * cast du {@link A_ComponentControler} en {@link CompFicheDePosteUpdateCtrl}
	 * @return
	 */
	public CompFicheDePosteUpdateCtrl ctrl() {
		return (CompFicheDePosteUpdateCtrl) ctrl;
	}

}