/*
 * Copyright Universit� de La Rochelle 2005
 *
 * ctarade@univ-lr.fr
 *
 * Ce logiciel est un programme informatique servant � g�rer les comptes
 * informatiques des utilisateurs. 
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".

 * En contrepartie de l'accessibilit� au code source et des droits de copie,
 * de modification et de redistribution accord�s par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
 * seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les conc�dants successifs.

 * A cet �gard  l'attention de l'utilisateur est attir�e sur les risques
 * associ�s au chargement,  � l'utilisation,  � la modification et/ou au
 * d�veloppement et � la reproduction du logiciel par l'utilisateur �tant 
 * donn� sa sp�cificit� de logiciel libre, qui peut le rendre complexe � 
 * manipuler et qui le r�serve donc � des d�veloppeurs et des professionnels
 * avertis poss�dant  des  connaissances  informatiques approfondies.  Les
 * utilisateurs sont donc invit�s � charger  et  tester  l'ad�quation  du
 * logiciel � leurs besoins dans des conditions permettant d'assurer la
 * s�curit� de leurs syst�mes et ou de leurs donn�es et, plus g�n�ralement, 
 * � l'utiliser et l'exploiter dans les m�mes conditions de s�curit�. 

 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accept� les
 * termes.
 */
package org.cocktail.feve.app.finder;

import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOHierarchie;
import org.cocktail.feve.eos.modele.mangue.EOStructureInfo;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

/**
 * @author ctarade 31 mars 2005
 * 
 */
public class FinderFeve extends Finder {

	/**
	 * liste tous les champ libres associes a la fiche de poste (emploi type,
	 * mission composante, service ...)
	 */
	public static NSDictionary findDicoFicheDePosteInContext(EOEditingContext ec, EOFicheDePoste fiche) {
		NSMutableDictionary dico = new NSMutableDictionary();

		if (fiche != null) {
			// la date pour recuperer les infos des structure
			// - si la date est la fiche en cours : today
			// - si date passe, on prend la fin de la fiche
			NSTimestamp dateRef = null;
			if (fiche.fdpDFin() != null && DateCtrl.isAfter(DateCtrl.now(), fiche.fdpDFin())) {
				dateRef = fiche.fdpDFin();
			} else {
				dateRef = DateCtrl.now();
			}

			EOStructureInfo laMissionComposante = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
					ec,
					fiche.toPoste().toStructure().toComposante(),
					dateRef,
					EOStructureInfo.TYPE_MISSION_COMPOSANTE
					);
			if (laMissionComposante != null && !StringCtrl.isEmpty(laMissionComposante.sinLibelle())) {
				dico.setObjectForKey(laMissionComposante.sinLibelle(), "missionComposante");
			} else {
				dico.setObjectForKey("", "missionComposante");
			}

			EOStructureInfo laMissionService = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
					ec,
					fiche.toPoste().toStructure(),
					dateRef,
					EOStructureInfo.TYPE_MISSION_SERVICE
					);
			if (laMissionService != null && !StringCtrl.isEmpty(laMissionService.sinLibelle())) {
				dico.setObjectForKey(laMissionService.sinLibelle(), "missionService");
			} else {
				dico.setObjectForKey("", "missionService");
			}

			EOStructureInfo leProjetService = EOStructureInfo.findStructureInfoForStructureAndDateAndTypeInContext(
					ec,
					fiche.toPoste().toStructure(),
					dateRef,
					EOStructureInfo.TYPE_PROJET_SERVICE
					);
			if (leProjetService != null && !StringCtrl.isEmpty(leProjetService.sinLibelle())) {
				dico.setObjectForKey(leProjetService.sinLibelle(), "projetService");
			} else {
				dico.setObjectForKey("", "projetService");
			}

			if (!StringCtrl.isEmpty(fiche.fdpMissionPoste())) {
				dico.setObjectForKey(fiche.fdpMissionPoste(), "missionPoste");
			} else {
				dico.setObjectForKey("", "missionPoste");
			}

			if (!StringCtrl.isEmpty(fiche.fdpContexteTravail())) {
				dico.setObjectForKey(fiche.fdpContexteTravail(), "contexte");
			} else {
				dico.setObjectForKey("", "contexte");
			}

			// composante - service
			dico.setObjectForKey(fiche.toPoste().toStructure().toComposante().llStructure(), "composante");
			dico.setObjectForKey(fiche.toPoste().toStructure().llStructure(), "structure");

			// dcp - famille - emploi type
			if (fiche.toReferensEmplois() != null) {
				dico.setObjectForKey(fiche.toReferensEmplois().intitulemploi(), "emploiType");
				dico.setObjectForKey(fiche.toReferensEmplois().toReferensFp().intitulfp(), "famillePro");
				dico.setObjectForKey(fiche.toReferensEmplois().toReferensFp().toReferensDcp().intituldcp(), "dcp");
			} else {
				dico.setObjectForKey("", "emploiType");
				dico.setObjectForKey("", "famillePro");
				dico.setObjectForKey("", "dcp");
			}

