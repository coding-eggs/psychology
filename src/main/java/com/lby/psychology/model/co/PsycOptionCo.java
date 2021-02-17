package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycOptionCo extends PageBasic {

    @NotNull(message = "问题不能为空")
    private Integer questionId;

    private String questionName;

    @NotNull(message = "选项编码不能为空")
    private String optionCode;

    @NotNull(message = "选项分数不能为空")
    private Integer optionScore;

    @NotNull(message = "选项描述不能为空")
    private String optionName;


}
