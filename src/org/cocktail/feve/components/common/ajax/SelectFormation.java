package org.cocktail.feve.components.common.ajax;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;

import com.webobjects.appserver.WOContext;

/**
 * Composant de selection d'une formation :
 * - soit d'un forId pointant sur un {@link EOFormationPersonnel}
 * - soit d'un champ libre
 * 
 * @author ctarade
 */
public class SelectFormation 
	extends FeveWebComponent {
	
	// bindings
	public EOFormationPersonnel eoFormationPersonnel;
	public String formName;
	public String btnAddFormationName;
	public String strChampLibre;
	
	public SelectFormation(WOContext context) {
		super(context);
	}
	
	/**
	 * Indique si la selection concerne une formation de la 
	 * nomenclature (le libellé est parmi la liste)
	 * @return
	 */
	public boolean getIsFormationNomenclature() {
		boolean isFormationNomenclature = false;
		
		isFormationNomenclature = EOFormationPersonnel.isFormationNomenclatureFeuilleForLibelle(
				ec, strChampLibre);
				
		return isFormationNomenclature;
	}

	/**
	 * Setter bidon pour ignorer sa valeur en binding entrant
	 * @param value
	 */
	public void setIsFormationNomenclature(boolean value) {
		
	}
	
	
	// gestion des setters pour synchroniser libellé et enregistrement
	
	private boolean shouldIgnoreNextSetterChampLibre = false;
	
	public final void setEoFormationPersonnel(
			EOFormationPersonnel eoFormationPersonnel) {
		if (eoFormationPersonnel != null) {
			setStrChampLibre(eoFormationPersonnel.forLibelle());
			shouldIgnoreNextSetterChampLibre = true;
		}
		this.eoFormationPersonnel = eoFormationPersonnel;
	}

	public final void setStrChampLibre(String strChampLibre) {
		if (shouldIgnoreNextSetterChampLibre) {
			shouldIgnoreNextSetterChampLibre = false;
		} else {
			this.strChampLibre = strChampLibre;
		}
	}
}