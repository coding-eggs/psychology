package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import com.lby.psychology.model.vo.PsycQuestionVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycExamCo extends PageBasic {

    @ApiModelProperty(value = "耗时")
    private String time;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "量表id")
    private Integer scaleId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty("量表名称")
    private String scaleName;

    @ApiModelProperty("问题列表")
    private List<PsycQuestionVo> questionList;

}
