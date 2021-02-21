package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycDimensionCo;
import com.lby.psychology.model.pojo.PsycQuestionDimension;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycDimensionVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PsycQuestionDimensionMapper {
    int deleteByPrimaryKey(Integer dimensionId);

    int insert(PsycQuestionDimension record);

    int insertSelective(PsycQuestionDimension record);

    PsycQuestionDimension selectByPrimaryKey(Integer dimensionId);

    int updateByPrimaryKeySelective(PsycQuestionDimension record);

    int updateByPrimaryKey(PsycQuestionDimension record);

    List<PsycDimensionVo> selectDimensionPageList(PsycDimensionCo co);

    List<PsycDimensionVo> selectDimensionListByScale(PsycScale co);

    int insertDimension(PsycQuestionDimension dimension);

    PsycDimensionVo selectDimensionDetail(Integer dimensionId);

    List<PsycQuestionVo> selectQuestionByDimension(Integer dimensionId);
}
