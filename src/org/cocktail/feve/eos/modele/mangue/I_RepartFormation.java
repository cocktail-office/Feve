package org.cocktail.feve.eos.modele.mangue;

/**
 * Descriptif d'une repartition entre une évaluation et une formation.
 * Interface crée pour gérer la migration des demandes de formations de 
 * la classe {@link EORepartFicheItem} et la classe {@link EORepartFormationSouhaitee}
 * @author ctarade
 *
 */
public interface I_RepartFormation {

	// les classements possibles
	public final static String SORT_EVALUATEUR 							= "toEvaluation.toEvaluateur.nomPrenom";
	public final static String SORT_AGENT 									= "toEvaluation.toIndividu.nomPrenom";
	public final static String SORT_FORMATION_SOUHAITEE 		= "libelleFormation";
	public final static String SORT_IS_NOMENCLATURE					= "isNomenclature";
	public final static String SORT_DATE_ENTRETIEN 					= "toEvaluation.dTenueEntretien";
	public final static String SORT_DATE_VISA_RH 						= "toEvaluation.dVisaResponsableRh";
	
	public String libelleFormation();
	
	public EOEvaluation toEvaluation();
	
	public boolean isNomenclature();
	
}
