package org.cocktail.feve.components.common;


import org.cocktail.feve.app.print.FevePdfBoxCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.components.CktlWebComponent;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

/**
 * Boite contenant le lien pour produire
 * une document PDF via SIX.
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public class FevePdfBoxLink extends CktlWebComponent {
	
	public FevePdfBoxLink(WOContext context) {
		super(context);
	}
	
	/**
	 * Methode reliee au clic sur le lien. Affiche 
	 * la page de chargement, si une impression n'est 
	 * pas deja en cours.
	 */
	public WOComponent doPrint() {
		FevePdfBoxComponent nextPage = (FevePdfBoxComponent) pageWithName(
				FevePdfBoxComponent.class.getName());
		if (!nextPage.isWorking()) {
			nextPage.setCurrentCtrl(ctrl());
			return nextPage; 
		} else {
			return CktlAlertPage.newAlertPageWithMessage(this, 
					"Edition annulee", "Un fichier PDF est deja en cours de creation.\n" +
					"Veuillez attendre la fin de cette tache avant d'en relancer une autre.", CktlAlertPage.NONE);
		}
	}
	
	public boolean synchronizesVariablesWithBindings() {
		return false;
	}
	
	/** Lien a afficher */
	public String printLabel() {
		return (String) valueForBinding("printLabel");
	}
	
	/** Tooltip sur le lien */
	public String printTip() {
		return (String) valueForBinding("printTip");
	}
	
	/** le controleur */
	private FevePdfBoxCtrl ctrl() {
		return (FevePdfBoxCtrl) valueForBinding("ctrl");
	}

	// variable bindons pour java 1.4.2 ....
	public String printLabel;
	public String printTip;
	public FevePdfBoxCtrl ctrl;

}
	