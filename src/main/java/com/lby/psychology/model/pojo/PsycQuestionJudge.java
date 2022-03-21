package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * psyc_question_judge
 * @author lby
 */
@Data
public class PsycQuestionJudge implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer judgeId;

    /**
     * 评判描述
     */
    @ApiModelProperty(value="评判描述")
    private String judgeName;

    /**
     * 评判分数段数
     */
    @ApiModelProperty(value="评判分数段数")
    private Integer startScore;

    /**
     * 评判分数段数
     */
    @ApiModelProperty(value="评判分数段数")
    private Integer endScore;

    private static final long serialVersionUID = 1L;
}
