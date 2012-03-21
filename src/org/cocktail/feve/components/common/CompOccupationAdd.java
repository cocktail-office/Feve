package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Composant de gestion d'ajout d'occupation a un poste
 * 
 * @author ctarade
 *
 */
public class CompOccupationAdd
	extends A_ComponentControled {

	public CompOccupationAdd(WOContext context) {
		super(context);
	}
	
	/**
	 * cast du {@link A_ComponentControler} en {@link CompOccupationAddCtrl}
	 * @return
	 */
	public CompOccupationAddCtrl ctrl() {
		return (CompOccupationAddCtrl) ctrl;
	}

}