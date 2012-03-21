package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.grhum.EOVReferens;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

/**
 * Navigateur de selection des données de REFERENS
 * 
 * @author ctarade
 */
public class CompBrowserReferens 
	extends FeveWebComponent {
	
	// --
	// bindings
	// --
	public EOReferensEmplois inReferensEmploi;
	public boolean shouldForceSelectionInReferensEmploi = false;
	public Boolean showActivites = Boolean.FALSE;
	public Boolean showCompetences = Boolean.FALSE;
	public Boolean showContentOnlyOnLeaf = Boolean.TRUE;
	// liste des activites a masquer 
	public NSArray referensActivitesToHideList;
	// liste des competences a masquer
	public NSArray referensCompetencesToHideList;
	
  // variables sortantes (bidons => voir les getters)
  public EOReferensEmplois outReferensEmploi;
  public EOReferensActivites outReferensActivite;
  public EOReferensCompetences outReferensCompetence;
	
	// gestion du CktlHXBrowser
	public NSMutableArray referensNodesSelectedPath;
	public VReferensNode referensNodeSelected;
	private VReferensNode referensNodeZero;
	
	public VReferensNode referensNodesSelectedPathItem;
		
	public CompBrowserReferens(WOContext context) {
		super(context);
		referensNodesSelectedPath = new NSMutableArray();
	}
	
	// getters locaux pour connaitres les variables entrantes (les setters sont jamais appeles quand il faut ...)
	
	private boolean getShowActivitesBinding() {
		Boolean result  = (Boolean) valueForBinding("showActivites");
		if (result == null) {
			result = Boolean.FALSE;
		}
		return result.booleanValue();
	}

	private boolean getShowCompetencesBinding() {
		Boolean result  = (Boolean) valueForBinding("showCompetences");
		if (result == null) {
			result = Boolean.FALSE;
		}
		return result.booleanValue();
	}

	private boolean getShowContentOnlyOnLeafBinding() {
		Boolean result  = (Boolean) valueForBinding("showContentOnlyOnLeaf");
		if (result == null) {
			result = Boolean.FALSE;
		}
		return result.booleanValue();
	}

	private EOReferensEmplois getInReferensEmploiBinding() {
		EOReferensEmplois result  = (EOReferensEmplois) valueForBinding("inReferensEmploi");
		return result;
	}

	private NSArray getReferensActivitesToHideList() {
		NSArray result  = (NSArray) valueForBinding("referensActivitesToHideList");
		return result;
	}

	private NSArray getReferensCompetencesToHideList() {
		NSArray result  = (NSArray) valueForBinding("referensCompetencesToHideList");
		return result;
	}
	
		
	/**
	 * affichage du component content que si la selection est une feuille
	 */
	public boolean showContent() {
		boolean result = true;
		if (getShowContentOnlyOnLeafBinding()) {
			result = (referensNodeSelected != null && referensNodeSelected.isLeaf());
		}
		return result;
	}

	/**
	 * On affiche le corps uniquement si on est sur un emploi, une activite ou une competence
	 * @return
	 */
	public boolean showCorps() {
		return getOutReferensEmploi() != null || getOutReferensActivite() != null || getOutReferensCompetence() != null;
	}

	/**
	 * Affichage du corps
	 * @return
	 */
	public String strCorps() {
		String result = StringCtrl.emptyString();
		if (getOutReferensEmploi() != null) {
			result = getOutReferensEmploi().toReferensCorps().intitulcorps();
		} else if (getOutReferensActivite() != null) {
			result = getOutReferensActivite().toReferensEmplois().toReferensCorps().intitulcorps();
		} else if (getOutReferensCompetence() != null) {
			result = getOutReferensCompetence().toReferensEmplois().toReferensCorps().intitulcorps();
		} 
		return result;
	}
	
	/**
	 * La zone indiquant les activites et competences est visible
	 * que si un emploi type est selectionné
	 * @return
	 */
	public boolean showActiviteCompetences() {
		return getOutReferensEmploi() != null;
	}
	
	/**
	 * L'infobulle contenant la liste des activites de l'emploi type
	 * @return
	 */
	public String getTipActivites() {
		StringBuffer sb = new StringBuffer();
		NSArray list = getOutReferensEmploi().tosReferensActivites();
		for (int i=0; i<list.count(); i++) {
			EOReferensActivites item = (EOReferensActivites) list.objectAtIndex(i);
			sb.append("&raquo;").append(StringCtrl.normalize(item.displayLong())).append("<br>");
		}
		return sb.toString();
	}
	
	/**
	 * L'infobulle contenant la liste des competence de l'emploi type
	 * @return
	 */
	public String getTipCompetences() {
		StringBuffer sb = new StringBuffer();
		NSArray list = getOutReferensEmploi().tosReferensCompetences();
		for (int i=0; i<list.count(); i++) {
			EOReferensCompetences item = (EOReferensCompetences) list.objectAtIndex(i);
			sb.append("&raquo;").append(StringCtrl.normalize(item.displayLong())).append("<br>");
		}
		return sb.toString();
	}
	
	//
	
  /**
   * Binding <code>zeroItem</code> du <code>CktlHXBrowser</code> 
   * listener est différent.StringCtrl
   */
  public VReferensNode referensNodeZero() {
  	if (referensNodeZero == null) {
  		// instanciation de l'arbre avec les archives uniquement 
  		// si l'emploi type des binding existe est qu'il est sur une DCP archivee
  		inReferensEmploi = getInReferensEmploiBinding();
  		// boolean ignoreArchive = (inReferensEmploi == null || !inReferensEmploi.toReferensFp().toReferensDcp().isArchive());
  		// DT 61185 : on affiche plus du tout les archives
  		boolean ignoreArchive = true;
  		// si on ignore tout le temps, alors on met a vide si l'emploi est sur une archive
  		if (inReferensEmploi != null && inReferensEmploi.isArchive()) {
  			inReferensEmploi = null;
  		}
  		referensNodeZero = new VReferensNode(
  				EOVReferens.findRoot(ec), allNodes(), ignoreArchive, getShowActivitesBinding(), getShowCompetencesBinding(), getReferensActivitesToHideList(), getReferensCompetencesToHideList());
    }
    return referensNodeZero;
  }
  
  public NSArray allNodes() {	
  	return session.getListVReferensNodes(); 
  }  
  
  // recherche
    
  /** le textfield de recherche */
  public String strEmploiSearch;
  
  /** la taille minimum de la chaine a rechercher */
  public boolean errSizeStrSearch;
  public final int MIN_SIZE_STRING_SEARCH = 3;

  /** les resultats de recherche */
  public NSArray vReferensFoundList;
  public EOVReferens vReferensFoundItem;
  public boolean errNoResults; 

  private void resetSearchErrors() {
    errSizeStrSearch = errNoResults = false;
  }
  
  /**
   * Lancer la recherche
   */
  public WOComponent searchReferens() {
    // retraitement 
    resetSearchErrors();
    if (StringCtrl.isEmpty(strEmploiSearch)) {
    	strEmploiSearch = StringCtrl.emptyString();
    }
    strEmploiSearch = StringCtrl.replace(strEmploiSearch, "*", "");
    strEmploiSearch = StringCtrl.replace(strEmploiSearch, "\\", "");    
    strEmploiSearch = StringCtrl.replace(strEmploiSearch, "''", "");    
    strEmploiSearch = StringCtrl.replace(strEmploiSearch, "%", "");    
    strEmploiSearch = StringCtrl.replace(strEmploiSearch, "@", "");    
   	// enlever les accents et caracteres speciaux
    strEmploiSearch = StringCtrl.chaineSansAccents(strEmploiSearch);
    strEmploiSearch = StringCtrl.toBasicString(strEmploiSearch);

    if (StringCtrl.normalize(strEmploiSearch).length() >= MIN_SIZE_STRING_SEARCH) {
    	if (getShowActivitesBinding() == true) {
      	vReferensFoundList = EOVReferens.findVReferensLibelleSeulLike(ec, strEmploiSearch, EOVReferens.NIVEAU_ACTIVITE);
    	} else if (getShowCompetencesBinding() == true) {
      	vReferensFoundList = EOVReferens.findVReferensLibelleSeulLike(ec, strEmploiSearch, EOVReferens.NIVEAU_COMPETENCE);
    	} else {
      	vReferensFoundList = EOVReferens.findVReferensLibelleSeulLike(ec, strEmploiSearch, EOVReferens.NIVEAU_EMPLOI);
    	}
    	if (vReferensFoundList.count()==0) {
    		errNoResults = true;
    	}
    } else {
      errSizeStrSearch = true;
      vReferensFoundList = null;
    }
    //doAfterSearchActivite();
    return null;
  }
  
  
  /**
   * Cliquer sur le libelle d'une activite suite a la recherche : 
   * cela va selectionner la dans l'arborescence
   */
  public WOComponent selectReferensFoundItem() {
    // trouver l'arbo des activites associee
    NSArray recsVReferens = EOVReferens.findReferensPath(vReferensFoundItem);

    // le dernier node selection
    referensNodeSelected = findNode(referensNodeZero().retrieveRootNodes(), (EOVReferens) recsVReferens.objectAtIndex(0));
    
    // selection du premier node
    referensNodesSelectedPath = new NSMutableArray();
    referensNodesSelectedPath.addObject(referensNodeSelected);

    int currentIndex = 1;
    
    // selection des autres
    while (currentIndex < recsVReferens.count()) {
    	EOVReferens recVReferens = (EOVReferens) recsVReferens.objectAtIndex(currentIndex);
      referensNodeSelected = findNode(referensNodeSelected.retrieveChildrenNodes(), recVReferens);
      referensNodesSelectedPath.addObject(referensNodeSelected);
      currentIndex++;
    }
    
    // reselection de la fin pour que l'interface soit mise a jour
    setReferensNodeSelected(referensNodeSelected);
    
    // raz de la resultats
    vReferensFoundList = null;
    
    return null;
  }
  
  /**
   * On affiche pas le détail de l'emploi du element trouve si on ne cherche
   * pas des activites ou des competences
   * @return
   */
  public boolean showStrVReferensFoundItemEmploi() {
  	return getShowActivitesBinding() || getShowCompetencesBinding();
  }
  
  /**
   * Methode interne permettant de retrouver le node associe a une donnee
   * @param someNodes :  les nodes d'un niveau de l'arbo parmi lesquesl on cherche
   * @param recVReferens : la donne <code>VReferensNode</code> en question
   */
  private VReferensNode findNode(NSArray someNodes, EOVReferens recVReferens) {
    NSArray nodes = EOQualifier.filteredArrayWithQualifier(someNodes, 
        CktlDataBus.newCondition(EOVReferens.KEY_KEY+"=%@", new NSArray(recVReferens.key())));
    if (nodes.count() > 0) {
      return (VReferensNode) nodes.lastObject();
    } else {
      return null;
    }
  }
  
  /**
   * Le traitement a effectuer lors de la selection
   * de l'activite. On appelle la methode 
   */
  public void setReferensNodeSelected(VReferensNode selectedItem) {
  	referensNodeSelected = selectedItem;
  	// memorisation du dernier emploi selectionné dans la session
  	if (referensNodeSelected != null) {
  		session.setLastReferensEmplois(referensNodeSelected.getRecVReferens().toReferensEmplois());
  	}
    //doAfterActiviteSelectedItem();
    resetSearchErrors();
  }
  
  
  // setters
  
  /**
   * Indique au composant que la valeur preselectionnée doit être
   * rafraichie. La valeur passee est alors a <em>true</em>.
   * @param value
   */
  public void setShouldForceSelectionInReferensEmploi(boolean value) {
  	shouldForceSelectionInReferensEmploi = value;
  	if (shouldForceSelectionInReferensEmploi) {
  		inReferensEmploi = getInReferensEmploiBinding();
			// reinstancier l'arbre
  		referensNodeZero = null;
  		session.clearBrowserReferensCache();
  		// selection
  		// DT 61185 : on affiche plus du tout les archives
  		if (inReferensEmploi != null && !inReferensEmploi.isArchive()) {
  			// on prend en priorite la derniere selection
  			if (session.getLastReferensEmplois() != null) {
  				inReferensEmploi = session.getLastReferensEmplois();
  			}
    		// on force la selection dans l'arbre
    		vReferensFoundItem = EOVReferens.findVReferensForReferensEmploi(inReferensEmploi);
    		// on fait comme si on faisait une recherche
    		selectReferensFoundItem();
  		} else {
  			// effacer la selection
  			referensNodesSelectedPath = new NSMutableArray();
  			referensNodeSelected = null;
  		}
  		// on indique que le forçage est terminé (valeur du composant appelant modifiée aussi)
  		shouldForceSelectionInReferensEmploi = false;
  	}

  }
  
  
  // getters
  
  
  /**
   * l'emploi type selectionné dans l'arbre
   */
  public EOReferensEmplois getOutReferensEmploi() {
  	EOReferensEmplois result = null;
  	if (referensNodeSelected != null) {
  		EOVReferens recVReferens = referensNodeSelected.getRecVReferens();
  		if (recVReferens.niveau().intValue() == EOVReferens.NIVEAU_EMPLOI) {
  			result = recVReferens.toReferensEmplois();
  		}
  	}
  	return result;
  }  
  /**
   * l'emploi type selectionné dans l'arbre
   */
  public EOReferensActivites getOutReferensActivite() {
  	EOReferensActivites result = null;
  	if (referensNodeSelected != null) {
  		EOVReferens recVReferens = referensNodeSelected.getRecVReferens();
  		if (recVReferens.niveau().intValue() == EOVReferens.NIVEAU_ACTIVITE) {
  			result = recVReferens.toReferensActivites();
  		}
  	}
  	return result;
  }  
  /**
   * la competence selectionnée dans l'arbre
   */
  public EOReferensCompetences getOutReferensCompetence() {
  	EOReferensCompetences result = null;
  	if (referensNodeSelected != null) {
  		EOVReferens recVReferens = referensNodeSelected.getRecVReferens();
  		if (recVReferens.niveau().intValue() == EOVReferens.NIVEAU_COMPETENCE) {
  			result = recVReferens.toReferensCompetences();
  		}
  	}
  	return result;
  }
}