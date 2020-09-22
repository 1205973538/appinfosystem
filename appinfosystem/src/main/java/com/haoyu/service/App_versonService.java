package com.haoyu.service;

import com.haoyu.entity.App_version;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 15:01
 */
public interface App_versonService {
    App_version getVersion(Long vid);
    //新增版本信息
    int versionadd(App_version app_version);
    //查询当前版本号
    App_version getVersion(Long vid,Long aid);
    //修改当前版本号
    int updateVersion(App_version app_version);
    //查询app所有的版本
    List<App_version> VERSIONS(Long id);

    void deleteAll(List<App_version> app_versions);
}
