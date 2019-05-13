package com.ccff.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.ccff.o2o.dao.AreaDao;
import com.ccff.o2o.entity.Area;
import com.ccff.test.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() throws Exception {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }

}
