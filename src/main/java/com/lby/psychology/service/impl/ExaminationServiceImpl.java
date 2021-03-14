package com.lby.psychology.service.impl;

import com.lby.psychology.mapper.PsycQuestionMapper;
import com.lby.psychology.mapper.PsycQuestionOptionsMapper;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements IExaminationService {

    @Autowired
    private PsycQuestionMapper questionMapper;

    @Autowired
    private PsycQuestionOptionsMapper optionsMapper;

    @Override
    public List<PsycQuestionVo> getExamDetail(Integer scaleId) {
        //获取量表下的问题
        List<PsycQuestionVo> questionVoList = questionMapper.selectQuestionDetail(scaleId);
        questionVoList.forEach(e->{
            e.setOptionList(optionsMapper.selectOptionByQuestionId(e.getId()));
        });
        return questionVoList;
    }
}
