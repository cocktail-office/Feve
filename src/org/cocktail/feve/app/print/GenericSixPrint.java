/*
 * Copyright Consortium Coktail, 16 mai 07
 * 
 * cyril.tarade at univ-lr.fr
 * 
 * Ce logiciel est un programme informatique servant � [rappeler les
 * caract�ristiques techniques de votre logiciel]. 
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. Vous pouvez
 * utiliser, modifier et/ou redistribuer ce programme sous les conditions
 * de la licence CeCILL telle que diffus�e par le CEA, le CNRS et l'INRIA 
 * sur le site "http://www.cecill.info".
 * 
 * En contrepartie de l'accessibilit� au code source et des droits de copie,
 * de modification et de redistribution accord�s par cette licence, il n'est
 * offert aux utilisateurs qu'une garantie limit�e.  Pour les m�mes raisons,
 * seule une responsabilit� restreinte p�se sur l'auteur du programme,  le
 * titulaire des droits patrimoniaux et les conc�dants successifs.
 * 
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
 * 
 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez 
 * pris connaissance de la licence CeCILL, et que vous en avez accept� les
 * termes.
 */

package org.cocktail.feve.app.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import org.cocktail.feve.app.Application;
import org.cocktail.feve.app.FeveUserInfo;
import org.cocktail.feve.app.Session;
import org.cocktail.feve.utils.FeveStringCtrl;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.print.CktlPrinter;
import org.cocktail.fwkcktlwebapp.common.util.CktlXMLWriter;
import org.cocktail.fwkcktlwebapp.common.util.CktlXMLWriter.SpecChars;
import org.cocktail.fwkcktlwebapp.common.util.FileCtrl;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.pdfbox.examples.persistence.AppendDoc;
import org.pdfbox.exceptions.COSVisitorException;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

