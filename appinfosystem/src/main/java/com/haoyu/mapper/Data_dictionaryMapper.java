package com.haoyu.mapper;

import com.haoyu.entity.Data_dictionary;
import com.haoyu.entity.Data_dictionaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Data_dictionaryMapper {
    int countByExample(Data_dictionaryExample example);

    int deleteByExample(Data_dictionaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Data_dictionary record);

    int insertSelective(Data_dictionary record);

    List<Data_dictionary> selectByExample(Data_dictionaryExample example);

    Data_dictionary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Data_dictionary record, @Param("example") Data_dictionaryExample example);

    int updateByExample(@Param("record") Data_dictionary record, @Param("example") Data_dictionaryExample example);

    int updateByPrimaryKeySelective(Data_dictionary record);

    int updateByPrimaryKey(Data_dictionary record);
}