package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;


/**
 * Ecran affichant la liste de tous les droits deduits par agent
 * 
 * @author ctarade
 */
public class PageAdminDroitsDeduits 
	extends A_ComponentControled {
  
	public PageAdminDroitsDeduits(WOContext context) {
		super(context);
	}
	
	/**
	 * Controleur associé
	 * @return
	 */
  public PageAdminDroitsDeduitsCtrl ctrl() {
  	return (PageAdminDroitsDeduitsCtrl) ctrl;
  }
}