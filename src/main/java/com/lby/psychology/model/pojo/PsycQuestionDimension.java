package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * psyc_question_dimension
 * @author 
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycQuestionDimension维度表")
@Data
public class PsycQuestionDimension implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer dimensionId;

    /**
     * 维度描述
     */
    @ApiModelProperty(value="维度描述")
    private String dimensionName;

    /**
     * 量表id
     */
    @ApiModelProperty(value="量表id")
    private Integer scaleId;

    /**
     * 维度编码
     */
    @ApiModelProperty(value="维度编码")
    private String dimensionCode;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;
}