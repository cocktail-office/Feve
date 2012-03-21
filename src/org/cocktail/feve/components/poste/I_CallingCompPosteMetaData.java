package org.cocktail.feve.components.poste;

/**
 * Descriptif des composants qui appelent le sous composante {@link CompPoste}
 * 
 * @author ctarade
 */
public interface I_CallingCompPosteMetaData {

	/**
	 * Le traitement a effectuer quand on a terminé le traitement dans {@link CompPoste}
	 * @return
	 */
	public void doAfterCompPosteMetaDataSuccess();
	
	/**
	 * Le traitement a effectuer quand on a annulé le traitement dans {@link CompPoste}
	 * @return
	 */
	public void doAfterCompPosteMetaDataCancel();

}
