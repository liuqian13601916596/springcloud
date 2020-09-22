package com.ljs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @顾客
 */
public class Customer implements Serializable {
    private  int cid;
    private  String cname;
    private  String password;
    private int state;

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}
