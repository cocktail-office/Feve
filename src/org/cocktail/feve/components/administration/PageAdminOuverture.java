package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

/**
 * Ecran de saisie des dates d'ouvertures et de fermeture
 * des données 
 * 
 * @author ctarade
 */
public class PageAdminOuverture extends FeveWebComponent {
	
	/** dates d'ouvertures */
	public NSTimestamp evaluationSaisieDDebut, evaluationSaisieDFin, ficheLolfSaisieDDebut, ficheLolfSaisieDFin;
	/** */
	public String libelleCreationPosteValeurParDefaut;
	/** */
	public Integer dureeMinimumAffectationPourEvaluation;
	
	/** le dictionnaire contenant toutes les variable de type boolean */
	public NSMutableDictionary<String, Boolean> dicoBoolean;
	/** la liste de toutes les cles du dictionnaire de boolean */
	public NSArray<String> keyList = EOMangueParametres.ARRAY_KEY_BOOLEAN;
	public String keyItem;
	
	public PageAdminOuverture(WOContext context) {
		super(context);
		initComponent();
	}

	private void initComponent() {
		restore();
	}	
	
	// manipulation des données

	/**
	 * Resynchroniser les données du formulaire avec celle des parametres
	 */
	private void restore() {
		evaluationSaisieDDebut 	= app.getDateParamValueForKey(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_DEBUT);
		evaluationSaisieDFin 		= app.getDateParamValueForKey(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_FIN);
		ficheLolfSaisieDDebut 	= app.getDateParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_DEBUT);
		ficheLolfSaisieDFin 		= app.getDateParamValueForKey(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_FIN);
		libelleCreationPosteValeurParDefaut 		= app.getParamValueForKey(EOMangueParametres.KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT);
		dureeMinimumAffectationPourEvaluation 		= app.getIntegerParamValueForKey(EOMangueParametres.KEY_FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION);
		
		dicoBoolean = new NSMutableDictionary<String, Boolean>();
		for (int i=0; i<keyList.count(); i++) {
			String key = (String) keyList.objectAtIndex(i);
			dicoBoolean.setObjectForKey(app.getBooleanParamValueForKey(key), key);	
		}
	}
	
	/**
	 * Sauvegarder les modifications
	 * @throws Throwable 
	 */
	public WOComponent doSave() throws Throwable {	
		if (evaluationSaisieDDebut != null) 												{update(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_DEBUT, 	DateCtrl.dateToString(evaluationSaisieDDebut));}
		if (evaluationSaisieDFin != null) 													{update(EOMangueParametres.KEY_FEV_EVALUATION_SAISIE_D_FIN, 		DateCtrl.dateToString(evaluationSaisieDFin));}
		if (ficheLolfSaisieDDebut != null) 													{update(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_DEBUT, 	DateCtrl.dateToString(ficheLolfSaisieDDebut));}
		if (ficheLolfSaisieDFin != null) 														{update(EOMangueParametres.KEY_FEV_FICHE_LOLF_SAISIE_D_FIN, 		DateCtrl.dateToString(ficheLolfSaisieDFin));}
		
		update(EOMangueParametres.KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT, 		libelleCreationPosteValeurParDefaut);
		if (dureeMinimumAffectationPourEvaluation != null) {
			update(EOMangueParametres.KEY_FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION, 		Integer.toString(dureeMinimumAffectationPourEvaluation.intValue()));
		}
		
		for (int i=0; i<keyList.count(); i++) {
			String key = (String) keyList.objectAtIndex(i);
			Boolean value = (Boolean) dicoBoolean.objectForKey(key);
			update(key, booleanToString(value));
		}
		
		if (UtilDb.save(ec(), "")) {
			restore();
			feveUserInfo().clearParamCache();
		}
		
		return null;
	}
	
	
	/**
	 * Annuler les modifications
	 * @return
	 */
	public WOComponent doCancel() {
		restore();
		return null;
	}
	
	/**
	 * Effectuer la mise a jour d'un enregistrement de la
	 * table MANGUE.MANGUE_PARAMETRES
	 * @param paramKey
	 */
	private void update(String paramKey, String paramValue) {
		EOMangueParametres record = EOMangueParametres.fetchMangueParametres(
				ec(), CktlDataBus.newCondition(EOMangueParametres.PARAM_KEY_KEY+"='" + paramKey+ "'"));
		if (record != null) {
			record.setParamValue(paramValue);
			app.clearCache(paramKey);
		}
 	}
	
	/**
	 * L'editingcontext utilisé pour ces données est particulier
	 * puisque c'est celui de Application
	 * @return
	 */
	private EOEditingContext ec() {
		return app.paramEditingContext();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static String booleanToString(Boolean value) {
		return value.booleanValue() ? A_FeveCktlRecord.OUI : A_FeveCktlRecord.NON;
	}

}