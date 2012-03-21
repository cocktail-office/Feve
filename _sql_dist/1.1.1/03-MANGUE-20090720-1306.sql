--
-- Patch MANGUE version 1.3.0.6 du 20/07/2009 à executer depuis le user MANGUE
--
--
-- PRE-REQUIS : Patch de MANGUE (1.3.0.5) du 23/06/2009
--              Patch de GRHUM (1.5.7.0) du 20/07/2009
--
SET DEFINE OFF;

--------------
-- 1.1.0.4
--------------

--
-- Creation de la table FICHE_LOLF
--
CREATE OR REPLACE FUNCTION MANGUE.CreateFicheLolf01 RETURN VARCHAR2
AS
BEGIN
    RETURN 'CREATE TABLE MANGUE.FICHE_LOLF' ||
		' (' || 
        ' FLO_KEY         NUMBER                        NOT NULL,' || 
        ' POS_KEY         NUMBER                        NOT NULL,' || 
        ' FLO_D_DEBUT     DATE                          NOT NULL,' || 
        ' FLO_D_FIN       DATE,' || 
        ' D_CREATION      DATE                          DEFAULT SYSDATE               NOT NULL,' || 
        ' D_MODIFICATION  DATE                          DEFAULT SYSDATE               NOT NULL,' || 
        ' EXE_ORDRE       NUMBER                        NOT NULL' || 
        ' )' || 
        ' LOGGING ' || 
        ' NOCOMPRESS ' || 
        ' NOCACHE' || 
        ' NOPARALLEL' || 
        ' NOMONITORING';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateFicheLolf02 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE MANGUE.FICHE_LOLF ADD ( CONSTRAINT PK_FICHE_LOLF PRIMARY KEY (FLO_KEY))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateFicheLolf03 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE MANGUE.FICHE_LOLF ADD ( CONSTRAINT FK_FICHE_LOLF_POSTE FOREIGN KEY (POS_KEY) REFERENCES MANGUE.POSTE (POS_KEY), CONSTRAINT FK_FICHE_LOLF_EXERCICE FOREIGN KEY (EXE_ORDRE) REFERENCES JEFY_ADMIN.EXERCICE (EXE_ORDRE))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateFicheLolf04 RETURN VARCHAR2
AS
BEGIN
    RETURN 'DROP SEQUENCE MANGUE.FICHE_LOLF_SEQ';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateFicheLolf05 RETURN VARCHAR2
AS
BEGIN
    RETURN 'CREATE SEQUENCE MANGUE.FICHE_LOLF_SEQ NOCACHE NOORDER';
END;
/

--
-- Creation de la table REPART_FLO_SILLAND
--
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilland01 RETURN VARCHAR2
AS
BEGIN
    RETURN 'CREATE TABLE MANGUE.REPART_FLO_SILLAND' ||
		' (' ||
		' SIL_KEY         NUMBER                        NOT NULL,' ||
		' FLO_KEY         NUMBER                        NOT NULL,' ||
		' RFS_QUOTITE     NUMBER                        DEFAULT 100                   NOT NULL,' ||
		' D_CREATION      DATE                          DEFAULT SYSDATE               NOT NULL,' ||
		' D_MODIFICATION  DATE                          DEFAULT SYSDATE               NOT NULL' ||
		' )' || 
        ' LOGGING' || 
        ' NOCOMPRESS ' || 
        ' NOCACHE' || 
        ' NOPARALLEL' || 
        ' NOMONITORING';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilland02 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE MANGUE.REPART_FLO_SILLAND ADD (CONSTRAINT PK_REPART_FLO_SILLAND PRIMARY KEY (SIL_KEY, FLO_KEY))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilland03 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE MANGUE.REPART_FLO_SILLAND ADD (CONSTRAINT FK_REP_FLO_SIL_SILLAND FOREIGN KEY (SIL_KEY) REFERENCES GRHUM.SILLAND (SIL_KEY), CONSTRAINT FK_REP_FLO_SIL_FICHE_LOLF FOREIGN KEY (FLO_KEY) REFERENCES MANGUE.FICHE_LOLF (FLO_KEY))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.DropRepartFloSillandSeq RETURN VARCHAR2
AS
BEGIN
	RETURN 'DROP SEQUENCE MANGUE.REPART_FLO_SILLAND_SEQ';
END;
/

