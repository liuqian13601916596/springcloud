package com.ljs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigClientHaddler {
/*
   @Value("${project.name}")
   private  String word;*/
    @GetMapping("/index")
    public String  index(){

        return "配置中心和版本是"+"-";
    }

}
