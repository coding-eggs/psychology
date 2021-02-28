package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycDimensionCo extends PageBasic {

    @ApiModelProperty("量表id")
    private Integer scaleId;

    @ApiModelProperty("量表类型")
    private Integer scaleType;

    @ApiModelProperty("维度描述")
    private String dimensionName;

}
