package org.cocktail.feve.app;
/*
 * Copyright CRI - Universite de La Rochelle, 2001-2007 
 * 
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use, 
 * modify and/or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and, more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOEditingContext;

/**
 * Complement de methodes de CktlDataBus a integrer 
 * dans la prochaine version du framework.
 */
public class FeveDataBus extends CktlDataBus {
  
  /** Le message de la derniere erreur */
  private String errorMessage;
  /**
   * Cree une nouvelle instance de data bus.
   */
  public FeveDataBus(EOEditingContext editingContext) {
    super(editingContext);
  }
  
  /**
   * Retourne le dernier message d'erreur ou <i>null</i> si aucun message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }
 
  /**
   * Definit un message d'erreur.
   */
  protected void setErrorMessage(String message) {
    this.errorMessage = message;
  }
  
  /**
   * Teste si la derniere operation avec la base de donnees etait executee
   * avec les erreurs.
   */
  public boolean hasError() {
    return (errorMessage != null);
  }
  
  /**
   * Test si la longeur de la valeur saisie ne depasse pas la valeur
   * maximale autorisee.
   * 
   * @param tableName Le nom de la table, dont l'attribut sera modifie.
   * @param attributeName Le nom de l'attribut.
   * @param attributeValue La valeur de l'attribut a enregistrer dans la base.
   * @param libelle Le libelle de champs de l'interface dans lequel la valeur
   *   etait saisie.
   * @param makeMessage Indique s'il faut generer un message d'avertissement.
   */
  public boolean checkForMaxSize(String tableName,
                                 String attributeName,
                                 String attributeValue,
                                 String libelle,
                                 int reserverdLen,
                                 boolean makeMessage)
  {
    int maxLen = CktlRecord.maxLengthForAttribute(tableName, attributeName);
    int realLen = (attributeValue==null)?0:attributeValue.length();
    setErrorMessage(null);
    if (realLen > (maxLen-reserverdLen)) {
      if (makeMessage) {
        StringBuffer sb = new StringBuffer();
        sb.append("La longeur de texte saisi dans le champ \"").append(libelle);
        sb.append("\"<br>d&eacute;passe la longeur maximale autoris&eacute;e.");
        sb.append("<br><br>Longeur maximale : ").append(maxLen-reserverdLen);
        sb.append("<br>Longeur saisie : ").append(realLen);
        setErrorMessage(sb.toString());
      }
      return false;
    }
    return true;
  }

}
