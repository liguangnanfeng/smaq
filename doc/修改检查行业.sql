1.修改 library_tbl
update
library_tbl
set
industry = new
where
industry = old and type = 1

2.修改a_danger_manual_tbl
update
a_danger_manual_tbl
set
industry = new
where
industry = old

3.修改company_tbl
update
company_tbl
set
industry = new
where
industry = old

4 行业与检查行业对应关系
industry.js 替换
constantutil.java 中替换getIndustryByIndustry2
