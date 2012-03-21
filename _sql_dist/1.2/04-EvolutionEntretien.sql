-- RAZ séquence MANGUE.TPL_BLOC_NATURE_SEQ;
DROP SEQUENCE MANGUE.TPL_BLOC_NATURE_SEQ;


DECLARE 
	max_tbn_key NUMBER;
BEGIN

	SELECT MAX(TBN_KEY)+1
	INTO max_tbn_key 
	FROM MANGUE.TPL_BLOC_NATURE;
    
    IF (max_tbn_key is NULL) THEN
        max_tbn_key := 1;
    END IF;
 
	EXECUTE IMMEDIATE 'CREATE SEQUENCE MANGUE.TPL_BLOC_NATURE_SEQ NOCACHE START WITH '||max_tbn_key ;

END;
/

--
-- nouvelle nature de bloc : formation souhaitée
--
Insert into MANGUE.TPL_BLOC_NATURE
   (TBN_KEY, TBN_LIBELLE, D_CREATION, D_MODIFICATION)
 Values
   (MANGUE.TPL_BLOC_NATURE_SEQ.NEXTVAL, 'formation souhaitee', SYSDATE, SYSDATE);
   
   
   
-- RAZ séquence MANGUE.TPL_ONGLET_SEQ;
DROP SEQUENCE MANGUE.TPL_ONGLET_SEQ;


DECLARE 
	max_ton_key NUMBER;
BEGIN

	SELECT MAX(TON_KEY)+1
	INTO max_ton_key 
	FROM MANGUE.TPL_ONGLET;
    
    IF (max_ton_key is NULL) THEN
        max_ton_key := 1;
    END IF;
 
	EXECUTE IMMEDIATE 'CREATE SEQUENCE MANGUE.TPL_ONGLET_SEQ NOCACHE START WITH '||max_ton_key ;

END;
/

-- onglet bilan de l'année écoulée
Insert into MANGUE.TPL_ONGLET
   (TON_KEY, TON_LIBELLE, TON_COMMENTAIRE, D_CREATION, D_MODIFICATION, 
    TON_POSITION, TFI_KEY, TON_CODE, D_DEB_VAL, D_FIN_VAL)
 Values
   (MANGUE.TPL_ONGLET_SEQ.NEXTVAL, 'Bilan de l''année écoulée', 'Bilan de l''année écoulée et événements survenus', SYSDATE, SYSDATE, 3, 
   (SELECT TFI_KEY FROM MANGUE.TPL_FICHE WHERE TFI_CODE = 'EVALUATION'), 
   'BILANECOUL',
   NULL,
   NULL);


-- RAZ séquence MANGUE.TPL_BLOC_SEQ;
DROP SEQUENCE MANGUE.TPL_BLOC_SEQ;


DECLARE 
	max_tbl_key NUMBER;
BEGIN

	SELECT MAX(TBL_KEY)+1
	INTO max_tbl_key 
	FROM MANGUE.TPL_BLOC;
    
    IF (max_tbl_key is NULL) THEN
        max_tbl_key := 1;
    END IF;
 
	EXECUTE IMMEDIATE 'CREATE SEQUENCE MANGUE.TPL_BLOC_SEQ NOCACHE START WITH '||max_tbl_key ;

END;
/


-- nouveau bloc : formation souhaitée
Insert into MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE)
Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Formations souhaitées', 
   NULL, 
   (SELECT MAX(TBL_POSITION)+1 FROM MANGUE.TPL_BLOC WHERE TON_KEY = 
   		(SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'EVAEVOL')), 
   SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'EVAEVOL'),
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'formation souhaitee'),
   'FORSOUHAI2');
/


Insert into MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE)
 Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Bilan d''activité de la période écoulée', 
   'Bilan des activités récurrentes du poste (réussites, difficultés rencontrées,  solutions apportées, ...)', 
   1, SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'BILANECOUL'),
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'dynamique'),
   'BILACTPERE');
/

Insert into MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE)
 Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Evénements survenus', 
   'Événements survenus en cours de période écoulée et ayant ayant entrainé un impact sur l''activité (nouvelles orientations, réorganisations, nouvelles méthodes, nouveaux outils, ...)', 
   2, SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'BILANECOUL'), 
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'dynamique'), 
   'EVENTSUPER');


-- RAZ séquence MANGUE.TPL_ITEM_SEQ;
DROP SEQUENCE MANGUE.TPL_ITEM_SEQ;


DECLARE 
	max_tit_key NUMBER;
BEGIN

	SELECT MAX(TIT_KEY)+1
	INTO max_tit_key 
	FROM MANGUE.TPL_ITEM;
    
    IF (max_tit_key is NULL) THEN
        max_tit_key := 1;
    END IF;
 
	EXECUTE IMMEDIATE 'CREATE SEQUENCE MANGUE.TPL_ITEM_SEQ NOCACHE START WITH '||max_tit_key ;

END;
/

