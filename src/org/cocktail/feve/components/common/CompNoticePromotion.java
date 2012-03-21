package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

/**
 * Gestion de la notice de promotion selon un template ( <code>EOTplBloc</code>
 * ).
 * 
 * Le points d'entree est <code>EOEvaluation</code>
 * 
 * @author ctarade
 */
public class CompNoticePromotion
		extends FeveWebComponent {

	// binding de template d'onglet
	public EOTplBloc inTplBloc;
	// binding de l'evaluation ou la fiche
	public EOEvaluation inFiche;
	// binding disabled
	public boolean inDisabled;

	private NSArray<Integer> reductionEchelonArray;

	public NSArray<Integer> promotionGradeArray = EOEvaluationNoticePromotion.PROMOTION_GRADE_ARRAY;
	public NSArray<Integer> promotionCorpsArray = EOEvaluationNoticePromotion.PROMOTION_CORPS_ARRAY;

	public Integer item;

	//
	private EOEvaluationNoticePromotion eoEvaluationNoticePromotion;

	//
	public boolean isTextAppreciationGeneraleEnCoursDeModif;

	public CompNoticePromotion(WOContext context) {
		super(context);
	}

	/**
	 * La liste des items liés au corps de l'agent
	 * 
	 * @return
	 */
	public NSArray<Integer> getReductionEchelonArray() {
		if (reductionEchelonArray == null) {
			if (inFiche.isAenes()) {
				reductionEchelonArray = EOEvaluationNoticePromotion.AENES_REDUCTION_ECHELON_ARRAY;
			} else if (inFiche.isItrf()) {
				reductionEchelonArray = EOEvaluationNoticePromotion.ITRF_REDUCTION_ECHELON_ARRAY;
			} else if (inFiche.isBu()) {
				reductionEchelonArray = EOEvaluationNoticePromotion.BU_REDUCTION_ECHELON_ARRAY;
			}
		}
		return reductionEchelonArray;
	}

	/**
	 * 
	 * @return
	 */
	public final String getLibelleReductionEchelonItem() {
		String libelle = null;

		if (inFiche.isAenes()) {
			libelle = EOEvaluationNoticePromotion.libelleAenesReductionEchelon(item);
		} else if (inFiche.isItrf()) {
			libelle = EOEvaluationNoticePromotion.libelleItrfReductionEchelon(item);
		} else if (inFiche.isBu()) {
			libelle = EOEvaluationNoticePromotion.libelleBuReductionEchelon(item);
		}

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isReductionEchelonAMotiver() {
		boolean isAMotiver = false;

		isAMotiver = EOEvaluationNoticePromotion.isAMotiver(getEoEvaluationNoticePromotion().enpReductionEchelon());

		return isAMotiver;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionGradeAMotiver() {
		boolean isAMotiver = false;

		isAMotiver = EOEvaluationNoticePromotion.isAMotiver(getEoEvaluationNoticePromotion().enpPromotionGrade());

		return isAMotiver;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionCorpsAMotiver() {
		boolean isAMotiver = false;

		isAMotiver = EOEvaluationNoticePromotion.isAMotiver(getEoEvaluationNoticePromotion().enpPromotionCorps());

		return isAMotiver;
	}

	/**
	 * 
	 * @return
	 */
	public String libellePromotionGradeItem() {
		String libelle = null;

		libelle = EOEvaluationNoticePromotion.libellePromotionGrade(item);

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public String libellePromotionCorpsItem() {
		String libelle = null;

		libelle = EOEvaluationNoticePromotion.libellePromotionCorps(item);

		return libelle;
	}

	/**
	 * Création de l'objet en base de données si nécéssaire
	 * 
	 * @return
	 * @throws Throwable
	 */
	public final EOEvaluationNoticePromotion getEoEvaluationNoticePromotion() {
		if (eoEvaluationNoticePromotion == null) {
			if (inFiche.tosEvaluationNoticePromotion().count() > 0) {
				eoEvaluationNoticePromotion = (EOEvaluationNoticePromotion) inFiche.tosEvaluationNoticePromotion().objectAtIndex(0);
			} else {
				eoEvaluationNoticePromotion = EOEvaluationNoticePromotion.createEvaluationNoticePromotion(
						edc(), inFiche);
				try {
					sauvegarde();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return eoEvaluationNoticePromotion;
	}

	public final void setEoEvaluationNoticePromotion(EOEvaluationNoticePromotion eoEvaluationNoticePromotion) {
		this.eoEvaluationNoticePromotion = eoEvaluationNoticePromotion;
	}

	/**
	 * Raz des variables locales lorsqu'on change de fiche
	 * 
	 * @param inFiche
	 */
	public final void setInFiche(EOEvaluation inFiche) {
		EOEvaluation prevFiche = this.inFiche;
		this.inFiche = inFiche;
		if (prevFiche != inFiche) {
			setEoEvaluationNoticePromotion(null);
			reductionEchelonArray = null;
		}
	}

	/**
	 * Action de récopier l'appréciation générale de l'entretien dans celle de la
	 * notice
	 * 
	 * @return
	 */
	public WOComponent doRecupererAppreciationGeneraleEntretien() {
		getEoEvaluationNoticePromotion().recupererAppreciationGeneraleEntretien();
		return null;
		// sauvegarde();
	}
}