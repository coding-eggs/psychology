package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;
import com.lby.psychology.model.vo.PsycQuestionVo;

import java.util.List;
import java.util.Map;

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
    * 获取问题列表
    * @author lby
    * @param co 筛选参数
    * @date 2021/2/17 20:40
    * @return com.lby.psychology.model.common.PageResult
    */
    PageResult getQuestionPageList(PsycQuestionCo co);



    /**
    * 根据量表编码获取量表详情
    * @author lby
    * @param scaleCode 量表code
    * @date 2021/2/18 15:06

    */
    List<PsycQuestionDetailVo> getQuestionDetail(Integer scaleCode);


    /**
    * 新增量表
    * @author lby
    * @param co 量表信息
    * @date 2021/2/20 11:04
    * @return boolean
    */
    boolean addScale(PsycScale co);

    /**
    * 获取量表详情
    * @author lby
    * @param scaleId 量表id
    * @date 2021/2/20 15:10
    * @return com.lby.psychology.model.pojo.PsycScale
    */
    PsycScale getScaleDetail(Integer scaleId);


    boolean updateScale(PsycScale psycScale);
}
