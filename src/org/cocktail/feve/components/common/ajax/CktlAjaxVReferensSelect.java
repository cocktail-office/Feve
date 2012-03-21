/*
 * Copyright COCKTAIL (www.cockta​il.org), 1995, 2010 This software 
 * is governed by the CeCILL license under French law and abiding by the
 * rules of distribution of free software. You can use, modify and/or 
 * redistribute the software under the terms of the CeCILL license as 
 * circulated by CEA, CNRS and INRIA at the following URL 
 * "http://www.cecill.info". 
 * As a counterpart to the access to the source code and rights to copy, modify 
 * and redistribute granted by the license, users are provided only with a 
 * limited warranty and the software's author, the holder of the economic 
 * rights, and the successive licensors have only limited liability. In this 
 * respect, the user's attention is drawn to the risks associated with loading,
 * using, modifying and/or developing or reproducing the software by the user 
 * in light of its specific status of free software, that may mean that it
 * is complicated to manipulate, and that also therefore means that it is 
 * reserved for developers and experienced professionals having in-depth
 * computer knowledge. Users are therefore encouraged to load and test the 
 * software's suitability as regards their requirements in conditions enabling
 * the security of their systems and/or data to be ensured and, more generally, 
 * to use and operate it in the same conditions as regards security. The
 * fact that you are presently reading this means that you have had knowledge 
 * of the CeCILL license and that you accept its terms.
 */
package org.cocktail.feve.components.common.ajax;

import org.cocktail.feve.eos.modele.grhum.EOVReferens;
import org.cocktail.fwkcktlajaxwebext.serveur.component.CktlAjaxWindow;
import org.cocktail.fwkcktlpersonneguiajax.serveur.components.ATreeViewComponent;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

/**
 * Composant de sélection d'un referens à l'aide d'une fenêtre modale contenant l'arbre des referenss.
 * 
 * @see CktlAjaxreferensTreeView
 * @binding id l'id du composant (obligatoire)
 * @binding utilisateurPersId l'id de la personne effectuant l'exploration de referenss (obligatoire)
 * @binding value la valeur saisie dans le champ texte (obligatoire)
 * @binding editingContext l'editingContext dans lequel seront fetchés les referenss
 * @binding selection mis à jour avec le referens sélectionné
 * @binding treeViewTitle
 * @binding treeLeafImageFramework
 * @binding treeLeafImage
 * @binding treeCollapsedImageFramework
 * @binding treeCollapsedImage
 * @binding treeExpandedImage
 * @binding treeExpandedImageFramework
 * @binding treeViewWidth
 * @binding treeViewHeight
 * @binding treeViewClassName
 * @binding treeQualifier Facultatif. Specifie le qualifier a appliquer pour recuperer les enfants d'un noeud. Ce qualifier doit être applicable aux
 *          objets EOStructure. Si non null, le qualifier se rajoute a l'eventuel filtre selectionne par l'utilisateur
 * @binding treeRootObject Facultatif. Specifie la racine de l'arbre.
 * @binding userFiltersDictionary Facultatif. Specifie un NSDictionary de paires "Libelle filtre"/Qualifier. Les Libelles apparaissent dans une liste
 *          deroulante, le qualifier correspondant au libelle selectionne est ensuite applique au treeView. Les qualifier doivent êre applicable aux
 *          objets EOStructure.
 * @binding textFieldSize Facultatif. Specifie la taille du champ (en colonnes)
 * @binding userFiltersFormID ID de l'objet FORM contenant le composant (necessaire si vous specifiez des filtres utilisateurs)
 * @binding updateContainerID Facultatif. Specifie un container à mettre à jour qd la fenetre se ferme.
 * @binding treeExclusions Facultatif. Permet de specifier un NSArray d'objets a exclure de l'affichage de l'arbre.
 * @binding displayDeleteButton Facultatif.Specifie si le bouton supprimer doit etre affiche. Par defaut a true.
 * @binding supprimerOnComplete Facultatif. Specifie une fonction JavaScript a executer après la suppression.
 * @binding confirmBeforeSelection Facultatif. Specifie s'il faut demander une confirmation avent la selection.
 * @binding onSuccessSelect Facultatif. Script a executer une fois le referens selectionné (par défaut, fermeture de la fenêtre de sélection).
 * @binding showField Facultatif. Permet d'afficher ou non le champ texte qui affiche le referens selectionné. Par defaut à True.
 */
public class CktlAjaxVReferensSelect extends ATreeViewComponent {

	private static final long serialVersionUID = 5431261586636009005L;

	public static final String VALUE_BDG = "value";

	/** Facultatif. Binding pour specifier la taille du champ (en colonnes). */
	public static final String TEXT_FIELD_SIZE_BDG = "textFieldSize";

	/** Facultatif. Binding pour specifier un container a mettre a jour qd la fenetre se ferme. */
	public static final String UPDATE_CONTAINER_ID_BDG = "updateContainerID";

