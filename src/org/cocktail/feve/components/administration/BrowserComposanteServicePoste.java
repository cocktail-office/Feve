package org.cocktail.feve.components.administration;

import org.cocktail.feve.components.common.FeveWebComponent;
import org.cocktail.feve.eos.modele.grhum.EOIndividu;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.mangue.A_EOEvaluationKeyValueCoding;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EOVCandidatEvaluation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

/**
 * Composant permettant de parcourir les postes, les individus et les fiches par
 * composante et par service
 * 
 * @author ctarade
 */
public class BrowserComposanteServicePoste
		extends FeveWebComponent {

	// les display groups
	public WODisplayGroup composanteDisplayGroup;
	public WODisplayGroup posteDisplayGroup;
	public WODisplayGroup vCandidatEvaluationDisplayGroup;

	//
	public NSArray<EOIndividu> individuList;

	// temoins de refresh oui du displaygroup des postes ou des evaluations
	public boolean shouldRefreshDisplayGroup = true;

	//
	public EOStructure composanteItem;
	public EOStructure composanteSelected;

	//
	public EOStructure serviceItem;
	public EOStructure serviceSelected;

	//
	public EOPoste posteItem;
	public EOPoste posteSelected;

	//
	public EOFicheDePoste ficheDePosteItem;
	public EOFicheDePoste ficheDePosteSelected;

	//
	public EOFicheLolf ficheLolfItem;
	public EOFicheLolf ficheLolfSelected;

	// periodes d'evaluations
	public NSArray<EOEvaluationPeriode> evaluationPeriodeList;
	public EOEvaluationPeriode evaluationPeriodeItem;
	public EOEvaluationPeriode evaluationPeriodeSelected;

	// individu selectionne (dans le cas ou la periode est inconnue)
	public EOIndividu individuItem;
	public EOIndividu individuSelected;

	// ce qu'il faut afficher sous les services : postes ou agents
	public final String TYPE_FILS_SERVICE_POSTE = "POSTE";
	public final String TYPE_FILS_SERVICE_AGENT = "AGENT";
	public String typeFilsServiceSelected;

	// la nature des postes (enseignant / non enseignant)
	public NSArray<String> posteNatureList = new NSArray<String>(new String[] {
			EOPoste.POSTE_NATURE_TOUTES,
			EOPoste.POSTE_NATURE_NON_ENSEIGNANT,
			EOPoste.POSTE_NATURE_ENSEIGNANT,
			EOPoste.POSTE_NATURE_INCONNUE });
	public String posteNatureItem;
	public String posteNatureSelected;

	// ce qu'il fauyt afficher sous les postes : fiches de postes ou fiches LOLF
	public final String TYPE_FILS_POSTE_FICHE_DE_POSTE = "FDP";
	public final String TYPE_FILS_POSTE_FICHE_LOLF = "FLO";
	public String typeFilsPosteSelected = TYPE_FILS_POSTE_FICHE_DE_POSTE;

	// indique s'il faut afficher les groupes archives O/N
	public boolean showArchive;

	// les setter sur evaluationPeriodeSelected et posteNatureSelected
	// sont appelés des le debut a null ...
	private int setEvaluationPeriodeSelectedCallCount = 0;
	private int setPosteNatureSelectedCallCount = 0;

	// filtres ajax
	public String filtreComposante;
	public String filtreService;
	public String filtrePoste;

	public BrowserComposanteServicePoste(WOContext context) {
		super(context);
		initComponent();
	}

	private void initComponent() {
		//
		evaluationPeriodeList = EOEvaluationPeriode.fetchAllEvaluationPeriodes(ec,
				CktlSort.newSort(EOEvaluationPeriode.EPE_D_DEBUT_KEY));
		// on preselectionne la periode actuelle
		evaluationPeriodeSelected = EOEvaluationPeriode.getCurrentPeriode(ec);
		// en priorité les postes non enseignants
		posteNatureSelected = EOPoste.POSTE_NATURE_NON_ENSEIGNANT;
		// on affiche en priorité les postes
		typeFilsServiceSelected = TYPE_FILS_SERVICE_POSTE;
		// on affiche en priorité les fiches de poste
		typeFilsPosteSelected = TYPE_FILS_POSTE_FICHE_DE_POSTE;
		// par defaut, on masque les groupes archives
		setShowArchive(false);
		//
		filtreComposante = "";
	}

	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		if (shouldRefreshDisplayGroup) {
			refreshDisplayGroup();
			shouldRefreshDisplayGroup = false;
		}
		super.appendToResponse(aResponse, aContext);
	}

	// refresh des display group

	/**
	 * forcer les donnees du DisplayGroup a etre rechargees.
	 */
	private void refreshDisplayGroup() {

		// selection du dg a recharger
		if (isTypeFilsServicePosteSelected()) {
			// le displaygroup des postes

			if (composanteSelected != null) {
				posteDisplayGroup.queryBindings().removeAllObjects();
				// si le service n'est pas selectionne, on prend
				// les postes de tous les services de la composante
				if (serviceSelected == null) {
					NSArray serviceList = composanteSelected.tosSousServiceDeep(showArchive);
					for (int i = 0; i < serviceList.count(); i++) {
						EOStructure service = (EOStructure) serviceList.objectAtIndex(i);
						posteDisplayGroup.queryBindings().setObjectForKey(
								service.cStructure(),
								"cStructure" + (i < 10 ? "0" : "") + Integer.toString(i));
					}
				} else {
					posteDisplayGroup.queryBindings().setObjectForKey(serviceSelected.cStructure(), "cStructure");
				}
				// la nature des postes
				posteDisplayGroup.setQualifier(getPosteNatureQualifier());
				posteDisplayGroup.qualifyDataSource(); // fetch
				// classer par nom
				posteDisplayGroup.setSortOrderings(EOPoste.ARRAY_SORT);
				posteDisplayGroup.updateDisplayedObjects(); // sort
			} else {
				posteDisplayGroup.setObjectArray(new NSArray());
			}

		} else if (isTypeFilsServiceAgentSelected()) {

			individuList = new NSArray();
			vCandidatEvaluationDisplayGroup.setObjectArray(new NSArray());

			if (composanteSelected != null) {
				// le display group des evaluations et la liste des individus
				// on est obligé de passer pas un tableau intermédiaire
				// car fetche sur des to-many avec des attributs derives ...
				vCandidatEvaluationDisplayGroup.queryBindings().removeAllObjects();
				if (evaluationPeriodeSelected != null) {
					vCandidatEvaluationDisplayGroup.queryBindings().setObjectForKey(evaluationPeriodeSelected, "periode");
				}
				vCandidatEvaluationDisplayGroup.qualifyDataSource(); // fetch

				// allez hop on passe par un tableau
				NSArray result = new NSArray();
				NSArray serviceList = new NSArray();
				if (serviceSelected != null) {
					serviceList = serviceList.arrayByAddingObject(serviceSelected);
				} else {
					serviceList = serviceList.arrayByAddingObjectsFromArray(composanteSelected.tosSousServiceDeep(showArchive));
				}

				// on boucle sur le bouzin
				for (int i = 0; i < vCandidatEvaluationDisplayGroup.displayedObjects().count(); i++) {
					EOVCandidatEvaluation record = (EOVCandidatEvaluation) vCandidatEvaluationDisplayGroup.displayedObjects().objectAtIndex(i);
					NSArray postes = record.tosPoste();
					for (int j = 0; j < postes.count(); j++) {
						EOPoste poste = (EOPoste) postes.objectAtIndex(j);
						if (serviceList.containsObject(poste.toStructure())) {
							result = result.arrayByAddingObject(record);
						}
					}
				}

				// la liste des individus si besoin (si aucun periode n'est selectionné)
				individuList = NSArrayCtrl.removeDuplicate(NSArrayCtrl.flattenArray(
						(NSArray) result.valueForKey(EOVCandidatEvaluation.TO_INDIVIDU_KEY)));
				// dedoubloner
				individuList = NSArrayCtrl.removeDuplicate(individuList);
				// classer
				individuList = CktlSort.sortedArray(individuList, EOIndividu.NOM_USUEL_KEY + "," + EOIndividu.PRENOM_KEY);
			}

		}

	}

	/**
	 * Obtenir le qualifier attendu sur le dgPoste selon la nature de poste
	 * selectionné
	 * 
	 * @return
	 */
	private EOQualifier getPosteNatureQualifier() {
		EOQualifier posteTypeQual = null;
		if (posteNatureSelected == null || posteNatureSelected.equals(EOPoste.POSTE_NATURE_INCONNUE)) {
			posteTypeQual = CktlDataBus.newCondition(
					EOPoste.IS_NON_ENSEIGNANT_KEY + "=%@ AND " + EOPoste.IS_ENSEIGNANT_KEY + "=%@",
					new NSArray(new Boolean[] { Boolean.FALSE, Boolean.FALSE }));
		} else if (posteNatureSelected.equals(EOPoste.POSTE_NATURE_NON_ENSEIGNANT)) {
			posteTypeQual = CktlDataBus.newCondition(EOPoste.IS_NON_ENSEIGNANT_KEY + "=%@", new NSArray(Boolean.TRUE));
		} else if (posteNatureSelected.equals(EOPoste.POSTE_NATURE_ENSEIGNANT)) {
			posteTypeQual = CktlDataBus.newCondition(EOPoste.IS_ENSEIGNANT_KEY + "=%@", new NSArray(Boolean.TRUE));
		}
		return posteTypeQual;
	}

	// navigation

	/**
	 * Selection d'une composante
	 */
	public WOComponent doComposanteSelection() {
		shouldRefreshDisplayGroup = true;
		composanteSelected = composanteItem;
		serviceSelected = null;
		posteSelected = null;
		// raz des selections
		if (isCibleFicheLolf()) {
			ficheLolfSelected = null;
		} else if (isCibleFicheDePoste()) {
			ficheDePosteSelected = null;
		} else if (isTypeFilsServiceAgentSelected()) {
			individuSelected = null;
		}
		return null;
	}

	/**
	 * Selection d'un service
	 */
	public WOComponent doServiceSelection() {
		shouldRefreshDisplayGroup = true;
		serviceSelected = serviceItem;
		posteSelected = null;
		// raz des selections
		if (isCibleFicheLolf()) {
			ficheLolfSelected = null;
		} else if (isCibleFicheDePoste()) {
			ficheDePosteSelected = null;
		} else if (isTypeFilsServiceAgentSelected()) {
			individuSelected = null;
		}
		return null;
	}

	/**
	 * Selection d'un poste
	 */
	public WOComponent doPosteSelection() {
		posteSelected = posteItem;
		// on selectionne le service au cas ou il y a eu un clic
		// composante => poste
		if (posteSelected != null && serviceSelected == null) {
			serviceSelected = posteSelected.toStructure();
		}
		// raz des selections
		if (isCibleFicheLolf()) {
			ficheLolfSelected = null;
		} else if (isCibleFicheDePoste()) {
			ficheDePosteSelected = null;
		}
		return null;
	}

	/**
	 * Selection d'une fiche de poste
	 */
	public WOComponent doFicheDePosteSelection() {
		ficheDePosteSelected = ficheDePosteItem;
		return null;
	}

	/**
	 * Selection d'une fiche Lolf
	 */
	public WOComponent doFicheLolfSelection() {
		ficheLolfSelected = ficheLolfItem;
		return null;
	}

	/**
	 * Selection d'un individu
	 */
	public WOComponent doIndividuSelection() {
		individuSelected = individuItem;
		// on selectionne le service au cas ou il y a eu un clic
		// composante => individu
		if (individuSelected != null && serviceSelected == null) {
			// trouver les services candidats
			NSArray servicesCandidats = individuSelected.tosEvaluation();
			// remonter aux services
			servicesCandidats = NSArrayCtrl.flattenArray((NSArray) servicesCandidats.valueForKey(A_EOEvaluationKeyValueCoding.TOS_STRUCTURE_KEY));
			// on prend le premier commun avec ceux de la composante
			int i = 0;
			while (i < servicesCandidats.count() - 1 && serviceSelected == null) {
				EOStructure serviceCandidat = (EOStructure) servicesCandidats.objectAtIndex(i);
				if (getServiceList().containsObject(serviceCandidat)) {
					serviceSelected = serviceCandidat;
				}
				i++;
			}
		}
		return null;
	}

	/**
	 * Tout deselectionner
	 */
	public WOComponent doClearSelection() {
		individuSelected = null;
		ficheDePosteSelected = null;
		ficheLolfSelected = null;
		posteSelected = null;
		serviceSelected = null;
		composanteSelected = null;
		// forcer un vidage des display group
		shouldRefreshDisplayGroup = true;
		return null;
	}

	// affichage

	/**
   * 
   */
	public String getTitleLnkComposanteItem() {
		String title = "";

		title = composanteItem.llStructure();

		title += " (cliquer pour afficher ses services)";

		return title;
	}

	/**
   * 
   */
	public String getTitleLnkServiceItem() {
		String title = "";

		title = serviceItem.llStructure();

		title += " (cliquer pour afficher ses postes)";

		return title;
	}

	// boolean internes et externes

	/**
	 * On exporte celui ci en bindings pour appliquer certaines restrictions dans
	 * l'interface {@link PageAdminDroits}
	 */
	public boolean isTypeFilsServicePosteSelected() {
		return typeFilsServiceSelected.equals(TYPE_FILS_SERVICE_POSTE);
	}

	public boolean isTypeFilsServiceAgentSelected() {
		return typeFilsServiceSelected.equals(TYPE_FILS_SERVICE_AGENT);
	}

	private boolean isCibleFicheDePoste() {
		return isTypeFilsServicePosteSelected() && typeFilsPosteSelected.equals(TYPE_FILS_POSTE_FICHE_DE_POSTE);
	}

	private boolean isCibleFicheLolf() {
		return isTypeFilsServicePosteSelected() && typeFilsPosteSelected.equals(TYPE_FILS_POSTE_FICHE_LOLF);
	}

	// boolean interface

	/**
   * 
   */
	public boolean isTheComposanteSelected() {
		return composanteItem == composanteSelected;
	}

	/**
   * 
   */
	public boolean isTheServiceSelected() {
		return serviceItem == serviceSelected;
	}

	/**
   * 
   */
	public boolean isThePosteSelected() {
		return posteItem == posteSelected;
	}

	/**
   * 
   */
	public boolean isTheFicheDePosteSelected() {
		return ficheDePosteItem == ficheDePosteSelected;
	}

	/**
   * 
   */
	public boolean isTheFicheLolfSelected() {
		return ficheLolfItem == ficheLolfSelected;
	}

	/**
   * 
   */
	public boolean isTheIndividuSelected() {
		return individuSelected == individuItem;
	}

	/**
	 * La liste des fiches est visible en mode poste et si un poste est
	 * selectionné
	 * 
	 * @return
	 */
	public boolean showFicheList() {
		return isTypeFilsServicePosteSelected() && posteSelected != null;
	}

	/**
   * 
   */
	public boolean showFicheDePosteList() {
		return showFicheList() && typeFilsPosteSelected.equals(TYPE_FILS_POSTE_FICHE_DE_POSTE);
	}

	/**
   * 
   */
	public boolean showFicheLolfList() {
		return showFicheList() && typeFilsPosteSelected.equals(TYPE_FILS_POSTE_FICHE_LOLF);
	}

	// getters / bindings sortants

	public EOStructure getComposante() {
		return composanteSelected;
	}

	public EOStructure getService() {
		return serviceSelected;
	}

	public EOPoste getPoste() {
		return isTypeFilsServicePosteSelected() ? posteSelected : null;
	}

	public EOFicheDePoste getFicheDePoste() {
		return isTypeFilsServicePosteSelected() && isCibleFicheDePoste() ? ficheDePosteSelected : null;
	}

	public EOFicheLolf getFicheLolf() {
		return isTypeFilsServicePosteSelected() && isCibleFicheLolf() ? ficheLolfSelected : null;
	}

	public EOIndividu getIndividu() {
		return isTypeFilsServiceAgentSelected() ? individuSelected : null;
	}

	/**
	 * On ne fait ressortir la periode que si on est en mode periode
	 * 
	 * @return
	 */
	public EOEvaluationPeriode getEvaluationPeriode() {
		return isTypeFilsServiceAgentSelected() ? evaluationPeriodeSelected : null;
	}

	// -- GETTERS --

	/**
	 * La liste de tous les services associés a la composante
	 */
	public NSArray<EOStructure> getServiceList() {
		NSArray<EOStructure> result = new NSArray<EOStructure>();

		if (composanteSelected != null) {
			//
			result = composanteSelected.tosSousServiceDeep(showArchive);
			// classement par le lcStructure
			result = CktlSort.sortedArray(result, EOStructure.LC_STRUCTURE_KEY);
			// filtrage
			if (!StringCtrl.isEmpty(filtreService)) {
				EOQualifier qual = EOStructure.getFiltreQualifier(filtreService);
				result = EOQualifier.filteredArrayWithQualifier(result, qual);
			}
		}

		return result;
	}

	// -- SETTERS ---

	/**
	 * Le changement du type des objets fils des service oblige a un rechargement
	 * des display group
	 */
	public void setTypeFilsServiceSelected(String value) {
		typeFilsServiceSelected = value;
		shouldRefreshDisplayGroup = true;
	}

	// setter sur la periode

	/**
	 * Le changement de periode implique un rechargement des display group
	 */
	public void setEvaluationPeriodeSelected(EOEvaluationPeriode value) {
		if (setEvaluationPeriodeSelectedCallCount > 0) {
			evaluationPeriodeSelected = value;
			shouldRefreshDisplayGroup = true;
		}
		setEvaluationPeriodeSelectedCallCount++;
	}

	// setter sur la periode

	/**
	 * Le changement de nature implique un rechargement des display group
	 */
	public void setPosteNatureSelected(String value) {
		if (setPosteNatureSelectedCallCount > 0) {
			posteNatureSelected = value;
			shouldRefreshDisplayGroup = true;
		}
		setPosteNatureSelectedCallCount++;
	}

	// setter sur visibilite des archives O/N

	/**
	 * On recharge la liste des composantes et des structures si l'une d'elle est
	 * selectionnee
	 */
	public void setShowArchive(boolean value) {
		showArchive = value;
		// modifier le display group
		if (!showArchive) {
			composanteDisplayGroup.queryBindings().setObjectForKey("0", EOStructure.IS_STR_ARCHIVE_KEY);
		} else {
			composanteDisplayGroup.queryBindings().removeObjectForKey(EOStructure.IS_STR_ARCHIVE_KEY);
		}
		composanteDisplayGroup.qualifyDataSource();
		// si la composante selectionnée n'est pas dans la nouvelle liste, on raz la
		// selection
		if (composanteSelected != null && !composanteDisplayGroup.displayedObjects().containsObject(composanteSelected)) {
			doClearSelection();
		} else {
			// si le service selectionné n'est pas dans la nouvelle, on raz la
			// selection
			if (serviceSelected != null && !getServiceList().containsObject(serviceSelected)) {
				doComposanteSelection();
			}
		}
	}

	/**
	 * Interception du filtre sur le libelle de la composante pour réduire la
	 * liste des résultats
	 * 
	 * @param value
	 */
	public void setFiltreComposante(String value) {
		filtreComposante = value;
		if (!StringCtrl.isEmpty(filtreComposante)) {
			EOQualifier qual = EOStructure.getFiltreQualifier(filtreComposante);
			composanteDisplayGroup.setQualifier(qual);
		} else {
			composanteDisplayGroup.setQualifier(null);
		}
		composanteDisplayGroup.qualifyDataSource();
	}

	/**
	 * Interception du filtre sur le libelle du poste ou de son occupant pour
	 * réduire la liste des résultats
	 * 
	 * @param value
	 */
	public void setFiltrePoste(String value) {
		filtrePoste = value;
		if (!StringCtrl.isEmpty(filtrePoste)) {
			EOQualifier qual = EOPoste.getFiltreQualifier(filtrePoste);
			posteDisplayGroup.setQualifier(qual);
		} else {
			posteDisplayGroup.setQualifier(null);
		}
		posteDisplayGroup.qualifyDataSource();
	}

	// setters bidons pour les bindings sortants

	public void setComposante(EOStructure value) {

	}

	public void setService(EOStructure value) {

	}

	public void setPoste(EOPoste value) {

	}

	public void setFicheDePoste(EOFicheDePoste value) {

	}

	public void setFicheLolf(EOFicheLolf value) {

	}

	public void setIndividu(EOIndividu value) {

	}

	public void setEvaluationPeriode(EOEvaluationPeriode value) {

	}

	public void setIsTypeFilsServicePosteSelected(boolean value) {

	}

}