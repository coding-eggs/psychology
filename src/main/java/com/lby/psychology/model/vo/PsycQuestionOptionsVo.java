package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestionOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycQuestionOptionsVo extends PsycQuestionOptions {

    /**
     * 问题描述
     */
    @ApiModelProperty(value="问题描述")
    private String questionName;


}
