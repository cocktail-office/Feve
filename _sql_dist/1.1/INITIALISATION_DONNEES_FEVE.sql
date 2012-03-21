CREATE OR REPLACE PROCEDURE Fev_Inserer_Hierarchie
(
  input_no_individu_resp INTEGER,
  input_no_individu INTEGER,
  input_epe_key INTEGER
)
-- sous procedure pour la creation de l'arbre hierarchique initial
-- Auteur : CRI/ULR
-- creation : 13/05/2009
-- modification : 13/05/2009
IS
  nb_doublons INTEGER;
  has_erreur INTEGER;
  code_erreur VARCHAR2(64);
BEGIN
	 has_erreur := 0;
	 -- verifier que l'individu n'est pas deja saisie comme n-1
	 SELECT COUNT(*) INTO nb_doublons FROM MANGUE.HIERARCHIE WHERE no_individu= input_no_individu and epe_key=input_epe_key;
	 IF (nb_doublons = 0) THEN
	 	-- verifier qu'il n'y a pas de boucle
		IF ((input_no_individu_resp IS NOT NULL AND input_no_individu_resp != input_no_individu) OR input_no_individu_resp IS NULL) THEN 
		   INSERT INTO MANGUE.HIERARCHIE(hie_key, no_individu_resp, no_individu, epe_key) VALUES (MANGUE.HIERARCHIE_seq.NEXTVAL, input_no_individu_resp, input_no_individu, input_epe_key);
        ELSE
		   has_erreur := 1;
		   code_erreur := 'boucle';
		END IF;
     ELSE
		has_erreur := 1;
		code_erreur := 'l individu n°'||input_no_individu||' existe deja en n-1';
	 END IF;
	 IF (has_erreur = 1) THEN
         dbms_output.put_line('Fev_Inserer_Hierarchie() : impossible d inserer input_no_individu_resp:' || input_no_individu_resp || ' input_no_individu:' || input_no_individu || ' (' || code_erreur || ')');
	 --ELSE
         --dbms_output.put_line('Fev_Inserer_Hierarchie() : insertion input_no_individu_resp:' || input_no_individu_resp || ' input_no_individu:' || input_no_individu);
	 END IF;
END;
/

CREATE OR REPLACE PROCEDURE Fev_Creer_Hierarchie
(
  c_structure_input grhum.STRUCTURE_ULR.c_structure%TYPE,
  is_racine INTEGER,
  in_epe_key integer
)
-- creation de l'arbre hierarchie initial
-- recupere et croise les informations de l'annuaire et de mangue
-- pour lancer la procedure, il faut indiquer le C_STRUCTURE de la racine
-- et le paramètre 1
--
-- exemple 
--
--	BEGIN
--		Fev_Creer_Hierarchie('1', 1, NULL);
-- 	END;
--
-- Auteur : CRI/ULR
-- creation : 13/05/2009
-- modification : 13/05/2009

IS
  -- les agents affectes de la structure
 	CURSOR affectes(le_c_structure NUMBER) IS
	SELECT * FROM MANGUE.AFFECTATION
	WHERE c_structure = le_c_structure
	and d_deb_affectation <= SYSDATE
	AND (d_fin_affectation >= SYSDATE OR d_fin_affectation IS NULL)
	and tem_valide = 'O'
	and no_dossier_pers in (
	    select no_dossier_pers from GRHUM.V_PERSONNEL_ACTUEL_NON_ENS)
	ORDER BY d_deb_affectation ASC;

  -- les structures filles
  CURSOR filles(le_c_structure NUMBER) IS
  SELECT * FROM grhum.v_service
  WHERE c_structure_pere = le_c_structure
  AND c_structure <> c_structure_pere;
  
  affectation_lgn mangue.affectation%ROWTYPE;
  filles_lgn grhum.v_service%ROWTYPE;
  no_individu_resp INTEGER;
  no_individu_resp_fille INTEGER; 
  no_individu_nm1  INTEGER;
  nb_membres INTEGER;
  nb_filles INTEGER;
  new_epe_key INTEGER;
  str_annee VARCHAR2(4);
  is_sup integer;
  date_debut_periode date;
  date_fin_periode date;
