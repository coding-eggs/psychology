package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.service.IJudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RequestMapping("/judge")
@RestController
public class JudgeController {

    @Autowired
    private IJudgeService judgeService;

    @ApiOperation(value = "分页获取评判列表")
    @PostMapping(value = "/getJudgeList")
    public ResponseData<PageResult> getJudgeList(@RequestBody PsycJudgeCo co){
        return new ResponseData<>(judgeService.getJudgeList(co));
    }

    @ApiOperation(value = "新增评判规则")
    @PostMapping(value = "/addJudge")
    public ResponseData<Boolean> addJudge(@RequestBody PsycQuestionJudge co){
        return new ResponseData<>(judgeService.addJudge(co));
    }

}
