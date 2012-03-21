package org.cocktail.feve.eos.modele;

import org.cocktail.feve.app.finder.Finder;
import org.cocktail.feve.eos.modele.grhum.EOFctSilland;
import org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderLolfNomenclatureDepense;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

/**
 * Complements de finder sur les objets de Carambole TypeAction
 * 
 * @author ctarade
 */
public class FinderTypeActionFeve extends Finder {

	/**
	 * Retrouver une destination dans un exercice a partir d'une autre action
	 * d'un autre exercice
	 * @param ec
	 * @param refTypeAction
	 * @param exercice
	 * @return
	 */
	public static EOLolfNomenclatureDepense findTypeActionForExercice(
			EOEditingContext ec, EOLolfNomenclatureDepense refTypeAction, EOExercice exercice) {
		//return FinderTypeAction.getLesTypeActions(ec, exercice, refTypeAction.tyacCode());
		return FinderLolfNomenclatureDepense.getUnTypeAction(ec, exercice, refTypeAction.lolfCode());
	}
	
	/**
	 * Indique si une destination est affectee dans un exercice a une fonction SILLAND
	 * @param typeAction
	 * @param exercice
	 * @return
	 */
	public static boolean isDeclareeInExercice(
			EOEditingContext ec, EOLolfNomenclatureDepense typeAction, EOExercice exercice, EOFctSilland silland) {
		EOLolfNomenclatureDepense actionInExercice= findTypeActionForExercice(ec, typeAction, exercice);
  	// la destination est affectee a cette fonction silland dans le nouvel exercice ?
		return actionInExercice != null && EORepartSillandLolf.fetchRepartLolfSilland(
				ec,
			//CktlDataBus.newCondition(EORepartSillandLolf.TO_FCT_SILLAND_KEY + "=%@ and " + EORepartSillandLolf.TO_TYPE_ACTION_KEY + "=%@",
				CktlDataBus.newCondition(EORepartSillandLolf.TO_FCT_SILLAND_KEY + "=%@ and " + EORepartSillandLolf.TO_LOLF_NOMENCLATURE_DEPENSE_KEY + "=%@",
					new NSArray(new Object[]{silland, actionInExercice}))) != null;
	}
}
