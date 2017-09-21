package com.spittr.image.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.Authorization;
import com.spittr.authorization.annotation.AutoCurrentUser;
import com.spittr.config.StaticConfig;
import com.spittr.image.core.ImageIssues;
import com.spittr.image.core.MessageImageService;
import com.spittr.image.core.UserImageService;
import com.spittr.image.model.MessageImage;
import com.spittr.image.model.UserImage;
import com.spittr.message.core.MessageService;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.UserService;
import com.spittr.user.model.User;


@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value="/api/image")
public class ImageController {
	
	@Autowired
	@Qualifier("messageImageServiceImpl")
	private MessageImageService messageImageService;
	
	@Autowired
	@Qualifier("userImageServiceImpl")
	private UserImageService userImageService;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	@Authorization
	public ReturnModel createMessageImage(
			@AutoCurrentUser User user,
			@RequestParam("image") String image,
			HttpServletRequest request
			) throws IOException{
		
		String imageDirPath = request.getSession().getServletContext().getRealPath(StaticConfig.DEFAULT_IMAGE_DIRECTORY);
		String appRootDir = request.getServletContext().getContextPath();
		
		String webPath = messageImageService.saveImageFromBase64(imageDirPath, image);
		MessageImage messageImage = messageImageService.get(webPath);

		ImageIssues.<MessageImage>formatImagePath(messageImage, appRootDir);
		return ReturnModel.SUCCESS(messageImage);
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	@Authorization
	public ReturnModel createUserImage(
			@AutoCurrentUser User user,
			@RequestParam("image") String image,
			HttpServletRequest request
			){
		
		String imageDirPath = request.getSession().getServletContext().getRealPath(StaticConfig.DEFAULT_IMAGE_DIRECTORY);
		String appRootDir = request.getServletContext().getContextPath();
		
		UserImage userImage = userImageService.saveImageFromBase64(imageDirPath, image, user);

		ImageIssues.<UserImage>formatImagePath(userImage, appRootDir);
		return ReturnModel.SUCCESS(userImage);
	}
	
}
