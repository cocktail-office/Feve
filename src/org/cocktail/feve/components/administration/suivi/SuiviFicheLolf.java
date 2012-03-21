package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen;
import org.cocktail.feve.eos.modele.mangue.EORepartFloSilland;
import org.cocktail.fwkcktljefyadmin.common.finder.FinderExercice;
import org.cocktail.fwkcktljefyadmin.common.metier.EOExercice;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;

/**
 * Ecran de suivi des fiches LOLF
 * 
 * @author ctarade
 */
public class SuiviFicheLolf
		extends A_SuiviGeneric {

	//
	public WODisplayGroup ficheLolfDg;
	public EOFicheLolf ficheLolfItem;

	//
	public EORepartFloSilland repartFloSillandItem;

	//
	public EORepartFloLolfNomen repartFloLolfNomenItem;

	// les exercices comptables
	public NSArray exerciceList;
	public EOExercice exerciceItem;
	public EOExercice exerciceSelected;

	public SuiviFicheLolf(WOContext context) {
		super(context);
		initComponent();
	}

	/**
	 * Initiliasation
	 */
	private void initComponent() {
		// liste des exercices comptables (du plus vieux au plus recent)
		exerciceList = FinderExercice.getExercices(ec);
		exerciceList = CktlSort.sortedArray(exerciceList, EOExercice.EXE_EXERCICE_KEY);
		// selection du courant
		// setExerciceSelected(FinderExercice.getExercicePourDate(ec,
		// DateCtrl.now()));
		setExerciceSelected(FinderExercice.getExerciceDepensePourDate(ec, DateCtrl.now()));
		shouldRefreshMainDg = false;
		// on masque les composantes archives
		composanteDg.queryBindings().setObjectForKey("0", "isStrArchive");
		composanteDg.qualifyDataSource();
	}

	/**
	 * Lors de la modification de l'exerice, on va recharger la liste des fiches
	 * 
	 * @param value
	 */
	public void setExerciceSelected(EOExercice value) {
		exerciceSelected = value;
		mainDg().queryBindings().setObjectForKey(
				exerciceSelected.exeExercice(), "exeOrdre");
		shouldRefreshMainDg = true;
	}

	// affichage

	/**
	 * La classe css pour la fiche lolf {@link #ficheLolfItem} On la met en rouge
	 * si elle a un probleme
	 */
	public String getTrFicheLolfItemCssClass() {
		String cssClass = "listboxLine";
		if (ficheLolfItem.hasWarning()) {
			cssClass += " ficheLolfIncomplete";
		}
		return cssClass;
	}

	// edition

	/**
	 * Ajouter les informations sur la fiche LOLF
	 */
	private void appendFicheLolfInfoToBuffer(EOFicheLolf ficheLolf, StringBuffer sb) {
		// descriptif fiche LOLF
		// support enseignant ou biatoss
		String codeSupport = ficheLolf.toPoste().isEnseignant() ? "E" : "B";
		sb.append(codeSupport).append(CSV_COLUMN_SEPARATOR);
		// composante
		sb.append(ficheLolf.toPoste().toStructure().toComposante().displayUltraCourt()).append(CSV_COLUMN_SEPARATOR);
		// service
		sb.append(ficheLolf.toPoste().toStructure().displayUltraCourt()).append(CSV_COLUMN_SEPARATOR);
		// libelle poste
		sb.append(ficheLolf.toPoste().posLibelle()).append(CSV_COLUMN_SEPARATOR);
		// code poste
		sb.append(ficheLolf.toPoste().posCode()).append(CSV_COLUMN_SEPARATOR);
		// dates du poste
		String dateDebut = "";
		if (ficheLolf.toPoste().posDDebut() != null) {
			dateDebut = DateCtrl.dateToString(ficheLolf.toPoste().posDDebut());
		}
		String dateFin = "";
		if (ficheLolf.toPoste().posDFin() != null) {
			dateFin = DateCtrl.dateToString(ficheLolf.toPoste().posDFin());
		}
		sb.append(dateDebut).append(CSV_COLUMN_SEPARATOR);
		sb.append(dateFin).append(CSV_COLUMN_SEPARATOR);
		// occupant actuel
		String nomPrenomOccupant = "";
		if (ficheLolf.toPoste().toAffectationDetailActuelle() != null) {
			nomPrenomOccupant = ficheLolf.toPoste().toAffectationDetailActuelle().toAffectation().toIndividu().nomPrenom();
		}
		sb.append(nomPrenomOccupant).append(CSV_COLUMN_SEPARATOR);
	}

	/**
	 * Ajouter les informations sur la fonction silland
	 */
	private void appendSillandInfoToBuffer(EORepartFloSilland repartFloSilland, StringBuffer sb) {
		// descriptif silland
		// silland
		sb.append(repartFloSilland.toFctSilland().silLibelle()).append(CSV_COLUMN_SEPARATOR);
		// quotite
		sb.append(repartFloSilland.rfsQuotite()).append(CSV_COLUMN_SEPARATOR);
	}

	/**
	 * Ajouter les informations sur la destination lolf
	 */
	private void appendDestinationLolfInfoToBuffer(EORepartFloLolfNomen repartFloLolfNomen, StringBuffer sb) {
		// descriptif lolf
		// nomenclature lolf
		// sb.append(repartFloLolfNomen.toTypeAction().tyacLibelle()).append(CSV_COLUMN_SEPARATOR);
		sb.append(repartFloLolfNomen.toLolfNomenclatureDepense().lolfLibelle()).append(CSV_COLUMN_SEPARATOR);
		// quotite
		sb.append(repartFloLolfNomen.rrfQuotite()).append(CSV_COLUMN_SEPARATOR);
		sb.append(CSV_NEW_LINE);
	}

	/**
	 * Indique si l'agent connecté peut avoir accès à la fiche lolf courant
	 * {@link #ficheLolfItem}
	 * 
	 * @return
	 */
	public boolean isFicheLolfItemAffichable() {
		return isAffichable(ficheLolfItem);
	}

	/**
	 * 
	 * @param eoFicheLolf
	 * @return
	 */
	private boolean isAffichable(EOFicheLolf eoFicheLolf) {
		boolean isAffichable = false;

		isAffichable = eoFicheLolf.hasAnyDroitAccesOnFicheLolf(feveUserInfo());

		return isAffichable;
	}

	/**
	 * 
	 */
	public WOResponse printCsv() {
		CktlDataResponse resp = new CktlDataResponse();
		StringBuffer sb = new StringBuffer();

		// entete
		sb.append("SUPPORT").append(CSV_COLUMN_SEPARATOR);
		sb.append("COMPOSANTE").append(CSV_COLUMN_SEPARATOR);
		sb.append("SERVICE").append(CSV_COLUMN_SEPARATOR);
		sb.append("LIBELLE POSTE").append(CSV_COLUMN_SEPARATOR);
		sb.append("CODE POSTE").append(CSV_COLUMN_SEPARATOR);
		sb.append("DEBUT POSTE").append(CSV_COLUMN_SEPARATOR);
		sb.append("FIN POSTE").append(CSV_COLUMN_SEPARATOR);
		sb.append("OCCUPANT").append(CSV_COLUMN_SEPARATOR);
		sb.append("SILLAND").append(CSV_COLUMN_SEPARATOR);
		sb.append("% SILLAND").append(CSV_COLUMN_SEPARATOR);
		sb.append("DESTINATION").append(CSV_COLUMN_SEPARATOR);
		sb.append("% DESTINATION").append(CSV_COLUMN_SEPARATOR);
		sb.append(CSV_NEW_LINE);

		for (int i = 0; i < ficheLolfDg.allObjects().count(); i++) {
			EOFicheLolf ficheLolf = (EOFicheLolf) ficheLolfDg.allObjects().objectAtIndex(i);

			if (isAffichable(ficheLolf)) {

				// si pas de fonction silland, alors libelle fiche et le reste vide
				if (ficheLolf.tosRepartFloSilland().count() == 0) {
					// descriptif fiche LOLF
					appendFicheLolfInfoToBuffer(ficheLolf, sb);
					// 6 cases vides
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(" ").append(CSV_COLUMN_SEPARATOR);
					sb.append(CSV_NEW_LINE);
				} else {
					//
					for (int j = 0; j < ficheLolf.tosRepartFloSilland().count(); j++) {
						EORepartFloSilland repartFloSilland = (EORepartFloSilland) ficheLolf.tosRepartFloSilland().objectAtIndex(j);

						// si pas de destination lolf, alors libelle fiche + silland et le
						// reste vide
						if (repartFloSilland.tosRepartFloLolfNomen().count() == 0) {
							// descriptif fiche LOLF
							appendFicheLolfInfoToBuffer(ficheLolf, sb);
							// descriptif silland
							appendSillandInfoToBuffer(repartFloSilland, sb);
							// 2 cases vides
							sb.append(" ").append(CSV_COLUMN_SEPARATOR);
							sb.append(" ").append(CSV_COLUMN_SEPARATOR);
							sb.append(CSV_NEW_LINE);
						} else {
							//
							for (int k = 0; k < repartFloSilland.tosRepartFloLolfNomen().count(); k++) {
								// descriptif fiche LOLF
								appendFicheLolfInfoToBuffer(ficheLolf, sb);
								// descriptif silland
								appendSillandInfoToBuffer(repartFloSilland, sb);
								// descriptif lolf
								appendDestinationLolfInfoToBuffer((EORepartFloLolfNomen) repartFloSilland.tosRepartFloLolfNomen().objectAtIndex(k), sb);

							}
						}
					}
				}

			}

		}
		NSData stream = new NSData(sb.toString(), CSV_ENCODING);
		resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);
		resp.setHeader(String.valueOf(stream.length()), "Content-Length");
		resp.setFileName(DateCtrl.dateToString(DateCtrl.now(), "%Y%m%d") + "_suivi_fiche_lolf.csv");
		return resp;
	}

	//

	@Override
	protected void doRefreshMainDg() {
		ficheLolfDg.qualifyDataSource();
		//
		NSArray result = ficheLolfDg.allObjects();
		// ajouter le qualifier sur les types de postes

		result = EOQualifier.filteredArrayWithQualifier(result, getPosteTypeQual());
		ficheLolfDg.setObjectArray(result);
	}

	@Override
	protected WODisplayGroup mainDg() {
		return ficheLolfDg;
	}

	@Override
	protected String prefixEntityDgToPoste() {
		return EOFicheLolf.TO_POSTE_KEY;
	}

	@Override
	public void doApresClassement() {
		// TODO Auto-generated method stub

	}
}