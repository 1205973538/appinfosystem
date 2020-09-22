package com.haoyu.mapper;

import com.haoyu.entity.App_category;
import com.haoyu.entity.App_categoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_categoryMapper {
    int countByExample(App_categoryExample example);

    int deleteByExample(App_categoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(App_category record);

    int insertSelective(App_category record);

    List<App_category> selectByExample(App_categoryExample example);

    App_category selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") App_category record, @Param("example") App_categoryExample example);

    int updateByExample(@Param("record") App_category record, @Param("example") App_categoryExample example);

    int updateByPrimaryKeySelective(App_category record);

    int updateByPrimaryKey(App_category record);
}