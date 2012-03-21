package org.cocktail.feve.eos.modele.grhum;


import org.cocktail.fwkcktlwebapp.common.CktlSort;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.common.util.StringCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

public class EOVReferens 
	extends _EOVReferens {
	
	public EOVReferens() {
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
  
  
  // methodes rajoutees
  
  public final static String KEY_ROOT = "-1";

  public final static CktlSort SORT_LIBELLE = CktlSort.newSort(LIBELLE_KEY);
  
	public static final EOSortOrdering Z_SORT_LIBELLE = EOSortOrdering.sortOrderingWithKey(
			EOVReferens.LIBELLE_KEY, EOSortOrdering.CompareAscending);
  
  // ignorer la racine pour l'affichage des fils
  public final static EOQualifier QUAL_IGNORE_ROOT = CktlDataBus.newCondition("key <> '"+KEY_ROOT+"'");

  // ignorer les DCP archives
  public static EOQualifier QUAL_IGNORE_ARCHIVE = null;
  
  static {
  	String strQualIgnoreArchive = "";
  	for (int i=0; i<EOReferensDcp.LIST_NUMDCP_ARCHIVE.count(); i++) {
  		strQualIgnoreArchive += EOVReferens.KEY_KEY +"<>'" + EOReferensDcp.LIST_NUMDCP_ARCHIVE.objectAtIndex(i) + "'";
  		if (i < EOReferensDcp.LIST_NUMDCP_ARCHIVE.count() - 1) {
  			strQualIgnoreArchive+= " and ";
  		}
  	}
  	QUAL_IGNORE_ARCHIVE = CktlDataBus.newCondition(strQualIgnoreArchive);
  }
  
  // ignorer les objets provenant des DPC archives
  public static EOQualifier QUAL_IGNORE_FROM_DCP_ARCHIVE = null;
  
  static {
  	String strQualIgnoreFromDcpArchive = "";
  	for (int i=0; i<EOReferensDcp.LIST_NUMDCP_ARCHIVE.count(); i++) {
  		strQualIgnoreFromDcpArchive += EOVReferens.TO_REFERENS_DCP_KEY + "." + EOReferensDcp.NUMDCP_KEY +"<>'" + EOReferensDcp.LIST_NUMDCP_ARCHIVE.objectAtIndex(i) + "'";
  		if (i < EOReferensDcp.LIST_NUMDCP_ARCHIVE.count() - 1) {
  			strQualIgnoreFromDcpArchive+= " and ";
  		}
  	}
  	QUAL_IGNORE_FROM_DCP_ARCHIVE = CktlDataBus.newCondition(strQualIgnoreFromDcpArchive);
  }
  
  public final static EOQualifier QUAL_IGNORE_ROOT_AND_ARCHIVE = new EOAndQualifier(new NSArray(
  		new EOQualifier[]{QUAL_IGNORE_ROOT, QUAL_IGNORE_ARCHIVE}));
  
  public final static String PREFIX_AUTRES = "z__";

  public final static String MENTION_NOUVELLE_NOMENCLATURE = "(nouvelle nomenclature)";
  public final static String MENTION_NE_PLUS_UTILISER 		= "_NE_PLUS_UTILISER_";
  
  // niveau de la vue contenant les emplois types
  public final static int NIVEAU_DCP = 1;
  public final static int NIVEAU_FAMILLE = 2;
  public final static int NIVEAU_EMPLOI = 3;
  public final static int NIVEAU_ACTIVITE = 4;
  public final static int NIVEAU_COMPETENCE = 5;
  
   
  /**
   * Donne la racine des donnees referens
   */
  public static EOVReferens findRoot(EOEditingContext ec) {
    return EOVReferens.fetchRequiredVReferens(ec,
				CktlDataBus.newCondition(KEY_KEY +"="+KEY_PERE_KEY));
  }

  /**
   * La recherche effectue une recherche "caseInsensitiveLike". 
   * L'enregistrement est selectionne dans la vue <i>VReferens</i>. 
   * On ne recherche pas sur la racine
   * @param ec
   * @param libelle
   * @param niveau
   */
  public static NSArray findVReferensLibelleSeulLike(EOEditingContext ec, String libelle, int niveau) {
   	// construire un qualifier avec tous les mots separes par des '_'
  	NSArray motList = NSArray.componentsSeparatedByString(libelle, "_");
  	String strQual = QUAL_IGNORE_FROM_DCP_ARCHIVE + " and " + NIVEAU_KEY + "="+ niveau ;
  	for (int i=0; i<motList.count(); i++) {
  		String mot = (String) motList.objectAtIndex(i);
  		if (!StringCtrl.isEmpty(mot)) {
    		if (i==0) {
    			strQual += " and (";
    		}
    		strQual += LIBELLE_SEUL_CHAINE_CLAIRE_KEY + " caseInsensitiveLike '*"+mot+"*'";
    		if (i < motList.count() - 1) {
    			strQual += " and ";
    		} else {
    			strQual += ")";
    		}
  		}
  	}
    return fetchVReferenses(ec, CktlDataBus.newCondition(strQual), SORT_LIBELLE);
  }
  
  
  /**
   * Retourne le chemin de l'activite representee dans l'enregistrement
   * <code>record</code>. Le chemin est constitue des <code>EOVReferens</code>
   * de l'entite <code>VReferens</code>.
   */
  public static NSArray findReferensPath(EOVReferens record) {
    NSArray path = new NSArray();
    String  clePere;
    EOVReferens rec;
    
    if (record != null && !record.key().equals(KEY_ROOT)) {
      clePere = record.keyPere();
      path = path.arrayByAddingObject(record);
    } else {
      clePere= KEY_ROOT;
    }
    while (!clePere.equals(KEY_ROOT)) {
      rec = fetchVReferens(record.editingContext(), CktlDataBus.newCondition(KEY_KEY+" = '"+clePere+"'"));
      if (rec == null) break;
      clePere = rec.keyPere();
      path = path.arrayByAddingObject(rec);
    }
    // on inverse tout ca pour avoir le sens pere -> fils
    NSArray revertPath = new NSArray();
    for (int i = 0; i < path.count(); i++)
      revertPath = revertPath.arrayByAddingObject(path.objectAtIndex(path.count()-i-1));
    return revertPath;
  }
  
  /**
	 * @param record
   * @return
   */
  public static EOVReferens findVReferensForReferensEmploi(EOReferensEmplois record) {
  	return EOVReferens.fetchVReferens(record.editingContext(), 
  			CktlDataBus.newCondition(
  					NIVEAU_KEY + " = " + NIVEAU_EMPLOI + " and " + EOVReferens.TO_REFERENS_EMPLOIS_KEY + "=%@", 
  					new NSArray(record)));
  }   
  /**
	 * @param record
   * @return
   */
  public static EOVReferens findVReferensForReferensActivites(EOReferensActivites record) {
  	return EOVReferens.fetchVReferens(record.editingContext(), 
  			CktlDataBus.newCondition(
  					NIVEAU_KEY + " = " + NIVEAU_ACTIVITE + " and " + EOVReferens.TO_REFERENS_ACTIVITES_KEY + "=%@", 
  					new NSArray(record)));
  }   
  /**
	 * @param record
   * @return
   */
  public static EOVReferens findVReferensForReferensCompetences(EOReferensCompetences record) {
  	return EOVReferens.fetchVReferens(record.editingContext(), 
  			CktlDataBus.newCondition(
  					NIVEAU_KEY + " = " + NIVEAU_COMPETENCE + " and " + EOVReferens.TO_REFERENS_COMPETENCES_KEY + "=%@", 
  					new NSArray(record)));
  }
  

  // ajouts pour les nodes
  
  public static final EOQualifier QUAL_RACINE = CktlDataBus.newCondition(
  		KEY_PERE_KEY + "=\"" + KEY_ROOT + "\"");


	/**
	 * @param edc
	 * @return Les objet EOVReferens definies comme racines de l'arbre des associations.
	 */
	public static NSArray getRacines(EOEditingContext edc) {
		return fetchVReferenses(edc, QUAL_RACINE, new NSArray(new Object[] {
				Z_SORT_LIBELLE
		}));
	}

	/**
	 * @param edc
	 * @param qualifier
	 * @return Les objets {@link EOVReferens} fils de l'objet en cours.
	 * 	on enleve la racine elle meme
	 */
	public NSArray getFils() {
		NSMutableArray filsMutable = new NSMutableArray(tosVReferensFils());
		filsMutable.removeIdenticalObject(this);
		NSArray fils = CktlSort.sortedArray(filsMutable.immutableClone(), EOVReferens.LIBELLE_SEUL_KEY);
		return fils;
	}
	/**
	 * @param ec
	 * @param qual
	 * @param fetchLimit pris en compte si >0
	 * @return Les formation en vigueur dependant du qualifier specifie.
	 */
	public static NSArray rechercher(EOEditingContext ec, EOQualifier qual, int fetchLimit) {
		NSMutableArray array = new NSMutableArray();
		NSTimestamp now = DateCtrl.now();
		array.add(QUAL_IGNORE_FROM_DCP_ARCHIVE);
		if (qual != null) {
			array.addObject(qual);
		}
		EOFetchSpecification spec = new EOFetchSpecification(
				EOVReferens.ENTITY_NAME, 
				new EOAndQualifier(array),
				new NSArray(Z_SORT_LIBELLE));
		if (fetchLimit > 0) {
			spec.setFetchLimit(fetchLimit);
		}
		spec.setUsesDistinct(true);
		NSArray res = ec.objectsWithFetchSpecification(spec);
		return res;
	}
}
