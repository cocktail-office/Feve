package org.cocktail.feve.eos.modele.mangue;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Descriptif d'une repartition entre competence et fiche de poste
 * @author ctarade
 *
 */
public interface I_RepartCompetence {

	/** le libelle de la competence */
	public String competenceDisplay();
	
	/** acces a la liste des niveau associés */
	public NSArray tosRepartNiveauComp(EOQualifier qualifier);
	
}
