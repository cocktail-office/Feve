SET DEFINE OFF;

CREATE TABLE GRHUM.ZLAGAF_ACTION
(
  NO_ACTION          NUMBER,
  ACTION             VARCHAR2(80),
  DOMAINE            VARCHAR2(50),
  CONTENU            VARCHAR2(4000),
  OBJECTIFS          VARCHAR2(4000),
  PUBLICS            VARCHAR2(4000),
  JOURS              NUMBER,
  TOTAL_H            NUMBER,
  EFFECTIF_MIN       NUMBER,
  EFFECTIF_MAX       NUMBER,
  MODE_VALIDATION    VARCHAR2(255),
  DS_CATALOGUE       VARCHAR2(1),
  REMUNERATION       NUMBER,
  FRAIS_FCT          NUMBER,
  FRAIS_MISSION      NUMBER,
  COUT_STAGIAIRE     NUMBER,
  INDIQUER_COUT      VARCHAR2(1),
  ACTION_INTERNE     VARCHAR2(1),
  DERNIER_N_SESSION  VARCHAR2(50),
  THEME_HS           VARCHAR2(50),
  ARCHIVAGE          VARCHAR2(1)
)
TABLESPACE DATA_GRHUM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;



CREATE TABLE GRHUM.ZLAGAF_DOMAINE
(
  DOMAINE   VARCHAR2(255),
  THEME     VARCHAR2(255),
  V603      VARCHAR2(1),
  OBSOLETE  VARCHAR2(1)
)
TABLESPACE DATA_GRHUM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;



CREATE TABLE GRHUM.ZLAGAF_THEME
(
  THEME       VARCHAR2(255),
  TYPE_FORM1  VARCHAR2(50)
)
TABLESPACE DATA_GRHUM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;



