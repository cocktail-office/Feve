ALTER TABLE MANGUE.OBJECTIF ADD (OBJ_POSITION NUMBER DEFAULT 0 NOT NULL);

COMMENT ON COLUMN MANGUE.OBJECTIF.OBJ_POSITION IS 'Position de l''objectif parmi les autres sur une même évaluation';

delete from MANGUE.objectif where eva_key not in (select eva_key from evaluation);

ALTER TABLE MANGUE.OBJECTIF
 ADD CONSTRAINT FK_OBJECTIF_EVALUATION 
 FOREIGN KEY (EVA_KEY) 
 REFERENCES MANGUE.EVALUATION (EVA_KEY);
 
CREATE OR REPLACE PROCEDURE MANGUE.reconstruire_position_objectif
-- initialisation des positions des objectifs
IS

-- Curseur de toutes les evaluations
CURSOR cur_eva IS SELECT * FROM mangue.evaluation;

-- curseur sur les objectifs
CURSOR cur_object(an_eva_key number) IS SELECT * FROM mangue.objectif 
where eva_key = an_eva_key;

lig_evaluation          evaluation%ROWTYPE;
lig_objectif            objectif%ROWTYPE;

max_pos number;

BEGIN
             
        -- 
        OPEN cur_eva;
         LOOP

            FETCH cur_eva into lig_evaluation;
            EXIT WHEN cur_eva%NOTFOUND;
                        
            -- les objectifs
              OPEN cur_object(lig_evaluation.eva_key);
              LOOP
        
                    FETCH cur_object into lig_objectif;
                    EXIT WHEN cur_object%NOTFOUND;
            
                    -- recuperer la valeur max des objectifs de cette evaluation
                    select max(obj_position) into max_pos from mangue.objectif where eva_key = lig_evaluation.eva_key;
            
                    -- incrementer 
                    update mangue.objectif set obj_position = max_pos+1 where obj_key = lig_objectif.obj_key;

              END LOOP;
              CLOSE cur_object;

        

        END LOOP;
        CLOSE cur_eva;
        
    
END;
/

BEGIN
	MANGUE.reconstruire_position_objectif();
END;

DROP PROCEDURE MANGUE.reconstruire_position_objectif;