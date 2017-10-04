package com.spittr.websocket.core;

import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.message.model.MLikee;
import com.spittr.websocket.model.NtcBody;
import com.spittr.websocket.model.NtcCLikee;
import com.spittr.websocket.model.NtcCmmnt;
import com.spittr.websocket.model.NtcMLikee;

public interface NtcService {

	NtcMLikee needMLikee(Long id);
	
	NtcCLikee needCLikee(Long id);
	
	NtcCmmnt needCmmnt(Long id);
	
	
	NtcMLikee getNtcMLikee(MLikee mLikee);
	
	NtcCLikee getNtcCLikee(CLikee cLikee);
	
	NtcCmmnt getNtcCmmnt(Comment cmmnt);
	
	
	NtcMLikee needNtcMLikee(MLikee mLikee);
	
	NtcCLikee needNtcCLikee(CLikee cLikee);
	
	NtcCmmnt needNtcCmmnt(Comment cmmnt);
	
	
	void create(NtcBody ntcBody);
	
	void update(MLikee mLikee, NtcMLikee ntcMLikee);
	
	void update(CLikee cLikee, NtcCLikee ntcCLikee);
	
	void update(NtcCmmnt ntcCmmnt);
	
	
	void rcv(NtcMLikee ntcMLikee);
	
	void rcv(NtcCLikee ntcCLikee);
	
	void rcv(NtcCmmnt ntcCmmnt);
}
