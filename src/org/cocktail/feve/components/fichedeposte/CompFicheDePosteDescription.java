package org.cocktail.feve.components.fichedeposte;
// Generated by the WOLips TemplateEngine Plug-in at 15 nov. 2005 10:37:50

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOVReferens;
import org.cocktail.feve.eos.modele.mangue.A_CanBeDeleted;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpActi;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpComp;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

/**
 * Description "REFERENS" d'une fiche de poste
 * 
 * @author ctarade
 */
public class CompFicheDePosteDescription extends FeveWebComponent {

  // variables entrantes
  private EOFicheDePoste inputLaFicheDePoste; 
  private EOFicheDePoste inputLaFicheDePostePrecedente;
  
  // gestion de l'ancre en cours
  private final static String ANCRE_ACTIVITE        	= "titreActivite";
  private final static String ANCRE_ACTIVITE_AUTRE  	= "titreActiviteAutre";
  private final static String ANCRE_COMPETENCE      	= "titreCompetence";
  private final static String ANCRE_COMPETENCE_AUTRE  = "titreCompetenceAutre";
  //private String prochaineAncre;
  
  // temoins de modification
  public boolean isModeModifET;
  
  // binding pour le CompBrowserReferens : indique s'il faut forcer la selection de l'emploi type a un instant t
  public boolean shouldForceSelectionInReferensEmploi;
  
  public CompFicheDePosteDescription(WOContext context) {
    super(context);
  }

  // mise en valeur des activites / competences fraichement ajoutees
  private EORepartFdpActi repartActiviteHighlight;
  private EORepartFdpAutre repartActiviteAutreHighlight;
  private EORepartFdpComp repartCompetenceHighlight;
  
  /**
   * Surcharge de cette methode pour la gestion de la mise en
   * valeur de l'activite ou la competence que l'on vient juste de rajouter
   */
  public void appendToResponse(WOResponse response, WOContext context) {
  	super.appendToResponse(response, context);
  	repartActiviteHighlight = null;
  	repartCompetenceHighlight = null;
  }
  
  // mise en surbrillance des activites/competences

  public String getClassTrRepartActivite() {
  	if (repartActiviteHighlight == uneRepartActivite) {
  		return FeveWebComponent.CLASS_HIGHLIGHT;
  	} else {
  		return FeveWebComponent.CLASS_NO_HIGHLIGHT;
  	}
  }
  
  public String getClassTrRepartActiviteAutre() {
  	if (repartActiviteAutreHighlight == uneRepartActiviteAutre) {
  		return FeveWebComponent.CLASS_HIGHLIGHT;
  	} else {
  		return FeveWebComponent.CLASS_NO_HIGHLIGHT;
  	}
  }
  
  public String getClassTrRepartCompetence() {
  	if (repartCompetenceHighlight == uneRepartCompetence) {
  		return FeveWebComponent.CLASS_HIGHLIGHT;
  	} else {
  		return FeveWebComponent.CLASS_NO_HIGHLIGHT;
  	}
  }
  
  public String getClassTrRepartCompetenceAutre() {
  	if (repartActiviteAutreHighlight == uneRepartCompetenceAutre) {
  		return FeveWebComponent.CLASS_HIGHLIGHT;
  	} else {
  		return FeveWebComponent.CLASS_NO_HIGHLIGHT;
  	}
  }
  
  // setter
  
  /**
   * detection du changement du point d'entree -> RAZ
   */
  public void setInputLaFicheDePoste(EOFicheDePoste value) {
    inputLaFicheDePostePrecedente = inputLaFicheDePoste;
    inputLaFicheDePoste = value;
    if (inputLaFicheDePoste != inputLaFicheDePostePrecedente) {
      desactiverModeModif();
      /*// on recupere l'emploi type de gepeto uniquement s'il est occupe
      if (inputLaFicheDePoste.toReferensEmplois() == null && inputLaFicheDePoste.tosAffectationDetail().count() > 0) {
        recupererEmploiTypeGepeto();  
      }*/
    }
  }

  // getters

