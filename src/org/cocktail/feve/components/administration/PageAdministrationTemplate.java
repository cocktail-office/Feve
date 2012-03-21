package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.administration.hierarchie.A_GestionPeriode;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;
import org.cocktail.feve.eos.modele.mangue.EOTplItem;
import org.cocktail.feve.eos.modele.mangue.EOTplItemValeur;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;
import org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

/**
 * Page de gestion de templates (forme des fiches)
 * 
 * @author ctarade
 */
public class PageAdministrationTemplate
		extends A_GestionPeriode {

	// liste des onglets
	public EOTplOnglet tplOngletItem;

	// liste des blocs
	public EOTplBloc tplBlocItem;

	// liste des items
	public EOTplItem tplItemItem;

	// liste des valeurs
	public EOTplItemValeur tplItemValeurItem;

	public PageAdministrationTemplate(WOContext context) {
		super(context);
	}

	/**
	 * La liste des onglets disponibles pour la période en cours
	 * {@link #getPeriodeSelected()}
	 * 
	 * @return
	 */
	public NSArray<EOTplOnglet> getEoTplOngletArray() {
		return session.getEoTplFicheEvaluation().tosOnglet(getPeriodeSelected(), null);
	}

	/**
	 * Liste des blocs de l'onglet {@link #tplOngletItem} disponibles pour la
	 * période en cours {@link #getPeriodeSelected()}
	 * 
	 * @return
	 */
	public NSArray<EOTplBloc> getEoTplBlocArrayForOngletItem() {
		return tplOngletItem.tosTplBlocSortedByPosition(getPeriodeSelected());
	}

	/**
	 * La liste des valeurs associées à l'item {@link #tplItemItem} sur la période
	 * en cours {@link #getPeriodeSelected()}
	 * 
	 * @return
	 */
	public NSArray getTplItemValeurList() {
		NSArray list = null;

		list = tplItemItem.tosTplRepartItemItemValeur(
				getPeriodeSelected().epeDDebut(), getPeriodeSelected().epeDFin());

		list = (NSArray) list.valueForKey(EOTplRepartItemItemValeur.TO_TPL_ITEM_VALEUR_KEY);

		return list;
	}
}