package org.cocktail.feve.components.common;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.eos.modele.mangue.EOAffectation;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSTimestamp;

/**
 * Controleur du composante {@link CompOccupationAdd}
 * @author ctarade
 *
 */
public class CompOccupationAddCtrl 
	extends A_ComponentControler {

	/** binding entrant : le poste surlequel creer l'occupation */
	public EOPoste poste;
	
	/** le composant qui appelle {@link CompOccupationAdd} */
	private I_CallingCompOccupationAdd caller;
	
	/** l'affectation selectionnée */
	public EOAffectation affectationSelected;
	
	/** le controleur du composant d'affectation pour l'affection d'une nouvelle occupation */
	public CompSelectAffectationCtrl compSelectAffectationCtrlNouvelleOccupation;
	
	/**
	 * La duree de l'occupation est la meme que la duree de
	 * la periode de l'affectation
	 */
	public boolean isCreateOccupationSameDateAffectation;
	
	/**
	 * La date de debut de l'occupation, si differente de
	 * la date de debut de l'affectation associee
	 */
	public NSTimestamp newOccupationDebut;
	
	/**
	 * La date de fin de l'occupation, si differente de
	 * la date de fin de l'affectation associee
	 */
	public NSTimestamp newOccupationFin;

	
	/**
	 * @deprecated
	 * @see #CompOccupationAddCtrl(Session, EOPoste)
	 * @param session
	 */
	public CompOccupationAddCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param aPoste
	 * @param aCaller : la page appelante, pour masquer ce composant des que l'ajout est OK
	 */
	public CompOccupationAddCtrl(Session session, EOPoste aPoste, I_CallingCompOccupationAdd aCaller) {
		super(session);
		poste = aPoste;
		caller = aCaller;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		compSelectAffectationCtrlNouvelleOccupation = new CompSelectAffectationCtrl(feveSession(), poste.toStructure());
	}
	
	/**
	 * Validation du formulaire
	 */
	private boolean validate() {
		boolean result = true;
		if (newOccupationDebut == null) {
			setErrMsg("La date de debut d'occupation du poste est obligatoire");
			result = false;
		}
		return result;
	}
	
	/**
	 * L'action de creer une nouvelle occupation.
	 * @return
	 */
	public WOComponent doAddOccupation() {
		clearError();
		if (validate()) {
			if (isCreateOccupationSameDateAffectation) {
				EOAffectationDetail.newRecordInContext(ec(), affectationSelected, poste, 
						affectationSelected.dDebAffectation(), affectationSelected.dFinAffectation(), false);
			} else {
				EOAffectationDetail.newRecordInContext(ec(), affectationSelected, poste, 
						newOccupationDebut, newOccupationFin, true);
			}
		   // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
		    // on notifie le succes au composant conteneur
		    caller.doAfterOccupationAddSuccess();
	    } catch (Throwable e) {
	    	setErrMsg("Erreur de sauvegarde : " + e.getMessage());
	    	e.printStackTrace();
			}
		} 
		return null;
	}
	
	
	// setters
	
	/**
	 * Remplir les donnees par default lors de la creation d'une occupation.
	 * Si une affectation est selectionnee, on copie ses dates dans les
	 * dates personnalisees de l'occupation au cas ou.
	 */
	public void setAffectationSelected(EOAffectation value) {
		affectationSelected = value;
		if (affectationSelected != null) {
			// par defaut, on prend les memes dates que l'affectation
			isCreateOccupationSameDateAffectation = true;
			newOccupationDebut = affectationSelected.dDebAffectation();
			newOccupationFin = affectationSelected.dFinAffectation();
		} else {
			newOccupationDebut = DateCtrl.now();
			newOccupationFin = null;
		}
	}
	
	
	// getters
	

	/**
	 * On affiche le bouton de creation d'occupation si 
	 * l'affectation est selectionee.
	 */
	public boolean isShowInfosCreateOccupation() {
		return affectationSelected != null;
	}

	public final I_CallingCompOccupationAdd getCaller() {
		return caller;
	}
	
}