  public EOFicheDePoste inputLaFicheDePoste() { return inputLaFicheDePoste; }

  
  // GESTION DES ASSOCIATIONS DE ACTIVITES / COMPETENCES
  
  public EORepartFdpActi uneRepartActivite;
  public EORepartFdpAutre uneRepartActiviteAutre;
  public EORepartFdpComp uneRepartCompetence;
  public EORepartFdpAutre uneRepartCompetenceAutre;
  
  // doit-on afficher le composant de saisie d une nouvelle activite/activite autre/competence
  public boolean isModeAjouterActivite, isModeAjouterActiviteAutre, isModeAjouterCompetence, isModeAjouterCompetenceAutre; 
  
  public EOReferensActivites lActiviteSelectionnee;
  public String laNouvelleActiviteAutre;
  public EOReferensCompetences laCompetenceSelectionnee;
  
  /**
   * associer une activite
   */
  public WOComponent associerActivite() throws Throwable {
  	if (lActiviteSelectionnee != null) {
  		desactiverModeModif();
  		repartActiviteHighlight = EORepartFdpActi.newRecordInContext(ec, inputLaFicheDePoste, lActiviteSelectionnee);
  		enregistrer();
  		ancreActivite();      
  	}
  	return neFaitRien();
  }

  /**
   * Suppressions multiples d'activites
   * @return
   * @throws Throwable
   */
  public WOComponent supprimerActivitesSelectionnees() throws Throwable {
  	return supprimerSelections(TYPE_ACTIVITES);
  }

  /**
   * Suppressions multiples d'activites autre
   * @return
   * @throws Throwable
   */
  public WOComponent supprimerActivitesAutresSelectionnees() throws Throwable {
  	return supprimerSelections(TYPE_ACTIVITES_AUTRES);
  }

  /**
   * Suppressions multiples d'activites
   * @return
   * @throws Throwable
   */
  public WOComponent supprimerCompetencesSelectionnees() throws Throwable {
  	return supprimerSelections(TYPE_COMPETENCES);
  }

  /**
   * Suppressions multiples d'activites autre
   * @return
   * @throws Throwable
   */
  public WOComponent supprimerCompetencesAutresSelectionnees() throws Throwable {
  	return supprimerSelections(TYPE_COMPETENCES_AUTRES);
  }
  
  
  private final static int TYPE_ACTIVITES 					= 0;
  private final static int TYPE_ACTIVITES_AUTRES 		= 1;
  private final static int TYPE_COMPETENCES 				= 2;
  private final static int TYPE_COMPETENCES_AUTRES 	= 3;
  
  /**
   * Supprimer les activites associees selectionnees
   * @return
   * @throws Throwable
   */
  private WOComponent supprimerSelections(int type) throws Throwable {
  	// compte le nombre de repart a supprimer
  	if (inputLaFicheDePoste != null) {
  		NSArray allRepartList = new NSArray();
  		if (type == TYPE_ACTIVITES) {
  			allRepartList = inputLaFicheDePoste.tosRepartFdpActi();
  		} else if (type == TYPE_ACTIVITES_AUTRES) {
  			allRepartList = inputLaFicheDePoste.tosRepartFdpActivitesAutres();
  		} else if (type == TYPE_COMPETENCES) {
  			allRepartList = inputLaFicheDePoste.tosRepartFdpComp();
  		} else if (type == TYPE_COMPETENCES_AUTRES) {
  			allRepartList = inputLaFicheDePoste.tosRepartFdpCompetencesAutres();
  		}
  		
  		// recuperer la liste des enregistrements a supprimer		
  		NSArray repartList = new NSArray();
  		for (int i=0; i<allRepartList.count(); i++) {
  			A_CanBeDeleted repart = (A_CanBeDeleted) allRepartList.objectAtIndex(i);
  			if (repart.getIsMarkedToDelete() == Boolean.TRUE) {
  				repartList = repartList.arrayByAddingObject(repart);
  			}
  		}
  		
  		
  		// 
  		if (repartList.count() > 0) {
  			for (int i=0; i<repartList.count(); i++) {
  				CktlRecord recordToDelete = (CktlRecord) repartList.objectAtIndex(i);
  				ec.deleteObject(recordToDelete);
  			}
  			// sauvegarde
  			enregistrer();
  		}
  	}
  	
		// se repositionner
		if (type == TYPE_ACTIVITES) {
			ancreActivite();
		} else if (type == TYPE_ACTIVITES_AUTRES) {
			ancreActiviteAutre();
		} else if (type == TYPE_COMPETENCES) {
			ancreCompetence();
		}  else if (type == TYPE_COMPETENCES_AUTRES) {
			ancreCompetenceAutre();
		} 
  	
  	return neFaitRien();
  }
  
  
  /**
   * associer une activite autre
   */
  public WOComponent associerActiviteAutre() throws Throwable {
  	if (!StringCtrl.isEmpty(laNouvelleActiviteAutre)) {
  		desactiverModeModif();
  		EORepartFdpAutre.newRecordInContext(
  				ec, inputLaFicheDePoste, laNouvelleActiviteAutre, EORepartFdpAutre.TYPE_ACTIVITE);
  		enregistrer();
  		ancreActiviteAutre();
  		// vider le conteu du champ
  		laNouvelleActiviteAutre = StringCtrl.emptyString();
  	}
  	return neFaitRien();
  }
  
