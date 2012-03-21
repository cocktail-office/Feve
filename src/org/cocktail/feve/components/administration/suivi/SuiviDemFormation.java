package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.feve.eos.modele.grhum.EOStructure;
import org.cocktail.feve.eos.modele.grhum.EOVService;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheItem;
import org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee;
import org.cocktail.feve.eos.modele.mangue.EOTplItem;
import org.cocktail.feve.eos.modele.mangue.I_ToEvaluation;
import org.cocktail.feve.eos.modele.mangue.I_RepartFormation;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;

/**
 * Page de suivi des demandes de formation
 * 
 * @author ctarade
 */
public class SuiviDemFormation
	extends A_SuiviGeneric {
	
	// liste des formations bloc champ libre
	public NSMutableArray<I_RepartFormation> repartFormationList;
	public I_RepartFormation repartFormationItem;

	//
	public WODisplayGroup periodeDg;
	public WODisplayGroup serviceDg;
	
	public SuiviDemFormation(WOContext context) {
		super(context);
		initComponent();
	}
	
	/**
	 * 
	 */
	private void initComponent() {
		// on preselectionne la periode actuelle
		periodeSelected = EOEvaluationPeriode.getCurrentPeriode(ec);
		// ne pas rafraichir le DG principal tout se suite
		shouldRefreshMainDg = false;
	}
	

	/**
	 * XXX plus utilisé en ajax ...
	 * On n'affiche que les evaluation avec une demande de formation
	 */
	protected void doRefreshMainDg() {
	
	}
	
	/**
	 *
	 */
	public void setServiceSelected(EOVService value) {
		serviceSelected = value;
		repartFormationList = null;
	}
	

	/**
	 * 
	 */
	public void setPeriodeSelected(EOEvaluationPeriode value) {
		periodeSelected = value;
		repartFormationList = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public NSArray<I_RepartFormation> getRepartFormationList() {
		if (repartFormationList == null) {
			
			repartFormationList = new NSMutableArray<I_RepartFormation>();		
			
			// ne conserver que les evaluations qui ont quelque chose en formation souhaitees
			repartFormationList.addObjectsFromArray(
					EORepartFicheItem.fetchRepartFicheItems(
							ec, 
							CktlDataBus.newCondition(
									EORepartFicheItem.TO_EVALUATION_KEY+"."+EOEvaluation.TO_EVALUATION_PERIODE_KEY+"=%@ and "+
									EORepartFicheItem.TO_TPL_ITEM_KEY+"."+EOTplItem.TIT_CODE_KEY+"=%@ and "+
									EORepartFicheItem.RFI_VALEUR_LIBRE_KEY + "<> nil", 
										new NSArray(new Object[]{
												periodeSelected, EOTplItem.CODE_FORMATIONS_SOUHAITEES})),
							CktlSort.newSort(
									EORepartFicheItem.TO_EVALUATION_KEY + "." + EOEvaluation.D_VISA_RESPONSABLE_RH_KEY)));
			
			// pour le dynamique, c'est toujours non vide
			repartFormationList.addObjectsFromArray(
					EORepartFormationSouhaitee.fetchRepartFormationSouhaitees(
							ec, 
							CktlDataBus.newCondition(
									EORepartFormationSouhaitee.TO_EVALUATION_KEY + "." + EOEvaluation.TO_EVALUATION_PERIODE_KEY + "=%@",
									new NSArray<EOEvaluationPeriode>(periodeSelected)),
							CktlSort.newSort(
									EORepartFormationSouhaitee.TO_EVALUATION_KEY + "." + EOEvaluation.D_VISA_RESPONSABLE_RH_KEY)));
			
			// filtrage en memoire sur des to-many -> faut se le palucher a la main :(
			if (serviceSelected != null) {
				
				// pour les champs libres
				NSMutableArray<I_RepartFormation> repartFormationListFilteredInMemory = new NSMutableArray<I_RepartFormation>();
				for (int i=0; i<repartFormationList.count(); i++) {
					boolean shouldAdd = false;
					I_RepartFormation repart = repartFormationList.objectAtIndex(i);
					// filtrer sur le service
					NSArray<EOStructure> structures = repart.toEvaluation().tosStructure();

					for (int j=0; j<structures.count(); j++) {
						EOStructure structure = (EOStructure) structures.objectAtIndex(j);
						if (structure.cStructure().equals(serviceSelected.toStructure().cStructure())) {
							shouldAdd = true;
							break;
						}
						shouldAdd = false;
					}
					// on l'ajoute au final
					if (shouldAdd) {
						repartFormationListFilteredInMemory.addObject(repart);
					}
				}
				
				repartFormationList = repartFormationListFilteredInMemory;
			}

			// classement
			if (getSortStringSelected() != null) {
				repartFormationList = (NSMutableArray<I_RepartFormation>) CktlSort.sortedArray(
						repartFormationList, getSortStringSelected(), getSortOrder());
			}
			
		}
		return repartFormationList;
	}
	

	/**
	 * Pas besoin ici
	 */
	protected WODisplayGroup mainDg() {
		return null;
	}

	/**
	 * Pas besoin ici
	 */
	protected String prefixEntityDgToPoste() {
		return null;
	}
	
	
	// edition
	
	public WOResponse printCsv() {
  	CktlDataResponse resp = new CktlDataResponse();
  	StringBuffer sb = new StringBuffer();
  	for (int i = 0; i < repartFormationList.count(); i++) {
  		I_RepartFormation repart = repartFormationList.objectAtIndex(i);
  		String nomPrenomEvaluateur = "inconnu";
  		if (repart.toEvaluation().toEvaluateur() != null) {
  			nomPrenomEvaluateur = repart.toEvaluation().toEvaluateur().nomPrenom();
  		}
  		sb.append(nomPrenomEvaluateur).append(CSV_COLUMN_SEPARATOR);
  		sb.append(repart.toEvaluation().toIndividu().nomPrenom()).append(CSV_COLUMN_SEPARATOR);
  		String libelleFormation = repart.libelleFormation().trim();
  		libelleFormation = StringCtrl.replace(libelleFormation, "\n", " / ");
  		libelleFormation = StringCtrl.replace(libelleFormation, ";", " / ");
  		libelleFormation = StringCtrl.replace(libelleFormation, "\r", "");
  		sb.append(libelleFormation).append(CSV_COLUMN_SEPARATOR);
  		String strDTenueEntretien = "non fait";
  		if (repart.toEvaluation().isEntretienTenu()) {
  			strDTenueEntretien = DateCtrl.dateToString(repart.toEvaluation().dTenueEntretien());
  		}
  		sb.append(strDTenueEntretien).append(CSV_COLUMN_SEPARATOR);
  		String strDVisaResponsable = "non visé";
  		if (repart.toEvaluation().isViseParResponsableRh()) {
  			strDVisaResponsable = DateCtrl.dateToString(repart.toEvaluation().dVisaResponsableRh());
  		}
  		sb.append(strDVisaResponsable).append(CSV_COLUMN_SEPARATOR);
  		sb.append(CSV_NEW_LINE);
		}
  	NSData stream = new NSData(sb.toString(), CSV_ENCODING);
  	resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);		
  	resp.setHeader(String.valueOf(stream.length()), "Content-Length");
  	resp.setFileName("suivi_demandes_formation.csv");
  	return resp;
	}
	
	
	// classement
	
	public void sortEvaluateur() {
		faireClassement(I_ToEvaluation.SORT_EVALUATEUR);
	}
	
	public void sortAgent() {
		faireClassement(I_ToEvaluation.SORT_AGENT);
	}
	
	public void sortFormationSouhaitee() {
		faireClassement(I_RepartFormation.SORT_FORMATION_SOUHAITEE);
	}
	
	public void sortIsNomenclature() {
		faireClassement(I_RepartFormation.SORT_IS_NOMENCLATURE);
	}
	
	public void sortDateEntretien() {
		faireClassement(I_ToEvaluation.SORT_DATE_ENTRETIEN);
	}
	
	public void sortDateVisaRh() {
		faireClassement(I_ToEvaluation.SORT_DATE_VISA_RH);
	}
	
	// modification des données

	public EORepartFormationSouhaitee repartFormationSelected;
	public EOFormationPersonnel eoFormationPersonnelSelected;
	public String champLibre;
	public boolean isNomenclature;

	/**
	 * Modification de la formation en cours
	 */
	public WOComponent editFormation() {
		repartFormationSelected = (EORepartFormationSouhaitee) repartFormationItem;
		eoFormationPersonnelSelected = repartFormationSelected.toFormationPersonnel();
		champLibre = repartFormationSelected.libelleFormation();
		mode = MODE_EDIT;
		return null;
	}
	
	// mode d'utilisation du composant
	public int mode;
	private final static int MODE_READ 	= 0;
	private final static int MODE_EDIT 	= 1;
	
	/**
	 * On autorise la modification de la formation {@link #repartFormationItem} uniquement
	 * en lecture seule, et pour les données provenant de l'entité
	 * {@link EORepartFormationSouhaitee}
	 */
	public boolean isShowLnkEditFormation() {
		boolean isShow = true;
		
		if (mode != MODE_READ) {
			isShow = false;
		}
		
		if (isShow &&
				!(repartFormationItem instanceof EORepartFormationSouhaitee)) {
			isShow = false;
		}
		
		return isShow;
	}
	
	
	/**
	 * Determine quelle est la ligne en cours de modification.
	 * On ne modifie que si le mode le permet et que si la formation
	 * en cours <code>repartFormationItem</code> est la meme que la selection
	 * <code>repartFormationSelected</code>
	 */
	public boolean isEditingCurrentFormation() {
		boolean isEditing = false;
		
		if (mode == MODE_EDIT) {
			isEditing = (repartFormationItem == repartFormationSelected);
		}
		
		return isEditing;
	}
	
	/**
	 * Enregistrement des modifications d'une formation existante
	 * @return
	 * @throws Throwable 
	 */
	public WOComponent doEditFormation() throws Throwable {
		clearError();
		
		if (isNomenclature) {
			repartFormationSelected.setToFormationPersonnelRelationship(eoFormationPersonnelSelected);
			repartFormationSelected.setChampLibre(null);
		} else {
			repartFormationSelected.setToFormationPersonnelRelationship(null);
			repartFormationSelected.setChampLibre(champLibre);
		}
		
		if (sauverEtGestionMessageErreur()) {
			// forcer le rechargement de la liste en vidant le cache
			//clearCache();
			// repasser en mode consultation
			mode = MODE_READ;
			//
			repartFormationSelected = null;
		}
		
		return null;
	}
	
	
	/**
	 * Sauvegarder l'operation en cours et met a jour le
	 * message d'erreur s'il y en a une
	 * @return <code>true</code> si aucun pb
	 */
	private boolean sauverEtGestionMessageErreur() {
		boolean noError = true;
		ec.lock();
		try {
			ec.saveChanges();
		} catch (Throwable e) {
			errorMessage = e.getMessage();
			ec.revert();
			noError = false;
		} finally {
			ec.unlock();
		}
		return noError;
	}
	
	// message d'erreur
	public String errorMessage;
	
	private void clearError() {
		errorMessage = StringCtrl.emptyString();
	}

	
	/**
	 * On ne peut annuler une modification
	 * de la formation en cours <code>repartFormationSelected</code>
	 * (formulaire active)
	 */
	public boolean isShowLnkCancel() {
		boolean isShow = false;

		if (mode == MODE_EDIT &&
				repartFormationItem == repartFormationSelected) {
			isShow = true;
		}
		
		return isShow;
	}
	

	
	/**
	 * Annuler l'operation en cours
	 * @return
	 */
	public WOComponent doCancel() {
		// annuler les modifications / insertions
		ec.revert();
		// nettoyage des eventuelles erreurs
		clearError();
		// repasser en mode consultation
		mode = MODE_READ;
		return null;
	}

	@Override
	public void doApresClassement() {
		repartFormationList = null;
	}
	
}