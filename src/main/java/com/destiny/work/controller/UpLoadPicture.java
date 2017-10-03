package com.destiny.work.controller;

import com.destiny.work.service.BaseService.UpLoadPictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/7/25.
 */

@Controller
@RequestMapping("/upload")
public class UpLoadPicture {

    @Resource(name = "upLoadPictureService")
    private UpLoadPictureService upLoadPictureService;

    @ResponseBody
    @RequestMapping(value = "/up_pic", method = RequestMethod.POST)
    /**
     * @RequestParam(value = "upload_pic", required = false) MultipartFile upload_pic
     * upload_pic（属性名要一致）
     */
    public Map<String, Object> up_pic(@RequestParam(value = "upload_pic", required = false) MultipartFile upload_pic) throws IOException {
        System.out.println("upload---");
        Map<String, Object> map = upLoadPictureService.UpLoad(upload_pic);
        return map;
    }
}
