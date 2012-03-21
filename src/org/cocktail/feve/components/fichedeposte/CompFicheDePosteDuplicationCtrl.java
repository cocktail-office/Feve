package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * Controleur du composant {@link CompFicheDePosteDuplication}
 * 
 * @author ctarade
 *
 */
public class CompFicheDePosteDuplicationCtrl
	extends A_ComponentControlerAndFilArianeNode {

	/** binding : la fiche de poste a dupliquer */
	public EOFicheDePoste ficheDePoste;
	
	/**
	 * Le poste de destination selectionné
	 */
	public EOPoste eoPosteSelected;
	
	/**
	 * La période de validité de la nouvelle fiche
	 */
	public NSTimestamp newFicheDDebut;
	public NSTimestamp newFicheDFin;

	/** le composant ayant appelé */
	private I_CallingCompFicheDePosteDuplication caller;
	
	/** la nouvelle fiche issue de la copie */
	public EOFicheDePoste newEoFicheDePoste;
	
	/**
	 * @deprecated
	 * @see #CompFicheDePosteCtrl(Session, EOFicheDePoste)
	 * @param session
	 */
	public CompFicheDePosteDuplicationCtrl(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param session
	 */
	public CompFicheDePosteDuplicationCtrl(
			Session session, EOFicheDePoste aFicheDePoste, I_CallingCompFicheDePosteDuplication aCaller) {
		super(session);
		ficheDePoste = aFicheDePoste;
		caller = aCaller;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		newFicheDDebut = DateCtrl.now();
		newFicheDFin = null;
		newEoFicheDePoste = null;
	}

	
	/**
	 * 
	 * @return
	 */
	private boolean validate() {
		boolean success = true;
		// il faut un poste de destination
		if (eoPosteSelected == null) {
			success = false;
			setErrMsg("Veuillez selectionner un poste");
		}
		// la date de debut est obligatoire
		if (success && newFicheDDebut == null) {
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


	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return null;
	}

	@Override
	protected void toLocalFullComponent() {

	}
	
	public WOComponent doDupliquerFiche() {
		clearError();
		
		if (validate()) {
	 		
			// fermer la derniere fiche du poste de destination
			// a la date de début de celle ci
			EOFicheDePoste lastFicheDePoste = eoPosteSelected.toDerniereFicheDePoste();
			if (lastFicheDePoste != null) {
				lastFicheDePoste.setDFin(newFicheDDebut.timestampByAddingGregorianUnits(0,0,-1,0,0,0));
			}

			newEoFicheDePoste = EOFicheDePoste.dupliquerFiche(
					ficheDePoste, 
					newFicheDDebut, 
					newFicheDFin, 
					eoPosteSelected);
	 		
			// enregistrement
			try {
	    	UtilDb.save(ec(),"");
	    	// on refetch un coup car le graphe d'objets n'est pas a jour
	    	ec().invalidateObjectsWithGlobalIDs(new NSArray(ec().globalIDForObject(newEoFicheDePoste)));
		    // notifier que l'operation s'est bien passée
		    caller.doAfterCompFicheDePosteDuplicationSuccess();
	    } catch (Throwable e) {
	    	setErrMsg(e.getMessage());
	    	e.printStackTrace();
			}
		}
 		
		return null;
	}

	public final I_CallingCompFicheDePosteDuplication getCaller() {
		return caller;
	}
	

}
