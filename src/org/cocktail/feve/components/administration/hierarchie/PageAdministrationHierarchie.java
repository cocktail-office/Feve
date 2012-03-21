package org.cocktail.feve.components.administration.hierarchie;
import org.cocktail.feve.components.administration.droits.PageCallingPageAdministrationDroits;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.components.common.A_FeveSubMenuPage;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


public class PageAdministrationHierarchie 
	extends A_FeveSubMenuPage
		implements PageCallingPageAdministrationDroits {

  public final static String ITEM_MENU_PERIODE			   	= "P&eacute;riodes d'entretien";
  public final static String ITEM_MENU_HIERARCHIE 	  = "Arbre Hi&eacute;rarchique";

  public final static String ITEM_MENU_DROITS      	= "<s>Droits Annexes</s>";

  
  public PageAdministrationHierarchie(WOContext context) {
    super(context);
  }
  
  public boolean isPeriode()	 				              {   return getSelectedItemMenu().equals(ITEM_MENU_PERIODE); }
  public boolean isArbreHierarachie()               {   return getSelectedItemMenu().equals(ITEM_MENU_HIERARCHIE); }
  public boolean isPageDroits()                  		{   return getSelectedItemMenu().equals(ITEM_MENU_DROITS); }

  public NSArray getMenuItems() {
		return new NSArray(new String[]{ITEM_MENU_PERIODE, ITEM_MENU_HIERARCHIE/*, ITEM_MENU_DROITS*/});
	}

	public WOComponent caller() {
		return this.parent();
	}
	
	/**
	 * Pointeur sur cette page pour permettre au 
	 * nodes de remonter a la page de gestion des droits
	 */
	public WOComponent self() {
		return this;
	}
	
	// pas d'heritage multiple ... je recopie les methodes 
	// de la classe abstraite PageControled
	
	/**
	 * Le controleur de cette page
	 */
	public A_ComponentControler ctrl;

	/**
	 * associer ce composant a son controleur des 
	 * que le binding est appele
	 */
	public void setCtrl(A_ComponentControler value) {
		ctrl = value;
		ctrl.setComponent(this);
	}
}