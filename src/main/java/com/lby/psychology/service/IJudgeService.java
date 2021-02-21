package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;
import com.lby.psychology.model.vo.PsycJudgeVo;


public interface IJudgeService {

    PageResult getJudgeList(PsycJudgeCo psycJudgeCo);

    boolean addJudge(PsycJudgeVo judge);

    boolean deleteJudge(Integer judgeId);

    boolean updateJudge(PsycQuestionJudge judge);

    PsycJudgeVo getJudgeInfo(Integer judgeId);
}
