package com.aa.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aa.o2o.BaseTest;
import com.aa.o2o.dao.ShopDao;
import com.aa.o2o.dto.ShopExecution;
import com.aa.o2o.entity.Area;
import com.aa.o2o.entity.PersonInfo;
import com.aa.o2o.entity.Shop;
import com.aa.o2o.entity.ShopCategory;
import com.aa.o2o.enums.ShopStateEnum;
import com.aa.o2o.service.impl.ShopServiceImpl;
import com.aa.o2o.utils.FileItemUtil;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("test2");
		shop.setShopAddr("test");
		shop.setPhone("123");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		FileItem rest = FileItemUtil.createFileItem("D:/Pictures/faer.jpg");
		System.out.println(rest);
		CommonsMultipartFile shopImg = new CommonsMultipartFile( FileItemUtil.createFileItem("D:/Pictures/faer.jpg"));
		ShopExecution res = shopService.addShop(shop, shopImg);
		System.out.println(res);
		//问题点ShopExecution se = shopService.addShop(shop, shopImg);
	}

}
