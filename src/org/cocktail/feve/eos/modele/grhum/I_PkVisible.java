package org.cocktail.feve.eos.modele.grhum;

/**
 * Un type de donn�es dont la cl� primaire ou un compos� peut etre lue
 * malgr� qu'elle ne soit pas visible dans le modele
 * 
 * @author ctarade
 */
public interface I_PkVisible {

	public final static String ID_KEY = "id";
	
	public String id();
	
}
