package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.components.fichedeposte.CompFicheDePosteListCtrl;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;

import er.extensions.eof.ERXQ;

/**
 * Ecran de suivi des fiches de poste
 * 
 * @author ctarade
 */
public class SuiviFicheDePoste
		extends A_SuiviGeneric {

	//
	@Deprecated
	public WODisplayGroup ficheDePosteDg;

	//
	private NSArray<EOFicheDePoste> eoFicheDePosteArray;

	// types de fiches de poste a afficher
	private final static String FICHE_DE_POSTE_TYPE_TOUTES = "--- Toutes ---";
	private final static String FICHE_DE_POSTE_TYPE_VALIDEES = "Oui";
	private final static String FICHE_DE_POSTE_TYPE_NON_VALIDEES = "Non";
	private final static String FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_AGENT = "En attente de l'agent";
	private final static String FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_RESP = "En attente du responsable";
	private final static String FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_DIR = "En attente du dir. de composante";
	public NSArray<String> ficheDePosteTypeList = new NSArray<String>(new String[] {
			FICHE_DE_POSTE_TYPE_TOUTES, FICHE_DE_POSTE_TYPE_VALIDEES, FICHE_DE_POSTE_TYPE_NON_VALIDEES,
			FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_AGENT, FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_RESP, FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_DIR });
	public String ficheDePosteTypeItem;
	public String ficheDePosteTypeSelected;

	// afficher les fiches actuelles O/N
	public Boolean isActuelle;

	/**
	 * Le controleur utilisé pour afficher la liste des fiches de poste
	 */
	public CompFicheDePosteListCtrl compFicheDePosteListCtrl;

	public SuiviFicheDePoste(WOContext context) {
		super(context);
		initComponent();
	}

	public NSArray<EOFicheDePoste> getEoFicheDePosteArray() {
		if (eoFicheDePosteArray == null) {

			// qualifier sur le service
			EOQualifier qualService = null;
			if (serviceSelected != null) {
				qualService = ERXQ.equals(EOFicheDePoste.TO_POSTE_KEY + "." + EOPoste.TO_STRUCTURE_KEY, serviceSelected);
			}

			// type de poste
			EOQualifier qualTypePoste = getPosteTypeQual();

			// actuelle O/N
			EOQualifier qualActuelle = ERXQ.equals(EOFicheDePoste.IS_ACTUELLE, isActuelle);

			// type fiche
			EOQualifier qualTypeFiche = null;
			if (ficheDePosteTypeSelected.equals(FICHE_DE_POSTE_TYPE_VALIDEES)) {
				qualTypeFiche = CktlDataBus.newCondition(
						EOFicheDePoste.IS_VALIDEE_AGENT + "=%@ and " + EOFicheDePoste.IS_VALIDEE_RESPONSABLE + "=%@ and " + EOFicheDePoste.IS_VALIDEE_DIRECTEUR + "=%@",
						new NSArray<Boolean>(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE }));
			} else if (ficheDePosteTypeSelected.equals(FICHE_DE_POSTE_TYPE_NON_VALIDEES)) {
				qualTypeFiche = CktlDataBus.newCondition(
						EOFicheDePoste.IS_VALIDEE_AGENT + "=%@ or " + EOFicheDePoste.IS_VALIDEE_RESPONSABLE + "=%@ or " + EOFicheDePoste.IS_VALIDEE_DIRECTEUR + "=%@",
						new NSArray<Boolean>(new Boolean[] { Boolean.FALSE, Boolean.FALSE, Boolean.FALSE }));
			} else if (ficheDePosteTypeSelected.equals(FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_AGENT)) {
				qualTypeFiche = CktlDataBus.newCondition(EOFicheDePoste.IS_VALIDEE_AGENT + "=%@",
						new NSArray<Boolean>(Boolean.FALSE));
			} else if (ficheDePosteTypeSelected.equals(FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_RESP)) {
				qualTypeFiche = CktlDataBus.newCondition(EOFicheDePoste.IS_VALIDEE_RESPONSABLE + "=%@",
						new NSArray<Boolean>(Boolean.FALSE));
			} else if (ficheDePosteTypeSelected.equals(FICHE_DE_POSTE_TYPE_ATTENTE_VALIDATION_DIR)) {
				qualTypeFiche = CktlDataBus.newCondition(EOFicheDePoste.IS_VALIDEE_DIRECTEUR + "=%@",
						new NSArray<Boolean>(Boolean.FALSE));
			}

			// filtrages en base
			eoFicheDePosteArray = EOFicheDePoste.fetchFicheDePostes(edc(), qualService, null);

			// filtrages EOF
			if (qualTypePoste != null) {
				eoFicheDePosteArray = EOQualifier.filteredArrayWithQualifier(eoFicheDePosteArray, qualTypePoste);
			}
			if (qualActuelle != null) {
				eoFicheDePosteArray = EOQualifier.filteredArrayWithQualifier(eoFicheDePosteArray, qualActuelle);
			}
			if (qualTypeFiche != null) {
				eoFicheDePosteArray = EOQualifier.filteredArrayWithQualifier(eoFicheDePosteArray, qualTypeFiche);
			}

			// reinstancier le controleur des que la liste change
			resetCompFicheDePosteListCtrl();

		}
		return eoFicheDePosteArray;
	}

	@Override
	@Deprecated
	protected void doRefreshMainDg() {

	}

	private void resetCompFicheDePosteListCtrl() {
		compFicheDePosteListCtrl = null;
	}

	private void initComponent() {
		// selection du type "toutes" par defaut
		setFicheDePosteTypeSelected(FICHE_DE_POSTE_TYPE_TOUTES);
		// on n'affiche que les fiches actuelles (on passe par le setter
		// pour forcer la construction du qualifier associé)
		setIsActuelle(Boolean.TRUE);
		// ne pas fetcher au début
		eoFicheDePosteArray = new NSArray<EOFicheDePoste>();
		// instancier le controleur avec la liste vide
		resetCompFicheDePosteListCtrl();
	}

	/**
	 * 
	 * @return
	 */
	public CompFicheDePosteListCtrl getCompFicheDePosteListCtrl() {
		if (compFicheDePosteListCtrl == null) {

			compFicheDePosteListCtrl = new CompFicheDePosteListCtrl(
					session, getEoFicheDePosteArray(),
					"Aucune fiche de poste ne r&eacute;pond &agrave; ces crit&egrave;res.",
					true, true, true, false, true, null, true, true);
			compFicheDePosteListCtrl.setStringLabel("Suivi global des fiches de poste");
			compFicheDePosteListCtrl.setLinkLabel("Accès à la liste des fiches de poste");
			compFicheDePosteListCtrl.setLinkTitle("Retourner &agrave; la liste des fiches");
		}
		return compFicheDePosteListCtrl;
	}

	/**
	 * @see A_SuiviGeneric#prefixEntityDgToPoste()
	 * @see EOFicheDePoste
	 */
	protected String prefixEntityDgToPoste() {
		return EOFicheDePoste.TO_POSTE_KEY;
	}

	/**
	 * Surcharge du setter pour la selection du type de fiche de poste afin de
	 * recharger le display group si changement.
	 */
	public void setFicheDePosteTypeSelected(String value) {
		if (value != null && value != ficheDePosteTypeSelected) {
			notifierShouldRefresh();
		}
		ficheDePosteTypeSelected = value;
	}

	/**
	 * Surcharge du setter pour modification du DG
	 */
	public void setIsActuelle(Boolean value) {
		if (isActuelle != value) {
			isActuelle = value;
			notifierShouldRefresh();
		}
	}

	@Override
	protected WODisplayGroup mainDg() {
		return ficheDePosteDg;
	}

	/**
	 * 
	 */
	public WOResponse printCsv() {
		CktlDataResponse resp = new CktlDataResponse();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < getCompFicheDePosteListCtrl().getEoFicheDePosteArray().count(); i++) {
			EOFicheDePoste ficheDePoste = (EOFicheDePoste) getCompFicheDePosteListCtrl().getEoFicheDePosteArray().objectAtIndex(i);

			getCompFicheDePosteListCtrl().setFicheDePosteItem(ficheDePoste);

			sb.append(ficheDePoste.toPoste().toComposante().lcStructure()).append(CSV_COLUMN_SEPARATOR);
			sb.append(ficheDePoste.toPoste().toStructure().lcStructure()).append(CSV_COLUMN_SEPARATOR);
			sb.append(ficheDePoste.toPoste().posCode()).append(CSV_COLUMN_SEPARATOR);
			sb.append(ficheDePoste.identifiant()).append(CSV_COLUMN_SEPARATOR);

			String strEmploiType = "";
			if (ficheDePoste.toReferensEmplois() != null) {
				strEmploiType = ficheDePoste.toReferensEmplois().display();
			}
			sb.append(strEmploiType).append(CSV_COLUMN_SEPARATOR);

			sb.append(DateCtrl.dateToString(getCompFicheDePosteListCtrl().getItemFicheDePosteDateDebut())).append(CSV_COLUMN_SEPARATOR);

			String strDFin = "";
			if (getCompFicheDePosteListCtrl().getItemFicheDePosteDateFin() != null) {
				strDFin = DateCtrl.dateToString(getCompFicheDePosteListCtrl().getItemFicheDePosteDateFin());
			}
			sb.append(strDFin).append(CSV_COLUMN_SEPARATOR);

			String strOccupant = "";
			if (getCompFicheDePosteListCtrl().getRecAffectationDetail() != null) {
				strOccupant = getCompFicheDePosteListCtrl().getRecAffectationDetail().toAffectation().toIndividu().display();
			}
			sb.append(strOccupant).append(CSV_COLUMN_SEPARATOR);
			sb.append(CSV_NEW_LINE);

		}
		NSData stream = new NSData(sb.toString(), CSV_ENCODING);
		resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);
		resp.setHeader(String.valueOf(stream.length()), "Content-Length");
		resp.setFileName("suivi_fiche_de_poste.csv");
		return resp;
	}

	@Override
	public void doApresClassement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifierShouldRefresh() {
		super.notifierShouldRefresh();
		eoFicheDePosteArray = null;
		resetCompFicheDePosteListCtrl();
	}

}