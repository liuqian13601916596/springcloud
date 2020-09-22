package com.ljs.feign;

import com.ljs.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "provider",fallback = FeignError.class)/*解决没有映射的问题*/
public interface FeignProvideClient {
    @GetMapping("/findall")
    public List<Customer> sel();
    @GetMapping("/index")
    public String selByid();
}