	/** Facultatif. Binding pour specifier si le bouton supprimer doit etre affiche. Par defaut a true. */
	public static final String DISPLAY_DELETE_BUTTON_BDG = "displayDeleteButton";

	/** Facultatif. Binding pour specifier une fonction JavaScript a executer apres la suppression. */
	public static final String SUPPRIMER_ON_COMPLETE_BDG = "supprimerOnComplete";

	private static final String ON_SUCCESS_BDG = "onSuccessSelect";

	public static final String SELECTION_BDG = "selection";

	/** Affiche le champ texte */
	public static final String BINDING_showField = "showField";

	private Boolean resetTree = Boolean.FALSE;

	private String containerReferensSelectionneId;

	public CktlAjaxVReferensSelect(WOContext context) {
		super(context);
	}

	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
	}

	public void setSelection(EOVReferens selection) {
		setValueForBinding(selection, CktlAjaxVReferensSelect.SELECTION_BDG);
	}

	public String onSuccessRechercher() {
		String onSuccessRechercher = null;
		onSuccessRechercher = "function () {openWinreferens('";
		onSuccessRechercher += getComponentId() + "', '";
		if (valueForBinding(TREE_VIEW_TITLE_BDG) != null) {
			onSuccessRechercher += valueForBinding(TREE_VIEW_TITLE_BDG) + "', true";
		}
		else {
			onSuccessRechercher += "Choisir une referens', true";
		}

		if (valueForBinding(TREE_VIEW_WIDTH_BDG) != null) {
			onSuccessRechercher += ", " + valueForBinding(TREE_VIEW_WIDTH_BDG);
		}
		else {
			onSuccessRechercher += ", null";
		}
		if (valueForBinding(TREE_VIEW_HEIGHT_BDG) != null) {
			onSuccessRechercher += ", " + valueForBinding(TREE_VIEW_HEIGHT_BDG);
		}
		else {
			onSuccessRechercher += ", null";
		}
		//	 if (valueForBinding(TREE_VIEW_CLASS_NAME_BDG) != null) {
		//	 onSuccessRechercher += ", '" + valueForBinding(TREE_VIEW_CLASS_NAME_BDG) + "'";
		//	 }
		//else {
		onSuccessRechercher += ", null";
		//}
		if (valueForBinding(UPDATE_CONTAINER_ID_BDG) != null) {
			onSuccessRechercher += ", '" + valueForBinding(UPDATE_CONTAINER_ID_BDG) + "'";
		}
		else {
			onSuccessRechercher += ", null";
		}
		onSuccessRechercher += ");}";

		return onSuccessRechercher;
	}

	public WOActionResults onSuccessSelect() {
		if (hasBinding(ON_SUCCESS_BDG)) {
			valueForBinding(ON_SUCCESS_BDG);
		}
		CktlAjaxWindow.close(context(), referensTreeViewId());
		return null;
	}

	public String containerreferensSelectionneId() {
	    if (showField() != null && showField())
	        return getComponentId() + "_selection";
	    else
	        return null;
	}

	/**
	 * Callback appelé lorsque la modal dialog se ferme. On reset le treeview pour le réafficher de zéro lors de la prochaine ouverture.
	 */
	public void onClose() {
		setResetTree(true);
		setIsTreeViewOpened(Boolean.FALSE);
	}

	public WOActionResults supprimerSelection() {
		setSelection(null);
		return null;
	}

	public String containerOnCloseID() {
		return getComponentId() + "_containerOnClose";
	}

	public String referensTreeViewId() {
		return getComponentId() + "_referensTreeView";
	}

	public Integer textFieldSize() {
		if (hasBinding(TEXT_FIELD_SIZE_BDG)) {
			return (Integer) valueForBinding(TEXT_FIELD_SIZE_BDG);
		}
		return Integer.valueOf(50);
	}

	public Boolean displayDeleteButton() {
		if (hasBinding(DISPLAY_DELETE_BUTTON_BDG)) {
			return (Boolean) valueForBinding(DISPLAY_DELETE_BUTTON_BDG);
		}
		return Boolean.TRUE;
	}

	public String supprimerOnComplete() {
		if (hasBinding(SUPPRIMER_ON_COMPLETE_BDG)) {
			return (String) valueForBinding(SUPPRIMER_ON_COMPLETE_BDG);
		}
		return null;
	}

	public Boolean getResetTree() {
		return resetTree;
	}

	public void setResetTree(Boolean resetTree) {
		this.resetTree = resetTree;
	}

	public Boolean showField() {
		if (hasBinding(BINDING_showField)) {
			return (Boolean) valueForBinding(BINDING_showField);
		}
		return Boolean.TRUE;
	}

	/**
	 * @return the containerReferensSelectionneId
	 */
	public String containerReferensSelectionneId() {
		return containerReferensSelectionneId;
	}

	/**
	 * @param containerReferensSelectionneId the containerReferensSelectionneId to set
	 */
	public void setContainerReferensSelectionneId(
			String containerReferensSelectionneId) {
		this.containerReferensSelectionneId = containerReferensSelectionneId;
	}
}