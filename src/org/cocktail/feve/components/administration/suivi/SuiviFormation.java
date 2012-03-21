package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.feve.eos.modele.mangue.A_DescriptionFormation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOIndividuFormations;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;

/**
 * Suivi des formations déjà suivies par les agents (table INDIVIDU_FORMATION)
 * 
 * @author ctarade
 * 
 */
public class SuiviFormation
		extends SuiviDemFormation {

	private NSArray<EOIndividuFormations> formationArray;
	public EOIndividuFormations formationItem;

	public SuiviFormation(WOContext context) {
		super(context);
	}

	/**
	 *
	 */
	public void setServiceSelected(EOVService value) {
		serviceSelected = value;
		formationArray = null;
	}

	/**
	 * 
	 */
	public void setPeriodeSelected(EOEvaluationPeriode value) {
		periodeSelected = value;
		formationArray = null;
	}

	public NSArray<EOIndividuFormations> getFormationArray() {
		if (formationArray == null) {

			formationArray = new NSMutableArray<EOIndividuFormations>();

			formationArray = EOIndividuFormations.fetchIndividuFormationses(
					edc(), null, null);

			// filtrage en memoire sur des to-many -> faut se le palucher a la main :(
			if (serviceSelected != null) {

				// pour les champs libres
				NSMutableArray<EOIndividuFormations> formationArrayFilteredInMemory = new NSMutableArray<EOIndividuFormations>();
				for (int i = 0; i < formationArray.count(); i++) {
					boolean shouldAdd = false;
					EOIndividuFormations formation = formationArray.objectAtIndex(i);
					// filtrer sur le service
					NSArray<EOStructure> structures = formation.toIndividu().tosPosteStructure();

					for (int j = 0; j < structures.count(); j++) {
						EOStructure structure = (EOStructure) structures.objectAtIndex(j);
						if (structure.cStructure().equals(serviceSelected.toStructure().cStructure())) {
							shouldAdd = true;
							break;
						}
						shouldAdd = false;
					}
					// on l'ajoute au final
					if (shouldAdd) {
						formationArrayFilteredInMemory.addObject(formation);
					}
				}

				formationArray = formationArrayFilteredInMemory;
			}

			// classement
			if (getSortStringSelected() != null) {
				formationArray = (NSMutableArray<EOIndividuFormations>) CktlSort.sortedArray(
						formationArray, getSortStringSelected(), getSortOrder());
			}
		}
		return formationArray;
	}

	@Override
	public void doApresClassement() {
		formationArray = null;
	}

	// classement

	public void sortAgent() {
		faireClassement(EOIndividuFormations.TO_INDIVIDU_KEY + "." + EOIndividu.NOM_PRENOM_KEY);
	}

	public void sortFormationSuivie() {
		faireClassement(A_DescriptionFormation.LIBELLE_KEY);
	}

	public void sortIsNomenclature() {
		faireClassement(A_DescriptionFormation.IS_NOMENCLATURE_KEY);
	}

	public void sortDDebut() {
		faireClassement(EOIndividuFormations.D_DEB_FORMATION_KEY);
	}

	public void sortDFin() {
		faireClassement(EOIndividuFormations.D_FIN_FORMATION_KEY);
	}

	// edition

	public WOResponse printCsv() {
		CktlDataResponse resp = new CktlDataResponse();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < formationArray.count(); i++) {
			EOIndividuFormations formation = formationArray.objectAtIndex(i);
			sb.append(formation.toIndividu().nomPrenom()).append(CSV_COLUMN_SEPARATOR);
			String libelleFormation = "";
			if (!StringCtrl.isEmpty(formation.libelle())) {
				libelleFormation = formation.libelle().trim();
				libelleFormation = StringCtrl.replace(libelleFormation, "\n", " / ");
				libelleFormation = StringCtrl.replace(libelleFormation, ";", " / ");
				libelleFormation = StringCtrl.replace(libelleFormation, "\r", "");
			}
			sb.append(libelleFormation).append(CSV_COLUMN_SEPARATOR);
			String strDDebFormation = "";
			if (formation.dDebFormation() != null) {
				strDDebFormation = DateCtrl.dateToString(formation.dDebFormation());
			}
			sb.append(strDDebFormation).append(CSV_COLUMN_SEPARATOR);
			String strDFinFormation = "";
			if (formation.dFinFormation() != null) {
				strDFinFormation = DateCtrl.dateToString(formation.dFinFormation());
			}
			sb.append(strDFinFormation).append(CSV_COLUMN_SEPARATOR);
			String duree = "";
			if (!StringCtrl.isEmpty(formation.duree())) {
				duree = formation.duree();
				if (formation.toTypeUniteTemps() != null) {
					duree += " " + formation.toTypeUniteTemps().tutLibelle();
				}
			}
			sb.append(duree);
			sb.append(CSV_NEW_LINE);
		}
		NSData stream = new NSData(sb.toString(), CSV_ENCODING);
		resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);
		resp.setHeader(String.valueOf(stream.length()), "Content-Length");
		resp.setFileName("suivi_formation_suivie.csv");
		return resp;
	}
}