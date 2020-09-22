package com.haoyu.service.impl;

import com.haoyu.entity.App_version;
import com.haoyu.entity.App_versionExample;
import com.haoyu.mapper.App_versionMapper;
import com.haoyu.service.App_versonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 15:02
 */
@Service("avs")
public class App_versionServceImpl implements App_versonService {

    @Resource
    App_versionMapper app_versionMapper;
    //查询版本号
    @Override
    public App_version getVersion(Long vid) {
        App_versionExample app_versionExample = new App_versionExample();
        App_versionExample.Criteria criteria = app_versionExample.createCriteria();
        if (vid!=null){
            criteria.andIdEqualTo(vid);
        }
        return app_versionMapper.selectByPrimaryKey(vid);
    }

    @Override
    public int versionadd(App_version app_version) {
        int a =  app_versionMapper.insertSelective(app_version);
        return a;
    }

    @Override
    public App_version getVersion(Long vid, Long aid) {

        App_versionExample example = new App_versionExample();
        App_versionExample.Criteria criteria = example.createCriteria();
        criteria.andAppidEqualTo(aid);
        criteria.andIdEqualTo(vid);
        List<App_version> app_versions = app_versionMapper.selectByExample(example);
        if (app_versions.size()>0){
            return app_versions.get(0);
        }else{
            return null ;

        }
    }

    @Override
    public int updateVersion(App_version app_version) {
        int a =app_versionMapper.updateByPrimaryKeySelective(app_version);
        return a;
    }

    @Override
    public List<App_version> VERSIONS(Long id) {
        App_versionExample example = new App_versionExample();
        App_versionExample.Criteria criteria = example.createCriteria();
        criteria.andAppidEqualTo(id);
        return app_versionMapper.selectByExample(example);
    }

    @Override
    public void deleteAll(List<App_version> app_versions) {
        app_versionMapper.deleteall(app_versions);
    }
}
