package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * psyc_scale
 * @author 
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycScale量表 表")
@Data
public class PsycScale implements Serializable {
    /**
     * 量表id
     */
    @ApiModelProperty(value="量表id")
    private Integer scaleId;

    /**
     * 量表描述
     */
    @ApiModelProperty(value="量表描述")
    private String scaleName;

    /**
     * 量表类型（10人际，20学习，30就业，40情感）
     */
    @ApiModelProperty(value="量表类型（10人际，20学习，30就业，40情感）")
    private Integer scaleType;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;
}