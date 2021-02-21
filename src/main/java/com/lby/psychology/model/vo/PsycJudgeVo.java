package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestionJudge;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycJudgeVo extends PsycQuestionJudge {

    private Integer scaleId;

    private Integer scaleType;

    private Integer dimensionId;

    private String dimensionName;

}
