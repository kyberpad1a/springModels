package com.example.springmodels.controllers;


import com.example.springmodels.models.modelEmployee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

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
}
