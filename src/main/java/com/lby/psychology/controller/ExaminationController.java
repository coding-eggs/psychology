package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.PsycExamVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IExaminationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "考试接口API")
@RestController
@RequestMapping("/exam")
public class ExaminationController {


    @Autowired
    private IExaminationService examinationService;

    @ApiOperation("获取答题详情")
    @GetMapping("/getExamDetail")
    public ResponseData<PsycExamVo> getExamDetail(Integer scaleId, Authentication authentication) {
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        PsycExamVo psycExamVo = new PsycExamVo();
        List<PsycQuestionVo> questionVoList = examinationService.getExamDetail(scaleId);
        psycExamVo.setQuestionList(questionVoList);
        psycExamVo.setScaleName(questionVoList.get(0).getScaleName());
        psycExamVo.setNickname(securityPsycUser.getNickname());
        return new ResponseData<>(psycExamVo);
    }


}
