package com.lby.psychology.model.vo;

import lombok.Data;

import java.util.Map;

@Data
public class PsycDashboardVo {

    private Integer totalCount;

    private Map<String,Object> interpersonal;

    private Map<String,Object> study;

    private Map<String,Object> employ;

    private Map<String,Object> emotion;

}
