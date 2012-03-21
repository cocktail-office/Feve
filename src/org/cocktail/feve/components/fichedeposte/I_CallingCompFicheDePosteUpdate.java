package org.cocktail.feve.components.fichedeposte;

/**
 * Descriptif des composants qui appelent le sous composante {@link CompFicheDePosteUpdate}
 * 
 * @author ctarade
 */
public interface I_CallingCompFicheDePosteUpdate {

	/**
	 * Le traitement a effectuer quand on a terminé le traitement dans {@link CompFicheDePosteUpdate}
	 * @return
	 */
	public void doAfterCompFicheDePosteUpdateSuccess();

	/**
	 * Le traitement a effectuer quand on annule la mise a jour dans {@link CompFicheDePosteUpdate}
	 * @return
	 */
	public void doCancelCompFicheDePosteUpdate();
}
