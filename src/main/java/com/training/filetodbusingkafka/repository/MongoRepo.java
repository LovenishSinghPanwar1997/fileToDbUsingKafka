package com.training.filetodbusingkafka.repository;

import com.training.filetodbusingkafka.entity.EmployeeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<EmployeeMongo,String> {
}
