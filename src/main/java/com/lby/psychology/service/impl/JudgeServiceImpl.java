package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionJudgeMapper;
import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.model.vo.PsycJudgeVo;
import com.lby.psychology.service.IJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JudgeServiceImpl implements IJudgeService {

    @Autowired
    private PsycQuestionJudgeMapper questionJudgeMapper;

    @Override
    public PageResult getJudgeList(PsycJudgeCo psycJudgeCo) {
        PageHelper.startPage(psycJudgeCo.getPageNum(),psycJudgeCo.getPageSize());
        PageInfo<PsycJudgeVo> pageInfo = new PageInfo<>(questionJudgeMapper.selectJudgeList(psycJudgeCo));
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    @Transactional
    public boolean addJudge(PsycJudgeVo judge) {
        questionJudgeMapper.insert(judge);
        return questionJudgeMapper.insertDimensionJudgeRlt(judge) > 0;
    }

    @Override
    public boolean deleteJudge(Integer judgeId) {
        return questionJudgeMapper.deleteByPrimaryKey(judgeId) > 0;
    }

    @Override
    public boolean updateJudge(PsycQuestionJudge judge) {
        return questionJudgeMapper.updateByPrimaryKeySelective(judge) > 0;
    }

    @Override
    public PsycJudgeVo getJudgeInfo(Integer judgeId) {
        return questionJudgeMapper.selectJudgeInfo(judgeId);
    }
}
