package org.cocktail.feve.components.common;
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

import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOReferensCorps;
import org.cocktail.feve.eos.modele.grhum.EOReferensDcp;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.grhum.EOReferensFp;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;


/**
 * Ecran de selection de donnees provenant de la base REFERENS.
 * 
 * @author ctarade
 */
public class CompSelectReferens extends com.webobjects.appserver.WOComponent {

	// bindings
	public boolean showActivites;
	public boolean showCompetences;
	public EOReferensEmplois referensEmploisIn;
	public boolean disabled;
	public boolean refreshOnChangeEmploiType;
	
	// les display groups utilises par l'interface
	public WODisplayGroup referensCorpsDg;
	public WODisplayGroup referensDcpDg;
	public WODisplayGroup referensEmploisDg;
	
	// les selections
	public EOReferensCorps referensCorpsSelected;
	public EOReferensDcp referensDcpSelected;
	public EOReferensFp referensFpSelected;
	public EOReferensEmplois referensEmploisSelected;
	public EOReferensActivites referensActivitesSelected;
	public EOReferensCompetences referensCompetencesSelected;
	
	// items
	public EOReferensCorps referensCorpsItem;
	public EOReferensDcp referensDcpItem;
	public EOReferensFp referensFpItem;
	public EOReferensEmplois referensEmploisItem;
	public EOReferensActivites referensActivitesItem;
	public EOReferensCompetences referensCompetencesItem;
	
	// temoins de rechargement de DG / manip de donnees
	private boolean shouldReloadReferensDcpDg;
	private boolean shouldReloadReferensEmploisDg;
	private boolean isFirstTimeReferensEmploisIn;
	private boolean shouldDoStuffReferensEmploisIn;
	
	public CompSelectReferens(WOContext context) {
		super(context);
		initComponent();
	}
	
	//
	
	private void initComponent() {
		shouldReloadReferensEmploisDg = true;
		shouldReloadReferensDcpDg = true;
		isFirstTimeReferensEmploisIn = true;
	}
	
	// setters utilises par les bindings
	
	/**
	 * On preselectionne l'emploi type cibl� (on ne traite le setter
	 * qu'une seule fois)
	 */
	public void setReferensEmploisIn(EOReferensEmplois value) {
		EOReferensEmplois prevReferensEmploisIn = referensEmploisIn;
		referensEmploisIn = value;
		setReferensEmploisSelected(referensEmploisIn);
		if (referensEmploisIn != null && isFirstTimeReferensEmploisIn) {
			shouldDoStuffReferensEmploisIn = true;
			isFirstTimeReferensEmploisIn = false;
		}
		// forcer le rechargement si l'emploi type est modifie
		if (prevReferensEmploisIn != null && referensEmploisIn != null && !prevReferensEmploisIn.numemploi().equals(referensEmploisIn.numemploi())) {
			shouldDoStuffReferensEmploisIn = true;
			isFirstTimeReferensEmploisIn = false;
		}
	}
	
	// manipulation de donnees
	
	/**
	 * Effectuer le rechargement des DG quand necessaire.
	 */
	public void appendToResponse(WOResponse arg0, WOContext arg1) {
		if (shouldReloadReferensDcpDg) {
			reloadReferensDcpDg();
			shouldReloadReferensDcpDg = false;
		}
		if (shouldDoStuffReferensEmploisIn) {
			doStuffReferensEmploisForced();
			shouldDoStuffReferensEmploisIn = false;
		}
		if (shouldReloadReferensEmploisDg) {
			reloadReferensEmploisDg();
			shouldReloadReferensEmploisDg = false;
		}
		super.appendToResponse(arg0, arg1);
	}
	
	public void setReferensEmploisSelected(EOReferensEmplois value) {
		referensEmploisSelected = value;
	}

	/**
	 * Refetcher le DG <code>referensEmploisDg</code> 
	 */
	private void reloadReferensDcpDg() {
		referensDcpDg.queryBindings().removeAllObjects();
		// qualifier supplementaire pour ne pas afficher les DCP archivees
		if (!showAncienneNomemclature()) {
			for (int i=0; i<EOReferensDcp.LIST_NUMDCP_ARCHIVE.count(); i++) {
				String key = "myNumdcp" + (i<10 ? "0" : "") + Integer.toString(i);
				referensDcpDg.queryBindings().setObjectForKey(EOReferensDcp.LIST_NUMDCP_ARCHIVE.objectAtIndex(i), key);
			}
		}
		referensDcpDg.qualifyDataSource();
	}
	
