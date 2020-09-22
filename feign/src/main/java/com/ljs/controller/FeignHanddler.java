package com.ljs.controller;

import com.ljs.entity.Customer;
import com.ljs.feign.FeignProvideClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
public class FeignHanddler {
    @Resource
    FeignProvideClient feignProvideClient;
    @GetMapping("/findall")
    public List<Customer> sel(){
        return  feignProvideClient.sel();
    }
    @GetMapping("/index")
    public String selByid(){
        return feignProvideClient.selByid();

    }
}
