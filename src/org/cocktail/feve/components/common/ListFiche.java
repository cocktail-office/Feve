package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;

import com.webobjects.appserver.WOContext;
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
 * Une liste generique d'objets fiche de poste et fiche LOLF
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public abstract class ListFiche extends ListRecord {

  
  /**
   * Affichage de l'occupation. On met la police en italique si 
   * cette occupation n'est pas la courante a la fiche
   */
  public boolean isOccupationCourante;
  
  /**
   * L'occupation associee au poste. On affiche en priorite 
   * celle qui est courante. Si non trouve, alors 
   */
  public EOAffectationDetail recAffectationDetail;
  
  /**
   * La structure item pour le filtrage des fiches
   */
  public EOStructure structureItem;

	public ListFiche(WOContext context) {
		super(context);
	}

  
  /**
   * Setter appele par les setters de <code>uneFicheDePoste</code>
   * et <code>uneFicheLolf</code>, dans lequel est initialise un
   * certain nombre de variables communes :
   * - <code>isOccupationCourante</code>
   * - <code>recOccupation</code>
   */
  public void setItemRecord(CktlRecord value) {
  	isOccupationCourante = true;
  	if (value != null) {
    	recAffectationDetail = ((A_Fiche)value).toAffectationDetailActuelle();
    	if (recAffectationDetail == null) {
    		isOccupationCourante = false;
    		recAffectationDetail = ((A_Fiche)value).toAffectationDetailDerniere();
    	}		
  	}
  	itemRecord = value;
  }  
  
  /**
   * Toutes les fiches de poste ou LOLF a afficher selon les filtres
   * mis en place : 
   * - periode (actuelles / anciennes)
   * - service (celui du poste associe)
   * - nom de l'occupant.
   * 
   * Si l'un des bindings <code>inOnlyPersonnel</code> ou 
   * <code>inOnlyPoste</code> est a <code>true</code>, alors la liste
   * est restreinte aux fiches du poste <code>inPoste</code>.
   */
  public NSArray listRecords() {
  	NSArray lesFiches = new NSArray();
  	if (inOnlyPersonnel) {
  		// les propres fiches de l'agent
  		lesFiches = (mode() == MODE_FICHE_DE_POSTE ?
  				session.individuConnecte().tosFicheDePoste() : session.individuConnecte().tosFicheLolf());
  		lesFiches = NSArrayCtrl.removeDuplicate(lesFiches);
  	} else if (inOnlyPoste) {
  		// les fiches du poste
  		lesFiches = (mode() == MODE_FICHE_DE_POSTE ?
  				inPoste.tosFicheDePoste() : inPoste.tosFicheLolf());
  		lesFiches = NSArrayCtrl.removeDuplicate(lesFiches);
  	}/* else {
      String strQual = "";
      NSArray args = new NSArray(Boolean.TRUE);
      if (showActuelles())
        strQual += "isActuelle = %@";
      else if (showAnciennes())
        strQual += "isFermee = %@";
      if (structureSelected != null) {
        strQual += " AND toPoste.toStructure = %@";
        args = args.arrayByAddingObject(structureSelected);
      }
      NSArray lesFichesSource = (mode() == MODE_FICHE_DE_POSTE ? 
      		session.lesFicheDePoste() : session.lesFicheLolf());
       lesFiches = EOQualifier.filteredArrayWithQualifier(
      		lesFichesSource, EOQualifier.qualifierWithQualifierFormat(strQual, args));
      if (!StringCtrl.isEmpty(nomPrenom)) {
        lesFiches = FinderFeve.filterIndividuForNomOrPrenomInArray(
        		lesFiches, nomPrenom, "toAffectationDetailActuelle.toAffectation.toIndividu.");
      }
      // classement par nom de l'individu
      lesFiches = EOSortOrdering.sortedArrayUsingKeyOrderArray(
      		lesFiches, EOFicheDePoste.ARRAY_SORT_INDIVIDU);
  	}*/
    return lesFiches;
  }

  /**
   * L'ecran pour filter par structure et par nom n'est
   * disponible que si <code>inOnlyPersonnel</code> et 
   * <code>inOnlyPoste</code> sont a <code>false</code>.
   * @return
   */
  public boolean showFiltres() {
  	return !inOnlyPersonnel && !inOnlyPoste;
  }
}
