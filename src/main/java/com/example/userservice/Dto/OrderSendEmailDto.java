package com.example.userservice.Dto;

public class OrderSendEmailDto {

    public  String address;
    public  String name;
    public  String email;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderSendEmailDto(  String address,  String name, String email){
        this.address= address;
        this.name = name;
        this.email = email;
    }
}
