package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.eos.modele.grhum.EOCorps;
import org.cocktail.feve.eos.modele.grhum.EOGrade;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.eof.ERXQ;

/**
 * Ecran de suivi des evaluation
 * 
 * @author ctarade
 */
public class SuiviEvaluation
		extends A_SuiviGeneric {

	@Deprecated
	public WODisplayGroup evaluationDg;

	public WODisplayGroup periodeDg;
	public WODisplayGroup serviceDg;

	private NSArray<EOVCandidatEvaluation> evaluationArray;

	public final static String STR_OUI = "OUI";
	public final static String STR_NON = "NON";
	public NSArray<String> ouiNonArray = new NSArray<String>(
			new String[] { STR_OUI, STR_NON });

	public String ouiNonItem;

	public String entretienTenuOuiNonSelected;
	public String visaRhOuiNonSelected;

	public WODisplayGroup periodeDh;

	// liste des corps
	private NSArray<EOCorps> eoCorpsArray;
	public EOCorps eoCorpsSelected;
	public EOCorps eoCorpsItem;

	// liste des grades
	private NSArray<EOGrade> eoGradeArray;
	public EOGrade eoGradeSelected;
	public EOGrade eoGradeItem;

	public SuiviEvaluation(WOContext context) {
		super(context);
		initComponent();
	}

	/**
	 * 
	 */
	private void initComponent() {
		// on preselectionne la periode actuelle
		setPeriodeSelected(EOEvaluationPeriode.getCurrentPeriode(ec));
		// ne pas rafraichir le DG principal tout se suite
		shouldRefreshMainDg = false;
		// selection du type "tous" par defaut pour accélerer les traitements
		// mais aussi car pas d'évaluation sur les postes non en cours / vacants ...
		setPosteTypeSelected(EOPoste.POSTE_TYPE_TOUS);
	}

	/**
	 * @deprecated XXX plus utilisé en ajax ...
	 */
	protected void doRefreshMainDg() {

	}

	/**
	 * 
	 * @return
	 */
	public NSArray<EOVCandidatEvaluation> getEvaluationArray() {

		if (evaluationArray == null) {

			NSArray<EOVCandidatEvaluation> result = null;

			if (periodeSelected != null) {

				result = EOVCandidatEvaluation.fetchVCandidatEvaluations(
						ec, ERXQ.equals(EOVCandidatEvaluation.TO_EVALUATION_PERIODE_KEY, periodeSelected), null);

			} else {
				// la période est obligatoire => aucun résultat si non selectionnée

				// result = EOVCandidatEvaluation.fetchAllVCandidatEvaluations(
				// ec);

				result = new NSArray<EOVCandidatEvaluation>();
			}

			boolean shouldFilterPoste = true;
			if (posteTypeSelected.equals(EOPoste.POSTE_TYPE_TOUS)) {
				shouldFilterPoste = false;
			}
			boolean shouldFilterService = true;
			if (serviceSelected == null) {
				shouldFilterService = false;
			}

			// filtrage en memoire sur des to-many -> faut se le palucher a la main :(
			NSMutableArray<EOVCandidatEvaluation> resultFilteredInMemory = new NSMutableArray<EOVCandidatEvaluation>();
			for (int i = 0; i < result.count(); i++) {
				boolean shouldAdd = false;
				EOVCandidatEvaluation evaluation = result.objectAtIndex(i);
				// filtrer sur le poste si besoin
				if (shouldFilterPoste) {
					NSArray<EOPoste> postes = EOQualifier.filteredArrayWithQualifier(
							evaluation.tosPoste(), getPosteTypeQual());
					shouldAdd = (postes.count() > 0);
				} else {
					shouldAdd = true;
				}
				// filtrer sur le service si besoin
				if (shouldAdd && shouldFilterService) {
					NSArray<EOStructure> structures = evaluation.tosStructure();
					for (int j = 0; j < structures.count(); j++) {
						EOStructure structure = structures.objectAtIndex(j);
						if (structure.cStructure().equals(serviceSelected.toStructure().cStructure())) {
							shouldAdd = true;
							break;
						}
						shouldAdd = false;
					}
				}
				// on l'ajoute au final
				if (shouldAdd) {
					resultFilteredInMemory.addObject(evaluation);
				}
			}

			evaluationArray = resultFilteredInMemory.immutableClone();

			// filtrage sur entretien tenu O/N et visa RH O/N
			if (!StringCtrl.isEmpty(entretienTenuOuiNonSelected)) {
				if (entretienTenuOuiNonSelected.equals(STR_OUI)) {
					evaluationArray = ERXQ.filtered(evaluationArray,
							ERXQ.isNotNull(EOVCandidatEvaluation.D_TENUE_ENTRETIEN_KEY));
				} else {
					evaluationArray = ERXQ.filtered(evaluationArray,
							ERXQ.isNull(EOVCandidatEvaluation.D_TENUE_ENTRETIEN_KEY));
				}
			}

			//
			if (!StringCtrl.isEmpty(visaRhOuiNonSelected)) {
				if (visaRhOuiNonSelected.equals(STR_OUI)) {
					evaluationArray = ERXQ.filtered(evaluationArray,
							ERXQ.isNotNull(EOVCandidatEvaluation.D_VISA_RESPONSABLE_RH_KEY));
				} else {
					evaluationArray = ERXQ.filtered(evaluationArray,
							ERXQ.isNull(EOVCandidatEvaluation.D_VISA_RESPONSABLE_RH_KEY));
				}
			}

		}

		return evaluationArray;
	}

	/**
	 * NE SERT PAS ICI
	 * 
	 * @see A_SuiviGeneric#prefixEntityDgToPoste()
	 * @see EOEvaluation
	 */
	protected String prefixEntityDgToPoste() {
		return null;
	}

	protected WODisplayGroup mainDg() {
		return evaluationDg;
	}

	/**
	 * On surcharge le setter de la super classe car le fetch ne peut se faire
	 * qu'en mémoire (tosStructure n'est pas le modele)
	 * 
	 * @param value
	 */
	public void setServiceSelected(EOVService value) {
		serviceSelected = value;
		resetEvaluationArray();
	}

	/**
	 * Lors de la modification de la periode, on va recharger la liste des
	 * evaluations
	 * 
	 * @param value
	 */
	public void setPeriodeSelected(EOEvaluationPeriode value) {
		periodeSelected = value;
		resetEvaluationArray();
		resetEoCorpsArray();
		resetEoGradeArray();
	}

	/**
	 * 
	 */
	public void setPosteTypeSelected(String value) {
		super.setPosteTypeSelected(value);
		resetEvaluationArray();
	}

	// edition

	public WOResponse printCsv() {
		return getCsvResponse(true, true, false, false);
	}

	/**
	 * 
	 * @param isAfficherDateEntretien
	 * @param isAfficherDateVisaRh
	 * @param isAfficherPoste
	 * @param isAfficherCarriere
	 * @return
	 */
	protected WOResponse getCsvResponse(
			boolean isAfficherDateEntretien,
			boolean isAfficherDateVisaRh,
			boolean isAfficherPoste,
			boolean isAfficherCarriere) {

		CktlDataResponse resp = new CktlDataResponse();
		StringBuffer sb = new StringBuffer();
		sb.append("Evaluateur").append(CSV_COLUMN_SEPARATOR);
		sb.append("Agent").append(CSV_COLUMN_SEPARATOR);

		if (isAfficherDateEntretien) {
			sb.append("Date Entretien").append(CSV_COLUMN_SEPARATOR);
		}
		if (isAfficherDateVisaRh) {
			sb.append("Date visa RH").append(CSV_COLUMN_SEPARATOR);
		}
		if (isAfficherPoste) {
			sb.append("Evolutions sur le poste").append(CSV_COLUMN_SEPARATOR);
		}
		if (isAfficherCarriere) {
			sb.append("Perspectives carrière").append(CSV_COLUMN_SEPARATOR);
		}

		sb.append(CSV_NEW_LINE);
		for (int i = 0; i < getEvaluationArray().count(); i++) {
			EOVCandidatEvaluation evaluation = (EOVCandidatEvaluation) getEvaluationArray().objectAtIndex(i);
			String nomPrenomEvaluateur = "inconnu";
			if (evaluation.toEvaluateur() != null) {
				nomPrenomEvaluateur = evaluation.toEvaluateur().nomPrenom();
			}
			sb.append(nomPrenomEvaluateur).append(CSV_COLUMN_SEPARATOR);
			sb.append(evaluation.toIndividu().nomPrenom()).append(CSV_COLUMN_SEPARATOR);

			if (isAfficherDateEntretien) {
				String strDateEntretien = "non fait";
				if (evaluation.dTenueEntretien() != null) {
					strDateEntretien = DateCtrl.dateToString(evaluation.dTenueEntretien());
				}
				sb.append(strDateEntretien).append(CSV_COLUMN_SEPARATOR);
			}

			if (isAfficherDateVisaRh) {
				String strDateVisaRh = "non fait";
				if (evaluation.dVisaResponsableRh() != null) {
					strDateVisaRh = DateCtrl.dateToString(evaluation.dVisaResponsableRh());
				}
				sb.append(strDateVisaRh).append(CSV_COLUMN_SEPARATOR);
			}

			if (isAfficherPoste) {
				String strEvolution = " ";
				if (evaluation.toEvaluation() != null &&
						!StringCtrl.isEmpty(evaluation.toEvaluation().evaEvolutionPropo())) {
					strEvolution = StringCtrl.replace(evaluation.toEvaluation().evaEvolutionPropo(), "\n", " ");
					strEvolution = StringCtrl.replace(strEvolution, "\r", " ");
				}
				sb.append(strEvolution).append(CSV_COLUMN_SEPARATOR);
			}

			if (isAfficherCarriere) {
				String strCarriere = " ";
				if (evaluation.toEvaluation() != null &&
						!StringCtrl.isEmpty(evaluation.toEvaluation().evaEvolutionEnvis())) {
					strCarriere = StringCtrl.replace(evaluation.toEvaluation().evaEvolutionEnvis(), "\n", " ");
					strCarriere = StringCtrl.replace(strCarriere, "\r", " ");
				}
				sb.append(strCarriere).append(CSV_COLUMN_SEPARATOR);
			}

			sb.append(CSV_NEW_LINE);
		}
		NSData stream = new NSData(sb.toString(), CSV_ENCODING);
		resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);
		resp.setHeader(String.valueOf(stream.length()), "Content-Length");
		resp.setFileName("suivi_entretiens.csv");
		return resp;

	}

	@Override
	public void doApresClassement() {
		resetEvaluationArray();
	}

	public void setEntretienTenuOuiNonSelected(
			String entretienTenuOuiNonSelected) {
		this.entretienTenuOuiNonSelected = entretienTenuOuiNonSelected;
		resetEvaluationArray();
	}

	public void setVisaRhOuiNonSelected(String visaRhOuiNonSelected) {
		this.visaRhOuiNonSelected = visaRhOuiNonSelected;
		resetEvaluationArray();
	}

	public void resetEvaluationArray() {
		evaluationArray = null;
	}

	public void resetEoCorpsArray() {
		eoCorpsArray = null;
	}

	public void resetEoGradeArray() {
		eoGradeArray = null;
	}

	public void setEoCorpsSelected(EOCorps eoCorpsSelected) {
		this.eoCorpsSelected = eoCorpsSelected;
	}

	public void setEoGradeSelected(EOGrade eoGradeSelected) {
		this.eoGradeSelected = eoGradeSelected;
	}

	/**
	 * 
	 * @return
	 */
	public final NSArray<EOCorps> getEoCorpsArray() {
		if (eoCorpsArray == null) {

			eoCorpsArray = new NSArray<EOCorps>();

			for (int i = 0; i < getEvaluationArray().count(); i++) {

				EOVCandidatEvaluation eoItem = getEvaluationArray().objectAtIndex(i);

				EOCorps eoCorps = eoItem.toIndividu().getCorpsForPeriode(
						periodeSelected.epeDDebut(), periodeSelected.epeDFin());

				if (eoCorps != null &&
						!eoCorpsArray.containsObject(eoCorps)) {
					eoCorpsArray = eoCorpsArray.arrayByAddingObject(eoCorps);
				}

			}

		}
		return eoCorpsArray;
	}

	/**
	 * 
	 * @return
	 */
	public final NSArray<EOGrade> getEoGradeArray() {
		if (eoGradeArray == null) {

			eoGradeArray = new NSArray<EOGrade>();

			for (int i = 0; i < getEvaluationArray().count(); i++) {

				EOVCandidatEvaluation eoItem = getEvaluationArray().objectAtIndex(i);

				EOGrade eoGrade = eoItem.toIndividu().getGradeForPeriode(
						periodeSelected.epeDDebut(), periodeSelected.epeDFin());

				if (eoGrade != null &&
						!eoGradeArray.containsObject(eoGrade)) {
					eoGradeArray = eoGradeArray.arrayByAddingObject(eoGrade);
				}

			}

		}
		return eoGradeArray;
	}

}