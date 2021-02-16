package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionJudgeMapper;
import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.service.IJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImpl implements IJudgeService {

    @Autowired
    private PsycQuestionJudgeMapper questionJudgeMapper;

    @Override
    public PageResult getJudgeList(PsycJudgeCo psycJudgeCo) {
        PageHelper.startPage(psycJudgeCo.getPageNum(),psycJudgeCo.getPageSize());
        PageInfo<PsycQuestionJudge> pageInfo = new PageInfo<>(questionJudgeMapper.selectJudgeList(psycJudgeCo));
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public boolean addJudge(PsycQuestionJudge judge) {
        return questionJudgeMapper.insert(judge) > 0;
    }
}
