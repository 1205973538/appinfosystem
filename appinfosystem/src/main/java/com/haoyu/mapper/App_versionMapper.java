package com.haoyu.mapper;

import com.haoyu.entity.App_version;
import com.haoyu.entity.App_versionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_versionMapper {
    int countByExample(App_versionExample example);

    int deleteByExample(App_versionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(App_version record);

    int insertSelective(App_version record);

    List<App_version> selectByExample(App_versionExample example);

    App_version selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") App_version record, @Param("example") App_versionExample example);

    int updateByExample(@Param("record") App_version record, @Param("example") App_versionExample example);

    int updateByPrimaryKeySelective(App_version record);

    int updateByPrimaryKey(App_version record);

    void deleteall(List<App_version> app_versions);
}