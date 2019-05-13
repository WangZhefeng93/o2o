package com.ccff.o2o.service.impl;

import com.ccff.o2o.dao.AreaDao;
import com.ccff.o2o.entity.Area;
import com.ccff.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao = null;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
