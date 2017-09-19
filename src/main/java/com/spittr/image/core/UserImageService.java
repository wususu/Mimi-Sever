package com.spittr.image.core;

import com.spittr.image.model.UserImage;
import com.spittr.user.model.User;

public interface UserImageService {

	public static UserImage newInstance(String realPath, String webPath, User user){
		return new UserImage(realPath, webPath, user);
	}
	
	UserImage saveImageFromBase64(String fatherPath, String base64String, User user);
	
	void update(UserImage userImage, String realPath, String webPath);
	
	UserImage get(User user);
}
