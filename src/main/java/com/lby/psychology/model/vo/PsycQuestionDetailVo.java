package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestionJudge;
import lombok.Data;

import java.util.List;

@Data
public class PsycQuestionDetailVo {

    //维度编码
    private Integer dimensionId;
    //维度描述
    private String dimensionName;
    //该维度得列表
    private List<PsycQuestionVo> detailList;
    //该维度得评判规则
    private List<PsycQuestionJudge> judgeList;
}
