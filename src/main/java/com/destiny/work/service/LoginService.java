package com.destiny.work.service;

import com.destiny.work.common.Utils;
import com.destiny.work.dao.BaseDao;
import com.destiny.work.model.TbUserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/6/3.
 */

@Service("loginService")
public class LoginService {

    @Resource(name = "baseDao")
    private BaseDao<TbUserEntity> baseDao;

    private TbUserEntity userEntity;

    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, String name, String password, String code) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String hql = "from TbUserEntity u where u.userName='" + name + "'";
        TbUserEntity userEntity = baseDao.findOne(hql);

        System.out.println("loginService----->" + request.getSession().getAttribute("code").toString());

        if (!request.getSession().getAttribute("code").toString().equalsIgnoreCase(code)) {
            map.put("message", "验证码错误，请重新输入！");
            map.put("isSuccess", false);
            return map;
        }

        if (null == userEntity) {
            map.put("message", "用户名不存在");
            map.put("isSuccess", false);
            return map;
        }

        if (password.equals(userEntity.getPassword())) {
            map.put("value", userEntity);
            map.put("message", "登录成功");
            map.put("isSuccess", true);
            Utils.addCookie(response, "userName", name);
            return map;
        }

        map.put("message", "用户名或密码错误!");
        map.put("isSuccess", false);
        return map;
    }
}
