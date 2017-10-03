package com.destiny.work.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常信息处理器
 * Created by Destiny_hao on 2017/7/25.
 */

public class LoginExceptionResolver implements HandlerExceptionResolver{

    private String message;

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        LoginException loginException = null;

        //解析出异常类型
        //如果异常类型是系统自定义的的异常，直接取出异常类型，在错误页面显示
        if(e instanceof LoginException) {
            loginException = (LoginException)e;
        } else {
            loginException = new LoginException("未知错误");
        }

        message = loginException.getMessage(); //取出错误信息

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("message", message);

        modelAndView.setViewName("error");

        return modelAndView;

        //最后在springMVc中配置
    }
}
