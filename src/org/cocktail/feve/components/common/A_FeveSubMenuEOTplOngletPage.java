package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

/**
 * Modification de la classe {@link A_FeveSubMenuPage} pour ne pas gérer
 * des {@link String} en menu, mais plutôt des {@link EOTplOnglet}
 * 
 * TODO prévoir une classe gérérique pour fusionner avec {@link A_FeveSubMenuPage}
 * 
 * @author ctarade
 */
public abstract class A_FeveSubMenuEOTplOngletPage
	extends FeveWebComponent {

	private int indexItemMenu; 
	private EOTplOnglet eoTplOngletItem;
	private EOTplOnglet eoTplOngletSelected;
	
	public A_FeveSubMenuEOTplOngletPage(WOContext context) {
		super(context);
	}

	@Override
  public void appendToResponse(WOResponse aResponse, WOContext aContext) {
  	super.appendToResponse(aResponse, aContext);
		// la css du menu
		addLocalCss(aResponse, "css/SubMenu.css");
  }

  /**
   * 
   */
	protected void razCache() {
  	setIndexItemMenu(0);
  	setEoTplOngletItem(null);
  	setEoTplOngletSelected(null);
  }

	
	public abstract NSArray<EOTplOnglet> getEoTplOngletArray();
	
	 
  public String classTabMenu() {
  	String classTabMenu = "tab";
  	
  	if (getEoTplOngletItem() == getEoTplOngletSelected()) {
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
	 * L'action a affectuer lors de la selection d'un item du menu.
	 * Methode a surcharger s'il y a quelque chose d'autre a faire.
	 */
  public WOComponent selectMenu() {
  	setEoTplOngletSelected(eoTplOngletItem);
  	return neFaitRien();
  }
  
  //FIXME RECUP DE A_FeveSubMenuPageControled

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

	public final int getIndexItemMenu() {
		return indexItemMenu;
	}

	public final void setIndexItemMenu(int indexItemMenu) {
		this.indexItemMenu = indexItemMenu;
	}

	public final EOTplOnglet getEoTplOngletItem() {
		return eoTplOngletItem;
	}

	public final void setEoTplOngletItem(EOTplOnglet eoTplOngletItem) {
		this.eoTplOngletItem = eoTplOngletItem;
	}
	
	public EOTplOnglet getEoTplOngletSelected() {
		// selection du premier onglet par defaut
		if (eoTplOngletSelected == null) {
			if (getEoTplOngletArray().count() > 0) {
				eoTplOngletSelected = getEoTplOngletArray().objectAtIndex(0);
			}
		}
		return eoTplOngletSelected;
	}

	public final void setEoTplOngletSelected(EOTplOnglet eoTplOngletSelected) {
		this.eoTplOngletSelected = eoTplOngletSelected;
	}
 
}
