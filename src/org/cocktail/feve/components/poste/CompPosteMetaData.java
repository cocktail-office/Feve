package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControler;

import com.webobjects.appserver.WOContext;

/**
 * Composant de gestion des meta données d'un poste (nom, validité, code)
 * - a utiliser pour la creation d'un poste
 * - a utiliser pour la modification d'un poste
 * 
 * @author ctarade
 */
public class CompPosteMetaData 
	extends A_ComponentControled {
	
	public CompPosteMetaData(WOContext context) {
		super(context);
	}

	/**
	 * Castage du {@link A_ComponentControler} en {@link CompPosteMetaDataCtrl}
	 * @return
	 */
	public CompPosteMetaDataCtrl ctrl() {
		return (CompPosteMetaDataCtrl) ctrl;
	}
}