package org.cocktail.feve.app;

import java.util.Enumeration;
import java.util.Hashtable;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlWebApplication;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Nouvelles methodes a integrer dans la classe CktlWebApplication
 * @author ctarade
 *
 */
public abstract class NextCRIWebApplication extends CktlWebApplication {
  
  
  
  /**
   * table de config "custom" pour l'appli
   */
  public abstract String customConfigEntityName();
  
  /**
   * table de config "custom" pour l'appli
   */
  public abstract String customConfigTableName();
  
  /**
   * L'attribut clé de la table {@link #customConfigEntityName()}
   */
  public abstract String customConfigTableAttributeKeyName();
  
  /**
   * L'attribut valeur de la table {@link #customConfigEntityName()}
   */
  public abstract String customConfigTableAttributeValueName();
  
  
  /**
   * liste des variables obligatoires de la table de param custom a l'appli
   * @return
   */
  public abstract String[] listeVariablesCustomParametre();
	
  /**
   * Pour les fetch sur des donn�es qui peuvent etre modifi�e dans l'appli
   * @return
   */
  public abstract EOEditingContext paramEditingContext();
  
	/**
	 * recup de donnees dans la table de parametres
	 * @param key
	 * @return
	 */
	public String customConfigStringForKey(String key) {
		String value = null;
		
		
		EOQualifier qual = CktlDataBus.newCondition(customConfigTableAttributeKeyName() + "='" + key + "'");

		NSArray<A_FeveCktlRecord> records = dataBus().fetchArray(
				paramEditingContext(), customConfigEntityName(), qual, null);

		if (records.count() > 0) {
			value = records.objectAtIndex(0).stringForKey(customConfigTableAttributeValueName());
			// si la valeur est vide, alors on indique explicitement la chaine vide
			// car le paramètre existe bel et bien
			if (value == null) {
				value = StringCtrl.emptyString();
			}
		}
		
		return value;
	}
  
  private final static String SB_SEPARATOR = ", ";
  
	// variables facultatives
	public boolean checkCustomParams() {
		boolean hasMandatoryMissing = false;
  	StringBuffer sb = new StringBuffer("Controle de la presence des valeurs dans "+customConfigTableName()+"\n" +
		 "-------------------------------------------------------\n\n");
		StringBuffer sbMandatoryMissing = new StringBuffer();
		Hashtable<String, String> hMandatoryFound = new Hashtable<String, String>();
		String[] keys = listeVariablesCustomParametre();
		if (keys != null && keys.length > 0) {
			for (int i = 0;  i <  keys.length; i++) {
				String key = keys[i];
				String value = customConfigStringForKey(key);
				if (value == null) {
					sbMandatoryMissing.append(key).append(SB_SEPARATOR);
					hasMandatoryMissing = true;
				} else {
					hMandatoryFound.put(key, value);
				}
			}
			// enlever le dernier separateur et ajouter le message d'erreur
			if (sbMandatoryMissing.length() > 0) {
				int sbSize = sbMandatoryMissing.length();
				sbMandatoryMissing.replace(sbSize - SB_SEPARATOR.length(), sbSize, "");
				sbMandatoryMissing.insert(0, "  > Valeurs de "+customConfigTableName()+" absents : ");
				sbMandatoryMissing.append(" - ERREUR");
			} else {
				sbMandatoryMissing.insert(0, "  > Toutes les valeurs de "+customConfigTableName()+" sont presentes - OK");
			}		
			sb.append(sbMandatoryMissing).append("\n");
			sb.append(getFormatted(hMandatoryFound));
		}
		CktlLog.rawLog(sb.toString());
		return !hasMandatoryMissing;
  }
  
  /**
   * Afficher le contenu format� cl� = valeur d'une {@link Hashtable}
   */
	private static StringBuffer getFormatted(Hashtable<String, String> hashtable) {
  	// determiner la taille de cle la plus long
  	int maxLength = 0;
  	Enumeration<String> enumKeys = hashtable.keys();
  	while (enumKeys.hasMoreElements()) {
  		String key = enumKeys.nextElement();
  		if (key.length() > maxLength) {
  			maxLength = key.length();
  		}
  	}
  	// affichage
  	StringBuffer sb = new StringBuffer();
  	enumKeys = hashtable.keys();
  	while (enumKeys.hasMoreElements()) {
  		String key = enumKeys.nextElement();
  		String value = hashtable.get(key);
  		sb.append("    * ").append(createKeyValue(key, value, maxLength)).append("\n");
  	}
    sb.append("\n");
    return sb;
  }
  

  private static String createKeyValue(final String key, final Object value, int keyLength) {
  	if (key.length()>keyLength) {
  		keyLength = key.length();
  	}
  	return StringCtrl.extendWithChars(key," ",keyLength,false) + " = " + value;
  }
  
}
