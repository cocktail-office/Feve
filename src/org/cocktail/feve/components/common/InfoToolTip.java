package org.cocktail.feve.components.common;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;

/**
 * Une infobulle avec image d'info

 * @author ctarade
 */
public class InfoToolTip extends com.webobjects.appserver.WOComponent {
	
	// bindings
	public String inText;
	public boolean inTextIsHtml = false;
	
	//
	private String _inTextHtml;
	
	public InfoToolTip(WOContext context) {
		super(context);
	}
	
	public String getInTextHtml() {
		if (_inTextHtml == null) {
			if (inTextIsHtml) {
				_inTextHtml = inText;
			} else {
				_inTextHtml = WOMessage.stringByEscapingHTMLAttributeValue(inText);
			}
		}
		return _inTextHtml;
	}
	
	public boolean shouldBeDisplayed() {
		return !StringCtrl.isEmpty(inText);
	}
}