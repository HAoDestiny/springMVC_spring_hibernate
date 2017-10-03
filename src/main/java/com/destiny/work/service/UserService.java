package com.destiny.work.service;

import com.destiny.work.dao.BaseDao;
import com.destiny.work.model.PageEntity;
import com.destiny.work.model.TbUserEntity;
import com.destiny.work.model.User;
import com.destiny.work.repository.UserRepository;
import com.destiny.work.service.BaseService.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by destiny on 2017/5/25.
 */


@Service("userService")
public class UserService {

    @Resource(name = "baseDao")
    private BaseDao<TbUserEntity> baseDao;

    @Autowired
    UserRepository userRepository;

    private Timestamp timestamp;
    private TbUserEntity userEntity;

    public Map<String, Object> register(User user) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println(user.getName());
        //String hql = "from TbUserEntity u where u.userName='" + user.getName() + "'";

        userEntity = new TbUserEntity();
        if (null == userRepository.findByUserName(user.getName())) {
            userEntity.setUserName(user.getName());
            userEntity.setPassword(user.getPassword());
            userEntity.setPhone(user.getPhone());
            userEntity.setEmail(user.getEmail());
            userEntity.setCreated(new Timestamp(System.currentTimeMillis()));
            userEntity.setUpdated(new Timestamp(System.currentTimeMillis()));
            userRepository.saveAndFlush(userEntity);
            //baseDao.save(userEntity); //保存用户
            map.put("message", "注册成功");
            map.put("success", true);
            return map;
        }
        map.put("message", "用户名已存在！");
        map.put("success", false);
        return map;
    }

    public Map<String, Object> checkPass(String userName, String password) throws Exception{
        Map<String, Object> map = new HashMap<>();
        //String hql = "from TbUserEntity u where u.userName='" + userName +"'";
        userEntity = new TbUserEntity();
        try {
            userEntity = userRepository.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!password.equals(userEntity.getPassword())) {
            map.put("message", "密码错误，请重新输入");
            map.put("success", false);
            return map;
        }
        map.put("message", "密码正确");
        map.put("success", true);
        return map;
    }

    public Map<String, Object> modify_pass(String userName, String renewpass) throws Exception{
        Map<String, Object> map = new HashMap<>();
        timestamp = new Timestamp(System.currentTimeMillis());
        //String  hql = "update TbUserEntity u set u.password='"+ renewpass +"', u.updated='"+ timestamp +"' where userName='" + userName+"'";

        try {
            userRepository.modifyPass(renewpass, timestamp, userName);
            //baseDao.upDate(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("message", "修改成功");
        map.put("success", true);
        return map;
    }

    /**
     * 分页查询
     * @param pageCoed
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageEntity<TbUserEntity> findAll(String pageCoed, int pageSize) throws Exception{
        int pc = BaseService.getPageCoed(pageCoed);

        return baseDao.pageFind("from TbUserEntity", pc, pageSize);
    }

    /***
     * 查询全部
     * @return
     * @throws Exception
     */
    public List<TbUserEntity> findAll() throws Exception {
        String hql = "from TbUserEntity";

        List<TbUserEntity> list = baseDao.findAll(hql);

        return list;
    }

}
