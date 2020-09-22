package com.ljs.service;

import com.ljs.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> selAll();
    public  Customer selById(int  id);
    public boolean delCustomer(int id);
    public  boolean updateCustomer(Customer customer);
    public  boolean insert(Customer customer);
}
