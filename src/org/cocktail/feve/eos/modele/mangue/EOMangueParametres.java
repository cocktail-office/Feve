package org.cocktail.feve.eos.modele.mangue;

import org.cocktail.feve.eos.modele.A_FeveCktlRecord;
import org.cocktail.fwkcktlwebapp.common.CktlLog;
import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.NSArrayCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.ycrifwk.utils.UtilDb;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXQ;

public class EOMangueParametres extends _EOMangueParametres {

		public final static String KEY_FEV_EVALUATION_SAISIE_D_DEBUT 		= "FEV_EVALUATION_SAISIE_D_DEBUT";
		public final static String KEY_FEV_EVALUATION_SAISIE_D_FIN 			= "FEV_EVALUATION_SAISIE_D_FIN";	
		public final static String KEY_FEV_FICHE_LOLF_SAISIE_D_DEBUT 		= "FEV_FICHE_LOLF_SAISIE_D_DEBUT";	
		public final static String KEY_FEV_FICHE_LOLF_SAISIE_D_FIN 			= "FEV_FICHE_LOLF_SAISIE_D_FIN";
		
		public final static String KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE = "FEV_ANNU_COMPOSANTE_CREATION_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE = "FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE = "FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE = "FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE = "FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE = "FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE = "FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE = "FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_CREATION_POSTE = "FEV_ANNU_SERVICE_CREATION_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE = "FEV_ANNU_SERVICE_SUPPRESSION_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE = "FEV_ANNU_SERVICE_MODIFICATION_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE = "FEV_ANNU_SERVICE_CONSULTATION_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE = "FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE = "FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE = "FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE = "FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF = "FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF";
		public final static String KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF = "FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF";
		public final static String KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF = "FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF";
		public final static String KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF = "FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF";
		public final static String KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION = "FEV_HIE_N_PLUS_1_CREATION_EVALUATION";
		public final static String KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION = "FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION";
		public final static String KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION = "FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION";
		public final static String KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION = "FEV_HIE_N_PLUS_P_CREATION_EVALUATION";
		public final static String KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION = "FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION";
		public final static String KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION = "FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION";
		public final static String KEY_FEV_SELF_CONSULTATION_FICHE_DE_POSTE	=	"FEV_SELF_CONSULTATION_FICHE_DE_POSTE";
		public final static String KEY_FEV_SELF_CONSULTATION_FICHE_LOLF =	"FEV_SELF_CONSULTATION_FICHE_LOLF";
		public final static String KEY_FEV_SELF_CONSULTATION_EVALUATION	=	"FEV_SELF_CONSULTATION_EVALUATION";
		public final static String KEY_FEV_SELF_CONSULTATION_POSTE =	"FEV_SELF_CONSULTATION_POSTE";
		
		// gestion des activites autres dans la fiche de poste O/N
		public final static String KEY_FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES =	"FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES";
		// gestion des competences autres dans la fiche de poste O/N
		public final static String KEY_FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES =	"FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES";
		// edition de la fiche de poste : disposition des 2 blocs activités compétences REFERENS
		public final static String KEY_FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL =	"FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL";
		// motif du nom par défaut d'un nouveau poste
		public final static String KEY_FEV_LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT =	"LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT";
		
		// Durée minimum cumulées des affectations d''un agent pour pouvoir être évalué sur une période (en jour)
		public final static String KEY_FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION = "FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION";
		
		// documentations (0 à n documentations)
		public final static String PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION 	=  "FEV_DOCUMENTATION_GUIDE_UTILISATION_";
		public final static String PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN 		=  "FEV_DOCUMENTATION_GUIDE_ENTRETIEN_";
		
		
		/** la liste des cles retournant une valeur booleenne */
		public final static NSArray<String> ARRAY_KEY_BOOLEAN = new NSArray<String>(new String[] {
		  		KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_SERVICE_CREATION_POSTE,
		  		KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE,
		  		KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE,
		  		KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE,
		  		KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF,
		  		KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF,
		  		KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF,
		  		KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION,
		  		KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION,
		  		KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION,
		  		KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION,
		  		KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION,
		  		KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION,
		  		KEY_FEV_SELF_CONSULTATION_FICHE_DE_POSTE,
		  		KEY_FEV_SELF_CONSULTATION_FICHE_LOLF,
		  		KEY_FEV_SELF_CONSULTATION_EVALUATION,
		  		KEY_FEV_SELF_CONSULTATION_POSTE,
		  		KEY_FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES,
		  		KEY_FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES,
		  		KEY_FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL
			});
		
