package org.cocktail.feve.components.common;

/**
 * Definir les methodes necessaires aux composants
 * qui affichent {@link CompOccupationUpdate}
 * 
 * @author ctarade
 */
public interface I_CallingCompOccupationUpdate {

	/** L'action a faire dès lors qu'une occupation a été mise a jour avec succès */
	public void doAfterOccupationUpdateSuccess();

	/**
	 * Le traitement a effectuer quand on annule la mise a jour dans {@link CompOccupationUpdate}
	 * @return
	 */
	public void doCancelOccupationUpdate();
}
