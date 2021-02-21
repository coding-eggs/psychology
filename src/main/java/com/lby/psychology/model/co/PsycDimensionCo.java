package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycDimensionCo extends PageBasic {

    private Integer scaleId;

    private Integer scaleType;

    private String dimensionName;

}