--
-- Creation de la table REPART_REP_FLO_SIL_LOLF_NOMEN
--
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilLolfNomen01 RETURN VARCHAR2
AS
BEGIN 
	RETURN 'CREATE TABLE MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN' ||
		' (' ||
		'   SIL_KEY         NUMBER                        NOT NULL,' ||
		'   FLO_KEY         NUMBER                        NOT NULL,' ||
		'   LOLF_ID         NUMBER                        NOT NULL,' ||
		'   EXE_ORDRE       NUMBER                        NOT NULL,' ||
		'   RRF_QUOTITE     NUMBER                        DEFAULT 100                   NOT NULL,' ||
		'   D_CREATION      DATE                          DEFAULT SYSDATE               NOT NULL,' ||
		'   D_MODIFICATION  DATE                          DEFAULT SYSDATE               NOT NULL' ||
		' )' ||
		' LOGGING ' ||
		' NOCOMPRESS ' ||
		' NOCACHE' ||
		' NOPARALLEL' ||
		' NOMONITORING';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilLolfNomen02 RETURN VARCHAR2
AS
BEGIN 
	RETURN 'ALTER TABLE MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN ADD ( CONSTRAINT PK_REPART_RFSL PRIMARY KEY (SIL_KEY, FLO_KEY, LOLF_ID))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.CreateRepartFloSilLolfNomen03 RETURN VARCHAR2
AS
BEGIN 
	RETURN 'ALTER TABLE MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN ADD (  CONSTRAINT FK_REPART_RFSL_RFS  FOREIGN KEY (SIL_KEY, FLO_KEY) REFERENCES MANGUE.REPART_FLO_SILLAND (SIL_KEY, FLO_KEY), CONSTRAINT FK_REPART_RFSL_EXERCICE '||
 			'FOREIGN KEY (EXE_ORDRE) REFERENCES JEFY_ADMIN.EXERCICE (EXE_ORDRE), CONSTRAINT FK_REPART_RFSL_LOLF_NOMEN FOREIGN KEY (LOLF_ID) REFERENCES JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE (LOLF_ID))';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.DropRepartFloLolfNomenSeq RETURN VARCHAR2
AS
BEGIN
	RETURN 'DROP SEQUENCE MANGUE.REPART_FLO_LOLF_NOMEN_SEQ';
END;
/

--
-- callage de la hierarchie sur les periodes d'evaluation
--
CREATE OR REPLACE FUNCTION MANGUE.AlterHierarchieAddEpeKey01 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.HIERARCHIE ADD (EPE_KEY  NUMBER)';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.AlterHierarchieAddEpeKey02 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.HIERARCHIE ADD CONSTRAINT FK_HIERARCHIE_PERIODE FOREIGN KEY (EPE_KEY) REFERENCES MANGUE.EVALUATION_PERIODE (EPE_KEY)';
END;
/

--------------
-- 1.1.0.8
--------------

--
-- voir plus bas la procedure de creation des enseignants
--

--------------
-- 1.1.0.11
--------------

--
-- position des objectifs
--
CREATE OR REPLACE FUNCTION MANGUE.AlterObjectifAddObjPosition01 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.OBJECTIF ADD (OBJ_POSITION NUMBER DEFAULT 0 NOT NULL)';
END;
/
CREATE OR REPLACE FUNCTION MANGUE.AlterObjectifAddObjPosition02 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.OBJECTIF ADD CONSTRAINT FK_OBJECTIF_EVALUATION FOREIGN KEY (EVA_KEY) REFERENCES MANGUE.EVALUATION (EVA_KEY)';
END;
/

-----------
--
-----------

--
-- Creation de la table REPART_SILLAND_LOLF
--


--
-- Attribut FEV_DROIT.DRO_C_STRUCTURE_COMPOSANTE
--
CREATE OR REPLACE FUNCTION MANGUE.AlterFeveDroitDroCStructComp01 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.FEV_DROIT ADD (DRO_C_STRUCTURE_COMPOSANTE VARCHAR2(10))';
END;
/

CREATE OR REPLACE FUNCTION MANGUE.AlterFeveDroitDroCStructComp02 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.FEV_DROIT ADD ( CONSTRAINT FK_FEV_DROIT_COMPOSANTE FOREIGN KEY (DRO_C_STRUCTURE_COMPOSANTE) REFERENCES GRHUM.STRUCTURE_ULR (C_STRUCTURE))';
END;
/

--
-- Attribut FEV_DROIT.DRO_EPE_KEY
--
CREATE OR REPLACE FUNCTION MANGUE.AlterFeveDroitDroEpeKey01 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.FEV_DROIT ADD (DRO_EPE_KEY NUMBER)';
END;
/

