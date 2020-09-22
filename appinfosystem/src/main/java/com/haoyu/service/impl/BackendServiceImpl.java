package com.haoyu.service.impl;

import com.haoyu.entity.*;
import com.haoyu.mapper.Backend_userMapper;
import com.haoyu.mapper.Data_dictionaryMapper;
import com.haoyu.mapper.Dev_userMapper;
import com.haoyu.service.BackendService;
import com.haoyu.service.DataDictonaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-01 17:26
 */
@Service("bs")
public class BackendServiceImpl implements BackendService {

    @Resource
    Backend_userMapper backend_userMapper;
    @Resource(name = "dds")
    DataDictonaryService dataDictonaryService;
    //登陆方法
    @Override
    public Backend_user BACKEND_USER(String userCode) {
        Backend_user backendUser = null;

        Backend_userExample backend_userExample = new Backend_userExample();
        Backend_userExample.Criteria criteria = backend_userExample.createCriteria();
        criteria.andUsercodeEqualTo(userCode);
        List<Backend_user> backend_users = backend_userMapper.selectByExample(backend_userExample);
        if (backend_users.size()>0){
            backendUser = backend_users.get(0);
            List<Data_dictionary> data_dictionaries = dataDictonaryService.getList("USER_TYPE", null);
            backendUser.setUserTypeName(data_dictionaries.get(0).getValuename());
        }
        return backendUser;
    }
}
