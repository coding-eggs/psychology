package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * psyc_question_options
 * @author 
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycQuestionOptions选项表")
@Data
public class PsycQuestionOptions implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer optionId;

    /**
     * 所属问题id
     */
    @ApiModelProperty(value="所属问题id")
    private Integer questionId;

    /**
     * 选项编码
     */
    @ApiModelProperty(value="选项编码")
    private String optionCode;

    /**
     * 选项分数
     */
    @ApiModelProperty(value="选项分数")
    private Integer optionScore;

    /**
     * 选项描述
     */
    @ApiModelProperty(value="选项描述")
    private String optionName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}
