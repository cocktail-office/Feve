/*
 * Copyright Universit� de La Rochelle 2005
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
package org.cocktail.ycrifwk.utils;

import java.util.GregorianCalendar;

import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.foundation.NSTimestamp;


/**
 * @author ctarade 31 mars 2005
 * classe dediee a la gestion des dates
 */
public class UtilDate {

	
	/**
	 * retourne l'objet de type GregorianCalendar associe a un NSTimeStamp
	 * @param date
	 * @return
	 */
	public static GregorianCalendar dateToCalendar(NSTimestamp date) {
		GregorianCalendar myCalendar = new GregorianCalendar();
		myCalendar.setTime(date);
		return myCalendar;
	}
    
	/**
	 * @deprecated utiliser getIntAnneeEnCours
	 * retourne l'annee en cours
	 * @return
	 */
	public static int currentAnnee() {
	    return getIntAnneeEnCours();
	}
	
	public static int getIntAnneeEnCours() {
		return getAnneeForDate(DateCtrl.now());
	    
	}
	
	/**
	 * retourne l'annee d'une date
	 * @return
	 */
	public static int getAnneeForDate(NSTimestamp date) {
		return dateToCalendar(date).get(GregorianCalendar.YEAR);
	}
	
	
	/**
	 * @deprecated utiliser getStrAnneeUnivEnCours
	 * donne l'annee universitaire en cours (ex: 2003-2004)
	 * @return
	 */
	public static String currentAnneeUniv() {
		return getStrAnneeUnivEnCours();
	}
	
	public static String getStrAnneeUnivEnCours() {
		int currentAnnee = currentAnnee();
		if (DateCtrl.isBefore(new NSTimestamp(), DateCtrl.stringToDate("01/09/"+currentAnnee)))
			return (currentAnnee-1)+"-"+currentAnnee;
		else
			return currentAnnee+"-"+(currentAnnee+1);
	}

	
	/**
	 * @deprecated utiliser getStrAnneeUnivSuivante
	 * donne l'annee universitaire suivante (ex: 2003-2004)
	 * @return
	 */
	public static String nextAnneeUniv() {
	    return getStrAnneeUnivSuivante();
	}
	
	public static String getStrAnneeUnivSuivante() {
		int currentAnnee = currentAnnee();
		if (DateCtrl.isBefore(new NSTimestamp(), DateCtrl.stringToDate("01/09/"+currentAnnee)))
			return currentAnnee+"-"+(currentAnnee+1);
		else
			return (currentAnnee+1)+"-"+(currentAnnee+2);
	}

	
	//TODO dateToDebutAnneeUniv : se caller sur les params de l'appli
    /**
     * retourne le debut de l'annee universitaire d'une date
     * ex : 02/02/2005 -> 01/09/2004
     * @param uneDate
     * @return
     */
    public static NSTimestamp dateToDebutAnneeUniv(NSTimestamp uneDate) {
        // on transltate la date sur l'annee civile : 01/09/2004 -> 01/01/2004
        NSTimestamp uneDateDecalee = uneDate.timestampByAddingGregorianUnits(0,-8,0,0,0,0); 
		GregorianCalendar nowGC = new GregorianCalendar();
		nowGC.setTime(uneDateDecalee);
		NSTimestamp debutAnneeDecalee = uneDateDecalee.timestampByAddingGregorianUnits(
		        0,
		        -nowGC.get(GregorianCalendar.MONTH),
		        -nowGC.get(GregorianCalendar.DAY_OF_MONTH)+1,
		        -nowGC.get(GregorianCalendar.HOUR_OF_DAY),
		        -nowGC.get(GregorianCalendar.MINUTE),
		        -nowGC.get(GregorianCalendar.SECOND)
		        );
		NSTimestamp debutAnnee = debutAnneeDecalee.timestampByAddingGregorianUnits(0,8,0,0,0,0);
		return debutAnnee;
	}

    /**
     * retourne la fin de l'annee universitaire d'une date
     * ex : 02/02/2005 -> 31/08/2005
     * @param uneDate
     * @return
     */
     public static NSTimestamp dateToFinAnneeUniv(NSTimestamp uneDate) {
         return dateToDebutAnneeUniv(uneDate).timestampByAddingGregorianUnits(1,0,-1,0,0,0);
     }
 
 }
