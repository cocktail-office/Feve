package org.cocktail.feve.eos.modele;

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

import org.cocktail.feve.app.Application;
import org.cocktail.feve.eos.modele.grhum.EOFormationPersonnel;
import org.cocktail.feve.eos.modele.grhum.EOGrhumRepartStructure;
import org.cocktail.feve.eos.modele.grhum.EORepartSillandLolf;
import org.cocktail.feve.eos.modele.mangue.EOAffectationDetail;
import org.cocktail.feve.eos.modele.mangue.EODroit;
import org.cocktail.feve.eos.modele.mangue.EODroitNouvelEntrant;
import org.cocktail.feve.eos.modele.mangue.EOEnqueteFormation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluation;
import org.cocktail.feve.eos.modele.mangue.EOEvaluationPeriode;
import org.cocktail.feve.eos.modele.mangue.EOFicheDePoste;
import org.cocktail.feve.eos.modele.mangue.EOFicheLolf;
import org.cocktail.feve.eos.modele.mangue.EOHierarchie;
import org.cocktail.feve.eos.modele.mangue.EOIndividuFormations;
import org.cocktail.feve.eos.modele.mangue.EOObjectif;
import org.cocktail.feve.eos.modele.mangue.EOPoste;
import org.cocktail.feve.eos.modele.mangue.EORepartEnqComp;
import org.cocktail.feve.eos.modele.mangue.EORepartEvaNouvelleComp;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpActi;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpAutre;
import org.cocktail.feve.eos.modele.mangue.EORepartFdpComp;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheBlocActivation;
import org.cocktail.feve.eos.modele.mangue.EORepartFicheItem;
import org.cocktail.feve.eos.modele.mangue.EORepartFloLolfNomen;
import org.cocktail.feve.eos.modele.mangue.EORepartFloSilland;
import org.cocktail.feve.eos.modele.mangue.EORepartFormationSouhaitee;
import org.cocktail.feve.eos.modele.mangue.EORepartNiveauComp;
import org.cocktail.feve.eos.modele.mangue.EOSituActivite;
import org.cocktail.feve.eos.modele.mangue.EOStructureInfo;
import org.cocktail.feve.eos.modele.mangue.EOTplBloc;
import org.cocktail.feve.eos.modele.mangue.EOTplBlocNature;
import org.cocktail.feve.eos.modele.mangue.EOTplFiche;
import org.cocktail.feve.eos.modele.mangue.EOTplItem;
import org.cocktail.feve.eos.modele.mangue.EOTplItemNature;
import org.cocktail.feve.eos.modele.mangue.EOTplItemValeur;
import org.cocktail.feve.eos.modele.mangue.EOTplOnglet;
import org.cocktail.feve.eos.modele.mangue.EOTplRepartItemItemValeur;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitAcces;
import org.cocktail.feve.eos.modele.mangue.EOTypeDroitCible;
import org.cocktail.fwkcktlwebapp.common.database.CktlRecord;
import org.cocktail.fwkcktlwebapp.common.util.DateCtrl;
import org.cocktail.fwkcktlwebapp.server.database.CktlDataBus;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSValidation;

/**
 * Classe permettant de gerer automatiquement les dates de creation et
 * modification des enregistrements. La liste des entités concernées est données
 * par le tableau {@link #ENTITIES_DATE_SENSITIVE}
 * 
 * Gère aussi d'autres méthodes communes
 * 
 * @author ctarade
 */
