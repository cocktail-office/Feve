--
-- Patch GRHUM version 1.5.7.0 du 20/07/2009 à executer depuis le user GRHUM
--
--
-- PRE-REQUIS : Patch de GRHUM (1.5.6.0) du 23/06/2009
--
SET DEFINE OFF;


-- grants
grant references on GRHUM.SILLAND to mangue;
grant references on GRHUM.V_PERSONNEL_ACTUEL_NON_ENS to mangue;


--
-- Suppression de la clé primaire avec un nom incorrect 'PK_REPART_SILLAND_LOLF' sur une table à supprimer 'Z_OLD_REPART_SILLAND_LOLF'
--
CREATE OR REPLACE FUNCTION GRHUM.DropIndexPkOldRepartSillandLol RETURN VARCHAR2
AS 
BEGIN
	RETURN 'DROP INDEX PK_REPART_SILLAND_LOLF';
END;
/

--
-- Creation de la table REPART_SILLAND_LOLF
--
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf01 RETURN VARCHAR2
AS
BEGIN
    RETURN 'CREATE TABLE GRHUM.REPART_SILLAND_LOLF' ||  
        '(' || 
        'SIL_KEY NUMBER NOT NULL,' || 
        'LOLF_ID NUMBER NOT NULL,' || 
        'EXE_ORDRE NUMBER NOT NULL,' || 
        'D_CREATION DATE DEFAULT SYSDATE NOT NULL,' || 
        'D_MODIFICATION DATE DEFAULT SYSDATE NOT NULL' || 
        ')' || 
        ' LOGGING' || 
        ' NOCOMPRESS' || 
        ' NOCACHE' || 
        ' NOPARALLEL' || 
        ' NOMONITORING' ;
END;
/
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf02 RETURN VARCHAR2
AS
BEGIN
    RETURN 'CREATE UNIQUE INDEX GRHUM.PK_REPART_SILLAND_LOLF ON GRHUM.REPART_SILLAND_LOLF (SIL_KEY, LOLF_ID, EXE_ORDRE) LOGGING NOPARALLEL';
END;
/
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf03 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE GRHUM.REPART_SILLAND_LOLF ADD (CONSTRAINT PK_REPART_SILLAND_LOLF  PRIMARY KEY (SIL_KEY, LOLF_ID, EXE_ORDRE))'; 
END;
/
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf04 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE GRHUM.REPART_SILLAND_LOLF ADD (CONSTRAINT FK_REP_SIL_LOL_SILLAND FOREIGN KEY (SIL_KEY) REFERENCES GRHUM.SILLAND (SIL_KEY))';
END;
/
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf05 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE GRHUM.REPART_SILLAND_LOLF ADD (CONSTRAINT FK_REP_SIL_LOL_LOLF FOREIGN KEY (LOLF_ID) REFERENCES JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE (LOLF_ID))';
END;
/
CREATE OR REPLACE FUNCTION GRHUM.CreateRepartSillandLolf06 RETURN VARCHAR2
AS
BEGIN
    RETURN 'ALTER TABLE GRHUM.REPART_SILLAND_LOLF ADD (CONSTRAINT FK_REP_SIL_LOL_EXERCICE FOREIGN KEY (EXE_ORDRE) REFERENCES JEFY_ADMIN.EXERCICE (EXE_ORDRE))';
END;
/

--
-- Procedure de synchronisation globale du user GRHUM
--
CREATE OR REPLACE PROCEDURE GRHUM.SyncGrhumFeve
AS 
NB_RECORDS INTEGER;
BEGIN

	-- ajout des fct silland manquantes (recherche et enseignement)
    -- enseignement
    select count(*) into nb_records from grhum.silland where sil_key = 14;
    if (nb_records = 0) then
        INSERT INTO GRHUM.SILLAND ( SIL_KEY, SIL_LIBELLE, SIL_INDICATEUR, SIL_ENSEIGNANT, SIL_NON_ENSEIGNANT ) VALUES (14, 'enseignement', NULL, 'O', 'N');
    end if;    
    -- recherche
    select count(*) into nb_records from grhum.silland where sil_key = 15;
    if (nb_records = 0) then
        INSERT INTO GRHUM.SILLAND ( SIL_KEY, SIL_LIBELLE, SIL_INDICATEUR, SIL_ENSEIGNANT, SIL_NON_ENSEIGNANT ) VALUES (15, 'recherche', NULL, 'O', 'N');
    end if;

	-- schema
	
	-- clé primaire PK_REPART_SILLAND_LOLF sur l'ancienne table
	SELECT count(*) INTO NB_RECORDS FROM USER_INDEXES WHERE INDEX_NAME = 'PK_REPART_SILLAND_LOLF' AND TABLE_NAME = 'Z_OLD_REPART_SILLAND_LOLF';
	IF (nb_records = 1) THEN
          EXECUTE IMMEDIATE GRHUM.DropIndexPkOldRepartSillandLol;     
    END IF;
	
	-- table REPART_SILLAND_LOLF
    SELECT count(*) INTO NB_RECORDS FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'REPART_SILLAND_LOLF';    
    IF (nb_records = 0) THEN
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf01;      
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf02;      
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf03;      
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf04;      
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf05;      
          EXECUTE IMMEDIATE GRHUM.CreateRepartSillandLolf06;      
    END IF;
