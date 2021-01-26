package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycPermission;
import com.lby.psychology.model.security.SecurityRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PsycPermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(PsycPermission record);

    int insertSelective(PsycPermission record);

    PsycPermission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(PsycPermission record);

    int updateByPrimaryKey(PsycPermission record);

    List<SecurityRolePermission> selectRolePermission();
}
