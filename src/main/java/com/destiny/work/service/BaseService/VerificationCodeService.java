package com.destiny.work.service.BaseService;

import com.destiny.work.common.VerificationCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Destiny_hao on 2017/8/8.
 */

@Service("verificationCodeService")
public class VerificationCodeService {

    private VerificationCode verificationCode;

    public void getVerifiCodeImg(HttpServletResponse response, HttpServletRequest request) throws IOException {

        verificationCode = new VerificationCode(90, 35, 4, 35);
        verificationCode.write(response.getOutputStream());
        //System.out.println("codeService---------> "+verificationCode.getCode());
        request.getSession().setAttribute("code", verificationCode.getCode());
    }
}
