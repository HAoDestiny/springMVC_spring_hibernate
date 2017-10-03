package com.destiny.work.controller;

import com.destiny.work.service.BaseService.VerificationCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Destiny_hao on 2017/8/8.
 */

@Controller
@RequestMapping("/verificationCode")
public class VerificationCodeController {

    @Resource(name = "verificationCodeService")
    private VerificationCodeService codeService;

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        codeService.getVerifiCodeImg(response, request);
    }
}
