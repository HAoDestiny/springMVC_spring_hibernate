package com.destiny.work.exception;

/**
 * 自定义系统登录异常信息类
 * Created by Destiny_hao on 2017/7/25.
 */

public class LoginException extends Exception{

    //异常信息
    private String message;

    public LoginException (String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
