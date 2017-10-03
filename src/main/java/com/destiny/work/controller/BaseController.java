package com.destiny.work.controller;

import com.destiny.work.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Destiny on 2017/5/25.
 */

@Controller
@RequestMapping("/wap")
public class BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/wap_login", method = RequestMethod.GET)
    public String wap_login() {

        return "login";
    }

    /***
     * 返回html页面
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String userName) {

        return "index";
    }

    /***
     * html页面通过ajax请求返回值
     * @return
     */
    @RequestMapping(value = "/getTestMessage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTestMessage(String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("messages", "wocao");
        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(String userName) { //接收重地址传递进来的值
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        System.out.println(userName+"----index");
        if(null == userName) {
            modelAndView.addObject("message", "");
            return modelAndView;
        }
        modelAndView.addObject("message", userName);
        return modelAndView;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }

    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public String pass(String userName, Model model) {
        System.out.println(userName+"------pass");
        model.addAttribute("message", userName);
        return "pass";
    }

    @RequestMapping(value = "/column", method = RequestMethod.GET)
    public String column() {
        return "columns";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account() {
        return "account";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page() {
        return "page";
    }


    @RequestMapping(value = "/adv", method = RequestMethod.GET)
    public String adv() {
        return "adv";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String book() {
        return "book";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/cate", method = RequestMethod.GET)
    public String cate() {
        return "cate";
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ModelAndView getAllUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        //mav.addObject("userMessage", userService.getAllUser());
        return mav;
    }
}
