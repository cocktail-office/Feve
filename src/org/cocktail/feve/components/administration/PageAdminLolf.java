package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.A_FeveSubMenuPage;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


/**
 * Page rassemblant l'ensemble des outils de gestion des droits
 * 
 * @author ctarade
 */
public class PageAdminLolf
	extends A_FeveSubMenuPage {
	
  public final static String ITEM_MENU_LOLF_GESTION   							= "Gestion";
  public final static String ITEM_MENU_LOLF_SYNC					      		= "Synchroniser les fiches";

  private final static NSArray<String> SUIVI_MENU_ITEMS = new NSArray<String>(new String[]{
  		ITEM_MENU_LOLF_GESTION, ITEM_MENU_LOLF_SYNC});
  
	
	public PageAdminLolf(WOContext context) {
		super(context);
	}

	public NSArray<String> getMenuItems() {
		return SUIVI_MENU_ITEMS;
	}
	
	public boolean isLolfGestion()   	{   return getSelectedItemMenu().equals(ITEM_MENU_LOLF_GESTION); }
  public boolean isLolfSync()      	{   return getSelectedItemMenu().equals(ITEM_MENU_LOLF_SYNC); }


	
}