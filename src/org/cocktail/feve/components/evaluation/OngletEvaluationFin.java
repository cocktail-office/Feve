package org.cocktail.feve.components.evaluation;

import org.cocktail.feve.app.print.PrintConsts;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSTimestamp;

public class OngletEvaluationFin
		extends A_OngletEvaluationAvecImpression {

	public boolean isAfficherAlerteTenueRealisee;

	public OngletEvaluationFin(WOContext context) {
		super(context);
		isAfficherAlerteTenueRealisee = false;
	}

	@Override
	public boolean isEmptyEvaluation() {
		return false;
	}

	// gestion des dates

	/**
	 * Indique si on peut remettre à vide la date de tenue. Oui si droit de modif
	 * ou admin, et que cette derniere n'est pas déja vide
	 */
	public boolean isDTenueReinitialisable() {
		boolean isDTenueReinitialisable = false;

		if ((isModifiable() || feveUserInfo().isAdmin()) &&
				getInEvaluation().isEntretienTenu()) {
			isDTenueReinitialisable = true;
		}

		return isDTenueReinitialisable;
	}

	/**
	 * Effectuer la RAZ de la date de tenue
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doReinitialiserDTenue() throws Throwable {
		setDTenueEntretien(null);
		return null;
	}

	/**
	 * Indique si on peut remettre à vide la date de visa RH. Oui si droit rh
	 * (admin) et que cette derniere n'est pas déja vide
	 */
	public boolean isDVisaResponsableRhReinitialisable() {
		boolean isDVisaResponsableRhReinitialisable = false;

		if (feveUserInfo().isAdmin() &&
				getInEvaluation().isViseParResponsableRh()) {
			isDVisaResponsableRhReinitialisable = true;
		}

		return isDVisaResponsableRhReinitialisable;
	}

	/**
	 * Effectuer la RAZ de la date de visa RH
	 * 
	 * @return
	 * @throws Throwable
	 */
	public WOComponent doReinitialiserDVisaReponsableRh() throws Throwable {
		setDVisaResponsableRh(null);
		return null;
	}

	/**
	 * La date de tenue d'entretien n'est pas modifiable si l'entretien a déjà été
	 * tenu ou si le composant est en lecture seule
	 * 
	 * @return
	 */
	public boolean isDisableDdTenueEntretien() {
		boolean isDisableDdTenueEntretien = true;

		if (isModifiable() &&
				getInEvaluation().isEntretienTenu() == false) {
			isDisableDdTenueEntretien = false;
		}

		return isDisableDdTenueEntretien;
	}

	/**
	 * La date n'est modifiable que par les admins et que si celle ci est vide
	 * 
	 * @return
	 */
	public boolean isDisabledDVisaResponsableRh() {
		boolean isDisabledDVisaResponsableRh = true;

		if (feveUserInfo().isAdmin() &&
				!getInEvaluation().isViseParResponsableRh()) {
			isDisabledDVisaResponsableRh = false;
		}

		return isDisabledDVisaResponsableRh;
	}

	// getters setters date pour avoir la sauvegarde automatique avec les
	// CktlAjaxDatePicker

	public final NSTimestamp getDTenueEntretien() {
		return getInEvaluation().dTenueEntretien();
	}

	public final void setDTenueEntretien(NSTimestamp dTenueEntretien) throws Throwable {
		getInEvaluation().setDTenueEntretien(dTenueEntretien);
		// afficher le message d'avertissement si la date n'est pas vide
		if (dTenueEntretien != null) {
			isAfficherAlerteTenueRealisee = true;
		}
		sauvegarde();
	}

	public final NSTimestamp getDVisaResponsableRh() {
		return getInEvaluation().dVisaResponsableRh();
	}

	public final void setDVisaResponsableRh(NSTimestamp dVisaResponsableRh) throws Throwable {
		getInEvaluation().setDVisaResponsableRh(dVisaResponsableRh);
		sauvegarde();
	}

	//

	/**
	 * Le code JS permettant d'afficher le message invitant le responsable a
	 * valider
	 */
	public String getJsAlertTenueRealisee() {
		String str = "";

		str = "alert('" + PrintConsts.MESSAGE_FICHE_EVALUATION_APRES_SAISIE_DATE_ENTRETIEN + "');";

		// ne pas reafficher le message par la suite
		isAfficherAlerteTenueRealisee = false;

		return str;
	}

}