CREATE OR REPLACE FUNCTION MANGUE.AlterFeveDroitDroEpeKey02 RETURN VARCHAR2
AS
BEGIN
	RETURN 'ALTER TABLE MANGUE.FEV_DROIT ADD (CONSTRAINT FK_FEV_DROIT_EVA_PERIODE FOREIGN KEY (DRO_EPE_KEY) REFERENCES MANGUE.EVALUATION_PERIODE (EPE_KEY))';
END;
/




--
-- Procedure de synchronisation globale du user MANGUE
--
CREATE OR REPLACE PROCEDURE MANGUE.SyncMangueFeve
AS 
NB_RECORDS INTEGER;
BEGIN

	-- ajout des paramètres de MANGUE_PARAMETRES sur les periode d'ouverture
	select count(*) into nb_records from MANGUE.MANGUE_PARAMETRES where PARAM_KEY = 'FEV_EVALUATION_SAISIE_D_DEBUT';
    if (nb_records = 0) then
   		INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
	    	MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_EVALUATION_SAISIE_D_DEBUT', TO_CHAR(SYSDATE-1, 'DD/MM/YYYY'), 'Date de debut de saisie des evaluations par les N+1 (hors periode, les evaluations sont en lecture seule)'); 
    end if;    
    select count(*) into nb_records from MANGUE.MANGUE_PARAMETRES where PARAM_KEY = 'FEV_EVALUATION_SAISIE_D_FIN';
    if (nb_records = 0) then
		INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
		    MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_EVALUATION_SAISIE_D_FIN', TO_CHAR(SYSDATE+90, 'DD/MM/YYYY'), 'Date de fin de saisie des evaluations par les N+1 (hors periode, les evaluations sont en lecture seule)'); 
    end if;    
    select count(*) into nb_records from MANGUE.MANGUE_PARAMETRES where PARAM_KEY = 'FEV_FICHE_LOLF_SAISIE_D_DEBUT';
    if (nb_records = 0) then
		INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
		    MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_FICHE_LOLF_SAISIE_D_DEBUT', TO_CHAR(SYSDATE-1, 'DD/MM/YYYY'), 'Date de debut de saisie des fiches LOLF par les responsables LOLF (hors periode, les fiches sont en lecture seule)'); 
    end if;    
    select count(*) into nb_records from MANGUE.MANGUE_PARAMETRES where PARAM_KEY = 'FEV_FICHE_LOLF_SAISIE_D_FIN';
    if (nb_records = 0) then
		INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
		    MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_FICHE_LOLF_SAISIE_D_FIN', TO_CHAR(SYSDATE+90, 'DD/MM/YYYY'), 'Date de fin de saisie des fiches LOLF par les responsables LOLF (hors periode, les fiches sont en lecture seule)'); 
    end if;    


	-- table FICHE_LOLF
	SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'FICHE_LOLF';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.CreateFicheLolf01;      
          EXECUTE IMMEDIATE MANGUE.CreateFicheLolf02;      
          EXECUTE IMMEDIATE MANGUE.CreateFicheLolf03;      
          EXECUTE IMMEDIATE MANGUE.CreateFicheLolf04;      
          EXECUTE IMMEDIATE MANGUE.CreateFicheLolf05;      
    END IF;
    
	-- table REPART_FLO_SILLAND
	SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'REPART_FLO_SILLAND';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilland01;      
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilland02;      
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilland03;      
    END IF;
    
    -- drop de la sequence REPART_FLO_SILLAND_SEQ
    SELECT count(*) INTO NB_RECORDS FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'REPART_FLO_SILLAND_SEQ';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.DropRepartFloSillandSeq;      
    END IF;
    
	-- table REPART_FLO_SILLAND
	SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'REPART_REP_FLO_SIL_LOLF_NOMEN';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilLolfNomen01;      
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilLolfNomen02;      
          EXECUTE IMMEDIATE MANGUE.CreateRepartFloSilLolfNomen03;      
    END IF;
    
    -- drop de la sequence REPART_FLO_SILLAND_SEQ
    SELECT count(*) INTO NB_RECORDS FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'REPART_FLO_SILLAND_SEQ';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.DropRepartFloLolfNomenSeq;      
    END IF;
    
    -- attribut HIERARCHIE.EPE_KEY
    SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'HIERARCHIE' AND COLUMN_NAME = 'EPE_KEY';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.AlterHierarchieAddEpeKey01;      
          EXECUTE IMMEDIATE MANGUE.AlterHierarchieAddEpeKey02;      
    END IF;

	-- attribut FEV_DROIT.C_STRUCTURE_COMPOSANTE
    SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'FEV_DROIT' AND COLUMN_NAME = 'DRO_C_STRUCTURE_COMPOSANTE';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.AlterFeveDroitDroCStructComp01;      
          EXECUTE IMMEDIATE MANGUE.AlterFeveDroitDroCStructComp02;      
    END IF;
    
	-- attribut FEV_DROIT.DRO_EPE_KEY
    SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'FEV_DROIT' AND COLUMN_NAME = 'DRO_EPE_KEY';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.AlterFeveDroitDroEpeKey01;      
          EXECUTE IMMEDIATE MANGUE.AlterFeveDroitDroEpeKey02;      
    END IF;
    
    -- suppression des objectifs "orphelins"
    delete from MANGUE.objectif where eva_key not in (select eva_key from evaluation);
    
	-- attribut OBJECTIF.OBJ_POSITION
    SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'OBJECTIF' AND COLUMN_NAME = 'OBJ_POSITION';
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE MANGUE.AlterObjectifAddObjPosition01;      
          EXECUTE IMMEDIATE MANGUE.AlterObjectifAddObjPosition02;      
    END IF;
