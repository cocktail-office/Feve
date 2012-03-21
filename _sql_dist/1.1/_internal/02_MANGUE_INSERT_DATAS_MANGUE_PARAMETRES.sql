-- droits relatifs aux responsables de composantes sur les postes
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_CREATION_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de créer des postes dans la composante et ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_SUPPRESSION_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de supprimer des postes de la composante et de ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_MODIFICATION_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de modifier les méta-données (noms, code, dates d''ouverture et de fermeture) des postes de la composante et de ses sous-services (O/N)');

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_CONSULTATION_POSTE', 'O', 
'Les responsables de composante dans l''annuaire ont le droit de consulter les postes et leurs fiches de la composante et de ses sous-services (O/N)');

-- droits relatifs aux responsables de composantes sur les fiche de poste
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_CREATION_FICHE_DE_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de créer des fiches de poste pour les postes de la composante et de ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_DE_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de modifier le contenu et les dates de validité des fiches de poste des postes de la composante et de ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_SUPPRESSION_FICHE_DE_POSTE', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de supprimer des fiches de poste des postes de la composante et de ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_DE_POSTE', 'O', 
'Les responsables de composante dans l''annuaire ont le droit de consulter les fiches de poste des postes de la composante et de ses sous-services (O/N)'); 

-- droits relatifs aux responsables de services sur les postes
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_CREATION_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de créer des postes dans le service (O/N)');

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_SUPPRESSION_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de supprimer des postes du service (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_MODIFICATION_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de modifier les méta-données (noms, code, dates d''ouverture et de fermeture) des postes du service (O/N)');

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_CONSULTATION_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de consulter les postes et leurs fiches du service (O/N)');

-- droits relatifs aux responsables de services sur les fiche de poste
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_CREATION_FICHE_DE_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de créer des fiches de poste pour les postes du service (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_MODIFICATION_FICHE_DE_POSTE', 'N', 
'Les responsables de service dans l''annuaire ont le droit de modifier le contenu et les dates de validité des fiches de poste des postes du service (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_SUPPRESSION_FICHE_DE_POSTE', 'N', 
'Les responsables de service dans l''annuaire ont le droit de supprimer des fiches de poste des postes du service (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_CONSULTATION_FICHE_DE_POSTE', 'O', 
'Les responsables de service dans l''annuaire ont le droit de consulter les fiches de poste des postes du service (O/N)'); 

-- droits relatifs aux responsables de composantes sur les fiche LOLF
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_MODIFICATION_FICHE_LOLF', 'N', 
'Les responsables de composante dans l''annuaire ont le droit de modifier le contenu des fiches LOLF des postes de la composante et de ses sous-services (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_COMPOSANTE_CONSULTATION_FICHE_LOLF', 'O', 
'Les responsables de composante dans l''annuaire ont le droit de consulter les fiches LOLF des postes de la composante et de ses sous-services (O/N)'); 

-- droits relatifs aux responsables de services sur les fiche LOLF
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_MODIFICATION_FICHE_LOLF', 'O', 
'Les responsables de service dans l''annuaire ont le droit de modifier le contenu des fiches LOLF des postes du service (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_ANNU_SERVICE_CONSULTATION_FICHE_LOLF', 'O', 
'Les responsables de service dans l''annuaire ont le droit de consulter les fiches LOLF des postes du service (O/N)'); 

-- droit relatifs aux N+1 de l'arbre hierarchique
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_1_CREATION_EVALUATION', 'O', 
'Le responsable hiérarchique N+1 définit dans l''arbre a le droit de créer l''évaluation (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_1_MODIFICATION_EVALUATION', 'O', 
'Le responsable hiérarchique N+1 définit dans l''arbre a le droit de modifier le contenu de l''évaluation (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_1_CONSULTATION_EVALUATION', 'O', 
'Le responsable hiérarchique N+1 définit dans l''arbre a le droit de consulter le contenu de l''évaluation (O/N)'); 

-- droit relatifs aux N+p (p>1) de l'arbre hierarchique
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_P_CREATION_EVALUATION', 'N', 
'Le responsable hiérarchique N+p définit dans l''arbre a le droit de créer l''évaluation (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_P_MODIFICATION_EVALUATION', 'N', 
'Le responsable hiérarchique N+p définit dans l''arbre a le droit de modifier le contenu de l''évaluation (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_HIE_N_PLUS_P_CONSULTATION_EVALUATION', 'O', 
'Le responsable hiérarchique N+p définit dans l''arbre a le droit de consulter le contenu de l''évaluation (O/N)'); 

-- droit relatifs a la personne connectee sur ses propres donnees / fiches
INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_SELF_CONSULTATION_FICHE_DE_POSTE', 'O', 
'La personnne connectée peut consulter le contenu de ses fiches de postes (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_SELF_CONSULTATION_FICHE_LOLF', 'O', 
'La personnne connectée peut consulter le contenu de ses fiches LOLF (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_SELF_CONSULTATION_EVALUATION', 'O', 
'La personnne connectée peut consulter le contenu de ses comptes rendu d''évaluation (O/N)'); 

INSERT INTO MANGUE.MANGUE_PARAMETRES ( PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES ) VALUES ( 
MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_SELF_CONSULTATION_POSTE', 'O', 
'La personnne connectée peut consulter le détail des autres occupants de son poste (O/N)'); 

