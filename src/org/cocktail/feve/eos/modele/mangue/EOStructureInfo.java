package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOStructureInfo extends _EOStructureInfo {

    public EOStructureInfo() {
        super();
    }

    public void validateForInsert() throws NSValidation.ValidationException {
    	setDCreation(DateCtrl.now());
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
     * Apparemment cette methode n'est pas appelee.
     * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
     */    
    public void validateForSave() throws NSValidation.ValidationException {
        validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForSave();
        
    }

    /**
     * Peut etre appele a partir des factories.
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

    // methodes rajoutees
    
    public final static NSArray arraySort = new NSArray(
        new Object[] {
            EOSortOrdering.sortOrderingWithKey("toStructure.llStructure", EOSortOrdering.CompareAscending),
            EOSortOrdering.sortOrderingWithKey("sinDDebut", EOSortOrdering.CompareAscending)
        }
    );
    
    
    public String display() {
      String display = toStructure().display() + " ";
      if (sinDFin() != null) {
        display += "du " + DateCtrl.dateToString(sinDDebut()) + " au " + DateCtrl.dateToString(sinDFin());
      } else {
        display += " à partir du " + DateCtrl.dateToString(sinDDebut());  
      }
      return display;
     }
    

    public static EOStructureInfo findStructureInfoForStructureAndDateAndTypeInContext
		(EOEditingContext ec, EOStructure structure, NSTimestamp dateRef, Integer type) {
		  EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(
		      "toStructure = %@ AND sinType = %@ AND sinDDebut <= %@ AND (sinDFin >= %@ OR sinDFin = nil)",
		      new NSArray(new Object[] { structure, type, dateRef, dateRef})
		  );
		  NSArray records = UtilDb.fetchArray(ec, "StructureInfo", qual, null);
		  EOStructureInfo record = null;
		  if (records.count() > 0) {
		    record = (EOStructureInfo) records.lastObject();
		  }
		  return record;
		}

		private static EOStructureInfo newDefaultRecordInContext(EOEditingContext ec) {
		  EOStructureInfo record = new EOStructureInfo();
		  ec.insertObject(record);
		  return record;
		}

		public static EOStructureInfo newRecordInContext(
		    EOEditingContext ec, 
		    EOStructure structure,
		    NSTimestamp debut,
		    NSTimestamp fin,
		    String libelle,
		    Integer type
		) {
		  EOStructureInfo newRecord = EOStructureInfo.newDefaultRecordInContext(ec);
		  
		  newRecord.addObjectToBothSidesOfRelationshipWithKey(structure,   "toStructure");
		  if (debut == null) {
		    debut = DateCtrl.now();
		  }
		  newRecord.setSinDDebut(debut);      
		  newRecord.setSinDFin(fin);
		  newRecord.setSinType(type);
		  newRecord.setSinLibelle(libelle);
		  
		  return newRecord;
		}

		public static Integer TYPE_MISSION_COMPOSANTE = new Integer(1);
    public static Integer TYPE_MISSION_SERVICE = new Integer(2);
    public static Integer TYPE_PROJET_SERVICE = new Integer(3);
}
