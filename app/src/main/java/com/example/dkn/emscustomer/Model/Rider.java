package com.example.dkn.emscustomer.Model;

import java.util.Date;

public class Rider {
    private String name,email,phone,password,address;
    private String dob;

    public Rider() {
    }

    public Rider(String name, String email, String phone, String password,String dob, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dob=dob;
        this.address=address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
