package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.model.vo.PsycJudgeVo;
import com.lby.psychology.service.IJudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "评判接口API")
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
    public ResponseData<Boolean> addJudge(@RequestBody PsycJudgeVo co){
        return new ResponseData<>(judgeService.addJudge(co));
    }

    @ApiOperation(value = "获取评判规则详情")
    @GetMapping(value = "/getJudgeInfo")
    public ResponseData<PsycJudgeVo> getJudgeInfo(Integer judgeId){
        return new ResponseData<>(judgeService.getJudgeInfo(judgeId));
    }

    @ApiOperation(value = "删除评判规则")
    @GetMapping(value = "/deleteJudge")
    public ResponseData<Boolean> deleteJudge(@RequestParam("judgeId") Integer judgeId){
        return new ResponseData<Boolean>(judgeService.deleteJudge(judgeId));
    }

    @ApiOperation(value = "更新评判规则")
    @PostMapping(value = "/updateJudge")
    public ResponseData<Boolean> updateJudge(@RequestBody PsycQuestionJudge psycQuestionJudge){
        return new ResponseData<>(judgeService.updateJudge(psycQuestionJudge));
    }

}
