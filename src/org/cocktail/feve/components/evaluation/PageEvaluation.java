package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.components.common.A_FeveSubMenuEOTplOngletPage;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

/**
 * TODO migrer vers la gestion des controleurs
 * 
 * @author ctarade
 * 
 */
public class PageEvaluation
		extends A_FeveSubMenuEOTplOngletPage {

	// variables entrantes
	public EOEvaluation inputLEvaluation;

	// cache du menu
	private NSArray<EOTplOnglet> eoTplOngletArray;

	/**
	 * indique si l'utilisateur connecte peut modifier l'evaluation
	 * <code>inputLEvaluation</code>
	 */
	private boolean canUpdateEvaluation;

	public PageEvaluation(WOContext context) {
		super(context);
		razCache();
	}

	@Override
	protected void razCache() {
		super.razCache();
		eoTplOngletArray = null;
	}

	// gestion du menu

	@Override
	public NSArray<EOTplOnglet> getEoTplOngletArray() {
		if (eoTplOngletArray == null) {
			eoTplOngletArray = session.getEoTplFicheEvaluation().tosOnglet(inputLEvaluation.toEvaluationPeriode(), inputLEvaluation);
		}
		return eoTplOngletArray;
	}

	public void setInputLEvaluation(EOEvaluation value) {
		// raz du cache si changement d'entree
		if (value != inputLEvaluation) {
			razCache();
		}
		//
		inputLEvaluation = value;
		//
		boolean newCanUpdateEvaluation = false;
		if (inputLEvaluation != null) {
			newCanUpdateEvaluation = inputLEvaluation.isModifiable(feveUserInfo());
		}
		setCanUpdateEvaluation(newCanUpdateEvaluation);
	}

	/**
	 * une evaluation est modifiable si la personne connectee a les droits - la
	 * personne a le droit - le visa du RH n'est pas apposé
	 * 
	 * @return
	 */
	public boolean isModifiable() {
		boolean isModifiable = false;

		if (inputLEvaluation != null &&
				isCanUpdateEvaluation() &&
				inputLEvaluation.isViseParResponsableRh() == false) {
			isModifiable = true;
		}

		return isModifiable;
	}

	/**
	 * les sous elements de l'évaluation sont verrouilles si : - la personne n'a
	 * pas les droits - la coche VR est presente
	 * 
	 * @return
	 */
	public boolean isNotModifiable() {
		return !isModifiable();
	}

	// bindings contenant la periode d'evaluation pour les sous composants
	// contenant les objectifs

	/**
	 * Période associée au binding <code>inputLEvaluation</code>
	 */
	public EOEvaluationPeriode recEvaluationPeriodePrec() {
		EOEvaluationPeriode record = null;
		if (inputLEvaluation != null) {
			record = inputLEvaluation.toEvaluationPeriode();
		}
		return record;
	}

	/**
	 * Période suivante à la periode associée au binding
	 * <code>inputLEvaluation</code>
	 * 
	 * @return
	 */
	public EOEvaluationPeriode recEvaluationPeriodeSuiv() {
		EOEvaluationPeriode record = null;
		if (inputLEvaluation != null) {
			record = inputLEvaluation.toEvaluationPeriode().toNextPeriode();
		}
		return record;
	}

	private final boolean isCanUpdateEvaluation() {
		return canUpdateEvaluation;
	}

	private final void setCanUpdateEvaluation(boolean canUpdateEvaluation) {
		this.canUpdateEvaluation = canUpdateEvaluation;
	}

	/**
	 * On peut modifier les objectifs précédents si le droit est explicitement
	 * donné (cas des nouveaux entrants)
	 * 
	 * @return
	 */
	public final boolean isModificationObjPrecAutorisee() {
		boolean result = false;

		result = inputLEvaluation.isModificationObjPrecAutorisee(session.individuConnecte());

		return result;
	}
}