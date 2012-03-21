package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;


/**
 * Affichage d'une liste de fiche de poste sous forme d'un tableau	
 * 
 * @author ctarade
 */
public class CompFicheDePosteList 
	extends A_ComponentControled {
	
	public CompFicheDePosteList(WOContext context) {
		super(context);
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		// TODO Auto-generated method stub
		super.appendToResponse(response, context);
	}
	

	/**
	 * Cast du {@link A_ComponentControler} en {@link CompFicheDePosteListCtrl}
	 * @return
	 */
	public CompFicheDePosteListCtrl ctrl() {
		return (CompFicheDePosteListCtrl) ctrl;
	}
	
  
	// ------------ SUPPRESSION DE FICHE DE POSTE / LOLF ----------------- //
	
	public WOComponent doDeleteFicheDePosteItem() {
		// page de confirmation
		CompFicheDePosteListCtrl.SupprimerFicheResponder responder = ctrl().new SupprimerFicheResponder(getTopParent());
		return CktlAlertPage.newAlertPageWithResponder(getTopParent(), "Suppression de fiche de poste",
				"<center>Confirmation de l'op&eacute;ration:<br><br>"+
				"Etes vous sur de vouloir supprimer la fiche de poste "+ 
				ctrl().ficheDePosteItem.toPoste().posCode() + " nomm&eacute;e " + ctrl().ficheDePosteItem.display() + " ?",
				"Confirmer", "Annuler", null, CktlAlertPage.QUESTION, responder);
	}
 
}