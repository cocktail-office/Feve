-- ajout des codes
ALTER TABLE MANGUE.TPL_BLOC ADD (TBL_CODE  VARCHAR2(10));

COMMENT ON COLUMN MANGUE.TPL_BLOC.TBL_CODE IS 'code du bloc (à ne pas modifier, c''est ce code qui est référencé dans les constantes des fichiers Java)';

UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'COMPPROTEC' WHERE TBL_KEY = 1;
UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'MANAGEMENT' WHERE TBL_KEY = 2;
UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'BILFORSUIV' WHERE TBL_KEY = 3;
UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'FORSOUHAIT' WHERE TBL_KEY = 4;
UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'CONACTSERV' WHERE TBL_KEY = 5;
UPDATE MANGUE.TPL_BLOC SET TBL_CODE = 'QUAPERSREL' WHERE TBL_KEY = 6;

ALTER TABLE MANGUE.TPL_BLOC MODIFY(TBL_CODE  NOT NULL);


ALTER TABLE MANGUE.TPL_FICHE ADD (TFI_CODE  VARCHAR2(10));

COMMENT ON COLUMN MANGUE.TPL_FICHE.TFI_CODE IS 'code de la fiche (à ne pas modifier, c''est ce code qui est référencé dans les constantes des fichiers Java)';

UPDATE MANGUE.TPL_FICHE SET TFI_CODE = 'FICHEDEPOS' WHERE TFI_KEY = 1;
UPDATE MANGUE.TPL_FICHE SET TFI_CODE = 'FICHELOLF' 	WHERE TFI_KEY = 2;
UPDATE MANGUE.TPL_FICHE SET TFI_CODE = 'EVALUATION' WHERE TFI_KEY = 3;

ALTER TABLE MANGUE.TPL_FICHE MODIFY(TFI_CODE  NOT NULL);


ALTER TABLE MANGUE.TPL_ITEM ADD (TIT_CODE  VARCHAR2(10));

COMMENT ON COLUMN MANGUE.TPL_ITEM.TIT_CODE IS 'code de l''item (à ne pas modifier, c''est ce code qui est référencé dans les constantes des fichiers Java)';

UPDATE MANGUE.TPL_ITEM SET TIT_CODE = 'LISFORMSOU' WHERE TIT_KEY = 32;


ALTER TABLE MANGUE.TPL_ONGLET ADD (TON_CODE  VARCHAR2(10));

COMMENT ON COLUMN MANGUE.TPL_ONGLET.TON_CODE IS 'code de l''onglet (à ne pas modifier, c''est ce code qui est référencé dans les constantes des fichiers Java)';

UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVAOBJPREC' WHERE TON_KEY = 1;
UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVAOBJSUIV' WHERE TON_KEY = 2;
UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVASITU' WHERE TON_KEY = 3;
UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVACOMP' WHERE TON_KEY = 4;
UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVAAPTI' WHERE TON_KEY = 5;
UPDATE MANGUE.TPL_ONGLET SET TON_CODE = 'EVAEVOL' WHERE TON_KEY = 6;

ALTER TABLE MANGUE.TPL_ONGLET MODIFY(TON_CODE  NOT NULL);


-- changements de libellés
UPDATE MANGUE.TPL_ONGLET SET TON_LIBELLE='Objectifs précédents' WHERE TON_CODE = 'EVAOBJPREC';
UPDATE MANGUE.TPL_ONGLET SET TON_LIBELLE='Objectifs suivants ' WHERE TON_CODE = 'EVAOBJSUIV';
UPDATE MANGUE.TPL_ONGLET SET TON_LIBELLE='Situation d''activités' WHERE TON_CODE = 'EVASITU';