package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.app.finder.FinderFeve;
import org.cocktail.feve.components.common.ListeRecordControled;
import org.cocktail.feve.eos.modele.mangue.A_EOEvaluationKeyValueCoding;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * TODO urgent migrer vers la gestion des controleurs Composant permettant
 * l'affichage d'une liste d'evaluations.
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class ListeEvaluation
		extends ListeRecordControled {

	// liste des periodes
	public EOEvaluationPeriode periodeItem;
	public EOEvaluationPeriode periodeSelected;

	/**
	 * indique si l'utilisateur connecte peut creer l'evaluation
	 * <code>itemEvaluation()</code>
	 */
	private boolean canCreateEvaluation;
	/**
	 * indique si l'utilisateur connecte peut modifier l'evaluation
	 * <code>itemEvaluation()</code>
	 */
	public boolean canUpdateEvaluation;
	/**
	 * indique si l'utilisateur connecte peut visualiser l'evaluation
	 * <code>itemEvaluation()</code>
	 */
	public boolean canViewEvaluation;
	/**
	 * indique si l'utilisateur connecte ne peut acceder � l'evaluation
	 * <code>itemEvaluation()</code>
	 */
	public boolean isDisabledLnkSelectEvaluation;
	/**
	 * Indique si l'utilisateur connecté peut modifier les objectifs précédents
	 */
	public boolean canModifierObjectifsPrecedents;

	/** l'objet itemRecord pointant sur un enregistrement <code>EOEvaluation</code> */
	public EOVCandidatEvaluation itemEvaluation;

	public ListeEvaluation(WOContext context) {
		super(context);
		initComponent();
	}

	private void initComponent() {
		// selection de la periode actuelle par defaut, sinon la derniere
		if (periodeList().count() > 0) {
			NSTimestamp today = DateCtrl.now();
			for (int i = 0; periodeSelected == null && i < periodeList().count(); i++) {
				EOEvaluationPeriode recPeriode = (EOEvaluationPeriode) periodeList().objectAtIndex(i);
				if (DateCtrl.isBeforeEq(recPeriode.epeDDebut(), today) &&
						DateCtrl.isAfterEq(recPeriode.epeDFin(), today)) {
					periodeSelected = recPeriode;
				}
			}
			// pas trouve, on prend la derniere
			if (periodeSelected == null) {
				periodeSelected = (EOEvaluationPeriode) periodeList().lastObject();
			}
		}
		nomPrenom = STR_SEARCH_DEFAULT_VALUE;
	}

	/**
	 * Toutes les periodes d'evaluation
	 */
	public NSArray periodeList() {
		return feveUserInfo().getPeriodeList();
	}

	/**
	 * Retourne une liste d'enregistrements de l'entite
	 * <code>VCandidatEvaluation</code>
	 */
	public NSArray listRecords() {
		NSArray lesEvaluations = new NSArray();
		if (inOnlyPersonnel) {
			lesEvaluations = session.individuConnecte().tosEvaluationSorted();
		} else if (periodeSelected != null) {
			// les evaluations existantes
			EOQualifier qualPeriode = CktlDataBus.newCondition(
					A_EOEvaluationKeyValueCoding.TO_EVALUATION_PERIODE_KEY + "=%@", new NSArray(periodeSelected));
			lesEvaluations = EOQualifier.filteredArrayWithQualifier(
					feveUserInfo().getEvaluationList(), qualPeriode);
			if (!StringCtrl.isEmpty(nomPrenom) && !nomPrenom.equals(STR_SEARCH_DEFAULT_VALUE)) {
				lesEvaluations = FinderFeve.filterIndividuForNomOrPrenomInArray(
						lesEvaluations, nomPrenom, A_EOEvaluationKeyValueCoding.TO_INDIVIDU_KEY + ".");
			}
			// classement par nom de l'individu
			lesEvaluations = EOSortOrdering.sortedArrayUsingKeyOrderArray(lesEvaluations, EOEvaluation.ARRAY_SORT_INDIVIDU);
			// TODO virer celles qui foirent
		}
		return lesEvaluations;
	}

	public int mode() {
		return MODE_EVALUATION;
	}

	// boolean interface

	/**
	 * coche responsable RH : seuls les administrateurs y ont accès, et si
	 * l'enregistrement evaluation existe
	 */
	public boolean disabledLaCocheResp() {
		boolean isDisabled = true;

		if (feveUserInfo().isAdmin()) {
			isDisabled = false;
		}

		if (isDisabled == false &&
				isEvaluationExisting() == false) {
			isDisabled = true;
		}

		return isDisabled;
	}

	/**
	 * Indique s'il faut afficher un avertissement sur la liste des evaluations
	 */
	public boolean hasWarning() {
		boolean result = false;
		for (int i = 0; i < listRecords().count(); i++) {
			EOVCandidatEvaluation eva = ((A_EOEvaluationKeyValueCoding) listRecords().objectAtIndex(i)).toVCandidatEvaluation();
			if (eva != null && eva.toEvaluation() != null) {
				boolean canModifEva = feveUserInfo().hasDroitOnCible(
						EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.EVALUATION, eva, false);
				if (!eva.isViseParResponsableRh() && canModifEva) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * L'agent connecte peut il modifier l'evaluation en cours. Si elle existe
	 * alors on verifie si cette derniere appartient a la liste des evaluations
	 * modifiables.
	 */
	public boolean canModifItemRecord() {
		return canUpdateEvaluation;
	}

	/**
	 * L'evaluation est verrouillee si vis�e, ou bien si l'enregistrement n'existe
	 * pas encore.
	 */
	public boolean isLockedItemRecord() {
		return itemEvaluation == null || itemEvaluation.isViseParResponsableRh();
	}

	/**
	 * Indique si l'enregistrement <code>EOEvaluation</code> existe pour un
	 * <code>itemRecord</code> donn�.
	 * 
	 * @return
	 */
	public boolean isEvaluationExisting() {
		boolean isEvaluationExisting = false;

		try {
			isEvaluationExisting = itemEvaluation.toEvaluation() != null;
		} catch (Exception e) {

		}

		return isEvaluationExisting;
	}

	// navigation

	/**
	 * Effacer la zone de recherche
	 */
	public WOComponent clearNomPrenom() {
		nomPrenom = STR_SEARCH_DEFAULT_VALUE;
		return null;
	}

	/**
	 * Retourne une page indique la liste des avertissement
	 */
	public WOComponent afficherWarning() {
		StringBuffer buff = new StringBuffer("<b>liste des entretiens professionnels &agrave; viser</b><br><br><ul>");
		for (int i = 0; i < listRecords().count(); i++) {
			EOVCandidatEvaluation eva = ((A_EOEvaluationKeyValueCoding) listRecords().objectAtIndex(i)).toVCandidatEvaluation();
			boolean canModifEva = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.EVALUATION, eva, false);
			// on ne comptabilise que les evaluation cr�es
			if (eva != null && eva.toEvaluation() != null && !eva.isViseParResponsableRh() && canModifEva) {
				buff.append("<li>");
				buff.append(eva.display());
				buff.append("</li>");
			}
		}
		buff.append("</ul>");
		return CktlAlertPage.newAlertPageWithCaller(this.parent(),
				"Avertissement sur les entretiens professionnels", buff.toString(), "Retourner", CktlAlertPage.ATTENTION);
	}

	/**
	 * La création doit être transparente pour l'utilisateur : si l'enregistrement
	 * n'existe pas, on la créé et on l'affiche, comme si elle existait déjà
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doCreateAndClicEvaluation() throws Throwable {
		EOEvaluation.doSelectEvaluation(
				ec, itemEvaluation, periodeSelected, session.individuConnecte());
		clicRecord();
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public ListeEvaluationCtrl ctrl() {
		return (ListeEvaluationCtrl) ctrl;
	}

	/**
	 * TODO ne plus utiliser directement (utiliser
	 * {@link #doCreateAndClicEvaluation()}
	 * 
	 * surcharge de la methode afin de passer dans <code>selectedRecord</code> 
	 * l'enregistrement <code>EOEvaluation</code> plutot que l'enregistrement
	 * <code>VCandidatEvaluation</code>
	 */
	@Override
	public WOComponent clicRecord() {
		selectedRecord = itemEvaluation;
		ctrl().doSelectEvaluation(itemEvaluation);
		return neFaitRien();
	}

	@Override
	public WOComponent hideRecord() {
		ctrl().doDeselectEvaluation();
		return super.hideRecord();
	}

	// display

	/**
	 * Message d'information sur la periode d'ouverture de saisie des evaluation
	 * 
	 * @return
	 */
	public String getInfoPeriodeEvaluation() {
		StringBuffer sb = new StringBuffer();
		sb.append("La saisie est autoris&eacute;e du ");
		sb.append(DateCtrl.dateToString(app.getEvaluationSaisieDDebut()));
		sb.append(" au ");
		sb.append(DateCtrl.dateToString(app.getEvaluationSaisieDFin()));
		sb.append("<br/>");

		if (app.isPeriodeEvaluationOuverte()) {
			sb.append("Vous pouvez saisir vos entretiens professionnels actuels.");
		} else {
			sb.append("<font class=textError>");
			sb.append("Vous ne pouvez pas saisir d'entretien professionnel.");
			if (feveUserInfo().isAdmin()) {
				sb.append(" (<u>SAUF VOUS</u>, car vous &ecirc;tes administrateur!)");
			}
			sb.append("</font>");
		}
		return sb.toString();
	}

	// setters

	/**
	 * Interception de l'evaluation <code>itemRecord</code> pour determiner les
	 * droits qui s'y appliquent
	 */
	public void setItemRecord(CktlRecord value) {
		itemRecord = value;

		if (itemRecord != null) {
			itemEvaluation = ((A_EOEvaluationKeyValueCoding) itemRecord).toVCandidatEvaluation();

			Object cible = itemEvaluation;
			if (cible == null) {
				cible = itemRecord;
			}

			// TODO est-ce que ce témoin est vraiment utile ?
			canCreateEvaluation = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.CREATION, EOTypeDroitCible.EVALUATION, cible, false);
			canUpdateEvaluation = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.EVALUATION, cible, false);
			canViewEvaluation = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.EVALUATION, cible, false);

			isDisabledLnkSelectEvaluation = !canUpdateEvaluation && !canViewEvaluation;

			// controle supplémentaire : si l'évaluation n'est pas crée et que
			// la personne connectée n'a pas le droit de la créer, on bloque
			if (isDisabledLnkSelectEvaluation == false) {
				if (isEvaluationExisting() == false && canCreateEvaluation == false) {
					isDisabledLnkSelectEvaluation = true;
				}
			}

			canModifierObjectifsPrecedents = itemEvaluation.isModificationObjPrecAutorisee(session.individuConnecte());

			// droit de saisie d'objectif précédent
			if (isDisabledLnkSelectEvaluation == true) {
				if (canModifierObjectifsPrecedents) {
					isDisabledLnkSelectEvaluation = false;
				}
			}

		}

	}
}