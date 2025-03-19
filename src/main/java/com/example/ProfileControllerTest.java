package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // This makes sure we use the "test" profile
public class ProfileControllerTest {

    @Value("${app.message}")
    private String message;

    @Test
    void testProfileMessage() {
        assertThat(message).isEqualTo("Welcome to the Test Environment!");
    }
}
