package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps;
import org.cocktail.feve.eos.modele.mangue.A_DescriptionFormation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOIndividuFormations;
import org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * Gestion de l'affichage de la saisie d'un bloc de type saisie
 * des données de formation d'un individu selon un template
 * (<code>EOTplBloc</code>).
 *
 * Le points d'entree est <code>EOEvaluation</code>
 * 
 * @author ctarade
 */
public class CompTplBlocFormation 
	extends FeveWebComponent {
	
	// binding de template d'onglet
	public EOTplBloc inTplBloc;
	// binding de l'evaluation ou la fiche
	public CktlRecord inFiche;
	// binding disabled
	public boolean inDisabled;
	// binding entite
	public String entityName;
	
	// listes
	private NSArray<A_DescriptionFormation> formationList;
	public A_DescriptionFormation formationItem;
	public A_DescriptionFormation formationSelected;
	
	// 
	private NSArray<EOTypeUniteTemps> eoTypeUniteTempsArray;
	public EOTypeUniteTemps eoTypeUniteTempsItem;
	
	// mode d'utilisation du composant
	public int mode;
	private final static int MODE_READ 	= 0;
	private final static int MODE_ADD 	= 1;
	private final static int MODE_EDIT 	= 2;
	
	// message d'erreur
	public String errorMessage;
	
	// champs pour une nouvelle formation
	public EOFormationPersonnel newEoFormationPersonnel;
	public String newFormationLibelle;
	public NSTimestamp newFormationDebut;
	public NSTimestamp newFormationFin;
	public String newFormationDuree;
	public EOTypeUniteTemps newFormationTypeUniteTemps;
	public boolean newFormationIsNomenclature;
	
	// champs pour l'edition d'une formation 
	public EOFormationPersonnel existingEoFormationPersonnel;
	public String existingFormationLibelle;
	public NSTimestamp existingFormationDebut;
	public NSTimestamp existingFormationFin;
	public String existingFormationDuree;
	public EOTypeUniteTemps existingFormationTypeUniteTemps;
	public boolean existingFormationIsNomenclature;
	
	public CompTplBlocFormation(WOContext context) {
		super(context);
		initComponent();
	}
	
	// methodes internes 
	
	/**
	 * Initialisation du composant
	 */
	private void initComponent() {
		mode = MODE_READ;
		clearError();
		clearCache();
	}
	
	private void clearError() {
		errorMessage = StringCtrl.emptyString();
	}
	
	private void clearCache() {
		formationList = null;
	}
	
	// getters
	
	/**
	 * Liste des formations suivies par l'agent titulaire de la fiche
	 */
	public NSArray<A_DescriptionFormation> formationList() {
		if (formationList == null) {
			formationList = A_DescriptionFormation.findRecords(
					ec, entityName, (EOEvaluation) inFiche);
			// classement
			formationList = CktlSort.sortedArray(
					formationList, A_DescriptionFormation.D_DEBUT_KEY);
			
		}
		return formationList;
	}
	
	/**
	 * Liste des formations suivies par l'agent titulaire de la fiche
	 */
	public NSArray<EOTypeUniteTemps> getEoTypeUniteTempsArray() {
		if (eoTypeUniteTempsArray == null) {
			eoTypeUniteTempsArray = EOTypeUniteTemps.fetchAllTypeUniteTempses(ec);
		}
		return eoTypeUniteTempsArray;
	}
	
	/**
	 * Indique s'il faut gérer les périodes ou non
	 * @return
	 */
	public boolean isGestionPeriode() {
		boolean isGestionPeriode = false;
		
		if (entityName.equals(EOIndividuFormations.ENTITY_NAME)) {
			isGestionPeriode = EOIndividuFormations.IS_GESTION_PERIODE;
		} else if (entityName.equals(EORepartFormationSouhaitee.ENTITY_NAME)) {
			isGestionPeriode = EORepartFormationSouhaitee.IS_GESTION_PERIODE;
		} 
		
		return isGestionPeriode;
	}
	
	/**
	 * Indique s'il faut gérer les durees ou non
	 * @return
	 */
	public boolean isGestionDuree() {
		boolean isGestionDuree = false;
		
		if (entityName.equals(EOIndividuFormations.ENTITY_NAME)) {
			isGestionDuree = EOIndividuFormations.IS_GESTION_DUREE;
		} else if (entityName.equals(EORepartFormationSouhaitee.ENTITY_NAME)) {
			isGestionDuree = EORepartFormationSouhaitee.IS_GESTION_DUREE;
		} 
		
		return isGestionDuree;
	}
	
	// setters
	
	/**
	 * Lors du changement de l'evaluation, on force a recharger
	 * la liste des formations
	 */
	public void setInFiche(CktlRecord value) {
		inFiche = value;
		clearCache();
	}
	
	// navigation
	
	/**
	 * Demande de saisie d'une nouvelle formation
	 */
	public WOComponent addFormation() {
		mode = MODE_ADD;
		newFormationLibelle = null;
		newEoFormationPersonnel = null;
		newFormationDebut = null;
		newFormationFin = null;
		newFormationDuree = null;
		// selection de la premiere unité de temps disponible
		if (getEoTypeUniteTempsArray().count() > 0) {
			newFormationTypeUniteTemps = (EOTypeUniteTemps) getEoTypeUniteTempsArray().objectAtIndex(0);
		}
		return null;
	}

	/**
	 * Demande de saisie d'une nouvelle formation
	 */
	public WOComponent editFormation() {
		mode = MODE_EDIT;
		formationSelected = formationItem;
		existingFormationLibelle = formationSelected.champLibre();
		existingFormationDebut = formationSelected.dDebut();
		existingFormationFin = formationSelected.dFin();
		existingEoFormationPersonnel = formationSelected.toFormationPersonnel();
		existingFormationTypeUniteTemps = formationSelected.toTypeUniteTemps();
		existingFormationDuree = formationItem.duree();
		return null;
	}
	
	// operations base de données
	
	/**
	 * Enregistrement de la nouvelle formation.
	 * Seule la date de debut est obligatoire.
	 * @throws Throwable 
	 */
	public WOComponent doAddNewFormation() throws Throwable {
		clearError();

		// controles termines : sauvegarde
		A_DescriptionFormation newRecord = A_DescriptionFormation.creerDescriptionFormation(
				ec, entityName, (EOEvaluation) inFiche);
		newRecord.setDDebut(newFormationDebut);
		newRecord.setDFin(newFormationFin);
		if (newFormationIsNomenclature) {
			newRecord.setToFormationPersonnelRelationship(newEoFormationPersonnel);
		} else {
			newRecord.setChampLibre(newFormationLibelle);
		}
		newRecord.setDuree(newFormationDuree);
		newRecord.setToTypeUniteTempsRelationship(newFormationTypeUniteTemps);
		
		
		if (sauverEtGestionMessageErreur()) {
			// forcer le rechargement de la liste en vidant le cache
			clearCache();
			// repasser en mode consultation
			mode = MODE_READ;
		}
		
		return null;
	}
	
	/**
	 * Enregistrement des modifications d'une formation existante
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doEditFormation() throws Throwable {
		clearError();
		
		formationSelected.setDDebut(existingFormationDebut);
		formationSelected.setDFin(existingFormationFin);
		if (existingFormationIsNomenclature) {
			formationSelected.setToFormationPersonnelRelationship(existingEoFormationPersonnel);
			formationSelected.setChampLibre(null);
		} else {
			formationSelected.setToFormationPersonnelRelationship(null);
			formationSelected.setChampLibre(existingFormationLibelle);
		}
		formationSelected.setDuree(existingFormationDuree);
		formationSelected.setToTypeUniteTempsRelationship(existingFormationTypeUniteTemps);
		
		if (sauverEtGestionMessageErreur()) {
			// forcer le rechargement de la liste en vidant le cache
			clearCache();
			// repasser en mode consultation
			mode = MODE_READ;
		}
		
		return null;
	}
	
	/**
	 * Sauvegarder l'operation en cours et met a jour le
	 * message d'erreur s'il y en a une
	 * @return <code>true</code> si aucun pb
	 */
	private boolean sauverEtGestionMessageErreur() {
		boolean noError = true;
		ec.lock();
		try {
			ec.saveChanges();
		} catch (Throwable e) {
			errorMessage = e.getMessage();
			ec.revert();
			noError = false;
		} finally {
			ec.unlock();
		}
		return noError;
	}
	
	/**
	 * Suppression d'une entree
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doDeleteFormation() throws Throwable {
		ec.deleteObject(formationItem);
		if (sauverEtGestionMessageErreur()) {
			// forcer le rechargement de la liste en vidant le cache
			clearCache();
		}
		return null;
	}
	
	/**
	 * Annuler l'operation en cours
	 * @return
	 */
	public WOComponent doCancel() {
		// annuler les modifications / insertions
		ec.revert();
		// nettoyage des eventuelles erreurs
		clearError();
		// repasser en mode consultation
		mode = MODE_READ;
		return null;
	}
	
	// boolean interface

	/**
	 * 
	 */
	public boolean isShowErrorMessage() {
		return !StringCtrl.isEmpty(errorMessage);
	}
	
	/**
	 * On autorise la saisie d'une nouvelle formation uniquement
	 * en lecture seule, quand le composant n'est pas disabled
	 */
	public boolean isShowLnkAddNewFormation() {
		boolean isShow = false;
		if (!inDisabled) {
			isShow = (mode == MODE_READ);
		}
		return isShow;
	}
	
	/**
	 * On autorise la modification d'une formation uniquement
	 * en lecture seule, quand le composant n'est pas disabled
	 */
	public boolean isShowLnkEditFormation() {
		boolean isShow = false;
		if (!inDisabled) {
			isShow = (mode == MODE_READ);
		}
		return isShow;
	}

	/**
	 * On autorise l'effacement d'une  formation uniquement
	 * en lecture seule, quand le composant n'est pas disabled
	 */
	public boolean isShowLnkDeleteFormation() {
		boolean isShow = false;
		if (!inDisabled) {
			isShow = (mode == MODE_READ);
		}
		return isShow;
	}
	
	/**
	 * Le formulaire de saisie d'une nouvelle formation n'est
	 * visible qu'en mode ajout.
	 */
	public boolean isShowFormAddNewFormation() {
		boolean isShow = false;
		if (!inDisabled) {
			isShow = (mode == MODE_ADD);
		}
		return isShow;
	}
	
	/**
	 * On ne peut annuler une modification
	 * de la formation en cours <code>formationSelected</code>
	 * (formulaire active)
	 */
	public boolean isShowLnkCancel() {
		boolean isShow = false;
		if (!inDisabled) {
			isShow =(mode == MODE_EDIT && formationItem == formationSelected);
		}
		return isShow;
	}
	
	/**
	 * Determine quelle est la ligne en cours de modification.
	 * On ne modifie que si le mode le permet et que si la formation
	 * en cours <code>formationItem</code> est la meme que la selection
	 * <code>formationSelected</code>
	 */
	public boolean isEditingCurrentFormation() {
		boolean isEditing = false;
		if (!inDisabled && mode == MODE_EDIT) {
			isEditing = (formationItem == formationSelected);
		}
		return isEditing;
	}
	
}