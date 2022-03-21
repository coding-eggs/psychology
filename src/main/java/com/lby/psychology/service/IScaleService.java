package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycQuestionCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycScale;
import com.lby.psychology.model.vo.PsycQuestionDetailVo;

import java.util.List;

public interface IScaleService {

    /**
     * 获取量表列表
     * @author lby
     * @param co 筛选参数
     * @date 2021/2/17 20:40
     * @return com.lby.psychology.model.common.PageResult
     */
    PageResult getScalePageList(PsycQuestionCo co);

    /**
     * 根据量表编码获取量表详情
     * @author lby
     * @param scaleCode 量表code
     * @date 2021/2/18 15:06

     */
    List<PsycQuestionDetailVo> getScaleQuestionDetail(Integer scaleCode);

    /**
     * 新增量表
     * @author lby
     * @param co 量表信息
     * @date 2021/2/20 11:04
     * @return boolean
     */
    boolean addScale(PsycScale co);

    /**
    * 更新量表信息
    * @author lby
    * @param psycScale 更新参数
    * @date 2021/2/22 20:58
    * @return boolean
    */
    boolean updateScale(PsycScale psycScale);

    /**
     * 删除量表
     * @param scaleId 量表id
     */
    boolean deleteScale(Integer scaleId);

    /**
    * 获取量表下拉
    * @author lby
    * @param scaleType 量表类型
    * @date 2021/2/22 20:58
    * @return java.util.List<com.lby.psychology.model.pojo.PsycScale>
    */
    List<PsycScale> getScaleListByType(Integer scaleType);

    /**
     * 获取量表详情
     * @author lby
     * @param scaleId 量表id
     * @date 2021/2/20 15:10
     * @return com.lby.psychology.model.pojo.PsycScale
     */
    PsycScale getScaleDetail(Integer scaleId);
}
