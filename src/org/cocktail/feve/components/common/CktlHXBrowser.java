package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Surcharge du composant {@link org.cocktail.fwkcktlwebapp.server.components.CktlHXBrowser} pour
 * ne plus préciser explicitement le formulaire HTML contenant le browser
 * @author ctarade
 *
 */
public class CktlHXBrowser extends org.cocktail.fwkcktlwebapp.server.components.CktlHXBrowser {

	public CktlHXBrowser(WOContext context) {
		super(context);
	}

  public String submitActionName() {
  	return "this.form.submit()";
  }
}
