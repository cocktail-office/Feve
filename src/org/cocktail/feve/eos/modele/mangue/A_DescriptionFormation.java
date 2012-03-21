package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.feve.eos.modele.grhum.EOTypeUniteTemps;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXEOControlUtilities;

/**
 * Classe de gestion d'objets pointant vers une formation
 * {@link EOFormationPersonnel}, ou sur un champ libre à défaut.
 * 
 * @author ctarade
 */
public abstract class A_DescriptionFormation
		extends A_FeveCktlRecord {

	/**
	 * Peut etre appele a partir des factories.
	 * 
	 * @throws NSValidation.ValidationException
	 */
	public void validateObjectMetier() throws NSValidation.ValidationException {

		// vérifier qu'une formation est saisie
		if (toFormationPersonnel() == null && StringCtrl.isEmpty(champLibre())) {
			throw new NSValidation.ValidationException("veuillez indiquer une formation");
		}

		// controles specifiques a la duree
		if (isGestionDuree()) {
			if (!StringCtrl.isEmpty(duree()) && toTypeUniteTemps() == null) {
				throw new NSValidation.ValidationException(
						"veuillez indiquer une unité de temps si vous précisez une durée");
			}
			//
			if (StringCtrl.isEmpty(duree()) && toTypeUniteTemps() != null) {
				throw new NSValidation.ValidationException(
						"veuillez indiquer une durée si vous précisez une unité de temps");
			}
		}

		// controles specifiques à la période
		if (isGestionPeriode()) {
			// la date de début est obligatoire
			if (dDebut() == null) {
				throw new NSValidation.ValidationException("date de début obligatoire");
			}
			// le début doit être avant la fin (si renseignée)
			if (dFin() != null && DateCtrl.isAfter(dDebut(), dFin())) {
				throw new NSValidation.ValidationException("date de début après la date de fin");
			}

		}

	}

	//
	// classes a surcharger
	//

	/**
	 * Le lien vers la formation
	 */
	public abstract EOFormationPersonnel toFormationPersonnel();

	public abstract void setToFormationPersonnelRelationship(EOFormationPersonnel eoFormationPersonnel);

	/**
	 * Le champ libre
	 */
	public abstract String champLibre();

	public abstract void setChampLibre(String champLibre);

	// champs facultatifs

	/**
	 * Indique s'il faut gérer un période ou non. A surcharger dans les sur
	 * classes si oui
	 */
	public boolean isGestionPeriode() {
		return false;
	}

	// utilisé pour classement
	public final static String D_DEBUT_KEY = "dDebut";
	public final static String LIBELLE_KEY = "libelle";
	public final static String IS_NOMENCLATURE_KEY = "isNomenclature";

	/**
	 * Date de début A surcharger dans les sur classes si nécéssaire (si
	 * {@link #isGestionPeriode() est a <code>true</code>)
	 */
	public NSTimestamp dDebut() {
		return null;
	}

	public void setDDebut(NSTimestamp dDebut) {

	}

	/**
	 * Date de fin A surcharger dans les sur classes si nécéssaire (si
	 * {@link #isGestionPeriode() est a <code>true</code>)
	 */
	public NSTimestamp dFin() {
		return null;
	}

	public void setDFin(NSTimestamp dFin) {

	}

	/**
	 * Indique s'il faut faire la gestion de duree ou non A surcharger dans les
	 * sur classes si oui
	 */
	public boolean isGestionDuree() {
		return false;
	}

	/**
	 * Unité de temps utilisée A surcharger dans les sur classes si nécéssaire (si
	 * {@link #isGestionDuree() est a <code>true</code>)
	 */
	public EOTypeUniteTemps toTypeUniteTemps() {
		return null;
	}

	public void setToTypeUniteTempsRelationship(EOTypeUniteTemps eoTypeUniteTemps) {

	}

	/**
	 * La durée associée A surcharger dans les sur classes si nécéssaire (si
	 * {@link #isGestionDuree() est a <code>true</code>)
	 */
	public String duree() {
		return null;
	}

	public void setDuree(String duree) {

	}

	// méthodes factory

	/**
	 * Creation d'un EO
	 */
	public static A_DescriptionFormation creerDescriptionFormation(
			EOEditingContext ec, String entityName, EOEvaluation evaluation) {
		A_DescriptionFormation object = (A_DescriptionFormation) ERXEOControlUtilities.createAndInsertObject(ec, entityName);

		if (entityName.equals(EOIndividuFormations.ENTITY_NAME)) {
			((EOIndividuFormations) object).setToIndividuRelationship(evaluation.toIndividu());
		} else if (entityName.equals(EORepartFormationSouhaitee.ENTITY_NAME)) {
			((EORepartFormationSouhaitee) object).setToEvaluationRelationship(evaluation);
		}

		return object;
	}

	// methodes finder

	/**
	 * La liste des objets appartenant a un proprietaire
	 */
	public static NSArray<A_DescriptionFormation> findRecords(
			EOEditingContext ec, String entityName, EOEvaluation evaluation) {

		NSArray<A_DescriptionFormation> result = new NSArray<A_DescriptionFormation>();

		if (entityName.equals(EOIndividuFormations.ENTITY_NAME)) {
			result = evaluation.toIndividu().tosIndividuFormations();
		} else if (entityName.equals(EORepartFormationSouhaitee.ENTITY_NAME)) {
			result = evaluation.tosRepartFormationSouhaitee();
		}

		return result;
	}

	// affichage

	/**
	 * Le libelle d'une formation suivie. On prend le champ libre si aucune
	 * {@link EOFormationPersonnel} n'est référencée, sinon c'est le libelle de
	 * {@link #toFormationPersonnel()}
	 */
	public String libelle() {
		String libelle = null;

		if (toFormationPersonnel() == null) {
			libelle = champLibre();
		} else {
			libelle = toFormationPersonnel().forLibelle();
		}

		return libelle;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNomenclature() {
		boolean isNomenclature = false;

		if (toFormationPersonnel() != null) {
			isNomenclature = true;
		}

		return isNomenclature;
	}
}
