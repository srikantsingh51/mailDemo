package com.sample.mail.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.mail.demo.model.Sample;
@Repository
public interface SampleRepository  extends MongoRepository<Sample,Long>{
	
	/**
     * Persists the given Sample.
     * @param Sample
	 * @return 
     */
    @SuppressWarnings("unchecked")
	public Sample save(Sample sample);
    
    /**
     * Returns the list of samples with given key.
     * @param Sample
     * @return
     */
    List<Sample> findByKey(String key);

}