	/**
	 * Refetcher le DG <code>referensEmploisDg</code> 
	 */
	private void reloadReferensEmploisDg() {
		referensEmploisDg.queryBindings().removeAllObjects();
		if (referensFpSelected != null) {
			referensEmploisDg.queryBindings().setObjectForKey(referensFpSelected, "myReferensFp");
		}
		if (referensDcpSelected != null) {
			referensEmploisDg.queryBindings().setObjectForKey(referensDcpSelected, "myReferensDcp");
		}
		if (referensCorpsSelected != null) {
			referensEmploisDg.queryBindings().setObjectForKey(referensCorpsSelected, "myReferensCorps");
		}
		// qualifier supplementaire pour ne pas afficher les emplois sur DCP archivees
		if (!showAncienneNomemclature()) {
			for (int i=0; i<EOReferensDcp.LIST_NUMDCP_ARCHIVE.count(); i++) {
				String key = "myNumdcp" + (i<10 ? "0" : "") + Integer.toString(i);
				referensEmploisDg.queryBindings().setObjectForKey(EOReferensDcp.LIST_NUMDCP_ARCHIVE.objectAtIndex(i), key);
			}
		}
		referensEmploisDg.qualifyDataSource();
	}
	
	/**
	 * Effectue les selections des champs suite a la preselection
	 * d'un emploi type
	 */
	private void doStuffReferensEmploisForced() {
		// au cas ou la DCP de l'emploi type est archivee
		reloadReferensDcpDg();
		// relister les emplois
		reloadReferensEmploisDg();
		// selection du corps
		referensCorpsSelected = referensEmploisIn.toReferensCorps();
		// selection de la BAP/DCP
		referensDcpSelected = referensEmploisIn.toReferensFp().toReferensDcp();
		// selection de la famille
		referensFpSelected = referensEmploisIn.toReferensFp();
		// et enfin l'emploi
		setReferensEmploisSelected(referensEmploisIn);
	}
		
	/**
	 * Indique si la liste des objets presente doit aussi contenir ceux
	 * de l'ancienne nomenclature. Cela depend de <code>referensEmploisForced</code>
	 */
	private boolean showAncienneNomemclature() {
		return referensEmploisIn != null && referensEmploisIn.isArchive();
	}
	
	
	// override de setters de l'interface our declencher un rechargement du DG des emplois
	
	/**
	 * 
	 */
	public void setReferensFpSelected(EOReferensFp value) {
		if (referensFpSelected != value) {
			shouldReloadReferensEmploisDg = true;
		}
		referensFpSelected = value;
	}
	
	/**
	 * 
	 */
	public void setReferensDcpSelected(EOReferensDcp value) {
		if (referensDcpSelected != value) {
			shouldReloadReferensEmploisDg = true;
		}
		referensDcpSelected = value;
	}
	
	/**
	 * 
	 */
	public void setReferensCorpsSelected(EOReferensCorps value) {
		if (referensCorpsSelected != value) {
			shouldReloadReferensEmploisDg = true;
		}
		referensCorpsSelected = value;
	}
	
	// interface
	
	/**
	 * On ne recharge pas la page si on n'affiche que des emplois
	 */
	public String onChangePopUpEmploi() {
		String onChange = "";
		if (refreshOnChangeEmploiType) {
			onChange = "this.form.submit();";
		}
		return onChange;
	}
	
	// variables sortantes
	
	public EOReferensEmplois getReferensEmploisOut() {
		return referensEmploisSelected;
	}
	
	public EOReferensActivites getReferensActivitesOut() {
		return referensActivitesSelected;
	}
	
	public EOReferensCompetences getReferensCompetencesOut() {
		return referensCompetencesSelected;
	}
	
	// setter bidons silencieux
	
	public void setReferensEmploisOut(EOReferensEmplois value) {
		
	}
	
	public void setReferensActivitesOut(EOReferensActivites value) {
		
	}
	
	public void setReferensCompetencesOut(EOReferensCompetences value) {
		
	}
}