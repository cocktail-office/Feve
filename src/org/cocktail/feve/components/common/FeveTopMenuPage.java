package org.cocktail.feve.components.common;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;



/**
 * Classe a surcharger par les pages qui sont les pages de niveau
 * le plus haut, c'est a dire qu'elles s'affichent en cliquant sur
 * un item du menu du haut.
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public class FeveTopMenuPage extends FeveWebComponent {

	public FeveTopMenuPage(WOContext context) {
		super(context);
	}

	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		super.appendToResponse(aResponse, aContext);
		// la css du menu
		addLocalCss(aResponse, "css/SubMenu.css");
	}
}
