package com.destiny.work.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/5/25.
 */
public class User {

    @JSONField(serialize = false)
    private String password;
    private String userName;
    private String code;
    private String email;
    private String phone;

    public User(){

    }

    public User(String password, String userName, String phone, String email, String code) {
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
