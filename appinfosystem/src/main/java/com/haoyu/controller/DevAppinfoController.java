package com.haoyu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haoyu.entity.*;
import com.haoyu.mapper.App_infoMapper;
import com.haoyu.mapper.App_versionMapper;
import com.haoyu.service.*;
import com.haoyu.test.App;
import com.sun.deploy.ui.AppInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Version;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 蒿雨
 * @create 2020-09-08 16:37
 */
@Controller
public class DevAppinfoController {
    //平台
    @Resource(name = "dds")
    DataDictonaryService dataDictonaryService;
    //应用信息
    @Resource(name = "appcate")
    App_categoryService app_categoryService;

    @Resource(name = "das")
    DevAppinfoService devAppinfoService;
    //版本
    @Resource(name = "avs")
    App_versonService app_versonService;
    @Resource
    App_versionMapper app_versionMapper;
    @Resource(name = "asi")
    AppinfoService appinfoService;

    @RequestMapping("/dev/app/list")
    public String devflatformlist(@RequestParam(value = "querySoftwareName", required = false, defaultValue = "") String querySoftwareName, @RequestParam(value = "queryStatus", required = false, defaultValue = "0") Long queryStatus, @RequestParam(value = "queryFlatformId", required = false, defaultValue = "0") Long queryFlatformId, @RequestParam(value = "queryCategoryLevel1", required = false, defaultValue = "0") Long queryCategoryLevel1, @RequestParam(value = "queryCategoryLevel2", required = false, defaultValue = "0") Long queryCategoryLevel2, @RequestParam(value = "queryCategoryLevel3", required = false, defaultValue = "0") Long queryCategoryLevel3, @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex, HttpSession session) throws Exception {
        //查询所属平台
        List<Data_dictionary> app_flatform1 = dataDictonaryService.getList("APP_FLATFORM", null);
        session.setAttribute("flatFormList1", app_flatform1);
        //查询状态
        List<Data_dictionary> statusList = dataDictonaryService.getList("APP_STATUS", null);
        session.setAttribute("statusList1", statusList);
        //一级菜单
        List<App_category> app_categories = app_categoryService.APP_CATEGORIES(null);
        session.setAttribute("categoryLevel1List1", app_categories);

        //查询app
        int pageNum = 1;
        if (pageIndex != 0) {
            pageNum = pageIndex;
        }
        Integer pageSize = 5;
        Dev_user dev_user = (Dev_user) session.getAttribute("devUserSession");
        PageHelper.startPage(pageNum, pageSize);
        List<App_info> app_infos = devAppinfoService.devlist(dev_user.getId(), querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
        PageInfo<App_info> pageInfo = new PageInfo<>(app_infos);
        session.setAttribute("appInfo1", app_infos);
        session.setAttribute("appInfoList", pageInfo);


        //回显数据
        session.setAttribute("querySoftwareName", querySoftwareName);
        session.setAttribute("queryStatus", queryStatus);
        session.setAttribute("queryFlatformId", queryFlatformId);
        session.setAttribute("queryCategoryLevel1", queryCategoryLevel1);
        session.setAttribute("queryCategoryLevel2", queryCategoryLevel2);
        session.setAttribute("queryCategoryLevel3", queryCategoryLevel3);
        return "jsp/developer/appinfolist";
    }

    @RequestMapping("/categorylevel11list")
    @ResponseBody
    public List<App_category> tolist2(Long pid, HttpSession session) {
        List<App_category> app_categories2 = app_categoryService.APP_CATEGORIES(pid);
        session.setAttribute("categoryLevel2List", app_categories2);
        return app_categories2;
    }

    @RequestMapping("/categoryleve21list")
    @ResponseBody
    public List<App_category> tolist3(Long pid, HttpSession session) {
        List<App_category> app_categories3 = app_categoryService.APP_CATEGORIES(pid);
        session.setAttribute("categoryLevel3List", app_categories3);
        return app_categories3;
    }

    //添加跳转
    @RequestMapping("/dev/flatform/app/appinfoadd")
    public String appinfoadd() {

        return "jsp/developer/appinfoadd";
    }

    @RequestMapping("/datadictionarylist")
    @ResponseBody
    public List<Data_dictionary> stringObjectMap() {
        List<Data_dictionary> app_flatform1 = dataDictonaryService.getList("APP_FLATFORM", null);
        return app_flatform1;
    }

    //一级分类
    @RequestMapping("/categorylevelAlist")
    @ResponseBody
    public List<App_category> stringObjectMap1() {
        List<App_category> app_categories = app_categoryService.APP_CATEGORIES(null);
        return app_categories;
    }

    //二级分类
    @RequestMapping("/categorylevelBlist")
    @ResponseBody
    public List<App_category> tolist4(Long pid, HttpSession session) {
        List<App_category> app_categories2 = app_categoryService.APP_CATEGORIES(pid);
        return app_categories2;
    }

    @RequestMapping("/apkexist")
    @ResponseBody
    public Map<String, Object> apkexist(String APKName) {
        App_info app_info = devAppinfoService.getapkname(APKName);
        Map<String, Object> map = new HashMap<>();
        if (APKName == null) {
            map.put("APKName", "empty");
            return map;
        } else if (app_info != null) {
            map.put("APKName", "exist");
            return map;
        } else {
            map.put("APKName", "noexist");
            return map;
        }

    }

    //新增
    @RequestMapping("/appinfoaddsave")
    public String appinfoaddsave(String softwareName, String APKName, String supportROM, String interfaceLanguage, BigDecimal softwareSize, Long downloads, Long flatformId, Long categoryLevel1, Long categoryLevel2, Long categoryLevel3, Long status, String appInfo, MultipartFile a_logoPicPath, HttpServletRequest request, HttpSession session) {
        String jue = null;
        String zhan = null;
        Dev_user dev_user = (Dev_user) session.getAttribute("devUserSession");
        if (dev_user == null) {
            return "jsp/devlogin";
        }
        if (a_logoPicPath != null) {
            //获取上传路径
            String path = request.getSession().getServletContext().getRealPath("statics/images" + File.separator);
            //获取原文件名
            String oldFileName = a_logoPicPath.getOriginalFilename();
            //获取扩展名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            //生成新文件名
            String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "img.jpg";
            //封装文件对象
            File targetFile = new File(path, fileName);
            //绝对路径
            jue = path + File.separator + fileName;
            //展示路径
            zhan = "statics/images" + File.separator + fileName;
            try {
                //实现上传
                a_logoPicPath.transferTo(targetFile);
                System.out.println("上传成功！");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("长度" + a_logoPicPath.getSize());
            //验证文件大小和格式
            int filesize = 500000;
            if (a_logoPicPath.getSize() > filesize) {
                request.setAttribute("fileUploadError", " * 上传大小不得超过 500k");
                return "jsp/developer/appinfoadd";
            }
            List<String> prefixs = new ArrayList<>();
            prefixs.add("jpg");
            prefixs.add("png");
            prefixs.add("jpeg");
            prefixs.add("pneg");
            if (prefixs.contains(prefix) == false) {
                request.setAttribute("fileUploadError", " * 上传图片格式不正确");
                return "jsp/developer/appinfoadd";
            }
        }
        //添加方法
        int num = devAppinfoService.addinfo(softwareName, APKName, supportROM, interfaceLanguage, softwareSize, downloads, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, status, appInfo, jue, zhan, dev_user.getId());
        return "redirect:/dev/app/list";
    }

    //新增版本跳转
    @RequestMapping("/appversionadd")
    public String appversionadd(Long id, HttpSession session) {
        List<App_version> app_infos = devAppinfoService.INFOS(id);
        session.setAttribute("appVersionList", app_infos);
        session.setAttribute("appid", id);
        return "jsp/developer/appversionadd";
    }

    //新增版本号
    @RequestMapping("/addversionsave")
    public String addversionsave(App_version app_version, MultipartFile a_downloadLink, HttpSession session) throws Exception {
        String juedui;
        if (a_downloadLink != null) {
            //获取上传路径
            String path = session.getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            //获取原文件名
            String oldFileName = a_downloadLink.getOriginalFilename();
            //获取扩展名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            //生成新文件名
            String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_." + prefix;
            //封装文件对象
            File targetFile = new File(path, fileName);
            //绝对路径
            juedui = path + File.separator + fileName;
            app_version.setApkfilename(oldFileName);
            app_version.setApklocpath(juedui);
            app_version.setModifydate(new Date());
            try {
                //实现上传
                a_downloadLink.transferTo(targetFile);
                System.out.println("上传成功！");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //验证文件大小和格式
            int filesize = 500000;
            if (a_downloadLink.getSize() > filesize) {
                session.setAttribute("fileUploadError", " * 上传大小不得超过 500k");
                return "jsp/developer/appversionadd";
            }
            List<String> prefixs = new ArrayList<>();
            prefixs.add("apk");
            if (prefixs.contains(prefix) == false) {
                session.setAttribute("fileUploadError", " * 上传安装包格式不正确");
                return "jsp/developer/appversionadd";
            }
            int a = app_versonService.versionadd(app_version);

            App_versionExample app_versionExample = new App_versionExample();
            App_versionExample.Criteria criteria = app_versionExample.createCriteria();
            criteria.andApklocpathEqualTo(app_version.getApklocpath());
            List<App_version> app_versions = app_versionMapper.selectByExample(app_versionExample);
            int b = devAppinfoService.updateappinfoVersion(app_version.getAppid(), app_versions.get(0).getId());
        }
        return "redirect:/dev/app/list";
    }

    //修改版本信息
    @RequestMapping("/appversionmodify")
    public String appversionmodify(Long vid, Long aid, HttpSession session) {
        System.out.println(vid);
        System.out.println(aid);
        //历史版本
        List<App_version> app_infos = devAppinfoService.INFOS(aid);
        session.setAttribute("appVersionList", app_infos);
        //查询当前版本号
        App_version app_version = app_versonService.getVersion(vid, aid);
        session.setAttribute("appVersion", app_version);
        return "jsp/developer/appversionmodify";
    }

    //修改最新版本
    @RequestMapping("/appversionmodifysave")
    public String appversionmodifysave(App_version app_version, MultipartFile attach, HttpSession session) {
        Dev_user dev_user = (Dev_user) session.getAttribute("devUserSession");
        app_version.setModifyby(dev_user.getId());
        app_version.setModifydate(new Date());
        String juedui;
        String xiangdui;
        if (attach.getSize() != 0) {
            //获取上传路径
            String path = session.getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            //获取原文件名
            String oldFileName = attach.getOriginalFilename();
            //获取扩展名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            //生成新文件名
            String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "." + prefix;
            //封装文件对象
            File targetFile = new File(path, fileName);
            //绝对路径
            juedui = path + File.separator + fileName;
            xiangdui = "statics/uploadfiles" + File.separator + fileName;
            app_version.setApkfilename(oldFileName);
            app_version.setApklocpath(juedui);
            app_version.setModifydate(new Date());
            app_version.setDownloadlink(xiangdui);
            List<String> prefixs = new ArrayList<>();
            //验证文件大小和格式
            int filesize = 500000;
            if (attach.getSize() > filesize) {
                session.setAttribute("fileUploadError", " * 上传大小不得超过 500k");
                return "jsp/developer/appversionmodify";
            }
            prefixs.add("apk");
            if (prefixs.contains(prefix) == false) {
                session.setAttribute("fileUploadError", " * 上传安装包格式不正确");
                return "jsp/developer/appversionmodify";
            }
            try {
                //实现上传
                attach.transferTo(targetFile);
                System.out.println("上传成功！");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int num = app_versonService.updateVersion(app_version);
            return "redirect:/dev/app/list";
        } else {
            int num = app_versonService.updateVersion(app_version);
            return "redirect:/dev/app/list";
        }
    }

    @RequestMapping("/appinfomodify")
    public String appinfomodify(Long id, HttpSession session) {
        App_info a = appinfoService.getApp(id);
        session.setAttribute("appInfo", a);
        return "jsp/developer/appinfomodify";
    }

    //动态返回平台属性
    @RequestMapping("/datadictionarylist1")
    @ResponseBody
    public List<Data_dictionary> datadictionarylist(String tcode) {
        List<Data_dictionary> data_dictionaries = dataDictonaryService.getList(tcode, null);
        return data_dictionaries;
    }

    //修改App基础信息
    @RequestMapping("/appinfomodifysave")
    public String appinfomodifysave(App_info app_info, MultipartFile attach, HttpSession session) {
        Dev_user dev_user = (Dev_user) session.getAttribute("devUserSession");
        app_info.setUpdatedate(new Date());
        app_info.setModifyby(dev_user.getId());
        app_info.setModifydate(new Date());
        String juedui;
        String xiangdui;
        if (attach.getSize() != 0) {
            //获取上传路径
            String path = session.getServletContext().getRealPath("statics/uploadfiles" + File.separator);
            //获取原文件名
            String oldFileName = attach.getOriginalFilename();
            //获取扩展名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            //生成新文件名
            String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "img." + prefix;
            //封装文件对象
            File targetFile = new File(path, fileName);
            //绝对路径
            juedui = path + File.separator + fileName;
            //相对路径
            xiangdui = "statics/uploadfiles" + File.separator + fileName;
            //验证文件大小和格式
            int filesize = 500000;
            if (attach.getSize() > filesize) {
                session.setAttribute("fileUploadError", " * 上传大小不得超过 500k");
                return "redirect:/appinfomodify";
            }
            List<String> prefixs = new ArrayList<>();
            prefixs.add("jpg");
            prefixs.add("png");
            prefixs.add("jpeg");
            prefixs.add("pneg");
            if (prefixs.contains(prefix) == false) {
                session.setAttribute("fileUploadError", " * 上传图片格式不正确");
                return "redirect:/appinfomodify";
            }
            try {
                //实现上传
                attach.transferTo(targetFile);
                System.out.println("上传成功！");
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            app_info.setLogopicpath(xiangdui);
            app_info.setLogolocpath(juedui);
            int a = devAppinfoService.updateAppinfo(app_info);
            return "redirect:/dev/app/list";
        }
        int a = devAppinfoService.updateAppinfo(app_info);
        return "redirect:/dev/app/list";
    }

    //删除文件
    @RequestMapping("/delfile")
    @ResponseBody
    public Map<String, Object> delfile(Long id, String flag) {
        Map<String, Object> map = new HashMap<>();
        if (flag.equals("logo")) {
            App_info app_info = appinfoService.getApp(id);
            String locpath = app_info.getLogolocpath();
            //删除文件
            File file = new File(locpath);
            if (file.exists()) {
                if (file.delete()) {
                    app_info.setLogolocpath("");
                    app_info.setLogopicpath("");
                    int num = devAppinfoService.updateAppinfo(app_info);
                    if (num > 0) {
                        map.put("result", "success");
                    } else {
                        map.put("result", "failed");
                    }
                }
            }
            return map;
        } else if (flag.equals("apk")) {
            App_version app_version = app_versonService.getVersion(id);
            String locpath = app_version.getApklocpath();
            //删除文件
            File file = new File(locpath);
            if (file.exists()) {
                if (file.delete()) {
                    app_version.setDownloadlink("");
                    app_version.setApkfilename("");
                    app_version.setApklocpath("");
                    int num = app_versonService.updateVersion(app_version);
                    if (num > 0) {
                        map.put("result", "success");
                    } else {
                        map.put("result", "failed");
                    }
                }
            }
            return map;
        }
        return map;
    }

    //查看app所有基础信息
    @RequestMapping("/appview")
    public String appview(long id, HttpSession session) {
        //基础信息
        App_info a = appinfoService.getApp(id);
        session.setAttribute("appInfo", a);

        //历史版本
        List<App_version> app_infos = devAppinfoService.INFOS(id);
        session.setAttribute("appVersionList", app_infos);
        return "jsp/developer/appinfoview";
    }

    //上下架
    @RequestMapping(value = "/dev/app/{appId}/sale", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> sale(@PathVariable Long appId) {
        Map<String, Object> map = new HashMap<>();
        int a = 0;
        App_info app_infos = (App_info) appinfoService.getApp(appId);
        try {
            if (app_infos.getStatus() != 4 && app_infos.getStatus() != 5) {
                a = appinfoService.updateUpDowm(appId, Long.valueOf(4));
                map.put("errorCode", '0');
                map.put("resultMsg", "success");
            } else if (app_infos.getStatus() == 4) {
                a = appinfoService.updateUpDowm(appId, Long.valueOf(5));
                map.put("errorCode", '0');
                map.put("resultMsg", "success");
            } else if (app_infos.getStatus() == 5) {
                a = appinfoService.updateUpDowm(appId, Long.valueOf(4));
                map.put("errorCode", '0');
                map.put("resultMsg", "success");
            }
            if (a == 0) {
                map.put("resultMsg", "failed");
            }
        } catch (Exception e) {
            map.put("errorCode", "exception000001");
        }
        return map;
    }

    //删除所有的数据
    @RequestMapping("/delapp")
    @ResponseBody
    public Map<String,Object> delapp(Long id) {
        Map<String, Object> map = new HashMap<>();
        App_info app_info = appinfoService.getApp(id);
        //查询该app下所有版本
        List<App_version> app_versions = app_versonService.VERSIONS(id);
        if (app_info != null) {
            for (App_version app_version : app_versions) {
                String locpath = app_version.getApklocpath();
                //删除文件
                File file = new File(locpath);
                if (file.exists()) {
                    file.delete();
                }
            }
            //删除版本
            if (app_versions.size() > 0) {
                app_versonService.deleteAll(app_versions);
            }
            //删除图片
            String locpath = app_info.getLogolocpath();
            //删除文件
            File file = new File(locpath);
            if (file.exists()) {
                file.delete();
            }
            int a = devAppinfoService.delete(app_info.getId());
            if (a!=0) {
                map.put("delResult", "true");
            } else {
                map.put("delResult", "false");
            }
        } else {
            map.put("delResult", "notexist");
        }
        return map;
    }
}
