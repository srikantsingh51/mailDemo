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

import com.sample.mail.demo.exception.EmailSendException;
import com.sample.mail.demo.exception.NoMailFoundException;
import com.sample.mail.demo.model.MailObject;
import com.sample.mail.demo.repository.MailObjectRepository;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	MailObjectRepository mailRepository ;
	
	@Override
	public MailObject sendMail(MailObject mailObject) throws EmailSendException {
		   mailObject.setDate(LocalDate.now());
		   MailObject savedMail = mailRepository.insert(mailObject);
		 return savedMail;
		
	}
	
	@Override
	public List<MailObject> findAll() throws NoMailFoundException{
		
		return  mailRepository.findAll();
		 
	}
	
	@Override
	public MailObject find(Long id) throws NoMailFoundException {
		return	mailRepository.findAll().stream().
						filter(mailObject -> mailObject.getId()==id)
						.findAny().get();
	}
	
	@Override
    public List<MailObject> findAllBySender(String senderId) throws NoMailFoundException{	
		return mailRepository.findAll().stream()
						.filter(mailObject -> senderId
						.equals(mailObject.getSenderId())).collect(Collectors.toList());
	}
	
	@Override
    public List<MailObject> findAllByReciever(String recieverId){		
 		return mailRepository.findAll().stream()
 						.filter(mailObject -> recieverId
 						.equals(mailObject.getReceiverId())).collect(Collectors.toList());
 	}
	
	@Override
    public int  estimatedMailByToday(){
    	 
    	 Long count = mailRepository.findAll().stream()
  						.filter(mailObject ->  LocalDate.now()
  						.equals(mailObject.getDate())).count();
    	 
    	 		LocalDateTime Midnight = LocalDateTime.now()
    	 				.truncatedTo(ChronoUnit.DAYS).plusDays(1);
    	 		Long hoursUntilMidnight = LocalDateTime.now()
						.until(Midnight, ChronoUnit.HOURS);
		return estimatedNumOfMailToday(hoursUntilMidnight.intValue(),count.intValue());
			
  	}

	@Override 
    public int estimatedMailByWeek(){
    	 LocalDateTime lasttSaturdayMidnight = LocalDateTime.now()
	   			    .truncatedTo(ChronoUnit.DAYS)
	   			    .with(TemporalAdjusters.previous((DayOfWeek.SATURDAY)));
    	
   		Long count = mailRepository.findAll().stream()
   						.filter(mailObject -> mailObject.getDate().isBefore(LocalDate.now()))
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
	
	@Override 
    public String getMessageContentById(Long id) {
		
	
			MailObject mObject = mailRepository.findById(id).get();
			mObject.setRead(true);
			mailRepository.save(mObject);
	  	   
    	 return mObject.getContent();
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
