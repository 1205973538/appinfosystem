package com.haoyu.controller;

import com.haoyu.entity.Backend_user;
import com.haoyu.service.BackendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 蒿雨
 * @create 2020-09-03 13:59
 */
@Controller
public class BackedController {
    @Resource(name = "bs")
    BackendService backendService;
    //跳转后台登录页面
    @RequestMapping("/admin/login")
    public String login() {
        return "jsp/backendlogin";
    }

    //登录方法
    @RequestMapping("/dologin")
    public String dologin(String userCode, String userPassword, Model model, HttpSession session){
        Backend_user backendUser = backendService.BACKEND_USER(userCode);
        if (backendUser==null){
            model.addAttribute("error","用户名不存在");
            return  "jsp/backendlogin";
        }else if(!userPassword.equals(backendUser.getUserpassword())){
            model.addAttribute("error","密码不正确");
            return  "jsp/backendlogin";
        }else{
            session.setAttribute("userSession",backendUser);
            return "jsp/backend/main";
        }
    }

    //退出方法
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
