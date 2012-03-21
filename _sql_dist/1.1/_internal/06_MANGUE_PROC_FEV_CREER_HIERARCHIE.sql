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
