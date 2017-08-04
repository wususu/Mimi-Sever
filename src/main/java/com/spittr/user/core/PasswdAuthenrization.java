package com.spittr.user.core;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.user.dao.PasswdDao;
import com.spittr.user.exception.PasswdErrorException;
import com.spittr.user.exception.UserNotFoundException;
import com.spittr.user.model.Passwd;

@Service
public class PasswdAuthenrization {

	private static final int SALT_LENGTH = 32;
	
	private static final String SECRET_TYPE = "SHA-1";
	
	@Autowired
	@Qualifier("passwdDaoImpl")
	private PasswdDao passwdDao;

	
	@Transactional
	public Passwd passwd(String uname, String passwd){
		Passwd passCred = passwdDao.passwdCreat(uname);
		
		byte[] buf = strToByteArray(uname + passwd);
		byte[] salt = getSalt();
		byte[] pass = generatePasswd(salt, buf);

		passCred.setSalt(salt);
		passCred.setUpass(pass);
		
		passwdDao.save(passCred);
		
		return passCred;
	}
	
	public Passwd authenrization(String name, String passwd){
		Passwd passwdCred = passwdDao.isPasswd(name);
		if (passwdCred == null) {
			throw new UserNotFoundException(name);
		}
		
		byte[] salt = passwdCred.getSalt();
		byte[] upasswd = passwdCred.getUpass();
		byte[] bpasswd = generatePasswd(salt, strToByteArray(name + passwd));

		if (!Arrays.equals(upasswd, bpasswd)) {
			throw new PasswdErrorException();
		}
		return passwdCred;
	}
	
	private byte[] generatePasswd(byte[] salt, byte[] bPasswd){
		byte[] buf = ByteBuffer.allocate(salt.length + bPasswd.length)
		.put(salt)
		.put(bPasswd)
		.array();
		
		return SHA1Digest(buf);
	}
	
	private byte[] getSalt() {
		byte[] bs = new byte[SALT_LENGTH];
		(new Random()).nextBytes(bs);
		return bs;
	}
	
	private byte[] SHA1Digest(byte[] in){
		try{
			MessageDigest md = MessageDigest.getInstance(SECRET_TYPE);
			byte[] out = md.digest(in);
			return out;
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	private byte[] strToByteArray(String str){
		if (str == null) {
			return null;
		}
		return str.getBytes();
	}
}
