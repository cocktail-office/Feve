package org.cocktail.feve.components.common;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;

public class CompOccupationUpdateCtrl 
	extends A_ComponentControler {

	/** binding entrant : l'occupation a modifier */
	public EOAffectationDetail affectationDetail;
	
	/** le composant qui appelle {@link CompOccupationUpdate} */
	private I_CallingCompOccupationUpdate caller;

	/**
	 * @deprecated
	 * @see #CompOccupationUpdateCtrl(Session, EOPoste)
	 * @param session
	 */
	public CompOccupationUpdateCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param anAffectationDetail
	 * @param aCaller : la page appelante, pour masquer ce composant des que l'ajout est OK
	 */
	public CompOccupationUpdateCtrl(Session session, EOAffectationDetail anAffectationDetail, I_CallingCompOccupationUpdate aCaller) {
		super(session);
		affectationDetail = anAffectationDetail;
		caller = aCaller;
	}
	
	/**
	 * Validation du formulaire
	 */
	private boolean validate() {
		boolean result = true;
		if (affectationDetail.adeDDebut() == null) {
			setErrMsg("La date de debut d'occupation du poste est obligatoire");
			result = false;
		}
		return result;
	}

	/**
	 * L'action de creer une nouvelle occupation.
	 * @return
	 */
	public WOComponent doUpdateOccupation() {
		clearError();
		if (validate()) {
		  // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
		    // on notifie le succes au composant conteneur
		    caller.doAfterOccupationUpdateSuccess();
	    } catch (Throwable e) {
	    	setErrMsg("Erreur de sauvegarde : " + e.getMessage());
	    	e.printStackTrace();
			}
		}
		return null;
	}

	public final I_CallingCompOccupationUpdate getCaller() {
		return caller;
	}
}
