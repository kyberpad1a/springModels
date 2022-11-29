package com.example.springmodels.controllers;


import com.example.springmodels.models.modelEmployee;
import com.example.springmodels.models.modelGood;
import com.example.springmodels.models.modelPasport;
import com.example.springmodels.models.modelPost;
import com.example.springmodels.repos.postRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class employeeController {
    @Autowired
    private com.example.springmodels.repos.employeeRepository employeeRepository;
    @Autowired
    private com.example.springmodels.repos.pasportRepository pasportRepository;
    @Autowired
    private com.example.springmodels.repos.postRepository postRepository;

    @GetMapping("/")
    public String employeeMain(Model model)
    {
        Iterable<modelEmployee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        Iterable<modelPost> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "employee-main";
    }

    @GetMapping("/employee/add")
    public String employeeAddPage(@ModelAttribute("employee") modelEmployee modelEmployee, Model modelPasport)
    {
        Iterable<modelPasport> pasport = pasportRepository.findAll();
        modelPasport.addAttribute("pasport", pasport);
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employee") @Valid modelEmployee modelEmployee, BindingResult bindingResult, @RequestParam int pasportNumber, Model modelPasport)
    {
        if (bindingResult.hasErrors())
        {
            Iterable<modelPasport> pasport = pasportRepository.findAll();
            modelPasport.addAttribute("pasport", pasport);
            return "employee-add";
        }
        modelEmployee.setEmployeePasport(pasportRepository.findByPasportNumber(pasportNumber));
        employeeRepository.save(modelEmployee);
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

        modelEmployee res = employeeRepository.findById(ID_Employee).orElseThrow();
        model.addAttribute("modelEmployee",res);
        return "employee-edit";
    }

    @PostMapping("/employee/{ID_Employee}/edit")
    public String employeeUpdate(@PathVariable("ID_Employee") long ID_Employee,
                                 @Valid modelEmployee modelEmployee, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "employee-edit";
        employeeRepository.save(modelEmployee);
        return "redirect:/";
    }
    @PostMapping("employee/{ID_Employee}/remove")
    public String employeeDelete(@PathVariable("ID_Employee") long ID_Employee, Model model){
        modelEmployee employee = employeeRepository.findById(ID_Employee).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/";
    }

    @GetMapping("/employee/post/add")
    private String postAddPage(Model model){
        Iterable<modelEmployee> employee = employeeRepository.findAll();
        model.addAttribute("employees", employee);
        Iterable<modelPost> post = postRepository.findAll();
        model.addAttribute("posts", post);
        return "employee-post-add";
    }

    @PostMapping("/employee/post/add")
    public String postAdd(@RequestParam Long employee, @RequestParam Long post, Model model)
    {
        modelEmployee employee1 = employeeRepository.findById(employee).orElseThrow();
        modelPost post1 = postRepository.findById(post).orElseThrow();
        employee1.getPosts().add(post1);

        employeeRepository.save(employee1);
        return "redirect:/employee/add";
    }

}
