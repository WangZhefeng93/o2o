package com.ccff.o2o.service;

import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

public interface ShopService {
    //根据商铺ID获取商铺信息
    Shop getByShopId(Long shopId);

    //更新店铺信息
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    //添加店铺信息
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
