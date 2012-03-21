package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Gestion d'un fil d'ariane
 * 
 * @author ctarade
 */
public class FilAriane extends FeveWebComponent {
	
	public A_ComponentControlerAndFilArianeNode nodeRoot;
	
	public FilAriane(WOContext context) {
		super(context);
	}
}