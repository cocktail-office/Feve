package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOAffectation;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;


/**
 * Ecran de selection d'affectation par service
 * 
 * @author ctarade
 */
public class CompSelectAffectation 
	extends A_ComponentControled {

	/** Le displayGroup contenant une liste d'affectation */
	public WODisplayGroup dgAffectation;

	public CompSelectAffectation(WOContext context) {
		super(context);
	}
	
	/**
	 * Rafraichir le DG si besoin
	 */
	public void appendToResponse(WOResponse response, WOContext context) {
		if (ctrl().shouldRefreshDgAffectation()) {
			ctrl().doRefreshDgAffectation();
		}
		super.appendToResponse(response, context);
	}
	
	
	// getters

	/**
	 * Export du DG vers le controleur
	 */
	protected WODisplayGroup getDgAffectation() {
		return dgAffectation;
	}
	
	/**
	 * raccourci vers le controleur
	 * @return
	 */
	public CompSelectAffectationCtrl ctrl() {
		return (CompSelectAffectationCtrl) ctrl;
	}
	
	/**
	 * raccourci vers l'affectaiton selectionnee dans le controleur
	 * @return
	 */
	public EOAffectation getAffectationSelected() {
		return ctrl().getAffectationSelected();
	}

	// setter silencieux
	
	public void setAffectationSelected(EOAffectation value) {
		
	}
}