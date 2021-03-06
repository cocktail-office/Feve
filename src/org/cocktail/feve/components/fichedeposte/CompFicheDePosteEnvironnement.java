package org.cocktail.feve.components.fichedeposte;
// Generated by the WOLips TemplateEngine Plug-in at 14 nov. 2005 16:06:43

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOStructureInfo;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSTimestamp;

public class CompFicheDePosteEnvironnement extends FeveWebComponent {

  // variables entrantes
  private EOFicheDePoste inputLaFicheDePoste; 
  private EOFicheDePoste inputLaFicheDePostePrecedente;
  
  // infos structure
  public String newMissionComposante, newMissionService, newProjetService, newMissionPoste, newContexteTravail;
  public EOStructureInfo recMissionComposante, recMissionService, recProjetService;
  private NSTimestamp laDateRef;
  
  // temoin d'erreur
  public String errorMessage;
  
  public CompFicheDePosteEnvironnement(WOContext context) {
    super(context);
  }

  
  // setter
  
  /**
   * Detection du changement du point d'entree -> RAZ si 
   * la fiche n'est plus la même
   */
  public void setInputLaFicheDePoste(EOFicheDePoste value) {
    inputLaFicheDePostePrecedente = inputLaFicheDePoste;
    inputLaFicheDePoste = value;
    if (inputLaFicheDePoste != inputLaFicheDePostePrecedente) {
    	recMissionComposante = null;
    	recMissionService = null;
    	recProjetService = null;
    	newMissionComposante = recMissionComposante().sinLibelle();
    	newMissionService = recMissionService().sinLibelle();
    	newProjetService = recProjetService().sinLibelle();
    	newMissionPoste = inputLaFicheDePoste.fdpMissionPoste();
    	newContexteTravail = inputLaFicheDePoste.fdpContexteTravail();
      laDateRef = null;
      errorMessage = null;
    	ancreHaut();
    }
  }
  
  
  // getter
  
  public boolean hasError() {
  	return !StringCtrl.isEmpty(errorMessage);
  }
  
  public EOFicheDePoste inputLaFicheDePoste() { return inputLaFicheDePoste; }

