package org.cocktail.feve.components.common;

import org.cocktail.feve.utils.FeveStringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlWebComponent;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOText;

import er.ajax.CktlAjaxUtils;

/**
 * Champ HTML de type textarea avec controle de la taille maximum
 * 
 * @author ctarade
 * 
 */
public class WOTextMaxSize extends CktlWebComponent {

	public String value;
	public boolean disabled;
	public Integer maxSize;

	private final static String PATH_JS = "js/TextAreaSizeControler.js";
	private final static String CSS_CLASS_NO_ERROR = "feveTextarea";
	private final static String CSS_CLASS_ERROR = "feveTextareaError";
	// la taille maximum par defaut si non renseigné (c'est le max pour SIX pour
	// pas qu'il plante ...)
	private final static int DEFAULT_MAX_SIZE = 1200;

	public WOTextMaxSize(WOContext context) {
		super(context);
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		// ajout js pour limiter le nombre de caractères des textarea
		// addLocalJScript(response, PATH_JS);
		CktlAjaxUtils.addScriptResourceInHead(context, response, "Feve", PATH_JS);
	}

	/**
	 * L'instruction JS bindee sur les actions OnKeyDown, onKeyUp ... du textarea
	 * 
	 * @return
	 */
	public String getOnKeyEvent() {
		String onKeyDown = "";
		onKeyDown = "textCounter('" +
				getTextId() + "'," +
				(maxSize != null ? maxSize.intValue() : DEFAULT_MAX_SIZE) + ",'" +
				getSpanId() + "','" +
				CSS_CLASS_NO_ERROR + "','" +
				CSS_CLASS_ERROR + "');";
		return onKeyDown;
	}

	/**
	 * Un identifiant unique pour le composant
	 * 
	 * @return
	 */
	private String getId() {
		return "WOTextMaxSize_" + hashCode();
	}

	/**
	 * Un identifiant unique pour le {@link WOText}
	 * 
	 * @return
	 */
	public String getTextId() {
		return "WOText_" + getId();
	}

	/**
	 * Un identifiant unique pour le span contenant l'info
	 * 
	 * @return
	 */
	public String getSpanId() {
		return "Span_" + getId();
	}

	/**
	 * La classe par défaut du composant
	 * 
	 * @return
	 */
	public String getDefaultCssClass() {
		return CSS_CLASS_NO_ERROR;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String aValue) {
		// nettoyage des caractères provenant de word (oe ...)
		value = FeveStringCtrl.cleanWordSpecs(aValue);
	}
}