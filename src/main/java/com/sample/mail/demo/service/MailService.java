package com.sample.mail.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mail.demo.model.MailObject;
import com.sample.mail.demo.repository.MailObjectRepository;

@Service
public class MailService {
	
	@Autowired
	MailObjectRepository mailRepository ;
	
	
	public MailObject sendMail(MailObject mailObject) {
		   mailObject.setDate(LocalDate.now());
	return mailRepository.insert(mailObject);
		
	}
	
	public List<MailObject> findAll(){
		return mailRepository.findAll();
	}
	
	public MailObject find(Long id){
		return	mailRepository.findAll().stream().
						filter(mailObject -> mailObject.getId()==id)
						.findAny().get();
	}
	
     public List<MailObject> findAllBySender(String senderId){	
		return mailRepository.findAll().stream()
						.filter(mailObject -> senderId
						.equals(mailObject.getSenderId())).collect(Collectors.toList());
	}
     
     public List<MailObject> findAllByReciever(String recieverId){		
 		return mailRepository.findAll().stream()
 						.filter(mailObject -> recieverId
 						.equals(mailObject.getReceiverId())).collect(Collectors.toList());
 	}
     
     public List<MailObject> findAllByDate(LocalDate date){
  		return mailRepository.findAll().stream()
  						.filter(mailObject ->  date
  						.equals(mailObject.getDate())).collect(Collectors.toList());
  	}
     
     public List<MailObject> findAllByDateRange(LocalDate toDate, LocalDate fromDate){
   		return mailRepository.findAll().stream()
   						.filter(mailObject -> mailObject.getDate().isBefore(toDate))
   						.filter(mailObject -> mailObject.getDate().isAfter(fromDate))
   						.collect(Collectors.toList());     
     }
     
     public String getMessageContentById(Long id) {
    	 return mailRepository.findAll().stream()
    			  	   .filter(mailObject -> mailObject.getId()==id)
    			  	   .findAny().get().getContent();
     }
     
     
	
	

}
