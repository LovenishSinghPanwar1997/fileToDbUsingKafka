package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.*;
import com.training.filetodbusingkafka.repository.MongoRepo;
import com.training.filetodbusingkafka.repository.PostgresRepo;
import com.training.filetodbusingkafka.service.CsvService;
import com.training.filetodbusingkafka.service.FileToDbService;
import com.training.filetodbusingkafka.service.JsonService;
import com.training.filetodbusingkafka.service.XMLService;
import oracle.jvm.hotspot.jfr.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileToDbServiceImpl implements FileToDbService {

    @Autowired
    MongoRepo mongoRepo;

    @Autowired
    PostgresRepo postgresRepo;

    @Autowired
    XMLService xmlService;

    @Autowired
    CsvService csvService;

    @Autowired
    JsonService jsonService;

    private static boolean flag = true;

    private static final String TOPIC = "mytopic";
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        this.kafkaTemplate.send(TOPIC,message);
    }


    public void sendMsgToTopic(Employee employeeMongo) {

        EmployeeSerializer employeeSerializer = new EmployeeSerializer();
        byte[] bytearr = employeeSerializer.serialize("mytopic", employeeMongo);
        String msg = new String(bytearr,StandardCharsets.UTF_8);

        this.sendMessage(msg);

    }

    @Override
    public void transfer() throws Exception{



        List<Employee> finalList = new ArrayList<>();
        List<Employee> xmlList = xmlService.readXML();
        finalList.addAll(xmlList);

        List<Employee> csvList = csvService.readCsv();
        finalList.addAll(csvList);

        List<Employee> jsonList = jsonService.readJson();
        finalList.addAll(jsonList);

        for (Employee e:finalList) {

            sendMsgToTopic(e);
        }


    }

    @KafkaListener(topics = "mytopic",groupId = "1")
    public void consume(String message) throws Exception
    {
        Employee employee;
        byte[] bytearr = message.getBytes("UTF-8");
        EmployeeDeserializer employeeDeserializer = new EmployeeDeserializer();
        employee = employeeDeserializer.deserialize("mytopic",bytearr);

        if(flag) {
            EmployeeMongo employeed = new EmployeeMongo();
            BeanUtils.copyProperties(employee, employeed);
            mongoThread(employeed);
            flag = false;
        }
        else
        {
            EmployeePostGres employeePostGres = new EmployeePostGres();
            BeanUtils.copyProperties(employee,employeePostGres);
            postThread(employeePostGres);
            flag = true;
        }
    }

    void mongoThread(EmployeeMongo employee)
    {
        mongoRepo.save(employee);
    }

    void postThread(EmployeePostGres employeePostGres)
    {
        postgresRepo.save(employeePostGres);
    }

}
