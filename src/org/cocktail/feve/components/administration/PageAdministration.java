package org.cocktail.feve.components.administration;
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


import org.cocktail.feve.components.administration.droits.PageCallingPageAdministrationDroits;
import org.cocktail.feve.components.administration.hierarchie.PageAdministrationHierarchieCtrl;
import org.cocktail.feve.components.common.A_FeveSubMenuPage;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


public class PageAdministration 
	extends A_FeveSubMenuPage 
		implements PageCallingPageAdministrationDroits {

	
	/**
	 * Binding obligatoire pour le composant 
	 * PageAdministrationHierarchie
	 */
	public PageAdministrationHierarchieCtrl pageAdministrationHierarchieCtrl = 
		new PageAdministrationHierarchieCtrl(session);
	
  public PageAdministration(WOContext context) {
    super(context);
  }

  public final static String MENU_DROITS_EVALUATION 		= "Droits sur entretiens";
  public final static String MENU_DIVERS								= "Divers";
  public final static String MENU_SUIVI 								= "Outils de suivi";
  public final static String MENU_LOLF									= "LOLF";
  public final static String MENU_OUVERTURE							= "Param&egrave;tres g&eacute;n&eacute;raux";
  public final static String MENU_DROITS								= "Droits";

  // gestion du menu
  public NSArray getMenuItems() {
  	return  new NSArray(
  			new String[]{
  					MENU_DROITS_EVALUATION, MENU_DIVERS,
  					MENU_SUIVI, MENU_LOLF, 
  					MENU_OUVERTURE, MENU_DROITS});
  }

	public boolean isPageAdministrationArbre()      				{ return getSelectedItemMenu().equals(MENU_DROITS_EVALUATION); }
	public boolean isPageAdministrationDivers()   					{ return getSelectedItemMenu().equals(MENU_DIVERS); }
	public boolean isPageAdministrationSuivi()   						{ return getSelectedItemMenu().equals(MENU_SUIVI); }
	public boolean isPageAdministrationLofl()   						{ return getSelectedItemMenu().equals(MENU_LOLF); }
	public boolean isPageAdministrationOuverture() 					{ return getSelectedItemMenu().equals(MENU_OUVERTURE); }
	public boolean isPageAdminDroits() 											{ return getSelectedItemMenu().equals(MENU_DROITS); }

	public WOComponent caller() {
		return this;
	}
	
	/**
	 * Pouvoir faire la selection de la page de droits sur evaluation
	 * depuis une autre page
	 */
	public void selectMenuDroitEvaluation(EOIndividu value) {
		setSelectedItemMenu(MENU_DROITS_EVALUATION);
		pageAdministrationHierarchieCtrl.setShouldForceAffichage(true);
		pageAdministrationHierarchieCtrl.setShouldSelectMenuDroits(true);
		pageAdministrationHierarchieCtrl.setIndividuDroitToDiplay(value);
	}
	
	
}