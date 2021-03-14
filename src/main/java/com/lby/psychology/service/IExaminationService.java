package com.lby.psychology.service;

import com.lby.psychology.model.vo.PsycQuestionVo;

import java.util.List;


public interface IExaminationService {

    /**
    * 根据量表id获取考试详情
    * @author myk
    * @param scaleId 量表id
    * @date 2021/3/9 21:58
    * @return java.util.List<com.lby.psychology.model.vo.PsycQuestionVo>
    */
    List<PsycQuestionVo> getExamDetail(Integer scaleId);

}
