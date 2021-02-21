package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("模糊查询问题列表")
    @GetMapping("/getQuestionLikeSearch")
    public ResponseData<List<PsycQuestionVo>> getQuestionLikeSearch(String keyword){
        return new ResponseData<>(questionService.getQuestionLikeSearch(keyword));
    }


    @ApiOperation("问题分页列表")
    @PostMapping("/getQuestionPageList")
    public ResponseData<PageResult> getQuestionPageList(@RequestBody PsycQuestionCo co){
        return new ResponseData<>(questionService.getQuestionPageList(co));
    }

    @ApiOperation("获取问题详情")
    @GetMapping("/getQuestionDetail")
    public ResponseData<List<PsycQuestionDetailVo>> getQuestionDetail(Integer scaleId){
        return new ResponseData<>(questionService.getQuestionDetail(scaleId));
    }

    @ApiOperation("新增量表")
    @PostMapping("/addScale")
    public ResponseData<Boolean> addScale(@RequestBody PsycScale co){
        return new ResponseData<>(questionService.addScale(co));
    }

    @ApiOperation("获取量表详情")
    @GetMapping("/getScaleDetail")
    public ResponseData<PsycScale> getScaleDetail(Integer scaleId){
        return new ResponseData<>(questionService.getScaleDetail(scaleId));
    }

    @ApiOperation("更新量表")
    @PostMapping("/updateScale")
    public ResponseData<Boolean> updateScale(@RequestBody PsycScale co){
        return new ResponseData<>(questionService.updateScale(co));
    }

    @ApiOperation("获取量表下拉")
    @GetMapping("/getScaleListByType")
    public ResponseData<List<PsycScale>> getScaleListByType(Integer scaleType){
        return new ResponseData<>(questionService.getScaleListByType(scaleType));
    }

}
