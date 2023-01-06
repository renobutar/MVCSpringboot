package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.tools.DbConnection;
@Controller
@RequestMapping("division")
public class DivisionController {
   private DivisionDao Ddao = new DivisionDao(DbConnection.getConnection());
   //Get All
   @GetMapping
   public String index(Model model){
        model.addAttribute("division", Ddao.getAll());
        return "division/index";
   }
}
