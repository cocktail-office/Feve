package org.cocktail.feve.components.common;

import org.cocktail.fwkcktlwebapp.common.util.RandomKeyGenerator;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

/**
 * Gestion d'une zone de texte avec fonctionnalité de modification / annulation
 * / sauvegarde
 * 
 * @author ctarade
 */
public class ChampLibre
		extends FeveWebComponent {

	// binding valeur du champ libre
	public String value;
	public boolean disabled;
	public String containerIdToUse;
	public String onBeforeModif;

	//
	public boolean isEnCoursDeModification;
	public boolean isAfficherOnBeforeModif;

	public ChampLibre(WOContext context) {
		super(context);
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		if (isAfficherOnBeforeModif) {
			isAfficherOnBeforeModif = false;
		}
	}

	/**
	 *
	 */
	public WOComponent editChampLibre() {
		isEnCoursDeModification = true;

		if (!StringCtrl.isEmpty(onBeforeModif)) {
			isAfficherOnBeforeModif = true;
		}

		return null;
	}

	/**
	 * 
	 */
	public WOComponent doCancel() {
		ec.revert();
		isEnCoursDeModification = false;
		isAfficherOnBeforeModif = false;
		return null;
	}

	/**
	 * 
	 */
	public boolean isDisabledChampLibre() {
		boolean isDisabled = disabled;
		if (isDisabled == false) {
			if (isEnCoursDeModification == false) {
				isDisabled = true;
			}
		}
		return isDisabled;
	}

	/**
	 * 
	 */
	public boolean isShowLnkEditChampLibre() {
		boolean isShow = false;
		if (disabled == false &&
				isEnCoursDeModification == false) {
			isShow = true;
		}
		return isShow;
	}

	/**
	 * 
	 */
	public boolean isShowLnkCancel() {
		boolean isShow = false;
		if (disabled == false &&
				isEnCoursDeModification) {
			isShow = true;
		}
		return isShow;
	}

	/**
	 *
	 */
	public boolean isShowBtnSauvegardeChampLibre() {
		boolean isShow = false;
		if (disabled == false &&
				isEnCoursDeModification) {
			isShow = true;
		}
		return isShow;
	}

	/**
	 * 
	 * @return
	 * @throws
	 */
	public WOComponent doSauvegarde() {
		try {
			super.sauvegarde();
			isEnCoursDeModification = false;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		isAfficherOnBeforeModif = false;
		return null;
	}

	private String containerId;

	/**
	 * Identifiant unique
	 * 
	 * @return
	 */
	public String containerId() {
		if (containerId == null) {
			containerId = (new StringBuilder("ChampLibreContainer_")).append(RandomKeyGenerator.getNewKey(10)).toString();
		}
		return containerId;
	}

	/**
	 * Le container associé aux boutons : si la varaible {@link #containerIdToUse}
	 * est non vide, alors on la prends, sinon {@link #containerId()};
	 * 
	 * @return
	 */
	public String btnContainerId() {
		String btnContainerId = null;

		if (!StringCtrl.isEmpty(containerIdToUse)) {
			btnContainerId = containerIdToUse;
		} else {
			btnContainerId = containerId();
		}

		return btnContainerId;
	}

	// binding uniquement en lecture seule

	public boolean isEnCoursDeModif;

	public final boolean isEnCoursDeModif() {
		return isEnCoursDeModification;
	}

	public final void setEnCoursDeModif(boolean isEnCoursDeModif) {
		this.isEnCoursDeModif = isEnCoursDeModif;
	}
}