package com.spittr.message.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spittr.message.exception.AuthorityNotAllowException;
import com.spittr.message.exception.CommentNotFoundException;
import com.spittr.message.exception.SourceDataErrorException;
import com.spittr.message.exception.UnderMessageNotEqualException;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;
import com.sun.org.apache.bcel.internal.generic.I2F;


public class CommentIssue {

	private static CommentIssue instance = new CommentIssue();;
	
	public static CommentIssue getInstance(){
		return instance;
	}
	
	private CommentIssue() {
		// TODO Auto-generated constructor stub
	}

	// 添加回复的评论的具体信息
	public static void generateDetailComment(Comment comment){
		if (comment == null) 
			throw new SourceDataErrorException();
		
		if (comment.getReplayWhichComment() == null) 
			return ;
		
		Comment rComment = null;
		User rUser = null;

		if (comment.getRcid() == null || comment.getRcuid() == null || comment.getRcUname() == null || comment.getRcNname() == null) {
			rComment = comment.getReplayWhichComment();
			rUser = rComment.getUser();
			
			comment.setRcid(rComment.getCid());
			comment.setRcuid(rUser.getUid());
			comment.setRcUname(rUser.getUname());
			comment.setRcNname(rUser.getNname());
		}
			
	}
	
	public static Comment generateIsFake(Comment comment, Boolean isFake){
		if (isFake != null && isFake.equals(true)) {
			comment.setFake(true);
			comment.setFakeName("匿名");
		}
		return comment;
	}
	
	public static void checkIsDelete(Comment comment) {
		if (comment == null || comment.isDelete()) 
			throw new CommentNotFoundException();
		
	}
	
	public static  Comment generateFakeComment(Comment comment){
		if (comment.isFake()) {}
			try {
				comment = (Comment)DynamicFilter.getInstance().addFilteFields("user").filter(comment);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return comment;
	}
	
	public static  Comment generateFakeComment(Comment comment, DynamicFilter jsonObjFilter){
		if (comment.isFake()) {
			try {
				comment = (Comment)jsonObjFilter.filter(comment);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return comment;
	}
	
	public static List<Comment> generateFakeCommentList(List<Comment> commentList){
		List<Comment> fakeCommentList = new ArrayList<>();
		for (Comment comment : commentList) {
			fakeCommentList.add(generateFakeComment(comment));
		}
		return fakeCommentList;
	}
	
	public static List<Comment> generateFakeCommentList(List<Comment> commentList, DynamicFilter jsonObjFilter){
		List<Comment> fakeCommentList = new ArrayList<>();
		for (Comment comment : commentList) {
			fakeCommentList.add(generateFakeComment(comment, jsonObjFilter));
		}
		return fakeCommentList;
	}
	
	public static Boolean checkReplayComment(Comment replayComment, Message underMessage){
		
		checkIsDelete(replayComment);

		if( replayComment.getUnderWhichMessage().getMid() != underMessage.getMid())
			throw new UnderMessageNotEqualException();
		
		return true;
	}
	
	public static Boolean checkAuthority(Comment comment, User login){
		if (comment.getUser() == null || login == null) 
			throw new NullPointerException();
		
		if (!comment.getUser().equals(login)) 
			throw new AuthorityNotAllowException();
		
		return true;
	}

}
