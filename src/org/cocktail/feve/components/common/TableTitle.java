package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Une table avec un titre
 * @author ctarade
 */
public class TableTitle extends A_ComponentControled {

	public String title;
	
	public TableTitle(WOContext context) {
		super(context);
	}
}