/**
 * La classe dont herite toutes les classes d'impressions SIX.
 * Cette methode herite de thread pour avoir une execution
 * non blocante de l'application globale.
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public abstract class GenericSixPrint {
	
	/** */
	private Session session;
	/** */
	private String resultFile;
	/** */
	private String maquetteId;
	/** */
	private String xmlFileName;
	/** */
	private NSDictionary dico;
	/** */
	private NSData resultData;
	/** */
	private FevePdfBoxCtrl ctrl;
	/** */
	private String endingMessage;

	/** Le buffer utiliser pour afficher les messages dans la console */
	private StringBuffer logBuffer;
	
	/**
	 * Constructeur que chaque classe doit surchager
	 * 
	 * @param aFeveUserInfo : les informations sur la personne connectée
	 * @param aResultFile : le chemin du fichier PDF genere
	 * @param aCtrl : le controleur d'impression associe
	 * @param aMaquetteId : l'id de la maquette SIX a utiliser
	 * @param aXmlFileName : le chemin du fichier XML genere
	 * @param aDico : les donnees parametres de cette impression
	 * @param session : la session de l'utilisateur connecté
	 */
	public GenericSixPrint(
			Session aSession, String aResultFile, 
			String aMaquetteId, String aXmlFileName, 
			NSDictionary aDico, FevePdfBoxCtrl aCtrl) {
		super();
		session = aSession;
		resultFile = aResultFile;
		maquetteId = aMaquetteId;
		xmlFileName = aXmlFileName;
		dico = aDico;
		ctrl = aCtrl;
		// le message de fin doit etre contenu dans le dictionnaire
		endingMessage = (String) dico.valueForKey(PrintConsts.DICO_KEY_ENDING_MESSAGE);
		logBuffer = new StringBuffer();
	}
	
	/**
	 * Methode de creation du fichier XML qui sera passe
	 * au moteur SIX pour creation du PDF
	 */
	protected abstract void generateXml(CktlXMLWriter w) throws IOException;
	
	/**
	 * La liste des adresse URL des documents qu'il faut ajouter a la 
	 * fin du fichier PDF constitué
	 * @return
	 */
	public NSArray staticDocumentUrlList() {
		return null;
	}
	
	/**
	 * Indique s'il faut ajotuer des documents a la fin de celui ci
	 */
	private boolean shouldAppendStaticDocuments() {
		return !NSArrayCtrl.isEmpty(staticDocumentUrlList());
	}
	
	/**
	 * Effectue la generation du fichier XML et la 
	 * generation du fichier PDF associe. Retourne le
	 * flux PDF.
	 * @throws Exception 
	 */
	public NSData builtPdfData() {
		resultData = null;
 		ctrl.setEndingMessage(endingMessage);
 	 	try { 
			long tStartXml = System.currentTimeMillis();
			CktlXMLWriter w = new CktlXMLWriter(xmlFileName);
			//w.setCharsToEscape("<>&");
			generateXml(w);
			ctrl.setTimeXml(new Long(System.currentTimeMillis() - tStartXml));
			long tStartSix = System.currentTimeMillis();
			resultData = getPdfFromSixServer();
			ctrl.setTimeSix(new Long(System.currentTimeMillis() - tStartSix));
			if (shouldAppendStaticDocuments()) {
				resultData = appendStaticDocuments();
			}
		}
		catch(Exception ex) {
  		ex.printStackTrace();
  		logBuffer.append(" Erreur d'ecriture d'un document XML :\n"+ex.getMessage());
  		resultData = null;
		} finally {
  		CktlLog.log(logBuffer.toString());
  	}
		return resultData;
	}
	

	 
  /**
   * Permet de fusionner un document PDF avec d'autres
   * @throws COSVisitorException 
   * @throws IOException 
   */
  private NSData appendStaticDocuments() throws IOException, COSVisitorException {
   	
  	// il faut d'abord ecrire le flux resultData dans un fichier
   	String tempFilename = PrintConsts.PDF_APPEND_TEMP_DOC;
  	FileOutputStream pouet = new FileOutputStream(tempFilename);
  	pouet.write(resultData.bytes());

  	AppendDoc appendDoc = new AppendDoc();
  	NSArray documentList = staticDocumentUrlList();
  	
  	for (int i=0; i<documentList.count(); i++) {
  		// 
  		String strDocumentUrl = (String) documentList.objectAtIndex(i);
     	// determiner le nom du fichier
    	String strFilename = getFile(strDocumentUrl);
    	//
  		appendDoc.doIt(tempFilename, strFilename, tempFilename);
  		// nettoyer
  		FileCtrl.deleteFile(strFilename);
  	}
  	
  	NSData result = new NSData(new File(tempFilename));

		// nettoyer
		FileCtrl.deleteFile(tempFilename);

		return result;
  }
	
  /**
   * T�l�charger un fichier
   * @param u
   * @throws IOException
   * @return le chemin du fichier
   */
  public String getFile(String documentUrl) throws IOException {
  	URL url = new URL(documentUrl);
  	URLConnection uc = url.openConnection();
  	int fileLenght = uc.getContentLength();
  	if (fileLenght == -1) {
  		throw new IOException("Fichier non valide.");
  	}
  	InputStream in = uc.getInputStream();
  	String fileName = url.getFile();
  	fileName = "/tmp/Downloaded_"+FileCtrl.getFileName(fileName);
  	FileOutputStream fos = new FileOutputStream(fileName);
  	byte[]buff = new byte[1024];
  	int l = in.read(buff);
  	while(l>0) {
  		fos.write(buff, 0, l);
  		l = in.read(buff);
  	}
  	fos.flush();
  	fos.close();
  	return fileName;
  } 
  
  
  /** 
   * Retourne le flux pdf de l'edition une fois
   * generee par le moteur SIX
   */
	private NSData getPdfFromSixServer() {
    // On suppose que cette methode existe
    Hashtable<String, String> params = new Hashtable<String, String>();
    
    Application app = (Application) Application.application();
    
    params.put("XML_PRINTER_DRIVER",    app.config().stringForKey("XML_PRINTER_DRIVER"));
    params.put("SIX_SERVICE_HOST",      app.config().stringForKey("SIX_SERVICE_HOST"));
    params.put("SIX_SERVICE_PORT",      app.config().stringForKey("SIX_SERVICE_PORT"));
    params.put("SIX_USE_COMPRESSION",   app.config().stringForKey("SIX_USE_COMPRESSION"));

    logBuffer.append("generating SIX stream "+maquetteId);
    
    // On cree et on initialise le pilote d'impression
    CktlPrinter printer = null;
    try {
    	printer = CktlPrinter.newDefaultInstance(params);
    } catch (ClassNotFoundException e) {
    	// Le pilote n'a pas ete trouve
    	logBuffer.append(" >> Erreur : Pilote introuvable ").append(e.getMessage());
    	return null;
    }

    printer.printFileImmediate(maquetteId, xmlFileName, resultFile);
    
    // On verifie si l'operation est OK
    logBuffer.append(" - ");
    if (printer.hasSuccess()) {
    	logBuffer.append("OK");
    } else {
    	logBuffer.append("FAIL");
    	ctrl.setErrSix(printer.getMessage());
    }
    logBuffer.append(" - ").append(printer.getMessage());
     
    NSData datas = null;

    try {
    	datas = new NSData(new File(resultFile));
    } catch (Exception e) {
    	datas = null;
    } finally {
    	// nettoyage des fichiers
    	//FileCtrl.deleteFile(resultFile);
    	//FileCtrl.deleteFile(xmlFileName);
    }
    return datas;
  }

  /**
   * 
   */
	public EOEditingContext ec() {
		return session.defaultEditingContext();
	}

  /**
   * 
   */
	public Application app() {
		return (Application) session.application();
	}

	/**
	 * 
	 */
	public NSDictionary dico() {
		return dico;
	}
	
  /**
   * permet de remplacer les string null en string "" et les "&" par "&amp;"
   * @param string
   * @return
   */
  protected String checkString(String string) {
  	String str = "";
  	if (string != null) {
  		// sauts de ligne
  		str = StringCtrl.replace(string, "<br>", "\n");
  		str = StringCtrl.replace(str, "<br/>", "\n");
  		str = StringCtrl.replace(str, "\n", SpecChars.br);
  		// et commercial
  		str = StringCtrl.replace(str, "&", SpecChars.amp);

  		// worderies
  		str = FeveStringCtrl.cleanWordSpecs(str);

  		// pour remettre les sauts de lignes ...
  		String saut = SpecChars.amp + SpecChars.br.substring(1, SpecChars.br.length());
  		str = StringCtrl.replace(str, saut, SpecChars.br);
  	}
  	return str;
  }
  
  /**
   * on remplace tous les "\n" par le code de saut de ligne
   * @param unDico
   * @return
   */
  protected NSDictionary cleanDico(NSDictionary unDico) {
  	// on remplace tous les "\n" par le code de saut de ligne
  	NSMutableDictionary dico = new NSMutableDictionary();
  	for (int i = 0; i < unDico.allKeys().count(); i++) {
  		String aKey = (String) unDico.allKeys().objectAtIndex(i);
  		// on a soit des string, soit des NSArray de String
  		Object anObject = unDico.objectForKey(aKey);
  		if (anObject instanceof String) {
  			dico.setObjectForKey(StringCtrl.replace((String) anObject , "\n", SpecChars.br), aKey);
  		} else if (anObject instanceof NSArray) {
  			NSArray anArray = (NSArray) anObject;
  			NSMutableArray newArray = new NSMutableArray();
  			for (int j = 0; j < anArray.count(); j++) {
  				if (anArray.objectAtIndex(j) instanceof String) {
  					String aString = (String) anArray.objectAtIndex(j);
  					newArray.addObject(StringCtrl.replace(aString , "\n", SpecChars.br));
  				} else {
  					newArray.addObject(anArray.objectAtIndex(j));                    
  				}                  
  			}
  			dico.setObjectForKey(newArray.immutableClone(), aKey);
  		} else {
  			dico.setObjectForKey(anObject, aKey);
  		}
  	}
  	
  	return dico;
  }

	public final FeveUserInfo feveUserInfo() {
		return feveSession().feveUserInfo();
	}

	public final Session feveSession() {
		return session;
	}

}
