package com.ccff.o2o.dao;

import com.ccff.o2o.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDao {
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
