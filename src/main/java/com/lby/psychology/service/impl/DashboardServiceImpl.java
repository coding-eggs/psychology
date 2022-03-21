package com.lby.psychology.service.impl;

import com.lby.psychology.mapper.PsycExamRecordMapper;
import com.lby.psychology.model.enums.EnumScaleCategories;
import com.lby.psychology.model.vo.PsycDashboardVo;
import com.lby.psychology.service.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

@Service
public class DashboardServiceImpl implements IDashboardService {

    @Autowired
    private PsycExamRecordMapper recordMapper;

    @Override
    public PsycDashboardVo dashboardDisplay() {

        DecimalFormat df = new DecimalFormat("00%");
        PsycDashboardVo result = new PsycDashboardVo();

        //获取记录总数
        Integer totalCount = recordMapper.selectRecordTotalByScaleType(null);
        result.setTotalCount(totalCount);

        //人际总数量
        Integer interpersonalTotal = recordMapper.selectRecordTotalByScaleType(EnumScaleCategories.INTERPERSONAL.getId());

        //学习总数量
        Integer studyTotal = recordMapper.selectRecordTotalByScaleType(EnumScaleCategories.STUDY.getId());

        //就业总数量
        Integer employTotal = recordMapper.selectRecordTotalByScaleType(EnumScaleCategories.EMPLOY.getId());

        //情感总数量
        Integer emotionTotal = recordMapper.selectRecordTotalByScaleType(EnumScaleCategories.EMOTION.getId());


        result.setInterpersonal(new HashMap<>(){
            {
                put("total",interpersonalTotal);
                put("totalRate",df.format(BigDecimal.valueOf(interpersonalTotal).divide(BigDecimal.valueOf(totalCount),2,BigDecimal.ROUND_UP)));
            }
        } );
        result.setStudy(new HashMap<>(){
            {
                put("total",studyTotal);
                put("totalRate",df.format(BigDecimal.valueOf(studyTotal).divide(BigDecimal.valueOf(totalCount),2,BigDecimal.ROUND_UP)));
            }
        } );

        result.setEmploy(new HashMap<>(){
            {
                put("total",employTotal);
                put("totalRate",df.format(BigDecimal.valueOf(employTotal).divide(BigDecimal.valueOf(totalCount),2,BigDecimal.ROUND_UP)));
            }
        } );

        result.setEmotion(new HashMap<>(){
            {
                put("total",employTotal);
                put("totalRate",df.format(BigDecimal.valueOf(emotionTotal).divide(BigDecimal.valueOf(totalCount),2,BigDecimal.ROUND_UP)));
            }
        } );



        return result;
    }
}
