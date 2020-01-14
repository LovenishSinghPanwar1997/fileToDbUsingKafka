package com.training.filetodbusingkafka.service;

import com.training.filetodbusingkafka.entity.Employee;

import java.util.List;

public interface CsvService {

    List<Employee> readCsv() throws Exception;

}
