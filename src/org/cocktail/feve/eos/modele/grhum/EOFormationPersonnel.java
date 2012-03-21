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

package org.cocktail.feve.eos.modele.grhum;

import org.cocktail.feve.app.finder.Finder;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EOFormationPersonnel extends _EOFormationPersonnel {

    public EOFormationPersonnel() {
        super();
    }

    public void validateForInsert() throws NSValidation.ValidationException {
        this.validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForInsert();
    }

    public void validateForUpdate() throws NSValidation.ValidationException {
        this.validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForUpdate();
    }

    public void validateForDelete() throws NSValidation.ValidationException {
        super.validateForDelete();
    }

    /**
     * Apparemment cette methode n'est pas appelée.
     * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
     */    
    public void validateForSave() throws NSValidation.ValidationException {
        validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForSave();
        
    }

    /**
     * Peut etre appele à partir des factories.
     * @throws NSValidation.ValidationException
     */
    public void validateObjectMetier() throws NSValidation.ValidationException {
      

    }
    
    /**
     * A appeler par les validateforsave, forinsert, forupdate.
     *
     */
    private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {
           
    }


    // ajouts
    
    public static final EOQualifier QUAL_RACINE = CktlDataBus.newCondition(
    		FOR_ID_KEY + "=" + FOR_ID_PERE_KEY);

  	public static final EOSortOrdering SORT_LIBELLE = EOSortOrdering.sortOrderingWithKey(
  			FOR_LIBELLE_KEY, EOSortOrdering.CompareAscending);

  	/** Formation actuelles */
  	public static final String STR_QUAL_IS_ACTUELLE = 
  		"(" +
  				FOR_D_OUVERTURE_KEY + "<=%@ or " + FOR_D_OUVERTURE_KEY + " = nil) and " +
  						"(" +
  				FOR_D_FERMETURE_KEY + ">=%@ or " + FOR_D_FERMETURE_KEY + " = nil)";

  	public final static String IS_FEUILLE_KEY = "isFeuille";
  	
  	/**
  	 * @param edc
  	 * @return Les objet EOFormation definies comme racines de l'arbre des associations.
  	 */
  	public static NSArray getRacines(EOEditingContext edc) {
  		return fetchFormationPersonnels(edc, QUAL_RACINE, new NSArray(new Object[] {
  			SORT_LIBELLE
  		}));
  	}

  	/**
  	 * @param edc
  	 * @param qualifier
  	 * @return Les objets {@link EOFormation} fils de l'objet en cours.
  	 * 	on enleve la racine elle meme
  	 */
  	public NSArray getFils() {
  		NSMutableArray filsMutable = new NSMutableArray(tosFormationFille());
  		filsMutable.removeIdenticalObject(this);
  		NSArray fils = CktlSort.sortedArray(filsMutable.immutableClone(), FOR_LIBELLE_KEY);
  		return fils;
  	}
  	
  	/**
  	 * 
  	 * @return
  	 */
  	public boolean isFeuille() {
  		boolean isFeuille = false;
  		
  		if (getFils().count() == 0) {
  			isFeuille = true;
  		}
  		
  		return isFeuille;
  	}
  	
  	/**
  	 * @param ec
  	 * @param qual
  	 * @param fetchLimit pris en compte si >0
  	 * @return Les formation en vigueur dependant du qualifier specifie.
  	 */
  	public static NSArray rechercher(EOEditingContext ec, EOQualifier qual, int fetchLimit) {
  		NSMutableArray array = new NSMutableArray();
  		NSTimestamp now = DateCtrl.now();
  		array.add(
  				CktlDataBus.newCondition(
  						STR_QUAL_IS_ACTUELLE, 
  						new NSArray<NSTimestamp>(
  								new NSTimestamp[]{now, now})));
  		if (qual != null) {
  			array.addObject(qual);
  		}
  		EOFetchSpecification spec = new EOFetchSpecification(
  				ENTITY_NAME, 
  				new EOAndQualifier(array),
  				new NSArray(SORT_LIBELLE));
  		if (fetchLimit > 0) {
  			spec.setFetchLimit(fetchLimit);
  		}
  		spec.setUsesDistinct(true);
  		NSArray res = ec.objectsWithFetchSpecification(spec);
  		return res;
  	}
  	
  	/**
  	 * Trouver la formation feuille ayant le libellé.
  	 * @param ec
  	 * @param forLibelle
  	 * @return <code>null</code> si 0 ou plusieurs résultats
  	 */
  	public static EOFormationPersonnel getEoFormationPersonnelFeuilleForForLibelle(
  			EOEditingContext ec, String forLibelle) {
  		EOFormationPersonnel result = null;
  		
  		NSArray<EOFormationPersonnel> array = fetchFormationPersonnels(
  				ec, ERXQ.equals(FOR_LIBELLE_KEY, forLibelle), null);
  		
  		array = EOQualifier.filteredArrayWithQualifier(
  				array, ERXQ.equals(IS_FEUILLE_KEY, Boolean.TRUE));
  		
  		if (array.count() == 1) {
  			result = array.objectAtIndex(0);
  		}
  				
  		
  		return result;
  	}
  	
  	/**
  	 * Indique si le libellé indique correspond à une unique nomenclature feuille
  	 * @param ec
  	 * @param forLibelle
  	 * @return
  	 */
  	public static boolean isFormationNomenclatureFeuilleForLibelle(
  			EOEditingContext ec, String forLibelle) {
  		boolean result = false;
  		
  		if (getEoFormationPersonnelFeuilleForForLibelle(ec, forLibelle) != null) {
  			result = true;
  		}
  		
  		return result;
  	}
  	
  	/**
  	 * TODO tenir compte des dates de validité !
  	 * liste des formations feuilles par la méthode raw rows
  	 * @param ec
  	 * @return
  	 */
  	public static NSArray<EOFormationPersonnel> fetchAllFormationPersonnelsRawRows(EOEditingContext ec) {
  	  
  	  String requete =
  	    "SELECT " + FOR_ID_COLKEY + " "+
  	    "FROM "+ ENTITY_TABLE_NAME + " " +
  	    "WHERE " + FOR_ID_COLKEY + " NOT IN ( " +
  	    "SELECT DISTINCT(" + FOR_ID_PERE_COLKEY + ") FROM "+ ENTITY_TABLE_NAME + ")";
  	  
  	  NSArray<NSDictionary<String, Object>> datasDico = Finder.rawRowsForSQL(
  	  		ec, app().mainModelName(), requete);        
  
  	  NSMutableArray<EOQualifier> quals = new NSMutableArray<EOQualifier>();
  	  
  	  for (int i = 0; i < datasDico.count(); i++) {
  	  	NSDictionary<String, Object> dico = datasDico.objectAtIndex(i);
  	    Number forId = (Number) dico.valueForKey(FOR_ID_COLKEY);

  	    quals.addObject(ERXQ.equals(FOR_ID_KEY, forId));
  	  }
  	  
  	  NSArray<EOFormationPersonnel> records = fetchFormationPersonnels(
  	  		ec, ERXQ.or(quals), null);

  	  return records;
  	}
}
