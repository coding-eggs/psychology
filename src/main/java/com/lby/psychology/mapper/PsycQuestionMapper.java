package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PsycQuestion record);

    int insertSelective(PsycQuestion record);

    PsycQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PsycQuestion record);

    int updateByPrimaryKey(PsycQuestion record);

    List<PsycQuestionVo> selectQuestionLikeSearch(@Param("keyword") String keyword);

    List<PsycQuestionVo> selectQuestionScale(PsycQuestionCo co);

    List<PsycQuestionVo> selectQuestionDetail(@Param("scaleId") Integer Id);

    int insertScale(PsycScale co);
}
