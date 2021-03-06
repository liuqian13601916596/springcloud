package com.ljs.controller;

import com.ljs.entity.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CustomerHanddler {
    @Resource
    RestTemplate restTemplate;
    @RequestMapping("/find")
    public List<Customer> find(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径,ribbon访问路径不用ip+port,而是用服务提供者的名称*/
        return restTemplate.getForEntity("http://provider/findall",List.class).getBody();//拿到访问的接口
    }
    @RequestMapping("/find2")
    public List<Customer> find2(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForObject("http://provider/findall",List.class);//拿到访问的接口


    }
    @RequestMapping("/name/{id}")

    public  Customer findbyname(@PathVariable("id") int id){/*得到对象就不用.getbody（）*/
        System.out.println(restTemplate.getForObject("http://provider/findname/{id}",Customer.class,id));
        Customer customer=  restTemplate.getForEntity("http://provider/findname/{id}",Customer.class,id).getBody();
        System.out.println(customer);
        return  customer;
    }
}
