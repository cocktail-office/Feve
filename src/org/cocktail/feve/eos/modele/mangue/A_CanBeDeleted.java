package org.cocktail.feve.eos.modele.mangue;

/**
 * Gestion de liste d'objets qui ont besoin d'etre "marques" comme etant 
 * a supprimer.
 * Exemple : liste d'activites associees
 * 
 * @author ctarade
 */
public abstract class A_CanBeDeleted 
	extends A_CanChangePosition {

	// suppression
	
	public final static String IS_MARKED_TO_DELETE_KEY = "isMarkedToDelete";
	
	private Boolean isMarkedToDelete = Boolean.FALSE;

	public final synchronized Boolean getIsMarkedToDelete() {
		return isMarkedToDelete;
	}

	public final synchronized void setIsMarkedToDelete(Boolean markedToDelete) {
		isMarkedToDelete = markedToDelete;
	}


}
