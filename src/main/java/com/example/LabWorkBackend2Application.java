package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
class LabWork2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabWork2BackendApplication.class, args);
    }

    @GetMapping("C:\\Users\\baxodir77\\IdeaProjects\\labWork-Backend2\\src\\main\\resources\\templates\\index.html")
    public String index() {
        return "index";
    }

    @PostMapping("/submit")
    public String handleSubmit(@RequestParam String name, Model model) {
        model.addAttribute("greeting", "Hello, " + name + "!");
        return "result";
    }
}
