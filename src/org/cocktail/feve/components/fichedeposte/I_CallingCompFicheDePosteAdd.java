package org.cocktail.feve.components.fichedeposte;

/**
 * Descriptif des composants qui appelent le sous composante {@link CompFicheDePosteAdd}
 * 
 * @author ctarade
 */
public interface I_CallingCompFicheDePosteAdd {

	/**
	 * Le traitement a effectuer quand on a terminé le traitement dans {@link CompFicheDePosteAdd}
	 * @return
	 */
	public void doAfterCompFicheDePosteAddSuccess();

	/**
	 * Le traitement a effectuer quand on annule l'ajout dans {@link CompFicheDePosteAdd}
	 * @return
	 */
	public void doCancelCompFicheDePosteAdd();

}
