package com.spittr.image.dao;

import com.spittr.dao.BaseDao;
import com.spittr.image.model.UserImage;
import com.spittr.user.model.User;

public interface UserImageDao extends BaseDao<UserImage>{
	UserImage get(User user);
}