public abstract class A_FeveCktlRecord
		extends CktlRecord {

	public static final String OUI = "O";
	public static final String NON = "N";

	private final static NSArray<String> ENTITIES_DATE_SENSITIVE = new NSArray<String>(new String[] {
			// modele mangue
			EOAffectationDetail.ENTITY_NAME,
			EODroit.ENTITY_NAME,
			EOEnqueteFormation.ENTITY_NAME,
			EODroit.ENTITY_NAME,
			EODroitNouvelEntrant.ENTITY_NAME,
			EOEvaluation.ENTITY_NAME,
			EOEvaluationPeriode.ENTITY_NAME,
			EOFicheDePoste.ENTITY_NAME,
			EOFicheLolf.ENTITY_NAME,
			EOHierarchie.ENTITY_NAME,
			EOIndividuFormations.ENTITY_NAME,
			EOObjectif.ENTITY_NAME,
			EOPoste.ENTITY_NAME,
			EORepartEnqComp.ENTITY_NAME,
			EORepartEvaNouvelleComp.ENTITY_NAME,
			EORepartFdpActi.ENTITY_NAME,
			EORepartFdpAutre.ENTITY_NAME,
			EORepartFdpComp.ENTITY_NAME,
			EORepartFdpActi.ENTITY_NAME,
			EORepartFicheBlocActivation.ENTITY_NAME,
			EORepartFicheItem.ENTITY_NAME,
			EORepartFloLolfNomen.ENTITY_NAME,
			EORepartFloSilland.ENTITY_NAME,
			EORepartFormationSouhaitee.ENTITY_NAME,
			EORepartNiveauComp.ENTITY_NAME,
			EOSituActivite.ENTITY_NAME,
			EOStructureInfo.ENTITY_NAME,
			EOTplBloc.ENTITY_NAME,
			EOTplBlocNature.ENTITY_NAME,
			EOTplFiche.ENTITY_NAME,
			EOTplItem.ENTITY_NAME,
			EOTplItemNature.ENTITY_NAME,
			EOTplItemValeur.ENTITY_NAME,
			EOTplOnglet.ENTITY_NAME,
			EOTplRepartItemItemValeur.ENTITY_NAME,
			EOTypeDroitAcces.ENTITY_NAME,
			EOTypeDroitCible.ENTITY_NAME,
			// modele grhum
			EOGrhumRepartStructure.ENTITY_NAME,
			EOFormationPersonnel.ENTITY_NAME,
			EORepartSillandLolf.ENTITY_NAME });

	private Boolean _shouldProcessDate;

	private boolean shouldProcessDate() {
		if (_shouldProcessDate == null) {
			_shouldProcessDate = new Boolean(ENTITIES_DATE_SENSITIVE.containsObject(entityName()));
		}
		return _shouldProcessDate;
	}

	public void validateForInsert() throws NSValidation.ValidationException {
		if (shouldProcessDate()) {
			setDCreation(DateCtrl.now());
			setDModification(DateCtrl.now());
		}
		super.validateForInsert();
	}

	public void validateForUpdate() throws NSValidation.ValidationException {
		if (shouldProcessDate()) {

			boolean shouldChangeDModification = false;
			// on met a jour la date uniquement si des attributs ou des to one ont
			// changé
			NSDictionary dico = changesFromSnapshot(editingContext().committedSnapshotForObject(this));
			NSArray keys = dico.allKeys();
			NSArray attributeKeys = attributeKeys();
			NSArray toOneRelationshipKeys = toOneRelationshipKeys();
			for (int i = 0; i < keys.count() && !shouldChangeDModification; i++) {
				String key = (String) keys.objectAtIndex(i);
				// l'attribut dModification seul n'est pas un critere de mise à jour
				if (attributeKeys.containsObject(key) && !key.equals(D_MODIFICATION)) {
					shouldChangeDModification = true;
				} else if (toOneRelationshipKeys.contains(key)) {
					shouldChangeDModification = true;
				}
			}
			if (shouldChangeDModification) {
				setDModification(DateCtrl.now());
			}
		}
		super.validateForUpdate();
	}

	public void validateForDelete() throws NSValidation.ValidationException {
		super.validateForDelete();
	}

	public void validateForSave() throws NSValidation.ValidationException {
		super.validateForSave();
	}

	private final static String D_CREATION = "dCreation";

	private void setDCreation(NSTimestamp value) {
		takeStoredValueForKey(value, D_CREATION);
	}

	private final static String D_MODIFICATION = "dModification";

	private void setDModification(NSTimestamp value) {
		takeStoredValueForKey(value, D_MODIFICATION);
	}

	/**
	 * Donne le qualifier attendu pour obtenir des enregistrements inclus dans une
	 * période
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public EOQualifier getValiditeQualifier(
			String dDebValKey, String dFinValKey, NSTimestamp dateDebut, NSTimestamp dateFin) {
		EOQualifier qual = CktlDataBus.newCondition(
				"(" + dDebValKey + "=nil and " + dFinValKey + "=nil) or " +
						"(" + dFinValKey + ">=%@ and (" + dDebValKey + "<=%@ or " + dDebValKey + "=nil )) or " +
						"(" + dDebValKey + "<=%@ and (" + dFinValKey + ">=%@ or " + dFinValKey + "=nil )) or " +
						"(" + dDebValKey + ">=%@ and " + dFinValKey + "<=%@ )",
				new NSArray<NSTimestamp>(new NSTimestamp[] {
						dateDebut, dateDebut,
						dateFin, dateFin,
						dateDebut, dateFin }));
		return qual;
	}

	private static Application app = (Application) Application.application();

	public static Application app() {
		return app;
	}

	/**
	 * Test si la longeur de la valeur saisie ne depasse pas la valeur maximale
	 * autorisee. Si dépassement, alors la méthode le nombre d'octets en trop, 0
	 * si ok.
	 */
	public int depassement(
			String attributeName,
			String attributeValue)
			throws NSValidation.ValidationException {

		int depassement = 0;

		int maxLen = maxLengthForAttribute(attributeName);
		int realLen = (attributeValue == null) ? 0 : attributeValue.length();

		if (realLen > maxLen) {
			depassement = realLen - maxLen;
		}

		return depassement;
	}
}
