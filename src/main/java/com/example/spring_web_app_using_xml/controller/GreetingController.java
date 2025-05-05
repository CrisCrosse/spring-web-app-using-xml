package com.example.spring_web_app_using_xml.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", defaultValue = "World", required = false)
            String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
