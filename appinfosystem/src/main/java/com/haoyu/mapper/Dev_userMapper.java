package com.haoyu.mapper;

import com.haoyu.entity.Dev_user;
import com.haoyu.entity.Dev_userExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Dev_userMapper {
    int countByExample(Dev_userExample example);

    int deleteByExample(Dev_userExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dev_user record);

    int insertSelective(Dev_user record);

    List<Dev_user> selectByExample(Dev_userExample example);

    Dev_user selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Dev_user record, @Param("example") Dev_userExample example);

    int updateByExample(@Param("record") Dev_user record, @Param("example") Dev_userExample example);

    int updateByPrimaryKeySelective(Dev_user record);

    int updateByPrimaryKey(Dev_user record);
}