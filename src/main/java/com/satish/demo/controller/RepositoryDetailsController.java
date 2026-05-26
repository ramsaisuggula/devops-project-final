
package com.satish.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RepositoryDetailsController {
    @GetMapping("/")
    public String home() {
        return "DevOps CI/CD App Running";
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
