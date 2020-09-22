package com.haoyu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haoyu.entity.App_category;
import com.haoyu.entity.App_info;
import com.haoyu.entity.App_version;
import com.haoyu.entity.Data_dictionary;
import com.haoyu.service.App_categoryService;
import com.haoyu.service.App_versonService;
import com.haoyu.service.AppinfoService;
import com.haoyu.service.DataDictonaryService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 16:16
 */
@Controller
public class AppController {
    @Resource(name = "dds")
    DataDictonaryService dataDictonaryService;
    @Resource(name = "appcate")
    App_categoryService app_categoryService;
    @Resource(name = "asi")
    AppinfoService appinfoService;
    @Resource(name="avs")
    App_versonService app_versonService;
    //审核App
    @RequestMapping("/admin/backend/app/list")
    public String tolist(HttpSession session, @RequestParam(value = "querySoftwareName", required = false, defaultValue = "") String querySoftwareName, @RequestParam(value = "queryFlatformId", required = false, defaultValue = "0") Long queryFlatformId, @RequestParam(value = "queryCategoryLevel1", required = false, defaultValue = "0") Long queryCategoryLevel1, @RequestParam(value = "queryCategoryLevel2", required = false, defaultValue = "0") Long queryCategoryLevel2,@RequestParam(value = "queryCategoryLevel3", required = false, defaultValue = "0") Long queryCategoryLevel3, @RequestParam(value = "pageIndex", required = false, defaultValue = "0")int pageIndex) throws Exception {
        //查询所属平台
        List<Data_dictionary> app_flatform = dataDictonaryService.getList("APP_FLATFORM", null);
        session.setAttribute("flatFormList", app_flatform);
        List<App_category> app_categories = app_categoryService.APP_CATEGORIES(null);
        session.setAttribute("categoryLevel1List", app_categories);
        //查询app
        int pageNum = 1;
        if (pageIndex != 0) {
            pageNum = pageIndex;
        }
        Integer pageSize = 5;
        PageHelper.startPage(pageNum, pageSize);
        List<App_info> app_infos = appinfoService.APP_INFOS(querySoftwareName, queryFlatformId, queryCategoryLevel1,queryCategoryLevel2, queryCategoryLevel3);
        PageInfo<App_info> pageInfo = new PageInfo<>(app_infos);
        session.setAttribute("appInfo1",app_infos);
        session.setAttribute("appInfoList", pageInfo);
        //回显数据
        session.setAttribute("querySoftwareName",querySoftwareName);
        session.setAttribute("queryFlatformId",queryFlatformId);
        session.setAttribute("queryCategoryLevel1",queryCategoryLevel1);
        session.setAttribute("queryCategoryLevel2",queryCategoryLevel2);
        session.setAttribute("queryCategoryLevel3",queryCategoryLevel3);
        return "jsp/backend/applist";
    }

    @RequestMapping("/categorylevellist")
    @ResponseBody
    public List<App_category> tolist2(Long pid,HttpSession session) {
        List<App_category> app_categories2 = app_categoryService.APP_CATEGORIES(pid);
        session.setAttribute("categoryLevel2List",app_categories2);
        return app_categories2;
    }

    @RequestMapping("/categoryleve2list")
    @ResponseBody
    public List<App_category> tolist3(Long pid,HttpSession session) {
        List<App_category> app_categories3 = app_categoryService.APP_CATEGORIES(pid);
        session.setAttribute("categoryLevel3List",app_categories3);
        return app_categories3;
    }

    @RequestMapping("/check")
    public String check(Integer aid,Long vid,HttpSession session){
        //a查基本信息
        List<App_info> appinfos = (List<App_info>) session.getAttribute("appInfo1");
        System.out.println(appinfos.size());
        for (App_info appinfo : appinfos) {
            if (aid == Integer.valueOf(String.valueOf(appinfo.getId()))){
                session.setAttribute("appInfo",appinfo);
                break;
            }
        }
        //v查版本信息
        App_version app_version = app_versonService.getVersion(vid);
        session.setAttribute("appVersion",app_version);
        return "jsp/backend/appcheck";
    }
    //审核
    @RequestMapping("/checksave")
    public String checksave(Long id,Long status){
        //修改
        appinfoService.UPDATE(id,status);
        return "redirect:/admin/backend/app/list";
    }

}
