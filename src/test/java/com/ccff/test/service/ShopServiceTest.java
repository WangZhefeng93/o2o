package com.ccff.test.service;

import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Area;
import com.ccff.o2o.entity.PersonInfo;
import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.entity.ShopCategory;
import com.ccff.o2o.enums.ShopStateEnum;
import com.ccff.o2o.service.ShopService;
import com.ccff.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService = null;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        String path = "C:\\Users\\xiaobaixiaoda\\Desktop\\maidi5.jpg";
        File shopImg = new File(path);
        ShopExecution shopExecution = shopService.addShop(shop,shopImg);
    }
}
