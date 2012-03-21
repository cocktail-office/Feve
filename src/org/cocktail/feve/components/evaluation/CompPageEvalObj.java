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
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOObjectif;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;

public class CompPageEvalObj extends FeveWebComponent {

	private EOEvaluation inputLEvaluation; // l'evaluation en entree

	/**
	 * Indique s'il faut masquer la colonne de resulats (c'est le cas pour les
	 * evaluations precedentes)
	 */
	private boolean isHiddenColonneResultats;

	/**
	 * Indique si l'ajout / suppression / modification sont possibles (c'est le
	 * cas pour les evaluations courantes)
	 */
	private boolean isAllowedAjoutSuppModif;

	/**
	 * Indique si la modification des resultats et observations sont possibles
	 * (c'est le cas pour les evaluations precedentes)
	 */
	private boolean isAllowedModifResultatObservation;

	/**
	 * Binding : la periode concernee pour ces objectifs.
	 */
	private EOEvaluationPeriode inputEvaluationPeriode;

	public EOObjectif unObjectif;

	/** les valeurs des textfields pour les nouveaux objecifs */
	public String objectif, moyen, mesure, resultat, observation;

	/** doit-on afficher le formulaire de saisie d'un nouvel objectif ? */
	public boolean isModeAjouter;

	public CompPageEvalObj(WOContext context) {
		super(context);
	}

	private void resetFields() {
		objectif = moyen = mesure = resultat = observation = "";
	}

	/**
	 * insertion d'un nouvel objectif
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent ajouterObjectif() throws Throwable {

		desactiverModeAjouter();
		EOObjectif newEoObjectif = EOObjectif.newRecordInContext(ec, inputLEvaluation, objectif, moyen, mesure, resultat, observation);
		UtilDb.save(ec, "");
		// rafrachir les to-many
		ec.invalidateObjectsWithGlobalIDs(new NSArray<EOGlobalID>(ec.globalIDForObject(inputLEvaluation)));

		resetFields();

		// se positionner sur le nouvel objectif au prochain rechargement
		session.setOnLoad("document.location='#" + newEoObjectif.ancre() + "';");

		return neFaitRien();
	}

	public WOComponent annulerAjouterObjectif() {
		desactiverModeAjouter();
		return neFaitRien();
	}

	/**
	 * suppression d'un objectif
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent supprimerObjectif() throws Throwable {
		desactiverModeAjouter();
		inputLEvaluation.removeObjectFromBothSidesOfRelationshipWithKey(unObjectif, "tosObjectif");
		ec.deleteObject(unObjectif);
		UtilDb.save(ec, "");
		// rafrachir les to-many
		ec.invalidateObjectsWithGlobalIDs(new NSArray<EOGlobalID>(ec.globalIDForObject(inputLEvaluation)));

		return neFaitRien();
	}

	/**
	 * passer un objectif en modification - on passe tous les autres en non
	 * modification
	 * 
	 * @return
	 */
	public WOComponent modifierObjectif() {
		desactiverModeAjouter();
		for (int i = 0; i < inputLEvaluation.tosObjectif().count(); i++) {
			((EOObjectif) inputLEvaluation.tosObjectif().objectAtIndex(i)).setIsEnCoursDeModification(false);
		}
		unObjectif.setIsEnCoursDeModification(true);
		return neFaitRien();
	}

	/**
	 * enregistrement des modifications d'un objectif + on passe tous les
	 * objectfis en non-modification
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent enregistrerObjectif() throws Throwable {

		modifierObjectif();
		unObjectif.setIsEnCoursDeModification(false);
		UtilDb.save(ec, "enregistrerObjectif()");

		// se positionner sur l'objectif modifié au prochain rechargement
		session.setOnLoad("document.location='#" + unObjectif.ancre() + "';");

		return neFaitRien();
	}

	public WOComponent annulerEnregistrerObjectif() {
		modifierObjectif();
		unObjectif.setIsEnCoursDeModification(false);
		ec.revert();
		return neFaitRien();
	}

	// DESCENDRE MONTER

	/**
	 * Remonter l'objectif d'un cran
	 */
	public WOComponent remonterObjectif() throws Throwable {
		unObjectif.up();
		enregistrerPositions();
		return neFaitRien();
	}

	/**
	 * Descendre l'objectif d'un cran
	 */
	public WOComponent descendreObjectif() throws Throwable {
		unObjectif.down();
		enregistrerPositions();
		return neFaitRien();
	}

	/**
	 * Memoriser les positions affichées à l'écran dans la base de données
	 * 
	 * @throws Throwable
	 */
	private void enregistrerPositions() throws Throwable {
		// mettre a jour la position des objectifs
		NSArray<EOObjectif> objectifs = unObjectif.toEvaluation().tosObjectif();
		for (int i = 0; i < objectifs.count(); i++) {
			EOObjectif objectif = objectifs.objectAtIndex(i);
			objectif.setObjPosition(new Integer(i + 1));
		}
		UtilDb.save(ec, "");
	}

	// navigation

