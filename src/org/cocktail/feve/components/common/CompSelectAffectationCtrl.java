package org.cocktail.feve.components.common;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOAffectation;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Controleur du composant CompSelectAffectation
 * 
 * @author ctarade
 */
public class CompSelectAffectationCtrl 
	extends A_ComponentControler {


	/** Liste des affectation */
	public EOAffectation affectationItem;
	public EOAffectation affectationSelected;	
	
	/**
	 * Le choix de la periode des affectations a afficher
	 */
	public final int PERIODE_ANCIENNE = 0;
	public final int PERIODE_ACTUELLE = 1;
	public final int PERIODE_FUTURE 	= 2;
	public int periode;

	/**
	 * Pour la liste des affectation, on indique si on veut
	 * voir les postes ayant deja une occupation (non par defaut)
	 */
	public boolean showAffectationAvecOccupations;
	
	/**
	 * Pour effectuer une recherche sur les affectation gepeto
	 */
	public String nomPrenom;

	/**
	 * Indique si le DG doit etre refetcher. Les valeurs changees de 
	 * <code>periode</code> et de <code>nomPrenom</code> determine
	 * son etat. 
	 */
	private boolean shouldRefreshDgAffectation;	

	/**
	 * La structure concernee
	 */
	private EOStructure recStructure;
	
	/**
	 * @deprecated
	 * @see CompSelectAffectationCtrl#CompSelectAffectationCtrl(Session, EOStructure)
	 * ne pas utiliser
	 * @param session
	 */
	public CompSelectAffectationCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param structure : la structure des affectations a afficher
	 */
	public CompSelectAffectationCtrl(Session session, EOStructure structure) {
		super(session);
		recStructure = structure;
		initCtrl();
	}
	
	/**
	 * 
	 */
	private void initCtrl() {
		// effacer la selection
		affectationSelected = null;
		// periode actuelle par defaut
		setPeriode(PERIODE_ACTUELLE);
	}
	
	/**
	 * Raccourci vers le composant
	 * @return
	 */
	private CompSelectAffectation compSelectAffectation() {
		return (CompSelectAffectation) getComponent();
	}
	
	/**
	 * Surcharge de ce setter, pour mise a jour du dg en consequences
	 */
	public void setPeriode(int value) {
		int prevPeriode = periode;
		periode = value;
		if (prevPeriode != periode) {
			shouldRefreshDgAffectation = true;
		}
	}
	
	/**
	 * Surcharge de ce setter, pour mise a jour du dg en consequences
	 */
	public void setShowAffectationAvecOccupations(boolean value) {
		boolean prevShowAffectationAvecOccupations = showAffectationAvecOccupations;
		showAffectationAvecOccupations = value;
		if (prevShowAffectationAvecOccupations != showAffectationAvecOccupations) {
			shouldRefreshDgAffectation = true;
		}
	}
	
	
	/**
	 * Surcharge du setter pour filter le dg d'apres le nom de l'affecte
	 */
	public void setNomPrenom(String value) {
		// on fait la recherche que si ya du changement
		if (value != nomPrenom)
			shouldRefreshDgAffectation = true;
		nomPrenom = value;
	}
	
	/**
	 * 
	 */
	public void doRefreshDgAffectation() {
		// filtre sur le service
		String strQual = "toStructure = %@";
		// filtre sur la periode
		if (periode == PERIODE_ACTUELLE)
			strQual += " AND dDebAffectation <= %@ AND (dFinAffectation >= %@ or dFinAffectation = nil)";
		else if (periode == PERIODE_ANCIENNE)
			strQual += " AND dFinAffectation < %@";
		else if (periode == PERIODE_FUTURE)
			strQual += " AND dDebAffectation > %@";
		WODisplayGroup dgAffectation = compSelectAffectation().getDgAffectation();		
		EOQualifier qualStrucEtDates = EOQualifier.qualifierWithQualifierFormat(
				strQual, new NSArray(new Object[]{recStructure, DateCtrl.now(), DateCtrl.now()}));	
		// filtre sur le nom
		if (nomPrenom != null)
			nomPrenom = nomPrenom.toUpperCase();
		EOQualifier qualIndividu = EOIndividu.getQualifierForNomOrPrenom(nomPrenom);
		if (qualIndividu != null) {
			dgAffectation.setQualifier(new EOAndQualifier(new NSArray(new EOQualifier[]{
					qualStrucEtDates, qualIndividu})));			
		} else {
			dgAffectation.setQualifier(qualStrucEtDates);
		}
		dgAffectation.qualifyDataSource();	
		
		// filtrer sur l'existance d'occupation O/N => filtrage en m�moire obligatoire
		if (!showAffectationAvecOccupations) {
			NSArray objects = dgAffectation.displayedObjects();
			NSArray filteredObjects = new NSArray(objects);
		
			filteredObjects = new NSArray();
			for (int i=0; i<objects.count(); i++) {
				boolean shouldKeepAffectation = true;
				EOAffectation affectation = (EOAffectation) objects.objectAtIndex(i);
				NSArray affectationDetailList = affectation.tosAffectationDetail();
				for (int j=0; j<affectationDetailList.count() && shouldKeepAffectation; j++) {
					EOAffectationDetail affectationDetail = (EOAffectationDetail) affectationDetailList.objectAtIndex(j);
					// on fait le filtrage selon la periode selectionn�e
					if ((periode == PERIODE_ACTUELLE && affectationDetail.isActuelle()) ||
							(periode == PERIODE_ANCIENNE && affectationDetail.isAncienne()) ||
							(periode == PERIODE_FUTURE && affectationDetail.isFuture())) {
						shouldKeepAffectation = false;
					}
				}
				if (shouldKeepAffectation) {
					filteredObjects = filteredObjects.arrayByAddingObject(affectation);
				}
			}
			
			dgAffectation.setObjectArray(filteredObjects);
		}
		shouldRefreshDgAffectation = false;
	}
	
	/**
	 * Action de selection de l'affectation
	 * @return
	 */
	public WOComponent doSelectAffectation() {
		affectationSelected = affectationItem;
		return null;
	}
	
	
	// getters
	
	/**
	 * 
	 * @return
	 */
	protected final EOAffectation getAffectationSelected() {
		CktlLog.log(this.getClass().getName() + ".getAffectationSelected() " + affectationSelected);
		return affectationSelected;
	}
	/**
	 * 
	 * @return
	 */
	protected final boolean shouldRefreshDgAffectation() {
		return shouldRefreshDgAffectation;
	}


  // boolean interface

  /**
   * 
   */
  public boolean isTheAffectationSelected() {
  	return affectationItem == affectationSelected;
  }

	public final void setAffectationSelected(EOAffectation affectationSelected) {
		CktlLog.log(this.getClass().getName() + ".setAffectationSelected() " + affectationSelected);
		this.affectationSelected = affectationSelected;
	}
}
