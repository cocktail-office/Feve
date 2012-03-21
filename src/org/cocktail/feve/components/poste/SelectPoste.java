package org.cocktail.feve.components.poste;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOPoste;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Composant de selection d'un poste
 * 
 * @author ctarade
 */
public class SelectPoste extends FeveWebComponent {

	// binding sortant : le poste selectionné
	public EOPoste eoPosteSelected;

	// liste des services
	public NSArray<EOStructure> eoStructureArray;
	public EOStructure eoStructureSelected;
	public EOStructure eoStructureItem;
	public boolean isAfficherArchive;

	// nature du poste (enseignant / non enseignant)
	public NSArray<String> posteNatureArray = EOPoste.POSTE_NATURE_ARRAY;
	public String posteNatureItem;
	public String posteNatureSelected;

	// type de poste (en cours / occupés / vacants ...)
	public NSArray<String> posteTypeArray = EOPoste.POSTE_TYPE_ARRAY;
	public String posteTypeItem;
	public String posteTypeSelected;
	
	// liste des postes
	public WODisplayGroup dgPoste;
	public EOPoste eoPosteItem;
	
	public SelectPoste(WOContext context) {
		super(context);
		initComponent();
	}
	 
	private void initComponent() {
		// par defaut, on masque les groupes archives
		isAfficherArchive = false;
		// par defaut, si 1 seul service, on le selectionne
		if (getEoStructureArray().count() == 1) {
			eoStructureSelected = getEoStructureArray().objectAtIndex(0);
		}
		// selection du type "en cours" par defaut
		posteTypeSelected = EOPoste.POSTE_TYPE_EN_COURS;
		// selection de la nature "non enseignant" par defaut
		posteNatureSelected = EOPoste.POSTE_NATURE_NON_ENSEIGNANT;
	}
	
	/**
	 * La liste des services visibles. 
	 * Elle est conditionnée par la valeur du boolean
	 * <code>showArchive</code> qui permet de masquer 
	 * ou non les services archivés
	 * @return
	 */
	public NSArray<EOStructure> getEoStructureArray() {
		NSArray<EOStructure> serviceList = feveUserInfo().getServicePosteList(isAfficherArchive);
		return serviceList;
	}
	
	
	/**
	 * Methode appelée lors du rechargement du formulaire
	 * contenant tous les filtres
	 * @return
	 */
	public WOComponent doFilterDgPoste() {
		NSMutableDictionary dgPosteQueryBindings = new NSMutableDictionary();
		NSArray qualList = new NSArray();
		
		
		boolean shouldRefreshDgPoste = false;
		boolean shouldClearDgPoste = false;
		
		// le filre sur la structure
		if (eoStructureSelected != null) {
			dgPosteQueryBindings.addEntriesFromDictionary(getStructureQueryBindings());
			shouldRefreshDgPoste = true;
		} else {
			// pas de service selectionné
			shouldClearDgPoste = true;
		}
			

		// nature
		EOQualifier posteNatureQualifier = EOPoste.getPosteNatureQualifier(null, posteNatureSelected);
		if (posteNatureQualifier != null) {
			qualList = qualList.arrayByAddingObject(posteNatureQualifier);
		}
	
		// type
		EOQualifier posteTypeQualifier = EOPoste.getPosteTypeQualifier(null, posteTypeSelected);
		if (posteTypeQualifier != null) {
			qualList = qualList.arrayByAddingObject(posteTypeQualifier);
		}

		dgPoste.queryBindings().removeAllObjects();
		dgPoste.queryBindings().setDictionary(dgPosteQueryBindings);	
		dgPoste.setQualifier(new EOAndQualifier(qualList));		
		
		if (shouldRefreshDgPoste) {
			dgPoste.qualifyDataSource();
		} else if (shouldClearDgPoste) {
			dgPoste.setObjectArray(new NSArray());
		}
		
		return null;
	}



	/**
	 * Obtenir le dictionnaire attendu pour la fetch spec
	 * associee au dgPoste concernant le filtre sur les structures
	 * @return <em>null</em> aucune structure n'est selectionnée.
	 */
	private NSMutableDictionary getStructureQueryBindings() {
		if (eoStructureSelected != null) {
			NSMutableDictionary dico = new NSMutableDictionary();
			dico.setObjectForKey(eoStructureSelected.cStructure(), "cStructure");
			return dico;
		} else {
			return null;
		}
	}
	
}