BEGIN


 --dbms_output.put_line('Fev_Creer_Hierarchie() : appel c_structure_input:' || c_structure_input ||' in_epe_key:' || in_epe_key);


	 -- recupere le grp_responsable de la structure
	 SELECT grp_responsable INTO no_individu_resp FROM grhum.STRUCTURE_ULR WHERE c_structure = c_structure_input;
	 
	 -- insertion du premier enregistrement specifique a la racine
	 IF (no_individu_resp IS NOT NULL) THEN
	 	IF (is_racine = 1) THEN
	 		-- on d'abord creer une periode
	 		SELECT MANGUE.EVALUATION_PERIODE_SEQ.NEXTVAL INTO new_epe_key FROM DUAL;
	 		SELECT to_char(SYSDATE,'yyyy') INTO str_annee from dual; 
	 		select decode (sign(sysdate - to_date('0109'||str_annee,'ddmmyyyy')), -1, 1, 0) into is_sup from dual;
	 		if (is_sup = 1) then
	 			date_debut_periode := to_date('0109'||str_annee,'ddmmyyyy');
	 			date_fin_periode := to_date('3108'||to_char(to_number(str_annee)+1),'ddmmyyyy');
	 		else
	 			date_debut_periode := to_date('0109'||to_char(to_number(str_annee)-1),'ddmmyyyy');
	 			date_fin_periode := to_date('3108'||str_annee,'ddmmyyyy');
	 		end if;
	 		INSERT INTO MANGUE.EVALUATION_PERIODE(EPE_KEY, EPE_D_DEBUT, EPE_D_FIN, D_CREATION, D_MODIFICATION) VALUES 
	 			(new_epe_key, date_debut_periode, date_fin_periode, sysdate, sysdate);
	 	   	MANGUE.FEV_inserer_hierarchie(NULL, no_individu_resp, new_epe_key);
		 ELSE 
		 	new_epe_key := in_epe_key;
		 END IF;
		 
		 
		 -- creation des enregistrement pour tous les membres
		 SELECT COUNT(*) INTO nb_membres 
		 	FROM mangue.affectation 
			WHERE c_structure = c_structure_input
			and d_deb_affectation <= SYSDATE
			AND (d_fin_affectation >= SYSDATE OR d_fin_affectation IS NULL)
			and tem_valide = 'O'
			and no_dossier_pers in (
		    select no_dossier_pers from GRHUM.V_PERSONNEL_ACTUEL_NON_ENS);
		
		 dbms_output.put_line(c_structure_input ||' >> ' ||nb_membres);
		 IF (nb_membres > 0) THEN
			 OPEN affectes(c_structure_input);
			 LOOP	 
			     FETCH affectes INTO affectation_lgn;
		 	 	 EXIT WHEN affectes%NOTFOUND;
			 	MANGUE.FEV_inserer_hierarchie(no_individu_resp, affectation_lgn.no_dossier_pers, new_epe_key);
		 	 END LOOP;
			 CLOSE affectes;		 
		 END IF;
		 	 
		 -- appel recursif pour toutes les structures filles
		 SELECT COUNT(*) INTO nb_filles FROM grhum.STRUCTURE_ULR WHERE c_structure_pere = c_structure_input;
		 IF (nb_filles > 0) THEN
			 OPEN filles(c_structure_input);
			 LOOP	 
			 	 FETCH filles INTO filles_lgn;
		 	 	 EXIT WHEN filles%NOTFOUND;
				 -- les responsables des structures filles sont sous la responsabilite du N+1
				 SELECT grp_responsable INTO no_individu_resp_fille FROM grhum.STRUCTURE_ULR WHERE c_structure = filles_lgn.c_structure;
		 		 IF (no_individu_resp_fille IS NOT NULL) THEN 
				    --dbms_output.put_line('Fev_Creer_Hierarchie() : appel no_individu_resp:' || no_individu_resp || ' no_individu_resp_fille:' || no_individu_resp_fille);
				 	MANGUE.FEV_inserer_hierarchie(no_individu_resp, no_individu_resp_fille, new_epe_key);	 			 
				 END IF;
				MANGUE. Fev_Creer_Hierarchie(filles_lgn.c_structure, 0, new_epe_key);
			 END LOOP;
			 CLOSE filles;		 
	     END IF;
	 END IF;
     
