package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXQ;

/**
 * Le motif des classes representant une evaluation.
 * 
 * @see EOEvaluation
 * @see EOVCandidatEvaluation
 * 
 *      Ces objets sont filtres par des qualifier, il faut donc des methodes
 *      communes.
 * 
 * @author ctarade
 */
public abstract class A_EOEvaluationKeyValueCoding
		extends A_FeveCktlRecord {

	public final static String TO_INDIVIDU_KEY = "toIndividu";
	public final static String TOS_STRUCTURE_KEY = "tosStructure";
	public static final String TO_EVALUATION_PERIODE_KEY = "toEvaluationPeriode";
	public final static String TOS_LAST_FICHE_DE_POSTE_KEY = "tosLastFicheDePoste";
	public final static String TO_EVALUATEUR_KEY = "toEvaluateur";

	/**
	 * L'agent a evaluer
	 * 
	 * @return
	 */
	public abstract EOIndividu toIndividu();

	/**
	 * La cle primaire de l'agent a evaluer
	 * 
	 * @return
	 */
	public abstract Integer noIndividuVisible();

	/**
	 * La cle primaire de la periode
	 * 
	 * @return
	 */
	public abstract Integer epeKeyVisible();

	/**
	 * La periode d'evaluation concernee
	 * 
	 * @return
	 */
	public abstract EOEvaluationPeriode toEvaluationPeriode();

	/**
	 * Retourne l'objet <code>EOVCandidatEvaluation</code> associe a cet
	 * enregistrement.
	 */
	public abstract EOVCandidatEvaluation toVCandidatEvaluation();

	/**
	 * 
	 * @param value
	 */
	public abstract void setIsViseParResponsableRh(boolean value);

	/**
	 * les fiches de poste (les dernieres) sur lesquelles portent cette evaluation
	 * -> filtrage par rapport aux dates de l'evaluation
	 */
	public NSArray tosLastFicheDePoste() {
		NSArray records = toIndividu().tosFicheDePostePourDate(
				toEvaluationPeriode().epeDDebut(), toEvaluationPeriode().epeDFin());
		// classement chronologique
		records = CktlSort.sortedArray(records, EOFicheDePoste.FDP_D_DEBUT_KEY);
		NSArray recsPoste = NSArrayCtrl.removeDuplicate((NSArray) records.valueForKey(EOFicheDePoste.TO_POSTE_KEY));

		NSArray recsFicheDePoste = new NSArray();
		// ne conserver que les dernieres fiches en date
		for (int i = 0; i < recsPoste.count(); i++) {
			EOPoste poste = (EOPoste) recsPoste.objectAtIndex(i);
			NSArray recs = EOQualifier.filteredArrayWithQualifier(
					records, CktlDataBus.newCondition(EOFicheDePoste.TO_POSTE_KEY + "=%@", new NSArray(poste)));
			// on ne conserve que le dernier
			if (recs.count() > 0) {
				recsFicheDePoste = recsFicheDePoste.arrayByAddingObject(recs.lastObject());
			}
		}

		// ne conserver que celles dont l'occupation du poste est la deniere
		NSArray result = new NSArray();

		NSArray recsAffDet = NSArrayCtrl.flattenArray((NSArray) recsFicheDePoste.valueForKeyPath(EOFicheDePoste.TO_POSTE_KEY + "." + EOPoste.TOS_AFFECTATION_DETAIL_KEY));

		// d'abord celle non fermees sur la periode
		NSArray recsAffDetNonFermees = new NSArray();
		for (int i = 0; i < recsAffDet.count(); i++) {
			EOAffectationDetail recAffDet = (EOAffectationDetail) recsAffDet.objectAtIndex(i);
			if (recAffDet.toAffectation().toIndividu().noIndividu().intValue() == noIndividuVisible().intValue() && (
					recAffDet.dFin() == null || DateCtrl.isAfterEq(recAffDet.dFin(), toEvaluationPeriode().epeDFin()))) {
				recsAffDetNonFermees = recsAffDetNonFermees.arrayByAddingObject(recAffDet);
			}
		}

		// ce eoqualifier marche pas ???
		/*
		 * EOQualifier.filteredArrayWithQualifier( recsAffDet,
		 * CktlDataBus.newCondition( EOAffectationDetail.D_FIN_AFFECTATION_DETAIL +
		 * "=%@ OR " + EOAffectationDetail.D_FIN_AFFECTATION_DETAIL + ">=%@", new
		 * NSArray(new Object[]{NSKeyValueCoding.NullValue, evaDFin()})));
		 */
		if (recsAffDetNonFermees.count() > 0) {
			// on prend les fiches ayant ces occupation
			for (int i = 0; i < recsFicheDePoste.count(); i++) {
				EOFicheDePoste recFicheDePoste = (EOFicheDePoste) recsFicheDePoste.objectAtIndex(i);
				for (int j = 0; j < recsAffDetNonFermees.count(); j++) {
					EOAffectationDetail recAffDet = (EOAffectationDetail) recsAffDetNonFermees.objectAtIndex(j);
					if (recFicheDePoste.tosAffectationDetail().containsObject(recAffDet)) {
						result = result.arrayByAddingObject(recFicheDePoste);
						break;
					}
				}
			}
		} else {
			// on prend alors les dernieres en date
			result = recsFicheDePoste;
		}

		return result;
	}

	/**
	 * La liste des postes de rattachement de cette evaluation
	 * 
	 * @return
	 */
	public NSArray tosPoste() {
		return (NSArray) tosLastFicheDePoste().valueForKey(EOFicheDePoste.TO_POSTE_KEY);
	}

	/**
	 * La liste des services de rattachement de cette evaluation
	 * 
	 * @return
	 */
	public NSArray<EOStructure> tosStructure() {
		return (NSArray<EOStructure>) tosPoste().valueForKey(EOPoste.TO_STRUCTURE_KEY);
	}

	/**
	 * L'evaluateur direct d'apres la hierarchie
	 * 
	 * @return
	 */
	public EOIndividu toEvaluateur() {
		EOHierarchie hierachie = getEoHierachie();
		EOIndividu evaluateur = null;
		if (hierachie != null) {
			evaluateur = hierachie.toIndividuResp();
		}
		return evaluateur;
	}

	/**
	 * La liste des <code>EOIndividu</code> qui sont n+p. Elle est composee de
	 * tous les N+p (p>1)
	 * 
	 * @return
	 */
	public NSArray getNppList() {
		NSArray result = new NSArray();

		EOHierarchie hierachie = EOHierarchie.getHierarchieForEvaluation(this);

		if (hierachie != null) {
			// lecture : remonter aux responsables des groupes interm�diaires
			// jusqu'a la racine
			boolean shouldContinue = true;
			hierachie = hierachie.toHierarchieNp1();
			while (shouldContinue) {
				if (hierachie.toHierarchieNp1() == null) {
					shouldContinue = false;
				}
				if (hierachie.toIndividuResp() != null) {
					result = result.arrayByAddingObject(hierachie.toIndividuResp());
				}
				hierachie = hierachie.toHierarchieNp1();
			}
			// enlever les doublons
			result = NSArrayCtrl.removeDuplicate(result);
		}

		return result;
	}

	/**
	 * Pour une evaluation donnee, detailler le niveau dans l'arbre hierachique la
	 * position de l'individu responsable
	 * 
	 * @param fromIndividu
	 * @return
	 */
	public String tipHeritageHierarchie(EOIndividu fromIndividu) {
		EOHierarchie hierarchie = EOHierarchie.getHierarchieForEvaluation(this);
		int niveau = 0;

		boolean shouldContinue = true;

		while (shouldContinue) {
			if (hierarchie.toIndividu() == fromIndividu) {
				shouldContinue = false;
			} else {
				hierarchie = hierarchie.toHierarchieNp1();
				niveau++;
			}
		}
		return "Responsable N+" + niveau;
	}

	public final static CktlSort ARRAY_SORT =
					CktlSort.newSort(
							TO_EVALUATION_PERIODE_KEY + "." + EOEvaluationPeriode.EPE_D_DEBUT_KEY);

	/**
	 * retourne l'evaluation suivante de l'individu (celle dont la date de debut
	 * arrive en premier apres la date de fin de celle ci)
	 * 
	 * @return
	 */
	public EOEvaluation toEvaluationSuivante() {
		NSArray<EOEvaluation> evaluations = toIndividu().tosEvaluation();
		EOQualifier qual = ERXQ.greaterThan(
				TO_EVALUATION_PERIODE_KEY + "." + EOEvaluationPeriode.EPE_D_DEBUT_KEY, toEvaluationPeriode().epeDFin());
		NSArray<EOEvaluation> records = EOQualifier.filteredArrayWithQualifier(evaluations, qual);
		EOEvaluation record = null;
		if (records.count() > 0) {
			records = EOSortOrdering.sortedArrayUsingKeyOrderArray(records, ARRAY_SORT);
			record = records.objectAtIndex(0);
		}
		return record;
	}

	/**
	 * retourne l'evaluation precedente de l'individu (celle dont la date de fin
	 * arrive en dernier apres la date de debut de celle ci)
	 * 
	 * @return
	 */
	public EOEvaluation toEvaluationPrecedente() {
		NSArray<EOEvaluation> evaluations = toIndividu().tosEvaluation();
		EOQualifier qual = ERXQ.lessThan(
				TO_EVALUATION_PERIODE_KEY + "." + EOEvaluationPeriode.EPE_D_FIN_KEY, toEvaluationPeriode().epeDDebut());
		NSArray<EOEvaluation> records = EOQualifier.filteredArrayWithQualifier(evaluations, qual);
		EOEvaluation record = null;
		if (records.count() > 0) {
			records = EOSortOrdering.sortedArrayUsingKeyOrderArray(records, ARRAY_SORT);
			record = records.lastObject();
		}
		return record;
	}

	/**
	 * TODO
	 * 
	 * @param individuResp
	 * @return
	 */
	public boolean isModificationObjPrecAutorisee(EOIndividu individuResp) {
		boolean result = false;

		EOQualifier qual = ERXQ.and(
				ERXQ.equals(EODroitNouvelEntrant.TO_INDIVIDU_RESP_KEY, individuResp),
				ERXQ.equals(EODroitNouvelEntrant.TO_INDIVIDU_ENTRANT_KEY, toIndividu()),
				ERXQ.lessThan(EODroitNouvelEntrant.DNE_D_DEBUT_KEY, DateCtrl.now()),
				ERXQ.greaterThan(EODroitNouvelEntrant.DNE_D_FIN_KEY, DateCtrl.now()));

		NSArray<EODroitNouvelEntrant> array = EODroitNouvelEntrant.fetchDroitNouvelEntrants(
				editingContext(), qual, null);

		if (array.count() > 0) {
			result = true;
		}

		return result;
	}

	/**
	 * Liste des évaluations dont le responsable passé en paramètre est autorisé à
	 * faire la saisie des objectifs précédents
	 * 
	 * @param eoIndividuResp
	 * @return
	 */
	public static NSArray<A_EOEvaluationKeyValueCoding> getEoEvaluationKeyValueCodingNouvelEntrantForResponsable(EOIndividu eoIndividuResp) {
		NSArray<A_EOEvaluationKeyValueCoding> array = new NSArray<A_EOEvaluationKeyValueCoding>();

		NSArray<EODroitNouvelEntrant> eoDroitNouvelEntrantArray = EODroitNouvelEntrant.getEoDroitNouvelEntrantListForResponsable(eoIndividuResp);

		for (int i = 0; i < eoDroitNouvelEntrantArray.count(); i++) {
			EODroitNouvelEntrant eoDroitNouvelEntrant = eoDroitNouvelEntrantArray.objectAtIndex(i);
			EOVCandidatEvaluation eoVCandidatEvaluation = eoDroitNouvelEntrant.toIndividuEntrant().toVCandidatEvaluationForPeriode(
					eoDroitNouvelEntrant.toEvaluationPeriode());
			array = array.arrayByAddingObject(eoVCandidatEvaluation);
		}

		return array;
	}

	// cache

	private EOHierarchie eoHierarchie;

	private EOHierarchie getEoHierachie() {
		if (eoHierarchie == null) {
			eoHierarchie = EOHierarchie.getHierarchieForEvaluation(this);
		}
		return eoHierarchie;
	}

}
