package com.destiny.work.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/9/1.
 */

public class LoginListener implements HttpSessionAttributeListener{

    private HttpSession session;
    /**
     * 存放在线用户ID静态集合
     */
    public static List<String> onLineUserIdList = new ArrayList<String>();

    private Map<String, HttpSession> map = new HashMap<>();

    /**
     * 当前session中有属性增加时
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        //System.out.println("attributeAdded--------->" + name + ", sessionID------>" + httpSessionBindingEvent.getSession().getId());
        if (name.equals("userName")) {
            String userName = httpSessionBindingEvent
                    .getSession().getAttribute("userName").toString();

            if (map.get(userName) != null) {
                session = map.get(userName);
                session.removeAttribute(userName);
                session.invalidate();
            }
            map.put(userName, httpSessionBindingEvent.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        System.out.println("attributeRemoved--------->" + name);
        if(name.equals("userName")) {
            map.remove("userName");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public Map<String, HttpSession> getMap() {
        return map;
    }

    public void setMap(Map<String, HttpSession> map) {
        this.map = map;
    }
}
