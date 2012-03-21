package org.cocktail.feve.components.administration.droits;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

/**
 * Composante de recherche et de selection d'individu
 * 
 * @author ctarade
 */
public class FieldSearchIndividu
		extends FeveWebComponent {

	/** liste des resultats */
	public NSArray<EOIndividu> individuList;
	public EOIndividu individuItem;
	public EOIndividu individuSelected;

	/** erreur */
	public boolean hasErrorLength;

	//
	public boolean isJustClicked;

	/** indique dans quelles zones de la population chercher */
	public boolean isOnlyPersonnelActuel;

	//
	public final int STR_SEARCH_MIN_SIZE = FeveWebComponent.STR_SEARCH_MIN_SIZE;

	public FieldSearchIndividu(WOContext context) {
		super(context);
		initComponent();
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		isJustClicked = false;
	}

	private void initComponent() {
		setStrSearch(STR_SEARCH_DEFAULT_VALUE);
		individuList = new NSArray<EOIndividu>();
		hasErrorLength = false;
		// on recherche par defaut parmi les personnels
		isOnlyPersonnelActuel = true;
	}

	// navigation

	/**
	 * Effectuer la recherche
	 */
	public WOComponent doSearch() {
		// RAZ message d'erreur
		hasErrorLength = false;
		individuList = new NSArray<EOIndividu>();
		// controle de la taille minimum
		String strSearchBasic = StringCtrl.toBasicString(getStrSearch());
		// faut-il faire la recherche
		if (StringCtrl.isEmpty(getStrSearch()) || StringCtrl.isEmpty(strSearchBasic) || getStrSearch().equals(STR_SEARCH_DEFAULT_VALUE)) {
			// pas de recherche a faire
		} else {
			// controle sur la taille
			if (strSearchBasic.length() >= STR_SEARCH_MIN_SIZE) {
				individuList = EOIndividu.findIndividuForNomOrPrenom(
						ec, getStrSearch(), isOnlyPersonnelActuel);
			} else {
				hasErrorLength = true;
			}
		}
		isJustClicked = true;
		return null;
	}

	/**
	 * RAZ de la recherche
	 * 
	 * @return
	 */
	public WOComponent doClear() {
		hasErrorLength = false;
		setStrSearch(STR_SEARCH_DEFAULT_VALUE);
		individuList = new NSArray<EOIndividu>();
		isJustClicked = true;
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String btnBtnDoClearName() {
		String name = "";

		name = "BtnDoClear_" + hashCode();

		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String btnBtnDoSearchName() {
		String name = "";

		name = "BtnDoSearch_" + hashCode();

		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String btnBtnDoSearchHiddenName() {
		String name = "";

		name = "BtnDoSearchHidden_" + hashCode();

		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String chkPersonnelActuelName() {
		String name = "";

		name = "ChkPersonnelActuel_" + hashCode();

		return name;
	}

	public String tFSearchOnKeyDown() {
		String script = "";

		script = "if(event.keyCode==13){" + btnBtnDoSearchName() + ".click();};";

		return script;
	}

	// getters

	public EOIndividu getIndividu() {
		return (NSArrayCtrl.isEmpty(individuList) ? null : individuSelected);
	}

	/**
	 * Indique si le composant a été utilisé lors du dernier rechargement de la
	 * page (sert à masquer ou pas le composant via css pour les pages qui
	 * l'utilisent)
	 * 
	 * @return
	 */
	public boolean getIsJustClicked() {
		return isJustClicked;
	}

	// setter bidon pour les bindings

	public void setIndividu(EOIndividu value) {

	}

	public void setIsJustClicked(boolean value) {

	}

}