END;
/

BEGIN
    MANGUE.SyncMangueFeve;
END;
/

DROP FUNCTION MANGUE.CreateFicheLolf01;
DROP FUNCTION MANGUE.CreateFicheLolf02;
DROP FUNCTION MANGUE.CreateFicheLolf03;
DROP FUNCTION MANGUE.CreateFicheLolf04;
DROP FUNCTION MANGUE.CreateFicheLolf05;

DROP FUNCTION MANGUE.CreateRepartFloSilland01;
DROP FUNCTION MANGUE.CreateRepartFloSilland02;
DROP FUNCTION MANGUE.CreateRepartFloSilland03;
DROP FUNCTION MANGUE.DropRepartFloSillandSeq;

DROP FUNCTION MANGUE.CreateRepartFloSilLolfNomen01;
DROP FUNCTION MANGUE.CreateRepartFloSilLolfNomen02;
DROP FUNCTION MANGUE.CreateRepartFloSilLolfNomen03;
DROP FUNCTION MANGUE.DropRepartFloLolfNomenSeq;

DROP FUNCTION MANGUE.AlterHierarchieAddEpeKey01;
DROP FUNCTION MANGUE.AlterHierarchieAddEpeKey02;

DROP FUNCTION MANGUE.AlterObjectifAddObjPosition01;
DROP FUNCTION MANGUE.AlterObjectifAddObjPosition02;

DROP FUNCTION MANGUE.AlterFeveDroitDroCStructComp01;     
DROP FUNCTION MANGUE.AlterFeveDroitDroCStructComp02;     

DROP FUNCTION MANGUE.AlterFeveDroitDroEpeKey01;     
DROP FUNCTION MANGUE.AlterFeveDroitDroEpeKey02;     
DROP PROCEDURE MANGUE.SyncMangueFeve;


--
-- 1.1.0.8 : positionner la procedure apres la synchronisation du user
-- pour eviter les erreurs de compilation si des tables manquent
--
CREATE OR REPLACE PROCEDURE MANGUE.Fev_Create_Poste_Enseignant IS
-- Procedure de création des postes d'enseignants liées à leur affectation
-- actuelles. Le code des postes générés sont préfixés de 2009 (à modifier
-- donc si besoin)
-- Une fiche LOLF générique est créée. A adapter / supprimer selon vos besoins. 
-- Auteur : CRI
-- creation : 13/01/2009
-- modification : 13/01/2009

-- recuperer les affectations actuelles des enseignants
CURSOR affectations IS
SELECT * FROM MANGUE.AFFECTATION
WHERE d_deb_affectation <= SYSDATE
AND (d_fin_affectation >= SYSDATE OR d_fin_affectation IS NULL)
and tem_valide = 'O'
and no_dossier_pers in (
    select no_dossier_pers from GRHUM.V_PERSONNEL_ACTUEL_ENS)
ORDER BY d_deb_affectation ASC;


aff_lgn MANGUE.AFFECTATION%ROWTYPE;

la_flo_key              MANGUE.FICHE_LOLF.FLO_KEY%TYPE;
la_pos_key              MANGUE.POSTE.POS_KEY%TYPE;
le_pos_code             MANGUE.POSTE.pos_code%TYPE;
le_pos_libelle         MANGUE.POSTE.pos_libelle%TYPE;
le_lc_structure         GRHUM.STRUCTURE_ULR.lc_structure%TYPE;
le_numero_poste         INTEGER;
nb_poste             INTEGER;
nb_poste_existant       INTEGER;
no_affectation         INTEGER;
le_numero_poste_str  VARCHAR2(4);

