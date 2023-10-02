package com.example.task.Repo;



import com.example.task.Data.OeeData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OeeRepository extends MongoRepository<OeeData, String> {

}
