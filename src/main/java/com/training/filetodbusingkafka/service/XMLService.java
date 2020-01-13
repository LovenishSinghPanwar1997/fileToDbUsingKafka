package com.training.filetodbusingkafka.service;


import com.training.filetodbusingkafka.entity.EmployeeXML;

import java.util.List;

public interface XMLService {
    public List<EmployeeXML> readXML();

}
