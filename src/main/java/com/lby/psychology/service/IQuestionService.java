package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestion;
import com.lby.psychology.model.vo.PsycQuestionVo;

import java.util.List;

public interface IQuestionService {

    /**
    * input模糊搜索问题
    * @author lby
    * @param keyword 模糊搜索关键字
    * @date 2021/2/17 20:39
    * @return java.util.List<com.lby.psychology.model.vo.PsycQuestionVo>
    */
    List<PsycQuestionVo> getQuestionLikeSearch(String keyword);

    /**
    * 分页查询问题列表
    * @author lby
    * @param co 查询参数
    * @date 2021/2/22 21:27
    * @return com.lby.psychology.model.common.PageResult
    */
    PageResult getQuestionPageList(PsycQuestionCo co);

    /**
    * 新增问题
    * @author lby
    * @param vo 新增数据
    * @date 2021/2/22 22:31
    * @return boolean
    */
    boolean addQuestion(PsycQuestion vo);

    /**
    * 更新问题接口
    * @author lby
    * @param vo 更新问题得参数
    * @date 2021/2/22 22:53
    * @return boolean
    */
    boolean updateQuestion(PsycQuestion vo);

    /**
    * 查询问题详情
    * @author lby
    * @param questionId 问题id
    * @date 2021/2/22 22:59
    * @return com.lby.psychology.model.vo.PsycQuestionVo
    */
    PsycQuestionVo getQuestionInfo(Integer questionId);
}
