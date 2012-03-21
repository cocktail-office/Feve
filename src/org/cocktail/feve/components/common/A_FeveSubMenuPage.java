package org.cocktail.feve.components.common;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;


/**
 * Classe qui doit etre implementee par tous les composants 
 * qui doivent afficher un sous menu.
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public abstract class A_FeveSubMenuPage 
	extends FeveWebComponent {

	// gestion du menu
  public int indexItemMenu = 0;

  public String unItemMenu = null;
  private String selectedItemMenu = null;

  public A_FeveSubMenuPage(WOContext context) {
		super(context);
	}

  public void appendToResponse(WOResponse aResponse, WOContext aContext) {
  	super.appendToResponse(aResponse, aContext);
		// la css du menu
		addLocalCss(aResponse, "css/SubMenu.css");
  }
  
  public String getSelectedItemMenu() {
  	
  	// selection du premier item par defaut
  	if (selectedItemMenu == null) {
  		if (getMenuItems().count() > 0) {
  			setSelectedItemMenu(getMenuItems().objectAtIndex(0));
  		}
  	}

  	return selectedItemMenu;
  }
   
  public final void setSelectedItemMenu(String value) {
  	selectedItemMenu = value;
  }
   
  public String classTabMenu() {
  	String classTabMenu = "tab";
		if (unItemMenu.equals(getSelectedItemMenu())) {
			classTabMenu = "selectedTab";
		}
		return classTabMenu;
  }

	public String idTabMenu() {
		String idTabMenu = "tab"+indexItemMenu;
		return idTabMenu;
	}

	/**
	 * "griser" les menus
	 * @return
	 */
	public final boolean isDisabledUnItemMenu() {
		return false;
	}

	/**
	 * Les libelles des items du menu
	 * @return
	 */
	public abstract NSArray<String> getMenuItems(); 


	/**
	 * L'action a affectuer lors de la selection d'un item du menu.
	 * Methode a surcharger s'il y a quelque chose d'autre a faire.
	 */
  public WOComponent selectMenu() {
  	setSelectedItemMenu(unItemMenu);
  	return neFaitRien();
  }
	
	/**
	 * Dans certains cas, on peut afficher une aide supplementaire
	 * @return
	 */
	public NSArray<String> getEtapeInfos() {
		return getMenuItems();
	}

  /**
   * L'info bulle affectee a un onglet
   */ 
  public String popInfo() {
  	Object commentaire = getEtapeInfos().objectAtIndex(indexItemMenu);
  	String strCommentaire = StringCtrl.emptyString();
  	if (commentaire instanceof String) {
  		strCommentaire = (String) commentaire;
  	}
  	return strCommentaire;
  }
  
  
}
