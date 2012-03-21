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

import org.cocktail.feve.eos.modele.grhum.EOVReferens;

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

public class CktlAjaxVReferensTreeViewCtrl {

	private CktlAjaxVReferensTreeView wocomponent;

	private Object _delegate;
	private VReferensNode rootVReferens;
	private VReferensNode unVReferens;
	private AjaxTreeModel myTreeModel = new AjaxTreeModel();
	//private static NSArray formations = null;

	public CktlAjaxVReferensTreeViewCtrl(CktlAjaxVReferensTreeView component) {
		wocomponent = component;
	}

	private EOEditingContext edc() {
		return wocomponent.edc();
	}

	public WOActionResults afficherFormationSelectionne() {
		VReferensNode node = unVReferens();
		if (node != null && node != rootVReferens()) {
			EOVReferens formation = (EOVReferens) node.object();
			((CktlAjaxVReferensTreeView) wocomponent).setSelection(formation);
		}
		return null;
	}

	public VReferensNode rootVReferens() {
		if (rootVReferens == null ||
				(wocomponent.treeRootObject() != null && rootVReferens.object() instanceof EmptyRootObject) ||
				(wocomponent.treeRootObject() == null && !(rootVReferens.object() instanceof EmptyRootObject))) {
			if (wocomponent.treeRootObject() != null) {
				rootVReferens = new VReferensNode(null, wocomponent.treeRootObject());
			}
			else {
				rootVReferens = new VReferensNode(null, new EmptyRootObject());
			}
		}
		return rootVReferens;
	}

	public void setRootVReferens(VReferensNode rootVReferens) {
		this.rootVReferens = rootVReferens;
	}

	public VReferensNode unVReferens() {
		return unVReferens;
	}

	public void setUnVReferens(VReferensNode unVReferens) {
		this.unVReferens = unVReferens;
	}

	public String unVReferensLibelle() {
		NSKeyValueCoding obj = unVReferens().object();
		return (String) obj.valueForKey(EOVReferens.LIBELLE_SEUL_KEY);
	}

	public AjaxTreeModel getMyTreeModel() {
		return myTreeModel;
	}
	
	public void setMyTreeModel(AjaxTreeModel myTreeModel) {
		this.myTreeModel = myTreeModel;
	}
	
	public CktlAjaxVReferensTreeView getWocomponent() {
		return wocomponent;
	}
	
	public void setWocomponent(CktlAjaxVReferensTreeView wocomponent) {
		this.wocomponent = wocomponent;
	}
	
	public VReferensNode selectObjectInTree(VReferensNode node, EOVReferens formation) {
		VReferensNode newSelectedNode = null;
		if (node.object().equals(formation)) {
			newSelectedNode = node;
		}
		else {
			Enumeration en = node.children().objectEnumerator();
			while (newSelectedNode == null && en.hasMoreElements()) {
				VReferensNode tmpNode = (VReferensNode) en.nextElement();
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
	        Enumeration<VReferensNode> enumeration = myTreeModel.depthFirstEnumeration(rootVReferens, false);
	        NSMutableArray<VReferensNode> nodesToPrune = new NSMutableArray<CktlAjaxVReferensTreeViewCtrl.VReferensNode>();
	        NSMutableArray<VReferensNode> nodesToKeep = new NSMutableArray<CktlAjaxVReferensTreeViewCtrl.VReferensNode>();
	        // On parcours les nodes affichées et on marque au fur et à mesure celles à garder selon le filtre
	        // Et celles à enlever
	        while (enumeration.hasMoreElements()) {
	            VReferensNode node = enumeration.nextElement();
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
	        for (VReferensNode node : nodesToPrune) {
	            if (!nodesToKeep.contains(node) && node.parent() != null && !(node.object instanceof EmptyRootObject)) {
	                NSMutableArray<VReferensNode> childrenMut = node.parent().children().mutableClone();
	                childrenMut.removeObject(node);
	                node.parent().children = childrenMut.immutableClone();
	            }
	        }
	    } else {
	        setRootVReferens(null);
	    }
	}
	   
    private void removeFromNodeToPruneAndAddToNodeToKeep(VReferensNode node, 
            NSMutableArray<VReferensNode> nodesToPrune, 
            NSMutableArray<VReferensNode> nodesToKeep) {
        nodesToPrune.removeObjectsInArray(node.parents());
        nodesToPrune.removeObject(node);
        nodesToKeep.addObject(node);
        nodesToKeep.addObjectsFromArray(node.parents());
    }
	
	public Object delegate() {
		if (_delegate == null) {
			_delegate = new CktlAjaxVReferensTreeViewCtrl.Delegate();
		}
		return _delegate;

	}

	public class Delegate implements AjaxTreeModel.Delegate {

		public NSArray childrenTreeNodes(Object node) {
			if (node != null) {
				return ((VReferensNode) node).children();
			}
			return NSArray.EmptyArray;
		}

		public boolean isLeaf(Object node) {
			if (node != null) {
				return ((VReferensNode) node).isLeaf();
			}
			return true;
		}

		public Object parentTreeNode(Object node) {
			if (node != null) {
				return ((VReferensNode) node).parent();
			}
			return null;
		}
	}

	public class VReferensNode {
		NSArray children;
		VReferensNode parent;
		NSKeyValueCoding object;

		public VReferensNode(VReferensNode parent, NSKeyValueCoding obj) {
			object = obj;
			this.parent = parent;
		}

		public NSArray children() {
			if (children == null) {
				NSArray<EOVReferens> res = NSArray.EmptyArray;
				if (object() instanceof EmptyRootObject) {
					res = EOVReferens.getRacines(edc());
				}	else {
					
					NSMutableArray array = new NSMutableArray();
					
					// doit etre fils direct de la formation en cours
					array.addObject(
							new EOKeyValueQualifier(
									EOVReferens.TO_V_REFERENS_PERE_KEY, 
									EOKeyValueQualifier.QualifierOperatorEqual, 
									object()));
					
					// pas de boucle infinie
					array.addObject(
							new EOKeyValueQualifier(
									EOVReferens.KEY_KEY, 
									EOKeyValueQualifier.QualifierOperatorNotEqual, 
									object().valueForKey(EOVReferens.KEY_KEY)));
					
					// qualifier additionnel (notamment celui du like qui est automatiquement généré)
					if (wocomponent.getQualifier() != null) {
						array.addObject(wocomponent.getQualifier());
					}
					
					res = EOVReferens.rechercher(edc(), new EOAndQualifier(array), 0);			
				}
		
				NSMutableArray res2 = new NSMutableArray();
				for (EOVReferens formation : res) {
					VReferensNode node = new VReferensNode(this, formation);
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

		public VReferensNode parent() {
			return parent;
		}

		public void setParent(VReferensNode node) {
			parent = node;
		}

		public NSKeyValueCoding object() {
			return object;
		}

		public NSArray<VReferensNode> parents() {
		    NSMutableArray<VReferensNode> _parents = new NSMutableArray<CktlAjaxVReferensTreeViewCtrl.VReferensNode>();
		    _parentsRecurs(this, _parents);
		    return _parents.immutableClone();
		}
		
		private void _parentsRecurs(VReferensNode node, NSMutableArray<VReferensNode> parents) {
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
            VReferensNode other = (VReferensNode) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (object == null) {
                if (other.object != null)
                    return false;
            } else if (!object.equals(other.object))
                return false;
            return true;
        }

        private CktlAjaxVReferensTreeViewCtrl getOuterType() {
            return CktlAjaxVReferensTreeViewCtrl.this;
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
