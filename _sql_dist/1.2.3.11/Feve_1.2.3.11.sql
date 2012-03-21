/** Prise en compte du témoin TEM_VALIDE de la table MANGUE.AFFECTATION */
--
-- V_AFF_ETAB_PERS_NON_ENS  (View) 
--
CREATE OR REPLACE FORCE VIEW mangue.v_aff_etab_pers_non_ens (no_seq_affectation,
                                                             no_dossier_pers,
                                                             no_occupation,
                                                             no_seq_carriere,
                                                             no_seq_contrat,
                                                             c_structure,
                                                             d_deb_affectation,
                                                             d_fin_affectation,
                                                             num_quot_affectation,
                                                             den_quot_affectation,
                                                             d_creation,
                                                             d_modification
                                                            )
AS
   SELECT UNIQUE a.no_seq_affectation, a.no_dossier_pers, a.no_occupation,
                 a.no_seq_carriere, a.no_seq_contrat, a.c_structure,
                 a.d_deb_affectation, a.d_fin_affectation,
                 a.num_quot_affectation, a.den_quot_affectation, a.d_creation,
                 a.d_modification
            FROM mangue.affectation a,
                 grhum.v_personnel_non_ens v,
                 grhum.repart_type_groupe rtg
           WHERE a.tem_valide = 'O'
             AND a.no_dossier_pers = v.no_dossier_pers
             AND a.c_structure = rtg.c_structure
             AND rtg.tgrp_code = 'S'
             AND (   (    a.d_deb_affectation <= v.d_debut
                      AND a.d_fin_affectation >= v.d_fin
                      AND a.d_fin_affectation IS NOT NULL
                      AND v.d_fin IS NOT NULL
                     )
                  OR (    a.d_deb_affectation >= v.d_debut
                      AND a.d_fin_affectation >= v.d_fin
                      AND a.d_fin_affectation IS NOT NULL
                      AND v.d_fin IS NOT NULL
                     )
                  OR (    a.d_deb_affectation <= v.d_debut
                      AND a.d_fin_affectation <= v.d_fin
                      AND a.d_fin_affectation IS NOT NULL
                      AND v.d_fin IS NOT NULL
                     )
                  OR (    a.d_deb_affectation >= v.d_debut
                      AND a.d_fin_affectation <= v.d_fin
                      AND a.d_fin_affectation IS NOT NULL
                      AND v.d_fin IS NOT NULL
                     )
                  OR (    a.d_fin_affectation >= v.d_debut
                      AND a.d_fin_affectation IS NOT NULL
                      AND v.d_fin IS NULL
                     )
                  OR (    a.d_deb_affectation <= v.d_fin
                      AND a.d_fin_affectation IS NULL
                      AND v.d_fin IS NOT NULL
                     )
                  OR (a.d_fin_affectation IS NULL AND v.d_fin IS NULL)
                 );


    
    

