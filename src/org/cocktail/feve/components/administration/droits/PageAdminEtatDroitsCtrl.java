package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.mangue.EODroit;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertResponder;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;

/**
 * Controleur de la page d'etat des droits
 * 
 * @author ctarade
 */
public class PageAdminEtatDroitsCtrl
	extends A_ComponentControler {

	// liste de tous les droits donnes dans l'application
	public EODroit droitItem;
	
	// classement en cours
	private NSMutableArray arrayStrSort;
	private final static String STR_SORT_TITULAIRE 			= EODroit.TO_INDIVIDU_KEY + "." + EOIndividu.NOM_USUEL_KEY;
	private final static String STR_SORT_TYPE_ACCES 		= EODroit.TO_TYPE_DROIT_ACCES_KEY + "." + EOTypeDroitAcces.DTA_LIBELLE_KEY;
	private final static String STR_SORT_TYPE_CIBLE 		=	EODroit.TO_TYPE_DROIT_CIBLE_KEY + "." + EOTypeDroitCible.DTC_LIBELLE_KEY;
	private final static String STR_SORT_CIBLE_DISPLAY 	=	EODroit.CIBLE_DISPLAY_KEY;
	
	// indique si une suppression de droit vient d'etre faite
	private boolean isDroitItemJustDeleted;
	
	// le message informant que le droit a ete efface
	public String strMessageDroitItemDeleted;
	
	// filtre sur le titulaire
	private NSArray titulaireList;
	public EOIndividu titulaireItem;
	public EOIndividu titulaireSelected;
	
	// filtre sur le type de droit d'acces
	private NSArray typeDroitAccesList;
	public EOTypeDroitAcces typeDroitAccesItem;
	public EOTypeDroitAcces typeDroitAccesSelected;
	
	// filtre sur le type de droit d'acces
	private NSArray typeDroitCibleList;
	public EOTypeDroitCible typeDroitCibleItem;
	public EOTypeDroitCible typeDroitCibleSelected;
	
	public PageAdminEtatDroitsCtrl(Session session) {
		super(session);
		initCtrl();
	}
	
	/**
	 * Initialisation du controleur
	 */
	private void initCtrl() {
		// classement par defaut
		arrayStrSort = new NSMutableArray(
				/*new String[]{STR_SORT_TITULAIRE, STR_SORT_TYPE_ACCES, STR_SORT_TYPE_CIBLE, STR_SORT_CIBLE_DISPLAY}*/);
	}

	/**
	 * Le classement en cours
	 */
	public NSArray getDroitDgArraySort() {
		StringBuffer sbSort = new StringBuffer();
		for (int i = 0; i < arrayStrSort.count(); i++) {
			sbSort.append(arrayStrSort.objectAtIndex(i));
			if (i<arrayStrSort.count()-1) {
				sbSort.append(",");
			}
		}
		return CktlSort.newSort(sbSort.toString());
	}
	
	
	// methodes de classement
	
	public WOComponent doSortTitulaire() {
		addSort(STR_SORT_TITULAIRE);
		return null;
	}
	
	public WOComponent doSortTypeAcces() {
		addSort(STR_SORT_TYPE_ACCES);
		return null;
	}
	
	public WOComponent doSortTypeCible() {
		addSort(STR_SORT_TYPE_CIBLE);
		return null;
	}
	
	public WOComponent doSortCibleDisplay() {
		addSort(STR_SORT_CIBLE_DISPLAY);
		return null;
	}
	
	/**
	 * Ajouter un classement. 
	 * Positionne les differents classement selon l'ordre
	 * dans lequel ils ont ete appliques
	 * @param sort
	 */
	private void addSort(String sort) {
		if (arrayStrSort.containsObject(sort)) {
			arrayStrSort.removeObject(sort);
		}
		arrayStrSort.insertObjectAtIndex(sort, 0);
	}
	
	
	// actions
	
	/**
	 * Suppression du droit 
	 */
	public WOComponent doDeleteDroit() {
		strMessageDroitItemDeleted = "Droit accord&eacute; &agrave; " +
			droitItem.toIndividu().nomPrenom() + " - " +
			droitItem.toTypeDroitAcces().dtaLibelle() + " " + 
			droitItem.toTypeDroitCible().dtcLibelle() + " sur " +
			droitItem.cibleDisplay() + " <b>supprim&eacute;</b>";
		ec().deleteObject(droitItem);
		try {
    	UtilDb.save(ec(), "");
    	isDroitItemJustDeleted = true;
    } catch (Throwable e) {
    	e.printStackTrace();
    }
		return null;
	}	
	
	/**
	 * Suppression de la liste des droits affich�s
	 */
	public WOComponent doDeleteDisplayedDroit() {
  	if (droitDg().displayedObjects().count() == 0) {
  		return getComponent().parent();
  	} else {
  		// page de confirmation
  		DeleteDisplayedDroitResponder responder = new DeleteDisplayedDroitResponder();
  		StringBuffer sbDroit = new StringBuffer("<ul>");
  		for (int i=0; i<droitDg().displayedObjects().count(); i++) {
				EODroit droitItem = (EODroit) droitDg().displayedObjects().objectAtIndex(i);
				sbDroit.append("<li>");
				sbDroit.append(droitItem.toIndividu().nomPrenom()).append(" (");
				sbDroit.append(droitItem.toTypeDroitAcces().dtaLibelle()).append(", ");
				sbDroit.append(droitItem.toTypeDroitCible().dtcLibelle()).append(") sur ");
				sbDroit.append(droitItem.cibleDisplay()).append("</li>");
			}
  		sbDroit.append("</ul>");
  		return CktlAlertPage.newAlertPageWithResponder(
  				getComponent(), 
  				"Suppression de plusieurs droits",
  				"<center>Confirmation de l'op&eacute;ration:<br><br>"+
  				"Etes vous sur de vouloir supprimer la liste de droits suivante :<br/>" + sbDroit.toString(),
  				"Confirmer la suppression", "Annuler", null, CktlAlertPage.QUESTION, responder);
  	}
  }
  

	/**
	 * La classe interne - l'implementation de AlertResponder pour la suppression
	 * de plusieurs droits en meme temps
	 */	 
	private class DeleteDisplayedDroitResponder implements CktlAlertResponder {

		public DeleteDisplayedDroitResponder() {
	
		}
		
		public WOComponent respondToButton(int buttonNo) {
			switch (buttonNo) {
				case 1: 
					int count = droitDg().displayedObjects().count();
					for (int i=0; i<count; i++) {
						EODroit droitItem = (EODroit) droitDg().displayedObjects().objectAtIndex(i);
						ec().deleteObject(droitItem);
					}
					try {
						UtilDb.save(ec(), "DeleteDisplayedDroitResponder");
						strMessageDroitItemDeleted = count + " droit(s) supprim&eacute;(s)";
						isDroitItemJustDeleted = true;
						droitDg().qualifyDataSource();
					} catch (Throwable e) {
						e.printStackTrace();
					}
					return getComponent().parent();
				case 2: return getComponent().parent();
				default: return null;
			}
		}
	} 
	
	
	// getters / setters
	
	public boolean getIsDroitItemJustDeleted() {
		return isDroitItemJustDeleted;
	}
	
	public void setIsDroitItemJustDeleted(boolean value) {
		isDroitItemJustDeleted = value;
	}

	/**
	 * Pointeur vers le display group des droits
	 * @return
	 */
	private WODisplayGroup droitDg() {
		return ((PageAdminEtatDroits) getComponent()).droitDg;
	}
	
	/**
	 * La liste de tous les titulaires
	 * @return
	 */
	public NSArray getTitulaireList() {
		if (titulaireList == null) {
			// on prend tous ceux qui sont dans le DG en supprimant les doublons
			titulaireList = (NSArray) droitDg().allObjects().valueForKey(EODroit.TO_INDIVIDU_KEY);
			// classer en enlever les doublons
			titulaireList = NSArrayCtrl.removeDuplicate(titulaireList);
			titulaireList = CktlSort.sortedArray(titulaireList, EOIndividu.NOM_USUEL_KEY);
		}
		return titulaireList;
	}
	
	/**
	 * La liste de tous les droits d'acces
	 * @return
	 */
	public NSArray getTypeDroitAccesList() {
		if (typeDroitAccesList == null) {
			// on prend tous ceux qui sont dans le DG en supprimant les doublons
			typeDroitAccesList = (NSArray) droitDg().allObjects().valueForKey(EODroit.TO_TYPE_DROIT_ACCES_KEY);
			// classer en enlever les doublons
			typeDroitAccesList = NSArrayCtrl.removeDuplicate(typeDroitAccesList);
			typeDroitAccesList = CktlSort.sortedArray(typeDroitAccesList, EOTypeDroitAcces.DTA_LIBELLE_KEY);
		}
		return typeDroitAccesList;
	}
	
	/**
	 * La liste de tous les types de cible
	 * @return
	 */
	public NSArray getTypeDroitCibleList() {
		if (typeDroitCibleList == null) {
			// on prend tous ceux qui sont dans le DG en supprimant les doublons
			typeDroitCibleList = (NSArray) droitDg().allObjects().valueForKey(EODroit.TO_TYPE_DROIT_CIBLE_KEY);
			// classer en enlever les doublons
			typeDroitCibleList = NSArrayCtrl.removeDuplicate(typeDroitCibleList);
			typeDroitCibleList = CktlSort.sortedArray(typeDroitCibleList, EOTypeDroitCible.DTC_LIBELLE_KEY);
		}
		return typeDroitCibleList;
	}
	
	
	// filtres
	
	public void setTitulaireSelected(EOIndividu value) {
		titulaireSelected = value;
	}
	
	public void setTypeDroitAccesSelected(EOTypeDroitAcces value) {
		typeDroitAccesSelected = value;
	}
	
	public void setTypeDroitCibleSelected(EOTypeDroitCible value) {
		typeDroitCibleSelected = value;
	}
	
	/**
	 * Le qualifier a appliquer au DG pour appliquer les filtres
	 */
	public EOQualifier getDroitDgQualifier() {
		EOQualifier qual = null;
		
		String strQual = "";
		NSArray args = new NSArray();
		
		if (titulaireSelected != null) {
			strQual += EODroit.TO_INDIVIDU_KEY + "=%@";
			args = args.arrayByAddingObject(titulaireSelected);
		}
		
		if (typeDroitAccesSelected != null) {
			if (strQual.length() > 0) {
				strQual += " and ";
			}
			strQual += EODroit.TO_TYPE_DROIT_ACCES_KEY + "=%@";
			args = args.arrayByAddingObject(typeDroitAccesSelected);
		}
		
		if (typeDroitCibleSelected != null) {
			if (strQual.length() > 0) {
				strQual += " and ";
			}
			strQual += EODroit.TO_TYPE_DROIT_CIBLE_KEY + "=%@";
			args = args.arrayByAddingObject(typeDroitCibleSelected);
		}
		
		if (strQual.length() > 0) {
			qual = CktlDataBus.newCondition(strQual, args);
		}
		
		return qual;
	}
	

	/** encodage du fichier cvs genere pour avoir un affichage correct des accents sous windows */
	private final static String CSV_ENCODING = "ISO-8859-1";
	
	private final static String CSV_COLUMN_SEPARATOR = "\t";
	private final static String CSV_NEW_LINE = "\n";
	
	// edition
	
	public WOResponse printCsv() {
  	CktlDataResponse resp = new CktlDataResponse();
  	StringBuffer sb = new StringBuffer();
  	for (int i = 0; i < droitDg().allObjects().count(); i++) {
  		EODroit droit = (EODroit) droitDg().allObjects().objectAtIndex(i);
  		sb.append(droit.toIndividu().nomPrenom()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(droit.toTypeDroitAcces().dtaLibelle()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(droit.toTypeDroitCible().dtcLibelle()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(droit.cibleDisplay()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(CSV_NEW_LINE);
		}
  	NSData stream = new NSData(sb.toString(), CSV_ENCODING);
  	resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);		
  	resp.setHeader(String.valueOf(stream.length()), "Content-Length");
  	resp.setFileName("export.csv");
  	return resp;
	}
}
