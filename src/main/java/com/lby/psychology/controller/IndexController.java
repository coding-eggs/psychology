package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;

import com.lby.psychology.model.vo.PsycDashboardVo;
import com.lby.psychology.service.IDashboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "大屏接口")
@RestController
@RequestMapping("/dashboard")
public class IndexController {

    @Autowired
    private IDashboardService dashboardService;


    @ApiOperation(value = "大屏展示")
    @GetMapping("/display")
    public ResponseData<PsycDashboardVo> display(){
        return new ResponseData<>(dashboardService.dashboardDisplay());
    }



}
