package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

/**
 * Descriptif d'un objet de type droit
 * @see Droit
 * @see EODroit
 * 
 * @author ctarade
 */
public interface I_Droit {
  
  /** pour des classement alphabetiques */
  public final static String CIBLE_DISPLAY_KEY = "cibleDisplay";

	public EOFicheDePoste toDroitFicheDePoste();
	
	public EOFicheLolf toDroitFicheLolf();
	
	public EOPoste toDroitPoste();
	
	public EOIndividu toDroitIndividu();
	
	public EOVCandidatEvaluation toDroitVCandidatEvaluation();

	public EOStructure toDroitStructure();

	public EOStructure toDroitComposante();

	public EOEvaluationPeriode toDroitEvaluationPeriode();

	public NSArray tosServiceFlatten();
	
	public EOTypeDroitCible toTypeDroitCible();
	
	public EOTypeDroitAcces toTypeDroitAcces();
	
	public String cibleDisplay();
	
  public boolean isDroitGlobal();

  public boolean isDroitComposante();

  public boolean isDroitStructure();

  public boolean isDroitPoste();

  public boolean isDroitFicheDePoste();
  
  public boolean isDroitFicheLolf();
  
  public boolean isDroitIndividu();
  
  public boolean isDroitVCandidatEvaluation();
  
  public EOEditingContext editingContext();
}
