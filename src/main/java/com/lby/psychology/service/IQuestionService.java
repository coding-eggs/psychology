package com.lby.psychology.service;

import com.lby.psychology.model.vo.PsycQuestionVo;

import java.util.List;

public interface IQuestionService {


    List<PsycQuestionVo> getQuestionLikeSearch(String keyword);

}
