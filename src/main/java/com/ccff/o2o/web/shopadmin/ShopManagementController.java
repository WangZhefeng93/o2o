package com.ccff.o2o.web.shopadmin;

import com.ccff.o2o.dto.ShopExecution;
import com.ccff.o2o.entity.Area;
import com.ccff.o2o.entity.PersonInfo;
import com.ccff.o2o.entity.Shop;
import com.ccff.o2o.entity.ShopCategory;
import com.ccff.o2o.enums.ShopStateEnum;
import com.ccff.o2o.service.AreaService;
import com.ccff.o2o.service.ShopCategoryService;
import com.ccff.o2o.service.ShopService;
import com.ccff.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService = null;
    @Autowired
    private ShopCategoryService shopCategoryService = null;
    @Autowired
    private AreaService areaService = null;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("result",true);
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
        } catch (Exception e){
            modelMap.put("result",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        /**
         * 1、接收并转换相应的参数，包括店铺信息以及图片信息
         */
        //（1）获取从前端传递过来的店铺信息，并将其转换成对应的Shop实体类
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr,Shop.class);
        } catch (Exception e){
            modelMap.put("result",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        //（2）获取从前端传递过来的需要上传的图片信息，将其接收到shopImg中
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("result",false);
            modelMap.put("errMsg","上传图片不能为空！");
            return modelMap;
        }

        /**
         * 2、注册店铺
         */
        //（1）判断店铺是否为空
        if (shop != null && shopImg != null){
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution shopExecution = null;
            try {
                shopExecution = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if (shopExecution.getState() == ShopStateEnum.CHECK.getState()){
                    modelMap.put("result",true);
                } else {
                    modelMap.put("result",false);
                    modelMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("result",false);
                modelMap.put("errMsg",e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("result",false);
            modelMap.put("errMsg","店铺信息和上传图片均不能为空！");
            return modelMap;
        }
    }

}
