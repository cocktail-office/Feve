package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.I_Droit;

import com.webobjects.appserver.WOComponent;


/**
 * Controleur de la page de gestion de droits deduits
 * 
 * @author ctarade
 */
public class PageAdminDroitsDeduitsCtrl 
	extends A_ComponentControler {

	// l'individu trouv� pour affichage de ses droits deduits
	public EOIndividu individuSelected;
	public FeveUserInfo ui;

	// item d'un droit parmi la liste
	public I_Droit droitItem;
	
	public PageAdminDroitsDeduitsCtrl(Session session) {
		super(session);
	}
	
	/**
	 * Selectionner l'individu selectionn� pour afficher les
	 * droits deduits
	 * @return
	 */
	public WOComponent doSelectIndividu() {
		if (individuSelected != null) {
			ui = new FeveUserInfo(feveApp(), feveSession().dataBus(), ec(), individuSelected.persId());
		}
		return null;
	}
	
	/**
	 * Interception du changement d'individu => RAZ de la liste
	 * @param value
	 */
	public void setIndividuSelected(EOIndividu value) {
		individuSelected = value;
		ui = null;
	}
}
