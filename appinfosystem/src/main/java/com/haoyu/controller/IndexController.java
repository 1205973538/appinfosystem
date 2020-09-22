package com.haoyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 蒿雨
 * @create 2020-09-01 17:03
 */
@Controller
public class IndexController {

    @RequestMapping("/to")
    public String string() {
        return "index";
    }

    @RequestMapping("/to1")
    public String string1() {
        return "jsp/devlogin";
    }

}
