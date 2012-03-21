package org.cocktail.feve.components.common;

import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

/**
 * Composant de gestion des occupations d'un poste
 * @author ctarade
 *
 */
public class CompOccupation
	extends A_ComponentControled {
	
	public CompOccupation(WOContext context) {
		super(context);
	}
	
	/**
	 * cast du {@link A_ComponentControler} en {@link CompOccupationCtrl}
	 * @return
	 */
	public CompOccupationCtrl ctrl() {
		return (CompOccupationCtrl) ctrl;
	}

  /**
   * On combine les classes CSS pour connaitre l'etat de l'occupation
   * @return
   */
  public String classTrOccupation() {
  	StringBuffer classList = new StringBuffer();
  	if (ctrl().affectationDetailItem.isActuelle()) {
  		classList.append(FeveWebComponent.CLASS_TR_OCCUPATION_ACTUELLE);
  	} else {
  		classList.append(FeveWebComponent.CLASS_TR_OCCUPATION_NON_ACTUELLE);
  	}
  	return classList.toString();
  }
  
  /**
   * Action de supprimer l'occupation
   * @return
   */
  public WOComponent supprimerOccupation() {
    // page de confirmation
  	CompOccupationCtrl.SupprimerOccupationResponder responder = ctrl().new SupprimerOccupationResponder(getTopParent());
    return CktlAlertPage.newAlertPageWithResponder(this, "Suppression de l'occupation<br>",
        "<center>Confirmation de l'op&eacute;ration:<br><br>"+
        "Etes vous sur de vouloir supprimer l'occupation du poste " + 
        ctrl().affectationDetailItem.fullDisplay(true) + " ?",
        "Confirmer", "Annuler", null, CktlAlertPage.QUESTION, responder);
  

  }

}