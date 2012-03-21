package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

/**
 * Page de gestion des poste 
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class PagePoste 
	extends A_ComponentControled {

	/**
   * L'objet de type <code>WODisplayGroup</code> contenant des
   * enregistrement <code>EOPoste</code> de l'entite Poste
   */
	public WODisplayGroup dgPoste;
	
	/**
	 * Le nombre d'appel au composant. Lors du premier appel, on doit 
	 * executer {@link PagePosteCtrl#doFilterDgPoste()} à partir de 
	 * cette classe
	 */
	private int callCount;
	
	public PagePoste(WOContext context) {
		super(context);
	}

	/**
	 * Cast du {@link A_ComponentControler} en {@link PagePosteCtrl}
	 * @return
	 */
	public PagePosteCtrl ctrl() {
		return (PagePosteCtrl) ctrl;
	}
	
	/**
	 * On a besoin de refetcher les postes de temps en temps.
	 * La variable associee est <code>shouldRefreshDgPoste</code>
	 */
	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		if (ctrl().shouldRefreshDgPoste) {
			if (callCount == 0) {
				ctrl().doFilterDgPoste();
			}
			dgPoste.qualifyDataSource();
			ctrl().shouldRefreshDgPoste = false;
		} else if (ctrl().shouldClearDgPoste) {
			dgPoste.setObjectArray(new NSArray<Object>());
			ctrl().shouldClearDgPoste = false;
		}
		callCount++;
		super.appendToResponse(aResponse, aContext);
	}

}