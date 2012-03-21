CREATE OR REPLACE GRHUM.VIEW V_REFERENS
(KEY, LIBELLE, LIBELLE_SEUL, KEY_PERE, NIVEAU, 
 NUMDCP, NUMFP, CODEEMPLOI, ORDRE)
AS 
select '-1' key, '_racine' libelle, '_racine' libelle_seul, '-1' key_pere, 0 niveau,
         null numdcp , null numfp, null codeemploi, null ordre 
from dual
union
select d.NUMDCP key, (decode(d.NUMDCP,'BB','B','CC','C', d.LETTREBAP) || ' ' || d.INTITULDCP) libelle, d.INTITULDCP libelle_seul, '-1' key_pere, 1 niveau,
        d.NUMDCP numdcp , null numfp, null codeemploi, null ordre 
from grhum.referens_dcp d
union
select f.NUMFP||f.numdcp key, decode(f.INTITULFP, 'Autres', 'z__Autres', f.INTITULFP) libelle, f.INTITULFP libelle_seul,f.numdcp key_pere, 2 niveau,
        f.numdcp numdcp, f.numfp numfp, null codeemploi, null ordre 
from grhum.referens_fp f
union 
select e.CODEEMPLOI key, '['||e.siglecorps||'] '||e.INTITULEMPLOI libelle, e.INTITULEMPLOI libelle_seul, e.NUMFP||e.numdcp key_pere, 3 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, null ordre 
from grhum.referens_emplois e, grhum.referens_dcp d
where e.NUMDCP = d.numdcp
union 
select a.CODEEMPLOI||'-_-'||a.ORDRE key, a.INTITULACTIVITE libelle, a.INTITULACTIVITE libelle_seul, a.CODEEMPLOI key_pere, 4 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, a.ordre ordre 
from grhum.referens_activites a, grhum.referens_emplois e, grhum.referens_dcp d
where a.CODEEMPLOI = e.codeemploi
and   e.NUMDCP = d.numdcp
union 
select c.CODEEMPLOI||'-_-'||c.ORDRE key, c.INTITULCOMP libelle, c.INTITULCOMP libelle_seul, c.CODEEMPLOI key_pere, 5 niveau,
        e.numdcp numdcp, e.numfp numfp, e.codeemploi codeemploi, c.ordre ordre 
from grhum.referens_competences c, grhum.referens_emplois e, grhum.referens_dcp d
where c.CODEEMPLOI = e.codeemploi
and   e.NUMDCP = d.numdcp
/

