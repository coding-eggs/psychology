package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycQuestionJudgeMapper {
    int deleteByPrimaryKey(Integer judgeId);

    int insert(PsycQuestionJudge record);

    int insertSelective(PsycQuestionJudge record);

    PsycQuestionJudge selectByPrimaryKey(Integer judgeId);

    int updateByPrimaryKeySelective(PsycQuestionJudge record);

    int updateByPrimaryKey(PsycQuestionJudge record);

    List<PsycQuestionJudge> selectJudgeList(PsycJudgeCo co);
}
