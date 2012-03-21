package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderExercice;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;


/**
 * Ecran de suivi des fiche LOLF
 * 
 * @author ctarade
 */
public class AdminSyncFicheLolf 
	extends FeveWebComponent {
	
	// liste des exercices comptable
	public NSArray exerciceList;
	public EOExercice exerciceItem;
	public EOExercice exerciceSelected;
	
	// message information sur la derniere operation
	public String lastMessage;
	
	public AdminSyncFicheLolf(WOContext context) {
		super(context);
		initComponent();
	}

	/**
	 * 
	 */
	private void initComponent() {
		// la liste des exercices saisis avec des fonction silland dans l'administration
		exerciceList = NSArrayCtrl.removeDuplicate(NSArrayCtrl.flattenArray((NSArray) 
				EORepartSillandLolf.fetchAllRepartLolfSillands(ec).valueForKeyPath(
						//EORepartSillandLolf.TO_TYPE_ACTION_KEY + "." + EOTypeAction.EXERCICE_KEY)));
						EORepartSillandLolf.TO_EXERCICE_KEY)));
		// selection de l'exercice courant
		//exerciceSelected = FinderExercice.getExercicePourDate(ec, DateCtrl.now());
		exerciceSelected = FinderExercice.getExerciceDepensePourDate(ec, DateCtrl.now());
		if (!exerciceList.containsObject(exerciceSelected)) {
			exerciceSelected = null;
		}
	}
	
	
	//
	
	/**
	 * Lancer la creation de toutes les fiches LOLF attendues
	 * sur l'exercice selectionné
	 * @throws Throwable 
	 */
	public WOComponent doCreateMissingFicheLolf() throws Throwable {
		int nbFicheDejaExistantes = 0;
		int nbFicheCrees = 0;
		int nbFichesRecuperees = 0;
		
		if (exerciceSelected != null) {
			//
			EOExercice exercicePrecedent = FinderExercice.getExercice(ec, exerciceSelected.exeExercice().intValue()-1);
			//
			NSTimestamp dDebutExercice = DateCtrl.stringToDate("01/01/" + exerciceSelected.exeExercice().intValue());
			NSTimestamp dFinExercice = DateCtrl.stringToDate("31/12/" + exerciceSelected.exeExercice().intValue());
			// liste de tous les postes ouverts sur l'exercice en question
			NSArray posteOuvertList = EOPoste.fetchPostes(
					ec,
					CktlDataBus.newCondition(
							EOPoste.POS_D_DEBUT_KEY + " <=%@ and (" +EOPoste.POS_D_FIN_KEY + " >=%@ or " + EOPoste.POS_D_FIN_KEY + " = nil)",
					new NSArray(new NSTimestamp[]{dDebutExercice, dFinExercice})), null);
			// recreer une fiche a partir de la N-1
			for (int i=0; i<posteOuvertList.count(); i++) {
				EOPoste posteOuvertItem = (EOPoste) posteOuvertList.objectAtIndex(i);
				EOFicheLolf ficheLolfExercicePrev = null;
				if (posteOuvertItem.tosFicheLolf().count() > 0) {
					// retrouver la fiche sur l'exercice selectionné
					NSArray ficheLolfFound = posteOuvertItem.tosFicheLolf(
							CktlDataBus.newCondition(EOFicheLolf.TO_EXERCICE_KEY +" =%@", new NSArray(exerciceSelected)));
					// ya pas, bon ben fiche exercice N-1
					if (ficheLolfFound.count() == 0) {
						if (exercicePrecedent != null) {
							// il existe un exercice precedent
							ficheLolfFound = posteOuvertItem.tosFicheLolf(
									CktlDataBus.newCondition(EOFicheLolf.TO_EXERCICE_KEY +" =%@", new NSArray(exercicePrecedent)));
							if (ficheLolfFound.count() > 0) {
								ficheLolfExercicePrev = (EOFicheLolf) ficheLolfFound.lastObject();
							}
						}
					} else {
						// on a une fiche actuelle --> on passe
						nbFicheDejaExistantes++;
						continue;
					}
				}
				// si on a la fiche N-1, on fait copier coller
				if (ficheLolfExercicePrev != null) {
					EOFicheLolf.newRecordInContextFrom(ficheLolfExercicePrev, exerciceSelected);
					nbFichesRecuperees++;
				} else {
					// on a pas de fiche ... on en fait une from scratch
					EOFicheLolf.newRecordInContext(ec, posteOuvertItem, exerciceSelected);
				}
				nbFicheCrees++;
			}
		}
		// une ptite sauvegarde
		UtilDb.save(ec, "");
		// message
		CktlLog.log("Creation des fiches LOLF existantes/creees/copiees_collees - " + nbFicheDejaExistantes + "/" + nbFicheCrees + "/" + nbFichesRecuperees);
		lastMessage = 
			"Fiches d&eacute;j&agrave; existantes  :" + nbFicheDejaExistantes + "<br/>" + 
			"Total fiches cr&eacute;&eacute;es: " + nbFicheCrees + "<br/>" + 
			"Total fiches &quot;copi&eacute/coll&eacute;&quot; : " + nbFichesRecuperees;
		
		return null;
	}
}