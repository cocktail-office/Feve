package org.cocktail.feve.components.evaluation;

import com.webobjects.appserver.WOContext;

/**
 * Onglet relatif à l'agent dans l'entretien d'évaluation
 * 
 * @author ctarade
 */
public class OngletEvaluationAgent
		extends A_OngletEvaluationAvecImpression {

	public OngletEvaluationAgent(WOContext context) {
		super(context);
	}

	@Override
	public boolean isEmptyEvaluation() {
		return true;
	}
}