package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycExamRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycExamRecordVo extends PsycExamRecord {

    private String scaleName;

    private Integer scaleType;

    private String nickName;

    private String scaleTypeName;



}
