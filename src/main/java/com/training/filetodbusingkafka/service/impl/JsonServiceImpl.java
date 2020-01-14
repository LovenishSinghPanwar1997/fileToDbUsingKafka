package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.Employee;
import com.training.filetodbusingkafka.service.JsonService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;




@Service
public class JsonServiceImpl implements JsonService {

    @Override
    public List<Employee> readJson() throws Exception {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("/Users/lovenishsinghpanwar/Downloads/employee.json");
        Object object;
        object = jsonParser.parse(reader);
        // Date dateFormat=new Date("MM/dd/yy");
        JSONArray employeeList = (JSONArray) object;
        List<Employee> employeeJsonEntityList = new ArrayList<>();
        for(Object employee: employeeList) {
            JSONObject employeejson = (JSONObject) employee;
            Employee employee1 = new Employee();
            employee1.setFirstName(employeejson.get("firstName").toString());
            employee1.setLastName(employeejson.get("lastName").toString());
            employee1.setDateOfBirth((employeejson.get("dateOfBirth").toString()));
            employee1.setExperience(employeejson.get("experience").toString());
            employeeJsonEntityList.add(employee1);
        }
        return employeeJsonEntityList;

    }
}