  /**
   * associer une competence
   */
  public WOComponent associerCompetence() throws Throwable {
  	if (laCompetenceSelectionnee != null) {
  		desactiverModeModif();
  		repartCompetenceHighlight = EORepartFdpComp.newRecordInContext(ec, inputLaFicheDePoste, laCompetenceSelectionnee);
  		enregistrer();
      ancreCompetence();
  	}
    return neFaitRien();
  }
  
  
  /**
   * associer une competece autre
   */
  public WOComponent associerCompetenceAutre() throws Throwable {
  	if (!StringCtrl.isEmpty(laNouvelleActiviteAutre)) {
  		desactiverModeModif();
  		EORepartFdpAutre.newRecordInContext(
  				ec, inputLaFicheDePoste, laNouvelleActiviteAutre, EORepartFdpAutre.TYPE_COMPETENCE);
  		enregistrer();
  		ancreCompetenceAutre();
  		// vider le conteu du champ
  		laNouvelleActiviteAutre = StringCtrl.emptyString();
  	}
  	return neFaitRien();
  }
  
  /**
   * Associer a la fiche toutes les activites et 
   * competences qui sont affectees a l'emploi type. 
   */
  public WOComponent associationAutomatique() throws Throwable {
  	if (inputLaFicheDePoste.toReferensEmplois() != null) {
  		// les activites
  		NSArray actList = inputLaFicheDePoste.toReferensEmplois().tosReferensActivites();
  		for (int i=0; i<actList.count(); i++) {
  			EOReferensActivites act = (EOReferensActivites) actList.objectAtIndex(i);
  			// on associe pas de doublons
  			if (!inputLaFicheDePoste.tosReferensActivites().containsObject(act)) {
  				EORepartFdpActi.newRecordInContext(ec, inputLaFicheDePoste, act);
  			}
  		}
  		// les competences
  		NSArray compList = inputLaFicheDePoste.toReferensEmplois().tosReferensCompetences();
  		for (int i=0; i<compList.count(); i++) {
  			EOReferensCompetences comp = (EOReferensCompetences) compList.objectAtIndex(i);
  			// on associe pas de doublons
  			if (!inputLaFicheDePoste.tosReferensCompetences().containsObject(comp))
  				EORepartFdpComp.newRecordInContext(ec, inputLaFicheDePoste, comp);
  		}
  		// on enregistre tout ca
  		enregistrer();
  	}
  	return neFaitRien();
  }
  
  // DESCENDRE MONTER 
  
  public WOComponent remonterActivite() throws Throwable {
    uneRepartActivite.up();
    repartActiviteHighlight = uneRepartActivite;
    enregistrer();
    ancreActivite();
    return neFaitRien();
  }
  
