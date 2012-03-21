package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOVCandidatEvaluation
		extends _EOVCandidatEvaluation {

	public EOVCandidatEvaluation() {
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
	 * Apparemment cette methode n'est pas appelee.
	 * 
	 * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
	 */
	public void validateForSave() throws NSValidation.ValidationException {
		validateObjectMetier();
		validateBeforeTransactionSave();
		super.validateForSave();

	}

	/**
	 * Peut etre appele a partir des factories.
	 * 
	 * @throws NSValidation.ValidationException
	 */
	public void validateObjectMetier() throws NSValidation.ValidationException {

	}

	/**
	 * A appeler par les validateforsave, forinsert, forupdate.
	 * 
	 */
	private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

	}

	// ajouts

	public final static String D_TENUE_ENTRETIEN_KEY = "dTenueEntretien";
	public final static String D_VISA_RESPONSABLE_RH_KEY = "dVisaResponsableRh";

	// les classements possibles
	public final static String SORT_EVALUATEUR = TO_EVALUATEUR_KEY + "." + EOIndividu.NOM_PRENOM_KEY;
	public final static String SORT_AGENT = TO_INDIVIDU_KEY + "." + EOIndividu.NOM_PRENOM_KEY;
	public final static String SORT_DATE_ENTRETIEN = D_TENUE_ENTRETIEN_KEY;
	public final static String SORT_DATE_VISA_RH = D_VISA_RESPONSABLE_RH_KEY;

	/**
	 * Indique si l'enregistrement EOEvaluation existe
	 */
	private boolean isEOEvaluationCreee() {
		return toEvaluation() != null;
	}

	/**
     * 
     */
	public boolean isViseParResponsableRh() {
		boolean result = false;
		if (isEOEvaluationCreee()) {
			result = toEvaluation().isViseParResponsableRh();
		}
		return result;
	}

	/**
     * 
     */
	public NSTimestamp dTenueEntretien() {
		NSTimestamp result = null;
		if (isEOEvaluationCreee()) {
			result = toEvaluation().dTenueEntretien();
		}
		return result;
	}

	/**
     * 
     */
	public NSTimestamp dVisaResponsableRh() {
		NSTimestamp result = null;
		if (isViseParResponsableRh()) {
			result = toEvaluation().dVisaResponsableRh();
		}
		return result;
	}

	/**
	 * La fenetre volante sur la case à cocher visa RH
	 */
	public String tipChkDVisaResponsableRh() {
		String str = "";

		if (isEOEvaluationCreee() && toEvaluation().isViseParResponsableRh()) {
			str = "Visé RH le " + DateCtrl.dateToString(toEvaluation().dVisaResponsableRh());
		} else {
			str = "Pas encore visé";
		}

		return str;
	}

	/**
	 * Methode interne permettant de liste un ensemble d'objets
	 * <code>VCandidatEvaluation</code>.
	 * 
	 * @param ec
	 * @param individu
	 *          : l'individu responsable
	 * @param periode
	 * @param ignoreNm1
	 *          : faut-il ne pas tenir compte des N-1 directs
	 * @param onlyNm1
	 *          : faut ne traiter que les N-1 directs
	 * @return
	 */
	public static NSArray getVCandidatEvaluationForIndividuAndPeriodeInContext(
				EOIndividu individu, EOEvaluationPeriode periode, boolean ignoreNm1, boolean onlyNm1) {
		NSArray records = new NSArray();

		EOEditingContext ec = individu.editingContext();

		// cas particulier du grand chef (de la hierachie) : on retourne tous les
		// individus de la hierarchie
		EOHierarchie eoHierarchieBigBoss = EOHierarchie.getEoHierarchieRacineForPeriodeInContext(ec, periode);

		if (eoHierarchieBigBoss != null) {
			EOIndividu theBigBoss = eoHierarchieBigBoss.toIndividu();
			if (!onlyNm1 && theBigBoss.noIndividu().intValue() == individu.noIndividu().intValue()) {
				records = fetchVCandidatEvaluations(
							ec, CktlDataBus.newCondition(TO_EVALUATION_PERIODE_KEY + "=%@", new NSArray(periode)), null);
			} else {
				// la liste des individu N-p d'apres la hierarchie dont on soustrait les
				// N-1
				NSMutableArray individusNmp = new NSMutableArray(
							onlyNm1 ?
									(NSArray) EOHierarchie.findHierarchieForIndividuRespNmpInContext(ec, individu, periode).valueForKey(EOHierarchie.TO_INDIVIDU_KEY) :
										(NSArray) EOHierarchie.findHierarchieForIndividuRespInContext(ec, individu, periode).valueForKey(EOHierarchie.TO_INDIVIDU_KEY));
				// on retire tous les N-1 si besoin
				if (ignoreNm1) {
					// la liste des individu N-1 d'apres la hierarchie
					NSArray individusNm1 = (NSArray) EOHierarchie.findHierarchieForIndividuRespInContext(
								ec, individu, periode).valueForKey(EOHierarchie.TO_INDIVIDU_KEY);
					individusNmp.removeObjectsInArray(individusNm1);
				}

				/*
				 * String strQual = ""; NSArray args = new NSArray(); if
				 * (individusNmp.count() > 0) { strQual += "("; for (int i = 0; i <
				 * individusNmp.count(); i++) { EOIndividu unIndividu = (EOIndividu)
				 * individusNmp.objectAtIndex(i); strQual += TO_INDIVIDU_KEY + "=%@";
				 * args = args.arrayByAddingObject(unIndividu); if (i !=
				 * individusNmp.count() -1) strQual += " OR "; } strQual +=
				 * ") AND "+TO_EVALUATION_PERIODE_KEY+"=%@"; args =
				 * args.arrayByAddingObject(periode); long start =
				 * System.currentTimeMillis(); records = fetchVCandidatEvaluations(ec,
				 * CktlDataBus.newCondition(strQual, args), null); CktlLog.log(
				 * "getVCandidatEvaluationForIndividuAndPeriodeInContext() fetchVCandidatEvaluations duration : "
				 * + (System.currentTimeMillis()-start) + "ms"); }
				 */
				if (individusNmp.count() > 0) {
					long start = System.currentTimeMillis();
					for (int i = 0; i < individusNmp.count(); i++) {
						EOIndividu unIndividu = (EOIndividu) individusNmp.objectAtIndex(i);
						EOQualifier qual = CktlDataBus.newCondition(
									TO_INDIVIDU_KEY + "=%@ and " + TO_EVALUATION_PERIODE_KEY + "=%@",
									new NSArray(new Object[] { unIndividu, periode }));
						records = records.arrayByAddingObjectsFromArray(
									fetchVCandidatEvaluations(ec, qual, null));
					}
					CktlLog.log("getVCandidatEvaluationForIndividuAndPeriodeInContext() fetchVCandidatEvaluations duration : " + (System.currentTimeMillis() - start) + "ms");
				}
			}
		}

		return records;
	}

	public String display() {
		return toIndividu().display() + " du " +
				DateCtrl.dateToString(toEvaluationPeriode().epeDDebut()) + " au " +
				DateCtrl.dateToString(toEvaluationPeriode().epeDFin());
	}

	/**
	 * Retourne l'enregistrement associ� � un individu et une periode
	 * 
	 * @param individu
	 * @param periode
	 * @return
	 */
	public static EOVCandidatEvaluation findRecordForIndividuAndPeriode(EOIndividu individu, EOEvaluationPeriode periode) {
		EOEditingContext ec = individu.editingContext();
		return fetchRequiredVCandidatEvaluation(ec,
					CktlDataBus.newCondition(TO_INDIVIDU_KEY + "=%@ and " + TO_EVALUATION_PERIODE_KEY + " =%@",
							new NSArray(new Object[] { individu, periode })));
	}

	@Override
	public EOVCandidatEvaluation toVCandidatEvaluation() {
		return this;
	}

	public void setIsViseParResponsableRh(boolean value) {
		if (toEvaluation() != null) {
			toEvaluation().setIsViseParResponsableRh(value);
		}
	}

}
