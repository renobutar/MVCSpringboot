package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.models.Division;
import com.example.demo.tools.DbConnection;
@Controller
@RequestMapping("division")
public class DivisionController {
   private DivisionDao Ddao = new DivisionDao(DbConnection.getConnection());
   //Get All
   @GetMapping
   public String index(Model model){
        model.addAttribute("divisions", Ddao.getAll());
        return "division/index";
   }

   @GetMapping(value = {"form", "form/{id}"})
      public String create (@PathVariable (required = false) Integer id, Model model){
         if(id != null){
            model.addAttribute("division", Ddao.getById(id));
         } else {
            model.addAttribute("division", new Division());
         }
         return "division/form";
      }
}
