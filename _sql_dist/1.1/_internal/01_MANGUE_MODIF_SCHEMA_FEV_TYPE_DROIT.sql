CREATE TABLE MANGUE.FEV_TYPE_DROIT_ACCES
(
  DTA_KEY         NUMBER                        NOT NULL,
  DTA_CODE        VARCHAR2(8)                   NOT NULL,
  DTA_LIBELLE     VARCHAR2(256)                 NOT NULL,
  D_CREATION      DATE        DEFAULT SYSDATE   NOT NULL,
  D_MODIFICATION  DATE        DEFAULT SYSDATE   NOT NULL
)

LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;

COMMENT ON TABLE MANGUE.FEV_TYPE_DROIT_ACCES IS 'Les différents types d''accès définit dans les droits';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_ACCES.DTA_KEY IS 'Clé primaire';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_ACCES.DTA_CODE IS 'Code du type d''accès (libelle très court)';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_ACCES.DTA_LIBELLE IS 'Libellé complet du type d''accès';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_ACCES.D_CREATION IS 'Date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_ACCES.D_MODIFICATION IS 'Date de dernière modification de l''enregistrement (interne)';


ALTER TABLE MANGUE.FEV_TYPE_DROIT_ACCES ADD (
  CONSTRAINT FEV_TYPE_DROIT_ACCES_PK
 PRIMARY KEY
 (DTA_KEY));

CREATE TABLE MANGUE.FEV_TYPE_DROIT_CIBLE
(
  DTC_KEY         NUMBER                        NOT NULL,
  DTC_CODE        VARCHAR2(8)                   NOT NULL,
  DTC_LIBELLE     VARCHAR2(256)                 NOT NULL,
  D_CREATION      DATE                          DEFAULT SYSDATE               NOT NULL,
  D_MODIFICATION  DATE                          DEFAULT SYSDATE               NOT NULL
)

LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
NOMONITORING;

COMMENT ON TABLE MANGUE.FEV_TYPE_DROIT_CIBLE IS 'Les différents types de cibles des droits';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_CIBLE.DTC_KEY IS 'Clé primaire';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_CIBLE.DTC_CODE IS 'Code du type de cible (libelle très court)';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_CIBLE.DTC_LIBELLE IS 'Libellé complet du type de cible';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_CIBLE.D_CREATION IS 'Date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.FEV_TYPE_DROIT_CIBLE.D_MODIFICATION IS 'Date de dernière modification de l''enregistrement (interne)';


ALTER TABLE MANGUE.FEV_TYPE_DROIT_CIBLE ADD (
  CONSTRAINT FEV_TYPE_DROIT_CIBLE_PK
 PRIMARY KEY
 (DTC_KEY));


ALTER TABLE MANGUE.FEV_DROIT
 ADD (DTA_KEY  NUMBER);

ALTER TABLE MANGUE.FEV_DROIT
 ADD (DTC_KEY  NUMBER);


COMMENT ON COLUMN MANGUE.FEV_DROIT.DTA_KEY IS 'type d''accès (MANGUE.FEV_TYPE_DROIT_ACCES)';

COMMENT ON COLUMN MANGUE.FEV_DROIT.DTC_KEY IS 'type des objets de la cible à impacter (MANGUE.FEV_TYPE_DROIT_CIBLE)';
ALTER TABLE MANGUE.FEV_DROIT
 ADD CONSTRAINT FK_FEV_TYPE_DROIT_ACCES 
 FOREIGN KEY (DTA_KEY) 
 REFERENCES MANGUE.FEV_TYPE_DROIT_ACCES (DTA_KEY);

ALTER TABLE MANGUE.FEV_DROIT
 ADD CONSTRAINT FK_FEV_TYPE_DROIT_CIBLE 
 FOREIGN KEY (DTC_KEY) 
 REFERENCES MANGUE.FEV_TYPE_DROIT_CIBLE (DTC_KEY);
 
 
 
