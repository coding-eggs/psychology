package com.lby.psychology.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Long userId;

    /**
     * 量表id
     */
    @ApiModelProperty(value="量表id")
    private Integer scaleId;

    /**
     * 用时
     */
    @JsonFormat(pattern = "HH:mm:ss")
    @ApiModelProperty(value="用时")
    private LocalTime useTime;

    private Date createDate;

    private Date updateDate;

    /**
     * 答题记录正文
     */
    @ApiModelProperty(value="答题记录正文")
    private byte[] recordContent;

    @ApiModelProperty(value="答题评判正文")
    private byte[] judgeContent;

    private static final long serialVersionUID = 1L;
}