BEGIN
        
          no_affectation := 0;
      
         OPEN affectations;
          LOOP

             FETCH affectations INTO aff_lgn;
             EXIT WHEN affectations %NOTFOUND;

             -- on ne cree pas un poste si l'affectation a deja un poste associe
             select count(*) into nb_poste_existant from mangue.affectation_detail where no_seq_affectation = aff_lgn.no_seq_affectation;
             
             if (nb_poste_existant = 0) then

                 -- on compte le total d'MANGUE.AFFECTATION pour cet agent
                 no_affectation := no_affectation + 1;
             
                 -- recup sequence poste
                 SELECT MANGUE.POSTE_seq.NEXTVAL INTO la_pos_key FROM dual;

                 -- generation du code du poste (annee + lc_structure + '-' + numero)
                 SELECT lc_structure INTO le_lc_structure FROM GRHUM.STRUCTURE_ULR WHERE c_structure = aff_lgn.c_structure;

                 nb_poste := 0;

                 SELECT COUNT(*) INTO nb_poste FROM MANGUE.POSTE WHERE c_structure = aff_lgn.c_structure;

                   IF (nb_poste > 0) THEN
                     SELECT TO_NUMBER(MAX(SUBSTR(pos_code, INSTR(pos_code, '-') +1, LENGTH(pos_code)))) INTO le_numero_poste FROM MANGUE.POSTE WHERE c_structure = aff_lgn.c_structure;
                  ELSE
                     le_numero_poste := 0;
                 END IF;

                 le_numero_poste := le_numero_poste +1;

                 IF (le_numero_poste < 10) THEN
                     le_numero_poste_str := '000' || TO_CHAR(le_numero_poste);
                 ELSE
                        IF (le_numero_poste < 100) THEN
                              le_numero_poste_str := '00' || TO_CHAR(le_numero_poste);
                        ELSE
                                IF (le_numero_poste < 1000) THEN
                                  le_numero_poste_str := '0' || TO_CHAR(le_numero_poste);
                             ELSE
                                 le_numero_poste_str := TO_CHAR(le_numero_poste);
                             END IF;
                       END IF;
                 END IF;

                 --dbms_output.put_line(SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(le_lc_structure, '-', ''),' ',''), '.',''),'&',''),1,11) || ' et ' || le_numero_poste_str);

                 le_pos_code := '2009' || SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(le_lc_structure, '-', ''),' ',''), '.',''),'&',''),1,11) || '-' || le_numero_poste_str;


                 -- insertion poste
                 INSERT INTO MANGUE.POSTE(pos_key, pos_libelle, d_creation, d_modification, pos_code, pos_d_debut, pos_d_fin, c_structure) VALUES
                 (la_pos_key, 'Poste enseignant', SYSDATE, SYSDATE, le_pos_code, aff_lgn.d_deb_affectation,NULL, aff_lgn.c_structure);
                 
                 -- insertion fiche_lolf
                 SELECT MANGUE.FICHE_LOLF_SEQ.NEXTVAL INTO la_flo_key FROM DUAL;
                 INSERT INTO MANGUE.FICHE_LOLF(flo_key, pos_key, flo_d_debut, flo_d_fin, d_creation, d_modification, exe_ordre) values
                 (la_flo_key, la_pos_key, to_date('01/01/2009','dd/mm/yyyy'), to_date('31/12/2009','dd/mm/yyyy'), sysdate, sysdate, 2009); 
                 
                 -- remplissage de la fiche lolf
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (14, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (15, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 300, 2009, 60, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 301, 2009, 30, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 302, 2009, 10, sysdate, sysdate);
                 
                 

                 -- insertion MANGUE.AFFECTATION_DETAIL
                 INSERT INTO MANGUE.AFFECTATION_DETAIL(ade_key, pos_key, no_seq_affectation, ade_d_debut, ade_d_fin, d_creation, d_modification) VALUES
                 (MANGUE.affectation_detail_seq.NEXTVAL, la_pos_key, aff_lgn.no_seq_affectation, aff_lgn.d_deb_affectation, aff_lgn.d_fin_affectation, SYSDATE, SYSDATE);

            end if;

        END LOOP;
        CLOSE affectations;

END;
/


--
-- 1.1.0.11 procedure de mise a niveau des positions des
-- objectifs : inutile pour une nouvelle installation de la feve
--
CREATE OR REPLACE PROCEDURE MANGUE.reconstruire_position_objectif
-- initialisation des positions des objectifs
IS

-- Curseur de toutes les evaluations
CURSOR cur_eva IS SELECT * FROM mangue.evaluation;

-- curseur sur les objectifs
CURSOR cur_object(an_eva_key number) IS SELECT * FROM mangue.objectif 
where eva_key = an_eva_key;

lig_evaluation          evaluation%ROWTYPE;
lig_objectif            objectif%ROWTYPE;

max_pos number;

BEGIN
             
        -- 
        OPEN cur_eva;
         LOOP

            FETCH cur_eva into lig_evaluation;
            EXIT WHEN cur_eva%NOTFOUND;
                        
            -- les objectifs
              OPEN cur_object(lig_evaluation.eva_key);
              LOOP
        
                    FETCH cur_object into lig_objectif;
                    EXIT WHEN cur_object%NOTFOUND;
            
                    -- recuperer la valeur max des objectifs de cette evaluation
                    select max(obj_position) into max_pos from mangue.objectif where eva_key = lig_evaluation.eva_key;
            
                    -- incrementer 
                    update mangue.objectif set obj_position = max_pos+1 where obj_key = lig_objectif.obj_key;

              END LOOP;
              CLOSE cur_object;

        

        END LOOP;
        CLOSE cur_eva;
        
    
END;
/



--
-- les vues pour la PAF
-- 
CREATE OR REPLACE FORCE VIEW MANGUE.V_LOLF_AFFECTATION_INTERVALLE
(NO_DOSSIER_PERS, INTERVALLE_DEBUT, INTERVALLE_FIN, QUOTITE)
AS 
select t.no_dossier_pers, lesDatesDebut, lesDatesFin, sum(num_quot_affectation)
from
(
  select unique no_dossier_pers_t_mult no_dossier_pers, lesDates1 lesDatesDebut, min(lesDates2) lesDatesFin
  from
  (
    select unique t1.no_dossier_pers no_dossier_pers_t_mult, t1.lesDates1, t2.lesDates2
    from
    (
    select unique no_dossier_pers no_dossier_pers, d_deb_affectation lesDates1
    from   affectation
    union  
    select unique no_dossier_pers no_dossier_pers, d_fin_affectation lesDates1
    from   affectation
    ) t1,
    (
    select unique no_dossier_pers no_dossier_pers, d_deb_affectation lesDates2
    from   affectation
    union  
    select unique no_dossier_pers no_dossier_pers, nvl(d_fin_affectation,sysdate+1) lesDates2
    from   affectation 
    ) t2
    where t1.no_dossier_pers = t2.no_dossier_pers
  )
  where (lesDates1 < lesDates2)
  and   (lesDates2 - lesDates1) > 1
  and   no_dossier_pers_t_mult = no_dossier_pers_t_mult
  group by no_dossier_pers_t_mult, lesDates1
) t
, affectation a
where t.no_dossier_pers = a.no_dossier_pers 
and lesDatesDebut >= d_deb_affectation
and (
        (d_fin_affectation is not null and lesDatesFin <= d_fin_affectation)
        or
        (d_fin_affectation is null)
)
group by t.no_dossier_pers, lesDatesDebut, lesDatesFin;
/


CREATE OR REPLACE FORCE VIEW MANGUE.V_LOLF_DATE_AFFECTATION
(ADE_KEY, D_DEBUT, D_FIN)
AS 
select ade_key,
    decode(ad.ade_date_diff_affectation, 0, a.d_deb_affectation, 1, ad.ade_d_debut)  d_debut, 
    decode(ad.ade_date_diff_affectation, 0, a.d_fin_affectation, 1, ad.ade_d_fin)  d_fin
from affectation a, affectation_detail ad
where a.no_seq_affectation = ad.no_seq_affectation
and a.tem_valide = 'O';
/


CREATE OR REPLACE FORCE VIEW MANGUE.V_LOLF_INDIVIDU
(NO_INDIVIDU, LOLF_ID, QUOTITE, DATE_DEBUT, DATE_FIN, 
 NUM_QUOT_AFFECTATION)
AS 
select no_dossier_pers, lolf_id, rrf_quotite * rfs_quotite / 100,
    -- on prend la date maximum entre debut de fiche et debut d'affectation
    greatest(flo_d_debut, v.d_debut), 
    decode(flo_d_fin,
        -- si la date de fin de fiche est nulle, on regarde sur l'affectation 
        null, v.d_fin,
        -- sinon, on prend la date mininum entre date de fiche et date d'affectation
        decode(v.d_fin, null, flo_d_fin, least(flo_d_fin, v.d_fin))),
    num_quot_affectation 
from    poste p, fiche_lolf l, repart_flo_silland rs, repart_rep_flo_sil_lolf_nomen rl, 
        affectation a, affectation_detail ad, v_lolf_date_affectation v
WHERE   p.pos_key = l.pos_key
AND     l.flo_key = rs.flo_key
AND     rs.flo_key = rl.flo_key
AND     rs.sil_key = rl.sil_key
AND     p.pos_key = ad.pos_key
AND     a.no_seq_affectation  = ad.no_seq_affectation
and     v.ADE_KEY = ad.ade_key
and     a.tem_valide = 'O';
/


--
-- autres vues
--
CREATE OR REPLACE FORCE VIEW MANGUE.V_AFF_ETAB_PERS_NON_ENS
(NO_SEQ_AFFECTATION, NO_DOSSIER_PERS, NO_OCCUPATION, NO_SEQ_CARRIERE, NO_SEQ_CONTRAT, 
 C_STRUCTURE, D_DEB_AFFECTATION, D_FIN_AFFECTATION, NUM_QUOT_AFFECTATION, DEN_QUOT_AFFECTATION, 
 D_CREATION, D_MODIFICATION)
AS 
select unique a.NO_SEQ_AFFECTATION, a.NO_DOSSIER_PERS, a.NO_OCCUPATION, a.NO_SEQ_CARRIERE, a.NO_SEQ_CONTRAT, 
 a.C_STRUCTURE, a.D_DEB_AFFECTATION, a.D_FIN_AFFECTATION, a.NUM_QUOT_AFFECTATION, a.DEN_QUOT_AFFECTATION, 
 a.D_CREATION, a.D_MODIFICATION
from mangue.affectation a, grhum.v_personnel_non_ens v, grhum.repart_type_groupe rtg
where a.no_dossier_pers = v.no_dossier_pers
and a.c_structure = rtg.c_structure
and rtg.TGRP_CODE = 'S'
and (
    (a.d_deb_affectation <= v.d_debut and a.d_fin_affectation >= v.d_fin and a.d_fin_affectation is not null and v.d_fin is not null) or
    (a.d_deb_affectation >= v.d_debut and a.d_fin_affectation >= v.d_fin and a.d_fin_affectation is not null and v.d_fin is not null) or
    (a.d_deb_affectation <= v.d_debut and a.d_fin_affectation <= v.d_fin and a.d_fin_affectation is not null and v.d_fin is not null) or
    (a.d_deb_affectation >= v.d_debut and a.d_fin_affectation <= v.d_fin and a.d_fin_affectation is not null and v.d_fin is not null) or
    (a.d_fin_affectation >= v.d_debut and a.d_fin_affectation is not null and v.d_fin is null) or
    (a.d_deb_affectation <= v.d_fin and a.d_fin_affectation is null and v.d_fin is not null) or
    (a.d_fin_affectation is null and v.d_fin is null));
/


CREATE OR REPLACE FORCE VIEW MANGUE.V_CANDIDAT_EVALUATION
(NOM_USUEL, PRENOM, EPE_KEY, EVA_KEY, NO_INDIVIDU)
AS 
(select unique nom_usuel, prenom, e.epe_key, eva_key, i.no_individu
from grhum.individu_ulr i, mangue.evaluation e, mangue.v_aff_etab_pers_non_ens v,
     mangue.evaluation_periode p, mangue.hierarchie h
where i.no_individu = e.no_individu
and e.no_individu = v.no_dossier_pers
and e.epe_key = p.epe_key
and h.epe_key = p.epe_key
and h.no_individu = e.no_individu
and (
    (v.D_DEB_affectation >= p.epe_d_debut and v.D_DEB_affectation <= p.epe_d_fin) or
    (v.D_FIN_affectation >= p.epe_d_debut and v.D_FIN_affectation <= p.epe_d_fin) or
    (v.d_deb_affectation < p.epe_d_fin and (v.d_fin_affectation is null or v.d_fin_affectation >= p.epe_d_fin))))
union 
-- les evaluations manquantes
(select distinct nom_usuel, prenom, p.epe_key, null eva_key, i.no_individu
from grhum.individu_ulr i, mangue.v_aff_etab_pers_non_ens v, mangue.evaluation_periode p,
    mangue.hierarchie h
where i.no_individu not in( 
    select no_individu from mangue.evaluation where epe_key = p.epe_key)
and i.no_individu = h.no_individu
and p.epe_key = h.epe_key
and i.no_individu = v.no_dossier_pers
and (
    (v.D_DEB_affectation >= p.epe_d_debut and v.D_DEB_affectation <= p.epe_d_fin) or
    (v.D_FIN_affectation >= p.epe_d_debut and v.D_FIN_affectation <= p.epe_d_fin) or
    (v.d_deb_affectation < p.epe_d_fin and (v.d_fin_affectation is null or v.d_fin_affectation >= p.epe_d_fin)))
having (sum(nvl(d_fin_affectation, p.epe_d_fin)-d_deb_affectation) >= 240 or sum(d_fin_affectation-d_deb_affectation) is null)
group by nom_usuel, prenom, p.epe_key, i.no_individu)
order by epe_key, nom_usuel, prenom;
/




--
-- commentaires
--
COMMENT ON TABLE MANGUE.FICHE_LOLF IS 'Fiche LOLF : profil d''un poste par exercice comptable';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.FLO_KEY IS 'clé primaire';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.POS_KEY IS 'poste associée (table MANGUE.POSTE)';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.FLO_D_DEBUT IS 'NE PAS MODIFIER (date de début d''effet)';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.FLO_D_FIN IS 'NE PAS MODIFIER (date de fin d''effet)';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.D_MODIFICATION IS 'date de dernière modification de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.FICHE_LOLF.EXE_ORDRE IS 'exercice comptable (table JEFY_ADMIN.EXERCICE)';

COMMENT ON TABLE MANGUE.HIERARCHIE IS 'Arbre hierachique des evaluations. Est callé sur les périodes d''évaluation';
COMMENT ON COLUMN MANGUE.HIERARCHIE.HIE_KEY IS 'clé primaire';
COMMENT ON COLUMN MANGUE.HIERARCHIE.NO_INDIVIDU_RESP IS 'individu N+1';
COMMENT ON COLUMN MANGUE.HIERARCHIE.NO_INDIVIDU IS 'individu N-1';
COMMENT ON COLUMN MANGUE.HIERARCHIE.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.HIERARCHIE.D_MODIFICATION IS 'date de modification de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.HIERARCHIE.EPE_KEY IS 'période d''évaluation associée à ce noeud (table MANGUE.EVALUATION_PERIODE)';

COMMENT ON COLUMN MANGUE.OBJECTIF.OBJ_POSITION IS 'Position de l''objectif parmi les autres sur une même évaluation';

COMMENT ON TABLE MANGUE.REPART_FLO_SILLAND IS 'Affectation des fonctions SILLAND aux fiches LOLF';
COMMENT ON COLUMN MANGUE.REPART_FLO_SILLAND.FLO_KEY IS 'fiche LOLF (table MANGUE.FICHE_LOLF)';
COMMENT ON COLUMN MANGUE.REPART_FLO_SILLAND.SIL_KEY IS 'fonction SILLAND (table GRHUM.SILLAND)';
COMMENT ON COLUMN MANGUE.REPART_FLO_SILLAND.RFS_QUOTITE IS 'quotité d''affectation de la fonction';
COMMENT ON COLUMN MANGUE.REPART_FLO_SILLAND.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_FLO_SILLAND.D_MODIFICATION IS 'date de dernière modification de l''enregistrement (interne)';

COMMENT ON TABLE MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN IS 'Affectation des actions aux fonctions SILLAND associées à une fiche LOLF';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.SIL_KEY IS 'couple fiche LOLF/SILLAND clé 1 (table MANGUE.REPART_FLO_SILLAND)';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.FLO_KEY IS 'couple fiche LOLF/SILLAND clé 2 (table MANGUE.REPART_FLO_SILLAND)';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.LOLF_ID IS 'action associée (table JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE)';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.EXE_ORDRE IS 'exercice comptable (table JEFY_ADMIN.EXERCICE)';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.RRF_QUOTITE IS 'quotité d''affectation';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.D_CREATION IS 'date de création de l''enregistrement (interne)';
COMMENT ON COLUMN MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN.D_MODIFICATION IS 'date de dernière modification de l''enregistrement (interne)';



-- mise à jour du numéro de version de la base de données
INSERT INTO MANGUE.DB_VERSION VALUES(1306, '1.3.0.6', TO_DATE('20/07/2009', 'DD/MM/YYYY'),SYSDATE,'Base de donnees MANGUE v1.3.0.6');

COMMIT;