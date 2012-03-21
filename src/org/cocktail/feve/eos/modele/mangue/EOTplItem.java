package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOTplItem extends _EOTplItem {

	public EOTplItem() {
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

	// methodes rajoutees

	// noms des items
	public final static String CODE_FORMATIONS_SOUHAITEES = "LISFORMSOU";
	public final static String CODE_APPRECIATION_GENERALE = "APGENVALAB";

	public boolean isListe() {
		return toTplItemNature().tinLibelle().equals(EOTplItemNature.TPL_ITEM_NATURE_LISTE);
	}

	public boolean isChampLibre() {
		return toTplItemNature().tinLibelle().equals(EOTplItemNature.TPL_ITEM_NATURE_CHAMP_LIBRE);
	}

	public boolean isTexteStatique() {
		return toTplItemNature().tinLibelle().equals(EOTplItemNature.TPL_ITEM_NATURE_TEXTE_STATIQUE);
	}

	/**
	 * La liste des repartitions associées sur une période
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public NSArray<EOTplRepartItemItemValeur> tosTplRepartItemItemValeur(NSTimestamp dateDebut, NSTimestamp dateFin) {
		NSArray<EOTplRepartItemItemValeur> result = null;

		//
		EOQualifier qual = getValiditeQualifier(
				EOTplRepartItemItemValeur.D_DEB_VAL_KEY, EOTplRepartItemItemValeur.D_FIN_VAL_KEY, dateDebut, dateFin);

		result = tosTplRepartItemItemValeur(qual);

		result = CktlSort.sortedArray(result, EOTplRepartItemItemValeur.TIV_POSITION_KEY);

		return result;
	}

	/**
	 * TODO faire generique recFiche : la fiche (fiche de poste, fiche LOLF ou
	 * fiche d'evaluation) Retrouver un enregistrement associ à une évaluation
	 * 
	 * @param recEvaluation
	 *          : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
	 * @return <em>null</em> si non trouvé.
	 * @return
	 */
	public EORepartFicheItem getRepartItemForEvaluation(
			EOEvaluation eoEvalution) {
		EORepartFicheItem result = null;

		EOQualifier qual = CktlDataBus.newCondition(
				EORepartFicheItem.TO_EVALUATION_KEY + "=%@",
				new NSArray<EOEvaluation>(eoEvalution));

		NSArray<EORepartFicheItem> array = tosRepartFicheItem(qual);

		if (array.count() > 0) {
			result = array.objectAtIndex(0);
		}

		return result;
	}

	/**
	 * Retrouver la valeur d'un item de type champ libre. Se base sur un
	 * enregistrement du type <code>EORepartFicheItem</code>. Si non trouvé, alors
	 * la chaine vide est retournee.
	 */
	public String getStrChampLibre(EOEvaluation eoEvaluation) {
		String strResult = StringCtrl.emptyString();
		// trouver l'enregistrement pour cette fiche
		EORepartFicheItem recRepartFicheItem = getRepartItemForEvaluation(eoEvaluation);
		// si existant, alors on va retourner la valeur associee
		if (recRepartFicheItem != null) {
			strResult = recRepartFicheItem.rfiValeurLibre();
		}
		return strResult;
	}
}
