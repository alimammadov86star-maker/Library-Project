package com.example.demo.controller.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to our Library System");
        return "home";
    }
}
