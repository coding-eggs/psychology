package com.lby.psychology.service.impl;

import com.lby.psychology.mapper.PsycQuestionMapper;
import com.lby.psychology.model.enums.EnumScaleType;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private PsycQuestionMapper questionMapper;


    @Override
    public List<PsycQuestionVo> getQuestionLikeSearch(String keyword) {
        List<PsycQuestionVo> list = questionMapper.selectQuestionLikeSearch(keyword);
        list.forEach(e->{
            e.setScaleTypeName(Objects.requireNonNull(EnumScaleType.getEnumById(e.getScaleCode())).getName());
        });
        return list;
    }
}
