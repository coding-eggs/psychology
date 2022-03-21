package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycExamRecord;
import com.lby.psychology.model.vo.PsycExamRecordVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface PsycExamRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(PsycExamRecord record);

    int insertSelective(PsycExamRecord record);

    int insertRecord(PsycExamRecord record);

    PsycExamRecord selectByPrimaryKey(Long recordId);

    int updateByPrimaryKeySelective(PsycExamRecord record);

    int updateByPrimaryKeyWithBLOBs(PsycExamRecord record);

    int updateByPrimaryKey(PsycExamRecord record);

    int deleteRecordByUserId(@Param("userId") Long userId);

    List<PsycExamRecordVo> selectRecordListByUserId(@Param("userId") Long userId);

    PsycExamRecordVo selectRecordById(@Param("recordId") Long recordId);

    Integer selectRecordTotalByScaleType(@Param("scaleType") Integer scaleType);


    BigDecimal selectLastWeekIncreRate(@Param("scaleType")Integer scaleType);


}
