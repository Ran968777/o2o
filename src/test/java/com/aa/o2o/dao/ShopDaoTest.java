package com.aa.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aa.o2o.BaseTest;
import com.aa.o2o.entity.Area;
import com.aa.o2o.entity.PersonInfo;
import com.aa.o2o.entity.Shop;
import com.aa.o2o.entity.ShopCategory;


public class ShopDaoTest extends BaseTest{
	@Autowired
	ShopDao shopDao;
	
	@Test
	public void test1() {
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
		shop.setShopName("测试");
		shop.setShopDesc("test");
		shop.setShopImg("test");
		shop.setShopAddr("test");
		shop.setPhone("123");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectNum = shopDao.insertShop(shop);
		assertEquals(1, effectNum);
	}
	
	@Test
	public void testUpdate() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		int effectNum = shopDao.updateShop(shop);
		assertEquals(1, effectNum);
	}
}
