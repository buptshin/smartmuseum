package com.example.smartmuseum.model;

import java.util.List;

//用户类
public class User {
    //地址
    private List<Address> address;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
