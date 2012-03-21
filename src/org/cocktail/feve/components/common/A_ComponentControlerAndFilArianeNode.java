package org.cocktail.feve.components.common;

import org.cocktail.feve.app.Session;

/**
 * Description d'un noeud de la file d'ariane
 * 
 * @author ctarade
 */
public abstract class A_ComponentControlerAndFilArianeNode 
	extends A_ComponentControler {

	/** affichage du lien O/N */
	private boolean showNode;
	/** libelle du lien */
	private String linkLabel;
	/** info bulle du lien */
	private String linkTitle;
	/** libelle du dernier element (qui n'est pas un lien mais un titre) */
	private String stringLabel;
	
	public A_ComponentControlerAndFilArianeNode(Session session) {
		super(session);
		this.initCtrl();
	}
	
	private void initCtrl() {
		// on affiche par defaut
		showNode = true;
		linkLabel = "<=== lien de retour ===>";
		stringLabel = "<=== titre ===>";
	}

	/** passer le composant en page pleine (uniquement ses attributs "locaux") */
	protected abstract void toLocalFullComponent();
	
	/** le noeud fils */
	public abstract A_ComponentControlerAndFilArianeNode child();
	
	/** passer l'ensemble de la page et ses fils en page pleine */
	public final void toFullComponent() {
		A_ComponentControlerAndFilArianeNode child = child();
		if (child != null) {
			child.toFullComponent();
		}
		toLocalFullComponent();
	}
	
	public final boolean showNode() {
		return showNode;
	}

	public final String getLinkLabel() {
		return linkLabel;
	}
	
	public final String getLinkTitle() {
		return linkTitle;
	}
	
	public final String getStringLabel() {
		return stringLabel;
	}
	
	public final void setShowNode(boolean value) {
		showNode = value;
	}
	
	public final void setLinkLabel(String value) {
		linkLabel = value;
	}
	
	public final void setLinkTitle(String value) {
		linkTitle = value;
	}
	
	public final void setStringLabel(String value) {
		stringLabel = value;
	}
}