END;
/

CREATE OR REPLACE PROCEDURE MANGUE.Fev_Creer_Poste_Non_Enseignant IS
-- Procedure de création des postes non enseignants liées à leur affectation
-- actuelles. Le code des postes générés sont préfixés de l'exercice en cours
-- Une fiche de poste et une fiche LOLF générique sont créées.
--
-- Auteur : CRI/ULR
-- creation : 19/05/2009
-- modification : 19/05/2009

-- recuperer les affectations actuelles des enseignants
CURSOR affectations IS
SELECT * FROM MANGUE.AFFECTATION
WHERE d_deb_affectation <= SYSDATE
AND (d_fin_affectation >= SYSDATE OR d_fin_affectation IS NULL)
and tem_valide = 'O'
and no_dossier_pers in (
    select no_dossier_pers from GRHUM.V_PERSONNEL_ACTUEL_NON_ENS)
ORDER BY d_deb_affectation ASC;


aff_lgn MANGUE.AFFECTATION%ROWTYPE;

la_fdp_key				MANGUE.FICHE_DE_POSTE.FDP_KEY%TYPE;
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
str_annee VARCHAR2(4);
nom_prenom				VARCHAR2(80);
  
BEGIN
 
 		-- recuperation de l'exercice en cours       
        SELECT to_char(SYSDATE,'yyyy') INTO str_annee from dual; 
 
 
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

                le_pos_code := str_annee || SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(le_lc_structure, '-', ''),' ',''), '.',''),'&',''),1,11) || '-' || le_numero_poste_str;

				-- recuperation du nom du nom de l'occupant
				SELECT NOM_USUEL||' '||PRENOM INTO nom_prenom FROM GRHUM.INDIVIDU_ULR WHERE NO_INDIVIDU = aff_lgn.no_dossier_pers;

                 -- insertion poste
                 INSERT INTO MANGUE.POSTE(pos_key, pos_libelle, d_creation, d_modification, pos_code, pos_d_debut, pos_d_fin, c_structure) VALUES
                 (la_pos_key, 'Poste de '||nom_prenom, SYSDATE, SYSDATE, le_pos_code, aff_lgn.d_deb_affectation,NULL, aff_lgn.c_structure);

                 -- insertion fiche_de_poste
                 SELECT MANGUE.FICHE_DE_POSTE_SEQ.NEXTVAL INTO la_fdp_key FROM DUAL;
                 INSERT INTO MANGUE.FICHE_DE_POSTE(fdp_key, pos_key, ety_code, fdp_visa_agent, fdp_visa_resp, fdp_visa_direc, fdp_mission_poste, fdp_contexte_travail, d_creation, d_modification, fdp_d_debut, fdp_d_fin, codeemploi) values
                 (la_fdp_key, la_pos_key, null, 'N', 'N', 'N', null, null, SYSDATE, SYSDATE, aff_lgn.d_deb_affectation, null, null); 
                 
                 
                 -- insertion fiche_lolf
                 SELECT MANGUE.FICHE_LOLF_SEQ.NEXTVAL INTO la_flo_key FROM DUAL;
                 INSERT INTO MANGUE.FICHE_LOLF(flo_key, pos_key, flo_d_debut, flo_d_fin, d_creation, d_modification, exe_ordre) values
                 (la_flo_key, la_pos_key, to_date('01/01/'||str_annee,'dd/mm/yyyy'), to_date('31/12/'||str_annee,'dd/mm/yyyy'), sysdate, sysdate, 2009); 
                 

                 -- insertion MANGUE.AFFECTATION_DETAIL
                 INSERT INTO MANGUE.AFFECTATION_DETAIL(ade_key, pos_key, no_seq_affectation, ade_d_debut, ade_d_fin, d_creation, d_modification) VALUES
                 (affectation_detail_seq.NEXTVAL, la_pos_key, aff_lgn.no_seq_affectation, aff_lgn.d_deb_affectation, aff_lgn.d_fin_affectation, SYSDATE, SYSDATE);

            end if;

        END LOOP;
        CLOSE affectations;


   

