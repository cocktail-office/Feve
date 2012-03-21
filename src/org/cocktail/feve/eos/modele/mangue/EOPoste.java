package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVPersonnelNonEns;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOPoste extends _EOPoste {

	public EOPoste() {
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

	// rajouts

	public final static String IS_FERME_KEY = "isFerme";
	public final static String IS_FUTUR_KEY = "isFutur";
	public final static String IS_OCCUPE_KEY = "isOccupe";
	public final static String IS_OUVERT_KEY = "isOuvert";
	public final static String IS_VACANT_KEY = "isVacant";

	public final static String IS_ENSEIGNANT_KEY = "isEnseignant";
	public final static String IS_NON_ENSEIGNANT_KEY = "isNonEnseignant";

	public final static String POSTE_NATURE_TOUTES = "Tous";
	public final static String POSTE_NATURE_NON_ENSEIGNANT = "Non enseignant";
	public final static String POSTE_NATURE_ENSEIGNANT = "Enseignant";
	public final static String POSTE_NATURE_INCONNUE = "Inconnu";
	public final static NSArray<String> POSTE_NATURE_ARRAY = new NSArray<String>(new String[] {
				POSTE_NATURE_TOUTES,
				POSTE_NATURE_NON_ENSEIGNANT,
				POSTE_NATURE_ENSEIGNANT,
				POSTE_NATURE_INCONNUE });

	public static NSArray ARRAY_SORT = new NSArray(new EOSortOrdering[] {
				EOSortOrdering.sortOrderingWithKey(
						"posCode",
						EOSortOrdering.CompareAscending) });

	private Number _posKey;

	// types de postes a afficher
	public final static String POSTE_TYPE_FERMES = "Ferm&eacute;s";
	public final static String POSTE_TYPE_VACANTS = "Vacants";
	public final static String POSTE_TYPE_OCCUPES = "Occup&eacute;s";
	public final static String POSTE_TYPE_EN_COURS = "En cours";
	public final static String POSTE_TYPE_TOUS = "Tous";

	public final static NSArray<String> POSTE_TYPE_ARRAY = new NSArray<String>(new String[] {
				POSTE_TYPE_TOUS,
				POSTE_TYPE_EN_COURS,
				POSTE_TYPE_OCCUPES,
				POSTE_TYPE_VACANTS,
				POSTE_TYPE_FERMES });

	public final static String POS_KEY_KEY = "posKey";

	public Number posKey() {
		if (_posKey == null)
			_posKey = (Number) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("posKey");
		return _posKey;
	}

	public String display() {
		return posCode() + " : " +
				(!StringCtrl.isEmpty(posLibelle()) && posLibelle().length() > 40 ? posLibelle().substring(0, 35) + "(...)" : posLibelle());
	}

	/**
	 * Affichage reduit au code et a l'occupation actuelle si elle existe.
	 * 
	 * @return
	 */
	public String displayCodeOccupationActuelle() {
		String display = "";

		display = display();

		display += " <";

		if (toAffectationDetailActuelle() != null) {
			display += toAffectationDetailActuelle().toAffectation().toIndividu().display();
		} else {
			display += "pas d'occupant actuellement";
		}

		display += ">";

		return display;
	}

	/**
	 * est-ce que les dates de validites sont actuelles
	 */
	public boolean isOuvert() {
		return DateCtrl.isBeforeEq(posDDebut(), DateCtrl.now()) &&
				(posDFin() == null || DateCtrl.isAfterEq(posDFin(), DateCtrl.now()));
	}

	/**
	 * la date de fin est passee
	 */
	public boolean isFerme() {
		return (posDFin() != null && DateCtrl.isBefore(posDFin(), DateCtrl.now()));
	}

	/**
	 * la date de fin est passee
	 */
	public boolean isVacant() {
		return toAffectationDetailActuelle() == null;
	}

	/**
	 * la date de fin est passee
	 */
	public boolean isOccupe() {
		return toAffectationDetailActuelle() != null;
	}

	/**
	 * la date de debut est pas encore atteinte
	 */
	public boolean isFutur() {
		return DateCtrl.isAfter(posDDebut(), DateCtrl.now());
	}

	/**
	 * Le poste concerne-t-il un personnel non enseignant (la valeur de
	 * l'affectation actuelle en priorit�, si non trouv�, on prend la derniere
	 * affectation)
	 * 
	 * @return
	 */
	public boolean isNonEnseignant() {
		boolean isNonEnseignant = false;
		if (toAffectationDetailActuelle() != null) {
			isNonEnseignant = toAffectationDetailActuelle().toAffectation().toIndividu().isNonEnseignant().equals("1");
		} else if (toAffectationDetailDerniere() != null) {
			// pas d'affectation actuelle, on regarde alors si le dernier occupant
			// etait
			// non enseignant lors de la derniere affectation au poste
			EOIndividu dernierOccupant = toAffectationDetailDerniere().toAffectation().toIndividu();
			NSTimestamp dateDerniereAffectation = toAffectationDetailDerniere().dFin();
			EOQualifier qual = null;
			if (dateDerniereAffectation != null) {
				qual = CktlDataBus.newCondition(
							EOVPersonnelNonEns.D_DEBUT_KEY + "<=%@ and " + EOVPersonnelNonEns.D_FIN_KEY + "=nil or " + EOVPersonnelNonEns.D_FIN_KEY + "=>%@",
							new NSArray(new NSTimestamp[] { dateDerniereAffectation, dateDerniereAffectation }));
			} else {
				qual = CktlDataBus.newCondition(EOVPersonnelNonEns.D_FIN_KEY + "=nil");
			}
			if (dernierOccupant.tosVPersonnelNonEns(qual).count() > 0) {
				isNonEnseignant = true;
			}
		}
		return isNonEnseignant;
	}

	/**
	 * Le poste concerne-t-il un personnel enseignant (la valeur de l'affectation
	 * actuelle en priorit�, si non trouv�, on prend la derniere affectation)
	 * 
	 * @return
	 */
	public boolean isEnseignant() {
		boolean isEnseignant = false;
		if (toAffectationDetailActuelle() != null) {
			isEnseignant = toAffectationDetailActuelle().toAffectation().toIndividu().isEnseignant().equals("1");
		} else if (toAffectationDetailDerniere() != null) {
			isEnseignant = toAffectationDetailDerniere().toAffectation().toIndividu().isEnseignant().equals("1");
		}
		return isEnseignant;
	}

	/**
	 * La dernier fiche de poste connue du poste
	 */
	public EOFicheDePoste toDerniereFicheDePoste() {
		NSArray fiches = tosFicheDePoste();
		EOFicheDePoste recFiche = null;
		if (fiches.count() > 0) {
			// on essaye de trouver celle qui a pas de date de fin
			EOQualifier qualFinNul = EOQualifier.qualifierWithQualifierFormat(EOFicheDePoste.FDP_D_FIN_KEY + " = nil", null);
			NSArray fichesFinNul = EOQualifier.filteredArrayWithQualifier(fiches, qualFinNul);
			if (fichesFinNul.count() > 0)
				recFiche = (EOFicheDePoste) fichesFinNul.lastObject();
			else {
				// pas de fin null, on prend la plus recente en date de fin
				NSArray arraySort = new NSArray(EOSortOrdering.sortOrderingWithKey(EOFicheDePoste.FDP_D_FIN_KEY, EOSortOrdering.CompareAscending));
				NSArray fichesDateAsc = EOSortOrdering.sortedArrayUsingKeyOrderArray(fiches, arraySort);
				recFiche = (EOFicheDePoste) fichesDateAsc.lastObject();
			}
		}
		return recFiche;
	}

	/**
	 * La dernier fiche LOLF connue du poste
	 */
	public EOFicheLolf toDerniereFicheLolf() {
		NSArray fiches = tosFicheLolf();
		EOFicheLolf recFiche = null;
		if (fiches.count() > 0) {
			// on essaye de trouver celle qui a pas de date de fin
			EOQualifier qualFinNul = EOQualifier.qualifierWithQualifierFormat(EOFicheLolf.FLO_D_FIN_KEY + " = nil", null);
			NSArray fichesFinNul = EOQualifier.filteredArrayWithQualifier(fiches, qualFinNul);
			if (fichesFinNul.count() > 0)
				recFiche = (EOFicheLolf) fichesFinNul.lastObject();
			else {
				// pas de fin null, on prend la plus recente en date de fin
				NSArray arraySort = new NSArray(EOSortOrdering.sortOrderingWithKey(EOFicheLolf.FLO_D_FIN_KEY, EOSortOrdering.CompareAscending));
				NSArray fichesDateAsc = EOSortOrdering.sortedArrayUsingKeyOrderArray(fiches, arraySort);
				recFiche = (EOFicheLolf) fichesDateAsc.lastObject();
			}
		}
		return recFiche;
	}

	public final static String TO_AFFECTATION_DETAIL_ACTUELLE_KEY = "toAffectationDetailActuelle";

	/**
	 * occupant du poste : - si le poste est ouvert : l'affectation en cours - si
	 * le poste ou la fiche fermé(e) : la derniere des affectations TODO renommer
	 * en toAffectationDetailCourante
	 */
	public EOAffectationDetail toAffectationDetailActuelle() {
		EOAffectationDetail record = null;
		if (isFerme()) {
			record = toAffectationDetailDerniere();
		} else if (isOuvert()) {
			EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
						"(" + EOAffectationDetail.ADE_DATE_DIFF_AFFECTATION_KEY + " = 1 and " +
								EOAffectationDetail.ADE_D_DEBUT_KEY + " <= %@ and (" + EOAffectationDetail.ADE_D_FIN_KEY + " >= %@ or " + EOAffectationDetail.ADE_D_FIN_KEY + " = nil)) or" +
								"(" + EOAffectationDetail.ADE_DATE_DIFF_AFFECTATION_KEY + " = 0 and " + EOAffectationDetail.TO_AFFECTATION_KEY + "." + EOAffectation.D_DEB_AFFECTATION_KEY + " <= %@ and " +
								"(" + EOAffectationDetail.TO_AFFECTATION_KEY + "." + EOAffectation.D_FIN_AFFECTATION_KEY + " >= %@ or " + EOAffectationDetail.TO_AFFECTATION_KEY + "." + EOAffectation.D_FIN_AFFECTATION_KEY + " = nil))",
						new NSArray(new NSTimestamp[] { DateCtrl.now(), DateCtrl.now(), DateCtrl.now(), DateCtrl.now() }));
			NSArray records = EOQualifier.filteredArrayWithQualifier(tosAffectationDetail(), qual);
			if (records.count() > 0) {
				record = (EOAffectationDetail) records.lastObject();
			}
		}
		return record;
	}

	public final static String TO_AFFECTATION_DETAIL_DERNIERE_KEY = "toAffectationDetailDerniere";

	/**
	 * La derniere occupation en date du poste
	 */
	public EOAffectationDetail toAffectationDetailDerniere() {
		EOAffectationDetail record = null;
		NSArray lesAffectationDetail = tosAffectationDetail();
		if (lesAffectationDetail.count() > 0) {
			record = (EOAffectationDetail) lesAffectationDetail.lastObject();
		}
		return record;
	}

	public EOStructure toComposante() {
		EOStructure record = null;
		if (toStructure() != null && toStructure().toComposante() != null) {
			record = toStructure().toComposante();
		}
		return record;
	}

	/**
	 * Toutes les fiches associees au poste
	 */
	public NSArray tosFiche() {
		return tosFicheDePoste().arrayByAddingObjectsFromArray(tosFicheLolf());
	}

	/**
	 * Indique si un ou plusieurs avertissements existent pour ce poste. Exemple :
	 * fiches non visees ...
	 */
	public boolean hasFicheWarning() {
		boolean hasWarning = false;
		// fiches non visees
		for (int i = 0; i < tosFiche().count(); i++) {
			A_Fiche fiche = (A_Fiche) tosFiche().objectAtIndex(i);
			hasWarning = hasWarning || fiche.hasWarning();
		}
		return hasWarning;
	}

	/**
	 * Indique si un ou plusieurs avertissements existent pour ce poste par
	 * rapport a ces occupations
	 */
	public boolean hasOccupationWarning() {
		boolean hasWarning = false;
		// fiches non visees
		for (int i = 0; i < tosAffectationDetail().count(); i++) {
			EOAffectationDetail affectationDetail = (EOAffectationDetail) tosAffectationDetail().objectAtIndex(i);
			hasWarning = hasWarning || affectationDetail.hasWarning();
		}
		return hasWarning;
	}

	/**
	 * Indique si la fiche de poste actuelle du poste pointe sur un emploi type de
	 * l'ancienne nomenclature
	 * 
	 * @return
	 */
	public boolean hasCurrentFicheDePosteEmploiTypeAncienWarning() {
		boolean hasWarning = false;

		NSArray ficheDePosteList = tosFicheDePoste();

		for (int i = 0; !hasWarning && i < ficheDePosteList.count(); i++) {
			EOFicheDePoste ficheDePoste = (EOFicheDePoste) ficheDePosteList.objectAtIndex(i);
			if (ficheDePoste.isActuelle() && ficheDePoste.toReferensEmplois() != null && ficheDePoste.toReferensEmplois().isArchive()) {
				hasWarning = true;
			}
		}

		return hasWarning;
	}

	/**
	 * Le descriptif des avertissements sur les fiches
	 */
	public String htmlFicheWarnMessage() {
		StringBuffer buff = new StringBuffer();
		// fiches non visees
		for (int i = 0; i < tosFiche().count(); i++) {
			A_Fiche fiche = (A_Fiche) tosFiche().objectAtIndex(i);
			if (!StringCtrl.isEmpty(fiche.htmlWarnMessage()))
				buff.append(fiche.htmlWarnMessage()).append("<hr>");
		}
		String result = buff.toString();
		// enlever le dernier separateur
		if (result.endsWith("<hr>"))
			result = result.substring(0, buff.length() - 4);
		// inserer le titre
		result = "<b>Avertissements relatifs au(x) visa(s) des fiches de ce poste</b><br/>" + result;
		return result;
	}

	/**
	 * Le descriptif des avertissements sur les occupations
	 */
	public String htmlOccupationWarnMessage() {
		StringBuffer buff = new StringBuffer();
		// fiches non visees
		for (int i = 0; i < tosAffectationDetail().count(); i++) {
			EOAffectationDetail occupation = (EOAffectationDetail) tosAffectationDetail().objectAtIndex(i);
			if (!StringCtrl.isEmpty(occupation.htmlWarnMessage()))
				buff.append(occupation.htmlWarnMessage()).append("<hr>");
		}
		String result = buff.toString();
		// enlever le dernier separateur
		if (result.endsWith("<hr>"))
			result = result.substring(0, buff.length() - 4);
		// inserer le titre
		result = "<b>Avertissements relatifs aux occupations de ce poste</b><br/>" + result;
		return result;
	}

	/**
	 * Classement chronologique
	 */
	/*
	 * public NSArray tosAffectationDetail() { return
	 * CktlSort.sortedArray(super.tosAffectationDetail(),
	 * EOAffectationDetail.ADE_DDEBUT_KEY); }
	 */

	public final static String TO_AFFECTATION_DETAIL_ACTUELLE_INDIVIDU_KEY =
			EOPoste.TO_AFFECTATION_DETAIL_ACTUELLE_KEY + "." +
					EOAffectationDetail.TO_AFFECTATION_KEY + "." +
					EOAffectation.TO_INDIVIDU_KEY;

	// recherche

	public final static String POS_LIBELLE_BASIC_KEY = POS_LIBELLE_KEY + "Basic";
	public final static String POS_CODE_BASIC_KEY = POS_CODE_KEY + "Basic";

	public String posLibelleBasic() {
		return StringCtrl.toBasicString(StringCtrl.chaineSansAccents(posLibelle()));
	}

	public String posCodeBasic() {
		return StringCtrl.toBasicString(StringCtrl.chaineSansAccents(posCode()));
	}

	private static EOPoste newDefaultRecordInContext(EOEditingContext ec) {
		EOPoste record = new EOPoste();
		ec.insertObject(record);
		return record;
	}

	public static EOPoste newRecordInContext(
				EOEditingContext ec,
				EOStructure structure,
				String code,
				String libelle,
				NSTimestamp dDebut,
				NSTimestamp dFin
			) {
		EOPoste newRecord = EOPoste.newDefaultRecordInContext(ec);

		newRecord.setToStructureRelationship(structure);
		newRecord.setCStructure(structure.cStructure());
		newRecord.setPosLibelle(libelle);
		newRecord.setPosCode(code);
		newRecord.setPosDDebut(dDebut);
		newRecord.setPosDFin(dFin);
		newRecord.setTemValide(OUI);

		return newRecord;
	}

	/**
	 * Obtenir un code de poste automatiquement. Le format est
	 * <annee><lc_structure>-<numero_libre_sur_4_chiffres>
	 * 
	 * @param strucutre
	 * @return
	 */
	public static String getDefaultPosCodeForStructure(EOStructure structure) {
		String posCode = DateCtrl.dateToString(DateCtrl.now()).substring(6, 10);

		posCode += structure.lcStructure();

		// tronquer à 25 caractères car le champ POS_CODE a une longueur max de 30
		// caractères
		if (posCode.length() > 25) {
			posCode = posCode.substring(0, 25);
		}

		// retrouver un numero libre pour cette annee
		EOQualifier qualPoste = EOQualifier.qualifierWithQualifierFormat(
					EOPoste.TO_STRUCTURE_KEY + "=%@ and " + EOPoste.POS_CODE_KEY + " like %@",
					new NSArray<Object>(new Object[] { structure, posCode + "*" }));
		NSArray arraySort = new NSArray(EOSortOrdering.sortOrderingWithKey(EOPoste.POS_CODE_KEY, EOSortOrdering.CompareAscending));
		NSArray recsPoste = UtilDb.fetchArray(
					structure.editingContext(), EOPoste.ENTITY_NAME, qualPoste, arraySort);
		// on trouve le premier code libre
		if (recsPoste.count() > 0) {
			boolean codeCreated = false;
			int i = 0;
			while (!codeCreated) {
				int noPoste = i + 1;
				// construction du code
				String tmpPoste = posCode + "-" +
						(noPoste < 10 ? "000" :
								(noPoste < 100 ? "00" :
										(noPoste < 1000 ? "0" : ""))) + noPoste;
				// il existe ou pas
				EOQualifier qualPosteId = EOQualifier.qualifierWithQualifierFormat(
							EOPoste.TO_STRUCTURE_KEY + "=%@ and " + EOPoste.POS_CODE_KEY + "=%@",
							new NSArray<Object>(new Object[] { structure, tmpPoste }));
				NSArray recsPosteId = EOQualifier.filteredArrayWithQualifier(recsPoste, qualPosteId);
				// existe pas : vendu !
				if (recsPosteId.count() == 0) {
					posCode = tmpPoste;
					codeCreated = true;
				}
				i++;
			}
		} else {
			posCode += "-0001";
		}

		return posCode;
	}

	/**
	 * Obtenir le qualifier attendu sur le dgPoste selon le type de poste
	 * selectionné
	 * 
	 * @param prefixToPoste
	 *          TODO
	 * @return
	 */
	public static EOQualifier getPosteTypeQualifier(String prefixToPoste, String posteTypeSelected) {
		EOQualifier posteTypeQual = null;

		String prefix = "";
		if (!StringCtrl.isEmpty(prefixToPoste)) {
			prefix = prefixToPoste + ".";
		}

		if (posteTypeSelected.equals(POSTE_TYPE_EN_COURS)) {
			posteTypeQual = CktlDataBus.newCondition(prefix + IS_OUVERT_KEY + "=%@", new NSArray<Boolean>(Boolean.TRUE));
		} else if (posteTypeSelected.equals(POSTE_TYPE_OCCUPES)) {
			posteTypeQual = CktlDataBus.newCondition(prefix + IS_FERME_KEY + "=%@ and " + prefix + IS_OCCUPE_KEY + "=%@",
						new NSArray<Boolean>(new Boolean[] { Boolean.FALSE, Boolean.TRUE }));
		} else if (posteTypeSelected.equals(POSTE_TYPE_VACANTS)) {
			posteTypeQual = CktlDataBus.newCondition(prefix + IS_FERME_KEY + "=%@ and " + prefix + IS_VACANT_KEY + "=%@",
						new NSArray<Boolean>(new Boolean[] { Boolean.FALSE, Boolean.TRUE }));
		} else if (posteTypeSelected.equals(EOPoste.POSTE_TYPE_FERMES)) {
			posteTypeQual = CktlDataBus.newCondition(prefix + IS_FERME_KEY + "=%@", new NSArray<Boolean>(Boolean.TRUE));
		}

		return posteTypeQual;
	}

	/**
	 * Obtenir le qualifier attendu sur le dgPoste selon la nature de poste
	 * selectionné
	 * 
	 * @return
	 */
	public static EOQualifier getPosteNatureQualifier(String prefixToPoste, String posteNatureSelected) {
		EOQualifier posteNatureQual = null;

		String prefix = "";
		if (!StringCtrl.isEmpty(prefixToPoste)) {
			prefix = prefixToPoste + ".";
		}

		if (posteNatureSelected.equals(POSTE_NATURE_NON_ENSEIGNANT)) {
			posteNatureQual = CktlDataBus.newCondition(prefix + IS_NON_ENSEIGNANT_KEY + "=%@", new NSArray<Boolean>(Boolean.TRUE));
		} else if (posteNatureSelected.equals(POSTE_NATURE_ENSEIGNANT)) {
			posteNatureQual = CktlDataBus.newCondition(prefix + IS_ENSEIGNANT_KEY + "=%@", new NSArray<Boolean>(Boolean.TRUE));
		} else if (posteNatureSelected.equals(POSTE_NATURE_INCONNUE)) {
			posteNatureQual = CktlDataBus.newCondition(
						prefix + IS_NON_ENSEIGNANT_KEY + "=%@ AND " + prefix + IS_ENSEIGNANT_KEY + "=%@",
						new NSArray<Boolean>(new Boolean[] { Boolean.FALSE, Boolean.FALSE }));
		}
		return posteNatureQual;
	}

	/**
	 * Donne la date de début de validité par défaut attendue pour la création
	 * d'une nouvelle fiche de poste pour ce poste
	 * 
	 * @return
	 */
	public NSTimestamp getDateDebutParDefautPourNouvelleFicheDePoste() {
		NSTimestamp dDebut = null;

		// par defaut, c'est la date de la dernier fiche +1 (si ca existe)
		// sinon, debut du poste
		A_Fiche derniereFiche = toDerniereFicheDePoste();
		if (derniereFiche != null) {
			dDebut = (derniereFiche.dFin() != null ? derniereFiche.dFin().timestampByAddingGregorianUnits(0, 0, 1, 0, 0, 0) : DateCtrl.now());
		} else {
			dDebut = posDDebut();
		}

		return dDebut;
	}

	// TODO migrer vers une gestion avec des expressions régulières
	public final static String MOTIF_TO_INDIVIDU_NOM_USUEL = "${toIndividu.nomUsuel}";
	public final static String MOTIF_TO_INDIVIDU_PRENOM = "${toIndividu.prenom}";

	/**
	 * Determiner le nom par défaut d'un poste lors sa création par Feve à partir
	 * d'une {@link EOAffectation}. Le motif est donné par le paramètre
	 * {@link EOMangueParametres#KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT}
	 * 
	 * @param eoAffectation
	 * 
	 * @return <code>null</code> si l'affectation est vide ou s'il n'y a pas de
	 *         motif
	 */
	public static String getLibelleDefautPourAffectation(EOAffectation eoAffectation) {
		String libelle = null;

		if (eoAffectation != null) {

			String motif = app().getParamValueForKey(EOMangueParametres.KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT);

			if (!StringCtrl.isEmpty(motif)) {

				libelle = motif;

				libelle = StringCtrl.replace(libelle, MOTIF_TO_INDIVIDU_NOM_USUEL, eoAffectation.toIndividu().nomUsuel());

				libelle = StringCtrl.replace(libelle, MOTIF_TO_INDIVIDU_PRENOM, eoAffectation.toIndividu().prenom());
			}

		}

		return libelle;
	}

	/**
	 * Obtenir le qualifier permettant de faire un filtrage sur le libelle
	 * 
	 * @param filtreComposante
	 * @return
	 */
	public static EOQualifier getFiltreQualifier(String filtrePoste) {
		EOQualifier qual = null;

		String occupantActuelNomKey =
				TO_AFFECTATION_DETAIL_ACTUELLE_KEY + "." +
						EOAffectationDetail.TO_AFFECTATION_KEY + "." +
						EOAffectation.TO_INDIVIDU_KEY + "." + EOIndividu.NOM_USUEL_KEY;

		String occupantActuelPrenomKey =
				TO_AFFECTATION_DETAIL_ACTUELLE_KEY + "." +
						EOAffectationDetail.TO_AFFECTATION_KEY + "." +
						EOAffectation.TO_INDIVIDU_KEY + "." + EOIndividu.PRENOM_KEY;

		qual = CktlDataBus.newCondition(
					POS_CODE_KEY + " caseInsensitiveLike '*" + filtrePoste + "*' or " +
							POS_LIBELLE_KEY + " caseInsensitiveLike '*" + filtrePoste + "*' or " +
							occupantActuelNomKey + " caseInsensitiveLike '*" + filtrePoste + "*' or " +
							occupantActuelPrenomKey + " caseInsensitiveLike '*" + filtrePoste + "*'");

		return qual;
	}

	/**
	 * Copier un poste vers un autre service
	 * 
	 * @param poste
	 * @param code
	 * @param libelle
	 * @param newPosDDebut
	 * @param newPosDFin
	 * @param eoStructureSelected
	 * @return
	 */
	public static EOPoste dupliquerPoste(EOPoste poste,
				String code, String libelle,
				NSTimestamp newPosDDebut, NSTimestamp newPosDFin,
				EOStructure eoStructure) {

		EOPoste eoPoste = newRecordInContext(
					poste.editingContext(), eoStructure, code, libelle, newPosDDebut, newPosDFin);

		return eoPoste;
	}
}
