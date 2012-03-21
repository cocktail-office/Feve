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

import org.cocktail.feve.app.Session;
import org.cocktail.feve.app.finder.FinderFeve;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOReferensActivites;
import org.cocktail.feve.eos.modele.grhum.EOReferensCompetences;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre;
import org.cocktail.fwkcktlwebapp.common.util.CktlXMLWriter;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;

import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * Edition PDF/SIX de la fiche de poste
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */
public class PrintFicheDePoste extends GenericSixPrint {

	public PrintFicheDePoste(Session session,
			NSDictionary dico, FevePdfBoxCtrl ctrl) {
		super(session,
				PrintConsts.PDF_FEV_FICHE_DE_POSTE,
				PrintConsts.ID_FEV_FICHE_DE_POSTE,
				PrintConsts.XML_FEV_FICHE_DE_POSTE,
				dico, ctrl);
	}

	/**
	 * 
	 */
	protected void generateXml(CktlXMLWriter w) throws IOException {

		// recuperer la fiche
		EOFicheDePoste fiche = (EOFicheDePoste) dico().objectForKey("fiche");

		// temoin s'il faut les infos personnelles ou pas
		boolean showInfosPersonnelles = ((Boolean) dico().objectForKey(PrintConsts.XML_KEY_SHOW_INFOS_PERSONNELLES)).booleanValue();

		boolean isBlocActiCompVertical = ((Boolean) dico().objectForKey(PrintConsts.XML_KEY_BLOC_ACTI_COMP_VERTICAL)).booleanValue();

		// recup du dico gepeto de l'occupant de la fiche
		NSMutableDictionary dico = new NSMutableDictionary();
		NSDictionary dicoAgent = null;
		if (showInfosPersonnelles && fiche.toAffectationDetailActuelle() != null) {
			dicoAgent = EOIndividu.findDicoAgentGepetoInContext(ec(), fiche.toAffectationDetailActuelle());
			dico.addEntriesFromDictionary(dicoAgent);
		}

		// recup du dico de l'environnement de la fiche
		dico.addEntriesFromDictionary(FinderFeve.findDicoFicheDePosteInContext(ec(), fiche));

		dico = new NSMutableDictionary(cleanDico(dico.immutableClone()));

		w.startDocument();
		w.writeComment("Edition de la fiche de poste");

		w.startElement("FevFicheDePoste");
		{

			// bloc activités et compétences REFERENS vertical ou non
			w.writeElement(PrintConsts.XML_KEY_BLOC_ACTI_COMP_VERTICAL,
					isBlocActiCompVertical ? PrintConsts.XML_VALUE_TRUE : PrintConsts.XML_VALUE_FALSE);

			// indiquer dans le XML s'il faut l'encart contenant les infos personelles
			w.writeElement(PrintConsts.XML_KEY_SHOW_INFOS_PERSONNELLES,
					showInfosPersonnelles ? PrintConsts.XML_VALUE_TRUE : PrintConsts.XML_VALUE_FALSE);

			EOStructure structureRacine = EOStructure.findRacineInContext(ec());
			if (structureRacine != null && !StringCtrl.isEmpty(structureRacine.llStructure())) {
				w.writeElement(PrintConsts.XML_KEY_ETABLISSEMENT, structureRacine.llStructure());
			}

			w.writeElement("libelle", checkString((String) dico.objectForKey("libelle")));

			if (dicoAgent != null) {
				w.writeElement("identifiant", checkString((String) dico.objectForKey("identifiant")));
				w.writeElement("nom", checkString((String) dico.objectForKey("nomUsuel")));
				w.writeElement("prenom", checkString((String) dico.objectForKey("prenom")));
				w.writeElement("dNaissance", checkString((String) dico.objectForKey("dNaissance")));
				w.writeElement("statut", checkString((String) dico.objectForKey("statut")));
				w.writeElement("corps", checkString((String) dico.objectForKey("corps")));
				w.writeElement("grade", checkString((String) dico.objectForKey("grade")));
			}

			w.writeElement("dcp", checkString((String) dico.objectForKey("dcp")));
			w.writeElement("famillePro", checkString((String) dico.objectForKey("famillePro")));
			w.writeElement("emploiType", checkString((String) dico.objectForKey("emploiType")));

			w.writeElement("composante", checkString((String) dico.objectForKey("composante")));
			w.writeElement("structure", checkString((String) dico.objectForKey("structure")));
			w.writeElement("poste", checkString(fiche.toPoste().posLibelle()));

			w.writeElement("missionComposante", checkString((String) dico.objectForKey("missionComposante")));
			w.writeElement("missionService", checkString((String) dico.objectForKey("missionService")));
			w.writeElement("projetService", checkString((String) dico.objectForKey("projetService")));
			w.writeElement("missionPoste", checkString((String) dico.objectForKey("missionPoste")));
			w.writeElement("contexte", checkString((String) dico.objectForKey("contexte")));

			w.startElement("activites");

			for (int i = 0; i < fiche.tosReferensActivites().count(); i++) {
				w.writeElement("activite", checkString(((EOReferensActivites) fiche.tosReferensActivites().objectAtIndex(i)).displayLong()));
			}
			w.endElement();

			// on affiche les activites autre que s'il y en a
			if (feveUserInfo().isFicheDePosteSaisieActiviteAutre()) {
				if (fiche.tosRepartFdpActivitesAutres().count() > 0) {
					w.startElement("activitesAutres");
					for (int i = 0; i < fiche.tosRepartFdpActivitesAutres().count(); i++) {
						w.writeElement("activiteAutre", checkString(((EORepartFdpAutre) fiche.tosRepartFdpActivitesAutres().objectAtIndex(i)).fauChampLibre()));
					}
					w.endElement();
				}
			}

			w.startElement("competences");
			for (int i = 0; i < fiche.tosReferensCompetences().count(); i++) {
				w.writeElement("competence", checkString(((EOReferensCompetences) fiche.tosReferensCompetences().objectAtIndex(i)).displayLong()));
			}
			w.endElement();

			// on affiche les competences autre que s'il y en a
			if (feveUserInfo().isFicheDePosteSaisieCompetenceAutre()) {
				if (fiche.tosRepartFdpCompetencesAutres().count() > 0) {
					w.startElement("competencesAutres");
					for (int i = 0; i < fiche.tosRepartFdpCompetencesAutres().count(); i++) {
						w.writeElement("competenceAutre", checkString(((EORepartFdpAutre) fiche.tosRepartFdpCompetencesAutres().objectAtIndex(i)).fauChampLibre()));
					}
					w.endElement();
				}
			}

		}
		w.endElement(); // "FevFicheDePoste"
		w.endDocument();
		w.close();

	}

}
