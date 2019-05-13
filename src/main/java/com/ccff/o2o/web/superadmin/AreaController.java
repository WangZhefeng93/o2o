package com.ccff.o2o.web.superadmin;

import com.ccff.o2o.entity.Area;
import com.ccff.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService = null;
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @RequestMapping(value = "/lsitarea",method = RequestMethod.GET)
    @ResponseBody   //加入该注解后，将信息以JSON形式返回给前端
    private Map<String,Object> lsitArea(){
        logger.info("======start======");
        long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<>();
        try {
            List<Area> list = areaService.getAreaList();
            modelMap.put("result",true);
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        }catch (Exception ex){
            ex.printStackTrace();
            modelMap.put("result",false);
            modelMap.put("errMsg",ex.toString());
        }
        logger.error("test error log!");
        long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]",endTime-startTime);
        logger.info("======end======");
        return modelMap;
    }
}