  public WOComponent descendreActivite() throws Throwable {
    uneRepartActivite.down();
    repartActiviteHighlight = uneRepartActivite;
    enregistrer();
    ancreActivite();
    return neFaitRien();
  }
  
  public WOComponent remonterActiviteAutre() throws Throwable {
  	uneRepartActiviteAutre.up();
    repartActiviteAutreHighlight = uneRepartActiviteAutre;
    enregistrer();
    ancreActiviteAutre();
    return neFaitRien();
  }
  
  public WOComponent descendreActiviteAutre() throws Throwable {
  	uneRepartActiviteAutre.down();
    repartActiviteAutreHighlight = uneRepartActiviteAutre;
    enregistrer();
    ancreActiviteAutre();
    return neFaitRien();
  }
  
  public WOComponent remonterCompetence() throws Throwable {
  	uneRepartCompetence.up();
    repartCompetenceHighlight = uneRepartCompetence;
    enregistrer();
    ancreCompetence();
    return neFaitRien();
  }
  
  public WOComponent descendreCompetence() throws Throwable {
  	uneRepartCompetence.down();
    repartCompetenceHighlight = uneRepartCompetence;
    enregistrer();
    ancreCompetence();
    return neFaitRien();
  }
  
  public WOComponent remonterCompetenceAutre() throws Throwable {
  	uneRepartCompetenceAutre.up();
    repartActiviteAutreHighlight = uneRepartCompetenceAutre;
    enregistrer();
    ancreCompetenceAutre();
    return neFaitRien();
  }
  
  public WOComponent descendreCompetenceAutre() throws Throwable {
  	uneRepartCompetenceAutre.down();
    repartActiviteAutreHighlight = uneRepartCompetenceAutre;
    enregistrer();
    ancreCompetenceAutre();
    return neFaitRien();
  }

  
  // GESTION DES ANCRES
  private void ancreHaut()          		{        session.setOnLoad("");}
  private void ancreActivite()      		{        session.setOnLoad("document.location='#" + ANCRE_ACTIVITE + "';");; }
  private void ancreActiviteAutre()     {        session.setOnLoad("document.location='#" + ANCRE_ACTIVITE_AUTRE + "';");; }
  private void ancreCompetence()        {        session.setOnLoad("document.location='#" + ANCRE_COMPETENCE + "';");;    }
  private void ancreCompetenceAutre()   {        session.setOnLoad("document.location='#" + ANCRE_COMPETENCE_AUTRE + "';");; }
     
  
  
  // GESTION DES MODES DE MODIF
  
  public WOComponent enregistrer() throws Throwable {
    desactiverModeModif();
    UtilDb.save(ec, "");
    // rafrachir les to-many
    ec.invalidateObjectsWithGlobalIDs(new NSArray(ec.globalIDForObject(inputLaFicheDePoste)));
    // mettre a jour la position des activites, activites autres et competences
    for (int i=0; i<inputLaFicheDePoste.tosRepartFdpActi().count(); i++) {
    	EORepartFdpActi repart = (EORepartFdpActi) inputLaFicheDePoste.tosRepartFdpActi().objectAtIndex(i);
    	repart.setRfaPosition(i+1);
    }
    for (int i=0; i<inputLaFicheDePoste.tosRepartFdpActivitesAutres().count(); i++) {
    	EORepartFdpAutre repart = (EORepartFdpAutre) inputLaFicheDePoste.tosRepartFdpActivitesAutres().objectAtIndex(i);
    	repart.setFauPosition(i+1);
    }
    for (int i=0; i<inputLaFicheDePoste.tosRepartFdpComp().count(); i++) {
    	EORepartFdpComp repart = (EORepartFdpComp) inputLaFicheDePoste.tosRepartFdpComp().objectAtIndex(i);
    	repart.setRfcPosition(i+1);
    }
    for (int i=0; i<inputLaFicheDePoste.tosRepartFdpCompetencesAutres().count(); i++) {
    	EORepartFdpAutre repart = (EORepartFdpAutre) inputLaFicheDePoste.tosRepartFdpCompetencesAutres().objectAtIndex(i);
    	repart.setFauPosition(i+1);
    }
    UtilDb.save(ec, "");
    return neFaitRien();
  }
  
  
  public WOComponent annulerModif() {
    ec.revert(); 
    desactiverModeModif();
    return neFaitRien();
  }
  
