package org.cocktail.feve.components.fichedeposte;
// Generated by the WOLips TemplateEngine Plug-in at 14 nov. 2005 12:12:59

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;


public class CompFicheDePosteAgent extends FeveWebComponent {

  // variables entrantes
  private A_Fiche inputLaFicheDePoste;
  private A_Fiche inputLaFicheDePostePrecedente;
  
  // les occupants / l'occupant en cours
  private NSArray lesOccupations;
  private EOAffectationDetail lOccupationEnCours;
  private NSDictionary leDicoOccupationEnCours;
  private Integer indexOccupationEnCours;
  
  // element de repetition
  public EOIndividu unIndividu;
  
  public CompFicheDePosteAgent(WOContext context) {
    super(context);
  }
  
  // setter
  
  /**
   * detection du changement du point d'entree -> RAZ
   */
  public void setInputLaFicheDePoste(A_Fiche value) {
  	inputLaFicheDePostePrecedente = inputLaFicheDePoste;
    inputLaFicheDePoste = value;
    // cas special : il faut pouvoir detecter que 
    // les occupations sont differentes
    boolean resetAll = false;
    if (inputLaFicheDePoste != inputLaFicheDePostePrecedente) {
    	resetAll = true;
    }
    if (!resetAll && inputLaFicheDePoste!= null && inputLaFicheDePoste == inputLaFicheDePostePrecedente) {
    	NSArray newOccupations = inputLaFicheDePoste.tosAffectationDetail();
    	if (lesOccupations().count() != newOccupations.count())
    		resetAll = true;
    	if (!resetAll) {
    		for (int i=0; i<lesOccupations().count(); i++) {
    			EOAffectationDetail occ = (EOAffectationDetail) lesOccupations().objectAtIndex(i);
    			if (!newOccupations.containsObject(occ)) {
    				resetAll = true;
    				break;
    			}
    		}
    		for (int i=0; i<newOccupations.count(); i++) {
    			EOAffectationDetail occ = (EOAffectationDetail) newOccupations.objectAtIndex(i);
    			if (!lesOccupations().containsObject(occ)) {
    				resetAll = true;
    				break;
    			}
    		}
    	}
    }
    if (resetAll) {
    	lOccupationEnCours = null;
      lesOccupations = null;
      leDicoOccupationEnCours = null;
      indexOccupationEnCours = null;
    }
  }


  // getters

  public A_Fiche inputLaFicheDePoste() { return inputLaFicheDePoste; }
  
  /**
   * dico d'infos GEPETO de l'occupant en cours
   */
  public NSDictionary leDicoIndividuEnCours() {
    if (leDicoOccupationEnCours == null) {
      if (lOccupationEnCours() != null)
        leDicoOccupationEnCours = EOIndividu.findDicoAgentGepetoInContext(ec, lOccupationEnCours());        
    }
    return leDicoOccupationEnCours;
  }
  
  /**
   * Toutes les occupations (affectation_detail) attachees au poste 
   */
  public NSArray lesOccupations() {
    if (lesOccupations == null)
      lesOccupations = inputLaFicheDePoste.tosAffectationDetail();
    return lesOccupations;
  }
  
  public EOAffectationDetail lOccupationEnCours() {
    if (lOccupationEnCours == null && hasOccupations()) {
      // par defaut, l'occupant en cours
      lOccupationEnCours = inputLaFicheDePoste().toAffectationDetailActuelle();
      // si ya pas, on prend la derniere
      if (lOccupationEnCours == null)
      	lOccupationEnCours = inputLaFicheDePoste().toAffectationDetailDerniere();
    }
    return lOccupationEnCours;
  }
  
  /**
   * Indique si le poste a deja eu des occupations
   */
  public boolean hasOccupations() {
  	return lesOccupations() != null && lesOccupations().count() > 0;
  }
  
  public int indexOccupantEnCoursAffichage() {
    return indexOccupationEnCours().intValue() +1;
  }
  
  private Integer indexOccupationEnCours() {
    if (indexOccupationEnCours == null) {
      indexOccupationEnCours = new Integer(0);
      if (lOccupationEnCours() != null) {
        indexOccupationEnCours = new Integer(lesOccupations().indexOfIdenticalObject(lOccupationEnCours()));
      }
    }
    return indexOccupationEnCours;
  }
  
  // methodes de navigation
  
  public WOComponent avanceOccupant() {
    if (indexOccupationEnCours().intValue() < lesOccupations().count() -1) {
      lOccupationEnCours = (EOAffectationDetail) lesOccupations().objectAtIndex(indexOccupationEnCours().intValue() +1);
      leDicoOccupationEnCours = null;
      indexOccupationEnCours = null;
    }
    return neFaitRien();
  }
  
  public WOComponent reculeOccupant() {
    if (indexOccupationEnCours().intValue() > 0) {
      lOccupationEnCours = (EOAffectationDetail) lesOccupations().objectAtIndex(indexOccupationEnCours().intValue() -1);
      leDicoOccupationEnCours = null;
      indexOccupationEnCours = null;
    }
    return neFaitRien();
  }
  
  // boolean interface
  
  public boolean showLienAvance() {
    return indexOccupationEnCours().intValue() < lesOccupations().count() -1;
  }
  
  public boolean showLienRecule() {
    return indexOccupationEnCours().intValue() > 0;
  }

  public boolean isLOccupationEnCoursExiste() {
    return lOccupationEnCours() != null;
  }
    
  // photo 
  
  public boolean lIndividuEnCoursExiste()     {   
  	return lOccupationEnCours() != null && lOccupationEnCours.toAffectation().toIndividu() != null; 
  }
    
    
}