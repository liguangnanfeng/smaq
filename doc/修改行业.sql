industry.js 替换

constantutil.java 中替换getIndustryByIndustry2
company_tbl industry2 替换

UPDATE company_tbl SET industry2 = REPLACE(industry2,"农林牧渔","农、林、牧、渔业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"批发零售","批发和零售业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"交通运输","交通运输、仓储和邮政业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"科技服务","科学研究和技术服务业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"商务服务","租赁和商务服务业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"住宿餐饮","住宿和餐饮业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"文体娱乐","文化、体育和娱乐业")
UPDATE company_tbl SET industry2 = REPLACE(industry2,"居民服务","居民服务、修理和其他服务业")

请求
/fore/indestry2Industry