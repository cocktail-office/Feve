package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOFicheDePoste extends _EOFicheDePoste {

	public EOFicheDePoste() {
		super();
		typeFiche = FICHE_DE_POSTE;
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

	public NSArray tosReferensActivites() {
		return (NSArray) tosRepartFdpActi().valueForKey(EORepartFdpActi.TO_REFERENS_ACTIVITES_KEY);
	}

	public NSArray tosReferensCompetences() {
		return (NSArray) tosRepartFdpComp().valueForKey(EORepartFdpComp.TO_REFERENS_COMPETENCES_KEY);
	}

	public NSArray tosRepartFdpActi() {
		NSArray tosRepartFdpActi = super.tosRepartFdpActi();
		return CktlSort.sortedArray(tosRepartFdpActi, EORepartFdpActi.RFA_POSITION_KEY);
	}

	/**
	 * La liste des activités autres
	 * 
	 * @return
	 */
	public NSArray tosRepartFdpActivitesAutres() {
		NSArray tosRepartFdpActivitesAutres = super.tosRepartFdpAutre(
					CktlDataBus.newCondition(EORepartFdpAutre.FAU_TYPE_KEY + "='" + EORepartFdpAutre.TYPE_ACTIVITE + "'"));
		return CktlSort.sortedArray(tosRepartFdpActivitesAutres, EORepartFdpAutre.FAU_POSITION_KEY);
	}

	/**
	 * La liste des competences autres
	 * 
	 * @return
	 */
	public NSArray tosRepartFdpCompetencesAutres() {
		NSArray tosRepartFdpCompetencesAutres = super.tosRepartFdpAutre(
					CktlDataBus.newCondition(EORepartFdpAutre.FAU_TYPE_KEY + "='" + EORepartFdpAutre.TYPE_COMPETENCE + "'"));
		return CktlSort.sortedArray(tosRepartFdpCompetencesAutres, EORepartFdpAutre.FAU_POSITION_KEY);
	}

	public NSArray tosRepartFdpComp() {
		// return super.tosRepartFdpComp(null,
		// CktlSort.newSort(EORepartFdpComp.RFC_POSITION_KEY), false);
		NSArray tosRepartFdpComp = super.tosRepartFdpComp();
		return CktlSort.sortedArray(tosRepartFdpComp, EORepartFdpComp.RFC_POSITION_KEY);
	}

	public final static String IS_VALIDEE_AGENT = "fdpVisaAgentBool";
	public final static String IS_VALIDEE_RESPONSABLE = "fdpVisaRespBool";
	public final static String IS_VALIDEE_DIRECTEUR = "fdpVisaDirecBool";

	public boolean fdpVisaAgentBool() {
		return OUI.equals(fdpVisaAgent());
	}

	public void setFdpVisaAgentBool(boolean value) {
		setFdpVisaAgent(NON);
		if (value == true)
			setFdpVisaAgent(OUI);
	}

	public boolean fdpVisaRespBool() {
		return OUI.equals(fdpVisaResp());
	}

	public void setFdpVisaRespBool(boolean value) {
		setFdpVisaResp(NON);
		if (value == true)
			setFdpVisaResp(OUI);
	}

	public boolean fdpVisaDirecBool() {
		return OUI.equals(fdpVisaDirec());
	}

	public void setFdpVisaDirecBool(boolean value) {
		setFdpVisaDirec(NON);
		if (value == true)
			setFdpVisaDirec(OUI);
	}

	/**
	 * Indique si les visa ne sont pas faites sur cette fiche
	 */
	public boolean hasWarning() {
		return !fdpVisaAgentBool() || !fdpVisaDirecBool() || !fdpVisaRespBool();
	}

	public String htmlWarnMessage() {
		StringBuffer buff = new StringBuffer();
		if (!fdpVisaAgentBool())
			buff.append("Fiche de poste ").append(display()).
					append(" non vis&eacute;e par l'agent.<br>");
		if (!fdpVisaRespBool())
			buff.append("Fiche de poste ").append(display()).
					append(" non vis&eacute;e par le responsable.<br>");
		if (!fdpVisaDirecBool())
			buff.append("Fiche de poste ").append(display()).
					append(" non vis&eacute;e par le directeur de composante.<br>");
		return buff.toString();
	}

	// setters silencieux pour ne pas planter lors d'acces non pr�vu

	public void setTosReferensActivites(NSArray value) {

	}

	public void setTosReferensCompetences(NSArray value) {

	}

	/**
	 * Le libellé dans le fil d'ariane quand on consulte la fiche de poste d'apres
	 * le profil connecté
	 * 
	 * @return
	 */
	public String getEnteteFiche(FeveUserInfo feveUserInfo) {

		String enteteDetail = "";

		boolean hasDroitVisualiserOccupants = (
					feveUserInfo.hasDroitOnCible(EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.POSTE, toPoste(), false) ||
					feveUserInfo.hasDroitOnCible(EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.POSTE, toPoste(), false));

		// entete pour afficher sur qui on travaille (numero de fiche si aucun droit
		// de consultation sur le poste)
		if (hasDroitVisualiserOccupants) {
			enteteDetail = display();
			EOAffectationDetail recAffectationDetail = toAffectationDetailActuelle();
			if (recAffectationDetail == null) {
				recAffectationDetail = toAffectationDetailDerniere();
			}
			enteteDetail = (recAffectationDetail != null && recAffectationDetail.toAffectation() != null ?
						recAffectationDetail.toAffectation().toIndividu().display() + " / " : "") + enteteDetail;
		} else {
			enteteDetail = identifiant();
		}
		return enteteDetail;
	}

	/**
	 * est-ce qu'une activité autre est en cours de modification
	 * 
	 * @return
	 */
	public boolean isAuMoinsUneActiviteAutrefEstEnCoursDeModification() {
		boolean result = false;
		int i = 0;
		while (!result && i < tosRepartFdpActivitesAutres().count()) {
			EORepartFdpAutre repart = (EORepartFdpAutre) tosRepartFdpActivitesAutres().objectAtIndex(i);
			if (repart.isEnCoursDeModification()) {
				result = true;
			}
			i++;
		}
		return result;
	}

	/**
	 * désactiver les activités autre est en cours de modification
	 * 
	 * @return
	 */
	public void desactiverTouteActiviteAutrefEnCoursDeModification() {
		for (int i = 0; i < tosRepartFdpActivitesAutres().count(); i++) {
			EORepartFdpAutre repart = (EORepartFdpAutre) tosRepartFdpActivitesAutres().objectAtIndex(i);
			repart.setIsEnCoursDeModification(false);
		}
	}

	/**
	 * est-ce qu'une competence autre est en cours de modification
	 * 
	 * @return
	 */
	public boolean isAuMoinsUneCompetenceAutrefEstEnCoursDeModification() {
		boolean result = false;
		int i = 0;
		while (!result && i < tosRepartFdpCompetencesAutres().count()) {
			EORepartFdpAutre repart = (EORepartFdpAutre) tosRepartFdpCompetencesAutres().objectAtIndex(i);
			if (repart.isEnCoursDeModification()) {
				result = true;
			}
			i++;
		}
		return result;
	}

	/**
	 * désactiver les competences autre est en cours de modification
	 * 
	 * @return
	 */
	public void desactiverTouteCompetenceAutrefEnCoursDeModification() {
		for (int i = 0; i < tosRepartFdpCompetencesAutres().count(); i++) {
			EORepartFdpAutre repart = (EORepartFdpAutre) tosRepartFdpCompetencesAutres().objectAtIndex(i);
			repart.setIsEnCoursDeModification(false);
		}
	}

	/**
	 * trouver un enregistrement a partir de la cle primaire
	 */
	public static EOFicheDePoste findFicheDePosteForFdpKeyInContext(EOEditingContext ec, int fdpKey) {
		EOFicheDePoste record = null;
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("fdpKey = %@", new NSArray(new Integer(fdpKey)));
		record = fetchFicheDePoste(ec, qual);
		return record;
	}

	/**
	 * Creer une fiche de poste a partir d'une fiche existante.
	 * 
	 * @param prevFiche
	 *          : la fiche a dupliquer
	 * @param dDebut
	 * @param dFin
	 * @param isDupliquerContenuActiviteCompetencesEmploiType
	 * @param eoPoste
	 *          le poste surlequel afficher la nouvelle fiche
	 * @return
	 */
	public static EOFicheDePoste dupliquerFiche(
				EOFicheDePoste prevFiche,
				NSTimestamp dDebut,
				NSTimestamp dFin,
				EOPoste eoPoste
			) {
		EOEditingContext ec = prevFiche.editingContext();

		// creer le record FicheDePoste
		EOFicheDePoste newEoFicheDePoste = newDefaultRecordInContext(ec);

		newEoFicheDePoste.setToPosteRelationship(eoPoste);
		newEoFicheDePoste.setFdpDDebut(dDebut);
		newEoFicheDePoste.setFdpDFin(dFin);
		newEoFicheDePoste.setFdpContexteTravail(prevFiche.fdpContexteTravail());
		newEoFicheDePoste.setFdpMissionPoste(prevFiche.fdpMissionPoste());

		if (prevFiche.isEmploiSurAncienneNomenclature() == false) {

			newEoFicheDePoste.setToReferensEmploisRelationship(prevFiche.toReferensEmplois());

			// creer les records RepartFdpActi
			for (int i = 0; i < prevFiche.tosRepartFdpActi().count(); i++) {
				EORepartFdpActi recRepart = (EORepartFdpActi) prevFiche.tosRepartFdpActi().objectAtIndex(i);
				EORepartFdpActi newRepart = EORepartFdpActi.newRecordInContext(
							ec, newEoFicheDePoste, recRepart.toReferensActivites());
				newRepart.setRfaPosition(recRepart.rfaPosition());
			}

			// creer les records RepartFdpComp
			for (int i = 0; i < prevFiche.tosRepartFdpComp().count(); i++) {
				EORepartFdpComp recRepart = (EORepartFdpComp) prevFiche.tosRepartFdpComp().objectAtIndex(i);
				EORepartFdpComp newRepart = EORepartFdpComp.newRecordInContext(
							ec, newEoFicheDePoste, recRepart.toReferensCompetences());
				newRepart.setRfcPosition(recRepart.rfcPosition());
			}

			// creer les records RepartFdpAutre
			for (int i = 0; i < prevFiche.tosRepartFdpAutre().count(); i++) {
				// nouvel enregistrement activite autre et comeptence autre
				EORepartFdpAutre recRepart = (EORepartFdpAutre) prevFiche.tosRepartFdpAutre().objectAtIndex(i);
				EORepartFdpAutre newRepart = EORepartFdpAutre.newRecordInContext(
							ec, newEoFicheDePoste, recRepart.fauChampLibre(), recRepart.fauType());
				newRepart.setFauPosition(recRepart.fauPosition());
			}
		}

		return newEoFicheDePoste;
	}

	public static EOFicheDePoste newRecordInContext(
				EOEditingContext ec,
				EOPoste poste,
				NSTimestamp dDebut,
				NSTimestamp dFin
			) {
		EOFicheDePoste newRecord = newDefaultRecordInContext(ec);

		newRecord.setToPosteRelationship(poste);
		newRecord.setFdpDDebut(dDebut);
		newRecord.setFdpDFin(dFin);

		return newRecord;
	}

	/**
	 * Constructeur par défaut
	 * 
	 * @param ec
	 * @return
	 */
	private static EOFicheDePoste newDefaultRecordInContext(EOEditingContext ec) {
		EOFicheDePoste record = new EOFicheDePoste();
		record.setFdpVisaAgent(NON);
		record.setFdpVisaDirec(NON);
		record.setFdpVisaResp(NON);
		ec.insertObject(record);
		return record;
	}

	/**
	 * Indique si la fiche est sur l'ancienne nomenclature ou non
	 * 
	 * @return
	 */
	public boolean isEmploiSurAncienneNomenclature() {
		boolean isEmploiSurAncienneNomenclature = false;

		if (toReferensEmplois() != null &&
					toReferensEmplois().isArchive()) {
			isEmploiSurAncienneNomenclature = true;
		}

		return isEmploiSurAncienneNomenclature;

	}
}
