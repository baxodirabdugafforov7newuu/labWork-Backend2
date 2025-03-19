package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Value("${app.message}")
    private String message;

    @GetMapping
    public String getMessage() {
        return message;
    }
}

@RestController
@RequestMapping("/lazy")
class LazyController {

    private final GreetingService lazyFarewellService;

    @Autowired
    public LazyController(@Qualifier("lazyFarewellService") GreetingService lazyFarewellService) {
        this.lazyFarewellService = lazyFarewellService;
    }

    @GetMapping("/farewell")
    public String farewell() {
        return lazyFarewellService.getMessage();
    }
}

@SpringBootTest
class GreetingServiceTest {


    private HelloService helloService;


    private FarewellService farewellService;

    @Test
    public void testHelloMessage() {
        assertEquals("Hello, Welcome!", helloService.getMessage());
    }

    @Test
    public void testFarewellMessage() {
        assertEquals("Goodbye, See you again!", farewellService.getMessage());
    }
}