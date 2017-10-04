package com.spittr.websocket.core;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.message.model.MLikee;
import com.spittr.websocket.dao.NtcDao;
import com.spittr.websocket.exception.NtcIDNotFoundException;
import com.spittr.websocket.exception.NullValueException;
import com.spittr.websocket.exception.ParamersErrorException;
import com.spittr.websocket.model.NtcBody;
import com.spittr.websocket.model.NtcCLikee;
import com.spittr.websocket.model.NtcCmmnt;
import com.spittr.websocket.model.NtcMLikee;

@Service
public class NtcServiceImpl implements NtcService{

	protected Logger logger = LoggerFactory.getLogger(NtcServiceImpl.class);
	
	@Autowired
	@Qualifier("ntcDaoImpl")
	protected NtcDao ntcDao;
	
	@Override
	public NtcMLikee needMLikee(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			throw new ParamersErrorException();
		}
		NtcMLikee ntcMlk = ntcDao.getNtcMLikee(id);
		if (ntcMlk == null) 			
			throw new NtcIDNotFoundException();
		return ntcMlk;
	} 

	@Override
	public NtcCLikee needCLikee(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			throw new ParamersErrorException();
		}
		NtcCLikee ntcClk = ntcDao.getNtcCLikee(id);
		if (ntcClk == null) 			
			throw new NtcIDNotFoundException();
		return ntcClk;
	}

	@Override
	public NtcCmmnt needCmmnt(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			throw new ParamersErrorException();
		}
		NtcCmmnt ntcCmnt = ntcDao.getNtcCmmnt(id);
		if (ntcCmnt == null) 			
			throw new NtcIDNotFoundException();
		return ntcCmnt;
	}

	
	@Override
	public NtcMLikee getNtcMLikee(MLikee mLikee) {
		// TODO Auto-generated method stub
		return	ntcDao.getNtcMLikee(mLikee.getMessage().getMid(), mLikee.getUser().getUid());
	}

	@Override
	public NtcCLikee getNtcCLikee(CLikee cLikee) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcCLikee(cLikee.getComment().getCid(), cLikee.getUser().getUid());
	}

	@Override
	public NtcCmmnt getNtcCmmnt(Comment cmmnt) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcCmmnt(cmmnt.getCid(), cmmnt.getUnderWhichMessage().getUid());
	}

	
	@Override
	public NtcMLikee needNtcMLikee(MLikee mLikee) {
		// TODO Auto-generated method stub
		NtcMLikee ntcMLikee = getNtcMLikee(mLikee);
		if (ntcMLikee == null) 
			throw new NullValueException();
		return ntcMLikee;
	}

	@Override
	public NtcCLikee needNtcCLikee(CLikee cLikee) {
		// TODO Auto-generated method stub
		NtcCLikee ntcCLikee = getNtcCLikee(cLikee);
		if (ntcCLikee == null) 
			throw new NullValueException();
		return ntcCLikee;
	}

	@Override
	public NtcCmmnt needNtcCmmnt(Comment cmmnt) {
		// TODO Auto-generated method stub
		NtcCmmnt ntcCmmnt = getNtcCmmnt(cmmnt);
		if (ntcCmmnt == null) 
			throw new NullValueException();
		return ntcCmmnt;
	}
	
	@Override
	public void create(NtcBody ntcBody) {
		// TODO Auto-generated method stub
		ntcDao.save(ntcBody);
	}

	@Override
	public void update(MLikee mLikee, NtcMLikee ntcMLikee) {
		// TODO Auto-generated method stub

		if (mLikee.isLike() == false && ntcMLikee.getIsRecived() == true) {
			ntcMLikee.setIsRecived(false);
			ntcMLikee.setTmRecived(null);
			ntcDao.update(ntcMLikee);
		}
	}

	@Override
	public void update(CLikee cLikee, NtcCLikee ntcCLikee) {
		// TODO Auto-generated method stub
		logger.info(cLikee.toString());
		logger.info(ntcCLikee.toString());
		if (cLikee.isLike() == false && ntcCLikee.getIsRecived() == true) {
			ntcCLikee.setIsRecived(false);
			ntcCLikee.setTmRecived(null);
			ntcDao.update(ntcCLikee);
		}
	}

	@Override
	public void update(NtcCmmnt ntcCmmnt) {
		// TODO Auto-generated method stub
		update(ntcCmmnt);
	}

	@Override
	public void rcv(NtcMLikee ntcMLikee) {
		// TODO Auto-generated method stub
		ntcMLikee.setIsRecived(true);
		ntcMLikee.setTmRecived(new Date());
		ntcDao.update(ntcMLikee);
	}
	
	@Override
	public void rcv(NtcCLikee ntcCLikee) {
		// TODO Auto-generated method stub
		ntcCLikee.setIsRecived(true);
		ntcCLikee.setTmRecived(new Date());
		ntcDao.update(ntcCLikee);
	}

	@Override
	public void rcv(NtcCmmnt ntcCmmnt) {
		// TODO Auto-generated method stub
		ntcCmmnt.setIsRecived(true);
		ntcCmmnt.setTmRecived(new Date());
		ntcDao.update(ntcCmmnt);
	}


}
