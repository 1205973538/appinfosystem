package com.haoyu.mapper;

import com.haoyu.entity.Backend_user;
import com.haoyu.entity.Backend_userExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Backend_userMapper {
    int countByExample(Backend_userExample example);

    int deleteByExample(Backend_userExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Backend_user record);

    int insertSelective(Backend_user record);

    List<Backend_user> selectByExample(Backend_userExample example);

    Backend_user selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Backend_user record, @Param("example") Backend_userExample example);

    int updateByExample(@Param("record") Backend_user record, @Param("example") Backend_userExample example);

    int updateByPrimaryKeySelective(Backend_user record);

    int updateByPrimaryKey(Backend_user record);
}