package com.spittr.websocket.dao;

import com.spittr.dao.BaseDao;
import com.spittr.websocket.model.*;

public interface NtcDao extends BaseDao<NtcBody>{
	
	NtcMLikee getNtcMLikee(long id);
	
	NtcCLikee getNtcCLikee(long id);
	
	NtcCmmnt getNtcCmmnt(long id);
	
	NtcMLikee getNtcMLikee(Long mid, Long lkUid);
	
	NtcCLikee getNtcCLikee(Long mid, Long lkUid);
	
	NtcCmmnt getNtcCmmnt(Long mid, Long lkUid);
}
