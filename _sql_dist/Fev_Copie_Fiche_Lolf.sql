CREATE OR REPLACE PROCEDURE MANGUE.Fev_Copie_Fiche_Lolf 
    (exercice_source number, exercice_destination number) IS
-- Recopie des fiches LOLF d'un exercice vers un autre.
-- Seules les destinations LOLF en vigueur sur les 2 exercices sont copiées.
--
-- ATTENTION, cette procédure ne recopie pas la configuration globale des
-- associations SILLAND - LOLF définies dans la table 
-- GRHUM.REPART_SILLAND_LOLF 
--
-- Auteur : CRI
-- creation : 02/03/2010
-- modification : 02/03/2010

-- les fiches lolf
CURSOR fiche_lolf_cur IS
SELECT * FROM MANGUE.FICHE_LOLF
WHERE EXE_ORDRE = exercice_source;

-- l'association au fct silland
CURSOR repart_silland_cur(my_flo_key number) IS
SELECT * FROM MANGUE.REPART_FLO_SILLAND
WHERE FLO_KEY = my_flo_key;

-- l'association aux destinations LOLF
CURSOR repart_lolf_cur(my_flo_key number, my_exe_ordre number, my_sil_key number) IS
SELECT * FROM MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN
WHERE FLO_KEY = my_flo_key
AND EXE_ORDRE = my_exe_ordre
AND SIL_KEY = my_sil_key;  

fiche_lolf_lgn MANGUE.FICHE_LOLF%ROWTYPE;
repart_silland_lgn MANGUE.REPART_FLO_SILLAND%ROWTYPE;
repart_lolf_lgn MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN%ROWTYPE;

la_flo_key number;
total number;

BEGIN
        
    OPEN fiche_lolf_cur;
    LOOP

         FETCH fiche_lolf_cur INTO fiche_lolf_lgn;
         EXIT WHEN fiche_lolf_cur %NOTFOUND;

             -- insertion fiche_lolf
             SELECT MANGUE.FICHE_LOLF_SEQ.NEXTVAL INTO la_flo_key FROM DUAL;
             
             INSERT INTO MANGUE.FICHE_LOLF(flo_key, pos_key, flo_d_debut, flo_d_fin, d_creation, d_modification, exe_ordre) values
             (la_flo_key, 
                fiche_lolf_lgn.pos_key, 
                to_date('01/01/'||to_char(exercice_destination),'dd/mm/yyyy'), 
                to_date('31/12/'||to_char(exercice_destination),'dd/mm/yyyy'), 
                sysdate, 
                sysdate, 
                exercice_destination); 
                
                
                OPEN repart_silland_cur(fiche_lolf_lgn.flo_key);
                LOOP
                
                     FETCH repart_silland_cur INTO repart_silland_lgn;
                     EXIT WHEN repart_silland_cur%NOTFOUND;
                
                
                         INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                         (repart_silland_lgn.sil_key, 
                            la_flo_key,
                            repart_silland_lgn.rfs_quotite,
                            sysdate,
                            sysdate);
                            
                                    OPEN repart_lolf_cur(repart_silland_lgn.flo_key, exercice_source, repart_silland_lgn.sil_key);
                                    LOOP
                
                                         FETCH repart_lolf_cur INTO repart_lolf_lgn;
                                         EXIT WHEN repart_lolf_cur%NOTFOUND;
                                         
                                         -- test si la destination est valide sur l'exercice de destination
                                         SELECT COUNT(*) into total
                                         FROM JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE
                                         WHERE LOLF_ID = repart_lolf_lgn.lolf_id
                                         AND LOLF_OUVERTURE <= to_date('31/12'||to_char(exercice_destination), 'dd/mm/yyyy')
                                         AND (LOLF_FERMETURE >= to_date('01/01'||to_char(exercice_destination), 'dd/mm/yyyy') OR LOLF_FERMETURE IS NULL);
                                         
                                         
                                         if (total > 0) THEN
                                         
                                            INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                                                 (repart_lolf_lgn.sil_key, 
                                                 la_flo_key, 
                                                 repart_lolf_lgn.lolf_id, 
                                                 exercice_destination,
                                                 repart_lolf_lgn.rrf_quotite, 
                                                 sysdate, 
                                                 sysdate);
                                         end if;
                
                                    END LOOP;
                                    CLOSE repart_lolf_cur;
                 
    
                END LOOP;
                CLOSE repart_silland_cur;
                 
             
    END LOOP;
    CLOSE fiche_lolf_cur;


   

END;
/