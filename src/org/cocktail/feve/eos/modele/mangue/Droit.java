package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Classe decrivant un objet de type droit, non insere dans la base de donnees.
 * Sert pour connaitre une liste de droits deduits.
 * 
 * @author ctarade
 */
public class Droit 
	implements NSKeyValueCoding, I_Droit {

	NSMutableDictionary dico = new NSMutableDictionary();
	
	public Droit() {
		super();
	}
	
	public void takeValueForKey(Object value, String key) {
		dico.takeValueForKey(value, key);
	}

	public Object valueForKey(String key) {
		if (key.equals(EODroit.CIBLE_DISPLAY_KEY)) {
			return cibleDisplay();
		} else if (key.equals(EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY)) {
			return toDroitVCandidatEvaluation();
		} else if (key.equals(EODroit.TO_DROIT_EVALUATION_PERIODE_KEY)) {
			return toDroitEvaluationPeriode();
		} else if (key.equals(EODroit.TO_DROIT_STRUCTURE_KEY)) {
			return toDroitStructure();
		} else if (key.equals(EODroit.TO_DROIT_COMPOSANTE_KEY)) {
			return toDroitComposante();
		} else if (key.equals(EODroit.TOS_SERVICE_FLATTEN_KEY)) {
			return tosServiceFlatten();
		} else if (key.equals(EODroit.TO_TYPE_DROIT_ACCES_KEY)) {
			return toTypeDroitAcces();
		} else if (key.equals(EODroit.TO_TYPE_DROIT_CIBLE_KEY)) {
			return toTypeDroitCible();
		} else if (key.equals(EODroit.TO_DROIT_FICHE_DE_POSTE_KEY)) {
			return toDroitFicheDePoste();
		} else if (key.equals(EODroit.TO_DROIT_FICHE_LOLF_KEY)) {
			return toDroitFicheLolf();
		} else if (key.equals(EODroit.TO_DROIT_INDIVIDU_KEY)) {
			return toDroitIndividu();
		} else if (key.equals(EODroit.TO_DROIT_POSTE_KEY)) {
			return toDroitPoste();
		} else if (key.equals(EODroit.IS_DROIT_GLOBAL_KEY)) {
			return isDroitGlobal();
		} else if (key.equals(EODroit.IS_DROIT_COMPOSANTE_KEY)) {
			return isDroitComposante();
		} else if (key.equals(EODroit.IS_DROIT_STRUCTURE_KEY)) {
			return isDroitStructure();
		} else if (key.equals(EODroit.IS_DROIT_POSTE_KEY)) {
			return isDroitPoste();
		} else if (key.equals(EODroit.IS_DROIT_FICHE_DE_POSTE_KEY)) {
			return isDroitFicheDePoste();
		} else if (key.equals(EODroit.IS_DROIT_FICHE_LOLF_KEY)) {
			return isDroitFicheLolf();
		} else if (key.equals(EODroit.IS_DROIT_INDIVIDU_KEY)) {
			return isDroitIndividu();
		} else if (key.equals(EODroit.IS_DROIT_V_CANDIDAT_EVALUATION_KEY)) {
			return isDroitVCandidatEvaluation();
		} else {
			return dico.valueForKey(key);
		}
	}

	public EOVCandidatEvaluation toDroitVCandidatEvaluation() {
		return (EOVCandidatEvaluation) dico.valueForKey(EODroit.TO_DROIT_V_CANDIDAT_EVALUATION_KEY);
	}

	public EOEvaluationPeriode toDroitEvaluationPeriode() {
		return (EOEvaluationPeriode) dico.valueForKey(EODroit.TO_DROIT_EVALUATION_PERIODE_KEY);
	}

	public EOStructure toDroitStructure() {
		return (EOStructure) dico.valueForKey(EODroit.TO_DROIT_STRUCTURE_KEY);
	}
	
	public EOStructure toDroitComposante() {
		return (EOStructure) dico.valueForKey(EODroit.TO_DROIT_COMPOSANTE_KEY);
	}

	/**
	 * N'est appel� que pour retrouver les evaluations pour un ayant droit
	 * sur une composante sur une periode d'evaluation determinee
	 */
	public NSArray tosServiceFlatten() {
		return EODroit.tosServiceFlatten(this);
	}

	public EOTypeDroitAcces toTypeDroitAcces() {
		return (EOTypeDroitAcces) dico.valueForKey(EODroit.TO_TYPE_DROIT_ACCES_KEY);
	}

	public EOTypeDroitCible toTypeDroitCible() {
		return (EOTypeDroitCible) dico.valueForKey(EODroit.TO_TYPE_DROIT_CIBLE_KEY);
	}

	public String cibleDisplay() {
		return EODroit.cibleDisplay(this);
	}

	public EOFicheDePoste toDroitFicheDePoste() {
		return (EOFicheDePoste) dico.valueForKey(EODroit.TO_DROIT_FICHE_DE_POSTE_KEY);
	}

	public EOFicheLolf toDroitFicheLolf() {
		return (EOFicheLolf) dico.valueForKey(EODroit.TO_DROIT_FICHE_LOLF_KEY);
	}

	public EOIndividu toDroitIndividu() {
		return (EOIndividu) dico.valueForKey(EODroit.TO_DROIT_INDIVIDU_KEY);
	}

	public EOPoste toDroitPoste() {
		return (EOPoste) dico.valueForKey(EODroit.TO_DROIT_POSTE_KEY);
	}

	public boolean isDroitComposante() {
		return EODroit.isDroitComposante(this);
	}

	public boolean isDroitFicheDePoste() {
		return EODroit.isDroitFicheDePoste(this);
	}

	public boolean isDroitFicheLolf() {
		return EODroit.isDroitFicheLolf(this);
	}

	public boolean isDroitGlobal() {
		return EODroit.isDroitGlobal(this);
	}

	public boolean isDroitIndividu() {
		return EODroit.isDroitIndividu(this);
	}

	public boolean isDroitPoste() {
		return EODroit.isDroitPoste(this);
	}

	public boolean isDroitStructure() {
		return EODroit.isDroitStructure(this);
	}

	public boolean isDroitVCandidatEvaluation() {
		return EODroit.isDroitVCandidatEvaluation(this);
	}

	/**
	 * On prend le premier {@link EOEditingContext} disponible > celui de l'individu
	 * car il est toujours renseign�
	 */
	public EOEditingContext editingContext() {
		return toDroitIndividu().editingContext();
	}
}
