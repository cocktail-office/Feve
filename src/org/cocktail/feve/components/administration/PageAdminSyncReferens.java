package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.grhum.old.EOActivite;
import org.cocktail.feve.eos.modele.grhum.old.EOCompetence;
import org.cocktail.feve.eos.modele.grhum.old.EOEmploiType;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpActi;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpComp;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;


/**
 * Page de synchronisation des donnees associees aux fiches de poste
 * et autre, avec la base de donnees importee de REFERENS
 * 
 * @author ctarade
 */
public class PageAdminSyncReferens extends FeveWebComponent {

	/** 
	 * La liste des emplois associes a des fiches de poste, mais n'ayant pas trouve
	 * d'equivalent dans REFERENS
	 */
	public NSArray emploiList;
	public EOEmploiType emploiItem;
	
	/** 
	 * La liste des activites associes a des fiches de poste, mais n'ayant pas trouve
	 * d'equivalent dans REFERENS
	 */
	public NSArray activiteList;
	public EOActivite activiteItem;
	
	/** 
	 * La liste des competences associes a des fiches de poste, mais n'ayant pas trouve
	 * d'equivalent dans REFERENS
	 */
	public NSArray competenceList;
	public EOCompetence competenceItem;
	
	/** L'enregistrement selectionne pour raccrochage */
	public CktlRecord recordSelected;
	/** les fiches de postes associees */
	public NSArray ficheDePosteList;
	public EOFicheDePoste ficheDePosteItem;
	/** les evaluations associees */
	public NSArray evaluationList;
	public EOEvaluation evaluationItem;
	
	/** le/les mots clefs pour la recherche d'objets dans REFERENS */
	public String keyword;
	
	/** la liste des resultats de la recherche */
	public NSArray resultList;
	public CktlRecord resultItem;
	public NSArray resultSelecteds;
	public CktlRecord resultSelected;
	
	/** etat recherche */
	public boolean isSearchDone;
	public boolean errKeyworkTooShort;
	public boolean errNoResult;
	
	public PageAdminSyncReferens(WOContext context) {
		super(context);
		initComponent();
	}
	
	/**
	 * Effectue l'initialisation du composant
	 */
	private void initComponent() {
		refreshLists();
		keyword = StringCtrl.emptyString();
		errKeyworkTooShort = false;
		errNoResult = false;
		isSearchDone = false;
	}
	
	// navigation
	
	/**
	 * Effectuer la selection d'un enregistrement pour traitement.
	 * On rafraichit la liste des objets lies.
	 */
	public WOComponent doSelectRecord() {
		resetSearch();
		if (emploiItem != null) {
			recordSelected = emploiItem;
			ficheDePosteList = EOFicheDePoste.fetchFicheDePostes(ec, 
					CktlDataBus.newCondition(EOFicheDePoste.TO_EMPLOI_TYPE_KEY+"=%@", new NSArray(emploiItem)), null);
			evaluationList = new NSArray();
		} else if (activiteItem != null) {
			recordSelected = activiteItem;
			ficheDePosteList = EOFicheDePoste.fetchFicheDePostes(ec, 
					CktlDataBus.newCondition(
							EOFicheDePoste.TOS_REPART_FDP_ACTI_KEY+"."+EORepartFdpActi.TO_ACTIVITE_KEY+"=%@", new NSArray(activiteItem)), null);
			evaluationList = new NSArray();
		} else if (competenceItem != null) {
			recordSelected = competenceItem;
			ficheDePosteList = EOFicheDePoste.fetchFicheDePostes(ec, 
					CktlDataBus.newCondition(
							EOFicheDePoste.TOS_REPART_FDP_COMP_KEY+"."+EORepartFdpComp.TO_COMPETENCE_KEY+"=%@", new NSArray(competenceItem)), null);
			evaluationList = (NSArray) EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComps(ec, 
					CktlDataBus.newCondition(
							EORepartEvaNouvelleComp.TO_COMPETENCE_KEY+"=%@", new NSArray(competenceItem)), null).valueForKey(EORepartEvaNouvelleComp.TO_EVALUATION_KEY);
		} 
		
		return null;
	}
	
	/**
	 * Abandonner la selection de la donnee a modifier
	 * @return
	 */
	public WOComponent doClearSelection() {
		recordSelected = null;
		ficheDePosteList = new NSArray();
		evaluationList = new NSArray();
		return null;
	}
	
