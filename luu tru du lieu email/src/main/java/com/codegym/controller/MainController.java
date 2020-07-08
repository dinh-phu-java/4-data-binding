package com.codegym.controller;

import com.codegym.model.Email;
import com.codegym.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MainController {
    @InitBinder
    public void initBinder(WebDataBinder theBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        theBinder.registerCustomEditor(Email.class,stringTrimmerEditor);
    }

    @Autowired
    private IEmailService emailService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index");
        ArrayList<Email> emails=new ArrayList<>(emailService.findAll());
        System.out.println("list email is: ");
        emails.forEach(k-> System.out.println(k));
        modelAndView.addObject("emails",emails);
        return modelAndView;
    }


    @GetMapping("/email/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id){
       ModelAndView modelAndView=new ModelAndView("edit");
        Email editEmail=emailService.findById(id);
        System.out.println("edit Email is: ");
        System.out.println(editEmail);
        modelAndView.addObject("editEmail",editEmail);
        modelAndView.addObject("message","hello edit page");
       return modelAndView;
    }

    @PostMapping("/email/edit")
    public ModelAndView processEditForm(@ModelAttribute Email editEmail){
        ModelAndView modelAndView=new ModelAndView("edit");
        modelAndView.addObject("message","Sua Thanh Cong");
        emailService.update(editEmail.getId(),editEmail);

        modelAndView.addObject("editEmail",emailService.findById(editEmail.getId()));
        return modelAndView;
    }
}
