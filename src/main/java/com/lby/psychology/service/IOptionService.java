package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycOptionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycQuestionOptions;
import com.lby.psychology.model.vo.PsycQuestionOptionsVo;

public interface IOptionService {

    /**
    * 分页获取选项列表
    * @author lby
    * @param co 分页参数
    * @date 2021/2/16 21:17
    * @return com.lby.psychology.model.common.PageResult
    */
    PageResult getOptionPageList(PsycOptionCo co);


    /**
    * 获取选项详情
    * @author lby
    * @param optionId 选项id
    * @date 2021/2/16 21:31
    * @return com.lby.psychology.model.vo.PsycQuestionOptionsVo
    */
    PsycQuestionOptionsVo getOptionInfo(Integer optionId);


    boolean addOption(PsycOptionCo co);

    /**
    * 删除选项
    * @author lby
    * @param optionId 选项id
    * @date 2021/2/16 21:38
    * @return boolean
    */
    boolean deleteOption(Integer optionId);

    /**
    * 更新选项信息
    * @author lby
    * @param options 选项信息
    * @date 2021/2/16 21:39
    * @return boolean
    */
    boolean updateOption(PsycQuestionOptions options);

}
