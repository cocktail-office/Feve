package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.app.print.FevePdfBoxCtrl;
import org.cocktail.feve.app.print.PrintConsts;
import org.cocktail.feve.app.print.PrintFicheDePoste;
import org.cocktail.feve.components.common.A_FeveSubMenuPageControled;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOMangueParametres;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * 
 * @author ctarade
 * 
 */
public class CompFicheDePoste
		extends A_FeveSubMenuPageControled {

	// variables entrantes

	private EOFicheDePoste inputLaFicheDePoste;
	private EOFicheDePoste inputLaFicheDePostePrecedente;

	//
	private boolean hasDroitVisualiserOccupants = false;
	public boolean showEditionFicheDePoste = false;
	public boolean showEditionProfilDePoste = false;

	public CompFicheDePoste(WOContext context) {
		super(context);
	}

	// gestion du menu

	private final static String MENU_ITEM_IDENTITE = "Identit&eacute; de l'agent";
	private final static String MENU_ITEM_SERVICE = "Service - Poste";
	private final static String MENU_ITEM_DESCRIPTION = "Description du poste";

	public NSArray getMenuItems() {
		NSMutableArray menuItems = new NSMutableArray();
		// par defaut, on voit les infos d'environement et la description
		if (inputLaFicheDePoste() != null) {
			menuItems = new NSMutableArray(new String[] {
					MENU_ITEM_SERVICE, MENU_ITEM_DESCRIPTION });
			if (session.niveauConnexion() != Session.NIVEAU_DA_FICHE_DE_POSTE) {
				//
				if (hasDroitVisualiserOccupants) {
					menuItems.insertObjectAtIndex(MENU_ITEM_IDENTITE, 0);
					showEditionFicheDePoste = true;
					showEditionProfilDePoste = false;
				} else {
					showEditionFicheDePoste = false;
					showEditionProfilDePoste = true;
				}
				// l'administrateur peut voir les 2
				if (feveUserInfo().isAdmin()) {
					showEditionFicheDePoste = true;
					showEditionProfilDePoste = true;
				}
			}
		}
		return menuItems.immutableClone();
	}

	// setter

	/**
	 * detection du changement du point d'entree -> RAZ
	 */
	public void setInputLaFicheDePoste(EOFicheDePoste value) {
		inputLaFicheDePostePrecedente = inputLaFicheDePoste;
		inputLaFicheDePoste = value;
		if (inputLaFicheDePoste != inputLaFicheDePostePrecedente) {
			setSelectedItemMenu(null);
			// determiner s'il peut voir les occupants de la fiche -->
			// la partie identit� n'est visible que si la personnel connectee
			// le droit de consuler ou de modifier le poste
			hasDroitVisualiserOccupants = (
					feveUserInfo().hasDroitOnCible(EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.POSTE, inputLaFicheDePoste.toPoste(), false) ||
					feveUserInfo().hasDroitOnCible(EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.POSTE, inputLaFicheDePoste.toPoste(), false));
		}

	}

	// IMPRESSIONS

	/**
	 * Impression de la fiche de poste avec les occupants
	 */
	public class PdfBoxFicheDePosteCtrl extends FevePdfBoxCtrl {

		public PdfBoxFicheDePosteCtrl(Class aGenericSixPrintClass, Session aSession) {
			super(aGenericSixPrintClass, aSession);
		}

		public NSDictionary<String, Object> buildDico() {
			NSMutableDictionary<String, Object> dico = new NSMutableDictionary<String, Object>();
			dico.setObjectForKey(inputLaFicheDePoste, "fiche");
			dico.setObjectForKey(PrintConsts.ENDING_MESSAGE_FICHE_DE_POSTE, PrintConsts.DICO_KEY_ENDING_MESSAGE);
			dico.setObjectForKey(Boolean.TRUE, PrintConsts.XML_KEY_SHOW_INFOS_PERSONNELLES);
			dico.setObjectForKey(
					app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL),
					PrintConsts.XML_KEY_BLOC_ACTI_COMP_VERTICAL);
			return dico;
		}

		public String fileName() {
			String fileName = "FicheDePoste_";
			EOAffectationDetail refAffDet = inputLaFicheDePoste.toAffectationDetailActuelle();
			if (refAffDet != null) {
				fileName += refAffDet.toAffectation().toIndividu().nomPrenom();
			}
			fileName += inputLaFicheDePoste.display();
			fileName = StringCtrl.toBasicString(fileName);
			fileName = StringCtrl.replace(fileName, "___", "_");
			fileName = StringCtrl.replace(fileName, "__", "_");
			if (fileName.endsWith("_")) {
				fileName = fileName.substring(0, fileName.length() - 1);
			}
			return fileName;
		}
	}

	/** */
	public PdfBoxFicheDePosteCtrl ctrlFicheDePoste() {
		return new PdfBoxFicheDePosteCtrl(PrintFicheDePoste.class, session);
	}

	/**
	 * Impression de la fiche de poste sans les occupants
	 */
	public class PdfBoxFicheDePosteSansOccupantsCtrl extends FevePdfBoxCtrl {

		public PdfBoxFicheDePosteSansOccupantsCtrl(Class aGenericSixPrintClass, Session aSession) {
			super(aGenericSixPrintClass, aSession);
		}

		public NSDictionary<String, Object> buildDico() {
			NSMutableDictionary<String, Object> dico = new NSMutableDictionary<String, Object>();
			dico.setObjectForKey(inputLaFicheDePoste, "fiche");
			dico.setObjectForKey(PrintConsts.ENDING_MESSAGE_FICHE_DE_POSTE, PrintConsts.DICO_KEY_ENDING_MESSAGE);
			dico.setObjectForKey(Boolean.FALSE, PrintConsts.XML_KEY_SHOW_INFOS_PERSONNELLES);
			dico.setObjectForKey(
					app.getBooleanParamValueForKey(EOMangueParametres.KEY_FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL),
					PrintConsts.XML_KEY_BLOC_ACTI_COMP_VERTICAL);
			return dico;
		}

		public String fileName() {
			String fileName = "ProfilDePoste_";
			fileName += inputLaFicheDePoste.display();
			return StringCtrl.toBasicString(fileName);
		}
	}

	/** */
	public PdfBoxFicheDePosteSansOccupantsCtrl ctrlFicheDePosteSansOccupants() {
		return new PdfBoxFicheDePosteSansOccupantsCtrl(PrintFicheDePoste.class, session);
	}

	// getters

	public EOFicheDePoste inputLaFicheDePoste() {
		return inputLaFicheDePoste;
	}

	// boolean interface
	public boolean isPageFdp1() {
		return getSelectedItemMenu().equals(MENU_ITEM_IDENTITE);
	}

	public boolean isPageFdp2() {
		return getSelectedItemMenu().equals(MENU_ITEM_SERVICE);
	}

	public boolean isPageFdp3() {
		return getSelectedItemMenu().equals(MENU_ITEM_DESCRIPTION);
	}

	public boolean isInputFicheDePosteExiste() {
		return inputLaFicheDePoste() != null;
	}

}