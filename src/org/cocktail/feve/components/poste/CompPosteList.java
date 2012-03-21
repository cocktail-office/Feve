package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;


/**
 * Affichage d'une liste de poste sous forme d'un tableau	
 * 
 * @author ctarade
 */
public class CompPosteList 
	extends A_ComponentControled {
	
	/** binding d'entree : liste des postes */
	public WODisplayGroup posteDg;
	
	/** binding d'entree : le message a afficher si pas de poste a afficher*/
	public String messageEmptyList;

  /** binding sortant : poste selectionné */
	public EOPoste posteSelected;
 
	
	public CompPosteList(WOContext context) {
		super(context);
	}
	
	/**
	 * Cast du {@link A_ComponentControler} en {@link CompPosteListCtrl} 
	 * @return
	 */
	public CompPosteListCtrl ctrl() {
		return (CompPosteListCtrl) ctrl;
	}
	
	/**
	 * Bouton de suppression d'un poste			
	 * @return
	 */
	public WOComponent supprimerPoste() {
		// page de confirmation	
		WOComponent backPage = ((FeveWebComponent) this).getTopParent();
		CompPosteListCtrl.SupprimerPosteResponder responder = ctrl().new SupprimerPosteResponder(backPage);
	   return CktlAlertPage.newAlertPageWithResponder(this, "Suppression de poste<br>",
	  		 "<center>Confirmation de l'op&eacute;ration:<br><br>"+
	  		 "Etes vous sur de vouloir supprimer le poste " + 
	       ctrl().getPosteItem().display() + " ?",
	       "Confirmer", "Annuler", null, CktlAlertPage.QUESTION, responder);
	 }


	
	// affichage

	/**
  * On combine les classes CSS pour connaitre l'etat du poste
  * @return
  */
	public String getTrPosteClass() {
		StringBuffer classList = new StringBuffer();
	 	if (ctrl().getPosteItem().isOccupe()) {
	 		classList.append(FeveWebComponent.CLASS_TR_OCCUPE);
	 	} else {
	 		classList.append(FeveWebComponent.CLASS_TR_VACANT);
	 	}
	 	classList.append(" , ");
	 	if (ctrl().isCanAccessPosteItem()) {
	 		classList.append(FeveWebComponent.CLASS_TR_MODIFIABLE);
	 	} else {
	 		classList.append(FeveWebComponent.CLASS_TR_NON_MODIFIABLE);
	 	}
	 	classList.append(" , ");
	 	if (ctrl().getPosteItem().isOuvert()) {
	 		classList.append(FeveWebComponent.CLASS_TR_OUVERT);
	 	} else {
	 		classList.append(FeveWebComponent.CLASS_TR_FERME);
	 	}
	 	
	 	return classList.toString();
	}
	
	/**
	 * La classe au passage de la souris
	 * @return
	 */
	public String getTrPosteClassOnMouseOver() {	
		return "this.className='"+FeveWebComponent.CLASS_TR_ON_MOUSE_OVER+"';";
	}	

	/**
	 * La classe au passage de la souris
	 * @return
	 */
	public String getTrPosteClassOnMouseOut() {
		return "this.className='"+getTrPosteClass()+"';";
	}
 
	
}