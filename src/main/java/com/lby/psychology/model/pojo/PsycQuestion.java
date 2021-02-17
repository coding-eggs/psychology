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

    /**
     * 10,20,30,40,50,60,70,80(人际关系综合诊断量表,GWB总体幸福感量表的维度,考前身心健康自我测试,匹兹堡睡眠质量PSQI,MBTI职业测试最新版,PDP性格测试,DASS-21测试量表,自杀态度调查问卷（QSA）)
     */
    @ApiModelProperty(value="10,20,30,40,50,60,70,80(人际关系综合诊断量表,GWB总体幸福感量表的维度,考前身心健康自我测试,匹兹堡睡眠质量PSQI,MBTI职业测试最新版,PDP性格测试,DASS-21测试量表,自杀态度调查问卷（QSA）)")
    private Integer scaleCode;

    /**
     * 10,20,30,40(人际，学习，就业，情感)
     */
    @ApiModelProperty(value="10,20,30,40(人际，学习，就业，情感)")
    private Integer type;

    private String dimension;

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