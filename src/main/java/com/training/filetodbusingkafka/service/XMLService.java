package com.training.filetodbusingkafka.service;


import com.training.filetodbusingkafka.entity.Employee;
import com.training.filetodbusingkafka.entity.EmployeeXML;

import java.util.List;

public interface XMLService {
    public List<Employee> readXML();

}