Insert into MANGUE.TPL_ITEM
   (TIT_KEY, TIT_LIBELLE, TIT_COMMENTAIRE, TIT_POSITION, TIN_KEY, TBL_KEY, D_CREATION, D_MODIFICATION, TIT_CODE)
 Values
   (MANGUE.TPL_ITEM_SEQ.NEXTVAL, 'Bilan d''activité de la période écoulée', NULL, 1, 
   (SELECT TIN_KEY FROM MANGUE.TPL_ITEM_NATURE WHERE TIN_LIBELLE = 'champ libre'), 
   (SELECT TBL_KEY FROM MANGUE.TPL_BLOC WHERE TBL_CODE = 'BILACTPERE'), SYSDATE, SYSDATE, 'BILACTPERB');


Insert into MANGUE.TPL_ITEM
   (TIT_KEY, TIT_LIBELLE, TIT_COMMENTAIRE, TIT_POSITION, TIN_KEY, TBL_KEY, D_CREATION, D_MODIFICATION, TIT_CODE)
 Values
   (MANGUE.TPL_ITEM_SEQ.NEXTVAL, 'Evénements survenus', NULL, 1, 
   (SELECT TIN_KEY FROM MANGUE.TPL_ITEM_NATURE WHERE TIN_LIBELLE = 'champ libre'), 
   (SELECT TBL_KEY FROM MANGUE.TPL_BLOC WHERE TBL_CODE = 'EVENTSUPER'), SYSDATE, SYSDATE, 'EVENTSUPEB');


--
-- onglet appréciation générale entre "aptitudes" et "évolutions"
--
Insert into MANGUE.TPL_ONGLET
   (TON_KEY, TON_LIBELLE, TON_COMMENTAIRE, D_CREATION, D_MODIFICATION, 
    TON_POSITION, TFI_KEY, TON_CODE, D_DEB_VAL, D_FIN_VAL)
 Values
   (MANGUE.TPL_ONGLET_SEQ.NEXTVAL, 'Appréciation générale', 
   'synthèse de la valeur professionnelle de l''agent détaillée dans par le positionnement des compétences et des aptitudes', SYSDATE, SYSDATE, 6, 
   (SELECT TFI_KEY FROM MANGUE.TPL_FICHE WHERE TFI_CODE = 'EVALUATION'),
   'SYNTVALPRO',
   NULL,
   NULL);
   
-- décalage des onglets suivants
UPDATE MANGUE.TPL_ONGLET SET TON_POSITION = TON_POSITION+1 
WHERE TON_POSITION > (
	SELECT TON_POSITION 
	FROM MANGUE.TPL_ONGLET
	WHERE TON_CODE = 'SYNTVALPRO');

Insert into MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE)
 Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Appréciation générale exprimant la valeur professionnelle de l''agent', null, 
   1, SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'SYNTVALPRO'),
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'dynamique'),
   'APGENVALAG');
/

Insert into MANGUE.TPL_ITEM
   (TIT_KEY, TIT_LIBELLE, TIT_COMMENTAIRE, TIT_POSITION, TIN_KEY, TBL_KEY, D_CREATION, D_MODIFICATION, TIT_CODE)
 Values
   (MANGUE.TPL_ITEM_SEQ.NEXTVAL, 
   'Vous pouvez vous référer au guide de l''entretien professionnel pour la rédaction de cette synthèse de la valeur professionnelle de l''agent', 
   NULL, 1, 
   (SELECT TIN_KEY FROM MANGUE.TPL_ITEM_NATURE WHERE TIN_LIBELLE = 'champ libre'), 
   (SELECT TBL_KEY FROM MANGUE.TPL_BLOC WHERE TBL_CODE = 'APGENVALAG'), SYSDATE, SYSDATE, 'APGENVALAB');
   
--
-- onglet agent en premiere position
--
Insert into MANGUE.TPL_ONGLET
   (TON_KEY, TON_LIBELLE, TON_COMMENTAIRE, D_CREATION, D_MODIFICATION, 
    TON_POSITION, TFI_KEY, TON_CODE, D_DEB_VAL, D_FIN_VAL)
 Values
   (MANGUE.TPL_ONGLET_SEQ.NEXTVAL, 'Agent', 
   'informations sur l''agent', SYSDATE, SYSDATE, 0, 
   (SELECT TFI_KEY FROM MANGUE.TPL_FICHE WHERE TFI_CODE = 'EVALUATION'),
   'EVAAGENT',
   (SELECT MIN(EPE_D_DEBUT) FROM MANGUE.EVALUATION_PERIODE),
   NULL);
   
-- décalage des onglets suivants
UPDATE MANGUE.TPL_ONGLET SET TON_POSITION = TON_POSITION + 1;
	
--
-- onglet fin en dernière position
--
Insert into MANGUE.TPL_ONGLET
   (TON_KEY, TON_LIBELLE, TON_COMMENTAIRE, D_CREATION, D_MODIFICATION, 
    TON_POSITION, TFI_KEY, TON_CODE, D_DEB_VAL, D_FIN_VAL)
 Values
   (MANGUE.TPL_ONGLET_SEQ.NEXTVAL, 'Finalisation', 
   'Actions disponibles en fin d''entretien', SYSDATE, SYSDATE, 
   (SELECT MAX(TON_POSITION)+1 FROM MANGUE.TPL_ONGLET), 
   (SELECT TFI_KEY FROM MANGUE.TPL_FICHE WHERE TFI_CODE = 'EVALUATION'),
   'EVAFIN',
   (SELECT MIN(EPE_D_DEBUT) FROM MANGUE.EVALUATION_PERIODE),
   NULL);
   