CREATE TABLE GRHUM.ZLAGAF_THEME_HS
(
  THEME  VARCHAR2(255)
)
TABLESPACE DATA_GRHUM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX GRHUM.ZLAGAF_ACTION_PK ON GRHUM.ZLAGAF_ACTION
(NO_ACTION)
LOGGING
TABLESPACE DATA_GRHUM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE GRHUM.ZLAGAF_ACTION ADD (
  CONSTRAINT ZLAGAF_ACTION_PK
 PRIMARY KEY
 (NO_ACTION)
    USING INDEX 
    TABLESPACE DATA_GRHUM
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));


Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Accueil', 'Techniques administratives et bureautique', 'N', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Accueil des publics', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Achats publics', 'Achats publics', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Adaptation à l¿emploi', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'O');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Administration et institutions', 'Accueil - sensibilisation à l''environnement professionnel', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Applications de gestion', 'Informatique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Bibliothéconomie', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Bilan de compétences', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Bureautique', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Colloques, congrès, conférences', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Communication', 'Service aux usagers', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Développement durable', 'Développement durable', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Développement personnel', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Documentation', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Environnement culturel', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Environnement professionnel', 'Accueil - sensibilisation à l''environnement professionnel', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Europe', 'Europe', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Expérimentation animale', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Expression écrite ou orale', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Formation de formateurs', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Formations juridiques', 'Techniques juridiques', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Formations juridiques et économiques', 'Techniques juridiques', 'N', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Formations scientifiques', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion de l¿étudiant', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion des ressources humaines', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion du patrimoine', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion du personnel', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion et comptabilité', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Gestion et suivi des politiques publiques', 'Gestion et suivi des politiques publiques', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Hygiène et sécurité', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Insertion professionnelle', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Langages et bases de données', 'Informatique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Langues étrangères', 'Formations linguistiques', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Lecture publique', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Maintenance des équipements et installations', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Management', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Méthodologie', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Organisation du travail', 'Management - Gestion des Ressources Humaines', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux autres diplômes', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux concours', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux concours (cat. A)', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux concours (cat. B)', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux concours (cat. C)', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Préparation aux diplômes nationaux', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Secrétariat', 'Techniques administratives et bureautique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Séminaires pour informaticiens', 'Informatique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Systèmes et réseaux', 'Informatique', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Techniques de recherche', 'Formations techniques spécifiques aux missions de chaque ministère', 'O', 'N');
Insert into GRHUM.ZLAGAF_DOMAINE
   (DOMAINE, THEME, V603, OBSOLETE)
 Values
   ('Technologies d¿information et de communication', 'Informatique', 'O', 'N');



Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Accueil - sensibilisation à l''environnement professionnel', 'Thème 01');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Achats publics', 'Thème 03');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Développement durable', 'Thème 10');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Europe', 'Thème 09');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Formations linguistiques', 'Thème 08');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Formations techniques spécifiques aux missions de chaque ministère', 'Thème 11');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Gestion et suivi des politiques publiques', 'Thème 04');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Informatique', 'Thème 07');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Management - Gestion des Ressources Humaines', 'Thème 02');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Service aux usagers', 'Thème 12');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Techniques administratives et bureautique', 'Thème 06');
Insert into GRHUM.ZLAGAF_THEME
   (THEME, TYPE_FORM1)
 Values
   ('Techniques juridiques', 'Thème 05');



Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('172. Acteurs de la prévention');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('177. Conduites à risques');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('182. Entretien des locaux');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('187. Ergonomie des postes de travail');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('192. Gestes et postures');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('197. Gestion des déchets');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('202. Incendie');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('207. Intervention entreprises extérieures');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('212. Machines, équipements travail');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('217. Premiers secours');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('222. Risque électrique');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('227. Risque en laboratoire');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('232. Risque poussière amiante');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('237. Risque majeur');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('242. Risque routier');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('247. Risques psychosociaux');
Insert into GRHUM.ZLAGAF_THEME_HS
   (THEME)
 Values
   ('999. Autre');
   
   
  
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (141, 'Prévention des risques professionnels et responsabilités juridiques', 'Hygiène et sécurité', 'L''hygiène et la sécurité au travail, la réparation des dommages, la responsabilité pénale, échange et réactions, présentation de quelques cas de jurisprudence, obligations de chacun en matière de sécurité.', 1, 6, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (142, 'Serre-file - Organisation sécurité incendie - évacuation', 'Hygiène et sécurité', 'Procédure d''évacuation du public', 1, 6, 'N', 0, 0, 0, 0, 'N', 'O', '172. Acteurs de la prévention', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (143, 'Perfectionnement en spectrométrie d''émission plasma ICP', 'Technologies d¿information et de communication', 'Rappels de statistiques sur l''écart-type et la moyenne-définition des caractéristiques analytiques, justesse, fidélité, limites de détection, robustesse-paramètres de fonctionnement du plasma, description et influence-optimisation, procédure et tests-rôle', 'Mieux comprendre le rôle des paramètres de fonctionnement du plasma afin d''optimiser la qualité des résultats analytiques. Améliorer les résultats par étalonnage interne et optimiser la procédure des droites d''étalonnage. Vérifier le bon fonctionnement de', 'Chercheurs et techniciens de laboratoires utilisant la technique I.C.P et ayant suivi stage de base', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (144, 'Formation du personnel utilisant une chaudière à vapeur de puissance électrique', 'Hygiène et sécurité', 'Rappel sur la vapeur, équipements, traitement de l''eau, risques et avaries, attitudes de l''intervenant. Application pratique : défauts, mise en sécurité, mise en service, vérification à faire et analyses des risques.', 'Etre capable de reconnaître l''ensemble des matériels technologiques de l''installation, de comprendre le fonctionnement de l''installation vapeur, d''apprécier les risques et définir les règles de sécurité.', 'Technicien et Enseignants', 1, 6, 4, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (145, 'Préparation à l''habilitation du personnel pour conduite d''autoclaves', 'Hygiène et sécurité', 'Propriétés de la vapeur d''eau, appareils de mesure de pression et de température, différents types d''appareils à pression de vapeur, appareils à couvercle amovible et réglementation des appareils  à couvercle amovible. Consignes d''exploitation et de sécur', 'Etre capable de conduire des autoclaves en respectant les consignes de sécurité, les règles d''exploitation et les procédures de contrôle, comprendre les différents principes de fonctionnement de ces appareils.', 'Conducteurs d''autoclaves et utilisateurs de stérilisateurs. Agents de maintenance.', 1.5, 9, 10, 'A l''issue de l''application pratique, un avis sur l''habilitation du stagiaire est adressé à l''employeur.', 'N', 39.61, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (146, 'Préparation concours interne d''adjoint administratif', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (147, 'Préparation concours interne SASU', 'Préparation aux concours (cat. B)', 6, 36, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (148, 'Quatorzièmes rencontres nationales : l''évaluation des risques', 'Hygiène et sécurité', 'Réglementation, principes généraux, acteurs de l''évaluation des risques au sein des ets, mise en œuvre de l''évaluation des risques, premières réalisations concrètes et retour d''expériences.', 'Prévention des risques professionnels.', 'Ingénieurs', 2, 12, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (149, 'Perfectionnement JAVA & XML', 'Langages et bases de données', 'Introduction à bibliothèque Java, quelques modèles de conception réutilisables, programmation d''interfaces graphiques avec Swing, programmation d''interfaces Web, programmation concurrente avec les threads, le traitement de documents XML avec Java.', 'Permettre au développeur de mieux connaître les classes de la bibliothèque Java (interface utilisateur, accès aux bases de données, programmation concurrente) et donner des notions sur le traitement de documents XML (bibliothèques disponible, DTD)', 'Toute personne étant amenée à développer des applications informatiques. Groupe de 12 personnes maxi', 3, 18, 8, 12, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (150, 'Lithopréparateur (Méthode théorique et nouvelles techniques)', 'Environnement professionnel', 'Présentation du labo d''accueil et des nouveaux techniciens, constitution d''une base théorique des protocoles de fabrication standard des lames minces de roches, présentation des réactions  physico-chimiques des différentes résines pendant l''imprégnation d', 'Constitution d''une base théorique des méthodes de fabrication de lames minces pour les nouveaux techniciens. Mise en œuvre de nouveau protocole et de nouvelle technique liés aux appareillages scientifiques. Comprendre les réactions physico-chimiques liées', 'Lithopréparateur (litholameleurs et lithobroyeurs)', 2, 12, 20, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (151, 'Perfectionnement EXCEL : liste de données-tableaux croisés', 'Bureautique', 'Créer, modifier, consulter une liste de données EXCEL, créer des extractions avec filtre automatique, avec rédaction de critères de sélection, utiliser ces extractions dans un tableau, créer des tableaux croisés à partir d''une liste de données.', 'Utiliser une liste de données EXCEL, extraire des données, créer des tableaux croisés de ces données.', 'Personnels déjà utilisateurs de EXCEL.', 1, 6, 24, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (152, 'Utilisation des services INTERNET/INTRANET', 'Technologies d¿information et de communication', 'INTERNET EXPLORER : définir les termes, présentation de produits existants, utiliser les service WEB, utilisation d''un service de transfert de dossier. UTILISATION DES SERVICES INTERNET/INTRANET : définir les termes, présentation de produits existants,', 'Savoir utiliser les techniques d''information et de communication avec le logiciel Netscape Communicator et Internet Explorer.', 'Personnels ATOS et d''encadrement. 
PRE-REQUIS : ne pas avoir déjà suivi une formation au courrier é', 2, 12, 240, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (153, 'Réunion du bureau du réseau lithopréparateurs', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (154, 'Journée d''info.des respons. services de gestion pers. des ets d''enseignemt sup', 'Environnement professionnel', 'Gestion des enseignants associés, dispositif des congés pour recherches ou conversions thématiques, bonification d''ancienneté au profit des chefs d''ets, le décret du 24 octobre 2002 concernant les recrutements dans le cadre de l''espace européen, situation', 'Réunion d''information et de travail entre les gestionnaires des éts d''enseignement supérieur et la direction des personnels enseignants.', 'Responsables des services de gestion des personnels des éts d''enseignement supérieur.', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (155, 'C.A.P.I. Certificat d''Animateur Propriété Industrielle', 'Formations juridiques', 'Présentation du cursus, panorama général de la propriété industrielle, droit de la propriété intellectuelle, droit de propriété intellectuelle, management de projet innovation et propriété industrielle, les outils documentaires, gestion de portefeuille pr', 'Permettre aux animateurs propriété industrielle : d''assurer un rôle d''aide à la décision et d''orientation vis à vis des créateurs d''entreprises, PME, services R&D, universités, chefs de laboratoires et chercheurs.., ainsi qu''un rôle d''interface vers les e', 'Partenaires du développement économique et technologique, chargés de mission, d''affaires, conseiller', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (2, 'Formation membres CHS et correspondants locaux HS', 'Hygiène et sécurité', 'Structures et fonctionnels e  hygiene et sécurité, risques physiques, l''animation sécurité, obligations règlementaires, risques chimiques en labo', 'connaissances de base en hygiène et sécurité, l''aptitude à déceler et à mesurer les risques professionnels, politique de prévention de l''ULR,', 'Membres CHS et correspondants locaux HS', 6, 38.75, 10, 50, 'N', 18.22, 10.72, 22.31, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (4, 'WORD - Perfectionnement', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (6, 'EXCEL - Perfectionnement', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (7, 'Excel -  conception d''un application', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (11, 'Un an de Droit public', 'Formations juridiques', 'Proposer un  bilan systématique des principales évolutions législatives, réglementaires ou jurisprudentielles, françaises ou européennes, intervenues endroit public et affectant l''action administrative.', 'Responsables des administrations centrales et des services déconcentrés de l''état', 5, 30, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (14, 'Prévention du risque incendie', 'Hygiène et sécurité', 10, 60, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (18, 'Adaptation à l''emploi des personnels AUC', 'Environnement professionnel', 5, 30, 'N', 10.63, 0, 1.07, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (19, 'Initiation au langage JAVA', 'Langages et bases de données', 'Introduction à JAVA, syntaxe élémentaire des types, des expressions et des instructions. Concepts de base de la programmation objet, tableaux, outils de développement et quelques notions sur la bibliothèque JAVA.', 'Offrir les connaissances élémentaires de la programmation objet et la maîtrise de leur expression avec le langage JAVA.', 'Toute personne étant amenée à developper des applications informatiques.', 3, 18, 10, 12, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (28, 'Windows 95 initiation', 'Bureautique', 0.5, 3, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (32, 'Electronique initiation niv 2', 'Environnement professionnel', 'Concours de la BAP 3', 3, 18, 'N', 5.31, 0, 2.63, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (33, 'Organiser son service', 'Management', 'Repartir et organiser le travail efficacement et harmonieusement, optimiser un circuit transverse, une procédure, initier et animer un projet entre plusieurs services, améliorer la qualité de service, organiser et mettre en place une évaluation ¿', 'Permettre à un cadre d''organiser ou de réorganiser son service à l''aide des outils de base à l''organisation du travail.', 'Personnel d''encadrement.', 3, 18, 1, 'N', 9.21, 0, 1.82, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (35, 'Animer une équipe de travail', 'Management', 'La communication, la circulation de l''information, le positionnement de cadre. Le rôle du cadre : organiser, déléguer.', 'Savoir se positionner comme cadre, apprendre à communiquer avec l''équipe, à faire circuler l''info, améliorer la connaissance de soi, réussir à appréhender les phénomènes de groupe, connaître les principaux enjeux du management des hommes.', 'Personnel d''encadrement.', 6, 37.5, 1, 1, 'O', 19.8, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (45, 'AGAD réservé ASU', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (47, 'ASTRF', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (49, 'AGTRF', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (52, 'ASI', 'Préparation aux concours (cat. A)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (54, 'Préparer sa retraite', 'Développement personnel', 'Différents régimes de retraite, pension civile et militaire, retraite du régime générale sécurité sociale, retraites complémentaires, cessation progressive d''activité, congé de fin d''activité, protection sociale la mutuelle, la transmission du patrimoine', 'Apporter des éléments de réponse aux multiples questions que se posent les agents à l''approche de la retraite, afin de les aider à aborder cette période dans des conditions favorisant leur épanouissement personnel.', 'Personnels agés de 55 à 65 ans', 3, 18, 1, 1, 'N', 8.15, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (57, 'Adaptation à l''emploi respon. De  Formation Continue des Personnels IATOS', 'Environnement professionnel', 'Textes réglementaires, instances intéressant la formation, l''enquête fonction publique/le logiciel LAGAF, le projet d''éts/le contrat/le plan de formation, le budget formation continue, les réseaux régionaux, les partenaires, l''élaboration du plan de forma', 'Ce stage organisé par la sous-direction de la formation des personnels - bureau DPATE D2 - vise à permettre aux personnes prenant leurs fonctions, de disposer d''éléments méthodologiques pour l''élaboration d''un plan de formation des personnels, de mieux co', 'Responsables de formation des personnels nouvellement nommés sur leurs fonctions.', 3, 18, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (60, 'Business Object', 'Bureautique', 2, 12, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (65, 'Prise de parole en Anglais', 'Langues étrangères', 'Personnes étant en contact avec des universités et des étudiants étrangers.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (70, 'Manipulation extincteurs et évacuation', 'Hygiène et sécurité', 'La prévention et la diminution des risques incendie, l''extincteur et la protection incendie, les divers foyers d''incendie, les divers agents extincteurs et un feu, pourquoi ? (triangle du feux).', 'Manipulation extincteur', 'Personnes à désigner parmi le personnel le plus exposé à faire face à un début d''incendie ou souhait', 0.5, 2.5, 15, 15, 'N', 13.09, 0, 0, 0, 'N', 'O', '202. Incendie', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (82, 'Agent réservé ITRF', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (86, 'Gestion déchets', 'Hygiène et sécurité', 'Approche légale et réglementaire, typologie des déchets, mise en place d''une gestion globale des déchets.', 'N', 0, 0, 3.08, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (88, 'Equipier de seconde intervention et d''évacuation', 'Hygiène et sécurité', 'Incendie en milieu industriel, partie pratique', '1. Repérer un risque incendie, organiser une intervention, intervenir efficacement sur un début d''incendie, 
    mettre en oeuvre les moyens de secours de l''établissement. 
2. Organiser et diriger l''évacuation des personnes.', 'Personnel désigné de l''Université', 2.5, 18, 12, 14, 'N', 58.48, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (89, 'PREVENTION DES RISQUES DANS L''UTILISTATION DE MATERIELS BIOLOGIQUES', 'Hygiène et sécurité', '- Pouvoir identifier et évaluer les risques d''une manipulation
- Développer la capacité à sécuriser les méthodes et techniques de travail
- Connaître les obligation réglementaires y afférant', 1.5, 10.3, 19, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (90, 'Statut de la fonction publique', 'Gestion des ressources humaines', 'La carrière du fonctionnaire et, plus particulièrement : les positions, l''avancement et les congés.', 'Cette formation a pour but de préciser ou rappeler les grandes règles de gestion des agents de la fonction publique d''Etat dans l''optique de la saisie des carrières agents de l''Univ.de la Rochelle dans la nouvelle base de données du personnel.', 1, 6, 11, 11, 'N', 5.61, 0, 4.39, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (92, 'Stage d''adaptation au premier emploi en Universite', 'Environnement professionnel', 'Organisation de l''Enseignemt Sup et de la Recherche, Enseignement Sup dans la Nation et en Europe, ressources humaines à l''Univ, gestion financière de l''Univ,  organisation de la Recherche dans l''Enseignemt Sup, l''Univ dans la cité.', 'Mieux connaître l''Enseignement supérieur et l''environnement professionnel', 'Toute personne catégorie A ou B nouvellement nommée à l''université', 'N', 10.63, 0, 1.07, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (93, 'Adaptation au premier emploi', 'Environnement professionnel', 'Mieux connaître l''Enseignement supérieur et l''environnement professionnel', 'Toute personne catégorie A ou B nouvellement nommée à l''Université', 5, 30, 2, 2, 'N', 17.71, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (96, 'CEGAPE', 'Gestion des ressources humaines', 'Organiser un service, assurance chômage, ouverture des droits, calcul des droits, la reprise, la réadmission,  fonctionnaires en disponibilité, activité réduite, allocation formation reclassement, coordination régime public et privé, études cas pratiques.', 'Connaître les mécanismes de la réglementation et savoir les appliquer.
Optimiser votre organisation et respecter toutes les formalités.
Eviter les litiges avec les assurés et réduire votre budget chômage.', 3, 18, 4, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (97, 'Litholaméleurs Lithobroyeurs', 'Environnement professionnel', 'Présentation du labo de l''univ d''accueil, et des participants. Echanges de pratiques et de méthodes sur des thèmes particuliers, organisation du travail en atelier. Restitution des débats en groupe, réflexions sur les missions et orga du travail ¿.', 'Organiser des échanges et des partages de pratiques sur un métier particulier, dans un but de formation et de constitution de réseaux d''échanges de savoir. Réunion annuelle de travail sur la mise en place du réseau lithopréparateur¿', 'Litholaméleurs et lithobroyeurs (BAP 7)', 2, 12, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (98, 'Fonctionnalités avancées de la Bibliothèque JAVA', 'Langages et bases de données', 'Introduction à bibliothèque Java, quelques modèles de conception réutilisables et mise en œuvre avec Java. Programmation d''interfaces graphiques avec Swing, programmation réseau, programmation concurrente par avec les threads, traitement de documents XML', 'Permettre au développeur de mieux connaître les classes de la bibliothèque JAVA (interface utilisateur, accès aux bases de données, entrées-sorties) et donner quelques notions sur le traitement de documents XML (bibliothèques disponibless, DTD)', 'Toute personne étant amenée à developper des applications informatiques.', 12, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (99, 'Réseaux de l''Enseignement supérieur et de la recherche', 'Systèmes et réseaux', 'Théorie : fonctionnalités d''un réseau voix-données-images, modèles, supports physiques, protocoles de transport, de nommage et d''administration, éléments actifs associés, haut débit, qualité de service, convergence voix-données-image, sécurité des réseaux', 'Décrire les principes de fonctionnement des réseaux dans le contexte de l''enseignement supérieur et de la recherche et présenter les évolutions prévisibles de ceux-ci.', 'Personne en charge d''une partie ou de la totalité d''un réseau ou souhaitant postuler à ces fonctions', 5, 30, 16, 16, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (100, 'Savoir communiquer - Savoir gérer les conflits au travail', 'Développement personnel', 'Les "règles du jeu" au sein d''une équipe, positions de vie et affirmation de soi, types de personnalités et source de motivation, styles de management, les bases de la négociation, méthode score de résolution de problèmes, gestion du stress, signes de', 'Savoir prévenir et traiter les difficultés relationnelles au sein d''une équipe de travail. Comprendre l''origine du conflit et mettre en œuvre des stratégies de motivation et de négociations. Gérer les relations dans un cadre gagnant-gagnant, dans le respe', 'Personnels de catégories A et B responsable d''une équipe de travail.', 3, 18, 24, 'PRE-REQUIS : avoir des notions sur les principes nécessaires à "l''encadrement du personnel".', 'O', 0, 0, 1.95, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (101, 'Nouveau code des marchés publics', 'Environnement professionnel', 'Le nouveau code des marchés publics, la nomenclature de fournitures et de services, le calcul des seuils.', 1, 6, 2, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (103, 'Habilitation électrique HOV-B1-BR(ELB012)', 'Hygiène et sécurité', 'Evaluation des risques, incidence sur le comportement et règles de sécurité, personnes intervenantes, domaines de tension et zones à risque électrique, consignation et travaux hors tension en BT, travaux au voisinage de pièces nues sous tension en BT,', 'Etre capable d''exécuter en sécurité des opérations spécifiques sur les installations et équipements électriques basse tension dans le respect des prescriptions de la publication UTE C 18 510.', 'Personnel électricien chargé d''assurer des travaux sur des ouvrages électriques basse tension', 2.5, 17.3, 5, 5, 'A l''issue de l''application pratique, un avis est adressé à l''employeur pour l''aider à définir le contenu du titre d''habilitation.', 'N', 58.23, 0, 0, 0, 'N', 'N', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (104, 'Les Congés pour raison de santé dans l''Education Nationale', 'Gestion des ressources humaines', 'Rappel des règles communes aux divers congés pour maladie, situation du fonctionnaire à l''issue des congés de maladie, incidence sur le traitement du fonctionnaire des congés pour raison de santé, accidents de trajet et de travail, étude de cas.', 'Maîtriser la réglementation des congés de maladie afin de mettre en œuvre l''ensemble des procédures liées aux divers congés pour maladie.', 'Service du Personnel IATOS, Enseignant et de bibliothèque, responsables administratifs de composante', 2, 12, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (105, 'Gestion des chargés d''enseignements et des heures complémentaires', 'Gestion des ressources humaines', 'Les bénéficiaires des contrats de charges d''enseignement, les conditions de recrutement, les prérogatives professionnelles, le mode de rémunération, les primes et indemnités spécifiques, les autorisations de cumul concernant les heures complémentaires,', 'Approfondir les connaissances des personnels chargés de la gestion des chargés d''enseignement et des heures complémentaire.', 'Service du personnel IATOS, Enseignant et de bibliothèque, responsables administratifs de composante', 1, 6, 1, 'O', 1.16, 0, 4.65, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (106, 'Léonardo et les actions de Socrates autres qu''Erasmus', 'Environnement professionnel', 'La mobilité étudiante et des personnels, les projets de partenariats, les projets de coopération, les réseaux transnationaux, thématiques et Comenius 3.', 'Formation et échange d''expériences.', 1, 6, 1, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (107, 'Lithopréparateur', 'Environnement professionnel', 'Activités en atelier : imprégnation sous vide, préparation des échantillons, polissage. Présentation succincte, par les opérateurs des divers appareils d''analyse utilisés dans le domaine de géologie pour montrer la nécessité d''un excellent polissage des l', 3, 18, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (108, 'LAGAF V6', 'Applications de gestion', 1, 6, 2, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (109, 'Contentieux Administratif Général', 'Formations juridiques', 'Juridictions administratives, organisation, procédure et efficacité. Relations entre l''administration et les citoyens et les précontentieux. La légalité interne et les dégrés de contrôle du juge, la légalité externe. Contentieux de la
fonction publique,', 'Présenter les juridictions administratives puis de couvrir les grands principes du contentieux administratif avec l''étude des principaux moyens pouvant être soulevés à l''appui d''une requête, l''étude des questions faisant l''objet d''un contentieux particuli', 'Fonctionnaires de l''Etat, des collectivités territoriales et des différents établissements publics,', 5, 30, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (110, 'Les relations internationales : enjeu décisif pour les Universités de la CEE', 'Environnement professionnel', 'Place des RI dans les # Univ d''Europe, comment se définit une politique des RI ? Initiatives individuelles et rôle de l''institution, mode de fonctionnement des services de RI dans la communauté européenne, accueil des enseignements et des étudiants dans l', 'Professionnaliser les personnels des services de relations internationales. Permettre une meilleure connaissance de l''environnement européen. Sensibiliser les personnels aux méthodes de travail des partenaires universitaires européens. Creér un réseau d''e', 'Expérience d''au moins 2 ans dans le domaine des RI, avoir 1bonne connaissance des programmes europée', 5, 30, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (111, 'Administration serveur LINUX (SAMBA) pour client windows', 'Systèmes et réseaux', 'Installation du système, gestion des utilisateurs, installation des services Internet, gestion et configuration du service Samba, installation et utilisation de X-Window, gestion de la sécurité du système, administration système.', 'Offrir à tout technicien ou ingénieur les éléments de base pour administrer un serveur Linux ainsi que les services Internet de base.', 'Toute personne en charge d''un serveur informatique (en particulier pour les applications Internet).', 4, 24, 2, 2, 'N', 14.18, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (112, 'Pratique de la gestion des ressources humaines', 'Gestion des ressources humaines', 'Analyse de la situation actuelle, sens et enjeux d''une bonne évaluation, aspects psychologiques de l''entretien, pré-entretien, entretien professionnel, gestion de l''entretien conflictuel, management et gestion des ressources humaines.', 'Former les chefs de services administratifs à la conduite d''un entretien professionnel, préparer les agents plus
spécifiquement à l''analyse de leur travail, de façon à ce qu''ils abordent l''entretien d''évaluation de façon positive et constructive,', 'Présidents et vice-présidents chargé des ressources humaines, directeurs ou responsables', 4, 24, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (113, 'Gestion constructive des conflits - initiation', 'Management', 'Une démarche imaginative : perception du malaise, analyse du milieu, définition des buts à atteindre, recherche de solutions (différentes issues d''un conflit). Moyens pédagogiques : auto-diagnostic, mises en situation suivie d''analyses, apports théoriques', 'Apprendre à gérer les conflits e amenant les participants à adopter une démarche constructive.', 'Personnel d''encadrement.', 2, 12, 1, 'O', 6.6, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (114, 'Les ateliers de l''enseignement supérieur - 3ème session', 'Environnement professionnel', 'Présentation du dossier de candidature des projets de mobilité Léornado, l''expérience d''un porteur de projet de moblilité Léonardo, projets de coopération inter-universitaires dans Erasmus, l''expérience d''un coordonnateur de PROG et IP', '3ème SESSION : conseil en montage de dossiers - PROG-MOD-IP-Projets de mobilité Léonardo.', 1, 6, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (115, 'Concevoir et développer sa politique d''achat public', 'Environnement professionnel', 'Présentation des objectifs et de la nouvelle réglementation de l''achat public, les enjeux de l''analyse prévisionnelle des besoins, l''organisation de la fonction achats, la spécificité des achats de matériel scientifique et informatique.', 'Sensibiliser les décideurs des ets d''enseignement supérieur aux modifications introduites par la nouvelle réglementation mais également de susciter une réflexion sur les opportunités et les moyens à mettre en œuvre pour s''engager ds une politique prévisio', 'Présidents et Directeurs d''éts, Vice-Présidents, Secrétaires généraux, Directeurs de composantes', 1, 6, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (116, 'Formation aux premiers secours', 'Hygiène et sécurité', 'La protection, l''alerte, la victime s''étouffe, la victime saigne abondamment, la victime est inconsciente et respire, la victime est inconsciente et ne respire plus, la victime se plaint d''un malaise, la victime se plaint après un traumatisme.', 'Code du travail : dans tous les services où sont effectués des travaux dangereux, un ou plusieurs agents doivent avoir reçu obligatoirement l''instruction nécessaire pour donner les premiers secours en cas d''urgence.', 2, 12, 9, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (117, 'Postes et postures - Travail sur écran', 'Hygiène et sécurité', 'Notion d''anatomie : la colonne vertébrale.Principe de base de manutention. Travail sur écran et santé. Vision, troubles musculo-squelettiques, stress. Aménagement du poste de travail,  implantation du poste de travail, bilan.', 'Modalité d''utilisation de l''écran et de l''équipement dans lequelcet écran est intégré.', 'Personnels administratifs, techniques et enseignants.', 1, 6, 15, 'N', 0, 0, 0, 0, 'N', 'O', '192. Gestes et postures', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (118, 'La mise en place des ECTS', 'Environnement professionnel', 'Donner les éléments techniques sur la création de l''espace européen de l''enseignement, la réforme des bachelors/masters et la mise en œuvre du système d''accumulation de crédits. Comparer l''état d''avancement de la mise en place des ECTS au niveau national', 'Secrétaires généraux, vices-présidents, directeurs d''UFR, resp. des services scolarité, des services', 2, 14, 20, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MAX, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (121, 'Comment lire la presse', 'Environnement professionnel', 'Les différentes sources de l''information : agences de presse sur différents supports. La presse écrite : analyse comparative du traitement de l''information dans différents journaux (textes et photos de presse).', 'Connaître les sources de l''information, son traitement dans la presse écrite et permettre une lecture critique.', 2, 12, 20, 'Personnels ATOS.', 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (122, 'Windows 95/98 - Approfondissement et sauvegardes', 'Bureautique', 'Installation et mise à jour de logiciels. Optimisation du système : gestion des pannes logicielles, vérification, défragmentation et nettoyage des disques, création de tâches planifiées. Sauvegarde et restauration de données.', 'Maîtriser l''utilisation de Microsoft Backup pour les sauvegardes sur applications nationales. Pouvoir effectuer les tâches de maintenance périodique du système.', 'Personnel ayant suivi la formation d''initiation à la micro-informatique ou ayant les connaissances', 1, 6, 96, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (123, 'Organisation constitutionnelle et administrative', 'Administration et institutions', 'La souveraineté nationale, la séparation des pouvoirs, la constitution de 1958, déconcentration et décentralisation, les collectivités territoriales.', 'Acquérir des connaissances sur le fonctionnement des institutions de l''Etat et de son administration.', 'Personnels de catégories B et C, personnels de santé et sociaux.', 3, 18, 26, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (124, 'Excel 2000', 'Bureautique', 'Vue générale d''Excel, construire des tableaux de gestion avec des formules simples, mise en forme des tableaux, création de formules conditionnellees, liens entre plusieurs tableaux, création de graphiques, initiation aux bases de données Excel et aux tab', 'Maîtriser les fonctionnalités du tableur EXCEL 2000', 'Personnels ayant à disposition le logiciel.', 4, 24, 72, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (125, 'La correspondance administrative', 'Environnement professionnel', 'Les règles générales de communication écrite, le style et le langage administratif, les différents types de correspondances administratives, cas particuliers.', 'Donner des méthodes pour rédiger avec plus de facilités.', 'Personnels administratifs de catégories B et C, personnels de santé et sociaux.', 1, 6, 78, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (127, 'Adjoint exceptionnel', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (128, 'Groupement Grand-Ouest de la formation continue des personnels', 'Environnement professionnel', 'Bilan des actions de l''année 2001 et du 1er trimestre 2002. Désignation du futut coordonateur. Informations diverses du groupement.', 'Réunion Grand-Ouest', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (130, 'Les ateliers de l''enseignement supérieur - 4ème session', 'Environnement professionnel', 'Les grands principes de la gestion financière de projets européens, les contrats de mobilité, la procédure suivie par l''agence pour le traitement des contrats de mobilité et l''analyse des rapports finaux, la gestion financière des crédits d''organisation d', '4ème SESSION : la gestion financière de la mobilité Erasmus et Leonardo', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (131, 'Optimisation d''un site Web - Niveau 1', 'Technologies d¿information et de communication', 'Mise à jour des connaissances sur les services de l''Internet et les protocoles associés. Mise au point des connaissances sur les images. Connaissances minimales sur le logiciel de traitement des images. Acquisition d''images. Intégration d''images et d''anim', 'Donner aux stagiaires les connaissances nécessaires aux traitement des images en vue d''améliorer l''aspect multimédia des pages Web, en tenant compte des conséquences pour le client :amélioration de la convivialité du site, temps de chargement, aspect sur', 'Personnes ayant déjà participé à la création de pages Web, mais non aux "spécialiste" des sites Web', 'PRE-REQUIS : savoir utiliser un micro ordinateur (Mac ou PC). Connaissance de base d''un traitement de texte, d''un logiciel de dessin de base (Paint ou autre) et utilisation d''un navigateur tel que Netscape ou Internet Explorer.', 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (132, 'Création et gestion d''un support d''enseignement  en formation ouverte', 'Environnement professionnel', 'Présentation des outils techniques au service de la pédagogie. Conception, réalisation d''une structure de module et intégration du contenu avec le logiciel Dreamweaver. Réalisation d''animations avec le logiciel Flash. Traitemt et intégration d''une séquenc', 'Transmettre les techniques de base nécessaires au développement de supports de cours pour une formation à distance via Internet. Connaître les solutions techniques existantes pour le développemt de modules de formation à distance. Connaître des logiciels', 'Personnel technique amené à collaborer avec des pédagogues ds le cadre du développemt de modules d''e', 5, 30, 1, 'PRE-REQUIS : avoir des connaissances de base dans le domaine de l''Internet (www, ftp, html) et quelques notions en traitement d''images.', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (133, 'Le risque chimique en laboratoire - Description et prévention', 'Hygiène et sécurité', 'Importance du langage en Toxicologie et en Toxicochimie. Exemple de la spéciation. Le risque physico-chimique. Le risque toxique. Réglementation. Les déchets chimiques et le stockage des produits. Protection collective. Equipements de protection individue', 'Pouvoir identifier et évaluer les risques d''une manipulation. Développer la capacité à sécuriser les méthodes et techniques de travail. Connaître les obligations réglementaires y afférant.', 3, 18, 25, 'N', 0, 0, 0, 0, 'N', 'O', '227. Risque en laboratoire', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (134, 'Les Logiciels de Gestion de Patrimoine - Séminaire ARTIES', 'Hygiène et sécurité', 'Conférences-débat sur le thème et forum de présentation de logiciels Dessin ou Gestion de Patrimoine.
(AUTOCAD, ARC+, VISIO2000, APIBAT, RS ABILA, VIZELIA, SNEDA)', 'Présenter aux participants cette problématique et les réponses techniques des professionnels. Présenter les différents types d''interfaces existantes et les nouvelles technologies. Présenter dans le cadre d''un Forum divers logiciels.', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (135, 'Les ateliers de l''enseignement supérieur - 5ème session', 'Environnement professionnel', 'Présentation générale des programmes  Léornado placements, Socrates/Erasmus. Comment remplir la demande de Charte, présentation du rapport final Socrates/Erasmus. Présentation du rapport final Leonardo.', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (136, 'REUNION DES RESPONSABLES DE FORMATION  GRAND  OUEST', 'Gestion des ressources humaines', 'Bilan quantitatif et qualitatif des formations. Coordination - Bilan des actions Parfaire en cours - partenariat avec les associations - référentiel responsable formation - questions diverses', 'Réunion formation continue IATOSS Grand Ouest', 'Secrétaires Généraux, Responsables de formation continue IATOSS.', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (137, 'Formation à l''achat public des établissements d''enseignement supérieur', 'Environnement professionnel', 'Le nouveau champ d''application du Code des marchés public, la nécessité et les méthodes d''analyse des besoins, le suivi des seuils de procédure, la préparation du marché, l''exécution du marché et les groupements de commandes.', 'L''objectif est à la fois de s''approprier les modifications introduites par la nouvelle réglementation issue du nouveau code des marchés, mais également de créer une situation favorable à la rencontre et à l''échange entre ces acteurs qui sont confrontés à', 2, 12, 1, 2, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (138, 'Recyclage en habilitation électrique', 'Hygiène et sécurité', 'Formation en 2 parties : théorique aux risques électriques et à leur prévention, pratique dans le cadre du domaine d''activité attribué à l''intéressé.', 'Rendre les stagiaires capables de respecter les prescriptions de sécurité définies par la publication UTE C 18-530. Permettre aux électriciens de mettre en application les prescriptions de sécurité de la publication UTE C 18-510 lors de l''exécution d''opér', 'Personne possédant les connaissances théoriques et pratiques nécessaires à la bonne exécution des tâ', 2, 12, 'Le niveau d''habilitation sera déterminé à l''issue de la formation théorique et après le contrôle des connaissances du stagiaires sur le logiciel HABILEC.', 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (139, 'Habilitation électrique recyclage HOV-B2V-BR-BC(ELB020)', 'Hygiène et sécurité', 'C''est un rappel des principes fondamentaux et mises à jour des connaissances des personnels habilités.', 'N', 0, 0, 0, 0, 'N', 'O', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (140, 'Traitement', 'Gestion des ressources humaines', 'Les traitements et rémunérations accessoires, la retraite des fonctionnaires et l''affiliation rétroactive, la validation des services auxiliaires.', 'Approfondir les connaissances des personnels chargés de la gestion des traitements, primes, dossiers de pension et dossiers de validation des années d''auxiliaire.', 'Service du personnel IATOSS, Enseignant et de bibliothèque, responsables administratifs de composant', 2, 12, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (269, 'Montage virtuel et trucages numériques', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (270, 'Groupe de travail des IHS', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (271, 'MODULE BUDGET JEFYCO', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (272, 'PREVENTION DU RISQUE AMIANTE', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (273, '22ème rencontres nationales du GPSUP', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (274, 'Accueil des personnes handicapées dans les Ets d''Ens. Supérieurs', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (275, 'Recyclage S.S.I.A.P.2', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (276, 'LOGICIEL PENSIONS', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (277, 'Perfectionnement en comptabilité générale', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (278, 'Colloque Parfaire', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (279, 'Formation COCONUTS', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (280, '22èmes rencontres nationales du GP''SUP"', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (281, 'Méthodo. De concep., produc. & exploit. D''un module de form. À distance', 'Méthodologie', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (282, 'MODULE DEPENSE JEFYCO', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (284, 'Interfaces graphiques avec JAVA', 'Technologies d¿information et de communication', 'introduction à bibliothèque Java - développement d''interfaces graphiques avec Swing - développement d''une interface Web avec Servlets et JSP (java Server pages) avec utilisation du serveur d''application Tomcat', 'Permettre au développeur d''appréhender les différents types d''interfaces graphiques (Swing, Web) offerts par la bibliothèque Java. La connexion d''une application Java à une base de données via une connexion JDBC sera également présentée.', 'toute personne étant amenée à développer des applications informatiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (285, 'Gestion des ressources humaines dans les organisations publiques', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (286, 'Réunion DRH du R.U.O.A.', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (287, 'Entretien et hygiène au laboratoire', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (288, 'Le Contrôle de gestion dans les Universités', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (289, 'Prévention des toxicomanies', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (290, 'Initiation à la communication', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (291, 'Préparation APASU', 'Préparation aux concours (cat. A)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (292, 'Initiation au contrôle de gestion', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (293, 'Gestion des contractuels', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (294, 'Formation SPIP - publication sur le site Web de l''Université', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (295, 'Rédiger son rapport d''activité', 'Préparation aux concours', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (296, 'Initiation au catalogage', 'Documentation', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (297, 'Habilitation électrique pour les non électriciens (HO-BO)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (298, 'Recyclage SSIAP 1', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '217. Premiers secours', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (299, 'La prise de parole en public', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (300, 'Gestion du stress', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (301, 'Utilisation d''XML dans des applications JAVA', 'Langages et bases de données', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (302, 'Préparation d''échantillons métallographiques', 'Formations scientifiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (303, 'Business Objects version 5', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (304, 'La prévention des risques professionnels', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (305, 'Commande numérique par calculateur', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (306, 'Système d''information et des comptabilités publiques', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (307, 'Les entretiens d''Orsay', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (308, 'La réforme de la formation professionnelle des agents de l''état', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (309, 'Gestion du temps', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (310, 'Salle pédagogique sous linux', 'Langages et bases de données', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (311, 'La pratique de l''inventaire dans les EPSCP', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (312, 'Journée d''étude des Agents comptables', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (313, 'Vérifications techniques réglementaires dans les bâtiments universitaires', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (314, 'Initiation à la gestion constructive des conflits', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (315, 'Initiation bonnes pratiques de laboratoire', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (316, 'La recherche dans l''enseignement supérieur', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (317, 'Habilitation PCR - recyclage', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (318, 'Formation à l''expérimentation animale Niveau II "Mammifères', 'Expérimentation animale', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (319, 'Préparation au CAPES - lettres modernes (ext)', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (320, 'CAFA - Gérer son temps et savoir déléguer', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (321, 'CAFA - La correspondance administrative', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (322, 'CAFA - La Gestion des archives', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (323, 'CAFA - EXCEL Initiation', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (324, 'CAFA - L''élaboration du budget', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (325, 'CAFA - L''enseignement supérieur', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (326, 'CAFA - Adaptation au service public l''E.N.', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (327, 'CAFA - Préparation APASU', 'Préparation aux concours (cat. A)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (328, 'CAFA - Initiation à powerpoint 2000', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (329, 'CAFA - Initiation à Powerpoint 2000 (2)', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (330, 'CAFA - WORD Perfectionnement', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (331, 'CAFA - Préparation concours adjoint administratif', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (332, 'CAFA - EXCEL Perfectionnement', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (333, 'CAFA - Préparation concours secrétaire administratif', 'Préparation aux concours (cat. B)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (334, 'Magasinier spécialisé', 'Préparation aux concours', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (335, 'Ponts roulants pontiers -  conducteurs expérimentés', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (336, 'Utilisation du GPS en géodésie et topométrie', 'Maintenance des équipements et installations', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (337, 'Montage d''une tour d''échafaudage', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (338, 'CARAMBOLE et KIWI', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (339, 'Auxiliaire de bibliothèque', 'Préparation aux concours', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (340, 'Marchés et services achats
Marchés et services achats', 'Environnement professionnel', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (341, 'Réglementation et gestion des achats', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (342, 'PIE et CORROSOL', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (343, 'SITUATION', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (344, 'SERRE-FILES', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '172. Acteurs de la prévention', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (345, 'Appareillage DMA', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (346, 'Pilotage de l''établissement', 'Administration et institutions', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (347, 'Réforme de la formation professionnelle tout au long de la vie', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (348, 'Initiation PAYE', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (349, 'A.C.M.O.', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '172. Acteurs de la prévention', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (350, 'La gestion du patrimoine dans les universités', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (351, 'Information sur les retraites', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (352, 'Professionnalisation des personnels de l''action culturelle des Ets Ens. Sup.', 'Environnement culturel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (353, 'Prévenir et gérer les risques contentieux', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (354, 'Réglementation assurance chômage', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (356, 'Comptabilité analytique', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (357, 'Technologies de l''information et de la communication', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (358, 'GEDI', 'Communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (359, 'SE PREPARER A L''ORAL DE CONCOURS', 'Préparation aux concours', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (360, '4ème journées nationales des observatoires des Ets Ens. Sup.', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (361, 'La LOLFsous winpaie', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (362, 'Mac OS X Essentials', 'Systèmes et réseaux', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (363, 'Interfaces WEB avec JAVA', 'Langages et bases de données', '- introduction à bibliothèque Java (organisation & contenu)
- modèle de conception modèle-vue-contrôleur
- servlets et JSP - utilisation du serveur d''application Tomcat
- Java Database Connectivity
- introduction à JSF (Java Server Faces)', 'Permettre au développeur d''appréhender les différents types d''interfaces graphiques (Swing, Web) offerts par la bibliothèque Java. La connexion d''une application Java à une base de données via une connexion JDBC sera également présentée.', 'toute personne étant amenée à développer des applications informatiques
groupe de 12 personnes', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (364, 'Interactions entre actes de gestion et organisation de service', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (365, 'Déroulement de carrière des personnels IATOSS', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (366, 'Formation aux outils de gestion des concours ITRF', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (367, 'La V.A.E.  pour la Gestion des ressources humaines', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (368, 'Recyclage PSC1', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '217. Premiers secours', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (369, 'PSC1', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '217. Premiers secours', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (370, 'Réseau des Observatoires de l''enseignement supérieur', 'Insertion professionnelle', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (371, 'PHOTOSHOP -  Perfectionnement', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (372, 'Base de données avancées', 'Langages et bases de données', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (373, 'Certificat Informatique et Internet (C2i) -', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (374, 'CACES plates-formes élévatrices (PEMP)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (375, 'Améliorer ses écrits professionnels', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (377, 'Habilitation électrique recyclage HOV-BOV(ELB019)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (378, 'Habilitation électrique B2-BR', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (379, 'Habilitation électrique HOV-BOV(ELB008)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (380, 'Indicateurs de l''activité scientifique des Ets de la production aux usages', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (381, 'Exploitation et entretien du matériel audiovisuel', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (382, 'Les opérations de fin d''exercice', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (383, 'Gestion d''un projet européen', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (384, 'Office Pro - Word - Excel - PowerPoint', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (385, '7èmes journées de mutualisation multimédias Ranaclès', 'Documentation', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (386, 'Publier sur le Web - méthodologie et aspects juridiques', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (387, 'Sécurité et sûreté biologiques dans la pratique quotidienne', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '227. Risque en laboratoire', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (388, 'Prise en main du logiciel Limesurvey et appli CATI', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (389, 'Biologie moléculaire - techniques PCR et clônage', 'Formations scientifiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (390, 'Gestion des conflits - approfondissement', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (391, 'Performances énergétiques appliquées aux bâtiments de l''Enseignement supérieur', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (392, 'Journée GPEEC des Universités du Grand Ouest', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (393, 'Management intégré de la qualité et de la performance', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (394, 'Mise en place de la réforme de la formation professionnelle', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (395, 'CAFA - Comptabilité Générale', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (396, 'CAFA - Initiation au droit administratif', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (397, 'CAFA - Prépa. Examen Prof. Secrét. Classe Exept.', 'Préparation aux concours (cat. A)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (398, 'CAFA - Préparation Examen Prof. APAENES', 'Préparation aux concours (cat. A)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (399, 'CAFA - Organisation constitutionnelle et administrative', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (400, 'CARAMBOLE', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (401, 'PIE', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (402, 'KIWI', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (403, 'COROSSOL', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (404, 'KIWI - COROSSOL', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (405, 'Réunion avec les services financiers de Poitiers', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (406, 'CAFA - Formation des APAENES', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (407, '2ème journée technique du GP SUP', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (409, 'Suivi de l''insertion professionnelle des sortants de l''enseignement supérieur', 'Insertion professionnelle', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (410, 'Formation de directeur de Service Universitaire de formation continue', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (411, 'Actualité des marchés publics', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (412, 'Sécurité incendie', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '202. Incendie', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (413, 'SPIP - KIT laboratoires de recherche - initiation', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (414, 'SPIP - KIT laboratoires de recherche - perfectionnement', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (415, 'Recyclage annuel obligatoire monitorat PSC1', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (416, 'SCOLARITE (scolarité, scol-péda,super-plan)', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (417, 'SPIP - Kit de site web', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (418, 'Les partenariats public-privé et contrats de performance énergétique', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (419, 'LRU et Patrimoine immobilier', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (420, 'POEMS et le pilotage des emplois et de la masse salariale', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (421, 'La comptabilité d''exercice dans un EPSCP', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (422, 'Prise de poste des chargés d''affaires juridiques de l''Enseignement Supérieur', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (423, 'Formation ACMO (formateur)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '172. Acteurs de la prévention', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (424, 'Rencontres nationales du GP''SUP', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (425, 'Réunion tri-partite hygiène et sécurité à l''Université', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (426, 'Adaptation à l''emploi des nouveaux responsables de formation des personnels', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (427, 'Les démarches qualité dans l''enseignement supérieur', 'Administration et institutions', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (428, 'Assemblée générale de l''association des DRH d''Ets d''Ens. Sup.', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (429, 'Compagnonnage métier du bâtiment électricité', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (430, 'LANGAGE XML', 'Langages et bases de données', 'XML : historique et objectifs - la syntaxe - l''utilisation de modèles de documents - quelques exemples d''application', 'permettre à toute personne amenée à manipuler des documents au format XML de comprendre les notions de langage de balisage et l''expression d''un modèle de document avec une DTD', 'toute personne étant amenée à manipuler des documents XML', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (431, 'Réglementation des marchés publics', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (432, 'La vie étudiante', 'Gestion de l¿étudiant', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (433, 'Assemblée Générale PARFAIRE', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (434, 'Manipulation de la centrale incendie d''Orbigny', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '202. Incendie', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (435, 'LINUX - LES BASES DE L''ADMINISTRATION', 'Langages et bases de données', '- introduction à Linux
- installation d''un système linux
- découverte d''applications usuelles
- partitionnement statique, systèmes de fichiers
- gestion des utilisateurs et droits UNIX
- installation d''applications binaires et source (rpm et deb)
- découverte de Vim
- notions de programmation bash
- mise en réseau, routage
- configuration de serveurs simples : SSH - NFS - CUPS', 'pouvoir commencer à administrer un parc de machines linux en réseau', 'tout utilisateur confirmé d''un poste de travail et/ou serveur groupe de 12 stagiaires', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (436, 'Lagaf 6,3', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (437, 'Préparation au concours de magasinier principal 2ème classe', 'Préparation aux concours (cat. C)', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (438, 'Journée des rencontres professionnelles de l''Ecole de la GRH', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (439, 'Situation des EPCSCP au regard du droit des assurances', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (440, 'Experts des jurys des concours ITRF', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (441, 'Frais de mission', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (442, 'Réforme des achats dans les Universités', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (443, 'MENER UN ENTRETIEN PROFESSIONNEL', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (444, 'Sécurité incendie dans les ERP', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '202. Incendie', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (446, 'La réglementation des concours ITRF', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (447, 'Système d''Information Documentaire', 'Technologies d¿information et de communication', 3, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (448, 'Connaître les dernières dispositions réglementaires et jurisprudentielles', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (449, 'Echange et partage de données et indicateurs', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (450, 'Assemblée Générale de l''Ecole Normale Supérieure', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (451, 'Eco-construction et Eco-réhabilitation : une traduction du développement durable', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (452, 'Conduite de réunion', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (453, 'Responsabilités et nouveau code du travail', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (454, 'Habilitation électrique HOV- B1V(ELB009)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '222. Risque électrique', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (455, 'Adaptation aux nouveaux locaux', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (456, 'Hygiène et sécurité : nouveau code du travail', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (458, 'Domanialité et valorisation', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (460, 'ANGLAIS', 'Langues étrangères', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (461, 'Exploitation d''une chaufferie à vapeur ou eau surchauffée', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (462, 'Bilan de compétences', 'Bilan de compétences', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (463, 'Le suivi de cohorte', 'Insertion professionnelle', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (464, 'ACCUEIL PHYSIQUE ET TELEPHONIQUE', 'Accueil des publics', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (465, 'MAITRISER SON POSTE INFORMATIQUE', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (466, 'INDESIGN', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (467, 'Réunion SISE', 'Insertion professionnelle', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (468, 'CAFA - Stress et souffrance mentale au travail', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (469, 'CAFA - Enjeux actuels du système éducatif', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (470, 'CAFA - Les finances publiques', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (471, 'Cycle supérieur de management des Ets d''Enseis. Sup. et de recherche', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (156, 'Perfectionnement WORD', 'Bureautique', 'Gestion des documents long, création d''une page de garde, création d''en-têtes et pieds de page, création de notes de bas de page, création d''une table des matières et index et numérotation automatique des titres et sous-titres, résolution des problèmes re', 'Maîtriser le publipostage et les modèles. Maîtriser les différents objets mis à disposition dans WORD.', 'Personnels déjà utilisateurs de WORD.', 1, 6, 24, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (157, 'Séquençage automatique d''ADN', 'Techniques de recherche', 'Théorie : principes de l''amplification par PCR, de la réaction de séquence, de séparation des oligonucléotides. Pratique : réaction de séquence, utilisation du séquenceur automatique, lecture et analyse des séquences. Instrumentation : Séquenceur automati', 'Maîtriser les techniques et outils permettant de séquencer un fragment d''ADN.', 'Prioritairement personnel de catégories C et B.', 2.5, 15, 'PRE-REQUIS : connaissances de bases en Biologie Moléculaire : structure de l''ADN, préparation d''ADN (maxi et mini-préparation).', 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (158, 'Habilitation électrique', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (159, 'Formation GIRAFE', 'Environnement professionnel', 'Former les nouveaux arrivants.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (160, 'CIEL PAYE', 'Gestion et comptabilité', 'Manipulation de base, créer un dossier, entrer les variables globales, créer et modifier les fiches salariés, gérer les profils salariés, préparer un dossier de paie, établir des bulletins de salaire, éditer et valider, éditions diverses, gérer les congés', 'Connaître et utiliser des logiciels professionnels de paie sous environnement Windows', 'Toute personne ayant des notions de comptabilité.', 3, 18, 'Attestion de formation.
Pré-requis : connaître l''environnement Windows et manipuler aisément souris et clavier, savoir traiter la paie.', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (161, 'EXCEL 97', 'Bureautique', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (162, 'Risques majeurs : sommes-nous prêts face au pire ?', 'Environnement professionnel', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (163, 'Pose de plafonds suspendus et de cloisons sèches', 'Environnement professionnel', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (164, 'Préparation au concours MO IEST OPTION B (IE)', 'Préparation aux concours', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (165, 'Installation d''un ordinateur - Mise en place d''un PC', 'Maintenance des équipements et installations', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (166, 'Finances Publiques', 'Environnement professionnel', 'Le budget de l''Etat et des collectivités territoriales, les ressources publiques, les dépenses publiques et la cour des comptes, les chambres régionales des comptes.', 'Permettre une meilleure connaissance des institutions financières et du fonctionnement budgétaire de l''Etat.', 'Personnels administratifs (priorité aux catégories B et C)', 2, 12, 26, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (167, 'Recyclage AFPS', 'Hygiène et sécurité', 'La victime s''étouffe, saigne abondamment, est inconsciente et respire, est inconsciente et ne respire plus.
En vidéo : la victime se plaint d''un malaise et se plaint après un traumatisme.
1 ou 2 mises en situation, observation des gestes de secours réal', 1, 6, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (168, 'L''évaluation des risques professionnels', 'Hygiène et sécurité', 'Evaluation des risques, présentation du guide.', 'Présentation du guide d''évaluation des risques professionnels.', 'Ingénieurs hygiène et sécurité et personnes en charge des problèmes d''hygiène et de sécurité dans le', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (169, 'Les images télévisuelles - Quel sens ? Quel impact ?', 'Lecture publique', 'Mieux comprendre comment sont "fabriquées" ces images. Apprendre à exercer son esprit critique par rapport à  l''ensemble des images télévisuelles.', 'Analyser différentes formes d''images prises dans différentes types d''émissions de télévision (journaux télévisés, documents, téléfilms, jeux ¿)', 'ATOSS et encadrement.', 2, 12, 15, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (170, 'WORD Initiation', 'Bureautique', 'BIATOSS', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (171, 'EXCEL - Initiation', 'Bureautique', 'IATOSS', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (172, 'Enseignement Supérieur', 'Environnement professionnel', 'Les établissements de l''enseignement supérieur, les structures de l''université, les différents cycles d''étude et les différentes catégories de personnels (enseignants et IATOS)', 'Acquérir les connaissances du programme relatif à l''enseignement supérieur des concours.', 'Personnels ATOS', 1, 6, 26, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (173, 'Découverte des démarches qualité et de certification', 'Environnement professionnel', 'Définition de la qualité  et démarches de certification.
Notion de client, de produit et de service. Relation de client/fournisseur.', 'Présenter les concepts "qualité" et "assurance qualité".
Présenter et échanger sur les applications possibles à l''université (administration/enseignement/recherche)', 'Personnel IATOS et Enseignants.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (174, 'Initiation WORD', 'Bureautique', 'Permettre aux débutants d''utiliser WORD', 'Personnel IATOSS', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (175, 'Le risque biologique en laboratoire de recherche', 'Hygiène et sécurité', 'Aspects réglementaires, évaluations des risques (microorganisme, OGM, prions, animaux, plantes) et la prévention (laboratoires confinés, enceintes de sécurité, protections individuelles, déchets, transport, moyens de décontamination ¿)', 'Actualiser nos connaissances dans le domaine afin d''être plus efficace, renforcer le réseau et connaître et apprendre à utiliser des documents.', 'Préventeurs du CNRS et des universités : ingénieurs d''hygiène et de sécurité, médecins de prévention', 'N', 0, 0, 0, 0, 'N', 'O', '227. Risque en laboratoire', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (176, 'Optimisation d''un site WEB - Univ d''Automnes', 'Technologies d¿information et de communication', 'Techniques de rérérencement, HTML avancé, Insertion de contenu dynamique, Séparation des données et de l''apparence avec XML.', 'Etre capable de proposer une solution optimale pour la réalisation ou la mise à niveau d''un site WEB. Maîtriser les outils logiciels permettant de proposer une solution optimale pour la mise à niveau d''un site WEB.', 'Informaticiens chargés de maintenir le site WEB de leur établissement. BAP E (informatique et calcul', 'Pré-requis : connaître le langage HTML et avoir des notions de Javascript.', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (177, 'ARTIES : étanchéités et façades', 'Hygiène et sécurité', 'Toiture terrasse. Techniques de rénovations. Membranes synthétiques et démarche HQE. Entretien des façades et sécurité des terrasses ou autre théme.', 'Former sur les éléments du clos et couvert (principalement les façades et les toitures ¿)', 'Responsable de service hygiène et sécurité.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (178, 'Vingtième rencontres nationales du GP''Sup', 'Hygiène et sécurité', 'La norme ISO 14001 relative à la protection de l''environnement. Impact sur l''environnement des rejets de produits toxiques. Les paramètres de mesure de la qualité de l''air et des rejets atmosphériques, filtrage des rejets atmosphériques des labos,', 'L''université et son impact sur l''Environnement.', 'Responsable service Hygiène et Sécurité.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (179, 'Déconcentration des actes de gestion', 'Environnement professionnel', 'Inscrire le processus de déconcentration dans le mouvement plus globla de la réforme de l''Etat, de lui donner du sens auprès des agents qui le mettent en œuvre quotidiennement en appliquant les mesures correspondantes', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (180, 'Formation initiale des CLHS', 'Hygiène et sécurité', 'Typologie des risques, cadre juridique de la prévention, les moyens d''action du CHS et des CLHS, problèmes de responsabilités, accident du travail et maladie professionnelles et études de projets.', 'Acquisition de connaissances de base en hygiène et sécurité, développer l''aptitude à déceler et à mesurer les risques professionnels, développer la capacité à analyser les méthodes et techniques de travail, s''impliquer dans la politique de prévention de l', 'Correspondant local en hygiène et sécurité.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (181, 'MULTIMEDIA (Ranacles)', 'Technologies d¿information et de communication', 'Plages pour échanges de compétences, de pratiques et d''expériences, sur un plan pédagogique et organisationnel. Présentations de matériels et de logiciels. Séances d''initiation et de fabrication. Présentation finale des travaux réalisés et bilan fin stage', 'Maîtrise  du multimédia', 'Utilisateurs de multimédia.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (182, 'Entretiens de Lille 2003 : GRH et Encadrement', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, MODE_VALIDATION, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (183, 'Réforme des retraites', 'Gestion des ressources humaines', 'Présentation, le dossier, les procédures et information des futurs retraités.', 'S''approprier la réforme des retraites et ses décrets d''application, afin que les services des pensions soient en mesure de les mettre en œuvre dans les meilleurs délais.', 'Responsables des services académiques de pensions, ceux des Inspections académiques, des établisseme', 'Regroupement en 3 centres interacadémiques.', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (184, 'TVA et stratégie', 'Gestion et comptabilité', 'Qu''est-ce que la TVA, le champ d''application, les règles de territorialité, le fait générateur et l''exigibilité, la base d''imposition et les taux de TVA, le droit à déduction, étude de cas particulier concernant les établissemts publics d''enseignemt sup', 'Faire comprendre les mécanismesde cet impôt, de permettre aux établissements de mettre en œuvre les règles de façon correcte et de leur donner les moyens d''appréhender sans difficulté les différentes situations auxquelles ils pourraient être confrontés.', 'Personnels amenés à mettre en œuvre la réglementation applicable en matière de TVA dans les domaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (185, 'Réunion du Groupe Grand Ouest', 'Gestion des ressources humaines', 'Bilan quantitatif et qualitatif des formations, définition des orientation de l''offre de formation régionale, travaux en atelier avec les correspondants formation, réunion plénière, compte rendu des ateliers, définition de l''offre de formation régionale.', 'Définir l''offre de formation régionale.', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (186, 'POWERPOINT', 'Bureautique', 'Personnel IATOSS', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (187, 'Gestion des déchets chimiques', 'Hygiène et sécurité', 'Cadre réglementaire applicable à la gestion des déchets dangereux, savoir reconnaître un danger, connaître ses déchets et les risques associés, connaître les filières de vos déchets, la procédure interne de tri, prévoir les accidents et les incendies.', 'Réaliser un tri à la source en toute sécurité et conformément à la réglementation. Connaître les contraintes et le fonctionnement des filières de collecte-traitement. Respecter les précautions de sécurité personnelle. Réagir efficacement en cas d''accident', 'Personnels devant réaliser un tri à la source des déchets chimiques de laboratoire', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (188, 'Seizièmes rencontres nationales du GP''SUP', 'Hygiène et sécurité', 'Communiquer en matière de sécurité. Méthodes de communication, outils pédagogiques et leur exploitation, applications concrètes, campagnes de communication en sécurité, internet,vecteur de la communication en sécurité.', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (189, 'Aide à la rédaction du formulaire de candidature Leonardo-mobilité 2004', 'Environnement professionnel', 'Atelier d''aide à la rédaction du formulaire de candidature (vocabulaire, rubriques, etc .).', 'Explication pas à pas du formulaire de candidature.', 'Exclusivement réservé aux nouveaux établissements promoteurs et aux nouveaux gestionnaires Leonardo', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (190, 'Réunion des DRH du Réseau Grand Ouest', 'Gestion des ressources humaines', 'Mise en place d''un réseau des DRH régional puis national, la fonction de DRH, le décret du 29 avril 2002, la mise en place d''une formation de formateurs à l''entretien d''évaluation dans le cadre du PNP, l''action sociale, points divers, calendrier et conten', 'Responsables de DRH', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (191, 'La maintenande de premier niveau du matériel audiovisuel et multimédia', 'Maintenance des équipements et installations', 'Vidéo projecteur LCE, périphérique vidéo, périphérique informatique, mise en application.', 'Connaître  les différents types de matériels utilisés en multimédia et en audiovisuel. Connaître les différentes connections entre ces matériels. Etre capable d''assurer une maintenance de premier niveau.', 'Personnels IATOS et des bibliothèques chargés de l''aide à l''utilisation de matériels audiovisuels ou', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (192, 'Les modifications du code des marchés publics', 'Environnement professionnel', 'Analyse des modifications du code des marchés publics et leurs conséquences. La responsabilité des acheteurs. Comment les collectivités ont-elles appliqué les modifications du Code des marchés publics ?', 'Comment adapter les modifications du code des marchés publics à ses marchés ?', 1, 6, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (193, 'ARTIES : énergie : économies, mesures et mise en concurrence', 'Hygiène et sécurité', 'Economies d''énergie, mesures, marchés publics d''énergie et assemblée générale ARTIES', 'Responsables Techniques Immobiliers de l''Enseignement Supérieur', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (194, 'Electricité Niveau 2 - Maintenance en électricité', 'Environnement professionnel', 'Electrotechnique appliquée, maintenance en électricité, normes et réglementation, régimes de neutre, le rapport du bureau de contrôle, conception et étude d''un projet, étude de schémas, moteur triphasés et dépannage sur maquettes.', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (195, 'Entretien des locaux', 'Environnement professionnel', 'Les différents types de surfaces, l''utilisation des matériels et des produits, savoir lire les étiquettes, lire et déchirer les fiches techniques, signification d''étiquetage des produits dangereux, règles d''hygiène et de sécurité.', 'Améliorer les connaissances professionnelles.', 'MO responsables des agents et agents chefs
OEA et gestionnaires (la première journée)', 3, 18, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (196, 'Formation initiale en CAO-DAO avec AUTOCAD - Niveau 1', 'Gestion du patrimoine', 'Etude de l''environnement de travail, création et modification d''objets 2D, utilisation des systèmes de coordonnées et de l''accrochage objet, gestion des calques, la cotation et présentation et édition des plans.', 'Découvrir et acquérir une bonne pratique des outils de base sur AUTOCAD.
Créer et modifier un dossier de plan.
Adapter AutoCad au besoin de votre activité', 'Ingénieurs, Techniciens, Dessinateurs, Projeteurs.', 5, 30, 8, 12, 'O', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (197, 'Initiation à Labview Version 8 - Acquisition de données sur PC', 'Formations scientifiques', 'Principe de l''acquisition de données et de la commande de procédé, initiation à Labview, type de données, variables et constantes, notion d''algorithme, structures de programmantion, utilisation de bibilothèques d''instruments virtuels.', 'Relier un capteur analogique, étalonnage du capteur, faire des mesures en continu en affichant les courbes et alarmes, stocker dans un fichier informatique les mesures et leur datation, principe de la commande d''un processus simple, notation supervision.', 'IATOSS et enseignants-chercheurs ayant à développer des applications de mesures, de tests ou de cont', 2, 12, 8, 12, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (198, 'LINUX - Utilisateurs', 'Langages et bases de données', 'Introduction à Linux, Découvrir l''environnemt Unix, Gérer ses fichiers sous Linux, Imprimer et Applications utilisateurs disponibles sous Linux.', 'Permettre aux utilisateurs de Microsoft Windows de migrer vers Linux et les Outils Libres.', 'Tout utilisateur d''un poste de travail souhaitant remplacer les outils du quotidien par des alternat', 5, 30, 12, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (199, 'Pratique et approfondissement en CAO-DAO avec AUTOCAD - Niveau 2', 'Gestion du patrimoine', 'Rappels de base et personnalisation du poste de travail, création et utilisation de blocs et de référence externes, mise en place et utilisation de bibliothèques graphiques, notions approfondies de visualisation et de mise en page d''un projet, présentatio', 'Adapter AUTOCAD au besoin de son activité
Compléter et consolider ses connaissances de base
Gérer un dossier de plans', 'Ingénieurs, Technicien, Dessinateurs, Projeteurs.', 3, 18, 12, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (200, 'Gérer son temps et savoir déléguer', 'Gestion des ressources humaines', 'La relation du temps, l''auto-diagnostic "temps", l''organisation du travail, les lois et outils de gestion du temps, la délégation, investissement en temps, les réunions et la gestion du temps, programme d''action personnel.', 'Gérer son temps et savoir déléguer.', 'Personnels de direction d''inspection, tout personnel ATOS encadrant une équipe.', 2, 12, 15, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (201, 'Microscopie électronique à balayage et microanalyse X (MEB)', 'Formations scientifiques', 'Microscopie électronique à balayage, microanalyse X par EDS, préparation d''échantillons et travail sur des échantillons particuliers.', 'Apporter les connaissances théoriques et pratiques de base à l''utilisation du microscope électronique à balayage (MEB) et à l''analyse X élémentaire par dispersion d''énergie (EDS).', 'Ingénieurs, techniciens (BAP A, B ou C), enseignants-chercheurs et doctorants débutant dans le domai', 3, 18, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (202, 'S.I.G. 1 - CONCEPTS ET UTILISATION DES SYSTEMES D''INFORMATION GEOGRAPHIQUE', 'Documentation', 'Introduction aux SIG, modes de représentation, travail pratique sur logiciel SIG, cartographie, réalisation d''un micro-projet, les bases de données et bases disponibles sous forme numérique.', 'Ce stage permet d''appréhender les concepts de base des S.I.G. ainsi que l''étendue de leurs applications dans différents domaines.', 'Personnels administratifs et techniques ou enseignants-chercheurs s''intéressant à l''utilisation des', 4, 24, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MAX, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (203, 'Pratique et approfondissement des systèmes d''information géographique - S.I.G. 2', 'Documentation', 'Présentation du projet, acquisition des données, acquisition sur le terrain, structuration des données, mise en place de la base de données, exploitation des données, analyse spatiale dans les SIG, restitution-mise en place, restitution via internet ou in', 'Permettre d''avoir une approche pratique des outils SIG au travers de la réalisation d''un projet complet qui amène les participants à aborder les différentes étapes de la mise en place et de l''utilisation d''un SIG. Prolongement du stage "Concept des SIG".', 'Personnels administratifs et techniques ou enseignants-chercheurs devant utiliser ou mettre en place', 4, 24, 9, 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (204, 'Rencontre du réseau des responsables marchés publics AMUE', 'Formations juridiques', 'Nouvelles modalités d''appréciation des seuils, échanges sur l''appropriation de ces notions par les établissements, la préparation des marchés, déroulements des procédures, points d''actualité.', 'Analyse et discuter de la réforme entrée en vigueur le 10 janvier 2004', 'Responsables marchés publics', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (205, 'Maîtriser toute la réglementation assurance chômage', 'Gestion des ressources humaines', 'Introduction à la nouvelle convention, organiser un service assurance chômage, l''ouverture des droits, le calcul de droits, la reprise, la réadmission, les fonctionnaires en disponibilité, l''activité réduite, la formation, nouvelles formalités ¿', 'Savoir traiter un dossier chômage efficacement et rapidement, dans le respect des règles et procédures, en maîtrisant l''ensemble des évènements qui interviennent au cours de la vie de celui-ci (formation, activité réduite, reprise, réadmission, maladie ..', 'Agents our responsables du service du personnel, de la paie, de la gestion administrative de personn', 3, 18, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (206, 'Maîtriser toute la réglementation assurance chomage', 'Gestion des ressources humaines', 'Radioprotection et matières nucléaires, quels équipements de protection individuelle pour quels risques et quelles options pour l''organisation incendie d''un campus universitaire.', 'Responsables hygiène et sécurité', 2, 12, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (207, 'Les Marchés Publics', 'Environnement professionnel', 5, 30, 1, 'N', 0, 0, 60.5, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (208, 'Dix huitième rencontres nationales du GP''SUP', 'Hygiène et sécurité', 'point sur les responsabilités (pénale, civile, administrative) - état des lieux dans l''enseignement supérieur, de la sécurité incendie ds les univers. - histoire de la responsabilité en hygiène et sécurité - les logiciels d''évaluation des risques', 'de l''obligation de sécurité et de sa transposition dans le management', 2, 12, 1, 'N', 180, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (209, 'Techniques d''animation et de pédagogie interactive', 'Environnement professionnel', '- situations nécessitant des techniques d''animation de pédagogie interactive
- analyse critique, au-évaluation à partir de tests - maîtrise des réuniions
- animation d''un groupe
- préparer une présentation orale, une action de formation

-', '- apporter des méthodes et outils pour préparer, animer ou participer à des actions collectives,
- techniques d''animation et communication 
- aider à être garant de la mobilisation des acteurs de son service et de la synergie avec l''établissement', 'Personnel d''encadrement', 3, 18, 1, 'N', 470, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (210, 'Entretien professionnel et fiche de poste', 'Gestion des ressources humaines', 'Dans le cadre des formations mises en place par la Mission de la formation pour l''accompagnement de la réforme de l''évaluation des fonctionnaires', 2, 12, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (212, 'Expérimentation animale  (niveau I)', 'Hygiène et sécurité', 'réglementation, animaleries de production et d''utilisation - anatomie et physiologie - manipulations, contention, prélèvements, administrations, pathologie infectieuse virale et parasitaire - essais toxicologiques et pharmacologiques - ethologie - etique', 'se mettre en accord avec la réglementation de l''expérimentation animale', 11, 66, 'N', 1633, 0, 0, 0, 'N', 'O', '227. Risque en laboratoire', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (213, 'Gestion des archives', 'Documentation', '- les archives publiques en France - comment gérer ses archives - vers la création d''un service d''archives', 'Comprendre les enjeux d''une bonne gestion des documents
Développer un système d''archivage au sein de son établissement', 'secrétaires généraux, agents comptables, autres responsables de service', 1.5, 9, 1, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (214, 'Mise en œuvre de la loi organique relative aux lois de finances (LOLF)', 'Gestion et comptabilité', '- gestion globalisée des moyens - pilotage de la masse salariale - gestion par la performance', 1, 6, 1, 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (215, 'Module de comptabilité JEFYCO', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (216, 'Habilitation à la  conduite d''autoclaves - Fonctionnement et Sécurité', 'Hygiène et sécurité', '- mise en commun du vécu de chacun - contrôle des connaissances  - rappel des dangers liés aux appareils à pression de vapeur - risques associés à l''utilisation des autoclaves - principales causes d''incidents et d''accidents - règlementations -', 'Etra capable de conduire des autoclaves en respectant les consignes de sécurité, les règles d''exploitation et les procédures de contrôle', 'conducteurs d''autoclaves et utilisateurs de stérilisateurs, agents de maintenance déjà qualificés', 1.5, 9, 10, 'N', 2344.16, 0, 0, 0, 'N', 'N', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (217, 'PYLA B sécurité laser, règles, consignes, procédures', 'Hygiène et sécurité', 'Utiliser l''outil laser dans des conditions de sécurité optimale', 'Opérateurs utilisant l''outil laser', 3, 18, 'N', 0, 0, 0, 0, 'N', 'N', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (218, 'Personne compétente en radioprotection', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '172. Acteurs de la prévention', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (219, 'Préparation à l''habilitation du Pers. Interv. Sur équip. Électriques', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (220, 'Perfectionnement à Labview - acquisition de données sur PC', 'Formations scientifiques', 'Gestion des tableaux - gestion du temps - gestion et pilotage de mesure par HPIB/SCPI - interaction - les liens bases de données', 'Programmer des mesures sur PC avec cartes d''acquisition et instruments de mesures programmables. Se perfectionner dans le développement des applications de mesures, de tests ou de contrôle de process', 'Techniciens, ingénieurs et enseignants, chercheurs', 2, 12, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (221, 'Systèmes d''Information Géographique & Bases de données', 'Formations scientifiques', '- notion de données - conception - conception et mise en place : exercices d''application - mise en application sur un micro-projet - bases de données et SIG', 'Acquérir les bases théoriques et réaliser une approche pratique de la mise en place des bases de données
Expérimenter les liens entre SIG et bases de données avec Arcgis et Mapinfo', 'Techniciens, chargés d''études, ingénieurs, chercheurs¿ confrontés à la conception de bases de donnée', 4, 24, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (222, 'Formation à la fiscalité directe', 'Environnement professionnel', '- Champ d''application de l''impôt sur les sociétés - détermination du résultat fiscal - paiement de l''impôt sur les sociétés - taxe professionnelle - taxes foncières et taxes assimilées aux impôts directs locaux', 'Règles et mécanismes de la fiscalité directe - modalités d''application de l''impôt sur les sociétés aux Etablissements Publics à caractère Scientifique Culturel et Professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (223, 'Tableau d''alarme', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, JOURS, TOTAL_H, EFFECTIF_MIN, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (224, 'Formation à la dématérialisation des procédures de marchés publics', 'Environnement professionnel', 'former les établissements de l''enseignement supérieur public à l''utilisation de la plate-forme de dématérialisation.', 'former le personnel des établissements à l''utilisation et à l''administration du logiciel', 1, 6, 3, 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (225, 'XXIIèmes journées ADHYS', 'Hygiène et sécurité', 'réglementation européenne, classification, étiquetage - toxicologie des perturbateurs endocriniens en biologie de la reproduction -  pesée de poudres : risques et prévention - prévention pour les cancers professionnels - 
environnement dans les cancers l', 'promouvoir l''hygiène et la sécurité dans les établissements d''enseignement supérieur ou de recherche
développer l''information et la formation, favoriser les échanges dans ces domain', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (226, 'Formation membres du Comité Hygiène et Sécurité', 'Hygiène et sécurité', 'les risques professionnels reconnus - les différents domaines de la prévention dont les 5 principaux - la détection des risques - l''organisation de la prévention à l''ULR', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (227, 'Formation à l''entretien d''évaluation', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (228, 'Formation méthodologique sur l''aide au pilotage dans les établissements', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (229, 'Déroulement de carrières des personnels IATOSS/ENSEIGNANTS', 'Gestion des ressources humaines', 'IATOSS : concours - avancements d''échelon - tableau d''avancement - liste d''aptitude - rôle de la CPE - rôle des CAP - rapport d''activité - ENSEIGNANTS : concours - avancement d''échelon - promotion de grade
rôle des instances', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (230, 'Journée des techniciens en informatique de laboratoire', 'Environnement professionnel', 'gestion d''un parc informatique au quotidien - mise en place d''une politique informatique', 'un large échange de vues entre personnels intervenant dans l''administration du parc informatique.Mise en place d''un réseau régional d''informaticiens de laboratoires', 'gestionnaire de parc informatique, administrateur réseaux et de ressources informatiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (231, 'Sociologie des organisations universitaires', 'Administration et institutions', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (232, 'Accueil du public', 'Accueil des publics', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (233, 'Conseil d''administration du GPSUP', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (234, 'PHOTOSHOP - Initiation', 'Bureautique', 'correction d''image - effacement de détails ou de défauts - création de calques, - modification des calques - manipulation - effets spéciaux - détourner d''une image, importation - formats des images', 'Manipulation, traitement et trucage d''image', 'personnels administratifs scientifiques et techniques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (235, 'Journée d''échanges politiques de prévention Hygiène et Sécurité', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (236, '4ème Rencontres Internationales de la Gestion Publique', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (237, 'Dispositif de formation personnels Encadrement', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (238, 'Perfectionnement et échanges des connaissances en lithopréparation', 'Adaptation à l¿emploi', 'relations entre les minéraux et le métamorphisme et l''échantillonnage in-situ avec l''importance de la schistosité et de la foliation. Comment tailler les échantillons - les consignes de sécurité en laboratoire', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (239, 'Approfondissement d''une démarche éducative de l''orientation', 'Insertion professionnelle', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (240, 'Accompagner l''étudiant dans sa recher d''information', 'Gestion de l¿étudiant', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (241, 'MICROSCOPIE ELECTRONIQUE - PREPARATION D''ECHANTILLONS', 'Formations scientifiques', 'aspects théoriques de l''instrumentation - préparation d''échantillons pour le MEB - préparation d''échantillons pour le MET- Observation', 'comprendre l''optique électronique et les modes de fonctionnement des microscopes électroniques à transmission et à balayage - comprendre les informations contenues dans les différents types d''images fournis par chacun des microscopes -  - les techniques a', 'ingénieurs et techniciens BAP', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (242, 'PREPARATION A L''HABILITATION ELECTRIQUE(personnels techniques non électriciens)', 'Hygiène et sécurité', 'Etre capable d''exécuter en sécurité des opérations spécifiques sur les installations et équipements électriques basse tension dans le respect des prescriptions de la publication UTE C 18 530', 'Personnel d''exploitation ou d''entretien "non électricien" appelé à effectuer des opérations simples', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (243, 'PREPARATION A L''HABILITATION ELECTRIQUE (enseignants et techniciens)', 'Hygiène et sécurité', 'Etre capable d''exécuter en sécurité des interventions sur les équipements électroniques et/ou plates formes d''essais', 'Personnel réalisant des interventions de mesure, de dépannage en atelier ou en plate forme d''essais', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (244, 'Formation à l''expérimentation animale à dominante "poissons" niveau II', 'Hygiène et sécurité', 'réglementation, animaleries de production et d''utilisation, éléments d''anatomie et de physiologie, éléments de pathologie infectieuse virale et parasitaire, essais toxicologiques et pharmacologiques, éthologie, éthique et bien être, méthodes substitutives', 'se mettre en accord avec la réglementation de l''expérimentation animale', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (245, 'Chef d''équipe en sécurité incendie dans les ERP (ERP2)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (246, 'WIN PAIE', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (247, 'Remise à niveau logiciel ChemStation et 1100A', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (248, 'Approfondissement du langage JAVA', 'Langages et bases de données', 'les concepts d''héritage, de polymorphisme et de typage et leur expression en Java - Introduction à bibliothèque Java - quelques modèles de conception réutilisables et mise en œuvre avec Java - notions sur le développement d''interfaces graphiques avec Swin', 'Permettre au développeur d''approfondir les concepts objet offerts par Java (héritage, polymorphisme, typage via la notion d''interface) et mieux connaître les classes de la bibliothèque Java', 'toute personne étant amenée à développer des applications informatiques', 'O', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (249, 'Initiation au langage XML', 'Langages et bases de données', 'XML : historique et objectifs - la syntaxe - l''utilisation de modèles de documents (DTD)
quelques exemples d''application', 'Permettre au développeur d''approfondir les concepts objet offerts par Java et mieux connaître les classes de la bibliothèque Java', 'toute personne étant amenée à développer des applications informatiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (250, 'Utilisation d''XML avec le langage JAVA', 'Langages et bases de données', 'introduction aux schémas XML - les bibliothèques DOM et SAX - notion sur la sérialisation XML d''objets en Java
- exemples de mise en œuvre', 'pouvoir manipuler des documents XML à travers des applications Java
pouvoir vérifier la syntaxe et valider des documents XML à travers des applications Java', 'toute personne étant amenée à développer des applications Java manipulant des documents XML', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (251, 'Initiation à la résonance magnétique nucléaire du proton et du carbone (RMN)', 'Techniques de recherche', '- principe physique de base de la résonance magnétique nucléaire - spectres de résonance des protons des molécules organiques
corrélation entre structure chimique, déplacement chimique et couplage spin-spin - analyse des systèmes AB, ABX, ABS2
RMN du ca', 'Connaître les principes de base de la résonance magnétique nucléaire
être capable d''interpréter des spectres RMN ''H et 13C de molécules organiques', 'Ingénieur et techniciens supérieurs des laboratoires de recherche et développement qui utilisent la', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, PUBLICS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (252, '(M.E.T.) Microscopie électronique à transmission et microanalyse X', 'Formations scientifiques', '- aspects théoriques : historique, structure système de vide, techniques de champ
- préparation d''échantillons pour le MET
- observations au MET', 'comprendre l''optique électronique, connaître les principes théoriques et pratiques de la microanalyse X par dispersion d''énergie (EDS) dans le domaine de matériaux et biomatériaux', 'ingénieurs, techniciens (BAP B et C), enseignants, chercheurs, doctorants', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (253, 'LINUX ADMINISTRATION - Perfectionnement', 'Langages et bases de données', 'N', 0, 0, 0, 0, 'N', 'N', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, CONTENU, OBJECTIFS, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (254, 'LABVIEW AVANCE - Acquisition de données sur PC', 'Formations scientifiques', '- acquisition de données : - configuration et contrôle de la carte d''acquisition NI USB 6008
acquisition de signaux simples et complexes provenant d''un générateur basse fréquence (GBF)
- contrôle de la carte son', 'se perfectionner dans le développement des applications de mesures -
savoir piloter une carte d''acquisition', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (255, 'La Gestion des archives', 'Documentation', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (256, 'Adaptation à l''emploi consécutive à la prise de fonction en EPSCP', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (257, 'Utilisation de précurseurs chimiques en laboratoires', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (258, 'Maintenance de bâtiments collectifs', 'Gestion du patrimoine', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (259, 'Conduite de projet', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (260, 'Correspondant local hygiène et sécurité', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (261, 'Regroupement ESEN', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (262, 'Role et fonctions du secrétaire assistant', 'Adaptation à l¿emploi', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (263, 'Gestion financière d''un EPSCP-LOLF', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (264, 'Formation sécurité pour les nouveaux arrivants', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (265, 'GAZ AVENIR 2006', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (266, 'Mise en œuvre et suivi d''un module de formation à l''aide d''une plate forme', 'Technologies d¿information et de communication', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (268, 'Master Administration des Etablissements Educatifs', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (472, 'CAFA - Connaissance du système éductaif', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (473, 'Business Object XI - Administrateur', 'Systèmes et réseaux', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (474, 'Jouer dans les espaces et lieux publics', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (475, 'Initiation à la bactériologie', 'Formations scientifiques', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (476, 'Journées Réseaux de l''Enseignement Supérieur', 'Systèmes et réseaux', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (478, 'Recyclage conduite d''autoclaves', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '212. Machines, équipements travail', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (479, 'Parcours de formation de l''encadrement', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (480, 'L''essentiel du droit de la formation continue', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (481, 'Initiation au droit administratif', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (482, 'Management d''une équipe', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, THEME_HS, ARCHIVAGE)
 Values
   (484, 'SECURITE - SURVIE EN MER', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', '999. Autre', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (485, 'Travailler en mode projet', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (486, 'Générateur vapeur spécifique', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (487, 'Communication en langue française', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (488, 'Expérimentation animale - Niveau 3"', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (489, 'Application  GPC (Gestion des produits chimiques)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (491, 'Application de gestion MANGUE', 'Applications de gestion', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (492, 'Master 2ème année - mention Sciences pour l''ingénieur', 'Préparation aux autres diplômes', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (493, 'Animer une réunion', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (494, 'L''entretien professionnel', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (495, 'Sécurité des spectacles', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (497, 'GRH - Le pilotage des emplois et de la masse salariale', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (498, 'Exploitation du système de sécurité incendie du PCMR', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (499, 'XLAB nouveaux gestionnaires', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (500, 'S.S.I.A.P.2', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (501, 'Exploitation d''équipements sous pression', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (503, 'Gérer les dépenses de personnel et les emplois', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (504, 'Pilotage des ressources humaines', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (505, 'After Effect Initiation', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (506, 'Repérage du mal être chez les jeunes et ses conséquences', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (507, 'Prévenir les risques psychosociaux en entreprise', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (508, 'Accueil du personnel handicapé dans le cadre de l''insertion professionnelle', 'Gestion du personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (509, 'Sécurité pour les activités Maintenance/Entretien/Courrier/Accueil', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (510, 'Sécurité pour les activités Maintenance/Entretien/Accueil', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (511, 'L''éclairage : technologies, réglementation, maintenance, environnement', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (512, 'Formation à l''utilisation de NET CFA', 'Gestion et comptabilité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (513, 'Exploitation du système de sécurité incendie de l''ILE', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (514, 'Assurer le suivi administratif d''un marché public de travaux', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (516, 'Délégation globale de gestion', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (517, 'Nouvelles règles de classement des enseignants-chercheurs', 'Gestion des ressources humaines', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (518, 'Prévention et gestion des conflits', 'Management', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (519, 'Conférence "langage, surdité et langue des signes"', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (520, 'Déploiement Chorus Opérateurs - scénario 1', 'Maintenance des équipements et installations', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (521, 'Réforme de la formation : que va-t-elle changer ?', 'Gestion du personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (522, 'Fabrication mécanique - Initiation aux techniques d''usinage sur machines outils', 'Développement personnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (523, 'Habilitation électrique haute tension (ELB010)', 'Hygiène et sécurité', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (524, 'Les missions des directeurs  de l''orientation et de l''insertion professionnelle', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');
Insert into GRHUM.ZLAGAF_ACTION
   (NO_ACTION, ACTION, DOMAINE, DS_CATALOGUE, REMUNERATION, FRAIS_FCT, FRAIS_MISSION, COUT_STAGIAIRE, INDIQUER_COUT, ACTION_INTERNE, ARCHIVAGE)
 Values
   (525, 'Les Accords-Cadres', 'Environnement professionnel', 'N', 0, 0, 0, 0, 'N', 'O', 'N');

   
/*
select t.theme, ths.theme theme_hygiene_securite, d.domaine, action
from zlagaf_action a, zlagaf_domaine d, zlagaf_theme t, zlagaf_theme_hs ths
where a.domaine = d.domaine
and d.theme = t.theme 
and ths.theme (+)= a.theme_hs
and obsolete = 'N'
and archivage = 'N'
and v603 = 'O'
order by theme, theme_hygiene_securite, domaine, action
*/

CREATE TABLE GRHUM.Z_FORMATION
(
  FOR_ID           NUMBER,
  FOR_LIBELLE      VARCHAR2(4000)               NOT NULL,
  FOR_COMMENTAIRE  VARCHAR2(4000),
  FOR_D_OUVERTURE  DATE,
  FOR_D_FERMETURE  DATE,
  D_CREATION       DATE                         DEFAULT SYSDATE               NOT NULL,
  D_MODIFICATION   DATE                         DEFAULT SYSDATE               NOT NULL,
  FOR_ID_PERE      NUMBER                       NOT NULL
)
TABLESPACE DATA_GRHUM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX GRHUM.Z_FORMATION_PK ON GRHUM.Z_FORMATION
(FOR_ID)
LOGGING
TABLESPACE DATA_GRHUM
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE GRHUM.Z_FORMATION ADD (
  CONSTRAINT Z_FORMATION_PK
 PRIMARY KEY
 (FOR_ID)
    USING INDEX 
    TABLESPACE DATA_GRHUM
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));

ALTER TABLE GRHUM.Z_FORMATION ADD (
  CONSTRAINT FK_FORMATION_PERE 
 FOREIGN KEY (FOR_ID) 
 REFERENCES GRHUM.Z_FORMATION (FOR_ID));


delete from z_formation;

drop sequence z_formation_seq;

create sequence z_formation_seq nocache;

insert into z_formation(for_id, for_libelle, for_id_pere)
select z_formation_seq.nextval, theme, z_formation_seq.nextval
from zlagaf_theme;

insert into z_formation(for_id, for_libelle, for_id_pere)
select z_formation_seq.nextval, 'Hygiène et sécurité', z_formation_seq.nextval
from dual;

insert into z_formation(for_id, for_libelle, for_id_pere)
select z_formation_seq.nextval, theme, for_id_hs.for_id
from zlagaf_theme_hs, (
    select for_id 
    from z_formation 
    where for_libelle = 'Hygiène et sécurité') for_id_hs;

insert into z_formation(for_id, for_libelle, for_id_pere, for_d_ouverture)
select z_formation_seq.nextval, d.domaine , for_pere.for_id, to_date('01/09/2010', 'dd/mm/yyyy')
from zlagaf_domaine d, (
    select for_id, for_libelle
    from z_formation) for_pere
where d.OBSOLETE = 'N'
and for_pere.for_libelle = d.theme;