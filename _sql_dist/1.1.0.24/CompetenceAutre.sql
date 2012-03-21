ALTER TABLE MANGUE.REPART_FDP_AUTRE ADD (FAU_TYPE  VARCHAR2(1) DEFAULT 'A' NOT NULL);

COMMENT ON TABLE MANGUE.REPART_FDP_AUTRE IS 'Activités et compétences "autres" associées à la fiche de poste. Contient des champs libres qui ne sont normalement pas disponibles dans les tables de nomenclatures d''activités et compétences du référentiel.';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.FAU_KEY IS 'clé primaire';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.FDP_KEY IS 'fiche de poste concernée (table MANGUE.FICHE_DE_POSTE)';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.D_MODIFICATION IS 'date de dernière modification de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.FAU_ACTIVITE_AUTRE IS 'l''activité ou la compétence autre (champ texte libre)';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.FAU_POSITION IS 'la position au sein d''une liste classée (le plus petit = le plus haut)';
COMMENT ON COLUMN MANGUE.REPART_FDP_AUTRE.FAU_TYPE IS 'type ("A" pour activité et "C" pour compétence)';

ALTER TABLE MANGUE.REPART_FDP_AUTRE ADD CONSTRAINT CHK_REPART_FPD_AUTRE_TYPE CHECK (FAU_TYPE IN ('A', 'C'));


ALTER TABLE MANGUE.REPART_NIVEAU_COMP_PRO MODIFY(RFC_KEY  NULL);
ALTER TABLE MANGUE.REPART_NIVEAU_COMP_PRO ADD (FAU_KEY  NUMBER);

COMMENT ON TABLE MANGUE.REPART_NIVEAU_COMP_PRO IS 'Evaluation : niveau des compétences (soit une compétence REFERENS, soit une compétence autre, mais pas les deux)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.EVA_KEY IS 'Evaluation associée (MANGUE.EVALUATION.EVA_KEY)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.RFC_KEY IS 'Compétence REFERENS d''une fiche de poste (MANGUE.REPART_FDP_COMP.RFC_KEY)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.RNC_KEY IS 'clé primaire';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.D_MODIFICATION IS 'date de dernière modification de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.NCP_KEY IS 'Niveau de compétence (GRHUM.NIVEAU_COMPETENCE_PRO.NCP_KEY)';
COMMENT ON COLUMN MANGUE.REPART_NIVEAU_COMP_PRO.FAU_KEY IS 'Compétence autre d''une fiche de poste (MANGUE.REPART_FDP_AUTRE.FAU_KEY)';

ALTER TABLE MANGUE.REPART_NIVEAU_COMP_PRO
 ADD CONSTRAINT CHK_RNC_COMPETENCE_UNIQUE
 CHECK (RFC_KEY is null or FAU_KEY is null);
 
ALTER TABLE MANGUE.REPART_NIVEAU_COMP_PRO
 ADD CONSTRAINT CHK_RNC_COMPETENCE_NONVIDE
 CHECK (RFC_KEY is not null or FAU_KEY is not null);

ALTER TABLE MANGUE.REPART_NIVEAU_COMP_PRO
 ADD CONSTRAINT FK_RNC_COMPETENCE_AUTRE 
 FOREIGN KEY (FAU_KEY) 
 REFERENCES MANGUE.REPART_FDP_AUTRE (FAU_KEY);


Insert into MANGUE.MANGUE_PARAMETRES
   (PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES)
 Values
   (MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_FICHE_DE_POSTE_SAISIE_ACTIVITES_AUTRES', 'O', 'Autoriser la saisie d''activités non référencées dans une nomenclature dans les fiches de poste (O/N).');

Insert into MANGUE.MANGUE_PARAMETRES
   (PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES)
 Values
   (MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_FICHE_DE_POSTE_SAISIE_COMPETENCES_AUTRES', 'N', 'Autoriser la saisie de compétences non référencées dans une nomenclature dans les fiches de poste et permettre leur évaluation dans la fiche d''entretien professionnel (O/N)');



