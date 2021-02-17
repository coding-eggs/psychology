package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionOptionsMapper;
import com.lby.psychology.model.co.PsycOptionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionOptions;
import com.lby.psychology.model.vo.PsycQuestionOptionsVo;
import com.lby.psychology.service.IOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements IOptionService {

    @Autowired
    private PsycQuestionOptionsMapper questionOptionsMapper;


    @Override
    public PageResult getOptionPageList(PsycOptionCo co) {
        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        PageInfo<PsycQuestionOptionsVo> pageInfo = new PageInfo<>(questionOptionsMapper.selectOptionListPage(co));
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public PsycQuestionOptionsVo getOptionInfo(Integer optionId) {
        return questionOptionsMapper.selectOptionInfo(optionId);
    }

    @Override
    public boolean addOption(PsycOptionCo co) {
        return questionOptionsMapper.insertOption(co) > 0;
    }

    @Override
    public boolean deleteOption(Integer optionId) {
        return questionOptionsMapper.deleteByPrimaryKey(optionId) > 0;
    }

    @Override
    public boolean updateOption(PsycQuestionOptions options) {
        return questionOptionsMapper.updateByPrimaryKeySelective(options) > 0;
    }


}
