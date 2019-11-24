package com.sample.mail.demo.controller;
import org.springframework.web.bind.annotation.RestController;

import com.sample.mail.demo.exception.EmailSendException;
import com.sample.mail.demo.exception.NoMailFoundException;
import com.sample.mail.demo.model.MailObject;
import com.sample.mail.demo.service.MailService;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class MailController {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);
	
	
	@Autowired
	MailService mailService ;
	
	/**
	 * Handle sending mail which save the mail object in Documents
	 *
	 * @param MailObject
	 * @return the string
	 * @throws EmailSendException 
	 */
	
	@RequestMapping("/send" )
	public String senMail(@RequestBody MailObject mailObject) throws  EmailSendException{
		try {
			  mailService.sendMail(mailObject);
		}catch(EmailSendException e) {
			return e.getMessage();
			
		}
		return "Message Send Sucssfully.";
	}
	
	
	@RequestMapping("/findAll" )
	public List<MailObject > findAll() throws NoMailFoundException {
		List<MailObject> list = null;
		try {
			list =  mailService.findAll();
		}catch(NoMailFoundException e) {
			 e.printStackTrace();; 
		}
		return list;
	}
	
	@RequestMapping("/findbyreciever/{recieverName}" )
	public List<MailObject> findAByReciever(@PathVariable String recieverName) throws NoMailFoundException{
		List<MailObject> list = null;
		try {
			list =  mailService.findAllByReciever(recieverName);
		}catch(NoMailFoundException e) {e.getStackTrace();}
		return list;
	}
	
	@RequestMapping("/findbysender/{senderName}" )
	public List<MailObject> findABySender(@PathVariable String senderName) throws NoMailFoundException{
		List<MailObject> list = null;
		try {
			list =  mailService.findAllBySender(senderName);
		}catch(Exception e){e.getStackTrace();}
		return list;
	}
	
	@RequestMapping("/findmailsoftheday" )
	public int findEstimateMailSentByDay() {
		int  numberOfMails =  mailService.estimatedMailByToday();
		return numberOfMails;
	}
	
	@RequestMapping("/findmailsoftheweek" )
	public int findEstimateMailSentByWeek() {
		int numberOfMails =  mailService.estimatedMailByWeek();
		return numberOfMails;
	}
	
	@RequestMapping("/readcontent/{id}" )
	public String findABySentDate(@PathVariable Long id) {
		return mailService.getMessageContentById(id);
	}
}
