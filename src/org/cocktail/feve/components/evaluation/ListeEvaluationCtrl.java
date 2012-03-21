package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.eos.modele.mangue.A_EOEvaluationKeyValueCoding;

/*
 * Copyright Consortium Coktail, 19 d�c. 06
 * 
 * cyril.tarade at univ-lr.fr
 * 
 * Ce logiciel est un programme informatique servant � [rappeler les
 * caract�ristiques techniques de votre logiciel]. 
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".
 * 
 * En contrepartie de l'accessibilit� au code source et des droits de copie,
 * de modification et de redistribution accord�s par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
 * seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les conc�dants successifs.
 * 
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
 * 
 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accept� les
 * termes.
 */

/**
 * Controleur de la page des evaluations
 */

public class ListeEvaluationCtrl
		extends A_ComponentControlerAndFilArianeNode {

	/** le controleur utilisé pour l'inspection d'une evaluation */
	public PageEvaluationCtrl pageEvaluationCtrl;

	/** faut-il afficher la période de l'évaluation pour chaque item de la liste */
	private boolean isAfficherPeriode;

	public ListeEvaluationCtrl(Session aSession) {
		super(aSession);
		// par défaut, affichage de la période
		setIsAfficherPeriode(true);
	}

	/**
	 * Afficher le composant sous son apparence globale, on masque les details en
	 * cours et on affiche la liste
	 */
	public void redisplayGlobalComponent() {
		ListeEvaluation listeEvaluation = (ListeEvaluation) getComponent();
		listeEvaluation.hideRecord();
	}

	/**
	 * L'action de selectionner une evaluation pour inspection
	 */
	public void doSelectEvaluation(A_EOEvaluationKeyValueCoding anEvaluation) {
		//
		pageEvaluationCtrl = new PageEvaluationCtrl(feveSession(), anEvaluation);
		pageEvaluationCtrl.setStringLabel(anEvaluation.toIndividu().nomPrenom() + " " + anEvaluation.toEvaluationPeriode().strAnneeDebutAnneeFin());
	}

	/**
	 * Annuler la selection
	 */
	public void doDeselectEvaluation() {
		pageEvaluationCtrl = null;
	}

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return pageEvaluationCtrl;
	}

	@Override
	protected void toLocalFullComponent() {
		redisplayGlobalComponent();
	}

	public final boolean getIsAfficherPeriode() {
		return isAfficherPeriode;
	}

	public final void setIsAfficherPeriode(boolean isAfficherPeriode) {
		this.isAfficherPeriode = isAfficherPeriode;
	}
}
