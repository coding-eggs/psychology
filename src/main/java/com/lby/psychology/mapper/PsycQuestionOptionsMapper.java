package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycOptionCo;
import com.lby.psychology.model.pojo.PsycQuestionOptions;
import com.lby.psychology.model.vo.PsycQuestionOptionsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycQuestionOptionsMapper {
    int deleteByPrimaryKey(Integer optionId);

    int insert(PsycQuestionOptions record);

    int insertSelective(PsycQuestionOptions record);

    PsycQuestionOptions selectByPrimaryKey(Integer optionId);

    int updateByPrimaryKeySelective(PsycQuestionOptions record);

    int updateByPrimaryKey(PsycQuestionOptions record);

    List<PsycQuestionOptionsVo> selectOptionListPage(PsycOptionCo co);

    PsycQuestionOptionsVo selectOptionInfo(Integer optionId);

    int insertOption(PsycOptionCo options);
}
