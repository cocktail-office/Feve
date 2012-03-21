CREATE OR REPLACE PROCEDURE MANGUE.Fev_Create_Poste_Enseignant IS
-- Procedure de création des postes d'enseignants liées à leur affectation
-- actuelles. Le code des postes générés sont préfixés de 2010 (à modifier
-- donc si besoin)
-- Une fiche LOLF générique est créée. A adapter / supprimer selon vos besoins. 
-- Auteur : CRI
-- creation : 13/01/2009
-- modification : 02/03/2010

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
nom_prenom				VARCHAR2(80);
  
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
                     SELECT TO_NUMBER(MAX(SUBSTR(pos_code, INSTR(pos_code, '-', -1) +1, LENGTH(pos_code)))) INTO le_numero_poste FROM MANGUE.POSTE WHERE c_structure = aff_lgn.c_structure;
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

                 le_pos_code := '2010' || SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(le_lc_structure, '-', ''),' ',''), '.',''),'&',''),1,11) || '-' || le_numero_poste_str;

                -- recuperation du nom du nom de l'occupant
                SELECT NOM_USUEL||' '||PRENOM INTO nom_prenom FROM GRHUM.INDIVIDU_ULR WHERE NO_INDIVIDU = aff_lgn.no_dossier_pers;

                
                 -- insertion poste
                 INSERT INTO MANGUE.POSTE(pos_key, pos_libelle, d_creation, d_modification, pos_code, pos_d_debut, pos_d_fin, c_structure) VALUES
                 (la_pos_key, 'Poste enseignant de '||nom_prenom|| ' (création automatique le '||to_char(sysdate, 'dd/mm/yyyy')||')', SYSDATE, SYSDATE, le_pos_code, aff_lgn.d_deb_affectation,NULL, aff_lgn.c_structure);
                 
                 -- insertion fiche_lolf
                 SELECT MANGUE.FICHE_LOLF_SEQ.NEXTVAL INTO la_flo_key FROM DUAL;
                 INSERT INTO MANGUE.FICHE_LOLF(flo_key, pos_key, flo_d_debut, flo_d_fin, d_creation, d_modification, exe_ordre) values
                 (la_flo_key, la_pos_key, to_date('01/01/2010','dd/mm/yyyy'), to_date('31/12/2010','dd/mm/yyyy'), sysdate, sysdate, 2010); 
                 
                 -- remplissage de la fiche lolf
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (14, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (15, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 300, 2010, 60, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 301, 2010, 30, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 302, 2010, 10, sysdate, sysdate);
                 
                 

                 -- insertion MANGUE.AFFECTATION_DETAIL
                 INSERT INTO MANGUE.AFFECTATION_DETAIL(ade_key, pos_key, no_seq_affectation, ade_d_debut, ade_d_fin, d_creation, d_modification) VALUES
                 (affectation_detail_seq.NEXTVAL, la_pos_key, aff_lgn.no_seq_affectation, aff_lgn.d_deb_affectation, aff_lgn.d_fin_affectation, SYSDATE, SYSDATE);

            end if;

        END LOOP;
        CLOSE affectations;


   

END;
/