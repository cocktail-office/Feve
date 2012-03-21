Insert into MANGUE.MANGUE_PARAMETRES
   (PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES)
 Values
   (MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'LIBELLE_CREATION_POSTE_VALEUR_PAR_DEFAUT', 'poste de ${toIndividu.nomUsuel} ${toIndividu.prenom}', 
   'Le nom par défaut d''un poste lors de sa création à partir de la sélection d''une affectation');

Insert into MANGUE.MANGUE_PARAMETRES
   (PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES)
 Values
   (MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 'FEV_EDITION_FICHE_DE_POSTE_DISPOSITION_ACT_COMP_REFERENS_VERTICAL', 'N', 
   'Présentation des 2 blocs activités et compétences REFERENS dans l''édition de la fiche de poste : "N"=l''un à côté de l''autre, "O"=l''un en dessous de l''autre');



COMMIT;