  /**
   * Passer la définition de l'emploi type de cette fiche de poste à NULL
   * @return
   * @throws Throwable 
   */
  public WOComponent supprimerET() throws Throwable {
  	inputLaFicheDePoste.setToReferensEmploisRelationship(null);
  	enregistrer();
  	return neFaitRien();
  }
  
  private void desactiverModeModif() {
    isModeModifET = isModeAjouterActivite = isModeAjouterActiviteAutre = isModeAjouterCompetence = isModeAjouterCompetenceAutre = false;
    inputLaFicheDePoste().desactiverTouteActiviteAutrefEnCoursDeModification();
    inputLaFicheDePoste().desactiverTouteCompetenceAutrefEnCoursDeModification();
  }
  
  public WOComponent activerModeModifET() {
    desactiverModeModif();
    isModeModifET = true;
    shouldForceSelectionInReferensEmploi = true;
    ancreHaut();
    return neFaitRien();
  }
 
  public WOComponent activerModeAjoutActivite() {
    desactiverModeModif();
    isModeAjouterActivite = true;
    shouldForceSelectionInReferensEmploi = true;
    ancreActivite();
    return neFaitRien();
  }
  
  public WOComponent activerModeAjoutActiviteAutre() {
    desactiverModeModif();
    isModeAjouterActiviteAutre = true;
    ancreActiviteAutre();
    return neFaitRien();
  }
  
  public WOComponent activerModeAjoutCompetence() {
    desactiverModeModif();
    isModeAjouterCompetence = true;
    shouldForceSelectionInReferensEmploi = true;
    ancreCompetence();
    return neFaitRien();
  }

  public WOComponent activerModeAjoutCompetenceAutre() {
    desactiverModeModif();
    isModeAjouterCompetenceAutre = true;
    ancreCompetenceAutre();
    return neFaitRien();
  }
  
  public WOComponent activerModeModifActiviteAutre() {
    desactiverModeModif();
    shouldForceSelectionInReferensEmploi = true;
    uneRepartActiviteAutre.setIsEnCoursDeModification(true);
    ancreActiviteAutre();
    return neFaitRien();
  }
  
  public WOComponent activerModeModifCompetenceAutre() {
    desactiverModeModif();
    shouldForceSelectionInReferensEmploi = true;
    uneRepartCompetenceAutre.setIsEnCoursDeModification(true);
    ancreCompetenceAutre();
    return neFaitRien();
  }
  
  // gestion des disponibilites des elements d interface
  
