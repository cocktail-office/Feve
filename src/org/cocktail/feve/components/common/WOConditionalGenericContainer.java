package org.cocktail.feve.components.common;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver._private.WOGenericContainer;

/**
 * Extenstion de {@link WOGenericContainer} afin de n'inclure dans
 * les balise {@link #elementName} que si le boolean {@link #condition}
 * est a true
 * 
 * @author ctarade
 */
public class WOConditionalGenericContainer extends WOComponent {

	public String elementName;
	public String elementStyle;
	public boolean condition;
	
	public WOConditionalGenericContainer(WOContext context) {
		super(context);
		initNullableValues();
	}
	
	/**
	 * Initialiser la variables dont les bindings sont facultatifs
	 */
	private void initNullableValues() {
		elementStyle = null;
	}
	
	/**
	 * La balise d'ouverture
	 * @return
	 */
	public String getElementStart() {
		String elementStart = "";
		elementStart = "<"+elementName;
		if (!StringCtrl.isEmpty(elementStyle)) {
			elementStart += " style=\"" + elementStyle + "\"";
		}
		elementStart += ">";
		return elementStart;
	}
	
	/**
	 * La balise d'ouverture
	 * @return
	 */
	public String getElementEnd() {
		String elementEnd = "";
		elementEnd = "</"+elementName+">";
		return elementEnd;
	}
}