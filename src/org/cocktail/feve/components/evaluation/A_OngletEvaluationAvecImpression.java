package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.app.print.FevePdfBoxCtrl;
import org.cocktail.feve.app.print.PrintConsts;
import org.cocktail.feve.app.print.PrintEvaluation;
import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Onglet affichant un composant permettant d'imprimer
 * le CR d'entretien
 * 
 * @author ctarade
 */
public abstract class A_OngletEvaluationAvecImpression
	extends FeveWebComponent {
	
	// binding
	private EOEvaluation inEvaluation;

	public boolean canUpdateEvaluation;
	
	public A_OngletEvaluationAvecImpression(WOContext context) {
		super(context);
	}
	 
  // IMPRESSIONS

  /**
   *  indique s'il faut imprimer une edition vierge ou pas
   */
  public abstract boolean isEmptyEvaluation();
  
  /**
   * Impression de la fiche d'evaluation
   */
  public class PdfBoxEvaluationCtrl extends FevePdfBoxCtrl {

		public PdfBoxEvaluationCtrl(Class<PrintEvaluation> aGenericSixPrintClass, Session aSession) {
			super(aGenericSixPrintClass, aSession);
		}

		public NSDictionary buildDico() {
	  	NSMutableDictionary dico = new NSMutableDictionary();
	  	dico.setObjectForKey(new Boolean(isEmptyEvaluation()), "isEmptyEvaluation");
	  	if (isEmptyEvaluation()) {
	  		dico.setObjectForKey(PrintConsts.ENDING_MESSAGE_FICHE_EVALUATION_VIERGE, PrintConsts.DICO_KEY_ENDING_MESSAGE);
	  	} else {
	  		dico.setObjectForKey(PrintConsts.ENDING_MESSAGE_FICHE_EVALUATION_NON_VIERGE, PrintConsts.DICO_KEY_ENDING_MESSAGE);
	  	}
	  	dico.setObjectForKey(inEvaluation, "evaluation");
	  	return dico;
		}
		
		public String fileName() {
			return StringCtrl.toBasicString("CompteRendu"+
					(isEmptyEvaluation() ? "Brouillon" : "") +"_" +
					inEvaluation.toIndividu().nomPrenom() + "_" +
					inEvaluation.toEvaluationPeriode().strAnneeDebutAnneeFin());
		}
  }
  
  /** Evaluation remplie */
  public PdfBoxEvaluationCtrl ctrlEvaluation() {
  	return new PdfBoxEvaluationCtrl(PrintEvaluation.class, session);
  }

	public final EOEvaluation getInEvaluation() {
		return inEvaluation;
	}

  
  //FIXME copier collé de PageEvaluation => a fusionner
  

  public void setInEvaluation(EOEvaluation value) {
		inEvaluation = value;

  	//
    canUpdateEvaluation = false;
    if (inEvaluation != null) {
    	canUpdateEvaluation = inEvaluation.isModifiable(feveUserInfo());
    };
  }
  
  /**
   * une evaluation est modifiable si la personne connectee a les droits
   * - la personne a le droit
   * - le visa du RH n'est pas apposé
   * @return
   */
  public boolean isModifiable() {
  	boolean isModifiable = false;

  	if (inEvaluation != null &&
  			canUpdateEvaluation &&
  			inEvaluation.isViseParResponsableRh() == false) {
  		isModifiable = true;
  	}
  	
  	return isModifiable;
  }
    
  /**
   * les sous elements de l'évaluation sont verrouilles si :
   * - la personne n'a pas les droits
   * - la coche VR est presente
   * @return
   */
  public boolean isNotModifiable() {
  	return !isModifiable();
  }
}