  /**
   * Retourne vrai si la personne connectee n'a pas les droits
   * de faire de modifications sur cette fiche
   */
  private boolean verrouNiveauAgent() {
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
  
  /**
   * combo des emploi types
   */
  public boolean isDisabledComboET() {
      return !isModeModifET;
  }
  
  /**
   * bouton modif d emploi type
   * @return
   */
  public boolean isDisabledBtnModifET() {
  	return verrouNiveauAgent() || 
  		verrouNiveauFiche() || 
  		isModeAjouterActivite || 
  		isModeAjouterActiviteAutre || 
  		isModeAjouterCompetence ||
  		isModeAjouterCompetenceAutre || 
  		inputLaFicheDePoste().isAuMoinsUneActiviteAutrefEstEnCoursDeModification() ||
  		inputLaFicheDePoste().isAuMoinsUneCompetenceAutrefEstEnCoursDeModification(); 
  }
  
  /**
   * les boutons de suppression d'activite et competence
   * @return
   */
  public boolean isDisabledBtnSupprimer() {
  	return verrouNiveauAgent() || 
  		verrouNiveauFiche() || 
  		isModeModifET || 
  		isModeAjouterActivite || 
  		isModeAjouterActiviteAutre || 
  		isModeAjouterCompetence ||
  		isModeAjouterCompetenceAutre ||
  		inputLaFicheDePoste().isAuMoinsUneActiviteAutrefEstEnCoursDeModification() ||
  		inputLaFicheDePoste().isAuMoinsUneCompetenceAutrefEstEnCoursDeModification();
  }
  /**
   * Disponibilite de la fonction asso auto.
   * Si la liste des activites et competence est vide.
   */
  public boolean showAssociationAutomatique() {
  	return !verrouNiveauAgent() && !verrouNiveauFiche() &&
  		!isModeModifET && 
  		inputLaFicheDePoste.toReferensEmplois() != null && 
  		inputLaFicheDePoste.tosRepartFdpActi().count() == 0 && 
  		inputLaFicheDePoste.tosRepartFdpComp().count() == 0;
  }
  
  /**
   * L'encart activites autres ne doit pas etre visible si aucune n'est associee.
   * On le montre tout le temps en modification.
   */
  public boolean showPanelActiviteAutre() {
  	if (!isDisabledBtnSupprimer() || isModeAjouterActiviteAutre) {
  		return true;
  	}
  	return inputLaFicheDePoste().tosRepartFdpActivitesAutres().count() > 0;
  }
  
  /**
   * L'encart competences autres ne doit pas etre visible si aucune n'est associee.
   * On le montre tout le temps en modification.
   */
  public boolean showPanelCompetenceAutre() {
  	if (!isDisabledBtnSupprimer() || isModeAjouterCompetenceAutre) {
  		return true;
  	}
  	return inputLaFicheDePoste().tosRepartFdpCompetencesAutres().count() > 0;
  }
  
  /**
   * 
   * @return
   */
  public boolean showSuppressionActivite() {
  	return !isDisabledBtnSupprimer() && inputLaFicheDePoste != null && inputLaFicheDePoste.tosRepartFdpActi().count() > 0;
  }
  
  /**
   * 
   * @return
   */
  public boolean showSuppressionActiviteAutre() {
  	return !isDisabledBtnSupprimer() && inputLaFicheDePoste != null && inputLaFicheDePoste.tosRepartFdpActivitesAutres().count() > 0;
  }
  
  /**
   * 
   * @return
   */
  public boolean showSuppressionCompetence() {
  	return !isDisabledBtnSupprimer() && inputLaFicheDePoste != null && inputLaFicheDePoste.tosReferensCompetences().count() > 0;
  }
  
  /**
   * 
   * @return
   */
  public boolean showSuppressionCompetenceAutre() {
  	return !isDisabledBtnSupprimer() && inputLaFicheDePoste != null && inputLaFicheDePoste.tosRepartFdpCompetencesAutres().count() > 0;
  }
  
  
  /**
   * On autorise pas l'association d'emploi type de l'ancienne nomenclature
   * @return
   */
  public boolean showBtnAssocierET() {
  	return inputLaFicheDePoste.toReferensEmplois() != null && !inputLaFicheDePoste.toReferensEmplois().isArchive();
  }
  
  /**
   * On autorise pas l'association d'activite de l'ancienne nomenclature
   * @return
   */
  public boolean showBtnAssocierActivite() {
  	return lActiviteSelectionnee != null && !lActiviteSelectionnee.toReferensEmplois().isArchive();
  }
  
  /**
   * On autorise pas l'association de competence type de l'ancienne nomenclature
   * @return
   */
  public boolean showBtnAssocierCompetence() {
  	return laCompetenceSelectionnee != null && !laCompetenceSelectionnee.toReferensEmplois().isArchive();
  }
  
  /**
	 *
   * @return
   */
  public boolean isShowBtnModifierActiviteAutre() {
  	return !isDisabledBtnSupprimer() && !inputLaFicheDePoste().isAuMoinsUneActiviteAutrefEstEnCoursDeModification();
  }
  
  /**
	 *
   * @return
   */
  public boolean isShowBtnValiderModificationActiviteAutre() {
  	return !verrouNiveauAgent() && 
  		!verrouNiveauFiche() && 
  		!isModeModifET && 
  		!isModeAjouterActivite && 
  		!isModeAjouterActiviteAutre && 
  		!isModeAjouterCompetence && 
  		!isModeAjouterCompetenceAutre &&
  		uneRepartActiviteAutre.isEnCoursDeModification();
  }
  
  /**
	 *
   * @return
   */
  public boolean isShowBtnModifierCompetenceAutre() {
  	return !isDisabledBtnSupprimer() && !inputLaFicheDePoste().isAuMoinsUneCompetenceAutrefEstEnCoursDeModification();
  }
  
  /**
	 *
   * @return
   */
  public boolean isShowBtnValiderModificationCompetenceAutre() {
  	return !verrouNiveauAgent() && 
  		!verrouNiveauFiche() && 
  		!isModeModifET && 
  		!isModeAjouterActivite && 
  		!isModeAjouterActiviteAutre && 
  		!isModeAjouterCompetence && 
  		!isModeAjouterCompetenceAutre &&
  		uneRepartCompetenceAutre.isEnCoursDeModification();
  }
  
  
  
  // generation code javascript
  
  private final static String ACTIVITES_LABEL =	"Activites";
  private final static String ACTIVITES_AUTRES_LABEL ="ActivitesAutres";
  private final static String COMPETENCES_LABEL = "Competences";
  private final static String COMPETENCES_AUTRES_LABEL ="ComptencesAutres";

  /**
   * code javascript de la fonction pour la selection / deselection de toutes les activites
   */
  public String getJsFunctionSelectionActivite() {
  	return getJsFunctionSelection(ACTIVITES_LABEL, (NSArray) inputLaFicheDePoste.tosReferensActivites().valueForKey(EOReferensActivites.ID_KEY));
  }
  
  /**
   * code javascript de la fonction pour la selection / deselection de toutes les activites autres
   */
  public String getJsFunctionSelectionActiviteAutre() {
  	return getJsFunctionSelection(ACTIVITES_AUTRES_LABEL, (NSArray) inputLaFicheDePoste.tosRepartFdpActivitesAutres().valueForKey(EORepartFdpAutre.ID_KEY));
  }
  
  /**
   * code javascript de la fonction pour la selection / deselection de toutes les competences
   */
  public String getJsFunctionSelectionCompetence() {
  	return getJsFunctionSelection(COMPETENCES_LABEL, (NSArray) inputLaFicheDePoste.tosReferensCompetences().valueForKey(EOReferensCompetences.ID_KEY));
  }
  
  /**
   * code javascript de la fonction pour la selection / deselection de toutes les competences autres
   */
  public String getJsFunctionSelectionCompetenceAutre() {
  	return getJsFunctionSelection(COMPETENCES_AUTRES_LABEL, (NSArray) inputLaFicheDePoste.tosRepartFdpCompetencesAutres().valueForKey(EORepartFdpAutre.ID_KEY));
  }
    
  /**
   * Le code onclick pour la coche de toutes les activites
   * @return
   */
  public String getOnClickSelectionActivite() {
  	return getOnClickSelection(ACTIVITES_LABEL);
  }
  
  /**
   * Le code onclick pour la coche de toutes les activites autres
   * @return
   */
  public String getOnClickSelectionActiviteAutre() {
  	return getOnClickSelection(ACTIVITES_AUTRES_LABEL);
  }
  
  /**
   * Le code onclick pour la coche de toutes les competences
   * @return
   */
  public String getOnClickSelectionCompetence() {
  	return getOnClickSelection(COMPETENCES_LABEL);
  }
  
  /**
   * Le code onclick pour la coche de toutes les competence autres
   * @return
   */
  public String getOnClickSelectionCompetenceAutre() {
  	return getOnClickSelection(COMPETENCES_AUTRES_LABEL);
  }
  
  // setters silencieux 
  
  public void setIsDisabledComboET(boolean value) {
  	
  }
  
  

	public EOVReferens vReferensTest;
}