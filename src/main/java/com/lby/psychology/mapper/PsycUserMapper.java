package com.lby.psychology.mapper;

import com.lby.psychology.model.pojo.PsycUser;
import com.lby.psychology.model.security.SecurityPsycUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PsycUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(PsycUser record);

    int insertSelective(PsycUser record);

    PsycUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(PsycUser record);

    int updateByPrimaryKey(PsycUser record);

    //根据用户名（邮箱）查询用户
    SecurityPsycUser selectPsycUserByEmail(String email);

    //查询openId是否存在
    int selectCountByOpenId (@Param("openId") String openId);

    //更新qq登录信息
    int updatePsycUserQQLogin(SecurityPsycUser user);

    //插入qq登录信息
    int insertPsycUserQQLogin(SecurityPsycUser user);

    //根据openid查询用户信息
    SecurityPsycUser selectUserByOpenId(String openId);

    //更新最后登录时间
    int updateLastLoginDate(Long userId);
}
