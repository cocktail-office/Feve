package org.cocktail.feve.components.poste;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.components.common.CompOccupationCtrl;
import org.cocktail.feve.components.fichedeposte.CompFicheDePosteListCtrl;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;

import com.webobjects.appserver.WOComponent;

/**
 * Controleur du composant {@link CompPosteDetail}
 * @author ctarade
 *
 */
public class CompPosteDetailCtrl 
	extends A_ComponentControlerAndFilArianeNode 
		implements I_CallingCompPosteMetaData {
 
	/**
	 * Le poste en cours de detail
	 */
	public EOPoste poste;
	
	/**
   * L'individu connecte a-t-il les droits de modifier le {@link #poste()}
   */
  private boolean canUpdatePoste;
  
  /**
   * L'individu connecte a-t-il les droits de creer des fiches
   * de postes sur le  {@link #poste()}
   */
  private boolean canCreateFicheDePosteOnPoste;
    
  /**
   * Le controleur utilisé pour la changement des meta données
   * du poste (code, libelle, dates)
   */
  public CompPosteMetaDataCtrl compPosteMetaDataCtrlUpdate;
    
  /**
   * Le controleur utilisé pour la gestion des occupations 
   */
  public CompOccupationCtrl compOccupationCtrl;
  
  /**
   * Le controleur utilisé pour afficher la liste des fiches de poste
   */
  public CompFicheDePosteListCtrl compFicheDePosteListCtrl;
  
  /**
   * Indique si on est en train de mettre a jour les meta données
   * du poste (on affiche alors {@link CompPosteMetaData} en pleine page)
   */
  public boolean showCompPosteMetaDataCtrl;
  
  
  /**
   * @deprecated
   * Constructeur par defaut, utiliser {@link #CompPosteDetailCtrl(Session, EOPoste)}
   * @param session
   */
	public CompPosteDetailCtrl(Session session) {
		super(session);
	}
	
	/**
	 * 
	 * @param session
	 * @param aPoste
	 */
	public CompPosteDetailCtrl(Session session, EOPoste aPoste) {
		super(session);
		poste = aPoste;
		initCtrl();
	}
	
	/**
	 * Initialisation des variables
	 */
	private void initCtrl() {
		canUpdatePoste = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.POSTE, poste, false);
		canCreateFicheDePosteOnPoste = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.CREATION, EOTypeDroitCible.FICHE_DE_POSTE, poste, false);
		// le composant de gestion des occupations
		compOccupationCtrl = new CompOccupationCtrl(feveSession(), poste);
		// le composant de gestion des fiches de poste
		compFicheDePosteListCtrl = new CompFicheDePosteListCtrl(feveSession(),
				poste.tosFicheDePoste(), "Pas de fiche de poste",
				true, true, false, canCreateFicheDePosteOnPoste, true, poste, feveUserInfo().isAdmin(), false);
		// on affiche pas le menu associé car la liste est au sein meme de ce composant
		compFicheDePosteListCtrl.setShowNode(false);
	}
	
	
	// navigation
	
	/**
	 * Aller a la page de modification des meta données du poste
	 */
	public WOComponent toCompPosteMetaDataUpdate() {
		compPosteMetaDataCtrlUpdate = new CompPosteMetaDataCtrl(feveSession(), poste, this);
		showCompPosteMetaDataCtrl = true;
		return null;
	}

	public void doAfterCompPosteMetaDataCancel() {
		showCompPosteMetaDataCtrl = false;
	}

	public void doAfterCompPosteMetaDataSuccess() {
		showCompPosteMetaDataCtrl = false;
	}
	
	
	/**
	 * Revenir a {@link CompPosteDetail} en page pleine
	 * @return
	 */
	public void toLocalFullComponent() {
		// modifications de meta données
		if (compPosteMetaDataCtrlUpdate != null) {
			doAfterCompPosteMetaDataCancel();
		}
		// la liste des occupations
		compOccupationCtrl.doCancelOccupationAdd();
		compOccupationCtrl.doCancelOccupationUpdate();
		// la liste des fiches de poste (pas forcément remplit pour les postes enseignant par exemple)
		if (compFicheDePosteListCtrl != null) {
			// ajout ou modification de meta donnée
			compFicheDePosteListCtrl.doCancelCompFicheDePosteAdd();
			compFicheDePosteListCtrl.doCancelCompFicheDePosteUpdate();
			// contenu de la fiche
			compFicheDePosteListCtrl.ficheDePosteSelected = null;
		}
	}
	
	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return compFicheDePosteListCtrl;
	}
	
	
	// getters manuel
	
	/**
	 * On affiche le composant entier des lors qu'on ne change pas
	 * les meta données du poste ou qu'on inspecte pas une fiche de poste
	 */
	public boolean showFullComponent() {
		return !compFicheDePosteListCtrl.isAFicheDePosteSelected();
	}
	
	
	
	// getters générés automatiquement
	
	public final boolean isCanUpdatePoste() {
		return canUpdatePoste;
	}
}
