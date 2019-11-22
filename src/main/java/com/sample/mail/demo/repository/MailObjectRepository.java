package com.sample.mail.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sample.mail.demo.model.MailObject;

public interface MailObjectRepository extends MongoRepository<MailObject,Long>{
	
	
	

}
