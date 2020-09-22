package com.haoyu.service;

import com.haoyu.entity.App_category;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 17:02
 */
public interface App_categoryService {
    //分类查询
    List<App_category> APP_CATEGORIES(Long parentId);
}
