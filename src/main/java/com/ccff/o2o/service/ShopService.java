package com.ccff.o2o.service;

import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    //添加店铺信息
    ShopExecution addShop(Shop shop, File shopImg);
}
