package com.destiny.work.controller;

import com.alibaba.fastjson.JSONArray;
import com.destiny.work.model.PageEntity;
import com.destiny.work.model.TbUserEntity;
import com.destiny.work.model.User;
import com.destiny.work.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/6/13.
 */
@Controller
@RequestMapping({"user", "/"})
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    /***
     * 注册
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody User userEntity) {
        System.out.println("register-------");
        System.out.println(userEntity.getName() + userEntity.getPassword());
        Map<String, Object> ret_map = new HashMap<>();
        try {
            Map<String,Object> map = userService.register(userEntity);
            ret_map.put("msg", map.get("message"));
            ret_map.put("success", map.get("success"));

        } catch (Exception e) {
            ret_map.put("msg", "异常：注册失败，请重新注册！");
            ret_map.put("success", false);
            e.printStackTrace();
        }
        return ret_map;
    }

    /***
     * 修改密码前确认密码
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/checkPass", method = RequestMethod.POST)
    @ResponseBody                       //@RequestBody Map<String, Object> data
    public Map<String, Object> checkPass(@RequestBody User userEntity) {
        System.out.println("checkPass--------->");
//        JSONObject jsonObject = new JSONObject(data);
        Map<String, Object> ret_map = new HashMap<String, Object>();

        System.out.println(userEntity.getName().trim());
        try {
            Map<String, Object> map = userService.checkPass(userEntity.getName().trim(), userEntity.getPassword());
            ret_map.put("message", map.get("message"));
            ret_map.put("success", map.get("success"));
        } catch (Exception e) {
            ret_map.put("message", "异常：请检查网络是否连接");
            ret_map.put("success", false);
            e.printStackTrace();
        }

        return ret_map;
    }

    /***
     * 修改密码
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    @ResponseBody                       //@RequestBody Map<String, Object> data
    public Map<String, Object> modifyPass(@RequestBody User userEntity, HttpServletRequest request) {
        System.out.println("modify");
        //JSONObject jsonObject = new JSONObject(data);
        Map<String, Object> ret_map = new HashMap<String, Object>();

        //System.out.println(jsonObject.get("userName").toString().trim());
        //System.out.println(jsonObject.get("password").toString());

        try {
            if(userEntity.getName().trim() == null || userEntity.getPassword() == null) {
                System.out.println("null");
                return null;
            }
            Map<String, Object> map = userService.modify_pass(userEntity.getName().trim(),
                    userEntity.getPassword());
            ret_map.put("message", map.get("message"));
            ret_map.put("success", map.get("success"));
            request.getSession().invalidate();
        } catch (Exception e) {

            ret_map.put("message","异常!");
            ret_map.put("success", false);
            e.printStackTrace();
        }

        return ret_map;
    }

    /***
     * 分页查询
     * @param model
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public String pageList(String pageCoed, Model model) {

        try {
          PageEntity<TbUserEntity> pageEntity = userService.findAll(pageCoed, 3);
          model.addAttribute("pageEntity", pageEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "account";
    }

    /***
     * 查询全部
     * @param model
     * @return
     */
    @RequestMapping(value = "/allList", method = RequestMethod.GET)
    public String account(Model model) {
        try {
            List<TbUserEntity> li = userService.findAll();
            model.addAttribute("userList", JSONArray.toJSONString(li).toString());
        } catch (Exception e) {
            model.addAttribute("userList", null);
            e.printStackTrace();
        }

        return "account";
    }

    @RequestMapping(value = "/allListJSON", method = RequestMethod.GET)
    public String allListJSON(Model model) throws Exception {
        List<TbUserEntity> li = userService.findAll();
        System.out.println(JSONArray.toJSONString(li).toString());
        return "error";
    }

}
