package com.destiny.work.service;

import com.destiny.work.common.Utils;
import com.destiny.work.dao.BaseDao;
import com.destiny.work.model.PageEntity;
import com.destiny.work.model.TbItemEntity;
import com.destiny.work.service.BaseService.BaseService;
import com.destiny.work.service.BaseService.UpLoadPictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/8/7.
 */

@Service("itemService")
public class ItemService {

    private TbItemEntity tbItemEntity = null;

    @Resource(name = "baseDao")
    private BaseDao<TbItemEntity> baseDao;

    @Resource(name = "upLoadPictureService")
    private UpLoadPictureService upLoadPictureService;

    public PageEntity<TbItemEntity> findAll(String pageCode, int pageSize) {
        int pc = BaseService.getPageCoed(pageCode);

        return baseDao.pageFind("from TbItemEntity", pc, pageSize);
    }

    public Map<String, Object> addItem(HttpServletRequest request) throws Exception{
        //把带二进制表单数据的request对象交给spring转换 得到一个文件和普通数据分开的新request对象
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, Object> rep_map = new HashMap<>();

        // 获取from表单参数
        String id = multipartRequest.getParameter("id");
        String title = multipartRequest.getParameter("title");
        String price = multipartRequest.getParameter("price");
        String num = multipartRequest.getParameter("num");
        String sell_point = multipartRequest.getParameter("sell_point");
        String cid = multipartRequest.getParameter("cid");

        //获得Request中的图片 photo 是from表单文件的name
        List<MultipartFile> fileList = multipartRequest.getFiles("up_pic");

        Map<String, Object> map = null;
        tbItemEntity = new TbItemEntity();

        for (MultipartFile mf : fileList) {
            if (!mf.isEmpty()) {
                // 对素材进行操作
                map = upLoadPictureService.UpLoad(mf);
            }
        }
        if ("".equals(map.get("imageName"))) {
            return map;
        }
        tbItemEntity.setId(Long.parseLong(id));
        tbItemEntity.setTitle(title);
        tbItemEntity.setPrice(Long.parseLong(price));
        tbItemEntity.setNum(Integer.parseInt(num));
        tbItemEntity.setImage(map.get("imageName").toString());
        tbItemEntity.setSellPoint(sell_point);
        tbItemEntity.setCid(Long.parseLong(cid));
        tbItemEntity.setStatus((byte) 1);
        tbItemEntity.setCreated(Utils.getTimestampTime());
        tbItemEntity.setUpdated(Utils.getTimestampTime());

        try {
            baseDao.save(tbItemEntity);
            rep_map.put("message", "添加成功!");
            rep_map.put("success", true);
        } catch (Exception e) {
            rep_map.put("message", "添加失败!");
            rep_map.put("success", true);
            e.printStackTrace();
        }
        return rep_map;
    }

    public Map<String, Object> delete(long ItemID) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(baseDao.delete("delete TbItemEntity i where i='" + ItemID + "'")) {
                map.put("message", "操作成功！");
                map.put("success", true);
                return map;
            }
        } catch (Exception e) {
            map.put("message", "操作异常！");
            map.put("success", false);
            e.printStackTrace();
            return map;
        }
        map.put("message", "操作失败！");
        map.put("success", false);
        return map;
    }
}
