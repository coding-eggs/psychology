package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycQuestionJudgeMapper;
import com.lby.psychology.mapper.PsycQuestionMapper;
import com.lby.psychology.mapper.PsycQuestionOptionsMapper;
import com.lby.psychology.mapper.PsycScaleMapper;
import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.enums.EnumScaleCategories;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IDimensionService;
import com.lby.psychology.service.IScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScaleServiceImpl implements IScaleService {

    @Autowired
    private PsycQuestionMapper questionMapper;

    @Autowired
    private PsycQuestionJudgeMapper questionJudgeMapper;

    @Autowired
    private PsycQuestionOptionsMapper optionsMapper;

    @Autowired
    private PsycScaleMapper scaleMapper;

    @Autowired
    private IDimensionService dimensionService;


    @Override
    public PageResult getScalePageList(PsycQuestionCo co) {
        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        List<PsycQuestionVo> list = questionMapper.selectQuestionScale(co);
        list.forEach(e ->{
            e.setScaleTypeName(Objects.requireNonNull(EnumScaleCategories.getEnumById(e.getScaleType())).getName());
        });

        PageInfo<PsycQuestionVo> pageInfo = new PageInfo<>(list);
        return PageResult.getPageResult(pageInfo);
    }


    @Override
    public List<PsycQuestionDetailVo> getScaleQuestionDetail(Integer scaleCode) {
        List<PsycQuestionDetailVo> result = new ArrayList<>();
        List<PsycQuestionVo> list = questionMapper.selectQuestionDetail(scaleCode);
        list.forEach(e ->{
            e.setScaleTypeName(Objects.requireNonNull(EnumScaleCategories.getEnumById(e.getScaleType())).getName());
        });
        Map<Integer,List<PsycQuestionVo>> map = list.stream().collect(Collectors.groupingBy(PsycQuestionVo::getDimension));
        //按每个维度查询数据
        for(Map.Entry<Integer,List<PsycQuestionVo>> e : map.entrySet()){
            PsycQuestionDetailVo questionDetailVo = new PsycQuestionDetailVo();
            List<PsycQuestionVo> questionVoList = e.getValue();
            questionVoList.forEach(v ->{
                //查询问题得选项
                v.setOptionList(optionsMapper.selectOptionByQuestionId(v.getId()));
            });
            questionDetailVo.setDetailList(questionVoList);
            questionDetailVo.setDimensionId(e.getKey());
            questionDetailVo.setDimensionName(e.getValue().get(0).getDimensionName());
            questionDetailVo.setJudgeList(questionJudgeMapper.selectJudgeListByDimension(e.getKey()));
            result.add(questionDetailVo);
        }
        return result;
    }

    @Override
    public boolean addScale(PsycScale co) {
        return questionMapper.insertScale(co) > 0;
    }

    @Override
    public boolean updateScale(PsycScale psycScale) {
        return scaleMapper.updateByPrimaryKeySelective(psycScale) > 0;
    }

    @Override
    public boolean deleteScale(Integer scaleId) {
        //删除量表相关维度
        scaleMapper.selectDimensionIdByScaleId(scaleId).forEach(e->dimensionService.deleteDimension(e));
        //删除量表
        return scaleMapper.deleteByPrimaryKey(scaleId) > 0;
    }

    @Override
    public List<PsycScale> getScaleListByType(Integer scaleType) {
        return scaleMapper.selectScaleListByType(scaleType);
    }

    @Override
    public PsycScale getScaleDetail(Integer scaleId) {
        return scaleMapper.selectByPrimaryKey(scaleId);
    }
}
