package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

/**
 * Afficher un message {@link #value} si le condition {@link #condition} est <code>true</code>
 * @author ctarade
 *
 */
public class WOConditionalErrorMessage extends WOComponent {
	
	public String value;
	public boolean condition;
	
	public WOConditionalErrorMessage(WOContext context) {	
		super(context);
	}
}