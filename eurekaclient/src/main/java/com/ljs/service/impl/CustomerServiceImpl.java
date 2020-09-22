package com.ljs.service.impl;

import com.ljs.entity.Customer;
import com.ljs.responsitory.CustomerMapper;
import com.ljs.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
   private static ArrayList<Customer> customerList;




    static {
    customerList=new ArrayList<>();
        Customer c1=new Customer(1,"张三","123456",1);
        Customer c2=new Customer(2,"李四","123456",1);
        Customer c3=new Customer(3,"王五","123456",1);
        Customer c4=new Customer(4,"张6","123456",1);
        Customer c5=new Customer(5,"张7","123456",1);
    customerList.add(c1);
    customerList.add(c2);
    customerList.add(c3);
    customerList.add(c4);
    customerList.add(c5);
}
    @Override
    public List<Customer> selAll() {

        return customerList;
    }

    @Override
    public Customer selById(int  id) {
        Customer c1=new Customer(1,"张三","123456",1);
        return c1;
    }

    @Override
    public boolean delCustomer(int id) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean insert(Customer customer) {
        return false;
    }
}