END;
/

CREATE OR REPLACE PROCEDURE MANGUE.Fev_Creer_Poste_Enseignant IS
-- Procedure de création des postes d'enseignants liées à leur affectation
-- actuelles. Le code des postes générés sont préfixés de l'exercice en cours
-- Une fiche LOLF générique est créée. A adapter / supprimer selon vos besoins. 
--
-- Auteur : CRI/ULR
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
str_annee VARCHAR2(4);
  
BEGIN
 
 		-- recuperation de l'exercice en cours       
        SELECT to_char(SYSDATE,'yyyy') INTO str_annee from dual; 
 
 
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

                le_pos_code := str_annee || SUBSTR(REPLACE(REPLACE(REPLACE(REPLACE(le_lc_structure, '-', ''),' ',''), '.',''),'&',''),1,11) || '-' || le_numero_poste_str;


                 -- insertion poste
                 INSERT INTO MANGUE.POSTE(pos_key, pos_libelle, d_creation, d_modification, pos_code, pos_d_debut, pos_d_fin, c_structure) VALUES
                 (la_pos_key, 'Poste enseignant', SYSDATE, SYSDATE, le_pos_code, aff_lgn.d_deb_affectation,NULL, aff_lgn.c_structure);
                 
                 -- insertion fiche_lolf
                 SELECT MANGUE.FICHE_LOLF_SEQ.NEXTVAL INTO la_flo_key FROM DUAL;
                 INSERT INTO MANGUE.FICHE_LOLF(flo_key, pos_key, flo_d_debut, flo_d_fin, d_creation, d_modification, exe_ordre) values
                 (la_flo_key, la_pos_key, to_date('01/01/'||str_annee,'dd/mm/yyyy'), to_date('31/12/'||str_annee,'dd/mm/yyyy'), sysdate, sysdate, 2009); 
                 
                 -- remplissage de la fiche lolf
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (14, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_FLO_SILLAND(sil_key, flo_key, rfs_quotite, d_creation, d_modification) VALUES 
                 (15, la_flo_key, 50, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 300, to_number(str_annee), 60, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 301, to_number(str_annee), 30, sysdate, sysdate);
                 INSERT INTO MANGUE.REPART_REP_FLO_SIL_LOLF_NOMEN(sil_key, flo_key, lolf_id, exe_ordre, rrf_quotite, d_creation, d_modification) VALUES
                 (14, la_flo_key, 302, to_number(str_annee), 10, sysdate, sysdate);
                 
                 

                 -- insertion MANGUE.AFFECTATION_DETAIL
                 INSERT INTO MANGUE.AFFECTATION_DETAIL(ade_key, pos_key, no_seq_affectation, ade_d_debut, ade_d_fin, d_creation, d_modification) VALUES
                 (affectation_detail_seq.NEXTVAL, la_pos_key, aff_lgn.no_seq_affectation, aff_lgn.d_deb_affectation, aff_lgn.d_fin_affectation, SYSDATE, SYSDATE);

            end if;

        END LOOP;
        CLOSE affectations;


   

END;
/