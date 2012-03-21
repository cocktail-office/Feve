package org.cocktail.feve.components.common;

import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOVReferens;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.components.CktlHXBrowserNode;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;

/**
 * Gere le contenu des "browser" des donnees de referens. Cette classe
 * permet de recuperer les donnees et d'initialiser le contenu du chaque
 * colonne du browser.
 * 
 * @author ctarade
 */
public class VReferensNode 
	extends CktlHXBrowserNode
		implements NSKeyValueCoding {

	// l'objet de la base de donnees representant ce noeud
	private EOVReferens record;
	
  // le pointeur vers le tableau contenant tous les nodes
  private NSArray allNodes;

  // faut-il afficher les DCP archivees
  private boolean shouldIgnoreArchives;
  //
  private boolean shouldShowActivite;
  // 
  private boolean shouldShowCompetence;
  
  // liste des activites a masquer
  private NSArray referensActivitesToHideList;

  // liste des competences a masquer
  private NSArray referensCompetencesToHideList;
  
  private final static int MAX_SIZE_LIBELLE = 34;
  
  /**
   * 
   * @param aRecord l'enregistrement <code>EOVReferens</code> associe
   * @param someAllNodes : la liste de tous nodes instancies
   * @param ignoreArchive : faut-il ignorer les DCP archives
   * @param showActivite : faut-il afficher les activites sous les emplois
   * @param showCompetence : faut-il afficher les competences sous les emplois
   * @param 
   */
	public VReferensNode(
				EOVReferens aRecord, NSArray someAllNodes, boolean ignoreArchives, boolean showActivite, boolean showCompetence,
				NSArray someReferensActivitesToHideList, NSArray someReferensCompetencesToHideList) {
		record = aRecord;
    allNodes = someAllNodes;
    shouldIgnoreArchives = ignoreArchives;
    shouldShowActivite = showActivite;
    shouldShowCompetence = showCompetence;
    referensActivitesToHideList = someReferensActivitesToHideList;
    referensCompetencesToHideList = someReferensCompetencesToHideList;
 	}
	

	/**
	 * Affichage dans le Browser : on coupe
	 * @return
	 */
	public String displayName() {
		return displayName(true);
	}
	
	/**
	 * Affichage dans la liste : affichage complet
	 * @return
	 */
	public String displayLongName() {
		return displayName(false);
	}
	
	
	/**
	 * Affichage dans le CktlHXBrowser.
	 * On ote le prefix "z__" reserve a Autres pour avoir l'item en 
	 * dernier en classment alphabetique
	 * On supprime la mention "nouvelle nomenclature" si on affiche
	 * pas les archives
	 */
  public String displayName(boolean cutOnMaxSize) {
  	String result = StringCtrl.emptyString();
  	if (record != null && !StringCtrl.isEmpty(record.libelle())) {
  		result = record.libelle();
  		// autres
  		if (result.startsWith(EOVReferens.PREFIX_AUTRES)) {
  			result = result.substring(EOVReferens.PREFIX_AUTRES.length(), result.length());
  		}
  		// supprimer la mention nouvelle nomemclature
  		if (shouldIgnoreArchives) {
  			result = StringCtrl.replace(result, EOVReferens.MENTION_NOUVELLE_NOMENCLATURE, "");
  		}
  		// prefixer les DCP archives d'un message
   		if (record.toReferensDcp().isArchive()) {
   			result = EOVReferens.MENTION_NE_PLUS_UTILISER + result;
   		}
  		// raccourcir si besoin
  		if (cutOnMaxSize && result.length() > MAX_SIZE_LIBELLE) {
    		result = StringCtrl.cut(result, MAX_SIZE_LIBELLE);
    		//result += " (...)";
    		result += "&#133;"; // les ... en HTML
  		}/*
  		if (record.niveau().intValue() == EOVReferens.NIVEAU_ACTIVITE) {
  			result = "A_" + result;
  		}
  		if (record.niveau().intValue() == EOVReferens.NIVEAU_COMPETENCE) {
  			result = "C_" + result;
  		}*/
  	}
  	return result;
  }

  /**
   * 
   */
	public boolean isLeaf() {
		return retrieveChildrenNodes().count() == 0;
	}

	/**
	 * Les nodes enfants. 
	 * La liste est conditionnee par les parametres <code>shouldShowActivite</code>
	 * et <code>shouldShowCompetence</code>
	 */
	public NSArray retrieveChildrenNodes() {
		NSArray childs = new NSArray();
		if (record.niveau().intValue() < EOVReferens.NIVEAU_EMPLOI) {
			// dcp ou famille, on affiche les enfants
			childs = record.tosVReferensFils(qualifierToUse(), EOVReferens.SORT_LIBELLE, true);
		} else if (record.niveau().intValue() == EOVReferens.NIVEAU_EMPLOI && (shouldShowActivite || shouldShowCompetence)) {
			// pour les emplois, on regarde les parametres
			//NSArray otherQualifiers = new NSArray();
			// les enregistrements a masquer
			NSArray recsToDelete = new NSArray();
			
			
			if (shouldShowActivite) {
				//otherQualifiers = otherQualifiers.arrayByAddingObject(EOVReferens.QUAL_ACTIVITE);
				// on peut avoir besoin de masquer certaines activites
				if (!NSArrayCtrl.isEmpty(referensActivitesToHideList)) {
					for (int i=0; i<referensActivitesToHideList.count(); i++) {
						EOReferensActivites referensActivite = (EOReferensActivites) referensActivitesToHideList.objectAtIndex(i);
						recsToDelete = recsToDelete.arrayByAddingObject(
								EOVReferens.findVReferensForReferensActivites(referensActivite));
					}
				}
			}
			if (shouldShowCompetence) {
				//otherQualifiers = otherQualifiers.arrayByAddingObject(EOVReferens.QUAL_COMPETENCE);
				// on peut avoir besoin de masquer certaines competences
				if (!NSArrayCtrl.isEmpty(referensCompetencesToHideList)) {
					for (int i=0; i<referensCompetencesToHideList.count(); i++) {
						EOReferensCompetences referensCompetences = (EOReferensCompetences) referensCompetencesToHideList.objectAtIndex(i);
						recsToDelete = recsToDelete.arrayByAddingObject(
								EOVReferens.findVReferensForReferensCompetences(referensCompetences));
					}
				}
			}
			/*EOQualifier qualifier = new EOAndQualifier(new NSArray(new EOQualifier[]{qualifierToUse(),
					new EOAndQualifier(new NSArray(otherQualifiers))}));
			CktlLog.log("retrieveChildrenNodes() qualifier="+qualifier);
			childs = record.tosVReferensFils(qualifier, EOVReferens.SORT_LIBELLE, true);*/
			childs = record.tosVReferensFils(qualifierToUse(), EOVReferens.SORT_LIBELLE, true);
						
			// masquer certains enregistrement
			if (recsToDelete.count() > 0) {
				NSMutableArray childsMutable = new NSMutableArray(childs);
				for (int i=0; i<recsToDelete.count(); i++) {
					EOVReferens recToDelete = (EOVReferens) recsToDelete.objectAtIndex(i);
					childsMutable.removeIdenticalObject(recToDelete);
				}
				childs = childsMutable.immutableClone();
			}
			// ne conserver que le niveau souhaite sous les emplois
			if (record.niveau().intValue() == EOVReferens.NIVEAU_EMPLOI) {
				NSMutableArray childsMutable = new NSMutableArray(childs);
				for (int i=0; i<childs.count(); i++) {
					EOVReferens child = (EOVReferens) childs.objectAtIndex(i);
					if ((!shouldShowActivite && child.niveau().intValue() == EOVReferens.NIVEAU_ACTIVITE) ||
							(!shouldShowCompetence && child.niveau().intValue() == EOVReferens.NIVEAU_COMPETENCE)) {
						childsMutable.removeIdenticalObject(child);
					}
				}
				childs = childsMutable.immutableClone();
			}
		}
		// suppression des doublons
		childs = NSArrayCtrl.removeDuplicate(childs);
		return recordsToNodes(childs);
	}

	/**
	 * 
	 */
	public NSArray retrieveRootNodes() {
		return recordsToNodes(new NSArray(EOVReferens.findRoot(
				record.editingContext()).tosVReferensFils(qualifierToUse(), EOVReferens.SORT_LIBELLE, true)));
	}
	
	/**
	 * l'enregistrement <code>EOVReferens</code> associe
	 * @return
	 */
	public EOVReferens getRecVReferens() {
		return record;
	}
	
	// methodes internes

	/**
	 * Transformer une liste d'enregistrement en nodes
	 */
  private NSArray recordsToNodes(NSArray objects) {
    NSMutableArray items = new NSMutableArray();
    for(int i=0; i<objects.count(); i++) {
    	VReferensNode node = newInstance((EOVReferens) objects.objectAtIndex(i));
      items.addObject(node);
    }
    return items;
  }
  
  /**
   * Avant d'instancier, on regarde dans la session si
   * l'objet n'existe pas deja
   */
  private VReferensNode newInstance(EOVReferens aRecord) {
  	VReferensNode theInstance = null;
    if (aRecord == null) {
    	theInstance = new VReferensNode(aRecord, allNodes, shouldIgnoreArchives, shouldShowActivite, shouldShowCompetence, referensActivitesToHideList, referensCompetencesToHideList);
    } else {
      theInstance = findNode(aRecord);
      if (theInstance == null) {
        theInstance = new VReferensNode(aRecord, allNodes, shouldIgnoreArchives, shouldShowActivite, shouldShowCompetence, referensActivitesToHideList, referensCompetencesToHideList);
        allNodes = allNodes.arrayByAddingObject(theInstance);
      }
    }
    return theInstance;
  }
  
  /**
   * Methode interne permettant de retrouver le node associe a un enregistrement
   * @param someNodes :  les nodes d'un niveau de l'arbo parmi lesquesl on cherche
   * @param record : l'enregistrement en question
   */
  public VReferensNode findNode(EOVReferens record) {
    NSArray nodes = EOQualifier.filteredArrayWithQualifier(allNodes, 
        CktlDataBus.newCondition(EOVReferens.KEY_KEY+"='"+record.key()+"'"));
    if (nodes.count() > 0) 
      return (VReferensNode) nodes.lastObject();
    return null;
  }

  /**
   * Indique ce que l'on doit afficher
   * @return
   */
  private EOQualifier qualifierToUse() {
  	if (shouldIgnoreArchives) {
			return EOVReferens.QUAL_IGNORE_ROOT_AND_ARCHIVE;
  	}	else {
  		return EOVReferens.QUAL_IGNORE_ROOT;
  	}
  }
  
  // appels via NSKeyValueCoding (fetch et affichage)
  
	public void takeValueForKey(Object arg0, String arg1) {
	}

	public Object valueForKey(String value) {
		if (!StringCtrl.isEmpty(value)) {
			if (value.equals(EOVReferens.KEY_KEY)) {
				return record.key();
			}
			if (value.equals("displayLongName")) {
				return displayLongName();
			}
		}
		return null;
	}
}
