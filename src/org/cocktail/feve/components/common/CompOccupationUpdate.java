package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Composant de gestion de mise a jour d'une occupation d'un poste
 * 
 * @author ctarade
 *
 */
public class CompOccupationUpdate
	extends A_ComponentControled {

	public CompOccupationUpdate(WOContext context) {
		super(context);
	}
	
	/**
	 * cast du {@link A_ComponentControler} en {@link CompOccupationUpdateCtrl}
	 * @return
	 */
	public CompOccupationUpdateCtrl ctrl() {
		return (CompOccupationUpdateCtrl) ctrl;
	}

}