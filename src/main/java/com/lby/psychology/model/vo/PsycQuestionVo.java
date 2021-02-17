package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycQuestion;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycQuestionVo extends PsycQuestion {

    private String scaleTypeName;

}
