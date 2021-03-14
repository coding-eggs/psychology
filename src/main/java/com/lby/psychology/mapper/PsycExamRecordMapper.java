package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycExamRecord;

public interface PsycExamRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(PsycExamRecord record);

    int insertSelective(PsycExamRecord record);

    PsycExamRecord selectByPrimaryKey(Long recordId);

    int updateByPrimaryKeySelective(PsycExamRecord record);

    int updateByPrimaryKeyWithBLOBs(PsycExamRecord record);

    int updateByPrimaryKey(PsycExamRecord record);
}