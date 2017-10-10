package com.spittr.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.spittr.user.core.UserService;
import com.spittr.websocket.core.NtcService;
import com.spittr.websocket.exception.ParamersErrorException;
import com.spittr.websocket.exception.ParamersRequiredException;
import com.spittr.websocket.model.NtcCLikee;
import com.spittr.websocket.model.NtcCmmnt;
import com.spittr.websocket.model.NtcMLikee;
import com.spittr.websocket.model.NtcMsg;
import com.spittr.websocket.model.NtcRCmmnt;
import com.spittr.websocket.model.NtcRcv;
import com.spittr.websocket.model.NtcType;
import com.spittr.websocket.model.StatusMsg;

@Controller
public class NtcController {

	protected Logger logger = LoggerFactory.getLogger(NtcController.class);
	
	@Autowired
	protected SimpMessagingTemplate msgTplt;
		
	@Autowired
	@Qualifier("userServiceImpl")
	protected UserService userService;
	
	@Autowired
	@Qualifier("ntcServiceImpl")
	protected NtcService ntcService;
	
	@MessageMapping("/ntc/rcv")
	@SendToUser(value="/queue/rcv/status")
	public Object recive(NtcRcv ntcRcv){
		if (ntcRcv.getId()==null || ntcRcv.getNtcType()==null) 
			throw new ParamersRequiredException();
		
		switch(ntcRcv.getNtcType()){
			case cLikee:
				NtcCLikee ntcCLikee =  ntcService.needCLikee(ntcRcv.getId());
				logger.info(ntcCLikee.toString());
				ntcService.rcv(ntcCLikee);
				break;
			case mLikee:
				NtcMLikee ntcMLikee =  ntcService.needMLikee(ntcRcv.getId());
				logger.info(ntcMLikee.toString());
				ntcService.rcv(ntcMLikee);
				break;
			case Cmmnt:
				NtcCmmnt ntcCmmnt = ntcService.needCmmnt(ntcRcv.getId());
				logger.info(ntcCmmnt.toString());
				ntcService.rcv(ntcCmmnt);
				break;
			case rCmmnt:
				NtcRCmmnt ntcRCmmnt = ntcService.needRCmmnt(ntcRcv.getId());
				logger.info(ntcRCmmnt.toString());
				ntcService.rcv(ntcRCmmnt);
				break;
			default:
				throw new ParamersErrorException();
			}
		return StatusMsg.OK(String.valueOf(ntcRcv.getId()));
	}
	
}
