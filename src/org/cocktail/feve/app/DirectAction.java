package org.cocktail.feve.app;
/*
 * Copyright Universit� de La Rochelle 2004
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


import java.io.IOException;

import org.cocktail.feve.components.PageLogin;
import org.cocktail.feve.components.fichedeposte.CompFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.fwkcktlwebapp.common.metier.EOCompte;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;
import org.cocktail.fwkcktlwebapp.server.CktlWebAction;
import org.cocktail.fwkcktlwebapp.server.components.CktlAlertPage;
import org.pdfbox.exceptions.COSVisitorException;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WORequest;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;

public class DirectAction extends CktlWebAction {

  public DirectAction(WORequest aRequest) {
    super(aRequest);
  }
  
  public WOActionResults defaultAction() {
    return ((Session)session()).getSavedPageWithName(PageLogin.class.getName());
  }

  /**
   * CAS : traitement authentification OK
   */
  public WOActionResults loginCasSuccessPage(String netid) {
  	EOEditingContext ec = ((Session)session()).ec();
  	EOCompte eoCompte = EOCompte.fetchByKeyValue(
  			ec, EOCompte.CPT_LOGIN_KEY, netid);
    Number persId = eoCompte.persId();
    return ((Session)session()).loginRedirect(persId);	
  }
  
  /**
   * CAS : traitement authentification en echec
   */
  public WOActionResults loginCasFailurePage(String errorMessage, String arg1) {
    StringBuffer msg = new StringBuffer();
    msg.append("Une erreur s'est produite lors de l'authentification de l'utilisateur&nbsp;:<br><br>");
    if (errorMessage != null)
      msg.append("&nbsp;:<br><br>").append(errorMessage);
    return getErrorPage(msg.toString());
	}

	/**
	 * CAS : page par defaut si CAS n'est pas parametre
	 */
	public WOActionResults loginNoCasPage() {
		return defaultAction();
	}


	/**
	 * affiche une page avec un message d'erreur
	 */
	private WOComponent getErrorPage(String errorMessage) {
	  CktlAlertPage page = (CktlAlertPage)cktlApp.pageWithName("CktlAlertPage", context());
	  page.showMessage(null, "ERREUR", errorMessage,
					   null, null, null, CktlAlertPage.ERROR, null);
	  return page;
	}
	
	
	/**
	 * retourner la page d'enquete sur formation
	 * si la variable dc est fixee, c'est le domaine de competence par defaut pour l'enquete 
	 * @return
	 *//*
	public WOActionResults enqueteAction() {
	  ((Session)session()).setNiveauConnexion(Session.NIVEAU_CONNEXION_ENQUETE);
	  String laLettreDcp =  context().request().stringFormValueForKey("dc");
	  // on initialise la page de formation si la lettre est passee
	  if (laLettreDcp != null) {
		  PageEnqueteFormation pageEnqueteFormation = (PageEnqueteFormation) ((Session)session()).getSavedPageWithName(PageEnqueteFormation.class.getName());
		  pageEnqueteFormation.externeSetLeDomaineCompetenceParDefaut(request().stringFormValueForKey("dc"));
	  }
	  return ((Session)session()).getSavedPageWithName(PageLogin.class.getName());
	}*/
    
    
    /**
     * retourner la page lolf
     * si la variable dc est fixee, c'est le domaine de competence par defaut pour l'enquete 
     * @return
     */
    public WOActionResults lolfAction() {
      ((Session)session()).setNiveauConnexion(Session.NIVEAU_CONNEXION_LOLF);
      return ((Session)session()).getSavedPageWithName(PageLogin.class.getName());
    }
    
    
    // METHODES D'EXPORT DES DONNEES (PORTLET / CANAUX ...)
    
    
    /**
     * export HTML d'une fiche de poste
     */
    public WOActionResults exportFicheDePosteAction() {
      Session session = ((Session)session());
      session.setNiveauConnexion(Session.NIVEAU_DA_FICHE_DE_POSTE);
      String fdpKeyStr =  context().request().stringFormValueForKey("fdpKey");
      CompFicheDePoste response = null;
      try {
        int fdpKey = Integer.parseInt(fdpKeyStr);
        EOFicheDePoste record = EOFicheDePoste.findFicheDePosteForFdpKeyInContext(session.ec(), fdpKey);
        response = (CompFicheDePoste) session.getSavedPageWithName(CompFicheDePoste.class.getName()); 
        // on passe la fiche de poste en binding de la page
        response.setInputLaFicheDePoste(record);
      } catch (Exception e) {
        // erreur quelconque > page blanche
      }
      return response;
    }
    
    public WOActionResults exportFicheLolfAction() {
      return null;
    }
    
    public WOActionResults exportPosteAction() {
      return null;
    }



		public WOActionResults loginCasSuccessPage(String arg0, NSDictionary arg1) {
			return loginCasSuccessPage(arg0);
		}

		public WOActionResults loginNoCasPage(NSDictionary arg0) {
			return loginNoCasPage();
		}
    
		public Session feveSession() {
	    Session feveSession = (Session) existingSession();
	    if (feveSession == null) {
	    	feveSession = (Session) session();
	    }
	    return feveSession;
	  }
	  

    /**
     * Affichage d'un document PDF.
		 * Le fichier affiche est celui dont le flux <code>NSData</code>
		 * est dans la variable <code>cngSession().lastPdfData()</code>
     * @throws COSVisitorException 
     * @throws IOException 
     */
    public WOActionResults printAction() throws IOException, COSVisitorException {
    	NSData pdfData = feveSession().getLastPdfData();
    	CktlDataResponse resp = new CktlDataResponse();
  	  if (pdfData != null) {
  	    resp.setContent(pdfData, CktlDataResponse.MIME_PDF);
  	    resp.setFileName(feveSession().getLastPdfCtrl().fileName() + ".pdf");
  	  } else {
  	  	resp.setContent("");
  	    resp.setHeader("0", "Content-Length");
  	    resp.setFileName("erreur");
  	  }   	
  	  return resp.generateResponse();
    }
}