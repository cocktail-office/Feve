package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.eos.modele.grhum.EOCorps;
import org.cocktail.feve.eos.modele.grhum.EOGrade;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.feve.eos.modele.mangue.I_ToEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.eof.ERXQ;

/**
 * Page de suivi des notices de promotion
 * 
 * @author ctarade
 */
public class SuiviPromotion
		extends SuiviEvaluation {

	private final static String POPULATION_AENES = "AENES";
	private final static String POPULATION_ITRF = "ITRF";
	private final static String POPULATION_BU = "BU";
	private final static NSArray<String> POPULATION_LIST = new NSArray<String>(
			new String[] { POPULATION_AENES, POPULATION_ITRF, POPULATION_BU });

	public NSArray<String> populationList = POPULATION_LIST;
	public String populationItem;
	public String populationSelected;

	//
	private NSArray<EOEvaluationNoticePromotion> promotionList;
	public EOEvaluationNoticePromotion promotionItem;

	// liste des evaluateurs
	private NSArray<EOIndividu> evaluateurArray;
	public EOIndividu evaluateurItem;
	private EOIndividu evaluateurSelected;

	// filtre sur reduction / promotion
	public String reductionEchelonOuiNonSelected;
	public String promotionGradeOuiNonSelected;
	public String promotionCorpsOuiNonSelected;

	// liste des réductions d'échelon favorables
	public NSArray<Integer> reductionEchelonFavorableArray = EOEvaluationNoticePromotion.REDUCTION_ECHELON_FAVORABLE_ARRAY;
	public Integer reductionEchelonFavorableItem;
	public Integer reductionEchelonFavorableSelected;

	// liste des promotion de grade favorables
	public NSArray<Integer> promotionGradeFavorableArray = EOEvaluationNoticePromotion.PROMOTION_GRADE_FAVORABLE_ARRAY;
	public Integer promotionGradeFavorableItem;
	public Integer promotionGradeFavorableSelected;

	// liste des promotion de corps favorables
	public NSArray<Integer> promotionCorpsFavorableArray = EOEvaluationNoticePromotion.PROMOTION_CORPS_FAVORABLE_ARRAY;
	public Integer promotionCorpsFavorableItem;
	public Integer promotionCorpsFavorableSelected;

	public SuiviPromotion(WOContext context) {
		super(context);
	}

	/**
	 * On ne garde que les evaluations ayant une notice de promotion
	 */
	@Override
	public NSArray<EOVCandidatEvaluation> getEvaluationArray() {

		NSArray<EOVCandidatEvaluation> array = super.getEvaluationArray();

		// ne garder que celles qui ont une notice de promotion
		NSMutableArray<EOVCandidatEvaluation> mutable = new NSMutableArray<EOVCandidatEvaluation>();

		for (int i = 0; i < array.count(); i++) {
			EOVCandidatEvaluation item = array.objectAtIndex(i);
			if (item.toEvaluation() != null &&
					item.toEvaluation().tosEvaluationNoticePromotion().count() > 0) {
				mutable.addObject(item);
			}
		}

		array = mutable.immutableClone();

		return array;
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOEvaluationNoticePromotion> getPromotionList() {
		if (promotionList == null) {

			// filtres sur corps et grades

			NSArray<EOVCandidatEvaluation> array = getEvaluationArray();

			boolean isCorpsAFiltrer = (eoCorpsSelected != null);
			boolean isGradeAFiltrer = (eoGradeSelected != null);

			if (isCorpsAFiltrer || isGradeAFiltrer) {

				NSMutableArray<EOVCandidatEvaluation> resultFilteredInMemory = new NSMutableArray<EOVCandidatEvaluation>();

				for (int i = 0; i < array.count(); i++) {

					boolean isCorpsOk = false;
					boolean isGradeOk = false;

					EOVCandidatEvaluation eoItem = array.objectAtIndex(i);

					if (isCorpsAFiltrer) {
						EOCorps eoCorps = eoItem.toIndividu().getCorpsForPeriode(
								periodeSelected.epeDDebut(), periodeSelected.epeDFin());

						if (eoCorpsSelected == eoCorps) {
							isCorpsOk = true;
						}
					}

					if (isGradeAFiltrer) {
						EOGrade eoGrade = eoItem.toIndividu().getGradeForPeriode(
								periodeSelected.epeDDebut(), periodeSelected.epeDFin());

						if (eoGradeSelected == eoGrade) {
							isGradeOk = true;
						}
					}

					if ((!isCorpsAFiltrer || (isCorpsAFiltrer && isCorpsOk)) &&
							(!isGradeAFiltrer || (isGradeAFiltrer && isGradeOk))) {
						resultFilteredInMemory.addObject(eoItem);
					}

				}

				array = resultFilteredInMemory.immutableClone();

			}

			promotionList = (NSArray<EOEvaluationNoticePromotion>) array.valueForKey(
					EOVCandidatEvaluation.TO_EVALUATION_KEY + "." + EOEvaluation.TOS_EVALUATION_NOTICE_PROMOTION_KEY);

			promotionList = NSArrayCtrl.flattenArray(promotionList);

			// filtrage sur la population
			if (populationSelected != null) {
				NSMutableArray<EOEvaluationNoticePromotion> filtered = new NSMutableArray<EOEvaluationNoticePromotion>();
				for (int i = 0; i < promotionList.count(); i++) {
					EOEvaluationNoticePromotion promo = promotionList.objectAtIndex(i);
					if ((populationSelected.equals(POPULATION_AENES) && promo.isAenes()) ||
							(populationSelected.equals(POPULATION_ITRF) && promo.isItrf()) ||
							(populationSelected.equals(POPULATION_BU) && promo.isBu())) {
						filtered.addObject(promo);
					}
				}
				promotionList = filtered.immutableClone();
			}

			// filtrage sur l'evaluateur
			if (getEvaluateurSelected() != null) {
				promotionList = EOQualifier.filteredArrayWithQualifier(
						promotionList,
						ERXQ.equals(EOEvaluationNoticePromotion.TO_EVALUATION_KEY + "." + EOVCandidatEvaluation.TO_EVALUATEUR_KEY, getEvaluateurSelected()));
			}

			// reduction oui / non
			EOQualifier qual = null;
			if (isReductionEchelonOuiSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_REDUCTION_ECHELON_OUI_KEY);
			} else if (isReductionEchelonNonSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_REDUCTION_ECHELON_NON_KEY);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// promotion de grade oui / non
			qual = null;
			if (isPromotionGradeOuiSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_PROMOTION_GRADE_OUI_KEY);
			} else if (isPromotionGradeNonSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_PROMOTION_GRADE_NON_KEY);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// promotion de corps oui / non
			qual = null;
			if (isPromotionCorpsOuiSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_PROMOTION_CORPS_OUI_KEY);
			} else if (isPromotionCorpsNonSelected()) {
				qual = ERXQ.isTrue(EOEvaluationNoticePromotion.IS_PROMOTION_CORPS_NON_KEY);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// filtre sur la réduction d'échelon favorable
			qual = null;
			if (isReductionEchelonOuiSelected() &&
					reductionEchelonFavorableSelected != null) {
				qual = ERXQ.equals(
						EOEvaluationNoticePromotion.ENP_REDUCTION_ECHELON_KEY, reductionEchelonFavorableSelected);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// filtre sur la promotion de grade favorable
			qual = null;
			if (isPromotionGradeOuiSelected() &&
					promotionGradeFavorableSelected != null) {
				qual = ERXQ.equals(
						EOEvaluationNoticePromotion.ENP_PROMOTION_GRADE_KEY, promotionGradeFavorableSelected);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// filtre sur la promotion de grade favorable
			qual = null;
			if (isPromotionCorpsOuiSelected() &&
					promotionCorpsFavorableSelected != null) {
				qual = ERXQ.equals(
						EOEvaluationNoticePromotion.ENP_PROMOTION_CORPS_KEY, promotionCorpsFavorableSelected);
			}
			promotionList = EOQualifier.filteredArrayWithQualifier(promotionList, qual);

			// classement
			if (getSortStringSelected() != null) {
				promotionList = (NSArray<EOEvaluationNoticePromotion>) CktlSort.sortedArray(
						promotionList, getSortStringSelected(), getSortOrder());
			}

		}

		return promotionList;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isReductionEchelonOuiSelected() {
		boolean isReductionEchelonOuiSelected = false;

		if (!StringCtrl.isEmpty(reductionEchelonOuiNonSelected) &&
				reductionEchelonOuiNonSelected.equals(STR_OUI)) {
			isReductionEchelonOuiSelected = true;
		}

		return isReductionEchelonOuiSelected;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isReductionEchelonNonSelected() {
		boolean isReductionEchelonNonSelected = false;

		if (!StringCtrl.isEmpty(reductionEchelonOuiNonSelected) &&
				reductionEchelonOuiNonSelected.equals(STR_NON)) {
			isReductionEchelonNonSelected = true;
		}

		return isReductionEchelonNonSelected;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionGradeOuiSelected() {
		boolean isPromotionGradeOuiSelected = false;

		if (!StringCtrl.isEmpty(promotionGradeOuiNonSelected) &&
				promotionGradeOuiNonSelected.equals(STR_OUI)) {
			isPromotionGradeOuiSelected = true;
		}

		return isPromotionGradeOuiSelected;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionGradeNonSelected() {
		boolean isPromotionGradeNonSelected = false;

		if (!StringCtrl.isEmpty(promotionGradeOuiNonSelected) &&
				promotionGradeOuiNonSelected.equals(STR_NON)) {
			isPromotionGradeNonSelected = true;
		}

		return isPromotionGradeNonSelected;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionCorpsOuiSelected() {
		boolean isPromotionCorpsOuiSelected = false;

		if (!StringCtrl.isEmpty(promotionCorpsOuiNonSelected) &&
				promotionCorpsOuiNonSelected.equals(STR_OUI)) {
			isPromotionCorpsOuiSelected = true;
		}

		return isPromotionCorpsOuiSelected;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPromotionCorpsNonSelected() {
		boolean isPromotionCorpsNonSelected = false;

		if (!StringCtrl.isEmpty(promotionCorpsOuiNonSelected) &&
				promotionCorpsOuiNonSelected.equals(STR_NON)) {
			isPromotionCorpsNonSelected = true;
		}

		return isPromotionCorpsNonSelected;
	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOIndividu> getEvaluateurArray() {
		if (evaluateurArray == null) {
			evaluateurArray = NSArrayCtrl.removeDuplicate(
					(NSArray) getEvaluationArray().valueForKey(EOVCandidatEvaluation.TO_EVALUATEUR_KEY));
			// suppression du NSKeyValueCoding.NullValue representant le N+1 du "roi"
			NSMutableArray evaluateurListMutable = new NSMutableArray(evaluateurArray);
			evaluateurListMutable.removeIdenticalObject(NSKeyValueCoding.NullValue);
			evaluateurArray = evaluateurListMutable.immutableClone();
			// classement par le nom
			evaluateurArray = CktlSort.sortedArray(evaluateurArray, EOIndividu.NOM_PRENOM_KEY);
		}
		return evaluateurArray;
	}

	public final EOIndividu getEvaluateurSelected() {
		return evaluateurSelected;
	}

	public final void setEvaluateurSelected(EOIndividu evaluateurSelected) {
		this.evaluateurSelected = evaluateurSelected;
		// forcer le rechargement de la liste des evaluations
		resetEvaluationArray();
		resetPromotionArray();
	}

	@Override
	public void setServiceSelected(EOVService value) {
		super.setServiceSelected(value);
		resetPromotionArray();
	}

	@Override
	public void setPeriodeSelected(EOEvaluationPeriode value) {
		super.setPeriodeSelected(value);
		resetPromotionArray();
	}

	@Override
	public void setEntretienTenuOuiNonSelected(String entretienTenuOuiNonSelected) {
		super.setEntretienTenuOuiNonSelected(entretienTenuOuiNonSelected);
		resetPromotionArray();
	}

	@Override
	public void setVisaRhOuiNonSelected(String visaRhOuiNonSelected) {
		super.setVisaRhOuiNonSelected(visaRhOuiNonSelected);
		resetPromotionArray();
	}

	public final void setPopulationSelected(String populationSelected) {
		this.populationSelected = populationSelected;
		resetPromotionArray();
	}

	@Override
	public void setEoCorpsSelected(EOCorps eoCorpsSelected) {
		super.setEoCorpsSelected(eoCorpsSelected);
		resetPromotionArray();
	}

	@Override
	public void setEoGradeSelected(EOGrade eoGradeSelected) {
		super.setEoGradeSelected(eoGradeSelected);
		resetPromotionArray();
	}

	private void resetPromotionArray() {
		promotionList = null;
	}

	// classement

	public void sortEvaluateur() {
		faireClassement(I_ToEvaluation.SORT_EVALUATEUR);
	}

	public void sortAgent() {
		faireClassement(I_ToEvaluation.SORT_AGENT);
	}

	@Override
	public void doApresClassement() {
		resetPromotionArray();
	}

	/**
	 * @param reductionEchelonOuiNonSelected
	 *          the reductionEchelonOuiNonSelected to set
	 */
	public final void setReductionEchelonOuiNonSelected(String reductionEchelonOuiNonSelected) {
		this.reductionEchelonOuiNonSelected = reductionEchelonOuiNonSelected;
		resetPromotionArray();
	}

	/**
	 * @param promotionGradeOuiNonSelected
	 *          the promotionGradeOuiNonSelected to set
	 */
	public final void setPromotionGradeOuiNonSelected(String promotionGradeOuiNonSelected) {
		this.promotionGradeOuiNonSelected = promotionGradeOuiNonSelected;
		resetPromotionArray();
	}

	/**
	 * @param promotionCorpsOuiNonSelected
	 *          the promotionCorpsOuiNonSelected to set
	 */
	public final void setPromotionCorpsOuiNonSelected(String promotionCorpsOuiNonSelected) {
		this.promotionCorpsOuiNonSelected = promotionCorpsOuiNonSelected;
		resetPromotionArray();
	}

	// edition

	public WOResponse printCsv() {
		CktlDataResponse resp = new CktlDataResponse();
		StringBuffer sb = new StringBuffer();
		sb.append("Evaluateur").append(CSV_COLUMN_SEPARATOR);
		sb.append("Agent").append(CSV_COLUMN_SEPARATOR);
		sb.append("Filière").append(CSV_COLUMN_SEPARATOR);
		sb.append("Corps").append(CSV_COLUMN_SEPARATOR);
		sb.append("Grade").append(CSV_COLUMN_SEPARATOR);
		sb.append("Réduction d'échelon").append(CSV_COLUMN_SEPARATOR);
		sb.append("Motif si défavorable").append(CSV_COLUMN_SEPARATOR);
		sb.append("Promotion de grade").append(CSV_COLUMN_SEPARATOR);
		sb.append("Motif si défavorable").append(CSV_COLUMN_SEPARATOR);
		sb.append("Promotion de corps").append(CSV_COLUMN_SEPARATOR);
		sb.append("Motif si défavorable").append(CSV_COLUMN_SEPARATOR);
		sb.append("Appréciation").append(CSV_COLUMN_SEPARATOR);
		sb.append(CSV_NEW_LINE);

		for (int i = 0; i < getPromotionList().count(); i++) {
			EOEvaluationNoticePromotion promotion = (EOEvaluationNoticePromotion) getPromotionList().objectAtIndex(i);
			String nomPrenomEvaluateur = "inconnu";
			if (promotion.toEvaluation().toEvaluateur() != null) {
				nomPrenomEvaluateur = promotion.toEvaluation().toEvaluateur().nomPrenom();
			}
			sb.append(nomPrenomEvaluateur).append(CSV_COLUMN_SEPARATOR);
			sb.append(promotion.toEvaluation().toIndividu().nomPrenom()).append(CSV_COLUMN_SEPARATOR);

			sb.append(promotion.toEvaluation().getLibellePopulation()).append(CSV_COLUMN_SEPARATOR);

			String strCorps = " ";
			if (!StringCtrl.isEmpty((String) promotion.toEvaluation().getDicoAgent().objectForKey("corps"))) {
				strCorps = (String) promotion.toEvaluation().getDicoAgent().objectForKey("corps");
			}
			sb.append(strCorps).append(CSV_COLUMN_SEPARATOR);

			String strGrade = " ";
			if (!StringCtrl.isEmpty((String) promotion.toEvaluation().getDicoAgent().objectForKey("grade"))) {
				strGrade = (String) promotion.toEvaluation().getDicoAgent().objectForKey("grade");
			}
			sb.append(strGrade).append(CSV_COLUMN_SEPARATOR);

			String strReductionEchelon = " ";
			if (promotion.enpReductionEchelon() != null) {
				strReductionEchelon = promotion.enpReductionEchelonLibelle();
			}
			sb.append(strReductionEchelon).append(CSV_COLUMN_SEPARATOR);
			String strReductionEchelonMotif = "";
			if (!StringCtrl.isEmpty(promotion.enpReductionEchelonRefusMotif())) {
				strReductionEchelonMotif = StringCtrl.replace(promotion.enpReductionEchelonRefusMotif(), "\n", " ");
				strReductionEchelonMotif = StringCtrl.replace(strReductionEchelonMotif, "\r", " ");
			}
			sb.append(strReductionEchelonMotif).append(CSV_COLUMN_SEPARATOR);

			String strPromotionGrade = " ";
			if (promotion.enpPromotionGrade() != null) {
				strPromotionGrade = promotion.enpPromotionGradeLibelle();
			}
			sb.append(strPromotionGrade).append(CSV_COLUMN_SEPARATOR);
			String strPromotionGradeMotif = "";
			if (!StringCtrl.isEmpty(promotion.enpPromotionGradeRefusMotif())) {
				strPromotionGradeMotif = StringCtrl.replace(promotion.enpPromotionGradeRefusMotif(), "\n", " ");
				strPromotionGradeMotif = StringCtrl.replace(strPromotionGradeMotif, "\r", " ");
			}
			sb.append(strPromotionGradeMotif).append(CSV_COLUMN_SEPARATOR);

			String strPromotionCorps = " ";
			if (promotion.enpPromotionCorps() != null) {
				strPromotionCorps = promotion.enpPromotionCorpsLibelle();
			}
			sb.append(strPromotionCorps).append(CSV_COLUMN_SEPARATOR);
			String strPromotionCorpsMotif = "";
			if (!StringCtrl.isEmpty(promotion.enpPromotionCorpsRefusMotif())) {
				strPromotionCorpsMotif = StringCtrl.replace(promotion.enpPromotionCorpsRefusMotif(), "\n", " ");
				strPromotionCorpsMotif = StringCtrl.replace(strPromotionCorpsMotif, "\r", " ");
			}
			sb.append(strPromotionCorpsMotif).append(CSV_COLUMN_SEPARATOR);

			String strAppreciationGenerale = "";
			if (!StringCtrl.isEmpty(promotion.enpAppreciationGenerale())) {
				strAppreciationGenerale = StringCtrl.replace(promotion.enpAppreciationGenerale(), "\n", " ");
				strAppreciationGenerale = StringCtrl.replace(strAppreciationGenerale, "\r", " ");
			}
			sb.append(strAppreciationGenerale).append(CSV_COLUMN_SEPARATOR);

			sb.append(CSV_NEW_LINE);
		}
		NSData stream = new NSData(sb.toString(), CSV_ENCODING);
		resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);
		resp.setHeader(String.valueOf(stream.length()), "Content-Length");
		resp.setFileName("suivi_promotion.csv");
		return resp;
	}

	/**
	 * 
	 * @return
	 */
	public String reductionEchelonFavorableItemDisplayString() {
		return EOEvaluationNoticePromotion.libelleReductionEchelonFavorable(reductionEchelonFavorableItem);
	}

	/**
	 * 
	 * @return
	 */
	public String promotionGradeFavorableItemDisplayString() {
		return EOEvaluationNoticePromotion.libellePromotionGrade(promotionGradeFavorableItem);
	}

	/**
	 * 
	 * @return
	 */
	public String promotionCorpsFavorableItemDisplayString() {
		return EOEvaluationNoticePromotion.libellePromotionCorps(promotionCorpsFavorableItem);
	}

	public final void setReductionEchelonFavorableSelected(Integer reductionEchelonFavorableSelected) {
		this.reductionEchelonFavorableSelected = reductionEchelonFavorableSelected;
		resetPromotionArray();
	}

	public final void setPromotionGradeFavorableSelected(Integer promotionGradeFavorableSelected) {
		this.promotionGradeFavorableSelected = promotionGradeFavorableSelected;
		resetPromotionArray();
	}

	public final void setPromotionCorpsFavorableSelected(Integer promotionCorpsFavorableSelected) {
		this.promotionCorpsFavorableSelected = promotionCorpsFavorableSelected;
		resetPromotionArray();
	}

}