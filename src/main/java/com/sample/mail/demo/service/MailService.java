package com.sample.mail.demo.service;

import java.util.List;

import com.sample.mail.demo.exception.NoMailFoundException;
import com.sample.mail.demo.exception.EmailSendException;
import com.sample.mail.demo.model.MailObject;

public interface MailService {
	
	public MailObject sendMail(MailObject mailObject) throws EmailSendException;
	
	public List<MailObject> findAll() throws NoMailFoundException;
	
	public MailObject find(Long id) throws NoMailFoundException;
	
	public List<MailObject> findAllBySender(String senderId) throws NoMailFoundException;
	
	public List<MailObject> findAllByReciever(String recieverId) throws NoMailFoundException ;
	
	public int  estimatedMailByToday();
	
	public int estimatedMailByWeek();
	
	public String getMessageContentById(Long id);
	 

}
