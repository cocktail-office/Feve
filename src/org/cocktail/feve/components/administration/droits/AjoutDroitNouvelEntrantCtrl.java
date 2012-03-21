package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.EODroitNouvelEntrant;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSTimestamp;

public class AjoutDroitNouvelEntrantCtrl
		extends A_ComponentControler {

	/** le controleur appelant */
	private GestionDroitsNouveauxEntrantsCtrl gestionDroitsNouveauxEntrantsCtrl;

	public EODroitNouvelEntrant eoDroitNouvelEntrant;

	public EOEvaluationPeriode eoEvaluationPeriode;
	public EOIndividu eoIndividuResp;
	public EOIndividu eoIndividuEntrant;

	public NSTimestamp dDebut, dFin;

	public boolean isIndividuEntrantPredefini;

	@Deprecated
	public AjoutDroitNouvelEntrantCtrl(Session aSession) {
		super(aSession);
	}

	public AjoutDroitNouvelEntrantCtrl(
			Session aSession, EOIndividu anEoIndividuEntrant, EOEvaluationPeriode anEoEvaluationPeriode, GestionDroitsNouveauxEntrantsCtrl aGestionDroitsNouveauxEntrantsCtrl) {
		super(aSession);
		eoIndividuEntrant = anEoIndividuEntrant;
		isIndividuEntrantPredefini = true;
		eoEvaluationPeriode = anEoEvaluationPeriode;
		// 2 mois à compter d'aujourd'hui par défaut
		dDebut = DateCtrl.now();
		dFin = dDebut.timestampByAddingGregorianUnits(0, 2, 0, 0, 0, 0);
		//
		gestionDroitsNouveauxEntrantsCtrl = aGestionDroitsNouveauxEntrantsCtrl;
	}

	public AjoutDroitNouvelEntrantCtrl(
			Session aSession, EOEvaluationPeriode anEoEvaluationPeriode, GestionDroitsNouveauxEntrantsCtrl aGestionDroitsNouveauxEntrantsCtrl) {
		super(aSession);
		eoIndividuEntrant = null;
		isIndividuEntrantPredefini = false;
		eoEvaluationPeriode = anEoEvaluationPeriode;
		// 2 mois à compter d'aujourd'hui par défaut
		dDebut = DateCtrl.now();
		dFin = dDebut.timestampByAddingGregorianUnits(0, 2, 0, 0, 0, 0);
		//
		gestionDroitsNouveauxEntrantsCtrl = aGestionDroitsNouveauxEntrantsCtrl;
	}

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doAdd() throws Throwable {
		if (eoIndividuResp != null && dDebut != null && dFin != null) {
			eoDroitNouvelEntrant = EODroitNouvelEntrant.createDroitNouvelEntrant(
					ec(), DateCtrl.now(), DateCtrl.now(), dDebut, dFin, eoEvaluationPeriode, eoIndividuEntrant, eoIndividuResp);
			sauvegarde();
			gestionDroitsNouveauxEntrantsCtrl.afterAjouterOk();
		}
		return null;
	}
}
