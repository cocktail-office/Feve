package org.cocktail.feve.components.administration.suivi;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

/**
 * Page de suivi des évolutions de carrière
 * 
 * @author ctarade
 */
public class SuiviEvolution
		extends SuiviEvaluation {

	public SuiviEvolution(WOContext context) {
		super(context);
	}

	// edition

	public WOResponse printCsv() {
		return getCsvResponse(false, false, true, true);
	}

}