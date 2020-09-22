package com.haoyu.service;

import com.haoyu.entity.App_info;
import com.haoyu.entity.App_version;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 16:47
 */
public interface DevAppinfoService {
    //查询app
    List<App_info> devlist(Long devid,String querySoftwareName,Long queryStatus,Long queryFlatformId, Long queryCategoryLevel1, Long queryCategoryLevel2, Long queryCategoryLevel3);
    //新增app
    int addinfo(String softwareName, String APKName, String supportROM, String interfaceLanguage, BigDecimal softwareSize,Long downloads,Long flatformId,Long categoryLevel1,Long categoryLevel2,Long categoryLevel3,Long status,String appInfo,String a_logoPicPath,String zhan,Long devid);
    //查询apkname是否存在
    App_info getapkname(String apkname);
    //查询产品的新增版本信息
    List<App_version> INFOS(Long id);
    //修改版本
    int updateappinfoVersion(Long id,Long versionId);
    //修海信息
    int updateAppinfo(App_info app_info);

    int delete(Long id);
}
