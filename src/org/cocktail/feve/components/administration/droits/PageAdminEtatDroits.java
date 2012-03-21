package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;


/**
 * Ecran affichant la liste de tous les enregistrements de l'entite <code>Droit</code>
 * 
 * @author ctarade
 */
public class PageAdminEtatDroits 
	extends A_ComponentControled {
	
	public WODisplayGroup droitDg;

	public PageAdminEtatDroits(WOContext context) {
		super(context);
	}

	public void appendToResponse(WOResponse arg0, WOContext arg1) {
		// appliquer filtres et classements
		droitDg.setQualifier(ctrl().getDroitDgQualifier());
		droitDg.setSortOrderings(ctrl().getDroitDgArraySort());
		droitDg.updateDisplayedObjects();  // qual & sort
		super.appendToResponse(arg0, arg1);
		// masquer le message de suppression au prochain appel
		// s'il etait affiche
		if (ctrl().getIsDroitItemJustDeleted()) {
			ctrl().setIsDroitItemJustDeleted(false);
		}
	}
	
	/**
	 * Controleur associé
	 * @return
	 */
  public PageAdminEtatDroitsCtrl ctrl() {
  	return (PageAdminEtatDroitsCtrl) ctrl;
  }
}