package org.cocktail.feve.eos.modele.grhum;

/**
 * Un type de données dont la clé primaire ou un composé peut etre lue
 * malgré qu'elle ne soit pas visible dans le modele
 * 
 * @author ctarade
 */
public interface I_PkVisible {

	public final static String ID_KEY = "id";
	
	public String id();
	
}
