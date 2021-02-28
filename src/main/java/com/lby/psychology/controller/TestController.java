package com.lby.psychology.controller;

import groovy.lang.GroovyShell;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Api
@RestController
public class TestController {


    @GetMapping("/test/hello")
    public String hello() throws Exception {
        if(true){
            throw new Exception("错误了");
        }
        return "hello1";
    }


    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Compilable compilable = (Compilable) engine;
        Bindings bindings = engine.createBindings(); //Local级别的Binding
        String script = "basicGrowth +  continuousGrowth*3"; //定义函数并调用
        CompiledScript JSFunction = null; //解析编译脚本函数
        try {
            JSFunction = compilable.compile(script);
            bindings.put("basicGrowth",100);
            bindings.put("continuousGrowth",50);
            Object result = JSFunction.eval(bindings);
            System.out.println(result); //调用缓存着的脚本函数对象，Bindings作为参数容器传入
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


    public static void  add() throws InterruptedException {
        list.add(1);
    }


}
