package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
