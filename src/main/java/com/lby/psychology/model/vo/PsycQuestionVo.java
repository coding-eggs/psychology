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

    @ApiModelProperty("量表描述")
    private String scaleName;

    @ApiModelProperty(value = "量表类型")
    private Integer scaleType;

    @ApiModelProperty(value = "量表类型描述")
    private String scaleTypeName;

    @ApiModelProperty(value = "维度名称")
    private String dimensionName;

    @ApiModelProperty(value = "是否做过")
    private boolean done;

    private List<PsycQuestionOptionsVo> optionList;

}
