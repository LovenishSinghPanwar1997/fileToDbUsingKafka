package com.training.filetodbusingkafka.repository;

import com.training.filetodbusingkafka.entity.EmployeePostGres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepo extends CrudRepository<EmployeePostGres,String> {
}
