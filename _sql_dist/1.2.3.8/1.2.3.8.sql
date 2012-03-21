
Insert into MANGUE.MANGUE_PARAMETRES
   (PARAM_ORDRE, PARAM_KEY, PARAM_VALUE, PARAM_COMMENTAIRES)
 Values
   (MANGUE.MANGUE_PARAMETRES_SEQ.NEXTVAL, 
   'FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION', 
   '180', 
   'Durée minimum cumulées des affectations d''un agent pour pouvoir être évalué sur une période (en jour)');


--
-- V_CANDIDAT_EVALUATION  (View) 
--
CREATE OR REPLACE FORCE VIEW mangue.v_candidat_evaluation (nom_usuel,
                                                           prenom,
                                                           epe_key,
                                                           eva_key,
                                                           no_individu
                                                          )
AS
   (SELECT UNIQUE nom_usuel, prenom, e.epe_key, eva_key, i.no_individu
             FROM grhum.individu_ulr i,
                  mangue.evaluation e,
                  mangue.v_aff_etab_pers_non_ens v,
                  mangue.evaluation_periode p,
                  mangue.hierarchie h
            WHERE i.no_individu = e.no_individu
              AND e.no_individu = v.no_dossier_pers
              AND e.epe_key = p.epe_key
              AND h.epe_key = p.epe_key
              AND h.no_individu = e.no_individu
              AND (   (    v.d_deb_affectation >= p.epe_d_debut
                       AND v.d_deb_affectation <= p.epe_d_fin
                      )
                   OR (    v.d_fin_affectation >= p.epe_d_debut
                       AND v.d_fin_affectation <= p.epe_d_fin
                      )
                   OR (    v.d_deb_affectation < p.epe_d_fin
                       AND (   v.d_fin_affectation IS NULL
                            OR v.d_fin_affectation >= p.epe_d_fin
                           )
                      )
                  ))
   UNION
   
-- les evaluations manquantes
   (SELECT DISTINCT nom_usuel, prenom, p.epe_key, NULL eva_key, i.no_individu
               FROM grhum.individu_ulr i,
                    mangue.v_aff_etab_pers_non_ens v,
                    mangue.evaluation_periode p,
                    mangue.hierarchie h
              WHERE i.no_individu NOT IN (SELECT no_individu
                                            FROM mangue.evaluation
                                           WHERE epe_key = p.epe_key)
                AND i.no_individu = h.no_individu
                AND p.epe_key = h.epe_key
                AND i.no_individu = v.no_dossier_pers
                AND (   (    v.d_deb_affectation >= p.epe_d_debut
                         AND v.d_deb_affectation <= p.epe_d_fin
                        )
                     OR (    v.d_fin_affectation >= p.epe_d_debut
                         AND v.d_fin_affectation <= p.epe_d_fin
                        )
                     OR (    v.d_deb_affectation < p.epe_d_fin
                         AND (   v.d_fin_affectation IS NULL
                              OR v.d_fin_affectation >= p.epe_d_fin
                             )
                        )
                    )
             HAVING (   SUM (  NVL (d_fin_affectation, p.epe_d_fin)
                             - d_deb_affectation
                            ) >=
                           (SELECT DECODE
                                      (nb,
                                       1, (SELECT TO_NUMBER (param_value)
                                             FROM mangue.mangue_parametres
                                            WHERE param_key =
                                                     'FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION'),
                                       180 /* valeur par defaut*/
                                      )
                              FROM (SELECT COUNT (*) nb
                                      FROM mangue.mangue_parametres
                                     WHERE param_key =
                                              'FEV_DUREE_MINIMUM_AFFECTATION_POUR_EVALUATION'))
                     OR SUM (d_fin_affectation - d_deb_affectation) IS NULL
                    )
           GROUP BY nom_usuel, prenom, p.epe_key, i.no_individu)
   ORDER BY epe_key, nom_usuel, prenom;

