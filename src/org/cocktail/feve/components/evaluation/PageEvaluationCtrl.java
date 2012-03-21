package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.eos.modele.mangue.A_EOEvaluationKeyValueCoding;

/**
 * Controleur du composant {@link PageEvaluation}
 *
 * @author ctarade
 *
 */
public class PageEvaluationCtrl 
	extends A_ComponentControlerAndFilArianeNode {

	private A_EOEvaluationKeyValueCoding evaluation;
	
	/**
	 * @deprecated
	 * @see #PageEvaluationCtrl(Session, A_EOEvaluationKeyValueCoding)
	 * @param session
	 */
	public PageEvaluationCtrl(Session session) {
		super(session);
	}
	
	/**
	 * 
	 * @param session
	 * @param anEvaluation
	 */
	public PageEvaluationCtrl(Session session, A_EOEvaluationKeyValueCoding anEvaluation) {
		super(session);
		evaluation = anEvaluation;
	}
	

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return null;
	}

	@Override
	protected void toLocalFullComponent() {
		
	}

}
