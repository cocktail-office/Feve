package org.cocktail.feve.eos.modele.mangue;

/**
 * pointe vers un {@link EOEvaluation}
 * 
 * @author ctarade
 */
public interface I_ToEvaluation {

	// les classements possibles
	public final static String SORT_EVALUATEUR 							= "toEvaluation.toEvaluateur.nomPrenom";
	public final static String SORT_AGENT 									= "toEvaluation.toIndividu.nomPrenom";
	public final static String SORT_DATE_ENTRETIEN 					= "toEvaluation.dTenueEntretien";
	public final static String SORT_DATE_VISA_RH 						= "toEvaluation.dVisaResponsableRh";
	public EOEvaluation toEvaluation();

}
