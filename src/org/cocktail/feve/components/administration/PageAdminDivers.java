package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.A_FeveSubMenuPage;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


/**
 * Page rassemblant l'ensemble des outils de gestion divers
 * 
 * @author ctarade
 */
public class PageAdminDivers
	extends A_FeveSubMenuPage {
	
  public final static String ITEM_MENU_DIVERS_SYNC_REFERENS   							= "Synchro REFERENS";
  public final static String ITEM_MENU_DIVERS_SQUELLETTE_FICHE		      		= "Squelettes des fiches";

  private final static NSArray<String> SUIVI_MENU_ITEMS = new NSArray<String>(new String[]{
  		ITEM_MENU_DIVERS_SYNC_REFERENS, ITEM_MENU_DIVERS_SQUELLETTE_FICHE});
  
	
	public PageAdminDivers(WOContext context) {
		super(context);
	}

	public NSArray<String> getMenuItems() {
		return SUIVI_MENU_ITEMS;
	}
	
	public boolean isDiversSyncReferens()   			{   return getSelectedItemMenu().equals(ITEM_MENU_DIVERS_SYNC_REFERENS); }
  public boolean isDiversSqueletteFiche()      	{   return getSelectedItemMenu().equals(ITEM_MENU_DIVERS_SQUELLETTE_FICHE); }


	
}