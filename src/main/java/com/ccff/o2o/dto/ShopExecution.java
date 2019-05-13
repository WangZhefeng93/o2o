package com.ccff.o2o.dto;

import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //店铺的数量
    private int count;
    //操作的Shop（增删改店铺时候使用）
    private Shop shop;
    //shop列表（查询店铺列表时候使用）
    private List<Shop> shopList;


    public ShopExecution() {
    }

    //店铺操作失败时使用的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //店铺操作成功返回单个商品信息时使用的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    //店铺操作成功返回多个商品信息时使用的构造器
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
