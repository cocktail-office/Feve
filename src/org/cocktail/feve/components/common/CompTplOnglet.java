package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheItem;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;
import org.cocktail.feve.eos.modele.mangue.EOTplItem;
import org.cocktail.feve.eos.modele.mangue.EOTplItemValeur;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;
import org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

/**
 * Gestion de l'affichage de la saisie d'un onglet parametre selon un template (
 * <code>EOTplOnglet</code>).
 * 
 * Les points d'entree possible sont : - <code>EOEvaluation</code> -
 * <code>Fiche</code>
 * 
 * @author ctarade
 */
public class CompTplOnglet
		extends FeveWebComponent {

	// binding de template d'onglet
	public EOTplOnglet inTplOnglet;
	// binding de l'evaluation ou la fiche
	public CktlRecord inFiche;
	// binding disabled
	public boolean inDisabled;

	// items de boucle
	public EOTplBloc tplBlocItem;
	public EOTplItem tplItemItem;
	public EOTplItemValeur tplItemValeurItem;

	// champ libre en cours de modification
	private EOTplItem tplItemSelected;

	// champ libre (interface)
	public String strChampLibre;

	/**
	 * cas particulier d'une activation de bloc facultatif, les setters des items
	 * sont appeles avec en valeur <em>null</em>, ce qui fait que ceux
	 * precedemment enregistres sont automatiquement effaces. On se sert de se
	 * temoin pour rendre temporairement les setter
	 * <code>setStrChampLibre(String)</code> et
	 * <code>setTplItemValeurSelected(EOTplItemValeur)</code> silenceux.
	 */
	private boolean isActivatingBlocFacultatif;

	public CompTplOnglet(WOContext context) {
		super(context);
		initComponent();
	}

	/**
	 * Initialisation du composant
	 */
	private void initComponent() {
		tplItemSelected = null;
	}

	/**
	 * Gestion des booleens intermediaires
	 */
	public void appendToResponse(WOResponse response, WOContext context) {
		isActivatingBlocFacultatif = false;
		super.appendToResponse(response, context);
	}

	// getters

	/**
	 * Preselection de la valeur dans une liste déroulante. Se base sur un
	 * enregistrement du type <code>EORepartFicheItem</code>. Si non trouvé, alors
	 * la selection est vide
	 */
	public EOTplItemValeur getTplItemValeurSelected() {
		EOTplItemValeur selection = null;
		// trouver l'enregistrement pour cette fiche
		EORepartFicheItem recRepartFicheItem = tplItemItem.getRepartItemForEvaluation((EOEvaluation) inFiche);
		// si existant, alors on va retourner la valeur associee
		if (recRepartFicheItem != null) {
			selection = recRepartFicheItem.toTplItemValeur();
		}
		return selection;
	}

	/**
	 * Retrouver la valeur d'un item de type champ libre. Se base sur un
	 * enregistrement du type <code>EORepartFicheItem</code>. Si non trouvé, alors
	 * la chaine vide est retournee.
	 */
	public String getStrChampLibre() {
		String strResult = StringCtrl.emptyString();

		strResult = tplItemItem.getStrChampLibre((EOEvaluation) inFiche);

		return strResult;
	}

	/**
	 * Determiner si le bloc (qui est normalement facultatif) a ete explicitement
	 * active (enregistrement du type <code>EORepartFicheBlocActivation</code>) Si
	 * non trouvé, alors le bloc reste inactif
	 * 
	 * @return
	 */
	public boolean getIsActifBlocFacultatif() {
		boolean isActif = false;
		// trouver l'enregistrement pour ce bloc et cette fiche
		/*
		 * EORepartFicheBlocActivation recRepartActivation =
		 * EORepartFicheBlocActivation.findRecordInContext( ec, tplBlocItem,
		 * (EOEvaluation) inFiche);
		 */
		EORepartFicheBlocActivation recRepartActivation = tplBlocItem.getActivationRecord((EOEvaluation) inFiche);

		if (recRepartActivation != null) {
			isActif = true;
		}
		return isActif;
	}

	/**
	 * La liste des valeurs associées à l'item {@link #tplItemItem} On se base sur
	 * les dates de validité de la fiche
	 * 
	 * @return
	 */
	public NSArray getTplItemValeurList() {
		NSArray list = null;

		EOEvaluationPeriode periode = ((EOEvaluation) inFiche).toEvaluationPeriode();

		list = tplItemItem.tosTplRepartItemItemValeur(
				periode.epeDDebut(), periode.epeDFin());

		list = (NSArray) list.valueForKey(EOTplRepartItemItemValeur.TO_TPL_ITEM_VALEUR_KEY);

		return list;
	}

	// setters

	/**
	 * Mettre à jour la selection d'un item dans une liste déroulante. Se base sur
	 * un enregistrement du type <code>EORepartFicheItem</code>. Si la nouvelle
	 * valeur est vide, alors on l'efface.
	 */
	public void setTplItemValeurSelected(EOTplItemValeur value) throws Throwable {
		// ce setter est silencieux dans certains cas
		if (!isActivatingBlocFacultatif) {
			// trouver l'enregistrement pour cette fiche
			/*
			 * EORepartFicheItem existingRecord =
			 * EORepartFicheItem.findRecordInContext( ec, tplItemItem, (EOEvaluation)
			 * inFiche);
			 */
			EORepartFicheItem existingRecord = tplItemItem.getRepartItemForEvaluation((EOEvaluation) inFiche);
			if (existingRecord != null) {
				// enregistrement existant
				if (value == null) {
					// selection vide, on efface
					ec.deleteObject(existingRecord);
				} else {
					// mise a jour
					existingRecord.setToTplItemValeurRelationship(value);
				}
				UtilDb.save(ec, "");
			} else {
				// nouvel enregistrement
				if (value != null) {
					// FIXME ne marche que pour les evaluations pour l'instant ...
					EORepartFicheItem newRecord = EORepartFicheItem.createRepartFicheItem(
							ec, DateCtrl.now(), DateCtrl.now(), tplItemItem);
					newRecord.setToEvaluationRelationship((EOEvaluation) inFiche);
					newRecord.setToTplItemValeurRelationship(value);
					UtilDb.save(ec, "");
				}
			}
			// se positionner sur le titre du bloc au prochain rechargement
			// session.setOnLoad("document.location='#"+tplBlocItem.anchorName()+"';");
		}
	}

	/**
	 * Mettre à jour de la chaine d'un item de type champ libre. Se base sur un
	 * enregistrement du type <code>EORepartFicheItem</code>. Si la nouvelle
	 * chaine est vide, alors on l'efface. Ce setter est privé pour n'etre appelé
	 * explicitement via la methode doSaveChampLibre()
	 */
	private void setStrChampLibre(String value) throws Exception {
		// ce setter est silencieux dans certains cas
		if (!isActivatingBlocFacultatif) {
			// trouver l'enregistrement pour cette fiche
			EORepartFicheItem existingRecord = tplItemItem.getRepartItemForEvaluation((EOEvaluation) inFiche);
			if (existingRecord != null) {
				// enregistrement existant
				if (StringCtrl.isEmpty(value)) {
					// selection vide, on efface
					ec.deleteObject(existingRecord);
				} else {
					// mise a jour
					existingRecord.setRfiValeurLibre(value);
				}
				sauvegarde();
			} else {
				// nouvel enregistrement
				if (!StringCtrl.isEmpty(value)) {
					// FIXME ne marche que pour les evaluations pour l'instant ...
					EORepartFicheItem newRecord = EORepartFicheItem.createRepartFicheItem(
							ec, DateCtrl.now(), DateCtrl.now(), tplItemItem);
					newRecord.setToEvaluationRelationship((EOEvaluation) inFiche);
					newRecord.setRfiValeurLibre(value);
					sauvegarde();
				}
			}
			// se positionner sur le titre du bloc au prochain rechargement
			session.setOnLoad("document.location='#" + tplBlocItem.anchorName() + "';");
			// desactiver l'edition du champ libre
			tplItemSelected = null;
		}
	}

	/**
	 * Activer un bloc facultatif. Se base sur un enregistrement du type
	 * <code>EORepartFicheBlocActivation</code>.
	 */
	public void setIsActifBlocFacultatif(boolean value) throws Throwable {
		// trouver l'enregistrement pour ce bloc et cette fiche
		/*
		 * EORepartFicheBlocActivation existingRecord =
		 * EORepartFicheBlocActivation.findRecordInContext( ec, tplBlocItem,
		 * (EOEvaluation) inFiche);
		 */
		EORepartFicheBlocActivation existingRecord = tplBlocItem.getActivationRecord((EOEvaluation) inFiche);
		if (existingRecord != null) {
			// enregistrement existant
			if (value == false) {
				// suppression
				ec.deleteObject(existingRecord);
			} else {
				// deja existant ... rien a faire
			}
			UtilDb.save(ec, "");
		} else {
			// nouvel enregistrement
			if (value == true) {
				// FIXME ne marche que pour les evaluations pour l'instant ...
				EOEvaluation inEvaluation = (EOEvaluation) inFiche;
				EORepartFicheBlocActivation newRecord = EORepartFicheBlocActivation.createRepartFicheBlocActivation(
						ec, DateCtrl.now(), DateCtrl.now(), tplBlocItem);
				newRecord.setToEvaluationRelationship(inEvaluation);
				UtilDb.save(ec, "");
				// memoriser qu'on passe de l'etat false > true
				isActivatingBlocFacultatif = true;
			}
		}
		// se positionner sur le titre du bloc au prochain rechargement
		session.setOnLoad("document.location='#" + tplBlocItem.anchorName() + "';");
	}

	// navigation

	/**
	 * Passer en mode edition du champ libre en cours <code>tplItemItem</code>
	 */
	public WOComponent editChampLibre() {
		tplItemSelected = tplItemItem;
		// se positionner sur le titre du bloc au prochain rechargement
		session.setOnLoad("document.location='#" + tplBlocItem.anchorName() + "';");
		return null;
	}

	/**
	 * Effectuer la sauvegarder explicite du champ libre
	 * 
	 * @throws Throwable
	 */
	public WOComponent doSaveChampLibre() throws Throwable {
		setStrChampLibre(strChampLibre);
		return null;
	}

	/**
	 * Annuler l'operation en cours
	 */
	public WOComponent doCancel() {
		ec.revert();
		tplItemSelected = null;
		return null;
	}

	// boolean interface

	/**
	 * Determiner la disponibilité d'un element (par defaut, si toute la page est
	 * desactivee, on desactive)
	 * 
	 * @return
	 */
	public boolean isDisabled() {
		boolean isDisabled = inDisabled;
		// on va pas plus loin si le composant lui meme est inactif
		if (!isDisabled) {
			// pour les bloc facultatifs non activ�s, on desactive
			if (tplBlocItem.isFacultatif()) {
				isDisabled = !getIsActifBlocFacultatif();
			}
		}
		return isDisabled;
	}

	/**
	 * Le champ libre est modifiable uniquement sur demande. Celui en cours de
	 * modification est designe par <code>tplItemSelected</code>
	 * 
	 * @return
	 */
	public boolean isDisabledChampLibre() {
		boolean isDisabled = isDisabled();
		if (!isDisabled) {
			isDisabled = (tplItemSelected == null || tplItemSelected != tplItemItem);
		}
		return isDisabled;
	}

	/**
	 * La liste des items d'un bloc n'est pas visible si le bloc est facultatif ET
	 * qu'il n'est pas explicitement demande de l'activer.
	 */
	public boolean isShowItemList() {
		boolean isShow = true;
		if (tplBlocItem.isFacultatif()) {
			isShow = getIsActifBlocFacultatif();
		}
		return isShow;
	}

	/**
	 * Pour les bloc facultatifs non activés, on grise le titre
	 */
	public boolean isGreyBlocItemTitle() {
		boolean isGrey = false;
		if (tplBlocItem.isFacultatif()) {
			isGrey = !getIsActifBlocFacultatif();
		}
		return isGrey;
	}

	/**
	 * Le lien de modification d'un champ libre n'est disponible que si un autre
	 * champ libre n'est pas deja en cours de modification
	 */
	public boolean isShowLnkEditChampLibre() {
		boolean isShow = false;
		if (!isDisabled() && tplItemSelected == null) {
			isShow = true;
		}
		return isShow;
	}

	/**
	 * Le lien d'annulation n'est disponible que pour un champ libre deja en cours
	 * de modification
	 */
	public boolean isShowLnkCancel() {
		boolean isShow = false;
		if (!isDisabled() && tplItemSelected == tplItemItem) {
			isShow = true;
		}
		return isShow;
	}

	/**
	 * Le bouton de sauvegarde n'est disponible que pour un champ libre deja en
	 * cours de modification
	 */
	public boolean isShowBtnSauvegardeChampLibre() {
		boolean isShow = false;
		if (!isDisabled() && tplItemSelected == tplItemItem) {
			isShow = true;
		}
		return isShow;
	}

	// delegation de la gestion des blocs

	/**
	 * Liste des blocs disponibles sur la période
	 */
	public NSArray<EOTplBloc> getEoTplBlocArray() {
		return inTplOnglet.tosTplBlocSortedByPosition(((EOEvaluation) inFiche).toEvaluationPeriode());
	}

}