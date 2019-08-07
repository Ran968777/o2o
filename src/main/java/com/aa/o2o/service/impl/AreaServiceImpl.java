package com.aa.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aa.o2o.dao.AreaDao;
import com.aa.o2o.entity.Area;
import com.aa.o2o.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areadao;
	@Override
	public List<Area> getAreaList() {
		
		return areadao.queryArea();
	}
	
	

}
