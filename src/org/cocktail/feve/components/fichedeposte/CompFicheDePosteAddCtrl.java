package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * controleur du composant {@link CompFicheDePosteAdd}
 * @author ctarade
 *
 */
public class CompFicheDePosteAddCtrl  
	extends A_ComponentControler 	{

	/** binding : le poste sur lequel creer la fiche de poste */
	public EOPoste poste;

	/**
	 * La date d'ouverture de la nouvelle fiche
	 */
	public NSTimestamp newFicheDDebut;

	/**
	 * La date de fermeture de la nouvelle fiche
	 */
	public NSTimestamp newFicheDFin;
	
	/** le composant ayant appelé */
	private I_CallingCompFicheDePosteAdd caller;
	
	/**
	 * @deprecated
	 * @see #CompFicheDePosteAddCtrl(Session, EOPoste)
	 * @param session
	 */
	public CompFicheDePosteAddCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param poste
	 */
	public CompFicheDePosteAddCtrl(Session session, EOPoste aPoste, I_CallingCompFicheDePosteAdd aCaller) {
		super(session);
		poste = aPoste;
		caller = aCaller;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		newFicheDDebut = poste.getDateDebutParDefautPourNouvelleFicheDePoste();
		newFicheDFin = null;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean validate() {
		boolean success = true;
		// la date de debut est obligatoire
		if (newFicheDDebut == null) {
			success = false;
			setErrMsg("La date d'ouverture de la fiche est obligatoire");
		}
		// verifier que le debut est avant la fin
		if (success && newFicheDFin != null && DateCtrl.isAfter(newFicheDDebut, newFicheDFin)) {
			success = false;
			setErrMsg("La date d'ouverture doit être avant la date de fermeture");
		}

		return success;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isShowAvertissementEmploiTypeAncienneNomenclature() {
		boolean show = false;
		// trouver la derniere fiche
		EOFicheDePoste lastFicheDePoste = poste.toDerniereFicheDePoste();
		if (lastFicheDePoste != null && 
				lastFicheDePoste.isEmploiSurAncienneNomenclature()) {
			show = true;
		}
		return show;
	}
	
	/**
	 * L'action de creer la fiche. On va creer la nouvelle fiche
	 * a partir de la derniere en date (sinon elle sera vierge).
	 * On ferme egalement la dernier fiche avec la date de debut
	 * de la nouvelle (J-1).
	 */
	public WOComponent doCreateFiche() {
		clearError();
		if (validate()) {
			EOFicheDePoste newFiche = null;
			// trouver la derniere fiche
			EOFicheDePoste lastFicheDePoste = poste.toDerniereFicheDePoste();
			
			if (lastFicheDePoste != null) {
				
				newFiche = EOFicheDePoste.dupliquerFiche(
						lastFicheDePoste, 
						newFicheDDebut, 
						newFicheDFin, 
						lastFicheDePoste.toPoste());
				
				// on ferme la derniere fiche de poste avec la date de debut de la new
				lastFicheDePoste.setDFin(newFicheDDebut.timestampByAddingGregorianUnits(0,0,-1,0,0,0));
				
			} else {
				// pas de fiche existante, on en fait une nouvelle vierge
				newFiche = EOFicheDePoste.newRecordInContext(ec(), poste, newFicheDDebut, newFicheDFin);
			}
		   // enregistrement
	    try {
	    	UtilDb.save(ec(),"");
	    	// on refetch un coup car le graphe d'objets n'est pas a jour
	    	ec().invalidateObjectsWithGlobalIDs(new NSArray(ec().globalIDForObject(newFiche)));
	      // on notifie le composant ayant appelé que l'ajout est OK
		    caller.doAfterCompFicheDePosteAddSuccess();
	    } catch (Throwable e) {
	    	setErrMsg(e.getMessage());
	    	e.printStackTrace();
			}
		} 
		return null;
	}

	public final I_CallingCompFicheDePosteAdd getCaller() {
		return caller;
	}
}
