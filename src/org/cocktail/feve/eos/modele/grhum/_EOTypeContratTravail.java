// _EOTypeContratTravail.java
/*
 * Copyright Cocktail, 2001-2008 
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

// DO NOT EDIT.  Make changes to EOTypeContratTravail.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EOTypeContratTravail extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "TypeContratTravail";
	public static final String ENTITY_TABLE_NAME = "GRHUM.TYPE_CONTRAT_TRAVAIL";

	// Attributes
	public static final String C_CATEGORIE_KEY = "cCategorie";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String DROIT_CONGES_CONTRAT_KEY = "droitCongesContrat";
	public static final String DUREE_INIT_CONTRAT_KEY = "dureeInitContrat";
	public static final String DUREE_MAX_CONTRAT_KEY = "dureeMaxContrat";
	public static final String HORAIRE_HEBDOMADAIRE_KEY = "horaireHebdomadaire";
	public static final String LC_TYPE_CONTRAT_TRAV_KEY = "lcTypeContratTrav";
	public static final String LL_TYPE_CONTRAT_TRAV_KEY = "llTypeContratTrav";
	public static final String PERIODE_REF_RENOUV_KEY = "periodeRefRenouv";
	public static final String REF_REGLEMENTAIRE_KEY = "refReglementaire";
	public static final String REGIME_URSSAF_KEY = "regimeUrssaf";
	public static final String TEM_CAV_AUTOR_TITUL_KEY = "temCavAutorTitul";
	public static final String TEM_CAV_CTRL169_H_KEY = "temCavCtrl169H";
	public static final String TEM_CDI_KEY = "temCdi";
	public static final String TEM_DELEGATION_KEY = "temDelegation";
	public static final String TEM_ENSEIGNANT_KEY = "temEnseignant";
	public static final String TEM_EQUIV_GRADE_KEY = "temEquivGrade";
	public static final String TEM_INVITE_ASSOCIE_KEY = "temInviteAssocie";
	public static final String TEM_MISSION_KEY = "temMission";
	public static final String TEM_PARTIEL_KEY = "temPartiel";
	public static final String TEM_REMUNERATION_ACCESSOIRE_KEY = "temRemunerationAccessoire";
	public static final String TEM_REMUNERATION_PRINCIPALE_KEY = "temRemunerationPrincipale";
	public static final String TEM_RENOUV_CONTRAT_KEY = "temRenouvContrat";
	public static final String TEM_SERVICE_PUBLIC_KEY = "temServicePublic";
	public static final String TEM_TITULAIRE_KEY = "temTitulaire";

	public static final String C_CATEGORIE_COLKEY = "C_CATEGORIE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String DROIT_CONGES_CONTRAT_COLKEY = "DROIT_CONGES_CONTRAT";
	public static final String DUREE_INIT_CONTRAT_COLKEY = "DUREE_INIT_CONTRAT";
	public static final String DUREE_MAX_CONTRAT_COLKEY = "DUREE_MAX_CONTRAT";
	public static final String HORAIRE_HEBDOMADAIRE_COLKEY = "HORAIRE_HEBDOMADAIRE";
	public static final String LC_TYPE_CONTRAT_TRAV_COLKEY = "LC_TYPE_CONTRAT_TRAV";
	public static final String LL_TYPE_CONTRAT_TRAV_COLKEY = "LL_TYPE_CONTRAT_TRAV";
	public static final String PERIODE_REF_RENOUV_COLKEY = "PERIODE_REF_RENOUV";
	public static final String REF_REGLEMENTAIRE_COLKEY = "REF_REGLEMENTAIRE";
	public static final String REGIME_URSSAF_COLKEY = "REGIME_URSSAF";
	public static final String TEM_CAV_AUTOR_TITUL_COLKEY = "TEM_CAV_AUTOR_TITUL";
	public static final String TEM_CAV_CTRL169_H_COLKEY = "TEM_CAV_CTRL_169H";
	public static final String TEM_CDI_COLKEY = "TEM_CDI";
	public static final String TEM_DELEGATION_COLKEY = "TEM_DELEGATION";
	public static final String TEM_ENSEIGNANT_COLKEY = "TEM_ENSEIGNANT";
	public static final String TEM_EQUIV_GRADE_COLKEY = "TEM_EQUIV_GRADE";
	public static final String TEM_INVITE_ASSOCIE_COLKEY = "TEM_INVITE_ASSOCIE";
	public static final String TEM_MISSION_COLKEY = "TEM_MISSION";
	public static final String TEM_PARTIEL_COLKEY = "TEM_PARTIEL";
	public static final String TEM_REMUNERATION_ACCESSOIRE_COLKEY = "TEM_REMUNERATION_ACCESSOIRE";
	public static final String TEM_REMUNERATION_PRINCIPALE_COLKEY = "TEM_REMUNERATION_PRINCIPALE";
	public static final String TEM_RENOUV_CONTRAT_COLKEY = "TEM_RENOUV_CONTRAT";
	public static final String TEM_SERVICE_PUBLIC_COLKEY = "TEM_SERVICE_PUBLIC";
	public static final String TEM_TITULAIRE_COLKEY = "TEM_TITULAIRE";

	// Relationships

	// Utilities methods
  public EOTypeContratTravail localInstanceIn(EOEditingContext editingContext) {
    EOTypeContratTravail localInstance = (EOTypeContratTravail)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
  public String cCategorie() {
    return (String) storedValueForKey("cCategorie");
  }

  public void setCCategorie(String value) {
    takeStoredValueForKey(value, "cCategorie");
  }

  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dDebVal() {
    return (NSTimestamp) storedValueForKey("dDebVal");
  }

  public void setDDebVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebVal");
  }

  public NSTimestamp dFinVal() {
    return (NSTimestamp) storedValueForKey("dFinVal");
  }

  public void setDFinVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinVal");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public String droitCongesContrat() {
    return (String) storedValueForKey("droitCongesContrat");
  }

  public void setDroitCongesContrat(String value) {
    takeStoredValueForKey(value, "droitCongesContrat");
  }

  public Integer dureeInitContrat() {
    return (Integer) storedValueForKey("dureeInitContrat");
  }

  public void setDureeInitContrat(Integer value) {
    takeStoredValueForKey(value, "dureeInitContrat");
  }

  public Integer dureeMaxContrat() {
    return (Integer) storedValueForKey("dureeMaxContrat");
  }

  public void setDureeMaxContrat(Integer value) {
    takeStoredValueForKey(value, "dureeMaxContrat");
  }

  public Integer horaireHebdomadaire() {
    return (Integer) storedValueForKey("horaireHebdomadaire");
  }

  public void setHoraireHebdomadaire(Integer value) {
    takeStoredValueForKey(value, "horaireHebdomadaire");
  }

  public String lcTypeContratTrav() {
    return (String) storedValueForKey("lcTypeContratTrav");
  }

  public void setLcTypeContratTrav(String value) {
    takeStoredValueForKey(value, "lcTypeContratTrav");
  }

  public String llTypeContratTrav() {
    return (String) storedValueForKey("llTypeContratTrav");
  }

  public void setLlTypeContratTrav(String value) {
    takeStoredValueForKey(value, "llTypeContratTrav");
  }

  public Integer periodeRefRenouv() {
    return (Integer) storedValueForKey("periodeRefRenouv");
  }

  public void setPeriodeRefRenouv(Integer value) {
    takeStoredValueForKey(value, "periodeRefRenouv");
  }

  public String refReglementaire() {
    return (String) storedValueForKey("refReglementaire");
  }

  public void setRefReglementaire(String value) {
    takeStoredValueForKey(value, "refReglementaire");
  }

  public String regimeUrssaf() {
    return (String) storedValueForKey("regimeUrssaf");
  }

  public void setRegimeUrssaf(String value) {
    takeStoredValueForKey(value, "regimeUrssaf");
  }

  public String temCavAutorTitul() {
    return (String) storedValueForKey("temCavAutorTitul");
  }

  public void setTemCavAutorTitul(String value) {
    takeStoredValueForKey(value, "temCavAutorTitul");
  }

  public String temCavCtrl169H() {
    return (String) storedValueForKey("temCavCtrl169H");
  }

  public void setTemCavCtrl169H(String value) {
    takeStoredValueForKey(value, "temCavCtrl169H");
  }

  public String temCdi() {
    return (String) storedValueForKey("temCdi");
  }

  public void setTemCdi(String value) {
    takeStoredValueForKey(value, "temCdi");
  }

  public String temDelegation() {
    return (String) storedValueForKey("temDelegation");
  }

  public void setTemDelegation(String value) {
    takeStoredValueForKey(value, "temDelegation");
  }

  public String temEnseignant() {
    return (String) storedValueForKey("temEnseignant");
  }

  public void setTemEnseignant(String value) {
    takeStoredValueForKey(value, "temEnseignant");
  }

  public String temEquivGrade() {
    return (String) storedValueForKey("temEquivGrade");
  }

  public void setTemEquivGrade(String value) {
    takeStoredValueForKey(value, "temEquivGrade");
  }

  public String temInviteAssocie() {
    return (String) storedValueForKey("temInviteAssocie");
  }

  public void setTemInviteAssocie(String value) {
    takeStoredValueForKey(value, "temInviteAssocie");
  }

  public String temMission() {
    return (String) storedValueForKey("temMission");
  }

  public void setTemMission(String value) {
    takeStoredValueForKey(value, "temMission");
  }

  public String temPartiel() {
    return (String) storedValueForKey("temPartiel");
  }

  public void setTemPartiel(String value) {
    takeStoredValueForKey(value, "temPartiel");
  }

  public String temRemunerationAccessoire() {
    return (String) storedValueForKey("temRemunerationAccessoire");
  }

  public void setTemRemunerationAccessoire(String value) {
    takeStoredValueForKey(value, "temRemunerationAccessoire");
  }

  public String temRemunerationPrincipale() {
    return (String) storedValueForKey("temRemunerationPrincipale");
  }

  public void setTemRemunerationPrincipale(String value) {
    takeStoredValueForKey(value, "temRemunerationPrincipale");
  }

  public String temRenouvContrat() {
    return (String) storedValueForKey("temRenouvContrat");
  }

  public void setTemRenouvContrat(String value) {
    takeStoredValueForKey(value, "temRenouvContrat");
  }

  public String temServicePublic() {
    return (String) storedValueForKey("temServicePublic");
  }

  public void setTemServicePublic(String value) {
    takeStoredValueForKey(value, "temServicePublic");
  }

  public String temTitulaire() {
    return (String) storedValueForKey("temTitulaire");
  }

  public void setTemTitulaire(String value) {
    takeStoredValueForKey(value, "temTitulaire");
  }


  public static EOTypeContratTravail createTypeContratTravail(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String temEquivGrade
, String temRemunerationPrincipale
) {
    EOTypeContratTravail eo = (EOTypeContratTravail) EOUtilities.createAndInsertInstance(editingContext, _EOTypeContratTravail.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setTemEquivGrade(temEquivGrade);
		eo.setTemRemunerationPrincipale(temRemunerationPrincipale);
    return eo;
  }

  public static NSArray fetchAllTypeContratTravails(EOEditingContext editingContext) {
    return _EOTypeContratTravail.fetchAllTypeContratTravails(editingContext, null);
  }

  public static NSArray fetchAllTypeContratTravails(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EOTypeContratTravail.fetchTypeContratTravails(editingContext, null, sortOrderings);
  }

  public static NSArray fetchTypeContratTravails(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOTypeContratTravail.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOTypeContratTravail fetchTypeContratTravail(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeContratTravail.fetchTypeContratTravail(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeContratTravail fetchTypeContratTravail(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EOTypeContratTravail.fetchTypeContratTravails(editingContext, qualifier, null);
    EOTypeContratTravail eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOTypeContratTravail)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one TypeContratTravail that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeContratTravail fetchRequiredTypeContratTravail(EOEditingContext editingContext, String keyName, Object value) {
    return _EOTypeContratTravail.fetchRequiredTypeContratTravail(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOTypeContratTravail fetchRequiredTypeContratTravail(EOEditingContext editingContext, EOQualifier qualifier) {
    EOTypeContratTravail eoObject = _EOTypeContratTravail.fetchTypeContratTravail(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no TypeContratTravail that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOTypeContratTravail localInstanceIn(EOEditingContext editingContext, EOTypeContratTravail eo) {
    EOTypeContratTravail localInstance = (eo == null) ? null : (EOTypeContratTravail)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
