package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumDimensionType {

    //10
    IPL_CDSIR_CHAT("ipl_cdsir_chat","人际方面的人际关系综合诊断量表交谈维度"),
    IPL_CDSIR_MFD("ipl_cdsir_mfd","人际方面的人际关系综合诊断量表交友维度"),
    IPL_CDSIR_TACT("ipl_cdsir_tact","人际方面的人际关系综合诊断量表处事维度"),
    IPL_CDSIR_COMM("ipl_cdsir_comm","人际方面的人际关系综合诊断量表异性交往维度"),
    IPL_CDSIR_TSCORE("ipl_cdsir_tscore","人际方面的人际关系综合诊断量表总分维度"),

    //20
    IPL_GWB_LIFE("ipl_gwb_life","人际方面的GWB总体幸福感量表对生活的满足和兴趣维度"),
    IPL_GWB_HEATHY("ipl_gwb_heathy","人际方面的GWB总体幸福感量表对健康的担心维度"),
    IPL_GWB_ENERGY("ipl_gwb_energy","人际方面的GWB总体幸福感量表精力维度"),
    IPL_GWB_MOOD("ipl_gwb_mood","人际方面的GWB总体幸福感量表忧郁或愉快的心境维度"),
    IPL_GWB_CONTROL("ipl_gwb_control","人际方面的GWB总体幸福感量表对情感和行为的控制维度"),
    IPL_GWB_FEEL("ipl_gwb_feel","人际方面的GWB总体幸福感量表松弛和紧张维度"),
    IPL_GWB_TSCORE("ipl_gwb_tscore","人际方面的GWB总体幸福感量表总分维度"),

    //30
    STY_EXAM_TSCORE("sty_exam_tscore","学习方面考前身心健康自我测试量表总分维度"),


    //50
    EMY_PDP_TIGER("emy_pdp_tiger","就业方面的PDP性格测试量表的老虎型"),
    EMY_PDP_PEACOCK("emy_pdp_peacock","就业方面的PDP性格测试量表的孔雀型"),
    EMY_PDP_KOALA("emy_pdp_koala","就业方面的PDP性格测试量表的考拉型"),
    EMY_PDP_OWL("emy_pdp_owl","就业方面的PDP性格测试量表的猫头鹰型"),
    EMY_PDP_CHAMELEON("emy_pdp_chameleon","就业方面的PDP性格测试量表的变色龙型"),

    //70
    EMT_DASS_STRESS("emt_dass_stress","情感方面的DASS-21测试量表的压力维度"),
    EMT_DASS_ANXIETY("emt_dass_anxiety","情感方面的DASS-21测试量表的焦虑维度"),
    EMT_DASS_DEPRESSION("emt_dass_depression","情感方面的DASS-21测试量表的抑郁维度"),

    //80
    EMT_QSA_NATURE("emt_qsa_nature","情感方面的QAS自杀态度调查问卷对自杀行为性质的认识维度"),
    EMT_QSA_SUICIDE("emt_qsa_suicide","情感方面的QAS自杀态度调查问卷对自杀者的态度维度"),
    EMT_QSA_DEPENDENT("emt_qsa_dependent","情感方面的qas自杀态度调查问卷对自杀者家属的态度维度"),
    EMT_QSA_MKILL("emt_qsa_mkill","情感方面的QAS自杀态度调查问卷对安乐死的态度维度")
    ;


    EnumDimensionType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EnumDimensionType getEnumById(String code) {
        for(EnumDimensionType e : EnumDimensionType.values()) {
            if (e.getCode().equals(code))
                return e;
        }
        return null;
    }

    private String code;

    private String name;
}
