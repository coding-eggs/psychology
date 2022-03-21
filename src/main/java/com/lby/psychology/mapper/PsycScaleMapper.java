package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycScale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PsycScaleMapper {
    int deleteByPrimaryKey(Integer scaleId);

    int insert(PsycScale record);

    int insertSelective(PsycScale record);

    PsycScale selectByPrimaryKey(Integer scaleId);

    int updateByPrimaryKeySelective(PsycScale record);

    int updateByPrimaryKey(PsycScale record);

    List<Integer> selectDimensionIdByScaleId(@Param("scaleId") Integer scaleId);

    List<PsycScale> selectScaleListByType(@Param("type") Integer type);

}
