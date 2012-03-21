package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Definition d'un objet qui est positionné par 
 * rapport a d'autres objets du meme type
 * 
 * @author ctarade
 */
public abstract class A_CanChangePosition
	extends A_FeveCktlRecord {
	
	/**
	 * La cle indique la position de l'objet
	 */
	public abstract String positionKey();

	/**
	 * La liste des enregistrements autres
	 * @return
	 */
	public abstract NSArray othersRecords();
	
	/**
	 * Remonter
	 */
	public void up() {
		// la position actuelle
		int position = intForKey(positionKey());
	// on ne remonte pas si c'est le premier element
    if (position != 0) {
    	// retrouver l'element precedent
    	NSArray recsPrev = EOQualifier.filteredArrayWithQualifier(othersRecords(),
    			CktlDataBus.newCondition(positionKey() + "=" + (position-1)));
    	if (recsPrev.count() == 1) {
    		A_CanChangePosition recPrev = (A_CanChangePosition) recsPrev.objectAtIndex(0);
    		recPrev.takeValueForKey(new Integer(position), positionKey());
    		this.takeValueForKey(new Integer(position-1), positionKey());
    	}
    }
	}
	
	/**
	 * Redescendre
	 */
	public void down() {
		// la position actuelle
		int position = intForKey(positionKey());
		// on ne descend pas si c'est le dernier element (on cherche les elements suivants)
  	NSArray recsNext = EOQualifier.filteredArrayWithQualifier(othersRecords(),
  			CktlDataBus.newCondition(positionKey() + ">" + position));
		if (recsNext.count() > 0) {
    	// recuperer l'element suivant
			recsNext = EOQualifier.filteredArrayWithQualifier(othersRecords(),
    			CktlDataBus.newCondition(positionKey() + "=" + (position+1)));
    	if (recsNext.count() == 1) {
    		A_CanChangePosition recNext = (A_CanChangePosition) recsNext.objectAtIndex(0);
    		recNext.takeValueForKey(new Integer(position), positionKey());
    		this.takeValueForKey(new Integer(position+1), positionKey());
    	}
    }
	}
}
