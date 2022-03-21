package com.lby.psychology.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycExamCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.PsycExamRecordDetailVo;
import com.lby.psychology.model.vo.PsycJudgeVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IExaminationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = "考试接口API")
@RestController
@RequestMapping("/exam")
public class ExaminationController {


    @Autowired
    private IExaminationService examinationService;

    @ApiOperation("获取答题详情")
    @GetMapping("/getExamDetail")
    public ResponseData<PsycExamCo> getExamDetail(Integer scaleId, Authentication authentication) {
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        PsycExamCo psycExamVo = new PsycExamCo();
        List<PsycQuestionVo> questionVoList = examinationService.getExamDetail(scaleId);
        psycExamVo.setQuestionList(questionVoList);
        psycExamVo.setScaleName(questionVoList.get(0).getScaleName());
        psycExamVo.setNickname(securityPsycUser.getNickname());
        return new ResponseData<>(psycExamVo);
    }


    @ApiOperation("新增答题记录")
    @PostMapping("/examScale")
    public ResponseData<List<PsycJudgeVo>> examScale(@RequestBody PsycExamCo co,Authentication authentication) throws JsonProcessingException {
        return new ResponseData<>(examinationService.examScale(co,((SecurityPsycUser)authentication.getPrincipal()).getUserId()));
    }


    @ApiOperation("查询当前用户的答题记录")
    @PostMapping("/getCurrentUserRecord")
    public ResponseData<PageResult> getCurrentUserRecord(@RequestBody PsycExamCo co, Authentication authentication){
        co.setUserId(((SecurityPsycUser)authentication.getPrincipal()).getUserId());
        return new ResponseData<>(examinationService.getCurrentUserRecord(co));
    }

    @ApiOperation("查询用户的的答题记录")
    @PostMapping("/getRecordByUserId")
    public ResponseData<PageResult> getRecordByUserId(@RequestBody PsycExamCo co){
        return new ResponseData<>(examinationService.getCurrentUserRecord(co));
    }


    @ApiOperation("查询记录详情")
    @GetMapping("/getRecordDetail")
    public ResponseData<PsycExamRecordDetailVo> getRecordDetail(Long recordId) throws IOException {
        return new ResponseData<>(examinationService.getRecordDetail(recordId));
    }

}
