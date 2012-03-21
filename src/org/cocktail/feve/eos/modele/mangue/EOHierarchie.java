package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOHierarchie extends _EOHierarchie {

	public EOHierarchie() {
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

		// ne pas autoriser 2 fois le même agent dans la hiérarchie

	}

	/**
	 * A appeler par les validateforsave, forinsert, forupdate.
	 * 
	 */
	private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {

	}

	// methodes rajoutees

	private static String[] couleurs = {
			"#E0FFFF", "#FFE0FF", "#FFFFE0", "#C3FDB8", "#F9B7FF"
		};

	public static NSArray arraySort = new NSArray(new EOSortOrdering[] {
				EOSortOrdering.sortOrderingWithKey("toIndividu.nomUsuel", EOSortOrdering.CompareAscending),
				EOSortOrdering.sortOrderingWithKey("toIndividu.prenom", EOSortOrdering.CompareAscending),
			}
			);

	/**
	 * Création d'un enregistrement
	 * 
	 * @param ec
	 * @param individuResp
	 * @param individu
	 * @param periode
	 * @return
	 */
	public static EOHierarchie newRecordInContext(
				EOEditingContext ec, EOIndividu individuResp, EOIndividu individu, EOEvaluationPeriode periode) {

		// ne pas autoriser qu'un agent soit N-1 plus d'une fois
		EOQualifier qual = CktlDataBus.newCondition(
					EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
					new NSArray<EOEvaluationPeriode>(periode));

		if (individu.tosHierarchieNm1(qual).count() > 0) {
			return null;
		}

		EOHierarchie newRecord = newDefaultRecordInContext(ec);
		if (individuResp != null) {
			newRecord.setToIndividuRespRelationship(individuResp);
		}
		newRecord.setToIndividuRelationship(individu);
		newRecord.setToEvaluationPeriodeRelationship(periode);

		return newRecord;
	}

	/**
	 * 
	 * @param ec
	 * @return
	 */
	private static EOHierarchie newDefaultRecordInContext(EOEditingContext ec) {
		EOHierarchie record = new EOHierarchie();
		ec.insertObject(record);
		return record;
	}

	/**
	 * 
	 * @return
	 */
	public EOHierarchie toHierarchieNp1() {
		EOHierarchie record = null;
		if (tosHierarchieNp1().count() > 0) {
			record = (EOHierarchie) tosHierarchieNp1().lastObject();
		}
		return record;
	}

	/**
     * 
     */
	public NSArray tosHierarchieNm1() {
		return CktlSort.sortedArray(super.tosHierarchieNm1(), "toIndividu.nomUsuel");
	}

	/**
	 * 
	 * @return
	 */
	public int niveau() {
		int niveau = 0;
		if (toHierarchieNp1() != null) {
			niveau = 1 + toHierarchieNp1().niveau();
		}
		return niveau;
	}

	/**
	 * 
	 * @return
	 */
	public String couleurHtml() {
		return couleurs[niveau() % 5];
	}

	private boolean voirNm1;

	/**
	 * interface 'arbre' : faut-il afficher les N-1
	 */
	public boolean voirNm1() {
		return voirNm1;
	}

	/**
	 * 
	 * @param value
	 */
	public void setVoirNm1(boolean value) {
		voirNm1 = value;
	}

	/**
     * 
     */
	public void voirTousNm1() {
		setVoirNm1(true);
		for (int i = 0; i < tosHierarchieNm1().count(); i++) {
			EOHierarchie uneHierarchieNm1 = (EOHierarchie) tosHierarchieNm1().objectAtIndex(i);
			uneHierarchieNm1.voirTousNm1();
		}
	}

	/**
     * 
     */
	public void cacherTousNm1() {
		setVoirNm1(false);
		for (int i = 0; i < tosHierarchieNm1().count(); i++) {
			EOHierarchie uneHierarchieNm1 = (EOHierarchie) tosHierarchieNm1().objectAtIndex(i);
			uneHierarchieNm1.cacherTousNm1();
		}
	}

	/**
	 * retrouve des enregistrements de N-p (p>1) - point d'entree = N+1
	 */
	public static NSArray findHierarchieForIndividuRespNmpInContext(
				EOEditingContext ec, EOIndividu individuResp, EOEvaluationPeriode periode) {
		NSArray nm1 = findHierarchieForIndividuRespInContext(ec, individuResp, periode);
		NSArray records = new NSArray();
		for (int i = 0; i < nm1.count(); i++) {
			EOHierarchie unNm1 = (EOHierarchie) nm1.objectAtIndex(i);
			records = records.arrayByAddingObject(unNm1);
			records = records.arrayByAddingObjectsFromArray(findHierarchieForIndividuRespNmpInContext(ec, unNm1.toIndividu(), periode));
		}
		return records;
	}

	/**
	 * retrouve des enregistrements des N-1 - point d'entree = N+1
	 * 
	 * @param ec
	 * @param individuResp
	 *          : si <em>null</em>, alors c'est un admin, donc on ramene tout
	 *          l'arbre
	 */
	public static NSArray findHierarchieForIndividuRespInContext(
				EOEditingContext ec, EOIndividu individuResp, EOEvaluationPeriode periode) {
		EOQualifier qual = CktlDataBus.newCondition(
					EOHierarchie.TO_INDIVIDU_RESP_KEY + "<>nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
					new NSArray(periode));
		if (individuResp != null) {
			qual = EOQualifier.qualifierWithQualifierFormat(
						EOHierarchie.TO_INDIVIDU_RESP_KEY + "=%@ and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
						new NSArray(new Object[] { individuResp, periode }));
		}
		return UtilDb.fetchArray(ec, EOHierarchie.ENTITY_NAME, qual, null);
	}

	/**
	 * @deprecated
	 * @see getHierarchieForEvaluation retrouve un enregistrement - point d'entree
	 *      = N-1
	 */
	public static EOHierarchie findHierarchieForIndividuInContext(
				EOEditingContext ec, EOIndividu individu, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
					EOHierarchie.TO_INDIVIDU_KEY + "=%@ and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
					new NSArray(new Object[] { individu, periode }));
		NSArray records = UtilDb.fetchArray(ec, EOHierarchie.ENTITY_NAME, qual, null);
		EOHierarchie record = null;
		if (records.count() > 0) {
			record = (EOHierarchie) records.lastObject();
		}
		return record;
	}

	/**
	 * retrouve un enregistrement - celui en haut de la pyramide
	 */
	public static EOHierarchie getEoHierarchieRacineForPeriodeInContext(
				EOEditingContext ec, EOEvaluationPeriode periode) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
					EOHierarchie.TO_INDIVIDU_RESP_KEY + "=nil and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
					new NSArray(periode));
		NSArray records = UtilDb.fetchArray(ec, EOHierarchie.ENTITY_NAME, qual, null);
		EOHierarchie record = null;
		if (records.count() > 0) {
			record = (EOHierarchie) records.lastObject();
		}
		return record;
	}

	/**
	 * Dupliquer un noeud de hierarchie
	 * 
	 * @param ec
	 * @param existingNode
	 * @param periodeTo
	 */
	private static EOHierarchie duplicateNode(EOEditingContext ec, EOHierarchie existingNode, EOEvaluationPeriode periodeTo) {
		EOHierarchie newNode = newRecordInContext(ec, existingNode.toIndividuResp(), existingNode.toIndividu(), periodeTo);
		NSArray recsChildren = existingNode.tosHierarchieNm1();
		for (int i = 0; i < recsChildren.count(); i++) {
			EOHierarchie existingChildren = (EOHierarchie) recsChildren.objectAtIndex(i);
			EOHierarchie childrenNewNode = duplicateNode(ec, existingChildren, periodeTo);
			newNode.addToTosHierarchieNm1Relationship(childrenNewNode);
		}
		return newNode;
	}

	/**
	 * Permet de copier coller un arbre hierarchique (pour la creation d'une
	 * nouvelle periode par exemple)
	 * 
	 * @param ec
	 * @param periodeFrom
	 * @param periodeTo
	 */
	public static void duplicateArbre(
				EOEditingContext ec, EOEvaluationPeriode periodeFrom, EOEvaluationPeriode periodeTo) {
		EOHierarchie recRoi = EOHierarchie.getEoHierarchieRacineForPeriodeInContext(ec, periodeFrom);
		EOHierarchie.duplicateNode(ec, recRoi, periodeTo);
	}

	/**
	 * Trouver l'enregistrement EOHierachie associe a un agent
	 * 
	 * @param evaluation
	 * @return
	 */
	public static EOHierarchie getHierarchieForEvaluation(A_EOEvaluationKeyValueCoding evaluation) {
		return fetchHierarchie(
					evaluation.editingContext(),
					CktlDataBus.newCondition(
							EOHierarchie.TO_INDIVIDU_KEY + "=%@ and " + EOHierarchie.TO_EVALUATION_PERIODE_KEY + "=%@",
							new NSArray(new Object[] {
									evaluation.toIndividu(), evaluation.toEvaluationPeriode() })));
	}

	/**
	 * Liste de tous les N-1 saisis dans la hiérarchie pour une periode
	 * 
	 * @param periode
	 * @return
	 */
	public static NSArray findAllEvaluateurForPeriode(EOEvaluationPeriode periode) {
		return (NSArray) fetchHierarchies(periode.editingContext(),
					CktlDataBus.newCondition(
							TO_INDIVIDU_KEY + "<> nil and " + TO_EVALUATION_PERIODE_KEY + "=%@",
							new NSArray(periode)),
					CktlSort.newSort(TO_INDIVIDU_RESP_KEY + ".nomPrenom")).valueForKey(TO_INDIVIDU_RESP_KEY);
	}

	/**
	 * Les enregistrements associés a un individu. Ces enregistrements sont celui
	 * ou il est N-1
	 * 
	 * @param individu
	 * @return
	 */
	public static NSArray getHierarchiesForIndividu(
				EOIndividu individu) {
		return fetchHierarchies(individu.editingContext(),
					CktlDataBus.newCondition(
							TO_INDIVIDU_KEY + "=%@", new NSArray(individu)), null);
	}

	/**
	 * L'enregistrement associé a un individu sur une periode. Cet enregistrement
	 * est celui ou il est N-1
	 * 
	 * @param individu
	 * @param periode
	 * @return
	 */
	public static EOHierarchie getHierarchieForIndividuAndPeriode(
				EOIndividu individu, EOEvaluationPeriode periode) {
		return fetchHierarchie(individu.editingContext(),
					CktlDataBus.newCondition(
							TO_INDIVIDU_KEY + "=%@ and " + TO_EVALUATION_PERIODE_KEY + "=%@",
							new NSArray(new Object[] {
									individu, periode })));
	}

	/**
	 * Effectuer la création de l'arbre hiérarchique d'après l'état du référentiel
	 * pour la période eoEvaluationPeriode. C'est la version java de la procédure
	 * SQL Fev_Creer_Hierarchie
	 * 
	 * @param eoEvaluationPeriode
	 *          la période a affecter à l'arbre
	 */
	public static void creerHierarchie(
				EOEvaluationPeriode eoEvaluationPeriode, EOStructure eoStructure)
			throws NSValidation.ValidationException {

		// trouver le responsable
		if (eoStructure.toResponsable() == null) {
			throw new NSValidation.ValidationException(
						"Impossible de créer la hiérarchie car le service \"" + eoStructure.llStructure() + "\"" +
								" cStructure=" + eoStructure.cStructure() + " n'a pas de responsable dans l'annuaire");
		}

		EOEditingContext ec = eoEvaluationPeriode.editingContext();

		// racine : création de l'enregistrement racine hierarchique
		if (eoStructure.isRacine()) {
			newRecordInContext(ec, null, eoStructure.toResponsable(), eoEvaluationPeriode);
		}

		// agents affectés => N-1 du responsable de la structure
		NSArray<EOIndividu> eoIndividuArray = eoStructure.getIndividuAffecteVPersonnelNonEns();

		for (EOIndividu eoIndividu : eoIndividuArray) {
			if (eoIndividu != eoStructure.toResponsable()) {
				newRecordInContext(ec, eoStructure.toResponsable(), eoIndividu, eoEvaluationPeriode);
			}
		}

		// liste des sous services
		NSArray<EOStructure> eoServiceFilsArray = eoStructure.tosServiceFils();
		for (EOStructure eoServiceFils : eoServiceFilsArray) {

			// ignorer les servicés archivés et les boucles
			if (eoServiceFils.isArchive() == false && eoStructure != eoServiceFils) {

				if (eoServiceFils.toResponsable() == null) {
					throw new NSValidation.ValidationException(
								"Impossible de créer la hiérarchie car le service \"" + eoServiceFils.llStructure() + "\"" +
										" cStructure=" + eoServiceFils.cStructure() + " n'a pas de responsable dans l'annuaire");
				}

				// le cdc est N-1 du responsable de la structure en cours
				if (eoStructure.toResponsable() != eoServiceFils.toResponsable()) {
					newRecordInContext(ec, eoStructure.toResponsable(), eoServiceFils.toResponsable(), eoEvaluationPeriode);
				}

				creerHierarchie(eoEvaluationPeriode, eoServiceFils);
			}
		}

	}

	/**
	 * Indique si l'enregistrement est une racine ou non
	 * 
	 * @return
	 */
	public boolean isRacine() {
		boolean isRacine = false;

		if (toIndividuResp() == null) {
			isRacine = true;
		}

		return isRacine;
	}
}
