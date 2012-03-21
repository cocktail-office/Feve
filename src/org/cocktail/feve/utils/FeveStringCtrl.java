package org.cocktail.feve.utils;

import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

/**
 * Traitement de chaines de caractères (à intégrer dans {@link StringCtrl}
 * 
 * @author ctarade
 */
public class FeveStringCtrl {
	
  /**
   * L'apostrophe "right single quote mark"
   */
  private final static String rightSingleQuoteMark = "\u2019";

  /**
   * Une autre apostrophe
   */
  private final static String otherSingleQuoteMark = "\u0092";
  
  /**
   * Le oe collé de coeur
   */
  private final static String latinSmallLigatureOe = "\u009C";
  
  /**
   * 3 petite points
   */
  private final static String troisPetitsPoints 	= "\u0085";
  private final static String troisPetitsPoints2 	= "\u00BF";

  /**
   * tiret 
   */
  private final static String tiret = "\u0096";
  

  /**
   * Remplacer tous les caractères "word" par des caractères UTF-8
   * @param str
   * @return
   */
  public static String cleanWordSpecs(String str) {
  	
  	str = StringCtrl.replace(str, rightSingleQuoteMark, "'");
  	str = StringCtrl.replace(str, otherSingleQuoteMark, "'");
  	str = StringCtrl.replace(str, latinSmallLigatureOe, "oe");
  	str = StringCtrl.replace(str, troisPetitsPoints, 		"...");
  	str = StringCtrl.replace(str, troisPetitsPoints2, 	"...");
  	str = StringCtrl.replace(str, tiret, "-");
  	
  	return str;
  }
	
}
