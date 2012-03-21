// _EONiveauCompetence.java
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

// DO NOT EDIT.  Make changes to EONiveauCompetence.java instead.
package org.cocktail.feve.eos.modele.grhum;

import com.webobjects.foundation.*;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.cocktail.feve.eos.modele.A_FeveCktlRecord;


@SuppressWarnings("all")
public abstract class _EONiveauCompetence extends  A_FeveCktlRecord {
	public static final String ENTITY_NAME = "NiveauCompetence";
	public static final String ENTITY_TABLE_NAME = "GRHUM.NIVEAU_COMPETENCE_PRO";

	// Attributes
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String NCP_LIBELLE_KEY = "ncpLibelle";
	public static final String NCP_POSITION_KEY = "ncpPosition";

	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String NCP_LIBELLE_COLKEY = "NCP_LIBELLE";
	public static final String NCP_POSITION_COLKEY = "NCP_POSITION";

	// Relationships

	// Utilities methods
  public EONiveauCompetence localInstanceIn(EOEditingContext editingContext) {
    EONiveauCompetence localInstance = (EONiveauCompetence)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


	// Accessors methods
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

  public String ncpLibelle() {
    return (String) storedValueForKey("ncpLibelle");
  }

  public void setNcpLibelle(String value) {
    takeStoredValueForKey(value, "ncpLibelle");
  }

  public Integer ncpPosition() {
    return (Integer) storedValueForKey("ncpPosition");
  }

  public void setNcpPosition(Integer value) {
    takeStoredValueForKey(value, "ncpPosition");
  }


  public static EONiveauCompetence createNiveauCompetence(EOEditingContext editingContext, String ncpLibelle
) {
    EONiveauCompetence eo = (EONiveauCompetence) EOUtilities.createAndInsertInstance(editingContext, _EONiveauCompetence.ENTITY_NAME);    
		eo.setNcpLibelle(ncpLibelle);
    return eo;
  }

  public static NSArray fetchAllNiveauCompetences(EOEditingContext editingContext) {
    return _EONiveauCompetence.fetchAllNiveauCompetences(editingContext, null);
  }

  public static NSArray fetchAllNiveauCompetences(EOEditingContext editingContext, NSArray sortOrderings) {
    return _EONiveauCompetence.fetchNiveauCompetences(editingContext, null, sortOrderings);
  }

  public static NSArray fetchNiveauCompetences(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EONiveauCompetence.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EONiveauCompetence fetchNiveauCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EONiveauCompetence.fetchNiveauCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EONiveauCompetence fetchNiveauCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _EONiveauCompetence.fetchNiveauCompetences(editingContext, qualifier, null);
    EONiveauCompetence eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EONiveauCompetence)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one NiveauCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EONiveauCompetence fetchRequiredNiveauCompetence(EOEditingContext editingContext, String keyName, Object value) {
    return _EONiveauCompetence.fetchRequiredNiveauCompetence(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EONiveauCompetence fetchRequiredNiveauCompetence(EOEditingContext editingContext, EOQualifier qualifier) {
    EONiveauCompetence eoObject = _EONiveauCompetence.fetchNiveauCompetence(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no NiveauCompetence that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EONiveauCompetence localInstanceIn(EOEditingContext editingContext, EONiveauCompetence eo) {
    EONiveauCompetence localInstance = (eo == null) ? null : (EONiveauCompetence)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }


// FetchSpecs...


}
