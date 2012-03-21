package org.cocktail.feve.components.poste;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.components.common.CompSelectAffectationCtrl;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOAffectation;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

/**
 * Controleur du composant {@link CompPosteDuplication}
 * 
 * @author ctarade
 *
 */
public class CompPosteDuplicationCtrl
	extends A_ComponentControlerAndFilArianeNode {

	/** binding : le poste a dupliquer */
	public EOPoste poste;
	
	/**
	 * Le service de destination selectionné
	 */
	private EOStructure eoStructureSelected;
	
	/**
	 * Composant de selection de l'affectation par rapport au service
	 */
	public CompSelectAffectationCtrl selectAffectationCtrl;
	
	/**
	 * L'affectation mangue à associer
	 */
	private EOAffectation eoAffectation;
	
	/**
	 * La période de validité du nouveau poste
	 */
	public NSTimestamp newPosDDebut;
	public NSTimestamp newPosDFin;
	
	public String newPosCode;
	public String newPosLibelle;
	
	/** la nouveau poste issue de la copie */
	public EOPoste newEoPoste;
	
	public boolean isCreationDepuisAffectation;
	
	/**
	 * @deprecated
	 * @see #CompFicheDePosteCtrl(Session, EOFicheDePoste)
	 * @param session
	 */
	public CompPosteDuplicationCtrl(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param session
	 */
	public CompPosteDuplicationCtrl(
			Session session, EOPoste aPoste) {
		super(session);
		poste = aPoste;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		newPosDDebut = DateCtrl.now();
		newPosDFin = null;
		newEoPoste = null;
		newPosCode = null;
		newPosLibelle = null;
		isCreationDepuisAffectation = true;
	}

	
	/**
	 * 
	 * @return
	 */
	private boolean validate() {
		boolean success = true;
		// il faut un service de destination
		if (eoStructureSelected == null) {
			success = false;
			setErrMsg("Veuillez selectionner un service");
		}
		// la date de debut est obligatoire
		if (success && newPosDDebut == null) {
			success = false;
			setErrMsg("La date d'ouverture du poste est obligatoire");
		}
		// verifier que le debut est avant la fin
		if (success && newPosDFin != null && DateCtrl.isAfter(newPosDDebut, newPosDFin)) {
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
	
	public WOComponent doDeselectionnerAffectation() {
		setEoAffectation(null);
		return null;
	}
	
	public WOComponent doDupliquerFiche() {
		clearError();
		
		if (validate()) {
	 		
			// fermer la derniere fiche du poste copié à la date d'ouverture
			EOFicheDePoste lastFicheDePoste = poste.toDerniereFicheDePoste();
			if (lastFicheDePoste != null) {
				lastFicheDePoste.setDFin(newPosDDebut.timestampByAddingGregorianUnits(0,0,-1,0,0,0));
			}

			newEoPoste = EOPoste.dupliquerPoste(
					poste, 
					newPosCode,
					newPosLibelle,
					newPosDDebut, 
					newPosDFin, 
					eoStructureSelected);
	 		
			// enregistrement
			try {
	    	UtilDb.save(ec(),"");
	    	// on refetch un coup car le graphe d'objets n'est pas a jour
	    	ec().invalidateObjectsWithGlobalIDs(new NSArray<EOGlobalID>(ec().globalIDForObject(newEoPoste)));
		    // notifier que l'operation s'est bien passée
		    //caller.doAfterCompPosteDuplicationSuccess();
	    } catch (Throwable e) {
	    	setErrMsg(e.getMessage());
	    	e.printStackTrace();
			}
		}
		
		return null;
	}
//
//	public final I_CallingCompPosteDuplication getCaller() {
//		return caller;
//	}
	
	public final EOStructure getEoStructureSelected() {
		return eoStructureSelected;
	}

	public final void setEoStructureSelected(EOStructure eoStructureSelected) {

		this.eoStructureSelected = eoStructureSelected;

		selectAffectationCtrl = new CompSelectAffectationCtrl(feveSession(), eoStructureSelected);
		
		// code par defaut
		if (eoStructureSelected != null) {
			newPosCode = EOPoste.getDefaultPosCodeForStructure(eoStructureSelected);
		}
		
		// raz de l'affectation qui doit être sur le même service
		setEoAffectation(null);

	}

	public final EOAffectation getEoAffectation() {
		return eoAffectation;
	}

	public final void setEoAffectation(EOAffectation eoAffectation) {
		
		CktlLog.log("setEoAffectation() " + eoAffectation);
		
		this.eoAffectation = eoAffectation;

		// libelle par defaut
		if (eoAffectation != null) {
			newPosLibelle = EOPoste.getLibelleDefautPourAffectation(eoAffectation);
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEoAffectationExiste() {
		return getEoAffectation() != null;
	}
}
