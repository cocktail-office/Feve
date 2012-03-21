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
