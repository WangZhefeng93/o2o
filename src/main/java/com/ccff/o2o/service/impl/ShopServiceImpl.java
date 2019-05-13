package com.ccff.o2o.service.impl;

import com.ccff.o2o.dao.ShopDao;
import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.enums.ShopStateEnum;
import com.ccff.o2o.exceptions.ShopOperationException;
import com.ccff.o2o.service.ShopService;
import com.ccff.o2o.util.ImageUtil;
import com.ccff.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao = null;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        //第一步，对shop对象进行空值判断
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //2、第二步，对shop对象进行初始值设置
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //3、向数据库中添加店铺信息
            int effectedNUm = shopDao.insertShop(shop);
            if (effectedNUm <= 0){
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (shopImg != null){
                    //3、第三步，存储图片
                    try {
                        addShopImg(shop,shopImg);
                    } catch (Exception ex){
                        throw new ShopOperationException("addShop error:"+ex.getMessage());
                    }
                    //4、第四步，更新店铺图片地址
                    effectedNUm = shopDao.updateShop(shop);
                    if (effectedNUm <= 0){
                        throw new ShopOperationException("更新店铺图片地址失败");
                    }
                }
            }
        }catch (Exception ex){
            throw new ShopOperationException("addShop error:"+ex.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, File shopImg) {
        //1、获取Shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //2、存储图片，并返回存储后的相对路径
        String shopImgAddr = ImageUtil.generateThumbnails(shopImg,dest);
        //3、设置shop对象保存后的相对路径
        shop.setShopImg(shopImgAddr);
    }
}
