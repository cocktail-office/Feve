-- date de visa de l'entretien par le responsable rh
ALTER TABLE MANGUE.EVALUATION ADD (D_VISA_RESPONSABLE_RH DATE);

COMMENT ON COLUMN MANGUE.EVALUATION.D_VISA_RESPONSABLE_RH IS 'Date de visa de l''entretien professionnel par un responsable RH';

-- viser toutes les évalutions à la date de fin de périodes (pour celles qui ont été visées)
UPDATE MANGUE.EVALUATION E
SET D_VISA_RESPONSABLE_RH =
    (SELECT EPE_D_FIN 
    FROM MANGUE.EVALUATION_PERIODE P
    WHERE E.EPE_KEY = P.EPE_KEY)
WHERE EVA_VISA_RESP = 'O';


-- date de tenue de l'entretien
ALTER TABLE MANGUE.EVALUATION ADD (D_TENUE_ENTRETIEN DATE);

COMMENT ON COLUMN MANGUE.EVALUATION.D_TENUE_ENTRETIEN IS 'Date à laquelle a effectivement lieu l''entrentien professionnel';

-- mettre toutes les dates de tenues d'entretien des anciennes
-- évaluations à la date de fin de la période
UPDATE MANGUE.EVALUATION E
SET D_TENUE_ENTRETIEN = 
    (SELECT EPE_D_FIN 
    FROM MANGUE.EVALUATION_PERIODE P
    WHERE E.EPE_KEY = P.EPE_KEY)
WHERE EPE_KEY IN 
	(SELECT EPE_KEY
	FROM MANGUE.EVALUATION_PERIODE
	WHERE EPE_D_FIN < SYSDATE);
	
	
-- date de validité sur les onglets
ALTER TABLE MANGUE.TPL_ONGLET ADD (D_DEB_VAL  DATE);
ALTER TABLE MANGUE.TPL_ONGLET ADD (D_FIN_VAL  DATE);

COMMENT ON COLUMN MANGUE.TPL_ONGLET.D_DEB_VAL IS 'Date de début de validité de l''onglet';
COMMENT ON COLUMN MANGUE.TPL_ONGLET.D_FIN_VAL IS 'Date de fin de validité de l''onglet';

-- mettre la date de début au début de la premiere période
UPDATE MANGUE.TPL_ONGLET SET D_DEB_VAL = 
    (SELECT MIN(EPE_D_DEBUT) 
    FROM MANGUE.EVALUATION_PERIODE P);	
	
-- date de validité sur les blocs
ALTER TABLE MANGUE.TPL_BLOC ADD (D_DEB_VAL  DATE);
ALTER TABLE MANGUE.TPL_BLOC ADD (D_FIN_VAL  DATE);

COMMENT ON COLUMN MANGUE.TPL_BLOC.D_DEB_VAL IS 'Date de début de validité du bloc';
COMMENT ON COLUMN MANGUE.TPL_BLOC.D_FIN_VAL IS 'Date de fin de validité du bloc';

-- mettre la date de début au début de la premiere période
UPDATE MANGUE.TPL_BLOC SET D_DEB_VAL = 
    (SELECT MIN(EPE_D_DEBUT) 
    FROM MANGUE.EVALUATION_PERIODE P);
