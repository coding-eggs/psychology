package com.lby.psychology.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = "异常页面路由")
@RestController
public class ErrorViewController {

    @ApiOperation("404 跳转页面")
    @RequestMapping("/404")
    public ModelAndView noPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error-404.html");
        return modelAndView;
    }


    @ApiOperation("500 跳转页面")
    @RequestMapping("/500")
    public ModelAndView serverErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error-500.html");
        return modelAndView;
    }


    @ApiOperation("login 跳转页面")
    @RequestMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login.html");
        return modelAndView;
    }

    @ApiOperation("index 跳转页面")
    @RequestMapping("/")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/profile.html");
        return modelAndView;
    }

}
