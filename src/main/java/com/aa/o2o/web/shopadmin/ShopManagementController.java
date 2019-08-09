package com.aa.o2o.web.shopadmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aa.o2o.dto.ShopExecution;
import com.aa.o2o.entity.PersonInfo;
import com.aa.o2o.entity.Shop;
import com.aa.o2o.enums.ShopStateEnum;
import com.aa.o2o.service.ShopService;
import com.aa.o2o.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	ShopService shopService;
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		//接受并转化响应的参数,包括店铺信息和图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr,shop.getClass());
		} catch (Exception e) {
			resMap.put("success", false);
			resMap.put("errMsg",e.getMessage());
			return resMap;
		} 
		
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver =
				new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");	
		}else {
			resMap.put("success", false);
			resMap.put("errMsg","图片为空");
			return resMap;
		}
		
		//注册店铺
		if (shop!=null&&shopImg!=null) {
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);  
			ShopExecution se = shopService.addShop(shop, shopImg);
			if (se.getState() == ShopStateEnum.CHECK.getState()) {
				resMap.put("success", true);
			}else {
				resMap.put("success", false);
				resMap.put("errMsg", se.getStateInfo());
			}
			return resMap;
		}else {
			resMap.put("success", false);
			resMap.put("errMsg","店铺信息为空");
			return resMap;
		}
		
		
	}

}
