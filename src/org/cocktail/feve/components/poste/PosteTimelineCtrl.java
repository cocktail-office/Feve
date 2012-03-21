package org.cocktail.feve.components.poste;
import java.util.GregorianCalendar;

import org.cocktail.feve.app.Session;
import org.cocktail.feve.components.common.A_ComponentControler;
import org.cocktail.feve.eos.modele.grhum.EOReferensEmplois;
import org.cocktail.feve.eos.modele.mangue.A_Fiche;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

/**
 * Controleur de la page timeline
 * 
 * @author ctarade
 */
public class PosteTimelineCtrl extends A_ComponentControler {

  /* HTML color values and names */
  private final static String HTML_COLORS [] = {
//      "800000", "Maroon", 
//      "8B0000", "DarkRed",
//      "B22222", "FireBrick",
//      "FF0000", "Red",
      "FA8072", "Salmon",
      "FF6347", "Tomato",
      "FF7F50", "Coral",
      "FF4500", "OrangeRed",
      //"D2691E", "Chocolate",
      "F4A460", "SandyBrown",
      "FF8C00", "DarkOrange",
      "FFA500", "Orange",
      //"B8860B", "DarkGoldenrod",
      "DAA520", "Goldenrod",
      "FFD700", "Gold",
      //"808000", "Olive",
      "FFFF00", "Yellow",
      "9ACD32", "YellowGreen",
      "ADFF2F", "GreenYellow",
      "7FFF00", "Chartreuse",
      "7CFC00", "LawnGreen",
      //"008000", "Green",
      "00FF00", "Lime",
      "32CD32", "LimeGreen",
      "00FF7F", "SpringGreen",
      "00FA9A", "MediumSpringGreen",
      "40E0D0", "Turquoise",
      "20B2AA", "LightSeaGreen",
      "48D1CC", "MediumTurquoise",
      //"008080", "Teal",
      //"008B8B", "DarkCyan",
      "00FFFF", "Aqua",
      "00FFFF", "Cyan",
      "00CED1", "DarkTurquoise",
      "00BFFF", "DeepSkyBlue",
      //"1E90FF", "DodgerBlue",
      //"4169E1", "RoyalBlue",
      //"000080", "Navy",
      //"00008B", "DarkBlue",
      //"0000CD", "MediumBlue",
      //"0000FF", "Blue",
      //"8A2BE2", "BlueViolet",
      //"9932CC", "DarkOrchid",
      //"9400D3", "DarkViolet",
      //"800080", "Purple",
      //"8B008B", "DarkMagenta",
      "FF00FF", "Fuchsia",
      //"FF00FF", "Magenta",
      //"C71585", "MediumVioletRed",
      "FF1493", "DeepPink",
      "FF69B4", "HotPink",
      //"DC143C", "Crimson",
      //"A52A2A", "Brown",
      "CD5C5C", "IndianRed",
      "BC8F8F", "RosyBrown",
      "F08080", "LightCoral",
      "FFFAFA", "Snow",
      "FFE4E1", "MistyRose",
      "E9967A", "DarkSalmon",
      "FFA07A", "LightSalmon",
      "A0522D", "Sienna",
      "FFF5EE", "SeaShell",
      "8B4513", "SaddleBrown",
      "FFDAB9", "Peachpuff",
      "CD853F", "Peru",
      "FAF0E6", "Linen",
      "FFE4C4", "Bisque",
      "DEB887", "Burlywood",
      "D2B48C", "Tan",
      "FAEBD7", "AntiqueWhite",
      "FFDEAD", "NavajoWhite",
      "FFEBCD", "BlanchedAlmond",
      "FFEFD5", "PapayaWhip",
      "FFE4B5", "Moccasin",
      "F5DEB3", "Wheat",
      "FDF5E6", "Oldlace",
      "FFFAF0", "FloralWhite",
      "FFF8DC", "Cornsilk",
      "F0E68C", "Khaki",
      "FFFACD", "LemonChiffon",
      "EEE8AA", "PaleGoldenrod",
      "BDB76B", "DarkKhaki",
      "F5F5DC", "Beige",
      "FAFAD2", "LightGoldenrodYellow",
      "FFFFE0", "LightYellow",
      "FFFFF0", "Ivory",
      "6B8E23", "OliveDrab",
      "556B2F", "DarkOliveGreen",
      "8FBC8F", "DarkSeaGreen",
      "006400", "DarkGreen",
      "228B22", "ForestGreen",
      "90EE90", "LightGreen",
      "98FB98", "PaleGreen",
      "F0FFF0", "Honeydew",
      "2E8B57", "SeaGreen",
      "3CB371", "MediumSeaGreen",
      "F5FFFA", "Mintcream",
      "66CDAA", "MediumAquamarine",
      "7FFFD4", "Aquamarine",
      "2F4F4F", "DarkSlateGray",
      "AFEEEE", "PaleTurquoise",
      "E0FFFF", "LightCyan",
      "F0FFFF", "Azure",
      "5F9EA0", "CadetBlue",
      "B0E0E6", "PowderBlue",
      "ADD8E6", "LightBlue",
      "87CEEB", "SkyBlue",
      "87CEFA", "LightskyBlue",
      "4682B4", "SteelBlue",
      "F0F8FF", "AliceBlue",
      "708090", "SlateGray",
      "778899", "LightSlateGray",
      "B0C4DE", "LightsteelBlue",
      "6495ED", "CornflowerBlue",
      "E6E6FA", "Lavender",
      "F8F8FF", "GhostWhite",
      "191970", "MidnightBlue",
      "6A5ACD", "SlateBlue",
      "483D8B", "DarkSlateBlue",
      "7B68EE", "MediumSlateBlue",
      "9370DB", "MediumPurple",
      "4B0082", "Indigo",
      "BA55D3", "MediumOrchid",
      "DDA0DD", "Plum",
      "EE82EE", "Violet",
      "D8BFD8", "Thistle",
      "DA70D6", "Orchid",
      "FFF0F5", "LavenderBlush",
      "DB7093", "PaleVioletRed",
      "FFC0CB", "Pink",
      "FFB6C1", "LightPink",
      "000000", "Black",
      "696969", "DimGray",
      "808080", "Gray",
      "A9A9A9", "DarkGray",
      "C0C0C0", "Silver",
      "D3D3D3", "LightGrey",
      "DCDCDC", "Gainsboro",
      "F5F5F5", "WhiteSmoke",
      "FFFFFF", "White"
  };
	
