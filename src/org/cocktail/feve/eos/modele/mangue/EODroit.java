package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.app.Application;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EODroit
		extends _EODroit
		implements I_Droit {

	public EODroit() {
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

	// methode rajoutees

	/**
	 * Obtenir l'ensemble des droits deduits pour une personne
	 * <code>individu</code>.
	 * 
	 * C'est un bout de la methode "reverse" de
	 * {@link #fetchDroits(EOEditingContext, EOTypeDroitAcces, EOTypeDroitCible, EOStructure, EOStructure, EOIndividu, EOVCandidatEvaluation, EOPoste, EOFicheDePoste, EOFicheLolf, boolean)}
	 * 
	 * @return une dictionnaire contenant la liste de <code>EODroit</code> ainsi
	 *         qu'un temoin indiquant si l'utilisateur n'a en heritage que ses
	 *         propres informations)
	 */
	public static NSDictionary getDeductedDroitForIndividu(Application app, EOIndividu individu) {
		// pour les droits deduits, on va creer une liste de EODroit non inseres
		// dans le context
		// afin de ne pas avoir d'insertion dans la base
		NSArray result = new NSArray();
		EOEditingContext ec = individu.editingContext();

		// on fait un dictionnaire de cache des paramKey pour eviter des multiplier
		// des acces
		// inutiles a la base de donnees (cf
		// EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey));
		NSMutableDictionary dicoTypeAccesCibleCache = new NSMutableDictionary();

		// ---------------------------------
		// -- droit deduits de l'annuaire --
		// ---------------------------------

		// -- droits deduits du responsable de service dans l'annuaire
		NSArray serviceRespList = (NSArray) EOVService.fetchVServices(ec,
					CktlDataBus.newCondition(
							EOVService.TO_STRUCTURE_KEY + "." + EOStructure.TO_RESPONSABLE_KEY + "=%@",
							new NSArray(individu)),
					null).valueForKey(EOVService.TO_STRUCTURE_KEY);

		for (int i = 0; i < serviceRespList.count(); i++) {
			EOStructure serviceAnnuaire = (EOStructure) serviceRespList.objectAtIndex(i);

			for (int j = 0; j < EOMangueParametres.ARRAY_KEY_BOOLEAN_SERVICE.count(); j++) {
				String paramKey = (String) EOMangueParametres.ARRAY_KEY_BOOLEAN_SERVICE.objectAtIndex(j);
				// on ne recupere que les parametres actifs
				if (app.getBooleanParamValueForKey(paramKey)) {
					NSDictionary dico = (NSDictionary) dicoTypeAccesCibleCache.valueForKey(paramKey);
					if (dico == null) {
						dico = EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey);
						dicoTypeAccesCibleCache.setObjectForKey(dico, paramKey);
					}
					EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_ACCES);
					EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_CIBLE);
					// creation d'un enregistrement temporaire (pas dans la base)
					Droit fakeEODroitAnnuaireService = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
					fakeEODroitAnnuaireService.takeValueForKey(serviceAnnuaire, EODroit.TO_DROIT_STRUCTURE_KEY);
					// ajout aux autres droits
					result = result.arrayByAddingObject(fakeEODroitAnnuaireService);
				}
			}
		}

		// -- droits deduits des responsables de composante
		NSArray composanteRespList = EOQualifier.filteredArrayWithQualifier(
					serviceRespList, CktlDataBus.newCondition(EOStructure.IS_COMPOSANTE_KEY + "=%@", new NSArray(Boolean.TRUE)));

		for (int i = 0; i < composanteRespList.count(); i++) {
			EOStructure composanteAnnuaire = (EOStructure) composanteRespList.objectAtIndex(i);

			for (int j = 0; j < EOMangueParametres.ARRAY_KEY_BOOLEAN_COMPOSANTE.count(); j++) {
				String paramKey = (String) EOMangueParametres.ARRAY_KEY_BOOLEAN_COMPOSANTE.objectAtIndex(j);
				// on ne recupere que les parametres actifs
				if (app.getBooleanParamValueForKey(paramKey)) {
					NSDictionary dico = (NSDictionary) dicoTypeAccesCibleCache.valueForKey(paramKey);
					if (dico == null) {
						dico = EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey);
						dicoTypeAccesCibleCache.setObjectForKey(dico, paramKey);
					}
					EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_ACCES);
					EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_CIBLE);
					// creation d'un enregistrement temporaire (pas dans la base)
					Droit fakeEODroitAnnuaireComposante = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
					fakeEODroitAnnuaireComposante.takeValueForKey(composanteAnnuaire, EODroit.TO_DROIT_COMPOSANTE_KEY);
					// ajout aux autres droits
					result = result.arrayByAddingObject(fakeEODroitAnnuaireComposante);
				}
			}
		}

		// -------------------------------------
		// -- droits deduits de la hierarchie --
		// -------------------------------------
		NSArray periodeList = EOEvaluationPeriode.fetchAllEvaluationPeriodes(ec);
		for (int i = 0; i < periodeList.count(); i++) {

			EOEvaluationPeriode periode = (EOEvaluationPeriode) periodeList.objectAtIndex(i);

			// N-1
			NSArray individuNm1List = EOVCandidatEvaluation.getVCandidatEvaluationForIndividuAndPeriodeInContext(
						individu, periode, false, true);
			for (int j = 0; j < individuNm1List.count(); j++) {
				EOVCandidatEvaluation vCandidatEvaluationNm1 = (EOVCandidatEvaluation) individuNm1List.objectAtIndex(j);

				for (int k = 0; k < EOMangueParametres.ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_1.count(); k++) {
					String paramKey = (String) EOMangueParametres.ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_1.objectAtIndex(k);
					// on ne recupere que les parametres actifs
					if (app.getBooleanParamValueForKey(paramKey)) {
						NSDictionary dico = (NSDictionary) dicoTypeAccesCibleCache.valueForKey(paramKey);
						if (dico == null) {
							dico = EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey);
							dicoTypeAccesCibleCache.setObjectForKey(dico, paramKey);
						}
						EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_ACCES);
						EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_CIBLE);
						// creation d'un enregistrement temporaire (pas dans la base)
						Droit fakeEODroitNm1 = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitNm1.takeValueForKey(vCandidatEvaluationNm1, EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY);
						fakeEODroitNm1.takeValueForKey(vCandidatEvaluationNm1.toIndividu(), EODroit.TO_DROIT_INDIVIDU_KEY);
						fakeEODroitNm1.takeValueForKey(vCandidatEvaluationNm1.toEvaluationPeriode(), EODroit.TO_DROIT_EVALUATION_PERIODE_KEY);
						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitNm1);
					}
				}
			}

			// N-p
			NSArray individuNmpList = EOVCandidatEvaluation.getVCandidatEvaluationForIndividuAndPeriodeInContext(
						individu, periode, true, false);
			for (int j = 0; j < individuNmpList.count(); j++) {
				EOVCandidatEvaluation vCandidatEvaluationNmp = (EOVCandidatEvaluation) individuNmpList.objectAtIndex(j);

				for (int k = 0; k < EOMangueParametres.ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_P.count(); k++) {
					String paramKey = (String) EOMangueParametres.ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_P.objectAtIndex(k);
					// on ne recupere que les parametres actifs
					if (app.getBooleanParamValueForKey(paramKey)) {
						NSDictionary dico = (NSDictionary) dicoTypeAccesCibleCache.valueForKey(paramKey);
						if (dico == null) {
							dico = EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey);
							dicoTypeAccesCibleCache.setObjectForKey(dico, paramKey);
						}
						EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_ACCES);
						EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_CIBLE);
						// creation d'un enregistrement temporaire (pas dans la base)
						Droit fakeEODroitNmp = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitNmp.takeValueForKey(vCandidatEvaluationNmp, EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY);
						fakeEODroitNmp.takeValueForKey(vCandidatEvaluationNmp.toIndividu(), EODroit.TO_DROIT_INDIVIDU_KEY);
						fakeEODroitNmp.takeValueForKey(vCandidatEvaluationNmp.toEvaluationPeriode(), EODroit.TO_DROIT_EVALUATION_PERIODE_KEY);
						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitNmp);
					}
				}
			}
		}

		NSMutableDictionary dicoResult = new NSMutableDictionary();
		// indiquer s'il a des droits deduits autres que les siens
		dicoResult.setObjectForKey(new Boolean(result.count() == 0), "hasLimitedHeritage");

		// -------------------------------------------------
		// -- droits par defaut de l'utilisateur connecte --
		// -------------------------------------------------

		for (int i = 0; i < EOMangueParametres.ARRAY_KEY_BOOLEAN_SELF.count(); i++) {
			String paramKey = (String) EOMangueParametres.ARRAY_KEY_BOOLEAN_SELF.objectAtIndex(i);
			// on ne recupere que les parametres actifs
			if (app.getBooleanParamValueForKey(paramKey)) {
				NSDictionary dico = (NSDictionary) dicoTypeAccesCibleCache.valueForKey(paramKey);
				if (dico == null) {
					dico = EOMangueParametres.getDicoTypeAccesCibleForParamKey(ec, paramKey);
					dicoTypeAccesCibleCache.setObjectForKey(dico, paramKey);
				}
				EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_ACCES);
				EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) dico.valueForKey(EOMangueParametres.DICO_KEY_TYPE_DROIT_CIBLE);

				// on fait un droit par type de cible
				if (EOTypeDroitCible.isTypeDroitCibleEvaluation(typeDroitCible)) {
					// les evaluations
					NSArray evaluationSelfList = individu.tosVCandidatEvaluation();
					for (int j = 0; j < evaluationSelfList.count(); j++) {
						EOVCandidatEvaluation evaluation = (EOVCandidatEvaluation) evaluationSelfList.objectAtIndex(j);
						//
						Droit fakeEODroitSelfEvaluation = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitSelfEvaluation.takeValueForKey(evaluation, EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY);
						fakeEODroitSelfEvaluation.takeValueForKey(evaluation.toIndividu(), EODroit.TO_DROIT_INDIVIDU_KEY);
						fakeEODroitSelfEvaluation.takeValueForKey(evaluation.toEvaluationPeriode(), EODroit.TO_DROIT_EVALUATION_PERIODE_KEY);

						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitSelfEvaluation);
					}

				} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
					// les fiches de poste
					NSArray ficheDePosteSelfList = individu.tosFicheDePoste();
					for (int j = 0; j < ficheDePosteSelfList.count(); j++) {
						EOFicheDePoste ficheDePoste = (EOFicheDePoste) ficheDePosteSelfList.objectAtIndex(j);
						//
						Droit fakeEODroitSelfFicheDePoste = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitSelfFicheDePoste.takeValueForKey(ficheDePoste, EODroit.TO_DROIT_FICHE_DE_POSTE_KEY);
						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitSelfFicheDePoste);
					}

				} else if (EOTypeDroitCible.isTypeDroitCibleFicheLolf(typeDroitCible)) {
					// les fiches lolf
					NSArray ficheLolfSelfList = individu.tosFicheLolf();
					for (int j = 0; j < ficheLolfSelfList.count(); j++) {
						EOFicheLolf ficheLolf = (EOFicheLolf) ficheLolfSelfList.objectAtIndex(j);
						//
						Droit fakeEODroitSelfFicheLolf = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitSelfFicheLolf.takeValueForKey(ficheLolf, EODroit.TO_DROIT_FICHE_LOLF_KEY);
						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitSelfFicheLolf);
					}

				} else if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
					// les postes
					NSArray posteSelfList = individu.tosPoste();
					for (int j = 0; j < posteSelfList.count(); j++) {
						EOPoste poste = (EOPoste) posteSelfList.objectAtIndex(j);
						//
						Droit fakeEODroitSelfPoste = createDroitOutOfContext(typeDroitAcces, typeDroitCible, individu);
						fakeEODroitSelfPoste.takeValueForKey(poste, EODroit.TO_DROIT_POSTE_KEY);
						// ajout aux autres droits
						result = result.arrayByAddingObject(fakeEODroitSelfPoste);
					}

				}
			}
		}

		dicoResult.setObjectForKey(result, "droitList");

		return dicoResult.immutableClone();
	}

	/**
	 * Obtenir l'ensemble des droits directs affectes a la personne
	 * <code>individu</code>
	 */
	public static NSArray<EODroit> fetchDroitsForIndividu(EOIndividu individu) {
		NSArray<EODroit> result = null;

		EOQualifier qual = ERXQ.equals(TO_INDIVIDU_KEY, individu);

		// lister tous les droits accordés a l'agent dans la base de donnees
		result = fetchDroits(
				individu.editingContext(), qual, null);

		return result;
	}

	/**
	 * Obtenir une liste de droits selon des parametres definis. Parmi les
	 * variables composante, service, individu, poste, ficheDePoste et ficheLolf,
	 * seul 1 ou 0 ne peuvent �tre pris en compte. Si plus d'un seul alors on
	 * prend le premier trouv�.
	 * 
	 * @return une liste de <code>EODroit</code>
	 */
	public static NSArray<EODroit> fetchDroits(
				EOEditingContext ec,
				EOTypeDroitAcces typeAcces,
				EOTypeDroitCible typeCible,
				EOStructure composante,
				EOStructure service,
				EOIndividu individu,
				EOEvaluationPeriode evaluationPeriode,
				EOPoste poste,
				EOFicheDePoste ficheDePoste,
				EOFicheLolf ficheLolf,
				boolean heritage
				) {
		NSArray<EODroit> result = new NSArray<EODroit>();

		/*
		 * org.cocktail.fwkcktlwebapp.common.CktlLog.log(
		 * "typeAcces="+typeAcces.dtaCode() + " " + "typeCible="+typeCible.dtcCode()
		 * + " " + "composante="+(composante != null ? composante.cStructure() :
		 * "__") + " " + "service="+(service != null ? service.cStructure() : "__")
		 * + " " + "individu="+(individu != null ? individu.persId() : "__") + " " +
		 * "evaluationPeriode="+(evaluationPeriode != null ?
		 * evaluationPeriode.strAnneeDebutAnneeFin() : "__") + " " + "poste="+(poste
		 * != null ? poste.posCode() : "__") + " " + "ficheDePoste="+(ficheDePoste
		 * != null ? "oui" : "__") + " " + "ficheLolf="+(ficheLolf != null ? "oui" :
		 * "__") + " " + "heritage="+heritage);
		 */
		if (heritage == false) {
			// ** droits directs **

			String strQual = new String();
			NSArray argsQual = new NSArray();

			// le type d'acces
			strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_TYPE_DROIT_ACCES_KEY, OP_AND);
			argsQual = appendArgumentsIfNotNull(argsQual, typeAcces);

			// le type de cible
			strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_TYPE_DROIT_CIBLE_KEY, OP_AND);
			argsQual = appendArgumentsIfNotNull(argsQual, typeCible);

			// periode d'evaluation fixee ?
			strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_EVALUATION_PERIODE_KEY, OP_AND);
			argsQual = appendArgumentsIfNotNull(argsQual, evaluationPeriode);

			// cible
			if (individu != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_INDIVIDU_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, individu);
			} else if (ficheDePoste != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_FICHE_DE_POSTE_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, ficheDePoste);
			} else if (ficheLolf != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_FICHE_LOLF_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, ficheLolf);
			} else if (poste != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_POSTE_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, poste);
			} else if (service != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_STRUCTURE_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, service);
			} else if (composante != null) {
				strQual = appendAndQualifierIfNotNull(strQual, argsQual, TO_DROIT_COMPOSANTE_KEY, OP_AND);
				argsQual = appendArgumentsIfNotNull(argsQual, composante);
			} else {
				// droit global
				if (evaluationPeriode == null) {
					// si la periode est vide, alors on regarde ceux qui ont un droit sur
					// TOUTES les periodes
					strQual += OP_AND + STR_QUAL_TOUTE_CIBLE_TOUTE_PERIODE;
				} else {
					// si la periode est vide, alors on regarde ceux qui ont un droit la
					// periode definie
					// plus haut (histoire de pas venir casser le qualifier ...)
					strQual += OP_AND + STR_QUAL_TOUTE_CIBLE_IGNORE_PERIODE;
				}
			}

			result = fetchDroits(ec, CktlDataBus.newCondition(strQual, argsQual), null);

		} else {

			// ** heritage >> on compose avec des droits directs en conservant le type
			// d'acces et le type de cible **

			// individu : on remonte au service avec la meme periode (si indiquee).
			// et en plus a l'individu sans periode (si indiquee)
			if (individu != null) {

				// remontee au service avec la periode (meme si elle est nulle)
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, composante, service, null, evaluationPeriode, null, null, null, false));

				// test supplementaire sans periode sur l'individu si la periode est
				// indiquee
				if (evaluationPeriode != null) {
					result = result.arrayByAddingObjectsFromArray(
								EODroit.fetchDroits(ec, typeAcces, typeCible, composante, service, individu, null, null, null, null, false));
				}

			}

			// fiche : on remonte au poste
			if (ficheDePoste != null || ficheLolf != null) {
				// pour le type de cible
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, composante, service, null, null, poste, null, null, false));
			}

			// poste : on remonte au service
			if (poste != null) {
				// pour le type de cible
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, composante, service, null, null, null, null, null, false));
			}

			// service : on remonte a la composante la meme periode (si indiquee).
			// et en plus au service sans periode (si indiquee)
			if (service != null) {

				// remontee au service avec la periode (meme si elle est nulle)
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, composante, null, null, evaluationPeriode, null, null, null, false));

				// test supplementaire sans periode sur le service si la periode est
				// indiquee
				if (evaluationPeriode != null) {
					result = result.arrayByAddingObjectsFromArray(
								EODroit.fetchDroits(ec, typeAcces, typeCible, composante, service, null, null, null, null, null, false));
				}

			}

			// service : on remonte au droit global et a la super composante la meme
			// periode (si indiquee).
			// et en plus au droit gloval et a la super composante sans periode (si
			// indiquee)
			if (composante != null) {

				// remontee au droit global avec la periode (meme si elle est nulle)
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, null, null, null, evaluationPeriode, null, null, null, false));

				// trouver la super composante si elle existe
				EOStructure structurePere = composante.toStructurePere();
				EOStructure superComposante = structurePere.toComposante();
				boolean shouldRemonterSuperComposante = (superComposante != null && superComposante != composante);

				if (shouldRemonterSuperComposante) {
					// pour le type de cible identique
					result = result.arrayByAddingObjectsFromArray(
								EODroit.fetchDroits(ec, typeAcces, typeCible, superComposante, null, null, evaluationPeriode, null, null, null, false));
				}

				// test supplementaire sans periode sur la composante si la periode est
				// indiquee
				if (evaluationPeriode != null) {
					result = result.arrayByAddingObjectsFromArray(
								EODroit.fetchDroits(ec, typeAcces, typeCible, composante, null, null, null, null, null, null, false));
				}

			}

			// droit global sur une periode : on remonte au droit global sur aucune
			// periode
			if (evaluationPeriode != null) {
				result = result.arrayByAddingObjectsFromArray(
							EODroit.fetchDroits(ec, typeAcces, typeCible, null, null, null, null, null, null, null, false));
			}

		}

		result = NSArrayCtrl.removeDuplicate(result);

		return result;
	}

	// type de qualifier
	private final static String OP_AND = " and ";
	// private final static String OP_OR = " or ";

	//
	private final static String NULL_VALUE_STRING = "nil";

	// qualifier visant toutes les cibles sur toutes les periodes
	public final static String STR_QUAL_TOUTE_CIBLE_TOUTE_PERIODE = "(" +
			TO_DROIT_COMPOSANTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_STRUCTURE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_INDIVIDU_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_EVALUATION_PERIODE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_V_CANDIDAT_EVALUATION_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_POSTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_FICHE_DE_POSTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_FICHE_LOLF_KEY + " = " + NULL_VALUE_STRING + ")";

	// qualifier visant toutes les cibles sans preciser la nation de periode
	public final static String STR_QUAL_TOUTE_CIBLE_IGNORE_PERIODE = "(" +
			TO_DROIT_COMPOSANTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_STRUCTURE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_INDIVIDU_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_POSTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_FICHE_DE_POSTE_KEY + " = " + NULL_VALUE_STRING + OP_AND +
			TO_DROIT_FICHE_LOLF_KEY + " = " + NULL_VALUE_STRING + ")";

	/**
	 * Methode interne pour ajouter des arguments a un qualifier
	 */
	private static String appendAndQualifierIfNotNull(
			String strQual, NSArray argsQual, String key, String typeQual) {
		// ajouter le separateur si qualifier non vide
		// et qu'il ne finit pas par une parenthese
		if (!StringCtrl.isEmpty(strQual)) {
			if (!strQual.trim().endsWith("(")) {
				strQual += typeQual;
			}
		}
		strQual += key + "=%@";
		return strQual;
	}

	/**
	 * Ajouter un argument a un qualifier
	 * 
	 * @param args
	 * @param value
	 * @return
	 */
	private static NSArray appendArgumentsIfNotNull(
				NSArray args, Object value) {
		if (value != null) {
			args = args.arrayByAddingObject(value);
		} else {
			args = args.arrayByAddingObject(NSKeyValueCoding.NullValue);
		}
		return args;
	}

	/**
	 * Construteur sans date de creation / modification
	 * 
	 * @param editingContext
	 * @param droTypeCible
	 * @param droType
	 * @param toIndividu
	 * @return
	 */
	public static EODroit createDroit(
				EOEditingContext editingContext,
				EOTypeDroitAcces typeAcces,
				EOTypeDroitCible typeCible,
				EOIndividu toIndividu) {
		EODroit eo = (EODroit) EOUtilities.createAndInsertInstance(editingContext, EODroit.ENTITY_NAME);
		eo.setDCreation(DateCtrl.now());
		eo.setDModification(DateCtrl.now());
		eo.setToTypeDroitAccesRelationship(typeAcces);
		eo.setToTypeDroitCibleRelationship(typeCible);
		eo.setToIndividuRelationship(toIndividu);
		eo.setNoIndividu(new Integer(toIndividu.noIndividu().intValue()));
		//
		return eo;
	}

	/**
	 * Creation d'un enregistrement qui ne sera pas enregistr� dans la base de
	 * donn�es
	 * 
	 * @param toIndividu
	 * @param droTypeCible
	 * @param droType
	 * @return
	 */
	private static Droit createDroitOutOfContext(
				EOTypeDroitAcces typeAcces,
				EOTypeDroitCible typeCible,
				EOIndividu toIndividu) {
		Droit droit = new Droit();
		droit.takeValueForKey(typeAcces, EODroit.TO_TYPE_DROIT_ACCES_KEY);
		droit.takeValueForKey(typeCible, EODroit.TO_TYPE_DROIT_CIBLE_KEY);
		droit.takeValueForKey(toIndividu, EODroit.TO_INDIVIDU_KEY);
		//
		return droit;
	}

	/**
	 * Le tip au passage de la souris attendu sur le droit (interface de saisie
	 * des droits)
	 * 
	 * @return
	 */
	public String toString() {
		String display = "";
		if (toDroitFicheDePoste() != null)
			display += "fiche de poste" + toDroitFicheDePoste().display();
		else if (toDroitFicheLolf() != null)
			display += "fiche LOLF " + toDroitFicheLolf().display();
		else if (toDroitPoste() != null) {
			display += "poste " + toDroitPoste().display();
			if (toDroitPoste().toAffectationDetailActuelle() != null &&
						toDroitPoste().toAffectationDetailActuelle().toAffectation() != null &&
						toDroitPoste().toAffectationDetailActuelle().toAffectation().toIndividu() != null)
				display += " (" + toDroitPoste().toAffectationDetailActuelle().toAffectation().toIndividu().display() + ")";
		} else if (toDroitIndividu() != null)
			display += "individu " + toDroitIndividu().display();
		else if (toDroitVCandidatEvaluation() != null)
			display += "evaluation " + toDroitVCandidatEvaluation().toIndividu().display() + " periode " + toDroitVCandidatEvaluation().toEvaluationPeriode().strDateDebutDateFin();
		else if (toDroitStructure() != null)
			display += "structure " + toDroitStructure().display();
		else if (toDroitComposante() != null)
			display += "composante " + toDroitComposante().display();
		else
			display += "global";

		try {
			display += " (" + toTypeDroitAcces().dtaLibelle() + ", " + toTypeDroitCible().dtcLibelle() + ")";// +
																																																				// "tempo dev : "
																																																				// +
																																																				// toIndividu().nomPrenom();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return display;
	}

	/**
     * 
     */
	public String cibleDisplay() {
		return cibleDisplay(this);
	}

	/**
	 * Affichage de la cible (interface d'etat des droits) Methode statique pour
	 * avoir un acces depuis la classe {@link Droit}
	 * 
	 * @return
	 */
	public static String cibleDisplay(I_Droit droit) {
		String display = "";
		if (droit.toDroitFicheDePoste() != null)
			display += droit.toDroitFicheDePoste().display();
		else if (droit.toDroitFicheLolf() != null)
			display += droit.toDroitFicheLolf().display();
		else if (droit.toDroitPoste() != null) {
			display += droit.toDroitPoste().display();
			if (droit.toDroitPoste().toAffectationDetailActuelle() != null &&
						droit.toDroitPoste().toAffectationDetailActuelle().toAffectation() != null &&
						droit.toDroitPoste().toAffectationDetailActuelle().toAffectation().toIndividu() != null)
				display += " (" + droit.toDroitPoste().toAffectationDetailActuelle().toAffectation().toIndividu().display() + ")";
		} else if (droit.toDroitIndividu() != null || droit.toDroitVCandidatEvaluation() != null) {
			// pour l'individu, cela ne peut concerner que les evaluations
			// (=entretiens)
			if (droit.toDroitIndividu() != null && droit.toDroitEvaluationPeriode() == null) {
				display += droit.toDroitIndividu().display() + " (tous ses entretiens)";
			} else {
				EOIndividu individuAEvaluer = null;
				EOEvaluationPeriode periode = null;
				// plantage possible si un gars n'est plus dans la vue mais bien dans
				// les droits
				boolean isEnregistrementExistant = true;
				try {
					droit.toDroitVCandidatEvaluation();
					droit.toDroitVCandidatEvaluation().toIndividu();
				} catch (Exception e) {
					isEnregistrementExistant = false;
				}
				if (isEnregistrementExistant == true) {
					// tout ce qui touche a la periode peut planter si le contrat ou
					// l'affectation n'est pas saisi ...
					if (droit.toDroitVCandidatEvaluation().toIndividu() != null &&
								droit.toDroitVCandidatEvaluation().toEvaluationPeriode() != null) {
						individuAEvaluer = droit.toDroitVCandidatEvaluation().toIndividu();
						periode = droit.toDroitVCandidatEvaluation().toEvaluationPeriode();
					} else {
						display += "** ATTENTION - affectation et/ou contrat-carriere non saisis ** - ";
						NSDictionary dicoPk = com.webobjects.eoaccess.EOUtilities.primaryKeyForObject(droit.editingContext(), droit.toDroitVCandidatEvaluation());
						Number noIndividu = (Number) dicoPk.valueForKey("noIndividu");
						individuAEvaluer = EOIndividu.findIndividuForNoIndividuInContext(droit.editingContext(), noIndividu);
						Number epeKey = (Number) dicoPk.valueForKey("epeKey");
						periode = EOEvaluationPeriode.findPeriodeForEpeKeyInContext(droit.editingContext(), epeKey);
					}
					display += individuAEvaluer.display() + " (entretien sur la periode " + periode.strDateDebutDateFin() + ")";
				} else {
					NSDictionary dicoPk = com.webobjects.eoaccess.EOUtilities.primaryKeyForObject(droit.editingContext(), (EOEnterpriseObject) droit);
					Number droKey = (Number) dicoPk.valueForKey("droKey");
					display += "** ERREUR - enregistrement " + EODroit.ENTITY_TABLE_NAME + " DRO_KEY=" + droKey.intValue() + " **";
				}
			}
		} else if (droit.toDroitStructure() != null) {
			// pour les structures, c'est soit pour les postes ou soit les
			// evaluations, il faut tester ...
			display += droit.toDroitStructure().display();
			// si evaluation, on rajouter le detail si toutes les periodes ou pas
			if (EOTypeDroitCible.isTypeDroitCibleEvaluation(droit.toTypeDroitCible())) {
				if (droit.toDroitEvaluationPeriode() == null) {
					display += " (tous les entretiens)";
				} else {
					display += " (entretiens sur la periode " + droit.toDroitEvaluationPeriode().strDateDebutDateFin() + ")";
				}
			}
		} else if (droit.toDroitComposante() != null) {
			// meme traitement pour les composanLtes
			display += droit.toDroitComposante().display();
			// si evaluation, on rajouter le detail si toutes les periodes ou pas
			if (EOTypeDroitCible.isTypeDroitCibleEvaluation(droit.toTypeDroitCible())) {
				if (droit.toDroitEvaluationPeriode() == null) {
					display += " (tous les entretiens)";
				} else {
					display += " (entretiens sur la periode " + droit.toDroitEvaluationPeriode().strDateDebutDateFin() + ")";
				}
			}
		} else {
			// meme traitement pour le droit global
			display += "global";
			// si evaluation, on rajouter le detail si toutes les periodes ou pas
			if (EOTypeDroitCible.isTypeDroitCibleEvaluation(droit.toTypeDroitCible())) {
				if (droit.toDroitEvaluationPeriode() == null) {
					display += " (tous les entretiens)";
				} else {
					display += " (entretiens sur la periode " + droit.toDroitEvaluationPeriode().strDateDebutDateFin() + ")";
				}
			}
		}
		return display;
	}

	public final static String TYPE_PROVENANCE_DIRECTE = "Directe";
	public final static String TYPE_PROVENANCE_HERITEE = "H&eacute;rit&eacute;e";
	public final static String TYPE_PROVENANCE_ANNUAIRE = "Annuaire";
	public final static String TYPE_PROVENANCE_HIERARCHIE = "Hi&eacute;rarchie";

	/**
	 * Indique si un individu a le droit compos� par le triplet (provenance,
	 * typeDroitAcces, typeDroitCible) sur la cible designee.
	 * 
	 * Se base sur les parametres par defaut definis dans MANGUE_PARAMETRES
	 * 
	 * @param individu
	 * @param typeDroitAcces
	 * @param typeDroitCible
	 * @param composante
	 * @param service
	 * @param individuEvalue
	 * @param periode
	 * @return
	 */
	public static boolean hasDroitFromParams(
				Application app,
				EOIndividu individu,
				String provenance,
				EOTypeDroitAcces typeDroitAcces,
				EOTypeDroitCible typeDroitCible,
				EOStructure structure,
				EOIndividu individuEvalue,
				EOEvaluationPeriode periode) {
		boolean result = false;

		// provenance annuaire : uniquement sur les services et les composantes
		if (provenance.equals(TYPE_PROVENANCE_ANNUAIRE)) {

			if (structure != null) {

				// determiner son niveau de responsabilit�
				boolean isResponsableComposante = (structure.toComposante().getIndividuDroitAnnuaireList().containsObject(individu));
				boolean isResponsableService = (structure.toResponsable() == individu);

				// parametres par defaut du responsable de composante
				if (isResponsableComposante) {
					if (EOTypeDroitAcces.isTypeDroitAccesCreation(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesModification(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheLolf(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesVisualisation(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheLolf(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesSuppression(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE);
						}
					}
				}

				// parametres par defaut du responsable de service
				// on fait un ou afin d'avoir le droit le plus large
				if (isResponsableService) {
					if (EOTypeDroitAcces.isTypeDroitAccesCreation(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_CREATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesModification(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheLolf(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesVisualisation(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheLolf(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF);
						}
					} else if (EOTypeDroitAcces.isTypeDroitAccesSuppression(typeDroitAcces)) {
						if (EOTypeDroitCible.isTypeDroitCiblePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE);
						} else if (EOTypeDroitCible.isTypeDroitCibleFicheDePoste(typeDroitCible)) {
							result = result || app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE);
						}
					}
				}

			}
		}

		// provenance hierarchie : se base sur l'arbre hierarchique
		else if (provenance.equals(TYPE_PROVENANCE_HIERARCHIE)) {

			// on va pas plus loin si ca ne concerne par une cible de type evaluation

			if (EOTypeDroitCible.isTypeDroitCibleEvaluation(typeDroitCible)) {

				if (individuEvalue != null && periode != null) {

					// determiner sa position dans l'arbre hierarchie par rapport a
					// l'individu evalue
					EOVCandidatEvaluation vCandidatEvaluation = EOVCandidatEvaluation.findRecordForIndividuAndPeriode(individuEvalue, periode);
					boolean isResponsableNp1 = (vCandidatEvaluation.toEvaluateur() == individu);
					boolean isResponsableNpp = vCandidatEvaluation.getNppList().containsObject(individu);

					// responsable N+1 : utiliser les droits par defaut
					if (isResponsableNp1) {
						if (EOTypeDroitAcces.isTypeDroitAccesCreation(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION);
						} else if (EOTypeDroitAcces.isTypeDroitAccesModification(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION);
						} else if (EOTypeDroitAcces.isTypeDroitAccesVisualisation(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION);
						}
					}

					// responsable N+p : utiliser les droits par defaut
					// on fait un "ou" afin d'avoir le droit le plus large
					if (isResponsableNpp) {
						if (EOTypeDroitAcces.isTypeDroitAccesCreation(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION);
						} else if (EOTypeDroitAcces.isTypeDroitAccesModification(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION);
						} else if (EOTypeDroitAcces.isTypeDroitAccesVisualisation(typeDroitAcces)) {
							result = app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION);
						}
					}
				}
			}

		}

		return result;
	}

	// la distinction par la cible
	public final static String IS_DROIT_GLOBAL_KEY = "isDroitGlobal";
	public final static String IS_DROIT_COMPOSANTE_KEY = "isDroitComposante";
	public final static String IS_DROIT_STRUCTURE_KEY = "isDroitStructure";
	public final static String IS_DROIT_POSTE_KEY = "isDroitPoste";
	public final static String IS_DROIT_FICHE_DE_POSTE_KEY = "isDroitFicheDePoste";
	public final static String IS_DROIT_FICHE_LOLF_KEY = "isDroitFicheLolf";
	public final static String IS_DROIT_INDIVIDU_KEY = "isDroitIndividu";
	public final static String IS_DROIT_V_CANDIDAT_EVALUATION_KEY = "isDroitVCandidatEvaluation";

	// un tableau les rassemblant pour utilisation dans des boucles par exemple
	public final static String[] DROIT_NATURE_LIST = new String[] {
			IS_DROIT_GLOBAL_KEY, IS_DROIT_COMPOSANTE_KEY, IS_DROIT_STRUCTURE_KEY, IS_DROIT_POSTE_KEY,
			IS_DROIT_FICHE_DE_POSTE_KEY, IS_DROIT_FICHE_LOLF_KEY, IS_DROIT_INDIVIDU_KEY, IS_DROIT_V_CANDIDAT_EVALUATION_KEY };

	public final static String NATURE_KEY = "nature";

	public final static String IS_DROIT_GLOBAL_LIBELLE = "Droit global";
	public final static String IS_DROIT_COMPOSANTE_LIBELLE = "Droit sur la composante";
	public final static String IS_DROIT_STRUCTURE_LIBELLE = "Droit sur le service";
	public final static String IS_DROIT_POSTE_LIBELLE = "Droit sur le poste";
	public final static String IS_DROIT_FICHE_DE_POSTE_LIBELLE = "Droit sur la fiche de poste";
	public final static String IS_DROIT_FICHE_LOLF_LIBELLE = "Droit sur la fiche LOLF";
	public final static String IS_DROIT_INDIVIDU_LIBELLE = "Droit sur toutes les evaluations de l'individu";
	public final static String IS_DROIT_V_CANDIDAT_EVALUATION_LIBELLE = "Droit sur l'evaluation";

	/**
	 * Obtenir le libelle de la nature d'un droit
	 * 
	 * @return
	 */
	public final static String getNatureLibelle(String method) {
		if (method.equals(IS_DROIT_GLOBAL_KEY)) {
			return IS_DROIT_GLOBAL_LIBELLE;
		} else if (method.equals(IS_DROIT_COMPOSANTE_KEY)) {
			return IS_DROIT_COMPOSANTE_LIBELLE;
		} else if (method.equals(IS_DROIT_STRUCTURE_KEY)) {
			return IS_DROIT_STRUCTURE_LIBELLE;
		} else if (method.equals(IS_DROIT_POSTE_KEY)) {
			return IS_DROIT_POSTE_LIBELLE;
		} else if (method.equals(IS_DROIT_FICHE_DE_POSTE_KEY)) {
			return IS_DROIT_FICHE_DE_POSTE_LIBELLE;
		} else if (method.equals(IS_DROIT_FICHE_LOLF_KEY)) {
			return IS_DROIT_FICHE_LOLF_LIBELLE;
		} else if (method.equals(IS_DROIT_INDIVIDU_KEY)) {
			return IS_DROIT_INDIVIDU_LIBELLE;
		} else if (method.equals(IS_DROIT_V_CANDIDAT_EVALUATION_KEY)) {
			return IS_DROIT_V_CANDIDAT_EVALUATION_LIBELLE;
		}
		return null;
	}

	//

	public final static String TOS_SERVICE_FLATTEN_KEY = "tosServiceFlatten";

	/**
     * 
     */
	public NSArray tosServiceFlatten() {
		return EODroit.tosServiceFlatten(this);
	}

	/**
	 * Effectuer un clone de l'ensembles des droits dont les cibles sont d'une
	 * periode vers une nouvelle (utilisé pour une création de période, afin de
	 * copier / coller l'ensemble des droits "annuels")
	 * 
	 * @param periodeFrom
	 * @param periodeTo
	 */
	public static void duplicateDroitPeriode(EOEditingContext ec, EOEvaluationPeriode periodeFrom, EOEvaluationPeriode periodeTo) {
		NSArray droitsPourPeriode = fetchDroits(
					ec,
					CktlDataBus.newCondition(TO_DROIT_EVALUATION_PERIODE_KEY + "=%@", new NSArray(periodeFrom)),
					null);

		for (int i = 0; i < droitsPourPeriode.count(); i++) {
			EODroit existingDroit = (EODroit) droitsPourPeriode.objectAtIndex(i);
			EODroit newDroit = createDroit(ec, existingDroit.toTypeDroitAcces(), existingDroit.toTypeDroitCible(), existingDroit.toIndividu());
			// on copie que les donnees relatives aux evaluations (fiches et poste pas
			// concern�)
			newDroit.setToDroitComposanteRelationship(existingDroit.toDroitComposante());
			newDroit.setToDroitStructureRelationship(existingDroit.toDroitStructure());
			newDroit.setToDroitIndividuRelationship(existingDroit.toDroitIndividu());
			// c'est a ce niveau qu'on indique la nouvelle periode
			newDroit.setToDroitEvaluationPeriodeRelationship(periodeTo);
		}
	}

	public boolean isDroitComposante() {
		return EODroit.isDroitComposante(this);
	}

	public boolean isDroitFicheDePoste() {
		return EODroit.isDroitFicheDePoste(this);
	}

	public boolean isDroitFicheLolf() {
		return EODroit.isDroitFicheLolf(this);
	}

	public boolean isDroitGlobal() {
		return EODroit.isDroitGlobal(this);
	}

	public boolean isDroitIndividu() {
		return EODroit.isDroitIndividu(this);
	}

	public boolean isDroitPoste() {
		return EODroit.isDroitPoste(this);
	}

	public boolean isDroitStructure() {
		return EODroit.isDroitStructure(this);
	}

	public boolean isDroitVCandidatEvaluation() {
		return EODroit.isDroitVCandidatEvaluation(this);
	}

	/**
	 * Racourci direct vers la ou les structures concernees (utilis� par
	 * l'inteface de gestion des postes par services)
	 */
	public static NSArray tosServiceFlatten(I_Droit droit) {
		NSArray serviceList = new NSArray();
		if (droit.isDroitFicheDePoste()) {
			serviceList = serviceList.arrayByAddingObject(droit.toDroitFicheDePoste().toPoste().toStructure());
		} else if (droit.isDroitFicheLolf()) {
			serviceList = serviceList.arrayByAddingObject(droit.toDroitFicheLolf().toPoste().toStructure());
		} else if (droit.isDroitPoste()) {
			serviceList = serviceList.arrayByAddingObject(droit.toDroitPoste().toStructure());
		} else if (droit.isDroitStructure()) {
			serviceList = serviceList.arrayByAddingObject(droit.toDroitStructure());
		} else if (droit.isDroitComposante()) {
			// pour une composante, on prend tous les sous services concern�s
			serviceList = serviceList.arrayByAddingObjectsFromArray(
						droit.toDroitComposante().tosSousServiceDeep(true));
		} else if (droit.isDroitGlobal()) {
			// le droit global engloble tous les services de l'etablissement
			serviceList = serviceList.arrayByAddingObjectsFromArray(
						EOStructure.findAllServicesInContext(droit.editingContext()));
		}
		return serviceList;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitGlobal(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitComposante(I_Droit droit) {
		return droit.toDroitComposante() != null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitVCandidatEvaluation(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() != null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitStructure(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() != null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitIndividu(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() != null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitFicheLolf(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() != null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitPoste(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() != null && droit.toDroitFicheDePoste() == null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isDroitFicheDePoste(I_Droit droit) {
		return droit.toDroitComposante() == null && droit.toDroitStructure() == null && droit.toDroitPoste() == null && droit.toDroitFicheDePoste() != null &&
				droit.toDroitFicheLolf() == null && droit.toDroitIndividu() == null && droit.toDroitVCandidatEvaluation() == null;
	}
}
