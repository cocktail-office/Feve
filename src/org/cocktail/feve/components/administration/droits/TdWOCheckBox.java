package org.cocktail.feve.components.administration.droits;

import org.cocktail.fwkcktlwebapp.server.components.CktlWebComponent;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOCheckBox;

/**
 * Extension de la {@link WOCheckBox} entourée d'une cellule de tableau
 * dont la couleur de fond s'adapte à l'état
 * 
 * @author ctarade
 */
public class TdWOCheckBox extends CktlWebComponent {

	public boolean isHidden;
	public boolean isDisabled;
	public boolean isChecked;
	
	private final static String PATH_JS_SCRIPT_CHANGECOLOR  = "js/ChangeCouleurFondCellule.js";
	
	public TdWOCheckBox(WOContext context) {
		super(context);
	}
	
	@Override
	public void appendToResponse(WOResponse arg0, WOContext arg1) {
		// TODO Auto-generated method stub
		super.appendToResponse(arg0, arg1);
		addLocalJScript(arg0, PATH_JS_SCRIPT_CHANGECOLOR);
	}
	
	/**
	 * Pour les coches non actives, on grise un peu le fond.
	 * Pour les cachees, on grise beaucoup plus.
	 * @return
	 */
	public String getTdCheckBoxClass() {
		String strClass = "bordureGaucheHaut";
		if (isHidden) {
			strClass += " fondTresGrise";
		} else if (isDisabled) {
			strClass += " fondGrise";
		}
		return strClass;
	}
}