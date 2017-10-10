package com.spittr.websocket.core;

import java.util.List;

import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.message.model.MLikee;
import com.spittr.websocket.model.NtcBody;
import com.spittr.websocket.model.NtcCLikee;
import com.spittr.websocket.model.NtcCmmnt;
import com.spittr.websocket.model.NtcMLikee;
import com.spittr.websocket.model.NtcMsg;
import com.spittr.websocket.model.NtcRCmmnt;

public interface NtcService {

	NtcMLikee needMLikee(Long id);
	
	NtcCLikee needCLikee(Long id);
	
	NtcCmmnt needCmmnt(Long id);
	
	NtcRCmmnt needRCmmnt(Long id);

	
	
	NtcMLikee getNtcMLikee(MLikee mLikee);
	
	NtcCLikee getNtcCLikee(CLikee cLikee);
	
	NtcCmmnt getNtcCmmnt(Comment cmmnt);
	
	NtcRCmmnt getNtcRCmmnt(Comment cmmnt);
	
	
	NtcMLikee needNtcMLikee(MLikee mLikee);
	
	NtcCLikee needNtcCLikee(CLikee cLikee);
	
	NtcCmmnt needNtcCmmnt(Comment cmmnt);
	
	NtcRCmmnt needNtcRCmmnt(Comment comment);
	
	
	void create(NtcBody ntcBody);
	
	void create(NtcCLikee ntcCLikee);
	
	void update(MLikee mLikee, NtcMLikee ntcMLikee);
	
	void update(CLikee cLikee, NtcCLikee ntcCLikee);
	
	void update(NtcCmmnt ntcCmmnt);
	
	void update(NtcRCmmnt ntcRCmmnt);
	
	
	void rcv(NtcMLikee ntcMLikee);
	
	void rcv(NtcCLikee ntcCLikee);
	
	void rcv(NtcCmmnt ntcCmmnt);
	
	void rcv(NtcRCmmnt ntcRCmmnt);
	
	
	List<NtcMsg> getNtcNotRcv(long uid);
	
	List<NtcMLikee> getNtcMLikeeNotRcv(long mUid);
	
	List<NtcCLikee> getNtcCLikeeNotRcv(long cUid);
	
	List<NtcCmmnt> getNtcCmmntNotRcv(long mUid);
	
	List<NtcRCmmnt> getNtcRCmmntNotRcv(long rcUid);
}
