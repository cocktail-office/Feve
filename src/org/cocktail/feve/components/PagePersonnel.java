package org.cocktail.feve.components;

import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.evaluation.ListeEvaluationCtrl;
import org.cocktail.feve.components.fichedeposte.CompFicheDePosteListCtrl;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;


/**
 * Rassemble l'ensemble des donnees liees a la personne
 * connectee : ses fiches de poste, ses fiche LOLF et
 * ses evaluations.
 */

public class PagePersonnel extends A_ComponentControled {
	
	/**
	 * @deprecated
	 * la fiche LOLF en cours d'examination
	 */
	public EOGenericRecord selectedFlo;

	// gestion des postes
	public EOPoste posteItem;
	public EOPoste posteSelected;
	
	/**
	 * l'evaluation en cours d'examination
	 */
	public EOGenericRecord selectedEva;

  /**
   * Le controleur utilisé pour afficher la liste des fiches de poste
   */
  public CompFicheDePosteListCtrl compFicheDePosteListCtrl;
  
  /**
   * Le controleur utilisé pour afficher la liste des evaluations
   */
  public ListeEvaluationCtrl listeEvaluationCtrl;
	
	public PagePersonnel(WOContext context) {
		super(context);
		initComponent();
	}
	
	private void initComponent() {
		// on selectionne le poste actuel par defaut
		if (session.individuConnecte() != null && 
				session.individuConnecte().tosPosteActuel().count() > 0) {
			posteSelected = (EOPoste) session.individuConnecte().tosPosteActuel().lastObject();
		}

		//
		compFicheDePosteListCtrl = new CompFicheDePosteListCtrl(session,
				feveUserInfo().recIndividu().tosFicheDePoste(), 
				"Vous n'avez aucune fiche de poste personnelle enregistr&eacute;e", 
				false, false, false, false, true, null, false, false);
		// 
		listeEvaluationCtrl = new ListeEvaluationCtrl(session);
		listeEvaluationCtrl.setShowNode(false);
	}
	
	/**
	 * Un message d'avertissement : indique si l'utilisateur
	 * a des choses a faire
	 */
	public String warnMessage() {
		StringBuffer message = new StringBuffer();
		// il doit viser sa fiche de poste actuelle
		NSArray fiches = feveUserInfo().recIndividu().tosFicheDePosteActuelle();
		for (int i=0; i<fiches.count(); i++) {
			EOFicheDePoste uneFiche = (EOFicheDePoste) fiches.objectAtIndex(i);
			if (!uneFiche.fdpVisaAgentBool()) {
				message.append("<li>fiche de poste ").append(uneFiche.identifiant()).append("</li>");
			}
		}
		if (message.length() > 0)  {
			message.insert(0, "Attention, vous n'avez pas vis&eacute; vos fiches <b>actuelles</b> suivantes :<ul>");
			message.append("</ul>");
		}
		return message.toString();
	}
	
	/**
	 * Indique s'il faut afficher le message d'avertissement.
	 * S'il est vide, alors non. On ne l'affiche que sur la 
	 * page d'ensemble
	 */
	public boolean showWarnMessage() {
		return (compFicheDePosteListCtrl == null || compFicheDePosteListCtrl.ficheDePosteSelected == null) && /*selectedFlo == null &&*/ selectedEva == null && warnMessage().length() > 0;
	}

	/**
	 */
	public boolean showFdp() {
		return /*selectedFlo == null &&*/ selectedEva == null;
	}

	/**
	 */
	public boolean showFlo() {
		return (compFicheDePosteListCtrl == null || compFicheDePosteListCtrl.ficheDePosteSelected == null) && selectedEva == null;
	}
	
	/**
	 */
	public boolean showEva() {
		return /*selectedFlo == null &&*/ (compFicheDePosteListCtrl == null || compFicheDePosteListCtrl.ficheDePosteSelected == null);
	}
	
	/**
	 * Masquer ce qui affiche en detail. Retour a la liste.
	 */
	public void redisplayGlobalComponent() {
		selectedEva = null;
		compFicheDePosteListCtrl.ficheDePosteSelected = null;
		//selectedFlo = null;
		listeEvaluationCtrl.redisplayGlobalComponent();
	}
	
	/**
	 * On affiche la legende que sur la page generale
	 */
	public boolean showLegende() {
		return (compFicheDePosteListCtrl == null || compFicheDePosteListCtrl.ficheDePosteSelected == null) && /*selectedFlo == null && */selectedEva == null;
	}
	
	// gestion du poste et de la fiche LOLF
}