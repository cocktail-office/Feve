package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.FinderTypeActionFeve;
import org.cocktail.feve.eos.modele.grhum.EOFctSilland;
import org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderExercice;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderLolfNomenclatureDepense;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktljefyadmin.common.metier.EOLolfNomenclatureDepense;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

/**
 * Page d'administration concernant les parametres LOLF
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class AdminLolf 
	extends FeveWebComponent {
	
	// liste des fonctions SILLAND
	public NSArray sillandList;
	public EOFctSilland sillandSelected;
	public EOFctSilland sillandItem;

	// liste des actions
	public EOLolfNomenclatureDepense lolfNomenclatureSelected;
	public EOLolfNomenclatureDepense lolfNomenclatureItem;

	// liste des exercices comptables
	public NSArray exerciceList;
	public EOExercice exerciceSelected;
	public EOExercice exerciceItem;
	
	// liste des associations silland <-> actions
	public EORepartSillandLolf repartSillandLolfItem;
	
	// mode d'utilisation de l'interface
	public boolean isAdding;
	
	public AdminLolf(WOContext context) {
		super(context);
		initComponent();
	}
	
	private void initComponent() {
		sillandList = EOFctSilland.findAllFctSillandInContext(ec);
		// selection de la 1ere fonction
		if (sillandList.count() > 0) {
			sillandSelected = (EOFctSilland) sillandList.objectAtIndex(0);
		}
		// liste des exercices comptables (du plus vieux au plus recent)
		exerciceList = FinderExercice.getExercices(ec);
		exerciceList = CktlSort.sortedArray(exerciceList, EOExercice.EXE_EXERCICE_KEY);
		// selection du courant
		exerciceSelected = //FinderExercice.getExercicePourDate(ec, DateCtrl.now());
			FinderExercice.getExerciceDepensePourDate(ec, DateCtrl.now());
		// 
		isAdding = false;
	}
	
	
	// getters
	
	/**
	 * Liste de toutes les actions pour l'annee selectionnee, pour lesquels
	 * on enleve les actions deja affectees
	 * @return
	 */
	public NSArray getLolfNomenclatureList() {
		NSMutableArray result = new NSMutableArray(
				FinderLolfNomenclatureDepense.getTypeActions(ec, exerciceSelected));
				//FinderTypeAction.getTypeActions(ec, exerciceSelected));
		// suppression des destinations deja affectees
		NSArray affectees = (NSArray) getRepartSilLolfList().valueForKey(
				//EORepartSillandLolf.TO_TYPE_ACTION_KEY);
				EORepartSillandLolf.TO_LOLF_NOMENCLATURE_DEPENSE_KEY);
		for (int i=0; i<affectees.count(); i++) {
			result.removeIdenticalObject(affectees.objectAtIndex(i));
		}
		return result.immutableClone();
	}
	
	/**
	 * Liste de toutes les destinations associées sur l'exercice selectionné
	 * @return
	 */
	public NSArray getRepartSilLolfList() {
		return sillandSelected.tosRepartSilLolf(exerciceSelected);
	}


	// display
	
	/**
	 * Nombre de destination associées a chaque fonction silland affichee dans le popup
	 * @return
	 */
	public String sillandItemDisplay() {
		String result = sillandItem.display();
		result += " (" + sillandItem.tosRepartSilLolf(exerciceSelected).count() + ")";
		return result;
	}
	
	
	// navigation
	
	/**
	 * Passage en mode affectation d'une nouvelle action
	 */
	public WOComponent add() {
		isAdding = true;
		return null;
	}
	
	/**
	 * Passage en mode consultation
	 * @return
	 */
	public WOComponent read() {
		isAdding = false;
		return null; 
	}
	
	// manipulation de donnees
	
	/**
	 * Affecter l'action a la fct silland
	 * @throws Throwable 
	 */
	public WOComponent doAdd() throws Throwable {
		if (lolfNomenclatureSelected != null) {
			//EORepartSillandLolf.createRepartLolfSilland(ec, DateCtrl.now(), DateCtrl.now(), sillandSelected, lolfNomenclatureSelected);
			EORepartSillandLolf.createRepartLolfSilland(
					ec, DateCtrl.now(), DateCtrl.now(), exerciceSelected, sillandSelected, lolfNomenclatureSelected);
			UtilDb.save(ec, "");
		}
		read();
		return null;
	}

	/**
	 * Enlever l'affectation de l'action a la fct silland
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doDelete() throws Throwable {
		sillandSelected.deleteTosRepartSilLolfRelationship(repartSillandLolfItem);
		UtilDb.save(ec, "");
		read();
		return null;
	}
	
	/**
	 * Annuler l'operation en cours
	 * @return
	 */
	public WOComponent doCancel() {
		ec.revert();
		read();
		return null;
	}

	/**
	 * Importer les repartitions SILLAND / LOLF d'un exercice precedent
	 * sur l'exercice en cours
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doImportFromExerciceNM1() throws Throwable {
		if (exerciceSelected != null) {
			EOExercice exercicePrecedent = FinderExercice.getExercice(ec, exerciceSelected.exeExercice().intValue()-1);
			if (exercicePrecedent != null) {
				for (int i=0; i<sillandList.count(); i++) {
					EOFctSilland sil = (EOFctSilland) sillandList.objectAtIndex(i);
					// toutes les associations de l'annee N-1
					NSArray lolfList = sil.tosTypeAction(exercicePrecedent);
					// recreation sur l'exercice N
					for (int j=0; j<lolfList.count(); j++) {
						EOLolfNomenclatureDepense lolf = (EOLolfNomenclatureDepense) lolfList.objectAtIndex(j);
						// retrouver cette destination dans l'exercice N+1
						EOLolfNomenclatureDepense nextLolf = FinderTypeActionFeve.findTypeActionForExercice(ec, lolf, exerciceSelected);
						// 
						if (nextLolf != null) {
							//EORepartSillandLolf.createRepartLolfSilland(ec, DateCtrl.now(), DateCtrl.now(), sil, nextLolf);
							EORepartSillandLolf.createRepartLolfSilland(
									ec, DateCtrl.now(), DateCtrl.now(), exerciceSelected, sil, nextLolf);
						}
					}
				}
				UtilDb.save(ec, "");
			}
		}

		return null;
	}
	
	/**
	 * Sauvegarder le changement de visibilité (binding onclick des
	 * coches de visibilite enseignant / non enseignant)
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doChangeVisibilite() throws Throwable {
		UtilDb.save(ec, "");
		return null;
	}
	
	
	// boolean interface
	
	/**
	 * Le bouton d'import n'est disponible que s'il existe un exercice
	 * N-1 et que l'exercice N n'a aucune repartition SILLAND / LOLF
	 */
	public boolean showBtnDoImportFromExerciceNM1() {
		boolean show = false;
		EOExercice exercicePrecedent = FinderExercice.getExercice(ec, exerciceSelected.exeExercice().intValue()-1);
		if (exercicePrecedent != null) {
			show = EORepartSillandLolf.fetchRepartLolfSillands(
					ec,
					CktlDataBus.newCondition(
							//EORepartSillandLolf.TO_TYPE_ACTION_KEY + "." + EOTypeAction.EXERCICE_KEY + "=%@",
							EORepartSillandLolf.TO_EXERCICE_KEY + "=%@",
							new NSArray(exerciceSelected)),
							
					null).count() == 0;
		}
		return show;
	}
}