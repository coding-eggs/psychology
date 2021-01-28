package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUserRoleRlt;
import com.lby.psychology.model.vo.RolePermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(PsycRole record);

    int insertSelective(PsycRole record);

    PsycRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(PsycRole record);

    int updateByPrimaryKey(PsycRole record);

    List<PsycRole> selectPsycRoleListByUserId(@Param("userId") Long userId);

    List<PsycRole> selectDefaultRoleList();

    int insertRoleUserRlt(List<PsycUserRoleRlt> list);

    List<RolePermissionVo> selectRolePermission(List<Integer> list);
}
