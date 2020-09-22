package com.ljs.responsitory;

import com.ljs.entity.Customer;

import java.util.List;

public interface CustomerMapper {
    /**
     * 找出全部的客户
     * @return
     */
    public List<Customer> selAll();
    public  Customer selById(int  id);
    public boolean delCustomer(int id);
    public  boolean updateCustomer(Customer customer);
    public  boolean insert(Customer customer);
}
