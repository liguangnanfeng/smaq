delete
from
t_recheck_item_tbl
WHERE
recheck_id in (select id from t_recheck_tbl where check_id in (select id from t_check_tbl where del = 1));

DELETE
FROM
t_recheck_tbl where check_id in (select id from t_check_tbl where del = 1);

delete
from
t_check_item_tbl
WHERE
check_id in (select id from t_check_tbl where del = 1);

delete
from
t_check_part_tbl
WHERE
check_id in (select id from t_check_tbl where del = 1);

delete
from
t_check_admin_tbl
WHERE
check_id in (select id from t_check_tbl where del = 1);


delete
from
t_check_document_tbl
WHERE
check_id in (select id from t_check_tbl where del = 1);

delete
from
t_rectification_tbl
WHERE
check_id in (select id from t_check_tbl where del = 1);

delete FROM
t_check_tbl
WHERE
del = 1
