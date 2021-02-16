package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycJudgeCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionJudge;

import java.util.List;

public interface IJudgeService {

    PageResult getJudgeList(PsycJudgeCo psycJudgeCo);

    boolean addJudge(PsycQuestionJudge judge);
}