	/** la liste des postes a afficher */
	public NSArray posteList;
	public EOPoste posteItem;
	public int posteIndex;
	
	/** une occupation d'un poste */
	public CktlRecord recordItem;
	private CktlRecord prevRecordItem;
	public int recordIndex;
	
	/** les dimensions du graphique */
	public int xEnd;
	
	// cache
	private NSTimestamp dDebPeriode;
	private NSTimestamp dFinPeriode;
	
	// la liste des types d'objets a afficher 
	private final static Integer TYPE_AFFECTATION_DETAIL 	= new Integer(0);
	private final static Integer TYPE_FICHE_DE_POSTE 			= new Integer(1);
	private final static Integer TYPE_FICHE_LOLF 					= new Integer(2);

	// la liste des checkbox
	public NSArray typeList = new NSArray(new Integer[]{
			TYPE_AFFECTATION_DETAIL, TYPE_FICHE_DE_POSTE, TYPE_FICHE_LOLF });
	public NSArray typeSelecteds = new NSArray(TYPE_AFFECTATION_DETAIL);
	public Integer typeItem;
	
	
	/**
	 * @deprecated Utiliser le contructeur avec aPosteList
	 * @param session
	 */
	public PosteTimelineCtrl(Session session) {
		super(session);
	}

	public PosteTimelineCtrl(Session aSession, NSArray aPosteList) {
		super(aSession);
		posteList = aPosteList;
		initCtrl();
	}
	
	/**
	 * Initialiser les tailles du graphique
	 */
	private void initCtrl() {
		// l'abcisse de fin est deduite entre la difference entre les bornes
		xEnd = getPixelsBeetwen(getDDebPeriode(), getDFinPeriode());
	}
	
	
	// getters
	
	/**
	 * Debut de periode
	 */
	public NSTimestamp getDDebPeriode() {
		if (dDebPeriode == null) {
			dDebPeriode = dateToDebutAnneeUniv(DateCtrl.now());
		}
		return dDebPeriode;
	}

	/**
	 * Fin de periode (1 an apres le debut)
	 */
	public NSTimestamp getDFinPeriode() {
		if (dFinPeriode == null) {
			dFinPeriode = getDDebPeriode().timestampByAddingGregorianUnits(1,0,-1,0,0,0);
		}
		return dFinPeriode;
	}
	
