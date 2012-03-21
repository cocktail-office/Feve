package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;

/**
 * Composant permettant de gerer le détail d'un poste
 * 
 * @author ctarade
 *
 */
public class CompPosteDetail 
	extends A_ComponentControled {
		
	public CompPosteDetail(WOContext context) {
		super(context);
	}
	
	/**
	 * Cast du {@link A_ComponentControler} en {@link CompPosteDetailCtrl}
	 * @return
	 */
	public CompPosteDetailCtrl ctrl() {
		return (CompPosteDetailCtrl) ctrl;
	}
	
	/**
	 * Au retour de la modification des meta données d'un poste, on 
	 * va rafficher le composante en page pleine
	 */
	public void doAfterCompPosteMetaDataSuccess() {
		ctrl().showCompPosteMetaDataCtrl = false;
	}
}