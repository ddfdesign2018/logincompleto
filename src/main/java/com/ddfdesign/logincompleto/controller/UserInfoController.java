package com.ddfdesign.logincompleto.controller;

import com.ddfdesign.logincompleto.hello.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ddfdesign.logincompleto.service.IUserInfoService;

@Controller
@RequestMapping("app")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("login")
    public ModelAndView login() {
        System.out.println("UserInfoController login  entrada");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom-login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        System.out.println("UserInfoController login  secure/article");
        ModelAndView mav = new ModelAndView();
        mav.addObject("userArticles", userInfoService.getAllUserArticles());
        mav.setViewName("articles");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    @GetMapping("user-creation")
    public ModelAndView newuser1() {
        System.out.println("Pagina para crear usuarios");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-creation");
        return mav;
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        System.out.println("Greeting get");
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        System.out.println("Greeting post");
        System.out.println(greeting.getPassword());
        System.out.println(greeting.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("Encriptacion: " + bCryptPasswordEncoder.encode(greeting.getPassword()));
        return "result";
    }

    //Get method que devuelve un eco del usuario que se quiere crear
    @GetMapping("/result")
    public ModelAndView newresult(){
        System.out.println("Result get");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("result");
        return mav;
    }
}
