package com.example.springmodels.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class modelEmployee {
    public modelEmployee(String employeeInitials, double employeeWeight, int employeeAge, Date employeeDateOfBirth, boolean employeeStatus){
        this.employeeInitials = employeeInitials;
        this.employeeWeight = employeeWeight;
        this.employeeAge = employeeAge;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeStatus = employeeStatus;
    }

    public modelEmployee() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Employee;
    private String employeeInitials;
    private double employeeWeight;
    private int employeeAge;
    private Date employeeDateOfBirth;
    private boolean employeeStatus;

    public Long getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(Long ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    public String getEmployeeInitials() {
        return employeeInitials;
    }

    public void setEmployeeInitials(String employeeInitials) {
        this.employeeInitials = employeeInitials;
    }

    public double getEmployeeWeight() {
        return employeeWeight;
    }

    public void setEmployeeWeight(double employeeWeight) {
        this.employeeWeight = employeeWeight;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public Date getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(Date employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public boolean isEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