	/**
	 * Effectuer la recherche dans les donnes de REFERENS.
	 * La taille minimum du mot recherche est controlee
	 * @return
	 */
	public WOComponent doSearch() {
		errKeyworkTooShort = false;
		if (!StringCtrl.isEmpty(keyword) && keyword.length() >= 3) {
			search();
		} else {
			errKeyworkTooShort = true;
		}
		return null;
	}
	
	/**
	 * Raccrocher la donnee REFERENS selectionnee aux objets
	 * dependants de l'ancienne donnee FEVE
	 * @return
	 */
	public WOComponent doReaffecter() {
		if (resultSelected instanceof EOReferensEmplois) {
			// emploi des fiches
			for (int i=0; i<ficheDePosteList.count(); i++) {
				EOFicheDePoste recFicheDePoste = (EOFicheDePoste) ficheDePosteList.objectAtIndex(i);
				recFicheDePoste.addObjectToBothSidesOfRelationshipWithKey(resultSelected, EOFicheDePoste.TO_REFERENS_EMPLOIS_KEY);
			}
		} else if (resultSelected instanceof EOReferensActivites) {
			// activites des fiches
			for (int i=0; i<ficheDePosteList.count(); i++) {
				EOFicheDePoste recFicheDePoste = (EOFicheDePoste) ficheDePosteList.objectAtIndex(i);
				for (int j=0; j<recFicheDePoste.tosRepartFdpActi().count(); j++) {
					EORepartFdpActi recRepart = (EORepartFdpActi) recFicheDePoste.tosRepartFdpActi().objectAtIndex(j);
					if (recRepart.toActivite().actLibelle().equals(((EOActivite) recordSelected).actLibelle())) {
						recRepart.addObjectToBothSidesOfRelationshipWithKey(resultSelected, EORepartFdpActi.TO_REFERENS_ACTIVITES_KEY);
					}
				}
			}
		} else if (resultSelected instanceof EOReferensCompetences) {
			// competences des fiches
			for (int i=0; i<ficheDePosteList.count(); i++) {
				EOFicheDePoste recFicheDePoste = (EOFicheDePoste) ficheDePosteList.objectAtIndex(i);
				for (int j=0; j<recFicheDePoste.tosRepartFdpComp().count(); j++) {
					EORepartFdpComp recRepart = (EORepartFdpComp) recFicheDePoste.tosRepartFdpComp().objectAtIndex(j);
					if (recRepart.toCompetence().comLibelle().equals(((EOCompetence) recordSelected).comLibelle())) {
						recRepart.addObjectToBothSidesOfRelationshipWithKey(resultSelected, EORepartFdpComp.TO_REFERENS_COMPETENCES_KEY);
					}
				}
			}
			// competences detectees de l'evaluation
			for (int i=0; i<evaluationList.count(); i++) {
				EOEvaluation recEvaluation = (EOEvaluation) evaluationList.objectAtIndex(i);
				for (int j=0; j<recEvaluation.tosRepartEvaNouvelleComp().count(); j++) {
					EORepartEvaNouvelleComp recRepart = (EORepartEvaNouvelleComp) recEvaluation.tosRepartEvaNouvelleComp().objectAtIndex(j);
					if (recRepart.toCompetence().comLibelle().equals(((EOCompetence) recordSelected).comLibelle())) {
						recRepart.addObjectToBothSidesOfRelationshipWithKey(resultSelected, EORepartEvaNouvelleComp.TO_REFERENS_COMPETENCES_KEY);
					}
				}
			}
		} 
		// sauvegarde
		try {
			UtilDb.save(ec, "");
			// mettre a jour les listes
			refreshLists();
			// retour aux listes
			doClearSelection();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// boolean 
	
	/**
	 * Indique l'utilisateur a choisi un enregistrement a editer
	 */
	public boolean isARecordSelected() {
		return recordSelected != null;
	}
	
	/**
	 * Indique l'utilisateur a choisi un enregistrement correspondant
	 */
	public boolean isAResultSelected() {
		return resultSelected != null;
	}
	
	// display
	
	/**
	 * 
	 */
	public String getNatureRecordSelected() {
		String nature = "";
		if (recordSelected != null) {
			if (recordSelected instanceof EOEmploiType) {
				nature = "Emploi type";
			} else if (recordSelected instanceof EOActivite) {
				nature = "Activite";
			} else if (recordSelected instanceof EOCompetence) {
				nature = "Competence";
			} 
		}
		return nature;
	}
	
	// setter
	
	/**
	 * Recuperer la selection unique du browser 
	 */
	public void setResultSelecteds(NSArray value) {
		resultSelecteds = value;
		if (!NSArrayCtrl.isEmpty(resultSelecteds)) {
			resultSelected = (CktlRecord) resultSelecteds.lastObject();
		}
	}
	
	// donnees
	
	/**
	 * Rafraichir la liste des objets "orphelins"
	 */
	private void refreshLists() {
		// liste des fiches de postes "orphelines"
		NSArray ficheDePosteList = EOFicheDePoste.fetchFicheDePostes(
				ec, CktlDataBus.newCondition("toEmploiType <> nil AND "+EOFicheDePoste.TO_REFERENS_EMPLOIS_KEY+" = nil"), null);
		// remonter aux emplois associe
		emploiList = NSArrayCtrl.removeDuplicate((NSArray) ficheDePosteList.valueForKey("toEmploiType"));
		emploiList = CktlSort.sortedArray(emploiList, "display");
		// liste des activites "orphelines"
		NSArray repartFdpActiList = EORepartFdpActi.fetchRepartFdpActis(
				ec, CktlDataBus.newCondition("toActivite <> nil AND "+EORepartFdpActi.TO_REFERENS_ACTIVITES_KEY+" = nil"), null);
		activiteList = NSArrayCtrl.removeDuplicate((NSArray) repartFdpActiList.valueForKey("toActivite"));
		activiteList = CktlSort.sortedArray(activiteList, "display");
		// liste des competences "orphelines"
		NSArray repartFdpCompList = EORepartFdpComp.fetchRepartFdpComps(
				ec, CktlDataBus.newCondition("toCompetence <> nil AND "+EORepartFdpComp.TO_REFERENS_COMPETENCES_KEY+" = nil"), null);
		NSArray repartNouvelleCompList = EORepartEvaNouvelleComp.fetchRepartEvaNouvelleComps(
					ec, CktlDataBus.newCondition("toCompetence <> nil AND "+EORepartFdpComp.TO_REFERENS_COMPETENCES_KEY+" = nil"), null);
		NSArray result = repartFdpCompList.arrayByAddingObjectsFromArray(repartNouvelleCompList);
		// remonter aux activites
		competenceList = NSArrayCtrl.removeDuplicate((NSArray) result.valueForKey("toCompetence"));
		competenceList = CktlSort.sortedArray(competenceList, "display");
	}
	
	/**
	 * Effectue la recherche parmi les donnees de REFERENS
	 */
	private void search() {
		errNoResult = false;
		if (recordSelected instanceof EOEmploiType) {
			String strCond = EOReferensEmplois.CODEMEN_KEY + " like '*"+keyword+"*' OR " +
			 EOReferensEmplois.INTITULEMPLOI_KEY + " like '*"+keyword+"*' OR " +
			 EOReferensEmplois.NUMEMPLOI_KEY + " like '*"+keyword + "*'";
			resultList = EOReferensEmplois.fetchReferensEmploises(ec, CktlDataBus.newCondition(strCond), null);
		} else if (recordSelected instanceof EOActivite) {
			String strCond = EOReferensActivites.INTITULACTIVITE_KEY + " like '*"+keyword+"*' OR " +
			EOReferensActivites.INTITULACTIVITE_CLEAN_KEY + " like '*"+keyword+"*'";
			resultList = EOReferensActivites.fetchReferensActiviteses(ec, CktlDataBus.newCondition(strCond), null);
		} else if (recordSelected instanceof EOCompetence) {
			String strCond = EOReferensCompetences.INTITULCOMP_KEY + " like '*"+keyword+"*' OR " +
			EOReferensCompetences.INTITULCOMP_CLEAN_KEY + " like '*"+keyword+"*'";
			resultList = EOReferensCompetences.fetchReferensCompetenceses(ec, CktlDataBus.newCondition(strCond), null);
		} 
		resultList = CktlSort.sortedArray(resultList, "display");
		isSearchDone = true;
		errNoResult = (resultList.count() == 0);
	}
	
	private void resetSearch() {
		resultList = null;
		resultSelecteds = null;
		isSearchDone = false;
		errNoResult = false;
	}
}