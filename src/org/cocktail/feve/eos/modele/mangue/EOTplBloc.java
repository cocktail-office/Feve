package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.components.common.CompNoticePromotion;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

public class EOTplBloc extends _EOTplBloc {

	// noms des blocs
	public final static String TPL_BLOC_COMPETENCES_PROFESSIONNELLES_ET_TECHNICITE_CODE = "COMPPROTEC";
	public final static String TPL_BLOC_MANAGEMENT_CODE = "MANAGEMENT";
	public final static String TPL_BLOC_CONTRIBUTION_A_L_ACTIVITE_DU_SERVICE_CODE = "CONACTSERV";
	public final static String TPL_BLOC_QUALITES_PERSONNELLES_ET_RELATIONNELLES_CODE = "QUAPERSREL";

	public EOTplBloc() {
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

	public boolean isFacultatif() {
		return tblFacultatif().equals(OUI);
	}

	/**
	 * Surcharge du lien vers tosTplItem pour avoir un classement selon le temoin
	 * <code>titPosition</code>
	 */
	public NSArray<EOTplItem> tosTplItemSorted() {
		NSArray<EOTplItem> tosTplItem = tosTplItem();

		tosTplItem = CktlSort.sortedArray(tosTplItem, EOTplItem.TIT_POSITION_KEY);

		return tosTplItem;
	}

	/**
	 * Definition de l'ancre HTML associee au bloc (chaine + clé primaire)
	 */
	public String anchorName() {
		Number tblKey = (Number) EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("tblKey");
		return "tplBloc-" + Integer.toString(tblKey.intValue());
	}

	/**
	 * Bloc formation : geres par l'interface <code>CompTplBlocFormation</code>
	 * 
	 * @return
	 */
	public boolean isBlocNatureFormation() {
		boolean isFormation = false;
		if (toTplBlocNature().tbnLibelle().equals(EOTplBlocNature.TPL_BLOC_NATURE_FORMATION)) {
			isFormation = true;
		}
		return isFormation;
	}

	/**
	 * Bloc formation souhaitee : geres par l'interface
	 * <code>CompTplBlocFormation</code>
	 * 
	 * @return
	 */
	public boolean isBlocNatureFormationSouhaitee() {
		boolean isBlocNatureFormationSouhaitee = false;
		if (toTplBlocNature().tbnLibelle().equals(EOTplBlocNature.TPL_BLOC_NATURE_FORMATION_SOUHAITEE)) {
			isBlocNatureFormationSouhaitee = true;
		}
		return isBlocNatureFormationSouhaitee;
	}

	/**
	 * Bloc notice de promotions : geres par l'interface
	 * {@link CompNoticePromotion}
	 * 
	 * @return
	 */
	public boolean isBlocNatureNoticeDePromotions() {
		boolean isBlocNatureNoticeDePromotions = false;
		if (toTplBlocNature().tbnLibelle().equals(EOTplBlocNature.TPL_BLOC_NOTICE_DE_PROMOTIONS)) {
			isBlocNatureNoticeDePromotions = true;
		}
		return isBlocNatureNoticeDePromotions;
	}

	/**
	 * Bloc dynamiques : geres par <code>CompTplOnglet</code>
	 * 
	 * @return
	 */
	public boolean isBlocNatureDynamique() {
		boolean isDynamique = false;
		if (toTplBlocNature().tbnLibelle().equals(EOTplBlocNature.TPL_BLOC_NATURE_DYNAMIQUE)) {
			isDynamique = true;
		}
		return isDynamique;
	}

	/**
	 * TODO faire generique recFiche : la fiche (fiche de poste, fiche LOLF ou
	 * fiche d'evaluation) Retrouver un enregistrement associe.
	 * 
	 * @param ec
	 * @param recTplBloc
	 *          : le bloc facultatif
	 * @param recEvaluation
	 *          : la fiche (fiche de poste, fiche LOLF ou fiche d'evaluation)
	 * @return <em>null</em> si non trouv�.
	 */
	public EORepartFicheBlocActivation getActivationRecord(EOEvaluation recEvaluation) {
		EORepartFicheBlocActivation result = null;

		EOQualifier qual = CktlDataBus.newCondition(
					EORepartFicheBlocActivation.TO_EVALUATION_KEY + " = %@",
					new NSArray<EOEvaluation>(recEvaluation));

		NSArray<EORepartFicheBlocActivation> array = tosRepartFicheBlocActivation(qual);

		if (array.count() > 0) {
			result = array.objectAtIndex(0);
		}

		return result;
	}
}
