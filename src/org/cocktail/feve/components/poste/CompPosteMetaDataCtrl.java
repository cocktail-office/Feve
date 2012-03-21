package org.cocktail.feve.components.poste;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.components.common.CompSelectAffectationCtrl;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOAffectation;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderExercice;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSTimestamp;

/**
 * Controleur du composant {@link CompPosteMetaData}
 * 
 * @author ctarade
 */
public class CompPosteMetaDataCtrl 
	extends A_ComponentControler {

	/**
	 * La page appelante pour le retour
	 */
	public I_CallingCompPosteMetaData caller;
	
	
	// -- consultation et modification --

	/**
	 * BINDING
	 * Le poste en cours de consultation ou en cours de modification
	 */
	public EOPoste inPoste;

	/** le message d'information sur le droit de modification des meta donnees du poste */
	public boolean showMsgInfoModifPoste;
	public String strMsgInfoModifPoste;

	
	
	// -- creation --
	
	/**
	 * BINDING
	 * Indique sur quelle service le nouveau poste doit être affecté
	 */
	public EOStructure inNewPosteService;
	
	/**
	 * Creer le poste a partir d'une affectation existante ?
	 */
	public boolean isCreatingFromExistingAffectation = true;
	
	/**
	 * Le code du nouveau poste
	 */
	public String newPosCode;

	/**
	 * Le libellé du nouveau poste
	 */
	public String newPosLibelle;
	
	/**
	 * La date d'ouverture du nouveau poste
	 */
	public NSTimestamp newPosDDebut; 

	/**
	 * La date de fermeture du nouveau poste
	 */
	public NSTimestamp newPosDFin; 
	
	/**
	 * L'affectation selectionnée dans le cas d'une création a partir d'une
	 * affectation déjà existante
	 */
	public EOAffectation newPosAffectation;
	
	/** 
	 * le controleur du composant d'affectation pour la creation d'un 
	 * nouveau poste avec une occupation 
	 */
	public CompSelectAffectationCtrl compSelectAffectationCtrlNouveauPoste;
	
	/**
	 * Creer la fiche de poste en meme temps que le nouveau poste
	 */
	public boolean isCreatingFicheDePoste;
	
	/**
	 * On autorise la création de fiche de poste uniquement sur les postes non enseignants
	 */
	public boolean isDisabledChkCreateFicheDePoste;

	
	/**
	 * Le constructeur par defaut. 
	 * Il ne faut pas l'utiliser directement
	 * - {@link #CompPosteCtrl(Session, EOPoste, I_CallingCompPosteMetaData)} pour la modification
	 * - {@link #CompPosteCtrl(Session, EOStructure, I_CallingCompPosteMetaData)} pour la creation
	 * @param session
	 */
	public CompPosteMetaDataCtrl(Session session) {
		super(session);
		// le contenu du message d'information
		strMsgInfoModifPoste = feveApp().config().stringForKey("MSG_INFO_MODIF_POSTE");
		showMsgInfoModifPoste = !StringCtrl.isEmpty(strMsgInfoModifPoste);
	}
	
	/**
	 * Constructeur a appeler en mode creation
	 * @param session
	 * @param anInNewPosteService
	 * @param aCaller
	 */
	public CompPosteMetaDataCtrl(Session session, EOStructure anInNewPosteService, I_CallingCompPosteMetaData aCaller) {
		super(session);
		inPoste = null;
		inNewPosteService = anInNewPosteService;
		caller = aCaller;
		// instancier le gestionnaire de selection d'affectation
		compSelectAffectationCtrlNouveauPoste = new CompSelectAffectationCtrl(session, inNewPosteService);
		// on demande la creation d'une  fiche de poste par defaut
		isCreatingFicheDePoste = true;
		//
		isDisabledChkCreateFicheDePoste = false;
	}
	
	/**
	 * Constructeur a appeler en mode modification
	 * @param session
	 * @param anInPoste
	 * @param aCaller
	 */
	public CompPosteMetaDataCtrl(Session session, EOPoste anInPoste, I_CallingCompPosteMetaData aCaller) {
		super(session);
		inPoste = anInPoste;
		caller = aCaller;
	}

	// boolean interface

	/**
	 * La modif du code du poste n'est accessible qu'aux administrateurs
	 */
	public boolean isDisabledCodePoste() {
		return !feveSession().isAdmin();
	}
	
	/**
	 * La modif de la date de debut du poste n'est accessible qu'aux administrateurs
	 */
	public boolean isDisabledDDebutPoste() {
		return !feveSession().isAdmin();
	}
	
	/**
	 * La modif de la date de fin du poste n'est accessible qu'aux administrateurs
	 */
	public boolean isDisabledDFinPoste() {
		return !feveSession().isAdmin();
	}
	
	/**
	 * On affiche le formulaire de creation de poste si l'affectation 
	 * est selectionnee pour la creation depuis une affectation
	 */
	public boolean showInfosPoste() {
		return !isCreatingFromExistingAffectation ||
			(isCreatingFromExistingAffectation && newPosAffectation != null);
	}
	
	// setters

	/**
	 * Indique que l'affectation vient d'etre selectionnee. On remplit le
	 * formulaire avec les donnees par default en mode creation
	 */
	public void setNewPosAffectation(EOAffectation value) {
		newPosAffectation = value;

		// on pre-remplit le formulaire en mode creation de poste
		if (isModeCreatePoste()) {
			fillDefaultValuesNewPoste();
		}
		
		// empecher la creation de fiche de poste pour un non enseignant
		if (newPosAffectation != null && newPosAffectation.toIndividu().isEnseignant().equals("1")) {
			isDisabledChkCreateFicheDePoste = true;
			// on décoche la creation de fiche de poste si besoin
			if (isCreatingFicheDePoste) {
				isCreatingFicheDePoste = false;
			}
		} else {
			isDisabledChkCreateFicheDePoste = false;
		}
	}
	
	
	// actions
	
	
	/**
	 * L'action de creer le poste
	 */
	public WOComponent doCreatePoste() {
		if (isFormValid()) {
		  // creer le poste
	    EOPoste newPoste = EOPoste.newRecordInContext(ec(), inNewPosteService, newPosCode, newPosLibelle, newPosDDebut, newPosDFin);        
	    // creer l'occupation
	    if (isCreatingFromExistingAffectation) {
	    	EOAffectationDetail.newRecordInContext(ec(), newPosAffectation, newPoste, newPosDDebut, newPosDFin, false);
	    }
	    // creer la fiche de poste (uniquement pour les postes non enseignants)
	    if (isCreatingFicheDePoste && newPoste.isNonEnseignant()) {
	      EOFicheDePoste.newRecordInContext(ec(), newPoste, newPosDDebut, newPosDFin);
	    }
	    // creer la fiche lolf
	    EOFicheLolf.newRecordInContext(
	     		//ec(), newPoste, FinderExercice.getExercicePourDate(ec(), DateCtrl.now()));
	     		ec(), newPoste, FinderExercice.getExerciceDepensePourDate(ec(), DateCtrl.now()));
	   	    // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
	    } catch (Throwable e) {
	    	e.printStackTrace();
			}
	    // on retourne sur la liste des postes en rafraichissant son contenu
	    ((I_CallingCompPosteMetaData) caller).doAfterCompPosteMetaDataSuccess();
	    return null;
		} else {
			return null;
		}
	}
	
	
	/**
	 * L'action d'enregistrer les modifications
	 */
	public WOComponent doUpdatePoste() {
		if (isFormValid()) {
	    // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
	    } catch (Throwable e) {
	    	e.printStackTrace();
			}
	    // on retourne sur la liste des postes en rafraichissant son contenu
	    ((I_CallingCompPosteMetaData) caller).doAfterCompPosteMetaDataSuccess();
	    return null;
		} else {
			return null;
		}

	}

	/**
	 * Remplir les donnes par default lors de la creation d'un poste.
	 * Les valeurs dependent si creation a partir d'une affectation ou pas.
	 */
	private void fillDefaultValuesNewPoste() {
		// on ecrase pas les donnes du poste si un code a ete saisi
		if (StringCtrl.isEmpty(newPosCode)) {
			newPosCode = EOPoste.getDefaultPosCodeForStructure(inNewPosteService);
		}
		newPosLibelle = EOPoste.getLibelleDefautPourAffectation(newPosAffectation);
		if (isCreatingFromExistingAffectation) {
			newPosDDebut = newPosAffectation.dDebAffectation();
			newPosDFin = newPosAffectation.dFinAffectation();
		} else {
			newPosDDebut = new NSTimestamp();
			newPosDFin = null;
		}
	}
	
	
	// temoins internes	
	
	/**
	 * Effectue la verification des donnees saisies. Si tout est
	 * OK, alors <code>true</code> est retourne. 
	 * 
	 * Sinon <code>false</code> et <code>errMsg</code> est mis a jour
	 */
	private boolean isFormValid() {
		// raz du msg
		clearError();
		if (isModeCreatePoste()) {
			// le code, le libelle et la date de debut sont obligatoires
			if (StringCtrl.isEmpty(newPosCode)) {
				setErrMsg("Le code du poste est obligatoire");
			} else if (StringCtrl.isEmpty(newPosLibelle)) {
				setErrMsg("Le libellé du poste est obligatoire");
			} else if (newPosDDebut == null) {
				setErrMsg("La date d'ouverture du poste est obligatoire");
			}
		} else {
			// le code, le libelle et la date de debut sont obligatoires
			if (StringCtrl.isEmpty(inPoste.posCode())) {
				setErrMsg("Le code du poste est obligatoire");
			} else if (StringCtrl.isEmpty(inPoste.posLibelle())) {
				setErrMsg("Le libelle du poste est obligatoire");
			} else if (inPoste.posDDebut() == null) {
				setErrMsg("La date d'ouverture du poste est obligatoire");
			}
		} 
		return !hasError();
	}

	/**
	 * 
	 */
	public boolean isModeCreatePoste() {
		return inPoste == null;
	}
}
