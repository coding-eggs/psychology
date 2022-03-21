package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;
import com.lby.psychology.service.IScaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "量表接口API")
@RestController
@RequestMapping("/scale")
public class ScaleController {

    @Autowired
    private IScaleService scaleService;

    @ApiOperation("量表分页列表")
    @PostMapping("/getScalePageList")
    public ResponseData<PageResult> getScalePageList(@RequestBody PsycQuestionCo co){
        return new ResponseData<>(scaleService.getScalePageList(co));
    }

    @ApiOperation("获取问题详情")
    @GetMapping("/getScaleQuestionDetail")
    public ResponseData<List<PsycQuestionDetailVo>> getScaleQuestionDetail(Integer scaleId){
        return new ResponseData<>(scaleService.getScaleQuestionDetail(scaleId));
    }

    @ApiOperation("新增量表")
    @PostMapping("/addScale")
    public ResponseData<Boolean> addScale(@RequestBody PsycScale co){
        return new ResponseData<>(scaleService.addScale(co));
    }

    @ApiOperation("更新量表")
    @PostMapping("/updateScale")
    public ResponseData<Boolean> updateScale(@RequestBody PsycScale co){
        return new ResponseData<>(scaleService.updateScale(co));
    }

    @ApiOperation("删除量表")
    @GetMapping("/deleteScale")
    public ResponseData<Boolean> deleteScale(Integer scaleId){
        return new ResponseData<>(scaleService.deleteScale(scaleId));
    }

    @ApiOperation("获取量表下拉")
    @GetMapping("/getScaleListByType")
    public ResponseData<List<PsycScale>> getScaleListByType(Integer scaleType){
        return new ResponseData<>(scaleService.getScaleListByType(scaleType));
    }

    @ApiOperation("获取量表详情")
    @GetMapping("/getScaleDetail")
    public ResponseData<PsycScale> getScaleDetail(Integer scaleId){
        return new ResponseData<>(scaleService.getScaleDetail(scaleId));
    }

}
