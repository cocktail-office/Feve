package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;

/**
 * controleur du composant {@link CompFicheDePosteUpdate}
 * @author ctarade
 *
 */
public class CompFicheDePosteUpdateCtrl  
	extends A_ComponentControler 	{

	/** binding : le poste sur lequel creer la fiche de poste */
	public EOFicheDePoste ficheDePoste;

	/** le composant ayant appelé */
	private I_CallingCompFicheDePosteUpdate caller;
	
	/**
	 * @deprecated
	 * @see #CompFicheDePosteUpdateCtrl(Session, EOPoste)
	 * @param session
	 */
	public CompFicheDePosteUpdateCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param poste
	 */
	public CompFicheDePosteUpdateCtrl(Session session, EOFicheDePoste aFicheDePoste, I_CallingCompFicheDePosteUpdate aCaller) {
		super(session);
		ficheDePoste = aFicheDePoste;
		caller = aCaller;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean validate() {
		boolean success = true;
		// la date de debut est obligatoire
		if (ficheDePoste.dDebut() == null) {
			success = false;
			setErrMsg("La date d'ouverture de la fiche est obligatoire");
		}
		// verifier que le debut est avant la fin
		if (success && ficheDePoste.dFin() != null && DateCtrl.isAfter(ficheDePoste.dDebut(), ficheDePoste.dFin())) {
			success = false;
			setErrMsg("La date d'ouverture doit être avant la date de fermeture");
		}
		// verifier si pas de chevauchement
		if (success) {
			NSArray recsFiche = A_Fiche.findFicheForPeriodeIgnoring(
					ficheDePoste.toPoste(), true, ficheDePoste.dDebut(), ficheDePoste.dFin(), ficheDePoste);
			if (recsFiche.count() > 0) {
				success = false;
				String msg = "Les dates se chevauchent avec d'autres fiches existantes sur ce poste :\n";
				for (int i=0; i<recsFiche.count(); i++) {
					A_Fiche recFiche = (A_Fiche) recsFiche.objectAtIndex(i);
					msg += recFiche.display();
					if (i < recsFiche.count() -1) {
						msg += "\n";
					}
				}
				setErrMsg(msg);
			}
		}
		return success;
	}

	/**
	 * Enregistrement des infos. On n'enregistre pas si les 
	 * dates chevauchent avec les autres fiches de meme nature
	 * pour ce poste.
	 */
	public WOComponent doUpdateFiche() {
		clearError();
		if (validate()) {
		   // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
		    // notifier que l'operation s'est bien passée
		    caller.doAfterCompFicheDePosteUpdateSuccess();
	    } catch (Throwable e) {
	    	setErrMsg(e.getMessage());
	    	e.printStackTrace();
			}
		}
		return null;
	}

	public final I_CallingCompFicheDePosteUpdate getCaller() {
		return caller;
	}
}