/** gestion des droits associée (table à part) */
CREATE TABLE MANGUE.FEV_DRO_NOUV_ENTRANT
(
  DNE_KEY              NUMBER                   NOT NULL,
  NO_INDIVIDU_ENTRANT  NUMBER                   NOT NULL,
  NO_INDIVIDU_RESP     NUMBER                   NOT NULL,
  EPE_KEY              NUMBER                   NOT NULL,
  D_CREATION           DATE                     DEFAULT SYSDATE               NOT NULL,
  D_MODIFICATION       DATE                     DEFAULT SYSDATE               NOT NULL,
  DNE_D_DEBUT 	       DATE                     NOT NULL,
  DNE_D_FIN 		   DATE                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;

COMMENT ON TABLE MANGUE.FEV_DRO_NOUV_ENTRANT IS 'L''affectation des droits liés aux nouveaux arrivants (ou ceux non évalués sur la période précédente) permettant au N+1 de saisir des objectifs pour l''évaluation courant';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.DNE_KEY IS 'Clé primaire';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.NO_INDIVIDU_ENTRANT IS 'Agent dont les objectifs précédents sont à saisir';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.NO_INDIVIDU_RESP IS 'Titulaire du droit';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.EPE_KEY IS 'Période concernée';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.DNE_D_DEBUT IS 'Date de début de saisie autorisée';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.DNE_D_FIN IS 'Date de fin de saisie autorisée';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.D_CREATION IS 'Date de création de l''enregistrement (interne)';

COMMENT ON COLUMN MANGUE.FEV_DRO_NOUV_ENTRANT.D_MODIFICATION IS 'Date de dernière modification de l''enregistrement (interne)';


ALTER TABLE MANGUE.FEV_DRO_NOUV_ENTRANT ADD (
  CONSTRAINT PK_FEV_DROIT_NOUVEL_ENTRANT
 PRIMARY KEY
 (DNE_KEY));

ALTER TABLE MANGUE.FEV_DRO_NOUV_ENTRANT ADD (
  CONSTRAINT FK_FEV_DRO_NOU_ENT_INDIVIDU 
 FOREIGN KEY (NO_INDIVIDU_ENTRANT) 
 REFERENCES GRHUM.INDIVIDU_ULR (NO_INDIVIDU),
  CONSTRAINT FK_FEV_DRO_NOU_ENT_RESP 
 FOREIGN KEY (NO_INDIVIDU_RESP) 
 REFERENCES GRHUM.INDIVIDU_ULR (NO_INDIVIDU),
  CONSTRAINT FK_FEV_DRO_NOU_ENT_PERIODE 
 FOREIGN KEY (EPE_KEY) 
 REFERENCES MANGUE.EVALUATION_PERIODE (EPE_KEY));


CREATE SEQUENCE MANGUE.FEV_DRO_NOUV_ENTRANT_SEQ NOCACHE;


-- affichage des onglets selon le statut
ALTER TABLE MANGUE.TPL_ONGLET ADD (TEM_CONTRACTUEL VARCHAR2(1) DEFAULT 'O' NOT NULL);
ALTER TABLE MANGUE.TPL_ONGLET ADD (TEM_TITULAIRE VARCHAR2(1) DEFAULT 'O' NOT NULL);

ALTER TABLE MANGUE.TPL_ONGLET
 ADD CONSTRAINT CHK_TPL_ONGLET_CONTRACTUEL
 CHECK (TEM_CONTRACTUEL IN ('O', 'N'));

ALTER TABLE MANGUE.TPL_ONGLET
 ADD CONSTRAINT CHK_TPL_ONGLET_TITULAIRE
 CHECK (TEM_TITULAIRE IN ('O', 'N'));


COMMENT ON COLUMN MANGUE.TPL_ONGLET.TEM_CONTRACTUEL IS 'Afficher l''onglet pour les contractuels';

COMMENT ON COLUMN MANGUE.TPL_ONGLET.TEM_TITULAIRE IS 'Afficher l''onglet pour les titulaires';


-- table notice de promotions

CREATE TABLE MANGUE.EVALUATION_NOTICE_PROMO
(
  ENP_KEY                      NUMBER           NOT NULL,
  EVA_KEY                      NUMBER           NOT NULL,
  ENP_REDUC_ECHELON      NUMBER,
  ENP_REDUC_REFUS_MOTIF  VARCHAR2(4000),
  ENP_PROMO_GRADE              NUMBER,
  ENP_PROMO_GRADE_REFUS_MOTIF  VARCHAR2(4000),
  ENP_PROMO_CORPS              NUMBER,
  ENP_PROMO_CORPS_REFUS_MOTIF  VARCHAR2(4000),
  ENP_APPRECIATION_GENERALE    VARCHAR2(4000),
  D_CREATION                   DATE             DEFAULT SYSDATE               NOT NULL,
  D_MODIFICATION               DATE             DEFAULT SYSDATE               NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;

COMMENT ON TABLE MANGUE.EVALUATION_NOTICE_PROMO IS 'Notice de promotion liée à un entretien professionnel';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_KEY IS 'Clé primaire';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.EVA_KEY IS 'L''entretien professionnel associé (table MANGUE.EVALUATION)';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_REDUC_ECHELON IS 'Réduction d''échelon (0=Défavorable, 1/2/3=selon la population)';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_REDUC_REFUS_MOTIF IS 'Le motif si la réduction d''échelon est défavorable (0)';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_PROMO_GRADE IS 'Promotion de grade : 0=Défavorable, 1=Favorable, 2=Très favorable';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_PROMO_GRADE_REFUS_MOTIF IS 'Le motif si la promotion de grade est défavorable';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_PROMO_CORPS IS 'Promotion de corps : 0=Défavorable, 1=Favorable, 2=Très favorable';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_PROMO_CORPS_REFUS_MOTIF IS 'Le motif si la promotion de corps est défavorable';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.ENP_APPRECIATION_GENERALE IS 'Appréciation générale de la valeur professionnelle';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.D_CREATION IS 'Date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.EVALUATION_NOTICE_PROMO.D_MODIFICATION IS 'Date de dernière modification de l''enregistrement (interne)';


ALTER TABLE MANGUE.EVALUATION_NOTICE_PROMO ADD (
  CONSTRAINT PK_EVALUATION_NOTICE_PROMO
 PRIMARY KEY
 (ENP_KEY));

ALTER TABLE MANGUE.EVALUATION_NOTICE_PROMO ADD (
  CONSTRAINT PK_ENP_EVALUATION 
 FOREIGN KEY (EVA_KEY) 
 REFERENCES MANGUE.EVALUATION (EVA_KEY));

CREATE SEQUENCE MANGUE.EVALUATION_NOTICE_PROMO_SEQ NOCACHE;

-- evolution squelette pour intégration de la notice

-- onglet s'intercale en dernière position
UPDATE MANGUE.TPL_ONGLET
SET TON_POSITION = TON_POSITION + 1
WHERE TON_POSITION = (
	SELECT MAX(TON_POSITION)
	FROM MANGUE.TPL_ONGLET);
	
INSERT INTO MANGUE.TPL_ONGLET
   (TON_KEY, TON_LIBELLE, TON_COMMENTAIRE, D_CREATION, D_MODIFICATION, 
    TON_POSITION, TFI_KEY, TON_CODE, D_DEB_VAL, D_FIN_VAL,
    TEM_CONTRACTUEL, TEM_TITULAIRE)
 Values
   (MANGUE.TPL_ONGLET_SEQ.NEXTVAL, 'Notice de promotions', '', SYSDATE, SYSDATE, 
    (SELECT MAX(TON_POSITION)-1 FROM MANGUE.TPL_ONGLET), 
    (SELECT TFI_KEY FROM MANGUE.TPL_FICHE WHERE TFI_CODE = 'EVALUATION'), 
    'NOTICEPROM', TO_DATE('01/09/2011', 'DD/MM/YYYY'), NULL, 'N', 'O');




-- bloc nature
INSERT INTO MANGUE.TPL_BLOC_NATURE (
    TBN_KEY, TBN_LIBELLE, D_CREATION, D_MODIFICATION)
SELECT
    MANGUE.TPL_BLOC_NATURE_SEQ.NEXTVAL,
    'notice de promotions',
    SYSDATE,
    SYSDATE
FROM
    DUAL;


-- bloc
INSERT INTO MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE,
    D_DEB_VAL, D_FIN_VAL)
Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Notice de promotions', NULL,
   1, SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'NOTICEPROM'),
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'notice de promotions'),
   'NOTICEPROM',
   TO_DATE('01/09/2011', 'DD/MM/YYYY'), NULL
   );


