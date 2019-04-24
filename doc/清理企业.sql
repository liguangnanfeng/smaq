delete from regulation_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from company_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from material_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from product_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from mequipment_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from sperson_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from sequipment_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from pequipment_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from detection_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from examination_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from facilities_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from evaluation_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from t_model_part_tbl where model_id in (select id from t_model_tbl where user_id in (select id from user_tbl where is_del = 1));

delete from t_model_tbl where user_id in (select id from user_tbl where is_del = 1);

delete from security_tbl where user_id in (select id from user_tbl where is_del = 1);

update t_check_tbl set del = 1 where user_id in (select id from user_tbl where is_del = 1);

delete from user_tbl where is_del = 1 and user_type = 5
