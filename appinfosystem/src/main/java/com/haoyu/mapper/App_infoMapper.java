package com.haoyu.mapper;

import com.haoyu.entity.App_info;
import com.haoyu.entity.App_infoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_infoMapper {
    int countByExample(App_infoExample example);

    int deleteByExample(App_infoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(App_info record);

    int insertSelective(App_info record);

    List<App_info> selectByExample(App_infoExample example);

    App_info selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") App_info record, @Param("example") App_infoExample example);

    int updateByExample(@Param("record") App_info record, @Param("example") App_infoExample example);

    int updateByPrimaryKeySelective(App_info record);

    int updateByPrimaryKey(App_info record);
}