package org.cocktail.feve.components.common;


/**
 * Definir les methodes necessaires aux composants
 * qui affichent {@link CompOccupationAdd}
 * 
 * @author ctarade
 */
public interface I_CallingCompOccupationAdd {

	/** L'action a faire dès lors qu'une occupation a été créée avec succès */
	public void doAfterOccupationAddSuccess();

	/**
	 * Le traitement a effectuer quand on annule l'ajout dans {@link CompOccupationAdd}
	 * @return
	 */
	public void doCancelOccupationAdd();
}
