package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.model.vo.PsycJudgeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycQuestionJudgeMapper {
    int deleteByPrimaryKey(Integer judgeId);

    int insert(PsycQuestionJudge record);

    int insertSelective(PsycQuestionJudge record);

    int deleteDimension(@Param("dimensionId") Integer dimensionId);

    PsycQuestionJudge selectByPrimaryKey(Integer judgeId);

    int updateByPrimaryKeySelective(PsycQuestionJudge record);

    int updateByPrimaryKey(PsycQuestionJudge record);

    List<PsycJudgeVo> selectJudgeList(PsycJudgeCo co);

    List<PsycQuestionJudge> selectJudgeListByDimension(@Param("dimension") Integer dimension);

    int insertDimensionJudgeRlt(PsycJudgeVo vo);

    PsycJudgeVo selectJudgeInfo(Integer judgeId);

    PsycJudgeVo selectJudgeByScore(@Param("dimensionId") Integer dimensionId, @Param("score") Integer score);
}
