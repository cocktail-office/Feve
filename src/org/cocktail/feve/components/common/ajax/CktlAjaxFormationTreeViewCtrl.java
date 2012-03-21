/*
 * Copyright COCKTAIL (www.cocktail.org), 1995, 2010 This software 
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

import java.util.Enumeration;

import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSMutableArray;

import er.ajax.AjaxTreeModel;

/**
 * @author Rodolphe PRIN <rodolphe.prin at cocktail.org>
 * @author Alexis TUAL <alexis.tual at cocktail.org>
 */

public class CktlAjaxFormationTreeViewCtrl {

	private CktlAjaxFormationTreeView wocomponent;

	private Object _delegate;
	private FormationNode rootFormation;
	private FormationNode unFormation;
	private AjaxTreeModel myTreeModel = new AjaxTreeModel();
	//private static NSArray formations = null;

	public CktlAjaxFormationTreeViewCtrl(CktlAjaxFormationTreeView component) {
		wocomponent = component;
	}

	private EOEditingContext edc() {
		return wocomponent.edc();
	}

	public WOActionResults afficherFormationSelectionne() {
		FormationNode node = unFormation();
		if (node != null && node != rootFormation()) {
			EOFormationPersonnel formation = (EOFormationPersonnel) node.object();
			((CktlAjaxFormationTreeView) wocomponent).setSelection(formation);
		}
		return null;
	}

	public FormationNode rootFormation() {
		if (rootFormation == null ||
				(wocomponent.treeRootObject() != null && rootFormation.object() instanceof EmptyRootObject) ||
				(wocomponent.treeRootObject() == null && !(rootFormation.object() instanceof EmptyRootObject))) {
			if (wocomponent.treeRootObject() != null) {
				rootFormation = new FormationNode(null, wocomponent.treeRootObject());
			}
			else {
				rootFormation = new FormationNode(null, new EmptyRootObject());
			}
		}
		return rootFormation;
	}

	public void setRootFormation(FormationNode rootFormation) {
		this.rootFormation = rootFormation;
	}

	public FormationNode unFormation() {
		return unFormation;
	}

	public void setUnFormation(FormationNode unFormation) {
		this.unFormation = unFormation;
	}

	public String unFormationLibelle() {
		NSKeyValueCoding obj = unFormation().object();
		return (String) obj.valueForKey(EOFormationPersonnel.FOR_LIBELLE_KEY);
	}

	/**
	 * On autorise que la saisie de feuilles
	 * @return
	 */
	public Boolean isFormationSelectionnable() {
		boolean isFormationSelectionnable = false;
		FormationNode node = unFormation();
		if (node != null && node != rootFormation()) {
			EOFormationPersonnel formation = (EOFormationPersonnel) node.object();
			isFormationSelectionnable = formation.isFeuille();
		}
		return isFormationSelectionnable;
	}

	public AjaxTreeModel getMyTreeModel() {
		return myTreeModel;
	}
	
	public void setMyTreeModel(AjaxTreeModel myTreeModel) {
		this.myTreeModel = myTreeModel;
	}
	
	public CktlAjaxFormationTreeView getWocomponent() {
		return wocomponent;
	}
	
	public void setWocomponent(CktlAjaxFormationTreeView wocomponent) {
		this.wocomponent = wocomponent;
	}
	
	public FormationNode selectObjectInTree(FormationNode node, EOFormationPersonnel formation) {
		FormationNode newSelectedNode = null;
		if (node.object().equals(formation)) {
			newSelectedNode = node;
		}
		else {
			Enumeration en = node.children().objectEnumerator();
			while (newSelectedNode == null && en.hasMoreElements()) {
				FormationNode tmpNode = (FormationNode) en.nextElement();
				newSelectedNode = selectObjectInTree(tmpNode, formation);
			}
		}
		if (newSelectedNode != null) {
			myTreeModel.setExpanded(node, true);
		}
		return newSelectedNode;
	}
	

	/**
	 * 
	 * Cette méthode filtre les nodes affichées dans l'arbre selon le qualifier.
	 * L'algo est le suivant :
	 * - Premier parcours de l'arbre "depthFirst"
	 *     - Si une node satisfait le filtre, on l'enregistre dans un tableau ainsi que tous ses parents
	 *       (pour que son affichage soit garantie)
	 *     - Sinon on l'ajoute dans un tableau
	 * - On parcourt le tableau des nodes à supprimer et on réorganise l'arbre en fonction (suppression 
	 *     de la node du tableau des enfants de son parent (ouf).
	 *     
	 *  Cette méthode n'est pas utilisée pour l'instant...  
	 *     
	 */
	private void filterTree() {
	    if (wocomponent.getQualifier() != null) {
	        Enumeration<FormationNode> enumeration = myTreeModel.depthFirstEnumeration(rootFormation, false);
	        NSMutableArray<FormationNode> nodesToPrune = new NSMutableArray<CktlAjaxFormationTreeViewCtrl.FormationNode>();
	        NSMutableArray<FormationNode> nodesToKeep = new NSMutableArray<CktlAjaxFormationTreeViewCtrl.FormationNode>();
	        // On parcours les nodes affichées et on marque au fur et à mesure celles à garder selon le filtre
	        // Et celles à enlever
	        while (enumeration.hasMoreElements()) {
	            FormationNode node = enumeration.nextElement();
	            if (!(node.object instanceof EmptyRootObject) &&
	                    !wocomponent.getQualifier().evaluateWithObject(node.object)) {
	                nodesToPrune.addObject(node);
	            } else {
	                // On rajoute la node dans les nodes à garder et on enlève ses parents
	                // des nodes à enlever
	                removeFromNodeToPruneAndAddToNodeToKeep(node, nodesToPrune, nodesToKeep);
	            }
	        }
	        // On parcours les nodes à enlever, et on réorganise l'arbre
	        for (FormationNode node : nodesToPrune) {
	            if (!nodesToKeep.contains(node) && node.parent() != null && !(node.object instanceof EmptyRootObject)) {
	                NSMutableArray<FormationNode> childrenMut = node.parent().children().mutableClone();
	                childrenMut.removeObject(node);
	                node.parent().children = childrenMut.immutableClone();
	            }
	        }
	    } else {
	        setRootFormation(null);
	    }
	}
	   
