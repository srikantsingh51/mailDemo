package com.sample.mail.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.mail.demo.model.MailObject;

@DataMongoTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailRepositoryTest {
	
	private 
	MailObject mailObject ;
	
	@Autowired
	private MailObjectRepository mailRepository;
	
	@Before
	public void before() {
		mailObject = new MailObject();
		mailObject.setSenderId("srikantsingh@gmail.com");
		mailObject.setReceiverId("reumanda@gmail.com");
		mailObject.setContent("Hello how are you");
		mailObject.setDate(LocalDate.now());
		mailObject.setId(1L);
	}
	
	@Test
	public void testMailRepository() {
		assertNotNull(mailRepository);
		MailObject MO = mailRepository.insert(mailObject);
		assertEquals(MO.getContent(),"Hello how are you");
		assertEquals(MO.getReceiverId(),"reumanda@gmail.com");
		
	}
	

}
