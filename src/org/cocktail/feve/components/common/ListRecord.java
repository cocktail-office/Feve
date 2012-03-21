package org.cocktail.feve.components.common;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;

/*
 * Copyright Universit� de La Rochelle 1993-2006
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

/**
 * @deprecated a virer
 * Une liste generique d'objets fiche et evaluation
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public abstract class ListRecord extends FeveWebComponent {

	/**
	 * Binding du composant pour definir s'il faut afficher
	 * tous les enregistrements personnel <code>true</code>.
	 * Sinon <code>false</code>, alors on affiche tous les
	 * enregistrements, definit selon les droits individuels
	 * de la personne connectee.
	 * 
	 * par defaut, cette valeur est <code>false</code>
	 */
	public boolean inOnlyPersonnel = false;
	
	/**
	 * Binding du composant pour definir s'il faut afficher
	 * tous les enregistrements du poste <code>true</code>.
	 * Sinon <code>false</code>, alors on affiche tous les
	 * enregistrements, associes au poste passe en binding
	 * <code>inPoste</code>.
	 * 
	 * par defaut, cette valeur est <code>false</code>
	 */
	public boolean inOnlyPoste = false;
	
	/**
	 * Binding du composant correspondant au poste pour
	 * lequel on va voir les fiches associees. Cette 
	 * valeur n'est prise en compte que si le binding
	 * <code>isOnlyPoste</code> est a <code>true</code>.
	 */
	public EOPoste inPoste;
	
	/**
	 * Un element de la liste pour la WORepetition
	 */
	public CktlRecord itemRecord;

	/**
	 * L'objet selectionne dans la WORepetition
	 */
	public EOGenericRecord selectedRecord;

	/**
	 * Rechercher des objets d'apres le nom prenom 
	 * du titulaire
	 */
	public String nomPrenom;
  
  /**
   * Definir ce qu'on affiche
   */
  public final static int MODE_FICHE_DE_POSTE = 1;
  public final static int MODE_FICHE_LOLF 		= 2;
  public final static int MODE_EVALUATION 		= 3;
   
	public ListRecord(WOContext context) {
		super(context);
	}
	
	/**
	 * @deprecated
	 * Indique si l'utilisateur connecte a les droits
	 * de modification sur l'objet en cours
	 */
	public abstract boolean canModifItemRecord();
	
	/**
	 * Indique si l'element est verrouille a cause de
	 * don contenu. ex: fiche visee
	 */
	public abstract boolean isLockedItemRecord();
	
	/**
	 * La liste des objets
	 */
	public abstract NSArray listRecords();
	
	/**
	 * Le mode d'utilisation du composant. On definit la nature
	 * des objets en retournant une des 3 valeurs :
	 * - <code>MODE_FICHE_DE_POSTE</code>
	 * - <code>MODE_FICHE_LOLF</code>
	 * - <code>MODE_EVALUATION</code>
	 * @return
	 */
	public abstract int mode();
	
	/**
	 * Indique si un objet a ete selectionne, soit
	 * que <code>selectedRecord</code> n'est pas <code>null</code>
	 */
	public boolean isSelectedRecordExists()    {   
		return selectedRecord != null; 
	}
  
	/**
	 * Masquer le detail de l'enregistrement en cours.
	 * <code>selectedRecord</code> est passe a <code>null</code>
	 */
	public WOComponent hideRecord() {
		selectedRecord = null;
		return neFaitRien();
	}
	
  // pour affichage CktlNavigationBar
  private final static int NB_ITEMS_PAR_PAGE = 20;
  private WODisplayGroup displayGroupRecords; 
  public boolean shouldRewindDisplayGroup;
   
  public WODisplayGroup displayGroupRecords(){
    if (displayGroupRecords == null) {
      displayGroupRecords = new WODisplayGroup();
      // pas de multi-page pour la consulation d'infos persos 
      if(!inOnlyPersonnel)
      	displayGroupRecords.setNumberOfObjectsPerBatch(NB_ITEMS_PAR_PAGE); 
    }
    displayGroupRecords.setObjectArray(listRecords());
    if (shouldRewindDisplayGroup) {
    	displayGroupRecords.setCurrentBatchIndex(0);
    	shouldRewindDisplayGroup = false;
    }
    return displayGroupRecords;
  }
  
  /**
   * Definir la periode de validite des enregistrement
   */
  public final String PERIODE_ACTUEL = "ACTUEL";
  public final String PERIODE_ANCIEN = "ANCIEN";
  public String periode = PERIODE_ACTUEL;
  
  /**
   * 
   */
  public boolean showActuelles() {
  	return periode.equals(PERIODE_ACTUEL);
  }
  
  /**
   * 
   */
  public boolean showAnciennes() {
  	return periode.equals(PERIODE_ANCIEN);
  }
  

  /**
   * lors du submit suite a un changement de coche : sauvegarde immediate
   * @return
  * @throws Throwable 
   */
  public WOComponent sauverCoche() throws Throwable {
    UtilDb.save(ec, "");
    return neFaitRien();
  }
  

  /**
   * Surcharge des setter de filtrage, afin de remettre
   * le WODisplayGroup associe aux premier element.
   */
  public void setPeriode(String value) {
  	if (value != null && value != periode)
  		shouldRewindDisplayGroup = true;
  	periode = value;
  }
  
  public void setNomPrenom(String value) {
  	if (value != null && value != nomPrenom)
  		shouldRewindDisplayGroup = true;
  	nomPrenom = value;
  }
  
  /**
   * Action de selectionner un enregistrement.
   */
  public WOComponent clicRecord() {
  	selectedRecord = itemRecord;
  	return neFaitRien();
  }
  
  // style des elements graphique

  /**
   * La classe CSS associee a une liste
   */
  public String classTrElementList() {
  	StringBuffer sbClass = new StringBuffer();
 	 if (canModifItemRecord() && !isLockedItemRecord()) {
 		 sbClass.append(FeveWebComponent.CLASS_TR_MODIFIABLE);
 	 } else {
 		 sbClass.append(FeveWebComponent.CLASS_TR_NON_MODIFIABLE);
 	 }
 	 return sbClass.toString();
  }
}
