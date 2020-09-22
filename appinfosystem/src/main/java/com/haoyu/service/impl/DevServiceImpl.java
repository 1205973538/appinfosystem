package com.haoyu.service.impl;

import com.haoyu.entity.Dev_user;
import com.haoyu.entity.Dev_userExample;
import com.haoyu.mapper.Dev_userMapper;
import com.haoyu.service.DevService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 16:09
 */
@Service("ds")
public class DevServiceImpl implements DevService {
    @Resource
    Dev_userMapper dev_userMapper;

    @Override
    public Dev_user DEV_USERS(String devcode) {
        Dev_user dev_user = null;
        Dev_userExample example = new Dev_userExample();
        Dev_userExample.Criteria criteria = example.createCriteria();
        criteria.andDevcodeEqualTo(devcode);
        List<Dev_user> dev_users = dev_userMapper.selectByExample(example);
        if (dev_users.size() > 0) {
            dev_user= dev_users.get(0);
        }
        return dev_user;
    }
}
