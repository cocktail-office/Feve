package org.cocktail.feve.components.common.ajax;

import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.fwkcktlpersonneguiajax.serveur.components.ATreeViewComponent;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXQ;

/**
 * Composant de sélection d'un formation à l'aide d'un arbre et d'un filtre sur cet arbre.
 * 
 * @binding id l'id du composant (obligatoire)
 * @binding utilisateurPersId l'id de la personne effectuant l'exploration de formations (obligatoire)
 * @binding editingContext l'editingContext dans lequel seront fetchés les formations
 * @binding selection mis à jour avec le formation sélectionné
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
 *          deroulante, le qualifier correspondant au libelle selectionne est ensuite applique au treeView. Les qualifier doivent être applicable aux
 *          objets EOStructure.
 * @binding textFieldSize Facultatif. Specifie la taille du champ (en colonnes)
 * @binding userFiltersFormID ID de l'objet FORM contenant le composant (necessaire si vous specifiez des filtres utilisateurs)
 * @binding filtreTexte boolean indiquant si un champ de filtre texte est affiché pour filtrer les nodes de l'arbre
 * @binding treeExclusions Facultatif. Permet de specifier un NSArray d'objets a exclure de l'affichage de l'arbre.
 * @binding displayDeleteButton Facultatif.Specifie si le bouton supprimer doit etre affiche. Par defaut a true.
 * @binding confirmBeforeSelection Facultatif. Specifie s'il faut demander une confirmation avent la selection.
 * @binding reset Facultatif. Spécifie si l'arbre doit être reseté
 * @binding cssSelectedFormation Facultatif. Spécifie la classe css à appliquer sur le gorupe sélectionné
 * @binding ctrl Controleur eventuel, qui doit heriter de CktlAjaxFormationTreeViewCtrl
 */
public class CktlAjaxFormationTreeView extends ATreeViewComponent {

	private static final long serialVersionUID = 6310181028611897457L;
	/**
	 * Facultatif. Binding pour specifier un NSDictionary de paires "Libelle filtre"/Qualifier. Les Libelles apparaissent dans une liste deroulante,
	 * le qualifier correspondant au libelle selectionne est ensuite applique au treeView. Les qualifier doivent √™re applicable aux objets
	 * EOStrcuture.
	 */
	public static final String USER_FILTERS_DICTIONARY_BDG = "userFiltersDictionary";
	/** Facultatif. Binding pour specifier s'il faut demander une confirmation avent la selection. */
	public static final String CONFIRM_BEFORE_SELECTION_BDG = "confirmBeforeSelection";
	/** Facultatif. Binding sp√©cifiant l'action a exécuter après sélection d'un formation */
	public static final String ON_SUCCESS_SELECT_BDG = "onSuccessSelect";
	/** Facultatif. Binding boolean spécifiant si l'arbre doit être reseté */
	public static final String RESET_BDG = "reset";
	/** Facultatif. Binding spécifiant la classe css à appliquer sur le gorupe sélectionné */
	public static final String CSS_SELECTED_GROUPE_BDG = "cssSelectedFormation";
	/** Facultatif. Controleur a utiliser. */
	public static final String CTRL_BDG = "ctrl";

	private NSDictionary filtres;
	private String unFiltre, unFiltreSelectionne;
	private EOQualifier userFiltreQualifier;
	private CktlAjaxFormationTreeViewCtrl ctrl;
	private EOFormationPersonnel selection;
	private String filtreTexte;

	public CktlAjaxFormationTreeView(WOContext context) {
		super(context);
	}

