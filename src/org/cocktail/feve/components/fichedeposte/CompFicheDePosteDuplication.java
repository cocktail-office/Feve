package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;

/**
 * Composant permettant de copier une fiche de poste vers un autre poste
 * 
 * @author ctarade
 */
public class CompFicheDePosteDuplication 
	extends A_ComponentControled {
	
	public CompFicheDePosteDuplication(WOContext context) {
		super(context);
	}

	
	/**
	 * cast du {@link A_ComponentControler} en {@link CompPosteDuplicationCtrl}
	 * @return
	 */
	public CompFicheDePosteDuplicationCtrl ctrl() {
		return (CompFicheDePosteDuplicationCtrl) ctrl;
	}
	
}