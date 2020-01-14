package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.Employee;
import com.training.filetodbusingkafka.entity.EmployeeXML;
import com.training.filetodbusingkafka.service.FileToDbService;
import com.training.filetodbusingkafka.service.XMLService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLServiceImpl implements XMLService
{

    @Value("${xml.path}")
    private static String path;

    public List<Employee> readXML()
    {
        List<Employee> empList=new ArrayList<>();
        try {
            //TODO : CAN YOU MOVE THIS TO PROPERTY FILE(DONE)
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EmployeeXML employeeXML = (EmployeeXML) jaxbUnmarshaller.unmarshal(file);



            for(EmployeeXML e : employeeXML.getEmpList())
            {
                Employee trans = new Employee();
                BeanUtils.copyProperties(e,trans);
                empList.add(trans);
            }

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return empList;

    }



}

