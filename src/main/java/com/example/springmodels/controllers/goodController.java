package com.example.springmodels.controllers;

import com.example.springmodels.models.modelEmployee;
import com.example.springmodels.models.modelGood;
import com.example.springmodels.repos.goodRepository;
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
public class goodController {
    @Autowired
    private com.example.springmodels.repos.goodRepository goodRepository;
    @Autowired
    private com.example.springmodels.repos.employeeRepository employeeRepository;

    @GetMapping("/good")
    public String goodMain(Model model)
    {
        Iterable<modelGood> goods = goodRepository.findAll();
        model.addAttribute("goods", goods);
        return "good-main";
    }

    @GetMapping("/good/add")
    public String goodAddPage(@ModelAttribute("good") modelGood modelGood, Model modelEmployee)
    {
        Iterable<modelEmployee> employees = employeeRepository.findAll();
        modelEmployee.addAttribute("employee", employees);

        return "good-add";
    }

    @PostMapping("/good/add")
    public String goodAdd(@ModelAttribute("good") @Valid modelGood modelGood, BindingResult bindingResult, @RequestParam String initials, Model modelEmployee)
    {

        if (bindingResult.hasErrors()) {
            Iterable<modelEmployee> employees = employeeRepository.findAll();
            modelEmployee.addAttribute("employee",employees);
            return "good-add";
        }
        modelGood.setEmployee(employeeRepository.findByEmployeeInitials(initials));
        goodRepository.save(modelGood);
        return "redirect:/good";
    }

    @GetMapping("/good/filter")
    public String goodFilter(Model model)
    {
        return "good-filter";
    }

    @PostMapping("/good/filter/result")
    public String goodResult(@RequestParam String goodName, Model model)
    {
        List<modelGood> result = goodRepository.findByGoodNameEquals(goodName);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "good-filter";
    }

    @GetMapping("/good/{ID_Good}")
    public String goodDetails(@PathVariable(value = "ID_Good") long ID_Good, Model model)
    {
        Optional<modelGood> good = goodRepository.findById(ID_Good);
        ArrayList<modelGood> res = new ArrayList<>();
        good.ifPresent(res::add);
        model.addAttribute("good",res);
        if(!goodRepository.existsById(ID_Good))
        {
            return "redirect:/good";
        }
        return "good-details";
    }
    @GetMapping("/good/{ID_Good}/edit")
    public String goodEdit(@PathVariable("ID_Good") long ID_Good, Model model)
    {
        /*if(!goodRepository.existsById(ID_Good)){
            return "redirect:/good";
        }
        Optional<modelGood> good = goodRepository.findById(ID_Good);
        ArrayList<modelGood> res = new ArrayList<>();
        good.ifPresent(res::add);*/
        modelGood res = goodRepository.findById(ID_Good).orElseThrow();
        model.addAttribute("modelGood",res);
        return "good-edit";
    }

    @PostMapping("/good/{ID_Good}/edit")
    public String goodUpdate(@PathVariable("ID_Good") long ID_Good,
                             @Valid modelGood modelGood, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return "good-edit";
        goodRepository.save(modelGood);
        return "redirect:/good";
    }
    @PostMapping("good/{ID_Good}/remove")
    public String goodDelete(@PathVariable("ID_Good") long ID_Good, Model model){
        modelGood good = goodRepository.findById(ID_Good).orElseThrow();
        goodRepository.delete(good);
        return "redirect:/good";
    }
}