    private void removeFromNodeToPruneAndAddToNodeToKeep(FormationNode node, 
            NSMutableArray<FormationNode> nodesToPrune, 
            NSMutableArray<FormationNode> nodesToKeep) {
        nodesToPrune.removeObjectsInArray(node.parents());
        nodesToPrune.removeObject(node);
        nodesToKeep.addObject(node);
        nodesToKeep.addObjectsFromArray(node.parents());
    }
	
	public Object delegate() {
		if (_delegate == null) {
			_delegate = new CktlAjaxFormationTreeViewCtrl.Delegate();
		}
		return _delegate;

	}

	public class Delegate implements AjaxTreeModel.Delegate {

		public NSArray childrenTreeNodes(Object node) {
			if (node != null) {
				return ((FormationNode) node).children();
			}
			return NSArray.EmptyArray;
		}

		public boolean isLeaf(Object node) {
			if (node != null) {
				return ((FormationNode) node).isLeaf();
			}
			return true;
		}

		public Object parentTreeNode(Object node) {
			if (node != null) {
				return ((FormationNode) node).parent();
			}
			return null;
		}
	}

	public class FormationNode {
		NSArray children;
		FormationNode parent;
		NSKeyValueCoding object;

		public FormationNode(FormationNode parent, NSKeyValueCoding obj) {
			object = obj;
			this.parent = parent;
		}

		public NSArray children() {
			if (children == null) {
				NSArray<EOFormationPersonnel> res = NSArray.EmptyArray;
				if (object() instanceof EmptyRootObject) {
					res = EOFormationPersonnel.getRacines(edc());
				}	else {
					
					NSMutableArray array = new NSMutableArray();
					
					// doit etre fils direct de la formation en cours
					array.addObject(
							new EOKeyValueQualifier(
									EOFormationPersonnel.TO_FORMATION_PERE_KEY, 
									EOKeyValueQualifier.QualifierOperatorEqual, 
									object()));
					
					// pas de boucle infinie
					array.addObject(
							new EOKeyValueQualifier(
									EOFormationPersonnel.FOR_ID_KEY, 
									EOKeyValueQualifier.QualifierOperatorNotEqual, 
									object().valueForKey(EOFormationPersonnel.FOR_ID_KEY)));
					
					// qualifier additionnel (notamment celui du like qui est automatiquement généré)
					if (wocomponent.getQualifier() != null) {
						array.addObject(wocomponent.getQualifier());
					}
					
					res = EOFormationPersonnel.rechercher(edc(), new EOAndQualifier(array), 0);			
				}
		
				NSMutableArray res2 = new NSMutableArray();
				for (EOFormationPersonnel formation : res) {
					FormationNode node = new FormationNode(this, formation);
					res2.addObject(node);
				}

				children = res2.immutableClone();
				
			}

			return children;
		}

		public void setChildren(NSArray children) {
            this.children = children;
        }
		
		public boolean isLeaf() {
			return (children().count() == 0);
		}

		public FormationNode parent() {
			return parent;
		}

		public void setParent(FormationNode node) {
			parent = node;
		}

		public NSKeyValueCoding object() {
			return object;
		}

		public NSArray<FormationNode> parents() {
		    NSMutableArray<FormationNode> _parents = new NSMutableArray<CktlAjaxFormationTreeViewCtrl.FormationNode>();
		    _parentsRecurs(this, _parents);
		    return _parents.immutableClone();
		}
		
		private void _parentsRecurs(FormationNode node, NSMutableArray<FormationNode> parents) {
		    if (node.parent() != null) {
		        parents.addObject(node.parent());
		        _parentsRecurs(node.parent, parents);
		    }
		}
		
		@Override
		public String toString() {
		    return object != null ? object.toString() : super.toString();
		}
		
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((object == null) ? 0 : object.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            FormationNode other = (FormationNode) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (object == null) {
                if (other.object != null)
                    return false;
            } else if (!object.equals(other.object))
                return false;
            return true;
        }

        private CktlAjaxFormationTreeViewCtrl getOuterType() {
            return CktlAjaxFormationTreeViewCtrl.this;
        }
		
	}

	public class EmptyRootObject implements NSKeyValueCoding {

		public EmptyRootObject() {
		}

		public void takeValueForKey(Object arg0, String arg1) {

		}

		public Object valueForKey(String arg0) {
			return "root";
		}

	}

}
