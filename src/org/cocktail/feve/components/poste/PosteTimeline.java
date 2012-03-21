package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;


/**
 * Page affichant une liste de poste sous forme de timeline
 * 
 * @author ctarade
 */
public class PosteTimeline 
	extends A_ComponentControled {
	
	public PosteTimeline(WOContext context) {
		super(context);
	}
	
	/**
	 * Castage du <code>YCktlWebPageCtrl</code> en <code>PosteTimelineCtrl</code
	 * @return
	 */
	public PosteTimelineCtrl ctrl() {
		return (PosteTimelineCtrl) ctrl;
	}
}