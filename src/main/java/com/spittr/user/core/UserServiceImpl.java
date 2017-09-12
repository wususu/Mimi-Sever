package com.spittr.user.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;
import com.spittr.user.dao.UserDao;
import com.spittr.user.exception.*;
import com.spittr.user.model.Passwd;
import com.spittr.user.model.User;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("mySQLTokenManager")
	private TokenManager tokenManager;
	
	@Autowired
	private PasswdAuthenrization passwdAuthenrization;
	
	@Override
	@Transactional
	public void create(String uname, String nname, String passwd){
		User user = null;
		
		user = userDao.get(uname);
		if (user  != null) 
			throw new UserNameAlreadyExistException(uname);
		user = userDao.getByNname(nname);
		if (user != null) 
			throw new NickNameAlreadyExistException(nname);
		
		user = UserService.newInstance(uname, nname);
		
		this.save(user);
		
		Passwd passwdCred = passwdAuthenrization.passwd(uname, passwd);
		if (user == null || passwdCred == null) {
			throw new UserNotFoundException(uname);
		}
	}
	
	@Override
	public Token login(String uname, String passwd){
		 Passwd passwdCred = passwdAuthenrization.authenrization(uname, passwd);
		 User user = passwdCred.getUser();

		 if (user.getPlogin().equals(false)) {
			throw new UserNotFoundException(uname);
		}
		 // 生成taken
		 Token token = tokenManager.createToken(user);
		 
		 return token;
	}
	
	@Override
	public void logout(User user){
		tokenManager.deleteTokens(user.getUid());
	}
	
	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return userDao.get(User.class, id);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		user.setPlogin(false);
		this.update(user);
	}

	@Override
	public User get(String uname) throws UserNotFoundException{
		// TODO Auto-generated method stub
		User user = userDao.get(uname);
		if (user == null) 
			throw new UserNotFoundException(uname);
		return user;
	}
}