INSERT INTO MANGUE.FEV_TYPE_DROIT_ACCES ( DTA_KEY, DTA_CODE, DTA_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
1, 'CRE', 'Création', SYSDATE, SYSDATE); 
INSERT INTO MANGUE.FEV_TYPE_DROIT_ACCES ( DTA_KEY, DTA_CODE, DTA_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
2, 'MAJ', 'Modification',  SYSDATE, SYSDATE); 
INSERT INTO MANGUE.FEV_TYPE_DROIT_ACCES ( DTA_KEY, DTA_CODE, DTA_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
3, 'SUP', 'Suppression', SYSDATE, SYSDATE);  
INSERT INTO MANGUE.FEV_TYPE_DROIT_ACCES ( DTA_KEY, DTA_CODE, DTA_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
4, 'VIS', 'Visualisation',  SYSDATE, SYSDATE);  



INSERT INTO MANGUE.FEV_TYPE_DROIT_CIBLE ( DTC_KEY, DTC_CODE, DTC_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
1, 'POS', 'Poste',  SYSDATE, SYSDATE); 
INSERT INTO MANGUE.FEV_TYPE_DROIT_CIBLE ( DTC_KEY, DTC_CODE, DTC_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
2, 'FDP', 'Fiche de poste',  SYSDATE, SYSDATE); 
INSERT INTO MANGUE.FEV_TYPE_DROIT_CIBLE ( DTC_KEY, DTC_CODE, DTC_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
3, 'FLO', 'Fiche LOLF', SYSDATE, SYSDATE); 
INSERT INTO MANGUE.FEV_TYPE_DROIT_CIBLE ( DTC_KEY, DTC_CODE, DTC_LIBELLE, D_CREATION, D_MODIFICATION ) VALUES ( 
4, 'EPR', 'Entretien prof.',  SYSDATE, SYSDATE);  



-- attention, cette ligne peut planter, c'est pas grave ..........
ALTER TABLE MANGUE.FEV_DROIT DROP COLUMN DRO_TYPE_CIBLE;

ALTER TABLE MANGUE.FEV_DROIT MODIFY(DRO_TYPE  NULL);




CREATE OR REPLACE PROCEDURE MANGUE.Fev_Migration_Droits IS
-- Procédure de migration des droits simples de lecture et ecriture
-- vers les droits détaillé
-- Auteur : CRI
-- creation : 20/02/2009
-- modification : 20/02/2009

CURSOR droits IS
SELECT * FROM MANGUE.FEV_DROIT;
droit_lgn MANGUE.FEV_DROIT%ROWTYPE;

