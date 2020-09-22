package com.haoyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haoyu.entity.*;
import com.haoyu.mapper.App_categoryMapper;
import com.haoyu.mapper.App_infoMapper;
import com.haoyu.mapper.App_versionMapper;
import com.haoyu.service.AppinfoService;
import com.haoyu.service.DataDictonaryService;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 16:19
 */
@Service("asi")
public class AppinfoServiceImpl implements AppinfoService {

    @Resource
    App_infoMapper app_infoMapper;
    @Resource
    DataDictonaryService dataDictonaryService;
    @Resource
    App_categoryMapper app_categoryMapper;
    @Resource
    App_versionMapper app_versionMapper;

    @Override
    public List<App_info> APP_INFOS(String querySoftwareName, Long queryFlatformId, Long queryCategoryLevel1, Long queryCategoryLevel2, Long queryCategoryLevel3) {
        List<App_info> app_infos = null;
        try {
            App_infoExample app_infoExample = new App_infoExample();
            App_infoExample.Criteria criteria = app_infoExample.createCriteria();
            if (querySoftwareName != null) {
                criteria.andSoftwarenameLike("%" + querySoftwareName + "%");
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
                //所属平台d
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
                }
                List<App_version> applist = app_versionMapper.selectByExample(app_versionExample);
                app_info.setVersonno(applist.get(0).getVersionno());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return app_infos;
    }

    @Override
    public void UPDATE(Long id, Long status) {
        App_info app_info = new App_info();
        app_info.setId(id);
        app_info.setStatus(status);
        app_infoMapper.updateByPrimaryKeySelective(app_info);

    }

    @Override
    public App_info getApp(Long id) {
        App_info app_info = app_infoMapper.selectByPrimaryKey(id);
        //状态
        List<Data_dictionary> app_flatform1 = dataDictonaryService.getList("APP_STATUS", app_info.getStatus());
        app_info.setStatusname(app_flatform1.get(0).getValuename());
        //所属平台d
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
        return app_info;
    }

    @Override
    public int updateUpDowm(Long id,Long num) {
        App_info app_info = new App_info();
        app_info.setStatus(num);
        app_info.setId(id);
        int a = app_infoMapper.updateByPrimaryKeySelective(app_info);
        return a;
    }

}
