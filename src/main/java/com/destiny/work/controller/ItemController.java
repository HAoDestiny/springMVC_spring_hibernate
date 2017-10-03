package com.destiny.work.controller;

import com.destiny.work.model.PageEntity;
import com.destiny.work.model.TbItemEntity;
import com.destiny.work.service.BaseService.UpLoadPictureService;
import com.destiny.work.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/8/7.
 */

@Controller
@RequestMapping("/items")
public class ItemController {

    @Resource(name = "itemService")
    private ItemService itemService;

    @Resource(name = "upLoadPictureService")
    private UpLoadPictureService upLoadPictureService;

    @RequestMapping(value = "/finalPageItem", method = RequestMethod.GET)
    public String finalPageItem(Model model, String pageCode) {
        System.out.println("findItemPage----------");

        PageEntity<TbItemEntity> pageEntity = itemService.findAll(pageCode, 10);

        if (null != pageEntity) {
            model.addAttribute("ItemPageEntity", pageEntity);
        } else {
            System.out.println("null----------");
            model.addAttribute("ItemPageEntity", null);
        }
        return "item";
    }


    /***
     * @param up_pic 上传文件的input 属性name的值 要一致
     * @param title
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @ResponseBody
//    public Map<String, Object> addItem (@RequestParam(value = "up_pic", required = false) MultipartFile up_pic,
//                                        String title, String price, String num, String sell_point, String cid) {
    public Map<String, Object> addItem(HttpServletRequest request) throws Exception {
        System.out.println("addItem----------");
        return itemService.addItem(request);
    }

    /***
     * 删除item
     * @param ItemID
     * @return
     */
    @RequestMapping(value = "/delItem/{ItemID}", method = RequestMethod.GET)
    @ResponseBody //防止变为jsp地址访问， 返回jsp页面
    public Map<String, Object> delItem(@PathVariable String ItemID) throws Exception{
        System.out.println("delItem----------" +" ,ID= " + ItemID);
        return itemService.delete(Long.parseLong(ItemID));
    }
}
