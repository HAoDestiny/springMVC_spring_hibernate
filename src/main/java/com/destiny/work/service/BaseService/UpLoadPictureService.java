package com.destiny.work.service.BaseService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Destiny_hao on 2017/8/8.
 */

@Service("upLoadPictureService")
public class UpLoadPictureService {

    public Map<String, Object> UpLoad(MultipartFile upload_pic) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //上传图片的原始名称
        String pic_originalFileName = upload_pic.getOriginalFilename();

        //上传图片
        if(null != upload_pic && pic_originalFileName.length()>0 && null != pic_originalFileName) {

            //存储图片的服务器中的物理路径
            String path = "C:\\Users\\hh\\IdeaProjects\\picture\\";

            //新图片名称
            String pic_newFileName = UUID.randomUUID()
                    + java.sql.Date.valueOf(DateFormat.getDateInstance().format(new Date())).toString()
                    + pic_originalFileName.substring(pic_originalFileName.lastIndexOf(".")); //截取图片原名称中的扩展名

            //新图片
            File newFile = new File(path + pic_newFileName);

            //将内存中的数据写入磁盘
            upload_pic.transferTo(newFile);
            map.put("imageName", pic_newFileName);
            map.put("success", true);
            map.put("message", "上传成功！");

        } else {
            map.put("imageName", "");
            map.put("success", false);
            map.put("message", "上传失败！");
        }

        return map;
    }
}
