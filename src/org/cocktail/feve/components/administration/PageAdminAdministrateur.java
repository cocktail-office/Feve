package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.A_ComponentControled;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;


/**
 * Ecran de gestion des membres du groupe administration 
 * de l'application.
 * 
 * @author ctarade
 */
public class PageAdminAdministrateur 
	extends A_ComponentControled {
	
	/** le display group affichant la liste des membres du groupe admin */
	public WODisplayGroup repartStructureDg;
	
	public PageAdminAdministrateur(WOContext context) {
		super(context);
		initComponent();
	}
	
	/**
	 * Initialisation 
	 * - binder le groupe d'appartenant a celui designe dans la
	 * 	configuration de l'application
	 */
	private void initComponent() {
		repartStructureDg.queryBindings().setObjectForKey(
				app.cStructureAdmin(), "cStructure");
		repartStructureDg.fetch();
	}
	


	/**
	 * Controleur associé
	 * @return
	 */
  public PageAdminAdministrateurCtrl ctrl() {
  	return (PageAdminAdministrateurCtrl) ctrl;
  }
}