// EOFicheDePoste.java
// Created on Wed Oct 26 08:25:07  2005 by Apple EOModeler Version 5.2

package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

public abstract class A_Fiche extends A_FeveCktlRecord {

  public final static NSArray ARRAY_SORT_INDIVIDU  = new NSArray(
      new EOSortOrdering[] {
          EOSortOrdering.sortOrderingWithKey("toAffectationDetailActuelle.toAffectation.toIndividu.nomUsuel", EOSortOrdering.CompareAscending),
          EOSortOrdering.sortOrderingWithKey("toAffectationDetailActuelle.toAffectation.toIndividu.prenom", EOSortOrdering.CompareAscending)            
      }
  );

  public final static int FICHE_DE_POSTE = 1;
  public final static int FICHE_LOLF = 2;

  public final static String D_DEBUT 	= "dDebut";
  public final static String D_FIN 		= "dFin";
  

  public int typeFiche;

  public A_Fiche() {
    super();
  }

  public NSTimestamp dDebut() {
    if (typeFiche == FICHE_DE_POSTE) {
      return (NSTimestamp) storedValueForKey("fdpDDebut");
    } else {
      return (NSTimestamp) storedValueForKey("floDDebut");
    }
  }

  public void setDDebut(NSTimestamp value) {
    if (typeFiche == FICHE_DE_POSTE) {
      takeStoredValueForKey(value, "fdpDDebut");
    } else {
      takeStoredValueForKey(value, "floDDebut");
    }
  }

  public NSTimestamp dFin() {
    if (typeFiche == FICHE_DE_POSTE) {
      return (NSTimestamp) storedValueForKey("fdpDFin");
    } else {
      return (NSTimestamp) storedValueForKey("floDFin");
    }
  }

  public void setDFin(NSTimestamp value) {
    if (typeFiche == FICHE_DE_POSTE) {
      takeStoredValueForKey(value, "fdpDFin");
    } else {
      takeStoredValueForKey(value, "floDFin");
    }
  }

  public org.cocktail.feve.eos.modele.mangue.EOPoste toPoste() {
    return (org.cocktail.feve.eos.modele.mangue.EOPoste) storedValueForKey("toPoste");
  }

  public void setToPoste(org.cocktail.feve.eos.modele.mangue.EOPoste value) {
    takeStoredValueForKey(value, "toPoste");
  }

  // methode rajoutees

  /**
   * Affichage d'une fiche : la cle primaire + les dates 
   */
  public String display() {
    StringBuffer buff = new StringBuffer(identifiant());
    buff.append(" (");
    if (dFin() != null)
    	buff.append("du ").append(DateCtrl.dateToString(dDebut())).append(" au ").append(DateCtrl.dateToString(dFin()));
    else
    	buff.append("à partir du ").append(DateCtrl.dateToString(dDebut()));
    buff.append(")");
    return buff.toString();
  }

  /**
   * La cle primaire precedee de #
   */
  public String identifiant() {
  	StringBuffer buff = new StringBuffer("#");
    if (typeFiche == FICHE_DE_POSTE) {
    	buff.append(EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("fdpKey"));
    } else {
    	buff.append(EOUtilities.primaryKeyForObject(editingContext(), this).valueForKey("floKey"));
    }
    return buff.toString();
  }
  
  /**
   * les individus qui ont occupe ce poste pendant la duree de validite de la fiche du plus ancien au plus recent
   * @return une liste d'objets de l'entite <code>AffectationDetail</code>
   */
  public NSArray tosAffectationDetail() {
    NSArray occupations = new NSArray();
    for (int i = 0; i < toPoste().tosAffectationDetail().count(); i++) {
      boolean conserver = false;
      EOAffectationDetail affectationDetail = (EOAffectationDetail) toPoste().tosAffectationDetail().objectAtIndex(i);
      if (affectationDetail.dFin() == null && dFin() == null) {
        conserver = true;
      } else if (affectationDetail.dFin() == null) {
        if (DateCtrl.isBeforeEq(affectationDetail.dDebut(), dFin())) {
          conserver = true;
        }
      } else if (dFin() == null) {
        if (DateCtrl.isBeforeEq(dDebut(), affectationDetail.dFin())) {
          conserver = true;
        }
      } else if (DateCtrl.isBeforeEq(dDebut(), affectationDetail.dDebut()) && DateCtrl.isAfterEq(dFin(), affectationDetail.dFin())) {
        conserver = true;
      } else if (DateCtrl.isAfterEq(dDebut(), affectationDetail.dDebut()) && DateCtrl.isAfterEq(dFin(), affectationDetail.dFin())) {
        conserver = true;
      } else if (DateCtrl.isBeforeEq(dDebut(), affectationDetail.dDebut()) && DateCtrl.isBeforeEq(dFin(), affectationDetail.dFin())) {
        conserver = true;
      } else if (DateCtrl.isAfterEq(dDebut(), affectationDetail.dDebut()) && DateCtrl.isBeforeEq(dFin(), affectationDetail.dFin())) {
        conserver = true;
      }
      if (conserver) {
        occupations = occupations.arrayByAddingObject(affectationDetail);
      }
    }
    // classement
    return (NSArray) EOSortOrdering.sortedArrayUsingKeyOrderArray(
    		occupations, CktlSort.newSort(EOAffectationDetail.ADE_D_DEBUT_KEY));
  }
  
