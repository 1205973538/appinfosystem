package com.haoyu.controller;

import com.haoyu.entity.Dev_user;
import com.haoyu.service.DevService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 蒿雨
 * @create 2020-09-08 15:59
 */
@Controller
public class DevController {


    @Resource(name = "ds")
    DevService devService;

    //开发者登录
    @RequestMapping("/dev/login")
    public String devlogin() {
        return "jsp/devlogin";
    }

    //开发者登录
    @RequestMapping("/devdologin")
    public String devdologin(HttpSession session,String devCode, String devPassword) {
        Dev_user dev_user = devService.DEV_USERS(devCode);
        if (dev_user == null) {
            session.setAttribute("error", "用户不存在！");
            return "jsp/devlogin";
        }
        if (!devPassword.equals(dev_user.getDevpassword())) {
            session.setAttribute("error", "你的密码有误！");
            return "jsp/devlogin";
        }
        session.setAttribute("devUserSession", dev_user);
        return "jsp/developer/main";
    }
    //开发者退出
    @RequestMapping("/dev/logout")
    public String devlogout(HttpSession session){
        session.invalidate();
        return "index";
    }

}
