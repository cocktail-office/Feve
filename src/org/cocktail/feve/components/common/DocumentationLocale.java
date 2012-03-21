package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

/**
 * Ecran de gestion de la documentation locale : guides d'entretien et d'utilisation
 * 
 * @author ctarade
 */
public class DocumentationLocale 
	extends FeveWebComponent {

	public boolean isDisabled = true;
	
	public NSMutableArray<EOMangueParametres> guideEntretienArray;
	public NSMutableArray<EOMangueParametres> guideUtilisationArray;
	
	public EOMangueParametres guideItem;
	
	public DocumentationLocale(WOContext context) {
		super(context);
		relireBase();
	}
	
	/**
	 * Relire les informations de la table des paramètres
	 */
	private void relireBase() {
		
		guideEntretienArray = EOMangueParametres.getParametres(
				ec, 
				EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN,
				EOMangueParametres.PARAM_KEY_KEY);
	
		guideUtilisationArray = EOMangueParametres.getParametres(
				ec, 
				EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION,
				EOMangueParametres.PARAM_KEY_KEY);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAfficherAjouterGuideEntretien() {
		boolean isAfficherAjouterGuideEntretien = true;

		isAfficherAjouterGuideEntretien = isAfficherAjouterGuide(guideEntretienArray);
		
		return isAfficherAjouterGuideEntretien;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAfficherAjouterGuideUtilisation() {
		boolean isAfficherAjouterGuideUtilisation = true;

		isAfficherAjouterGuideUtilisation = isAfficherAjouterGuide(guideUtilisationArray);
		
		return isAfficherAjouterGuideUtilisation;
	}
	
	/**
	 * On autorise l'ajout d'un guide uniquement s'il n'y a aucun
	 * d'entre eux en cours de modification
	 * @return
	 */
	private boolean isAfficherAjouterGuide(NSArray<EOMangueParametres> array) {
		
		boolean isAfficherAjouterGuide = true;

		EOMangueParametres item = null;
		int i = 0;

		while (isAfficherAjouterGuide && i < array.count()) {
			item = array.objectAtIndex(i);
			if (item.getObjetInterfaceDocumentation().isEnModification()) {
				isAfficherAjouterGuide = false;
			}
			i++;
		}
		
		return isAfficherAjouterGuide;
	}
		
	/**
	 * 
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent ajouterGuideEntretien() throws Throwable {
		ajouterGuide(EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN);
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent ajouterGuideUtilisation() throws Throwable {
		ajouterGuide(EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION);
		return null;
	}
	
	/**
	 * 
	 * @param paramKeyPrefix
	 * @throws Throwable 
	 */
	private void ajouterGuide(String paramKeyPrefix) throws Throwable {
		EOMangueParametres nouveauParametre = EOMangueParametres.nouveauParametre(ec, paramKeyPrefix);
		if (paramKeyPrefix.equals(EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN)) {
			guideEntretienArray.addObject(nouveauParametre);
		} else if (paramKeyPrefix.equals(EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION)) {
			guideUtilisationArray.addObject(nouveauParametre);
		}
	}

	/**
	 * 
	 * @return
	 * @throws Throwable 
	 */	
	public WOComponent supprimerGuide() throws Throwable {
		EOMangueParametres.supprimerParametre(guideItem);
		sauvegarde();
		relireBase();
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
  public WOActionResults changementOrdreListeGuideEntretien() throws Throwable {
  	EOMangueParametres.majPosition(ec, guideEntretienArray);
  	return null;
  } 
	
	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
  public WOActionResults changementOrdreListeGuideUtilisation() throws Throwable {
  	EOMangueParametres.majPosition(ec, guideUtilisationArray);
  	return null;
  } 

  /**
   * Annuler l'opération en cours ou l'ajout d'une nouvelle doc
   * @return
   */
  public WOActionResults annuler() {
  	// objet déjà en base ? si non, suppression du tableau associé
  	if (ec.insertedObjects().containsObject(guideItem)) {
  		if (guideItem.getObjetInterfaceDocumentation().getBaseParamKey().equals(
  				EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN)) {
  			guideEntretienArray.removeIdenticalObject(guideItem);
  		} else if (guideItem.getObjetInterfaceDocumentation().getBaseParamKey().equals(
  				EOMangueParametres.PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION)) {
  			guideUtilisationArray.removeIdenticalObject(guideItem);
  		}
  	}
  	//
  	guideItem.getObjetInterfaceDocumentation().annuler();
  	return null;
  }
  
}