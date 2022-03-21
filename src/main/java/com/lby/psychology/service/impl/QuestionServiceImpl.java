package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionMapper;
import com.lby.psychology.mapper.PsycQuestionOptionsMapper;
import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private PsycQuestionMapper questionMapper;

    @Autowired
    private PsycQuestionOptionsMapper optionsMapper;

    @Override
    public List<PsycQuestionVo> getQuestionLikeSearch(String keyword) {
        return  questionMapper.selectQuestionLikeSearch(keyword);
    }

    @Override
    public PageResult getQuestionPageList(PsycQuestionCo co) {
        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        List<PsycQuestionVo> list = questionMapper.selectQuestionPageList(co);
        list.forEach(e ->{
            //获取相关选项
            e.setOptionList(optionsMapper.selectOptionByQuestionId(e.getId()));
        });

        PageInfo<PsycQuestionVo> pageInfo = new PageInfo<>(list);
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public boolean addQuestion(PsycQuestion vo) {
        return questionMapper.insertQuestion(vo) > 0;
    }

    @Override
    public boolean updateQuestion(PsycQuestion vo) {
        return questionMapper.updateByPrimaryKeySelective(vo) > 0;
    }

    @Override
    public boolean deleteQuestion(Integer questionId) {
        //删除相关选项
        optionsMapper.deleteOptionByQuestionId(questionId);
        return questionMapper.deleteByPrimaryKey(questionId) > 0;
    }

    @Override
    public PsycQuestionVo getQuestionInfo(Integer questionId) {
        return questionMapper.selectQuestionInfo(questionId);
    }
}
