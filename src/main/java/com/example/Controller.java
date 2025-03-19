package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AppController {

    @Value("${app.name}")
    private String appName;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}

@Component
@ConfigurationProperties(prefix = "app.config")
class AppConfig {
    private String title;
    private String version;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}

@Controller
class ConfigController {

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/config")
    public String showConfig(Model model) {
        model.addAttribute("title", appConfig.getTitle());
        model.addAttribute("version", appConfig.getVersion());
        return "config";
    }
}