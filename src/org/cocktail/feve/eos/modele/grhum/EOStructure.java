package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.feve.app.finder.Finder;
import org.cocktail.feve.eos.modele.mangue.EOAffectation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOStructure extends _EOStructure {

	public EOStructure() {
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

	// METHODE RAJOUTEES

	public final static NSArray<Object> ARRAY_SORT = new NSArray<Object>(new Object[] {
				EOSortOrdering.sortOrderingWithKey(LL_STRUCTURE_KEY, EOSortOrdering.CompareAscending) });

	public final static String TO_RESPONSABLE_KEY = "toResponsable";
	public final static String TOS_STRUCTURES_FILLES_KEY = "tosStructuresFilles";
	public final static String TOS_SERVICE_PETITS_FILS_WITHOUT_SELF = "tosServicePetitsFilsWithoutSelf";

	public String display() {
		return displayLong();
	}

	/**
	 * on tronque le llStructure a 25 caracteres + lcStructure
	 */
	public String displayCourt() {
		String display = "";
		if (llStructure() != null) {
			display += (llStructure().length() > 25 ? llStructure().substring(0, 20) + "(...)" : llStructure());
		}
		if (lcStructure() != null) {
			display += " " + lcStructure();
		}
		display += (toStructurePere() == null ? "" : " (" + EOStructure.findComposanteForService((EOStructure) this).lcStructure() + ")");
		return display;
	}

	/**
	 * display tres court
	 */
	public String displayTresCourt() {
		String display = lcStructure();
		display += (toStructurePere() == null ? "" : " (" + EOStructure.findComposanteForService((EOStructure) this).lcStructure() + ")");
		return display;
	}

	/**
	 * display ultra court
	 */
	public String displayUltraCourt() {
		String display = lcStructure();
		return display;
	}

	/**
	 * On tronque le llStructure a 40 caracteres. Si la structure n'est pas un
	 * service, on affiche "archive' au lieu de la composante.
	 */
	public String displayLong() {
		String display = "";
		if (llStructure() != null) {
			display += (llStructure().length() > 40 ? llStructure().substring(0, 35) + "(...)" : llStructure());
		} else {
			display = this + "";
		}
		// certains services qui n'en sont plus ne sont pas dans V_Service
		// d'ou composante introuvable ...
		EOStructure structureComposante = EOStructure.findComposanteForService((EOStructure) this);
		display += (toStructurePere() == null ? "" : " (" +
				(structureComposante != null ? structureComposante.lcStructure() : "archive") + ")");
		return display;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public String displayOrganigramme() {
		return llStructure() + " (" + toStructurePere().lcStructure() + ")";
	}

	/**
	 * toutes les structures filles - petites files - etc
	 * 
	 * @return
	 */
	public NSArray tosStructuresFillesDeep() {
		NSArray records = new NSArray();
		for (int i = 0; i < tosStructuresFilles().count(); i++) {
			EOStructure uneStructure = (EOStructure) tosStructuresFilles().objectAtIndex(i);
			records = records.arrayByAddingObject(uneStructure);
			records = records.arrayByAddingObjectsFromArray(uneStructure.tosStructuresFillesDeep());
		}
		return records;
	}

	// les responsables administratifs (table FEV_REPART_STRUCTURE_RESP)
	public NSArray tosResponsableAdministratif() {
		return EOIndividu.findResponsableAdministratifForStructure(editingContext(), (EOStructure) this);
	}

	/**
	 * la structure est-elle une composante de l universite ?
	 * 
	 * @return
	 */
	public boolean isComposante() {
		return super.isStrComposante() != null && super.isStrComposante().equals("1");
	}

	/**
	 * la structure est-elle un service de l universite ?
	 * 
	 * @return
	 */
	public boolean isService() {
		return super.isStrService() != null && super.isStrService().equals("1");
	}

	/**
	 * trouver la composante dont d�pend un service
	 * 
	 * @return
	 */
	public EOStructure toComposante() {
		EOStructure composante = null;
		if (isComposante()) {
			composante = (EOStructure) this;
		} else if (toStructurePere() != null) {
			composante = toStructurePere().toComposante();
		}
		return composante;
	}

	public String cStructure() {
		return (String) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("cStructure");
	}

	public final static String TOS_SERVICE_FILS_KEY = "tosServiceFils";
	public final static String C_STRUCTURE_KEY = "cStructure";
	public final static String IS_SERVICE_KEY = "isService";
	public final static String IS_COMPOSANTE_KEY = "isComposante";

	/**
	 * Liste de tous les sous services au sens annuaire (on l'inclue si elle meme
	 * est aussi service) A utiliser par exemple pour un affichage a 2 niveaux : -
	 * 1 = composantes - 2 = services (le resultat de cette methode)
	 */
	public NSArray<EOStructure> tosServiceFils() {
		NSArray result = new NSArray();
		if (isService()) {
			// les fils directs (sans lui)
			result = NSArrayCtrl.flattenArray(
						(NSArray) tosStructuresFilles(
								CktlDataBus.newCondition(C_STRUCTURE_KEY + "<>%@ and " + IS_SERVICE_KEY + "=%@",
										new NSArray(new Object[] { this, Boolean.TRUE }))));
			// doublons
			result = NSArrayCtrl.removeDuplicate(result);
			// classement alphabetique par lcStructure (c'est ce qui est affich� dans
			// le Browser)
			result = CktlSort.sortedArray(result, LC_STRUCTURE_KEY);
		} else if (isComposante()) {
			result = result.arrayByAddingObject(this);
		}
		//
		return result;
	}

	/**
	 * La liste des individus ayant droit sur la structure du fait de la
	 * configuration de l'annuaire.
	 * 
	 * @return un <code>NSArray</code> de <code>EOIndividu</code>
	 */
	public NSArray<EOIndividu> getIndividuDroitAnnuaireList() {
		NSArray<EOIndividu> result = new NSArray<EOIndividu>();

		// remonter aux responsables des groupes interm�diaires jusqu'a la racine
		boolean shouldContinue = true;
		EOStructure structure = this;
		while (shouldContinue) {
			if (structure == structure.toStructurePere()) {
				shouldContinue = false;
			}
			if (structure.toResponsable() != null) {
				result = result.arrayByAddingObject(structure.toResponsable());
			}
			structure = structure.toStructurePere();
		}

		return result;
	}

	/**
	 * TODO switcher sur Rechercher tous les sous services de la structure. On
	 * arrete la recherche dans l'arbre s'il y a un trou (i.e. une structure pas
	 * service) ou bien si on trouve une composante (on l'incluera elle meme).
	 * Ajoute aussi la structure initiale a la liste
	 * 
	 * @param showArchive
	 * @return
	 */
	public NSArray<EOStructure> tosSousServiceDeep(boolean showArchive) {
		NSArray<EOStructure> result = new NSArray<EOStructure>();
		boolean shouldNotContinue = !showArchive && isArchive();
		if (!shouldNotContinue) {
			//
			if (isService()) {
				result = result.arrayByAddingObject(this);
			}
			//
			NSArray<EOStructure> servicesFils = tosServiceFils();
			for (int i = 0; i < servicesFils.count(); i++) {
				EOStructure service = servicesFils.objectAtIndex(i);
				if (cStructure() != service.cStructure()) {
					// TODO decommenter la ligne du dessous quand les composantes seront
					// correctement
					// saisies, i.e. il n'y aura qu'un seul et unique niveau de
					// composantes
					// if (service != service.toStructurePere() &&
					// !service.isComposante()) {
					if (service != service.toStructurePere()) {// &&
																											// !service.isComposante())
																											// {
						result = result.arrayByAddingObjectsFromArray(
									service.tosSousServiceDeep(showArchive));
					}
				}
			}
		}
		//
		return result;
	}

	/**
	 * Rechercher tous les sous services de la structure. On arrete la recherche
	 * dans l'arbre s'il y a un trou (i.e. une structure pas service) ou bien si
	 * on trouve une composante (on l'incluera elle meme). Ajoute aussi la
	 * structure initiale a la liste
	 * 
	 * @param showArchive
	 * @return
	 */
	public NSArray tosSousServiceUntilComposante(boolean showArchive) {
		NSArray result = new NSArray();
		boolean shouldNotContinue = !showArchive && isArchive();
		if (!shouldNotContinue) {
			//
			if (isService()) {
				result = result.arrayByAddingObject(this);
			}
			//
			NSArray servicesFils = tosServiceFils();
			for (int i = 0; i < servicesFils.count(); i++) {
				EOStructure service = (EOStructure) servicesFils.objectAtIndex(i);
				if (service != service.toStructurePere() && !service.isComposante()) {
					result = result.arrayByAddingObjectsFromArray(service.tosSousServiceUntilComposante(showArchive));
				}
			}
		}
		//
		return result;
	}

	public final static String IS_ARCHIVE_KEY = "isArchive";

	/**
	 * TODO soit mettre le groupe des archives dans GRHUM_PARAMETRES, soit
	 * utiliser les dates de validit� Permet de savoir si le groupe fait parti des
	 * archives (dont le structure pere ou la structure elle meme est celle des
	 * archives)
	 * 
	 * @return
	 */
	public boolean isArchive() {
		return isStrArchive() != null && isStrArchive().equals("1");
	}

	/**
	 * Indique si la structure est une racine
	 * 
	 * @return
	 */
	public boolean isRacine() {
		boolean isRacine = false;

		if (cStructure().equals(toStructurePere().cStructure()) &&
					cTypeStructure().equals("E")) {
			isRacine = true;
		}

		return isRacine;
	}

	/**
	 * Trouver la racine de l'annuaire
	 * 
	 * @param ec
	 * @return
	 */
	public static EOStructure findRacineInContext(EOEditingContext ec) {
		EOStructure racine = null;

		EOQualifier qual = CktlDataBus.newCondition(
				C_STRUCTURE_KEY + "=" + TO_STRUCTURE_PERE_KEY + "." + EOStructure.C_STRUCTURE_KEY + " and " + C_TYPE_STRUCTURE_KEY + "='E'");
		NSArray records = UtilDb.fetchArray(ec, ENTITY_NAME, qual, null);
		if (records.count() > 0) {
			racine = (EOStructure) records.lastObject();
		}
		return racine;
	}

	/**
	 * liste de tous les "sous-services" (recursivement) pour une structure donnee
	 * 
	 * @param ec
	 * @return
	 */
	public static NSArray findSousServiceForStructureInContext(EOEditingContext ec, EOStructure structure) {
		NSArray datasDico = new NSArray();
		NSArray records = new NSArray();
		// cas particulier de la racine, le CONNECT BY PRIOR pete une erreur oracle
		// CONNECT BY NOCYCLE PRIOR ne marche qu'avec oracle 10g
		String requete =
				"SELECT UNIQUE PERS_ID " +
						"FROM V_SERVICE " +
						"START WITH pers_id = %@ " +
						"CONNECT BY PRIOR c_structure = TO_NUMBER(c_structure_pere)";
		if (structure == structure.toStructurePere()) {
			for (int i = 0; i < structure.tosStructuresFilles().count(); i++) {
				EOStructure uneFille = (EOStructure) structure.tosStructuresFilles().objectAtIndex(i);
				if (uneFille != structure) {
					String uneRequete = StringCtrl.replace(requete, "%@", uneFille.persId().toString());
					datasDico = datasDico.arrayByAddingObjectsFromArray(Finder.rawRowsForSQL(ec, "Grhum", uneRequete));
				}
			}
			// on oublie pas de la compter si elle est elle-meme un service
			if (structure.isService()) {
				NSMutableDictionary dicoRacine = new NSMutableDictionary();
				dicoRacine.setObjectForKey(structure.persId(), "PERS_ID");
				datasDico = datasDico.arrayByAddingObject(dicoRacine.immutableClone());
			}
		} else {
			requete = StringCtrl.replace(requete, "%@", structure.persId().toString());
			datasDico = datasDico.arrayByAddingObjectsFromArray(Finder.rawRowsForSQL(ec, "Grhum", requete));
		}

		for (int i = 0; i < datasDico.count(); i++) {
			NSDictionary dico = (NSDictionary) datasDico.objectAtIndex(i);
			Number persId = (Number) dico.valueForKey("PERS_ID");
			records = records.arrayByAddingObject(findStructureForPersIdInContext(ec, persId));
		}
		return records;
	}

	/**
	 * tourver une structure par son persId
	 * 
	 * @param ec
	 * @param persId
	 * @return
	 */
	public static EOStructure findStructureForPersIdInContext(EOEditingContext ec, Number persId) {
		EOStructure structure = null;
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("persId = %@", new NSArray(persId));
		NSArray records = UtilDb.fetchArray(ec, "Structure", qual, null);
		if (records.count() > 0) {
			structure = (EOStructure) records.lastObject();
		}
		return structure;
	}

	/**
	 * trouver la composante dont d�pend un service
	 */
	public static NSArray findServicesForComposante(EOStructure composante) {
		NSArray records = UtilDb.fetchArray(composante.editingContext(), "VService", null, null);
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("toComposante = %@", new NSArray(composante));
		return (NSArray) EOQualifier.filteredArrayWithQualifier(records, qual).valueForKey("toStructure");
	}

	/**
	 * trouver la composante dont d�pend un service
	 * 
	 * @param ec
	 * @return
	 */
	public static EOStructure findComposanteForService(EOStructure service) {
		EOStructure composante = null;

		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("toStructure = %@", new NSArray(service));
		NSArray records = UtilDb.fetchArray(service.editingContext(), "VService", qual, null);
		if (records.count() > 0) {
			composante = (EOStructure) ((EOGenericRecord) records.lastObject()).valueForKey("toComposante");
		}

		return composante;
	}

	/**
	 * liste de tous les services
	 * 
	 * @param ec
	 * @return
	 */
	public static NSArray<EOStructure> findAllServicesInContext(EOEditingContext ec) {
		NSArray records = (NSArray) UtilDb.fetchArray(ec, "VService", null, null).valueForKey("toStructure");
		NSArray sortOrdering = new NSArray(ARRAY_SORT);
		return EOSortOrdering.sortedArrayUsingKeyOrderArray(records, sortOrdering);
	}

	/**
	 * Liste des agents non enseignants actuels affectés à la structure
	 * 
	 * @return
	 */
	public NSArray<EOIndividu> getIndividuAffecteVPersonnelNonEns() {
		NSArray<EOIndividu> result = null;

		NSTimestamp now = DateCtrl.now();

		String toVPersonnelActuelKey = EOAffectation.TO_INDIVIDU_KEY + "." + EOIndividu.TOS_V_PERSONNEL_NON_ENS_KEY;

		EOQualifier qual = CktlDataBus.newCondition(
					EOAffectation.TO_STRUCTURE_KEY + "=%@ and " + EOAffectation.D_DEB_AFFECTATION_KEY + "<=%@ and (" +
							EOAffectation.D_FIN_AFFECTATION_KEY + " >= %@ or " + EOAffectation.D_FIN_AFFECTATION_KEY + "=nil) and " +
							toVPersonnelActuelKey + "." + EOVPersonnelNonEns.TO_INDIVIDU_KEY + "." + EOIndividu.PERS_ID_KEY + "<> nil and " +
							toVPersonnelActuelKey + "." + EOVPersonnelNonEns.D_DEBUT_KEY + "<=%@ and (" +
							toVPersonnelActuelKey + "." + EOVPersonnelNonEns.D_FIN_KEY + " >= %@ or " + toVPersonnelActuelKey + "." + EOVPersonnelNonEns.D_FIN_KEY + "=nil)",
					new NSArray<Object>(new Object[] {
							this, now, now, now, now }));

		result = (NSArray<EOIndividu>) EOAffectation.fetchAffectations(editingContext(), qual, null).valueForKey(EOAffectation.TO_INDIVIDU_KEY);

		return result;
	}

	/**
	 * Obtenir le qualifier permettant de faire un filtrage sur le libelle
	 * 
	 * @param filtreComposante
	 * @return
	 */
	public static EOQualifier getFiltreQualifier(String filtreComposante) {
		EOQualifier qual = null;

		qual = CktlDataBus.newCondition(
					LC_STRUCTURE_KEY + " caseInsensitiveLike '*" + filtreComposante + "*' or " +
							LL_STRUCTURE_KEY + " caseInsensitiveLike '*" + filtreComposante + "*'");

		return qual;
	}
}