  private EOStructureInfo recMissionComposante() {
  	if (recMissionComposante == null) {
  		recMissionComposante = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
    			ec, 
    			inputLaFicheDePoste.toPoste().toStructure().toComposante(),
    			laDateRef(),
    			EOStructureInfo.TYPE_MISSION_COMPOSANTE);
    	// si enregistrement inexistant -> creation
    	if (recMissionComposante == null) {
    		recMissionComposante = EOStructureInfo.newRecordInContext(
    				ec,
  				inputLaFicheDePoste.toPoste().toStructure().toComposante(),
  				DateCtrl.now(),
  				null,
  				"",
  				EOStructureInfo.TYPE_MISSION_COMPOSANTE);        
    	}	
  	}
  	return recMissionComposante;
  }
  
  private EOStructureInfo recMissionService() {
  	if (recMissionService == null) {
    	recMissionService = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
    			ec, 
    			inputLaFicheDePoste.toPoste().toStructure(),
    			laDateRef(),
    			EOStructureInfo.TYPE_MISSION_SERVICE);
    	// si enregistrement inexistant -> creation
    	if (recMissionService == null) {
    		recMissionService = EOStructureInfo.newRecordInContext(
    				ec,
    				inputLaFicheDePoste.toPoste().toStructure(),
    				DateCtrl.now(),
    				null,
    				"",
    				EOStructureInfo.TYPE_MISSION_SERVICE);
    	}	 	
  	}
  	return recMissionService;
  }
  
  private EOStructureInfo recProjetService() {
  	if (recProjetService == null) {
    	recProjetService = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
      		ec, 
      		inputLaFicheDePoste.toPoste().toStructure(),
      		laDateRef(),
      		EOStructureInfo.TYPE_PROJET_SERVICE);
      // si enregistrement inexistant -> creation
      if (recProjetService == null) {
      	recProjetService = EOStructureInfo.newRecordInContext(
      			ec,
      			inputLaFicheDePoste.toPoste().toStructure(),
      			DateCtrl.now(),
      			null,
      			"",
      			EOStructureInfo.TYPE_PROJET_SERVICE);
      }
  	}
    return recProjetService;
  }
  
  /**
   * la date pour recuperer les infos des structure
   * - si la date est la fiche en cours : today
   * - si date passe, on prend la fin de la fiche
   * @return
   */
  private NSTimestamp laDateRef() {
    if (laDateRef == null) {
      if (inputLaFicheDePoste.fdpDFin() != null && DateCtrl.isAfter(DateCtrl.now(), inputLaFicheDePoste.fdpDFin())) {
        laDateRef = inputLaFicheDePoste.fdpDFin();
      } else {
        laDateRef = DateCtrl.now();
      }      
    }
    return laDateRef;
  }
  
  
  // PASSAGES D UN MODE A UN AUTRE
  
  public boolean isModeModifMissionComposante, isModeModif2, isModeModif3, isModeModif4, isModeModif5;
     
  
  private void desactiverModeModif() {
    isModeModifMissionComposante = isModeModif2 = isModeModif3 = isModeModif4 = isModeModif5 = false;
  }
  
  public WOComponent activerModeModifMissionComposante() {
    desactiverModeModif();
    ancreMissionComposante();
    isModeModifMissionComposante = true;
    return neFaitRien();
  }
  
  public WOComponent activerModeModif2() {
    desactiverModeModif();
    ancreMissionService();
    isModeModif2 = true;
    return neFaitRien();
  }
  
  public WOComponent activerModeModif3() {
    desactiverModeModif();
    ancreProjetService();
    isModeModif3 = true;
    return neFaitRien();
  }
  
  public WOComponent activerModeModif4() {
    desactiverModeModif();
    ancreMissionPoste();
    isModeModif4 = true;
    return neFaitRien();
  }
  
  public WOComponent activerModeModif5() {
    desactiverModeModif();
    ancreContexteTravail();
    isModeModif5 = true;
    return neFaitRien();
  }
  
  public boolean isDisabledTexteMissionComposante() {   return !isModeModifMissionComposante;}
  public boolean isDisabledTexte2() {   return !isModeModif2;}
  public boolean isDisabledTexte3() {   return !isModeModif3;}
  public boolean isDisabledTexte4() {   return !isModeModif4;}
  public boolean isDisabledTexte5() {   return !isModeModif5;}
  
  // DISPOS DES BOUTONS
  
  /**
   * Disponibilité du bouton de modification du texte 
   * "mission de la composante"
   */
  public boolean isDisabledBtnModifMissionComposante() {
  	boolean isDisabledBtnModifMissionComposante = true;
  	
  	// ne tester l'accès que si aucune autre champ est en modification
  	if (!isModeModif2 && !isModeModif3 && !isModeModif4 && !isModeModif5) {
  		// d'abord voir s'il est à le droit en tant que responsable de la composante du service
  		isDisabledBtnModifMissionComposante = !feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.MODIFICATION, 
    			EOTypeDroitCible.FICHE_DE_POSTE,
    			inputLaFicheDePoste.toPoste().toStructure().toComposante(),
    			true);
  		/*// sinon, voir s'il est responsable de la composante en tant que service
  		if (isDisabledBtnModifMissionComposante) {
    		isDisabledBtnModifMissionComposante = !feveUserInfo().hasDroitOnCible(
      			EOTypeDroitAcces.MODIFICATION, 
      			EOTypeDroitCible.FICHE_DE_POSTE,
      			inputLaFicheDePoste.toPoste().toStructure().toComposante(),
      			false);
  		}*/
  	}
  	
  	return isDisabledBtnModifMissionComposante;
  	
  	/*
    boolean verrouNiveauComposante = 
    	!feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.MODIFICATION, 
    			EOTypeDroitCible.FICHE_DE_POSTE,
    			inputLaFicheDePoste.toPoste().toStructure().toComposante(),
    			true);
    return verrouNiveauComposante || isModeModif2 || isModeModif3 || isModeModif4 || isModeModif5 ;*/
  }
  
  /**
   * Droit de modification du texte de service
   */
  public boolean isDisabledBtnModif2() {
    //boolean verrouNiveauService = !session.canModifStructure(inputLaFicheDePoste.toPoste().toStructure());
    boolean verrouNiveauService = 
    	!feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.MODIFICATION, 
    			EOTypeDroitCible.FICHE_DE_POSTE,
    			inputLaFicheDePoste.toPoste().toStructure(),
    			false);
    return verrouNiveauService || isModeModifMissionComposante || isModeModif3 || isModeModif4 || isModeModif5 ;
  }
  
  /**
   * Droit de modification du texte de service
   */
  public boolean isDisabledBtnModif3() {
    //boolean verrouNiveauService = !session.canModifStructure(inputLaFicheDePoste.toPoste().toStructure());
    boolean verrouNiveauService = 
    	!feveUserInfo().hasDroitOnCible(
    			EOTypeDroitAcces.MODIFICATION, 
    			EOTypeDroitCible.FICHE_DE_POSTE,
    			inputLaFicheDePoste.toPoste().toStructure(),
    			false);
    return verrouNiveauService || isModeModifMissionComposante || isModeModif2 || isModeModif4 || isModeModif5;
  }

  /**
   * Retourne vrai si la personne connectee n'a pas les droits
   * de faire de modifications sur cette fiche
   */
  private boolean verrouNiveauAgent() {
  	//return !session.canModifFiche(inputLaFicheDePoste);
  	return !feveUserInfo().hasDroitOnCible(
  			EOTypeDroitAcces.MODIFICATION, 
  			EOTypeDroitCible.FICHE_DE_POSTE,
  			inputLaFicheDePoste,
  			false);
  }
  
  /**
   * Retourne vrai si la fiche n'est pas modifiable a 
   * cause de son contenu. Une Fiche de poste n'est
   * modifiable que si aucun visa n'a ete fait
   */
  private boolean verrouNiveauFiche() {
  	return inputLaFicheDePoste().fdpVisaAgentBool() || 
  		inputLaFicheDePoste().fdpVisaRespBool() || 
  		inputLaFicheDePoste().fdpVisaDirecBool();
  }
  
  public boolean isDisabledBtnModif4() {
   return verrouNiveauAgent() || verrouNiveauFiche() || isModeModifMissionComposante || isModeModif2 || isModeModif3 || isModeModif5;
  }
  
  public boolean isDisabledBtnModif5() {
   return verrouNiveauAgent() || verrouNiveauFiche() || isModeModifMissionComposante || isModeModif2 || isModeModif3 || isModeModif4;
  }

  // datas
  
  private WOComponent sauvegarder() throws Throwable {
    UtilDb.save(ec, "");
    desactiverModeModif();
    errorMessage = null;
    return neFaitRien();
  }

  public WOComponent annulerModifMissionComposante() {
  	newMissionComposante = recMissionComposante().sinLibelle();
  	return annulerModif();
  }

  public WOComponent annulerModifMissionService() {
  	newMissionService = recMissionService().sinLibelle();;
  	return annulerModif();
  }

  public WOComponent annulerModifProjetService() {
  	newProjetService = recProjetService().sinLibelle();
  	return annulerModif();
  }

  public WOComponent annulerModifContexteTravail() {
  	newContexteTravail = inputLaFicheDePoste.fdpContexteTravail();
  	return annulerModif();
  }

  public WOComponent annulerModifMissionPoste() {
  	newMissionPoste = inputLaFicheDePoste.fdpMissionPoste();
  	return annulerModif();
  }
  

  private WOComponent annulerModif() {
    ec.revert();
    desactiverModeModif();
    errorMessage = null;
    return neFaitRien();
  }
  
  
  
  public WOComponent enregistrerMissionComposante() throws Throwable {
    if (session.feveDataBus().checkForMaxSize("StructureInfo", "sinLibelle",
        newMissionComposante, "Mission composante", 0, true)) {
    	recMissionComposante().setSinLibelle(newMissionComposante);
    	sauvegarder();
    } else {
    	ancreHaut();
    	errorMessage = session.feveDataBus().getErrorMessage();
    }
    return null;
  }
  
  public WOComponent enregistrerMissionService() throws Throwable {
    if (session.feveDataBus().checkForMaxSize("StructureInfo", "sinLibelle",
        newMissionService, "Mission service", 0, true)) {
    	recMissionService().setSinLibelle(newMissionService);
    	sauvegarder();
    } else {
    	ancreHaut();
    	errorMessage = session.feveDataBus().getErrorMessage();
    }
    return null;
  }

  public WOComponent enregistrerProjetService() throws Throwable {
    if (session.feveDataBus().checkForMaxSize("StructureInfo", "sinLibelle",
    		newProjetService, "Projet service", 0, true)) {
    	recProjetService().setSinLibelle(newProjetService);
    	sauvegarder();
    } else {
    	ancreHaut();
    	errorMessage = session.feveDataBus().getErrorMessage();
    }
    return null;
  }
   
  public WOComponent enregistrerMissionPoste() throws Throwable {
    if (session.feveDataBus().checkForMaxSize("FicheDePoste", "fdpMissionPoste",
    		newMissionPoste, "Mission poste", 0, true)) {
    	inputLaFicheDePoste().setFdpMissionPoste(newMissionPoste);
    	sauvegarder();
    } else {
    	ancreHaut();
    	errorMessage = session.feveDataBus().getErrorMessage();
    }
    return null;
  }
  
  public WOComponent enregistrerContexteTravail() throws Throwable {
    if (session.feveDataBus().checkForMaxSize("FicheDePoste", "fdpContexteTravail",
    		newContexteTravail, "Contexte travail", 0, true)) {
    	inputLaFicheDePoste().setFdpContexteTravail(newContexteTravail);
    	sauvegarder();
    } else {
    	ancreHaut();
    	errorMessage = session.feveDataBus().getErrorMessage();
    }
    return null;
  }
  
  // GESTION DES ANCRES
  
  public final String ANCRE_MISSION_COMPOSANTE 	= "amc";
  public final String ANCRE_MISSION_SERVICE 		= "ams";
  public final String ANCRE_PROJET_SERVICE 			= "aps";
  public final String ANCRE_MISSION_POSTE 			= "amp";
  public final String ANCRE_CONTEXTE_TRAVAIL 		= "act";
  
  private void ancreHaut()          						{        session.setOnLoad("");}
  private void ancreMissionComposante()      		{        session.setOnLoad("document.location='#" + ANCRE_MISSION_COMPOSANTE + "';");; }
  private void ancreMissionService()     				{        session.setOnLoad("document.location='#" + ANCRE_MISSION_COMPOSANTE + "';");; }
  private void ancreProjetService()        			{        session.setOnLoad("document.location='#" + ANCRE_PROJET_SERVICE + "';");;    }
  private void ancreMissionPoste()        			{        session.setOnLoad("document.location='#" + ANCRE_MISSION_POSTE + "';");;    }
  private void ancreContexteTravail()        		{        session.setOnLoad("document.location='#" + ANCRE_CONTEXTE_TRAVAIL + "';");;    }

}