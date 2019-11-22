package com.sample.mail.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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
     
    public int  etimatedMailByToday(LocalDate date){
    	 
    	 Long count = mailRepository.findAll().stream()
  						.filter(mailObject ->  date
  						.equals(mailObject.getDate())).count();
    	 
    	 		LocalDateTime Midnight = LocalDateTime.now()
    	 				.truncatedTo(ChronoUnit.DAYS).plusDays(1);
    	 		Long hoursUntilMidnight = LocalDateTime.now()
						.until(Midnight, ChronoUnit.HOURS);
		return estimatedNumOfMailToday(hoursUntilMidnight.intValue(),count.intValue());
			
  	}
     
    public int etimatedMailByWeek(LocalDate toDate){
    	 LocalDateTime lasttSaturdayMidnight = LocalDateTime.now()
	   			    .truncatedTo(ChronoUnit.DAYS)
	   			    .with(TemporalAdjusters.previous((DayOfWeek.SATURDAY)));
    	
   		Long count = mailRepository.findAll().stream()
   						.filter(mailObject -> mailObject.getDate().isBefore(toDate))
   						.filter(mailObject -> mailObject.getDate().isAfter( lasttSaturdayMidnight.toLocalDate()))
   						.count(); 
   		LocalDateTime nextSaturdayMidnight = LocalDateTime.now()
   			    // truncating to midnight
   			    .truncatedTo(ChronoUnit.DAYS)
   			    // adding adjustment to next saturday
   			    .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
   		
   			// getting hours until next saturday midnight
   			Long hoursUntilNextSaturdayMidnight = LocalDateTime.now()
   			    // getting offset in hours
   			    .until(nextSaturdayMidnight, ChronoUnit.HOURS);
   			
   			return estimatedNumOfMailByWeek(hoursUntilNextSaturdayMidnight.intValue(),count.intValue());
   			
     }
     
    public String getMessageContentById(Long id) {
    	 return mailRepository.findAll().stream()
    			  	   .filter(mailObject -> mailObject.getId()==id)
    			  	   .findAny().get().getContent();
     }
        
	private int estimatedNumOfMailToday(int hrRemaining,int numberOfSendMail) {
		int passedHours = 24 - hrRemaining ;
		float mailPerHour =(float)numberOfSendMail/(float)passedHours;
		return (int)(mailPerHour * (float)hrRemaining);
		
	}
	
	private int estimatedNumOfMailByWeek(int hrRemaining,int numberOfSendMail) {
		int passedHours = (24 * 7) - hrRemaining ;
		float mailPerHour =(float)numberOfSendMail/(float)passedHours;
		return (int)(mailPerHour * (float)hrRemaining);
		
	}
	
}
