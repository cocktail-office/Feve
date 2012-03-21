package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOTplOnglet extends _EOTplOnglet {

	// les codes des onglets (ne sont pas encore completement gérés via les tables
	// de template)
	private final static String TPL_ONGLET_EVA_AGENT_CODE = "EVAAGENT";
	private final static String TPL_ONGLET_EVA_OBJ_PRECEDENT_CODE = "EVAOBJPREC";
	private final static String TPL_ONGLET_EVA_OBJ_SUIVANT_CODE = "EVAOBJSUIV";
	public final static String SITUATION_ACTIVITE_CODE = "EVASITU";
	private final static String TPL_ONGLET_EVA_COMPETENCE_CODE = "EVACOMP";
	private final static String TPL_ONGLET_EVA_APTITUDE_CODE = "EVAAPTI";
	private final static String TPL_ONGLET_EVA_EVOLUTION_CODE = "EVAEVOL";
	private final static String TPL_ONGLET_EVA_FIN_CODE = "EVAFIN";
	private final static String TPL_ONGLET_EVA_NOTICE_PROMOTION = "NOTICEPROM";

	public EOTplOnglet() {
		super();
	}

	public void validateForInsert() throws NSValidation.ValidationException {
		setDCreation(DateCtrl.now());
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

	// methodes rajoutees

	/**
	 * Surcharge du lien vers tosTplBloc pour avoir un classement selon le temoin
	 * <code>tblPosition</code>
	 */
	public NSArray<EOTplBloc> tosTplBlocSortedByPosition(
				EOEvaluationPeriode eoPeriode) {

		//
		EOQualifier qual = getValiditeQualifier(
					EOTplBloc.D_DEB_VAL_KEY, EOTplBloc.D_FIN_VAL_KEY, eoPeriode.epeDDebut(), eoPeriode.epeDFin());

		NSArray<EOTplBloc> tosTplBloc = tosTplBloc(qual);

		tosTplBloc = CktlSort.sortedArray(tosTplBloc, EOTplBloc.TBL_POSITION_KEY);

		return tosTplBloc;
	}

	/**
	 * Retourner l'enregistrement <code>EOTplOnglet</code> associe a un libelle
	 * <code>ongletLabel</code>
	 * 
	 * @param ec
	 * @param ongletLabel
	 * @return
	 */
	public static EOTplOnglet ongletForLabelInArray(NSArray<EOTplOnglet> eoTplOngletArray, String ongletLabel) {
		EOTplOnglet result = null;

		EOQualifier qual = CktlDataBus.newCondition(
					EOTplOnglet.TON_LIBELLE_KEY + "='" + ongletLabel + "'");

		NSArray<EOTplOnglet> array = EOQualifier.filteredArrayWithQualifier(eoTplOngletArray, qual);
		if (array.count() > 0) {
			result = array.objectAtIndex(0);
		}

		return result;
	}

	// nature des onglets

	public boolean isAgent() {
		return tonCode().equals(TPL_ONGLET_EVA_AGENT_CODE);
	}

	public boolean isObjectifsPrecedents() {
		return tonCode().equals(TPL_ONGLET_EVA_OBJ_PRECEDENT_CODE);
	}

	public boolean isObjectifsSuivants() {
		return tonCode().equals(TPL_ONGLET_EVA_OBJ_SUIVANT_CODE);
	}

	public boolean isSituation() {
		return tonCode().equals(SITUATION_ACTIVITE_CODE);
	}

	public boolean isCompetence() {
		return tonCode().equals(TPL_ONGLET_EVA_COMPETENCE_CODE);
	}

	public boolean isEvolution() {
		return tonCode().equals(TPL_ONGLET_EVA_EVOLUTION_CODE);
	}

	public boolean isFin() {
		return tonCode().equals(TPL_ONGLET_EVA_FIN_CODE);
	}
}
