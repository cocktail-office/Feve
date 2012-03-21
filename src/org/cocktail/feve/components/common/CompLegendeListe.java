package org.cocktail.feve.components.common;
// Generated by the WOLips Templateengine Plug-in at 15 janv. 2007 11:10:43

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

/**
 * La legende pour une page affichant une liste
 *
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class CompLegendeListe extends WOComponent {

	/**
	 * Binding du composant. Indique s'il faut
	 * afficher la legende sur les incoces
	 * Par defaut, sa valeur est <code>true</code>.
	 */
	public boolean showLegendeIcones = true;
	
	public CompLegendeListe(WOContext context) {
		super(context);
	}

}