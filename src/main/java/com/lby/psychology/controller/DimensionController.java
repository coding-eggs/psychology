package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycDimensionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionDimension;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycDimensionVo;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;
import com.lby.psychology.service.IDimensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "维度接口API")
@RestController
@RequestMapping("/dimension")
public class DimensionController {

    @Autowired
    private IDimensionService dimensionService;


    @ApiOperation("分页查询维度列表")
    @PostMapping("/getDimensionPageList")
    public ResponseData<PageResult> getDimensionPageList(@RequestBody PsycDimensionCo co){
        return new ResponseData<>(dimensionService.getDimensionPageList(co));
    }


    @ApiOperation("新增维度")
    @PostMapping("/addDimension")
    public ResponseData<Boolean> addDimension(@RequestBody PsycQuestionDimension co){
        return new ResponseData<>(dimensionService.addDimension(co));
    }

    @ApiOperation("编辑维度")
    @PostMapping("/updateDimension")
    public ResponseData<Boolean> updateDimension(@RequestBody PsycQuestionDimension co){
        return new ResponseData<>(dimensionService.updateDimension(co));
    }

    @ApiOperation("获取维度详情")
    @GetMapping("/getDimensionDetail")
    public ResponseData<PsycDimensionVo> getDimensionDetail(Integer dimensionId){
        return new ResponseData<>(dimensionService.getDimensionDetail(dimensionId));
    }


    @ApiOperation("获取维度相关问题评判详情")
    @GetMapping("/getDimensionQuestionDetail")
    public ResponseData<PsycQuestionDetailVo> getDimensionQuestionDetail(Integer dimensionId){
        return new ResponseData<>(dimensionService.getDimensionQuestionDetail(dimensionId));
    }

    @ApiOperation("获取维度下拉")
    @PostMapping("/getDimensionListByScale")
    public ResponseData<List<PsycDimensionVo>> getDimensionListByScale(@RequestBody PsycScale co){
        return new ResponseData<>(dimensionService.getDimensionListByScale(co));
    }

}