	@Override
	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		if (ctrl().getWocomponent() == null) {
			ctrl().setWocomponent(this);
		}
		if (hasBinding(RESET_BDG)) {
			if (Boolean.TRUE.equals(valueForBinding(RESET_BDG))) {
				resetTree();
			}
			if (canSetValueForBinding(RESET_BDG)) {
				setValueForBinding(Boolean.FALSE, RESET_BDG);
			}
		}
		super.appendToResponse(response, context);
	}

	private void resetTree() {
		ctrl().rootFormation().setChildren(null);
		EOFormationPersonnel formation = selection();
		if (formation != null)
			ctrl().selectObjectInTree(ctrl().rootFormation(), formation);
	}

	public CktlAjaxFormationTreeViewCtrl ctrl() {
		if (ctrl == null) {
			if (hasBinding(CTRL_BDG)) {
				ctrl = (CktlAjaxFormationTreeViewCtrl) valueForBinding(CTRL_BDG);
			}
			if (ctrl == null) {
				ctrl = new CktlAjaxFormationTreeViewCtrl(this);
			}
		}
		return ctrl;
	}

	public WOActionResults filtrer() {
		String filtre = unFiltreSelectionne();
		if (filtre != null) {
			if (filtre.equalsIgnoreCase("Tous")) {
				setUserFiltreQualifier(null);
			}
			else {
				setUserFiltreQualifier((EOQualifier) filtres.objectForKey(filtre));
			}
//			ctrl().setRootFormation(null);
		}
		else {
			setUserFiltreQualifier(null);
//			ctrl().setRootFormation(null);
		}
		resetTree();
		
		return null;
	}

	public WOActionResults resetFilters() {
	    filtreTexte = null;
	    setUnFiltreSelectionne(null);
	    setUserFiltreQualifier(null);
	    resetTree();
        return null;
    }
	
	public WOActionResults selectionner() {
		ctrl().afficherFormationSelectionne();
		return (WOActionResults) valueForBinding(ON_SUCCESS_SELECT_BDG);
	}

	public String cssClassForSelectedLink() {

		if (hasBinding(CSS_SELECTED_GROUPE_BDG) &&
				selection() != null && selection().equals(ctrl().unFormation().object())) {
			return (String) valueForBinding(CSS_SELECTED_GROUPE_BDG);
		}
		else {
			return String.valueOf("");
		}
	}

	public NSDictionary filtres() {
		if (filtres == null) {
			filtres = (NSDictionary) valueForBinding(USER_FILTERS_DICTIONARY_BDG);
		}
		return filtres;
	}

	public boolean isFiltrageAvailable() {
		boolean isFiltrageAvailable = hasBinding(USER_FILTERS_DICTIONARY_BDG);
		if (isFiltrageAvailable) {
			NSDictionary dicoFiltres = filtres();
			isFiltrageAvailable = dicoFiltres.count() > 0 ? true : false;
		}
		return isFiltrageAvailable;
	}

	public void setUserFiltreQualifier(EOQualifier userFiltreQualifier) {
		this.userFiltreQualifier = userFiltreQualifier;
	}

	public EOQualifier getUserFiltreQualifier() {
		return userFiltreQualifier;
	}

	private EOQualifier getTexteQualifier() {
		if (filtreTexte != null)
			return ERXQ.contains(EOFormationPersonnel.FOR_LIBELLE_DESCENDANCE_KEY, filtreTexte);
		else
			return null;
	}

	public EOQualifier getQualifier() {
		EOQualifier qual = getTreeQualifier();
		if (qual != null) {
			if (getUserFiltreQualifier() != null) {
				qual = new EOAndQualifier(new NSArray(new Object[] {
						qual, getUserFiltreQualifier()
				}));
			}
		}
		else {
			qual = getUserFiltreQualifier();
		}
		EOQualifier qualTexte = getTexteQualifier();
		if (qualTexte != null) {
			qual = ERXQ.and(qual, qualTexte);
		}
		return qual;
	}

	public NSArray getTreeExclusions() {
		return (NSArray) valueForBinding(TREE_EXCLUSIONS_BDG);
	}

	public EOFormationPersonnel treeRootObject() {
		return (EOFormationPersonnel) valueForBinding(TREE_ROOT_OBJECT_BDG);
	}

	public Boolean isFormationSelectionnable() {
		return ctrl().isFormationSelectionnable();
	}

	public String selectionOnClickBefore() {
		if (hasBinding(CONFIRM_BEFORE_SELECTION_BDG)) {
			if (((Boolean) valueForBinding(CONFIRM_BEFORE_SELECTION_BDG)).booleanValue()) {
				return ("confirm('Voulez-vous selectionner la formation " + jsEncode(ctrl.unFormationLibelle()) + " ?')");
			}
		}
		return null;
	}

	public String filtrePopUpID() {
		return getComponentId() + "_filtrePopUp";
	}

	public String unAjaxTreeID() {
		return getComponentId() + "_ajaxTree";
	}

	public String containerAjaxTreeID() {
		return getComponentId() + "_containerAjaxTree";
	}

	/**
	 * @return the unFiltreSelectionne
	 */
	public String unFiltreSelectionne() {
		return unFiltreSelectionne;
	}

	/**
	 * @param unFiltreSelectionne the unFiltreSelectionne to set
	 */
	public void setUnFiltreSelectionne(String unFiltreSelectionne) {
		this.unFiltreSelectionne = unFiltreSelectionne;
	}

	/**
	 * @return the unFiltre
	 */
	public String unFiltre() {
		return unFiltre;
	}

	/**
	 * @param unFiltre the unFiltre to set
	 */
	public void setUnFiltre(String unFiltre) {
		this.unFiltre = unFiltre;
	}

	/**
	 * @param ctrl the ctrl to set
	 */
	public void setCtrl(CktlAjaxFormationTreeViewCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public EOFormationPersonnel selection() {
		if (selection != valueForBinding(SELECTION_BDG))
			selection = (EOFormationPersonnel) valueForBinding(SELECTION_BDG);
		return selection;
	}

	/**
	 * @param selection
	 */
	public void setSelection(EOFormationPersonnel selection) {
		this.selection = selection;
		setValueForBinding(selection, CktlAjaxFormationTreeView.SELECTION_BDG);
	}

	public String getFiltreTexte() {
		return filtreTexte;
	}

	public void setFiltreTexte(String filtreTexte) {
		this.filtreTexte = filtreTexte;
	}

}