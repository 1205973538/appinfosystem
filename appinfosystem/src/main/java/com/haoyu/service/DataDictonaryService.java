package com.haoyu.service;

import com.haoyu.entity.Data_dictionary;

import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 16:22
 */
public interface DataDictonaryService {

    List<Data_dictionary> getList(String typeCode,Long valueId);

}
