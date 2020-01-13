package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.*;
import com.training.filetodbusingkafka.repository.MongoRepo;
import com.training.filetodbusingkafka.repository.PostgresRepo;
import com.training.filetodbusingkafka.service.FileToDbService;
import oracle.jvm.hotspot.jfr.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class FileToDbServiceImpl implements FileToDbService {

    @Autowired
    MongoRepo mongoRepo;

    @Autowired
    PostgresRepo postgresRepo;



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
    public void transfer(){

        Employee employee = new Employee();
        employee.setFirstName("Adam");
        employee.setLastName("Sales");
        employee.setDateOfBirth("22/11/1997");
        employee.setExperience("9");

        sendMsgToTopic(employee);


    }

    @KafkaListener(topics = "mytopic",groupId = "1")
    public void consume(String message) throws Exception
    {
        Employee employee;
        byte[] bytearr = message.getBytes("UTF-8");
        EmployeeDeserializer employeeDeserializer = new EmployeeDeserializer();
        employee = employeeDeserializer.deserialize("mytopic",bytearr);
        EmployeeMongo employeed = new EmployeeMongo();
        BeanUtils.copyProperties(employee,employeed);
        System.out.println(employeed);
        mongoThread(employeed);
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
