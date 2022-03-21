package com.lby.psychology.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lby.psychology.model.co.PsycExamCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.vo.PsycExamRecordDetailVo;
import com.lby.psychology.model.vo.PsycExamRecordVo;
import com.lby.psychology.model.vo.PsycJudgeVo;
import com.lby.psychology.model.vo.PsycQuestionVo;

import java.io.IOException;
import java.util.List;


public interface IExaminationService {

    /**
    * 根据量表id获取考试详情
    * @author lby
    * @param scaleId 量表id
    * @date 2021/3/9 21:58
    * @return java.util.List<com.lby.psychology.model.vo.PsycQuestionVo>
    */
    List<PsycQuestionVo> getExamDetail(Integer scaleId);

    /**
     * 考试
     * @param co
     * @return
     */
    List<PsycJudgeVo> examScale(PsycExamCo co, Long userId) throws JsonProcessingException;

    /**
    * 查询个人答题记录
    * @author lby
    * @param co 参数
    * @date 2021/3/30 16:19
    * @return com.lby.psychology.model.common.PageResult
    */
    PageResult getCurrentUserRecord(PsycExamCo co);


    /**
    * 获取答题详情
    * @author lby
    * @param [recordId] 记录id
    * @date 2021/3/30 16:20
    * @return com.lby.psychology.model.vo.PsycExamRecordDetailVo
    */
    PsycExamRecordDetailVo getRecordDetail(Long recordId) throws IOException;


}
