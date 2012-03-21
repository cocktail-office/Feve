package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOStructure;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

public class SelectService extends FeveWebComponent {

	// liste des services
	public NSArray<EOStructure> eoStructureArray;
	public EOStructure eoStructureSelected;
	public EOStructure eoStructureItem;
	public boolean isAfficherArchive;
	
	// le container a mettre a jour si besoin
	public String updateContainerID;
	
	public SelectService(WOContext context) {
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
	
}