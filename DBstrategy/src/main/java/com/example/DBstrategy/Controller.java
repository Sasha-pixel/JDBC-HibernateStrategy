package com.example.DBstrategy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/insertFile")
    public void run() {
        service.insertFile();
    }

    @PostMapping("/insertStr")
    public void insertByJDBC(@RequestBody String json) {
        service.insertStr(json);
    }

    @GetMapping("/select")
    public String selectAll() {
        return service.selectAll();
    }

    @PostMapping("/change")
    public String changeStrategy(@RequestBody String name) {
        return service.changeStrategy(name);
    }
}