		/** la liste des cles  retournant une valeur booleenne concernant les composantes*/
		public final static NSArray<String> ARRAY_KEY_BOOLEAN_COMPOSANTE = new NSArray<String>(new String[] {
		  		KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE,
		  		KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF,
		  		KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF
			});		
		
		/** la liste des cles  retournant une valeur booleenne concernant les services */
		public final static NSArray<String> ARRAY_KEY_BOOLEAN_SERVICE = new NSArray<String>(new String[] {
	  		KEY_FEV_ANNU_SERVICE_CREATION_POSTE,
	  		KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE,
	  		KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE,
	  		KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE,
	  		KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE,
	  		KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE,
	  		KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE,
	  		KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE,
	  		KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF,
	  		KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF
			});
		
		/** la liste des cles  retournant une valeur booleenne concernant les N+1 de la hierarchie */
		public final static NSArray<String> ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_1 = new NSArray<String>(new String[] {
	  		KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION,
	  		KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION,
	  		KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION
			});
		
		/** la liste des cles  retournant une valeur booleenne concernant les N+p de la hierarchie */
		public final static NSArray<String> ARRAY_KEY_BOOLEAN_HIERARCHIE_N_PLUS_P = new NSArray<String>(new String[] {
	  		KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION,
	  		KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION,
	  		KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION
			});		
		
		/** la liste des cles  retournant une valeur booleenne concernant les fiches personnelles */
		public final static NSArray<String> ARRAY_KEY_BOOLEAN_SELF = new NSArray<String>(new String[] {
				KEY_FEV_SELF_CONSULTATION_FICHE_DE_POSTE,
	  		KEY_FEV_SELF_CONSULTATION_FICHE_LOLF,
	  		KEY_FEV_SELF_CONSULTATION_EVALUATION,
	  		KEY_FEV_SELF_CONSULTATION_POSTE
			});		
		

		/**
		 * les cles du dictionnaire produit par la methode {@link #getDicoTypeAccesCibleForParamKey(String)}
		 */
		public final static String DICO_KEY_TYPE_DROIT_ACCES = "DICO_KEY_TYPE_DROIT_ACCES";
		public final static String DICO_KEY_TYPE_DROIT_CIBLE = "DICO_KEY_TYPE_DROIT_CIBLE";
		
