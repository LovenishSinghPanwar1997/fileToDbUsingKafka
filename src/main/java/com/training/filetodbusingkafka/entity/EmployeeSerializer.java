package com.training.filetodbusingkafka.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class EmployeeSerializer implements Serializer {

    @Override
    public void configure(Map<String, ?> var1, boolean var2) {

    }

    @Override
    public byte[] serialize(String var1, Employee var2) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(var2).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
