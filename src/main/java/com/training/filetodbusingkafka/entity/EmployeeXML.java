package com.training.filetodbusingkafka.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXML {

    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "dateOfBirth")
    private String dateOfBirth;
    @XmlElement(name = "experience")
    private String experience;

    @XmlElement(name="employee")
    private List<EmployeeXML> empList;



    //getter and setter

    public List<EmployeeXML> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmployeeXML> empList) {
        this.empList = empList;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getExperience() {
        return experience;
    }


    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "EmployeeXML{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