    /**
     * Retourne le couple {@link EOTypeDroitAcces} et {@link EOTypeDroitCible} associe
     * a une cle
     * @param ec
     * @param paramKey
     * @return un {@link NSDictionary} avec 2 valeur,
     */
    public static NSDictionary<String, A_FeveCktlRecord> getDicoTypeAccesCibleForParamKey(EOEditingContext ec, String paramKey) {
    	NSMutableDictionary<String, A_FeveCktlRecord> dico = new NSMutableDictionary<String, A_FeveCktlRecord>();
    	if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_CREATION_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_CREATION_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesCreation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCiblePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_SUPPRESSION_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesSuppression(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCiblePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_MODIFICATION_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesModification(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCiblePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_CONSULTATION_POSTE) || paramKey.equals(KEY_FEV_SELF_CONSULTATION_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesVisualisation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCiblePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesCreation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheDePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesSuppression(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheDePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE) || paramKey.equals(KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesModification(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheDePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE) || paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE) || paramKey.equals(KEY_FEV_SELF_CONSULTATION_FICHE_DE_POSTE)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesVisualisation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheDePoste(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF) || paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesModification(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheLolf(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF) || paramKey.equals(KEY_FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF) || paramKey.equals(KEY_FEV_SELF_CONSULTATION_FICHE_LOLF)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesVisualisation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleFicheLolf(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_HIE_N_PLUS_1_CREATION_EVALUATION) || paramKey.equals(KEY_FEV_HIE_N_PLUS_P_CREATION_EVALUATION)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesCreation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleEvaluation(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION) || paramKey.equals(KEY_FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesModification(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleEvaluation(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} else if (paramKey.equals(KEY_FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION) || paramKey.equals(KEY_FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION) || paramKey.equals(KEY_FEV_SELF_CONSULTATION_EVALUATION)) {
    		dico.setObjectForKey(EOTypeDroitAcces.typeDroitAccesVisualisation(ec), DICO_KEY_TYPE_DROIT_ACCES);
    		dico.setObjectForKey(EOTypeDroitCible.eoTypeDroitCibleEvaluation(ec), 			DICO_KEY_TYPE_DROIT_CIBLE);
    	} 
    	
    	if (dico.size() == 0) {
    		CktlLog.log("erreur:"+paramKey);
    	}
    	
    	return dico.immutableClone();
    }
    
    
    public EOMangueParametres() {
        super();
    }

    public void validateForInsert() throws NSValidation.ValidationException {
        this.validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForInsert();
    }

    public void validateForUpdate() throws NSValidation.ValidationException {
        this.validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForUpdate();
    }

    public void validateForDelete() throws NSValidation.ValidationException {
        super.validateForDelete();
    }

    /**
     * Apparemment cette methode n'est pas appelee.
     * @see com.webobjects.eocontrol.EOValidation#validateForUpdate()
     */    
    public void validateForSave() throws NSValidation.ValidationException {
        validateObjectMetier();
        validateBeforeTransactionSave();
        super.validateForSave();
        
    }

    /**
     * Peut etre appele a partir des factories.
     * @throws NSValidation.ValidationException
     */
    public void validateObjectMetier() throws NSValidation.ValidationException {
      

    }
    
    /**
     * A appeler par les validateforsave, forinsert, forupdate.
     *
     */
    private final void validateBeforeTransactionSave() throws NSValidation.ValidationException {
           
    }


    // classe de gestion par l'interface HTML / AJAX
    
  	/**
  	 * Classe maison permettant la transalation d'un objet {@link EOMangueParametres} 
  	 * de documentation en objet gérable simplement par l'interface.
  	 * Le format suivant est utilisé :
  	 * [position]'|'[libelle du document]'|'[url fichier]
  	 * 
  	 * @author ctarade
  	 */
  	public class ObjetInterfaceDocumentation {
  		
  		private final static String SEPARATEUR = "|";
  		
  		private int position;
  		private String libelle;
  		private String url;
  		
  		private boolean isEnModification;
  		
  		/** le paramKey prefix */
  		private String baseParamKey;
  		
  		public ObjetInterfaceDocumentation() {
  			super();
  			toItem();
  			isEnModification = false;
  		}
  		
  		/**
  		 * 
  		 */
  		private void toItem() {
  			if (!StringCtrl.isEmpty(paramValue())) {
    			NSArray<String> tokens = NSArray.componentsSeparatedByString(paramValue(), SEPARATEUR);
    			setLibelle(tokens.objectAtIndex(0));
    			setUrl(tokens.objectAtIndex(1));
    			String strPosition = paramKey();
    			strPosition = strPosition.substring(strPosition.lastIndexOf("_")+1);
    			if (strPosition.startsWith("0")) {
    				strPosition = strPosition.substring(1);
    			}
    			setPosition(Integer.parseInt(strPosition));
    			setBaseParamKey(paramKey().substring(0, paramKey().lastIndexOf("_")+1));
  			}
  		}

			public final String getLibelle() {
				return libelle;
			}

			public final void setLibelle(String libelle) {
				this.libelle = libelle;
			}

			public final String getUrl() {
				return url;
			}

			public final void setUrl(String url) {
				this.url = url;
			}

			public final int getPosition() {
				return position;
			}

			public final void setPosition(int position) {
				this.position = position;
			}
  		
			public void majObjetMetier() {
				if (getPosition() < 10) {
					setParamKey(getBaseParamKey() + "0" + getPosition());
				} else {
					setParamKey(getBaseParamKey() + getPosition());
				}
				setParamValue(getLibelle() + SEPARATEUR + getUrl());
			}

			public final boolean isEnModification() {
				return isEnModification;
			}

			public final void setEnModification(boolean isEnModification) {
				this.isEnModification = isEnModification;
			}
			
			public WOActionResults modifier() {
				setEnModification(true);
				return null;
			}
			
			/**
			 * Validation du parametre
			 * @return
			 * @throws NSValidation.ValidationException
			 */
			private void valider() 
				throws NSValidation.ValidationException {
				// il faut un libelle
				if (StringCtrl.isEmpty(getLibelle())) {
					throw new NSValidation.ValidationException(
							"Le libellé est obligatoire");
				}
				// et une URL
				if (StringCtrl.isEmpty(getUrl())) {
					throw new NSValidation.ValidationException(
							"L'URL est obligatoire");
				}
				// tester que la taille totale ne dépasse pas la valeur maximum autorisée
				int depassement = depassement(PARAM_VALUE_KEY, paramValue());
				if (depassement > 0) {
					throw new NSValidation.ValidationException(
							"La taille totale du libellé et de l'URL ne doit pas dépasser " + maxLengthForAttribute(PARAM_VALUE_KEY) +
							" caractères. Votre saisie dépasse de " + depassement + " caractères. Veuillez adapter la saisie " +
							"pour continuer");
				}
			}

			
			/**
			 * Enregistrement dans la base de données
			 */
			public void sauvegarder() {
				clearError();
				try {
					majObjetMetier();
					valider();
					UtilDb.save(editingContext(), "");
					setEnModification(false);
				} catch (NSValidation.ValidationException va) {
					setErrorMessage(va.getMessage());
				} catch (Throwable e) {
					e.printStackTrace();
				} 
			}
			
			public WOActionResults annuler() {
				clearError();
				setEnModification(false);
				toItem();
				editingContext().revert();
				return null;
			}

			public final String getBaseParamKey() {
				return baseParamKey;
			}

			public final void setBaseParamKey(String baseParamKey) {
				this.baseParamKey = baseParamKey;
			}
			
			// message d'erreur
			private String errorMessage;
			
			private void clearError() {
				setErrorMessage(null);
			}

			public final String getErrorMessage() {
				return errorMessage;
			}

			public final void setErrorMessage(String errorMessage) {
				this.errorMessage = errorMessage;
			}
			
			public boolean isError() {
				boolean isError = false;
				
				if (!StringCtrl.isEmpty(getErrorMessage())) {
					isError = true;
				}
				
				return isError;
			}
  	}
    
    private ObjetInterfaceDocumentation objetInterfaceDocumentation;
    
    public ObjetInterfaceDocumentation getObjetInterfaceDocumentation() {
    	if (objetInterfaceDocumentation == null) {
    		objetInterfaceDocumentation = new ObjetInterfaceDocumentation();
    	}
    	return objetInterfaceDocumentation;
    }
    
    /**
     * Création d'un paramètre 
     *
     * @param ec
     * @param paramKey
     * @return
     * @throws Exception 
     */
    public final static EOMangueParametres nouveauParametre(
    		EOEditingContext editingContext, String paramKey) throws Exception {
    	
    	// on autorise uniquement les documentations pour l'instant ...
    	if (!paramKey.startsWith(PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN) &&
    			!paramKey.startsWith(PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_UTILISATION)) {
    		throw new Exception("Impossible de créer des paramètres autres que " +
    				PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN + " et " + PREFIX_KEY_FEV_DOCUMENTATION_GUIDE_ENTRETIEN);
    	}
 
    	// determiner la premiere position libre
    	NSArray<EOMangueParametres> parametres = getParametres(editingContext, paramKey, null);
    	int position = 0;
    	int i = 0;
    	while (position == 0 && i<parametres.count()-1) {
    		int positionAttendue = i+1;
    		EOMangueParametres eoMangueParametres = parametres.objectAtIndex(i);
    		if (eoMangueParametres.getObjetInterfaceDocumentation().getPosition() != positionAttendue) {
    			position = positionAttendue;
    		}
    		i++;
    	}

    	// non trouvé, on prend le numéro libre à suivre
    	if (position == 0) {
    		position = parametres.count()+1;
    	}
    	String libelle = "nom de la documentation";
    	String url = "http://chemin.de/la/documentation.pdf";
    	
    	EOMangueParametres eoMangueParametres = createMangueParametres(editingContext, paramKey);
    	eoMangueParametres.getObjetInterfaceDocumentation().setPosition(position);
    	eoMangueParametres.getObjetInterfaceDocumentation().setLibelle(libelle);
    	eoMangueParametres.getObjetInterfaceDocumentation().setUrl(url);
    	eoMangueParametres.getObjetInterfaceDocumentation().setBaseParamKey(paramKey);
    	eoMangueParametres.getObjetInterfaceDocumentation().majObjetMetier();    	
    	eoMangueParametres.getObjetInterfaceDocumentation().setEnModification(true);
    	
    	return eoMangueParametres;
    }
    
    /**
     * Suppression d'un enregistrement (gestion du décalage des positions)
     * 
     * @param eoMangueParametres
     */
    public final static void supprimerParametre(
    		EOMangueParametres eoMangueParametres) {
    	
    	// trouver la position actuelle de l'objet
    	int position = eoMangueParametres.getObjetInterfaceDocumentation().getPosition();
    	String baseParamKey = eoMangueParametres.getObjetInterfaceDocumentation().getBaseParamKey();
    	
    	
    	EOEditingContext ec = eoMangueParametres.editingContext();
    	
    	// suppression 
    	eoMangueParametres.editingContext().deleteObject(eoMangueParametres);
    	
    	// sauvegarde obligatoire sinon pb de contrainte unique sur paramKey
    	try {
				UtilDb.save(ec, "");
			} catch (Throwable e) {
				ec.revert();
				e.printStackTrace();
				return;
			}
    	
    	// décalage des objets suivants
    	NSArray<EOMangueParametres> parametres = getParametres(ec, baseParamKey, null);
    	for (int i=0; i<parametres.count(); i++) {
    		EOMangueParametres parametre = parametres.objectAtIndex(i);
    		int positionCourante = parametre.getObjetInterfaceDocumentation().getPosition();
    		if (positionCourante > position) {
    			parametre.getObjetInterfaceDocumentation().setPosition(positionCourante-1);
    			parametre.getObjetInterfaceDocumentation().majObjetMetier();
    		}
    	}
    	
    }
    
    /**
     * Liste de paramètres répondant à une clé. La recheche se fait aussi 
     * dans le {@link EOEditingContext} si jamais l'objet n'est pas encore
     * en base de données.
     * 
     * @param editingContext
     * @param paramKeyPrefix
     * @return
     */
    public final static NSMutableArray<EOMangueParametres> getParametres(
    		EOEditingContext editingContext, String paramKeyPrefix, String sortKeys) {
    	NSMutableArray<EOMangueParametres> array = new NSMutableArray<EOMangueParametres>();
    	
    	CktlSort sort = null;
    	if (!StringCtrl.isEmpty(sortKeys)) {
    		sort = CktlSort.newSort(sortKeys);
    	}
    	// les objets de la base
    	array.addObjectsFromArray(
    			EOMangueParametres.fetchMangueParametreses(
    					editingContext, 
    					ERXQ.startsWith(
    							EOMangueParametres.PARAM_KEY_KEY, 
    							paramKeyPrefix),
    					sort));

    	// suppression d'éventuels doublons 
    	NSArrayCtrl.removeDuplicatesInNSArray(array);
    	
    	return array;
    }
    
    /**
     * Mettre à jour la position des objets metiers en rapport avec la position
     * dans le tableau. Il faut faire un traitement spécial car il y a une 
     * contrainte unique sur paramKey qui fait qu'il faut faire un update séquentiel
     */
    public final static void majPosition(
    		EOEditingContext ec,
    		NSArray<EOMangueParametres> eoMangueParametresArray) {
    	int position1 = -1;
    	int position2 = -1;
     	EOMangueParametres eoMangueParametres1 = null;
     	EOMangueParametres eoMangueParametres2 = null;
    	for (int i=0; i<eoMangueParametresArray.count(); i++) {
    		EOMangueParametres eoMangueParametres = eoMangueParametresArray.objectAtIndex(i);
    		int nouvellePosition = eoMangueParametresArray.indexOfIdenticalObject(eoMangueParametres)+1;
    		if (eoMangueParametres.getObjetInterfaceDocumentation().getPosition() != nouvellePosition) {
    			if (eoMangueParametres1 == null) {
    				// le premier
    				eoMangueParametres1 = eoMangueParametres;
    				position1 = eoMangueParametres1.getObjetInterfaceDocumentation().getPosition();
    			} else {
    				// le second
    				eoMangueParametres2 = eoMangueParametres;
    				position2 = eoMangueParametres2.getObjetInterfaceDocumentation().getPosition();
    			}
    		}
    	}
    	// permutation en passant par une sauvegarde intermédiare
    	if (eoMangueParametres1 != null && eoMangueParametres2 != null) {
    		eoMangueParametres1.getObjetInterfaceDocumentation().setPosition(0);
    		eoMangueParametres1.getObjetInterfaceDocumentation().sauvegarder();
    		eoMangueParametres2.getObjetInterfaceDocumentation().setPosition(position1);
    		eoMangueParametres2.getObjetInterfaceDocumentation().sauvegarder();
    		eoMangueParametres1.getObjetInterfaceDocumentation().setPosition(position2);
    		eoMangueParametres1.getObjetInterfaceDocumentation().sauvegarder();
    	}
    }
}
