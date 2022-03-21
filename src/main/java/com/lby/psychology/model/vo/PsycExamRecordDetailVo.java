package com.lby.psychology.model.vo;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class PsycExamRecordDetailVo {

    private String nickname;

    private Long userId;

    private String scaleName;

    private LocalTime userTime;

    private List<PsycJudgeVo> judgeList;

    private List<PsycQuestionVo> questionList;
}
