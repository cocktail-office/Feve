package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.mangue.EODroitNouvelEntrant;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

public class GestionDroitsNouveauxEntrantsCtrl
		extends A_ComponentControler {

	// periodes d'evaluations
	public NSArray<EOEvaluationPeriode> evaluationPeriodeList;
	public EOEvaluationPeriode evaluationPeriodeItem;
	public EOEvaluationPeriode evaluationPeriodeSelected;

	//
	public EOVCandidatEvaluation eoVCandidatEvaluationItem;

	//
	public EODroitNouvelEntrant eoDroitNouvelEntrantItem;

	// ajout d'un nouveau droit
	public AjoutDroitNouvelEntrantCtrl ajoutDroitNouvelEntrantCtrl;

	public boolean isAfficherAjouter;

	public GestionDroitsNouveauxEntrantsCtrl(Session aSession) {
		super(aSession);
		initCtrl();
	}

	private void initCtrl() {
		//
		evaluationPeriodeList = EOEvaluationPeriode.fetchAllEvaluationPeriodes(
				ec(),
				CktlSort.newSort(EOEvaluationPeriode.EPE_D_DEBUT_KEY));
		// on preselectionne la periode actuelle
		evaluationPeriodeSelected = EOEvaluationPeriode.getCurrentPeriode(
				ec());
		//
		isAfficherAjouter = false;
	}

	public WOComponent toAjouter() {
		ajoutDroitNouvelEntrantCtrl = new AjoutDroitNouvelEntrantCtrl(
				feveSession(), eoVCandidatEvaluationItem.toIndividu(), evaluationPeriodeSelected, this);
		isAfficherAjouter = true;
		return null;
	}

	public WOComponent toAjouterNonDefini() {
		ajoutDroitNouvelEntrantCtrl = new AjoutDroitNouvelEntrantCtrl(
				feveSession(), evaluationPeriodeSelected, this);
		isAfficherAjouter = true;
		return null;
	}

	public WOComponent annuler() {
		isAfficherAjouter = false;
		return null;
	}

	public void afterAjouterOk() {
		isAfficherAjouter = false;
	}

	public NSTimestamp dDebut() {
		return eoDroitNouvelEntrantItem.dneDDebut();
	}

	public void setDDebut(NSTimestamp value) throws Throwable {
		eoDroitNouvelEntrantItem.setDneDDebut(value);
		sauvegarde();
	}

	public NSTimestamp dFin() {
		return eoDroitNouvelEntrantItem.dneDFin();
	}

	public void setDFin(NSTimestamp value) throws Throwable {
		eoDroitNouvelEntrantItem.setDneDFin(value);
		sauvegarde();
	}

	public WOComponent supprimer() throws Throwable {
		ec().deleteObject(eoDroitNouvelEntrantItem);
		sauvegarde();
		return null;
	}
}
