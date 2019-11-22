package com.sample.mail.demo.controller;
import org.springframework.web.bind.annotation.RestController;

import com.sample.mail.demo.model.MailObject;
import com.sample.mail.demo.service.MailService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class MailController {
	
	@Autowired
	MailService mailService ;
	
	@RequestMapping("/send" )
	public String senMail(@RequestBody MailObject mailObject) {
		MailObject obj =  mailService.sendMail(mailObject);
		if(obj.getId() > 0) {
			return "Sucessfully Send";
		}
		return "System Failure";
	}
	
	
	@RequestMapping("/findAll" )
	public List<MailObject > findAll() {
		List<MailObject> list =  mailService.findAll();
		return list;
	}
	
	@RequestMapping("/findbyreciever/{recieverName}" )
	public List<MailObject> findAByReciever(@PathVariable String recieverName) {
		List<MailObject> list =  mailService.findAllByReciever(recieverName);
		return list;
	}
	
	@RequestMapping("/findbysender/{senderName}" )
	public List<MailObject> findABySender(@PathVariable String senderName) {
		List<MailObject> list =  mailService.findAllBySender(senderName);
		return list;
	}
	
	@RequestMapping("/findmailsoftheday" )
	public int findABySentDate() {
		int  numberOfMails =  mailService.etimatedMailByToday(LocalDate.now());
		return numberOfMails;
	}
	
	@RequestMapping("/findmailsoftheweek" )
	public int findMailsByWeek() {
		int numberOfMails =  mailService.etimatedMailByWeek(LocalDate.now().plusDays(1));
		return numberOfMails;
	}
	
	@RequestMapping("/readcontent/{id}" )
	public String findABySentDate(@PathVariable Long id) {
		return mailService.getMessageContentById(id);
	}
}
