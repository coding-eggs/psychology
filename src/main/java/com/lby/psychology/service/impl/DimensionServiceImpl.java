package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionDimensionMapper;
import com.lby.psychology.model.co.PsycDimensionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.enums.EnumScaleCategories;
import com.lby.psychology.model.pojo.PsycQuestionDimension;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycDimensionVo;
import com.lby.psychology.service.IDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DimensionServiceImpl implements IDimensionService {

    @Autowired
    private PsycQuestionDimensionMapper dimensionMapper;

    @Override
    public PageResult getDimensionPageList(PsycDimensionCo co) {
        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        List<PsycDimensionVo> list = dimensionMapper.selectDimensionPageList(co);
        list.forEach(e ->{
            e.setScaleTypeName(Objects.requireNonNull(EnumScaleCategories.getEnumById(e.getScaleType())).getName());
        });

        PageInfo<PsycDimensionVo> pageInfo = new PageInfo<>(list);
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public boolean addDimension(PsycQuestionDimension psycQuestionDimension) {
        return dimensionMapper.insertDimension(psycQuestionDimension) > 0;
    }

    @Override
    public boolean updateDimension(PsycQuestionDimension psycQuestionDimension) {
        return dimensionMapper.updateByPrimaryKeySelective(psycQuestionDimension) > 0;
    }

    @Override
    public PsycDimensionVo getDimensionDetail(Integer dimensionId) {
        return dimensionMapper.selectDimensionDetail(dimensionId);
    }

    @Override
    public List<PsycDimensionVo> getDimensionListByScale(PsycScale co) {
        return dimensionMapper.selectDimensionListByScale(co);
    }
}
