package com.training.filetodbusingkafka.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class EmployeeDeserializer implements Deserializer {

    @Override
    public void configure(Map<String, ?> var1, boolean var2) {

    }

    @Override
    public Employee deserialize(String var1, byte[] var2) {
        ObjectMapper mapper = new ObjectMapper();
        Employee user = null;
        try {
            user = mapper.readValue(var2, Employee.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