	/**
	 * La liste des <code>EOAffectationDetail</code> associees a <code>posteItem</code>
	 * etant sur la periode
	 * @return
	 */
	public NSArray getRecordListOnPeriode() {
		NSArray records = (typeItem.intValue() == TYPE_AFFECTATION_DETAIL.intValue() ? posteItem.tosAffectationDetail() :
			(typeItem.intValue() == TYPE_FICHE_DE_POSTE.intValue() ? posteItem.tosFicheDePoste() : 
				typeItem.intValue() == TYPE_FICHE_LOLF.intValue() ? posteItem.tosFicheLolf() : new NSArray())); 
		NSArray recordListOnPeriode = new NSArray();
		for (int i=0; i<records.count(); i++) {
			CktlRecord rec = (CktlRecord) records.objectAtIndex(i);
			boolean shouldAdd = false;	
			if (getRecDFin(rec) == null) {
				if (DateCtrl.isBefore(getRecDDeb(rec), getDFinPeriode())) {
					shouldAdd = true;
				}
			} else {
				if ((DateCtrl.isAfter(getRecDDeb(rec), getDDebPeriode()) && DateCtrl.isBefore(getRecDDeb(rec), getDFinPeriode())) ||
						(DateCtrl.isAfter(getRecDFin(rec), getDDebPeriode()) && DateCtrl.isBefore(getRecDFin(rec), getDFinPeriode())) || 
						(DateCtrl.isBeforeEq(getRecDDeb(rec), getDDebPeriode()) && DateCtrl.isAfterEq(getRecDFin(rec), getDFinPeriode()))) {
					shouldAdd = true;
				}
			}
			if (shouldAdd) {
				recordListOnPeriode = recordListOnPeriode.arrayByAddingObject(rec);
			}
		}
		return recordListOnPeriode;
	}
		
	/**
	 * La dimension et la postion d'une occupation de poste
	 * @return
	 */
	public String getRecordItemStyle() {
		String style = "";
		// les affectations sont retournees par date de debut croissante
		// pour la premiere, on fait une marge par rapport au debut du graphique
		// pour les autres, on fait une marge par rapport a l'affectation N-1
		int marginLeft = 0;
		if (recordIndex == 0) {
			// le debut est le differentiel en semaine avec getMinPosteDDebut()
			marginLeft = getPixelsBeetwen(getDDebPeriode(), retailledDDeb(getRecDDeb(recordItem)));
		} else {
			// date de fin de la N-1
			if (getRecDFin(prevRecordItem) != null) {
				marginLeft = getPixelsBeetwen(retailledDDeb(getRecDFin(prevRecordItem)), retailledDDeb(getRecDDeb(recordItem)));
			}
		}
		
		// l'abcisse est la duree ou bien la date max si pas de fin (maximisee a la taille du graphique)
		int width = 0;
		if (getRecDFin(recordItem) != null) {
			width = getPixelsBeetwen(retailledDDeb(getRecDDeb(recordItem)), retailledDFin(getRecDFin(recordItem)));
		} else {
			width = xEnd - getPixelsBeetwen(getDDebPeriode(), retailledDDeb(getRecDDeb(recordItem)));
		}
		// on fait width-1 pour permettre un affichage d'un trait de separation
		style = "margin-left: " + marginLeft + "px; width: " + (width-1) + "px; background-color: " + getColor() + "; border-right: 1px dotted #000;";
		return style;
	}

	public String getOnMouseOverAffectationDetailItem() {
		return "";//"this.style.border-top='1px solid red';this.style.border-bottom='1px solid red';";
	}
	
	public String getOnMouseOutAffectationDetailItem() {
		return "";//"this.style.border-top='0px';this.style.border-bottom='0px';";
	}
	
	/**
	 * Infobulle sur le passage d'une affectation
	 * @return
	 */
	public String getRecordItemTitle() {
		String title = null;
		if (recordItem instanceof EOAffectationDetail) {
			title = ((EOAffectationDetail) recordItem).fullDisplay(false);
		} else if (recordItem instanceof A_Fiche) {
			title = ((A_Fiche) recordItem).display();
		}
		return title;
	}
	
	/**
	 * Affichage du contenu de la div represetant <code>recordItem</code>
	 * @return
	 */
	public String getRecordItemDisplay() {
		String disp = null;
		if (recordItem instanceof EOAffectationDetail) {
			disp = ((EOAffectationDetail) recordItem).toAffectation().toIndividu().nomPrenom();
		} else if (recordItem instanceof EOFicheDePoste) {
			EOReferensEmplois emploi =  ((EOFicheDePoste) recordItem).toReferensEmplois();
			if (emploi != null) {
				disp = emploi.display();
			} else {
				disp = "<emploi type non defini>";
			}
		} else if (recordItem instanceof EOFicheLolf) {
			disp = ((EOFicheLolf) recordItem).toPoste().posLibelle();
		}
		return disp;
	}
	
	/**
	 * La hauteur de la ligne d'un poste depend du nombre de type
	 * d'objets affiches
	 * @return
	 */
	public String getDivLigPosteStyle() {
		int base = 20;
		int coef = 1;
		if (typeSelecteds.count() == 2) {
			coef = 2;
		} else if (typeSelecteds.count() == 3) {
			coef = 3;
		}
		return "height: " + (base*coef) + "px;";
	}
	
	// setter
	
	/**
	 * Memorise l'affectation N-1
	 */
	public void setAffectationDetailItem(CktlRecord value) {
		prevRecordItem = recordItem;
		recordItem = value;
	}
	
	
	// navigation
	
