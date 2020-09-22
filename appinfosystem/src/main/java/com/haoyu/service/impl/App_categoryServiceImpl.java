package com.haoyu.service.impl;

import com.haoyu.entity.App_category;
import com.haoyu.entity.App_categoryExample;
import com.haoyu.mapper.App_categoryMapper;
import com.haoyu.service.App_categoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 17:02
 */
@Service("appcate")
public class App_categoryServiceImpl implements App_categoryService {


    @Resource
    App_categoryMapper app_categoryMapper;

    @Override
    public List<App_category> APP_CATEGORIES(Long parentId) {
        App_categoryExample example = new App_categoryExample();
        App_categoryExample.Criteria criteria = example.createCriteria();
        if (parentId == null) {
            criteria.andParentidIsNull();
        } else {
            criteria.andParentidEqualTo(parentId);
        }
        List<App_category> app_categories = app_categoryMapper.selectByExample(example);
        return app_categories;
    }
}
