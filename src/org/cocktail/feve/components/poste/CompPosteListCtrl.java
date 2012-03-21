package org.cocktail.feve.components.poste;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertResponder;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;

/**
 * Controleur du composant {@link CompPosteList}
 * @author ctarade
 *
 */
public class CompPosteListCtrl
	extends A_ComponentControlerAndFilArianeNode {
	
	
	/**
	 * 
	 */
	private EOPoste posteItem;
	
	/**
	 * Affichage de l'occupation. On met la police en italique si 
	 * cette occupation n'est pas la courante a la fiche
	 */
	private boolean isOccupationCourante;

	/**
	 * L'occupation associee au poste. On affiche en priorite 
	 * celle qui est courante. Si non trouve, alors 
	 */
	private EOAffectationDetail recAffectationDetail;
 
	/**
	 * Le nombre d'occupations (affectation_detail) attachees au poste en cours
	 */
	private int nbOccupations;
 
	/**
	 * Le nombre de fiche de poste attachees au poste en cours
	 */
	private int nbFicheDePoste;
 
	/**
	 * Le nombre de fiche LOLF attachees au poste en cours
	 */
	private int nbFicheLolf;
  
  /**
   * L'individu connecte a-t-il les droits suppression sur le
   * poste <code>posteItem</code>
   */
	private boolean canDeletePosteItem;
  
  /**
   * L'individu connecte a-t-il les droits modification sur le
   * poste <code>posteItem</code>
   */
	private boolean canUpdatePosteItem;
  
  /**
   * L'individu connecte a-t-il les droits visualisation sur le
   * poste <code>posteItem</code>
   */
	private boolean canViewPosteItem;

  /**
   * L'individu connecte a-t-il les droits de voir la ligne du poste.
   * On regarde d'abord au niveau du poste, si rien alors on fouille
   * dans les fiches s'il y a au moins quelque chose
   */
	private boolean canAccessPosteItem;

	/**
	 * Le controleur pour la consultation du poste {@link #posteItem}.
	 * Doit être réinstancié pour chaque poste
	 */
	public CompPosteDetailCtrl compPosteDetailCtrlItem;

	/**
	 * Le controleur pour la duplication du poste {@link #posteItem}.
	 * Doit être réinstancié pour chaque poste
	 */
	public CompPosteDuplicationCtrl compPosteDuplicationCtrlItem;
	
	/**
	 * Indique si on est en train d'inspecter {@link #posteItem}
	 */
	public boolean showPosteDetailItem;
	
	/**
	 * Indique si on est en train de dupliquer {@link #posteItem}
	 */
	public boolean showPosteDuplicationItem;
	
	/**
	 * Le constructeur par defaut. 
	 * @param session
	 */
	public CompPosteListCtrl(Session session) {
		super(session);
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		compPosteDetailCtrlItem = null;
		compPosteDuplicationCtrlItem = null;
		showPosteDetailItem = false;
		showPosteDuplicationItem = false;
	}


  /**
   * Surcharge du setter de <code>posteItem</code>, dans lequel est initialise un
   * certain nombre de variables :
   * - <code>isOccupationCourante</code>
   * - <code>recOccupation</code>
   * - <code>nbFicheDePoste</code>
   * - <code>nbFicheLolf</code>
   */
  public void setPosteItem(EOPoste value) {
  	isOccupationCourante = true;
  	if (value != null) {
    	recAffectationDetail = value.toAffectationDetailActuelle();
    	if (recAffectationDetail == null) {
    		isOccupationCourante = false;
    		recAffectationDetail = value.toAffectationDetailDerniere();
    	}
    	nbOccupations = value.tosAffectationDetail().count();
    	nbFicheDePoste = value.tosFicheDePoste().count();
    	nbFicheLolf = value.tosFicheLolf().count();
  		
    	canDeletePosteItem = feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.SUPPRESSION, EOTypeDroitCible.POSTE, value, false);

    	canUpdatePosteItem = feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.POSTE, value, false);
    	
    	canViewPosteItem = feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.POSTE, value, false);
    
    	// voir s'il a acces a quelque chose
    	canAccessPosteItem = false;
    	if (canDeletePosteItem || canUpdatePosteItem || canViewPosteItem) {
    		canAccessPosteItem = true;
    	} 

    	// on cherche dans les fiches de poste ...
    	if (!canAccessPosteItem) {
    		NSArray ficheDePosteList = value.tosFicheDePoste();
    		for (int i = 0; !canAccessPosteItem && i < ficheDePosteList.count(); i++) {
    	 		canAccessPosteItem = feveUserInfo().hasAnyDroitAccesOnCible(
      				EOTypeDroitCible.FICHE_DE_POSTE, ficheDePosteList.objectAtIndex(i), false);
    		}
    		// si toujours rien, on cherche dans les fiches lolf
    		if (!canAccessPosteItem) {
    			NSArray ficheLolfList = value.tosFicheLolf();
    	 		for (int i = 0; !canAccessPosteItem && i < ficheLolfList.count(); i++) {
      	 		canAccessPosteItem = feveUserInfo().hasAnyDroitAccesOnCible(
        				EOTypeDroitCible.FICHE_LOLF, ficheLolfList.objectAtIndex(i), false);
      		}
    		}
    	}

  	}

  	posteItem = value;
 
  }
  

  
  // navigation

  /**
   * La classe interne - l'implementation de AlertResponder pour la suppression
   */  
  public class SupprimerPosteResponder implements CktlAlertResponder {
     
  	private WOComponent responderCaller;
   
  	public SupprimerPosteResponder(WOComponent component) {
  		responderCaller = component;
  	}
	     
  	public WOComponent respondToButton(int buttonNo) {
  		switch (buttonNo) {
  		case 2: 
  			return responderCaller;
  		case 1: 
  			ec().deleteObject(posteItem);
  			try {
  				UtilDb.save(ec(), "");
  			} catch (Throwable e) {
  				e.printStackTrace();
  			}
  			return responderCaller;
  		default: return null;
  		}
  	}
  }

  
 	/**
 	 * L'action de cliquer sur un poste dans la liste
 	 * @return
 	 */
 	public WOComponent doSelectPoste() {
 		compPosteDetailCtrlItem = new CompPosteDetailCtrl(feveSession(), posteItem);
 		compPosteDetailCtrlItem.setStringLabel("Détail du poste " + posteItem.posCode());
 		compPosteDetailCtrlItem.setLinkLabel("Accès au détail du poste");
 		compPosteDetailCtrlItem.setLinkTitle("Retourner au d&eacute;tail du poste");
 		showPosteDetailItem = true;
 		return null;
 	}
 
 	/**
 	 * L'action d'accéder à l'interface de duplication d'un poste dans la liste
 	 * @return
 	 */
 	public WOComponent doDupliquerPoste() {
 		compPosteDuplicationCtrlItem = new CompPosteDuplicationCtrl(feveSession(), posteItem);
 		compPosteDuplicationCtrlItem.setStringLabel("Copie du poste " + posteItem.posCode());
 		compPosteDuplicationCtrlItem.setLinkLabel("Accès l'interface de copie du poste");
 		compPosteDuplicationCtrlItem.setLinkTitle("Retourner au d&eacute;tail du poste");
 		showPosteDuplicationItem = true;
 		return null;
 	}
 	
 	
 	
 	
 	// getters générés automatiquement
 	
  
	public final EOPoste getPosteItem() {
		return posteItem;
	}

	public final boolean isOccupationCourante() {
		return isOccupationCourante;
	}

	public final boolean isNotOccupationCourante() {
		return !isOccupationCourante;
	}

	public final EOAffectationDetail getRecAffectationDetail() {
		return recAffectationDetail;
	}

	public final int getNbOccupations() {
		return nbOccupations;
	}

	public final int getNbFicheDePoste() {
		return nbFicheDePoste;
	}

	public final int getNbFicheLolf() {
		return nbFicheLolf;
	}

	public final boolean isCanDeletePosteItem() {
		return canDeletePosteItem;
	}

	public final boolean isCanUpdatePosteItem() {
		return canUpdatePosteItem;
	}

	public final boolean isCanViewPosteItem() {
		return canViewPosteItem;
	}

	public final boolean isCanAccessPosteItem() {
		return canAccessPosteItem;
	}
	
	public final boolean isAffichageFullComponent() {
		return showPosteDetailItem == false && showPosteDuplicationItem == false;
	}

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		A_ComponentControlerAndFilArianeNode child = null;
		
		if (showPosteDetailItem) {
			child = compPosteDetailCtrlItem;
		} else if (showPosteDuplicationItem) {
			child = compPosteDuplicationCtrlItem;
		}
		
		return child;
	}

	@Override
	protected void toLocalFullComponent() {
		compPosteDetailCtrlItem = null;
		compPosteDuplicationCtrlItem  = null;
		showPosteDetailItem = false;
		showPosteDuplicationItem = false;
	}

}
