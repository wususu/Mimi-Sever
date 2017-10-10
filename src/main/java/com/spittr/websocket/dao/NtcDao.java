package com.spittr.websocket.dao;

import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.websocket.model.*;

public interface NtcDao extends BaseDao<NtcBody>{
	
	NtcMLikee getNtcMLikee(long id);
	
	NtcCLikee getNtcCLikee(long id);
	
	NtcCmmnt getNtcCmmnt(long id);
	
	NtcRCmmnt getNtcRCmmnt(long id);
	
	
	NtcMLikee getNtcMLikee(Long mid, Long lkUid);
	
	NtcCLikee getNtcCLikee(Long mid, Long lkUid);
	
	NtcCmmnt getNtcCmmnt(Long mid, Long cid);
	
	NtcRCmmnt getNtcRCmmnt(Long  cid, Long rcid);
	
	
	List<NtcMLikee> getNtcMLikeeNotRcv(long mUid);
	
	List<NtcCLikee> getNtcCLikeeNotRcv(long cUid);
	
	List<NtcCmmnt> getNtcCmmntNotRcv(long mUid);
	
	List<NtcRCmmnt> getNtcRCmmntNotRcv(long rcUid);
}
