package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * psyc_exam_record
 * @author 
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycExamRecord答题记录表")
@Data
public class PsycExamRecord implements Serializable {
    /**
     *  id
     */
    @ApiModelProperty(value=" id")
    private Long recordId;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 量表id
     */
    @ApiModelProperty(value="量表id")
    private Integer scaleId;

    /**
     * 用时
     */
    @ApiModelProperty(value="用时")
    private Date useTime;

    private Date createDate;

    private Date updateDate;

    /**
     * 答题记录正文
     */
    @ApiModelProperty(value="答题记录正文")
    private byte[] recordContent;

    private static final long serialVersionUID = 1L;
}