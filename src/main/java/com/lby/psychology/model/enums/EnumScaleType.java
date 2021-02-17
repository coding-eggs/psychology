package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumScaleType {

    IPL_CDSIR(10,"人际关系综合诊断量表"),
    IPL_GWB(20,"人际方面的GWB总体幸福感量表"),
    STY_EXAM(30,"考前身心健康自我测试"),
    STY_PSQI(40,"匹兹堡睡眠质量PSQI"),
    EMY_PDP(50,"就业方面的PDP性格测试量表"),
    EMT_DASS(70,"情感方面的DASS-21测试量表"),
    EMT_QSA(80,"情感方面的QAS自杀态度调查问卷"),;



    public static EnumScaleType getEnumById(int id) {
        for(EnumScaleType e : EnumScaleType.values()) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }


    EnumScaleType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private final Integer id;

    private final String name;
}
