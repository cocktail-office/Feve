package org.cocktail.feve.components.common;

import org.cocktail.feve.app.Application;
import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.feve.app.Session;
import org.cocktail.fwkcktlajaxwebext.serveur.CktlAjaxWOComponent;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

/**
 * classe qui surcharge CktlWebPage permet de rajouter des methodes dans les
 * composants
 */
public class FeveWebComponent
		extends CktlAjaxWOComponent {

	public Application app;
	public Session session;
	public EOEditingContext ec;

	// champ de recherche
	public final static String STR_SEARCH_DEFAULT_VALUE = "Recherche par nom";
	public final String TF_SEARCH_ON_CLICK = "if(this.value=='" + STR_SEARCH_DEFAULT_VALUE + "')this.value=''";
	public final String TF_SEARCH_ON_BLUR = "if(this.value=='')this.value='" + STR_SEARCH_DEFAULT_VALUE + "'";
	public final static int STR_SEARCH_MIN_SIZE = 3;
	private String strSearch;
	// css de l'application
	public final static String CSS_FEVE_FILE_NAME = "css/Feve.css";
	public final static String CLASS_TR_OCCUPATION_NON_ACTUELLE = "feveTROccupationNonActuelle";
	// liste des affectations detail / occupations
	public final static String CLASS_TR_OCCUPATION_ACTUELLE = "feveTROccupationActuelle";
	public final static String CLASS_TR_ON_MOUSE_OVER = "feveTROnMouseOver";
	public final static String CLASS_TR_FERME = "feveTRFerme";
	public final static String CLASS_TR_OUVERT = "feveTROuvert";
	public final static String CLASS_TR_NON_MODIFIABLE = "feveTRNonModifiable";
	public final static String CLASS_TR_MODIFIABLE = "feveTRModifiable";
	public final static String CLASS_TR_VACANT = "feveTRVacant";
	// listes
	public final static String CLASS_TR_OCCUPE = "feveTROccupe";
	public final static String CLASS_NO_HIGHLIGHT = "listboxLine";
	// highlight
	public final static String CLASS_HIGHLIGHT = "listboxLineHighlight";

	public FeveWebComponent(WOContext context) {
		super(context);
		this.initObject();
	}

	/**
	 * Remonter jusqu'a la page contenant globale. Est utilisé pour les composant
	 * ayant besoin d'un caller
	 * 
	 * @return
	 */
	public WOComponent getTopParent() {
		WOComponent topParent = this;
		while (topParent != null && topParent.parent() != null) {
			topParent = topParent.parent();
		}
		return topParent;
	}

	private void initObject() {
		session = (Session) session();
		ec = session.ec();
		app = (Application) application();
		strSearch = STR_SEARCH_DEFAULT_VALUE;
	}

	public WOComponent neFaitRien() {
		return null;
	}

	public WOComponent sauvegarde() {
		try {
			UtilDb.save(ec, "");
		} catch (Exception e) {
			e.printStackTrace();
			session.addSimpleErrorMessage(e.getMessage(), e);
		}
		return neFaitRien();
	}

	public FeveUserInfo feveUserInfo() {
		return session.feveUserInfo();
	}

	// generation automatique du code javascript pour la selection
	// deselection des checkbox de suppression
	private final static String JS_FUNCTION_PREFIX_NAME = "checkAll";
	private final static String JS_FUNCTION_NAME_KEY = "$NAME$";
	private final static String JS_FUNCTION_INSTRUCTION_CHECK_KEY = "$INSTRUCTION_CHECK$";
	private final static String JS_SELECT_DESELECT_PATTERN = "" +
			"function " + JS_FUNCTION_PREFIX_NAME + JS_FUNCTION_NAME_KEY + "(checkbox) {\n" +
			JS_FUNCTION_INSTRUCTION_CHECK_KEY +
			"};";

	/**
	 * Le code javascript pour la selection / deseleciton de toutes les cases a
	 * cocher de la categorie indiquee
	 */
	protected String getJsFunctionSelection(String name, NSArray<String> ids) {
		String js = JS_SELECT_DESELECT_PATTERN;

		js = StringCtrl.replace(js, JS_FUNCTION_NAME_KEY, name);

		String codeCheck = "";

		for (int i = 0; i < ids.count(); i++) {
			codeCheck += "document.getElementById(\"" + ids.objectAtIndex(i) + "\").checked=checkbox.checked;\n";
		}

		js = StringCtrl.replace(js, JS_FUNCTION_INSTRUCTION_CHECK_KEY, codeCheck);

		return js;
	}

	/**
	 * Le code javascript sur la coche principale qui permet de selectionner /
	 * deselectionner tous les objets
	 * 
	 * @param name
	 * @return
	 */
	protected String getOnClickSelection(String name) {
		String js = "js:";

		js += JS_FUNCTION_PREFIX_NAME + name + "(this);";

		return js;
	}

	public final String getStrSearch() {
		return strSearch;
	}

	public final void setStrSearch(String value) {
		if (StringCtrl.isEmpty(value)) {
			value = STR_SEARCH_DEFAULT_VALUE;
		}
		this.strSearch = value;
	}

	@Override
	public boolean synchronizesVariablesWithBindings() {
		// TODO Auto-generated method stub
		return true;
	}
}
