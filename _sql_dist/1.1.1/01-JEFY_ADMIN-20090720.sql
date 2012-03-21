--
-- Patch JEFY_ADMIN du 20/07/2009 à executer depuis le user JEFY_ADMIN
--
--
-- PRE-REQUIS : 
--

grant references on jefy_admin.exercice to grhum;
grant references on JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE to grhum;
grant references on jefy_admin.exercice to mangue;
grant references on JEFY_ADMIN.LOLF_NOMENCLATURE_DEPENSE to mangue;



commit;