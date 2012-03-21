package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

/**
 * 
 * @author ctarade
 */
public class LigneDocumentation 
	extends WOComponent {
  
	public EOMangueParametres eoMangueParametre;
	public String updateContainerID;
	public boolean isDisabled = true;
	
	public LigneDocumentation(WOContext context) {
		super(context);
	}
}