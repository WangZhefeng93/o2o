package com.ccff.o2o.dao;

import com.ccff.o2o.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDao {
    List<Area> queryArea();     //以List的形式，列出区域列表
}
