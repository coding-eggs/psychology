package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycQuestionDimension;

public interface PsycQuestionDimensionMapper {
    int deleteByPrimaryKey(Integer dimensionId);

    int insert(PsycQuestionDimension record);

    int insertSelective(PsycQuestionDimension record);

    PsycQuestionDimension selectByPrimaryKey(Integer dimensionId);

    int updateByPrimaryKeySelective(PsycQuestionDimension record);

    int updateByPrimaryKey(PsycQuestionDimension record);
}