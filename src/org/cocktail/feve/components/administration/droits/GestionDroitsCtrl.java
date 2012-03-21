package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EODroit;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.eof.ERXQ;

/**
 * Controleur de la page de gestion de droits
 * 
 * @author ctarade
 */
public class GestionDroitsCtrl
		extends A_ComponentControler {

	// bindings du browser
	public EOStructure composanteSelected;
	public EOStructure serviceSelected;
	public EOPoste posteSelected;
	public EOIndividu individuSelected;
	public EOFicheDePoste ficheDePosteSelected;
	public EOFicheLolf ficheLolfSelected;
	public EOEvaluationPeriode evaluationPeriodeSelected;
	public boolean isGestionPoste;

	// repetition des droits affiches
	public EODroit droitItem;

	// les types de droits d'acces
	private final EOTypeDroitAcces eoTypeDroitAccesCreation = EOTypeDroitAcces.typeDroitAccesCreation(ec());
	private final EOTypeDroitAcces eoTypeDroitAccesModification = EOTypeDroitAcces.typeDroitAccesModification(ec());
	private final EOTypeDroitAcces eoTypeDroitAccesSuppression = EOTypeDroitAcces.typeDroitAccesSuppression(ec());
	private final EOTypeDroitAcces eoTypeDroitAccesVisualisation = EOTypeDroitAcces.typeDroitAccesVisualisation(ec());
	private final NSArray<EOTypeDroitAcces> eoTypeDroitAccesPosteList = new NSArray<EOTypeDroitAcces>(new EOTypeDroitAcces[] {
			eoTypeDroitAccesCreation, eoTypeDroitAccesModification, eoTypeDroitAccesSuppression, eoTypeDroitAccesVisualisation });
	private final NSArray<EOTypeDroitAcces> eoTypeDroitAccesAgentList = new NSArray<EOTypeDroitAcces>(new EOTypeDroitAcces[] {
			eoTypeDroitAccesCreation, eoTypeDroitAccesModification, eoTypeDroitAccesVisualisation });
	public EOTypeDroitAcces typeDroitAccesItem;

	// les types de cibles
	private final EOTypeDroitCible eoTypeDroitCiblePoste = EOTypeDroitCible.eoTypeDroitCiblePoste(ec());
	private final EOTypeDroitCible eoTypeDroitCibleFicheDePoste = EOTypeDroitCible.eoTypeDroitCibleFicheDePoste(ec());
	private final EOTypeDroitCible eoTypeDroitCibleFicheLolf = EOTypeDroitCible.eoTypeDroitCibleFicheLolf(ec());
	private final EOTypeDroitCible eoTypeDroitCibleEvaluation = EOTypeDroitCible.eoTypeDroitCibleEvaluation(ec());
	private final NSArray<EOTypeDroitCible> eoTypeDroitCiblePosteList = new NSArray<EOTypeDroitCible>(new EOTypeDroitCible[] {
			eoTypeDroitCiblePoste, eoTypeDroitCibleFicheDePoste, eoTypeDroitCibleFicheLolf });
	private final NSArray<EOTypeDroitCible> eoTypeDroitCibleAgentList = new NSArray<EOTypeDroitCible>(new EOTypeDroitCible[] {
			eoTypeDroitCibleEvaluation });
	public EOTypeDroitCible typeDroitCibleItem;

	// les provenances
	public String strProvenanceItem;

	// liste des beneficiaires
	private NSMutableDictionary beneficiaireListDico;
	public EOIndividu beneficiaireItem;

	// la liste des <code>EODroit</code> sur la cible selectionnee
	private NSMutableDictionary droitListDico;

	// la liste de nature de cible (distinction uniquement pour l'heritage en
	// cas de type de cible diff�rent)
	// ce tableau est construit dynamiquement selon les donnees
	// (on ne garde que les nature ou il y a au moins 1 droit)
	// voir les constantes EODroit.IS_DROIT_XXXX
	private NSArray<String> natureList;
	public String natureItem;

	// ajout de droit : binding composante FieldSearchIndividu
	public EOIndividu individuSearch;
	public NSMutableDictionary<String, Boolean> checkAddDroitDico;
	public boolean isCheckedAddDroit;
	public boolean errAddDroitSelectionIndividu;
	public boolean errAddDroitSelectionCheckbox;
	public boolean errAddDroitBeneficiaireExistant;

	// modification de droits existants
	public NSMutableDictionary<String, Boolean> checkUpdateDroitDico;

	// gestion de l'affichage initial du panel d'ajout de droits
	private final static String STYLE_HIDDEN = "display:none";
	private final static String STYLE_VISIBLE = "display:inline";
	public boolean isFieldSearchIndividuUsed;

	public GestionDroitsCtrl(Session session) {
		super(session);
		initCtrl();
	}

	/**
	 * Initialisation du composant
	 */
	private void initCtrl() {
		checkAddDroitDico = new NSMutableDictionary<String, Boolean>();
		checkUpdateDroitDico = new NSMutableDictionary<String, Boolean>();
		clearCache();
		resetAddErrors();
	}

	/**
	 * Effacer le cache des donnees
	 */
	public void clearCache() {
		droitListDico = new NSMutableDictionary();
		beneficiaireListDico = new NSMutableDictionary();
	}

	/**
	 * Pointeur vers le composant control� (utilis� par {@link CktlAlertPage})
	 * 
	 * @return
	 */
	public GestionDroits getComponent() {
		return (GestionDroits) getComponent();
	}

	/**
	 * RAZ des temoins d'erreur d'ajout
	 */
	private void resetAddErrors() {
		errAddDroitSelectionCheckbox = errAddDroitSelectionIndividu = errAddDroitBeneficiaireExistant = false;
	}

	// methodes internes

	private boolean isOnlyAComposanteSelected() {
		return composanteSelected != null && serviceSelected == null;
	}

	private boolean isOnlyAServiceSelected() {
		return serviceSelected != null && posteSelected == null;
	}

	private boolean isOnlyAPosteSelected() {
		return posteSelected != null;
	}

	private boolean isOnlyAFicheDePosteSelected() {
		return ficheDePosteSelected != null;
	}

	private boolean isOnlyAFicheLolfSelected() {
		return ficheLolfSelected != null;
	}

	private boolean isOnlyAIndividuSelected() {
		return individuSelected != null;
	}

	private boolean isOnlyAVCandidatEvaluationSelected() {
		return getVCandidatEvaluationSelected() != null;
	}

	/**
	 * Obtenir l'enregistrement EOVCandidatEvaluation d'apres la selection
	 * {@link #individuSelected} et {@link #evaluationPeriodeSelected}
	 * 
	 * @return
	 */
	private EOVCandidatEvaluation getVCandidatEvaluationSelected() {
		EOVCandidatEvaluation vCandidatEvaluationSelected = null;
		if (individuSelected != null && evaluationPeriodeSelected != null) {
			vCandidatEvaluationSelected = EOVCandidatEvaluation.findRecordForIndividuAndPeriode(individuSelected, evaluationPeriodeSelected);
		}
		return vCandidatEvaluationSelected;
	}

	/**
	 * Creer une cle pour un dictionnaire d'apres un type d'acces, un type de
	 * cible voir d'un individu
	 * 
	 * @param typeDroitAcces
	 * @param typeDroitCible
	 * @return
	 */
	private String getDicoKey(
			EOTypeDroitAcces typeDroitAcces,
			EOTypeDroitCible typeDroitCible,
			EOIndividu individu,
			String nature) {
		StringBuffer sbKey = new StringBuffer();
		if (typeDroitAcces != null) {
			sbKey.append(EOTypeDroitAcces.DTA_CODE_KEY + "=" + typeDroitAcces.dtaCode());
		}
		if (typeDroitCible != null) {
			if (sbKey.length() > 0) {
				sbKey.append("|");
			}
			sbKey.append(EOTypeDroitCible.DTC_CODE_KEY + "=" + typeDroitCible.dtcCode());
		}
		if (individu != null) {
			if (sbKey.length() > 0) {
				sbKey.append("|");
			}
			sbKey.append(EOIndividu.PERS_ID_KEY + "=" + individu.persId().intValue());
		}
		if (!StringCtrl.isEmpty(nature)) {
			if (sbKey.length() > 0) {
				sbKey.append("|");
			}
			sbKey.append(EODroit.NATURE_KEY + "=" + nature);
		}
		return sbKey.toString();
	}

	/**
	 * Obtenir la cle dictionnaire d'un objet, puis en ressortir l'objet repondant
	 * a cette clause parmi le tableau <code>array</code>
	 * 
	 * @param dicoKey
	 * @param value
	 * @param array
	 * @return
	 */
	private Object getObjectForDicoKey(
			String dicoKey, String objectKey, NSArray<?> array) {
		// System.out.println("getObjectForDicoKey() dicoKey=" + dicoKey +
		// " objectKey=" + objectKey + " array=" + array.count());
		NSArray<String> keyValuesList = NSArray.componentsSeparatedByString(objectKey, "|");
		for (int i = 0; i < keyValuesList.count(); i++) {
			String keyValues = keyValuesList.objectAtIndex(i);
			NSArray<String> keyValueList = NSArray.componentsSeparatedByString(keyValues, "=");
			String key = keyValueList.objectAtIndex(0);
			if (key.equals(dicoKey)) {
				Object value = keyValueList.objectAtIndex(1);
				try {
					// filtrage sur des EOGenericRecord
					EOQualifier qual = ERXQ.equals(dicoKey, value);
					return EOQualifier.filteredArrayWithQualifier(array, qual).objectAtIndex(0);
				} catch (Exception e) {
					// a defaut, filtrage sur un String
					EOQualifier qual = ERXQ.equals("toString", value);
					return EOQualifier.filteredArrayWithQualifier(array, qual).objectAtIndex(0);
				}
			}
		}
		return null;
	}

	/**
	 * Extraire l'enregistrement {@link EOTypeDroitAcces} associe a une cle
	 * 
	 * @param key
	 * @return
	 */
	private EOTypeDroitAcces getEOTypeDroitAccesForDicoKey(String key) {
		return (EOTypeDroitAcces) getObjectForDicoKey(EOTypeDroitAcces.DTA_CODE_KEY, key, getTypeDroitAccesList());
	}

	/**
	 * Extraire l'enregistrement {@link EOTypeDroitCible} associe a une cle
	 * 
	 * @param key
	 * @return
	 */
	private EOTypeDroitCible getEOTypeDroitCibleForDicoKey(String key) {
		return (EOTypeDroitCible) getObjectForDicoKey(EOTypeDroitCible.DTC_CODE_KEY, key, getTypeDroitCibleList());
	}

	/**
	 * Extraire l'enregistrement {@link EOIndividu} associe a une cle
	 * 
	 * @param key
	 * @return
	 */
	private EOIndividu getEOIndividuForDicoKey(String key) {
		NSArray<EOIndividu> beneficiaireArray = getBeneficiaireList(EODroit.TYPE_PROVENANCE_DIRECTE);
		beneficiaireArray = beneficiaireArray.arrayByAddingObjectsFromArray(getBeneficiaireList(EODroit.TYPE_PROVENANCE_HERITEE));
		return (EOIndividu) getObjectForDicoKey(EOIndividu.PERS_ID_KEY, key, beneficiaireArray);
	}

	/**
	 * Extraire la valeur de la nature associe a une cle
	 * 
	 * @param key
	 * @return
	 */
	private String getStrNatureForDicoKey(String key) {
		return (String) getObjectForDicoKey(EODroit.NATURE_KEY, key, new NSArray(EODroit.DROIT_NATURE_LIST));
	}

	/**
	 * Creer un enregistrement {@link EODroit} d'apres la selection du browser et
	 * les parametres de type. Si la nature est specifiee, alors on va devoir
	 * creer le droit sur un niveau plus haut (ex: individu,nature=service, on
	 * cree sur le service)
	 * 
	 * @return
	 */
	private EODroit createEODroit(
			EOTypeDroitAcces typeDroitAcces,
			EOTypeDroitCible typeDroitCible,
			EOIndividu individu,
			String nature) {
		EODroit recDroit = EODroit.createDroit(
				ec(), typeDroitAcces, typeDroitCible, individu);
		// cas d'une creation d'un droit direct
		if (StringCtrl.isEmpty(nature)) {
			if (isOnlyAVCandidatEvaluationSelected()) {
				recDroit.setToDroitVCandidatEvaluationRelationship(getVCandidatEvaluationSelected());
			} else if (isOnlyAIndividuSelected()) {
				recDroit.setToDroitIndividuRelationship(individuSelected);
				recDroit.setDroNoIndividu(new Integer(individuSelected.noIndividu().intValue()));
			} else if (isOnlyAFicheDePosteSelected()) {
				recDroit.setToDroitFicheDePosteRelationship(ficheDePosteSelected);
			} else if (isOnlyAFicheLolfSelected()) {
				recDroit.setToDroitFicheLolfRelationship(ficheLolfSelected);
			} else if (isOnlyAPosteSelected()) {
				recDroit.setToDroitPosteRelationship(posteSelected);
			} else if (isOnlyAServiceSelected()) {
				recDroit.setToDroitStructureRelationship(serviceSelected);
			} else if (isOnlyAComposanteSelected()) {
				recDroit.setToDroitComposanteRelationship(composanteSelected);
			}
		} else {
			// cas d'un creation d'un droit indirect
			if (nature.equals(EODroit.IS_DROIT_V_CANDIDAT_EVALUATION_KEY)) {
				recDroit.setToDroitVCandidatEvaluationRelationship(getVCandidatEvaluationSelected());
			} else if (nature.equals(EODroit.IS_DROIT_INDIVIDU_KEY)) {
				recDroit.setToDroitIndividuRelationship(individuSelected);
				recDroit.setDroNoIndividu(new Integer(individuSelected.noIndividu().intValue()));
			} else if (nature.equals(EODroit.IS_DROIT_FICHE_DE_POSTE_KEY)) {
				recDroit.setToDroitFicheDePosteRelationship(ficheDePosteSelected);
			} else if (nature.equals(EODroit.IS_DROIT_FICHE_LOLF_KEY)) {
				recDroit.setToDroitFicheLolfRelationship(ficheLolfSelected);
			} else if (nature.equals(EODroit.IS_DROIT_POSTE_KEY)) {
				recDroit.setToDroitPosteRelationship(posteSelected);
			} else if (nature.equals(EODroit.IS_DROIT_STRUCTURE_KEY)) {
				recDroit.setToDroitStructureRelationship(serviceSelected);
			} else if (nature.equals(EODroit.IS_DROIT_COMPOSANTE_KEY)) {
				recDroit.setToDroitComposanteRelationship(composanteSelected);
			}
		}
		return recDroit;
	}

	/**
	 * Filter des enregistrements du tableau {@link #getDroitList()}
	 * 
	 * @param typeDroitAcces
	 * @param typeDroitCible
	 * @param individu
	 * @param nature
	 * @return
	 */
	private NSArray<EODroit> filterDroitList(
			EOTypeDroitAcces typeDroitAcces,
			EOTypeDroitCible typeDroitCible,
			EOIndividu individu,
			String nature,
			String strProvenance) {
		StringBuffer sbQual = new StringBuffer();
		NSArray<Object> args = new NSArray<Object>();
		if (typeDroitAcces != null) {
			sbQual.append(EODroit.TO_TYPE_DROIT_ACCES_KEY + "=%@");
			args = args.arrayByAddingObject(typeDroitAcces);
		}
		if (typeDroitCible != null) {
			if (sbQual.length() > 0) {
				sbQual.append(" and ");
			}
			sbQual.append(EODroit.TO_TYPE_DROIT_CIBLE_KEY + "=%@");
			args = args.arrayByAddingObject(typeDroitCible);
		}
		if (individu != null) {
			if (sbQual.length() > 0) {
				sbQual.append(" and ");
			}
			sbQual.append(EODroit.TO_INDIVIDU_KEY + "=%@");
			args = args.arrayByAddingObject(individu);
		}
		if (!StringCtrl.isEmpty(nature)) {
			if (sbQual.length() > 0) {
				sbQual.append(" and ");
			}
			sbQual.append(nature + "=%@");
			args = args.arrayByAddingObject(Boolean.TRUE);
		}
		return EOQualifier.filteredArrayWithQualifier(
				getDroitList(strProvenance), CktlDataBus.newCondition(
						sbQual.toString(), args));
	}

	// display

	/**
	 * Affiche le libelle de la selection du browser. Si aucune selection, on
	 * considere qu'on est sur du droit global.
	 */
	public String cibleDisplay() {
		String display = "Globale";
		if (ficheDePosteSelected != null) {
			display = "Fiche de poste : " + ficheDePosteSelected.display();
		} else if (ficheLolfSelected != null) {
			display = "Fiche LOLF : " + ficheLolfSelected.display();
		} else if (getVCandidatEvaluationSelected() != null) {
			display = "Evaluation : " + getVCandidatEvaluationSelected().display();
		} else if (individuSelected != null) {
			display = "Toutes les entretiens professionnels de " + individuSelected.display();
		} else if (posteSelected != null) {
			display = "Poste : " + posteSelected.display();
		} else if (serviceSelected != null) {
			display = "Service : " + serviceSelected.display();
		} else if (composanteSelected != null) {
			display = "Composante : " + composanteSelected.display();
		}
		return display;
	}

	/**
	 * Detail du pourquoi du droit
	 */
	public String getStrBeneficiaireItemTip() {
		String tip = "";

		// provenance annuaire : le detail de la responsabilit� sur le group
		if (strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_ANNUAIRE)) {
			tip = beneficiaireItem.tipHeritageAnnuaire(serviceSelected != null ? serviceSelected : composanteSelected);
		} else if (strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HIERARCHIE)) {
			// provenance hierachie : le positionnement dans l'arbre
			tip = getVCandidatEvaluationSelected().tipHeritageHierarchie(beneficiaireItem);
		} else if (strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HERITEE)) {
			// provenance heritee : afficher le libelle de la nature
			tip = EODroit.getNatureLibelle(natureItem);
		}

		return tip;
	}

	/**
	 * Le nombre de lignes a fusionner sur le libelle de la provenance.
	 * 
	 * @return
	 */
	public int getTdProvenanceRowspan() {
		int total = 0;
		if (isProvenanceHeritee()) {
			// pour la provenance heritee, ce sont les beneficiaires par le nombre de
			// nature
			for (int i = 0; i < getBeneficiaireList(strProvenanceItem).count(); i++) {
				EOIndividu beneficiaire = (EOIndividu) getBeneficiaireList(strProvenanceItem).objectAtIndex(i);
				for (int j = 0; j < EODroit.DROIT_NATURE_LIST.length; j++) {
					String nature = (String) EODroit.DROIT_NATURE_LIST[j];
					if (filterDroitList(null, null, beneficiaire, nature, strProvenanceItem).count() > 0) {
						total++;
					}
				}
			}
		} else {
			// pour le reste, c'est uniquement le nombre de beneficiaires
			total = getBeneficiaireList(strProvenanceItem).count();
		}
		return total;
	}

	/**
	 * Gestion de la visibilite de la zone d'ajout de droit apres rechargement de
	 * la page. Elle doit etre masquee par defaut, sauf si une recherche
	 * d'individu vient juste d'etre faite.
	 * 
	 * @return
	 */
	public String getStyleAddDroit() {
		String style = null;
		if (isFieldSearchIndividuUsed) {
			style = STYLE_VISIBLE;
		} else {
			style = STYLE_HIDDEN;
		}
		return style;
	}

	// boolean interface

	/**
	 * On affiche la case a cocher dans le tableau provenance*typecible*typeacces
	 * que dans le cas de certaines combinaisons.
	 */
	public boolean hideCheckBox() {
		boolean show = true;

		// cas generaux non traites

		// creation fiche lolf
		if (typeDroitAccesItem.equals(eoTypeDroitAccesCreation) && typeDroitCibleItem.equals(eoTypeDroitCibleFicheLolf)) {
			show = false;
		}

		// suppression fiche lolf ou evaluation
		if (show && typeDroitAccesItem.equals(eoTypeDroitAccesSuppression) &&
				(typeDroitCibleItem.equals(eoTypeDroitCibleFicheLolf) || typeDroitCibleItem.equals(eoTypeDroitCibleEvaluation))) {
			show = false;
		}

		return !show;
	}

	/**
	 * Pour la construction du tableau, on a besoin d'afficher ou nom cette ligne.
	 * Selon le mode dans lequel on se trouve, l'affichage se fait ou pas.
	 * 
	 * @return
	 */
	public boolean showBaliseTrOpen() {
		// on affiche que si ce n'est pas le premier beneficiaire
		boolean show = (getBeneficiaireList(strProvenanceItem).indexOfIdenticalObject(beneficiaireItem) != 0);
		return show;
	}

	/**
	 * Pour la construction du tableau, on a besoin d'afficher ou nom cette ligne.
	 * Selon le mode dans lequel on se trouve, l'affichage se fait ou pas.
	 * 
	 * @return
	 */
	public boolean showBaliseTrClose() {
		// on affiche que si ce n'est pas le dernier beneficiaire
		boolean show = (getBeneficiaireList(strProvenanceItem).indexOfIdenticalObject(beneficiaireItem) != getBeneficiaireList(strProvenanceItem).count() - 1);
		// un cas particulier en mode herite, il faut sauter une ligne
		// quand meme si on a un seul beneficiaire mais plusieurs natures
		if (isProvenanceHeritee() && !show) {
			show = (getNatureList().indexOfIdenticalObject(natureItem) != getNatureList().count() - 1);
		}
		return show;
	}

	/**
	 * Une distinction doit etre faite graphiquement pour les droits herites
	 * 
	 * @return
	 */
	public boolean isProvenanceHeritee() {
		return strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HERITEE);
	}

	// getters

	/**
	 * La liste des types de droit d'acces est differente selon si l'on est en
	 * mode poste ou en mode agent
	 */
	public NSArray getTypeDroitAccesList() {
		if (isGestionPoste) {
			return eoTypeDroitAccesPosteList;
		} else {
			return eoTypeDroitAccesAgentList;
		}
	}

	/**
	 * La liste des types de droit de cible est differente selon si l'on est en
	 * mode poste ou en mode agent mais aussi du type d'acces en cours
	 */
	public NSArray getTypeDroitCibleList() {
		if (isGestionPoste) {
			return eoTypeDroitCiblePosteList;
		} else {
			return eoTypeDroitCibleAgentList;
		}
	}

	/**
	 * La liste des provenance est conditionn�e par la selection du browser
	 */
	public NSArray getStrProvenanceList() {
		NSArray strProvenanceList = new NSArray();

		strProvenanceList = strProvenanceList.arrayByAddingObject(EODroit.TYPE_PROVENANCE_DIRECTE);
		strProvenanceList = strProvenanceList.arrayByAddingObject(EODroit.TYPE_PROVENANCE_HERITEE);

		// la liste d'annuaire n'est visible qu'en mode poste et si une composante
		// est selectionn�e
		if (isGestionPoste && composanteSelected != null) {
			strProvenanceList = strProvenanceList.arrayByAddingObject(EODroit.TYPE_PROVENANCE_ANNUAIRE);
		}

		// la ligne de hierarchie n'est disponible qu'en mode agent evaluation et
		// qu'une evaluation est selectionn�e
		if (!isGestionPoste && getVCandidatEvaluationSelected() != null) {
			strProvenanceList = strProvenanceList.arrayByAddingObject(EODroit.TYPE_PROVENANCE_HIERARCHIE);
		}

		return strProvenanceList;
	}

	/**
	 * La liste des beneficiaire pour la provenance en cours
	 * {@link #strProvenanceItem}
	 * 
	 * @return
	 */
	public NSArray getBeneficiaireList() {
		return getBeneficiaireList(strProvenanceItem);
	}

	/**
	 * La liste des beneficaires de droits pour la provenance specifiee
	 * 
	 * @param provenance
	 * @return
	 */
	private NSArray<EOIndividu> getBeneficiaireList(String strProvenance) {
		NSArray<EOIndividu> beneficiaireList = (NSArray<EOIndividu>) beneficiaireListDico.objectForKey(strProvenance);

		if (beneficiaireList == null) {
			beneficiaireList = new NSArray<EOIndividu>();
			if (strProvenance.equals(EODroit.TYPE_PROVENANCE_ANNUAIRE)) {
				// pour l'annuaire, ce sont les responsables des groupes
				if (serviceSelected != null) {
					beneficiaireList = beneficiaireList.arrayByAddingObjectsFromArray(serviceSelected.getIndividuDroitAnnuaireList());
				}
				if (composanteSelected != null) {
					beneficiaireList = beneficiaireList.arrayByAddingObjectsFromArray(composanteSelected.getIndividuDroitAnnuaireList());
				}

			} else if (strProvenance.equals(EODroit.TYPE_PROVENANCE_HIERARCHIE)) {
				// pour la hierarchie, c'est le N+1 et les N+p
				beneficiaireList = beneficiaireList.arrayByAddingObject(
						getVCandidatEvaluationSelected().toEvaluateur());
				beneficiaireList = beneficiaireList.arrayByAddingObjectsFromArray(
						getVCandidatEvaluationSelected().getNppList());

			} else if (strProvenance.equals(EODroit.TYPE_PROVENANCE_DIRECTE) || strProvenance.equals(EODroit.TYPE_PROVENANCE_HERITEE)) {
				// provenance directe ou indirecte, on se base sur le tableau
				// #getDroitList()
				beneficiaireList = (NSArray) getDroitList(strProvenance).valueForKey(EODroit.TO_INDIVIDU_KEY);
			}

			// enlever les doublons
			beneficiaireList = NSArrayCtrl.removeDuplicate(beneficiaireList);
			beneficiaireListDico.setObjectForKey(beneficiaireList, strProvenance);
		}
		return beneficiaireList;
	}

	/**
	 * Indique si la case a cocher a l'intersection des 4 parametres
	 * {@link #beneficiaireItem} - {@link #strProvenanceItem} -
	 * {@link #typeDroitCibleItem} - {@link #typeDroitAccesItem} est oui ou non
	 * cochee
	 * 
	 * @return
	 */
	public boolean getIsChecked() {
		boolean isChecked = false;

		// si la provenance est annauire ou heritee, on fait appel a la methode qui
		// va bien
		if (strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_ANNUAIRE) || strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HIERARCHIE)) {
			isChecked = EODroit.hasDroitFromParams(
					feveApp(),
					beneficiaireItem,
					strProvenanceItem,
					typeDroitAccesItem,
					typeDroitCibleItem,
					serviceSelected == null ? composanteSelected : serviceSelected,
					individuSelected,
					evaluationPeriodeSelected);
		} else if (strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_DIRECTE) || strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HERITEE)) {
			// sinon, il faut regarde dans le tableau des droits getDroitList()
			isChecked = filterDroitList(typeDroitAccesItem, typeDroitCibleItem, beneficiaireItem, natureItem, strProvenanceItem).count() > 0;
		}

		return isChecked;
	}

	/**
	 * On autorise les modifications que sur les provenances directes ou heritees
	 * Le reste, on regarde la combinaison des selections du browser. Cette
	 * methode est appelee a la fois pour les provenances mais aussi en ajout.
	 * Pour savoir si on est en ajout, on regarde si {@link #strProvenanceItem}
	 * est a null.
	 * 
	 * @return
	 */
	public boolean getIsDisabledCheckBox() {
		boolean isEnabled = false;

		boolean isAdding = (strProvenanceItem == null);
		boolean isHerite = (!isAdding && strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HERITEE));

		// actif en mode ajout ou en mode direct/herite
		if (isAdding || strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_DIRECTE) || isHerite) {
			isEnabled = true;
		}

		// en mode poste, on autorise pas la saisie sur les evaluation
		if (isGestionPoste && typeDroitCibleItem.equals(eoTypeDroitCibleEvaluation)) {
			isEnabled = false;
		}

		// en mode agent / evaluation, on autorise pas la saisie sur les postes et
		// fiches
		// si on a selectionn� un agent ou une evaluation
		if (isEnabled) {
			if (!isGestionPoste && (
					typeDroitCibleItem.equals(eoTypeDroitCibleFicheLolf) ||
							typeDroitCibleItem.equals(eoTypeDroitCibleFicheDePoste) ||
					typeDroitCibleItem.equals(eoTypeDroitCiblePoste))) {
				isEnabled = false;
			}
		}

		return !isEnabled;
	}

	/**
	 * La liste des <code>EODroit</code> associes a la cible selectionnee d'apres
	 * {@link #strProvenanceItem}
	 * 
	 * @return
	 */
	private NSArray<EODroit> getDroitList(String strProvenance) {
		NSArray<EODroit> droitList = (NSArray<EODroit>) droitListDico.valueForKey(strProvenance);
		if (droitList == null) {
			droitList = new NSArray<EODroit>();
			// on boucle sur toutes les combinaisons possibles d'acces et de cible
			for (int i = 0; i < getTypeDroitAccesList().count(); i++) {
				EOTypeDroitAcces typeDroitAcces = (EOTypeDroitAcces) getTypeDroitAccesList().objectAtIndex(i);
				for (int j = 0; j < getTypeDroitCibleList().count(); j++) {
					EOTypeDroitCible typeDroitCible = (EOTypeDroitCible) getTypeDroitCibleList().objectAtIndex(j);
					// boolean isHeritage = (strProvenanceItem != null &&
					// strProvenanceItem.equals(EODroit.TYPE_PROVENANCE_HERITEE));
					boolean isHeritage = (strProvenance != null && strProvenance.equals(EODroit.TYPE_PROVENANCE_HERITEE));
					NSArray<EODroit> droitListFetched = EODroit.fetchDroits(
							ec(),
							typeDroitAcces,
							typeDroitCible,
							composanteSelected,
							serviceSelected,
							individuSelected,
							evaluationPeriodeSelected,
							posteSelected,
							ficheDePosteSelected,
							ficheLolfSelected,
							isHeritage);
					droitList = droitList.arrayByAddingObjectsFromArray(droitListFetched);
				}
			}
			droitListDico.setObjectForKey(droitList, strProvenance);
		}
		return droitList;
	}

	/**
	 * La liste des natures disponibles pour une occurence de
	 * {@link #getDroitList()}
	 * 
	 * @return
	 */
	public NSArray<String> getNatureList() {
		if (natureList == null) {
			natureList = new NSArray<String>();
			// on teste 1 a 1 si la liste des droits est de chacune des natures
			for (int i = 0; i < EODroit.DROIT_NATURE_LIST.length; i++) {
				String droitNatureKey = EODroit.DROIT_NATURE_LIST[i];
				NSArray<EODroit> droitNatureForKey = filterDroitList(
						typeDroitAccesItem, typeDroitCibleItem, beneficiaireItem, droitNatureKey, strProvenanceItem);
				// s'il y en a de cette nature, alors on garde
				if (droitNatureForKey.count() > 0) {
					natureList = natureList.arrayByAddingObject(droitNatureKey);
				}
			}
		}
		return natureList;
	}

	// setters

	/**
	 * Lors du changement du type de provenance, on force le rechargement de la
	 * liste des beneficiaires.
	 */
	public void setStrProvenanceItem(String value) {
		strProvenanceItem = value;
	}

	/**
	 * Lors du changement de la nature du droit (cas pour
	 * {@link #strProvenanceItem} = {@link EODroit#TYPE_PROVENANCE_HERITEE} alors
	 * on va RAZ la liste des droits
	 * 
	 * @param value
	 */
	public void setDroitNatureItem(String value) {
		natureItem = value;
	}

	/**
	 * Interception du changement de beneficiaire dans le cas de la provenance
	 * heritee, on force le recharge des types de natures associ�es �
	 * {@link #getDroitList()}
	 * 
	 * @param value
	 */
	public void setBeneficiaireItem(EOIndividu value) {
		natureList = null;
		beneficiaireItem = value;
	}

	/**
	 * Liste des cases a cocher pour l'ajout d'un nouveau droit
	 * 
	 * @param value
	 */
	public void setIsCheckedAddDroit(Boolean value) {
		// construction d'une cle pour l'entree du dictionnaire
		String key = getDicoKey(typeDroitAccesItem, typeDroitCibleItem, null, null);
		checkAddDroitDico.setObjectForKey(value, key);
	}

	/**
	 * Liste des cases a cocher pour la modification de droits existants sur le
	 * triplet {@link #beneficiaireItem} - {@link #typeDroitAccesItem} -
	 * {@link #typeDroitCibleItem}
	 * 
	 * @param value
	 */
	public void setIsChecked(Boolean value) {
		String key = getDicoKey(typeDroitAccesItem, typeDroitCibleItem, beneficiaireItem, natureItem);
		checkUpdateDroitDico.setObjectForKey(value, key);
	}

	// navigation

	/**
	 * Ajouter le droit dans la base de donn�es. Effectuer la separation de toutes
	 * les coches en autant d'enregistrement EODroit.
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doAddDroit() throws Throwable {
		resetAddErrors();
		CktlLog.log(checkAddDroitDico.toString());
		if (individuSearch == null) {
			errAddDroitSelectionIndividu = true;
		} else {
			// controler s'il n'est pas deja dans la liste des beneficiaires directs
			errAddDroitBeneficiaireExistant = getBeneficiaireList(EODroit.TYPE_PROVENANCE_DIRECTE).containsObject(individuSearch);

			if (!errAddDroitBeneficiaireExistant) {
				// pour chaque entree du dictionnaire, on fait une insertion
				NSArray keys = checkAddDroitDico.allKeys();
				for (int i = 0; i < keys.count(); i++) {
					String key = (String) keys.objectAtIndex(i);
					boolean shouldCreate = ((Boolean) checkAddDroitDico.objectForKey(key)).booleanValue();
					if (shouldCreate) {
						// determiner les types associes
						EOTypeDroitAcces typeDroitAcces = getEOTypeDroitAccesForDicoKey(key);
						EOTypeDroitCible typeDroitCible = getEOTypeDroitCibleForDicoKey(key);
						EODroit newDroit = createEODroit(typeDroitAcces, typeDroitCible, individuSearch, null);
						// indiquer la periode si necessaire
						if (evaluationPeriodeSelected != null) {
							newDroit.setToDroitEvaluationPeriodeRelationship(evaluationPeriodeSelected);
						}
					}
				}

				// indiquer que la selection est vide
				if (ec().insertedObjects().count() == 0) {
					errAddDroitSelectionCheckbox = true;
				} else if (UtilDb.save(ec(), "")) {
					// nettoyer le dictionnaire si on veut rajouter d'autres droits
					checkAddDroitDico.removeAllObjects();
				}
			}
		}
		return null;
	}

	/**
	 * Synchroniser les valeurs des coches du formulaire avec ce qu'il y a dans la
	 * base de donn�es.
	 * 
	 * Pour l'instant ne concerne que les provenances DIRECTES !!!
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doUpdateDroit() throws Throwable {
		CktlLog.log(checkUpdateDroitDico.toString());

		// pour chaque entree du dictionnaire, on fait une insertion
		NSArray keys = checkUpdateDroitDico.allKeys();
		for (int i = 0; i < keys.count(); i++) {
			String key = (String) keys.objectAtIndex(i);

			// determiner les types associes
			EOTypeDroitAcces typeDroitAcces = getEOTypeDroitAccesForDicoKey(key);
			EOTypeDroitCible typeDroitCible = getEOTypeDroitCibleForDicoKey(key);
			EOIndividu individu = getEOIndividuForDicoKey(key);
			String nature = getStrNatureForDicoKey(key);

			// c'est une creation
			boolean isCreation = ((Boolean) checkUpdateDroitDico.objectForKey(key)).booleanValue();
			if (isCreation) {
				EODroit newDroit = createEODroit(typeDroitAcces, typeDroitCible, individu, nature);
				// indiquer la periode si necessaire
				if (evaluationPeriodeSelected != null) {
					newDroit.setToDroitEvaluationPeriodeRelationship(evaluationPeriodeSelected);
				}

			} else {
				// c'est une suppression
				EODroit recDroitToDelete = (EODroit) filterDroitList(
						typeDroitAcces, typeDroitCible, individu, nature,
						nature != null ? EODroit.TYPE_PROVENANCE_HERITEE : EODroit.TYPE_PROVENANCE_DIRECTE).objectAtIndex(0);
				ec().deleteObject(recDroitToDelete);
			}
		}
		if (UtilDb.save(ec(), "")) {
			// nettoyer le dictionnaire si on veut rajouter d'autres droits
			checkUpdateDroitDico.removeAllObjects();
		}
		return null;
	}
}
