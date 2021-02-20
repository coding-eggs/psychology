package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.pojo.PsycQuestionOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycQuestionVo extends PsycQuestion {


    /**
     * 量表id
     */
    @ApiModelProperty(value="量表id")
    private Integer scaleId;

    private String scaleName;

    private Integer scaleType;

    private String scaleTypeName;

    private String dimensionName;

    private List<PsycQuestionOptions> optionList;

}
