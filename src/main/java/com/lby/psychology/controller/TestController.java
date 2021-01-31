package com.lby.psychology.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class TestController {

    @PostMapping("/test/hello")
    @ApiOperation("hello")
    public void hello(){
         throw  new RuntimeException();
    }
}
