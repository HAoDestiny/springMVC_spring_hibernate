package com.destiny.work.interceptors;

import com.destiny.work.common.Token;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录验证拦截器
 * Created by Destiny_hao on 2017/7/26.
 */
public class LoginInterceptors implements HandlerInterceptor {

    /**
     * 在进入controller前执行
     * 应用：用于身份验证，身份授权
     * 比如身份认证不通过，需要此方法进行拦截，不再向下执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        /**
         * 1.访问公共地址时放行，这里的公共地址有登录页面的地址和获取验证码
         * 2.检查session域中是否有登录的信息（用户名等）
         */

        //获取拦截的地址
        String url = httpServletRequest.getRequestURI().toString();


        //进行判断
        if (url.indexOf("login") > 0 || url.indexOf("getCode") > 0 || url.indexOf("register") > 0) {
            return true;
        }

        //获取session中的用户信息，判断是否为空
        if (null == httpServletRequest.getSession().getAttribute("userName")) {
            httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        String token = httpServletRequest.getParameter("token");
        if (null != token) {
            Map<String, Object> claims = Token.checkToken(token);
            if (null != claims) {
                return true;
            }
        }

        String headerToken = httpServletRequest.getHeader("Authorization");
        if (null != headerToken) {
            Map<String, Object> claims = Token.checkToken(headerToken);
            if (null != claims) {
                return true;
            }
        }

        if (url.indexOf("index") > 0) {
            return true;
        }

        //否则进行登录页面页面跳转
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    /**
     * 在进入controller后，返回modelAndView前执行
     * 应用：返回公用的模型数据，统一返回视图
     * 比如：多级的菜单导航（）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在controller执行完成后执行
     * 应用：统一的异常处理，统一的日志处理
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
