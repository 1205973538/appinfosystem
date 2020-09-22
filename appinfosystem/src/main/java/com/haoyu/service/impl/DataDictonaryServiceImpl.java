package com.haoyu.service.impl;

import com.haoyu.entity.Data_dictionary;
import com.haoyu.entity.Data_dictionaryExample;
import com.haoyu.mapper.Data_dictionaryMapper;
import com.haoyu.service.DataDictonaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 蒿雨
 * @create 2020-09-03 16:22
 */
@Service("dds")
public class DataDictonaryServiceImpl implements DataDictonaryService {
    @Resource
    Data_dictionaryMapper data_dictionaryMapper;

    //通用条件分级查询器（平台查询）
    @Override
    public List<Data_dictionary> getList(String typeCode, Long valueId) {
        Data_dictionaryExample data_dictionaryExample = new Data_dictionaryExample();
        Data_dictionaryExample.Criteria criteria1 = data_dictionaryExample.createCriteria();
       if (typeCode!=null){
           criteria1.andTypecodeEqualTo(typeCode);
       }
       if (valueId!=null) {
           criteria1.andValueidEqualTo(valueId);
       }
        List<Data_dictionary> data_dictionaries = data_dictionaryMapper.selectByExample(data_dictionaryExample);
        return data_dictionaries;
    }
}
