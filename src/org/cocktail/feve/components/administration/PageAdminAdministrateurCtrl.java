package org.cocktail.feve.components.administration;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOGrhumRepartStructure;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.foundation.NSArray;


/**
 * Controleur du composant de gestion des droits administrateurs
 * 
 * @author ctarade
 */
public class PageAdminAdministrateurCtrl
	extends A_ComponentControler {

	/** item de la repetition */
	public EOGrhumRepartStructure repartStructureItem;
	
	/** nouvel individu administrateur */
	public EOIndividu individu;
	
	public PageAdminAdministrateurCtrl(Session session) {
		super(session);
	}
	
	/**
	 * Raccouric vers le WODisplayGroup representant
	 * la liste des membres du groupe administrateur
	 * @return
	 */
	public WODisplayGroup repartStructureDg() {
		return ((PageAdminAdministrateur) getComponent()).repartStructureDg;
	}
	
	/**
	 * Suppression d'un administrateur
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doDeleteRepartStructureItem() throws Throwable {
		ec().deleteObject(repartStructureItem);
		try {
			UtilDb.save(ec(), "");
			// rafraichir
			repartStructureDg().fetch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ajout d'un nouvel administrateur
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doAddAdministrateur() throws Throwable {
		// individu selectionn� ?
		if (individu != null) {
			// s'assurer qu'il n'est pas deja admin
			if (!((NSArray) repartStructureDg().displayedObjects().valueForKeyPath(
					EOGrhumRepartStructure.TO_INDIVIDU_KEY + "." + EOIndividu.PERS_ID_KEY)).containsObject(individu.persId())) {
				EOGrhumRepartStructure.createGrhumRepartStructure(
						ec(), feveApp().cStructureAdmin(), individu.persId());
				try {
					UtilDb.save(ec(), "");
					// rafraichir
					repartStructureDg().fetch();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