-- Bloc + item de modalités de recours

-- nouveau bloc : modalité de recours sur l'onglet "fin"
Insert into MANGUE.TPL_BLOC
   (TBL_KEY, TBL_LIBELLE, TBL_COMMENTAIRE, TBL_POSITION, D_CREATION, 
    D_MODIFICATION, TON_KEY, TBL_FACULTATIF, TBN_KEY, TBL_CODE)
Values
   (MANGUE.TPL_BLOC_SEQ.NEXTVAL, 'Modalités de recours', 
	NULL,
   (SELECT NVL(MAX(TBL_POSITION)+1,1) FROM MANGUE.TPL_BLOC WHERE TON_KEY = 
   		(SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'EVAFIN')), 
   SYSDATE, SYSDATE, 
   (SELECT TON_KEY FROM MANGUE.TPL_ONGLET WHERE TON_CODE = 'EVAFIN'),
   'N', 
   (SELECT TBN_KEY FROM MANGUE.TPL_BLOC_NATURE WHERE TBN_LIBELLE = 'dynamique'),
   'EVARECOURS');
/

-- RAZ sequence item nature
DROP SEQUENCE MANGUE.TPL_ITEM_NATURE_SEQ;

DECLARE 
	max_tin_key NUMBER;
BEGIN

	SELECT MAX(TIN_KEY)+1
	INTO max_tin_key 
	FROM MANGUE.TPL_ITEM_NATURE;
    
    IF (max_tin_key is NULL) THEN
        max_tin_key := 1;
    END IF;
 
	EXECUTE IMMEDIATE 'CREATE SEQUENCE MANGUE.TPL_ITEM_NATURE_SEQ NOCACHE START WITH '||max_tin_key ;

END;
/
   
--
-- nouvelle nature d'item : texte statique
--
Insert into MANGUE.TPL_ITEM_NATURE
   (TIN_KEY, TIN_LIBELLE, D_CREATION, D_MODIFICATION)
 Values
   (MANGUE.TPL_ITEM_NATURE_SEQ.NEXTVAL, 'texte statique', SYSDATE, SYSDATE);
/
   
-- nouvel item : modalité de recours dans le bloc recours
Insert into MANGUE.TPL_ITEM
   (TIT_KEY, TIT_LIBELLE, TIT_COMMENTAIRE, TIT_POSITION, TIN_KEY, TBL_KEY, D_CREATION, D_MODIFICATION, TIT_CODE)
 Values
   (MANGUE.TPL_ITEM_SEQ.NEXTVAL, 
   'Modalités de recours (article 6 du décret n°2007-1365)', 
   'L''autorité hiérarchique peut être saisie par le fonctionnaire d''une demande de révision du compte rendu de l''entretien professionnel.<br>Ce recours hiérarchique est exercé dans un délai de quinze jours francs suivant la notification à l''agent du compte rendu de ce dernier. L''autorité hiérarchique notifie sa réponse dans un délai de quinze jours francs après la demande de révision de l''entretien professionnel.<br>Les commissions administratives paritaires peuvent, à la requête de l''intéressé, sous réserve qu''il ait au préalable exercé le recours hiérarchique mentionné à l''alinéa précédent auprès de son autorité hiérarchique, demander à ce dernier la révision du compte rendu de l''entretien professionnel. Dans ce cas, communication doit être faite aux commissions de tous éléments utiles d''information.',
   1, 
   (SELECT TIN_KEY FROM MANGUE.TPL_ITEM_NATURE WHERE TIN_LIBELLE = 'texte statique'), 
   (SELECT TBL_KEY FROM MANGUE.TPL_BLOC WHERE TBL_CODE = 'EVARECOURS'), SYSDATE, SYSDATE, 'EVARECOURI');
/

--
-- renommage bloc "Management" en "Management et gestion de projets"
--
UPDATE MANGUE.TPL_BLOC SET TBL_LIBELLE = 'Management et gestion de projets' WHERE TBL_CODE = 'MANAGEMENT';

--
-- commentaire pour les formations pour se rapporter au guide
--
UPDATE MANGUE.TPL_BLOC
SET TBL_COMMENTAIRE = 'Se rapporter au guide de l''entretien professionnel pour saisir les formations suivies et souhaitées'
WHERE TBL_CODE IN ('BILFORSUIV', 'FORSOUHAIT', 'FORSOUHAI2');

--
-- renommage de l'onglet "Évolution" en "Évolutions professionnelles"
--
UPDATE MANGUE.TPL_ONGLET SET TON_LIBELLE = 'Évolutions professionnelles' WHERE TON_CODE = 'EVAEVOL';