package org.cocktail.feve.components.evaluation;
/*
 * Copyright Universit� de La Rochelle 2005
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

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOSituActivite;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;


public class CompPageEvalSituActi extends FeveWebComponent {

	public EOEvaluation inputLEvaluation; 	// l'evaluation en entree
	public boolean disabled;	// autorise-t-on la modification ?
 
	public EOSituActivite uneSituActivite;
	public int indexRepetition;
    
	public String activiteLibelle;
    
	public boolean isModeAjouter;	// doit-on afficher le formulaire de saisie d'une nouvelle situation ?
    
    public CompPageEvalSituActi(WOContext context) {
        super(context);
    }

    private void resetFields() {
        activiteLibelle = "";
    }
    
    /**
     * insertion d'une nouvelle situation d'activite
     * @return
     * @throws Throwable
     */
    public WOComponent ajouterSituActi() throws Throwable {
        desactiverModeAjouter();
        /*EOSituActivite nouvelleSituActivite = */EOSituActivite.newRecordInContext(ec, inputLEvaluation, activiteLibelle);
        UtilDb.save(ec, "");
        resetFields();
        return neFaitRien();
    }
    
    public WOComponent annulerAjouterSituActi() {
        desactiverModeAjouter();
        return neFaitRien();
    }
    
    /**
     * suppression d'une situation d'activite
     * @return
     * @throws Throwable
     */
    public WOComponent supprimerSituActi() throws Throwable {
        inputLEvaluation.removeObjectFromBothSidesOfRelationshipWithKey(uneSituActivite, "tosSituActivite");
        ec.deleteObject(uneSituActivite);
        UtilDb.save(ec, "");
        return neFaitRien();
    }
    
    /**
     * passer un situation d'activite en modification
     * - on passe tous les autres en non modification
     * @return
     */
    public WOComponent modifierSituActi() {
      desactiverModeAjouter();
      for (int i = 0; i < inputLEvaluation.tosSituActivite().count(); i++) {
        ((EOSituActivite)inputLEvaluation.tosSituActivite().objectAtIndex(i)).setIsEnCoursDeModification(false);
      }
      uneSituActivite.setIsEnCoursDeModification(true);
      return neFaitRien();
    }
    
    /**
     * enregistrement des modifications d'un situation d'activite
     * + on passe tous les situation d'activite en non-modification
     * @return
     * @throws Throwable
     */
    public WOComponent enregistrerSituActi() throws Throwable {
        modifierSituActi();
        uneSituActivite.setIsEnCoursDeModification(false);
        UtilDb.save(ec, "enregistrerSituActi()");
        return neFaitRien();
    }
    
    
    public WOComponent annulerEnregistrerSituActi() {
        modifierSituActi();
        uneSituActivite.setIsEnCoursDeModification(false);
        ec.revert();
        return neFaitRien();
    }
    
    /**
     * passer en mode ajout 
     * @return
     */
    public WOComponent activerModeAjouter() {
        isModeAjouter = true;
        return neFaitRien();
    }
    
    public WOComponent desactiverModeAjouter() {
        isModeAjouter = false;
        return neFaitRien();
    }


    /**
     * un des situation d'activite est en cours de modif (field isEnCoursDeModification)
     * @return
     */
    private boolean auMoinsUneSituActiEstEnCoursDeModification() {
        boolean isModifiable = false;
        for (int i = 0; i < inputLEvaluation.tosSituActivite().count(); i++) {
            isModifiable = isModifiable || ((EOSituActivite)inputLEvaluation.tosSituActivite().objectAtIndex(i)).isEnCoursDeModification();
        }
        return isModifiable;
    }
    
   /**
     * visibilite de la ligne d'ajout de situation d'activite (avec tous les champs de saisie)
     * - le mode ajouter est ON
     * - le composant est pas disabled via binding
     * - aucun des objectif est en cours de modif (field isEnCoursDeModification)
     */
    public boolean showLigneAjouter() {
        return isModeAjouter && !disabled && !auMoinsUneSituActiEstEnCoursDeModification();
    }
    
    /**
     * visibilite de l'hyperlien "Ajouter un nouvel objectif"
     * - le mode ajouter est OFF 
     * - le composant est pas disabled via binding
     * - aucune des situations d'activite est en cours de modif (field isEnCoursDeModification)
     * @return
     */
    public boolean showLienAjouter() {
        return !isModeAjouter && !disabled && !auMoinsUneSituActiEstEnCoursDeModification();
    }
    
    /**
     * l'item en cours est-il verrouill� (le contenu des zones de texte)
     */
    public boolean isDisabledTexteUneSituActi() {
       return disabled || !uneSituActivite.isEnCoursDeModification() ; 
    }

    /**
     * disponibilite des boutons de manip de l'item en cours (modif / supp)
     * @return
     */
    public boolean isDisabledBtnModifSup() {
        return disabled || auMoinsUneSituActiEstEnCoursDeModification() || isModeAjouter; 
    }
    
    
    /**
     * le fond de la ligne de situation d'activite en cours
     * - modiable : rouge leger
     * - rien sinon
     * @return
     */
    public String trBgColorUneSituActi() {
        String color = "";
        if (uneSituActivite.isEnCoursDeModification()) {
            color = "#FFE1E1";
        }
        return color;
    }
    
    public String titreSituation() {
        return "Expos&eacute; situation d'activit&eacute; " + (indexRepetition+1) ;
    }
}