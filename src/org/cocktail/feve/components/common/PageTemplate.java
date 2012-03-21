package org.cocktail.feve.components.common;

/*
 * Copyright Universit� de La Rochelle 2004
 *
 * ctarade@univ-lr.fr
 *
 * Ce logiciel est un programme informatique servant � g�rer les comptes
 * informatiques des utilisateurs. 
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".

 * En contrepartie de l'accessibilit� au code source et des droits de copie,
 * de modification et de redistribution accord�s par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
 * seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les conc�dants successifs.

 * A cet �gard  l'attention de l'utilisateur est attir�e sur les risques
 * associ�s au chargement,  � l'utilisation,  � la modification et/ou au
 * d�veloppement et � la reproduction du logiciel par l'utilisateur �tant 
 * donn� sa sp�cificit� de logiciel libre, qui peut le rendre complexe � 
 * manipuler et qui le r�serve donc � des d�veloppeurs et des professionnels
 * avertis poss�dant  des  connaissances  informatiques approfondies.  Les
 * utilisateurs sont donc invit�s � charger  et  tester  l'ad�quation  du
 * logiciel � leurs besoins dans des conditions permettant d'assurer la
 * s�curit� de leurs syst�mes et ou de leurs donn�es et, plus g�n�ralement, 
 * � l'utiliser et l'exploiter dans les m�mes conditions de s�curit�. 

 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accept� les
 * termes.
 */

import org.cocktail.feve.app.Session;
import org.cocktail.fwkcktlwebapp.server.components.CktlMenuItemSet;
import org.cocktail.fwkcktlwebapp.server.components.CktlMenuListener;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

public class PageTemplate extends FeveWebComponent {

	/**
	 * Les bindings pour la CktlDefaultPage
	 */
	public String pageTitle;

	public PageTemplate(WOContext context) {
		super(context);
	}

	/**
	 * 
	 */
	public void appendToResponse(WOResponse arg0, WOContext arg1) {
		super.appendToResponse(arg0, arg1);
		// css theme
		addLocalCss(arg0, "css/CktlCommon.css", "FwkCktlThemes");
		addLocalCss(arg0, "css/CktlCommonBleu.css", "FwkCktlThemes");
		// ajout de la css specifique a cette appli
		// addLocalCss(arg0, "css/Feve.css");
		// ajax
		// FAIT PLANTER LES OBSERVER
		/*
		 * addLocalJScript(arg0, "prototype.js", "Ajax"); addLocalJScript(arg0,
		 * "wonder.js", "Ajax"); addLocalJScript(arg0, "effects.js", "Ajax");
		 */
		// dans le cas d'une page avec le menu, on retaille pour que la
		// page soit completement remplie
		if (!session.isPageLogin) {
			addHTMLBinding(arg0, "marginwidth", "0", TAG_OPEN_BODY);
			addHTMLBinding(arg0, "topmargin", "0", TAG_OPEN_BODY);
			addHTMLBinding(arg0, "leftmargin", "0", TAG_OPEN_BODY);
			addHTMLBinding(arg0, "marginheight", "0", TAG_OPEN_BODY);
		}

		// la css du menu
		// addLocalCss(arg0, "css/CRIWebMenu.css", "FwkCktlWebApp");
		// css de l'application
		addLocalCss(arg0, FeveWebComponent.CSS_FEVE_FILE_NAME);
	}

	public CktlMenuListener menuListener() {
		if (session.getFeveMenuLister() == null)
			session.setFeveMenuLister(new FeveMenuListener());
		return session.getFeveMenuLister();
	}

	public CktlMenuItemSet menuItems() {
		if (session.getFeveMenuItemSet() == null)
			session.setFeveMenuItemSet(initFeveMenuItems());
		return session.getFeveMenuItemSet();

	}

