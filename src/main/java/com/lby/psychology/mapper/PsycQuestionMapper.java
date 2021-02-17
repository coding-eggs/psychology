package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.vo.PsycQuestionVo;
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
}