-- allonger la taille du commentaire des items
ALTER TABLE MANGUE.TPL_ITEM MODIFY(TIT_COMMENTAIRE VARCHAR2(4000));

-- actualisation du décret de recours
update mangue.tpl_item set 
    tit_libelle = 'Modalités de recours (article 6 du décret n°2010-888 modifié par le décret n°2011-2041)',
    tit_commentaire = 'L''autorité hiérarchique peut être saisie par le fonctionnaire d''une demande de révision du compte rendu de l''entretien professionnel.<br>Ce recours hiérarchique est exercé dans un délai de quinze jours francs à compter de la date de notification à l''agent du compte rendu de l''entretien. L''autorité hiérarchique notifie sa réponse dans un délai de quinze jours francs à compter de la date de réception de la demande de révision du compte rendu d''entretien professionnel.<br>Les commissions administratives paritaires peuvent, à la requête de l''intéressé, sous réserve qu''il ait au préalable exercé le recours mentionné à l''alinéa précédent, demander à l''autorité hiérarchique la révision du compte rendu de l''entretien professionnel. Dans ce cas, communication doit être faite aux commissions de tous éléments utiles d''information. Les commissions administratives paritaires doivent être saisies dans un délai d''un mois à compter de la date de notification de la réponse formulée par l''autorité hiérarchique dans le cadre du recours.'
where tit_code = 'EVARECOURI';



ALTER TABLE MANGUE.POSTE ADD (TEM_VALIDE  VARCHAR2(1) DEFAULT 'O' NOT NULL);
ALTER TABLE MANGUE.POSTE ADD CONSTRAINT CHK_POSTE_TEM_VALIDE_O_N CHECK (TEM_VALIDE in ('O', 'N'));
COMMENT ON COLUMN MANGUE.POSTE.TEM_VALIDE IS 'Enregistrement en vigueur ''O'' ou effacé ''N''';
