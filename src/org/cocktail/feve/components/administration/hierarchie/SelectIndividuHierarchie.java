package org.cocktail.feve.components.administration.hierarchie;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOHierarchie;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

/**
 * Composant de selection d'un individu pour saisie dans la hierarchie
 * 
 * @author ctarade
 */
public class SelectIndividuHierarchie 
	extends FeveWebComponent {
	
	// bindings
	public EOEvaluationPeriode inEoEvaluationPeriode;
	public EOHierarchie inEoHierarchie;
	public String selectName;
	public String formName;
	
  // radio ajout individu
  public final String TYPE_INDIVIDU_NON_ENS 	= "NON_ENS";
  public final String TYPE_INDIVIDU_ENS     	= "ENS";
  public final String TYPE_INDIVIDU_AUTRE     = "AUTRE";
  public String typeIndividu;

  // radio modif individu
  public final String HIE_INDIVIDU_NON_HIE = "NON_HIE";
  public final String HIE_INDIVIDU_HIE     = "HIE";
  public String hieIndividu;
  
  // liste des individus
  public EOIndividu individuItem;
  public NSArray<EOIndividu> individuSelectedArray; // selection dans le browser
  public EOIndividu individuSelected; // selection dans le FieldSearchIndividu
  
	public SelectIndividuHierarchie(WOContext context) {
		super(context);
		initComponent();
	}
	
	/**
	 * 
	 */
	private void initComponent() {
		typeIndividu = TYPE_INDIVIDU_NON_ENS;
		hieIndividu = HIE_INDIVIDU_NON_HIE;
	}
	

	/**
	 * Liste des individus a afficher
	 * @return
	 */
	public NSArray<EOIndividu> getIndividuArray() {
  	NSArray<EOIndividu> individuArray = new NSArray<EOIndividu>();
    if (HIE_INDIVIDU_HIE.equals(hieIndividu)) {
      if (TYPE_INDIVIDU_NON_ENS.equals(typeIndividu)) {
        individuArray = EOIndividu.findIndividuActuelNonEnsInHierarchieInContext(ec, inEoEvaluationPeriode);
      } else {
        individuArray = EOIndividu.findIndividuActuelEnsInHierarchieInContext(ec, inEoEvaluationPeriode);
      }        
    } else {
      if (TYPE_INDIVIDU_NON_ENS.equals(typeIndividu)) {
        individuArray = EOIndividu.findIndividuActuelNonEnsNotInHierarchieInContext(ec, inEoEvaluationPeriode);
      } else {
        individuArray = EOIndividu.findIndividuActuelEnsNotInHierarchieInContext(ec, inEoEvaluationPeriode);
      }
    }
    // classement alpha
    individuArray = EOSortOrdering.sortedArrayUsingKeyOrderArray(individuArray, EOIndividu.INDIVIDU_SORT);
    return individuArray;
  }
	
	/**
	 * Variable sortante : individu selectionné
	 * @return
	 */
  public EOIndividu getOutEoIndividuSelected() {
    EOIndividu selection = null;
    
    if (isAfficherBrowserIndividu()) {
    	// en mode browser
      if (individuSelectedArray != null && individuSelectedArray.count() > 0) {
        selection = individuSelectedArray.lastObject();
      }
    } else {
    	// en mode recherche
    	selection = individuSelected;
    }
    
    return selection;
  }
  
  
  // setter bidon ..
  
  public void setOutEoIndividuSelected(EOIndividu value) {
  	
  }
  
  // rappatriement ancien composant, a changer en plus propre ...
  
  /**
   * Identifiant unique pour le composant
   */
  private String getId() {
  	String id = "";
  	
  	if (inEoHierarchie != null && inEoHierarchie.toIndividu() != null) {
  		id = String.valueOf(inEoHierarchie.toIndividu().persId().intValue());
  	}
  	
  	return id;
  }
  
  /**
   * nom du nom form unique
   */
  public String fieldName() {
    return "Field" + getId();
  }
  
  /**
   * nom du select form unique
   */
  public String selectName() {
    return "Select" + getId();
  }
  
  
  // 
  
  /**
   * On masque le browser des individus si 
   * on recherche un agent "autre" (trop de monde ...)
   */
  public boolean isAfficherBrowserIndividu() {
  	boolean isAfficher = true;
  	
  	if (TYPE_INDIVIDU_AUTRE.equals(typeIndividu)) {
  		isAfficher = false;
  	}
  	
  	return isAfficher;
  }
}