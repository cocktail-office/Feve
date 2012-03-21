/*
 * Copyright Universit� de La Rochelle 2008
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
package org.cocktail.feve.app.print;

import java.io.IOException;
import java.util.Hashtable;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.app.finder.FinderFeve;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EONiveauCompetence;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationNoticePromotion;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOIndividuFormations;
import org.cocktail.feve.eos.modele.mangue.EOObjectif;
import org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpComp;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheItem;
import org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee;
import org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp;
import org.cocktail.feve.eos.modele.mangue.EOSituActivite;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;
import org.cocktail.feve.eos.modele.mangue.EOTplItem;
import org.cocktail.feve.eos.modele.mangue.EOTplItemValeur;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;
import org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur;
import org.cocktail.feve.eos.modele.mangue.I_RepartCompetence;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.CktlXMLWriter;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Edition PDF/SIX de la fiche d'evaluation
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class PrintEvaluation extends GenericSixPrint {

	/** indique si la fiche a imprimer est un brouillon ou non */
	private boolean isBrouillon;

	public PrintEvaluation(Session session,
			NSDictionary dico, FevePdfBoxCtrl ctrl) {
		super(session,
				PrintConsts.PDF_FEV_FICHE_EVALUATION,
				PrintConsts.ID_FEV_FICHE_EVALUATION,
				PrintConsts.XML_FEV_FICHE_EVALUATION,
				dico,
				ctrl);
	}

	/**
	 * 
	 */
	protected void generateXml(CktlXMLWriter w) throws IOException {

		// recuperer la fiche
		EOEvaluation evaluation = (EOEvaluation) dico().objectForKey("evaluation");

		// vierge ou pas ?
		isBrouillon = ((Boolean) dico().objectForKey("isEmptyEvaluation")).booleanValue();

		// recup du dico gepeto de l'occupant de la fiche
		NSMutableDictionary dico = new NSMutableDictionary();
		NSDictionary dicoAgent = EOIndividu.findDicoAgentGepetoInContext(ec(), evaluation);
		dico.addEntriesFromDictionary(dicoAgent);

		// recup du dico de l'environnement de la fiche
		dico.addEntriesFromDictionary(FinderFeve.findDicoEvaluationInContext(ec(), evaluation));
		dico = new NSMutableDictionary(cleanDico(dico.immutableClone()));

		w.setEscapeSpecChars(true);
		w.startDocument();
		w.writeComment("Edition de la fiche d evaluation");

		// liste des onglets attendus pour cette évaluation
		EOEvaluationPeriode periode = evaluation.toEvaluationPeriode();
		NSArray<EOTplOnglet> eoTplOngletArray = feveSession().getEoTplFicheEvaluation().tosOnglet(periode, evaluation);

		w.startElement("FevFicheEvaluation");
		{

			EOStructure structureRacine = EOStructure.findRacineInContext(ec());
			if (structureRacine != null && !StringCtrl.isEmpty(structureRacine.llStructure())) {
				w.writeElement(PrintConsts.XML_KEY_ETABLISSEMENT, structureRacine.llStructure());
			}

			w.writeElement("periode", checkString((String) dico.objectForKey("periode")));
			w.writeElement("identifiant", checkString((String) dico.objectForKey("identifiant")));
			w.writeElement("nom", checkString((String) dico.objectForKey("nomUsuel")));
			w.writeElement("prenom", checkString((String) dico.objectForKey("prenom")));
			w.writeElement("dNaissance", checkString((String) dico.objectForKey("dNaissance")));
			w.writeElement("statut", checkString((String) dico.objectForKey("statut")));

			w.writeElement("corps", checkString((String) dico.objectForKey("corps")));
			w.writeElement("grade", checkString((String) dico.objectForKey("grade")));

			w.writeElement("population", checkString(evaluation.getLibellePopulation()));

			w.writeElement("emploiType", checkString((String) dico.objectForKey("emplois")));
			w.writeElement("structure", checkString((String) dico.objectForKey("services")));

			w.writeElement("responsable", checkString((String) dico.objectForKey("responsable")));

			// page 1 : objectifs precedents
			EOEvaluation lEvaluationEnCours = (EOEvaluation) dico.objectForKey("lEvaluationEnCours");
			EOEvaluation lEvaluationPrecedente = (EOEvaluation) dico.objectForKey("lEvaluationPrecedente");
			w.startElement("objectifsPrecedents");
			if (lEvaluationPrecedente != null && lEvaluationPrecedente.tosObjectif() != null) {
				w.writeElement("periodePrecedente", lEvaluationEnCours.toEvaluationPeriode().strAnneeDebutAnneeFin());
				for (int i = 0; i < lEvaluationPrecedente.tosObjectif().count(); i++) {
					EOObjectif objectif = (EOObjectif) lEvaluationPrecedente.tosObjectif().objectAtIndex(i);
					w.startElement("objectifPrecedent");
					{
						w.writeElement("objectif", checkString(objectif.objObjectif()));
						w.writeElement("moyen", checkString(objectif.objMoyen()));
						w.writeElement("mesure", checkString(objectif.objMesure()));
						w.writeElement("resultats", checkString(objectif.objResultat()));
						w.writeElement("observation", checkString(objectif.objObservation()));
					}
					w.endElement();
				}
			} else {
				// indiquer qu'il n'y a pas de periode precedent
				w.writeElement("periodePrecedente", "<inconnue>");
			}
			w.endElement();

			// page 2 : objectifs a venir
			w.startElement("objectifsSuivants");
			if (lEvaluationEnCours != null && lEvaluationEnCours.tosObjectif() != null) {
				w.writeElement("periodeSuivante", lEvaluationEnCours.toEvaluationPeriode().toNextPeriode() != null ?
						lEvaluationEnCours.toEvaluationPeriode().toNextPeriode().strAnneeDebutAnneeFin() : "<inconnue>");
				for (int i = 0; i < lEvaluationEnCours.tosObjectif().count(); i++) {
					EOObjectif objectif = (EOObjectif) lEvaluationEnCours.tosObjectif().objectAtIndex(i);
					w.startElement("objectifSuivant");
					{
						w.writeElement("objectif", isBrouillon ? PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT : checkString(objectif.objObjectif()));
						w.writeElement("moyen", isBrouillon ? PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT : checkString(objectif.objMoyen()));
						w.writeElement("mesure", isBrouillon ? PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT : checkString(objectif.objMesure()));
						w.writeElement("observation", isBrouillon ? PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT : checkString(objectif.objObservation()));
					}
					w.endElement();
				}
			}
			w.endElement();

			// page 3 : situations d activites

			// est-ce un onglet à afficher ?
			if (feveSession().getEoTplOngletSituationActivite() != null &&
					eoTplOngletArray.containsObject(feveSession().getEoTplOngletSituationActivite())) {
				w.startElement("situations");
				if (lEvaluationEnCours.tosSituActivite() != null) {
					if (isBrouillon) {
						for (int i = 0; i < 3; i++) {
							w.startElement("situation");
							{
								w.writeElement("numero", (new Integer(i + 1)).toString());
								w.writeElement(PrintConsts.XML_KEY_LIBELLE, PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT);
							}
							w.endElement();
						}
					} else {
						for (int i = 0; i < lEvaluationEnCours.tosSituActivite().count(); i++) {
							w.startElement("situation");
							{
								EOSituActivite situation = (EOSituActivite) lEvaluationEnCours.tosSituActivite().objectAtIndex(i);
								w.writeElement("numero", (new Integer(i + 1)).toString());
								w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString(situation.sacSituation()));
							}
							w.endElement();
						}
					}
				}
				w.endElement();
			}

			// page 4 : competences + proposition
			NSArray fiches = lEvaluationEnCours.tosLastFicheDePoste();
			w.startElement("competences");
			for (int i = 0; i < fiches.count(); i++) {
				w.startElement("fdp");
				EOFicheDePoste fiche = (EOFicheDePoste) fiches.objectAtIndex(i);

				// la liste des competences
				NSArray repartList = fiche.tosRepartFdpComp();
				// si la configuration l'autorise, on ajoute les competences autres
				if (feveUserInfo().isFicheDePosteSaisieCompetenceAutre()) {
					repartList = repartList.arrayByAddingObjectsFromArray(fiche.tosRepartFdpCompetencesAutres());
				}

				for (int j = 0; j < repartList.count(); j++) {
					// w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString((String)
					// fiche.display()));
					// w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString((String)
					// fiche.toReferensEmplois().intitulemploi()));

					String intitulemploi = "";
					if (fiche.toReferensEmplois() != null) {
						intitulemploi = fiche.toReferensEmplois().intitulemploi();
					}
					w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString(intitulemploi));

					I_RepartCompetence uneRepart = (I_RepartCompetence) repartList.objectAtIndex(j);

					if (uneRepart instanceof EORepartFdpComp) {
						w.startElement(PrintConsts.XML_KEY_COMPETENCE);
					} else {
						w.startElement(PrintConsts.XML_KEY_COMPETENCE_AUTRE);
					}

					{
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString((String) uneRepart.competenceDisplay()));
						EORepartNiveauComp repartNiveau = null;
						NSArray niveauList = uneRepart.tosRepartNiveauComp(
								CktlDataBus.newCondition(EORepartNiveauComp.TO_EVALUATION_KEY + "=%@", new NSArray(evaluation)));
						if (niveauList.count() > 0) {
							repartNiveau = (EORepartNiveauComp) niveauList.lastObject();
						}
						if (isBrouillon) {
							// brouillon : la liste de toutes les valeurs possible separees
							// par des "/"
							NSArray nivCompList = evaluation.toEvaluationPeriode().niveauCompetenceList();
							StringBuffer strNivCompList = new StringBuffer();
							for (int k = 0; k < nivCompList.count(); k++) {
								EONiveauCompetence nivComp = (EONiveauCompetence) nivCompList.objectAtIndex(k);
								strNivCompList.append(nivComp.ncpLibelle());
								// on separe avec des /
								if (k < nivCompList.count() - 1) {
									strNivCompList.append(" / ");
								}
							}
							w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, strNivCompList.toString());

						} else {
							if (repartNiveau != null && repartNiveau.toNiveauCompetence() != null) {
								w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, checkString((String) repartNiveau.toNiveauCompetence().ncpLibelle()));
							} else {
								w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, PrintConsts.XML_VALUE_NON_RENSEIGNE);
							}
						}
					}
					w.endElement(); // "competence" ou "competenceAutre"

				}
				w.endElement(); // "fdp"
			}
			w.endElement();

			// competencesProfessionnellesEtTechnicites
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_COMPETENCES_PROFESSIONNELLES_ET_TECHNICITE);
			feedXMLWriterForBloc(w, evaluation, EOTplBloc.TPL_BLOC_COMPETENCES_PROFESSIONNELLES_ET_TECHNICITE_CODE, isBrouillon);
			w.endElement();

			// "management"
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_MANAGEMENT);
			boolean hasManagement = EORepartFicheBlocActivation.isActif(ec(), EOTplBloc.TPL_BLOC_MANAGEMENT_CODE, evaluation);
			if (hasManagement) {
				feedXMLWriterForBloc(w, evaluation, EOTplBloc.TPL_BLOC_MANAGEMENT_CODE, isBrouillon);
				w.writeElement(PrintConsts.XML_KEY_EVALUATION_HAS_MANAGEMENT, PrintConsts.XML_VALUE_TRUE);
			} else {
				w.writeElement(PrintConsts.XML_KEY_EVALUATION_HAS_MANAGEMENT, PrintConsts.XML_VALUE_FALSE);
			}
			w.endElement(); // "management"

			// contributionALActiviteDuService
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_CONTRIBUTION_A_L_ACTIVITE_DU_SERVICE);
			feedXMLWriterForBloc(w, evaluation, EOTplBloc.TPL_BLOC_CONTRIBUTION_A_L_ACTIVITE_DU_SERVICE_CODE, isBrouillon);
			w.endElement(); // "contributionALActiviteDuService"

			// aualitesPersonnellesEtRelationnelles
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_QUALITES_PERSONNELLES_ET_RELATIONNELLES);
			feedXMLWriterForBloc(w, evaluation, EOTplBloc.TPL_BLOC_QUALITES_PERSONNELLES_ET_RELATIONNELLES_CODE, isBrouillon);
			w.endElement(); // "aualitesPersonnellesEtRelationnelles"

			// formationsSuivies
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATIONS_SUIVIES);
			if (isBrouillon) {
				for (int i = 0; i < 3; i++) {
					w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATION_SUIVIE);
					{
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
						w.writeElement(PrintConsts.XML_KEY_DEBUT, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
						w.writeElement(PrintConsts.XML_KEY_FIN, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
						w.writeElement(PrintConsts.XML_KEY_DUREE, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
						w.writeElement(PrintConsts.XML_KEY_TYPE_UNITE_TEMPS, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
					}
					w.endElement(); // "formationsSuivie"
				}
			} else {
				NSArray<EOIndividuFormations> formationList = EOIndividuFormations.findRecordsInContext(
						ec(), evaluation.toIndividu());
				// classement chronologique
				formationList = CktlSort.sortedArray(
						formationList, EOIndividuFormations.D_DEB_FORMATION_KEY);
				for (int i = 0; i < formationList.count(); i++) {
					EOIndividuFormations formationItem = formationList.objectAtIndex(i);
					w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATION_SUIVIE);
					{
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, formationItem.libelle() != null ? checkString(formationItem.libelle()) : "");
						w.writeElement(PrintConsts.XML_KEY_DEBUT, DateCtrl.dateToString(formationItem.dDebFormation()));
						w.writeElement(PrintConsts.XML_KEY_FIN, formationItem.dFinFormation() != null ? checkString(DateCtrl.dateToString(formationItem.dFinFormation())) : "");
						w.writeElement(PrintConsts.XML_KEY_DUREE, !StringCtrl.isEmpty(formationItem.duree()) ? formationItem.duree() : "");
						w.writeElement(PrintConsts.XML_KEY_TYPE_UNITE_TEMPS, formationItem.toTypeUniteTemps() != null ? formationItem.toTypeUniteTemps().tutLibelle() : "");
					}
					w.endElement(); // "formationsSuivie"
				}
			}
			w.endElement(); // "formationsSuivies"

			// formationsSouhaitees
			w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATIONS_SOUHAITEES);

			if (isBrouillon) {
				for (int i = 0; i < 3; i++) {
					w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATION_SOUHAITEE);
					{
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
					}
					w.endElement(); // "formationSouhaitee"
				}
			} else {

				// gestion dynamique
				NSArray<EORepartFormationSouhaitee> array = evaluation.tosRepartFormationSouhaitee();

				for (EORepartFormationSouhaitee repart : array) {
					w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATION_SOUHAITEE);
					{
						String libelle = repart.libelle();
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, libelle != null ? checkString(libelle) : "");
					}
					w.endElement(); // "formationSouhaitee"
				}

				// gestion ancienne en champ libre
				EORepartFicheItem repartFormationSouhaitee = EORepartFicheItem.findRecordForItemCodeInContext(
						ec(), EOTplItem.CODE_FORMATIONS_SOUHAITEES, evaluation);

				if (repartFormationSouhaitee != null && !StringCtrl.isEmpty(repartFormationSouhaitee.rfiValeurLibre())) {
					w.startElement(PrintConsts.XML_ELEMENT_EVALUATION_FORMATION_SOUHAITEE);
					{
						String libelle = repartFormationSouhaitee.rfiValeurLibre();
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, libelle != null ? checkString(libelle) : "");
					}
					w.endElement(); // "formationSouhaitee"
				}

			}

			w.endElement(); // "formationsSouhaitees"

			// competences annexes
			w.startElement("competencesAnnexes");
			if (isBrouillon) {
				w.writeElement("hasCompetencesAnnexes", PrintConsts.XML_VALUE_TRUE);
				for (int i = 0; i < 3; i++) {
					w.startElement("competenceAnnexe");
					{
						w.writeElement(PrintConsts.XML_KEY_LIBELLE, PrintConsts.XML_VALUE_EVALUATION_EMPTY_FIELD);
					}
					w.endElement();
				}
			} else {
				NSArray<EORepartEvaNouvelleComp> competencesAnnexes = (NSArray<EORepartEvaNouvelleComp>) evaluation.tosRepartEvaNouvelleComp();
				if (competencesAnnexes.count() > 0) {
					w.writeElement("hasCompetencesAnnexes", PrintConsts.XML_VALUE_TRUE);
					for (int i = 0; i < competencesAnnexes.count(); i++) {
						EORepartEvaNouvelleComp uneRepart = competencesAnnexes.objectAtIndex(i);
						w.startElement("competenceAnnexe");
						{
							w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString((String) uneRepart.toReferensCompetences().displayLong()));
						}
						w.endElement();
					}
				} else {
					w.writeElement("hasCompetencesAnnexes", PrintConsts.XML_VALUE_FALSE);
				}
			}
			w.endElement(); // "competencesAnnexes"

			if (isBrouillon) {
				w.writeElement(PrintConsts.XML_KEY_SHOW_EVOLUTION_AGENT, PrintConsts.XML_VALUE_FALSE);
				w.writeElement(PrintConsts.XML_KEY_SHOW_SIGNATURES, PrintConsts.XML_VALUE_FALSE);
				w.writeElement("champlibre", PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT);
				w.writeElement("evoluPropo", PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT);
				w.writeElement("evoluEnvis", PrintConsts.XML_VALUE_EVALUATION_EMPTY_TEXT);
			} else {

				if (evaluation.dTenueEntretien() != null) {
					String strDateTenueEntretien = DateCtrl.dateToString(evaluation.dTenueEntretien());
					w.writeElement(PrintConsts.XML_KEY_DATE_TENUE_ENTRETIEN, strDateTenueEntretien);
				}

				w.writeElement(PrintConsts.XML_KEY_SHOW_EVOLUTION_AGENT, PrintConsts.XML_VALUE_TRUE);
				w.writeElement(PrintConsts.XML_KEY_SHOW_SIGNATURES, PrintConsts.XML_VALUE_TRUE);

				String champLibre = lEvaluationEnCours.evaChampLibre();
				if (!StringCtrl.isEmpty(champLibre)) {
					w.writeElement("champlibre", checkString(lEvaluationEnCours.evaChampLibre()));
				}
				w.writeElement("evoluPropo", checkString(lEvaluationEnCours.evaEvolutionPropo()));
				w.writeElement("evoluEnvis", checkString(lEvaluationEnCours.evaEvolutionEnvis()));
			}

			// notice de promotion
			if (lEvaluationEnCours.tosEvaluationNoticePromotion().count() > 0) {
				EOEvaluationNoticePromotion eoPromo = (EOEvaluationNoticePromotion) lEvaluationEnCours.tosEvaluationNoticePromotion().objectAtIndex(0);
				w.startElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS);
				{
					String population = lEvaluationEnCours.getLibellePopulation();
					w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_POPULATION, checkString(population));

					Integer reductionEchelon = eoPromo.enpReductionEchelon();
					if (reductionEchelon != null) {
						w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_REDUCTION_ECHELON, checkString(eoPromo.enpReductionEchelonLibelle()));
						if (EOEvaluationNoticePromotion.isAMotiver(reductionEchelon)) {
							w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE_A_MOTIVER, PrintConsts.XML_VALUE_TRUE);
							if (!StringCtrl.isEmpty(eoPromo.enpReductionEchelonRefusMotif())) {
								w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_REDUCTION_ECHELON_MOTIF, checkString(eoPromo.enpReductionEchelonRefusMotif()));
							}
						}
					}

					Integer promotionGrade = eoPromo.enpPromotionGrade();
					if (promotionGrade != null) {
						w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE, checkString(eoPromo.enpPromotionGradeLibelle()));
						if (EOEvaluationNoticePromotion.isAMotiver(promotionGrade)) {
							w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE_A_MOTIVER, PrintConsts.XML_VALUE_TRUE);
							if (!StringCtrl.isEmpty(eoPromo.enpPromotionGradeRefusMotif())) {
								w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE_MOTIF, checkString(eoPromo.enpPromotionGradeRefusMotif()));
							}
						}
					}

					Integer promotionCorps = eoPromo.enpPromotionCorps();
					if (promotionCorps != null) {
						w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS, checkString(eoPromo.enpPromotionCorpsLibelle()));
						if (EOEvaluationNoticePromotion.isAMotiver(promotionCorps)) {
							w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS_A_MOTIVER, PrintConsts.XML_VALUE_TRUE);
							if (!StringCtrl.isEmpty(eoPromo.enpPromotionCorpsRefusMotif())) {
								w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS_MOTIF, checkString(eoPromo.enpPromotionCorpsRefusMotif()));
							}
						}
					}

					String strEnpAppreciationGenerale = "";
					if (!StringCtrl.isEmpty(eoPromo.enpAppreciationGenerale())) {
						strEnpAppreciationGenerale = eoPromo.enpAppreciationGenerale();
					}
					w.writeElement(PrintConsts.XML_ELEMENT_NOTICE_PROMOTIONS_AVIS_GENERAL, checkString(strEnpAppreciationGenerale));

				}
				w.endElement(); // notice de promotions
			}

			// gestion dynamique des onglets

			for (EOTplOnglet onglet : eoTplOngletArray) {

				Hashtable<String, String> paramsOnglet = new Hashtable<String, String>();
				paramsOnglet.put("code", onglet.tonCode());

				w.startElement("onglet", paramsOnglet);
				{

					w.writeElement("libelle", onglet.tonLibelle());
					w.writeElement("commentaire", checkString(onglet.tonCommentaire()));

					NSArray<EOTplBloc> eoTplBlocArray = onglet.tosTplBlocSortedByPosition(periode);

					for (EOTplBloc bloc : eoTplBlocArray) {

						Hashtable<String, String> paramsBloc = new Hashtable<String, String>();
						paramsBloc.put("code", bloc.tblCode());

						w.startElement("bloc", paramsBloc); // bloc
						{

							w.writeElement("libelle", bloc.tblLibelle());
							w.writeElement("commentaire", checkString(bloc.tblCommentaire()));

							if (bloc.isBlocNatureDynamique()) {

								NSArray<EOTplItem> eoTplItemArray = bloc.tosTplItemSorted();

								for (EOTplItem item : eoTplItemArray) {

									// seule la gestion des champs libres et statiques est faite
									if (item.isChampLibre() || item.isTexteStatique()) {

										Hashtable<String, String> paramsItem = new Hashtable<String, String>();
										paramsItem.put("code", item.titCode());

										w.startElement("item", paramsItem);
										{

											w.writeElement("libelle", "");

											if (item.isChampLibre()) {
												w.writeElement("commentaire", checkString(item.titCommentaire()));
												w.writeElement("valeur", checkString(item.getStrChampLibre(evaluation)));
											} else {
												// cas particulier pour les textes statiques : le
												// libelle est
												// le commentaire et la valeur est le commentaire
												w.writeElement("commentaire", checkString(item.titLibelle()));
												w.writeElement("valeur", checkString(item.titCommentaire()));
											}

										}
										w.endElement();

									}

								}

							} else {
								// blocs formation
							}

						}
						w.endElement(); // / bloc

					}

				}
				w.endElement(); // /onglet
			}

		}
		w.endElement(); // "FevFicheEvaluation"
		w.endDocument();
		w.close();

	}

	/**
	 * Completer un flux XML avec les donnees d'apres le code d'un bloc de
	 * données.
	 * 
	 * @param w
	 * @param recEvaluation
	 * @param strBlocCode
	 * @throws IOException
	 */
	private void feedXMLWriterForBloc(
			CktlXMLWriter w, EOEvaluation recEvaluation, String strBlocCode, boolean isEmptyEvaluation)
			throws IOException {
		EOTplBloc recTplBloc = EOTplBloc.fetchTplBloc(
				ec(), EOTplBloc.TBL_CODE_KEY, strBlocCode);
		// pour tous les items attendus sur ce bloc
		for (int i = 0; i < recTplBloc.tosTplItemSorted().count(); i++) {
			EOTplItem recTplItem = (EOTplItem) recTplBloc.tosTplItemSorted().objectAtIndex(i);
			w.startElement(PrintConsts.XML_KEY_COMPETENCE);
			{
				w.writeElement(PrintConsts.XML_KEY_LIBELLE, checkString(recTplItem.titLibelle()));
				if (isEmptyEvaluation) {
					// brouillon : la liste de toutes les valeurs possible separees par
					// des "/"
					EOEvaluationPeriode periode = recEvaluation.toEvaluationPeriode();
					NSArray repartList = recTplItem.tosTplRepartItemItemValeur(
							periode.epeDDebut(), periode.epeDFin());
					NSArray tplItemValeurList = (NSArray) repartList.valueForKey(
							EOTplRepartItemItemValeur.TO_TPL_ITEM_VALEUR_KEY);
					StringBuffer strItemValeurList = new StringBuffer();
					for (int j = 0; j < tplItemValeurList.count(); j++) {
						EOTplItemValeur tplItemValeur = (EOTplItemValeur) tplItemValeurList.objectAtIndex(j);
						strItemValeurList.append(tplItemValeur.tivLibelle());
						// on separe avec des /
						if (j < tplItemValeurList.count() - 1) {
							strItemValeurList.append(" / ");
						}
					}
					w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, strItemValeurList.toString());

				} else {
					// trouver le <code>EORepartItem</code> associe a l'evaluation
					// courante
					// EORepartFicheItem recRepartFicheItem =
					// EORepartFicheItem.findRecordInContext(ec(), recTplItem,
					// recEvaluation);
					EORepartFicheItem recRepartFicheItem = recTplItem.getRepartItemForEvaluation(recEvaluation);
					// compte rendu
					if (recRepartFicheItem != null) {
						w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, checkString(recRepartFicheItem.toTplItemValeur().tivLibelle()));
					} else {
						w.writeElement(PrintConsts.XML_KEY_EVALUATION_NIVEAU, PrintConsts.XML_VALUE_NON_RENSEIGNE);
					}
				}
			}
			w.endElement(); // "competence"
		}
	}

	/**
	 * Les documents joints a l'evaluation reelle
	 */
	public NSArray<String> staticDocumentUrlList() {
		/* if (!isBrouillon) { */
		NSArray<String> documentList = new NSArray<String>();

		// les documents pour tous
		documentList = documentList.arrayByAddingObjectsFromArray(app().getAdditionnalUrlDocumentsEvaluation());

		// les documents pour les fonctionnaires (et pour les fonctionnaires
		// stagiaires)
		EOEvaluation evaluation = (EOEvaluation) dico().objectForKey("evaluation");
		if (evaluation.toIndividu().isFonctionnaire(
				evaluation.toEvaluationPeriode().epeDDebut(), evaluation.toEvaluationPeriode().epeDFin()) ||
				evaluation.toIndividu().isStagiaire(evaluation.toEvaluationPeriode().epeDFin())) {
			documentList = documentList.arrayByAddingObjectsFromArray(app().getAdditionnalUrlDocumentsEvaluationFonctionnaire());
		}

		return documentList;
		/*
		 * } else { return super.staticDocumentUrlList(); }
		 */
	}
}
