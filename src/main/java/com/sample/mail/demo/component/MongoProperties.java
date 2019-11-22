package com.sample.mail.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MongoProperties {
	
	private final Environment enviorment ;
	
	@Autowired
	public MongoProperties(Environment enviorment) {
		this.enviorment = enviorment;
	}
	
	public String getDataBase() {
		return enviorment.getProperty("mongodb.database");
	}
	
	
	public String getHost() {
		return enviorment.getProperty("mongodb.host");
	}
	
	public int getPort() {
		return enviorment.getProperty("mongodb.port", Integer.class);
	}
	
	
	

}
