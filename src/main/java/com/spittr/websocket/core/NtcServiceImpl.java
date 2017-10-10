package com.spittr.websocket.core;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spittr.message.model.*;
import com.spittr.websocket.dao.NtcDao;
import com.spittr.websocket.exception.*;
import com.spittr.websocket.model.*;

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
	public NtcRCmmnt needRCmmnt(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			throw new ParamersErrorException();
		}
		NtcRCmmnt ntcRCmmnt = ntcDao.getNtcRCmmnt(id);
		if (ntcRCmmnt == null) 			
			throw new NtcIDNotFoundException();
		return ntcRCmmnt;
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
	public NtcRCmmnt getNtcRCmmnt(Comment cmmnt) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcRCmmnt(cmmnt.getCid(), cmmnt.getRcid());
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
	public NtcRCmmnt needNtcRCmmnt(Comment comment) {
		// TODO Auto-generated method stub
		NtcRCmmnt ntcRCmmnt = getNtcRCmmnt(comment);
		if (ntcRCmmnt == null) 
			throw new NullValueException();
		return ntcRCmmnt;
	}
	
	@Override
	public void create(NtcBody ntcBody) {
		// TODO Auto-generated method stub
		System.err.println(ntcBody);
		ntcDao.save(ntcBody);
	}
	
	@Override
	public void create(NtcCLikee ntcCLikee){
		ntcDao.save(ntcCLikee);
	}


	@Override
	public void update(MLikee mLikee, NtcMLikee ntcMLikee) {
		// TODO Auto-generated method stub

		if (mLikee.isLike() == false && ntcMLikee.getIsRecived() == true) {
			if (ntcMLikee.getLkUid() != ntcMLikee.getmUid()) 
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
		if (cLikee.isLike() == false && ntcCLikee.getIsRecived() == true ) {
			// 不是同一用户
			if (ntcCLikee.getcUid() != ntcCLikee.getLkUid())
				ntcCLikee.setIsRecived(false);
			
			ntcCLikee.setTmRecived(null);
			ntcDao.update(ntcCLikee);
		}
	}

	@Override
	public void update(NtcCmmnt ntcCmmnt) {
		// TODO Auto-generated method stub
		ntcDao.update(ntcCmmnt);
	}

	@Override
	public void update(NtcRCmmnt ntcRCmmnt) {
		// TODO Auto-generated method stub
		ntcDao.update(ntcRCmmnt);
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

	@Override
	public void rcv(NtcRCmmnt ntcRCmmnt) {
		// TODO Auto-generated method stub
		ntcRCmmnt.setIsRecived(true);
		ntcRCmmnt.setTmRecived(new Date());
		ntcDao.update(ntcRCmmnt);
	}

	@Override
	public List<NtcMLikee> getNtcMLikeeNotRcv(long mUid) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcMLikeeNotRcv(mUid);
	}

	@Override
	public List<NtcCLikee> getNtcCLikeeNotRcv(long cUid) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcCLikeeNotRcv(cUid);
	}

	@Override
	public List<NtcCmmnt> getNtcCmmntNotRcv(long mUid) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcCmmntNotRcv(mUid);
	}

	@Override
	public List<NtcRCmmnt> getNtcRCmmntNotRcv(long rcUid) {
		// TODO Auto-generated method stub
		return ntcDao.getNtcRCmmntNotRcv(rcUid);
	}
	
	@Override
	public List<NtcMsg> getNtcNotRcv(long uid){
		List<NtcMsg> ntcMsgs = new LinkedList<>();
		for (NtcMLikee ntcMLikee : getNtcMLikeeNotRcv(uid)) 
			ntcMsgs.add(new NtcMsg(NtcType.mLikee, ntcMLikee));
		
		for (NtcCLikee ntcCLikee : getNtcCLikeeNotRcv(uid)) 
			ntcMsgs.add(new NtcMsg(NtcType.cLikee, ntcCLikee));
		
		for(NtcCmmnt ntcCmmnt : getNtcCmmntNotRcv(uid))
			ntcMsgs.add(new NtcMsg(NtcType.Cmmnt, ntcCmmnt));
		
		for(NtcRCmmnt ntcRCmmnt : getNtcRCmmntNotRcv(uid))
			ntcMsgs.add(new NtcMsg(NtcType.rCmmnt, ntcRCmmnt));
		
		logger.info(ntcMsgs.toString());
		logger.info(ntcMsgs.size() + " ");
		return ntcMsgs;
	}

}
