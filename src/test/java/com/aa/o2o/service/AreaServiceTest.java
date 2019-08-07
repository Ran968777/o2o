package com.aa.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aa.o2o.BaseTest;
import com.aa.o2o.entity.Area;
import com.aa.o2o.service.impl.AreaServiceImpl;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	@Test
	public void testArea() throws Exception {
		List<Area> areas = areaServiceImpl.getAreaList();
		assertEquals("西关", areas.get(0).getAreaName());
		
	}
}
