package org.cocktail.feve.components.administration.suivi;

import org.cocktail.feve.components.poste.CompPosteListCtrl;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;


/**
 * Ecran de suivi des postes
 * 
 * @author ctarade
 */
public class SuiviPoste 
	extends A_SuiviGeneric {

	//
	public WODisplayGroup posteDg;
	
	/** le gestionnaire de la liste des postes */
	public CompPosteListCtrl compPosteListCtrl;
	
	public SuiviPoste(WOContext context) {	
		super(context);
		initComponent();
	}
	
	private void initComponent() {
		compPosteListCtrl = new CompPosteListCtrl(session);
		// definir les libelles dans le fil d'ariane
		compPosteListCtrl.setStringLabel("Suivi global des postes");
		compPosteListCtrl.setLinkLabel("Accès à la liste des postes");
		compPosteListCtrl.setLinkTitle("Retourner &agrave; la liste des postes");
	}
	
	protected void doRefreshMainDg() {
		posteDg.qualifyDataSource();
		//
		NSArray result = posteDg.allObjects();
		// ajouter le qualifier sur les types de postes
		result = EOQualifier.filteredArrayWithQualifier(result, getPosteTypeQual());
		result = EOQualifier.filteredArrayWithQualifier(result, getPosteNatureQual());
		posteDg.setObjectArray(result);
	}
	
	/**
	 * @see A_SuiviGeneric#prefixEntityDgToPoste() 
	 * @see EOPoste
	 */
	protected String prefixEntityDgToPoste() {
		return null;
	}

	protected WODisplayGroup mainDg() {
		return posteDg;
	}

	/**
	 * 
	 */
	public WOResponse printCsv() {
  	CktlDataResponse resp = new CktlDataResponse();
  	StringBuffer sb = new StringBuffer();
  	
  	// entete
   	sb.append("COMPOSANTE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("SERVICE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("CODE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("LIBELLE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("OUVERTURE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("FERMETURE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("OCCUPANT").append(CSV_COLUMN_SEPARATOR);
   	sb.append("NB F.POSTE").append(CSV_COLUMN_SEPARATOR);
   	sb.append("NB F.LOLF").append(CSV_COLUMN_SEPARATOR);
		sb.append(CSV_NEW_LINE);
     	
  	for (int i = 0; i < posteDg.allObjects().count(); i++) {
  		EOPoste poste = (EOPoste) posteDg.allObjects().objectAtIndex(i);

  		compPosteListCtrl.setPosteItem(poste);
  		
  		sb.append(poste.toComposante().lcStructure()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(poste.toStructure().lcStructure()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(poste.posCode()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(poste.posLibelle()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(DateCtrl.dateToString(poste.posDDebut())).append(CSV_COLUMN_SEPARATOR);
  		
  		String posDFin = "";
  		if (poste.posDFin() != null) {
  			posDFin = DateCtrl.dateToString(poste.posDFin());
  		}
  		sb.append(posDFin).append(CSV_COLUMN_SEPARATOR);

  		String occupant = "";
  		if (compPosteListCtrl.getRecAffectationDetail() != null &&
  				compPosteListCtrl.getRecAffectationDetail().toAffectation() != null &&
  				compPosteListCtrl.getRecAffectationDetail().toAffectation().toIndividu() != null) {
  			occupant = compPosteListCtrl.getRecAffectationDetail().toAffectation().toIndividu().display();
  		}
  		sb.append(occupant).append(CSV_COLUMN_SEPARATOR);

  		sb.append(compPosteListCtrl.getNbFicheDePoste()).append(CSV_COLUMN_SEPARATOR);
  		sb.append(compPosteListCtrl.getNbFicheLolf());

  		sb.append(CSV_NEW_LINE);
  		
		}
  	NSData stream = new NSData(sb.toString(), CSV_ENCODING);
  	resp.setContent(stream);
		resp.setContentEncoding(CSV_ENCODING);		
  	resp.setHeader(String.valueOf(stream.length()), "Content-Length");
  	resp.setFileName(DateCtrl.dateToString(DateCtrl.now(), "%Y%m%d") + "_suivi_poste.csv");
  	return resp;
	}

	@Override
	public void doApresClassement() {
		// TODO Auto-generated method stub
		
	}
}