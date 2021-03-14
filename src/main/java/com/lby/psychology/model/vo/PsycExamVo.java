package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PsycExamVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty("量表名称")
    private String scaleName;

    @ApiModelProperty("问题列表")
    private List<PsycQuestionVo> questionList;

}
