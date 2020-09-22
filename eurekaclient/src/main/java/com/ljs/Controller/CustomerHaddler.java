package com.ljs.Controller;

import com.ljs.entity.Customer;
import com.ljs.service.CustomerService;
import com.ljs.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CustomerHaddler {
    @Resource
    CustomerService customerService;
    @ResponseBody
     @RequestMapping("/json")
    public JsonResult test(){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setMsg("成功");
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/findall")
    public List<Customer> findall(){

        customerService.selAll();

        return  customerService.selAll();
    }

    @RequestMapping("/findname/{id}")
    public Customer findname(@PathVariable("id")  int id) {
        id=1;
     Customer customer =  customerService.selById(id);

        return  customer;
    }
    @Value("${server.port}")
    private  int port;
    @RequestMapping("/port")
    public String selPort(){
        return  "当前的服务端口是"+port;
    }
     @RequestMapping("/index")
    public String selByid(){
        return "访问index";

    }
}
