package com.gjl.weixin.interceptor;

import com.gjl.weixin.cache.CodeCache;
import com.gjl.weixin.cache.GlobalCache;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SysInterceptor extends HandlerInterceptorAdapter {
    /**
     * 进入拦截器后首先进入的方法
     * 返回false则不再继续执行
     * 返回true则继续执行
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception
    {
        //System.out.println("我是拦截器：我证明我进来了");
       HttpSession session=request.getSession();
        //Student userInfo = (Student)session.getAttribute("userInfo");
        Object userInfo = session.getAttribute("userInfo");
        if(request.getRequestURI().contains("weChatConnect")||
                request.getRequestURI().contains("menuAdd")||
                request.getRequestURI().contains("getAccessToken")||
                request.getRequestURI().contains("push")||
                request.getRequestURI().contains("user/get")||
                request.getRequestURI().contains("login")||
                request.getRequestURI().contains("complain/insert")||
                request.getRequestURI().contains("questionnaire/findAll")||
                request.getRequestURI().contains("groupsGet")){
            return true;
        }
        if(userInfo == null )
        {
            System.out.println("我证明用户没有登录");
            if(userInfo == null){

                adminRedirect(request,response);
            }
            return false;
        }
        System.out.println("我证明用户已经登录");
        return  true;
    }
    /**
     * 生成视图时执行，可以用来处理异常，并记录在日志中
     */
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object arg2, Exception exception){}
    /** -
     * 生成视图之前执行，可以修改ModelAndView
     */
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object arg2, ModelAndView arg3)throws Exception{}
    /**
     * 对于请求是ajax请求重定向问题的处理方法
     */
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath+"/indexlogin.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else {
            response.sendRedirect(basePath + "/indexlogin.html");
        }

    }

    public void adminRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath+"/adminlogin.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else {
            response.sendRedirect(basePath + "/adminlogin.html");
        }

    }



}
