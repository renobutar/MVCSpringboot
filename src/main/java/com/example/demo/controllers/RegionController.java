package com.example.demo.controllers;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.RegionDao;
import com.example.demo.models.Region;
import com.example.demo.tools.DbConnection;

@Controller
@RequestMapping("region")
public class RegionController {
    private RegionDao rdao = new RegionDao(DbConnection.getConnection());
    //Get All
    @GetMapping
    public String index(Model model){   
        model.addAttribute("regions", rdao.getAll());
        return "region/index";
    }

    //create
    //get
    @GetMapping(value = {"form","form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model){  
        if(id != null){
            model.addAttribute("region", rdao.getById(id));
        }else {
            model.addAttribute("region", new Region());
        }
        return "region/form";
    }
    
    //post
    @PostMapping("save")
    public String save(@Nullable Region region){
        Boolean result;
        if(region.getId() != null) {
            result = rdao.updateData(region);
        }else{
            result = rdao.insertData(region);
        }
        if(result){
            return "redirect:/region";
        }else{
            return "region/form";
        }
    }

    @PostMapping(value = {"delete/{id}"})
    public  String delete(@PathVariable Integer id){
        Boolean result = rdao.delete(id);
        if(result){
            return "redirect:/region";
        } else {
            return "Failed Delete";
        }
    }
}
