package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "问题接口API")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("模糊问题列表")
    @GetMapping("/getQuestionLikeSearch")
    public ResponseData<List<PsycQuestionVo>> getQuestionLikeSearch(String keyword){
        return new ResponseData<>(questionService.getQuestionLikeSearch(keyword));
    }

    @ApiOperation("获取问题分页列表")
    @PostMapping("/getQuestionPageList")
    public ResponseData<PageResult> getQuestionPageList(@RequestBody PsycQuestionCo co){
        return new ResponseData<>(questionService.getQuestionPageList(co));
    }

    @ApiOperation("新增问题")
    @PostMapping("/addQuestion")
    public ResponseData<Boolean> addQuestion(@RequestBody PsycQuestion vo){
        return new ResponseData<>(questionService.addQuestion(vo));
    }

    @ApiOperation("获取问题详情")
    @GetMapping("/getQuestionInfo")
    public ResponseData<PsycQuestionVo> getQuestionInfo(Integer questionId){
        return new ResponseData<>(questionService.getQuestionInfo(questionId));
    }

    @ApiOperation("更新问题")
    @PostMapping("/updateQuestion")
    public ResponseData<Boolean> updateQuestion(@RequestBody PsycQuestion vo){
        return new ResponseData<>(questionService.updateQuestion(vo));
    }

}
