package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.Employee;
import com.training.filetodbusingkafka.service.CsvService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public List<Employee> readCsv() throws Exception {
        List<Employee> employees = new ArrayList<>();
        String csvfile = "/Users/lovenishsinghpanwar/Downloads/employee.csv";
        BufferedReader br = null;
        String line = "";
        String splitby = ",";

            br = new BufferedReader(new FileReader(csvfile));

        while ((line = br.readLine()) != null) {
            Employee emp = new Employee();
            String[] record = line.split(splitby);
            //System.out.println(record[1]+" "+record[2]);
            emp.setFirstName(record[0]);
            emp.setLastName(record[1]);
            emp.setDateOfBirth(record[2]);
            emp.setExperience(record[3]);
            employees.add(emp);
            System.out.println(emp);
        }
        return employees;
    }
}
