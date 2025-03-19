package com.example;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

public interface GreetingService {
    String getMessage();
}

@Service
class HelloService implements GreetingService {
    @Override
    public String getMessage() {
        return "Hello, Welcome!";
    }
}

@Service
class FarewellService implements GreetingService {
    @Override
    public String getMessage() {
        return "Goodbye, See you again!";
    }
}

@Controller
class GreetingController {

    private final GreetingService greetingService;

    // Constructor-based injection
    public GreetingController(@Qualifier("helloService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet")
    public String greet(Model model) {
        model.addAttribute("message", greetingService.getMessage());
        return "greet";
    }
}

@Lazy
@Service
class LazyFarewellService implements GreetingService {
    public LazyFarewellService() {
        System.out.println("LazyFarewellService initialized!");
    }

    @Override
    public String getMessage() {
        return "Goodbye (Lazy Loading)!";
    }
}