package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver._private.WOCheckBox;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;
public class EOAffectationDetail extends _EOAffectationDetail {

    public EOAffectationDetail() {
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
    
    public String displayAgent() {
      String display = toAffectation().toIndividu().display() + " " + display();
      return display;
    }
    
    public String display() {
      String display = toAffectation().toIndividu().display() + " ";
      if (dFin() != null) {
        display = "du " + DateCtrl.dateToString(dDebut()) + " au " + DateCtrl.dateToString(dFin());
      } else {
        display = "a partir du " + DateCtrl.dateToString(dDebut());        
      }
      return display;
    }
    
    public final static String IS_ACTUELLE_KEY 	= "isActuelle";
    public final static String IS_ANCIENNE_KEY 	= "isAncienne";
    public final static String IS_FUTURE_KEY 		= "isFuture";
    
    public boolean isActuelle() {
      return dDebut() != null && DateCtrl.isBeforeEq(dDebut(), DateCtrl.now()) &&    
          ( dFin() == null || DateCtrl.isAfterEq(dFin(), DateCtrl.now()));
    }
    
    public boolean isAncienne() {
      return dFin() != null && DateCtrl.isBefore(dFin(), DateCtrl.now());
    }
    
    public boolean isFuture() {
      return dDebut() != null && DateCtrl.isAfter(dDebut(), DateCtrl.now()) && 
      	(dFin() == null || DateCtrl.isAfter(dFin(), DateCtrl.now()));
    }
    
    public final static String D_DEBUT_AFFECTATION_DETAIL = "dDebut";
    
    /**
     * La date de debut de l'occupation conditionnee
     */
    public NSTimestamp dDebut() {
    	if (adeDateDiffAffectation().intValue() == 1)
    		return adeDDebut();
    	else if (toAffectation() != null)
    		return toAffectation().dDebAffectation();
    	else return null;
    }
    
    public final static String D_FIN_AFFECTATION_DETAIL = "dFin";

    /**
     * La date de fin de l'occupation conditionnee
     */
    public NSTimestamp dFin() {
    	if (adeDateDiffAffectation().intValue() == 1)
    		return adeDFin();
    	else if (toAffectation() != null)
    		return toAffectation().dFinAffectation();
    	else return null;
    }

    /**
     * L'affichage d'une occupation. 
     * <affectation gepeto> + <dates de l'affectation detail>
     * 
     * On afficher une message d'erreur au cas ou l'affectation 
     * gepeto attachee n'existe plus.
     */
    public String fullDisplay(boolean isHtml) {
    	try {
    		boolean isDifferent = adeDateDiffAffectation().intValue() == 1;
    		String disp = toAffectation().display() + 
    			(isDifferent ? (isHtml?" <br><i>":"")+ "reduction du " + DateCtrl.dateToString(dDebut()) + " au " +
    					(dFin() != null ? DateCtrl.dateToString(dFin()) : " [pas de fin]") + (isHtml?"</i>":"")
    					: "");
    		return disp;
    	} catch (Exception e) {
    	}
    	return "*** ERREUR ***";
    }
    
    /**
     * acces a {@link #adeDateDiffAffectation()} en boolean 
     * @return
     */
    public boolean getIsAdeDateDiffAffectation() {
    	return adeDateDiffAffectation().intValue() == 1;
    }
    
    /**
     * acces a {@link #adeDateDiffAffectation()} en boolean inverse
     * pour les {@link WOCheckBox}
     * @return
     */
    public boolean getIsAdeDateSameAffectation() {
    	return adeDateDiffAffectation().intValue() == 0;
    }
    
    /**
     * acces a {@link #adeDateDiffAffectation()} en boolean
     * pour les {@link WOCheckBox}
     * @return
     */
    public void setIsAdeDateSameAffectation(boolean isAdeDateSameAffectation) {
    	if (isAdeDateSameAffectation) {
    		setAdeDateDiffAffectation(new Integer(0));
    	} else {
    		setAdeDateDiffAffectation(new Integer(1));
    	}
    }
    
    /**
     * Indique si les dates de cette occupation se chevauchent 
     * avec une autre occupation sur le même poste
     * @return
     */
    public boolean isChevauchementAutreAffectationDetailMemeAgent() {
    	boolean result = (getListAffectationDetailChevauchement().count() > 0);
    	return result;
    }
    
    /**
     * Liste des occupations chevauchant cette occupation
     * @return
     */
    private NSArray getListAffectationDetailChevauchement() {
    	NSArray list = new NSArray();

     	NSArray affectationDetailList = 
    		EOAffectationDetail.fetchAffectationDetails(editingContext(), 
    				CktlDataBus.newCondition(
    					EOAffectationDetail.TO_POSTE_KEY + "=%@", new NSArray(toPoste())), null);
    	for (int i=0; i<affectationDetailList.count(); i++) {
    		EOAffectationDetail affectationDetailItem = (EOAffectationDetail) affectationDetailList.objectAtIndex(i);
    		if (affectationDetailItem != this) {
    			if (!((dFin() != null && affectationDetailItem.dDebut() != null && DateCtrl.isAfter(affectationDetailItem.dDebut(), dFin())) ||
    					(affectationDetailItem.dFin() != null && dDebut() != null && DateCtrl.isAfter(dDebut(), affectationDetailItem.dFin())))) {
    				list = list.arrayByAddingObject(affectationDetailItem);
    			}
    		}
    	}
    	
    	return  list;
    }

    /**
     * Indique s'il faut afficher un message avertissement
     */
    public boolean hasWarning() {
    	return isChevauchementAutreAffectationDetailMemeAgent();
    }
    
    public String htmlWarnMessage() {
    	StringBuffer buff = new StringBuffer();
    	
    	NSArray listAffectationDetailChevauchement = getListAffectationDetailChevauchement();
    	
    	for (int i=0; i<listAffectationDetailChevauchement.count(); i++) {
    		EOAffectationDetail affectationDetailItem = (EOAffectationDetail) listAffectationDetailChevauchement.objectAtIndex(i);
    		buff.append("&raquo; Occupation ").append(affectationDetailItem.fullDisplay(true)).append("<br/>");
    	}
    	
    	if (buff.length() > 0) {
    		buff.insert(0, "<u>chevauchement(s) avec " + fullDisplay(true) + "</u><br/>");
    	}
    	
    	return buff.toString();
    }

		public static EOAffectationDetail newRecordInContext(
		    EOEditingContext ec, 
		    EOAffectation affectation,
		    EOPoste poste,
		    NSTimestamp debut,
		    NSTimestamp fin,
		    boolean adeDateDiffAffectation
		) {
		  EOAffectationDetail newRecord = newDefaultRecordInContext(ec);
		  newRecord.setAdeDDebut(debut);
		  newRecord.setAdeDFin(fin);
		  newRecord.setAdeDateDiffAffectation(adeDateDiffAffectation ? new Integer(1) : new Integer(0));
		  poste.addToTosAffectationDetailRelationship(newRecord);
		  newRecord.addObjectToBothSidesOfRelationshipWithKey(affectation, "toAffectation");
		  return newRecord;
		}

		private static EOAffectationDetail newDefaultRecordInContext(EOEditingContext ec) {
		  EOAffectationDetail record = new EOAffectationDetail();
		  ec.insertObject(record);
		  return record;
		}
    
}
