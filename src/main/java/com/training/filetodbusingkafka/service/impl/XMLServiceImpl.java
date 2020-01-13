package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.entity.EmployeeXML;
import com.training.filetodbusingkafka.service.XMLService;
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
    public List<EmployeeXML> readXML()
    {
        List<EmployeeXML> empList=new ArrayList<>();
        try {
            File file = new File("/Users/harshdesai/Downloads/employee.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EmployeeXML employeeXML = (EmployeeXML) jaxbUnmarshaller.unmarshal(file);


            for(EmployeeXML e : employeeXML.getEmpList())
            {
                empList.add(e);
                employeeXML.setEmpList(empList);
            }

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return empList;

    }



}

