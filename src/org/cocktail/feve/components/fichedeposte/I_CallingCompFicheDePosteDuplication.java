package org.cocktail.feve.components.fichedeposte;

public interface I_CallingCompFicheDePosteDuplication {

	/**
	 * Le traitement a effectuer quand on a terminé le traitement dans {@link CompFicheDePosteDuplication}
	 * @return
	 */
	public void doAfterCompFicheDePosteDuplicationSuccess();

	/**
	 * Le traitement a effectuer quand on annule l'ajout dans {@link CompFicheDePosteDuplication}
	 * @return
	 */
	public void doCancelCompFicheDePosteDuplication();
	
}
