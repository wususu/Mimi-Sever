package com.spittr.image.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.spittr.config.StaticConfig;
import com.spittr.image.ImageFormatErrorException;


public class ImageBase64Utils {
	
	public static final String IMAGE_DIRECTORY = StaticConfig.DEFAULT_IMAGE_DIRECTORY;
	
	
	
	public static void formatBase64StringToImage(String base64String, String path){
		byte[] imageByte = Base64.decodeBase64(base64String);
		try {
			FileUtils.writeByteArrayToFile(new File(path), imageByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ImageFormatErrorException();
		}
	}
	
}
