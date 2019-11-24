package com.sample.mail.demo.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sample.mail.demo.model.MailObject;
import com.sample.mail.demo.repository.MailObjectRepository;

public class MailServiceTest {
	
	@Mock
	private MailObjectRepository mailObjectRepository;
	
	@InjectMocks
	private MailServiceImpl   mailObjectService;
	
	private 
	MailObject mailObject ;
	
	private 
	MailObject mailObject1 ;
	
	@Before
	public void setUp() {
		mailObject = new MailObject();
		mailObject.setSenderId("srikantsingh@gmail.com");
		mailObject.setReceiverId("reumanda@gmail.com");
		mailObject.setContent("Hello how are you");
		mailObject.setSubject("Job Discrition");
		mailObject.setDate(LocalDate.now());
		mailObject.setId(1L);
		mailObject1 = new MailObject();
		mailObject1.setSenderId("reumanda@gmail.com");
		mailObject1.setReceiverId("srikantsingh@gmail.com");
		mailObject1.setContent("I am fine I will be there");
		mailObject1.setSubject("Job Discrition");
		mailObject1.setDate(LocalDate.now());
		mailObject1.setId(2L);
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void sendMailTest() throws Exception {
		when(mailObjectRepository.insert(mailObject)).thenReturn(mailObject);
		MailObject MO = mailObjectService.sendMail(mailObject);
		assertEquals(MO.getContent(),"Hello how are you");
		assertEquals(MO.getReceiverId(),"reumanda@gmail.com");
		assertEquals(MO.getSenderId(),"srikantsingh@gmail.com");
		assertEquals(MO.getSubject(),"Job Discrition");		
		
	}
	
	@Test
	public void testFindAll() throws Exception{
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1));
		List<MailObject> list = mailObjectService.findAll();
		assertEquals(list.size(),2);
		assertEquals(list.get(0).getContent(),"Hello how are you");
		assertEquals(list.get(0).getReceiverId(),"reumanda@gmail.com");
		assertEquals(list.get(0).getSenderId(),"srikantsingh@gmail.com");
		assertEquals(list.get(0).getSubject(),"Job Discrition");
		assertEquals(list.get(1).getContent(),"I am fine I will be there");
		assertEquals(list.get(1).getReceiverId(),"srikantsingh@gmail.com");
		assertEquals(list.get(1).getSenderId(),"reumanda@gmail.com");
		assertEquals(list.get(1).getSubject(),"Job Discrition");
	}
	
	@Test
	public void testFindById() throws Exception{
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1));
		MailObject MO = mailObjectService.find(1L);
		assertEquals(MO.getContent(),"Hello how are you");
	}
	
	@Test
	public void testFindBySender() throws Exception{
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1));
		List<MailObject> list = mailObjectService.findAllBySender("srikantsingh@gmail.com");
		assertEquals(list.size(),1);
		assertEquals(list.get(0).getContent(),"Hello how are you");
		assertEquals(list.get(0).getReceiverId(),"reumanda@gmail.com");
		assertEquals(list.get(0).getSenderId(),"srikantsingh@gmail.com");
		assertEquals(list.get(0).getSubject(),"Job Discrition");
		
	}
	
	@Test
	public void testFindByReceiver() throws Exception{
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1));
		List<MailObject> list = mailObjectService.findAllByReciever("reumanda@gmail.com");
		assertEquals(list.size(),1);
		assertEquals(list.get(0).getContent(),"Hello how are you");
		assertEquals(list.get(0).getReceiverId(),"reumanda@gmail.com");
		assertEquals(list.get(0).getSenderId(),"srikantsingh@gmail.com");
		assertEquals(list.get(0).getSubject(),"Job Discrition");	
	}
	
	@Test
	public void testFindContentById() throws Exception{
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1));
		String content = mailObjectService.getMessageContentById(2L);
		assertEquals(content,"I am fine I will be there");
	}
	
	@Ignore("This test is ignore because value is change by the time ")
	@Test
	public void testEtimatedMailByToday() {
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject));
				int value  = mailObjectService.estimatedMailByToday();
				assertEquals(value,4);
		
	}
	
	@Ignore("This test is ignore because value is change by the time ")
	@Test
	public void testEtimatedMailByWeek() {
		when(mailObjectRepository.findAll()).thenReturn(Arrays.asList(mailObject,mailObject1
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject
											,mailObject,mailObject1 ,mailObject,mailObject));
				int value  = mailObjectService.estimatedMailByWeek();
				assertEquals(value,4);
		
	}
}