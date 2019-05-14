package com.ccff.test.dao;

import com.ccff.o2o.dao.ShopCategoryDao;
import com.ccff.o2o.entity.ShopCategory;
import com.ccff.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao = null;

    @Test
    public void testQueryShopCategory(){
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        System.out.println(shopCategoryList);
    }
}
