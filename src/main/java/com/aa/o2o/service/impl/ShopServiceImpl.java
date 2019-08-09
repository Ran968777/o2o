package com.aa.o2o.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aa.o2o.dao.ShopDao;
import com.aa.o2o.dto.ShopExecution;
import com.aa.o2o.entity.Shop;
import com.aa.o2o.enums.ShopStateEnum;
import com.aa.o2o.service.ShopService;
import com.aa.o2o.utils.ImageUtil;
import com.aa.o2o.utils.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}

		try {
			//赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectNum = shopDao.insertShop(shop);
			if (effectNum < 0) {
				throw new RuntimeException("店铺创建失败");
			}else {
				if (shopImg != null) {
					//存储图片
					
					try {
						addShopImg(shop,shopImg);  
					} catch (Exception e) {
						throw new RuntimeException("addShopImg error"+e.getMessage());
					}
					//更新店铺的地址
					effectNum = shopDao.updateShop(shop);
					if (effectNum<0) {
						throw new RuntimeException("更新图片失败");
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("addShop erroe" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}	

}
