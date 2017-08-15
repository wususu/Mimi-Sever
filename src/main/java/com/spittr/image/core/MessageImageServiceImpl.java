package com.spittr.image.core;

import static com.spittr.image.DateTimeUtils.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.image.dao.MessageImageDao;
import com.spittr.image.exception.ImageNotFoundException;
import com.spittr.image.model.MessageImage;


@Service
public class MessageImageServiceImpl implements MessageImageService{

	@Autowired
	@Qualifier("messageImageDaoImpl")
	private MessageImageDao messageImageDao;
	
	@Override
	public String saveImageFromBase64(String fatherPath, String base64String){
		Date date = newDate();
		String childDir = ImageIssues.generateChildDir(date);
		String realPath = ImageIssues.generateImagePath(fatherPath, childDir);
		String webPath = ImageIssues.generateWebPath(childDir);
		if(ImageIssues.saveImage(realPath, base64String)){
			// save 
			MessageImage messageImage = MessageImageService.newInstance(realPath, webPath);
			messageImageDao.save(messageImage);
		}
		return webPath;
	}
	
	@Override
	public MessageImage get(String webPath){
		MessageImage messageImage = messageImageDao.get(webPath);
		if (messageImage == null) 
			throw new ImageNotFoundException(webPath);
		
		return messageImage;
	}
	
	@Override
	public MessageImage get(Long imageid){
		MessageImage messageImage = messageImageDao.get(MessageImage.class, imageid);
		if (messageImage == null) 
			throw new ImageNotFoundException();
		return messageImage;
	}

	
	@Override
	public List<MessageImage> getByMid(Long mid) {
		// TODO Auto-generated method stub
		List<MessageImage> messageImageList = messageImageDao.getByMid(mid);
		return messageImageList;
	}

	@Override
	public void save(MessageImage messageImage) {
		// TODO Auto-generated method stub
		messageImageDao.save(messageImage);
	}

	@Override
	@Transactional
	public void delete(MessageImage messageImage) {
		// TODO Auto-generated method stub
		messageImage.setIsDelete(true);
		messageImage.setTmDelete(new Date());
		messageImageDao.update(messageImage);
	}
}
