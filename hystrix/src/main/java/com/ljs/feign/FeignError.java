package com.ljs.feign;

import com.ljs.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeignError implements  FeignProvideClient {
    @Override
    public List<Customer> sel() {
        return null;
    }

    @Override
    public String selByid() {

        return "服务器维护中";
    }
}