END;
/

BEGIN
    GRHUM.SyncGrhumFeve;
END;
/
       
DROP FUNCTION GRHUM.DropIndexPkOldRepartSillandLol;
DROP FUNCTION GRHUM.CreateRepartSillandLolf01;     
DROP FUNCTION GRHUM.CreateRepartSillandLolf02;     
DROP FUNCTION GRHUM.CreateRepartSillandLolf03;     
DROP FUNCTION GRHUM.CreateRepartSillandLolf04;     
DROP FUNCTION GRHUM.CreateRepartSillandLolf05;     
DROP FUNCTION GRHUM.CreateRepartSillandLolf06;     
DROP PROCEDURE GRHUM.SyncGrhumFeve;

-- commentaires
COMMENT ON TABLE GRHUM.REPART_SILLAND_LOLF IS 'Repartition des destinations lolf par fonction SILLAND et par exercice comptable'; 
COMMENT ON COLUMN GRHUM.REPART_SILLAND_LOLF.SIL_KEY IS 'Fonction SILLAND (table GRHUM.SILLAND)';
COMMENT ON COLUMN GRHUM.REPART_SILLAND_LOLF.LOLF_ID IS 'Destination (table JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE)'; 
COMMENT ON COLUMN GRHUM.REPART_SILLAND_LOLF.EXE_ORDRE IS 'Exercice (table JEFY_ADMIN.EXERCICE)'; 
COMMENT ON COLUMN GRHUM.REPART_SILLAND_LOLF.D_CREATION IS 'Date de création de l''enregistrement (interne)';
COMMENT ON COLUMN GRHUM.REPART_SILLAND_LOLF.D_MODIFICATION IS 'Date de dernière modification de l''enregistrement (interne)'; 

-- vues rassemblant l'ensemble des objets issus de REFERENS
CREATE OR REPLACE VIEW GRHUM.V_REFERENS
(KEY, LIBELLE, LIBELLE_SEUL, KEY_PERE, NIVEAU, 
 NUMDCP, NUMFP, CODEEMPLOI, ORDRE)
AS 
select '-1' key, '_racine' libelle, '_racine' libelle_seul, '-1' key_pere, 0 niveau,
         null numdcp , null numfp, null codeemploi, null ordre 
from dual
union
select d.NUMDCP key, (decode(d.NUMDCP,'BB','B','CC','C', d.LETTREBAP) || ' ' || d.INTITULDCP) libelle, d.INTITULDCP libelle_seul, '-1' key_pere, 1 niveau,
        d.NUMDCP numdcp , null numfp, null codeemploi, null ordre 
from grhum.referens_dcp d
union
select f.NUMFP||f.numdcp key, decode(f.INTITULFP, 'Autres', 'z__Autres', f.INTITULFP) libelle, f.INTITULFP libelle_seul,f.numdcp key_pere, 2 niveau,
        f.numdcp numdcp, f.numfp numfp, null codeemploi, null ordre 
from grhum.referens_fp f
union 
select e.CODEEMPLOI key, '['||e.siglecorps||'] '||e.INTITULEMPLOI libelle, e.INTITULEMPLOI libelle_seul, e.NUMFP||e.numdcp key_pere, 3 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, null ordre 
from grhum.referens_emplois e, grhum.referens_dcp d
where e.NUMDCP = d.numdcp
union 
select 'ReferensActivites-'||a.CODEEMPLOI||'-_-'||a.ORDRE key, a.INTITULACTIVITE libelle, a.INTITULACTIVITE libelle_seul, a.CODEEMPLOI key_pere, 4 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, a.ordre ordre 
from grhum.referens_activites a, grhum.referens_emplois e, grhum.referens_dcp d
where a.CODEEMPLOI = e.codeemploi
and   e.NUMDCP = d.numdcp
union 
select 'ReferensCompetences-'||c.CODEEMPLOI||'-_-'||c.ORDRE key, c.INTITULCOMP libelle, c.INTITULCOMP libelle_seul, c.CODEEMPLOI key_pere, 5 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, c.ordre ordre 
from grhum.referens_competences c, grhum.referens_emplois e, grhum.referens_dcp d
where c.CODEEMPLOI = e.codeemploi
and   e.NUMDCP = d.numdcp;


-- mise à jour du numéro de version de la base de données
INSERT INTO GRHUM.DB_VERSION VALUES(14, '1.5.7.0', TO_DATE('20/07/2009', 'DD/MM/YYYY'),SYSDATE,'Remise a niveau des nomenclatures de Feve');

COMMIT;