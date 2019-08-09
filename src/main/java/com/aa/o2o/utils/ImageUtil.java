package com.aa.o2o.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private  static final Random RANDOM = new Random(); 
		
	public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;//相对地址
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/fengche.png")), 0.25f)
			.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return relativeAddr;
	}
	
	/**
	 * 创建目标路径所涉及到的目录，即/home/work/xiangze/xx.jpg,
	 * 那么home woek xiangze 这三个目录都要创建出来
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFilePath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFilePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(CommonsMultipartFile cFile) {
		String originalFileNameString = cFile.getOriginalFilename();
		
		return originalFileNameString.substring(originalFileNameString.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * @return
	 */
	private static String getRandomFileName() {
		//获取随机五位数
		int rannum = RANDOM.nextInt(89999)+10000;
		String newTimeStr = sDATE_FORMAT.format(new Date());
		return newTimeStr+rannum;
	}

	public static void main(String[] args) throws Exception {
		
		Thumbnails.of(new File("D:\\Pictures\\faer.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basePath + "/fengche.png")), 0.25f)
				.outputQuality(0.8f).toFile("D:\\Pictures\\faern1ew.jpg");

	}
}
