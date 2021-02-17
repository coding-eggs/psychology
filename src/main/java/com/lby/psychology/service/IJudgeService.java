package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;


public interface IJudgeService {

    PageResult getJudgeList(PsycJudgeCo psycJudgeCo);

    boolean addJudge(PsycQuestionJudge judge);

    boolean deleteJudge(Integer judgeId);

    boolean updateJudge(PsycQuestionJudge judge);

    PsycQuestionJudge getJudgeInfo(Integer judgeId);
}
