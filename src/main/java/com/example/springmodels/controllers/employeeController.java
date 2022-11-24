package com.example.springmodels.controllers;


import com.example.springmodels.models.modelEmployee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class employeeController {
    @Autowired
    private com.example.springmodels.repos.employeeRepository employeeRepository;

    @GetMapping("/")
    public String employeeMain(Model model)
    {
        Iterable<modelEmployee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee-main";
    }

    @GetMapping("/employee/add")
    public String employeeAddPage(Model model)
    {
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@RequestParam String employeeInitials,
                              @RequestParam double employeeWeight,
                              @RequestParam int employeeAge,
                              @RequestParam Date employeeDateOfBirth,
                              @RequestParam(defaultValue = "false") boolean employeeStatus, Model model)
    {
        modelEmployee employee = new modelEmployee(employeeInitials, employeeWeight, employeeAge, employeeDateOfBirth, employeeStatus);
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/employee/filter")
    public String employeeFilter(Model model)
    {
        return "employee-filter";
    }

    @PostMapping("/employee/filter/result")
    public String employeeResult(@RequestParam String employeeInitials, Model model)
    {
        List<modelEmployee> result = employeeRepository.findByEmployeeInitialsContains(employeeInitials);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "employee-filter";
    }

    @GetMapping("/employee/{ID_Employee}")
    public String employeeDetails(@PathVariable(value = "ID_Employee") long ID_Employee, Model model)
    {
        Optional<modelEmployee> employee = employeeRepository.findById(ID_Employee);
        ArrayList<modelEmployee> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("employee",res);
        if(!employeeRepository.existsById(ID_Employee))
        {
            return "redirect:/employee";
        }
        return "employee-details";
    }
    @GetMapping("/employee/{ID_Employee}/edit")
    public String employeeEdit(@PathVariable("ID_Employee") long ID_Employee, Model model)
    {
        if(!employeeRepository.existsById(ID_Employee)){
            return "redirect:/";
        }
        Optional<modelEmployee> post = employeeRepository.findById(ID_Employee);
        ArrayList<modelEmployee> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("employee",res);
        return "employee-edit";
    }

    @PostMapping("/employee/{ID_Employee}/edit")
    public String employeeUpdate(@PathVariable("ID_Employee") long ID_Employee,
                                 @RequestParam String employeeInitials,
                                 @RequestParam double employeeWeight,
                                 @RequestParam int employeeAge,
                                 @RequestParam Date employeeDateOfBirth,
                                 @RequestParam(defaultValue = "false") boolean employeeStatus, Model model)
    {
        modelEmployee employee = employeeRepository.findById(ID_Employee).orElseThrow();
        employee.setEmployeeInitials(employeeInitials);
        employee.setEmployeeWeight(employeeWeight);
        employee.setEmployeeAge(employeeAge);
        employee.setEmployeeDateOfBirth(employeeDateOfBirth);
        employee.setEmployeeStatus(employeeStatus);
        employeeRepository.save(employee);
        return "redirect:/";
    }
    @PostMapping("employee/{ID_Employee}/remove")
    public String employeeDelete(@PathVariable("ID_Employee") long ID_Employee, Model model){
        modelEmployee employee = employeeRepository.findById(ID_Employee).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/";
    }
}
