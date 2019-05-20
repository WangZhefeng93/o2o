package com.ccff.o2o.dao;

import com.ccff.o2o.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDao {
    /**
     * 通过ShopId查询店铺全部信息
     * @param shopId：需要查询的店铺的shopId
     * @return 返回被查询的Shop对象
     */
    Shop queryByShopId(long shopId);

    /**
     * 添加商铺
     * @param shop
     * @return 返回受影响的行数
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return 返回受影响的行数
     */
    int updateShop(Shop shop);
}
