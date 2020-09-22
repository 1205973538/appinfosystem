package com.haoyu.service.impl;

import com.haoyu.entity.*;
import com.haoyu.mapper.App_categoryMapper;
import com.haoyu.mapper.App_infoMapper;
import com.haoyu.mapper.App_versionMapper;
import com.haoyu.service.DataDictonaryService;
import com.haoyu.service.DevAppinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-08 16:49
 */
@Service("das")
public class DevAppinfoServiceImpl implements DevAppinfoService {
    @Resource
    App_infoMapper app_infoMapper;
    @Resource
    DataDictonaryService dataDictonaryService;
    @Resource
    App_categoryMapper app_categoryMapper;
    @Resource
    App_versionMapper app_versionMapper;

    @Override
    public List<App_info> devlist(Long devid, String querySoftwareName, Long queryStatus, Long queryFlatformId, Long queryCategoryLevel1, Long queryCategoryLevel2, Long queryCategoryLevel3) {
        List<App_info> app_infos = null;
        try {
            App_infoExample app_infoExample = new App_infoExample();
            App_infoExample.Criteria criteria = app_infoExample.createCriteria();
            criteria.andDevidEqualTo(devid);
            if (querySoftwareName != null) {
                criteria.andSoftwarenameLike("%" + querySoftwareName + "%");
            }
            if (queryStatus != 0 && queryStatus != null) {
                criteria.andStatusEqualTo(queryStatus);
            }
            if (queryFlatformId != 0 && queryFlatformId != null) {
                criteria.andFlatformidEqualTo(queryFlatformId);
            }
            if (queryCategoryLevel1 != 0 && queryCategoryLevel1 != null) {
                criteria.andCategorylevel1EqualTo(queryCategoryLevel1);
            }
            if (queryCategoryLevel2 != 0 && queryCategoryLevel2 != null) {
                criteria.andCategorylevel2EqualTo(queryCategoryLevel2);
            }
            if (queryCategoryLevel3 != 0 && queryCategoryLevel3 != null) {
                criteria.andCategorylevel3EqualTo(queryCategoryLevel3);
            }
            app_infos = app_infoMapper.selectByExample(app_infoExample);

            for (App_info app_info : app_infos) {
                //所属平台
                List<Data_dictionary> app_flatform = dataDictonaryService.getList("APP_FLATFORM", app_info.getFlatformid());
                app_info.setFlatformname(app_flatform.get(0).getValuename());
                //一级分类
                App_categoryExample example = new App_categoryExample();
                App_categoryExample.Criteria criteria1 = example.createCriteria();
                criteria1.andIdEqualTo(app_info.getCategorylevel1());
                List<App_category> list1 = app_categoryMapper.selectByExample(example);
                app_info.setCategoryname1(list1.get(0).getCategoryname());
                //二级分类
                App_categoryExample example2 = new App_categoryExample();
                App_categoryExample.Criteria criteria2 = example2.createCriteria();
                criteria2.andIdEqualTo(app_info.getCategorylevel2());
                List<App_category> list2 = app_categoryMapper.selectByExample(example2);
                app_info.setCategoryname2(list2.get(0).getCategoryname());
                //三级分类
                App_categoryExample example3 = new App_categoryExample();
                App_categoryExample.Criteria criteria3 = example3.createCriteria();
                criteria3.andIdEqualTo(app_info.getCategorylevel3());
                List<App_category> list3 = app_categoryMapper.selectByExample(example3);
                app_info.setCategoryname3(list3.get(0).getCategoryname());
                //状态
                List<Data_dictionary> app_flatform1 = dataDictonaryService.getList("APP_STATUS", app_info.getStatus());
                app_info.setStatusname(app_flatform1.get(0).getValuename());
                //版本号
                App_versionExample app_versionExample = new App_versionExample();
                App_versionExample.Criteria criteria4 = app_versionExample.createCriteria();
                if (app_info.getVersionid() != null) {
                    criteria4.andIdEqualTo(app_info.getVersionid());
                    List<App_version> applist = app_versionMapper.selectByExample(app_versionExample);
                    app_info.setVersonno(applist.get(0).getVersionno());
                } else {
                    app_info.setVersonno("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return app_infos;
    }

    @Override
    public int addinfo(String softwareName, String APKName, String supportROM, String interfaceLanguage, BigDecimal softwareSize, Long downloads, Long flatformId, Long categoryLevel1, Long categoryLevel2, Long categoryLevel3, Long status, String appInfo, String a_logoPicPath, String zhan, Long devid) {
        App_info app_info = new App_info();
        app_info.setDevid(devid);
        app_info.setSoftwarename(softwareName);
        app_info.setApkname(APKName);
        app_info.setSupportrom(supportROM);
        app_info.setInterfacelanguage(interfaceLanguage);
        app_info.setSoftwaresize(softwareSize);
        app_info.setDownloads(downloads);
        app_info.setFlatformid(flatformId);
        app_info.setCategorylevel1(categoryLevel1);
        app_info.setCategorylevel2(categoryLevel2);
        app_info.setCategorylevel3(categoryLevel3);
        app_info.setStatus(status);
        app_info.setAppinfo(appInfo);
        app_info.setLogopicpath(zhan);
        app_info.setLogolocpath(a_logoPicPath);
        int a = app_infoMapper.insertSelective(app_info);
        return a;
    }

    @Override
    public App_info getapkname(String apkname) {
        App_info app_info = null;
        try {
            App_infoExample app_infoExample = new App_infoExample();
            App_infoExample.Criteria criteria = app_infoExample.createCriteria();
            criteria.andApknameEqualTo(apkname);
            List<App_info> app_infos = app_infoMapper.selectByExample(app_infoExample);
            app_info = app_infos.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app_info;
    }

    @Override
    public List<App_version> INFOS(Long id) {
        App_versionExample example = new App_versionExample();
        App_versionExample.Criteria criteria = example.createCriteria();
        criteria.andAppidEqualTo(id);
        List<App_version> app_infos = app_versionMapper.selectByExample(example);
        if (app_infos.size()!=0) {
            for (App_version app_info : app_infos) {
                //app名
                App_infoExample app_infoExample = new App_infoExample();
                App_infoExample.Criteria criteria1 = app_infoExample.createCriteria();
                criteria1.andIdEqualTo(id);
                List<App_info> app_infos1 = app_infoMapper.selectByExample(app_infoExample);
                app_info.setSoftwarename(app_infos1.get(0).getSoftwarename());
                //状态
                List<Data_dictionary> app_flatform1 = dataDictonaryService.getList("PUBLISH_STATUS", app_info.getPublishstatus());
                app_info.setPublishstatusname(app_flatform1.get(0).getValuename());
            }
            return app_infos;
        }else{
            System.out.println("没有");
            app_infos = null;
            return app_infos;
        }
    }

    @Override
    public int updateappinfoVersion(Long id,Long versionid) {
        App_info app_info = new App_info();
        app_info.setId(id);
        app_info.setVersionid(versionid);
        int a = app_infoMapper.updateByPrimaryKeySelective(app_info);
        return a;
    }

    @Override
    public int updateAppinfo(App_info app_info) {
        int a = app_infoMapper.updateByPrimaryKeySelective(app_info);
        return a;
    }

    @Override
    public int delete(Long id) {
        int a = app_infoMapper.deleteByPrimaryKey(id);
        return a;
    }
}
