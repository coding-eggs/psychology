package com.lby.psychology.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    @ApiOperation("hello")
    public void hello(){
        System.out.println("hello");
    }
}
