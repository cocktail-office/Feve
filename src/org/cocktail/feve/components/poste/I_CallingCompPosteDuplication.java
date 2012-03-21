package org.cocktail.feve.components.poste;

public interface I_CallingCompPosteDuplication {

	/**
	 * Le traitement a effectuer quand on a terminé le traitement dans {@link CompPosteDuplication}
	 * @return
	 */
	public void doAfterCompPosteDuplicationSuccess();

	/**
	 * Le traitement a effectuer quand on annule l'ajout dans {@link CompPosteDuplication}
	 * @return
	 */
	public void doCancelCompPosteDuplication();
	
}
