package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;

import com.webobjects.appserver.WOContext;

/**
 * Gestion de l'affichage des photos des agents
 * 
 * @author ctarade
 */
public class PhotoIndividu 
	extends FeveWebComponent {
	
	public EOIndividu individu;
	
	public PhotoIndividu(WOContext context) {
		super(context);
	}
}