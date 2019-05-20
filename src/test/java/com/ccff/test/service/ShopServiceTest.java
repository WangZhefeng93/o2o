package com.ccff.test.service;

import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Area;
import com.ccff.o2o.entity.PersonInfo;
import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.entity.ShopCategory;
import com.ccff.o2o.enums.ShopStateEnum;
import com.ccff.o2o.exceptions.ShopOperationException;
import com.ccff.o2o.service.ShopService;
import com.ccff.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService = null;

    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException {
        Shop shop = shopService.getByShopId(2L);
        shop.setShopName("修改后的店铺名称_不能再错了");
        String path = "C:\\Users\\xiaobaixiaoda\\Desktop\\2018081819553095 (2).png";
        File shopImg = new File(path);
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop,inputStream,shopImg.getName());
        System.out.println(shopExecution.getState());
        System.out.println(shopExecution.getStateInfo());
        System.out.println(shopExecution.getShop().getShopImg());
    }

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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        String path = "C:\\Users\\xiaobaixiaoda\\Desktop\\maidi5.jpg";
        File shopImg = new File(path);
        ShopExecution shopExecution = shopService.addShop(shop,new FileInputStream(shopImg),shopImg.getName());
    }
}
