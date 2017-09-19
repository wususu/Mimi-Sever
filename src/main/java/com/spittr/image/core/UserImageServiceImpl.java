package com.spittr.image.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.image.dao.UserImageDao;
import com.spittr.image.exception.CreateImageErrorException;
import com.spittr.image.model.UserImage;
import com.spittr.user.core.UserService;
import com.spittr.user.model.User;

@Service
public class UserImageServiceImpl implements UserImageService{

	@Autowired
	@Qualifier("userImageDaoImpl")
	private UserImageDao userImageDao;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Override
	@Transactional
	public UserImage saveImageFromBase64(String fatherPath, String base64String, User user) {
		// TODO Auto-generated method stub
		UserImage userImage = get(user);
		
		String childDir = ImageIssues.generateChildDir(new Date());
		String realPath = ImageIssues.generateImagePath(fatherPath, childDir);
		String webPath = ImageIssues.generateWebPath(childDir);

		if (ImageIssues.saveImage(realPath, base64String)){
			if (userImage == null) {
				userImage = UserImageService.newInstance(realPath, webPath, user);
				userImageDao.save(userImage);
				user.setUserPic(userImage);
				userService.update(user);
			}else {
				update(userImage, realPath, webPath);
			}
			
			return userImage;
		}else {
			throw new CreateImageErrorException();
		}
	}

	@Override
	@Transactional
	public void update(UserImage userImage, String realPath, String webPath) {
		// TODO Auto-generated method stub
		userImage.setRealPath(realPath);
		userImage.setWebPath(webPath);
		userImage.setTmCreated(new Date());
		
		userImageDao.update(userImage);
		return ;
	}

	@Override
	public UserImage get(User user) {
		// TODO Auto-generated method stub
		UserImage userImage = userImageDao.get(user);
		return userImage;
	}
}