BEGIN
        
        OPEN droits;
          LOOP

             FETCH droits INTO droit_lgn;
             EXIT WHEN droits %NOTFOUND;

             -- droit en ecriture => déclinaison en droits selon le type de droit
             IF (droit_lgn.DRO_TYPE = 'W') THEN
             
             
                  -- sur composante, structure 
                  IF (droit_lgn.DRO_C_STRUCTURE_COMPOSANTE is not null OR droit_lgn.DRO_C_STRUCTURE is not null) THEN

                      -- creation / modification / suppression  sur poste et fiche de poste
                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 1, 1);

                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 1);

                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 3, 1);

                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 1, 2);

                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 2);

                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 3, 2);
                      
                      -- mise a jour de fiche LOLF
                     INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                      DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                      MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                      droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 3);

                  ELSE 
                    BEGIN
                    
                    
                        -- sur poste
                        if (droit_lgn.DRO_POS_KEY is not null) THEN
                        
                            -- modification poste
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 1);

                            -- creation, modification et suppression fiche de poste
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 1, 2);
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 2);
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 1, 3);
                       
                            -- mise a jour de fiche LOLF
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 3);

                        ELSE
                            BEGIN
                            
                                -- sur fiche de poste ou fiche LOLF
                                IF (droit_lgn.DRO_FDP_KEY is not null OR droit_lgn.DRO_FLO_KEY is not null) THEN
                                
                            
                                    BEGIN
                                
                                        -- sur fiche de poste
                                        IF (droit_lgn.DRO_FDP_KEY is not null) THEN
                                
                                            -- mise a jour de fiche de poste
                                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 2);

                                        -- sur fiche LOLF
                                        ELSE 
                                            -- mise a jour de fiche LOLF
                                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 3);
                                        END IF;

                               
                                    END;
                            
                            
                            
                                -- sur individu
                                ELSE
                            
                                    -- creation et mise a jour evaluation
                                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                       DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                       MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                       droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 1, 4);
                                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                       DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                       MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                       droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 2, 4);
                                 
                                END IF;
                            
                            
                            END;
                            
                            
                        END IF;
                    
                    
                    END; 

                  
                  END IF;
                  
                  
            -- droit en lecture
            ELSE
            
                -- sur composante, structure 
                IF (droit_lgn.DRO_C_STRUCTURE_COMPOSANTE is not null OR droit_lgn.DRO_C_STRUCTURE is not null) THEN

                    -- visu sur poste, fiche de poste et fiche LOLF
                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                        DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                        MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                        droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 1);
                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                        DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                        MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                        droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 2);
                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                        DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                        MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                        droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 3);
                    

                ELSE
                
                    BEGIN
                    
                        -- sur poste
                        IF (droit_lgn.DRO_POS_KEY is not null) THEN
                            
                            -- visu sur poste
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 1);      
                            -- visu sur fiche de poste
                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 2);
                            
                        ELSE
                        
                            BEGIN
                            
                                -- sur fiche poste
                                IF (droit_lgn.DRO_FDP_KEY is not null) THEN
                        
                                
                                    -- visu sur fiche de poste
                                    INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                        DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                        MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                        droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 2);
                        
                                ELSE 
                                
                                    BEGIN
                                    
                                    
                                        -- sur fiche LOLF
                                        IF (droit_lgn.DRO_FLO_KEY is not null) THEN
                                    
                                             -- visu sur fiche LOLF
                                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 3);
                        
                        
                                        -- sur individu
                                        ELSE
                                        
                                        
                                             -- visu sur toute fiche d'evaluation
                                            INSERT INTO MANGUE.FEV_DROIT ( DRO_KEY, D_CREATION, D_MODIFICATION, NO_INDIVIDU, DRO_FDP_KEY, DRO_FLO_KEY, DRO_POS_KEY, DRO_NO_INDIVIDU, DRO_C_STRUCTURE,
                                                DRO_D_DEBUT, DRO_D_FIN, DRO_TYPE, DRO_C_STRUCTURE_COMPOSANTE, DRO_EPE_KEY, DTA_KEY, DTC_KEY ) VALUES ( 
                                                MANGUE.FEV_DROIT_SEQ.NEXTVAL, sysdate, sysdate, droit_lgn.NO_INDIVIDU, droit_lgn.DRO_FDP_KEY, droit_lgn.DRO_FLO_KEY, droit_lgn.DRO_POS_KEY, droit_lgn.DRO_NO_INDIVIDU, droit_lgn.DRO_C_STRUCTURE, 
                                                droit_lgn.DRO_D_DEBUT , droit_lgn.DRO_D_FIN, droit_lgn.DRO_TYPE, droit_lgn.DRO_C_STRUCTURE_COMPOSANTE, droit_lgn.DRO_EPE_KEY, 4, 4);
                                                
                                                
                                        END IF;
                                    
                                    
                                    END;        
                                
                                END IF;
                            
                            END;
                        
                        
                        END IF;
                    
                    
                    END;
                
                
                END IF;
            
            
            
            END IF;


        END LOOP;
        CLOSE droits;


   

END;
/





BEGIN 
  MANGUE.FEV_MIGRATION_DROITS;
END; 


-- nettoyage des anciennes donnees
DELETE FROM MANGUE.FEV_DROIT WHERE DTA_KEY IS NULL OR DTC_KEY IS NULL; 
-- suppression des cas impossibles
-- creation fiche lolf
delete from MANGUE.fev_droit where dta_key = 1 and dtc_key = 3;


-- suppression des attributs inutiles
ALTER TABLE MANGUE.FEV_DROIT
 DROP CONSTRAINT CHK_FEV_DROIT_TYPE;

ALTER TABLE MANGUE.FEV_DROIT DROP COLUMN DRO_D_DEBUT;

ALTER TABLE MANGUE.FEV_DROIT DROP COLUMN DRO_D_FIN;

ALTER TABLE MANGUE.FEV_DROIT DROP COLUMN DRO_TYPE;

