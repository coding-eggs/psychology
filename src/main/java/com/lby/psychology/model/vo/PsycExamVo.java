package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class PsycExamVo {

    @ApiModelProperty(value = "耗时")
    private LocalTime time;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty("量表名称")
    private String scaleName;

    @ApiModelProperty("问题列表")
    private List<PsycQuestionVo> questionList;

}
