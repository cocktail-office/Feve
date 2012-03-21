package org.cocktail.feve.components.common;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;


/**
 * 
 * @author ctarade
 *
 * Composant HTML permettant de selectionner des items parmi une liste.
 * . Cette liste peut-etre statique (parametre rechercheActive = false), auquel cas le tableau lesObjetsDisponibles
 * doit etre renseign�
 * . Cette liste peut-etre dynamique (parametre rechercheActive = true), auquel cas il faudra renseigner
 * dans la classe listener, la methode de recherche
 */

public class CompListeGenerique extends FeveWebComponent {

    private NSArray lesObjets; 
    private Class classeListener;
    private int nbObjetsMaximum;
    private boolean rechercheActive;
    private boolean disabled = false;	// valeur par defaut disabled
    
    private boolean lienActif = false;	// valeur par defaut lien actif : elements cliquables
    private Method methodeClicObjet = null;	// methode a executer si les liens sont actifs
    
    public Object unObjet;				// doit etre passe en param pour le clic sur lien fonctionne
    public Object lObjetSelectionne;
    public Object lObjetDisponibleSelectionne;
    
    private NSArray lesObjetsDisponibles;
    private NSArray lesObjetsPrecharges;
     
    public String chaineAChercher;
    
    private Object classeListenerInstance;
    
    private boolean modeModification = false;
    
    public CompListeGenerique(WOContext context) {
        super(context);
    }