			// libelle
			dico.setObjectForKey(fiche.display(), "libelle");
		}

		return dico.immutableClone();
	}

	/**
	 * le dictionnaire contenant toutes les infos pour l'impression de
	 * l'evaluation (objectifs, niveau des competences ...)
	 */
	public static NSDictionary<String, Object> findDicoEvaluationInContext(EOEditingContext ec, EOEvaluation evaluation) {
		NSMutableDictionary<String, Object> dico = new NSMutableDictionary<String, Object>();

		if (evaluation != null) {
			// responsable hierarchique
			EOHierarchie hierarchie = EOHierarchie.findHierarchieForIndividuInContext(
					ec, evaluation.toIndividu(), evaluation.toEvaluationPeriode());
			if (hierarchie != null && hierarchie.toIndividuResp() != null) {
				dico.setObjectForKey(hierarchie.toIndividuResp().display(), "responsable");
			} else {
				dico.setObjectForKey("aucun", "responsable");
			}

			// periode
			/*
			 * dico.setObjectForKey("du " +
			 * DateCtrl.dateToString(evaluation.toEvaluationPeriode().epeDDebut()) +
			 * " au " +
			 * DateCtrl.dateToString(evaluation.toEvaluationPeriode().epeDFin()),
			 * "periode");
			 */
			dico.setObjectForKey(evaluation.toEvaluationPeriode().strAnneeDebutAnneeFin(), "periode");

			NSArray fiches = evaluation.tosLastFicheDePoste();
			/*
			 * // composantes - services NSArray services = (NSArray)
			 * fiches.valueForKey("toPoste.toStructure.llStructure"); services =
			 * NSArrayCtrl.removeDuplicate(services); String strServices = ""; for
			 * (int i = 0; i < services.count(); i++) { String unService = (String)
			 * services.objectAtIndex(i); strServices += unService; if (i !=
			 * services.count() - 1) { strServices += " ; "; } }
			 * dico.setObjectForKey(strServices, "services");
			 */

			NSArray services = evaluation.tosStructure();
			String strServices = "";
			for (int i = 0; i < services.count(); i++) {
				EOStructure unService = (EOStructure) services.objectAtIndex(i);
				strServices += unService.llStructure();
				if (i != services.count() - 1) {
					strServices += " ; ";
				}
			}
			dico.setObjectForKey(strServices, "services");

			// emplois types
			NSArray emplois = new NSArray();
			String strEmplois = "";
			for (int i = 0; i < fiches.count(); i++) {
				EOFicheDePoste uneFicheDePoste = (EOFicheDePoste) fiches.objectAtIndex(i);
				if (uneFicheDePoste.toReferensEmplois() != null) {
					strEmplois += uneFicheDePoste.toReferensEmplois().intitulemploi();
					if (i < emplois.count() - 1) {
						strEmplois += " ; ";
					}
				}
			}
			dico.setObjectForKey(strEmplois, "emplois");

			// evaluation precedente
			EOEvaluation evaluationPrec = evaluation.toEvaluationPrecedente();
			if (evaluationPrec != null) {
				dico.setObjectForKey(evaluationPrec, "lEvaluationPrecedente");
			}

			// evaluation actuelle
			dico.setObjectForKey(evaluation, "lEvaluationEnCours");

			// evaluation suivante
			EOEvaluation evaluationSuiv = evaluation.toEvaluationSuivante();
			if (evaluationSuiv != null) {
				dico.setObjectForKey(evaluationSuiv, "lEvaluationSuivante");
			}
		}

		return dico.immutableClone();
	}

	/**
	 * trouver un record pour un individu selon un nom et/ou un prenom
	 * 
	 * @param ec
	 * @param nomOuPrenom
	 * @param entity
	 * @param prefix
	 * @return
	 */
	public static NSArray filterIndividuForNomOrPrenomInArray(NSArray array, String nomOuPrenom, String prefix) {
		// mise en caps
		nomOuPrenom = nomOuPrenom.toUpperCase();
		NSArray records = new NSArray();
		NSArray mots = NSArray.componentsSeparatedByString(nomOuPrenom, " ");
		String premierMot, deuxiemeMot;
		premierMot = deuxiemeMot = "";
		String localPrefix = (prefix != null ? prefix : StringCtrl.emptyString());
		if (mots.count() > 0) {
			premierMot = (String) mots.objectAtIndex(0);
			if (mots.count() > 1) {
				deuxiemeMot = (String) mots.objectAtIndex(1);
			}
			NSArray args = new NSArray(new String[] { "*" + nomOuPrenom + "*", "*" + nomOuPrenom + "*", "*" + premierMot + "*", "*" + deuxiemeMot + "*", "*" + premierMot + "*", "*" + deuxiemeMot + "*" });
			String strQual =
					localPrefix + "nomUsuel like %@ OR " + localPrefix + "prenom like %@ OR (" +
							localPrefix + "nomUsuel like %@ AND " + localPrefix + "prenom like %@) OR (" +
							localPrefix + "prenom like %@ AND " + localPrefix + "nomUsuel like %@)";
			EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(strQual, args);
			NSArray arraySort = new NSArray(EOSortOrdering.sortOrderingWithKey(localPrefix + "nomUsuel", EOSortOrdering.CompareAscending));
			records = EOQualifier.filteredArrayWithQualifier(array, qual);
			records = EOSortOrdering.sortedArrayUsingKeyOrderArray(records, arraySort);
			records = NSArrayCtrl.removeDuplicate(records);
		}
		return records;
	}
}
