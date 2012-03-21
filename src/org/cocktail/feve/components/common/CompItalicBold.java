package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Un composant qui permet d'afficher un contenu
 * - en italique ou en gras
 * @author ctarade
 */

public class CompItalicBold extends com.webobjects.appserver.WOComponent {

	// binding
	public boolean isBold = false;
	public boolean isItalic = false;
	
	public CompItalicBold(WOContext context) {
		super(context);
	}
	
	public String getPrefixContent() {
		String suffix = "";
		if (isBold) {
			suffix = "<b>";
		} 
		if (isItalic) {
			suffix = "<i>";
		}
		return suffix;
	}
	
	public String getSuffixContent() {
		String suffix = "";
		if (isBold) {
			suffix += "</b>";
		}	
		if (isItalic) {
			suffix += "</i>";
		}
		return suffix;
	}
}