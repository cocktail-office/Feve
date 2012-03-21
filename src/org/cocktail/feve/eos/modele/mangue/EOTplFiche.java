package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EOTplFiche extends _EOTplFiche {

	public EOTplFiche() {
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

	// ajouts

	// les noms des templates des fiches utilisees
	public final static String TPL_FICHE_EVALUATION_CODE = "EVALUATION";

	/**
	 * La liste des onglets contenus dans la période, selon l'evaluation (selon
	 * l'agent des agents peuvent êtres en plus ou en moins)
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public NSArray<EOTplOnglet> tosOnglet(
			EOEvaluationPeriode eoPeriode, EOEvaluation eoEvaluation) {

		//
		EOQualifier qual = getValiditeQualifier(
				EOTplOnglet.D_DEB_VAL_KEY, EOTplOnglet.D_FIN_VAL_KEY, eoPeriode.epeDDebut(), eoPeriode.epeDFin());

		NSArray<EOTplOnglet> result = tosTplOnglet(qual);

		// traitement si l'évaluation est précisée : on affiche les onglets selon le
		// statut de l'agent
		if (eoEvaluation != null) {

			NSTimestamp dDebut = eoEvaluation.toEvaluationPeriode().epeDDebut();
			NSTimestamp dFin = eoEvaluation.toEvaluationPeriode().epeDFin();

			EOQualifier qualStatut = null;

			if (eoEvaluation.toIndividu().isFonctionnaire(dDebut, dFin) ||
					eoEvaluation.toIndividu().isStagiaire(dFin)) {
				qualStatut = ERXQ.equals(
						EOTplOnglet.TEM_TITULAIRE_KEY, OUI);
			} else if (eoEvaluation.toIndividu().isContractuel(dDebut, dFin)) {
				qualStatut = ERXQ.equals(
						EOTplOnglet.TEM_CONTRACTUEL_KEY, OUI);
			}

			if (qualStatut != null) {
				result = EOQualifier.filteredArrayWithQualifier(result, qualStatut);
			}

		}

		result = CktlSort.sortedArray(result, EOTplOnglet.TON_POSITION_KEY);

		return result;
	}

	/**
	 * A voir si cela peut servir plus tard : afficher les onglet masqués mais
	 * pour lesquels il y a des données de saisies (par exemple, lorqu'on décidé
	 * de ne plus l'afficher à un fonctionnaire en cours d'année)
	 * 
	 * @param eoPeriode
	 * @param eoEvaluation
	 * @return
	 */
	private NSArray<EOTplOnglet> tosOngletHistorique(
			EOEvaluationPeriode eoPeriode, EOEvaluation eoEvaluation) {

		// gestion de l'historique pour les onglets dynamiques : on affiche les
		// onglets pour lesquels il y a des données (pour avoir l'affichage suite
		// à un masquage d'onglet postérieur)
		// TODO voir pour créer une table d'affiche qui contiendrait les dates de
		// validité et les statuts afin d'autoriser la gestion des onglets non
		// dynamiques
		// liste des onglets à rajouter

		NSMutableArray<EOTplOnglet> arrayOngletSupp = new NSMutableArray<EOTplOnglet>();
		boolean isOngletSuivantATraiter = false;
		// TODO mettre ce tableau en cache
		NSArray<EOTplOnglet> arrayOnglet = tosOnglet(eoPeriode, null);
		for (int i = 0; i < arrayOnglet.count(); i++) {
			isOngletSuivantATraiter = false;
			EOTplOnglet eoOnglet = arrayOnglet.objectAtIndex(i);
			NSArray<EOTplBloc> arrayBloc = eoOnglet.tosTplBlocSortedByPosition(eoPeriode);
			for (int j = 0; j < arrayBloc.count() && !isOngletSuivantATraiter; j++) {
				EOTplBloc eoBloc = arrayBloc.objectAtIndex(j);
				NSArray<EOTplItem> arrayItem = eoBloc.tosTplItemSorted();
				for (int k = 0; k < arrayItem.count() && !isOngletSuivantATraiter; k++) {
					EOTplItem eoItem = arrayItem.objectAtIndex(k);
					if (eoItem.isListe()) {
						EORepartFicheItem eoRepartFicheItem = eoItem.getRepartItemForEvaluation(eoEvaluation);
						if (eoRepartFicheItem != null && eoRepartFicheItem.toTplItemValeur() != null) {
							isOngletSuivantATraiter = true;
							arrayOngletSupp.addObject(eoOnglet);
						}
					} else if (eoItem.isChampLibre()) {
						if (!StringCtrl.isEmpty(eoItem.getStrChampLibre(eoEvaluation))) {
							isOngletSuivantATraiter = true;
							arrayOngletSupp.addObject(eoOnglet);
						}
					}
				}
			}
		}

		return arrayOngletSupp;
	}
}
