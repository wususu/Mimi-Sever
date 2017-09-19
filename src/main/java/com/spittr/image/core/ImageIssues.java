package com.spittr.image.core;

import static com.spittr.image.DateTimeUtils.*;

import java.io.File;
import java.util.Date;

import com.spittr.config.StaticConfig;
import com.spittr.image.exception.ImageAlreadyBeUsedException;
import com.spittr.image.exception.ImageNotFoundException;
import com.spittr.image.model.MessageImage;
import com.spittr.image.model.UserImage;

public class ImageIssues {
	
	private ImageIssues(){
		throw new AssertionError();
	}
	
	public static String generateImagePath(String fatherDir, String childDir){
		String path = fatherDir  +  childDir;
		return path;
	}
	
	public static String generateChildDir(Date date){
		 return File.separatorChar + getDate(date) + File.separatorChar + generateImageName(date);
	}
	
	private static String generateImageName(Date date){
		return  getTime(date) +  "_" +date.getTime() + ".png";
	}
	
	public static String generateWebPath(String childDir){
		return  StaticConfig.DEFAULT_IMAGE_DIRECTORY + childDir;
	}
	
	public static boolean saveImage(String path, String base64String){
		ImageBase64Utils.formatBase64StringToImage(base64String, path);
		return true;
	}
	
	public static void formatMessageImagePath(MessageImage messageImage, String appRootDir){
		messageImage.setWebPath(appRootDir + messageImage.getWebPath());
		return ;
	}
	
	public static void formatUserImagePath(UserImage userImage, String appRootDir){
		userImage.setWebPath(appRootDir + userImage.getWebPath());
		return ;
	}
	
	public static Boolean isImageCanUse(MessageImage messageImage){
		if (messageImage.getIsDelete()) 
			throw new ImageNotFoundException();
		
		if (messageImage.getMessage() != null || messageImage.getMid() != null) {
			if (messageImage.getMessage().isDelete()) {
				messageImage.setIsDelete(true);
				messageImage.setTmDelete(new Date());
				throw new ImageNotFoundException(); 
			}
			throw new ImageAlreadyBeUsedException();
		}
		
		return true;
	}
	
}
