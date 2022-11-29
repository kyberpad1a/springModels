package com.example.springmodels.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class modelPost {
    public modelPost(){}
    @Id
    @GeneratedValue
    private Long ID_Post;
    private String postName;
    private double postSalary;
    @ManyToMany
    @JoinTable(name="employee_post",
            joinColumns=@JoinColumn(name="post_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))
    private List<modelEmployee> employees;

    public Long getID_Post() {
        return ID_Post;
    }

    public void setID_Post(Long ID_Post) {
        this.ID_Post = ID_Post;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public double getPostSalary() {
        return postSalary;
    }

    public void setPostSalary(double postSalary) {
        this.postSalary = postSalary;
    }

    public List<modelEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<modelEmployee> employees) {
        this.employees = employees;
    }
}
