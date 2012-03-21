package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;

/**
 * Composant permettant de copier un poste vers un autre service
 * 
 * @author ctarade
 */
public class CompPosteDuplication 
	extends A_ComponentControled {
	
	public CompPosteDuplication(WOContext context) {
		super(context);
	}

	/**
	 * cast du {@link A_ComponentControler} en {@link CompPosteDuplicationCtrl}
	 * @return
	 */
	public CompPosteDuplicationCtrl ctrl() {
		return (CompPosteDuplicationCtrl) ctrl;
	}
}