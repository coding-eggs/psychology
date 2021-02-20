package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * psyc_question
 * @author
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycQuestion人际关系问题表")
@Data
public class PsycQuestion implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 问题描述
     */
    @ApiModelProperty(value="问题描述")
    private String questionName;



    private Integer dimension;

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
