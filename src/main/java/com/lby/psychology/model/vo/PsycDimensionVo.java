package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestionDimension;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycDimensionVo extends PsycQuestionDimension {

    private Integer scaleType;

    private String scaleName;

    private String scaleTypeName;

}
