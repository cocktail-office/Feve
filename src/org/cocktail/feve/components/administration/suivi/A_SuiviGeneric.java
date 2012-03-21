package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Rassemble tous les traitements & valeurs communs à une page de suivi
 * 
 * @author ctarade
 */
public abstract class A_SuiviGeneric
		extends FeveWebComponent {

	// sorties CSV

	/**
	 * encodage du fichier cvs genere pour avoir un affichage correct des accents
	 * sous windows
	 */
	protected final static String CSV_ENCODING = "ISO-8859-1";

	protected final static String CSV_COLUMN_SEPARATOR = "\t";
	protected final static String CSV_NEW_LINE = "\n";

	// periodes d'evaluation
	public WODisplayGroup periodeDg;
	public EOEvaluationPeriode periodeItem;
	public EOEvaluationPeriode periodeSelected;

	// composantes
	public WODisplayGroup composanteDg;
	public EOStructure composanteItem;
	public EOStructure composanteSelected;

	// services
	public WODisplayGroup serviceDg;
	public EOVService serviceItem;
	public EOVService serviceSelected;

	// nature du poste (enseignant / non enseignant)
	public NSArray<String> posteNatureList = EOPoste.POSTE_NATURE_ARRAY;
	public String posteNatureItem;
	public String posteNatureSelected;

	// types de postes a afficher
	public NSArray<String> posteTypeList = EOPoste.POSTE_TYPE_ARRAY;
	public String posteTypeItem;
	public String posteTypeSelected;

	/**
	 * On doit passer par un qualifier a part car en faisant setQualifier sur le
	 * displaygroup, ca marche pas ...
	 */
	private EOQualifier posteTypeQual;
	private EOQualifier posteNatureQual;

	/**
	 * Temoin s'il faut recharger le dg
	 */
	protected boolean shouldRefreshMainDg = false;

	public A_SuiviGeneric(WOContext context) {
		super(context);
		initComponent();
	}

	/**
	 * Surcharge de la methode pour rafraichir le display group si besoin
	 */
	public void appendToResponse(WOResponse response, WOContext context) {
		if (shouldRefreshMainDg) {
			doRefreshMainDg();
			shouldRefreshMainDg = false;
		}
		super.appendToResponse(response, context);
	}

	/**
	 * Methode a definir pour fetcher le display group
	 */
	protected abstract void doRefreshMainDg();

	/**
	 * Le DG principal
	 * 
	 * @return
	 */
	protected abstract WODisplayGroup mainDg();

	/**
	 * Demande explicite de rafraichissement du DG (bouton rafraichir)
	 */
	public WOComponent doRefresh() {
		notifierShouldRefresh();
		return null;
	}

	/**
	 * Initialisation du composant
	 */
	private void initComponent() {
		// selection du type "en cours" par defaut
		setPosteTypeSelected(EOPoste.POSTE_TYPE_EN_COURS);
		// on indique qu'il ne faut pas tout de suite fetcher
		shouldRefreshMainDg = false;
	}

	/**
	 * Pour le fetch sur les poste, selon l'entite du DG utilise, on va construire
	 * differement le qualifier
	 * 
	 * @see #setPosteTypeSelected(String)
	 * @return
	 */
	protected abstract String prefixEntityDgToPoste();

	/**
	 * Surcharge du setter pour la selection du type de poste afin de recharger le
	 * display group si changement.
	 */
	public void setPosteTypeSelected(String value) {
		if (value != null && value != posteTypeSelected) {
			// maintenant on complete le queryBindings
			String prefix = (!StringCtrl.isEmpty(prefixEntityDgToPoste()) ? prefixEntityDgToPoste() : "");
			setPosteTypeQual(EOPoste.getPosteTypeQualifier(prefix, value));
			notifierShouldRefresh();
		}
		posteTypeSelected = value;
	}

	/**
	 * Surcharge du setter pour la selection du type de poste afin de recharger le
	 * display group si changement.
	 */
	public void setPosteNatureSelected(String value) {
		if (value != null && value != posteNatureSelected) {
			// maintenant on complete le queryBindings
			String prefix = (!StringCtrl.isEmpty(prefixEntityDgToPoste()) ? prefixEntityDgToPoste() : "");
			setPosteNatureQual(EOPoste.getPosteNatureQualifier(prefix, value));
			notifierShouldRefresh();
		}
		posteNatureSelected = value;
	}

	/**
	 * Lors de la modification du service, on va recharger la liste
	 * 
	 * @param value
	 */
	public void setServiceSelected(EOVService value) {
		serviceSelected = value;
		if (serviceSelected == null) {
			mainDg().queryBindings().removeObjectForKey("cStructure");
		} else {
			mainDg().queryBindings().setObjectForKey(
					serviceSelected.toStructure().cStructure(), "cStructure");
		}
		notifierShouldRefresh();
	}

	/**
	 * Lors de la modification de la composante, on va recharger la liste
	 * 
	 * @param value
	 */
	public void setComposanteSelected(EOStructure value) {
		composanteSelected = value;
		if (composanteSelected == null) {
			mainDg().queryBindings().removeObjectForKey("cStructureComposante");
		} else {
			mainDg().queryBindings().setObjectForKey(
					composanteSelected.cStructure(), "cStructureComposante");
		}
		notifierShouldRefresh();
	}

	public final EOQualifier getPosteTypeQual() {
		return posteTypeQual;
	}

	private final void setPosteTypeQual(EOQualifier posteTypeQual) {
		this.posteTypeQual = posteTypeQual;
	}

	// classement
	private String sortStringSelected;
	private int sortOrder;

	/**
	 * 
	 * @param sort
	 */
	protected void faireClassement(String sort) {
		// si c'est le même classement alors on fait une inversion
		if (sortStringSelected != null && sort.equals(sortStringSelected)) {
			if (sortOrder == CktlSort.Ascending) {
				sortOrder = CktlSort.Descending;
			} else {
				sortOrder = CktlSort.Ascending;
			}
		} else {
			sortOrder = CktlSort.Ascending;
		}
		//
		sortStringSelected = sort;
		// forcer le rafraichissement de la liste
		doApresClassement();
	}

	public final int getSortOrder() {
		return sortOrder;
	}

	public final String getSortStringSelected() {
		return sortStringSelected;
	}

	public abstract void doApresClassement();

	public final EOQualifier getPosteNatureQual() {
		return posteNatureQual;
	}

	public final void setPosteNatureQual(EOQualifier posteNatureQual) {
		this.posteNatureQual = posteNatureQual;
	}

	/**
	 * 
	 */
	public void notifierShouldRefresh() {
		shouldRefreshMainDg = true;
	}

}
