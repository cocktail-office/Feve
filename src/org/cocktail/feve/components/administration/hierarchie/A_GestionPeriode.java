package org.cocktail.feve.components.administration.hierarchie;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.fwkcktlwebapp.common.CktlSort;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


/**
 * Rassemble toutes les méthodes communes au pages
 * de la gestion des periodes, en selectionnant la période
 * en cours lors du premier affichage
 * @author ctarade
 *
 */
public abstract class A_GestionPeriode 
	extends FeveWebComponent {

	// la liste des periodes d'evaluation
	private NSArray<EOEvaluationPeriode> periodeArray;
	private EOEvaluationPeriode periodeItem; 
	private EOEvaluationPeriode periodeSelected; 

	public A_GestionPeriode(WOContext context) {
		super(context);
		resetPeriodeArray();
	}

	/**
	 * Forcer le rechargement de la liste des périodes
	 */
	public void resetPeriodeArray() {

		// retrouver les periodes d'evaluation
		periodeArray = EOEvaluationPeriode.fetchAllEvaluationPeriodes(
				ec, CktlSort.newSort(EOEvaluationPeriode.EPE_D_DEBUT_KEY));
		//
		periodeSelected = EOEvaluationPeriode.getCurrentPeriode(ec);
		// on prend la deniere en date sinon
		if (periodeSelected == null) {
			periodeSelected = EOEvaluationPeriode.getLastPeriode(ec);
		}
	}
	
	public final EOEvaluationPeriode getPeriodeItem() {
		return periodeItem;
	}

	public final void setPeriodeItem(EOEvaluationPeriode periodeItem) {
		this.periodeItem = periodeItem;
	}

	public final EOEvaluationPeriode getPeriodeSelected() {
		return periodeSelected;
	}

	public void setPeriodeSelected(EOEvaluationPeriode periodeSelected) {
		this.periodeSelected = periodeSelected;
	}

	public final NSArray<EOEvaluationPeriode> getPeriodeArray() {
		return periodeArray;
	}

	public final void setPeriodeArray(NSArray<EOEvaluationPeriode> periodeArray) {
		this.periodeArray = periodeArray;
	}
	
	
}
