package org.cocktail.feve.components.common;
import org.cocktail.feve.app.Session;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertResponder;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

/**
 * controleur du composant {@link CompOccupation}
 * @author ctarade
 *
 */
public class CompOccupationCtrl 
	extends A_ComponentControler
		implements I_CallingCompOccupationAdd, I_CallingCompOccupationUpdate {
	
	/** binding entrant : le poste concerné */
	public EOPoste poste;
	
  /**
   * L'individu connecte a-t-il les droits de modifier le poste {@link #poste}
   */
  public boolean canUpdatePoste;
  
  /**
   * L'individu connecte a-t-il les droits de consullter les occupations du poste {@link #poste}:
   * C'est soit le droit de le visualiser, soit le droit de le modifier
   */
  public boolean canViewOccupationsPoste;

  /**
   * Une item d'occupation dans la liste de toutes les occupations du poste
   */
  public EOAffectationDetail affectationDetailItem;
    
  /**
   * Indique s'il en mode ajout d'occupation
   */
  public boolean isAdding;
  
  /**
   * Indique s'il en mode modification d'occupation
   */
  public boolean isUpdating;
  
  /**
   * Le controleur du composant d'ajout d'occupation
   */
  public CompOccupationAddCtrl compOccupationAddCtrl;
  
  /**
   * Le controleur du composant de modification d'occupation
   */
  public CompOccupationUpdateCtrl compOccupationUpdateCtrl;
  
  
  
	/**
	 * @deprecated
	 * {@link #CompOccupationCtrl(Session, EOPoste)}
	 */
	public CompOccupationCtrl(Session session) {
		super(session);
	}

	/**
	 * A appeler dès que l'on change de poste
	 * @param session
	 * @param aPoste
	 */
	public CompOccupationCtrl(Session session, EOPoste aPoste) {
		super(session);
		poste = aPoste;
		initCtrl();
		isAdding = false;
		isUpdating = false;
	}

	/**
	 * 
	 */
	private void initCtrl() {
		canUpdatePoste = feveUserInfo().hasDroitOnCible(
  			EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.POSTE, poste, false);
		if (canUpdatePoste) {
  		canViewOccupationsPoste = true;
  	} else {
  		canViewOccupationsPoste = feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.POSTE, poste, false);
  	}
	}
  
  /**
   * La liste des affectation detail associees au poste 
   * actuellement affiche. Un classement chronologique
   * est applique a la liste.
   */
  public NSArray affectationDetailList() {
  	NSArray list = poste.tosAffectationDetail();
  	return EOSortOrdering.sortedArrayUsingKeyOrderArray(
  			list, CktlSort.newSort(EOAffectationDetail.D_DEBUT_AFFECTATION_DETAIL));
  }
  
  /**
   * cas du {@link A_ComponentControled} en {@link CompOccupation}
   * @return
   */
  protected CompOccupation getComponent() {
  	return (CompOccupation) super.getComponent();
  }
  
  /**
   * La classe interne - l'implementation de AlertResponder pour la suppression
   */  
  public class SupprimerOccupationResponder implements CktlAlertResponder {
      
    private WOComponent parentComponent;
    
    public SupprimerOccupationResponder(WOComponent aParentComponent) {
      parentComponent = aParentComponent;
    }
      
    public WOComponent respondToButton(int buttonNo) {
      switch (buttonNo) {
      case 2: 
        return parentComponent;
      case 1: 
      	poste.removeFromTosAffectationDetailRelationship(affectationDetailItem);
        ec().deleteObject(affectationDetailItem);
        try {
            UtilDb.save(ec(), "");
          } catch (Throwable e) {
            e.printStackTrace();
          }
        return parentComponent;
      default: return null;
      }
    }
  }

  /**
   * Passer en mode ajout d'occupation
   * @return
   */
  public WOComponent toAddOccupation() {
  	isAdding = true;
  	compOccupationAddCtrl = new CompOccupationAddCtrl(feveSession(), poste, this);
  	return null;
  }

  /**
   * Apres la creation d'une occupation, on affiche le composant en consultation
   */
  public void doAfterOccupationAddSuccess() {
  	isAdding = false;
  }

  /**
   * Apres annulation de l'ajout d'occupation
   */
  public void doCancelOccupationAdd() {
  	isAdding = false;
  }
	
  /**
   * Passer en mode modification d'occupation
   * @return
   */
  public WOComponent toUpdateOccupation() {
  	isUpdating = true;
  	compOccupationUpdateCtrl = new CompOccupationUpdateCtrl(feveSession(), affectationDetailItem, this);
  	return null;
  }

  /**
   * Apres la modification d'une occupation, on affiche le composant en consultation
   */
  public void doAfterOccupationUpdateSuccess() {
  	isUpdating = false;
  }

  /**
   * Apres annulation de la MAJ d'occupation
   */
	public void doCancelOccupationUpdate() {
		isUpdating = false;
	}
	
	
  // getters
  
  /**
   * Indique si le composant est en mode consultation
   */
  public boolean isViewing() {
  	return !isAdding && !isUpdating;
  }
}
