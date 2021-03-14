package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycOptionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionOptions;
import com.lby.psychology.model.vo.PsycQuestionOptionsVo;
import com.lby.psychology.service.IOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "选项接口API")
@RestController
@RequestMapping("/option")
public class OptionController {


    @Autowired
    private IOptionService optionService;

    @ApiOperation("分页获取选项列表")
    @PostMapping("/getOptionPageList")
    public ResponseData<PageResult> getOptionPageList(@RequestBody PsycOptionCo co){
        return new ResponseData<>(optionService.getOptionPageList(co));
    }

    @ApiOperation("查询选项详情")
    @GetMapping("/getOptionInfo")
    public ResponseData<PsycQuestionOptionsVo> getOptionInfo(Integer optionId){
        return new ResponseData<>(optionService.getOptionInfo(optionId));
    }

    @ApiOperation("新增选项")
    @PostMapping("/addOption")
    public ResponseData<Boolean> addOption(@RequestBody @Validated PsycOptionCo co){
        return new ResponseData<>(optionService.addOption(co));
    }

    @ApiOperation("删除选项")
    @GetMapping("/deleteOption")
    public ResponseData<Boolean> deleteOption(Integer optionId){
        return new ResponseData<>(optionService.deleteOption(optionId));
    }

    @ApiOperation("更新选项")
    @PostMapping("/updateOption")
    public ResponseData<Boolean> updateOption(@RequestBody PsycQuestionOptions options){
        return new ResponseData<>(optionService.updateOption(options));
    }

}