	/**
	 * Reculer d'1 an 
	 */
	public WOComponent toPrevPeriode() {
		dDebPeriode = getDDebPeriode().timestampByAddingGregorianUnits(-1, 0, 0, 0, 0, 0);
		dFinPeriode = null;
		return null;
	}
	
	/**
	 * Avancer d'1 an 
	 */
	public WOComponent toNextPeriode() {
		dDebPeriode = getDDebPeriode().timestampByAddingGregorianUnits(1, 0, 0, 0, 0, 0);
		dFinPeriode = null;
		return null;
	}
	
	
	// methodes internes
	
	/**
	 * La date de debut associee a l'object <code>aRecord</code>
	 */
	private NSTimestamp getRecDDeb(CktlRecord aRecord) {
		NSTimestamp deb = null;
		if (aRecord instanceof EOAffectationDetail) {
			deb = ((EOAffectationDetail) aRecord).dDebut();
		} else if (aRecord instanceof EOFicheDePoste) {
			deb = ((EOFicheDePoste) aRecord).dDebut();
		} else if (aRecord instanceof EOFicheLolf) {
			deb = ((EOFicheLolf) aRecord).dDebut();
		}
		return deb;
	}
	
	/**
	 * La date de fin associee a l'object <code>aRecord</code>
	 */
	private NSTimestamp getRecDFin(CktlRecord aRecord) {
		NSTimestamp fin = null;
		if (aRecord instanceof EOAffectationDetail) {
			fin = ((EOAffectationDetail) aRecord).dFin();
		} else if (aRecord instanceof EOFicheDePoste) {
			fin = ((EOFicheDePoste) aRecord).dFin();
		} else if (aRecord instanceof EOFicheLolf) {
			fin = ((EOFicheLolf) aRecord).dFin();
		}
		return fin;
	}
	
	/**
	 * La cle du dictionaire de couleur associe a l'item <code>recordItem</code>
	 */
	private String getColorKey() {
		String key = null;
		if (recordItem instanceof EOAffectationDetail) {
			key = "IND_"+Integer.toString(((EOAffectationDetail) recordItem).toAffectation().toIndividu().persId().intValue());
		} else if (recordItem instanceof A_Fiche) {
			key = "POS_"+((A_Fiche) recordItem).toPoste().posCode();
		}
		return key;
	}
	
	/**
	 * Transformer une duree en ms en une longueur en semaines
	 */
	private int getPixelsBeetwen(NSTimestamp start, NSTimestamp end) {
		long ms = end.getTime() - start.getTime();
		return (int) (ms / (1000*3600*24/2));
	}

	private NSMutableDictionary dicoColor = new NSMutableDictionary();
	
	private String getColor() {
		String colorKey = getColorKey();
		String color = (String) dicoColor.valueForKey(colorKey);
		if (color == null) {
			color = HTML_COLORS[((dicoColor.count()*2)%HTML_COLORS.length)+1];
			dicoColor.setObjectForKey(color, colorKey);
		}
		return color;
	}
	
	/**
	 * La date de debut de l'affectation pour affichage correct 
	 * dans l'intervalle de visibilit�
	 * @param affectationDetailItem
	 * @return
	 */
	private NSTimestamp retailledDDeb(NSTimestamp date) {
		if (DateCtrl.isBefore(date, getDDebPeriode())) {
			return getDDebPeriode();
		} else {
			return date;
		}
	}
	
	/**
	 * La date de fin de l'affectation pour affichage correct 
	 * dans l'intervalle de visibilit�
	 * @param affectationDetailItem
	 * @return
	 */
	private NSTimestamp retailledDFin(NSTimestamp date) {
		if (DateCtrl.isAfter(date, getDFinPeriode())) {
			return getDFinPeriode();
		} else {
			return date;
		}
	}
	
	/**
	 * TODO utiliser la version qui sera dans {@link DateCtrl}
	 * retourne le debut de l'annee universitaire d'une date	
	 * ex : 02/02/2005 -> 01/09/2004
	 * @param uneDate
	 * @return
	 */
	private static NSTimestamp dateToDebutAnneeUniv(NSTimestamp uneDate) {	
		// on transltate la date sur l'annee civile : 01/09/2004 -> 01/01/2004
		int jourDebutAnnee = Integer.valueOf("01/09".substring(0,2)).intValue();
	  int moisDebutAnnee = Integer.valueOf("01/09".substring(3,5)).intValue();
      
      NSTimestamp uneDateDecalee = uneDate.timestampByAddingGregorianUnits(
          jourDebutAnnee-1, -(moisDebutAnnee-1), 0, 0, 0, 0); 
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
      NSTimestamp debutAnnee = debutAnneeDecalee.timestampByAddingGregorianUnits(
          0, (moisDebutAnnee-1), 0, 0, 0, 0);
      return debutAnnee;
  }
}
