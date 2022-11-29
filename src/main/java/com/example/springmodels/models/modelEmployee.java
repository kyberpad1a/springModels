package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
public class modelEmployee {

    public modelEmployee() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Employee;
    @NotBlank(message = "Поле не может быть пустым")
    @NotNull
    private String employeeInitials;
    @NotNull
    @Positive(message = "Вес не может быть отрицательным")
    @Digits(integer = 3, fraction = 1, message = "Количество символов слева от запятой не может превышать 3, справа - 1")
    private double employeeWeight;
    @Positive(message = "Возраст не может быть отрицательным")
    @Max(value = 100, message = "Возраст не может быть больше 100")
    private int employeeAge;
    //@Past(message = "Дата рождения должна быть раньше текущей даты")
    private Date employeeDateOfBirth;
    private boolean employeeStatus;
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Collection<modelGood> responsibleGoods;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="pasport_id")
    private modelPasport employeePasport;
    @ManyToMany
    @JoinTable (name="employee_post",
            joinColumns=@JoinColumn (name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="post_id"))
    private List<modelPost> posts;


    public modelEmployee(Long ID_Employee, String employeeInitials, double employeeWeight, int employeeAge, Date employeeDateOfBirth, boolean employeeStatus, Collection<modelGood> responsibleGoods, modelPasport employeePasport) {
        this.ID_Employee = ID_Employee;
        this.employeeInitials = employeeInitials;
        this.employeeWeight = employeeWeight;
        this.employeeAge = employeeAge;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeStatus = employeeStatus;
        this.responsibleGoods = responsibleGoods;
        this.employeePasport = employeePasport;
    }

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

    public Collection<modelGood> getResponsibleGoods() {
        return responsibleGoods;
    }

    public void setResponsibleGoods(Collection<modelGood> responsibleGoods) {
        this.responsibleGoods = responsibleGoods;
    }

    public modelPasport getEmployeePasport() {
        return employeePasport;
    }

    public void setEmployeePasport(modelPasport employeePasport) {
        this.employeePasport = employeePasport;
    }

    public List<modelPost> getPosts() {
        return posts;
    }

    public void setPosts(List<modelPost> posts) {
        this.posts = posts;
    }
}
