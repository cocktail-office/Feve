package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;


/**
 * Page de gestion des droits.
 * 
 * @author ctarade
 */
public class GestionDroits 
	extends A_ComponentControled {
	
	public GestionDroits(WOContext context) {
		super(context);
	}
	public void appendToResponse(WOResponse response, WOContext context) {
		if (ctrl() != null) {
			ctrl().clearCache();
		}
		super.appendToResponse(response, context);
	} 
	
	/**
	 * Controleur associé
	 * @return
	 */
  public GestionDroitsCtrl ctrl() {
  	return (GestionDroitsCtrl) ctrl;
  }
}