package com.haoyu.service;

import com.haoyu.entity.Dev_user;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 16:08
 */
public interface DevService {
    //根据账号查询对象
    Dev_user DEV_USERS(String devcode);
}
