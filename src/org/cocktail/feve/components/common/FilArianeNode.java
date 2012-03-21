package org.cocktail.feve.components.common;

import com.webobjects.appserver.WOContext;

/**
 * Un noeud du fil d'ariane
 *
 * @author ctarade
 */
public class FilArianeNode extends A_ComponentControled {

	/** binding : le node a afficher */
	public A_ComponentControlerAndFilArianeNode node;
	
	public FilArianeNode(WOContext context) {
		super(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isNodeNull() {
		return node == null;
	}
	
	/**
	 * Determiner le dernier noeud visible
	 * - on le desactive
	 * - on lui met une css particuliere
	 * @return
	 */
	public boolean isLastNode() {
		boolean disabled = false;
		if (node.child() == null) {
			disabled = true;
		}
		if (!disabled) {
			A_ComponentControlerAndFilArianeNode localNode = node.child();
			boolean shouldContinue = true;
			disabled = true;
			while (shouldContinue && localNode != null) {
				if (localNode.showNode()) {
					shouldContinue = false;
					disabled = false;
				} else {
					localNode = localNode.child();
				}
			}
		}
		return disabled;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCssClass() {
		String cssClass = "";
		if (isLastNode()) {
			cssClass = "lastNode";
		}
		return cssClass;
	}
}