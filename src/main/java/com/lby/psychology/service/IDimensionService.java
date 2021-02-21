package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycDimensionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionDimension;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycDimensionVo;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;

import java.util.List;

public interface IDimensionService {

    PageResult getDimensionPageList(PsycDimensionCo co);

    boolean addDimension(PsycQuestionDimension psycQuestionDimension);

    boolean updateDimension(PsycQuestionDimension psycQuestionDimension);

    PsycDimensionVo getDimensionDetail(Integer dimensionId);

    List<PsycDimensionVo> getDimensionListByScale(PsycScale co);

    PsycQuestionDetailVo getDimensionQuestionDetail(Integer dimensionId);

}
