package com.rits.oee.repository;



import com.rits.oee.model.OeeData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OeeRepository extends MongoRepository<OeeData, String> {
}
