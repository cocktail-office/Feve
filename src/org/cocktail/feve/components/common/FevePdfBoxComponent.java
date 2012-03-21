package org.cocktail.feve.components.common;


import java.lang.reflect.InvocationTargetException;

import org.cocktail.feve.app.Application;
import org.cocktail.feve.app.Session;
import org.cocktail.feve.app.print.FevePdfBoxCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.CktlDataResponse;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSNumberFormatter;
import com.webobjects.woextensions.WOLongResponsePage;

/**
 This class and the associated component creates a simple long request page.
 This page takes the information from the main page and performs the calculation
 of the prime numbers.  Note that for a long request a developer must inherit
 from the WOLongResponse page and, in this case, implement methods for the 
 long task, the refresh rate, and the task to perform when the long request is
 complete.
 */
 
/**
 * Page d'attente pour la creation d'un document PDF via SIX. 
 *  
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public class FevePdfBoxComponent extends WOLongResponsePage {
  
	// le temps de rafraichissement de la page intermédiaire (sec)
	private static final double REFRESH_INTERVAL = 2.0;

	/** le flux PDF une fois genere */
	private NSData lastPdfData;
	
	/** le controleur d'impression */
	private FevePdfBoxCtrl currentCtrl;

	/**
	 * Constructor - initializes the instance variables to default
	 * values, which will be overridden by the passed in arguments.  This
	 * is done to ensure that if any are not set by the arguments the example
	 * can continue.
	 */
	public FevePdfBoxComponent(WOContext context) {
		super(context);
		lastPdfData = null;
		setRefreshInterval(REFRESH_INTERVAL);
		setCachingEnabled(false);
	}
	
	/**
	 * Override of appendToResponse - this method increments the count (the total
	 * number of refreshes) and sets the refresh interval to the passed in
	 * value.
	 */
	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		setRefreshInterval(REFRESH_INTERVAL);

		// verification si le timeout n'est pas atteint	
		if (isDicoOver()) {
			if (f_TimeExecSix() > (float) ((Application)application()).sixTtl()) {
				currentCtrl().setTimeSix(f_TimeExecSix()*1000);
				currentCtrl().setErrSix("Le temps maximum autorisé pour la création du fichier PDF est dépassé ...");
				setRefreshInterval(0);
			}
		}
		
		super.appendToResponse( aResponse, aContext );
	}


	/**
	 * Override of performAction method - this is where the main computation is done
	 * for the example.  By placing the computation in invokeAction is is automatically
	 * performed when the component loads.  Here we use the current values of the
	 * start and stop values to calculate all of the primes.
	 */
	public Object performAction() {
		PrintFactory printFactory = new PrintFactory();
		/* This class does a setStatus: on the refresh page as it is computing
		 * another way of setting the status would be to implement
		 * -returnStatusPage, and in there, to have the main thread pole this object
		 * (first keep this object as an ivar) for the status.
		 * but then pnc needs a thread safe -status method.
		 */
		printFactory.doPrint();
		// des que le traitement est terminé, on positionne le flux PDF généré dans la session
		feveSession().setCurrentPdfData(lastPdfData);
		feveSession().setCurrentPdfCtrl(currentCtrl());
		return null;
	}
	
	private Session feveSession() {
		return (Session)session();
	}
	
	/**
	 * Method to return the result page when the computation is complete.  This
	 * methods sets the result page, passes all of the computation information,
	 * and then returns the page.
	 */
	public WOComponent pageForResult(Object result) {
		return null;
	}

	/**
	 * Il est possible que la page ne bascule pas automatiquement
	 * vers le contenu fichier PDF. Si c'est le cas, on propose
	 * un acces manuel.
	 */
	public WOResponse getPdfPage() {
		/*((Session)session()).setCurrentPdfData(lastPdfData);
		((Session)session()).setCurrentPdfCtrl(currentCtrl());
		String url = cngApp().getApplicationURL(context())+"/wa/print?wosid="+feveSession().sessionID();
		WORedirect redirect = new WORedirect(context());
		redirect.setUrl(url);
		return redirect;*/
		
		//TEMPO pour faire marcher les éditions avec IE et https .......
		CktlDataResponse resp = new CktlDataResponse();
		if (lastPdfData != null) {
			resp.setContent(lastPdfData, CktlDataResponse.MIME_PDF);
			resp.setFileName(feveSession().getLastPdfCtrl().fileName() + ".pdf");
			resp.setHeader(String.valueOf(lastPdfData.length()) , "Content-Length");

			// bidouille poitiers https et ie
			resp.disableClientCaching();
			resp.removeHeadersForKey("Cache-Control");
			resp.removeHeadersForKey("pragma");

		} else {
			resp.setHeader("0" , "Content-Length");
		}			

		return resp.generateResponse();
	}
	
	/**
	 * Le lien vers le fichier PDF a telecharge.
	 * On positionne le flux genere dans la session, et on appele
	 * la direct action qui retourne ce dernier en flux 
	 *//*
	public String getPdfHref() {
		String href = cngApp().getApplicationURL(context())+"/wa/print?wosid="+((Session)session()).sessionID();
		return href;
	}*/
	
	/**
	 * La classe qui fait le boulot
	 */
	private class PrintFactory {
		public void doPrint() {
			isWorking = true;
			lastPdfData = null;
			try {
				lastPdfData = currentCtrl().generatePdfData();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} finally {
				isWorking = false;
			}
		}		
  }
	
	/**
	 * Indique si la construction du dico des donnees est terminee
	 */
	public boolean isDicoOver() {
		return currentCtrl().getTimeDico() != null;
	}
	
	/**
	 * Indique si la construction du fichier XML est terminee
	 */
	public boolean isXmlOver() {
		return currentCtrl().getTimeXml() != null;
	}
	
	/**
	 * Indique si la generation du PDF par SIX est terminee
	 */
	public boolean isSixOver() {
		return currentCtrl().getTimeSix() != null;
	}
	
	/**
	 * Indique si la generation du PDF par SIX a echoue
	 */
	public boolean isErrSix() {
		return currentCtrl().isErrSix();
	}

	/**
	 * Indique si le message d'erreur si SIX est en erreur
	 */
	public String getStrErrSix() {
		return currentCtrl().getErrSix();
	}
	
	/**
	 * Indique s'il faut afficher un message a la fin de l'edition
	 * @return
	 */
	public boolean hasEndingMessage() {
		return !StringCtrl.isEmpty(getEndingMessage());
	}
	
	/**
	 * Indique si le message de fin de traitement
	 */
	public String getEndingMessage() {
		return currentCtrl().getEndingMessage();
	}
	
	/**
	 * Indique si la production du dico est en cours
	 */
	public boolean isDicoStep() {
		return !isDicoOver();
	}
	
	/**
	 * Indique si la production du dico est en cours
	 */
	public boolean isXmlStep() {
		return isDicoOver() && !isXmlOver();
	}
	
	/**
	 * Indique si la production du dico est en cours
	 */
	public boolean isSixStep() {
		return isXmlOver() && !isSixOver();
	}

	/**
	 * Le temps de calcul du dico.
	 * Si termine -> la valeur du controleur
	 * Si en cours : date debut d'execution du controleur - date actuelle
	 */
	public String timeExecDico() {
		Number result = null;
		if (isDicoOver()) {
			result = new Float(currentCtrl().getTimeDico().floatValue()/1000.0);
		} else {
			if (isDicoStep()) {
				if (currentCtrl().getTimeStart() == null) {
					result = new Float(0);
				} else {
					result = new Float((System.currentTimeMillis()-currentCtrl().getTimeStart().longValue())/1000.0);
				}
			} else {
				result = new Float(0);
			}
		}	
		return numberFormatter().format(result);
	}
	
	/**
	 * Le temps de calcul du XML.
	 * Si termine -> la valeur du controleur
	 * Si en cours : date debut d'execution du controleur - date dico
	 * Si pas termine : 0
	 */
	public String timeExecXml() {
		Number result = null;
		if (isXmlOver()) {
			result = new Float(currentCtrl().getTimeXml().floatValue()/1000.0);
		} else {
			if (isXmlStep()) {
				result = new Float((System.currentTimeMillis()-currentCtrl().getTimeDico().longValue()-currentCtrl().getTimeStart().longValue())/1000.0);
			} else {
				result = new Float(0);
			}
		}
		return numberFormatter().format(result);
	}
	
	/**
	 * Le temps de calcul du SIX.
	 * Si termine -> la valeur du controleur
	 * Si en cours : date debut d'execution du controleur - (duree dico + xml)
	 * Si pas termine : 0
	 */
	public String timeExecSix() {
		return numberFormatter().format(f_TimeExecSix());
	}
	
	/**
	 * Le temps de calcul du SIX.
	 * Si termine -> la valeur du controleur
	 * Si en cours : date debut d'execution du controleur - (duree dico + xml)
	 * Si pas termine : 0
	 */
	private float f_TimeExecSix() {
		float result = (float)0;	
		if (isSixOver()) {
			result = currentCtrl().getTimeSix().floatValue()/(float)1000;
		} else {
			if (isSixStep()) {
				result = (System.currentTimeMillis()
						- (currentCtrl().getTimeXml().longValue()+currentCtrl().getTimeDico().longValue())
						- currentCtrl().getTimeStart().longValue())/(float)1000;
			} else {
				result = (float)0;
			}
		}
		return result;
	}
	
	/**
	 * Affichage des durees
	 */
	private NSNumberFormatter numberFormatter;
	
	public NSNumberFormatter numberFormatter() {
		if (numberFormatter == null) {
			numberFormatter = new NSNumberFormatter();
		  numberFormatter.setPattern("#,##0.00 s");
		  numberFormatter.setHasThousandSeparators(true);
		  numberFormatter.setThousandSeparator(" ");
		}
		return numberFormatter;
	}
	
	/**
	 * 
	 */
	public void setCurrentCtrl(FevePdfBoxCtrl value) {
		currentCtrl = value;
	}
	
	/**
	 * 
	 */
	public FevePdfBoxCtrl currentCtrl() {
		return currentCtrl;
	}

	// suppression de la variable static qui bloquait tout
	// le monde si SIX bloque
	private boolean isWorking = false;
	
	public boolean isWorking() {
		return isWorking;
	}

	public String getFileName() {
		return currentCtrl().fileName() + ".pdf";
	}
}