package com.haoyu.service;

import com.github.pagehelper.PageInfo;
import com.haoyu.entity.App_info;
import com.haoyu.entity.Data_dictionary;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 16:19
 */
public interface AppinfoService {
    //查询app
    List<App_info> APP_INFOS(String querySoftwareName, Long queryFlatformId, Long queryCategoryLevel1, Long queryCategoryLevel2, Long queryCategoryLevel3);

    void UPDATE(Long id,Long status);
    //根据id查商品
     App_info getApp(Long id);
     //上下架方法
    int updateUpDowm(Long id,Long num);
}