	/**
	 * passer en mode ajout
	 * 
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
	 * un des objectif est en cours de modif (field isEnCoursDeModification)
	 * 
	 * @return
	 */
	private boolean auMoinsUnObjectifEstEnCoursDeModification() {
		boolean isModifiable = false;
		for (int i = 0; i < inputLEvaluation.tosObjectif().count(); i++) {
			isModifiable = isModifiable || ((EOObjectif) inputLEvaluation.tosObjectif().objectAtIndex(i)).isEnCoursDeModification();
		}
		return isModifiable;
	}

	// boolean interface

	/**
	 * visibilite de la ligne d'ajout d'objectif (avec tous les champs de saisie)
	 * - le mode ajouter est ON - le composant autorise ajout/supp/modif via
	 * binding - aucun des objectif est en cours de modif (field
	 * isEnCoursDeModification)
	 */
	public boolean showLigneAjouter() {
		boolean show = isModeAjouter;
		if (show) {
			show = isAllowedAjoutSuppModif && !auMoinsUnObjectifEstEnCoursDeModification();
		}
		return show;
	}

	/**
	 * visibilite de l'hyperlien "Ajouter un nouvel objectif" - le mode ajouter
	 * est OFF - le composant est pas disabled via binding - aucun des objectif
	 * est en cours de modif (field isEnCoursDeModification)
	 * 
	 * @return
	 */
	public boolean showLienAjouter() {
		boolean show = !isModeAjouter;
		if (show) {
			show = isAllowedAjoutSuppModif && !auMoinsUnObjectifEstEnCoursDeModification();
		}
		return show;
	}

	/**
	 * disponibilite des boutons de suppression de l'item en cours
	 */
	public boolean showBtnSupprimer() {
		boolean show = isAllowedAjoutSuppModif;
		if (show) {
			show = !auMoinsUnObjectifEstEnCoursDeModification() && !isModeAjouter;
		}
		return show;
	}

	/**
	 * disponibilite des boutons d'edition/modif de l'item en cours
	 */
	public boolean showBtnModifer() {
		boolean show = isAllowedAjoutSuppModif || isAllowedModifResultatObservation;
		if (show) {
			show = !auMoinsUnObjectifEstEnCoursDeModification() && !isModeAjouter;
		}
		return show;
	}

	/**
	 * Disponibilité des boutons remonter / descendre
	 * 
	 * @return
	 */
	public boolean showBtnRemonterDescendre() {
		boolean show = isAllowedAjoutSuppModif;

		if (show) {
			show = !auMoinsUnObjectifEstEnCoursDeModification() && !isModeAjouter;
		}

		return show;
	}

	/**
	 * Textfield objectif / moyen / element de mesure en cours de modification
	 * 
	 * @return
	 */
	public boolean isDisabledTFObjectifMoyenMesure() {
		boolean isDisabled = !isAllowedAjoutSuppModif;

		return isDisabled;
	}

	/**
	 * Textfield resultats obtenus / observations en cours de modification
	 * 
	 * @return
	 */
	public boolean isDisabledTFResultatObservation() {
		boolean isDisabled = !isAllowedModifResultatObservation;
		return isDisabled;
	}

	// affichage

	/**
	 * le fond de la ligne de l'objectif en cours - modiable : rouge leger - rien
	 * sinon
	 * 
	 * @return
	 */
	public String trBgColorUnObjectif() {
		String color = "";
		if (unObjectif.isEnCoursDeModification())
			color = "#FFE1E1";
		return color;
	}

	/**
	 * 
	 * @return
	 */
	public int getColspanTdLienAjouter() {
		int colspan = 0;

		if (isHiddenColonneResultats()) {
			colspan = 6;
		} else {
			colspan = 7;
		}

		return colspan;
	}

	/**
	 * On affiche un message si l'evaluation n'existe pas (et donc pas d'objectifs
	 * a afficher ...)
	 * 
	 * @return
	 */
	public boolean isInputLEvaluationExiste() {
		return inputLEvaluation != null;
	}

	public final EOEvaluation getInputLEvaluation() {
		return inputLEvaluation;
	}

	public final void setInputLEvaluation(EOEvaluation inputLEvaluation) {
		this.inputLEvaluation = inputLEvaluation;
	}

	public final boolean isHiddenColonneResultats() {
		return isHiddenColonneResultats;
	}

	public final void setIsHiddenColonneResultats(boolean isHiddenColonneResultats) {
		this.isHiddenColonneResultats = isHiddenColonneResultats;
	}

	public final boolean isAllowedAjoutSuppModif() {
		return isAllowedAjoutSuppModif;
	}

	public final void setIsAllowedAjoutSuppModif(boolean isAllowedAjoutSuppModif) {
		this.isAllowedAjoutSuppModif = isAllowedAjoutSuppModif;
	}

	public final boolean isAllowedModifResultatObservation() {
		return isAllowedModifResultatObservation;
	}

	public final void setIsAllowedModifResultatObservation(
			boolean isAllowedModifResultatObservation) {
		this.isAllowedModifResultatObservation = isAllowedModifResultatObservation;
	}

	public final EOEvaluationPeriode getInputEvaluationPeriode() {
		return inputEvaluationPeriode;
	}

	public final void setInputEvaluationPeriode(
			EOEvaluationPeriode inputEvaluationPeriode) {
		this.inputEvaluationPeriode = inputEvaluationPeriode;
	}
}