  /**
   * La liste des occupations de cette fiche par l'individu ciblé
   * @param individu
   * @return
   */
  public NSArray tosAffectationDetail(EOIndividu individu) {
  	return EOQualifier.filteredArrayWithQualifier(
  			tosAffectationDetail(), 
  			CktlDataBus.newCondition(
  					EOAffectationDetail.TO_AFFECTATION_KEY + "." + EOAffectation.TO_INDIVIDU_KEY + "=%@",
  					new NSArray(individu)));
  					
  }
  
  public final static String IS_ACTUELLE = "isActuelle";

  
  /**
   * occupant de la fiche de poste : 
   * - si la fiche est ouverte : l'occupant actuel du poste
   * - si la fiche est ferme : l'occupant lors de la fermeture
   */
  public EOAffectationDetail toAffectationDetailActuelle() {
    EOAffectationDetail record = null;
    if (isActuelle()) {
    	record = toPoste().toAffectationDetailActuelle();
    } else if (isFermee()) {
    	EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
    			"(adeDateDiffAffectation = 1 AND adeDDebut <= %@ AND (" +
    			"	adeDFin >= %@ OR adeDFin = nil)" +
    			") OR" +
    	    "(adeDateDiffAffectation = 0 AND toAffectation.dDebAffectation <= %@ AND (" +
    	    "	toAffectation.dFinAffectation >= %@ OR toAffectation.dFinAffectation = nil)" +
    	    ")",
    	    new NSArray(new NSTimestamp[] {dFin(), dFin(), dFin(), dFin()})); 
      NSArray records = EOQualifier.filteredArrayWithQualifier(tosAffectationDetail(), qual);
      if (records.count() > 0) {
        record = (EOAffectationDetail) records.lastObject();
      } 
    } 
    return record;
  }
  
  /**
   * La derniere occupation en date du poste 
   */
  public EOAffectationDetail toAffectationDetailDerniere() {
    return toPoste().toAffectationDetailDerniere();
  }
  
  /**
   * La fiche est-elle actuelle 
   */
  public boolean isActuelle() {
  	return DateCtrl.isBeforeEq(dDebut(), DateCtrl.now()) &&
  		(dFin() == null || DateCtrl.isAfterEq(dFin(), DateCtrl.now()));
  }
  
  /**
   * La fiche est-elle non actuelle 
   */
  public boolean isNotActuelle() {
  	return !isActuelle();
  }
  
  /**
   * La fiche est-elle fermee : la date de fin
   * est avant la date du jour
   */
  public boolean isFermee() {
  	return dFin() != null && DateCtrl.isBefore(dFin(), DateCtrl.now());
  }
  
  /**
   * La fiche est-elle future : la date de debut
   * est apres la date du jour
   */
  public boolean isFuture() {
  	return DateCtrl.isAfter(dDebut(), DateCtrl.now());
  }

  /**
   * Methode a implementer qui permet de savoir si
   * des avertissement concernent cette fiche
   */
  public abstract boolean hasWarning();
  
  /**
   * Methode a implementer qui permet d'avoir le
   * descriptif des avertissements
   */
  public abstract String htmlWarnMessage();

	/**
	 * Trouver les fiches qui existent pendant la periode passee en parametre
	 * @param poste : le poste sur lequel chercher
	 * @param isFicheDePoste : <code>true</code> si on recherche des fiche de poste,
	 * 				 								<code>false</code> si fiche LOLF.
	 * @param dDebut : le debut de la periode
	 * @param dFin : la fin de la periode
	 * @param ficheIgnore : une fiche a ignorer (dans le cas de modification);
	 * @return
	 */
	public static NSArray<A_Fiche> findFicheForPeriodeIgnoring(EOPoste poste, boolean isFicheDePoste,
			NSTimestamp dDebut, NSTimestamp dFin, A_Fiche ficheIgnore) {
		NSArray<A_Fiche> fiches = (isFicheDePoste ? poste.tosFicheDePoste() : poste.tosFicheLolf());
	  String strQual = (dFin == null ? "dFin >= %@ or dFin = nil" :
	  	"(" +
	      "(dDebut >= %@ and dFin >= %@) or " +
	      "(dDebut >= %@ and dFin <= %@) or " +
	      "(dDebut <= %@ and dFin <= %@) or " +
	      "(dDebut <= %@ and dFin >= %@))");
	  EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, 
	  		(dFin == null ? new NSArray<NSTimestamp>(dDebut) : new NSArray<NSTimestamp>(
	  				new NSTimestamp[]{dDebut, dFin, dDebut, dFin, dDebut, dFin, dDebut, dFin})));
	  NSArray<A_Fiche> records = EOQualifier.filteredArrayWithQualifier(fiches, qual);	  
	  // ignore la fiche 
	  if (ficheIgnore != null) {
	  	NSMutableArray<A_Fiche> recordsMutable = new NSMutableArray<A_Fiche>(records);
	  	recordsMutable.removeObject(ficheIgnore);
	  	records = recordsMutable.immutableClone();
	  }
	  return records;
	}
}
