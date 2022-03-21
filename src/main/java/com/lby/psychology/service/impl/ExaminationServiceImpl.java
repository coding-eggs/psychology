package com.lby.psychology.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.mapper.PsycExamRecordMapper;
import com.lby.psychology.mapper.PsycQuestionJudgeMapper;
import com.lby.psychology.mapper.PsycQuestionMapper;
import com.lby.psychology.mapper.PsycQuestionOptionsMapper;
import com.lby.psychology.model.co.PsycExamCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.enums.EnumScaleType;
import com.lby.psychology.model.pojo.PsycExamRecord;
import com.lby.psychology.model.vo.PsycExamRecordDetailVo;
import com.lby.psychology.model.vo.PsycExamRecordVo;
import com.lby.psychology.model.vo.PsycJudgeVo;
import com.lby.psychology.model.vo.PsycQuestionVo;
import com.lby.psychology.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExaminationServiceImpl implements IExaminationService {

    @Autowired
    private PsycQuestionMapper questionMapper;

    @Autowired
    private PsycQuestionOptionsMapper optionsMapper;

    @Autowired
    private PsycQuestionJudgeMapper psycQuestionJudgeMapper;

    @Autowired
    private PsycExamRecordMapper recordMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<PsycQuestionVo> getExamDetail(Integer scaleId) {
        //获取量表下的问题
        List<PsycQuestionVo> questionVoList = questionMapper.selectQuestionDetail(scaleId);
        questionVoList.forEach(e->{
            e.setOptionList(optionsMapper.selectOptionByQuestionId(e.getId()));
        });
        return questionVoList;
    }

    @Override
    public List<PsycJudgeVo> examScale(PsycExamCo co, Long userId) throws JsonProcessingException {

        List<PsycJudgeVo> result = new ArrayList<>();
        //计算答题,将list中的数据分组 将PsycQuestionVo按维度Dimension(维度ID）分组，value为一个list<PsycQuestionVo>
        Map<Integer,List<PsycQuestionVo>> map = co.getQuestionList().stream().
                collect(Collectors.groupingBy(PsycQuestionVo::getDimension));
        //按维度计算
        for(Map.Entry<Integer,List<PsycQuestionVo>> entry : map.entrySet()) {
            List<PsycQuestionVo> questionVoList = entry.getValue();
            //获取维度分数求和
            Integer score = questionVoList.stream()
                    .mapToInt(e-> e.getOptionList().
                            stream().
                            filter(v->v.getOptionCode().equals(e.getOptionCode())).
                            collect(Collectors.toList()).get(0).getOptionScore()).
                            sum();
            //判断该分数在哪个评判
            result.add(psycQuestionJudgeMapper.selectJudgeByScore(entry.getKey(),score));
        }
        //将结果存储到记录表
        PsycExamRecord record = new PsycExamRecord();
        record.setScaleId(co.getScaleId());
        record.setUserId(userId);
        //writeValueAsBytes将java对象转换成json字符串
        record.setJudgeContent(objectMapper.writeValueAsBytes(result));
        record.setRecordContent(objectMapper.writeValueAsBytes(co.getQuestionList()));
        record.setUseTime(LocalTime.parse(co.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss")));
        recordMapper.insertRecord(record);
        return result;
    }

    @Override
    public PageResult getCurrentUserRecord(PsycExamCo co) {

        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        List<PsycExamRecordVo> list = recordMapper.selectRecordListByUserId(co.getUserId());
        list.forEach(e->e.setScaleTypeName(EnumScaleType.getEnumById(e.getScaleType()).getName()));

        PageInfo<PsycExamRecordVo> pageInfo = new PageInfo<>(list);

        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public PsycExamRecordDetailVo getRecordDetail(Long recordId) throws IOException {

        PsycExamRecordVo recordVo = recordMapper.selectRecordById(recordId);
        PsycExamRecordDetailVo result = new PsycExamRecordDetailVo();
        result.setUserId(recordVo.getUserId());
        result.setNickname(recordVo.getNickName());
        result.setScaleName(recordVo.getScaleName());
        result.setUserTime(recordVo.getUseTime());

        result.setJudgeList( objectMapper.readValue(recordVo.getJudgeContent(), new TypeReference<>() {
        }));
        result.setQuestionList(objectMapper.readValue(recordVo.getRecordContent(), new TypeReference<>() {
        }));

        return result;
    }
}
