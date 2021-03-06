package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycJudgeCo extends PageBasic {

    private Integer scaleId;

    private Integer scaleType;

    private Integer dimensionId;

    private Integer startScore;

    private Integer endScore;
}
