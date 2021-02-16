package com.lby.psychology.controller;

public class Test {

    private String name = "bbb";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String test(String s){
        return name + s;
    }

    public static void main(String[] args) {
        Test test = new Test();
        String s = test.test("kkk");
        System.out.println(s);
    }
}
