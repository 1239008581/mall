select `user`.*,
  count(commodity_collection.user_id) as commodity_collection_num,
  count(brand_collection.user_id) as brand_collection_num,
  count(footprint.user_id) as footprint_num,
  count(`order`.user_id) as order_num
from `user`
  left join collection as commodity_collection on `user`.id = commodity_collection.user_id and commodity_collection.type = 'commodity'
  left join collection as brand_collection on `user`.id = brand_collection.user_id and brand_collection.type = 'brand'
  left join footprint on `user`.id = footprint.user_id
  left join `order` on `user`.id = `order`.user_id
where `user`.openId = 'asdfsadf'