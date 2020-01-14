package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.Employee;
import com.training.filetodbusingkafka.entity.EmployeeXML;
import com.training.filetodbusingkafka.service.FileToDbService;
import com.training.filetodbusingkafka.service.XMLService;
import org.springframework.beans.BeanUtils;
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
    public List<Employee> readXML()
    {
        List<Employee> empList=new ArrayList<>();
        try {
            File file = new File("/Users/lovenishsinghpanwar/Downloads/employee.xml");
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

