package com.ljs.contorller;

import com.ljs.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class RestTemplateHaddler {
    @Resource
    RestTemplate restTemplate;
    @RequestMapping("/find")
    public List<Customer> find(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForEntity("http://localhost:8888/findall",List.class).getBody();//拿到访问的接口


    }
    @RequestMapping("/find2")
    public List<Customer> find2(){/*这个的返回值类型得和服务提供的类型一样，下面是访问路径*/
        return restTemplate.getForObject("http://localhost:8888/findall",List.class);//拿到访问的接口


    }
    @RequestMapping("/name/{id}")

    public  Customer findbyname(@PathVariable("id") int id){/*得到对象就不用.getbody（）*/
    System.out.println(restTemplate.getForObject("http://localhost:8888/findname/{id}",Customer.class,id));
        Customer customer=  restTemplate.getForEntity("http://localhost:8888/findname/{id}",Customer.class,id).getBody();
        System.out.println(customer);
        return  customer;
    }
}
