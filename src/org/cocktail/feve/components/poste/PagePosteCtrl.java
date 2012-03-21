package org.cocktail.feve.components.poste;
import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControled;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Le controleur de la page de gestion des postes {@link PagePoste}
 * 
 * @author ctarade
 */
public class PagePosteCtrl 
	extends A_ComponentControlerAndFilArianeNode 
		implements I_CallingCompPosteMetaData {
	
	/** type de recherche choisie */
	private final static String RADIO_TYPE_FILTRE 		= "F";
	private final static String RADIO_TYPE_RECHERCHE 	= "R";
	public String radioType;
	public String radioTypeFiltre 		= RADIO_TYPE_FILTRE;
	public String radioTypeRecherche 	= RADIO_TYPE_RECHERCHE;
	
	/**
	 * un item <code>EOStructure</code> parmi la liste des structures filtre.
	 */
	public EOStructure structureItem;
	
	/**
	 * la selection <code>EOStructure</code> dans la liste des structures filtre.
	 */
	public EOStructure structureSelected;
	
	/**
	 * Temoin s'il faut recharger le dg
	 */
	protected boolean shouldRefreshDgPoste;
	
	/**
	 * Faut-il supprimer toutes les données du dg
	 */
	protected boolean shouldClearDgPoste;
	 
  /**
   * indique s'il faut afficher les groupes archives O/N
   */
  public boolean showArchive; 

	public NSArray<String> posteTypeList = EOPoste.POSTE_TYPE_ARRAY;
	public String posteTypeItem;
	public String posteTypeSelected;

	// nature du poste (enseignant / non enseignant)
	public NSArray<String> posteNatureList = EOPoste.POSTE_NATURE_ARRAY;
	public String posteNatureItem;
	public String posteNatureSelected;
	
	/** le gestionnaire de la liste des postes */
	private CompPosteListCtrl compPosteListCtrl;

	/** le gestionnaire de creation de poste */
  public CompPosteMetaDataCtrl compPosteMetaDataCtrlAdd;

  /** indique si on est mode creation de poste */
  public boolean showCompPosteMetaDataAdd = false;
 
  /**
   * @param session
   */
  public PagePosteCtrl(Session session) {
		super(session);
		initCtrl();
	}
 
	private void initCtrl() {
		// par defaut, si 1 seul service, on le selectionne
		if (feveUserInfo().getServicePosteList().count() == 1) {
			structureSelected = (EOStructure) feveUserInfo().getServicePosteList().lastObject();
			shouldRefreshDgPoste = true;
		}
		// selection du type "en cours" par defaut
		posteTypeSelected = EOPoste.POSTE_TYPE_EN_COURS;
		// selection de la nature "non enseignant" par defaut
		posteNatureSelected = EOPoste.POSTE_NATURE_NON_ENSEIGNANT;
		// par defaut, on masque les groupes archives
		showArchive = false;
		compPosteListCtrl = new CompPosteListCtrl(feveSession());
		compPosteListCtrl.setStringLabel("Liste des postes du service");
		compPosteListCtrl.setLinkLabel("Accès à la liste des postes du service");
		compPosteListCtrl.setLinkTitle("Retourner aux postes du service");
		// en filtre par defaut
		radioType = RADIO_TYPE_FILTRE;
	}
	
	/**
	 * cast du {@link A_ComponentControled} en {@link PagePoste}
	 * @return
	 */
	protected PagePoste getComponent() {
		return (PagePoste) super.getComponent();
	}

	/**
   * Raccourci vers le DH de {@link PagePoste}
   */
	private WODisplayGroup dgPoste() {
		return getComponent().dgPoste;
	}
	
	
	/**
	 * La liste des services visibles. 
	 * Elle est conditionnée par la valeur du boolean
	 * <code>showArchive</code> qui permet de masquer 
	 * ou non les services archivés
	 * @return
	 */
	public NSArray getServiceList() {
		NSArray<EOStructure> serviceList = feveUserInfo().getServicePosteList(showArchive);
		return serviceList;
	}
	
	
	/**
	 * Methode appelée lors du rechargement du formulaire
	 * contenant tous les filtres
	 * @return
	 */
	public WOComponent doFilterDgPoste() {
		NSMutableDictionary dgPosteQueryBindings = new NSMutableDictionary();
		NSArray qualList = new NSArray();

		// filtre 
		if (isFiltre()) {
			
			// le filre sur la structure
			if (structureSelected != null) {
				dgPosteQueryBindings.addEntriesFromDictionary(getStructureQueryBindings());
				canCreatePosteOnStructureSelected = feveUserInfo().hasDroitOnCible(
						EOTypeDroitAcces.CREATION, 
						EOTypeDroitCible.POSTE,
						structureSelected, 
						false);
				shouldRefreshDgPoste = true;
			} else {
				// pas de service selectionné
				shouldClearDgPoste = true;
				canCreatePosteOnStructureSelected = false;
			}
			

			// nature
			EOQualifier posteNatureQualifier = EOPoste.getPosteNatureQualifier(null, posteNatureSelected);
			if (posteNatureQualifier != null) {
				qualList = qualList.arrayByAddingObject(posteNatureQualifier);
			}
			// type
			EOQualifier posteTypeQualifier = EOPoste.getPosteTypeQualifier(null, posteTypeSelected);
			if (posteTypeQualifier != null) {
				qualList = qualList.arrayByAddingObject(posteTypeQualifier);
			}

		} else {

			// Deuxieme ligne : recherche
			String strSearchBasic = StringCtrl.toBasicString(getComponent().getStrSearch());
			if (!StringCtrl.isEmpty(strSearchBasic) && 
					!getComponent().getStrSearch().equals(FeveWebComponent.STR_SEARCH_DEFAULT_VALUE) && 
					strSearchBasic.length() >= FeveWebComponent.STR_SEARCH_MIN_SIZE) {
				dgPosteQueryBindings.addEntriesFromDictionary(getDicoSearch());
				shouldRefreshDgPoste = true;
				shouldClearDgPoste = false;
			}	else {
				shouldClearDgPoste = true;
			}

		}
		

			
		dgPoste().queryBindings().removeAllObjects();
		dgPoste().queryBindings().setDictionary(dgPosteQueryBindings);	
		dgPoste().setQualifier(new EOAndQualifier(qualList));		

		return null;
	}

	/**
	 * Obtenir le dictionnaire attendu pour la fetch spec
	 * associee au dgPoste concernant le filtre sur les structures
	 * @return <em>null</em> aucune structure n'est selectionnée.
	 */
	private NSMutableDictionary getStructureQueryBindings() {
		if (structureSelected != null) {
			NSMutableDictionary dico = new NSMutableDictionary();
			dico.setObjectForKey(structureSelected.cStructure(), "cStructure");
			return dico;
		} else {
			return null;
		}
	}
	/**
	 * Obtenir le dictionnaire de recherche pour dgPoste
	 * pour faire une recherche par nom
	 */
	private NSDictionary getDicoSearch() {
		NSMutableDictionary dico = new NSMutableDictionary();
		// eclater la chaine
		NSArray mots = NSArray.componentsSeparatedByString(getComponent().getStrSearch(), " ");
		// 
		for (int i=0; i<mots.count(); i++) {
			String mot = (String) mots.objectAtIndex(i);
			// enlever les caracteres incorrects
			String motClean = StringCtrl.toBasicString(StringCtrl.chaineSansAccents(mot));
			// remplacer les _ par des wildcard
			motClean = StringCtrl.replace(motClean, "_", "*");
			if (!StringCtrl.isEmpty(motClean)) {
				dico.setObjectForKey("*"+motClean+"*", "mot" + i);
			}
		}
		return dico.immutableClone();
	}
	
	
	// setters
	
	/**
	 * Indiquer s'il faut rafraichir la liste des postes
	 * depuis une page externe
	 */
	public void setShouldRefreshDgPoste(boolean value) {
		shouldRefreshDgPoste = value;
	}
		
  /**
   * L'individu connecte a-t-il les droits creation sur le
   * service <code>structureSelected</code>
   */
  public boolean canCreatePosteOnStructureSelected;
  
  
  // NAVIGATION
  
  /**
   * Effacer le texte de recherche
   */
  public WOComponent doClearStrSearch() {
  	String prevStrSearch = getComponent().getStrSearch();
  	getComponent().setStrSearch(FeveWebComponent.STR_SEARCH_DEFAULT_VALUE);
   	// on fait un rechargement que si necessaire
   	if (prevStrSearch != null && !prevStrSearch.equals(getComponent().getStrSearch())) {
   		doFilterDgPoste();
   	}
  	return null;
  }
  
  
  
  // BOOLEAN INTERFACE
  
  /**
   * Indique si la taille minimum de la chaine
   * de recherche n'est pas atteinte
   */
  public boolean getIsShowWarningSizeStrSearch() {
  	return StringCtrl.isEmpty(getComponent().getStrSearch()) || getComponent().getStrSearch().length() < FeveWebComponent.STR_SEARCH_MIN_SIZE;
  }

  /**
   * On affiche le popup des structure s'il y a plus
   * d'1 structure disponible
   */
  public boolean showPopUpStructure() {
  	return feveUserInfo().getServicePosteList().count() > 1;
  }
  
  /**
   * 
   * @return
   */
  public boolean isFiltre() {
  	return radioType.equals(RADIO_TYPE_FILTRE);
  }
  
  /**
   * 
   * @return
   */
  public boolean isRecherche() {
  	return radioType.equals(RADIO_TYPE_RECHERCHE);
  }
  
  // bascule vers CompPosteMetaData

  /**
   * Afficher l'ecran de saisie d'un nouveau poste
   */
  public WOComponent toCompPosteMetaDataAdd() {
  	showCompPosteMetaDataAdd = true;
  	compPosteMetaDataCtrlAdd = new CompPosteMetaDataCtrl(feveSession(), structureSelected, this);
  	return null;
  }  

  /**
   * Traitement apres ajout d'un nouveau poste
   */
	public void doAfterCompPosteMetaDataSuccess() {
		showCompPosteMetaDataAdd = false;
		// on demande un rechargement de la liste des postes en cas d'ajout
		// pour que ce dernier apparaisse
		setShouldRefreshDgPoste(true);
	}

  /**
   * Traitement apres annulation ajout d'un nouveau poste
   */
	public void doAfterCompPosteMetaDataCancel() {
		showCompPosteMetaDataAdd = false;
	}
		
	/**
	 * Indique si le composant est affiché en page pleine
	 * @return
	 */
	public boolean showFullComponent() {
		boolean show = true;

		// on met a jour les meta données du poste ?
		if (showCompPosteMetaDataAdd) {
			show = false;
		}
		
		return show;
	}
	
	/**
	 * Indique si l'un des sous composants est affiché
	 * @return
	 */
	public boolean showSubComponents() {
		boolean show = false;
		
		// si on est pas en mise a jour les meta données du poste
		if (!showCompPosteMetaDataAdd) {
			// on regarde si un poste est selectionné
			if (getCompPosteListCtrl().isAffichageFullComponent() == false) {
				show = true;
			}
		}
		
		return show;
	}
	
	// getters
	
	public final CompPosteListCtrl getCompPosteListCtrl() {
		return compPosteListCtrl;
	}

	
	// fil ariane

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return getCompPosteListCtrl();
	}

	@Override
	protected void toLocalFullComponent() {
		// masquer l'ajout
		if (compPosteMetaDataCtrlAdd != null) {
			doAfterCompPosteMetaDataCancel();
		}
	}
	
}
