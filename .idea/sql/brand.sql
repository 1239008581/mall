select * from brand
where id in (select brand_id from brand_classify where classify_id = 1)