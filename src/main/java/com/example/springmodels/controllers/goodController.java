package com.example.springmodels.controllers;

import com.example.springmodels.models.modelEmployee;
import com.example.springmodels.models.modelGood;
import com.example.springmodels.repos.goodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class goodController {
    @Autowired
    private com.example.springmodels.repos.goodRepository goodRepository;

    @GetMapping("/good")
    public String goodMain(Model model)
    {
        Iterable<modelGood> goods = goodRepository.findAll();
        model.addAttribute("goods", goods);
        return "good-main";
    }

    @GetMapping("/good/add")
    public String goodAddPage(Model model)
    {
        return "good-add";
    }

    @PostMapping("/good/add")
    public String goodAdd(@RequestParam String goodName,
                              @RequestParam double goodPrice,
                              @RequestParam int goodQuantity,
                              @RequestParam Date goodSupplyDate,
                              @RequestParam(defaultValue = "false") boolean goodDefect, Model model)
    {
        modelGood good = new modelGood(goodName, goodPrice, goodQuantity, goodSupplyDate, goodDefect);
        goodRepository.save(good);
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
}
