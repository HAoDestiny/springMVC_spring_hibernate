package com.destiny.work.controller;

import com.destiny.work.common.Token;
import com.destiny.work.model.TbUserEntity;
import com.destiny.work.model.User;
import com.destiny.work.repository.UserRepository;
import com.destiny.work.service.LoginService;
import com.destiny.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/6/3.
 */

@Controller
@RequestMapping("/account_login")
public class LoginController {

    @Resource(name = "loginService")
    private LoginService loginService;

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //提交表单@ModelAttribute("userEntity") User userEntity（key/value）
    public @ResponseBody
    Map<String, Object> login(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody User userEntity) {

        //提交json
        Map<String, Object> req_map = new HashMap<>();
        System.out.println("login-------");

        try {
            String token = "";
            Map<String, Object> map = loginService.login(request, response,
                    userEntity.getName(), userEntity.getPassword(), userEntity.getCode());

            Object object = map.get("value");
            if (null != object) {
                Map<String, Object> m = new HashMap<>();
                TbUserEntity userEntitys = (TbUserEntity) object;
                m.put("userId", userEntitys.getId());
                token = Token.createToken(m);
                System.out.println("token=======" + token);
                System.out.println(userEntitys.getUserName());

                req_map.put("value", map.get("value"));
                req_map.put("message", map.get("message"));
                req_map.put("success", map.get("isSuccess"));

                //将用户名信息放到session域中，在拦截器里判断是否登录
                request.getSession().setAttribute("userName", userEntity.getName());
                request.getSession().setAttribute("access_key", token);
                return req_map;
            }
            req_map.put("message", map.get("message"));
            req_map.put("success", map.get("isSuccess"));
        } catch (Exception e) {
            req_map.put("message", "异常：登录失败!");
            req_map.put("success", false);
            e.printStackTrace();
        }
        return req_map;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        System.out.println("logout-------");
        request.getSession().invalidate(); //清除session域中的所有信息
        return "redirect:/wap/login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TbUserEntity> test() {
        List<TbUserEntity> list = userRepository.findAll();
        return list;
    }
}