	/**
	 * Les items du menu principal
	 */
	public final static int ID_MENU_ADM = 0; // admin
	public final static int ID_MENU_CID = 1; // changement identite
	public final static int ID_MENU_REC = 2; // recap
	public final static int ID_MENU_BIB = 7; // bibliotheque
	public final static int ID_MENU_LOS = 3; // stats lolf
	public final static int ID_MENU_LOL = 4; // liste lolf
	public final static int ID_MENU_PER = 5; // perso
	public final static int ID_MENU_QUI = 6; // quitter
	public final static int ID_MENU_LOG = 8; // logs de l'application

	/**
	 * Construit et renvoie une collection des elements du menu. Pourrait etre
	 * appelee un fois.
	 */
	private CktlMenuItemSet initFeveMenuItems() {
		CktlMenuItemSet menuItemSet = new CktlMenuItemSet();
		if (session.niveauConnexion() == Session.NIVEAU_CONNEXION_COMPLETE) {
			menuItemSet.addMenuItem(ID_MENU_PER, "Mes fiches", "Mes fiches personnelles(fiche de poste, lolf, entretien professionnel)", "_self");
			// le gars qui voit que ses fiches / evaluation n'a pas acces a ce menu
			if (!feveUserInfo().getIsLimitedUser()) {
				menuItemSet.addMenuItem(ID_MENU_REC, "Toutes les fiches", "Vue d'ensemble des informations", "_self");
			}
			menuItemSet.addMenuItem(ID_MENU_BIB, "Bibliotheque", "Documents d'aide &agrave; l'utilisation", "_self");
			if (session.isAdmin()) {
				menuItemSet.addMenuItem(ID_MENU_ADM, "Administration", "Parametres de l'application", "_self");
				menuItemSet.addMenuItem(ID_MENU_CID, "Changer d'identite", "Tester l'application sous l'identit&eacute; d'une autre personne", "_self");
				// menuItemSet.addMenuItem(ID_MENU_LOG, "Logs",
				// "Fichier de log de l'application", "_self");
			}
		} else if (session.niveauConnexion() == Session.NIVEAU_CONNEXION_LOLF) {
			menuItemSet.addMenuItem(ID_MENU_LOL, "Fiches LOLF", "Affichage des fiches lolf des agents", "_self");
			menuItemSet.addMenuItem(ID_MENU_LOS, "Evaluation", "Affichage des donn&eacute;es saisies concernant la LOLF", "_self");
		}
		menuItemSet.addMenuItem(ID_MENU_QUI, "Quitter", "Quitter l'application", "_self");

		return menuItemSet;
	}

	/**
	 * Definit une classe de receveur d'evenements de menu.
	 */
	public class FeveMenuListener extends CktlMenuListener {

		public void initMenu() {
			// c'est pour que le menu "Mes fiches"
			// soit surligne au debut
			selectItemWithId(ID_MENU_PER);
			// c'est pour que lors du reclic sur "Mes fiches"
			// ca remonte d'un niveau
			selectMenu(ID_MENU_PER);
		}

		// La methode appelee chaque fois qu'un evenement est genere.
		public WOComponent selectMenu(int menuId) {
			// Le libelle de l'entree selectionnee
			// String menuTitre = menu.getMenuWithId(menuId).label;
			// La page-reponse a la selection de menu
			WOComponent reponse = null;
			// raz des modifications non sauvegardees
			ec.revert();
			switch (menuId) {
			case ID_MENU_ADM:
				reponse = session.selectAdministration();
				break;
			case ID_MENU_REC:
				reponse = session.selectToutesLesFiches();
				break;
			case ID_MENU_BIB:
				reponse = session.selectBibliotheque();
				break;
			case ID_MENU_CID:
				reponse = session.selectChangerIdentite();
				break;
			case ID_MENU_PER:
				reponse = session.selectFichesPersonnelles();
				break;
			case ID_MENU_LOL:
				reponse = null;
				break;
			case ID_MENU_LOS:
				reponse = null;
				break;
			case ID_MENU_QUI:
				reponse = session.selectQuitter();
				break;
			case ID_MENU_LOG:
				reponse = session.selectLogs();
				break;
			default:
				break;
			}
			session.setPrevMenuId(menuId);
			return reponse;
		}

		public WOComponent disconnect() {
			return session.logout();
		}
	}
}