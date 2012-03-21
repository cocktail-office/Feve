package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertResponder;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver._private.WOCheckBox;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.eof.ERXQ;

/**
 * Controleur du composant {@link CompFicheDePosteList}
 * 
 * @author ctarade
 */
public class CompFicheDePosteListCtrl
		extends A_ComponentControlerAndFilArianeNode
		implements I_CallingCompFicheDePosteAdd, I_CallingCompFicheDePosteUpdate, I_CallingCompFicheDePosteDuplication {

	/** binding d'entree : liste des fiches de poste */
	public NSArray<EOFicheDePoste> ficheDePosteList;

	/** binding d'entree : le message a afficher si pas de poste a afficher */
	public String messageEmptyList;

	/** binding d'entree : indique s'il faut afficher le nom de l'occupant */
	public boolean showOccupant;

	/**
	 * binding d'entree : indique s'il faut afficher les boutons de suppression de
	 * fiche
	 */
	public boolean showBtnDelete;

	/**
	 * binding d'entree : indique s'il faut afficher les boutons de modif des meta
	 * données de fiche
	 */
	public boolean showBtnUpdate;

	/**
	 * binding d'entree : indique s'il faut afficher la ligne d'ajout d'une
	 * nouvelle fiche
	 */
	public boolean showBtnAdd;

	/** binding d'entree : indique s'il faut afficher le bouton de copie de fiche */
	public boolean showBtnCopier;

	/** Indique s'il en mode ajout de nouvelle fiche */
	public boolean isAdding;

	/**
	 * Indique s'il faut sauvegarder les modifications lors de l'evenement
	 * "onClick" des {@link WOCheckBox}
	 */
	public boolean shouldSaveOnClick;

	/** le controleur pour l'ajout d'une nouvelle fiche de poste */
	public CompFicheDePosteAddCtrl compFicheDePosteAddCtrl;

	/** Indique s'il en mode modification de meta donnee de fiche */
	public boolean isUpdating;

	/** le controleur pour l'ajout d'une nouvelle fiche de poste */
	public CompFicheDePosteUpdateCtrl compFicheDePosteUpdateCtrl;

	/** Indique s'il en mode copie de fiche de poste vers un autre */
	public boolean isDuplicationFiche;

	/** le controleur pour la copie d'une fiche de poste */
	public CompFicheDePosteDuplicationCtrl compFicheDePosteDuplicationCtrl;

	/**
	 * binding d'entree : le poste surlequel créer les fiches si
	 * {@link #showBtnAdd} est a <code>true</code>
	 */
	public EOPoste poste;

	public EOFicheDePoste ficheDePosteItem;
	public EOFicheDePoste ficheDePosteSelected;

	//
	private EOAffectationDetail recAffectationDetail;
	public boolean isOccupationCourante;

	//
	public boolean canModifItemFicheDePoste;
	public boolean canViewItemFicheDePoste;
	public boolean canDeleteItemFicheDePoste;
	public boolean canNotAccesItemFicheDePoste;

	/** le controleur pour l'affichage d'une fiche de poste */
	public CompFicheDePosteCtrl compFicheDePosteCtrl;

	/** afficher les filtres locaux (par emploi type ...) */
	public boolean isAfficherFiltresLocaux;

	/**
	 * @deprecated
	 * @see #CompFicheDePosteListCtrl(Session, NSArray, String, boolean, boolean,
	 *      boolean)
	 * @param session
	 */
	public CompFicheDePosteListCtrl(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param session
	 * @param aFicheDePosteList
	 * @param aMessageEmptyList
	 * @param aShowBtnDelete
	 * @param aShowBtnUpdate
	 * @param aShowOccupant
	 * @param aShowBtnAdd
	 * @param aShouldSaveOnClick
	 * @param aPosteForAddFiche
	 * @param isAfficherFiltresLocaux
	 *          TODO
	 */
	public CompFicheDePosteListCtrl(Session session,
			NSArray<EOFicheDePoste> ficheDePosteList,
			String aMessageEmptyList,
			boolean aShowBtnDelete,
			boolean aShowBtnUpdate,
			boolean aShowOccupant,
			boolean aShowBtnAdd,
			boolean aShouldSaveOnClick,
			EOPoste aPosteForAddFiche,
			boolean aShowBtnCopier,
			boolean isAfficherFiltresLocaux) {
		super(session);
		this.ficheDePosteList = ficheDePosteList;
		messageEmptyList = aMessageEmptyList;
		showBtnDelete = aShowBtnDelete;
		showBtnUpdate = aShowBtnUpdate;
		showOccupant = aShowOccupant;
		showBtnAdd = aShowBtnAdd;
		shouldSaveOnClick = aShouldSaveOnClick;
		poste = aPosteForAddFiche;
		showBtnCopier = aShowBtnCopier;
		this.isAfficherFiltresLocaux = isAfficherFiltresLocaux;
	}

	/**
	 * Retrouver l'occupation associée a cette fiche de poste (celle en cours si
	 * possible, sinon la derniere)
	 * 
	 * @param value
	 */
	public void setFicheDePosteItem(EOFicheDePoste value) {
		ficheDePosteItem = value;
		isOccupationCourante = true;
		if (ficheDePosteItem != null) {
			setRecAffectationDetail(ficheDePosteItem.toAffectationDetailActuelle());
			if (getRecAffectationDetail() == null) {
				isOccupationCourante = false;
				setRecAffectationDetail(ficheDePosteItem.toAffectationDetailDerniere());
			}
			//
			canModifItemFicheDePoste = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.MODIFICATION, EOTypeDroitCible.FICHE_DE_POSTE, ficheDePosteItem, false);
			canViewItemFicheDePoste = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.VISUALISATION, EOTypeDroitCible.FICHE_DE_POSTE, ficheDePosteItem, false);
			canDeleteItemFicheDePoste = feveUserInfo().hasDroitOnCible(
					EOTypeDroitAcces.SUPPRESSION, EOTypeDroitCible.FICHE_DE_POSTE, ficheDePosteItem, false);

			canNotAccesItemFicheDePoste = !canModifItemFicheDePoste && !canViewItemFicheDePoste && !canDeleteItemFicheDePoste;
		}
	}

	/**
	 * La fiche est verrouillee si visee
	 */
	public boolean isLockedFicheDePosteItem() {
		return ficheDePosteItem.fdpVisaAgentBool() || ficheDePosteItem.fdpVisaRespBool() || ficheDePosteItem.fdpVisaDirecBool();
	}

	/**
	 * Action de selectionner un enregistrement.
	 */
	public WOComponent doSelectFicheDePoste() {
		ficheDePosteSelected = ficheDePosteItem;
		compFicheDePosteCtrl = new CompFicheDePosteCtrl(feveSession(), ficheDePosteSelected);
		// configuration du fil d'ariane
		compFicheDePosteCtrl.setStringLabel("Fiche de poste " + ficheDePosteSelected.getEnteteFiche(feveUserInfo()));
		return neFaitRien();
	}

	/**
	 * Indique si un objet a ete selectionne, soit que <code>selectedRecord</code>
	 * n'est pas <code>null</code>
	 */
	public boolean isAFicheDePosteSelected() {
		return ficheDePosteSelected != null;
	}

	// style des elements graphique

	/**
	 * La classe CSS associee a une liste
	 */
	public String classTrFicheDePosteItem() {
		StringBuffer sbClass = new StringBuffer();
		if (!canNotAccesItemFicheDePoste && !isLockedFicheDePosteItem()) {
			sbClass.append(FeveWebComponent.CLASS_TR_MODIFIABLE);
		} else {
			sbClass.append(FeveWebComponent.CLASS_TR_NON_MODIFIABLE);
		}
		return sbClass.toString();
	}

	/**
	 * message flottant d'info de la coche en passant dessus avec la souris
	 * 
	 * @return
	 */
	public String messageFlottantCocheAgent() {
		if (ficheDePosteItem.toAffectationDetailActuelle() != null &&
				ficheDePosteItem.toAffectationDetailActuelle().toAffectation().toIndividu() == feveSession().individuConnecte()) {
			return "Agent : Cliquez pour viser votre fiche de poste";
		} else {
			return "Agent : Cliquez pour viser cette fiche de poste";
		}
	}

	/**
	 * 
	 * @return
	 */
	public String messageFlottantCocheResp() {
		String messageFlottantCoche = "";

		// droit de modification sur cette fiche de poste
		boolean canModifFicheDePosteSurService = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION,
				EOTypeDroitCible.FICHE_DE_POSTE,
				ficheDePosteItem,
				false);

		if (canModifFicheDePosteSurService) {
			messageFlottantCoche = "Responsable : cliquez pour viser cette fiche. "/*
																																							 * +
																																							 * "ATTENTION, d&eacute;cocher = d&eacute;cocher celle de l agent !!!"
																																							 */;
		} else {
			messageFlottantCoche = "Visa du responsable";
		}
		return messageFlottantCoche;
	}

	/**
	 * 
	 * @return
	 */
	public String messageFlottantCocheDirec() {
		String messageFlottantCoche = "";

		// droit de modification des fiches de postes sur la composante
		boolean canModifFicheDePosteSurComposante = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION,
				EOTypeDroitCible.FICHE_DE_POSTE,
				ficheDePosteItem.toPoste().toStructure().toComposante(),
				true);
		if (canModifFicheDePosteSurComposante) {
			messageFlottantCoche = "Dir. Composante : cliquez pour viser cette fiche. "/*
																																									 * +
																																									 * "ATTENTION, d&eacute;cocher = tout d&eacute;cocher !!!"
																																									 */;
		} else {
			messageFlottantCoche = "Visa du Directeur de la composante";
		}
		return messageFlottantCoche;
	}

	/**
	 * disponibilite des coches - coche AGENT : si lui meme ou admin
	 * 
	 * @return
	 */
	public boolean disabledLaCocheAgent() {
		boolean isDisabled = true;

		if (feveSession().isAdmin() ||
				ficheDePosteItem.toAffectationDetailActuelle() != null &&
				ficheDePosteItem.toAffectationDetailActuelle().toAffectation().toIndividu() == feveSession().individuConnecte()) {
			isDisabled = false;
		}
		return isDisabled;
	}

	/**
	 * coche N+1 : si droit de modification sur la fiche de poste
	 * 
	 * @return
	 */
	public boolean disabledLaCocheResp() {
		boolean isDisabled = true;

		// droit de modification sur cette fiche de poste
		boolean canModifFicheDePosteSurService = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION,
				EOTypeDroitCible.FICHE_DE_POSTE,
				ficheDePosteItem,
				false);

		if (canModifFicheDePosteSurService) {
			isDisabled = false;
		}

		return isDisabled;
	}

	/**
	 * coche directeur : si droit de modification sur les fiches de poste de la
	 * composante
	 * 
	 * @return
	 */
	public boolean disabledLaCocheDirec() {
		boolean isDisabled = true;

		// droit de modification des fiches de postes sur la composante
		boolean canModifFicheDePosteSurComposante = feveUserInfo().hasDroitOnCible(
				EOTypeDroitAcces.MODIFICATION,
				EOTypeDroitCible.FICHE_DE_POSTE,
				ficheDePosteItem.toPoste().toStructure().toComposante(),
				true);
		if (canModifFicheDePosteSurComposante) {
			isDisabled = false;
		}

		return isDisabled;
	}

	/**
	 * 
	 * @return
	 */
	public NSTimestamp getItemFicheDePosteDateDebut() {
		return getItemFicheDePosteDateDebut(ficheDePosteItem);
	}

	/**
	 * La date de debut affichee varie selon l'ecran qui affiche cette liste. En
	 * mode "non personnel", on affiche les dates de validite de la fiche En mode
	 * "personnel", on prend la plus grande entre date de debut de la premiere
	 * occupation et la date de debut de fiche
	 */
	public NSTimestamp getItemFicheDePosteDateDebut(EOFicheDePoste ficheDePoste) {
		NSTimestamp dDebut = ficheDePoste.dDebut();
		if (inOnlyPersonnel()) {
			// retrouver l'occupation de l'agent a la fiche
			if (dDebut != null) {
				NSArray<EOAffectationDetail> occupations = ficheDePoste.tosAffectationDetail(
						feveUserInfo().recIndividu());
				if (occupations.count() > 0) {
					NSTimestamp firstDDebut = occupations.objectAtIndex(0).dDebut();
					if (firstDDebut != null && DateCtrl.isAfter(firstDDebut, dDebut)) {
						dDebut = firstDDebut;
					}
				}
			}
		}
		return dDebut;
	}

	/**
	 * 
	 * @return
	 */
	public NSTimestamp getItemFicheDePosteDateFin() {
		return getItemFicheDePosteDateFin(ficheDePosteItem);
	}

	/**
	 * La date de fin affichee varie selon l'ecran qui affiche cette liste. En
	 * mode "non personnel", on affiche les dates de validite de la fiche En mode
	 * "personnel", on prend la plus petite entre date de fin de la dernier
	 * occupation et la date de fin de fiche
	 */
	public NSTimestamp getItemFicheDePosteDateFin(EOFicheDePoste ficheDePoste) {
		NSTimestamp dFin = ficheDePoste.dFin();
		if (inOnlyPersonnel()) {
			// retrouver l'occupation de l'agent a la fiche
			if (dFin != null) {
				NSArray<EOAffectationDetail> occupations = ficheDePoste.tosAffectationDetail(
						feveUserInfo().recIndividu());
				if (occupations.count() > 0) {
					NSTimestamp lastDFin = occupations.lastObject().dFin();
					if (lastDFin != null && DateCtrl.isBefore(lastDFin, dFin)) {
						dFin = lastDFin;
					}
				}
			}
		}
		return dFin;
	}

	/**
	 * TODO bidouille pour des histoires de confidentialité sur les dates de
	 * validité témoin interne qui permet de savoir si la fiche de poste
	 * {@link #ficheDePosteItem} est une fiche personnelle ou non. Pour
	 * l'administrateur, cette methode retourne toujours <em>false</em>
	 * 
	 * @return
	 */
	private boolean inOnlyPersonnel() {
		boolean result = false;

		if (!feveUserInfo().isAdmin()) {
			result = (ficheDePosteItem.tosAffectationDetail(feveUserInfo().recIndividu()).count() > 0);
		}

		return result;
	}

	/**
	 * La classe interne - l'implementation de AlertResponder pour la suppression
	 */
	public class SupprimerFicheResponder implements CktlAlertResponder {

		private WOComponent parentComponent;

		public SupprimerFicheResponder(WOComponent aParentComponent) {
			parentComponent = aParentComponent;
		}

		public WOComponent respondToButton(int buttonNo) {
			switch (buttonNo) {
			case 2:
				return parentComponent;
			case 1:
				ec().deleteObject(ficheDePosteItem);
				try {
					UtilDb.save(ec(), "");
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return parentComponent;
			default:
				return null;
			}
		}
	}

	// ajout

	/**
	 * Passer en mode ajout d'une nouvelle fiche
	 */
	public WOComponent toAddFicheDePoste() {
		isAdding = true;
		compFicheDePosteAddCtrl = new CompFicheDePosteAddCtrl(
				feveSession(), poste, this);
		return null;
	}

	/**
	 * Methode appelée après la création d'une nouvelle fiche de poste
	 */
	public void doAfterCompFicheDePosteAddSuccess() {
		isAdding = false;
	}

	/**
	 * Annulation de la saisie d'une nouvelle fiche
	 */
	public void doCancelCompFicheDePosteAdd() {
		isAdding = false;
	}

	// mise a jour des meta donnees

	/**
	 * Passer en mode modification des meta données de {@link #ficheDePosteItem}
	 */
	public WOComponent toUpdateFicheDePoste() {
		isUpdating = true;
		compFicheDePosteUpdateCtrl = new CompFicheDePosteUpdateCtrl(
				feveSession(), ficheDePosteItem, this);
		return null;
	}

	/**
	 * Methode appelée après la création d'une nouvelle fiche de poste
	 */
	public void doAfterCompFicheDePosteUpdateSuccess() {
		isUpdating = false;
	}

	/**
	 * Annulation de la mise a jour des meta données de la fiche
	 */
	public void doCancelCompFicheDePosteUpdate() {
		isUpdating = false;
	}

	/**
	 * Passer en mode modification des meta données de {@link #ficheDePosteItem}
	 */
	public WOComponent toDuplicationFicheDePoste() {
		isDuplicationFiche = true;
		compFicheDePosteDuplicationCtrl = new CompFicheDePosteDuplicationCtrl(
				feveSession(), ficheDePosteItem, this);
		return null;
	}

	public void doAfterCompFicheDePosteDuplicationSuccess() {
		isDuplicationFiche = false;
	}

	public void doCancelCompFicheDePosteDuplication() {
		isDuplicationFiche = false;
	}

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return compFicheDePosteCtrl;
	}

	@Override
	protected void toLocalFullComponent() {
		ficheDePosteSelected = null;
		compFicheDePosteCtrl = null;
	}

	public final EOAffectationDetail getRecAffectationDetail() {
		return recAffectationDetail;
	}

	public final void setRecAffectationDetail(EOAffectationDetail recAffectationDetail) {
		this.recAffectationDetail = recAffectationDetail;
	}

	// filtre sur les emploi

	public NSMutableArray<EOReferensEmplois> eoReferensEmploisArray;
	public EOReferensEmplois eoReferensEmploisItem;
	public EOReferensEmplois eoReferensEmploisSelected;

	public final NSMutableArray<EOReferensEmplois> getEoReferensEmploisArray() {
		if (eoReferensEmploisArray == null) {
			eoReferensEmploisArray = new NSMutableArray((NSArray<EOReferensEmplois>) ficheDePosteList.valueForKey(EOFicheDePoste.TO_REFERENS_EMPLOIS_KEY));
			eoReferensEmploisArray.removeIdenticalObject(NSKeyValueCoding.NullValue);
			eoReferensEmploisArray = new NSMutableArray(NSArrayCtrl.removeDuplicate(eoReferensEmploisArray));
			eoReferensEmploisArray = (NSMutableArray<EOReferensEmplois>) CktlSort.sortedArray(eoReferensEmploisArray, EOReferensEmplois.DISPLAY_KEY);
		}
		return eoReferensEmploisArray;
	}

	public final EOReferensEmplois getEoReferensEmploisSelected() {
		return eoReferensEmploisSelected;
	}

	public final void setEoReferensEmploisSelected(EOReferensEmplois eoReferensEmploisSelected) {
		this.eoReferensEmploisSelected = eoReferensEmploisSelected;
		eoFicheDePosteArray = null;
	}

	/** liste des fiches de poste affichées */
	public NSArray<EOFicheDePoste> eoFicheDePosteArray;

	public NSArray<EOFicheDePoste> getEoFicheDePosteArray() {
		if (eoFicheDePosteArray == null) {
			EOQualifier qual = null;
			if (getEoReferensEmploisSelected() != null) {
				qual = ERXQ.equals(EOFicheDePoste.TO_REFERENS_EMPLOIS_KEY, getEoReferensEmploisSelected());
			}
			eoFicheDePosteArray = EOQualifier.filteredArrayWithQualifier(ficheDePosteList, qual);
		}
		return eoFicheDePosteArray;
	}

	public final void setEoFicheDePosteArray(NSArray<EOFicheDePoste> eoFicheDePosteArray) {

	}
}
