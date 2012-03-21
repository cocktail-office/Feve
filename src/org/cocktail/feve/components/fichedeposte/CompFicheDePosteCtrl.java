package org.cocktail.feve.components.fichedeposte;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControlerAndFilArianeNode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;

/**
 * Controleur du composant {@link CompFicheDePoste}
 * TODO passer {@link CompFicheDePoste} en mode contrôlé et virer le binding {@link CompFicheDePoste#inputLaFicheDePoste}
 * 
 * @author ctarade
 *
 */
public class CompFicheDePosteCtrl
	extends A_ComponentControlerAndFilArianeNode {

	/** binding : la fiche de poste a examiner*/
	public EOFicheDePoste ficheDePoste;
	
	/**
	 * @deprecated
	 * @see #CompFicheDePosteCtrl(Session, EOFicheDePoste)
	 * @param session
	 */
	public CompFicheDePosteCtrl(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param session
	 */
	public CompFicheDePosteCtrl(Session session, EOFicheDePoste aFicheDePoste) {
		super(session);
		ficheDePoste = aFicheDePoste;
	}

	@Override
	public A_ComponentControlerAndFilArianeNode child() {
		return null;
	}

	@Override
	protected void toLocalFullComponent() {

	}

}
