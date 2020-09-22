package com.haoyu.service;

import com.haoyu.entity.Backend_user;
import com.haoyu.entity.Dev_user;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-01 17:26
 */
public interface BackendService {
    //根据账号查询对象
    Backend_user BACKEND_USER(String userCode);
}
