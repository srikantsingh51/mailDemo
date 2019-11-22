package com.sample.mail.demo.repository;
  

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.mail.demo.model.Sample;
@RunWith(SpringRunner.class)
@DataMongoTest
public class TestSampleRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SampleRepository sampleRepository;
	
	Sample  sample = null;
	
	@Before
	public void before() {
		sample = new Sample("A","All the best");
	}
	
	@Test
	public void checkMongoTemplate() {
		assertNotNull(mongoTemplate);
		Sample sam = mongoTemplate.save(sample);
		assertEquals(sam.getKey(),"A");
	}
	
	
	@Test
	public void saveSample() {
		sampleRepository.save(sample);
	}
	

}
