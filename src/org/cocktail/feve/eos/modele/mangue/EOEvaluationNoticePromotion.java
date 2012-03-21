/*
 * Copyright Cocktail, 2001-2008 
 * 
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use, 
 * modify and/or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and, more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOEvaluationNoticePromotion
		extends _EOEvaluationNoticePromotion
		implements I_ToEvaluation {

	public EOEvaluationNoticePromotion() {
		super();
	}

	public void validateForInsert() throws NSValidation.ValidationException {
		this.validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForInsert();
	}

	public void validateForUpdate() throws NSValidation.ValidationException {
		this.validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForUpdate();
	}

	public void validateForDelete() throws NSValidation.ValidationException {
		super.validateForDelete();
	}

	/**
	 * Apparemment cette methode n'est pas appelée.
	 * 
	 * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
	 */
	public void validateForSave() throws NSValidation.ValidationException {
		validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForSave();

	}

	/**
	 * Peut etre appele à partir des factories.
	 * 
	 * @throws NSValidation.ValidationException
	 */
	public void validateObjectMetier() throws NSValidation.ValidationException {

		// tout refus à motiver ne doit pas être vide
		if (enpReductionEchelon() != null &&
				isAMotiver(enpReductionEchelon()) &&
				StringCtrl.isEmpty(enpReductionEchelonRefusMotif())) {
			throw new NSValidation.ValidationException(
					"Vous devez motiver l'avis défavorable (réduction du temps moyen d'échelon)");
		}

		if (enpPromotionGrade() != null &&
				isAMotiver(enpPromotionGrade())
				&& StringCtrl.isEmpty(enpPromotionGradeRefusMotif())) {
			throw new NSValidation.ValidationException(
					"Vous devez motiver l'avis défavorable (promotion de grade)");
		}

		if (enpPromotionCorps() != null &&
				isAMotiver(enpPromotionCorps()) &&
				StringCtrl.isEmpty(enpPromotionCorpsRefusMotif())) {
			throw new NSValidation.ValidationException(
					"Vous devez motiver l'avis défavorable (promotion de corps)");
		}

	}

	/**
	 * A appeler par les validateforsave, forinsert, forupdate.
	 * 
	 */
	private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

	}

	// ajouts

	private final static Integer ZERO = new Integer(0);
	private final static Integer UN = new Integer(1);
	private final static Integer DEUX = new Integer(2);
	private final static Integer TROIS = new Integer(3);

	private final static Integer REDUCTION_ECHELON_NIVEAU_1 = UN;
	private final static Integer REDUCTION_ECHELON_NIVEAU_2 = DEUX;
	private final static Integer REDUCTION_ECHELON_NIVEAU_3 = TROIS;

	public final static Integer DEFAVORABLE = ZERO;

	private final static String REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_POPULATION_INCONNUE_DEFAULT = "<avis défavorable à démotiver>";

	public final static Integer AENES_REDUCTION_ECHELON_DEFAVORABLE = DEFAVORABLE;
	public final static Integer AENES_REDUCTION_ECHELON_FAVORABLE_1_MOIS = REDUCTION_ECHELON_NIVEAU_1;
	public final static Integer AENES_REDUCTION_ECHELON_FAVORABLE_2_MOIS = REDUCTION_ECHELON_NIVEAU_2;
	public final static Integer AENES_REDUCTION_ECHELON_TRES_FAVORABLE_3_MOIS = REDUCTION_ECHELON_NIVEAU_3;

	private final static String AENES_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE = "Défavorable";
	private final static String AENES_REDUCTION_ECHELON_FAVORABLE_1_MOIS_LIBELLE = "Favorable (réduction d'1 mois)";
	private final static String AENES_REDUCTION_ECHELON_FAVORABLE_2_MOIS_LIBELLE = "Favorable (réduction de 2 mois)";
	private final static String AENES_REDUCTION_ECHELON_TRES_FAVORABLE_3_MOIS_LIBELLE = "Très favorable (réduction de 3 mois)";

	private final static String AENES_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT = "<avis défavorable à motiver - souhaitez-vous une majoration d'ancienneté>";

	public final static NSArray<Integer> AENES_REDUCTION_ECHELON_ARRAY = new NSArray<Integer>(
			new Integer[] {
					AENES_REDUCTION_ECHELON_TRES_FAVORABLE_3_MOIS,
					AENES_REDUCTION_ECHELON_FAVORABLE_2_MOIS,
					AENES_REDUCTION_ECHELON_FAVORABLE_1_MOIS,
					AENES_REDUCTION_ECHELON_DEFAVORABLE });

	public final static Integer ITRF_REDUCTION_ECHELON_DEFAVORABLE = DEFAVORABLE;
	public final static Integer ITRF_REDUCTION_ECHELON_FAVORABLE = REDUCTION_ECHELON_NIVEAU_1;

	private final static String ITRF_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE = "Défavorable";
	private final static String ITRF_REDUCTION_ECHELON_FAVORABLE_LIBELLE = "Favorable";

	private final static String ITRF_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT = "<avis défavorable à motiver>";

	public final static NSArray<Integer> ITRF_REDUCTION_ECHELON_ARRAY = new NSArray<Integer>(
			new Integer[] {
					ITRF_REDUCTION_ECHELON_FAVORABLE,
					ITRF_REDUCTION_ECHELON_DEFAVORABLE });

	public final static Integer BU_REDUCTION_ECHELON_DEFAVORABLE = DEFAVORABLE;
	public final static Integer BU_REDUCTION_ECHELON_FAVORABLE = REDUCTION_ECHELON_NIVEAU_1;

	private final static String BU_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE = "Défavorable";
	private final static String BU_REDUCTION_ECHELON_FAVORABLE_LIBELLE = "Favorable";

	private final static String BU_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT = "<avis défavorable à motiver>";

	public final static NSArray<Integer> BU_REDUCTION_ECHELON_ARRAY = new NSArray<Integer>(
			new Integer[] {
					BU_REDUCTION_ECHELON_FAVORABLE,
					BU_REDUCTION_ECHELON_DEFAVORABLE });

	public final static Integer PROMOTION_GRADE_DEFAVORABLE = DEFAVORABLE;
	public final static Integer PROMOTION_GRADE_FAVORABLE = UN;
	public final static Integer PROMOTION_GRADE_TRES_FAVORABLE = DEUX;

	private final static String PROMOTION_GRADE_DEFAVORABLE_LIBELLE = "Défavorable";
	private final static String PROMOTION_GRADE_FAVORABLE_LIBELLE = "Favorable";
	private final static String PROMOTION_GRADE_TRES_FAVORABLE_LIBELLE = "Très favorable";

	private final static String PROMOTION_GRADE_DEFAVORABLE_MOTIF_REFUS_DEFAULT = "<avis défavorable à motiver>";

	public final static NSArray<Integer> PROMOTION_GRADE_ARRAY = new NSArray<Integer>(
			new Integer[] {
					PROMOTION_GRADE_TRES_FAVORABLE,
					PROMOTION_GRADE_FAVORABLE,
					PROMOTION_GRADE_DEFAVORABLE });

	public final static Integer PROMOTION_CORPS_DEFAVORABLE = DEFAVORABLE;
	public final static Integer PROMOTION_CORPS_FAVORABLE = UN;
	public final static Integer PROMOTION_CORPS_TRES_FAVORABLE = DEUX;

	private final static String PROMOTION_CORPS_DEFAVORABLE_LIBELLE = "Défavorable";
	private final static String PROMOTION_CORPS_FAVORABLE_LIBELLE = "Favorable";
	private final static String PROMOTION_CORPS_TRES_FAVORABLE_LIBELLE = "Très favorable";

	private final static String PROMOTION_CORPS_DEFAVORABLE_MOTIF_REFUS_DEFAULT = "<avis défavorable à motiver>";

	public final static NSArray<Integer> PROMOTION_CORPS_ARRAY = new NSArray<Integer>(
			new Integer[] {
					PROMOTION_CORPS_TRES_FAVORABLE,
					PROMOTION_CORPS_FAVORABLE,
					PROMOTION_CORPS_DEFAVORABLE });

	public final static NSArray<Integer> REDUCTION_ECHELON_FAVORABLE_ARRAY = new NSArray<Integer>(
			new Integer[] { REDUCTION_ECHELON_NIVEAU_1, REDUCTION_ECHELON_NIVEAU_2, REDUCTION_ECHELON_NIVEAU_3 });

	public final static NSArray<Integer> PROMOTION_GRADE_FAVORABLE_ARRAY = new NSArray<Integer>(
			new Integer[] { PROMOTION_GRADE_FAVORABLE, PROMOTION_GRADE_TRES_FAVORABLE });

	public final static NSArray<Integer> PROMOTION_CORPS_FAVORABLE_ARRAY = new NSArray<Integer>(
			new Integer[] { PROMOTION_CORPS_FAVORABLE, PROMOTION_CORPS_TRES_FAVORABLE });

	public final static String IS_REDUCTION_ECHELON_OUI_KEY = "isReductionEchelonOui";
	public final static String IS_REDUCTION_ECHELON_NON_KEY = "isReductionEchelonNon";
	public final static String IS_PROMOTION_GRADE_OUI_KEY = "isPromotionGradeOui";
	public final static String IS_PROMOTION_GRADE_NON_KEY = "isPromotionGradeNon";
	public final static String IS_PROMOTION_CORPS_OUI_KEY = "isPromotionCorpsOui";
	public final static String IS_PROMOTION_CORPS_NON_KEY = "isPromotionCorpsNon";

	/**
	 * Donne le libellé associé à un code sur une réduction d'échelon AENES
	 * 
	 * @param value
	 * @return
	 */
	public static String libelleAenesReductionEchelon(Integer value) {
		String libelle = "";

		if (value != null) {
			if (value.intValue() == AENES_REDUCTION_ECHELON_DEFAVORABLE.intValue()) {
				libelle = AENES_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE;
			} else if (value.intValue() == AENES_REDUCTION_ECHELON_FAVORABLE_1_MOIS.intValue()) {
				libelle = AENES_REDUCTION_ECHELON_FAVORABLE_1_MOIS_LIBELLE;
			} else if (value.intValue() == AENES_REDUCTION_ECHELON_FAVORABLE_2_MOIS.intValue()) {
				libelle = AENES_REDUCTION_ECHELON_FAVORABLE_2_MOIS_LIBELLE;
			} else if (value.intValue() == AENES_REDUCTION_ECHELON_TRES_FAVORABLE_3_MOIS.intValue()) {
				libelle = AENES_REDUCTION_ECHELON_TRES_FAVORABLE_3_MOIS_LIBELLE;
			}
		}

		return libelle;
	}

	/**
	 * Donne le libellé associé à un code sur une réduction d'échelon ITRF
	 * 
	 * @param value
	 * @return
	 */
	public static String libelleItrfReductionEchelon(Integer value) {
		String libelle = "";

		if (value != null) {
			if (value.intValue() == ITRF_REDUCTION_ECHELON_DEFAVORABLE.intValue()) {
				libelle = ITRF_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE;
			} else if (value.intValue() == ITRF_REDUCTION_ECHELON_FAVORABLE.intValue()) {
				libelle = ITRF_REDUCTION_ECHELON_FAVORABLE_LIBELLE;
			}
		}

		return libelle;
	}

	/**
	 * Donne le libellé associé à un code sur une réduction d'échelon BU
	 * 
	 * @param value
	 * @return
	 */
	public static String libelleBuReductionEchelon(Integer value) {
		String libelle = "";

		if (value != null) {
			if (value.intValue() == BU_REDUCTION_ECHELON_DEFAVORABLE.intValue()) {
				libelle = BU_REDUCTION_ECHELON_DEFAVORABLE_LIBELLE;
			} else if (value.intValue() == BU_REDUCTION_ECHELON_FAVORABLE.intValue()) {
				libelle = BU_REDUCTION_ECHELON_FAVORABLE_LIBELLE;
			}
		}

		return libelle;
	}

	/**
	 * Donne le libellé associé à une code de réduction d'échelon, en concaténant
	 * les libellés si cela touches plus d'une population
	 * 
	 * @param value
	 * @return
	 */
	public static String libelleReductionEchelonFavorable(Integer value) {
		String libelle = "";

		String aenes = libelleAenesReductionEchelon(value);
		String itrf = libelleItrfReductionEchelon(value);
		String bu = libelleBuReductionEchelon(value);

		if (!StringCtrl.isEmpty(aenes)) {
			libelle += "AENES : " + aenes;
		}

		if (!StringCtrl.isEmpty(itrf)) {
			if (!StringCtrl.isEmpty(libelle)) {
				libelle += " / ";
			}
			libelle += "ITRF : " + itrf;
		}

		if (!StringCtrl.isEmpty(bu)) {
			if (!StringCtrl.isEmpty(libelle)) {
				libelle += " / ";
			}
			libelle += "BU : " + bu;
		}

		return libelle;
	}

	/**
	 * Donne le libellé associé à un code sur une promotion de grade
	 * 
	 * @param value
	 * @return
	 */
	public static String libellePromotionGrade(Integer value) {
		String libelle = "";

		if (value != null) {
			if (value.intValue() == PROMOTION_GRADE_DEFAVORABLE.intValue()) {
				libelle = PROMOTION_GRADE_DEFAVORABLE_LIBELLE;
			} else if (value.intValue() == PROMOTION_GRADE_FAVORABLE.intValue()) {
				libelle = PROMOTION_GRADE_FAVORABLE_LIBELLE;
			} else if (value.intValue() == PROMOTION_GRADE_TRES_FAVORABLE.intValue()) {
				libelle = PROMOTION_GRADE_TRES_FAVORABLE_LIBELLE;
			}
		}

		return libelle;
	}

	/**
	 * Donne le libellé associé à un code sur une promotion de corps
	 * 
	 * @param value
	 * @return
	 */
	public static String libellePromotionCorps(Integer value) {
		String libelle = "";

		if (value != null) {
			if (value.intValue() == PROMOTION_CORPS_DEFAVORABLE.intValue()) {
				libelle = PROMOTION_CORPS_DEFAVORABLE_LIBELLE;
			} else if (value.intValue() == PROMOTION_CORPS_FAVORABLE.intValue()) {
				libelle = PROMOTION_CORPS_FAVORABLE_LIBELLE;
			} else if (value.intValue() == PROMOTION_CORPS_TRES_FAVORABLE.intValue()) {
				libelle = PROMOTION_CORPS_TRES_FAVORABLE_LIBELLE;
			}
		}

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public String enpReductionEchelonLibelle() {
		String libelle = "";

		if (isAenes()) {
			libelle = EOEvaluationNoticePromotion.libelleAenesReductionEchelon(enpReductionEchelon());
		} else if (isItrf()) {
			libelle = EOEvaluationNoticePromotion.libelleItrfReductionEchelon(enpReductionEchelon());
		} else if (isBu()) {
			libelle = EOEvaluationNoticePromotion.libelleBuReductionEchelon(enpReductionEchelon());
		}

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public String enpPromotionGradeLibelle() {
		String libelle = "";

		libelle = libellePromotionGrade(enpPromotionGrade());

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public String enpPromotionCorpsLibelle() {
		String libelle = "";

		libelle = libellePromotionCorps(enpPromotionCorps());

		return libelle;
	}

	/**
	 * Indique si la décision doit être motiver
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isAMotiver(Integer value) {
		boolean isAMotiver = false;

		if (value != null) {
			if (value.intValue() == AENES_REDUCTION_ECHELON_DEFAVORABLE.intValue() ||
					value.intValue() == ITRF_REDUCTION_ECHELON_DEFAVORABLE.intValue() ||
					value.intValue() == BU_REDUCTION_ECHELON_DEFAVORABLE.intValue() ||
					value.intValue() == PROMOTION_GRADE_DEFAVORABLE.intValue() ||
					value.intValue() == PROMOTION_CORPS_DEFAVORABLE.intValue()) {
				isAMotiver = true;
			}
		}

		return isAMotiver;
	}

	// raccourcis vers la population

	public boolean isAenes() {
		boolean isAenes = false;

		isAenes = toEvaluation().isAenes();

		return isAenes;
	}

	public boolean isItrf() {
		boolean isItrf = false;

		isItrf = toEvaluation().isItrf();

		return isItrf;
	}

	public boolean isBu() {
		boolean isBu = false;

		isBu = toEvaluation().isBu();

		return isBu;
	}

	public boolean isReductionEchelonOui() {
		boolean isReductionEchelonOui = false;

		if (enpReductionEchelon() != null &&
				enpReductionEchelon().intValue() != DEFAVORABLE.intValue()) {
			isReductionEchelonOui = true;
		}

		return isReductionEchelonOui;
	}

	public boolean isReductionEchelonNon() {
		boolean isReductionEchelonNon = false;

		if (enpReductionEchelon() != null &&
				enpReductionEchelon().intValue() == DEFAVORABLE.intValue()) {
			isReductionEchelonNon = true;
		}

		return isReductionEchelonNon;
	}

	public boolean isPromotionGradeOui() {
		boolean isPromotionGradeOui = false;

		if (enpPromotionGrade() != null &&
				enpPromotionGrade().intValue() != DEFAVORABLE.intValue()) {
			isPromotionGradeOui = true;
		}

		return isPromotionGradeOui;
	}

	public boolean isPromotionGradeNon() {
		boolean isPromotionGradeNon = false;

		if (enpPromotionGrade() != null &&
				enpPromotionGrade().intValue() == DEFAVORABLE.intValue()) {
			isPromotionGradeNon = true;
		}

		return isPromotionGradeNon;
	}

	public boolean isPromotionCorpsOui() {
		boolean isPromotionCorpsOui = false;

		if (enpPromotionCorps() != null &&
				enpPromotionCorps().intValue() != DEFAVORABLE.intValue()) {
			isPromotionCorpsOui = true;
		}

		return isPromotionCorpsOui;
	}

	public boolean isPromotionCorpsNon() {
		boolean isPromotionCorpsNon = false;

		if (enpPromotionCorps() != null &&
				enpPromotionCorps().intValue() == DEFAVORABLE.intValue()) {
			isPromotionCorpsNon = true;
		}

		return isPromotionCorpsNon;
	}

	// Mettre une valeur par défaut en motif si avis défavorable

	@Override
	public void setEnpReductionEchelon(Integer value) {
		super.setEnpReductionEchelon(value);
		if (value != null &&
				value.intValue() == DEFAVORABLE.intValue()) {
			if (isAenes()) {
				setEnpReductionEchelonRefusMotif(AENES_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT);
			} else if (isItrf()) {
				setEnpReductionEchelonRefusMotif(ITRF_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT);
			} else if (isBu()) {
				setEnpReductionEchelonRefusMotif(BU_REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_DEFAULT);
			} else {
				setEnpReductionEchelonRefusMotif(REDUCTION_ECHELON_DEFAVORABLE_MOTIF_REFUS_POPULATION_INCONNUE_DEFAULT);
			}
		}
	}

	@Override
	public void setEnpPromotionGrade(Integer value) {
		super.setEnpPromotionGrade(value);
		if (value != null &&
				value.intValue() == DEFAVORABLE.intValue()) {
			setEnpPromotionGradeRefusMotif(PROMOTION_GRADE_DEFAVORABLE_MOTIF_REFUS_DEFAULT);
		}
	}

	@Override
	public void setEnpPromotionCorps(Integer value) {
		super.setEnpPromotionCorps(value);
		if (value != null &&
				value.intValue() == DEFAVORABLE.intValue()) {
			setEnpPromotionCorpsRefusMotif(PROMOTION_CORPS_DEFAVORABLE_MOTIF_REFUS_DEFAULT);
		}
	}

	// recopie de l'appréciation générale de l'entretien

	/**
	 * Recopier la valeur de l'appréciation saisie dans l'entretien professionnel
	 * pour la positionner dans celle de la notice de promotion
	 */
	public void recupererAppreciationGeneraleEntretien() {
		EORepartFicheItem eoRepart = EORepartFicheItem.findRecordForItemCodeInContext(
				editingContext(), EOTplItem.CODE_APPRECIATION_GENERALE, toEvaluation());
		if (eoRepart != null &&
				!StringCtrl.isEmpty(eoRepart.rfiValeurLibre())) {
			setEnpAppreciationGenerale(eoRepart.rfiValeurLibre());
		}
	}

}
