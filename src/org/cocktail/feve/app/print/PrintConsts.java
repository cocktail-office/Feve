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

import org.cocktail.fwkcktlwebapp.common.util.CktlXMLWriter.SpecChars;

/**
 * Constantes pour l'ensemble des impressions
 * 
 * @author Cyril TARADE <cyril.tarade at univ-lr.fr>
 */
public class PrintConsts {

	public final static String PDF_APPEND_TEMP_DOC = "/tmp/AppendDoc.pdf";

	public final static String XML_DEFAULT_PATH = "/tmp/donnees.xml";

	public final static String DICO_KEY_ENDING_MESSAGE = "endingMessage";

	public final static String XML_FEV_FICHE_DE_POSTE = "/tmp/FevFicheDePoste.xml";
	public final static String PDF_FEV_FICHE_DE_POSTE = "/tmp/FevFicheDePoste.pdf";
	public final static String ID_FEV_FICHE_DE_POSTE = "FEV_FICHE_DE_POSTE";
	public final static String ENDING_MESSAGE_FICHE_DE_POSTE =
			"Apr&egrave;s avoir sign&eacute; la fiche de poste et l'avoir faite signer &agrave l'agent, veuillez :" +
					"<ul>" +
					"<li>En conserver une copie</li>" +
					"<li>En donner une copie &agrave; l'agent</li>" +
					"<li>Envoyer l'original &agrave; la DRH</li>" +
					"</ul>";

	public final static String XML_FEV_FICHE_EVALUATION = "/tmp/FevFicheEvaluation.xml";
	public final static String PDF_FEV_FICHE_EVALUATION = "/tmp/FevFicheEvaluation.pdf";
	public final static String ID_FEV_FICHE_EVALUATION = "FEV_FICHE_EVALUATION";
	public final static String ENDING_MESSAGE_FICHE_EVALUATION_NON_VIERGE =
			"Apr&egrave;s avoir sign&eacute; le compte rendu et l'avoir fait signer &agrave l'agent, veuillez :" +
					"<ul>" +
					"<li>En conserver une copie</li>" +
					"<li>Envoyer l'original &agrave; la DRH</li>" +
					"</ul>";

	public final static String ENDING_MESSAGE_FICHE_EVALUATION_VIERGE =
			"Ce compte rendu vierge vous servira lors de la pr&eacute;paration de l'entretien et en tant que support lors des &eacute;changes<br/>" +
					"Apr&egrave;s l'entretien, vous devrez donc saisir, sur cette m&ecirc;me application, chaque partie abord&eacute;e lors de la rencontre.<br/>" +
					"Vous pourrez ensuite garder une copie du compte rendu, sign&eacute; par les deux parties, et envoyer l'original &agrave; la DRH.";

	public final static String MESSAGE_FICHE_EVALUATION_APRES_SAISIE_DATE_ENTRETIEN =
			"Vous pouvez désormais imprimer le compte rendu d\\\'entretien professionnel, " +
					"le faire signer à l\\\'agent puis en garder une copie et retourner l\\\'original à la DRH";

	// -- les attributs / valeurs communs --

	//
	public final static String XML_KEY_ETABLISSEMENT = "etablissement";
	public final static String XML_KEY_COMPETENCE = "competence";
	public final static String XML_KEY_COMPETENCE_AUTRE = "competenceAutre";
	public final static String XML_KEY_LIBELLE = "libelle";
	public final static String XML_KEY_DEBUT = "debut";
	public final static String XML_KEY_FIN = "fin";
	public final static String XML_KEY_DUREE = "duree";
	public final static String XML_KEY_TYPE_UNITE_TEMPS = "typeUniteTemps";

	// un element non renseigne
	public final static String XML_VALUE_NON_RENSEIGNE = "<Non renseigné>";
	public final static String XML_VALUE_TRUE = "true";
	public final static String XML_VALUE_FALSE = "false";

	// -- edition de la fiche de poste --

	public final static String XML_KEY_SHOW_INFOS_PERSONNELLES = "showInfosPersonelles";
	public final static String XML_KEY_BLOC_ACTI_COMP_VERTICAL = "blocActiCompVertical";

	// -- edition de l'evaluation --

	public final static String XML_KEY_DATE_TENUE_ENTRETIEN = "dateTenueEntretien";

	public final static String XML_ELEMENT_EVALUATION_COMPETENCES_PROFESSIONNELLES_ET_TECHNICITE = "competencesProfessionnellesEtTechnicites";
	public final static String XML_ELEMENT_EVALUATION_MANAGEMENT = "management";
	public final static String XML_ELEMENT_EVALUATION_CONTRIBUTION_A_L_ACTIVITE_DU_SERVICE = "contributionALActiviteDuService";
	public final static String XML_ELEMENT_EVALUATION_QUALITES_PERSONNELLES_ET_RELATIONNELLES = "aualitesPersonnellesEtRelationnelles";
	public final static String XML_ELEMENT_EVALUATION_FORMATIONS_SUIVIES = "formationsSuivies";
	public final static String XML_ELEMENT_EVALUATION_FORMATION_SUIVIE = "formationSuivie";

	public final static String XML_KEY_EVALUATION_NIVEAU = "niveau";
	public final static String XML_KEY_EVALUATION_HAS_MANAGEMENT = "hasManagement";

	public final static String XML_ELEMENT_EVALUATION_FORMATIONS_SOUHAITEES = "formationsSouhaitees";
	public final static String XML_ELEMENT_EVALUATION_FORMATION_SOUHAITEE = "formationSouhaitee";

	public final static String XML_KEY_SHOW_EVOLUTION_AGENT = "showEvolutionAgent";
	public final static String XML_KEY_SHOW_SIGNATURES = "showSignatures";

	// notice de promotion
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS = "noticeDePromotions";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_POPULATION = "noticeDePromotionsPopulation";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_REDUCTION_ECHELON = "noticeDePromotionsReductionEchelon";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_REDUCTION_ECHELON_A_MOTIVER = "noticeDePromotionsReductionEchelonAMotiver";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_REDUCTION_ECHELON_MOTIF = "noticeDePromotionsReductionEchelonMotif";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE = "noticeDePromotionsPromotionGrade";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE_A_MOTIVER = "noticeDePromotionsPromotionGradeAMotiver";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_GRADE_MOTIF = "noticeDePromotionsPromotionGradeMotif";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS = "noticeDePromotionsPromotionCorps";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS_A_MOTIVER = "noticeDePromotionsPromotionCorpsAMotiver";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_PROMOTION_CORPS_MOTIF = "noticeDePromotionsPromotionCorpsMotif";
	public final static String XML_ELEMENT_NOTICE_PROMOTIONS_AVIS_GENERAL = "noticeDePromotionsAvisGeneralSurUnePromotion";

	//
	public final static String XML_VALUE_EVALUATION_EMPTY_FIELD = "...";
	public final static String XML_VALUE_EVALUATION_EMPTY_TEXT = "." + SpecChars.br + "." + SpecChars.br + "." + SpecChars.br + ".";
}