    private Object classeListenerInstance() {
        if (classeListenerInstance == null) {
            try {
                classeListenerInstance = classeListener.newInstance();
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return classeListenerInstance;
    }
    
	/**
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalAccessException
	 */
	public WOComponent rechercher() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	    
	    // la recherche ne modifie la liste des objets que si elle est active
	    if (rechercheActive()) {

	        // mise en majuscules
		    if (chaineAChercher != null) {
		        chaineAChercher = chaineAChercher.toUpperCase();
		 
		        // recuperation de la methode qui retourne la methode de recherche
		        Class[] typesRecupRechercher = {};
		        Method methodeRecupRechercher = classeListener.getMethod("rechercherObjets", typesRecupRechercher);
		        
		        // recuperation de la methode recherche
		        Object[] paramsRecupRechercher = {};
			    Method methodeRechercher = (Method)methodeRecupRechercher.invoke(classeListenerInstance(), paramsRecupRechercher);
		        
			    // appel de la methode de recherche
		        Object[] paramsRechercher = {ec, chaineAChercher};
		        NSArray lesObjetsTrouves = (NSArray)methodeRechercher.invoke(classeListenerInstance(), paramsRechercher);
		        
		        setLesObjetsDisponibles(lesObjetsTrouves);
		    }
	    }

	    
		// selection de la premiere valeur
		if (lesObjetsDisponibles() != null && lesObjetsDisponibles().count() > 0) {
		    lObjetDisponibleSelectionne = lesObjetsDisponibles().objectAtIndex(0);
		}
		
		return null;
	}
	
	/**
	 * ajouter le compte resultat de la recherche a la liste des membres
	 * @return
	 */
	public WOComponent ajouterObjet() {
	    // ajout de l'individu
		NSMutableArray lesObjetsMutables = new NSMutableArray(lesObjets);
		lesObjetsMutables.addObject(lObjetDisponibleSelectionne);
		lesObjets = lesObjetsMutables.immutableClone();
		
		// on raz les resultats de la recherche
		if (rechercheActive()) {
		    setLesObjetsDisponibles(new NSArray());
		}
		
		// raz de la selection
		lObjetDisponibleSelectionne = null;
		return null;	
	}
	
	public WOComponent modifier() {
	    modeModification = true;
	    return null;
	}
	
	public WOComponent verrouiller() {
	    modeModification = false;
	    return null;
	}
	
	
	/**
	 * disponibilite du bouton d'ajout d'un compte : la selection ne doit pas etre vide
	 * @return
	 */
	public boolean isDisabledAjouterIndivdu() {
		return lObjetDisponibleSelectionne == null;
	}
	
	/**
	 * supprimer le compte en cours de la liste des membres
	 * @return
	 */
	public WOComponent removeObjet() {
		// suppression de l'individu
	    NSMutableArray lesIndividusMutable = new NSMutableArray(lesObjets);
	    lesIndividusMutable.removeObject(unObjet);
	    lesObjets = lesIndividusMutable.immutableClone();
	    
		// on raz les resultats de la recherche
		if (rechercheActive()) {
		    setLesObjetsDisponibles(new NSArray());
		}
		
		// raz de la selection
		lObjetDisponibleSelectionne = null;
		return null;	
	}
    
    
    // METHODES DE DISPLAY
    public String displayUnObjet() throws IllegalArgumentException, InvocationTargetException, IllegalAccessException, SecurityException, NoSuchMethodException {
        // recuperation de la methode qui retourne le type de l'objet
        //Class[] typesRecupTypeObjet = {};
        //Method methodeRecupTypeObjet = classeListener.getMethod("typeObjet", typesRecupTypeObjet);
        
        // recuperation du type de l'objet (ex : EOIndividu)
        //Class[] paramsRecupTypeObjet = {};
        //Class classeTypeObjet = (Class)methodeRecupTypeObjet.invoke(classeListenerInstance(), paramsRecupTypeObjet);

        // recuperation de la methode qui retourne la methode de display
        Class[] typesRecupMethodeDisplay = {};
        Method methodeRecupMethodeDisplay = classeListener.getMethod("displayObjet", typesRecupMethodeDisplay);
  
        // recuperation de la methode de display
        Class[] paramsRecupMethodeDisplay = {};
        Method methodeDisplay = (Method)methodeRecupMethodeDisplay.invoke(classeListenerInstance(), paramsRecupMethodeDisplay);
  
        // appel de la methode de display avec l'objet en parametre
        //Class[] typesDisplay = {classeTypeObjet};
        Object[] paramsDisplay = {unObjet};
        return (String)methodeDisplay.invoke(classeListenerInstance(), paramsDisplay);
    }
    
    // TEMOINS D'INTERFACE
    public boolean limiteMaximumAtteinte() {
        boolean limiteMaximumAtteinte = false;
        if (lesObjets != null && lesObjets.count() >= nbObjetsMaximum()) {
            limiteMaximumAtteinte = true;
        }
        return limiteMaximumAtteinte;
    }
    
    // SETTERS GETTERS
    public Method methodeClicObjet() 					{       return methodeClicObjet;    }
    public void setMethodeClicObjet(Method value)		{       methodeClicObjet = value;    }
    public NSArray lesObjets() 							{       return lesObjets;    }
    public void setLesObjets(NSArray value) 			{       lesObjets = value;    }
    public Class classeListener() 						{       return classeListener;    }
    public void setClasseListener(Class value) 			{       classeListener = value;    }
    public int nbObjetsMaximum() 						{       return nbObjetsMaximum;    }
    public void setNbObjetsMaximum(int value) 			{       nbObjetsMaximum = value;    }
    public boolean rechercheActive() 					{       return rechercheActive;    }
    public void setRechercheActive(Boolean value) 		{       rechercheActive = value.booleanValue();    }
    public boolean disabled() 							{       return disabled;    }
    public void setDisabled(Boolean value) 				{       disabled = value.booleanValue();    }
    public boolean lienActif() 							{       return lienActif;    }
    public void setLienActif(Boolean value) 			{       lienActif = value.booleanValue();    }
    public boolean modeModification() 					{       return modeModification;    }
    public void setModeModification(Boolean value)		{       modeModification = value.booleanValue();    }
    public NSArray lesObjetsPrecharges() 				{   	return lesObjetsPrecharges;    }
    public void setLesObjetsPrecharges(NSArray value) 	{       lesObjetsPrecharges = value;    }
    public void setLesObjetsDisponibles(NSArray value) 	{		lesObjetsDisponibles = value;	}
    public NSArray lesObjetsDisponibles()				{		
        // on enleve les objets deja dans la liste (diff�rentiel entre les precharg� et les selections
        NSMutableArray lesObjetsDisponiblesMutable = null;
        if (rechercheActive) {
            lesObjetsDisponiblesMutable = new NSMutableArray(lesObjetsDisponibles);
        }
        else {
            lesObjetsDisponiblesMutable = new NSMutableArray(lesObjetsPrecharges());
        }
        lesObjetsDisponiblesMutable.removeObjectsInArray(lesObjets);
        return lesObjetsDisponiblesMutable.immutableClone();
    }

}